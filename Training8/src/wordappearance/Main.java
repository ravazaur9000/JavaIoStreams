package wordappearance;

import com.sun.javafx.scene.control.skin.IntegerFieldSkin;
import javafx.util.Pair;

import java.io.*;
import java.util.*;

/**
 * Created by admarcu on 11/4/2016.
 */
public class Main {


    public static void main(String args[]) throws IOException, InterruptedException {
        System.out.println("asdas");
        inverse();
    }

    public static void inverse() throws IOException, InterruptedException {
        RandomAccessFile fl = new RandomAccessFile("text.txt", "rw");
        Scanner sc2 = null;
        try {
            sc2 = new Scanner(new File("text.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int lines = 0;
        ArrayList<String> fileString = new ArrayList<>();
        Map<String, Integer> appearance = new HashMap<String, Integer>();

        while (sc2.hasNextLine()) {
            lines++;
            int li = 0;
            Scanner s2 = new Scanner(sc2.nextLine());
            while (s2.hasNext()) {
                String s = s2.next();
                //aList.add(new Pair<String, Integer>(s, 0));
                fileString.add(s);
            }
        }
        System.out.println(fileString);

        int N = 1;
        MyWordThread[] threads = new MyWordThread[N];

        for (int i = 0; i < N; i++) {
            threads[i] = new MyWordThread(fileString, (HashMap<String, Integer>) appearance, i, N);
            threads[i].start();
        }
        for (int i = 0; i < N; i++) {
            threads[i].join();
        }
        Iterator it = appearance.entrySet().iterator();
        Writer write = new FileWriter("output.txt");
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            float percentage=(Integer)pair.getValue()*100/fileString.size();
            write.write(pair.getKey() + " " + percentage+ "%\n");
           // it.remove(); // avoids a ConcurrentModificationException
        }
        write.close();
        //System.out.println(appearance);

/*        String s = "";
        for (int i = 0; i < aList.size(); i++) {
            s += aList.get(i) + " ";
        }
        System.out.println(s);*/
        //System.out.println(lines);
/*        Writer write = new FileWriter("output.txt");
        for (Pair<String, Integer> p : aList) {
            write.write(p.getKey().toString() + " ");
            if (p.getValue() == 0) {
                write.write("\n");
            }
        }
        write.close();*/
    }

}
