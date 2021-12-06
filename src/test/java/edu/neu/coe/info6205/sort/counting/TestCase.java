
package edu.neu.coe.info6205.sort.counting;

import edu.neu.coe.info6205.msdRadix.*;
import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

    public class TestCase {
        String[] input = "刘持平 洪文胜 樊辉辉 苏会敏 高民政 曹玉德 袁继鹏 舒冬梅 杨腊香".split(" ");
        String[] expected = "刘持平 洪文胜 樊辉辉 苏会敏 高民政 曹玉德 袁继鹏 舒冬梅 杨腊香".split(" ");

        @Test
        public void test0() {
            System.out.println("Test case 0:");
            System.out.println("Before sorting: " + Arrays.toString(input));
            MSDRadixPair.sort(input);
            System.out.println("After sorting: " + Arrays.toString(expected));
            assertArrayEquals(expected, input);
            System.out.println();
        }



        @Test
        public void test1() throws IOException {
            String[] words = getWords("chinese.txt", HindiTestCase::lineAsList);
            LSDRadixPair.sort(words);
            System.out.println("Test case 1:");
            System.out.println("After sorting 2nd word must be: अंक ");
            assertEquals("洪文胜", words[1]);
            System.out.println("After sorting  4th word must be: अंकुर");
            assertEquals("苏会敏", words[3]);
        }

        @Test
        public void test2()
        {
            System.out.println("Test case 2:");
            System.out.println("Before sorting: " + Arrays.toString(input));
            LSDRadixPair.sort(input);
            System.out.println("After sorting: " + Arrays.toString(expected));
            assertArrayEquals(expected, input);
            System.out.println();
        }

        @Test
        public void test3()
        {
            String in[]="刘持平 洪文胜 樊辉辉 苏会敏".split(" ");
            QuickDualPivotPair.sort(in);
            assertArrayEquals(expected, input);
        }

        @Test
        public void test4()
        {
            String arr[] = {};
            TimSortPair.sort(arr);
            assertArrayEquals(new String[0], arr);
        }


        @Test
        public void test5()
        {
            String arr[] = {};
            MSDRadixSort.sort(arr);
            assertArrayEquals(new String[0], arr);
        }

        @Test
        public void test6() throws IOException {
            System.out.println("Test case 6:");
            System.out.println("Before sorting: " + Arrays.toString(input));
            TimSortPair.sort(input);
            System.out.println("After sorting: " + Arrays.toString(expected));
            assertArrayEquals(expected, input);
            System.out.println();
        }

        @Test
        public void test7()
        {
            System.out.println("Test case 7:");
            System.out.println("Before sorting: " + Arrays.toString(input));
            TimSortPair.sort(input);
            System.out.println("After sorting: " + Arrays.toString(expected));
            assertArrayEquals(expected, input);
            System.out.println();
        }

        /**
         * Create a string representing an integer, with commas to separate thousands.
         *
         * @param x the integer.
         * @return a String representing the number with commas.
         */
        public static String formatWhole(final int x) {
            return String.format("%,d", x);
        }

        /**
         * Method to open a resource relative to this class and from the corresponding File, get an array of Strings.
         *
         * @param resource           the URL of the resource containing the Strings required.
         * @param stringListFunction a function which takes a String and splits into a List of Strings.
         * @return an array of Strings.
         */
        static String[] getWords(final String resource, final Function<String, List<String>> stringListFunction) {
            try {
                final File file = new File(getPathname(resource, HindiTestCase.class));
                final String[] result = getWordArray(file, stringListFunction, 2);
                System.out.println("getWords: testing with " + formatWhole(result.length) + " unique words: from " + file);
                return result;
            } catch (final FileNotFoundException e) {
                System.out.println("Cannot find resource: " + resource);
                return new String[0];
            }
        }

        private static List<String> getWordList(final FileReader fr, final Function<String, List<String>> stringListFunction, final int minLength) {
            final List<String> words = new ArrayList<>();
            for (final Object line : new BufferedReader(fr).lines().toArray())
                words.addAll(stringListFunction.apply((String) line));
            return words.stream().distinct().filter(s -> s.length() >= minLength).collect(Collectors.toList());
        }


        /**
         * Method to read given file and return a String[] of its content.
         *
         * @param file               the file to read.
         * @param stringListFunction a function which takes a String and splits into a List of Strings.
         * @param minLength          the minimum acceptable length for a word.
         * @return an array of Strings.
         */
        static String[] getWordArray(final File file, final Function<String, List<String>> stringListFunction, final int minLength) {
            try (final FileReader fr = new FileReader(file)) {
                return getWordList(fr, stringListFunction, minLength).toArray(new String[0]);
            } catch (final IOException e) {
                System.out.println("Cannot open file: " + file);
                return new String[0];
            }
        }

        static List<String> lineAsList(final String line) {
            final List<String> words = new ArrayList<>();
            words.add(line);
            return words;
        }

        private static String getPathname(final String resource, @SuppressWarnings("SameParameterValue") final Class<?> clazz) throws FileNotFoundException {
            final URL url = clazz.getClassLoader().getResource(resource);
            if (url != null) return url.getPath();
            throw new FileNotFoundException(resource + " in " + clazz);
        }

    }