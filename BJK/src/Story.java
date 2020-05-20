/*-------------------------------------------------------------------------
Author(s): Justin Ngo
Description: <Implementation of a Queue data structure, dice rolling, and story generation>
<Story implements Queue interface>
*/

import java.util.ArrayList;
import java.util.Scanner;

public class Story implements StoryQueue {

    private class Node { // Creation of basic Linked List
        Object data = null;
        Node next = null;

        public Node(Object data) {
            this.data = data;
        }
    }

    protected Node head = null;
    protected int size = 0;
    protected int sizeLine = 0;
    protected int diceSides = 0;
    protected int numberLuck = 0;
    protected int attempts = 0;
    protected Node tail = null;

    private boolean limit(){ // Limit config settings
        if(sizeLine > 9999 || diceSides > 9999 || numberLuck > 9999){ // Max limit
            System.out.println("Program cannot compute, try lowering your settings");
            return false;
        }
        if(sizeLine == 0 || sizeLine < 0 || diceSides == 0 || diceSides < 0){ // Non negative or zero numbers
            System.out.println("Size of line or dice sides is either negative or zero, try again");
            return false;
        }
        if(numberLuck < 1 || numberLuck > diceSides){ // Number is not on the dice
            System.out.println("Lucky number must be within 1 and number of sides, try again");
            return false;
        }
        if(attempts < 1 || attempts > 20){ // Attempts range
            System.out.println("Attempts must be withing 1 and  20, try again");
            return false;
        }
        else{
            return true;
        }
    }

    private void quickSort() {
        ArrayList<String> meme = new ArrayList<>();

        for (int i = 0; i < sizeLine; i++) {
            meme.add(toStringInd(i));
        }

        Sorts.quicksort(meme);
        System.out.println("The line is now sorted " + meme);
    }

    private void mergeSort() {
        ArrayList<String> meme = new ArrayList<>();

        for (int i = 0; i < sizeLine; i++) {
            meme.add(toStringInd(i));
        }

        Sorts.mergesort(meme);
        System.out.println("The line is now sorted " + meme);
    }

    private int diceRoll() {
        return ((int)(Math.random() * ((diceSides - 1) + 1)) + 1); // Random sided dice roller from 1 - dice sides
    }

    public void add(Object item) {
        Node newNode = new Node(item);

        if (size == 0) {
            head = newNode; // Head set to be the first node added
            tail = head; // Set tail to also be head since their is only one item
        } else {
            tail.next = newNode; // newNode added to next to node in list
            tail = newNode; // Point tail to newNode's index

        }
        size++;
    }

    public boolean findNameInFront(Object item) {

            if (head.data.equals(item)) { // If name guessed is found in head return true
                return true;
        }
        return false;
    }

    public Object getFirstName() {
        return head.data;
    }

    public Object getName(int j) {
        Node curr = head; // Pointer node
        for (int i = 0; i < size; i++) {
            if (i==j) { // Item is found in list and return name
                return curr.data;
            } else {
                curr = curr.next; // Traverse queue at each node of queue
            }
        }
        return false;
    }

    public void setFirstName(Object item) {
        head.data = item;
    }

    public String toString() { // Queue to String
        String whole = "[";
        for(int i = 0; i < size; i++) {
            if(i == size-1){
                whole += getName(i);
            }
            else {
                whole += getName(i) + ", ";
            }
        }
        whole += "]";
        return whole;
    }

    public String toStringInd(int index) { // toString algorithm for sorthing methods
        String whole = "";
        for(int i = 0; i < size; i++) {
            if(i == index){
                whole += getName(i);
            }
        }
        return whole;
    }

    public void diceRollTest(){ // Dice roll method
        System.out.println("Option 1: Dice Roll");
        System.out.println("Option 2: Guess");
        int d;
        Scanner scan = new Scanner(System.in);
        d = scan.nextInt(); // User input
        if(d==1) {
            attempts -= 1;
            int result = diceRoll();
            if(result == numberLuck){ // If dice roll equals user's lucky number win condition
                System.out.println("Dice rolled");
                System.out.println("Result: "+ result);
                Object name = getFirstName();
                System.out.println("Success, the name is " + name);
                return;
            }
            else if(attempts >= 0) { // Dice rolled and not lucky nuumber
                System.out.println("Dice rolled");
                System.out.println("Result: "+ result);
                System.out.println("Attempts left: " + attempts);
                if(attempts == 0){ // Fail condition
                    System.out.println("No more attempts left");
                    return;
                }
                System.out.println("Do you want to roll again?");
                diceRollTest(); // Recursion
            }
        }
        if(d==2) { } // Exit condition
    }

    public void guesser(){ // Check user's name input
        Scanner scan = new Scanner(System.in);
        String name = scan.next();
        Boolean inFront = findNameInFront(name);
        if (inFront == true) {
            System.out.println("You guessed correctly, my name is " + getFirstName());
            System.out.println("The line is comprised of from first to last " + toString());
        } else {
            System.out.println("Sorry, my name is " + getFirstName());
            System.out.println("The line is comprised of from first to last " + toString());
        }
    }

    public void sorter(){ // Sort selection method
        Scanner scan = new Scanner(System.in);
        int d = scan.nextInt();
        if (d == 0) {
            quickSort();
        } else if (d == 1) {
            mergeSort();
        }
    }

    public void generateStory() {
        // List of names that can be choose from
        System.out.println("Is RNG in your favor?");
        String[] myStringArray = new String[]{"Steve", "Greg", "Tim", "Jim", "Kim", "Liam", "Hidgens", "Nixon", "Max", "Levi", "Stephen",
        "Jimmy", "Melanie", "Lester", "Kylie", "Chang", "Benson", "Lex", "Rex", "Cody", "Kyle", "Henson", "Billington", "Franchesco", "Wilmington", "Jafarlesmie","Jreg","Monsieur","Jacob","Swanson","Peter"};
        int j;
        for (int i = 0; i < sizeLine; i++) {
            j=((int)(Math.random() * ((31 - 1) + 1))); // Choose out of 30 names from Steve to Jreg
            add(myStringArray[j]); // String added to Queue
        }
    }

    public void restart() { // Restart method
        System.out.println("Would you like to play again?");
        System.out.println("Yes/No");
        Scanner scan = new Scanner(System.in);
        String d;
        d = scan.next();
        if(d.equals("Yes")){
            GameRunner.init();
        }
        else if(d.equals("No")){
            System.out.println("Have a nice day");
        }
        else{ // Fail input
            System.out.println("Not a valid input");
            restart(); // Recursion
        }
    }

    public void customSettings() { // Custom settings
        System.out.println("Difficulty Settings");
        // Total people in line
        System.out.println("Number of people in line");
        Scanner scan = new Scanner(System.in);
        int customOne = scan.nextInt();
        sizeLine = customOne;
        // Number of sides
        System.out.println("How many sides are on the dice");
        int customTwo = scan.nextInt();
        diceSides = customTwo;
        System.out.println("What is your lucky number? (1-Sides)");
        // Lucky number that will reveal the answer
        int customThree = scan.nextInt();
        numberLuck = customThree;
        System.out.println("Number of attempts? (1-20)");
        // Number of attempts
        int customFour = scan.nextInt();
        attempts = customFour;
        boolean capable = limit();
        if(!capable){
            customSettings(); // Recursion
        }
    }

    public void changeName() { // Custom settings
        Scanner scan = new Scanner(System.in);
        String name = scan.next();
        Object oldName = head.data;
        setFirstName(name);
        System.out.println("Old name was "+ oldName +" new name is " + head.data);
        System.out.println("New line is " + toString());
    }

}
