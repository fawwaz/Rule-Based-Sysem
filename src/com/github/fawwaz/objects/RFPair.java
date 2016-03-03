/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.fawwaz.objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author Asus
 */
public class RFPair {
    public Integer rulenumber;
    public ArrayList<Integer> matchedfact;

    public RFPair() {
        matchedfact = new ArrayList<>();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.rulenumber);
        hash = 97 * hash + Objects.hashCode(this.matchedfact);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RFPair other = (RFPair) obj;
        if (!Objects.equals(this.rulenumber, other.rulenumber)) {
            return false;
        }
        if (!Objects.equals(this.matchedfact, other.matchedfact)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Rule : "+this.rulenumber+"\n");
        for (int i = 0; i < matchedfact.size(); i++) {
            sb.append("Condition "+i +" matched fact number "+matchedfact.get(i)+"\n");
        }
        sb.append("\n");
        return sb.toString();
    }
    
    
    
}
