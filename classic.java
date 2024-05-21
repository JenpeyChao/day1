import java.util.Scanner;
import java.util.Random;
public class classic {
    public static void guessing(int endRange){
        
        Random rand = new Random(); 
        int rand_int = rand.nextInt(endRange);// creates a random number
        rand_int+=1;
        String intGuess;
        int guess = Integer.MAX_VALUE;
        int attempts = 0;
        Scanner myObj = new Scanner(System.in);

        while(guess != rand_int){

            intGuess = myObj.nextLine();
            guess = Integer.parseInt(intGuess);
            if (guess < rand_int){ //if the guess is lower than the actual number
                System.out.println("The number is higher");
            }else if (guess > rand_int){//if the guess is higher than the actual number
                System.out.println("The number is lower");
            }

            attempts+=1;
            if (guess == rand_int){
                System.out.println("Nice you guessed the number");
                System.out.println(attempts+" attempts");
                break;
            }
        }
    }
    public static void main(String[] args){
        boolean tryAgain = true;
        
        while (tryAgain){
            Scanner myObj = new Scanner(System.in); // used to find the user input
            System.out.println("You have a limit of 15 attempts");
            System.out.println("Guess a random number between 1 and 100");
            guessing(100);
            
            
            System.out.println("Would you like to try again?"); //asks if you want to try again
            String again = myObj.nextLine();
            again.toLowerCase();
            if (!again.equals("yes")){ // if they say anything but yes then it stops
                tryAgain = false;
            }
        }
        System.out.println("Goodbye");
    

    }
    
}
