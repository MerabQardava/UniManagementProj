package services;

import Exceptions.CourseNotFoundException;
import Exceptions.StudentNotFoundException;
import beans.Course;
import beans.Lector;
import beans.Student;

import java.util.List;

public interface UniManagement {
    public Course createCourse(String courseName);
    public boolean deleteCourse(String courseName) throws CourseNotFoundException;
    public Student createStudent(int id, String firstName, String lastName, String facNumber);
    public boolean deleteStudent(int id) throws StudentNotFoundException;
    public Lector createAssistance(int id, String firstName, String lastName);
    public boolean deleteAssistance(int id);
    public boolean assignAssistanceToCourse(Lector assistance, Course course) throws CourseNotFoundException;
    public boolean assignProfessorToCourse(Lector professor, Course course);
    public boolean addStudentToCourse(Student student, Course course);
    public boolean addStudentsToCourse(List<Student> students, Course course);
    public boolean removeStudentFromCourse(Student student, Course course) throws StudentNotFoundException, CourseNotFoundException;
}
