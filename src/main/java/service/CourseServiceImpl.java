package service;

import dao.CourseDaoImpl;
import dao.InstructorDaoImpl;
import domain.Course;
import domain.Instructor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CourseServiceImpl {
    private CourseDaoImpl courseDao = new CourseDaoImpl();
    private InstructorDaoImpl instructorDao = new InstructorDaoImpl();

    
    public List<Course> findAll() throws SQLException {
        try {
            List<Course> courseList = courseDao.getCourses();
            for (Course course : courseList) {
                course.setInstructor(instructorDao.read(course.getInstructor().getId()));
            }
            return courseList;
        } catch (SQLException e) {
            throw e;
        }
    }

    
    public List<Course> findByInstructor(String condition) throws SQLException {
        List<Course> courseListByInstructor = new ArrayList<>();
        try {
            if (condition.matches("\\d++")) {
                Long id = Long.parseLong(condition);
                courseListByInstructor = courseDao.getInstructorCourses(id);
                for (Course course : courseListByInstructor) {
                    course.setInstructor(instructorDao.read(id));
                }
            } else {
                List<Instructor> instructorList = instructorDao.getInstructors();
                for (Instructor instructor : instructorList) {
                    if (instructor.getSurname().equals(condition)) {
                        courseListByInstructor.addAll(courseDao.getInstructorCourses(instructor.getId()));
                        courseListByInstructor.forEach(course -> course.setInstructor(instructor));
                    }
                }
            }
        } catch (SQLException e) {
            throw e;
        }
        return courseListByInstructor;
    }

    
    public List<Course> getOtherByStudent(Long studentId) throws SQLException {
    	List<Course> courses;
        try {
        	courses = courseDao.getOtherCoursesById(studentId);
            for (Course course : courses) {
                course.setInstructor(instructorDao.read(course.getInstructor().getId()));
            }
        } catch (SQLException e) {
            throw e;
        }
        return courses;
    }
    
    
    public Course findById(Long id) throws SQLException {
        try {
            Course course = courseDao.read(id);
            if (course != null) {
                course.setInstructor(instructorDao.read(course.getInstructor().getId()));
            }
            return course;
        } catch (SQLException e) {
            throw e;
        }
    }

    
    public Long save(Course course) throws SQLException {
        Long id = course.getId();
        try {
            if (id != null) {
                courseDao.update(course);
            } else {
                id = courseDao.create(course);
            }
        } catch (SQLException e) {
            throw e;
        }
        return id;
    }

    
    public void delete(Long id) throws SQLException {
        try {
            courseDao.delete(id, "courses");
            
        } catch (SQLException e) {
            throw e;
        }
    }
}
