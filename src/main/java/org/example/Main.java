package org.example;



import org.example.Exceptions.CourseNotFoundException;
import org.example.services.UniManagementImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        UniManagementImpl test=new UniManagementImpl();
        ArgumentParser parser=new ArgumentParser(test);


        Scanner scan = new Scanner(System.in);

        System.out.println("Start The Application? Yes/Exit");
        String input = scan.nextLine();
        if(input.equalsIgnoreCase("yes")){
            System.out.println("Please Enter Your Next Command");
            while(true){
                input=scan.nextLine();
                if(input.equalsIgnoreCase("exit")){
                    break;
                }

                parser.parseCommand(input);



            }
        }
    }
}