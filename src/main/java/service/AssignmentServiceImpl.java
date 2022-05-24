package service;

import dao.AssignmentDaoImpl;
import dao.CourseDaoImpl;
import dao.InstructorDaoImpl;
import dao.StudentDaoImpl;
import domain.Assignment;
import domain.Course;
import domain.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AssignmentServiceImpl {
    private AssignmentDaoImpl assignmentDao = new AssignmentDaoImpl();
    private StudentDaoImpl studentDao = new StudentDaoImpl();
    private CourseDaoImpl courseDao = new CourseDaoImpl();
    private InstructorDaoImpl instructorDao = new InstructorDaoImpl();
 
    
    public List<Assignment> findAll() throws SQLException {
        try {
            List<Assignment> assignmentList = assignmentDao.getAssignments();
            for (Assignment assignment : assignmentList) {
                fillAssignments(assignment);
            }
            return assignmentList;
        } catch (SQLException e) {
            throw e;
        }
    }

    
    public Assignment findById(Long id) throws SQLException {
        try {
            Assignment assignment = assignmentDao.read(id);
            fillAssignments(assignment);
            return assignment;
        } catch (SQLException e) {
            throw e;
        }
    }

    
    public Assignment findByStudentAndCourse(Long studentId, Long courseId) throws SQLException {
        try {
            Assignment assignment = assignmentDao.getByStudentAndCourse(studentId, courseId);
            fillAssignments(assignment);
            return assignment;
        } catch (SQLException e) {
            throw e;
        }
    }
    
    
    public List<Assignment> findByStudent(Long studentId) throws SQLException {
        try {
            List<Assignment> assignmentList = assignmentDao.getAssignmentsByStudent(studentId);
            for (Assignment assignment : assignmentList) {
                fillAssignments(assignment);
            }
            return assignmentList;
        } catch (SQLException e) {
            throw e;
        }
    }

    
    public List<Assignment> findInstructorAssignments(Long instructorId) throws SQLException {
        try {
            List<Course> instructorCourses = courseDao.getInstructorCourses(instructorId);
            List<Assignment> results = new ArrayList<>();
            for (Course course : instructorCourses) {
                results.addAll(assignmentDao.getAssignmentsByCourse(course.getId()));
            }
            for (Assignment assignment : results) {
                fillAssignments(assignment);
            }
            return results;
        } catch (SQLException e) {
            throw e;
        }
    }

    
    public Long save(Assignment assignment) throws SQLException {
        Long id = assignment.getId();
        try {
            if (id != null) {
                assignmentDao.update(assignment);
            } else {
                id = assignmentDao.create(assignment);
            }
        } catch (SQLException e) {
            throw e;
        }
        return id;
    }

    
    public void delete(List<Long> ids) throws SQLException {
        try {
            for (Long id : ids) {
                assignmentDao.delete(id, "assignments");
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    
    public void fillAssignments(Assignment assignment) throws SQLException {
    	if (assignment == null) {
    		return;
    	}
        try {
            assignment.setStudent(studentDao.read(assignment.getStudent().getId()));
            assignment.setCourse(courseDao.read(assignment.getCourse().getId()));
            assignment.getCourse().setInstructor(instructorDao.getById(assignment.getCourse().getInstructor().getId()));
        } catch (SQLException e) {
        	throw e;
        }
    }

	
	public int checkAssign(Long studentId, Long courseId) {
		return	assignmentDao.getCountByStudentAndCourse(studentId, courseId);
	}

	
	public List<Student> findStudentsByCourse(Long courseId) throws SQLException {
		List<Student> students = assignmentDao.getStudentsByCourse(courseId);
		
		for(int i = 0; i < students.size(); i++) {
			try {
				Student stud = studentDao.read(students.get(i).getId());
				students.set(i, stud);
			} catch (SQLException e) {
				throw e;
			}
		}
		return students;
	}
}
