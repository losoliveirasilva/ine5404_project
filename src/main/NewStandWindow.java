package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.GroupLayout.Alignment.CENTER;

public class NewStandWindow extends JDialog{

    private JLabel lblCodigo;
    private JTextField edtCodigo;
    private JButton btnNext;

    private JButton btnOk;
    private JButton btnCancel;

    public NewStandWindow(JFrame parent){
        super(parent);

        setModal(true);

        lblCodigo = new JLabel("Código:");
        edtCodigo = new JTextField();
        edtCodigo.setPreferredSize(new Dimension(80, 24));
        btnNext = new JButton("Próximo");
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                onSelectNext();
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblCodigo)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(edtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNext)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(CENTER)
                        .addComponent(lblCodigo)
                        .addComponent(edtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNext)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)

        );

        setMinimumSize(new Dimension(320, 160));

        pack();

        setTitle("Nova bancada");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

    }

    public String showDialog(){
        setVisible(true);
        return edtCodigo.getText();
    }

    /*
     * 3x - numero de serie (12 bits | 4096)
     * 2x - sensores        ( 8 bits | 8 sensores)
     *      1   - Iluminacao
     *      2   - Temperatura
     *      4   - Nivel da Agua
     *      8   - pH
     *      16  - OD
     *      32  -
     *      64  -
     *      128 -
     * 2x - atuadores       ( 8 bits | 8 atuadores)
     *      1   -
     *      2   -
     *      4   -
     *      8   -
     *      16  -
     *      32  -
     *      64  -
     *      128 -
    */

    private void onSelectNext() {
        String str = edtCodigo.getText();

    }

}
