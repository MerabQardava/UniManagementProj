package beans;

import Exceptions.StudentNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Course {
    private String name;
    private final List<Student> students;
    private Lector assistance;
    private Lector lector;

    public Course(String name){
        this.name=name;
        this.students=new ArrayList<>();
    }

    public Course(String name, Lector lector, Lector assistance){
        this.name=name;
        this.lector=lector;
        if(lector!=null){lector.addCourse(this);}
        this.assistance=assistance;
        if (assistance != null) { assistance.addCourse(this); }
        this.students=new ArrayList<>();
    }

    public boolean addStudent(Student student){
        if(students.contains(student)){
            System.out.println("The Student Could Not Be Added: This Student Is Already Assigned To The Course");
            return false;
        }

        if(!isFull()){
            students.add(student);
            System.out.println("The Student Has Been Added");
            return true;
        }else{
            System.out.println("The Student Could Not Be Added");
            return false;
        }
    }

    public boolean delStudent(Student student) throws StudentNotFoundException {
        if(students.contains(student)){
            students.remove(student);
            System.out.println("The Student Has Been Deleted");
            return true;
        }else{
            throw new StudentNotFoundException("The Student Could Not Be Found");
        }
    }


    public void setAssistance(Lector assistance) {
        this.assistance = assistance;
    }

    public void setLector(Lector lector) {
        this.lector = lector;
    }

    public List<Student> getStudents(){
        return students;
    }

    public String getName() {
        return name;
    }

    public boolean isFull(){
        return students.size()>=30;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(name, course.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}