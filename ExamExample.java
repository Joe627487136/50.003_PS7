package Week9;

import java.util.Random;
import java.util.concurrent.Phaser;

public class ExamExample
{
    public static int numberofstudents=3;
    public static void main(String[] args) throws InterruptedException
    {
        Examiner();
    }
    public static void Examiner () throws InterruptedException {
        Phaser phaser = new Phaser();
        phaser.register();//register self... phaser waiting for 1 party (thread)
        Random rand = new Random();
        System.out.println("Waiting Student");
        for (int i=0; i<numberofstudents; i++){
            new ExamExample().StudentPhaser(phaser,2000*rand.nextInt(5));
        }
        phaser.arriveAndDeregister();
        Thread.sleep(10000);
        System.out.println("Exam finished");
    }

    private void StudentPhaser(final Phaser phaser,final int sleepTime)
    {
        phaser.register();
        new Thread(){
            @Override
            public void run()
            {
                try
                {
                    System.out.println("Student "+ Thread.currentThread().getName()+" arrived");
                    phaser.arriveAndAwaitAdvance();//threads register arrival to the phaser.
                    Thread.sleep(sleepTime);
                }

                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                System.out.println("Student "+Thread.currentThread().getName()+" handed in papar liao");
            }
        }.start();
    }
}