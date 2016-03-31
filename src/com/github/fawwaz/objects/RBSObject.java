/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.fawwaz.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Asus
 */
public class RBSObject {
    public boolean isPositive=true;
    public String name;
    public ArrayList<Pair<String,String>> attributes;

    public RBSObject() {
        attributes = new ArrayList<>();
    }    
    
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Informasi objek \n");
        sb.append("Nama Objek : " +name + "\n");
        sb.append("Neutralitiy: "+isPositive+"\n");
        for (int i = 0; i < attributes.size(); i++) {
            sb.append(attributes.get(i).fst() + " = " + attributes.get(i).snd() + "\n");
        }
        sb.append("\n=====\n");
        return sb.toString();
    }
    
    // Return true jika memiliki nilai dari attribute yang dimaksud
    public boolean hasAttributeValue(String attributename,String _value){
        boolean retval;
        System.out.println("Checking membership of key :"+attributename + " with value : " + _value);
        
        Pair<String,String> p = new Pair(attributename,_value);
        for (int i = 0; i < attributes.size(); i++) {
            if(attributes.get(i).equals(p)){
                return true;
            }
        }
        return false;
    }
    
    // Return true jika memiliki attribute yang dimaksud
    public boolean hasAttribute(String attributename){
        for (int i = 0; i < attributes.size(); i++) {
            Pair<String,String> p = attributes.get(i);
            if(p.fst().equals(attributename)){
                return true;
            }
        }
        return false;        
    }
    
    public String getAttributevalue(String attribute){
        for (int i = 0; i < attributes.size(); i++) {
            Pair<String,String> p = attributes.get(i);
            if (p.fst().equals(attribute)) {
                return p.snd();
            }
        }
        return null;
    }
    
}
