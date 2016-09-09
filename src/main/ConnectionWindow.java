package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

/**
 * Created by olive on 07/09/2016.
 */
public class ConnectionWindow  extends JDialog {

    private GroupLayout layout;

    private JLabel l_port, l_baud;
    private JComboBox cb_port, cb_baud;

    private JButton b_ok, b_cancel;

    private String[] strv;

    private boolean option;

    public ConnectionWindow(JFrame parent, Enumeration e){

        super(parent);

        setModal(true);

        l_port = new JLabel("Port");
        l_baud = new JLabel("Baud");

        cb_port = new JComboBox();
        cb_port.addItem("Escolha a porta");
        for (; e.hasMoreElements();)
            cb_port.addItem(e.nextElement());

        cb_baud = new JComboBox();
        cb_baud.addItem("Escolha o baud rate");
        cb_baud.addItem("9600");

        b_ok = new JButton("Ok");
        b_ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                closeDialog(true);
            }
        });

        b_cancel = new JButton("Cancelar");
        b_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                closeDialog(false);
            }
        });

        strv = new String[]{"nope", "nope"};

        layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHonorsVisibility(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup()
                        .addComponent(l_port)
                        .addComponent(l_baud)
                        .addComponent(b_ok)
                    )
                    .addGroup(layout.createParallelGroup()
                        .addComponent(cb_port)
                        .addComponent(cb_baud)
                        .addComponent(b_cancel)
                    )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                            .addComponent(l_port)
                            .addComponent(cb_port)
                        )
                        .addGroup(layout.createParallelGroup()
                            .addComponent(l_baud)
                            .addComponent(cb_baud)
                        )
                        .addGroup(layout.createParallelGroup()
                            .addComponent(b_ok)
                            .addComponent(b_cancel)
                        )
        );

        pack();

        setTitle("Conexões");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

    }

    public String[] showDialog(){
        setVisible(true);
        return strv;
    }

    private String[] closeDialog(boolean b){
        option = b;

        if(b){
            strv[0] = (String)cb_port.getSelectedItem();
            strv[1] = (String)cb_baud.getSelectedItem();
        }else{
            strv[0] = "nope";
            strv[1] = "nope";
        }

        dispose();
        return strv;
    }
}
