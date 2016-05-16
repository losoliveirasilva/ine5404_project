package panels;

import graphics.*;

import java.awt.*;

import javax.swing.*;

import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.CENTER;
import static javax.swing.GroupLayout.Alignment.LEADING;

public class GraphPanel extends JPanel {
    public Graph graph;

    public GraphPanel() {
        graph = new Graph();

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