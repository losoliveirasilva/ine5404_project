package rxtx;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

public class TwoWaySerialComm {

    String[] dataContent;

    public TwoWaySerialComm(String[] dataContent) {
        super();
        this.dataContent = dataContent;
    }

    public void connect(String portName) throws Exception {

        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);

        if (portIdentifier.isCurrentlyOwned()) {
            System.out.println("Error: Port is currently in use");
        } else {
            CommPort commPort = portIdentifier.open(this.getClass().getName(), 2000);

            if (commPort instanceof SerialPort) {
                SerialPort serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

                InputStream in = serialPort.getInputStream();
                OutputStream out = serialPort.getOutputStream();

                (new Thread(new SerialReader(in, this.dataContent))).start();
                (new Thread(new SerialWriter(out))).start();

            } else {
                System.out.println("Error: Only serial ports are handled by this example.");
            }
        }
    }

    /** */
    public static class SerialReader implements Runnable {

        InputStream in;
        String[] dataContent;

        public SerialReader(InputStream in, String[] dataContent) {
            this.in = in;
            this.dataContent = dataContent;
        }

        public void run() {
            byte[] buffer = new byte[1];
            int len = -1;
            String bufferIn;
            String dale = "";
            try {
                while ((len = this.in.read(buffer)) > -1) {

                    if (len > 0) {

                        bufferIn = new String(buffer, 0, len);

                        if(bufferIn.equals("\n")){

                            String[] bufferSplit = dale.split(";");

                            for (int i = 0; i < bufferSplit.length; i++) {
                                dataContent[i] = bufferSplit[i];
                            }

                            dale = "";
                        }else{
                            dale += bufferIn;
                        }

                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /** */
    public static class SerialWriter implements Runnable {
        OutputStream out;

        public SerialWriter(OutputStream out) {
            this.out = out;
        }

        public void run() {
            try {
                int c = 0;
                while ((c = System.in.read()) > -1) {
                    this.out.write(c);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Enumeration getPortList(){
        Enumeration portList = CommPortIdentifier.getPortIdentifiers();
        System.out.println(portList.hasMoreElements());
        while(portList.hasMoreElements()){
            System.out.println("Has more elements");
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
