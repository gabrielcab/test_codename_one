package com.mycompany.myapp;

import com.codename1.components.FloatingActionButton;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.course.DiscaFactory;

import java.util.List;

/**
 * Created by cashexpress on 06/07/2017.
 */
public class MesCartes extends Form {
    private Resources res;
    public MesCartes(Form retour,Resources res){
        super("Mes Cartes");

        this.res=res;
        this.setUIID("MesCartesForm");

        setLayout(new BorderLayout());

        Container haut =new Container(new FlowLayout(Component.CENTER));


        haut.setUIID("MesCartesHaut");

        new DiscaFactory(this,res);//moment de l'ajout des cartes
        Container center = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Button recherche = new Button("recherche");
        center.addComponent(haut);
      //  banniere.addComponent(recherche);

        //DiscaFactory.ajouterCarte("/Users/cashexpress/Downloads/Cartes/carte01/carte.xml");

        List<Button> listeBoutons= DiscaFactory.listerCartes();
        center.setUIID("MesCartesCenter");
        for(Button b : listeBoutons){
            b.setUIID("CardButton");
           // this.addComponent(b);
            center.addComponent(b);
        }
        this.addComponent(BorderLayout.CENTER,center);

    }

}
