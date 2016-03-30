/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.fawwaz.objects;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Fawwaz Muhammad
 */
public class RBSGraphNode {
    public String graphName;
    public String node_type; // alpha, beta, terminal
    public String class_type_test; // it only has value if the graph node type is alpha and represents a class type
    public String attribute_test; // only if atom 
    public String value_test; // only if atom
    public Integer associated_rule_number; // it only has value if the graph node type is terminal
    public ArrayList<Integer> child_node; //  save every child node
    public ArrayList<Integer> parent_node; // save every parent node
    public ArrayList<Integer> associated_facts; // save partial matches;
    
    public RBSGraphNode(){
        child_node = new ArrayList<>();
        parent_node = new ArrayList<>();
        associated_facts = new ArrayList<>();
    }
    
    public RBSGraphNode(String graphName,String node_type){
        child_node = new ArrayList<>();
        parent_node = new ArrayList<>();
        associated_facts = new ArrayList<>();
        this.graphName = graphName;
        this.node_type = node_type;
    }
    
    public RBSGraphNode(String graphName,String node_type, String class_type_test){
        child_node = new ArrayList<>();
        parent_node = new ArrayList<>();
        associated_facts = new ArrayList<>();
        this.graphName = graphName;
        this.node_type = node_type;
        this.class_type_test = class_type_test;
    }
    
    public RBSGraphNode(String graphName,String node_type, String attribute_test,String value_test){
        child_node = new ArrayList<>();
        parent_node = new ArrayList<>();
        associated_facts = new ArrayList<>();
        this.graphName = graphName;
        this.node_type = node_type;
        this.attribute_test = attribute_test;
        this.value_test = value_test;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.graphName);
        hash = 47 * hash + Objects.hashCode(this.node_type);
        hash = 47 * hash + Objects.hashCode(this.class_type_test);
        hash = 47 * hash + Objects.hashCode(this.attribute_test);
        hash = 47 * hash + Objects.hashCode(this.value_test);
        hash = 47 * hash + Objects.hashCode(this.associated_rule_number);
        hash = 47 * hash + Objects.hashCode(this.child_node);
        hash = 47 * hash + Objects.hashCode(this.parent_node);
        hash = 47 * hash + Objects.hashCode(this.associated_facts);
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
        final RBSGraphNode other = (RBSGraphNode) obj;
        if (!Objects.equals(this.graphName, other.graphName)) {
            return false;
        }
        if (!Objects.equals(this.node_type, other.node_type)) {
            return false;
        }
        if (!Objects.equals(this.class_type_test, other.class_type_test)) {
            return false;
        }
        if (!Objects.equals(this.attribute_test, other.attribute_test)) {
            return false;
        }
        if (!Objects.equals(this.value_test, other.value_test)) {
            return false;
        }
        if (!Objects.equals(this.associated_rule_number, other.associated_rule_number)) {
            return false;
        }
        if (!Objects.equals(this.child_node, other.child_node)) {
            return false;
        }
        if (!Objects.equals(this.parent_node, other.parent_node)) {
            return false;
        }
        if (!Objects.equals(this.associated_facts, other.associated_facts)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RBSGraphNode{" + 
                "graphName=" + graphName + 
                //"\nnode_type=" + node_type + 
                //"\nclass_type_test=" + class_type_test + 
                //"\nattribute_test=" + attribute_test + 
                //"\nvalue_test=" + value_test + 
                //"\nassociated_rule_number=" + associated_rule_number + 
                //"\nchild_node=" + child_node + 
                "\nparent_node=" + parent_node + 
                //"\nassociated_facts=" + associated_facts + 
                "}";
    }
    
    
    
}
