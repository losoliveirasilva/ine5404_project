package main;

import graphics.ColorIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.GroupLayout.Alignment.CENTER;

public class NewStandWindow extends JDialog{

    GroupLayout layout;

    private JLabel lblCode;
    private JTextField edtCode;
    private JButton btnConfigure;

    private int gap;

    /* Code */
    private JLabel lblTabName;
    private JTextField edtTabName;
    /* pH */
    private JLabel lblpHColor;
    private ColorIcon cipH;
    private JButton btnpHColor;
    /* DO */
    private JLabel lblDOColor;
    private ColorIcon ciDO;
    private JButton btnDOColor;
    /* EC */
    private JLabel lblECColor;
    private ColorIcon ciEC;
    private JButton btnECColor;
    /* ORP */
    private JLabel lblORPColor;
    private ColorIcon ciORP;
    private JButton btnORPColor;

    private JButton btnOk;
    private JButton btnCancel;

    public NewStandWindow(JFrame parent){
        super(parent);

        setModal(true);

        gap = 0;

        lblCode = new JLabel("Código:");
        edtCode = new JTextField();
        edtCode.setPreferredSize(new Dimension(80, 24));
        btnConfigure = new JButton("Configurar");
        btnConfigure.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                onSelectConfigure();
            }
        });

        lblTabName = new JLabel("Nome da aba:");
        edtTabName = new JTextField();
        edtTabName.setPreferredSize(new Dimension(100, 24));

        /* pH */
        lblpHColor = new JLabel("pH");
        lblpHColor.setVisible(false);
        btnpHColor = new JButton();
        cipH = new ColorIcon(new Color(0, 0, 0), new Dimension(24, 24));
        btnpHColor.setSize(24, 24);
        btnpHColor.setPreferredSize(new Dimension(24, 24));
        btnpHColor.setMinimumSize(new Dimension(24, 24));
        btnpHColor.setMaximumSize(new Dimension(24, 24));
        btnpHColor.setIcon(cipH);
        btnpHColor.setVisible(false);
        btnpHColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                onChangeColor("pH");
            }
        });
        
        /* DO */
        lblDOColor = new JLabel("DO");
        lblDOColor.setVisible(false);
        btnDOColor = new JButton();
        ciDO = new ColorIcon(new Color(255, 0, 0), new Dimension(24, 24));
        btnDOColor.setSize(24, 24);
        btnDOColor.setPreferredSize(new Dimension(24, 24));
        btnDOColor.setMinimumSize(new Dimension(24, 24));
        btnDOColor.setMaximumSize(new Dimension(24, 24));
        btnDOColor.setIcon(ciDO);
        btnDOColor.setVisible(false);
        btnDOColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        
        /* EC */
        lblECColor = new JLabel("EC");
        lblECColor.setVisible(false);
        btnECColor = new JButton();
        ciEC = new ColorIcon(new Color(0, 255, 0), new Dimension(24, 24));
        btnECColor.setSize(24, 24);
        btnECColor.setPreferredSize(new Dimension(24, 24));
        btnECColor.setMinimumSize(new Dimension(24, 24));
        btnECColor.setMaximumSize(new Dimension(24, 24));
        btnECColor.setIcon(ciEC);
        btnECColor.setVisible(false);
        btnECColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        
        /* ORP */
        lblORPColor = new JLabel("ORP");
        lblORPColor.setVisible(false);
        btnORPColor = new JButton();
        ciORP = new ColorIcon(new Color(0, 0, 255), new Dimension(24, 24));
        btnORPColor.setSize(24, 24);
        btnORPColor.setPreferredSize(new Dimension(24, 24));
        btnORPColor.setMinimumSize(new Dimension(24, 24));
        btnORPColor.setMaximumSize(new Dimension(24, 24));
        btnORPColor.setIcon(ciORP);
        btnORPColor.setVisible(false);
        btnORPColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHonorsVisibility(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addContainerGap()
                //.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(CENTER)
                    .addComponent(lblCode)
                    .addComponent(lblpHColor)
                    .addComponent(lblDOColor)
                    .addComponent(lblECColor)
                    .addComponent(lblORPColor)
                )
                //.addContainerGap()
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(CENTER)
                    .addComponent(edtCode, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                )
                //.addContainerGap()
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(CENTER)
                    .addComponent(btnConfigure)
                    .addComponent(btnpHColor)
                    .addComponent(btnDOColor)
                    .addComponent(btnECColor)
                    .addComponent(btnORPColor)
                )
                .addContainerGap()
            //.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGap(gap)
                .addContainerGap()
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(CENTER)
                    .addComponent(lblCode)
                    .addComponent(edtCode, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConfigure)
                )
                //.addGap(gap)
                .addContainerGap()
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(CENTER)
                    .addComponent(lblpHColor)
                    .addComponent(btnpHColor)
                )
                .addContainerGap()
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(CENTER)
                    .addComponent(lblDOColor)
                    .addComponent(btnDOColor)
                )
                .addContainerGap()
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(CENTER)
                    .addComponent(lblECColor)
                    .addComponent(btnECColor)
                )
                .addContainerGap()
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(CENTER)
                    .addComponent(lblORPColor)
                    .addComponent(btnORPColor)
                )
                .addContainerGap()
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        //setMinimumSize(new Dimension(320, 160));

        pack();

        setTitle("Nova bancada");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

    }

    public String showDialog(){
        setVisible(true);
        return edtCode.getText();
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

    private void onSelectConfigure() {
        String str = edtCode.getText();

        if(str.length() == 8) {
            String seriesNum = str.substring(0, 3);
            String sensors = str.substring(3, 6);
            String acts = str.substring(6, 8);
            int seriesNumValue = Integer.parseInt(seriesNum, 16);
            int sensorsValue = Integer.parseInt(sensors, 16);
            int actsValue = Integer.parseInt(acts, 16);

            lblpHColor.setVisible((sensorsValue & 1) == 1);
            btnpHColor.setVisible((sensorsValue & 1) == 1);

            lblDOColor.setVisible((sensorsValue & 2) == 2);
            btnDOColor.setVisible((sensorsValue & 2) == 2);

            lblECColor.setVisible((sensorsValue & 4) == 4);
            btnECColor.setVisible((sensorsValue & 4) == 4);

            lblORPColor.setVisible((sensorsValue & 8) == 8);
            btnORPColor.setVisible((sensorsValue & 8) == 8);


            System.out.println(seriesNumValue);
            System.out.println(sensorsValue);
            System.out.println(actsValue);
        }else{
            System.out.println("String com quantidade errada!");
        }


        gap = 200;

        pack();
    }

    public void onChangeColor(String btn){
        ColorChooserWindow cc = new ColorChooserWindow(this);

        if(btn.equals("pH")){
            cipH.setColor(cc.showColorChooser());
        }

    }

}
