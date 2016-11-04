package wordappearance;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by admarcu on 11/4/2016.
 */
public class MyWordThread extends Thread {
    ArrayList<String> fileString;
    HashMap<String, Integer> appearance;
    int id;
    int N;

    public MyWordThread(ArrayList<String> fileString, HashMap<String, Integer> appearance, int id, int N) {
        this.fileString = fileString;
        this.id = id;
        this.N = N;
        this.appearance = appearance;
    }

    public void run() {
        for (int j = id * fileString.size() / N; j < (fileString.size() * (id + 1)) / N; j++) {
            if(appearance.get(fileString.get(j)) != null){
                synchronized (appearance) {
                    appearance.replace(fileString.get(j), appearance.get(fileString.get(j)) + 1);
                }
            }
            else{
                synchronized (appearance){
                    appearance.put(fileString.get(j),1);
                }

            }
            //System.out.println(isPrime(input.get(j)));

        }
        System.out.println(this.id);
    }
}
