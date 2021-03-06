package main;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by losoliveirasilva on 6/24/16.
 */
public class Alert  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String sensor;
    private int sensorNum;

    private boolean lessThanFlag;
    private int lessThanValue;
    private boolean greaterThanFlag;
    private int greaterThanValue;

    public Alert(String s, int sn, int lt, int gt){
        super();

        sensor = s;
        sensorNum = sn;

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
                str = sensor + " é menor que " + lessThanValue + ", com valor de " + value + " (" + time + ")\n";
            }
        }

        if(greaterThanFlag){
            if(value > greaterThanValue){
                str = sensor + " é maior que " + greaterThanValue + ", com valor de " + value + " (" + time + ")\n";
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

    public boolean lessThanFlag(){
        return lessThanFlag;
    }

    public boolean greaterThanFlag(){
        return greaterThanFlag;
    }

    public int lessThanValue(){
        return lessThanValue;
    }

    public int greaterThanValue(){
        return greaterThanValue;
    }

    public String sensor(){
        return sensor;
    }

}
