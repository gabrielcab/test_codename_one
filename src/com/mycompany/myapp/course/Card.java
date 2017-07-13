package com.mycompany.myapp.course;

import com.mycompany.myapp.course.elementCarte.ElementCarte;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cashexpress on 06/07/2017.
 */
public class Card {
    private String id;
    private Metadata metadata;

    private List<ElementCarte> recto;
    private List<ElementCarte> verso;

    public String getId(){
        return id;
    }

    public Card(String id, Metadata md){
        this.id=id;
        metadata = md;
    }

    public void setRecto(List<ElementCarte> l){
        recto = l;
    }
    public void setVerso(List<ElementCarte> l){
        verso = l;
    }

    public List<ElementCarte> getRecto(){
        return recto;
    }
    public List<ElementCarte> getVerso(){
        return verso;
    }
}
