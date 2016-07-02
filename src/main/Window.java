package main;

import graphics.DataPack;
import graphics.GraphData;
import panels.*;
import rxtx.*;

import javax.swing.*;
import javax.swing.plaf.TabbedPaneUI;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;

public class Window extends JFrame /*implements SerialListener */{
    public static final long serialVersionUID = 1L;

    private JMenuBar menuBar;
    private JMenu menuFile, menuHelp, menuTools, submenuPorts, submenuBaud;
    private JMenuItem itemNew, itemAbout, itemEditStand, itemEditAlerts;
    private JCheckBoxMenuItem itemBaud9600;

    private JTabbedPane tabbedPane;

    private double[] counterteste = {0.0, 0.0, 0.0, 0.0, 0.0};

    String[] str = new String[5];

    public Window() {
        super();

        getOSLookAndFeel();

        tabbedPane = new JTabbedPane();

        /*TwoWaySerialComm serial = new TwoWaySerialComm(dataContent);
        serial.addListener(
            new SerialListener() {
                public void serialReceived() {
                    updateData();
                }
            }
        );*/

        //serial.getPortList();

        menuBar = new JMenuBar();
        menuFile = new JMenu("Arquivo");
        itemNew = new JMenuItem("Nova bancada");
        menuTools = new JMenu("Ferramentas");
        submenuPorts = new JMenu("Ports");
        submenuBaud = new JMenu("Baud Rate");
        itemBaud9600 = new JCheckBoxMenuItem("9600");
        itemBaud9600.setState(true);
        itemEditStand = new JMenuItem("Editar Bancada");
        itemEditAlerts = new JMenuItem("Editar Alertas");
        menuHelp = new JMenu("Ajuda");
        itemAbout = new JMenuItem("Sobre");

        itemAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
        itemAbout.addActionListener(
            e -> updateData()
        );

        itemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        itemNew.addActionListener(
            e -> onSelectNewStand()
        );

        itemEditStand.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
        itemEditStand.addActionListener(
            e -> onEditStand()
        );

        //itemEditAlerts.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
        itemEditAlerts.addActionListener(
            e -> onSelectConfigAlert()
        );

        menuFile.add(itemNew);
        menuHelp.add(itemAbout);
        menuTools.add(itemEditStand);
        menuTools.add(itemEditAlerts);
        menuTools.add(submenuPorts);
        submenuBaud.add(itemBaud9600);
        menuTools.add(submenuBaud);
        menuBar.add(menuFile);
        menuBar.add(menuTools);
        menuBar.add(menuHelp);

        add(tabbedPane);

        setTitle("Interface");
        setJMenuBar(menuBar);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setMinimumSize(new Dimension(1175, 715));

        pack();

        updateData();

    }

    private void onSelectConfigAlert(){

        if(tabbedPane.getSelectedComponent() != null){

            String[] sensoresold = ((TabPanel) tabbedPane.getSelectedComponent()).getDataPack().getAvailableString();
            String[] sensoresnew = new String[sensoresold.length - 2];

            for (int i = 0; i < sensoresnew.length; i++) {
                sensoresnew[i] = sensoresold[i + 2];
            }

            ConfigAlertsWindow caw = new ConfigAlertsWindow(this, ((TabPanel) tabbedPane.getSelectedComponent()).alertManager(), sensoresnew);

            AlertManager am = caw.showDialog();

            if (am != null) {
                if (tabbedPane.getSelectedComponent() != null) {
                    ((TabPanel) tabbedPane.getSelectedComponent()).alertManager(am);
                }
            }
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

        str[0] = "" + (125 + (int)(Math.sin(counterteste[0])*10));
        str[1] = "" + (100 + (int)(Math.sin(counterteste[1])*10));
        str[2] = "" + (75 + (int)(Math.sin(counterteste[2])*10));
        str[3] = "" + (50 + (int)(Math.sin(counterteste[3])*10));
        str[4] = "" + (25 + (int)(Math.sin(counterteste[4])*10));

        if(tabbedPane.getSelectedComponent() != null)
            ((TabPanel)tabbedPane.getSelectedComponent()).setNewData(str);

        for(int i = 0; i < 5; i++) {
            counterteste[i] += .5;
            if (counterteste[i] > 360) {
                counterteste[i] = 0;
            }
        }
    }

    private void createTab(DataPack dataPack){
        TabPanel tp = new TabPanel(dataPack);
        tabbedPane.addTab(dataPack.getTabName(), tp);
        tabbedPane.setTabComponentAt(tabbedPane.getTabCount()-1, new ButtonTabPanel(tabbedPane));
        tabbedPane.setSelectedIndex(tabbedPane.getTabCount()-1);
        //this.repaint();
        pack();
    }

    private void onSelectNewStand(){

        DataPack dataPack = null;

        ConfigStandWindow newStand = new ConfigStandWindow(this, dataPack, "Nova bancada", true);

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

            ConfigStandWindow configStand = new ConfigStandWindow(this, dataPack, "Editar bancada", false);

            dp = configStand.showDialog();

            if(dp.getAvailableNum() > 2) {
                tp.setDataPack(dp);
            }
        }
    }

}