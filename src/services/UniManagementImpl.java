package services;

import Exceptions.CourseNotFoundException;
import Exceptions.StudentNotFoundException;
import beans.Course;
import beans.Lector;
import beans.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class UniManagementImpl implements UniManagement{
    private final List<Student> students=new ArrayList<>();
    private final List<Course> courses=new ArrayList<>();
    private final List<Lector> assistants=new ArrayList<>();
    private List<Lector> lectors=new ArrayList<>();
    private Predicate<Course> courseExists;

    @Override
    public Course createCourse(String courseName) {
        if(courses.size()>=10){
            System.out.println("Could Not Create A Course: The Course List Is Full");
            return null;
        }

        courseExists = (course) -> course.getName().equals(courseName);

        for (Course c : courses) {
            if (courseExists.test(c)) {
                System.out.println("Could Not Create A Course: The Course Already Exists");
                return null;
            }
        }
        Course course = new Course(courseName);
        courses.add(course);
        return course;
    }

    @Override
    public boolean deleteCourse(String courseName) throws CourseNotFoundException {
        for (Course c : courses) {
            if (courseExists.test(c)) {
                courses.remove(c);
                return true;
            }
        }
        throw new CourseNotFoundException("The Course Was Not Found");
    }

    @Override
    public Student createStudent(int id, String firstName, String lastName, String facNumber) {
        if(students.size()>=1000){
            System.out.println("Could not create a student: The number of students is already >=1000");
            return null;
        }
        for(Student s:students){
            if(s.getId()==id){
                System.out.println("Could not create a student: Student with the given id already exsists.");
                return null;
            }
        }
        Student newStudent = new Student(id,firstName,lastName,facNumber);
        students.add(newStudent);
        return newStudent;
    }

    @Override
    public boolean deleteStudent(int id) throws StudentNotFoundException {
        for(Student s:students){
            if(s.getId()==id){
                students.remove(s);
                return true;
            }
        }
        throw new StudentNotFoundException("Student Not Found");
    }

    @Override
    public Lector createAssistance(int id, String firstName, String lastName) {
        for(Lector l:assistants){
            if(l.getId()==id){
                return null;
            }
        }
        Lector newAssistant = new Lector(id,firstName,lastName);
        assistants.add(newAssistant);
        return newAssistant;
    }

    @Override
    public boolean deleteAssistance(int id) {
        for(Lector s:assistants){
            if(s.getId()==id){
                assistants.remove(s);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean assignAssistanceToCourse(Lector assistance, Course course) throws CourseNotFoundException {
        if(assistance.isFull()){
            System.out.println("Could Not Assign A Assistant To The Course");
            return false;
        }else{
            assistance.addCourse(course);
            course.setAssistance(assistance);
            return true;
        }
    }

    @Override
    public boolean assignProfessorToCourse(Lector professor, Course course) {
        if(professor.isFull()){
            System.out.println("Could Not Assign A Professor To The Course");
            return false;
        }else{
            professor.addCourse(course);
            course.setLector(professor);
            return true;
        }
    }

    @Override
    public boolean addStudentToCourse(Student student, Course course) {
        if(course.isFull()||student.isFull()){
            System.out.println("Could Not Add Student To The Course");
            return false;
        }else{
            student.addCourse(course);
            course.addStudent(student);
            return true;
        }
    }

    @Override
    public boolean addStudentsToCourse(List<Student> students, Course course) {
        for(Student s:students){
            if(course.isFull()||s.isFull()){
                return false;
            };
            s.addCourse(course);
            course.addStudent(s);
        }
        return true;
    }

    @Override
    public boolean removeStudentFromCourse(Student student, Course course) throws StudentNotFoundException, CourseNotFoundException {
        return course.delStudent(student)&&student.delCourse(course);
    }

    public Lector createProfessor(int id, String firstName, String lastName) {
        for(Lector l : lectors) {
            if(l.getId() == id) {
                return null;
            }
        }
        Lector newProfessor = new Lector(id, firstName, lastName);
        lectors.add(newProfessor);
        return newProfessor;
    }

    public boolean deleteProfessor(int id) {
        for(Lector l : lectors) {
            if(l.getId() == id) {
                lectors.remove(l);
                return true;
            }
        }
        return false;
    }

}
