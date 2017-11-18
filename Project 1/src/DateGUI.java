
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.StringTokenizer;
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

    public String txtFile = null;
    JMenuItem openAction;
    JMenuItem exitAction;

    UnsortedDateList udl;
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
        // Create a menu bar
        JMenuBar menuBar = new JMenuBar();
        // Add bar
        this.setJMenuBar(menuBar);
        // Define and add two drop down menu to the menubar
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        // Add dropdowns

        this.openAction = new JMenuItem("Open");
        this.exitAction = new JMenuItem("Exit");
        fileMenu.add(this.openAction);
        fileMenu.add(this.exitAction);

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

    // clear text area
    public void clear() {
        this.textAreaRight.setText("");
        this.textAreaLeft.setText("");
    }

    // sets txtFile to a new file name
    public void openFile() {

        this.openAction.addActionListener(new ActionListener() {
            Component frame;

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new java.io.File("."));
                //chooser.setSelectedFile(new File(""));
                chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                // chooser.setAcceptAllFileFilterUsed(false);
                if (chooser.showOpenDialog(frame) == JFileChooser.OPEN_DIALOG) {
                    String[] fl = (String.valueOf(chooser.getSelectedFile()).split("\\\\"));
                    txtFile = fl[fl.length - 1];


                    dateFromFileToList();
                    updateJFrame();
                } else {
                    // do when cancel
                }
            }

        });
    }

    public void selfExit() {
        this.exitAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    // get values from file
    public void dateFromFileToList() {


        try (
                FileReader fr = new FileReader(txtFile);
                BufferedReader br = new BufferedReader(fr)
        ) {
            udl = new UnsortedDateList();
            String line = br.readLine();
            while(line != null) {
                String[] dateLine = line.split(",");

                for(String date: dateLine) {
                    if(valueIsValid(date)) {
                        udl.append(new Date212(date));
                    }
                }
                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private boolean valueIsValid(String str) throws IllegalArgumentException {
        boolean data = false;
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
                data = true;
            }
        } catch (NumberFormatException e) {
            //e.printStackTrace();
        } finally {
            return data;
        }
    }

    /**
     *
     *
     */
    public void run() {

        this.openFile();
        this.selfExit();

    }

    /**
     * results to be displayed
     */
    private void updateJFrame() {
        // display original array

        this.textAreaLeft.setText(
                this.convertDateToSingleString(this.udl));
/*
        // display sorted array
        this.textAreaRight.setText(
                this.convertDateToSingleString(
                        this.getSortedArray()));
   */
    }


    /**
     * creates a single string to display
     *
     * @param dl  an DateList and runs toString on it
     *             using StringBuilder
     * @return     String to be displayed by gui
     */

    private String convertDateToSingleString(DateList dl) {
        StringBuilder inputText = new StringBuilder();

        DateListIterator dll = dl.reset();
        while(dll.hasNext()) {
            inputText.append(String.format("%s%n", dll.next()));
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
}