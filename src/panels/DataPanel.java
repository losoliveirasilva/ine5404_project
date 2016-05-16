package panels;

import javax.swing.*;
import java.awt.*;

import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.CENTER;
import static javax.swing.GroupLayout.Alignment.LEADING;

public class DataPanel extends JPanel /*implements ActionListener*/{

    JLabel[] labelData;
    String[] dataContent = {"N/A","N/A","N/A","N/A"};
    JLabel labelInfo;

    Choice choice;

    public DataPanel(String[] dataContent) {

        this.dataContent = dataContent;

        labelInfo = new JLabel("Informations:");

        choice = new Choice();

        labelData = new JLabel[4];

        for (int i = 0; i < 4; i++) {
            this.labelData[i] = new JLabel("Dado " + i + ": " + this.dataContent[i]);
        }

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(CENTER)
                .addComponent(labelInfo)
                .addComponent(labelData[0])
                .addComponent(labelData[1])
                .addComponent(labelData[2])
                .addComponent(labelData[3])

            )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addComponent(labelInfo).addGap(20)
            .addComponent(labelData[0])
            .addComponent(labelData[1])
            .addComponent(labelData[2])
            .addComponent(labelData[3])
        );

    }

}


