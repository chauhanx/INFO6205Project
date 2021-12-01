package edu.neu.coe.info6205.util;

import org.junit.Before;
import org.junit.Test;

import com.google.common.io.Resources;

import edu.neu.coe.info6205.msdRadix.MSDRadixSort;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.HelperFactory;
import edu.neu.coe.info6205.sort.SortWithHelper;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class TimerTest {

    @Before
    public void setup() {
        pre = 0;
        run = 0;
        post = 0;
    }

    @Test
    public void testStop() {
        final Timer timer = new Timer();
        GoToSleep(TENTH, 0);
        final double time = timer.stop();
        assertEquals(TENTH_DOUBLE, time, 20);
        assertEquals(1, run);
        assertEquals(1, new PrivateMethodTester(timer).invokePrivate("getLaps"));
    }

    @Test
    public void testPauseAndLap() {
        final Timer timer = new Timer();
        final PrivateMethodTester privateMethodTester = new PrivateMethodTester(timer);
        GoToSleep(TENTH, 0);
        timer.pauseAndLap();
        final Long ticks = (Long) privateMethodTester.invokePrivate("getTicks");
        assertEquals(TENTH_DOUBLE, ticks / 1e6, 12);
        assertFalse((Boolean) privateMethodTester.invokePrivate("isRunning"));
        assertEquals(1, privateMethodTester.invokePrivate("getLaps"));
    }

    @Test
    public void testPauseAndLapResume0() {
        final Timer timer = new Timer();
        final PrivateMethodTester privateMethodTester = new PrivateMethodTester(timer);
        GoToSleep(TENTH, 0);
        timer.pauseAndLap();
        timer.resume();
        assertTrue((Boolean) privateMethodTester.invokePrivate("isRunning"));
        assertEquals(1, privateMethodTester.invokePrivate("getLaps"));
    }

    @Test
    public void testPauseAndLapResume1() {
        final Timer timer = new Timer();
        GoToSleep(TENTH, 0);
        timer.pauseAndLap();
        GoToSleep(TENTH, 0);
        timer.resume();
        GoToSleep(TENTH, 0);
        final double time = timer.stop();
        assertEquals(110, time, 10.0);
        assertEquals(3, run);
    }

    @Test
    public void testLap() {
        final Timer timer = new Timer();
        GoToSleep(TENTH, 0);
        timer.lap();
        GoToSleep(TENTH, 0);
        final double time = timer.stop();
        assertEquals(TENTH_DOUBLE, time, 10.0);
        assertEquals(2, run);
    }
    
    @Test
    public void getRadixsort()
    {
    	  MSDRadixSort msdSort = new MSDRadixSort();
    	   String[] a = null;
    	
    	 try{
             String fileName = "hindi.txt";
             URL url = Resources.getResource(fileName);
             ArrayList<String> lines = new ArrayList<>(Resources.readLines(url,StandardCharsets.UTF_8));
             a = lines.toArray(new String[0]);
             String[] aux = new String[a.length];
             msdSort.sort(a);
//             msdSort.print(a);
         }catch(FileNotFoundException ex){
             System.out.println(ex.getMessage());
         } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	   
    	
    	 final Config config = ConfigTest.setupConfig("true", "0", "1", "", "");
         Helper<Integer> helper = HelperFactory.create("InsertionSort", a.length, config);
         helper.init(a.length);
         final PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);
         MSDRadixSort sorter = new MSDRadixSort();
         sorter.sort(a);
         
          Timer timer = new Timer();
         final String[] temp =a;
          double mean = timer.repeat(10, () -> temp, t -> {
        	 sorter.sort(temp);
             return null;
         });
        System.out.println("Time taken for Sorted Array: "+mean);
    }

    @Test
    public void testPause() {
        final Timer timer = new Timer();
        GoToSleep(TENTH, 0);
        timer.pause();
        GoToSleep(TENTH, 0);
        timer.resume();
        final double time = timer.stop();
        assertEquals(TENTH_DOUBLE, time, 10.0);
        assertEquals(2, run);
    }

    @Test
    public void testMillisecs() {
        final Timer timer = new Timer();
        GoToSleep(TENTH, 0);
        timer.stop();
        final double time = timer.millisecs();
        assertEquals(TENTH_DOUBLE, time, 20.0);
        assertEquals(1, run);
    }

    @Test
    public void testRepeat1() {
        final Timer timer = new Timer();
        final double mean = timer.repeat(10, () -> {
            GoToSleep(HUNDREDTH, 0);
            return null;
        });
        assertEquals(10, new PrivateMethodTester(timer).invokePrivate("getLaps"));
        assertEquals(TENTH_DOUBLE / 10, mean, 6);
        assertEquals(10, run);
        assertEquals(0, pre);
        assertEquals(0, post);
    }

    @Test
    public void testRepeat2() {
        final Timer timer = new Timer();
        final int zzz = 40;
        final double mean = timer.repeat(10, () -> zzz, t -> {
            GoToSleep(t, 0);
            return null;
        });
        assertEquals(10, new PrivateMethodTester(timer).invokePrivate("getLaps"));
        assertEquals(zzz, mean, 8.5);
        assertEquals(10, run);
        assertEquals(0, pre);
        assertEquals(0, post);
    }

//    @Test // Slow
//    public void testRepeat3() {
//        final Timer timer = new Timer();
//        final int zzz = 50;
//        final double mean = timer.repeat(10, () -> zzz, t -> {
//            GoToSleep(t, 0);
//            return null;
//        }, t -> {
//            GoToSleep(t, -1);
//            return t;
//        }, t -> GoToSleep(10, 1));
//        assertEquals(10, new PrivateMethodTester(timer).invokePrivate("getLaps"));
//        assertEquals(zzz, mean, 6);
//        assertEquals(10, run);
//        assertEquals(10, pre);
//        assertEquals(10, post);
//    }

    int pre = 0;
    int run = 0;
    int post = 0;

    private void GoToSleep(long mSecs, int which) {
        try {
            Thread.sleep(mSecs);
            if (which == 0) run++;
            else if (which > 0) post++;
            else pre++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static final int TENTH = 100;
    public static final double TENTH_DOUBLE = 100;
    public static final int HUNDREDTH = 10;

}