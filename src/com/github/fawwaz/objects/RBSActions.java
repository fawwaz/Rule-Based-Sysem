/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.fawwaz.objects;

/**
 *
 * @author Asus
 */
public class RBSActions {
    public String type; // ADD | REMOVE | MODIFY
    public Integer refer;  // REMOVE | MODIFY
    public RBSObject added; // ADD
    public String key_value; // MODIFY
    
    public static final String TYPE_ADD = "ADD";
    public static final String TYPE_REMOVE = "REMOVE";
    public static final String TYPE_MODIFY = "MODIFY";
    
    public String second_param;
    public String third_param;

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Informasi Action :\n");
        sb.append("Tipe Action \t\t: " +type + "\n");
        sb.append("Refer Rule \t\t: "+refer+"\n");
        sb.append("\tObject To add: \n");
        if(added!=null){
            sb.append(added.toString()+"\n");
        }else{
            sb.append("Null \n");
        }
        sb.append("\tKey value :\n");
        if(key_value!=null){
            sb.append(key_value+"\n");
        }else{
            sb.append("Null \n");
        }
        sb.append("\n=====\n");
        return sb.toString();
    }
    
    
}
