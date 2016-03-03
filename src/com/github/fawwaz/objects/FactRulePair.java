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
public class FactRulePair {
    public Integer rulenumber;
    public Integer factnumber;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + rulenumber;
        hash = 47 * hash + factnumber;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null){
            return false;
        }
        if(getClass() != obj.getClass()){
            return false;
        }
        final FactRulePair other = (FactRulePair) obj;
        
        if(this.rulenumber != other.rulenumber){
            return false;
        }
        
        if(this.factnumber != other.factnumber){
            return false;
        }
        
        return true;
    }
    
    
    
    
}
