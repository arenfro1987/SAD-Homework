


import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
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
public class KWIC {
    private char[] charArray;
    private char[] shiftedCharArray;
    private String fileName;
    private int[] indexOfFirstCharOfNewLine;
    private int[] circularShiftArray;
    private int[][] alphabetizedIndexPositions;
    
    KWIC(String inputText, String fileName) {
       this.fileName = fileName;
       input(inputText); 
    }
    
    /**
     *
     * @return string of the shifted, sorted, filtered sentences
     */
    public String runComponents() {
        circularShift();
        alphabetizing();
        filter();
        return output();
    }
    
    /**
     *
     * @param input input from the user for the sentences to be sorted
     */
    public void input(String input) {
        //format the string to get into the separate lines
        String[] lines = input.split("\\r?\\n");
        //format to remove duplicate whitespace in the line
        int i = 0;
        while(i != lines.length) {
            //removes duplicate whitespace
            lines[i] = lines[i].trim();
            lines[i] = lines[i].replaceAll("\\s+", " ");
            lines[i] += "\n";
//            System.out.println(lines[i]);
            i++;
        }
        String tempString = "";
        for(int n = 0; n < lines.length; n++) {
            tempString += lines[n];
        }
        charArray = tempString.toCharArray();
        indexOfFirstCharOfNewLine = new int[lines.length];
        int iterator = 0;
        for(int n = 0; n < lines.length; n++) {
            if(charArray[n] == '\n') {
                indexOfFirstCharOfNewLine[iterator] = n;
                iterator++;
            }
        }
    }

    /**
     * Shifts the words around to produce the shifted arrays of sentences
     */
    public void circularShift() {
        ArrayList<Integer> shift_index = new ArrayList<Integer>();
        shift_index.add(0);
        CharArrayWriter writer = new CharArrayWriter();
        for (int i = 1; i <= indexOfFirstCharOfNewLine.length; i++) {
            int upper = (i == indexOfFirstCharOfNewLine.length) ? charArray.length : indexOfFirstCharOfNewLine[i];
            char[] line = new char[upper - indexOfFirstCharOfNewLine[i - 1]];
            System.arraycopy(charArray, indexOfFirstCharOfNewLine[i - 1], line, 0, line.length);
            String tmp = new String(line);
            StringTokenizer st = new StringTokenizer(tmp);
            ArrayList<String> tokens = new ArrayList<String>();
            while (st.hasMoreTokens()) {
                tokens.add(st.nextToken());
            }
            for (int j = 0; j < tokens.size(); j++) {
                for (int k = j; k < j + tokens.size(); k++) {
                    try {
                        writer.write(tokens.get(k % tokens.size()));
                        if (k != j + tokens.size() - 1) {
                            writer.write(' ');
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                shift_index.add(writer.size());
            }
        }
        shift_index.remove(shift_index.size() - 1);
        circularShiftArray = new int[shift_index.size()];
        for (int i = 0; i < shift_index.size(); i++) {
            circularShiftArray[i] = shift_index.get(i);
        }
        shiftedCharArray = writer.toCharArray();
    }

    /**
     * sorts the words using the alphabetizing array to get the index locations
     */
    public void alphabetizing() {
        ArrayList<KWICCharArray> list = new ArrayList<KWICCharArray>();
        for (int i = 1; i <= circularShiftArray.length; i++) {
            int upper = (i == circularShiftArray.length) ? shiftedCharArray.length : circularShiftArray[i];
            KWICCharArray temp = new KWICCharArray(shiftedCharArray, circularShiftArray[i - 1], upper - circularShiftArray[i - 1], i - 1);
            list.add(temp);
        }
        Collections.sort(list);
        alphabetizedIndexPositions = new int[list.size()][2];
        for (int i = 0; i < alphabetizedIndexPositions.length; i++) {
            alphabetizedIndexPositions[i][0] = list.get(i).getStart();
            alphabetizedIndexPositions[i][1] = list.get(i).getNum();
        }
    }

    /**
     *
     * @return gives the formatted and filtered string back
     */
    public String output() {
        String output = "";
        for (int i = 0; i < alphabetizedIndexPositions.length; i++) {
            int start = alphabetizedIndexPositions[i][0];
            int end = alphabetizedIndexPositions[i][1];
            for (int j = 0; j < end; j++) {
//                System.out.print(shiftedCharArray[circularShiftArray[start] + j]);
                output += shiftedCharArray[start + j];
            }
            output += "\n";
        }
        return output;
    }

    /**
     * filters out noise words specified in the file
     */
    public void filter() {
        String line;
        ArrayListIgnoreCase noiseWords = new ArrayListIgnoreCase();
        try {
            //read in from file on web server to remove the given noise words
//            System.out.println(url.getPath());
//            String temp = (new File(MasterControl.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getAbsolutePath());
//            temp = temp.replace("HW2.jar", fileName);
            URL url = getClass().getResource("/resources/" + fileName);
//            System.out.println("this is the path that is being read for the noise words: " + url.getPath());
            InputStream fileIn = url.openStream();
            InputStreamReader streamReader = new InputStreamReader(fileIn, Charset.forName("UTF-8"));
            BufferedReader buffReader = new BufferedReader(streamReader);
            while((line = buffReader.readLine()) != null) {
                noiseWords.add(line);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(KWIC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(KWIC.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<int[]> filteredList = new ArrayList();
        for(int i = 0;i < alphabetizedIndexPositions.length; i++) {
            filteredList.add(alphabetizedIndexPositions[i]);
        }
        for (int i = 0; i < alphabetizedIndexPositions.length; i++) {
            int start = alphabetizedIndexPositions[i][0];
            int end = alphabetizedIndexPositions[i][1];
            String sentence = "";
            for (int j = 0; j < end; j++) {
//                System.out.print(shiftedCharArray[circularShiftArray[start] + j]);
                sentence += shiftedCharArray[start + j];
            }
            if(sentence.contains(" ")) {
                sentence = sentence.substring(0, sentence.indexOf(" "));
                if (noiseWords.contains(sentence)) {
                    int x = filteredList.indexOf(alphabetizedIndexPositions[i]);
                    filteredList.remove(x);
                }
            }
        }
        alphabetizedIndexPositions = new int[filteredList.size()][2];
        for(int i = 0;i < filteredList.size(); i++) {
            alphabetizedIndexPositions[i][0] = filteredList.get(i)[0];
            alphabetizedIndexPositions[i][1] = filteredList.get(i)[1];
        }
    }
}
