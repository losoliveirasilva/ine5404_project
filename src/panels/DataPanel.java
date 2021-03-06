package panels;

import graphics.DataPack;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.CENTER;
import static javax.swing.GroupLayout.Alignment.LEADING;

public class DataPanel extends JPanel /*implements ActionListener*/{

    private JLabel[] labelData;
    private JLabel[] labelContent;
    //private String[] dataContent;
    private JLabel labelInfo;

    private PaintedSquarePanel paintedSquarePanel[];

    private String[] strAvailable;

    private DataPack dataPack;

    public DataPanel(DataPack dataPack) {

        this.dataPack = dataPack;

        labelInfo = new JLabel("Dados:");

        labelData = new JLabel[dataPack.getAvailableNum()];
        labelContent = new JLabel[dataPack.getAvailableNum()];
        //dataContent = new String[dataPack.getAvailableNum()];

        paintedSquarePanel = new PaintedSquarePanel[dataPack.getAvailableNum()];

        strAvailable = dataPack.getAvailableString();

        for (int i = 0; i < dataPack.getAvailableNum(); i++) {
            //this.dataContent[i] = new String("N/A");
            this.labelContent[i] = new JLabel(new String("N/A"));
            this.labelData[i] = new JLabel(dataPack.getAvailableString()[i] + ":");
            this.labelData[i].setVisible(true);
            paintedSquarePanel[i] = new PaintedSquarePanel(new Dimension(16, 16), dataPack.getAvailableColor()[i]);
        }

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup sg1 = layout.createSequentialGroup();
        GroupLayout.ParallelGroup pg1 = layout.createParallelGroup(LEADING);
        GroupLayout.SequentialGroup sg2 = layout.createSequentialGroup();
        GroupLayout.ParallelGroup pg2 = layout.createParallelGroup(LEADING);

        GroupLayout.ParallelGroup pg_1 = layout.createParallelGroup(LEADING);
        GroupLayout.ParallelGroup pg_2 = layout.createParallelGroup(LEADING);
        GroupLayout.ParallelGroup pg_3 = layout.createParallelGroup(LEADING);

        for (int i = 2; i < labelData.length; i++){
            pg_1.addComponent(labelData[i]);
        }

        for (int i = 2; i < labelData.length; i++){
            pg_2.addComponent(labelContent[i]);
        }

        for (int i = 2; i < labelData.length; i++){
            pg_3.addComponent(paintedSquarePanel[i], GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE);
        }

        sg2.addGroup(pg_3);
        sg2.addGroup(pg_1);
        sg2.addGap(30).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        sg2.addGroup(pg_2);

        pg1.addGroup(sg2);
        sg1.addGroup(pg1);
        layout.setHorizontalGroup(sg1);

        /*
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(CENTER)
                .addGroup(layout.createParallelGroup(CENTER)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelData[i])
                        .addComponent(labelContent[i])
                    )
                )
            )
        )
        ;*/

        GroupLayout.SequentialGroup sg3 = layout.createSequentialGroup();

        for (int i = 2; i < labelData.length; i++){
            sg3.addGroup(layout.createParallelGroup(CENTER)
                .addComponent(labelData[i])
                .addComponent(paintedSquarePanel[i], GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(labelContent[i])
            );
        }

        layout.setVerticalGroup(sg3);

    }

    public void updateLabels(String[] dataContent){
        /*for (int i = 0; i < dataContent.length; i++) {
            this.labelData[i].setText(strAvailable[i] + ": " + this.dataContent[i]);
        }*/

        for (int i = 0; i < dataContent.length; i++) {
            this.labelContent[i+2].setText(dataContent[i]);
        }
    }

    public void setDataPack(DataPack dp){
        this.dataPack = dp;

        for (int i = 0; i < dataPack.getAvailableNum(); i++) {
            paintedSquarePanel[i].setColor(dataPack.getAvailableColor()[i]);
        }
    }

}


