package com.mycompany.myapp.course;

import com.codename1.io.Log;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.tree.Tree;
import com.codename1.ui.tree.TreeModel;
import com.codename1.ui.util.Resources;
import com.codename1.xml.Element;
import com.codename1.xml.XMLParser;
import com.mycompany.myapp.course.elementCarte.ElementCarte;
import com.mycompany.myapp.course.elementCarte.Ressource;
import com.mycompany.myapp.course.elementCarte.Texte;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;

/**
 * Created by cashexpress on 07/07/2017.
 */



public class CreationCarteParserCNO {
    class XMLTreeModel implements TreeModel {
        private Element root;
        public XMLTreeModel(Element e) {
            root = e;
        }


        public Vector getChildren(Object parent) {
            if(parent == null) {
                Vector c = new Vector();
                c.addElement(root);
                return c;
            }
            Vector result = new Vector();
            Element e = (Element)parent;
            for(int iter = 0 ; iter < e.getNumChildren() ; iter++) {
                result.addElement(e.getChildAt(iter));
            }
            return result;
        }

        public boolean isLeaf(Object node) {
            Element e = (Element)node;
            return e.getNumChildren() == 0;
        }
    }



    public Card ajouterCarte(String path, Resources res){
        List<ElementCarte> recto=new ArrayList<ElementCarte>();
        List<ElementCarte> verso=new ArrayList<ElementCarte>();
        List<ElementCarte> currentList=recto;
        Card resu=null;
        int cpt=1;
        InputStream testInput = res.getData(path);
        try(Reader r = new InputStreamReader(testInput, "UTF-8")) {
            Element e = new XMLParser().parse(r);

            Iterator it = e.iterator();
            XMLTreeModel tree = new XMLTreeModel(e);
            Vector<Element> childs = tree.getChildren(e);

           for(Element elem :childs){
               //Dialog.show(elem.getTagName(),""+cpt,"suivant","cancel");
              // cpt++;
               if(elem.getTagName().equals("titre")){
                   Vector v = elem.getTextDescendants(null, false, 1);
                   if(v != null && v.size() > 0) {
                       Element actual = (Element)v.get(0);
                       String text = actual.getText();
                       resu = new Card(text,new Metadata());
                   }

               }
               if(elem.getTagName().equals("recto")){
                   recto=parcours(new XMLTreeModel(new Element("")).getChildren(elem));
                   cpt=1;
               }
               if(elem.getTagName().equals("verso")){
                   verso=parcours(new XMLTreeModel(new Element("")).getChildren(elem));;
                   cpt=1;
               }
               if(elem.getTagName().equals("texte")){
                 //  Dialog.show("texte",elem.toString(),"suivant","cancel");
                   currentList.add(new Texte(Integer.toString(cpt),elem.getText()));
                   cpt++;
               }
            }
        } catch(IOException err) {
            Log.e(err);
        }
        resu.setRecto(recto);
        resu.setVerso(verso);
        return resu;
    }


    private List<ElementCarte> parcours(Vector<Element> v){
        List<ElementCarte> liste = new ArrayList<>();
        if(v.size()==0){
            return null;
        }
        for(Element et : v){
            //Dialog.show("et",et.toString(),"suivant","cancel");
            if(et.getNumChildren()>0){
                if(et.getTagName().equals("res")){
                    //Dialog.show("res",et.toString(),"suivant","cancel");
                    List<ElementCarte> liste2 = gererRessource(et);
                    if(liste2!=null){
                        liste.addAll(liste2);
                    }

                }
                List<ElementCarte> liste2 = parcours(new XMLTreeModel(new Element("")).getChildren(et));
                if(liste2!=null){
                    liste.addAll(liste2);
                }
            }
            else{

             if(et.isTextElement()){
                // Dialog.show("texte",et.getText(),"suivant","cancel");
                 liste.add(new Texte(Integer.toString(liste.size()+1),et.getText()));
             }
             else{

             }
            }
        }
        return liste;
    }

    private List <ElementCarte> gererRessource(Element elem){
        List<ElementCarte> liste = new ArrayList<>();
        XMLTreeModel tree = new XMLTreeModel(new Element(""));
        Vector<Element> vec = tree.getChildren(elem);
        for(Element e : vec ){
            if(e.getTagName().equals("img")){
                liste.add(new Ressource("res","image",e.getAttribute("url"),0));
            }
            if(e.getTagName().equals("audio")){
                liste.add(new Ressource("res-audio","audio",e.getAttribute("url"),0));
            }
            if(e.getTagName().equals("video")){
                liste.add(new Ressource("res-video","video",e.getAttribute("url"),0));
            }
        }
        return liste;
    }

    /*public Card ajouterCarte(String path,Resources res) throws FileNotFoundException {
        List<ElementCarte> recto=new ArrayList<ElementCarte>();
        List<ElementCarte> verso=new ArrayList<ElementCarte>();
        List<ElementCarte> currentList=recto;
        Card cur =null;
        try {
            File inputFile = new File(path);
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            InputStream testInput = res.getData(path);
            Document doc = dBuilder.parse(testInput);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :"
                    + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("*");
            cur = new Card(doc.getElementsByTagName("titre").item(0).getTextContent(),new Metadata());
            for (int i = 1; i < nodeList.getLength(); i++) {
                //Dialog.show(Integer.toString(i),Integer.toString(nodeList.getLength()),"suivant", "Cancel");
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    // do something with the current element
                    Element eElement = (Element) node;
                    if(eElement.getTagName().equals("verso")){
                        currentList=verso;
                    }
                    addAllIfNotNull(currentList,gererTag(eElement,recto.size()+verso.size()));
                    //Dialog.show(((Element) node).getTagName(), node.getTextContent(), "suivant", "Cancel");

                   // System.out.println(node.getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        cur.setRecto(recto);
        cur.setVerso(verso);
        return cur;
    }

    private static List<ElementCarte> gererTag(Element node, int cpt){
        List<ElementCarte> l=new ArrayList<ElementCarte>();
        int cpt2=cpt+1;
        if (node.getTagName().equals("texte")){
            String s;
            NodeList fils =node.getChildNodes();
                    for(int i=0;i<fils.getLength();i++){
                        s =fils.item(i).getTextContent();
                        if (s.trim().replaceAll("\n ", "").length()>0) {//si s ne contient pas que des espaces et line break
                            l.add(new Texte(Integer.toString(cpt2),s));
                            cpt2++;
                          //  Dialog.show("texte",s,"suivante","cancel");
                        }
                    }
            return l;
        }
        if (node.getTagName().equals("res")){
            NodeList fils =node.getChildNodes();
            for(int i=0;i<fils.getLength();i++) {
                Node item = fils.item(i);
                if (item.getNodeType() == Node.ELEMENT_NODE) {
                    Element el = (Element) item;
                    if(el.getTagName().equals("img")){
                        l.add(new Ressource( Integer.toString(cpt2),"image",el.getAttribute("url"),1 ));//gerer type non connu, faute de frappe url etc
                    }
                    if(el.getTagName().equals("audio")){
                        l.add(new Ressource( Integer.toString(cpt2),"audio",el.getAttribute("url"),1 ));//gerer type non connu, faute de frappe url etc
                    }
                    if(el.getTagName().equals("video")){
                        l.add(new Ressource( Integer.toString(cpt2),"video",el.getAttribute("url"),1 ));//gerer type non connu, faute de frappe url etc
                    }
                   // Dialog.show("Ressource",l.get(l.size()-1).showData(),"suivante","cancel");
                }
            }


        }

        return l;
    }

    */
    public static <E> void addAllIfNotNull(List<E> list, Collection<? extends E> c) {
        if (c != null) {
            list.addAll(c);

        }
    }

}
