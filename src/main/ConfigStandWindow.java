package main;

import graphics.ColorIcon;
import graphics.DataPack;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.GroupLayout.Alignment.*;

public class ConfigStandWindow extends JDialog{

    GroupLayout layout;

    /* Code */
    private JLabel lblCode;
    private JTextField edtCode;
    private JButton btnConfigure;

    private JPanel panel;

    /* Tab Name */
    private JLabel lblTabName;
    private JTextField edtTabName;
    /* Background */
    private JLabel lblBackColor;
    private ColorIcon ciBack;
    private JButton btnBackColor;
    /* Grid */
    private JLabel lblGridColor;
    private ColorIcon ciGrid;
    private JButton btnGridColor;
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
    /* Fluxo de agua */
    private JLabel lblFluxColor;
    private ColorIcon ciFlux;
    private JButton btnFluxColor;
    /* Temperatura da Agua */
    private JLabel lblTAgColor;
    private ColorIcon ciTAg;
    private JButton btnTAgColor;
    /* Nivel Agua */
    private JLabel lblNvAgColor;
    private ColorIcon ciNvAg;
    private JButton btnNvAgColor;
    /* Temperatura Ambiente */
    private JLabel lblTAmColor;
    private ColorIcon ciTAm;
    private JButton btnTAmColor;
    /* Umidade Ambiente */
    private JLabel lblHumColor;
    private ColorIcon ciHum;
    private JButton btnHumColor;
    /* Luz */
    private JLabel lblLightColor;
    private ColorIcon ciLight;
    private JButton btnLightColor;

    private JButton btnOk;
    private JButton btnCancel;

    private String returnStr;
    private DataPack dataPack = null;

    public ConfigStandWindow(JFrame parent, DataPack dataPack, String str){
        super(parent);

        setModal(true);

        panel = new JPanel();
        panel.setSize(0, 0);
        panel.setPreferredSize(new Dimension(0, 0));
        panel.setVisible(false);

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
        lblTabName.setVisible(false);
        edtTabName = new JTextField();
        edtTabName.setVisible(false);
        edtTabName.setPreferredSize(new Dimension(100, 24));

        /* Background */
        lblBackColor = new JLabel("Background");
        lblBackColor.setVisible(false);
        btnBackColor = new JButton();
        ciBack = new ColorIcon(new Color(255, 255, 255), new Dimension(24, 24));
        btnBackColor.setSize(24, 24);
        btnBackColor.setPreferredSize(new Dimension(24, 24));
        btnBackColor.setMinimumSize(new Dimension(24, 24));
        btnBackColor.setMaximumSize(new Dimension(24, 24));
        btnBackColor.setIcon(ciBack);
        btnBackColor.setVisible(false);
        btnBackColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                onChangeColor("Back");
            }
        });
        
        /* Grid */
        lblGridColor = new JLabel("Grade");
        lblGridColor.setVisible(false);
        btnGridColor = new JButton();
        ciGrid = new ColorIcon(new Color(50, 50, 50), new Dimension(24, 24));
        btnGridColor.setSize(24, 24);
        btnGridColor.setPreferredSize(new Dimension(24, 24));
        btnGridColor.setMinimumSize(new Dimension(24, 24));
        btnGridColor.setMaximumSize(new Dimension(24, 24));
        btnGridColor.setIcon(ciGrid);
        btnGridColor.setVisible(false);
        btnGridColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                onChangeColor("Grid");
            }
        });

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
                onChangeColor("DO");
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
                onChangeColor("EC");
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
                onChangeColor("ORP");
            }
        });
        
        /* Flux */
        lblFluxColor = new JLabel("Fluxo");
        lblFluxColor.setVisible(false);
        btnFluxColor = new JButton();
        ciFlux = new ColorIcon(new Color(255, 233, 0), new Dimension(24, 24));
        btnFluxColor.setSize(24, 24);
        btnFluxColor.setPreferredSize(new Dimension(24, 24));
        btnFluxColor.setMinimumSize(new Dimension(24, 24));
        btnFluxColor.setMaximumSize(new Dimension(24, 24));
        btnFluxColor.setIcon(ciFlux);
        btnFluxColor.setVisible(false);
        btnFluxColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                onChangeColor("Flux");
            }
        });
        
        /* TAg */
        lblTAgColor = new JLabel("Temp Agua");
        lblTAgColor.setVisible(false);
        btnTAgColor = new JButton();
        ciTAg = new ColorIcon(new Color(255, 0, 149), new Dimension(24, 24));
        btnTAgColor.setSize(24, 24);
        btnTAgColor.setPreferredSize(new Dimension(24, 24));
        btnTAgColor.setMinimumSize(new Dimension(24, 24));
        btnTAgColor.setMaximumSize(new Dimension(24, 24));
        btnTAgColor.setIcon(ciTAg);
        btnTAgColor.setVisible(false);
        btnTAgColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                onChangeColor("TAg");
            }
        });

        /* NvAg */
        lblNvAgColor = new JLabel("Nivel Agua");
        lblNvAgColor.setVisible(false);
        btnNvAgColor = new JButton();
        ciNvAg = new ColorIcon(new Color(0, 255, 71), new Dimension(24, 24));
        btnNvAgColor.setSize(24, 24);
        btnNvAgColor.setPreferredSize(new Dimension(24, 24));
        btnNvAgColor.setMinimumSize(new Dimension(24, 24));
        btnNvAgColor.setMaximumSize(new Dimension(24, 24));
        btnNvAgColor.setIcon(ciNvAg);
        btnNvAgColor.setVisible(false);
        btnNvAgColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                onChangeColor("NvAg");
            }
        });
        
        /* TAm */
        lblTAmColor = new JLabel("Temp Ambiente");
        lblTAmColor.setVisible(false);
        btnTAmColor = new JButton();
        ciTAm = new ColorIcon(new Color(131, 0, 255), new Dimension(24, 24));
        btnTAmColor.setSize(24, 24);
        btnTAmColor.setPreferredSize(new Dimension(24, 24));
        btnTAmColor.setMinimumSize(new Dimension(24, 24));
        btnTAmColor.setMaximumSize(new Dimension(24, 24));
        btnTAmColor.setIcon(ciTAm);
        btnTAmColor.setVisible(false);
        btnTAmColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                onChangeColor("TAm");
            }
        });
        
        /* Hum */
        lblHumColor = new JLabel("Umidade");
        lblHumColor.setVisible(false);
        btnHumColor = new JButton();
        ciHum = new ColorIcon(new Color(0, 255, 220), new Dimension(24, 24));
        btnHumColor.setSize(24, 24);
        btnHumColor.setPreferredSize(new Dimension(24, 24));
        btnHumColor.setMinimumSize(new Dimension(24, 24));
        btnHumColor.setMaximumSize(new Dimension(24, 24));
        btnHumColor.setIcon(ciHum);
        btnHumColor.setVisible(false);
        btnHumColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                onChangeColor("Hum");
            }
        });
        
        /* Light */
        lblLightColor = new JLabel("Luz");
        lblLightColor.setVisible(false);
        btnLightColor = new JButton();
        ciLight = new ColorIcon(new Color(255, 71, 0), new Dimension(24, 24));
        btnLightColor.setSize(24, 24);
        btnLightColor.setPreferredSize(new Dimension(24, 24));
        btnLightColor.setMinimumSize(new Dimension(24, 24));
        btnLightColor.setMaximumSize(new Dimension(24, 24));
        btnLightColor.setIcon(ciLight);
        btnLightColor.setVisible(false);
        btnLightColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                onChangeColor("Light");
            }
        });

        btnOk = new JButton("Ok");
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                closeDialog("Ok");
            }
        });
        btnOk.setVisible(false);

        btnCancel = new JButton("Cancelar");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                closeDialog("Cancel");
            }
        });

        layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHonorsVisibility(true);
        layout.setHonorsVisibility(btnOk, false);

        int gapsize = -(btnOk.getPreferredSize().width + btnCancel.getPreferredSize().width);
        gapsize += lblCode.getPreferredSize().width + edtCode.getPreferredSize().width + btnConfigure.getPreferredSize().width;

        layout.setHorizontalGroup(
            layout.createParallelGroup(CENTER)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblCode)
                    .addComponent(edtCode, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConfigure)
                )
                .addComponent(panel)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblTabName)
                    .addComponent(edtTabName, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(lblBackColor)
                        .addComponent(lblGridColor)
                        .addComponent(lblpHColor)
                        .addComponent(lblDOColor)
                        .addComponent(lblECColor)
                        .addComponent(lblORPColor)
                        .addComponent(lblFluxColor)
                        .addComponent(lblTAgColor)
                        .addComponent(lblNvAgColor)
                        .addComponent(lblTAmColor)
                        .addComponent(lblHumColor)
                        .addComponent(lblLightColor)
                    )
                    .addGroup(layout.createParallelGroup(TRAILING)
                        .addComponent(btnBackColor)
                        .addComponent(btnGridColor)
                        .addComponent(btnConfigure)
                        .addComponent(btnpHColor)
                        .addComponent(btnDOColor)
                        .addComponent(btnECColor)
                        .addComponent(btnORPColor)
                        .addComponent(btnFluxColor)
                        .addComponent(btnTAgColor)
                        .addComponent(btnNvAgColor)
                        .addComponent(btnTAmColor)
                        .addComponent(btnHumColor)
                        .addComponent(btnLightColor)
                    )
                )
                //.addComponent(panel)
                .addGroup(layout.createSequentialGroup()
                    .addGap(gapsize + 12)
                    .addComponent(btnOk)
                    .addComponent(btnCancel)

                )
        );

        /*
        .addGap(gap)
                .addContainerGap()
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
         */

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(CENTER)
                    .addComponent(lblCode)
                    .addComponent(edtCode, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConfigure)
                )
                .addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(CENTER)
                    .addComponent(lblTabName)
                    .addComponent(edtTabName)
                )
                .addGap(20)
                .addGroup(layout.createParallelGroup(CENTER)
                    .addComponent(lblBackColor)
                    .addComponent(btnBackColor)
                )
                .addGroup(layout.createParallelGroup(CENTER)
                    .addComponent(lblGridColor)
                    .addComponent(btnGridColor)
                )
                .addGroup(layout.createParallelGroup(CENTER)
                    .addComponent(lblpHColor)
                    .addComponent(btnpHColor)
                )
                .addGroup(layout.createParallelGroup(CENTER)
                    .addComponent(lblDOColor)
                    .addComponent(btnDOColor)
                )
                .addGroup(layout.createParallelGroup(CENTER)
                    .addComponent(lblECColor)
                    .addComponent(btnECColor)
                )
                .addGroup(layout.createParallelGroup(CENTER)
                    .addComponent(lblORPColor)
                    .addComponent(btnORPColor)
                )
                .addGroup(layout.createParallelGroup(CENTER)
                    .addComponent(lblFluxColor)
                    .addComponent(btnFluxColor)
                )
                .addGroup(layout.createParallelGroup(CENTER)
                    .addComponent(lblTAgColor)
                    .addComponent(btnTAgColor)
                )
                .addGroup(layout.createParallelGroup(CENTER)
                    .addComponent(lblNvAgColor)
                    .addComponent(btnNvAgColor)
                )
                .addGroup(layout.createParallelGroup(CENTER)
                    .addComponent(lblTAmColor)
                    .addComponent(btnTAmColor)
                )
                .addGroup(layout.createParallelGroup(CENTER)
                    .addComponent(lblHumColor)
                    .addComponent(btnHumColor)
                )
                .addGroup(layout.createParallelGroup(CENTER)
                    .addComponent(lblLightColor)
                    .addComponent(btnLightColor)
                )
                //.addComponent(panel)
                .addGap(20)
                .addGroup(layout.createParallelGroup(CENTER)
                    .addComponent(btnOk)
                    .addComponent(btnCancel)
                )
        );

        //setMinimumSize(new Dimension(320, 160));

        if(dataPack != null) {
            editColors(dataPack);
            edtCode.setText(dataPack.getCode());
            edtTabName.setText(dataPack.getTabName());
            onSelectConfigure();
        }

        pack();

        setTitle(str);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

    }

    public void editColors(DataPack dataPack){

        Color[] c = dataPack.getColorVect();

        if(c[0] != null)
            ciBack.setColor(c[0]);
        if(c[1] != null)
            ciGrid.setColor(c[1]);
        if(c[2] != null)
            cipH.setColor(c[2]);
        if(c[3] != null)
            ciDO.setColor(c[3]);
        if(c[4] != null)
            ciEC.setColor(c[4]);
        if(c[5] != null)
            ciORP.setColor(c[5]);
        if(c[6] != null)
            ciFlux.setColor(c[6]);
        if(c[7] != null)
            ciTAg.setColor(c[7]);
        if(c[8] != null)
            ciNvAg.setColor(c[8]);
        if(c[9] != null)
            ciTAm.setColor(c[9]);
        if(c[10] != null)
            ciHum.setColor(c[10]);
        if(c[11] != null)
            ciLight.setColor(c[11]);

    }

    public DataPack showDialog(){
        setVisible(true);

        return dataPack;
    }

    private String closeDialog(String btn){

        if(btn.equals("Ok")){
            String str = edtCode.getText();

            String seriesNum = str.substring(0, 3);
            String sensors = str.substring(3, 6);
            String acts = str.substring(6, 8);
            int seriesNumValue = Integer.parseInt(seriesNum, 16);
            int sensorsValue = Integer.parseInt(sensors, 16);
            int actsValue = Integer.parseInt(acts, 16);


            returnStr = "Ok btn";

            dataPack = new DataPack(
                edtCode.getText(),
                edtTabName.getText(),
                ciBack.getColor(),
                ciGrid.getColor(),
                (sensorsValue & 1)   == 1   ? cipH.getColor()   :null,
                (sensorsValue & 2)   == 2   ? ciDO.getColor()   :null,
                (sensorsValue & 4)   == 4   ? ciEC.getColor()   :null,
                (sensorsValue & 8)   == 8   ? ciORP.getColor()  :null,
                (sensorsValue & 16)  == 16  ? ciFlux.getColor() :null,
                (sensorsValue & 32)  == 32  ? ciTAg.getColor()  :null,
                (sensorsValue & 64)  == 64  ? ciNvAg.getColor() :null,
                (sensorsValue & 128) == 128 ? ciTAm.getColor()  :null,
                (sensorsValue & 256) == 256 ? ciHum.getColor()  :null,
                (sensorsValue & 512) == 512 ? ciLight.getColor():null
            );

        }else if(btn.equals("Cancel")){
            returnStr = "Cancel btn";

            dataPack = new DataPack(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
            );

        }

        dispose();
        return returnStr;
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

            panel.setPreferredSize(new Dimension(0, 15));
            panel.setVisible(true);

            lblTabName.setVisible(true);
            edtTabName.setVisible(true);

            lblBackColor.setVisible(true);
            btnBackColor.setVisible(true);

            lblGridColor.setVisible(true);
            btnGridColor.setVisible(true);

            btnOk.setVisible(true);

            lblpHColor.setVisible((sensorsValue & 1) == 1);
            btnpHColor.setVisible((sensorsValue & 1) == 1);

            lblDOColor.setVisible((sensorsValue & 2) == 2);
            btnDOColor.setVisible((sensorsValue & 2) == 2);

            lblECColor.setVisible((sensorsValue & 4) == 4);
            btnECColor.setVisible((sensorsValue & 4) == 4);

            lblORPColor.setVisible((sensorsValue & 8) == 8);
            btnORPColor.setVisible((sensorsValue & 8) == 8);

            lblFluxColor.setVisible((sensorsValue & 16) == 16);
            btnFluxColor.setVisible((sensorsValue & 16) == 16);

            lblTAgColor.setVisible((sensorsValue & 32) == 32);
            btnTAgColor.setVisible((sensorsValue & 32) == 32);

            lblNvAgColor.setVisible((sensorsValue & 64) == 64);
            btnNvAgColor.setVisible((sensorsValue & 64) == 64);

            lblTAmColor.setVisible((sensorsValue & 128) == 128);
            btnTAmColor.setVisible((sensorsValue & 128) == 128);

            lblHumColor.setVisible((sensorsValue & 256) == 256);
            btnHumColor.setVisible((sensorsValue & 256) == 256);

            lblLightColor.setVisible((sensorsValue & 512) == 512);
            btnLightColor.setVisible((sensorsValue & 512) == 512);


            System.out.println(seriesNumValue);
            System.out.println(sensorsValue);
            System.out.println(actsValue);
        }else{
            System.out.println("String com quantidade errada!");
        }

        pack();
    }

    public void onChangeColor(String btn){
        ColorChooserWindow cc = new ColorChooserWindow(this);

        if(btn.equals("Back")){
            ciBack.setColor(cc.showColorChooser(ciBack.getColor()));
        }else if(btn.equals("Grid")){
            ciGrid.setColor(cc.showColorChooser(ciGrid.getColor()));
        }else if(btn.equals("pH")){
            cipH.setColor(cc.showColorChooser(cipH.getColor()));
        }else if(btn.equals("DO")){
            ciDO.setColor(cc.showColorChooser(ciDO.getColor()));
        }else if(btn.equals("EC")){
            ciEC.setColor(cc.showColorChooser(ciEC.getColor()));
        }else if(btn.equals("ORP")){
            ciORP.setColor(cc.showColorChooser(ciORP.getColor()));
        }else if(btn.equals("Flux")){
            ciFlux.setColor(cc.showColorChooser(ciFlux.getColor()));
        }else if(btn.equals("TAg")){
            ciTAg.setColor(cc.showColorChooser(ciTAg.getColor()));
        }else if(btn.equals("NvAg")){
            ciNvAg.setColor(cc.showColorChooser(ciNvAg.getColor()));
        }else if(btn.equals("TAm")){
            ciTAm.setColor(cc.showColorChooser(ciTAm.getColor()));
        }else if(btn.equals("Hum")){
            ciHum.setColor(cc.showColorChooser(ciHum.getColor()));
        }else if(btn.equals("Light")){
            ciLight.setColor(cc.showColorChooser(ciLight.getColor()));
        }

    }

}
