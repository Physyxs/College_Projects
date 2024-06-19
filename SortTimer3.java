package sorttimer3;
/**
 * Brandon Logan
 * CSC 1351-02
 * Spring 2023
 * Lab 8
 */
import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

/**
 * Creates an array of random integers.
 * Uses the selection sort, insertion sort, merge sort and quick sort 
 * to sort the array.
 * Gets and prints run times for the sort methods.
 */
public class SortTimer3 
{
    
    /**
     * Uses the selection sort to sort an array of integers into
     * increasing order.
     * @param nums the array to be sorted
     */
    public static void selectionSort(int[] nums)
    {
        for (int i = 0; i < nums.length-1; i++)
        {   
            int min = nums[i];
            int minPos = i;
            for(int j = i+1; j < nums.length; j++)
                if(nums[j] < min)
                { 
                    min = nums[j];
                    minPos = j;
                }
            if(minPos != i)
            {
                int temp = nums[i];
                nums[i] = nums[minPos];
                nums[minPos] = temp;
            }
        }
    }

    
    public static void selectionSortPrint(int[] nums)
    {
        for (int i = 0; i < nums.length-1; i++)
        {
            int min = nums[i];
            int minPos = i;
            for(int j = i+1; j < nums.length; j++)
                if(nums[j] < min)
                { 
                    min = nums[j];
                    minPos = j;
                }
            if(minPos != i)
            {
                int temp = nums[i];
                nums[i] = nums[minPos];
                nums[minPos] = temp;
            }
            System.out.println("nums = " + Arrays.toString(nums));
        }
    }

    
    /**
     * Uses the insertion sort to sort an array of integers into
     * increasing order.
     * @param nums the array to be sorted
     */    
    public static void insertionSort(int[] nums)
    {
        boolean inserted;
        int insertVal;
        for(int i = 1; i < nums.length; i++)
        {
            insertVal = nums[i];
            inserted = false;
            for(int j = i-1; j >= 0; j--)
            { 
                if(insertVal < nums[j])
                {
                    nums[j+1] = nums[j];
                }
                else
                {
                    nums[j+1] = insertVal;
                    inserted = true;
                    break;
                }
            }
            if( ! inserted)
            {
                nums[0] = insertVal;
            }
        }
    }

    
    public static void insertionSortPrint(int[] nums)
    {
        boolean inserted;
        int insertVal;
        for(int i = 1; i < nums.length; i++)
        {
            insertVal = nums[i];
            inserted = false;
            for(int j = i-1; j >= 0; j--)
            { 
                if(insertVal < nums[j])
                {
                    nums[j+1] = nums[j];
                }
                else
                {
                    nums[j+1] = insertVal;
                    inserted = true;
                    break;
                }
            }
            if( ! inserted)
            {
                nums[0] = insertVal;
            }
            System.out.println("nums = " + Arrays.toString(nums));
        }
    }


   // Uses the merge sort to sort the subarray nums[left..right].
    public static void mergeSort(int[] nums, int left, int right)
    {
        
        if(left < right)
        {
            int mid = (left + right) / 2;
            if (left < mid)
            {
                mergeSort(nums, left, mid);
            }
            if (mid+1 < right)
            {
                mergeSort(nums, mid+1, right);
            }
            
            int[] result = new int[right - left + 1];
            
            int m = left;
            int n = mid+1;
            int k = 0;
            while (m <= mid && n <= right)
            {
                if (nums[m] <= nums[n])
                {
                    result[k] = nums[m];
                    k++;
                    m++;
                }
                else
                {
                    result[k] = nums[n];
                    k++;
                    n++;
                }
            }
            if (n > mid+1)
            {
                while (m <= mid)
                {
                    result[k] = nums[m];
                    k++;
                    m++;
                }
                while (n <= right)
                {
                    result[k] = nums[n];
                    k++;
                    n++;
                }
                
                for(k=0; k<=right-left; k++)
                {
                    nums[left+k] = result[k];
                }
            }
        }
    }

    
    public static void mergeSortPrint(int[] nums, int left, int right)
    {        
        if(left < right)
        {
            int mid = (left + right) / 2;
            if (left < mid)
            {
                mergeSortPrint(nums, left, mid);
            }
            if (mid+1 < right)
            {
                mergeSortPrint(nums, mid+1, right);
            }
            
            int[] result = new int[right - left + 1];
            
            int m = left;
            int n = mid+1;
            int k = 0;
            while (m <= mid && n <= right)
            {
                if (nums[m] <= nums[n])
                {
                    result[k] = nums[m];
                    k++;
                    m++;
                }
                else
                {
                    result[k] = nums[n];
                    k++;
                    n++;
                }
            }
            if (n > mid+1)
            {
                while (m <= mid)
                {
                    result[k] = nums[m];
                    k++;
                    m++;
                }
                while (n <= right)
                {
                    result[k] = nums[n];
                    k++;
                    n++;
                }
                
                for(k=0; k<=right-left; k++)
                {
                    nums[left+k] = result[k];
                }
            }
        }
        System.out.println("nums = " + Arrays.toString(nums));
    }

    
    // Uses the quick sort to sort the subarray nums[left..right].
    public static void quickSort(int[] nums, int left, int right )
    {
        if(left<right){
            int p = partition(nums, left, right);
            if(left<p){
                quickSort(nums,left,p);
            }
            if(p+1<right){
                quickSort(nums,p+1,right);
            }
        }
    }

    
    // Partitions the subarray nums[left..right].
    public static int partition(int[] nums, int left, int right)
    {
        int pivot = nums[left];
        int i = left-1;
        int j = right+1;
        while(i<j){
            i++;
            while(nums[i]<pivot){i++;}
            j--;
            while(nums[j]>pivot){j--;}
            if(i<j){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        return j;
    }


    public static void quickSortPrint(int[] nums, int left, int right )
    {
        if(left<right){
            int p = partition(nums, left, right);
            System.out.println("nums = " + Arrays.toString(nums));
            if(left<p){
                quickSortPrint(nums,left,p);
            }
            if(p+1<right){
                quickSortPrint(nums,p+1,right);
            }
        }
    }

    
    public static void main(String[] args) 
    {
        
        int[] numsSelection = {5,6,4,7,2,1,8,3};
        System.out.println("Selection Sort:");
        System.out.println("nums = " + Arrays.toString(numsSelection));
        selectionSortPrint(numsSelection);
        System.out.println();
        
        int[] numsInsertion = {5,8,4,6,2,1,7,3};
        System.out.println("Insertion Sort:");
        System.out.println("nums = " + Arrays.toString(numsInsertion));
        insertionSortPrint(numsInsertion);
        System.out.println();
        
        int[] numsMerge = {5,3,6,4,2,0,1};
        System.out.println("Merge Sort:");
        System.out.println("nums = " + Arrays.toString(numsMerge));
        mergeSortPrint(numsMerge, 0, numsMerge.length-1);
        System.out.println();
        
        int[] numsQuick = {5,8,4,7,2,1,6,3};
        System.out.println("Quick Sort:");
        System.out.println("nums = " + Arrays.toString(numsQuick));
        quickSortPrint(numsQuick, 0, numsQuick.length-1);
        System.out.println();
        
        int seed = 0;
        Random rand = new Random(seed);
        int maxRandomValue = 1000000;
        int numRuns = 3;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the starting value for the length n of "
                + "the array to be sorted, the stepsize by which n is "
                + "increased, and the number of steps: ");
        int start = in.nextInt();
        int stepSize = in.nextInt();
        int numSteps = in.nextInt();
        int end = start + numSteps * stepSize;
        System.out.println();
        
        for (int n = start; n <= end; n += stepSize)
        {            
            int[] nums1 = new int[n];
            int[] nums2 = new int[n];
            int[] nums3 = new int[n];
            int[] nums4 = new int[n];
            long sum1 = 0;
            long sum2 = 0;
            long sum3 = 0;
            long sum4 = 0;
            int m;
            
            for (int j = 1; j <= numRuns; j++)
            {                    
                for (int i = 0; i < n; i++)
                {
                    m = rand.nextInt(maxRandomValue) + 1;
                    nums1[i] = m;
                    nums2[i] = m;
                    nums3[i] = m;
                    nums4[i] = m;
                }
                
                long startTime = System.currentTimeMillis();
                selectionSort(nums1);
                long endTime = System.currentTimeMillis();
                long elapsedTime = endTime - startTime;
                sum1 += elapsedTime;        
                
                startTime = System.currentTimeMillis();
                insertionSort(nums2);
                endTime = System.currentTimeMillis();
                elapsedTime = endTime - startTime;
                sum2 += elapsedTime;        

                startTime = System.currentTimeMillis();
                mergeSort(nums3, 0, nums3.length-1);
                endTime = System.currentTimeMillis();
                elapsedTime = endTime - startTime;
                sum3 += elapsedTime;        

                startTime = System.currentTimeMillis();
                quickSort(nums4, 0, nums4.length-1);
                endTime = System.currentTimeMillis();
                elapsedTime = endTime - startTime;
                sum4 += elapsedTime;                    
            }
            long average1 = (long)(1. * sum1 / numRuns);
            long average2 = (long)(1. * sum2 / numRuns);
            long average3 = (long)(1. * sum3 / numRuns);
            long average4 = (long)(1. * sum4 / numRuns);
            System.out.printf("n = %6d   Sort Run Time (milliseconds):  "
                    + "Selection: %4d   Insertion: %4d   Merge: %3d"
                    + "   Quick: %3d\n", 
                    n, average1, average2, average3, average4);
        }        
    }    
}
