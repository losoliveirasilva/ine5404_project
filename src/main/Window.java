package main;

import graphics.*;
import panels.*;
//import rxtx.*;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;

class Window extends JFrame /*implements SerialListener */{
    public static final long serialVersionUID = 1L;

    private JTabbedPane tabbedPane;

    private StandFileInfo standFileInfo;
    private GreenhouseFileInfo greenhouseFileInfo;

    private JFileChooser fc;

    private JScrollPane paneScrollPane;

    private double[] counterteste = {0.0, 0.0, 0.0, 0.0, 0.0};

    private String[] str = new String[5];

    Window() {
        super();

        getOSLookAndFeel();

        fc = new JFileChooser();
        fc.addChoosableFileFilter(new GreenhouseFileFilter());
        fc.addChoosableFileFilter(new StandFileFilter());


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

        JMenuBar menuBar;
        JMenu menuFile, menuHelp, menuTools, submenuPorts, submenuBaud;
        JMenuItem itemSaveGrennhouseFile, itemSaveStandFile, itemOpenFile, itemNew, itemAbout, itemEditStand, itemEditAlerts;
        JCheckBoxMenuItem itemBaud9600;

        menuBar = new JMenuBar();
        menuFile = new JMenu("Arquivo");
        itemNew = new JMenuItem("Nova bancada");
        itemOpenFile = new JMenuItem("Abrir...");
        itemSaveGrennhouseFile = new JMenuItem("Salvar estufa");
        itemSaveStandFile = new JMenuItem("Salvar bancada");
        menuTools = new JMenu("Ferramentas");
        submenuPorts = new JMenu("Ports");
        submenuBaud = new JMenu("Baud Rate");
        itemBaud9600 = new JCheckBoxMenuItem("9600");
        itemBaud9600.setState(true);
        itemEditStand = new JMenuItem("Editar Bancada");
        itemEditAlerts = new JMenuItem("Editar Alertas");
        menuHelp = new JMenu("Ajuda");
        itemAbout = new JMenuItem("Sobre");

        itemSaveGrennhouseFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        itemSaveGrennhouseFile.addActionListener(
            e -> {
                try{
                    saveGreenhouseFile();
                }
                catch (IOException i){
                    System.out.println(i.toString());
                }
            }
        );

        itemSaveStandFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_MASK));
        itemSaveStandFile.addActionListener(
            e -> {
                try{
                    saveStandFile();
                }
                catch (IOException i){
                    System.out.println(i.toString());
                }
            }
        );

        itemOpenFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        itemOpenFile.addActionListener(
            e -> {
                try{
                    String strReturn;
                    strReturn = openFile();
                    if(strReturn.equals("ghf")){
                        openedGreehouseFile();
                    }else if(strReturn.equals("stf")){
                        openedStandFile();
                    }
                }
                catch (IOException | ClassNotFoundException i){
                    System.out.println(i.toString());
                }
            }
        );

        itemAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
        itemAbout.addActionListener(
            e -> {
                updateData();
                //JOptionPane.showMessageDialog(this, "Janela \"Sobre\"", "Sobre", JOptionPane.INFORMATION_MESSAGE);
            }
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
        menuFile.add(itemOpenFile);
        menuFile.add(itemSaveGrennhouseFile);
        menuFile.add(itemSaveStandFile);
        menuHelp.add(itemAbout);
        menuTools.add(itemEditStand);
        menuTools.add(itemEditAlerts);
        menuTools.add(submenuPorts);
        submenuBaud.add(itemBaud9600);
        menuTools.add(submenuBaud);
        menuBar.add(menuFile);
        menuBar.add(menuTools);
        menuBar.add(menuHelp);

        paneScrollPane = new JScrollPane(tabbedPane);
        paneScrollPane.setVerticalScrollBarPolicy(
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        paneScrollPane.setHorizontalScrollBarPolicy(
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //add(tabbedPane);

        add(paneScrollPane);

        setTitle("Kodama");
        setJMenuBar(menuBar);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //setMinimumSize(new Dimension(1175, 715));
        setPreferredSize(new Dimension(1175, 750));

        pack();

        updateData();

    }

    private String openFile() throws IOException, ClassNotFoundException{
        fc.setSelectedFile(new File(""));

        int returnVal = fc.showOpenDialog(this);

        String extension = "";

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();


            String s = file.getName();
            int i = s.lastIndexOf('.');

            if (i > 0 &&  i < s.length() - 1) {
                extension = s.substring(i+1).toLowerCase();
            }


            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream (fis);

            if(extension.equals("ghf")){
                greenhouseFileInfo = (GreenhouseFileInfo)in.readObject();
            }

            if(extension.equals("stf")){
                standFileInfo = (StandFileInfo)in.readObject();
            }

            fis.close();
            in.close();

        }

        return extension;

    }

    private void openedGreehouseFile(){
        StandFileInfo[] s = greenhouseFileInfo.get();
        tabbedPane.removeAll();
        for (StandFileInfo value : s) {
            createTab(value.dataPack(), value.alerts(), value.graphData(), value.alertManager());
        }

    }

    private void openedStandFile(){
        createTab(standFileInfo.dataPack(), standFileInfo.alerts(), standFileInfo.graphData(), standFileInfo.alertManager());
        //System.out.println("Leu: " + standFileInfo.dataPack().getTabName());
    }

    private void saveGreenhouseFile() throws IOException{
        GreenhouseFileInfo gfi = new GreenhouseFileInfo();
        int returnVal;

        for (int i = 0; i < tabbedPane.getTabCount(); i++) {
            gfi.add(((TabPanel) tabbedPane.getComponentAt(i)).standFileInfo());
        }

        if(tabbedPane.getComponentCount() > 0) {
            fc.setSelectedFile(new File("untitled.ghf"));
            returnVal = fc.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File saveFile = fc.getSelectedFile();
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(saveFile));
                out.writeObject(gfi);
                out.close();
            }
        }
    }

    private void saveStandFile() throws IOException{
        TabPanel tp = ((TabPanel) tabbedPane.getSelectedComponent());
        int returnVal;

        if(tp != null) {
            //returnVal = fc.showOpenDialog(this);
            fc.setSelectedFile(new File(tp.getDataPack().getTabName().toLowerCase() + ".stf"));
            returnVal = fc.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File saveFile = fc.getSelectedFile();
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(saveFile));
                out.writeObject(tp.standFileInfo());
                out.close();
            }
        }
    }

    private void onSelectConfigAlert(){

        if(tabbedPane.getSelectedComponent() != null){

            String[] sensoresold = ((TabPanel) tabbedPane.getSelectedComponent()).getDataPack().getAvailableString();
            String[] sensoresnew = new String[sensoresold.length - 2];

            System.arraycopy(sensoresold, 2, sensoresnew, 0, sensoresnew.length);

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

        str[0] = Integer.toString(125 + (int)(Math.sin(counterteste[0])*10));
        str[1] = Integer.toString(100 + (int)(Math.cos(counterteste[1])*10));
        str[2] = Integer.toString(75 + (int)(Math.sin(counterteste[2])*10));
        str[3] = Integer.toString(50 + (int)(Math.sin(counterteste[3])*10));
        str[4] = Integer.toString(25 + (int)(Math.sin(counterteste[4])*10));

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

    private void createTab(DataPack dataPack, String str, GraphData[] g, AlertManager am){

        TabPanel tp = new TabPanel(dataPack);
        tp.addTextAlertPanel(str.substring(28), "red_b");
        tp.alertManager(am);
        tp.graphData(g);

        String[] labelup = new String[g.length];
        for (int i = 0; i < labelup.length; i++) {
            labelup[i] = "" + g[i].getCurrValue()[g[i].getCurrValue().length - 1];
        }

        tp.dataPanel().updateLabels(labelup);
        /*tp.getGraphPanel().getGraph().repaint();
        tp.getGraphPanel().repaint();
        tp.repaint();*/
        tabbedPane.addTab(dataPack.getTabName(), tp);
        tabbedPane.setTabComponentAt(tabbedPane.getTabCount()-1, new ButtonTabPanel(tabbedPane));
        tabbedPane.setSelectedIndex(tabbedPane.getTabCount()-1);
        //this.repaint();
        pack();
    }

    private void onSelectNewStand(){

        DataPack dataPack;

        ConfigStandWindow newStand = new ConfigStandWindow(this, null, "Nova bancada", true);

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

            if((dp != null) && (dp.getAvailableNum() > 2)){
                tp.setDataPack(dp);
            }
        }
    }

}