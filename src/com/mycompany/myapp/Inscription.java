package com.mycompany.myapp;

import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;

/**
 * Created by cashexpress on 08/07/2017.
 */
public class Inscription extends Form {
    private Resources res;
    public Inscription(Resources res){
        super("Inscription");
        this.res =res;
        setLayout(new BorderLayout());
        this.setUIID("SignUpForm");
        Container haut =new Container(new FlowLayout(Component.CENTER));
        Button photoButton = new Button("",res.getImage("photoButton.png"));
        photoButton.setUIID("PhotoButton");
        haut.addComponent(photoButton);
        this.addComponent(BorderLayout.NORTH,haut);
        Container center = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        center.setUIID("SignUpCenter");

        Container row1 = new Container(new GridLayout(1,2));
        TextField prenom = new TextField();
        prenom.setHint("Prénom, ex :Luz");

        TextField nom = new TextField();
        nom.setHint("Nom, ex : Berrien");

        prenom.setUIID("SignUpField");
        nom.setUIID("SignUpField");

        row1.addComponent(prenom);
        row1.addComponent(nom);

        row1.setHeight(3);
        center.addComponent(row1);
        center.setScrollableY(false);

        TextField email = new TextField();
        center.addComponent(email);
        email.setHint("Email, ex : luz-berrien91@sfr.fr");

        email.setUIID("SignUpField");



        TextField password = new TextField();
        password.setConstraint(TextField.PASSWORD);
        center.addComponent(password);
        password.setHint("Mot De Passe");
        password.setUIID("SignUpField");

        Container row4 = new Container(new BorderLayout());
        Label code = new Label ("+33 (0)");
        row4.addComponent(BorderLayout.WEST,code);
        code.setUIID("SignUpField");

        TextField phoneNumber = new TextField();
        phoneNumber.setConstraint(TextField.PHONENUMBER);
        row4.addComponent(BorderLayout.CENTER,phoneNumber);
        phoneNumber.setHint("Numéro de téléphone");

        phoneNumber.setUIID("SignUpField");

        center.addComponent(row4);






        this.addComponent(BorderLayout.CENTER,center);

        Button getStarted = new Button("Get Started",res.getImage("flechedr.png"));
        getStarted.setGap(getStarted.getStyle().getFont().getHeight());
        getStarted.setUIID("SignUpButton");

        getStarted.setTextPosition(Component.LEFT);
        getStarted.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new MesCartes(new Inscription(res),res).show();
            }
        });

        this.addComponent(BorderLayout.SOUTH, getStarted);

        this.setBackCommand(new Command("Back", res.getImage("flechedr.png")) {
            public void actionPerformed(ActionEvent ev) {
                // notice that when showing a previous form it is best to use showBack() so the
                // transition runs in reverse
            }
        });


    }
}
