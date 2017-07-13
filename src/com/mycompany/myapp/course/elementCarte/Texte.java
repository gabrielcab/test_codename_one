package com.mycompany.myapp.course.elementCarte;

import com.codename1.ui.Dialog;

/**
 * Created by cashexpress on 06/07/2017.
 */
public class Texte extends ElementCarte {
    private String data;
    public Texte(String titre,String data){
        super.titre=titre;
        this.data=data;
    }

    @Override
    public String getTypeElement(){
        return "Texte";
    }

    @Override
    public String showData(){
        return data;
    }

}
