/*-------------------------------------------------------------------------
Author(s): Justin Ngo
Description: <Class calling up methods from Story class>
<Backbone of game and can be though of as the parent class>
*/
import java.util.Scanner;

public class GameRunner{

    // Initialize program
    public static void init(){
        System.out.println("Totally fair and cool game");
        System.out.println("1: Random Line Guesser 2: Quit");
        Scanner scan = new Scanner(System.in);
        int d;
        d = scan.nextInt();
        if(d==1){
            GameRunner.storyTest();
        }
        else if(d==2){
            System.out.println("Have a nice day");
        }
        else{
            System.out.println("Not a valid input");
            init();
        }
    }

    public static void storyTest(){

        Story storyGen = new Story();
        storyGen.customSettings();
        storyGen.generateStory();
        // Output story
        System.out.println("You are a manager at a supermarket and there's someone at the front of line, what's their name?");
        storyGen.diceRollTest(); // Dice Roll
        //Guess
        System.out.println("You are a manager at a supermarket and there's someone at the front of line, what's their name?");
        storyGen.guesser();
        // Sort
        System.out.println("For no apparent reason, you decide to sort the line in your head by length of their first name");
        System.out.println("What is your method of choice, Quicksort[0] or Mergesort[1]?");
        storyGen.sorter();
        System.out.println("You decide to change the name of the first person, what is their new name?");
        storyGen.changeName();
        storyGen.restart();

    }
    }

