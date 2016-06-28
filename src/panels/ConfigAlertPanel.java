package panels;

import main.Alert;

import javax.swing.*;
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
    private JButton btn;

    public ConfigAlertPanel(String[] sensors){
        configPanel(sensors);
        this.setBorder(BorderFactory.createLineBorder(Color.black));

        GroupLayout layout = new GroupLayout(this);
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
                .addComponent(btn, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
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
                .addComponent(btn, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        );

    }

    public ConfigAlertPanel(Alert a){

    }

    private void configPanel(String[] s){
        lbl0 = new JLabel("Notificar quando o sensor");
        comboBox = new JComboBox(s);
        comboBox.setPreferredSize(new Dimension(100, 24));
        lbl1 = new JLabel("for");
        cbmenor = new JCheckBox("menor que");
        tfmenor = new JTextField();
        tfmenor.setPreferredSize(new Dimension(80, 24));
        cbmaior = new JCheckBox("maior que");
        tfmaior = new JTextField();
        tfmaior.setPreferredSize(new Dimension(80, 24));
        btn = new JButton("Salvar");
        btn.setPreferredSize(new Dimension(80, 30));
    }

}
