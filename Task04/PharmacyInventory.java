import java.util.*;
public class PharmacyInventory {
    static volatile int sink;
    static long lookups(Map<String,Integer> m,List<String> keys){
        long s=System.nanoTime();int x=0;
        for(int r=0;r<100000;r++)x+=m.getOrDefault(keys.get(r%keys.size()),0);
        sink=x;return System.nanoTime()-s;
    }
    public static void main(String[] args){
        int[] sizes={1000,10000,50000};
        for(int n:sizes){
            Map<String,Integer> h=new HashMap<>(), t=new TreeMap<>(), linked=new LinkedHashMap<>();
            List<String> keys=new ArrayList<>();
            for(int i=0;i<n;i++){String k=String.format("MED%06d",i);keys.add(k);h.put(k,i);t.put(k,i);linked.put(k,i);}
            System.out.println("\nMedicines="+n);
            System.out.printf("100k_get_ms HashMap=%.3f TreeMap=%.3f LinkedHashMap=%.3f%n",
                lookups(h,keys)/1e6,lookups(t,keys)/1e6,lookups(linked,keys)/1e6);
            long s=System.nanoTime();String first="";for(String k:t.keySet())first=k;
            long tree=System.nanoTime()-s;
            s=System.nanoTime();for(String k:linked.keySet())first=k;
            long insertion=System.nanoTime()-s;
            System.out.printf("ordered_iteration_ms TreeMap=%.3f LinkedHashMap=%.3f%n",tree/1e6,insertion/1e6);
            System.out.println("TreeMap first/last="+t.keySet().iterator().next()+"/"+((TreeMap<String,Integer>)t).lastKey());
            System.out.println("LinkedHashMap first="+linked.keySet().iterator().next());
        }
    }
}
