package edu.neu.coe.info6205.msdRadix;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class helper {

    public static Node[] getChinesePair(String[] arr){
        Node[] array = new Node[arr.length];
        try {
            for (int i = 0; i < arr.length; i++) {
                array[i] = getPinyin(new Node(arr[i]));
            }
           // System.out.println(array[0].getValue());
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return array;
    }

    public static Node getPinyin(Node node) throws BadHanyuPinyinOutputFormatCombination{

        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        node.setValue(PinyinHelper.toHanYuPinyinString(node.getValue(), format,"",false));
        return node;
    }
}
