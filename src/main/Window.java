package main;

import panels.*;
import rxtx.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import static javax.swing.GroupLayout.Alignment.*;

public class Window extends JFrame {
    public static final long serialVersionUID = 1L;

    private JMenuBar menuBar;
    private JMenu menuFile, menuHelp, menuTools, submenuPorts, submenuBaud;
    private JMenuItem itemNew, itemAbout;
    private JCheckBoxMenuItem itemBaud9600;

    private DataPanel dataPanel;
    private GraphPanel graphPanel;

    private String[] dataContent;

    private TwoWaySerialComm serial;

    public Window() {
        super();

        getOSLookAndFeel();

        dataContent = new String[]{"25", "50", "75", "100"};

        serial = new TwoWaySerialComm(dataContent);

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

        dataPanel = new DataPanel(dataContent);
        graphPanel = new GraphPanel();

        itemAbout.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                }
        );

        itemNew.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

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

        graphPanel.graph.updateData(dataContent);

    }

    private void getOSLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /*private void onSelectRegisterProduct() {
        WindowEditProduct editProductWindow = new WindowEditProduct(this);
        editProductWindow.setVisible(true);
    }*/

}