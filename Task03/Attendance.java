import java.util.*;
class Student {
    private final String regNo, name;
    Student(String regNo,String name){this.regNo=regNo;this.name=name;}
    @Override public boolean equals(Object o){
        if(this==o)return true;
        if(!(o instanceof Student))return false;
        return regNo.equals(((Student)o).regNo);
    }
    // Correct hashCode: equal students (same regNo) must have same hash.
    @Override public int hashCode(){return Objects.hash(regNo);}
    @Override public String toString(){return regNo+" "+name;}
}
public class Attendance {
    public static void main(String[] args){
        Set<Student> scans=new HashSet<>();
        scans.add(new Student("192311001","Arun"));
        scans.add(new Student("192311002","Divya"));
        scans.add(new Student("192311001","Arun"));
        System.out.println("Corrected unique students: "+scans.size());
        Set<Student> sameBucket=new HashSet<>();
        sameBucket.add(new Student("FB","One"));
        sameBucket.add(new Student("Ea","Two"));
        System.out.println("Distinct IDs retained: "+sameBucket.size());
        Map<String,Student> byId=new HashMap<>();
        byId.put("192311001",new Student("192311001","Arun"));
        byId.put("192311001",new Student("192311001","Arun updated"));
        System.out.println("Map de-duplication size: "+byId.size());
        // Mutable-key warning: fields used in hashCode must not change while stored.
    }
}
