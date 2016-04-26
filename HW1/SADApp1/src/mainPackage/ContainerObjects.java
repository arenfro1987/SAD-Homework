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
public class ContainerObjects {
        private MainUserInterface ui;

    /**
     *instance of user interface
     * @param ui
     */
    public void setUi(MainUserInterface ui) {
            this.ui = ui;
        }

    /**
     *get/set user interface
     * @return
     */
    public MainUserInterface getUi() {
            return ui;
        }
         
    }
