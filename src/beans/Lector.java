package beans;

import Exceptions.CourseNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class Lector extends User {
    private final LectorType lectorType;
    private final List<Course> courses;

    public Lector(int id, String firstName, String lastName,LectorType type) {
        super(id, firstName, lastName);
        this.lectorType=type;
        courses=new ArrayList<>();
    }

    public Lector(int id, String firstName,String lastName){
        super(id,firstName,lastName);
        this.lectorType=LectorType.ASSISTANT;
        courses=new ArrayList<>();
    }

    public boolean addCourse(Course course){
        if(isFull()){
            System.out.println("The course could not be added: The Lector is already assigned to this course");
            return false;
        }

        if(courses.size()<4){
            courses.add(course);
            System.out.println("The Course Has Been Added");
            return true;
        }
        System.out.println("The Course Could Not Be Added");
        return false;
    }

    public boolean delCourse(Course course) throws CourseNotFoundException {
        if(courses.contains(course)){

            courses.remove(course);
            System.out.println("The Course Has Been Removed");
            return true;
        }
        throw new CourseNotFoundException("The Course Could No Be Found");
    }

    public boolean isFull() {
        return courses.size()>=4;
    }

    public List<Course> getCourses(){
        return courses;
    }

    //For Debugging
    @Override
    public String toString() {
        return "Lector{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", lectorType=" + lectorType +
                ", numberOfCourses=" + (courses != null ? courses.size() : 0) +
                '}';
    }

    public void showInfo() {
        System.out.println("Full Name: "+this.firstName+" "+this.lastName);
        System.out.println("LectorType: " + lectorType);
        System.out.print("Courses: ");
        for (Course course : courses) {
            System.out.print(course.getName() + " ");
        }
        System.out.println();
    }


}

