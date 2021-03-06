package edu.neu.coe.info6205.msdRadix;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

    public class TimSortPair {

        static int RUN = 32;
        static boolean isChinese = true;

        public static int min(int a, int b)
        {
            if(a<b)
                return a;
            else
                return b;
        }

        public static void insertionSort(Pair a[], int beg, int end) /* function to sort an array with
	insertion sort */
        {
            int i, j;
            Pair temp;
            for (i = beg + 1; i <= end; i++)
            {
                temp = a[i];
                j = i - 1;

                while(j >= beg && less(temp.getValue(),a[j].getValue())){
                    a[j+1] = a[j];
                    j = j-1;
                }
                a[j+1] = temp;
            }

        }


        private static void timSort(Pair a[], int n)
        {
            /* Sort individual subarrays of size RUN */
            for (int i = 0; i < n; i+=RUN)
                insertionSort(a, i, min((i+RUN-1), (n-1)));
            // Start merging from size RUN (or 32).
            for (int size = RUN; size < n; size = 2*size)
            {
                for (int beg = 0; beg < n; beg += 2*size)
                {
	            /* find ending point of left subarray. The
	starting point of right sub array is mid + 1 */
                    int mid = beg + size - 1;
                    int end = min((beg + 2*size - 1),(n-1));

	            /* Merge subarray a[beg...mid] and a[mid
	+1...end] */
                    if(mid < end)
                        merge(a, beg, mid, end);
                }
            }
        }

        private static void merge(Pair a[], int beg, int mid, int end)
        {
            int i, j, k;
            int n1 = mid - beg + 1;
            int n2 = end - mid;
            //temporary arrays
            Pair[] LeftArray = new Pair [n1];
            Pair[] RightArray = new Pair [n2];
            /* copy data to temp arrays */
            for (i = 0; i < n1; i++)
                LeftArray[i] = a[beg + i];
            for (j = 0; j < n2; j++)
                RightArray[j] = a[mid + 1 + j];
            i = 0;
            j = 0;
            k = beg;
            while (i < n1 && j < n2) {
                if(less(LeftArray[i].getValue(),RightArray[j].getValue())) a[k++] = LeftArray[i++];
                else a[k++] = RightArray[j++];
            }
            while (i<n1){
                a[k++] = LeftArray[i++];
            }
            while (j<n2) {
                a[k++] = RightArray[j++];
            }

        }

        private static boolean less(String v, String w) {
            return helper.compare(v,w) <= 0;
        }

        /* function to print the array elements */
        public static void sort(String[] arr) {
            Pair[] a = new Pair[arr.length];
            for(int i=0;i<arr.length;i++){
                a[i] = new Pair(arr[i]);
            }
            timSort(a, a.length);

//            to print array back to chinese words
//            for(int i=0;i<arr.length;i++){
//                arr[i] = a[i].getKey();
//            }
        }

        public static void main(String args[]) throws IOException
        {
            try
            {
                IOTextFile io = new IOTextFile();
                String[] a = io.readFileStreamByLength(isChinese,200);
                sort(a);
                io.writeStream(a);
            }
            catch(FileNotFoundException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }


