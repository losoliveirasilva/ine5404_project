package panels;

import graphics.DataPack;
import graphics.Graph;
import graphics.GraphData;
import graphics.Ponto;
import main.Alert;
import main.AlertManager;
import main.StandFileInfo;

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

        alerts = new AlertManager();

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
        alertsPanel.text(str, style);
    }

    public void alertManager(AlertManager am){
        this.alerts = am;
    }

    public AlertManager alertManager(){
        return this.alerts;
    }

    public StandFileInfo standFileInfo(){
        return new StandFileInfo(alertsPanel.text(), this.dataPack, this.graphPanel.graphData(), this.alerts);
    }

    public void graphData(GraphData[] g){
        graphPanel.graphData(g);
    }

    public DataPanel dataPanel(){
        return dataPanel;
    }

}
