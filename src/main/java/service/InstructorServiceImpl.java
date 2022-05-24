package service;

import dao.InstructorDaoImpl;
import domain.Instructor;

import java.sql.SQLException;
import java.util.List;


public class InstructorServiceImpl {
    private InstructorDaoImpl instructorDao = new InstructorDaoImpl();


    public List<Instructor> findAll() throws SQLException {
        try {
            return instructorDao.getInstructors();
        } catch (SQLException e) {
            throw e;
        }
    }


    public Instructor findById(Long id) throws SQLException {
        try {
            return instructorDao.read(id);
        } catch (SQLException e) {
            throw e;
        }
    }


    public Long create(Instructor instructor) throws SQLException {
        try {
            return instructorDao.create(instructor);
        } catch (SQLException e) {
            throw e;
        }
    }


    public List<Instructor> findBySurname(String surname) throws SQLException {
        try {
            return instructorDao.getBySurname(surname);
        } catch (SQLException e) {
            throw e;
        }
    }
}
