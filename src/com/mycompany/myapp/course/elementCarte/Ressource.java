package com.mycompany.myapp.course.elementCarte;

import com.codename1.ui.Dialog;

/**
 * Created by cashexpress on 08/07/2017.
 */
public class Ressource extends ElementCarte {
    private static final String[] TYPE = {"image", "video", "audio"};
    private String path;
    private String type;
    private int scale;

    public Ressource(String titre,String type, String path, int scale){
        super.titre=titre;
        this.path=path;
        this.scale=scale;
        this.type=type;
    }

    public String getURL(){
        return path;
    }
    public String getTypeRessource(){
        return type;
    }

    @Override
    public String showData(){
        return getURL();
    }

    @Override
    public String getTypeElement(){
        return "Ressource";
    }
}
