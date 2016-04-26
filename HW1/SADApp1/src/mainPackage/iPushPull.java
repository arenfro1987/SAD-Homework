package mainPackage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ryan & derek
 */
public interface iPushPull {
    //called by the application to notify the other side of the pipe to take data out of it

    /**
     *
     */
    void push();
    //called by the application to take data out of the pipe

    /**
     *
     */
    void pull();
}
