package com.mycompany.myapp.course;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.io.Storage;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Inscription;
import com.mycompany.myapp.MesCartes;
import com.mycompany.myapp.course.elementCarte.ElementCarte;
import com.mycompany.myapp.course.elementCarte.Ressource;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by cashexpress on 08/07/2017.
 */
public class CarteSimpleForm extends Form {
    private Form retour;
    public CarteSimpleForm(Card c,Form retour){

        super(c.getId());
        this.retour =retour;
        setLayout(new BorderLayout());
        Container recto = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container verso = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        Button afficherVerso =new Button("afficherVerso");
        for(ElementCarte ec : c.getRecto()){
            if(ec.getTypeElement().equals("Texte")){
                recto.addComponent(new Label(ec.showData()));
            }
            if(ec.getTypeElement().equals("Ressource")){

                Ressource currentImage = (Ressource) ec;
                if(currentImage.getTypeRessource().equals("image")){
                    try {

                        /*tu peux faire un appel a ta fonction ici, l'url de la ressource est dans currentImage.getURL();
                        * InputStream is = TaClasse.getInputStream(currentImage.getURL());
                        *
                        * */


                        InputStream is =  Storage.getInstance().createInputStream("DSC0078.JPG");
                        EncodedImage i = EncodedImage.create(is, is.available());

                        ImageViewer imf= new ImageViewer(i);
                        imf.setUIID("ImageViewerUIID");
                       recto.addComponent(imf);

                       // this.add(BorderLayout.CENTER,imf);


                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            }

        }

        for(ElementCarte ec : c.getVerso()){
            if(ec.getTypeElement().equals("Texte")){
                verso.addComponent(new Label(ec.showData()));
            }

        }
        recto.addComponent(afficherVerso);
        this.addComponent(BorderLayout.NORTH,recto);
       // this.addComponent(afficherVerso);
        verso.setHidden(true);
        this.addComponent(BorderLayout.SOUTH,verso);
        afficherVerso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if(afficherVerso.getText().equals("afficherVerso")) {
                    verso.setHidden(false);
                    afficherVerso.setText("cacherVerso");
                    revalidate();
                }
                else{
                    verso.setHidden(true);
                    afficherVerso.setText("afficherVerso");
                    revalidate();
                }
            }
        });

        Button getStarted = new Button("retour");
        getStarted.setGap(getStarted.getStyle().getFont().getHeight());
        getStarted.setUIID("SignUpButton");

        getStarted.setTextPosition(Component.LEFT);
        getStarted.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                retour.show();
            }
        });

        verso.addComponent(getStarted);

    }


}
