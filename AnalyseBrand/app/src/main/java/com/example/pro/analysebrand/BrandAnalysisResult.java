package com.example.pro.analysebrand;


import java.util.List;

public class BrandAnalysisResult {
    private String brandName;
    private String tweetCount;
    public BrandAnalysisResult(){
    }
    public BrandAnalysisResult(String brandName, String tweetCount){
        this.brandName = brandName;
        this.tweetCount = tweetCount;
    }
    public String getBrandName(){
        return this.brandName;
    }
    public void setBrandName(String brandName){
        this.brandName = brandName;
    }
    public String getTweetCount(){
        return this.tweetCount;
    }
    public void setTweetCount(String tweetCount){
        this.tweetCount = tweetCount;
    }
    /*public static List<BrandAnalysisResult> getBrands(){
        List<BrandAnalysisResult>
    }*/
}
