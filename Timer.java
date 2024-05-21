import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Random;
public class Timer {
    static AtomicBoolean stop = new AtomicBoolean(false);
    public static void guessing(int endRange, int countLimit, long time){
        AtomicInteger guess = new AtomicInteger(0);

        Random rand = new Random();
        int rand_int = rand.nextInt(endRange);
        rand_int+=1;
        AtomicInteger attempts = new AtomicInteger(0);
        
        Thread userInputThread = new Thread();

        Thread timeThread = new Thread(()->{ 
            // made a thread to act like a timer
            try{
            Thread.sleep(time);
            //once the thread is done sleeping it sets the bool into true, where it stops 
            stop.getAndSet(true);

            return;
            }catch(InterruptedException e){
                
            }
        });
        timeThread.start();

        //loops until the time stops, the user found the number, or the count for attempts are gone
        while(guess.get() != rand_int && countLimit > 0 && !stop.get()){
            //restarts the guess and stops the prev thread
            guess.set(0);
            userInputThread.interrupt();
            //creates a new thread every time, so the user can keep guessing
            userInputThread = new Thread(()->{
                System.out.println("Pick a number!");
                try{
                    
                    Scanner myObj = new Scanner(System.in);
                    int input = myObj.nextInt();
                    myObj.nextLine();
                    guess.set(input);
                    
                    return;
                }finally{}
                
    
            });
            userInputThread.start();
            try{
                // if the time stops then you stop the thread and loop
                if (stop.get()){
                    userInputThread.interrupt();
                    userInputThread.join();
                    timeThread.join();
                    break;
                }else{
                    //user has 5 sec before it closes
                    userInputThread.join(5000);
                    System.out.println(guess.get());
                    //if the time hasnt ran out and the thread is alive and they havent guessed yet
                    if (!stop.get()&& userInputThread.isAlive() && guess.get() == 0  ){
                        //then you remove a try and continue to the next loop
                        System.out.println("Pick Faster!");
                        countLimit-=1;
                        System.out.println("You have "+ countLimit + " more tries");
                        continue;
                    }
                }  
            }catch(InterruptedException e){
            }
            //if the time has ran out
            if(stop.get()){
                System.out.println("You ran out of time");
                System.out.println("The number was "+ rand_int);
            }else if (guess.get() < rand_int){
                System.out.println("The number is higher");
            }else if (guess.get() > rand_int){
                System.out.println("The number is lower");
            }
            countLimit-=1;
            attempts.incrementAndGet();
            //if the user guesses the number then it stops the loop
            if (guess.get() == rand_int){
                System.out.println("Nice you guessed the number");
                break;
            //if the user ran out of attempts to guess
            }else if (countLimit<=0){
                System.out.println("Sorry you didnt guess the number");
                System.out.println(attempts.get()+" attempts");
            }
            System.out.println("You have "+ countLimit + " more tries");

        }

    }
    public static void main(String[] args){

        
        //asks the user if they want to play in easy, med, or hard
        System.out.println("Choose a difficulty: (Easy, Medium, or Hard)");
        Scanner myObj = new Scanner(System.in);
        String diff = myObj.nextLine();
        diff = diff.toLowerCase();
        switch(diff){ //picks what they chose
            case "easy":
                System.out.println("You have a limit of 10 attempts");
                System.out.println("You have 60 seconds");
                System.out.println("Guess a random number between 1 and 50");
                    
                guessing(50,10,60000);
                break;
            case "medium":
                System.out.println("You have a limit of 15 attempts");
                System.out.println("You have 40 seconds");
                System.out.println("Guess a random number between 1 and 100");
                    
                guessing(100,15,40000);
                break;
            case "hard":
                System.out.println("You have a limit of 20 attempts");
                System.out.println("You have 30 seconds");
                System.out.println("Guess a random number between 1 and 500");
                    
                guessing(500,20,30000);
                break;
            default:
                break;
        }
        myObj.close();
    }
    
}
