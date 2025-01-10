package org.example;



import org.example.beans.Course;
import org.example.beans.Lector;
import org.example.beans.LectorType;
import org.example.beans.Student;
import org.example.services.UniManagementImpl;

import java.util.ArrayList;
import java.util.List;

public class ArgumentParser {
    private String[] args;
    public static final String CREATE_STUDENT = "createStudent";
    public static final String CREATE_COURSE = "createCourse";
    public static final String CREATE_ASSISTANCE = "createAssistance";
    public static final String CREATE_PROFESSOR = "createProfessor";
    public static final String ASSIGN_STUDENT_TO_COURSE = "assignStudentToCourse";
    public static final String ASSIGN_STUDENTS_TO_COURSE = "assignStudentsToCourse";
    public static final String ASSIGN_ASSISTANCE_TO_COURSE = "assignAssistanceToCourse";
    public static final String ASSIGN_PROFESSOR_TO_COURSE = "assignProfessorToCourse";
    public static final String DELETE_COURSE = "deleteCourse";
    public static final String DELETE_STUDENT = "deleteStudent";
    public static final String DELETE_ASSISTANT = "deleteAssistant";
    public static final String DELETE_PROFESSOR = "deleteProfessor";
    public static final String REMOVE_STUDENT_FROM_COURSE="removeStudentFromCourse";

    //Added Extra Methods For Debugging Purposes
    public static final String GET_PROFESSOR_INFO="getProfessorInfo";
    public static final String GET_STUDENT_INFO="getStudentInfo";
    public static final String GET_COURSE_INFO ="getCourseInfo";
    public static final String GET_ASSISTANT_INFO ="getAssistantInfo";



    private final UniManagementImpl uniManagement;

    public ArgumentParser(UniManagementImpl uniManagement) {
        this.uniManagement = uniManagement;
    }

    public void parseCommand(String command){
        args=command.split(" ");
        executeCommand();
    }

    private void executeCommand(){
        try{
            switch(args[0]){
                case CREATE_STUDENT:
                {
                    Student stud=uniManagement.createStudent(Integer.parseInt(args[1]),args[2],args[3],args[4]);
                    System.out.println("The Student Has Been Created: "+stud.toString());
                }
                    break;

                case CREATE_COURSE:
                {
                    Course course = uniManagement.createCourse(args[1]);
                    System.out.println("The Course Has Been Created: "+course.toString());
                }
                    break;

                case CREATE_ASSISTANCE:
                {
                    Lector assistant =uniManagement.createAssistance(Integer.parseInt(args[1]),args[2],args[3]);
                    System.out.println("The Assistant Has Been Created: "+assistant.toString());
                }
                    break;

                case CREATE_PROFESSOR:
                {
                    Lector professor=uniManagement.createProfessor(Integer.parseInt(args[1]),args[2],args[3], LectorType.valueOf(args[4].toUpperCase()));
                    System.out.println("The Professor Has Been Created: "+professor.toString());
                }
                    break;

                case ASSIGN_STUDENT_TO_COURSE:
                //I wanted to keep the variable scope local so used braces
                {
                    Student stud=uniManagement.getStudentById(Integer.parseInt(args[1]));
                    Course course=uniManagement.getCourseByName(args[2]);
                    uniManagement.addStudentToCourse(stud,course);
                    System.out.println("The Student Has Been Assigned To The Course");
                }
                    break;

                case ASSIGN_ASSISTANCE_TO_COURSE:
                {
                    Lector assistant=uniManagement.getAssistantById(Integer.parseInt(args[1]));
                    Course course=uniManagement.getCourseByName(args[2]);
                    uniManagement.assignAssistanceToCourse(assistant,course);
                    System.out.println("The Assistant Has Been Assigned To The Course");
                }
                    break;

                case ASSIGN_PROFESSOR_TO_COURSE:
                {
                    Lector lector=uniManagement.getProfessorById(Integer.parseInt(args[1]));
                    Course course=uniManagement.getCourseByName(args[2]);
                    uniManagement.assignProfessorToCourse(lector,course);
                }
                    break;
                case GET_COURSE_INFO:
                {
                    Course course=uniManagement.getCourseByName(args[1]);
                    course.showInfo();
                }
                    break;

                case GET_STUDENT_INFO:
                {
                    Student stud=uniManagement.getStudentById(Integer.parseInt(args[1]));
                    stud.showInfo();
                }
                break;

                case GET_PROFESSOR_INFO:
                {
                    Lector lector=uniManagement.getProfessorById(Integer.parseInt(args[1]));
                    lector.showInfo();
                }
                break;

                case GET_ASSISTANT_INFO:
                {
                    Lector assistant=uniManagement.getAssistantById(Integer.parseInt(args[1]));
                    assistant.showInfo();
                }
                break;

                case DELETE_COURSE:
                    uniManagement.deleteCourse(args[1]);
                break;

                case DELETE_ASSISTANT:
                    uniManagement.deleteAssistance(Integer.parseInt(args[1]));
                break;

                case DELETE_STUDENT:
                    uniManagement.deleteStudent(Integer.parseInt(args[1]));
                break;

                case DELETE_PROFESSOR:
                    uniManagement.deleteProfessor(Integer.parseInt(args[1]));
                break;

                case REMOVE_STUDENT_FROM_COURSE:
                {
                    Student stud=uniManagement.getStudentById(Integer.parseInt(args[1]));
                    Course course=uniManagement.getCourseByName(args[2]);
                    uniManagement.removeStudentFromCourse(stud,course);
                }
                break;

                case ASSIGN_STUDENTS_TO_COURSE:
                {
                    Course course=uniManagement.getCourseByName(args[1]);
                    List<Student> students=new ArrayList<>();
                    for(String s:args){
                        Student stud=uniManagement.getStudentById(Integer.parseInt(s));
                        students.add(stud);
                    }
                    uniManagement.addStudentsToCourse(students,course);
                }

                break;


                default:
                    System.out.println("Invalid Method: "+args[0]);




            }

        }catch(Exception e){
            System.out.println(e);
        }
    }
}
