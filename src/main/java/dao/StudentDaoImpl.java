package dao;

import domain.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class StudentDaoImpl extends BaseDaoImpl {

    
    public List<Student> getStudents() throws SQLException {
        String sql = "SELECT id, surname, name, study_year FROM students";
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getLong("id"));
                student.setSurname(resultSet.getString("surname"));
                student.setName(resultSet.getString("name"));
                student.setStudyYear(resultSet.getInt("study_year"));
                students.add(student);
            }
            return students;
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
            	returnConnection(connection);
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    
    public List<Student> getBySurname(String surname) throws SQLException {
        String sql = "SELECT id, surname, name, study_year FROM students WHERE surname = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, surname);
            resultSet = statement.executeQuery();
            List<Student> studentList = new ArrayList<>();
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getLong("id"));
                student.setSurname(resultSet.getString("surname"));
                student.setName(resultSet.getString("name"));
                student.setStudyYear(resultSet.getInt("study_year"));
                studentList.add(student);
            }
            return studentList;
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
            	returnConnection(connection);
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    
    public Long create(Student student) throws SQLException {
        String sql = "INSERT INTO students (id, surname, name, study_year, blocked) VALUES (?, ?, ?, ?, false)";
        PreparedStatement statement = null;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql, statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, student.getId());
            statement.setString(2, student.getSurname());
            statement.setString(3, student.getName());
            statement.setLong(4, student.getStudyYear());
            statement.executeUpdate();
            return student.getId();
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
            	returnConnection(connection);
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    
    public Student read(Long id) throws SQLException {
        String sql = "SELECT id, surname, name, study_year FROM students WHERE id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Student student = null;
            if (resultSet.next()) {
                student = new Student();
                student.setId(resultSet.getLong("id"));
                student.setSurname(resultSet.getString("surname"));
                student.setName(resultSet.getString("name"));
                student.setStudyYear(resultSet.getInt("study_year"));
            }
            return student;
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
            	returnConnection(connection);
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                resultSet.close();
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
    }


    public void update(Student student) throws SQLException {
        String sql = "UPDATE students SET surname = ?, name = ?, study_year = ? WHERE id = ?";
        PreparedStatement statement = null;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, student.getSurname());
            statement.setString(2, student.getName());
            statement.setInt(3, student.getStudyYear());
            statement.setLong(4, student.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
            	returnConnection(connection);
                statement.close();
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
    }
    
    
    public void setUnblock(Long id) throws SQLException {
        String sql = "UPDATE students SET blocked = false WHERE id = ?";
        PreparedStatement statement = null;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
            	returnConnection(connection);
                statement.close();
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
    }
    
    
    public void setBlock(Long id) throws SQLException {
        String sql = "UPDATE students SET blocked = true WHERE id = ?";
        PreparedStatement statement = null;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
            	returnConnection(connection);
                statement.close();
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
    }
    
    
	public List<Student> getBlockedStudents() throws SQLException {
		String sql = "SELECT id, surname, name, study_year FROM students WHERE blocked = true";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            List<Student> studentList = new ArrayList<>();
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getLong("id"));
                student.setSurname(resultSet.getString("surname"));
                student.setName(resultSet.getString("name"));
                student.setStudyYear(resultSet.getInt("study_year"));
                studentList.add(student);
            }
            return studentList;
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
            	returnConnection(connection);
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}

	
	public List<Student> getUnblockedStudents() throws SQLException {
		String sql = "SELECT id, surname, name, study_year FROM students WHERE blocked = false";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            List<Student> studentList = new ArrayList<>();
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getLong("id"));
                student.setSurname(resultSet.getString("surname"));
                student.setName(resultSet.getString("name"));
                student.setStudyYear(resultSet.getInt("study_year"));
                studentList.add(student);
            }
            return studentList;
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
            	returnConnection(connection);
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}

	
	public boolean isBlocked(Long id) throws SQLException {
		String sql = "SELECT blocked FROM students WHERE id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        boolean result = true;
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getBoolean(1);
            }
            return result;
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
            	returnConnection(connection);
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}
}
