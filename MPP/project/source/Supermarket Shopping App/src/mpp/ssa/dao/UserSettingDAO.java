package mpp.ssa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserSettingDAO implements IUserSettingDAO {

    private DbConnection dbConnection;

    public UserSettingDAO() {
        dbConnection = new DbConnection();
    }

    private UserSettingDO extractUserSettingFromResultSet(ResultSet rs) throws SQLException {
        UserSettingDO userSetting = new UserSettingDO();
        userSetting.setId( rs.getString("id"));
        userSetting.setUsername( rs.getString("username"));
        userSetting.setSettingName( rs.getString("settingName"));
        userSetting.setSettingValue(rs.getString("settingValue"));
        return userSetting;
    }

    @Override
    public List<UserSettingDO> getUserSettingsByUsername(String username) throws SQLException {
        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM UserSetting WHERE username=?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            List<UserSettingDO> userSettings = new ArrayList<UserSettingDO>();
            while(rs.next())
            {
                UserSettingDO userSetting = extractUserSettingFromResultSet(rs);
                userSettings.add(userSetting);
            }
            return userSettings;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            connection.close();
        }

        return null;
    }

    @Override
    public boolean insertUserSetting(UserSettingDO userSetting) throws SQLException {
        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO UserSetting VALUES (?, ?, ?, ?)");
            ps.setString(1, userSetting.getId());
            ps.setString(2, userSetting.getUsername());
            ps.setString(3, userSetting.getSettingName());
            ps.setString(4, userSetting.getSettingValue());
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
    public boolean updateUserSetting(UserSettingDO userSetting) throws SQLException {
        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE UserSetting SET settingValue=? WHERE username=? AND settingName=?");
            ps.setString(1, userSetting.getSettingValue());
            ps.setString(2, userSetting.getUsername());
            ps.setString(3, userSetting.getSettingName());
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