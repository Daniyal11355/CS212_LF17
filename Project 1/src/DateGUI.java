
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.util.stream.IntStream;

/**
 * <p>
 * GUI should have a GridLayout with one row and two columns, left column should
 * display the originalArray in the format read from the file the right column should
 * display the originalArray as Date212 object in sorted order (using Selection Sort)
 *
 * @author  m.shamilov
 */


public class DateGUI extends JFrame{
    /**
     *  use a Dimensions array to setPreferredSize()
     *
     *  @see  java.awt.Dimension
     */
    private final Dimension WIN_SIZE = new Dimension(400,200);
    private JTextArea textAreaLeft, textAreaRight;
    /**
     * store original array
     */
    private String[] originalStringArray;
    /**
     * Date212 arrays for use original and sorted
     */
    private Date212[] originalArray, sortedArray;
    /**
     * initiates all JFrame elements
     *
     * @throws  HeadlessException
     */
    public DateGUI() throws HeadlessException {
        // set up JFrame parameters
        super("Project 1 : M.Shamilov");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(WIN_SIZE);
        this.setLocation(300, 250);
        this.setResizable(true);
        this.setLayout(new GridLayout(1, 2));

        // initialize text area
        this.textAreaLeft = this.textArea();
        this.textAreaRight = this.textArea();
        // initialize scroll pane
        JScrollPane scrollPaneLeft = new JScrollPane(this.textAreaLeft);
        JScrollPane scrollPaneRight = new JScrollPane(this.textAreaRight);
        // add everything to content pane
        this.getContentPane().add(scrollPaneLeft);
        this.getContentPane().add(scrollPaneRight);
        //
        this.pack();
        this.setVisible(true);
    }

    /**
     *
     * @param arr  pass in a String array to update Date212[] and update the gui
     */
    public void run(String[] arr) {
        this.setOriginalStringArray(arr);
        this.updateDates();
        this.updateJFrame();
    }

    /**
     * results to be displayed
     */
    private void updateJFrame() {
        // display original array
        this.textAreaLeft.setText(
                this.convertDateToSingleString(
                        this.getOriginalStringArray()));

        // display sorted array
        this.textAreaRight.setText(
                this.convertDateToSingleString(
                        this.getSortedArray()));
    }
    /**
    * update Date212[]
    */
    private void updateDates() {
        this.setOriginalArray(this.getOriginalStringArray());
        this.setSortedArray(this.getOriginalArray());
    }

    /**
     * creates a single string to display
     *
     * @param arr  an Object[] and runs toString on it
     *             using StringBuilder
     * @return     String to be displayed by gui
     */
    private String convertDateToSingleString(Object[] arr) {
        StringBuilder inputText = new StringBuilder();

        for(Object arg: arr) {
            inputText.append(String.format("%s%n", arg.toString()));
        }
        return inputText.toString();
    }

    /**
     *
     * @return  JFrame text area
     */
    private JTextArea textArea() {
        JTextArea textArea = new JTextArea(6, 12);
        textArea.setEditable(false);

        return textArea;
    }

    /**
     *
     * @param arr  passed as reference and is sorted
     */
    private void doSelectionSort(Date212[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j].compareTo(arr[index]) < 0) {
                    index = j;
                }
            }
            Date212 smallerNumber = arr[index];
            arr[index] = arr[i];
            arr[i] = smallerNumber;
        }
    }

    // getters/ setters
    public Date212[] getOriginalArray() {
        return originalArray;
    }
    /**
     *
     * @param originalArray  makes equivalent Date212[]
     * @see  java 8 -> lambdas
     */
    public void setOriginalArray(String[] originalArray) {
        this.originalArray = new Date212[originalArray.length];
        // fill the values of Date212 array
        // using java 8 lambdas
        IntStream.range(0, originalArray.length).forEach(
                i -> this.originalArray[i] = new Date212(originalArray[i]));
    }

    public Date212[] getSortedArray() {
        return sortedArray;
    }

    /**
     *
     * @param sortedArray  set value of sorted array and run sort method
     */
    public void setSortedArray(Date212[] sortedArray) {
        this.sortedArray = new Date212[sortedArray.length];
        // fill the values of Date212 array
        // using java 8 lambdas
        IntStream.range(0, sortedArray.length).forEach(
                i -> this.sortedArray[i] = sortedArray[i]);
        // run sort method on Date212 array
        this.doSelectionSort(this.sortedArray);
    }

    public String[] getOriginalStringArray() {
        return this.originalStringArray;
    }

    public void setOriginalStringArray(String[] originalStringArray) {
        this.originalStringArray = originalStringArray;
    }
}