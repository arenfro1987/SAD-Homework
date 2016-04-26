package mainPackage;


import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ryan
 */
public class ShiftManager implements iPushPull, Runnable {

    /**
     * manages the shifting
     * @param pipeIn
     */
    public ShiftManager(PipedInputStream pipeIn) {
        this.pipeIn = pipeIn;
    }
    
    private String[] formattedStrings;
    private PipedInputStream pipeIn;
    private String recievedString = "";
    private ArrayList<ArrayList<String>> shifterStrings = new ArrayList<ArrayList<String>>();

    @Override
    public void push() {
        try {
            //run the sorter to recieve pipes
            iSortedList sorter = iSortedList.createSorter("default");

            int lines = 0;
            for(int i = 0; i < shifterStrings.size(); i++) {
                for(int n = 0; n < shifterStrings.get(i).size(); n++) {
                    lines++;
                }
            }
            sorter.setNumberOfLines(lines);
            PipedOutputStream pipeOutToSorter = new PipedOutputStream();
            String temp = "";
            for(int i = 0; i < shifterStrings.size(); i++) {
                for(int n = 0; n < shifterStrings.get(i).size(); n++) {
                    temp += shifterStrings.get(i).get(n) + "\r\n";
                }
            }
            byte[] b = temp.getBytes();
            PipedInputStream pipeIntoSorter = new PipedInputStream(b.length);
            pipeOutToSorter.connect(pipeIntoSorter);
            sorter.setPipeInputStream(pipeIntoSorter);
            sorter.pull();
            pipeOutToSorter.write(b);
            pipeOutToSorter.flush();
        } catch (IOException ex) {
            Logger.getLogger(ShiftManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void pull() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            //pipes are read in as ints, -1 means it is empty
            byte[] b = new byte[pipeIn.available()];
            pipeIn.read(b, 0, pipeIn.available());
            int value;
            for(int i = 0; i < b.length; i++) {
                value = b[i];
                recievedString += (char) value;
            }
            
            pipeIn.close();
        } catch (IOException ex) {
            Logger.getLogger(NoExtraWhiteSpaceOrNumbers.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        formattedStrings = recievedString.split("\\r?\\n");
        CircularShifter shifter = new CircularShifter();
        for(int x =0; x < formattedStrings.length ; x++)
        {
            shifterStrings.add(shifter.shift(formattedStrings[x]));
        }
        
        push();
    }
    
}
