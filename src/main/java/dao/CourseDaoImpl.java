package dao;

import domain.Course;
import domain.Instructor;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class CourseDaoImpl extends BaseDaoImpl {

    
    public List<Course> getCourses() throws SQLException {
        String sql = "SELECT id, name, hours, topic, instructor_id, begin, finish FROM courses";
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            List<Course> courses = new ArrayList<>();
            while (resultSet.next()) {
                Course course = new Course();
                course.setId(resultSet.getLong("id"));
                course.setName(resultSet.getString("name"));
                course.setHours(resultSet.getInt("hours"));
                course.setTopic(resultSet.getString("topic"));
                Date begin = resultSet.getDate("begin");
                Date finish = resultSet.getDate("finish");
                course.setBeginDate(begin.toLocalDate());
                course.setFinishDate(finish.toLocalDate());
                course.setInstructor(new Instructor());
                course.getInstructor().setId(resultSet.getLong("instructor_id"));
                courses.add(course);
            }
            return courses;
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

    
    
    public List<Course> getInstructorCourses(Long id) throws SQLException {
        String sql = "SELECT id, name, hours, topic, begin, finish FROM courses WHERE instructor_id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            List<Course> courses = new ArrayList<>();
            while (resultSet.next()) {
                Course course = new Course();
                course.setId(resultSet.getLong("id"));
                course.setName(resultSet.getString("name"));
                course.setHours(resultSet.getInt("hours"));
                course.setTopic(resultSet.getString("topic"));
                Date begin = resultSet.getDate("begin");
                Date finish = resultSet.getDate("finish");
                course.setBeginDate(begin.toLocalDate());
                course.setFinishDate(finish.toLocalDate());
                course.setInstructor(new Instructor());
                course.getInstructor().setId(id);
                courses.add(course);
            }
            return courses;
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
    

    
    public List<Course> getOtherCoursesById(Long id) throws SQLException {
        String sql = "SELECT id, name, hours, topic, instructor_id, begin, finish FROM courses WHERE id NOT IN (SELECT course_id FROM assignments WHERE student_id = ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            List<Course> courses = new ArrayList<>();
            while (resultSet.next()) {
                Course course = new Course();
                course.setId(resultSet.getLong("id"));
                course.setName(resultSet.getString("name"));
                course.setHours(resultSet.getInt("hours"));
                course.setTopic(resultSet.getString("topic"));
                Date begin = resultSet.getDate("begin");
                Date finish = resultSet.getDate("finish");
                course.setBeginDate(begin.toLocalDate());
                course.setFinishDate(finish.toLocalDate());
                course.setInstructor(new Instructor());
                course.getInstructor().setId(resultSet.getLong("instructor_id"));
                courses.add(course);
            }
            return courses;
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

    
    
    public Long create(Course course) throws SQLException {
        String sql = "INSERT INTO courses (name, hours, topic, instructor_id, begin, finish) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, course.getName());
            statement.setInt(2, course.getHours());
            statement.setString(3, course.getTopic());
            statement.setLong(4, course.getInstructor().getId());
            LocalDate begin = course.getBeginDate();
            LocalDate end = course.getFinishDate();
            statement.setDate(5, Date.valueOf(begin));
            statement.setDate(6, Date.valueOf(end));
            
            statement.executeUpdate();
            Long id = null;
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
            return id;
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

    
    
    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM courses WHERE id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
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
    
    
    public Course read(Long id) throws SQLException {
        String sql = "SELECT name, hours, topic, instructor_id, begin, finish FROM courses WHERE id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Course course = null;
            if (resultSet.next()) {
                course = new Course();
                course.setId(id);
                course.setName(resultSet.getString("name"));
                course.setHours(resultSet.getInt("hours"));
                course.setTopic(resultSet.getString("topic"));
                Date begin = resultSet.getDate("begin");
                Date finish = resultSet.getDate("finish");
                course.setBeginDate(begin.toLocalDate());
                course.setFinishDate(finish.toLocalDate());
                course.setInstructor(new Instructor());
                course.getInstructor().setId(resultSet.getLong("instructor_id"));
            }
            return course;
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

    
    
    public void update(Course course) throws SQLException {
        String sql = "UPDATE courses SET name = ?, hours = ?, topic = ?, instructor_id = ?, begin = ?, finish = ? WHERE id = ?";
        PreparedStatement statement = null;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, course.getName());
            statement.setInt(2, course.getHours());
            statement.setString(3, course.getTopic());
            statement.setLong(4, course.getInstructor().getId());
            LocalDate begin = course.getBeginDate();
            LocalDate end = course.getFinishDate();
            statement.setDate(5, Date.valueOf(begin));
            statement.setDate(6, Date.valueOf(end));
            statement.setLong(7, course.getId());
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
}
