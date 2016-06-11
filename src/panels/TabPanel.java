package panels;

import graphics.DataPack;
import graphics.Graph;

import javax.swing.*;

import static javax.swing.GroupLayout.Alignment.CENTER;

/**
 * Created by losoliveirasilva on 6/2/16.
 */
public class TabPanel extends JPanel{

    private DataPack dataPack;

    private DataPanel dataPanel;
    private GraphPanel graphPanel;

    public AlertsPanel alertsPanel;

    public TabPanel(DataPack dataPack){
        super();
        this.dataPack = dataPack;

        dataPanel = new DataPanel(dataPack);
        graphPanel = new GraphPanel(dataPack);

        alertsPanel = new AlertsPanel();

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(CENTER)
                    .addComponent(alertsPanel)
                    .addComponent(dataPanel)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(graphPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(CENTER)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(alertsPanel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dataPanel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    )
                    .addComponent(graphPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)

        );

    }

    public DataPack getDataPack(){
        return this.dataPack;
    }

    public void setDataPack(DataPack dataPack){
        this.dataPack = dataPack;

        repaint();

        //dataPanel = new DataPanel(dataPack);
        //graphPanel = new GraphPanel(dataPack);

    }

    public GraphPanel getGraphPanel() { return graphPanel; }

}
