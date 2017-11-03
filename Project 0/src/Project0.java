
// CSCI 212 Project 0 Due: September 27, 2017
//

/*
    * using charAr method
    Write a Java program that will
    1. Ask the user to type in a sentence, using a JOptionPane.showInputDialog().
    2. The program will examine each letter in the string and count how many time the upper-case letter 'E' appears,
        and how many times the lower-case letter 'e' appears. The key here is to use the charAt method in class String.
    3. Using a JOptionPane.showMessageDialog(), tell the user how many upper and lower case e's were in the string.
    4. Repeat this process until the user types the word "Stop".
 */

import javax.swing.JOptionPane;

public class Project0 {
    public static void main(String[] args) {
        // initialize NumberOfLetters class
        NumberOfEs numberOfEs = new NumberOfEs();
        // call mainApp() method
        numberOfEs.run();
    } // main
} // Project0

class NumberOfEs {
    // all variable are private and only used inside the class methods
    private String stringInput; // store user input
    private int[] eCounter; // correct letter counter

    // prompt user for a sentence with JOptionPane
    private void promptForString() {
        // assign stringInput with a sentence from user
        this.stringInput = JOptionPane.showInputDialog(null,
                "Please enter a sentence.");
    }

    // loop over string and count the letters e, E
    private void countLetters() {
        // initialize the counter for the letters [0]==e [1]==E
        this.eCounter = new int[]{0,0};
        // for loop through stringInput
        for(int i = 0; i < this.stringInput.length(); i++ ) {
            // test if letter is found and update counter
            // use .charAt to get a character while still maintaining the letter as String
            if(String.valueOf(this.stringInput.charAt(i)).equals("e")) {
                this.eCounter[0]++;
            }
            if(String.valueOf(this.stringInput.charAt(i)).equals("E")) {
                this.eCounter[1]++;
            }
        }// for
    }

    // show user results using JOptionPane
    private void showResults() {
        // message to user the total tally
        JOptionPane.showMessageDialog(null,
                "Number of lower case e's: " + this.eCounter[0]
                        + "\nNumber of upper case e's: " + this.eCounter[1]);
    }

    // main app puts everything together and set public for access
    public void run() {
        // run a while loop until user stops it
        while(true) {
            // prompt user for input
            this.promptForString();
            // test for stop keyword, break out of the while loop
            if(this.stringInput.equalsIgnoreCase("stop")) {
                break;
            }
            // if not stopped
            // count the letters
            this.countLetters();
            // show the results
            this.showResults();
        } // while
    }
} // NumberOfEs

