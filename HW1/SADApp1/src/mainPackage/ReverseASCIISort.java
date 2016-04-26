package mainPackage;


import java.util.Collections;
import java.util.Comparator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ryan
 */
public class ReverseASCIISort implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        String word1, word2;
        word1 = o1.toString();
        if(o1.contains(" ")) {
            word1 = word1.substring(0, word1.indexOf(" "));
        }
        word2 = o2.toString();
        if(o2.contains(" ")) {
            word2 = word2.substring(0, word2.indexOf(" "));
        }
        int returnValue = 0;
        int smaller, word1Length, word2Length;
        if (word1.length() > word2.length()) {
            smaller = word2.length();
        } else if (word1.length() < word2.length()) {
            smaller = word1.length();
        } else {
            smaller = word1.length();
        }
        for (int i = 0; i < smaller; i++) {

            if (Integer.valueOf((int) word1.charAt(i)) >= 97 && Integer.valueOf((int) word2.charAt(i)) >= 97 || Integer.valueOf((int) word1.charAt(i)) <= 90 && Integer.valueOf((int) word2.charAt(i)) <= 90) {//both lower case or both upper case
                if (word1.charAt(i) < word2.charAt(i)) {//first letter is less then the second
                    return -1;
                } else if (word1.charAt(i) == word2.charAt(i)) {//same lettter

                } else {//second letter is greater
                    return 1;
                }

            } else if (Integer.valueOf((int) word1.charAt(i)) <= 90 && Integer.valueOf((int) word2.charAt(i)) >= 97) { //word 1 is upper word 2 is lower
                if (word1.toLowerCase().charAt(i) == word2.toLowerCase().charAt(i)) //are they the same letter
                {
                    return 1; //the upper case is higher
                } else //not the same letter
                 if (word1.toLowerCase().charAt(i) < word2.toLowerCase().charAt(i)) {//first letter is less then the second
                        return -1;
                    } else {//second letter is greater
                        return 1;
                    }

            } else //word 2 us upper and word 1 is lower
            if (word1.toLowerCase().charAt(i) == word2.toLowerCase().charAt(i)) //are they the same letter
            {
                return -1; //upper case is higher
            } else //not the same letter
             if (word1.toLowerCase().charAt(i) < word2.toLowerCase().charAt(i)) {//first letter is less then the second
                    return -1;
                } else {//second letter is greater
                    return 1;
                }
        }

        if (word1.length() > word2.length()) {
            return 1;
        } else if (word1.length() < word2.length()) {
            return -1;
        } else {
            return 0;
        }

    }
}
