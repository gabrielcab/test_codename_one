package com.mycompany.myapp.course;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cashexpress on 06/07/2017.
 */
public class DiscaFactory {
    private static Form retour;
    private static List<Card> listeCartes = new ArrayList<Card>();

    public static List<Button> listerCartes(){
        List<Button> resu=new ArrayList<Button>();
        if(resu.size()>0){
            Dialog.show("probleme", "init","liste","liste");
        }
        Button b;
        for(Card e : listeCartes){
            b =new Button(e.getId());
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    new CarteSimpleForm(e,retour).show();
                }
            });
            resu.add(b);

        }
        return resu;
    }
    public static Form getRetour(){
        return retour;
    }
    public void afficherCarte(String id){
        Card cur;
        for(Card e : listeCartes){
            if(e.getId().equals(id)){
                cur=e;
                break;
            }

        }
    }

    public static void ajouterCarte(String path, Resources res){

       // try {
           // listeCartes.add(CreationCarteParser.ajouterCarte(path));
            listeCartes.add(new CreationCarteParserCNO().ajouterCarte(path,res));
       // } catch (FileNotFoundException e) {
        //    e.printStackTrace();
        //}
    }

    public DiscaFactory(Form retour,Resources res){
        this.retour=retour;
        ajouterCarte("carte.xml",res);
        ajouterCarte("carte2.xml",res);
        ajouterCarte("carte3.xml",res);
        ajouterCarte("carte4.xml",res);
        ajouterCarte("carte5.xml",res);
        ajouterCarte("carte6.xml",res);
        ajouterCarte("carte7.xml",res);
        ajouterCarte("carte8.xml",res);
        ajouterCarte("carte9.xml",res);
        ajouterCarte("carte10.xml",res);


    }
}
