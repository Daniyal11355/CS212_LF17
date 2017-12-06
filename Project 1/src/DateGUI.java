
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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
 *  JFrame class display given values
 *  JMenu options open, exit, insert
 *
 *  @author  m.shamilov
 */
public class DateGUI extends JFrame{
    /**
     *  use a Dimensions array to setPreferredSize()
     *
     *  @see  java.awt.Dimension
     */
    private final Dimension WIN_SIZE = new Dimension(400,200);
    // left and right display of date
    private JTextArea textAreaLeft, textAreaRight;
    // store the directory of file to open as String
    private String txtFile;
    // JFrame menu items
    private JMenuItem openAction;
    private JMenuItem exitAction;
    private JMenuItem insertAction;

    // linked lists not used for this example
    // private UnsortedDateList udl;
    // private SortedDateList sdl;

    // tree map
    private DateTreeMap dateTreeMap;
    /**
     * initiates all JFrame elements
     *
     * @throws  HeadlessException
     */
    public DateGUI() throws HeadlessException {
        // set up JFrame parameters
        super("Project 4 : M.Shamilov");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(WIN_SIZE);
        this.setLocation(300, 250);
        this.setResizable(true);
        this.setLayout(new GridLayout(1, 2));
        // Initialize tree map
        this.dateTreeMap = new DateTreeMap();
        // Initialize linked lists
        // this.udl = new UnsortedDateList();
        // this.sdl = new SortedDateList();
        // Create a menu bar
        JMenuBar menuBar = new JMenuBar();
        // Add bar
        this.setJMenuBar(menuBar);
        // Define and add two drop down menu to the menu bar
        JMenu fileMenu = new JMenu("File");
        // Add a second menu with one item
        JMenu editMenu = new JMenu("Edit");
        // Add menu to jframe
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        // Add dropdown file menu
        this.openAction = new JMenuItem("Open");
        this.exitAction = new JMenuItem("Exit");
        fileMenu.add(this.openAction);
        fileMenu.add(this.exitAction);
        // Add dropdown insert menu
        this.insertAction = new JMenuItem("Insert");
        editMenu.add(this.insertAction);
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
        this.insertDate();
    }

    /**
     *
     */
    private void insertDate() {
        this.insertAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String date = JOptionPane.showInputDialog(null,
                        "Add a new date\nformat: \"YYYYMMDD\"");

                addData(date);
            }
        });
    }

    /**
     *
     * results to be displayed
     */
    private void updateJFrame() {
        // clear before load new data
        // display original list
        // this.textAreaLeft.setText(this.udl.toString());
        this.textAreaLeft.setText(this.dateTreeMap.toString());
        // display sorted list
        // this.textAreaRight.setText(this.sdl.toString());
        this.textAreaRight.setText(this.dateTreeMap.toStringSorted());
    }

    /**
     *
     * clears the text area in jframe and linked lists
     */
    public void clear() {
        // clears treeMap
        this.dateTreeMap = new DateTreeMap();
        // clears linked lists
        //this.udl = new UnsortedDateList();
        //this.sdl = new SortedDateList();
        // clears jframe
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
                    clear();
                    dateFromFileToList();
                    updateJFrame();
                }
            }
        });
    }

    /**
     *
     * exit program dropdown option is selected
     *
     * @see java 8 lambda
     */
    public void selfExit() {
        this.exitAction.addActionListener(e -> System.exit(0));
    }

    /**
     *
     * opens a file and makes to linked lists
     * sorted and unsorted
     *
     * @see java 7 try-with-resources
     */
    public void dateFromFileToList() {
        try (
                FileReader fr = new FileReader(txtFile);
                BufferedReader br = new BufferedReader(fr)
        ) {
            String line = br.readLine();
            while(line != null) {
                String[] dateLine = line.split(",");

                for(String date: dateLine) {
                    addData(date);
                }
                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * updates uld and sdl after testing for IllegalDate212Exception
     *
     * @param date String given by user or file
     */
    private void addData(String date) {
        try {
            Date212 d = new Date212(date);
            this.dateTreeMap.addDate(d);
            // this.udl.add(d);
            // this.sdl.add(d, this.sdl);
            this.updateJFrame();
        } catch (IllegalDate212Exception ide) {
            System.out.println(ide);
            // ide.printStackTrace();
        }
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