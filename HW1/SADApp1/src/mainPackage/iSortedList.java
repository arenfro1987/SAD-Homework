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
public interface iSortedList extends iPushPull{

    /**
     *
     * @param type
     * @return
     */
    static SorterTypes createSorter(String type) {
        SorterTypes sorter = new SorterTypes(type);
        return sorter;
    }
    
    /**
     *creates sorter
     * @param pipeIn
     */
    void setPipeInputStream(PipedInputStream pipeIn);

    /**
     *
     * @param lines
     */
    void setNumberOfLines(int lines);
}
