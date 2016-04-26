


import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ryan Conyac
 */
public class ArrayListIgnoreCase extends ArrayList<String>{
    
    //Used for the filtering component to filter the words correctly
    @Override
    public boolean contains(Object o) {
        String paramStr = (String)o;
        for(String s: this) {
            if(paramStr.equalsIgnoreCase(s)) return true;
        }
        return false;
    }
}
