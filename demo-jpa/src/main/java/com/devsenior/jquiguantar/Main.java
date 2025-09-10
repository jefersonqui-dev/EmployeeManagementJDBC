package com.devsenior.jquiguantar;

import com.devsenior.jquiguantar.dao.EmployeeDao;

public class Main {
    public static void main(String[] args) {
        var dao = new EmployeeDao();
        dao.findAll().forEach(System.out::println);
    }
}