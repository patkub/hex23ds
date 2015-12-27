/**
 * Created by Patka on 12/26/2015.
 */

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.awt.dnd.*;
import javax.swing.*;
import java.util.ArrayList;
import java.io.*;

public class ConvPanel extends JPanel implements DropTargetListener {
    private JLabel lblHex, lblMMM, lblDragDrop;;
    private JTextField txtHex, txtMMM;
    private JButton btnClear, btnConvert;
    private DropTarget dropTarget;

    //constructor
    public ConvPanel()
    {
        //Create labels
        lblHex = new JLabel("Hex: ");
        lblMMM = new JLabel("Major.Minor.Macro: ");
        lblDragDrop = new JLabel("Drag TMD File anywhere in this window.");

        //Create text-boxes
        txtHex = new JTextField(20);
        txtMMM = new JTextField(20);
        txtMMM.setEditable(false);

        //Create buttons
        btnConvert = new JButton("Convert");
        btnClear = new JButton("Clear");

        //Create listeners for buttons
        SelectionListener selListener = new SelectionListener();
        btnConvert.addActionListener(selListener);
        btnClear.addActionListener(selListener);

        //Add convert from content of panel
        add(lblHex);
        add(txtHex);
        add(lblMMM);
        add(txtMMM);
        add(btnConvert);
        add(btnClear);
        add(lblDragDrop);

        //Setup look of panel
        setPreferredSize(new Dimension(230, 200));
        setBackground(Color.white);

        dropTarget = new DropTarget(this, DnDConstants.ACTION_COPY_OR_MOVE, this, true, null);
    }

    // Implementation of the DropTargetListener interface
    public void dragEnter(DropTargetDragEvent event) {}
    public void dragExit(DropTargetEvent event) {}
    public void dragOver(DropTargetDragEvent event) {}
    public void dropActionChanged(DropTargetDragEvent event) {}

    public void drop(DropTargetDropEvent event) {
        try {
            Transferable t = event.getTransferable();
            event.acceptDrop(event.getDropAction());
            java.util.List files = (java.util.List) t.getTransferData(DataFlavor.javaFileListFlavor);
            File droppedFile = (File)files.get(0);

            String strHex = Converter.processFile(droppedFile);
            txtHex.setText(strHex);
            event.dropComplete(true);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,
                    "An error occurred trying to process the file.",
                    "File Error",
                    JOptionPane.ERROR_MESSAGE);

            System.out.println(e);
        }
    }

    /*
     * Button action events.
     */
    private class SelectionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            Object source = event.getSource();

            if (source == btnConvert)
            {
                // convert
                String strHex = txtHex.getText();
                ArrayList<String> MMMParts = Converter.hexToMMM(strHex);
                String strMMM = new String("");
                strMMM = MMMParts.get(0) + "." + MMMParts.get(1) + "." + MMMParts.get(2);
                txtMMM.setText(strMMM);
            }
            else if (source == btnClear)
            {
                // clear text-boxes
                txtHex.setText("");
                txtMMM.setText("");
            }
        }
    }
}
