import java.util.*;
public class TriageBenchmark {
    static class P { final int severity; final long arrival; P(int s,long a){severity=s;arrival=a;} }
    public static void main(String[] args){
        for(int n:new int[]{1000,10000,50000}){
            PriorityQueue<P> q=new PriorityQueue<>(Comparator.comparingInt((P p)->p.severity).thenComparingLong(p->p.arrival));
            long s=System.nanoTime();
            for(int i=0;i<n;i++)q.offer(new P((i%5)+1,i));
            long add=System.nanoTime()-s;
            s=System.nanoTime();while(!q.isEmpty())q.poll();long remove=System.nanoTime()-s;
            System.out.printf("N=%d offer_ms=%.3f poll_all_ms=%.3f%n",n,add/1e6,remove/1e6);
        }
    }
}
