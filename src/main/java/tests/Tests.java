package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import connection.ConnectionManager;
import domain.Assignment;
import domain.Course;
import domain.Instructor;
import domain.Result;
import domain.Student;
import domain.User;
import enums.Roles;
import service.AssignmentServiceImpl;
import service.CourseServiceImpl;
import service.InstructorServiceImpl;
import service.ResultServiceImpl;
import service.StudentServiceImpl;
import service.UserServiceImpl;

class Tests {
	private UserServiceImpl userService = new UserServiceImpl();
	private AssignmentServiceImpl assignmentService = new AssignmentServiceImpl();
	private CourseServiceImpl courseService = new CourseServiceImpl();
	private InstructorServiceImpl instructorService = new InstructorServiceImpl();
	private StudentServiceImpl studentService = new StudentServiceImpl();
	private ResultServiceImpl resultService = new ResultServiceImpl();
	@BeforeAll
	public static void initial() {
		ConnectionManager.updateConnections("jdbc:postgresql://localhost/test", "postgres", "oleg");
		
		String clearTables = "DELETE FROM results; DELETE FROM assignments; DELETE FROM students; DELETE FROM instructors; DELETE FROM users; " +
							 "DELETE FROM courses;";
		
		String initUsersTable = "INSERT INTO users(id, login, password, role) VALUES (1, 'admin', '12345', 0), " + 
				"(2, 'instructor1', '12345', 1), (3, 'instructor2', '12345', 1), " + 
				"(4, 'instructor3', '12345', 1), (5, 'student1', '12345', 2), " + 
				"(6, 'student2', '12345', 2), (7, 'student3', '12345', 2), " + 
				"(8, 'student4', '12345', 2), (9, 'student5', '12345', 2), " +
				"(10, 'student6', '12345', 2)";
		
		String initInstructorsTable = "INSERT INTO instructors (id, surname, name) VALUES (1, 'Степанова', 'Татьяна'), " +
				"(2, 'Воронов', 'Александров'), (3, 'Щербаков', 'Владимир'), (4, 'Валентинова', 'Елена')";
		
		String initStudentsTable = "INSERT INTO students (id, surname, name, study_year, blocked) VALUES (5, 'Антонова', 'Алина', 2, false), " + 
				"(6, 'Рябцева', 'Дарья', 2, false), (7, 'Багров', 'Алексей', 2, false), (8, 'Крылов', 'Иван', 2, false), " + 
				"(9, 'Круценко', 'Петр', 2, true), (10, 'Егорв', 'Валентин', 2, true)";
				
		String initCoursesTable = "INSERT INTO courses (id, name, hours, topic, instructor_id, begin, finish) " +
				"VALUES (1, 'JAVA', 6, 'programming', 2, '2021-02-01', '2021-06-11'), (2, 'JavaScript + React', 20, 'programming', 4, '2021-02-04', '2021-05-26'), " +
				"(3, 'Graphic Designer', 15, 'graphic', 4, '2021-09-01', '2021-12-30'), (4, 'C++', 12, 'programming', 3, '2022-02-05', '2022-07-02');";
				
		String initAssignmentsTable = "INSERT INTO assignments (id, student_id, course_id) VALUES (1, 5, 2), (2, 5, 3), " +
				"(3, 6, 3), (4, 7, 1), (5, 7, 4), (6, 7, 2), (7, 8, 1), (8, 9, 3), (9, 10, 4)";
				
		String initResultsTable = "INSERT INTO results (id, assignment_id, grade) VALUES (1, 1, 86), (2, 6, 73), (3, 4, 94), (4, 7, 90)";
		
		try {
			Connection connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			statement.execute(clearTables);
			statement.execute(initUsersTable);
			statement.execute(initInstructorsTable);
			statement.execute(initStudentsTable);
			statement.execute(initCoursesTable);
			statement.execute(initAssignmentsTable);
			statement.execute(initResultsTable);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
 	
	@Test
	public void userServiceTests_findAll_checkSize() {
		try {
			List<User> users = userService.findAll();
			assertTrue(users.size() > 0);
		} catch (SQLException e) {
			fail("userService.findAll throws exception");
		}
	}
	
	@Test
	public void userServiceTests_findAll_checkUser() {
		try {
			List<User> users = userService.findAll();
			User user = users.get(0);
			assertNotNull(user);
		} catch (SQLException e) {
			fail("userService.findAll throws exception");
		}
	}
	
	@Test
	public void userServiceTests_save_createUser() {
		try {
			User user = new User();
			user.setLogin("some_login");
			user.setPassword("some_password");
			user.setRole(Roles.STUDENT);
			Long id = userService.save(user);
			assertNotNull(id);
		} catch (SQLException e) {
			fail("userService.save throws exception");
		}
	}

	@Test
	public void userServiceTests_save_updateUser() {
		try {
			List<User> users = userService.findAll();
			User user = users.get(0);
			Long id = user.getId();
			Long returnedId = userService.save(user);
			assertEquals(id, returnedId);
		} catch (SQLException e) {
			fail("userService.save throws exception");
		}
	}
	
	@Test
	public void userServiceTests_findById_nonExistingUser() {
		try {
			Long nonExistId = -1L;
			User nonExistUser = userService.findById(nonExistId);
			assertNull(nonExistUser);
		} catch (SQLException e) {
			fail("userService.findById throws exception");
		}
	}
	
	@Test
	public void userServiceTests_findById_existingUser() {
		try {
			Long existId = 1L;
			User user = userService.findById(existId);
			assertNotNull(user);
		} catch (SQLException e) {
			fail("userService.findById throws exception");
		}
	}
	
	@Test
	public void userServiceTests_findByLoginAndPass_existingUser() {
		try {
			String login = "student1";
			String password = "12345";
			User user = userService.findByLoginAndPass(login, password);
			assertNotNull(user);
		} catch (SQLException e) {
			fail("userService.findByLoginAndPass throws exception");
		}
	}
	
	@Test
	public void userServiceTests_findByLoginAndPass_nonExistingUser() {
		try {
			String nonExistLogin = "non_exist_login";
			String nonExistPassword = "non_exist_password";
			User user = userService.findByLoginAndPass(nonExistLogin, nonExistPassword);
			assertNull(user);
		} catch (SQLException e) {
			fail("userService.findByLoginAndPass throws exception");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	public void studentServiceTests_findAll_checkSize() {
		try {
			List<Student> students = studentService.findAll();
			assertTrue(students.size() > 0);
		} catch (SQLException e) {
			fail("studentService.findAll throws exception");
		}
	}
	
	@Test
	public void studentServiceTests_findAll_checkStudent() {
		try {
			List<Student> students = studentService.findAll();
			Student student = students.get(0);
			assertNotNull(student);
		} catch (SQLException e) {
			fail("studentService.findAll throws exception");
		}
	}
	
	@Test
	public void studentServiceTests_findById_nonExistingUser() {
		try {
			Long nonExistId = -1L;
			Student nonExistStudent = studentService.findById(nonExistId);
			assertNull(nonExistStudent);
		} catch (SQLException e) {
			fail("studentService.findById throws exception");
		}
	}
	
	@Test
	public void studentServiceTests_findById_existingUser() {
		try {
			Long existId = 6L;
			Student student = studentService.findById(existId);
			assertNotNull(student);
		} catch (SQLException e) {
			fail("studentService.findById throws exception");
		}
	}
	
	@Test
	public void studentServiceTests_create() {
		try {
			User user = new User();
            
			user.setLogin("loginlogin");
			user.setPassword("password_password");		
			user.setRole(Roles.STUDENT);
            Long id = userService.save(user);
            user.setId(id);
            
            Student student = new Student();
            student.setSurname("Johnson");
            student.setName("John");
            student.setStudyYear(4);
            student.setId(id);
            Long studentId = studentService.create(student);
			assertEquals(studentId, id);
		} catch (SQLException e) {
			fail("studentService.create throws exception");
		}
	}
	
	@Test
	public void studentServiceTests_getBlockedStudents_checkSize() {
		try {
			List<Student> students = studentService.getBlockedStudents();
			assertTrue(students.size() > 0);
		} catch (SQLException e) {
			fail("studentService.getBlockedStudents throws exception");
		}
	}
	
	@Test
	public void studentServiceTests_getBlockedStudents_checkStudent() {
		try {
			List<Student> students = studentService.getBlockedStudents();
			Student student = students.get(0);
			assertNotNull(student);
		} catch (SQLException e) {
			fail("studentService.getBlockedStudents throws exception");
		}
	}
	
	@Test
	public void studentServiceTests_getUnblockedStudents() {
		try {
			List<Student> students = studentService.getUnblockedStudents();
			assertTrue(students.size() > 0);
		} catch (SQLException e) {
			fail("studentService.getUnblockedStudents throws exception");
		}
	}
	
	@Test
	public void studentServiceTests_getUnblockedStudents_checkStudent() {
		try {
			List<Student> students = studentService.getUnblockedStudents();
			Student student = students.get(0);
			assertNotNull(student);
		} catch (SQLException e) {
			fail("studentService.getUnblockedStudents throws exception");
		}
	}
	
	@Test
	public void studentServiceTests_setBlock() {
		try {
			List<Student> blockedStudents = studentService.getBlockedStudents();
			int blocked = blockedStudents.size();
			studentService.setBlock(8L);
			int newBlocked = studentService.getBlockedStudents().size();
			assertEquals(blocked + 1, newBlocked);
		} catch (SQLException e) {
			fail("studentService.setUnblock throws exception");
		}
	}
	
	@Test
	public void studentServiceTests_setUnblock() {
		try {
			List<Student> unblockedStudents = studentService.getUnblockedStudents();
			int unblocked = unblockedStudents.size();
			studentService.setUnblock(8L);
			int newUnblocked = studentService.getUnblockedStudents().size();
			assertEquals(unblocked + 1, newUnblocked);
		} catch (SQLException e) {
			fail("studentService.setUnblock throws exception");
		}
	}
	
	@Test
	public void studentServiceTests_isBlocked_blocked() {
		try {
			Long studentId = studentService.getUnblockedStudents().get(0).getId();
			studentService.setBlock(studentId);
			assertTrue(studentService.isBlocked(studentId));
		} catch (SQLException e) {
			fail("studentService.isBlocked throws exception");
		}
	}
	
	@Test
	public void studentServiceTests_isBlocked_unblocked() {
		try {
			Long studentId = studentService.getBlockedStudents().get(0).getId();
			studentService.setUnblock(studentId);
			assertFalse(studentService.isBlocked(studentId));
		} catch (SQLException e) {
			fail("studentService.isBlocked throws exception");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	public void instructorServiceTests_findAll_checkSize() {
		try {
			List<Instructor> instructors = instructorService.findAll();
			assertTrue(instructors.size() > 0);
		} catch (SQLException e) {
			fail("instructorService.findAll throws exception");
		}
	}
	
	@Test
	public void instructorServiceTests_findAll_checkInstructor() {
		try {
			List<Instructor> instructors = instructorService.findAll();
			Instructor instructor = instructors.get(0);
			assertNotNull(instructor);
		} catch (SQLException e) {
			fail("instructorService.findAll throws exception");
		}
	}
	
	@Test
	public void instructorServiceTests_findById_nonExistingInstructor() {
		try {
			Long nonExistId = -1L;
			Instructor nonExistInstructor = instructorService.findById(nonExistId);
			assertNull(nonExistInstructor);
		} catch (SQLException e) {
			fail("instructorService.findById throws exception");
		}
	}
	
	@Test
	public void instructorServiceTests_findById_existingInstructor() {
		try {
			Long existId = instructorService.findAll().get(0).getId();
			Instructor instructor = instructorService.findById(existId);
			assertNotNull(instructor);
		} catch (SQLException e) {
			fail("instructorService.findById throws exception");
		}
	}
	
	@Test
	public void instructorServiceTests_create() {
		try {
			User user = new User();
            
			user.setLogin("login_login");
			user.setPassword("password_password");		
			user.setRole(Roles.INSTRUCTOR);
            Long id = userService.save(user);
            user.setId(id);
            
            Instructor instructor = new Instructor();
            instructor.setSurname("Иванов");
            instructor.setName("Иван");
            instructor.setId(id);
            Long instructorId = instructorService.create(instructor);
			assertEquals(instructorId, id);
		} catch (SQLException e) {
			fail("instructorService.create throws exception");
		}
	}
	
	@Test
	public void instructorServiceTests_findBySurname_nonExistingName() {
		try {
            String name = "Non existing name";
            List<Instructor> instructors = instructorService.findBySurname(name);
			assertTrue(instructors.size() == 0);
		} catch (SQLException e) {
			fail("instructorService.findBySurname throws exception");
		}
	}
	
	@Test
	public void instructorServiceTests_findBySurname_existingName() {
		try {
            String name = "Степанова";
            List<Instructor> instructors = instructorService.findBySurname(name);
			assertNotNull(instructors.get(0));
		} catch (SQLException e) {
			fail("instructorService.findBySurname throws exception");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	public void courseServiceTests_findAll_checkSize() {
		try {
			List<Course> courses = courseService.findAll();
			assertTrue(courses.size() > 0);
		} catch (SQLException e) {
			fail("courseService.findAll throws exception");
		}
	}

	@Test
	public void courseServiceTests_findAll_checkCourse() {
		try {
			List<Course> courses = courseService.findAll();
			Course course = courses.get(0);
			assertNotNull(course);
		} catch (SQLException e) {
			fail("courseService.findAll throws exception");
		}
	}
	
	@Test
	public void courseServiceTests_findByInstructor_findByName() {
		try {
			String name = "Воронов";
			List<Course> courses = courseService.findByInstructor(name);
			assertNotNull(courses.get(0));
		} catch (SQLException e) {
			fail("courseService.findByInstructor throws exception");
		}
	}
	
	@Test
	public void courseServiceTests_findByInstructor_findById() {
		try {
			Long existId = instructorService.findAll().get(1).getId();
			List<Course> courses = courseService.findByInstructor(existId.toString());
			assertNotNull(courses.get(0));
		} catch (SQLException e) {
			fail("courseService.findByInstructor throws exception");
		}
	}
	
	@Test
	public void courseServiceTests_getOtherByStudent() {
		try {
			Long studentId = studentService.findAll().get(0).getId();
			List<Course> courses = courseService.getOtherByStudent(studentId);
			assertNotNull(courses.get(0));
		} catch (SQLException e) {
			fail("courseService.getOtherByStudent throws exception");
		}
	}
	
	@Test
	public void courseServiceTests_findById_nonExistingCourse() {
		try {
            Long id = -1L;
            Course course = courseService.findById(id);
			assertNull(course);
		} catch (SQLException e) {
			fail("courseService.findById throws exception");
		}
	}
	
	@Test
	public void courseServiceTests_findById_existingCourse() {
		try {
            Long courseId = courseService.findAll().get(0).getId();
            Course course = courseService.findById(courseId);
			assertNotNull(course);
		} catch (SQLException e) {
			fail("courseService.findById throws exception");
		}
	}
	
	@Test
	public void courseServiceTests_save() {
		try {
			int courseSize = courseService.findAll().size();
            Course newCourse = new Course();
            newCourse.setName("course_name");
            newCourse.setHours(10);
            newCourse.setBeginDate(LocalDate.of(2021, 1, 10));
            newCourse.setFinishDate(LocalDate.of(2021, 6, 10));
            newCourse.setTopic("topic_name");
            Instructor instructor = new Instructor();
            instructor.setId(3L);
            newCourse.setInstructor(instructor);
            Long courseId = courseService.save(newCourse);
			assertEquals(courseSize + 1, courseService.findAll().size());
		} catch (SQLException e) {
			fail("courseService.save throws exception");
		}
	}
	
	@Test
	public void courseServiceTests_delete() {
		try {
			int courseSize = courseService.findAll().size();
            Course newCourse = new Course();
            newCourse.setName("course_new");
            newCourse.setHours(10);
            newCourse.setBeginDate(LocalDate.of(2021, 1, 10));
            newCourse.setFinishDate(LocalDate.of(2021, 6, 10));
            newCourse.setTopic("topic_name");
            Instructor instructor = new Instructor();
            instructor.setId(3L);
            newCourse.setInstructor(instructor);
            Long courseId = courseService.save(newCourse);
			assertNotNull(courseService.findById(courseId));
			courseService.delete(courseId);
			assertNull(courseService.findById(courseId));
		} catch (SQLException e) {
			fail("courseService.delete throws exception");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	public void assignmentServiceTests_findAll_checkSize() {
		try {
			List<Assignment> assignments = assignmentService.findAll();
			assertTrue(assignments.size() > 0);
		} catch (SQLException e) {
			fail("assignmentService.findAll throws exception");
		}
	}

	@Test
	public void assignmentServiceTests_findAll_checkCourse() {
		try {
			List<Assignment> assignments = assignmentService.findAll();
			Assignment assignment = assignments.get(0);
			assertNotNull(assignment);
		} catch (SQLException e) {
			fail("assignmentService.findAll throws exception");
		}
	}
	
	@Test
	public void assignmentServiceTests_findById_nonExistingAssingment() {
		try {
			Long nonExistId = -1L;
			Assignment assignment = assignmentService.findById(nonExistId);
			assertNull(assignment);
		} catch (SQLException e) {
			fail("assignmentService.findById throws exception");
		}
	}
	
	@Test
	public void assignmentServiceTests_findById_existingAssignment() {
		try {
			Long existId = assignmentService.findAll().get(0).getId();
			Assignment assignment = assignmentService.findById(existId);
			assertNotNull(assignment);
		} catch (SQLException e) {
			fail("assignmentService.findById throws exception");
		}
	}
	
	@Test
	public void assignmentServiceTests_findByStudentAndCourse_existingAssignment() {
		try {
            Long studentId = studentService.findById(5L).getId();
            Long courseId = courseService.findById(3L).getId();
            Assignment assignment = assignmentService.findByStudentAndCourse(studentId, courseId);
			assertNotNull(assignment);
		} catch (SQLException e) {
			fail("assignmentService.findByStudentAndCourse throws exception");
		}
	}
	
	@Test
	public void assignmentServiceTests_findByStudentAndCourse_nonExistingAssignment() {
		try {
            Assignment assignment = assignmentService.findByStudentAndCourse(-1L, -1L);
			assertNull(assignment);
		} catch (SQLException e) {
			fail("assignmentService.findByStudentAndCourse throws exception");
		}
	}
	
	@Test
	public void assignmentServiceTests_findByStudent_existingStudent_checkSize() {
		try {
			Long studentId = studentService.findById(5L).getId();
			List<Assignment> assignments = assignmentService.findByStudent(studentId);
			assertTrue(assignments.size() > 0);
		} catch (SQLException e) {
			fail("assignmentService.findByStudent throws exception");
		}
	}
	
	@Test
	public void assignmentServiceTests_findByStudent_existingStudent_checkAssignment() {
		try {
			Long studentId = studentService.findById(5L).getId();
			List<Assignment> assignments = assignmentService.findByStudent(studentId);
			assertNotNull(assignments.get(0));
		} catch (SQLException e) {
			fail("assignmentService.findByStudent throws exception");
		}
	}
	
	@Test
	public void assignmentServiceTests_findByStudent_nonExistingStudent() {
		try {
			List<Assignment> assignments = assignmentService.findByStudent(-1L);
			assertTrue(assignments.size() == 0);
		} catch (SQLException e) {
			fail("assignmentService.findByStudent throws exception");
		}
	}
	
	@Test
	public void assignmentServiceTests_findInstructorAssignments_existingInstructor_checkSize() {
		try {
			Long instructorId = instructorService.findById(2L).getId();
			List<Assignment> assignments = assignmentService.findInstructorAssignments(instructorId);
			assertTrue(assignments.size() > 0);
		} catch (SQLException e) {
			fail("assignmentService.findInstructorAssignments throws exception");
		}
	}
	
	@Test
	public void assignmentServiceTests_findInstructorAssignments_existingInstructor_checkAssignment() {
		try {
			Long instructorId = instructorService.findById(2L).getId();
			List<Assignment> assignments = assignmentService.findInstructorAssignments(instructorId);
			assertNotNull(assignments.get(0));
		} catch (SQLException e) {
			fail("assignmentService.findByStudent throws exception");
		}
	}
	
	@Test
	public void assignmentServiceTests_findInstructorAssignments_nonExistingInstructor() {
		try {
			List<Assignment> assignments = assignmentService.findInstructorAssignments(-1L);
			assertTrue(assignments.size() == 0);
		} catch (SQLException e) {
			fail("assignmentService.findInstructorAssignments throws exception");
		}
	}
	
	@Test
	public void assignmentServiceTests_save() {
		try {
			Long studentId = 6L;
			Long courseId = 2L;
			assertNull(assignmentService.findByStudentAndCourse(studentId, courseId));
			Assignment assignment = new Assignment();
			Student student = studentService.findById(studentId);
			Course course = courseService.findById(courseId);
			assignment.setCourse(course);
			assignment.setStudent(student);
			assignmentService.save(assignment);
			assertNotNull(assignmentService.findByStudentAndCourse(studentId, courseId));
		} catch (SQLException e) {
			fail("assignmentService.save throws exception");
		}
	}
	
	@Test
	public void assignmentServiceTests_delete() {
		try {
			assertNotNull(assignmentService.findByStudentAndCourse(5L, 3L));
			Assignment assignment = assignmentService.findByStudentAndCourse(5L, 3L);
			List<Long> ids = new ArrayList();
			ids.add(assignment.getId());
			assignmentService.delete(ids);
			assertNull(assignmentService.findByStudentAndCourse(5L, 3L));
		} catch (SQLException e) {
			fail("assignmentService.delete throws exception");
		}
	}
	
	@Test
	public void assignmentServiceTests_checkAssign_noMatches() {
		int res = assignmentService.checkAssign(-1L, -1L);
		assertEquals(0, res);
	}
	
	@Test
	public void assignmentServiceTests_checkAssign_foundMatches() {
		int res = assignmentService.checkAssign(7L, 2L);
		assertEquals(1, res);
	}
	
	@Test
	public void assignmentServiceTests_findStudentsByCourse_checkSize() {
		Long courseId;
		try {
			courseId = courseService.findAll().get(0).getId();
			List<Student> students = assignmentService.findStudentsByCourse(courseId);
			assertTrue(students.size() > 0);
		} catch (SQLException e) {
			fail("assignmentService.findStudentsByCourse throws exception");
		}
		
	}
	
	@Test
	public void assignmentServiceTests_findStudentsByCourse_checkStudent() {
		Long courseId;
		try {
			courseId = courseService.findAll().get(0).getId();
			List<Student> students = assignmentService.findStudentsByCourse(courseId);
			assertNotNull(students.get(0));
		} catch (SQLException e) {
			fail("assignmentService.findStudentsByCourse throws exception");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	public void resultServiceTests_findAll_checkSize() {
		try {
			List<Result> results = resultService.findAll();
			assertTrue(results.size() > 0);
		} catch (SQLException e) {
			fail("resultService.findAll throws exception");
		}
	}

	@Test
	public void resultServiceTests_findAll_checkCourse() {
		try {
			List<Result> results = resultService.findAll();
			assertNotNull(results.get(0));
		} catch (SQLException e) {
			fail("resultService.findAll throws exception");
		}
	}
	
	@Test
	public void resultServiceTests_findByStudent_nonExistingStudent() {
		try {
			Long nonExistId = -1L;
			List<Result> results = resultService.findByStudent(nonExistId);
			assertTrue(results.size() == 0);
		} catch (SQLException e) {
			fail("resultService.findByStudent throws exception");
		}
	}
	
	@Test
	public void resultServiceTests_findByStudent_existingStudent() {
		try {
			Long existId = studentService.findAll().get(0).getId();
			List<Result> results = resultService.findByStudent(existId);
			assertTrue(results.size() == 0);
		} catch (SQLException e) {
			fail("resultService.findByStudent throws exception");
		}
	}
	
	@Test
	public void resultServiceTests_findByAssignmentId_existingAssignment() {
		try {
            Long id = assignmentService.findAll().get(0).getId();
            Result result = resultService.findByAssignmentId(id);
            assertNotNull(result);
		} catch (SQLException e) {
			fail("resultService.findByAssignmentId throws exception");
		}
	}
	
	@Test
	public void resultServiceTests_findByAssignmentId_nonExistingAssignment() {
		try {
            Result result = resultService.findByAssignmentId(-1L);
			assertNull(result);
		} catch (SQLException e) {
			fail("resultService.findByAssignmentId throws exception");
		}
	}
	
	@Test
	public void resultServiceTests_findById_existing() {
		try {
			Long id = resultService.findAll().get(0).getId();
			Result result = resultService.findById(id);
			assertNotNull(result);
		} catch (SQLException e) {
			fail("resultService.findById throws exception");
		}
	}
	
	@Test
	public void resultServiceTests_findById_nonExisting() {
		try {
			Result result = resultService.findById(-1L);
			assertNull(result);
		} catch (SQLException e) {
			fail("resultService.findById throws exception");
		}
	}
	
	@Test
	public void resultServiceTests_save() {
		try {
			Result result = new Result();
			Assignment assignment = assignmentService.findById(3L);
			result.setAssignment(assignment);
			result.setGrade(90);
			Long id = resultService.save(result);
			assertNotNull(id);
		} catch (SQLException e) {
			fail("resultService.save throws exception");
		}
	}
	
	@Test
	public void resultServiceTests_delete() {
		try {
			Long id = resultService.findAll().get(0).getId();
			List<Long> ids = new ArrayList();
			ids.add(id);
			resultService.delete(ids);
			Result result = resultService.findById(id);
			assertNull(result);
		} catch (SQLException e) {
			fail("resultService.delete throws exception");
		}
	}
}
