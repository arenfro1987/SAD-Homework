package mainPackage;


import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
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
public class NoExtraWhiteSpaceOrNumbers extends FormatManager implements Runnable {

    /**
     *class removes unnesesary char from strings
     */
    public NoExtraWhiteSpaceOrNumbers() {
    }

    /**
     *
     * @param pipeIn
     */
    public NoExtraWhiteSpaceOrNumbers(PipedInputStream pipeIn) {
        super();
        this.pipeIn = pipeIn;
    }
    
    /**
     *constructor
     */
    public void format() {
        //format the string to get into the separate lines
        lines = recievedString.split("\\r?\\n");
        //format to remove duplicate whitespace in the line
        int i = 0;
        while(i != lines.length) {
            //removes duplicate whitespace
            lines[i] = lines[i].trim();
            lines[i] = lines[i].replaceAll("\\s+", " ");
            //we don't accept numbers in this class
            lines[i] = lines[i].replace("\\d+", "");
//            System.out.println(lines[i]);
            i++;
        }
        
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
        format();
        push();
    }
    
}
