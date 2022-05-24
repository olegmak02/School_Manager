package service;

import dao.*;
import dao.AssignmentDaoImpl;
import dao.CourseDaoImpl;
import dao.ResultDaoImpl;
import dao.StudentDaoImpl;
import domain.Assignment;
import domain.Result;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ResultServiceImpl {
    private ResultDaoImpl resultDao = new ResultDaoImpl();
    private AssignmentDaoImpl assignmentDao = new AssignmentDaoImpl();
    private StudentDaoImpl studentDao = new StudentDaoImpl();
    private CourseDaoImpl courseDao = new CourseDaoImpl();


    public List<Result> findAll() throws SQLException {
        try {
            List<Result> resultList = resultDao.getResults();
            for (Result result : resultList) {
                fillResult(result);
            }
            return resultList;
        } catch (SQLException e) {
            throw e;
        }
    }


    public List<Result> findByStudent(Long id) throws SQLException {
        try {
            List<Assignment> studentAssignment = assignmentDao.getAssignmentsByStudent(id);
            List<Result> resultList = new ArrayList<>();
            for (Assignment assignment : studentAssignment) {
                resultList.addAll(resultDao.getListByAssignment(assignment.getId()));
            }
            for (Result result : resultList) {
                for (Assignment assignment : studentAssignment) {
                    if (Math.toIntExact(result.getAssignment().getId()) == assignment.getId()) {
                        result.setAssignment(assignment);
                    }
                }
                result.getAssignment().setCourse(courseDao.read(result.getAssignment().getCourse().getId()));
                result.getAssignment().setStudent(studentDao.read(result.getAssignment().getStudent().getId()));
            }
            return resultList;
        } catch (SQLException e) {
            throw e;
        }
    }
    

    public Result findByAssignmentId(Long id) throws SQLException {
        try {
            Result result = resultDao.findByAssignment(id);
            fillResult(result);
            return result;
        } catch (SQLException e) {
            throw e;
        }
    }


    public Result findById(Long id) throws SQLException {
        try {
            Result result = resultDao.read(id);
            fillResult(result);
            return result;
        } catch (SQLException e) {
            throw e;
        }
    }


    public void fillResult(Result result) throws SQLException {
    	if (result == null) {
    		return;
    	}
    	try {
            result.setAssignment(assignmentDao.read(result.getAssignment().getId()));
            result.getAssignment().setCourse(courseDao.read(result.getAssignment().getCourse().getId()));
            result.getAssignment().setStudent(studentDao.read(result.getAssignment().getStudent().getId()));
        } catch (SQLException e) {
            throw e;
        }

    }


    public Long save(Result result) throws SQLException {
        Long id = result.getId();
        try {
            if (id != null) {
                resultDao.update(result);
            } else {
                id = resultDao.create(result);
            }
        } catch (SQLException e) {
            throw e;
        }
        return id;
    }


    public void delete(List<Long> ids) throws SQLException {
        try {
            for (Long id : ids) {
                resultDao.delete(id, "results");
            }
        } catch (SQLException e) {
            throw e;
        }
    }
}
