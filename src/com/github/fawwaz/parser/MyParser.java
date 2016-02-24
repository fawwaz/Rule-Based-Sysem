/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.fawwaz.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Asus
 */
public class MyParser {
    public void Parse(){
        //String test = "Hai saya ganteng loh  9 . 23";
        String contoh = "IF (objek attribut1:v; atribut2:konstanta_string; attribut3:23; attribute4:[2+x]; attribute5:{>2 AND <9};) (objeklain attribute1:NOT y OR NOT Z; atribute5:NOT A AND B;) THEN MODIFY 1 (kentut benar) ADD (objek nama:z;) REMOVE 4";
        String key_value = "\\w+:\\w+";
        String ascendent = "\\(\\w+"+"( "+key_value+")+"+"\\)";
        
        String specification_test        = "\\{[><]\\d+ ?(AND|OR)? ?[><]\\d+\\}"; // Handle kasus {>2 AND <9} // (\w+:{[><]\d+ ?(AND|OR)? ?[><]\d+};)|
        String specification_evaluation  = "\\[([\\d+]|\\w)[+-]([\\d+]|\\w)\\]";
        String specification_variable    = "\\w";
        String specification_atom        = "\\w+";
        String logic_single_operand      = "(NOT)? ?\\w+";
        String logic_double_operand      = logic_single_operand+" ?(AND|OR) ?"+logic_single_operand;
        String specification_logic       = "("+logic_single_operand+")|("+logic_double_operand+")";
        String attributes                = "\\w+:(("+specification_test+")|("+specification_evaluation+")|("+specification_variable+")|("+specification_atom+")|("+specification_logic+"));";
        String nama_objek                = "\\w+";
        String objek                     = "\\("+nama_objek+"( "+attributes+")+"+"\\)";
        String modified_property         = "\\(\\w+ \\w+\\)";
        String action_add                = "ADD "+objek;
        String action_modify             = "MODIFY \\d+ "+modified_property;
        String action_remove             = "REMOVE \\d+";
        String action                    = "(("+action_add+")|("+action_modify+")|("+action_remove+"))+";
        String actions                   = "( "+action+")+";
        String conditions                = "( "+objek+")+";
        String valid_sentence            = "IF"+conditions+" THEN"+actions;
        // TODO code application logic here
        Pattern pattern = Pattern.compile(valid_sentence);
        
        Matcher matcher = pattern.matcher(contoh);
        
        int i =0;
        while(matcher.find()){
            i++;
//            System.out.print("Start index : "+matcher.start());
//            System.out.println(" End index : "+matcher.end());
            System.out.println("["+i+"] >>> "+matcher.group());
        }
    }
}
