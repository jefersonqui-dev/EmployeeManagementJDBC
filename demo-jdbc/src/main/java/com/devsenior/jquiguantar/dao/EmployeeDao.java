package com.devsenior.jquiguantar.dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.devsenior.jquiguantar.vo.Employee;

public class EmployeeDao {

    public void saveEmployee(Employee employee) {
        var url = "jdbc:postgresql://localhost:5432/RH";
        var user = "postgres";
        var pass = "";

        try {
            var conn = DriverManager.getConnection(url, user, pass);
            var stmt = conn.createStatement();

            var format = """
                        INSERT INTO employees (employee_id, first_name, last_name, email, phone_number, hire_date, job_id, salary, manager_id, department_id)
                            VALUES(nextval('employees_employee_id_seq'::regclass), '%s', '%s', '%s', '%s', '%s', %d, %d, '%s', %d)
                    """;
            var sql = String.format(format, employee.getFirstName(), employee.getLastName(),
                    employee.getEmail(), employee.getPhoneNumber(), "2025-01-03", // employee.getHirDate(),
                    employee.getJobId(), employee.getSalary(), employee.getManagerId(),
                    employee.getDepartmentId());

            System.out.println(sql);
            stmt.execute(sql);

            stmt.close();
            conn.close();
            System.out.println("Empleado Guardado Exitosamente");
        } catch (SQLException e) {
            System.err.println("Error al Guardar el Usuario: " + e.getMessage());
        }

    }

    public void updateEmployee(Integer id, Employee employee) {
        var url = "jdbc:postgresql://localhost:5432/RH";
        var user = "postgres";
        var pass = "";

        try (var conn = DriverManager.getConnection(url, user, pass);
                var stmt = conn.createStatement()) {

            var format = """
                    update employees
                    set first_name = '%s',
                        last_name = '%s',
                        email = '%s',
                        phone_number = '%s',
                        job_id = %d,
                        salary = %d,
                        manager_id = '%s',
                        department_id = %d
                    where employee_id = %d
                    """;
            var sql = String.format(format, employee.getFirstName(), employee.getLastName(),
                    employee.getEmail(), employee.getPhoneNumber(), // employee.getHirDate(),
                    employee.getJobId(), employee.getSalary(), employee.getManagerId(),
                    employee.getDepartmentId(), id);

            System.out.println(sql);
            stmt.execute(sql);

            // stmt.close();
            // conn.close();
            System.out.println("Empleado Actualizado Exitosamente");
        } catch (SQLException e) {
            System.err.println("Error al Actualizar el Usuario: " + e.getMessage());
        }
    }

    public void deleteEmployee(Integer id) {
        var url = "jdbc:postgresql://localhost:5432/RH";
        var user = "postgres";
        var pass = "";

        try (var conn = DriverManager.getConnection(url, user, pass);
                var stmt = conn.prepareStatement("delete from employees where employee_id = ?")) {

            stmt.setInt(1, id);

            stmt.execute();

            // stmt.close();
            // conn.close();
            System.out.println("Empleado Eliminado Exitosamente");
        } catch (SQLException e) {
            System.err.println("Error al Eliminar el Usuario: " + e.getMessage());
        }
    }

    public List<Employee> finAllEmployees() {
        var employees = new ArrayList<Employee>();
        var url = "jdbc:postgresql://localhost:5432/RH";
        var user = "postgres";
        var pass = "Ganimedes5.-";

        try (var conn = DriverManager.getConnection(url, user, pass);
                var stmt = conn.prepareStatement("SELECT * from EMPLOYEES");
                var rset = stmt.executeQuery()) {

            while (rset.next()) {
                var employee = new Employee();
                employee.setEmployeeId(rset.getInt("employee_id"));
                employee.setFirstName(rset.getString("first_name"));
                employee.setLastName(rset.getString("last_name"));
                employee.setEmail(rset.getString("email"));
                employee.setPhoneNumber(rset.getString("phone_number"));
                employee.setDepartmentId(rset.getInt("department_id"));
                employee.setManagerId(rset.getString("manager_id"));
                employee.setSalary(rset.getInt("salary"));
                employee.setJobId(rset.getInt("job_id"));

                employees.add(employee);
            }

            rset.close();

            // stmt.close();
            // conn.close();
            System.out.println("Empleado Listados Exitosamente");
        } catch (SQLException e) {
            System.err.println("Error al listar lis Usuarios: " + e.getMessage());
        }

        return employees;
    }

    public Employee findEmployeeById(Integer id) {
        var url = "jdbc:postgresql://localhost:5432/RH";
        var user = "postgres";
        var pass = "";
        Employee employee = null;

        try (var conn = DriverManager.getConnection(url, user, pass);
                var stmt = conn.prepareStatement("SELECT * FROM EMPLOYEES WHERE employee_id = ?")) {

            stmt.setInt(1, id);
            try (var rset = stmt.executeQuery()) {
                if (rset.next()) {
                    employee = new Employee();
                    employee.setEmployeeId(rset.getInt("employee_id"));
                    employee.setFirstName(rset.getString("first_name"));
                    employee.setLastName(rset.getString("last_name"));
                    employee.setEmail(rset.getString("email"));
                    employee.setPhoneNumber(rset.getString("phone_number"));
                    employee.setDepartmentId(rset.getInt("department_id"));
                    employee.setManagerId(rset.getString("manager_id"));
                    employee.setSalary(rset.getInt("salary"));
                    employee.setJobId(rset.getInt("job_id"));
                }
            }
            System.out.println("Empleado encontrado exitosamente");
        } catch (SQLException e) {
            System.err.println("Error al buscar el empleado: " + e.getMessage());
        }

        return employee;

    }

}
