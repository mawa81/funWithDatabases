package com.codersbay.databases;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        TeacherDAO teacherDAO = new TeacherDAO();
        List<Teacher> teachers = teacherDAO.getAllTeachers();

        System.out.println("##### All Teachers");

        for (Teacher teacher : teachers) {
            System.out.println(teacher.toString());
        }

        System.out.println("##### All Teachers?");

        Teacher someTeacher = teacherDAO.getTeacher(1001);

        System.out.println(someTeacher);

        Teacher someOtherTeacher = teacherDAO.getTeacher(1012);
        System.out.println(someOtherTeacher);

        teacherDAO.createTeacher(new Teacher(1, "Albus", "Dumbledore", 1));
        List<Teacher> allTeachers = teacherDAO.getAllTeachers();
        System.out.println("#### ALL TEACHERS AFTER INSERT");

        for (Teacher teacher : allTeachers) {
            System.out.println(teacher.toString());
        }

        teacherDAO.deleteTeacher(1);
        allTeachers = teacherDAO.getAllTeachers();
        System.out.println("#### ALL TEACHERS AFTER DELETE");

        for (Teacher teacher : allTeachers) {
            System.out.println(teacher.toString());
        }
    }

}
