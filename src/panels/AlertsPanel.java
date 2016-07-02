package panels;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.*;
import java.awt.*;

/**
 * Created by losoliveirasilva on 6/5/16.
 */
public class AlertsPanel extends JPanel{
// JTextPane e JTextArea

    private JTextPane tPane;
    private String newline = "\n";

    StyledDocument doc;

    public AlertsPanel() {
        super();

        tPane = new JTextPane();
        doc = tPane.getStyledDocument();
        addStylesToDocument(doc);

        tPane.setBackground(Color.WHITE);

        String[] initString =
            { "This is an editable JTextPane, ",            //regular
                "another ",                                   //italic
                "styled ",                                    //bold
                "text ",                                      //small
                "component, ",                                //large
                "which supports embedded components..." + newline,//regular
                " " + newline,                                //button
                "...and embedded icons..." + newline,         //regular
                " ",                                          //icon
                newline + "JTextPane is a subclass of JEditorPane that " +
                    "uses a StyledEditorKit and StyledDocument, and provides " +
                    "cover methods for interacting with those objects."
            };

        String[] initStyles =
            { "regular", "italic", "bold", "small", "large",
                "regular", "button", "regular", "icon",
                "regular"
            };

        /*try {
            for (int i=0; i < initString.length; i++) {
                doc.insertString(doc.getLength(), initString[i],
                    doc.getStyle(initStyles[i]));
            }
        } catch (BadLocationException ble) {
            System.err.println("Couldn't insert initial text into text pane.");
        }*/

        try {
            doc.insertString(doc.getLength(), "Bancada criada com sucesso\n\n", doc.getStyle("green_b"));
        } catch (BadLocationException ble) {
            System.err.println("Couldn't insert initial text into text pane.");
        }

        JScrollPane paneScrollPane = new JScrollPane(tPane);
        paneScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        paneScrollPane.setPreferredSize(new Dimension(400, 180));
        paneScrollPane.setMinimumSize(new Dimension(10, 10));

        tPane.setEditable(false);

        add(paneScrollPane);
        setVisible(true);

    }

    protected void addStylesToDocument(StyledDocument doc) {
        //Initialize some styles.
        Style def = StyleContext.getDefaultStyleContext().
            getStyle(StyleContext.DEFAULT_STYLE);

        Style regular = doc.addStyle("regular", def);
        StyleConstants.setFontFamily(def, "SansSerif");

        Style s = doc.addStyle("italic", regular);
        StyleConstants.setItalic(s, true);

        s = doc.addStyle("bold", regular);
        StyleConstants.setBold(s, true);

        s = doc.addStyle("small", regular);
        StyleConstants.setFontSize(s, 10);

        s = doc.addStyle("large", regular);
        StyleConstants.setFontSize(s, 16);

        s = doc.addStyle("icon", regular);
        StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);

        s = doc.addStyle("green", regular);
        StyleConstants.setForeground(s, new Color(0, 114, 0));

        s = doc.addStyle("green_b", regular);
        StyleConstants.setForeground(s, new Color(0, 114, 0));
        StyleConstants.setBold(s, true);

        s = doc.addStyle("red", regular);
        StyleConstants.setForeground(s, new Color(210, 0, 0));

        s = doc.addStyle("red_b", regular);
        StyleConstants.setForeground(s, new Color(210, 0, 0));
        StyleConstants.setBold(s, true);

    }

    public void addText(String str, String style){
        try {
            doc.insertString(doc.getLength(), str, doc.getStyle(style));
        } catch (BadLocationException ble) {
            System.err.println("Couldn't insert initial text into text pane.");
        }
    }
}
