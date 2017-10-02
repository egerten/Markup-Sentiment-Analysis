package com.example.pro.analysebrand;

public class Brand {
    private BrandAnalysisResult bar;
    private int positiveCount;
    private int negativeCount;
    private int neutralCount;
    public Brand(){
        positiveCount = -1;
        negativeCount = -1;
        neutralCount = -1;
        bar = new BrandAnalysisResult("","-1");
    }
    public Brand(BrandAnalysisResult bar,int positiveCount,int neutralCount, int negativeCount){
        this.neutralCount = neutralCount;
        this.negativeCount = negativeCount;
        this.positiveCount = positiveCount;
        this.bar = bar;
    }
    public int getPositiveCount(){return positiveCount;}
    public int getNegativeCount(){return negativeCount;}
    public int getNeutralCount(){return neutralCount;}
    public BrandAnalysisResult getBar(){return bar;}
    public void setBar(BrandAnalysisResult bar){this.bar = bar;}
    public void setPositiveCount(int positiveCount){this.positiveCount = positiveCount;}
    public void setNegativeCount(int negativeCount){this.negativeCount = negativeCount;}
    public void setNeutralCount(int neutralCount){this.neutralCount = neutralCount;}
}
