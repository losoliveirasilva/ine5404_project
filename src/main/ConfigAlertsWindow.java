package main;

import panels.AlertsPanel;
import panels.ConfigAlertPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by losoliveirasilva on 6/22/16.
 */
public class ConfigAlertsWindow extends JDialog {

    //private GroupLayout layout;

    private JButton btnOk;
    private JButton btnCancel;

    private JScrollPane paneScrollPane;
    private JPanel panelception;

    private AlertManager am = new AlertManager();

    private String str[];

    public ConfigAlertsWindow(JFrame parent, AlertManager am, String[] sensorLabel){
        super(parent);

        str = sensorLabel;

        panelception = new JPanel();
        panelception.setLayout(new BoxLayout(panelception, BoxLayout.Y_AXIS));

        paneScrollPane = new JScrollPane(panelception);
        paneScrollPane.setVerticalScrollBarPolicy(
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //paneScrollPane.setPreferredSize(new Dimension(350, 180));
        //paneScrollPane.setMinimumSize(new Dimension(10, 10));

        //panelception.add(new ConfigAlertPanel(this, str));

        if(am != null){
            for (int i = 0; i < am.get().length; i++) {
                panelception.add(new ConfigAlertPanel(this, str, am.get()[i]));
            }
        }

        panelception.add(new ConfigAlertPanel(this, str));

        btnOk = new JButton("Ok");
        btnOk.setPreferredSize(new Dimension(80, 30));
        btnOk.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    closeDialog("Ok");
                }
            }
        );

        btnCancel = new JButton("Cancelar");
        btnCancel.setPreferredSize(new Dimension(80, 30));
        btnCancel.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    closeDialog("Cancel");
                }
            }
        );

        JPanel gapPanel = new JPanel();
        gapPanel.setPreferredSize(new Dimension(10, 0));

        //panel.add(paneScrollPane);

        /*GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);*/

        GroupLayout layout = new GroupLayout(this);
        layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHonorsVisibility(true);
        layout.setHonorsVisibility(btnOk, false);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addComponent(paneScrollPane)
                .addGroup(
                    layout.createSequentialGroup()
                        .addComponent(btnOk, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(gapPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                )
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(paneScrollPane)
                .addGroup(
                    layout.createParallelGroup()
                        .addComponent(btnOk, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(gapPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                )
        );

        //add(paneScrollPane);

        setModal(true);

        pack();

        this.addWindowListener(new WindowAdapter()
        {
            public void windowClosed(WindowEvent e)
            {

            }

            public void windowClosing(WindowEvent e)
            {
                closingDialog();
            }
        });

        setTitle("Configurar alertas");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(new Dimension(675, 500));
        setResizable(false);

    }

    public AlertManager showDialog(){
        setVisible(true);

        return am;
    }

    private void closingDialog(){
        am = null;
    }

    public void closeDialog(String btn){
        if(btn.equals("Ok")){
            for (int i = 0; i < (panelception.getComponentCount()); i++) {
                if(((ConfigAlertPanel)panelception.getComponent(i)).returnAlert() != null)
                    am.add(((ConfigAlertPanel)panelception.getComponent(i)).returnAlert());
            }
        }else{
            am = null;
        }

        dispose();
    }

    public void addAlertPanel(){
        panelception.add(new ConfigAlertPanel(this, str));
        revalidate();
        repaint();
    }

    public void deleteAlertPanel(ConfigAlertPanel cap){
        if(panelception.getComponentCount() == 1){
            panelception.remove(cap);
            addAlertPanel();
        }else{
            panelception.remove(cap);
            ((ConfigAlertPanel)(panelception.getComponent(panelception.getComponentCount()-1))).setFlag();
        }
        revalidate();
        repaint();
    }
}
