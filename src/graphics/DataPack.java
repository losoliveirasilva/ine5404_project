package graphics;

import java.awt.*;

/**
 * Created by losoliveirasilva on 6/2/16.
 */
public class DataPack {

    /* Code */
    private String code;

    /* Tab Name */
    private String tabName;

    /* Background */
    private Color cBack;
    /* Background */
    private Color cGrid;
    /* pH */
    private Color cpH;
    /* DO */
    private Color cDO;
    /* EC */
    private Color cEC;
    /* ORP */
    private Color cORP;
    /* Fluxo de agua */
    private Color cFlux;
    /* Temperatura da Agua */
    private Color cTAg;
    /* Nivel Agua */
    private Color cNvAg;
    /* Temperatura Ambiente */
    private Color cTAm;
    /* Umidade Ambiente */
    private Color cHum;
    /* Luz */
    private Color cLight;

    public static final String[] stringset = {
        "Background",           // 0
        "Grid",                 // 1
        "pH",                   // 2
        "DO",                   // 3
        "EC",                   // 4
        "ORP",                  // 5
        "Fluxo",                // 6
        "Temp. agua",           // 7
        "Nivel de agua",        // 8
        "Temp. ambiente",       // 9
        "Umidade ambiente",     // 10
        "Luz ambiente"          // 11
    };

    public DataPack(String code,
                    String tabName,
                    Color cBack,
                    Color cGrid,
                    Color cpH,
                    Color cDO,
                    Color cEC,
                    Color cORP,
                    Color cFlux,
                    Color cTAg,
                    Color cNvAg,
                    Color cTAm,
                    Color cHum,
                    Color cLight
    ){
        super();

        this.code = code;
        this.tabName = tabName;
        this.cBack = cBack;
        this.cGrid = cGrid;
        this.cpH = cpH;
        this.cDO = cDO;
        this.cEC = cEC;
        this.cORP = cORP;
        this.cFlux = cFlux;
        this.cTAg = cTAg;
        this.cNvAg = cNvAg;
        this.cTAm = cTAm;
        this.cHum = cHum;
        this.cLight = cLight;

    }

    public String getTabName(){
        return this.tabName;
    }

    public String getCode(){
        return this.code;
    }

    public Color[] getColorVect(){
        return new Color[] {
            this.cBack,
            this.cGrid,
            this.cpH,
            this.cDO,
            this.cEC,
            this.cORP,
            this.cFlux,
            this.cTAg,
            this.cNvAg,
            this.cTAm,
            this.cHum,
            this.cLight
        };
    }

    public int getAvailableNum(){
        int num = 0;
        Color [] c = getColorVect();

        for(int i = 0; i < 11; i++){

            num += (c[i] != null)?1:0;

        }

        return num;
    }

    public Color[] getAvailableColor(){
        Color[] velho = getColorVect();
        Color[] novo = new Color[getAvailableNum()];
        int k = 0;

        for(int i = 0; i < 11; i++){

            if (velho[i] != null){
                novo[k++] = velho[i];
            }
        }

        return novo;
    }

    public String[] getAvailableString(){
        Color[] velho = getColorVect();
        String[] novo = new String[getAvailableNum()];
        int k = 0;

        for(int i = 0; i < 11; i++){

            if (velho[i] != null){
                novo[k++] = stringset[i];
            }
        }

        return novo;
    }

    // Debug only
    public String getColorsString(){
        String str = "";

        str += tabName + "\n";
        str += (this.cBack != null)?"Back: " + this.cBack.toString() + "\n":"";
        str += (this.cGrid != null)?"Grid: " + this.cGrid.toString() + "\n":"";
        str += (this.cpH != null)?"ph: " + this.cpH.toString() + "\n":"";
        str += (this.cDO != null)?"DO: " + this.cDO.toString() + "\n":"";
        str += (this.cEC != null)?"EC: " + this.cEC.toString() + "\n":"";
        str += (this.cORP != null)?"ORP: " + this.cORP.toString() + "\n":"";
        str += (this.cFlux != null)?"Flux: " + this.cFlux.toString() + "\n":"";
        str += (this.cTAg != null)?"TAg: " + this.cTAg.toString() + "\n":"";
        str += (this.cNvAg != null)?"NvAg: " + this.cNvAg.toString() + "\n":"";
        str += (this.cTAm != null)?"TAm: " + this.cTAm.toString() + "\n":"";
        str += (this.cHum != null)?"Hum: " + this.cHum.toString() + "\n":"";
        str += (this.cLight != null)?"Light: " + this.cLight.toString() + "\n":"";
        
        return str;
    }

}
