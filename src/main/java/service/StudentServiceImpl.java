package service;

import dao.StudentDaoImpl;
import domain.Student;

import java.sql.SQLException;
import java.util.List;


public class StudentServiceImpl {
    private StudentDaoImpl studentDao = new StudentDaoImpl();

    
    public List<Student> findAll() throws SQLException {
        try {
            return studentDao.getStudents();
        } catch (SQLException e) {
            throw e;
        }
    }

    
    public Student findById(Long id) throws SQLException {
        try {
            return studentDao.read(id);
        } catch (SQLException e) {
            throw e;
        }
    }

    
    public Long create(Student student) throws SQLException {
        try {
            return studentDao.create(student);
        } catch (SQLException e) {
            throw e;
        }
    }
    
    
	public List<Student> getBlockedStudents() throws SQLException {
		try {
            return studentDao.getBlockedStudents();
        } catch (SQLException e) {
            throw e;
        }
	}

	
	public List<Student> getUnblockedStudents() throws SQLException {
		try {
            return studentDao.getUnblockedStudents();
        } catch (SQLException e) {
            throw e;
        }
	}

	
	public void setBlock(Long studentId) throws SQLException {
		try {
			studentDao.setBlock(studentId);
		} catch (SQLException e) {
			throw e;
		}
		
	}
	
	
	public void setUnblock(Long studentId) throws SQLException {
		try {
			studentDao.setUnblock(studentId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	public boolean isBlocked(Long id) throws SQLException {
		try {
			return studentDao.isBlocked(id);
		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		}
	}
}
