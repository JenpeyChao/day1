import java.util.Scanner;
import java.util.Random;
public class DifficultyLevels {
    public static void guessing(int endRange, int countLimit){
        //creates the variables needed 
        Random rand = new Random();
        int rand_int = rand.nextInt(endRange);
        rand_int+=1;
        String intGuess;
        int attempts = 0;
        int guess = Integer.MAX_VALUE;
        Scanner myObj = new Scanner(System.in);
        //loops until the user found the number or when the attempt is lower than 0
        while(guess != rand_int && countLimit > 0){
            //gets the number from the user and tells if its higher or lower
            intGuess = myObj.nextLine();
            guess = Integer.parseInt(intGuess);
            if (guess < rand_int){
                System.out.println("The number is higher");
            }else if (guess > rand_int){
                System.out.println("The number is lower");
            }

            countLimit-=1;
            attempts+=1;
            //prints if you found the number
            if (guess == rand_int){
                System.out.println("Nice you guessed the number");
            //tells you howm any attemps you did when you ran out of attempts
            }else if (countLimit<=0){
                System.out.println("Sorry you didnt guess the number");
                System.out.println(attempts+" attempts");
            }
            System.out.println("You have "+ countLimit + " more tries");

        }
    }
    public static void main(String[] args){
        boolean tryAgain = true;
        
        while (tryAgain){
            //find which difficulty the user wants 
            System.out.println("Choose a difficulty: (Easy, Medium, or Hard)");
            Scanner myObj = new Scanner(System.in);
            String diff = myObj.nextLine();
            diff.toLowerCase(); // makes it all lowercase

            switch(diff){ //lets the user pick if they want easy, med, or hard 
                //each case has a limit of attempts 
                case "easy":
                    System.out.println("You have a limit of 10 attempts");
                    System.out.println("Guess a random number between 1 and 50");
                    guessing(50,10);
                    break;
                case "medium":
                    System.out.println("You have a limit of 15 attempts");
                    System.out.println("Guess a random number between 1 and 100");
                    guessing(100,15);
                    break;
                case "hard":
                    System.out.println("You have a limit of 20 attempts");
                    System.out.println("Guess a random number between 1 and 500");
                    guessing(500,20);
                    break;
                default:
                    break;
            }
            //if they chose none of the options or misspelled they can try again
            //allows the user to play again if they wanted to 
            System.out.println("Would you like to try again?");
            String again = myObj.nextLine();
            again.toLowerCase();
            if (!again.equals("yes")){
                tryAgain = false;
            }
        }
        System.out.println("Goodbye");

    }
    
}
