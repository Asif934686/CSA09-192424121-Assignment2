import java.util.*;
public class UpNextRedesign{
    static long arrayListFront(int n){
        List<Integer> list=new ArrayList<>();
        for(int i=0;i<n;i++)list.add(i);
        long s=System.nanoTime();for(int i=0;i<n;i++)list.add(0,-i);
        return System.nanoTime()-s;
    }
    static long dequeEnds(int n){
        Deque<Integer> q=new ArrayDeque<>();
        for(int i=0;i<n;i++)q.addLast(i);
        long s=System.nanoTime();for(int i=0;i<n;i++)q.addFirst(-i);
        return System.nanoTime()-s;
    }
    public static void main(String[] args){
        for(int n:new int[]{1000,10000,50000}){
            long old=arrayListFront(n), revised=dequeEnds(n);
            System.out.printf("N=%d ArrayList_front_ms=%.3f ArrayDeque_end_ms=%.3f%n",n,old/1e6,revised/1e6);
        }
        Deque<String> upNext=new ArrayDeque<>();
        upNext.addLast("Song B");upNext.addFirst("Urgent Song A");
        System.out.println("Next="+upNext.removeFirst());
    }
}
