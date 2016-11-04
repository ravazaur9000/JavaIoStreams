import javafx.util.Pair;
import sun.reflect.generics.tree.Tree;

import java.io.*;
import java.nio.channels.Pipe;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println(countchars('a',0)); // 0 is file seek
        inverse();


        try {
            RandomAccessFile raf = new RandomAccessFile("tree.txt", "rw");
            listfiles(".", 0, raf); //search in current directory
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void listfiles(String path, int level, RandomAccessFile raf) throws IOException {
        File file = new File(path);
        File[] currentLevel = file.listFiles();
        if (currentLevel != null && currentLevel.length > 0) {
            for (File f : currentLevel) {
                for (int i = 0; i < level; i++) {
                    raf.write("\t".getBytes());
                }
                if (f.isFile()) {
                    raf.write((f.getName()+"\n").getBytes());
                } else {
                    raf.write((f.getName() + "\\\n").getBytes());
                    listfiles(f.getAbsolutePath(), level + 1, raf);
                }
            }
        }
    }


    public static void inverse() throws IOException {
        RandomAccessFile fl = new RandomAccessFile("text.txt", "rw");
        Scanner sc2 = null;
        try {
            sc2 = new Scanner(new File("text.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int lines = 0;
        //Pair<String,Boolean> pair=new Pair<String,Boolean>("asd",false);
        ArrayList<Pair<String, Boolean>> aList = new ArrayList<Pair<String, Boolean>>();
        while (sc2.hasNextLine()) {
            lines++;
            int li = 0;
            Scanner s2 = new Scanner(sc2.nextLine());
            while (s2.hasNext()) {
                String s = s2.next();
                if (li == 0) {
                    aList.add(new Pair<String, Boolean>(s, true));
                } else {
                    aList.add(new Pair<String, Boolean>(s, false));
                }

                li = 1;
            }
        }
        Collections.reverse(aList);

        String s = "";
        for (int i = 0; i < aList.size(); i++) {
            s += aList.get(i) + " ";
        }
        //System.out.println(s);
        //System.out.println(lines);
        Writer write = new FileWriter("test.txt");
        for (Pair<String, Boolean> p : aList) {
            write.write(p.getKey().toString() + " ");
            if (p.getValue() == true) {
                write.write("\n");
            }

        }
        write.close();


    }


    public static int countchars(char checker, int position) throws IOException {
        RandomAccessFile fl = new RandomAccessFile("file.txt", "rw");
        fl.seek(position);
        long pointer = fl.getFilePointer();
        Reader reader = new FileReader("file.txt");
        int count = 0;
        int a = fl.read();
        while (a != -1) {
            // char a=(char)fl.read();
            char x = (char) a;
            if (checker == x)
                count++;
            //System.out.print(x + " ");
            a = fl.read();
        }
        return count;
    }
}
