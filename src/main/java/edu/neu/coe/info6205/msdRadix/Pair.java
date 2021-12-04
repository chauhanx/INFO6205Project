package edu.neu.coe.info6205.msdRadix;

import com.ibm.icu.text.Collator;
import java.util.Locale;

public class Pair {
    private static final Collator collator = Collator.getInstance(Locale.CHINA);
    private String key;
    private String uniValue;
    private byte[] value;

    public Pair(){
        this.key = null;
        this.value = null;
        this.uniValue = null;
    }

    public Pair(String word){
        this.key = word;
        this.value = collator.getCollationKey(word).toByteArray();
        this.uniValue = helper.getPinyin(word);
    }

    public String getKey(){
        return key;
    }

    public String getValue(){
        return uniValue;
    }

    public byte[] getArr(){
        return value;
    }

    public void setValue(String uniValue){
        this.uniValue = uniValue;
    }

}
