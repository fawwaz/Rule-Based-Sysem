/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.fawwaz;

/**
 *
 * @author Asus
 */
public class Util {
    public static int[] convertobasen(int num, int max, int length){
        int[] retval = new int[length];
        int idx = length-1;
        int r =0;
        int q = num;
        while(true){
            r = Math.floorMod(q, max);
            retval[idx] = r;
            q = (q-r) / max;
            if(idx>0){
                idx--;
            }
            if(q==0){
                break;
            }
        }
        return retval;
    }
    
    public void testconvertobasen(int max,int length){
        for (int i = 0; i < Math.pow(max, length); i++) {
//            int[] a =  getConvertedToBaseN(i, max, length);
            int[] a = convertobasen(i, max, length);
            for (int j = 0; j < a.length; j++) {
                System.out.print(a[j]+" ");
            }
            System.out.println("");
//            System.out.println();
            //System.out.println(Integer.toString(i,max));
            
        }
        
    }
}
