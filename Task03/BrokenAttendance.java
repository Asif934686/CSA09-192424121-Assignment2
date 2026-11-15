import java.util.*;
class BrokenStudent {
    final String regNo;
    BrokenStudent(String regNo){this.regNo=regNo;}
    public boolean equals(Object o){return o instanceof BrokenStudent && regNo.equals(((BrokenStudent)o).regNo);}
    // Deliberately missing hashCode(): this is the defect under investigation.
}
public class BrokenAttendance {
    public static void main(String[] args){
        Set<BrokenStudent> scans=new HashSet<>();
        scans.add(new BrokenStudent("192311001"));
        scans.add(new BrokenStudent("192311002"));
        scans.add(new BrokenStudent("192311001"));
        System.out.println("Broken implementation set size (JVM-dependent): "+scans.size());
    }
}
