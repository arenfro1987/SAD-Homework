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
public class CircularShifter extends ShifterTypes {
    private String originalSentence;
    private ArrayList<String>  shiftingSentence;
    
    /**
     *instance of original and shifted sentence
     */
    public CircularShifter()
    {
        
    }

    

    
    
    @Override
     ArrayList<String> shift(String toShift) {
         originalSentence = toShift;
         shiftingSentence = new ArrayList<String>();
         shiftHelper(toShift);
        return shiftingSentence;
    }
     
    /**
     *shift method returns arraylist of the shifted strings
     * @param toShift
     */
    public void shiftHelper(String toShift)
     {
         shiftingSentence.add(toShift);
          int index = toShift.indexOf (' ');
          if(index == -1) {
              return;
          }
          String start = toShift.substring (index+1);
          start += " ";
          String end = toShift.substring (0 , index);
          String shifted = start + end;
         if(shifted.equals(originalSentence))
         {
             return;
         }
         else
         {
             shiftHelper(shifted);
         }
         
         
         //recursive helper class for shift
     }
}
