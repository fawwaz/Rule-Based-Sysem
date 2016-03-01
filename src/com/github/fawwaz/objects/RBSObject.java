/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.fawwaz.objects;

import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Asus
 */
public class RBSObject {
    public boolean isPositive=true;
    public String name;
    public HashMap<String,String> attributes;

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Informasi objek \n");
        sb.append("Nama Objek : " +name + "\n");
        sb.append("Neutralitiy: "+isPositive+"\n");
        for(String attr : attributes.keySet()){
            sb.append(attr + " = " + attributes.get(attr) + "\n");
        }
        sb.append("\n=====\n");
        return sb.toString();
    }
    
    // Return true jika memiliki nilai dari attribute yang dimaksud
    public boolean hasAttributeValue(String attributename,String _value){
        boolean retval;
        System.out.println("Checking membership of key :"+attributename + " with value : " + _value);
        String val = attributes.get(attributename);
        System.out.println("However value of this object is "+ val);
        if(attributes.containsKey(attributename) && _value.equals(val)){
            System.out.println("Get the value equal to "+val);
            retval = true;
        }else{
            retval = false;
        }
        System.out.println("Retval is "+retval);
        return retval;
    }
    
    // Return true jika memiliki attribute yang dimaksud
    public boolean hasAttribute(String attributename){
        return attributes.containsKey(attributename);
    }
    
}
