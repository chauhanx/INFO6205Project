package edu.neu.coe.info6205.msdRadix;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class helper {

    public static Node[] getChinesePair(String[] arr){
        Node[] array = new Node[arr.length];
        try {
            for (int i = 0; i < arr.length; i++) {
                array[i] = new Node(arr[i]);
            }
           // System.out.println(array[0].getValue());
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return array;
    }
}
