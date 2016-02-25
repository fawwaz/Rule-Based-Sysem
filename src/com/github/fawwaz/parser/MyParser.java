/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.fawwaz.parser;

import com.github.fawwaz.objects.RBSActions;
import com.github.fawwaz.objects.RBSObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Asus
 */
public class MyParser {
    
    String key_value = "\\w+:\\w+";
    String ascendent = "\\(\\w+" + "( " + key_value + ")+" + "\\)";

    String specification_test1 = "\\{[><]\\d+ ?(AND|OR)? ?[><]\\d+\\}"; // Handle kasus {>2 AND <9} // (\w+:{[><]\d+ ?(AND|OR)? ?[><]\d+};)|
    String specification_test2 = "\\{[><](\\w|\\d+)\\}";
    String specification_test3 = "\\{(NOT )?(\\w|\\d+)\\}";
    String specification_evaluation = "\\[([\\d+]|\\w)[+-]([\\d+]|\\w)\\]";
    String specification_variable = "\\w";
    String specification_atom = "\\w+";
    String logic_single_operand = "(NOT)? ?\\w+";
    String logic_double_operand = logic_single_operand + " ?(AND|OR) ?" + logic_single_operand;
    String specification_logic = "(" + logic_single_operand + ")|(" + logic_double_operand + ")";
    String attributes = "\\w+:((" + specification_test1 + ")|(" + specification_evaluation + ")|(" + specification_variable + ")|(" + specification_atom + ")|(" + specification_logic + ")|("+specification_test2+")|("+specification_test3+"));";
    String nama_objek = "\\w+";
    String nama_objek2 = "(?=\\()\\w+";
    String objek = "(NOT )?\\(" + nama_objek + "( " + attributes + ")+" + "\\)";
    String modified_property = "\\(\\w+ ("+specification_atom+"|("+specification_evaluation+"))\\)";
    String action_add_key = "ADD";
    String action_add = "ADD " + objek;
    String action_modify_key = "MODIFY";
    String action_modify = "MODIFY \\d+ " + modified_property;
    String action_remove_key = "REMOVE";
    String action_remove = "REMOVE \\d+";
    String action_number = "(?: )(\\d+)(?: )";
    String action_command = "("+action_add_key+"|"+action_modify_key+"|"+action_remove_key+")";
    String action = "((" + action_add + ")|(" + action_modify + ")|(" + action_remove + "))+";
    String actions = "( " + action + ")+";
    String conditions = "( " + objek + ")+";
    String valid_sentence = "IF" + conditions + " THEN" + actions;
    String valid_conditions = "(?:IF)"+conditions+" (?:THEN)";
    
    public void Parse(String input){
        //String test = "Hai saya ganteng loh  9 . 23";
//        String contoh = "IF (objek attribut1:v; atribut2:konstanta_string; attribut3:23; attribute4:[2+x]; attribute5:{>2 AND <9};) (objeklain attribute1:NOT y OR NOT Z; atribute5:NOT A AND B;) THEN MODIFY 1 (kentut benar) ADD (objek nama:z;) REMOVE 4";
        String contoh2 = "IF (brick position:heap; name:n; size:s;) NOT (brick position:heap; size:{>s};) NOT (brick position:hand;) THEN MODIFY 1 (position hand)";
        // TODO code application logic here
        Pattern pattern = Pattern.compile(valid_sentence);
        
        Matcher matcher = pattern.matcher(input);
        
        int i =0;
        while(matcher.find()){
            i++;
//            System.out.print("Start index : "+matcher.start());
//            System.out.println(" End index : "+matcher.end());
            System.out.println("["+i+"] >>> "+matcher.group());
        }
    }
    
    public void isValidObject(String input){
        Pattern pattern = Pattern.compile(objek);
        
        Matcher matcher = pattern.matcher(input);
        
        int i =0;
        while(matcher.find()){
            i++;
//            System.out.print("Start index : "+matcher.start());
//            System.out.println(" End index : "+matcher.end());
            System.out.println("["+i+"] >>> "+matcher.group());
        }
    }
    
//    public ArrayList<RBSObject> getConditions(String rule){
//        ArrayList<RBSObject> _conditions = new ArrayList<>();
//        Pattern pattern = Pattern.compile(conditions);
//        Matcher matcher = pattern.matcher(rule);
//        int i =0;
//        while(matcher.find()){
//            i++;
////            System.out.print("Start index : "+matcher.start());
////            System.out.println(" End index : "+matcher.end());
//            System.out.println("["+i+"] >>> "+matcher.group());
//        }
//        return _conditions;
//    }
    
    public HashMap<String,String> getAttributes(String fact){
        HashMap<String, String> _attributes = new HashMap<>();
        Pattern pattern = Pattern.compile(attributes);
        Matcher matcher = pattern.matcher(fact);
        while(matcher.find()){
            String[] pairs = matcher.group().replace(";","").split(":");
            _attributes.put(pairs[0], pairs[1]);
//            System.out.print("Start index : "+matcher.start());
//            System.out.println(" End index : "+matcher.end());
            //System.out.println("["+i+"] >>> "+matcher.group());
        }
        return _attributes;
    }
    
    public String getObjectName(String fact){
        String _objectname ="";
        Pattern pattern = Pattern.compile(nama_objek);
        Matcher matcher = pattern.matcher(fact);
        while(matcher.find()){
            return matcher.group();
        }
        return "nofound";
    }
    
    public ArrayList<RBSActions> getActions(String actions){
        ArrayList<RBSActions> _rbsactions = new ArrayList<>();
        Pattern pattern = Pattern.compile(action);
        Matcher matcher = pattern.matcher(actions);
        while(matcher.find()){
            RBSActions _rbsaction = getAction(matcher.group());
            _rbsactions.add(_rbsaction);
        }
        return _rbsactions;
    }
    
    public RBSActions getAction(String action){
        RBSActions rbsactions = new RBSActions();
        
        
        // Get action type : 
        Pattern pattern_command = Pattern.compile(action_command);
        Matcher matcher_command = pattern_command.matcher(action);
        while(matcher_command.find()){
            rbsactions.type = matcher_command.group();
        }
        
        // Get Integer
        if(rbsactions.type.equals("REMOVE") | rbsactions.type.equals("MODIFY")){
            Pattern pattern_reference = Pattern.compile(action_number);
            Matcher matcher_reference = pattern_reference.matcher(action);
            while(matcher_reference.find()){
                rbsactions.refer = Integer.parseInt(matcher_reference.group().replace(" ", ""));
            }
        }
        
        // get property to modify
        if(rbsactions.type.equals("MODIFY")){
            Pattern pattern_modify = Pattern.compile(modified_property);
            Matcher matcher_modify = pattern_modify.matcher(action);
            while(matcher_modify.find()){
                rbsactions.key_value = matcher_modify.group();
            }
                    
        }
        
        // get object to add
        if(rbsactions.type.equals("ADD")){
            RBSObject _added = new RBSObject();
            _added.name = getObjectName(action);
            _added.attributes = getAttributes(action);
            rbsactions.added = _added;
        }
        
        return rbsactions;
    }
    
    public ArrayList<RBSObject> getConditions(String rules){
        ArrayList<RBSObject> _rules = new ArrayList<>();
        Pattern pattern = Pattern.compile(valid_conditions);
        Matcher matcher = pattern.matcher(rules);
        while(matcher.find()){
            Pattern pattern_object = Pattern.compile(objek);
            Matcher matcher_object = pattern_object.matcher(matcher.group().replace("IF", "").replace("THEN", ""));
            while(matcher_object.find()){
                RBSObject _object = getObject(matcher_object.group());
                if(matcher_object.group().startsWith("NOT")){
                    _object.isPositive = false;
                }else{
                    _object.isPositive = true;
                }
                // Is negative set up here ... 
                _rules.add(_object);
            }
        }
        return _rules;
    }
    
    public RBSObject getObject(String object){
        RBSObject _object = new RBSObject();
        _object.name = getObjectName(object);
        _object.attributes = getAttributes(object);
        return _object;
    }
    
    
    public static void main(String args[]) {
        MyParser parser = new MyParser();
//        parser.Parse("");
//        System.out.println(parser.getObjectName("(brick name:A; size:10; position:heap;)"));
        parser.getConditions("IF (brick position:heap; name:n; size:s;) NOT (brick position:heap; size:{>s};) NOT (brick position:hand;) THEN MODIFY 1 (position hand)");
    }
}
