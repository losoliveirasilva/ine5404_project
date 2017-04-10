package main;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by losoliveirasilva on 7/4/16.
 */
public class GreenhouseFileInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private ArrayList<StandFileInfo> stands;

    public GreenhouseFileInfo(){
        super();

        stands = new ArrayList();
    }

    public void add(StandFileInfo s){
        stands.add(s);
    }

    public StandFileInfo[] get(){
        StandFileInfo[] s = new StandFileInfo[stands.size()];

        for (int i = 0; i < s.length; i++) {
            s[i] = stands.get(i);
        }

        return s;
    }

}
