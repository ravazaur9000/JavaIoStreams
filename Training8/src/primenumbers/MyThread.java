package primenumbers;

/**
 * Created by admarcu on 11/3/2016.
 */


public class MyThread extends Thread {
    Score s;
    int id;
    int N;

    public MyThread(Score s,int id,int N) {
        this.s=s;
        this.id=id;
        this.N=N;

    }
    @Override
    public void run() {

        for (int j = id*s.randoms.size()/N; j < (s.randoms.size() * (id+1)) / N; j++) {
            //System.out.println(isPrime(input.get(j)));
            if(isPrime(s.randoms.get(j))){
                //System.out.println(s.randoms.get(j));
                synchronized (s.outputs){
                    s.outputs.add(s.randoms.get(j));
                }
            }
        }
    }


    private static boolean isPrime(int n) {
        if(n==0){
            return false;
        }
        for(int i=2;i<n;i++) {
            if(n%i==0)
                return false;
        }
        return true;
    }
}
