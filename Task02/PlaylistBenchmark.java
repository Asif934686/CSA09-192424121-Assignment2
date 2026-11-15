import java.util.*;
public class PlaylistBenchmark {
    static volatile long sink;
    static ArrayList<Integer> array(int n){ArrayList<Integer>a=new ArrayList<>(n);for(int i=0;i<n;i++)a.add(i);return a;}
    static LinkedList<Integer> linked(int n){LinkedList<Integer>a=new LinkedList<>();for(int i=0;i<n;i++)a.add(i);return a;}
    static long frontArray(ArrayList<Integer>a,int reps){long s=System.nanoTime();for(int i=0;i<reps;i++)a.add(0,-i);return System.nanoTime()-s;}
    static long frontLinked(LinkedList<Integer>a,int reps){long s=System.nanoTime();for(int i=0;i<reps;i++)a.add(0,-i);return System.nanoTime()-s;}
    static long getArray(ArrayList<Integer>a,int reps,int index){long s=System.nanoTime(),x=0;for(int i=0;i<reps;i++)x+=a.get(index);sink=x;return System.nanoTime()-s;}
    static long getLinked(LinkedList<Integer>a,int reps,int index){long s=System.nanoTime(),x=0;for(int i=0;i<reps;i++)x+=a.get(index);sink=x;return System.nanoTime()-s;}
    static long iterateArray(ArrayList<Integer>a){long s=System.nanoTime(),x=0;for(int v:a)x+=v;sink=x;return System.nanoTime()-s;}
    static long iterateLinked(LinkedList<Integer>a){long s=System.nanoTime(),x=0;for(int v:a)x+=v;sink=x;return System.nanoTime()-s;}
    static long appendArray(ArrayList<Integer>a,int reps){long s=System.nanoTime();for(int i=0;i<reps;i++)a.add(i);return System.nanoTime()-s;}
    static long appendLinked(LinkedList<Integer>a,int reps){long s=System.nanoTime();for(int i=0;i<reps;i++)a.add(i);return System.nanoTime()-s;}
    static long middleEditArray(ArrayList<Integer>a,int reps,int index){long s=System.nanoTime();for(int i=0;i<reps;i++){a.add(index,i);a.remove(index);}return System.nanoTime()-s;}
    static long middleEditLinked(LinkedList<Integer>a,int reps,int index){long s=System.nanoTime();for(int i=0;i<reps;i++){a.add(index,i);a.remove(index);}return System.nanoTime()-s;}
    static long frontRemoveArray(ArrayList<Integer>a,int reps){long s=System.nanoTime();for(int i=0;i<reps;i++)a.remove(0);return System.nanoTime()-s;}
    static long frontRemoveLinked(LinkedList<Integer>a,int reps){long s=System.nanoTime();for(int i=0;i<reps;i++)a.remove(0);return System.nanoTime()-s;}
    public static void main(String[] args){
        int[] sizes={1000,10000,50000};
        for(int n:sizes){
            System.out.println("\nN="+n);
            int reps=Math.min(n,5000);
            ArrayList<Integer>a=array(n); LinkedList<Integer>l=linked(n);
            long aa=frontArray(a,reps), ll=frontLinked(l,reps);
            System.out.printf("front_insert_%d ArrayList_ms=%.3f LinkedList_ms=%.3f%n",reps,aa/1e6,ll/1e6);
            a=array(n);l=linked(n);
            long ar=getArray(a,reps,n/2),lr=getLinked(l,reps,n/2);
            System.out.printf("middle_get_%d ArrayList_ms=%.3f LinkedList_ms=%.3f%n",reps,ar/1e6,lr/1e6);
            a=array(n);l=linked(n);
            long ai=iterateArray(a),li=iterateLinked(l);
            System.out.printf("iterate ArrayList_ms=%.3f LinkedList_ms=%.3f%n",ai/1e6,li/1e6);
            long ae=appendArray(a,reps),le=appendLinked(l,reps);
            System.out.printf("append_%d ArrayList_ms=%.3f LinkedList_ms=%.3f%n",reps,ae/1e6,le/1e6);
            a=array(n);l=linked(n); int edits=Math.min(1000,n/2);
            long ame=middleEditArray(a,edits,n/2),lme=middleEditLinked(l,edits,n/2);
            System.out.printf("middle_insert_remove_%d ArrayList_ms=%.3f LinkedList_ms=%.3f%n",edits,ame/1e6,lme/1e6);
            a=array(n);l=linked(n);
            long afr=frontRemoveArray(a,edits),lfr=frontRemoveLinked(l,edits);
            System.out.printf("front_remove_%d ArrayList_ms=%.3f LinkedList_ms=%.3f%n",edits,afr/1e6,lfr/1e6);
        }
        ArrayDeque<Integer> upNext=new ArrayDeque<>();
        upNext.addFirst(2);upNext.addLast(3);upNext.addFirst(1);
        System.out.println("UpNext dequeue="+upNext.removeFirst()+","+upNext.removeFirst()+","+upNext.removeFirst());
    }
}
