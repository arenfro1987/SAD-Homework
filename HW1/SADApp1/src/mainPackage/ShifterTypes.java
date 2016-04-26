package mainPackage;


import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ryan
 */
public class ShifterTypes implements iShift{
    private ShifterTypes shifter = null;

    /**
     *instance of ShifterTypes
     */
    public ShifterTypes() {
    }

    /**
     *constructor for shiftertypes
     * @param type
     */
    public ShifterTypes(String type) {
        switch(type) {
            default:
                shifter = new CircularShifter();
        }
    }
    
    /**
     *constructor for shiftertypes
     * @return
     */
    public ShifterTypes getShifter() {
        return shifter;
    }
            
    ArrayList<String> shift(String string) {
       return null; 
    }

    /**
     *
     */
    @Override
    public void push() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void pull() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
