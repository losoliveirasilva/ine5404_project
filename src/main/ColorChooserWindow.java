package main;

import jdk.nashorn.internal.scripts.JD;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Created by losoliveirasilva on 6/1/16.
 */
public class ColorChooserWindow extends JDialog implements ChangeListener {

    private JColorChooser tcc;
    private Color newColor;

    public ColorChooserWindow(JDialog parent){
        super(parent);
        setModal(true);

        //Set up color chooser for setting text color
        tcc = new JColorChooser();
        tcc.getSelectionModel().addChangeListener(this);
        tcc.setBorder(BorderFactory.createTitledBorder(
            "Choose Text Color"));

        add(tcc, BorderLayout.PAGE_END);

    }

    public void stateChanged(ChangeEvent e) {
        newColor = tcc.getColor();
    }

    public Color showColorChooser(){
        pack();
        setVisible(true);
        return newColor;
    }

}
