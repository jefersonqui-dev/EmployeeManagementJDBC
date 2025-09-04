package com.devsenior.jquiguantar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import com.devsenior.jquiguantar.dao.EmployeeDao;
import com.devsenior.jquiguantar.vo.Employee;

public class Main {
    public static void main(String[] args) {
        var employee = new Employee();

        employee.setFirstName("Jefferson");
        employee.setLastName("Quiguantar");
        employee.setEmail("Jefferson .quiguantar@ejemplo.com");
        employee.setPhoneNumber("0999999999");
        employee.setJobId(1);
        employee.setSalary(1500);
        employee.setManagerId("100");
        employee.setDepartmentId(10);
        employee.setHirDate(LocalDate.of(2025, 1, 1));
        var employeeDao = new EmployeeDao();
        // employeeDao.saveEmployee(employee);
        // employee.setLastName("Diaz Arriera");
        // employee.setPhoneNumber("3207665432");
        // employeeDao.updateEmployee(1, employee);

        // employeeDao.deleteEmployee(34);

        // employeeDao.finAllEmployees().forEach(System.out::println);

        employeeDao.findEmployeeById(1);

    }

    public static void testDataBaseConnection() {

        var employees = new ArrayList<Employee>();

        // Driver manager que nos de una conexion a la base de datos
        var url = "jdbc:postgresql://localhost:5432/RH";
        var user = "postgres";
        var pass = "";

        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexion exitosa");

            // Preparar una sentencia para lanzar una instruccion sql a la bd

            Statement stmt = conn.createStatement();
            // stml.execute("DELETE FROM employees WHERE employee_id = 999");
            ResultSet rset = stmt.executeQuery("SELECT employee_id as id, email, salary, first_name FROM employees");

            while (rset.next()) {
                var employee = new Employee();
                employee.setEmployeeId(rset.getInt("id"));
                employee.setFirstName(rset.getString("first_name"));
                employee.setEmail(rset.getString("email"));
                employee.setSalary(rset.getInt("salary"));
                employees.add(employee);
                employees.forEach(System.out::println);
            }

            // Cerrar conexiones (flujos)
            rset.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.err.println("Error al conectarse a la base de datos");
        }
    }
}