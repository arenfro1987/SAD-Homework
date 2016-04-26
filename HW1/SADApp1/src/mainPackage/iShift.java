package mainPackage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ryan
 */
public interface iShift extends iPushPull{

    /**
     *creates shifter
     * @param shifterType
     * @return
     */
    static ShifterTypes createNewShifter(String shifterType) {
        ShifterTypes shifter = null;
        return shifter.getShifter();
    }
}
