package edu.neu.coe.info6205.msdRadix;

public class Pair {
    private String key;
    private String value;


    public Pair(){
        this.key = null;
        this.value = null;
    }

    public Pair(String key,String value){
        this.key = key;
        this.value = value;
    }

    public Pair(String key){
        this.key = key;
        this.value = helper.getPinyin(key);
    }

    public String getKey(){
        return key;
    }

    public String getValue(){
        return value;
    }


}
