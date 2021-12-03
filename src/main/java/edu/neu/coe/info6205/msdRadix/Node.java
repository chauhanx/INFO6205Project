package edu.neu.coe.info6205.msdRadix;

public class Node {

    private String key;
    private String value;

    public Node(){
        this.key = null;
        this.value = null;
    }

    public Node(String word){
        this.key = word;
        this.value = word;
//        this.value = PinyinHelper.toHanYuPinyinString(word,defaultFormat,"",true);
    }

    public String getKey(){
        return key;
    }

    public String getValue(){
        return value;
    }

    public void setValue(String value){
        this.value = value;
    }

}
