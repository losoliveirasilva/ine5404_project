package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.GroupLayout.Alignment.CENTER;

public class NewStandWindow extends JDialog{

    private JLabel lblCodigo;
    private JTextField edtCodigo;
    private JButton btnConfigure;

    private JButton btnOk;
    private JButton btnCancel;

    public NewStandWindow(JFrame parent){
        super(parent);

        setModal(true);

        lblCodigo = new JLabel("Código:");
        edtCodigo = new JTextField();
        edtCodigo.setPreferredSize(new Dimension(80, 24));
        btnConfigure = new JButton("Configurar");
        btnConfigure.addActionListener(new ActionListener() {
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
                .addComponent(btnConfigure)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(CENTER)
                        .addComponent(lblCodigo)
                        .addComponent(edtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnConfigure)
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
     * 3x - sensores        (12 bits | 12 sensores)
     *      1    - pH
     *      2    - DO - Oxigenio dissolvido
     *      4    - EC - Eletrocondutividade
     *      8    - ORP - Potencial de oxidacao-reducao
     *      16   - Fluxo de agua (quanto foi gasto - diferenca nas duas leituras)
     *      32   - Temperatura da agua
     *      64   - Nivel da agua
     *      128  - Temperatura ambiente
     *      264  - Umidade ambiente
     *      512  - Luz ambiente
     *      1024 -
     *      2048 -
     * 2x - atuadores       ( 8 bits | 8 atuadores)
     *      1    - Motor
     *      2    - LEDs
     *      4    -
     *      8    -
     *      16   -
     *      32   -
     *      64   -
     *      128  -
    */

    private void onSelectNext() {
        String str = edtCodigo.getText();

    }

}
