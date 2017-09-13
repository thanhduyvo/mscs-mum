package mpp.ssa.dao;

import java.sql.SQLException;

public interface IUserDAO {

    UserDO getUserByUserNameAndPassword(String username, String password) throws SQLException;

    boolean insertUser(UserDO user) throws SQLException;

    boolean updateUser(UserDO user) throws SQLException;
}