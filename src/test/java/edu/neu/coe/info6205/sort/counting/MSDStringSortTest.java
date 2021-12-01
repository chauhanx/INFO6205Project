package edu.neu.coe.info6205.sort.counting;

import edu.neu.coe.info6205.msdRadix.MSDRadixSort;
import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.util.Config;
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

public class MSDStringSortTest {

    String[] input = "आके कान्हा फिर से बंशी बजा दे कलयुगी गोपियों को फिर से नचा दे आके कान्हा तू फिर से बंशी बजा दे".split(" ");
    String[] expected = "आके आके कलयुगी कान्हा कान्हा को गोपियों तू दे दे दे नचा फिर फिर फिर बंशी बंशी बजा बजा से से से".split(" ");

    @Test
    public void sort() {
        System.out.println("Test case 1:");
        System.out.println("Before sorting: " + Arrays.toString(input));
        MSDRadixSort.sort(input);
        System.out.println("After sorting: " + Arrays.toString(input));
        assertArrayEquals(expected, input);
        System.out.println();
    }

    @Test
    public void sort1() throws IOException {
        int n = 11368;
        final Helper<String> helper = new BaseHelper<>("test", n, 1L, Config.load(MSDStringSortTest.class));
        helper.init(n);
        String[] words = getWords("hindi.txt", MSDStringSortTest::lineAsList);
        MSDRadixSort.sort(words);
        System.out.println("Test case 2:");
        System.out.println("After sorting 2nd word must be: अंक ");
        assertEquals("अंक", words[1]);
        System.out.println("After sorting  4th word must be: अंकुर");
        assertEquals("अंकुर", words[3]);
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
            final File file = new File(getPathname(resource, MSDStringSortTest.class));
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