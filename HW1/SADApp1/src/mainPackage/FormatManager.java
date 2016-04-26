package mainPackage;


import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author ryan & derek
 */
public class FormatManager implements iStringFormat, Runnable {
    private NoExtraWhiteSpaceOrNumbers formatter = null;

    /**
     * instance of formatter to use
     */
    protected PipedInputStream pipeIn = null;

    /**
     * instance of in pipe
     */
    protected String recievedString = "";

    /**
     * instance of recieved string  when put together
     */
    protected char pipedInChar;

    /**
     * instance of temporary storage for char
     */
    protected int value;

    /**
     * instance of value recieved from pipe
     */
    protected String lines[];

    /**
     * instance of text broken into array
     * @param pipeIn
     */
    @Override
    public void setPipeIn(PipedInputStream pipeIn) {
        this.pipeIn = pipeIn;
    }

    /**
     * set pipe varible
     */
    public FormatManager() {
    }

    /**
     *
     * @param type
     * @param pipeIn
     */
    public FormatManager(String type, PipedInputStream pipeIn) {
        switch(type) {
            default:
               formatter = new NoExtraWhiteSpaceOrNumbers(pipeIn);
               break;
        }
        
    }
    
    /**
     * contructor for format manager
     * @return
     */
    public iStringFormat getFormatClass() {
        return formatter;
    }
       
    String formatString(String[] stringList) {
       return null; 
    }
    
    ShiftManager createShiftManager() {
        return null;
    }

    @Override
    public void push() {
        try {
            //push to the shift manager
            
            //initialize pipes for transporting to formatter
            //pipe in for formatter
            PipedInputStream pipeInputForShiftManager = new PipedInputStream();
            //pipe out from this class
            PipedOutputStream pipeOutToShiftManager = new PipedOutputStream();
            try {
                pipeOutToShiftManager.connect(pipeInputForShiftManager);
            } catch (IOException ex) {
                Logger.getLogger(NoExtraWhiteSpaceOrNumbers.class.getName()).log(Level.SEVERE, null, ex);
            }
            ShiftManager shiftManager = new ShiftManager(pipeInputForShiftManager);
            
            
            //should probably have a new thread to handle writing the data into the formatter pipe?
            String message = "";
            for(int i = 0; i < lines.length; i++) {
                message += lines[i] + "\r\n";
            }
            pipeOutToShiftManager.write(message.getBytes());
            pipeOutToShiftManager.flush(); //tells the inputPipe to read
            shiftManager.pull();
        } catch (IOException ex) {
            Logger.getLogger(FormatManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void pull() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        
    }
    
}
