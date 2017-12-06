
// CSCI 212 Project 4 Due: December 11, 2017
//


/**
 * Simple GUI takes a list of dates and converts them from
 * "20040229" ro February 29, 2004
 * shows a sorted and unsorted side
 * checks for proper date format
 *
 * can use a <code>LinkedList</code> or <code>TreeMap</code> to show results
 *
 * Note: previous version has <code>String</code> option
 *
 * @author  m.shamilov
 */


public class Project1 {

    /**
     * main method executes project
     *
     * @param args not used in this version
     */
    public static void main(String[] args) {

        DateGUI gui = new DateGUI();
        gui.run();
    }
}