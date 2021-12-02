package edu.neu.coe.info6205.msdRadix;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class helper {

    public static Node[] getChinesePair(String[] arr){
        Node[] array = new Node[arr.length];
        try {
            for (int i = 0; i < arr.length; i++) {
                array[i] = new Node(arr[i]);
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return array;
    }
}
