
// CSCI 212 Project 1 Due: October 20, 2017
//

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/**
 * <p>
 * Takes a file.txt argument and converts it into a static <code>array</code>
 * with proper length.
 * <p>
 * Before the <code>array</code> is passed into <code>DateGUI</code> need
 * check for <code>Exceptions</code> .
 *
 * @author  m.shamilov
 */


public class Project1 {

    /**
     * main method executes project
     *
     * @param args  passed as txt file and sets it as String
     */

    public static void main(String[] args) {

        DateGUI gui = new DateGUI();
        gui.run();
    }
}