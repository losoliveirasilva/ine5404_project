package graphics;

import javax.swing.*;
import java.awt.*;

/**
 * Created by losoliveirasilva on 6/1/16.
 */
public class ColorIcon implements Icon {

    private int width;
    private int height;

    private Color  color;

    public ColorIcon(Color c, Dimension d){
        color = c;
        width = d.width;
        height = d.height;
    }

    public void setColor(Color c){
        color = c;
    }

    public void paintIcon(Component c, Graphics g, int i, int i1) {
        g.setColor(color);
        g.fillRect(0,0, width, height);
    }

    public int getIconWidth() {
        return 24;
    }

    public int getIconHeight() {
        return 24;
    }

}
