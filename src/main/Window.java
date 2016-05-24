package main;

import gnu.io.RXTXVersion;
import panels.*;
import rxtx.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;

import static javax.swing.GroupLayout.Alignment.*;

public class Window extends JFrame /*implements SerialListener */{
    public static final long serialVersionUID = 1L;

    private JMenuBar menuBar;
    private JMenu menuFile, menuHelp, menuTools, submenuPorts, submenuBaud;
    private JMenuItem itemNew, itemAbout;
    private JCheckBoxMenuItem itemBaud9600;

    private DataPanel dataPanel;
    private GraphPanel graphPanel;

    private String[] dataContent;
    private double[] counterteste = {0.0, 0.0, 0.0, 0.0};

    public Window() {
        super();

        getOSLookAndFeel();

        dataContent = new String[]{"0", "0", "0", "0"};

        TwoWaySerialComm serial = new TwoWaySerialComm(dataContent);
        serial.addListener(
            new SerialListener() {
                public void serialReceived() {
                    updateData();
                }
            }
        );

        serial.getPortList();

        menuBar = new JMenuBar();
        menuFile = new JMenu("File");
        itemNew = new JMenuItem("New");
        menuTools = new JMenu("Tools");
        submenuPorts = new JMenu("Ports");
        submenuBaud = new JMenu("Baud Rate");
        itemBaud9600 = new JCheckBoxMenuItem("9600");
        itemBaud9600.setState(true);
        menuHelp = new JMenu("Help");
        itemAbout = new JMenuItem("About");

        dataPanel = new DataPanel();
        graphPanel = new GraphPanel();

        itemAbout.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (int i=0; i < dataContent.length; i++)
                            dataContent[i] = Integer.toString(randInt(0, 100));
                        updateData();
                    }
                }
        );

        itemNew.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            serial.connect("/dev/ttyACM0");
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                }
        );

        menuFile.add(itemNew);
        menuHelp.add(itemAbout);
        menuTools.add(submenuPorts);
        submenuBaud.add(itemBaud9600);
        menuTools.add(submenuBaud);
        menuBar.add(menuFile);
        menuBar.add(menuTools);
        menuBar.add(menuHelp);

        setTitle("Interface");
        setJMenuBar(menuBar);
        //tSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLayout(new GridLayout(1, 2));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dataPanel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(graphPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                //.addComponent(graphPanel)
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(CENTER)
                    .addComponent(dataPanel)
                    .addComponent(graphPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    //.addComponent(graphPanel)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)

        );

        setMinimumSize(new Dimension(905, 698));

        pack();

        updateData();

    }

    public void teste() {

        for (int i = 0; i < dataContent.length; i++){
            if (i%2 ==0)
                dataContent[i] = "" + (70 + (int)(Math.sin(counterteste[i])*40));
            else
                dataContent[i] = "" + (70 + (int)(Math.cos(counterteste[i])*40));
            counterteste[i] += .05;
            if (counterteste[i] > 360){
                counterteste[i] = 0;
            }
        }
        updateData();
        try {
            Thread.sleep(50);
        } catch (InterruptedException ie) {
            //Handle exception
        }

    }

    private void getOSLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void updateData(){
        dataPanel.updateLabels(dataContent);
        graphPanel.updateGraph(dataContent);
    }

    public static int randInt(int min, int max) {

        // NOTE: This will (intentionally) not run as written so that folks
        // copy-pasting have to think about how to initialize their
        // Random instance.  Initialization of the Random instance is outside
        // the main scope of the question, but some decent options are to have
        // a field that is initialized once and then re-used as needed or to
        // use ThreadLocalRandom (if using at least Java 1.7).
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

}
