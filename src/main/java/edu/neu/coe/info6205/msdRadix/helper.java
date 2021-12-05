package edu.neu.coe.info6205.msdRadix;

import com.ibm.icu.text.Collator;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.Locale;


public class helper {

    static Collator collator = Collator.getInstance(Locale.CHINESE);

    public static Pair[] getChinesePair(String[] arr){
        Pair[] array = new Pair[arr.length];
        for (int i = 0; i < arr.length; i++) {
            array[i] = new Pair(arr[i]);
        }
       // System.out.println(array[0].getValue());
        return array;
    }


    public static Pair getPinyin(Pair pair) throws BadHanyuPinyinOutputFormatCombination{

        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        pair.setValue(PinyinHelper.toHanYuPinyinString(pair.getValue(), format,"",false));
        return pair;
    }

    public static int compare(String str1, String str2){
        return collator.compare(str1, str2);
    }

    public static int compareChar(char str1, char str2){
        return collator.compare(str1, str2);
    }


    public static String getPinyin(String s)  {
        try {
            HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
            format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
            format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
            String str = PinyinHelper.toHanYuPinyinString(s, format, "", false);
            return str;
        }catch (BadHanyuPinyinOutputFormatCombination ex){
            return null;
        }
    }

}
