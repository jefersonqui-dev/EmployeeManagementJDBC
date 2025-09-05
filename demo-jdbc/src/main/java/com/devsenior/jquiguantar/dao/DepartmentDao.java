package com.devsenior.jquiguantar.dao;

import java.util.List;

import com.devsenior.jquiguantar.vo.Department;

public class DepartmentDao extends AbstractDao<Department, Integer> {
    @Override
    public void save(Department deparment) {
        try (var conn = getConnection();
                var stmt = conn.prepareStatement("INSERT INTO departments VALUES(1,?,?);")) {
            stmt.setString(1, deparment.getName());
            stmt.setInt(2, deparment.getLocationId());
            stmt.execute();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public void update(Integer id, Department value) {
        throw new UnsupportedOperationException("Unimplement method 'update'");

    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Department> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Department findById(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

}
