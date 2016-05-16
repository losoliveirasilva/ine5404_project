package graphics;

import java.awt.*;
import java.util.Random;

import javax.swing.*;

public class Graph extends JPanel {

    int width = 700;
    int height = 600;

    int hBorders = 5; // No. de divisões horizontais
    int vBorders = 5; // No. de divisões verticais

    Ponto origin;
    //Ponto[] data = new Ponto[width];
    Ponto[][] graphLine = new Ponto[4][10];
    GraphData[] data = new GraphData[4];

    public Graph() {
        updateOrigin();
        startData();
        setBackground(Color.WHITE);

        // pH
        data[0] = new GraphData(0, 140, 50);

        // Outros (a fazer)
        data[1] = new GraphData(0, 140, 50);
        data[2] = new GraphData(0, 140, 50);
        data[3] = new GraphData(0, 140, 50);

        setPreferredSize(new Dimension(701, 601));

    }

    public void paint(Graphics g) {
        super.paint(g);
        updateOrigin();
        g.drawRect(origin.x, origin.y-height, width, height);
        drawBorders(g);
        drawData(g);
    }

    public void drawBorders(Graphics g) {
        for (int i = 1; i < hBorders; i++) {
            g.drawLine(origin.x + (width * i) / hBorders, origin.y, origin.x
                + (width * i) / hBorders, origin.y - height);
        }
        for (int i = 1; i < vBorders; i++) {
            g.drawLine(origin.x, origin.y - (height * i) / vBorders, origin.x
                + width, origin.y - (height * i) / vBorders);
        }
    }

    public void updateOrigin() {
        origin = new Ponto(((this.getSize().width) / 2) - 350, ((this.getSize().height) / 2) + 300);
    }

    public void drawData(Graphics g) {
        g.setColor(Color.BLUE);
        for (int i = 0; i < data[0].getCurrValue().length - 1; i++) {
            g.drawLine(origin.x + ((width / data[0].getCurrValue().length) * i), origin.y - (int)(data[0].getRelativeCurrValue(i) * height), origin.x + ((width / data[0].getCurrValue().length) * (i+1)),
                origin.y - (int)(data[0].getRelativeCurrValue(i+1) * height));

        }

        g.setColor(Color.RED);
        for (int i = 0; i < data[1].getCurrValue().length - 1; i++) {
            g.drawLine(origin.x + ((width / data[1].getCurrValue().length) * i), origin.y - (int)(data[1].getRelativeCurrValue(i) * height), origin.x + ((width / data[1].getCurrValue().length) * (i+1)),
                origin.y - (int)(data[1].getRelativeCurrValue(i+1) * height));

        }

        g.setColor(Color.GREEN);
        for (int i = 0; i < data[2].getCurrValue().length - 1; i++) {
            g.drawLine(origin.x + ((width / data[2].getCurrValue().length) * i), origin.y - (int)(data[2].getRelativeCurrValue(i) * height), origin.x + ((width / data[2].getCurrValue().length) * (i+1)),
                origin.y - (int)(data[2].getRelativeCurrValue(i+1) * height));

        }

        g.setColor(Color.YELLOW);
        for (int i = 0; i < data[3].getCurrValue().length - 1; i++) {
            g.drawLine(origin.x + ((width / data[3].getCurrValue().length) * i), origin.y - (int)(data[3].getRelativeCurrValue(i) * height), origin.x + ((width / data[3].getCurrValue().length) * (i+1)),
                origin.y - (int)(data[3].getRelativeCurrValue(i+1) * height));

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
                graphLine[i][j] = new Ponto(origin.x + ((width / graphLine[i].length) * j),
                    origin.y);
            }
        }
    }
}