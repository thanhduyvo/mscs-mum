package mpp.ssa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO implements IUserDAO {

    private DbConnection dbConnection;

    public UserDAO() {
        dbConnection = new DbConnection();
    }

    private UserDO extractUserFromResultSet(ResultSet rs) throws SQLException {
        UserDO user = new UserDO();
        user.setUsername(rs.getString("username"));
        user.setUserType(rs.getString("userType"));
        return user;
    }

    @Override
    public UserDO getUserByUserNameAndPassword(String username, String password) throws SQLException {
        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM User WHERE username=? AND password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                return extractUserFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            connection.close();
        }

        return null;
    }

    @Override
    public boolean insertUser(UserDO user) throws SQLException {
        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO User VALUES (NULL, ?, ?, ?)");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getUserType());
            int i = ps.executeUpdate();
            if(i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            connection.close();
        }

        return false;
    }

    @Override
    public boolean updateUser(UserDO user) throws SQLException {
        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE User SET password=?, userType=? WHERE username=?");
            ps.setString(1, user.getPassword());
            ps.setString(2, user.getUserType());
            ps.setString(3, user.getUsername());
            int i = ps.executeUpdate();
            if(i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            connection.close();
        }

        return false;
    }
}