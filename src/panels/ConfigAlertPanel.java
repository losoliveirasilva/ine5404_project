package panels;

import main.Alert;
import main.ConfigAlertsWindow;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.GroupLayout.Alignment.CENTER;

/**
 * Created by losoliveirasilva on 6/28/16.
 */
public class ConfigAlertPanel extends JPanel{

    private JComboBox comboBox;
    private JLabel lbl0;
    private JLabel lbl1;
    private JCheckBox cbmenor;
    private JCheckBox cbmaior;
    private JTextField tfmenor;
    private JTextField tfmaior;
    private JButton btnSave;
    private JButton btnDelete;
    private ConfigAlertsWindow caw;
    private boolean flag;
    private GroupLayout layout;

    public ConfigAlertPanel(ConfigAlertsWindow caw, String[]sensors){
        configPanel(sensors);
        this.caw = caw;
    }

    public ConfigAlertPanel(ConfigAlertsWindow caw, String[]sensors, Alert a){
        configPanel(sensors);
        this.caw = caw;

        cbmenor.setSelected(a.lessThanFlag());
        cbmaior.setSelected(a.greaterThanFlag());

        tfmenor.setText(a.lessThanValue() + "");
        tfmaior.setText(a.greaterThanValue() + "");

        comboBox.setSelectedItem(a.sensor());

        flag = false;
        onSave();

    }

    private void configPanel(String[] s){

        this.setBorder(BorderFactory.createLineBorder(Color.black));


        flag = true;

        layout = new GroupLayout(this);

        lbl0 = new JLabel("Notificar quando o sensor");
        comboBox = new JComboBox();
        comboBox.addItem("Nenhum");
        for (int i = 0; i < s.length; i++) {
            comboBox.addItem(s[i]);
        }
        comboBox.setPreferredSize(new Dimension(124, 24));
        comboBox.addActionListener(
            new ActionListener () {
                @Override
                public void actionPerformed(ActionEvent e) {
                    onChange();
                }
            }
        );
        lbl1 = new JLabel("for");
        cbmenor = new JCheckBox("menor que");
        cbmenor.addActionListener(
            new ActionListener () {
                @Override
                public void actionPerformed(ActionEvent e) {
                    onChange();
                }
            }
        );
        tfmenor = new JTextField();
        tfmenor.getDocument().addDocumentListener(
            new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                    onChange();
                }
                public void removeUpdate(DocumentEvent e) {
                    onChange();
                }
                public void insertUpdate(DocumentEvent e) {
                    onChange();
                }
            }
        );
        tfmenor.setPreferredSize(new Dimension(80, 24));
        cbmaior = new JCheckBox("maior que");
        cbmaior.addActionListener(
            new ActionListener () {
                @Override
                public void actionPerformed(ActionEvent e) {
                    onChange();
                }
            }
        );
        tfmaior = new JTextField();
        tfmaior.getDocument().addDocumentListener(
            new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                    onChange();
                }
                public void removeUpdate(DocumentEvent e) {
                    onChange();
                }
                public void insertUpdate(DocumentEvent e) {
                    onChange();
                }
            }
        );
        tfmaior.setPreferredSize(new Dimension(80, 24));
        btnSave = new JButton("Salvar");
        btnSave.setPreferredSize(new Dimension(80, 30));
        btnSave.setEnabled(false);
        btnSave.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    onSave();
                }
            }
        );
        btnDelete = new JButton("Deletar");
        btnDelete.setPreferredSize(new Dimension(80, 30));
        btnDelete.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    removeThis();
                }
            }
        );

        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addComponent(lbl0)
                .addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(lbl1)
                .addGroup(
                    layout.createParallelGroup()
                        .addComponent(cbmaior)
                        .addComponent(cbmenor)
                )
                .addGroup(
                    layout.createParallelGroup()
                        .addComponent(tfmaior, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfmenor, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(
                    layout.createParallelGroup()
                        .addComponent(btnSave, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                )
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(CENTER)
                .addComponent(lbl0)
                .addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(lbl1)
                .addGroup(
                    layout.createSequentialGroup()
                        .addComponent(cbmaior)
                        .addComponent(cbmenor)
                )
                .addGroup(
                    layout.createSequentialGroup()
                        .addComponent(tfmaior, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfmenor, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(
                    layout.createSequentialGroup()
                        .addComponent(btnSave, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                )
        );
    }

    private void onSave(){
        if(flag) {
            caw.addAlertPanel();
            flag = false;
        }
        btnSave.setEnabled(false);
    }

    private void onChange(){
        if( comboBox.getSelectedItem().equals("Nenhum") ||
            (cbmaior.isSelected() && tfmaior.getText().equals("")) ||
            (cbmenor.isSelected() && tfmenor.getText().equals("")) ||
            (!cbmaior.isSelected() && !cbmenor.isSelected()))
        {
            btnSave.setEnabled(false);
        }else{
            btnSave.setEnabled(true);
        }
    }

    public void setFlag(){
        flag = true;
    }

    private void removeThis(){
        caw.deleteAlertPanel(this);
        flag = true;
    }

    public Alert returnAlert(){

        int lt, gt;

        if(comboBox.getSelectedItem().toString().equals("Nenhum")){
            return null;
        }

        if(cbmenor.isSelected()){
            lt = Integer.parseInt(tfmenor.getText());
        }else {
            lt = -1;
        }

        if(cbmaior.isSelected()){
            gt = Integer.parseInt(tfmaior.getText());
        }else {
            gt = -1;
        }

        Alert a = new Alert(comboBox.getSelectedItem().toString(), comboBox.getSelectedIndex()-1, lt, gt);

        return a;
    }

}
