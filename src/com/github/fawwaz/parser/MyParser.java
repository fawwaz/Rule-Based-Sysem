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
    
    public static String key_value = "\\w+:\\w+";
    public static String ascendent = "\\(\\w+" + "( " + key_value + ")+" + "\\)";

    public static String specification_test1 = "\\{[><]\\d+ ?(AND|OR)? ?[><]\\d+\\}"; // Handle kasus {>2 AND <9} // (\w+:{[><]\d+ ?(AND|OR)? ?[><]\d+};)|
    public static String specification_test2 = "\\{[><](\\w|\\d+)\\}";
    public static String specification_test2_variabel = "\\{[><](\\w)\\}";
    public static String specification_test2_angka = "\\{[><](\\d+)\\}";
    public static String specification_test3 = "\\{(NOT )?(\\w|\\d+)\\}";
    public static String specification_test_overall = "(("+specification_test1+")|("+specification_test2+")|("+specification_test3+"))";
    public static String specificaton_variabel2 = "(\\w+)";
    public static String specification_variable = "([abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ])";
    public static String specification_number = "(\\d+)";
    public static String specification_evaluation = "\\[(\\w+)[+*/%-](\\w+)\\]";
    public static String specification_atom = "\\w+";
    public static String logic_single_operand = "(NOT)? ?\\w+";
    public static String logic_double_operand = logic_single_operand + " ?(AND|OR) ?" + logic_single_operand;
    public static String specification_logic = "(" + logic_single_operand + ")|(" + logic_double_operand + ")";
    public static String attributes = "\\w+:((" + specification_test1 + ")|(" + specification_evaluation + ")|(" + specificaton_variabel2 + ")|(" + specification_atom + ")|(" + specification_logic + ")|("+specification_test2+")|("+specification_test3+"));";
    public static String nama_objek = "\\w+";
    public static String nama_objek2 = "\\(\\w+";
    public static String objek = "(NOT )?\\(" + nama_objek + "( " + attributes + ")+" + "\\)";
    public static String modified_property = "\\(\\w+ ("+specification_atom+"|("+specification_evaluation+"))\\)";
    public static String action_add_key = "ADD";
    public static String action_add = "ADD " + objek;
    public static String action_modify_key = "MODIFY";
    public static String action_modify = "MODIFY \\d+ " + modified_property;
    public static String action_remove_key = "REMOVE";
    public static String action_remove = "REMOVE \\d+";
    public static String action_number = "( \\d+ )";
    public static String action_command = "("+action_add_key+"|"+action_modify_key+"|"+action_remove_key+")";
    public static String action = "((" + action_add + ")|(" + action_modify + ")|(" + action_remove + "))+";
    public static String actions = "( " + action + ")+";
    public static String conditions = "( " + objek + ")+";
    public static String valid_sentence = "IF" + conditions + " THEN" + actions;
    public static String valid_conditions = "(?:IF)"+conditions+" (?:THEN)";
    
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
        Pattern pattern = Pattern.compile(nama_objek2);
        Matcher matcher = pattern.matcher(fact);
        while(matcher.find()){
            return matcher.group().replace("(", "");
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
            if(rbsactions.type.equals("REMOVE")){
                rbsactions.refer = Integer.valueOf(action.replace("REMOVE ", ""));
            }else if(rbsactions.type.equals("MODIFY")){
                rbsactions.refer = Integer.valueOf(action.replace("MODIFY ", "").substring(0, 1));
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
            _added.attributes = getAttributes(action.replace("ADD ", ""));
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
    
    public ArrayList<String> getVariableInEvaluation(String evaluation_expression){
        // Make sure this function called in valid input..
        ArrayList<String> retval = new ArrayList<>();
//        if(evaluation_expression.matches(specification_evaluation)){
            Pattern pattern = Pattern.compile(specification_variable);
            Matcher matcher = pattern.matcher(evaluation_expression);
            while(matcher.find()){
                retval.add(matcher.group());
            }
//        }else{
//            System.out.println("Invalid method call..");
//        }
        return retval;
    }
    
    public ArrayList<Integer> getNumberInEvaluation(String evaluation_expression){
//        System.out.println(specification_evaluation);
        ArrayList<Integer> retval = new ArrayList<>();
//        if(evaluation_expression.matches(specification_evaluation)){
            Pattern pattern = Pattern.compile(specification_number);
            Matcher matcher = pattern.matcher(evaluation_expression);
            while(matcher.find()){
                System.out.println("matched " + matcher.group());
                retval.add(Integer.valueOf(matcher.group()));
            }
//        }else{
//            System.out.println("Invalid method call..");
//        }
        return retval;
    }
    
    
    public Integer doevaluate(String math_expression){
        // parse first
        // understand the symbol
        // execute
        return 0;
    }
    
    public boolean dotest(String test_expression){
        return true;
    }
    /*START NGASAL ...*/
   
    
    
    private int[] getConvertedToBaseN(int num,int max,int length){
        int[] retval = new int[length];
        int mod = 0;
        int div = num;
        int idx = length-1; // java starts with 0
        do{
            mod         = Math.floorMod(div, max);
            retval[idx] = mod;
            div         = Math.floorDiv(div, max);
            if(idx>0){
                idx--;
            }
            if(mod<max){
                retval[idx] = div;
                break;
            }
        }while(idx>=0);
        return retval;
    }
    
    
    public static void main(String args[]) {
        MyParser parser = new MyParser();
//        parser.teslagi();
//        parser.Parse("");
//        System.out.println(parser.getObjectName("(brick name:A; size:10; position:heap;)"));
//        parser.isValidObject("IF (brick position:heap; name:n; size:s;) NOT (brick position:heap; size:{>s};) NOT (brick position:hand;) THEN MODIFY 1 (position hand)");
        
//          System.out.println(parser.getActions("THEN REMOVE 1 ADD (year mod4:[n%4]; mod100:[n%100]; mod400:[n%400];) MODIFY 2 (tai toi)"));
//        ArrayList<String> variables = parser.getVariableInEvaluation("[n+1]");
//        for (String variable : variables) {
//            System.out.println("I got variable : "+ variable);
//        }
//        String a = "[n+1]";
//        String z = a.replaceAll("\\[","").replaceAll("\\]","").replaceAll("\\d+","").replaceAll(parser.specification_variable, "");
//        System.out.println("Startsw ith "+z);
//        ArrayList<Integer> variables2 = parser.getNumberInEvaluation("[n+1]");
//        for(Integer variable : variables2){
//            System.out.println("I got number : "+ variable);
//        }
        //System.out.println(parser.getObjectName("(brick position:heap; name:n; size:s;)"));
        
    }
}
