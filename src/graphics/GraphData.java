package graphics;

import java.io.Serializable;

public class GraphData implements Serializable {

    private static final long serialVersionUID = 1L;
    
    int minValue;
    int maxValue;
    int[] currValue;

    public GraphData(int minValue, int maxValue, int numSamples) {

        this.minValue = minValue;
        this.maxValue = maxValue;
        currValue = new int[numSamples];
        for(int i=0;i<currValue.length;i++){
            currValue[i] = 0;
        }

    }

    public int getMinValue() {return minValue;}
    public void setMinValue(int minValue) {this.minValue = minValue;}

    public int getMaxValue() {return maxValue;}
    public void setMaxValue(int maxValue) {this.maxValue = maxValue;}

    public int getCurrValue(int index) {return currValue[index];}
    public void setCurrValue(int index, int currValue) {this.currValue[index] = currValue;}

    public int[] getCurrValue() {return currValue;}
    public void setCurrValue(int[] currValue) {this.currValue = currValue;}

    public double getRelativeCurrValue(int index){
        return ((double)currValue[index]/(double)(maxValue - minValue));
    }

}
