package panels;

import graphics.*;

import java.awt.*;

import javax.swing.*;

import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.CENTER;
import static javax.swing.GroupLayout.Alignment.LEADING;

public class GraphPanel extends JPanel {
    private Graph graph;
    private DataPack dataPack;

    public GraphPanel(DataPack dataPack) {
        this.dataPack = dataPack;
        graph = new Graph(dataPack);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
            //.addComponent(graph, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            .addComponent(graph)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
            //.addComponent(graph, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            .addComponent(graph)
        );

        //setPreferredSize(new Dimension(700, 601));

    }

    public GraphData[] graphData(){
        return graph.graphData();
    }

    public void graphData(GraphData[] g){
        graph.graphData(g);
    }

    public Graph getGraph() { return graph; }

    public void setDataPack(DataPack dp){
        this.dataPack = dp;
        graph.setDataPack(dp);
    }

    public void updateGraph(String[] data){
        graph.updateData(data);
        this.graph.repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        graph.repaint();

    }

}