package main;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by losoliveirasilva on 6/28/16.
 */
public class AlertManager implements Serializable {

    private static final long serialVersionUID = 1L;

    private ArrayList<Alert> alertsList;

    public AlertManager(){
        super();
        alertsList = new ArrayList();
    }

    public void add(Alert a){
        alertsList.add(a);
    }

    public Alert[] get(){
        Alert[] a = new Alert[alertsList.size()];

        for (int i = 0; i < a.length; i++) {
            a[i] = alertsList.get(i);
        }

        return a;
    }

    public void remove(Alert a){
        alertsList.remove(a);
    }

    public void remove(int i){
        alertsList.remove(i);
    }

    public String computeAll(String [] data){
        String str = "";

        if(alertsList.size() == 0) return "";

        for (int i = 0; i < alertsList.size(); i++) {
            str += alertsList.get(i).compute(Integer.parseInt(data[alertsList.get(i).sensorNum()]));
        }

        return str;
    }
}
