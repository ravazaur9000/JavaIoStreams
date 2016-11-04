package primenumbers;

import java.util.*;
class Score {
    List<Integer> randoms = Collections.synchronizedList(new ArrayList());
    List<Integer> outputs = Collections.synchronizedList(new ArrayList());
}

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Random rand = new Random();
        Score score=new Score();

        for (int i = 0; i < 50; i++) {
            score.randoms.add(rand.nextInt(20));
        }
        int N = 1; //NUMBER OF THREADS
        for (int i = 0; i < N; i++) {// i e threadid-ul
            for (int j = i*score.randoms.size()/N; j < (score.randoms.size() * (i+1)) / N; j++) {
                //System.out.println(i+" : " + randoms.get(j));
            }
        }
        MyThread[] threads = new MyThread[N];

        for (int i = 0; i < N; i++) {

            threads[i]=new MyThread(score,i,N);
            threads[i].start();
        }
        for (int i = 0; i < N; i++) {
            threads[i].join();
        }


        System.out.println(score.outputs);


    }



}
