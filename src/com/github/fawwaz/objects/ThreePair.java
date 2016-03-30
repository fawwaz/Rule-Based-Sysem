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
public class ThreePair {
    public Integer root_id; // nyimpan id dari last_root;
    public String variable; // nyimpan variabelnya.
    public String attribute; // nyimpan atribut yang mengisi variable
    public Integer rule_id; // nyimpan urutan rule
    
    public ThreePair(String variable, String attribute, Integer rule_id) {
        this.variable = variable;
        this.attribute = attribute;
        this.rule_id = rule_id;
    }
    
    public ThreePair(Integer root_id, String variable, String attribute, Integer rule_id) {
        this.root_id = root_id;
        this.variable = variable;
        this.attribute = attribute;
        this.rule_id = rule_id;
    }

    @Override
    public String toString() {
        return "ThreePair{"+ "Rule Number" + rule_id + ", root_id=" + root_id + ", variable=" + variable + ", attribute=" + attribute +'}';
    }
    
}
