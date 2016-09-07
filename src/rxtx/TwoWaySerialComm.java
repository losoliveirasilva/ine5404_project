package rxtx;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import main.Window;

import javax.swing.*;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;

public class TwoWaySerialComm
{

    private Window window;

    public TwoWaySerialComm(Window w)
    {
        super();

        window = w;
    }

    public void connect (String portName) throws Exception
    {
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        if ( portIdentifier.isCurrentlyOwned() )
        {
            System.out.println("Error: Port is currently in use");
        }
        else
        {
            CommPort commPort = portIdentifier.open(this.getClass().getName(),2000);

            if ( commPort instanceof SerialPort )
            {
                SerialPort serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);

                InputStream in = serialPort.getInputStream();
                OutputStream out = serialPort.getOutputStream();

                (new Thread(new SerialReader(in, window))).start();
                (new Thread(new SerialWriter(out))).start();

            }
            else
            {
                System.out.println("Error: Only serial ports are handled by this example.");
            }
        }
    }

    /** */
    public static class SerialReader implements Runnable
    {
        InputStream in;
        Window window2;

        public SerialReader (InputStream in, Window w2)
        {
            this.in = in;
            window2 = w2;
        }

        public void run ()
        {
            byte[] buffer = new byte[1];
            int len = -1;
            String bufferIn;
            String str = "";
            //String time = "";/*new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime())*/
            try
            {
                while ((len = this.in.read(buffer)) > -1) {
                    if (len > 0) {
                        bufferIn = new String(buffer, 0, len);
                        str += bufferIn;
                        if (bufferIn.equals("\n")) {
                            //time = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
                            //System.out.print(time + ": ");
                            window2.updateData(str);
                            str = "";
                        }
                    }
                }
                /*while ( ( len = this.in.read(buffer)) > -1 )
                {
                    System.out.print(new String(buffer,0,len));
                }*/
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }
        }
    }

    /** */
    public static class SerialWriter implements Runnable
    {
        OutputStream out;

        public SerialWriter ( OutputStream out )
        {
            this.out = out;
        }

        public void run ()
        {
            try
            {
                int c = 0;
                while ( ( c = System.in.read()) > -1 )
                {
                    this.out.write(c);
                }
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }
        }
    }

    /*public static void main ( String[] args )
    {
        try
        {
            (new TwoWaySerialComm()).connect("COM3");
        }
        catch ( Exception e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }*/

    public Enumeration getPortList(){
        Enumeration portList = CommPortIdentifier.getPortIdentifiers();
        //System.out.println(portList.hasMoreElements());
        while(portList.hasMoreElements()){
            //System.out.println("Has more elements");
            CommPortIdentifier portId = (CommPortIdentifier) portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_PARALLEL) {
                System.out.println(portId.getName());
            }
            else{
                System.out.println(portId.getName());
            }

        }
        return portList;
    }
}