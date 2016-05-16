package graphics;

import java.awt.*;
import java.util.Random;

import javax.swing.*;

public class Graph extends JPanel {

    int width = 700;
    int height = 600;

    int hBorders = 5; // No. de divisões horizontais
    int vBorders = 5; // No. de divisões verticais

    private static final Color[] colorset = {
        Color.BLUE,
        Color.RED,
        Color.GREEN,
        Color.MAGENTA,
        Color.CYAN,
        Color.ORANGE
    };

    //Ponto[] data = new Ponto[width];
    Ponto[][] graphLine = new Ponto[4][10];
    GraphData[] data = new GraphData[4];

    public Graph() {
        startData();
        setBackground(Color.WHITE);

        // pH
        data[0] = new GraphData(0, 140, 50);

        // Outros (a fazer)
        data[1] = new GraphData(0, 140, 50);
        data[2] = new GraphData(0, 140, 50);
        data[3] = new GraphData(0, 140, 50);

        setPreferredSize(new Dimension(width+1, height+1));

    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width, height);
        drawBorders(g);
        drawData(g);
    }

    // Considera height = 600 e origin.x = 0
    public void drawBorders(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        for (int i = 1; i < hBorders; i++) {
            g.drawLine((width * i) / hBorders, height, (width * i) / hBorders, 0);
        }
        for (int i = 1; i < vBorders; i++) {
            g.drawLine(0, height - (height * i) / vBorders, width, height - (height * i) / vBorders);
        }
    }

    public void drawData(Graphics g) {
        for (int k = 0; k < data.length; k++) {
            g.setColor(colorset[k]);
            for (int i = 0; i < data[0].getCurrValue().length - 1; i++) {
                g.drawLine((width / data[k].getCurrValue().length) * i, height - (int)(data[k].getRelativeCurrValue(i) * height), ((width / data[k].getCurrValue().length) * (i+1)),
                    height - (int)(data[k].getRelativeCurrValue(i+1) * height));
            }
        }
    }

    public void updateData(String[] newData) {
        for(int i = 0; i < data.length; i++){
            for (int j = 0; j < (data[i].getCurrValue().length - 1); j++) {
                data[i].setCurrValue(j,data[i].getCurrValue(j+1));
            }
            //Random r = new Random(); // Generates random number
            data[i].setCurrValue(data[i].getCurrValue().length - 1,/*r.nextInt(141)*/ Integer.parseInt(newData[i]));
        }
    }

    public void startData() {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < (graphLine[i].length); j++) {
                graphLine[i][j] = new Ponto(((width / graphLine[i].length) * j), height);
            }
        }
    }
}