package org.example.beans;


import org.example.Exceptions.CourseNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Student extends User {
    private String facNumber;
    private final List<Course> courses;


    public Student(int id, String firstName, String lastName,String facNumber) {
        super(id, firstName, lastName);
        courses=new ArrayList<>();
        this.facNumber=facNumber;
    }

    public boolean addCourse(Course course){
        if(isFull()){
            System.out.println("The Course Could Not Be Added: Student Is Already Assigned To This Course");
            return false;
        }
        if(courses.size()<10){
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
        throw new CourseNotFoundException("The Course Could Not Be Found");
    }

    public List<Course> getCourses() {
        return courses;
    }

    public boolean isFull(){
        return courses.size()>=10;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return student.getId()==id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    //For Debugging
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", facNumber='" + facNumber + '\'' +
                ", numberOfCourses=" + (courses != null ? courses.size() : 0) +
                '}';
    }

    public void showInfo() {
        System.out.println("Full Name: "+this.firstName+" "+this.lastName);
        System.out.println("Faculty Number: " + facNumber);
        System.out.print("Courses: ");
        for (Course course : courses) {
            System.out.print(course.getName() + " ");
        }
        System.out.println();
    }

}
