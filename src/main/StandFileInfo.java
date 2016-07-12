package main;

import graphics.DataPack;
import graphics.GraphData;
import graphics.Ponto;

import java.io.Serializable;

/**
 * Created by losoliveirasilva on 7/4/16.
 */
public class StandFileInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String alertString;
    private DataPack dataPack;
    private GraphData[] graphData;
    private AlertManager alertManager;

    public StandFileInfo(String as, DataPack dp, GraphData[] gd, AlertManager am){

        super();

        this.alertString = as;
        this.dataPack = dp;
        this.graphData = gd;
        this.alertManager = am;

    }

    public String alerts(){
        return alertString;
    }

    public DataPack dataPack(){
        return dataPack;
    }

    public GraphData[] graphData(){
        return graphData;
    }

    public AlertManager alertManager(){
        return alertManager;
    }


}
