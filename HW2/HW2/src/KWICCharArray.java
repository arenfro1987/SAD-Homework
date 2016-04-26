

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ryan
 */
public class KWICCharArray implements Comparable{

    private char[] chars;//chars of the sentence
    private int index;//index of which line it is
    private int start;//start index
    private int num;//length of the phrase or sentence

    /**
     *
     * @param chars the characters of the sentence
     * @param start the index of the start of the sentence
     * @param num the length of the sentence
     * @param index index of when the sentence comes up in the line list
     */
    public KWICCharArray(char[] chars, int start, int num, int index) {
        this.setChars(chars);
        this.setStart(start);
        this.setNum(num);
        this.setIndex(index);
    }

    @Override
    public int compareTo(Object o) {
        KWICCharArray myCharArray = (KWICCharArray) o;      
        String word1 = new String(chars, start, num), word2 = new String(myCharArray.getChars(), myCharArray.getStart(), myCharArray.getNum());
        if(word1.contains(" ")) {
            word1 = word1.substring(0, word1.indexOf(" "));
        }
        if(word2.contains(" ")) {
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

    /**
     *
     * @param start
     */
    public void setStart(int start) {
        this.start = start;
    }

    /**
     *
     * @return
     */
    public int getStart() {
        return start;
    }

    /**
     *
     * @param num
     */
    public void setNum(int num) {
        this.num = num;
    }

    /**
     *
     * @return
     */
    public int getNum() {
        return num;
    }

    /**
     *
     * @param index
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     *
     * @return
     */
    public int getIndex() {
        return index;
    }

    /**
     *
     * @param chars
     */
    public void setChars(char[] chars) {
        this.chars = chars;
    }

    /**
     *
     * @return
     */
    public char[] getChars() {
        return chars;
    }

}
