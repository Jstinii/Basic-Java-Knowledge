/*-------------------------------------------------------------------------
Author(s): Justin Ngo
Description: <Implementation of a merge sort and quick sort data structures >
<quicksort and mergesort implement a ArrayList and String input>
<Methods include: quicksort(), mergesort(), divideAndCQuick(), divideAndCMerge(), and merge()>
*/
import java.util.ArrayList;

public class Sorts {

    public static void quicksort(ArrayList<String> aList) {

        // Call recursive Quicksort Algorithm
        divideAndConQuick(aList,0,aList.size()-1);

    }

    public static void mergesort(ArrayList<String> aList) {

        // Call recursive Mergesort Algorithm
        divideAndConMerge(aList);

    }

    public static void divideAndConQuick(ArrayList<String> aList, int left, int right) {

        int j = left;

        int n = right;


        // Find middle pivot even if odd size
        String pivot = aList.get(left + (right - left) / 2);


        while (j <= n) {
            while (aList.get(j).length() < pivot.length()) {

                j++; // Move up the list numbers already under pivot
            }
            while (aList.get(n).length() > pivot.length()) {

                n--; // Move down the list numbers already over pivot
            }

            if (j <= n) {

                // Swap j and n values

                String temp = aList.get(j);
                aList.set(j, aList.get(n));
                aList.set(n, temp);

                // Move index to next position on both sides
                j++;
                n--;
            }
        }

        // Call divideAndCQuick() method recursively

        // Go through entire left half of median
        if (left < n) {

            divideAndConQuick(aList, left, n);   // Check from beginning to point left of n

        }
        // Go through entire right half of median
        if (j < right) {


            divideAndConQuick(aList, j, right);  // Check from point j to end


        }
    }

    public static void divideAndConMerge(ArrayList<String> aList)
    {
        // Divider point
        int middle;

        // Create temporary right and left sub lists
        ArrayList<String> left = new ArrayList<>();
        ArrayList<String> right = new ArrayList<>();

        if (aList.size() > 1) { // Avoid StackOverflowError and create exact amount of sublists
            middle = aList.size() / 2;
            for (int j = 0; j < middle; j++) {
                left.add(aList.get(j));
            }
            for (int n = middle; n < aList.size(); n++)
                right.add(aList.get(n));

            // Divide until each element in its own ArrayList
            divideAndConMerge(left);
            divideAndConMerge(right);

            //Merge all ArrayLists to reach the original size ArrayList
            merge(aList, left, right);
        }


    }

    public static void merge(ArrayList<String> aList, ArrayList<String> left, ArrayList<String> right)
    {
        // Temporary ArrayList to build the merge list
        ArrayList<String> temp;

        // Initial index values
        int listI  = 0;
        int leftI  = 0;
        int rightI = 0;

        while (leftI < left.size() && rightI < right.size()) {
            if (left.get(leftI).length() < right.get(rightI).length() ) {
                aList.set(listI, left.get(leftI)); // Add to original ArrayList from smallest of left half
                leftI++;
            } else {
                aList.set(listI, right.get(rightI)); // Add to original ArrayList from smallest of right half
                rightI++;
            }
            listI++;
        }

        int tempIndex = 0;

        if (leftI >= left.size()) {
            temp = right; // Add possible missed ArrayList elements right of left.size to temp ArrayList
            tempIndex = rightI;
        }
        else {
            temp = left;  // Add possible missed ArrayList elements left of right.size to temp
            tempIndex = leftI;
        }

        // Sorted missing elements from ArrayList temp copy to the original one aList
        for (int j = tempIndex; j < temp.size(); j++) {
            aList.set(listI, temp.get(j));
            listI++;
        }

    }
}
