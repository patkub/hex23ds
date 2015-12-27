/**
 * Created by Patka on 12/26/2015.
 */

import java.io.*;
import java.util.ArrayList;

public class Converter {

    public static ArrayList<String> hexToMMM(String strHex)
    {
        ArrayList<String> output = new ArrayList<String>();

        int dec = Integer.parseInt(strHex, 16);         // convert string hex to decimal
        String strBin = Integer.toBinaryString(dec);    // convert decimal to string binary
        strBin = formatBin(strBin);                     // format to 16-bit

        String strMajor = strBin.substring(0, 6);       // extract major
        String strMinor = strBin.substring(6, 12);      // extract minor
        String strMacro = strBin.substring(12, 16);     // extract macro

        int major = Integer.parseInt(strMajor, 2);      // convert binary to decimal
        int minor = Integer.parseInt(strMinor, 2);      // convert binary to decimal
        int macro = Integer.parseInt(strMacro, 2);      // convert binary to decimal

        strMajor = Integer.toString(major);
        strMinor = Integer.toString(minor);
        strMacro = Integer.toString(macro);

        output.add(strMajor);
        output.add(strMinor);
        output.add(strMacro);

        return output;
    }

    public static String formatBin(String strBin)
    {
        if (strBin.length() < 16)
        {
            strBin = "0" + strBin;
            return formatBin(strBin);
        }

        return strBin;
    }

    public static String processFile(File myFile) throws IndexOutOfBoundsException, IOException
    {
        RandomAccessFile raf = new RandomAccessFile(myFile.getPath(), "r");

        // read 2 bytes (16 bits) from 0x000001DC = 476
        byte[] data = new byte[2];
        raf.seek(476);
        raf.read(data, 0, 2);
        return bytesToHex(data);
    }

    public static String bytesToHex(byte[] in) {
        final StringBuilder builder = new StringBuilder();
        for(byte b : in) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
}
