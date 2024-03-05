import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BingoGame implements Runnable{

    List<BingoCard> cards;
    public static boolean[] result;
    public static boolean bingoChecker;
    int [] prev = new int[76];

    public BingoGame(){
        result = new boolean[76];
        result[0] = true;
        bingoChecker = false;
        cards = new ArrayList<>();
    }

    @Override
    public void run(){

        Scanner sc = new Scanner(System.in);
        System.out.print("How many players? ");
        int cnt = sc.nextInt();

        for(int i = 0; i < cnt; i++){
            cards.add(new BingoCard(i+1));
        }
        for(BingoCard card: cards){
            System.out.println("Card "+ card.id);
            System.out.println(card);
        }
        ArrayList<Thread> thrd = new ArrayList<>();

      //  for(BingoCard card: cards){
           // thrd.add(new Thread(new BingoRowChecker(card,3)));
      //  }

        for(BingoCard card: cards){
            thrd.add(new Thread(new BingoPatternPlus(card,3,3)));
        }

        for(Thread t: thrd){
            t.start();
        }
        int rand = 0;
        Random r = new Random();
       int ctr = 0;

        while(!bingoChecker){
            for(int i = 0; i < 76; i++){
                do{
                    rand = r.nextInt(75) + 1;
                }while(sameNumberChecker(rand,i));
            }
            prev[ctr] = rand;
            System.out.println("result: " + rand);

            for(int i = 0; i < ctr; i++){
                System.out.print(" "+ prev[i] + " ");
            }
            result[rand] = true;

            synchronized (result){
                result.notifyAll();
            }

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ctr++;
        }

    }
    private boolean sameNumberChecker(int num, int upper){
        boolean idk = false;
        for(int i = 0; i < upper; i++){
            if(num == prev[i]){
                idk = true;
                break;
            }
        }
        return idk;
    }
}
