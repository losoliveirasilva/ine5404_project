package panels;

import javax.swing.*;
import java.awt.*;

/**
 * Created by losoliveirasilva on 6/1/16.
 */
public class PaintedSquarePanel extends JPanel{

    private Dimension dimension;
    private Color color;

    public PaintedSquarePanel(Dimension d, Color c){
        super();

        dimension = d;
        color = c;

        setBackground(color);
        setPreferredSize(dimension);

    }
}
