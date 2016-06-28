package panels;

import javax.swing.*;
import java.awt.*;

/**
 * Created by losoliveirasilva on 6/1/16.
 */
public class PaintedSquarePanel extends JPanel{

    public PaintedSquarePanel(Dimension d, Color c){
        super();

        setBackground(c);
        setPreferredSize(d);

    }

    public void setColor(Color c){
        setBackground(c);
    }
}
