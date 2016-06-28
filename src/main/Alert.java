package main;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by losoliveirasilva on 6/24/16.
 */
public class Alert {

    private String sensor;
    private int sensorNum;

    private boolean lessThanFlag;
    private int lessThanValue;
    private boolean greaterThanFlag;
    private int greaterThanValue;

    private int index;

    public Alert(String s, int sn, int index, int lt, int gt){
        super();

        sensor = s;
        sensorNum = sn;

        this.index = index;

        if(lt == -1){
            lessThanFlag = false;
            lessThanValue = 0;
        }else{
            lessThanFlag = true;
            lessThanValue = lt;
        }

        if(gt == -1){
            greaterThanFlag = false;
            greaterThanValue = 0;
        }else{
            greaterThanFlag = true;
            greaterThanValue = gt;
        }

    }

    public String compute(int value){

        String str = "";
        String time = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());

        if(lessThanFlag){
            if(value < lessThanValue){
                str = sensor + " é menor que " + lessThanValue + " às " + time + "\n";
            }
        }

        if(greaterThanFlag){
            if(value > greaterThanValue){
                str = sensor + " é maior que " + greaterThanValue + " às " + time + "\n";
            }
        }

        return str;

    }

    /*public void setLessThan(int v){
        lessThanFlag = true;
        lessThanValue = v;
    }

    public void setGreaterThan(int v){
        greaterThanFlag = true;
        greaterThanValue = v;
    }*/

    public int sensorNum(){
        return this.sensorNum;
    }

}
