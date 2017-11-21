
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
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

    private String txtFile;
    private JMenuItem openAction;
    private JMenuItem exitAction;

    private UnsortedDateList udl;
    private SortedDateList sdl;
    /**
     * initiates all JFrame elements
     *
     * @throws  HeadlessException
     */
    public DateGUI() throws HeadlessException {
        // set up JFrame parameters
        super("Project 2 : M.Shamilov");
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
        // Add dropdown menu=
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

    /**
     *
     * run method
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

        // display sorted array
        this.textAreaRight.setText(
                this.convertDateToSingleString(this.sdl));
    }

    /**
     *
     * clears the text area in jframe
     */
    public void clear() {
        this.textAreaRight.setText("");
        this.textAreaLeft.setText("");
    }

    /**
     * open file dropdown option is selected
     * and sets it to txtFile
     *
     * if new file is opened updates jframe
     */

    public void openFile() {

        this.openAction.addActionListener(new ActionListener() {
            Component frame;

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new java.io.File("."));
                chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                if (chooser.showOpenDialog(frame) == JFileChooser.OPEN_DIALOG) {
                    // convert file location to string
                    txtFile = (String.valueOf(chooser.getSelectedFile()));

                    // update with new file and display in jframe
                    dateFromFileToList();
                    updateJFrame();
                } else {
                    // do when cancel
                }
            }
        });
    }

    /**
     *
     * exit program dropdown option is selected
     */
    public void selfExit() {
        this.exitAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     *
     * opens a file and makes to linked lists
     * sorted and unsorted
     */
    // get values from file
    public void dateFromFileToList() {
        try (
                FileReader fr = new FileReader(txtFile);
                BufferedReader br = new BufferedReader(fr)
        ) {
            udl = new UnsortedDateList();
            sdl = new SortedDateList();
            String line = br.readLine();
            while(line != null) {
                String[] dateLine = line.split(",");

                for(String date: dateLine) {
                    if(valueIsValid(date)) {
                        udl.add(new Date212(date));
                        sdl.add(new Date212(date), sdl);

                    }
                }
                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
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

    /**
     * Exception handling method
     *
     * @param str given and checked to fit Date212 requirements
     * @return true only if passed all tests
     * @throws IllegalArgumentException
     */
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
}
