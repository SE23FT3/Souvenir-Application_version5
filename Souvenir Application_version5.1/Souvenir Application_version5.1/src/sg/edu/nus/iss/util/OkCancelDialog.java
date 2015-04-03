package sg.edu.nus.iss.util;


import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

public abstract class OkCancelDialog extends JDialog {

    public OkCancelDialog (JFrame parent, String title) {
        super (parent, title);
        add ("Center", createFormPanel());
        add ("South",  createButtonPanel());
    }

    public OkCancelDialog (JFrame parent) {
        this (parent, "");
    }

    private JPanel createButtonPanel () {
        JPanel p = new JPanel ();

        JButton b;
        ActionListener l;

        b = new JButton ("OK");
        l = new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                boolean success = false;
				try {
					success = performOkAction ();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                if (success) {
                    destroyDialog ();
                }
            }
        };
        b.addActionListener (l);
        p.add (b);

        b = new JButton ("Cancel");
        l = new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                destroyDialog ();
            }
        };
        b.addActionListener (l);
        p.add (b);

        return p;
    }

    private void destroyDialog () {
        setVisible (false);
        dispose();
    }

    protected abstract JPanel createFormPanel () ;

    protected abstract boolean performOkAction () throws IOException ;

}