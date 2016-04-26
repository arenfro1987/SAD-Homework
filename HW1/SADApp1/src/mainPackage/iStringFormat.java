package mainPackage;


import java.io.PipedInputStream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ryan
 */
public interface iStringFormat extends iPushPull{

    /**
     *
     * @param formatType
     * @param pipeIn
     * @return
     */
    static iStringFormat createFormat(String formatType, PipedInputStream pipeIn) {
        FormatManager format = new FormatManager(formatType, pipeIn);
        return format.getFormatClass();
    }

    /**
     *returns format manager
     * @param pipeIn
     */
    void setPipeIn(PipedInputStream pipeIn);
    
    
    
}
