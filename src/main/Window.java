package main;

import graphics.DataPack;
import graphics.GraphData;
import panels.*;
import rxtx.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Window extends JFrame /*implements SerialListener */{
    public static final long serialVersionUID = 1L;

    private JMenuBar menuBar;
    private JMenu menuFile, menuHelp, menuTools, submenuPorts, submenuBaud;
    private JMenuItem itemNew, itemAbout, itemEditStand;
    private JCheckBoxMenuItem itemBaud9600;

    private JTabbedPane tabbedPane;

    private String[] dataContent;
    private double[] counterteste = {0.0, 0.0, 0.0, 0.0};

    public Window() {
        super();

        getOSLookAndFeel();

        tabbedPane = new JTabbedPane();

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
        menuFile = new JMenu("Arquivo");
        itemNew = new JMenuItem("Nova bancada");
        menuTools = new JMenu("Ferramentas");
        submenuPorts = new JMenu("Ports");
        submenuBaud = new JMenu("Baud Rate");
        itemBaud9600 = new JCheckBoxMenuItem("9600");
        itemBaud9600.setState(true);
        itemEditStand = new JMenuItem("Editar Bancada");
        menuHelp = new JMenu("Ajuda");
        itemAbout = new JMenuItem("Sobre");

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

        itemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        itemNew.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        onSelectNewStand();
                    }
                }
        );

        itemEditStand.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
        itemEditStand.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //onEditStand();
                    TabPanel tp = (TabPanel)tabbedPane.getSelectedComponent();
                    if(tp != null) {
                        tp.alertsPanel.addText("Fodeu.", Color.RED);
                        System.out.println(tp.alertsPanel.getText());
                    }
                }
            }
        );

        menuFile.add(itemNew);
        menuHelp.add(itemAbout);
        menuTools.add(itemEditStand);
        menuTools.add(submenuPorts);
        submenuBaud.add(itemBaud9600);
        menuTools.add(submenuBaud);
        menuBar.add(menuFile);
        menuBar.add(menuTools);
        menuBar.add(menuHelp);

        add(tabbedPane);

        setTitle("Interface");
        setJMenuBar(menuBar);
        //tSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLayout(new GridLayout(1, 2));

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

    private void updateData(){}

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

    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }

    private void createTab(DataPack dataPack){
        TabPanel tp = new TabPanel(dataPack);
        tabbedPane.addTab(dataPack.getTabName(), tp);
        tabbedPane.setTabComponentAt(tabbedPane.getTabCount()-1, new ButtonTabPanel(tabbedPane));
        tabbedPane.setSelectedIndex(tabbedPane.getTabCount()-1);
        //this.repaint();
        pack();

        GraphData [] gd = new GraphData[tp.getGraphPanel().getGraph().getGraphData().length];
        int inteiro = 0;

//        for (int i = 0; i < tp.getGraphPanel().getGraph().getGraphData().length; i++){
        for (int i = 0; i < dataPack.getAvailableNum() - 2; i++){
            gd[i] = new GraphData(0, 140, 50);
            inteiro = 0;
            for (int k = 0; k < gd[i].getCurrValue().length; k++){
                gd[i].setCurrValue(k, inteiro);
                inteiro += i;
            }
        }

        tp.getGraphPanel().getGraph().setGraphData(gd);

    }

    private void createTab(DataPack dataPack, GraphData [] gd){
        TabPanel tp = new TabPanel(dataPack);
        tp.getGraphPanel().getGraph().setGraphData(gd);
        tabbedPane.addTab(dataPack.getTabName(), tp);
        tabbedPane.setTabComponentAt(tabbedPane.getTabCount()-1, new ButtonTabPanel(tabbedPane));
        tabbedPane.setSelectedIndex(tabbedPane.getTabCount()-1);
        //this.repaint();
        pack();
    }

    private void onSelectNewStand(){

        DataPack dataPack = null;

        ConfigStandWindow newStand = new ConfigStandWindow(this, dataPack, "Nova bancada");

        dataPack = newStand.showDialog();

        if(dataPack != null) {
            if (dataPack.getAvailableNum() > 2) {
                createTab(dataPack);
            }
        }


    }

    private void onEditStand(){

        TabPanel tp = (TabPanel)tabbedPane.getSelectedComponent();
        if(tp != null) {
            DataPack dataPack = tp.getDataPack();
            DataPack dp;

            ConfigStandWindow configStand = new ConfigStandWindow(this, dataPack, "Editar bancada");

            dp = configStand.showDialog();

            if(dp.getAvailableNum() > 2) {
                tabbedPane.remove(tabbedPane.getSelectedIndex());
                createTab(dp, tp.getGraphPanel().getGraph().getGraphData());
            }
        }
    }

}