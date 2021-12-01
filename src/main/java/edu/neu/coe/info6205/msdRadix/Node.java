package edu.neu.coe.info6205.msdRadix;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class Node {

    private String key;
    private String value;

    public Node(){
        this.key = null;
        this.value = null;
    }

    public Node(String word) throws BadHanyuPinyinOutputFormatCombination{
        HanyuPinyinOutputFormat defaultFormat =  new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
        this.key = word;
        this.value = PinyinHelper.toHanYuPinyinString(word,defaultFormat,"",true);
    }

    public void setKeyValue(String word)throws BadHanyuPinyinOutputFormatCombination{
        new Node(word);
    }

    public String getKey(){
        return key;
    }

    public String getValue(){
        return value;
    }

}
