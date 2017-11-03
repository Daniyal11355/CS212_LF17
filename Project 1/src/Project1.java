
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
        String file = "test_dates.txt";
        DateGUI gui = new DateGUI();
        //new array w/ proper size and correct format
        String[] dates = resizeArray(fromTxtToArray(file));
        // run originalArray into GUI
        gui.run(dates);
    }


    /**
     *
     * @param file  take txt file, open it, read each line
     *              separate with StringTokenizer
     *              pass through valueIsValid() to catch exception
     * @return      array of wanted values
     * @see  java 7 try-with-resources
     */

    private static String[] fromTxtToArray(String file) {
        // array with initial length
        String[] makeArray = new String[101];

        // open .txt
        // using java 7 try-with-resources
        try (
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr)
        ) {
            String line = br.readLine();
            int i = 0;
            while (line != null) {
                // test for multiple values on each line w/ StringTokenizer
                StringTokenizer myTokens = new StringTokenizer(line, ",");
                while(myTokens.hasMoreTokens()) {
                        // take out each token and test format
                        makeArray[i] = (myTokens.nextToken());

                        i += valueIsValid(makeArray[i]);
                } // while .hasMoreTokens
                line = br.readLine();
            } // while line!=null
        } catch (IOException | IllegalArgumentException  | ArrayIndexOutOfBoundsException e) {
            System.out.printf(String.valueOf(e));
            //e.printStackTrace();
        }
        return makeArray;
    }

    /**
     * <code>IndexOutOfBoundsException</code> method
     *
     * @param str  test for length, all integers, month and day boundaries
     * @return     0 if fails, 1 if passes
     */
    private static int valueIsValid(String str) throws IllegalArgumentException {
        int i = 0;
        // test for proper length

        try {
            if(str.length() != 8) {
                throw new IllegalArgumentException();
            }
            // test for all integers
            Integer.parseInt(str);
            // test is month is valid

            if (Integer.parseInt(str.substring(4, 6)) < 1 ||
                        Integer.parseInt(str.substring(4, 6)) > 12) {
                throw new IllegalArgumentException();
            }
            // test if day valid
            else if (Integer.parseInt(str.substring(6, 8)) < 1 ||
                        Integer.parseInt(str.substring(6, 8)) > 31) {
                throw new IllegalArgumentException();
            } else {
                // all valid
                i++;
            }
        } catch (NumberFormatException e) {
            //e.printStackTrace();
        } finally {
            return i;
        }
    }

    /**
     *
     * @param arr  change static array to == size of elements in array
     * @return     resized array
     * @see  java 8 -> lambdas
     */
    private static String[] resizeArray(String[] arr) {
        int counter = 0;
        // get length of array needed
        for(String arg: arr) {
            if(arg == null) {
                break;
            }
            counter++;
        }
        // create array to correct size and set values
        // using java 8 lambdas
        return IntStream.range(0, counter).mapToObj(i -> arr[i]).toArray(String[]::new);
    }
}