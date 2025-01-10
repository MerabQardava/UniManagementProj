package org.example.services;



import org.example.Exceptions.CourseNotFoundException;
import org.example.Exceptions.ProfessorNotFoundException;
import org.example.Exceptions.StudentNotFoundException;
import org.example.beans.Course;
import org.example.beans.Lector;
import org.example.beans.LectorType;
import org.example.beans.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class UniManagementImpl implements UniManagement {
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
                for(Student s: c.getStudents()){
                    s.delCourse(c);
                }
                if(c.getLector()!=null){
                    c.getLector().delCourse(c);
                }
                if(c.getAssistance()!=null){
                    c.getAssistance().delCourse(c);
                }
                courses.remove(c);
                System.out.println("The Course "+c.getName()+" Has Been Deleted");
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
                for(Course c:s.getCourses()){
                    c.delStudent(s);
                }
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
                for(Course c:s.getCourses()){
                    c.setAssistance(null);
                }
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
            System.out.println("Could Not Assign A Professor To The Course: Too Many Courses");
            return false;
        }else{
            if(course.getLector()!=null){
                course.getLector().getCourses().remove(course);
            }
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
        return course.delStudent(student) && student.delCourse(course);
    }

    public Lector createProfessor(int id, String firstName, String lastName, LectorType type) {
        for(Lector l : lectors) {
            if(l.getId() == id) {
                return null;
            }
        }
        Lector newProfessor = new Lector(id, firstName, lastName, type);
        lectors.add(newProfessor);
        return newProfessor;
    }

    public boolean deleteProfessor(int id) {
        for(Lector l : lectors) {
            if(l.getId() == id) {
                for(Course c:l.getCourses()){
                    c.setAssistance(null);
                }
                lectors.remove(l);
                return true;
            }
        }
        return false;
    }

    public Student getStudentById(int id) throws StudentNotFoundException {
        for(Student s:students){
            if(s.getId()==id){
                return s;
            }
        }
        throw new StudentNotFoundException("The Student With The Given  Id Was Not Found In Our Database");
    }

    public Course getCourseByName(String name) throws CourseNotFoundException {
        for(Course c:courses){
            if(c.getName().equalsIgnoreCase(name)){
                return c;
            }
        }
        throw new CourseNotFoundException("The Course With The Given Id Was Not Found In Our Database");
    }

    public Lector getAssistantById(int id) throws ProfessorNotFoundException {
        for(Lector l:assistants){
            if(l.getId()==id){
                return l;
            }
        }
        throw new ProfessorNotFoundException("The Professor With The Given Id Was Not Found In Our Database");
    }

    public Lector getProfessorById(int id) throws ProfessorNotFoundException {
        for(Lector l:lectors){
            if(l.getId()==id){
                return l;
            }
        }
        throw new ProfessorNotFoundException("The Professor With The Given Id Was Not Found In Our Database");
    }

    //For Debugging
    @Override
    public String toString() {
        return "UniManagementImpl{" +
                "numberOfStudents=" + students.size() +
                ", numberOfCourses=" + courses.size() +
                ", numberOfAssistants=" + assistants.size() +
                ", numberOfLectors=" + lectors.size() +
                '}';
    }

}
