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

    public void connect (String portName, int baud) throws Exception
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
                serialPort.setSerialPortParams(baud,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);

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

        private int bitsCounter(String str){
            if(str.equals("0"))
                return 0;
            if(str.equals("1") || str.equals("2") || str.equals("4") || str.equals("8"))
                return 1;
            if(str.equals("3") || str.equals("5") || str.equals("6") || str.equals("9") ||
               str.equals("A") || str.equals("a") || str.equals("C") || str.equals("c"))
                return 2;
            if(str.equals("7") || str.equals("B") || str.equals("b") || str.equals("D") || str.equals("d") ||
               str.equals("E") || str.equals("e"))
                return 3;
            if(str.equals("F") || str.equals("f"))
                return 4;

            return -1;
        }

        public void run ()
        {
            byte[] buffer = new byte[1];
            int len = -1;
            String bufferIn;
            String str = "";
            String state = "";
            int numZigbee = 0;
            int numInfo = 0;
            int numSensores = 0;
            //String time = "";/*new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime())*/
            try
            {
                while ((len = this.in.read(buffer)) > -1) {
                    if (len > 0) {
                        bufferIn = new String(buffer, 0, len);
                        if(bufferIn.equals(126))
                            state = "start";

                        switch(state){
                            case "idle":
                                break;
                            case "start":
                                state = "zigbee";
                                break;
                            case "zigbee":
                                if(numZigbee < 14)
                                    ++numZigbee;
                                else {
                                    numZigbee = 0;
                                    state = "info";
                                }
                                break;
                            case "info":
                                str += bufferIn;
                                numInfo++;

                                // ID
                                if(numInfo >= 1 && numInfo <= 3)
                                    ;

                                // Sensores
                                if(numInfo >= 4 && numInfo <= 6){
                                    numSensores += bitsCounter(bufferIn);
                                }

                                if(numInfo >= (8 + (3*numSensores))) {
                                    // 3 id
                                    // 3 sensor
                                    // 2 atuador
                                    // 3*numSensores
                                    state = "checksum";
                                }

                                break;
                            case "checksum":
                                window2.updateData(str);
                                str = "";
                                state = "idle";
                                numInfo = 0;
                                break;
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