import java.util.*;
public class StringBenchmark {
    static volatile int sink;
    static String buildPlus(int fields) {
        String s = "";
        for (int i=0;i<fields;i++) s += "FIELD" + i + "|";
        return s;
    }
    static String buildBuilder(int fields) {
        StringBuilder s = new StringBuilder();
        for (int i=0;i<fields;i++) s.append("FIELD").append(i).append('|');
        return s.toString();
    }
    static String buildBuffer(int fields) {
        StringBuffer s = new StringBuffer();
        for (int i=0;i<fields;i++) s.append("FIELD").append(i).append('|');
        return s.toString();
    }
    static long measure(java.util.function.IntFunction<String> fn, int fields, int tickets) {
        long start=System.nanoTime();
        int checksum=0;
        for(int t=0;t<tickets;t++) checksum += fn.apply(fields).length();
        sink=checksum;
        return System.nanoTime()-start;
    }
    public static void main(String[] args) {
        int[][] workloads={{6,1},{50,10000},{200,3000}};
        String[] names={"Short ticket","High volume","Wide ticket"};
        for(int w=0;w<workloads.length;w++){
            int f=workloads[w][0], n=workloads[w][1];
            System.out.println("\n"+names[w]+" fields="+f+" tickets="+n);
            for(String method:new String[]{"String +","StringBuilder","StringBuffer"}){
                long total=0;
                for(int r=0;r<5;r++){
                    long x=switch(method){
                        case "String +" -> measure(StringBenchmark::buildPlus,f,n);
                        case "StringBuilder" -> measure(StringBenchmark::buildBuilder,f,n);
                        default -> measure(StringBenchmark::buildBuffer,f,n);
                    };
                    if(r>0) total+=x;
                }
                System.out.printf("%-14s average_ms=%.3f%n",method,total/4.0/1e6);
            }
        }
    }
}
