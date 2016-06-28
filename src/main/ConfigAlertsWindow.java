package main;

import panels.AlertsPanel;
import panels.ConfigAlertPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by losoliveirasilva on 6/22/16.
 */
public class ConfigAlertsWindow extends JDialog {

    //private GroupLayout layout;

    private JButton btnOk;
    private JButton btnCancel;

    private JScrollPane paneScrollPane;
    private JPanel panelception;

    public ConfigAlertsWindow(JFrame parent, AlertManager am){
        super(parent);

        String str[] = {"Foo", "Bla"};

        panelception = new JPanel();
        panelception.setLayout(new BoxLayout(panelception, BoxLayout.Y_AXIS));

        paneScrollPane = new JScrollPane(panelception);
        paneScrollPane.setVerticalScrollBarPolicy(
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //paneScrollPane.setPreferredSize(new Dimension(350, 180));
        //paneScrollPane.setMinimumSize(new Dimension(10, 10));

        panelception.add(new ConfigAlertPanel(str));
        /*
        panelception.add(new ConfigAlertPanel(str));
        panelception.add(new ConfigAlertPanel(str));
        panelception.add(new ConfigAlertPanel(str));*/

        add(paneScrollPane);

        setModal(true);

        pack();

        setTitle("Configurar alertas");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(new Dimension(635, 300));
        setResizable(false);
    }

    public void showDialog(){
        setVisible(true);
    }
}
