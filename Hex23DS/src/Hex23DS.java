/**
 * Created by Patka on 12/26/2015.
 * Extract 3DS version from firmware file.
 */

import javax.swing.JFrame;

public class Hex23DS
{
    public static void main(String[] args)
    {
        //create the main frame
        JFrame conversionFrame = new JFrame("Hex23DS");
        conversionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        conversionFrame.getContentPane().add(new ConvPanel());
        conversionFrame.pack();
        conversionFrame.setVisible(true);
        conversionFrame.setResizable(false);
    }
}