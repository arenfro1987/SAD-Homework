package mainPackage;


import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.Collections;
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
public class SorterTypes implements iSortedList, Runnable{
    private PipedInputStream inputsFromThreads;

    /**
     *instance of input pipe
     */
    protected ArrayList<String> recievedStringList = new ArrayList();
    private int numberOfLines;
    /**
     *instance of shifted strings and the number of lines
     */
    public SorterTypes() {
    }

    /**
     * constructor 
     * @param type
     */
    public SorterTypes(String type) {
        this.numberOfLines = numberOfLines;
        switch(type) {
            default:
                sorter = new ReverseASCIISort();
                break;
        }
    }
    private ReverseASCIISort sorter = null;

    /**
     * instance of sorter
     */
    public void sort() {
        Collections.sort(recievedStringList, sorter);
    }
    
    
    void sortStrings(String[] strings) {
        
    }

    /**
     * 
     */
    @Override
    public void push() {

            MainUserInterface mainUI = MainUserInterface.container.getUi();
            mainUI.setRecievedStringList(recievedStringList);

    }
    

    @Override
    public void pull() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            //check each pipe if they have data in them
            int i = 0;
            
            String recievedString = "";
            while(numberOfLines != i) {
                try {
                    byte[] b = new byte[inputsFromThreads.available()];
                    inputsFromThreads.read(b, 0, inputsFromThreads.available());
                    int value;
                    for (int n = 0; n < b.length; n++) {
                        value = b[n];
                        recievedString += (char) value;
                    }
                } catch (IOException ex) {
                    Logger.getLogger(SorterTypes.class.getName()).log(Level.SEVERE, null, ex);
                }
//                recievedStringList.add(recievedString);
//                recievedString = "";
                i++;
            }
            inputsFromThreads.close();
            String[] temp = recievedString.split("\\r\\n");
            for(int n = 0; n < temp.length; n++) {
                recievedStringList.add(temp[n]);
            }
            
            sort();
            push();
        } catch (IOException ex) {
            Logger.getLogger(SorterTypes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param pipeIn
     */
    @Override
    public void setPipeInputStream(PipedInputStream pipeIn) {
        this.inputsFromThreads = pipeIn;
    }
    
    /**
     *
     * @param lines
     */
    @Override
    public void setNumberOfLines(int lines) {
        this.numberOfLines = lines;
    }
}
