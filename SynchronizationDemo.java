/*
 * Cristian Quiterio
 * NQL182
 * 10/9/22
 * CPSC2800 Section 1 40676
 */
package synchronizationdemo;

import java.util.concurrent.Semaphore;
import java.util.Random;

class Task implements Runnable {
    Semaphore semaphore = new Semaphore(1);
    
    @Override
    public void run() {
        try{
            //Initializing the turn with the ball & setting up the random time for playing with the ball and asking for it
            int turn = 1;
            Random rand = new Random();
            //Continuously loop to simulate wanting to play with the ball
            while(true){
                System.out.println(Thread.currentThread().getName() + " wants the ball. Turn #" + turn);
                semaphore.acquire(); //shows mutual exclusion by requiring the thread to have the key to access this code
                System.out.println(Thread.currentThread().getName() + " is currently playing with the ball. Turn #" + turn);
                Thread.sleep(rand.nextInt(1000,5000));
                System.out.println(Thread.currentThread().getName() + " is done playing with the ball. Turn #" + turn);
                turn++;
                semaphore.release(); //shows mutual exclusion by releaseing the key since the operation is complete
                Thread.sleep(rand.nextInt(1000,5000));
            }
        }    
        catch(Exception e){
            e.printStackTrace();
        }
    }
}

public class SynchronizationDemo {

    public static void main(String[] args) {
        //setting up the ball game as the task and the teams as threads
        Task task = new Task();
        Thread blue = new Thread(task);
        Thread red = new Thread(task);
        Thread white = new Thread(task);
        blue.setName("Blue Team");
        red.setName("Red Team");
        white.setName("White Team");
        //activating the teams desire to get the ball
        blue.start();          
        red.start();
        white.start();
    }   
}