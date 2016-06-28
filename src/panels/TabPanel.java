package panels;

import graphics.DataPack;
import graphics.Graph;
import main.Alert;
import main.AlertManager;

import javax.swing.*;

import static javax.swing.GroupLayout.Alignment.CENTER;

/**
 * Created by losoliveirasilva on 6/2/16.
 */
public class TabPanel extends JPanel{

    private DataPack dataPack;

    private DataPanel dataPanel;
    private GraphPanel graphPanel;

    private AlertsPanel alertsPanel;

    private AlertManager alerts;

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

        alerts = new AlertManager();
        //alerts.add(new Alert("DO", 0, 0, 118, 132));


    }

    public DataPack getDataPack(){
        return this.dataPack;
    }

    public void setDataPack(DataPack dataPack){
        this.dataPack = dataPack;

        graphPanel.setDataPack(dataPack);
        dataPanel.setDataPack(dataPack);

    }

    public void setNewData(String[] str){
        graphPanel.updateGraph(str);
        dataPanel.updateLabels(str);
        //addTextAlertPanel(doAlert.compute(Integer.parseInt(str[0])), "red_b");
        addTextAlertPanel(alerts.computeAll(str), "red_b");
    }

    public GraphPanel getGraphPanel() { return graphPanel; }

    public void addTextAlertPanel(String str, String style){
        alertsPanel.addText(str, style);
    }

}
