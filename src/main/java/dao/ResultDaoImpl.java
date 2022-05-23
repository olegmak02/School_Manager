package dao;

import domain.Assignment;
import domain.Result;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ResultDaoImpl extends BaseDaoImpl {


    
    public List<Result> getResults() throws SQLException {
        String sql = "SELECT id, assignment_id, grade FROM results";
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            List<Result> results = new ArrayList<>();
            while (resultSet.next()) {
                Result result = new Result();
                result.setId(resultSet.getLong("id"));
                result.setAssignment(new Assignment());
                result.getAssignment().setId(resultSet.getLong("assignment_id"));
                result.setGrade(resultSet.getInt("grade"));
                results.add(result);
            }
            return results;
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

    
    public List<Result> getListByGrade(int fromGrade, int toGrade) throws SQLException {
        String sql = "SELECT id, assignment_id, grade FROM results WHERE grade BETWEEN ? AND ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, fromGrade);
            statement.setInt(2, toGrade);
            resultSet = statement.executeQuery();
            List<Result> results = new ArrayList<>();
            while (resultSet.next()) {
                Result result = new Result();
                result.setId(resultSet.getLong("id"));
                result.setAssignment(new Assignment());
                result.getAssignment().setId(resultSet.getLong("assignment_id"));
                result.setGrade(resultSet.getInt("grade"));
                results.add(result);
            }
            return results;
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

    
    public List<Result> getListByAssignment(Long id) throws SQLException {
        String sql = "SELECT id, grade FROM results WHERE assignment_id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            List<Result> results = new ArrayList<>();
            while (resultSet.next()) {
                Result result = new Result();
                result.setId(resultSet.getLong("id"));
                result.setAssignment(new Assignment());
                result.getAssignment().setId(id);
                result.setGrade(resultSet.getInt("grade"));
                results.add(result);
            }
            return results;
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

    
    public Long create(Result result) throws SQLException {
        String sql = "INSERT INTO results (assignment_id, grade) VALUES (?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, result.getAssignment().getId());
            statement.setInt(2, result.getGrade());
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

    
    public Result read(Long id) throws SQLException {
        String sql = "SELECT assignment_id, grade FROM results WHERE id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Result result = null;
            if (resultSet.next()) {
                result = new Result();
                result.setId(id);
                result.setAssignment(new Assignment());
                result.getAssignment().setId(resultSet.getLong("assignment_id"));
                result.setGrade(resultSet.getInt("grade"));
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


    public void update(Result result) throws SQLException {
        String sql = "UPDATE results SET assignment_id = ?, grade = ? WHERE id = ?";
        PreparedStatement statement = null;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, result.getAssignment().getId());
            statement.setInt(2, result.getGrade());
            statement.setLong(3, result.getId());
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

	
	public Result findByAssignment(Long id) throws SQLException {
		String sql = "SELECT id, grade FROM results WHERE assignment_id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Result result = null;
            if (resultSet.next()) {
                result = new Result();
                result.setId(resultSet.getLong("id"));
                result.setAssignment(new Assignment());
                result.getAssignment().setId(id);
                result.setGrade(resultSet.getInt("grade"));
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
