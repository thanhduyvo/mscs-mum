package mpp.ssa.dao;

import java.sql.SQLException;
import java.util.List;

public interface IUserSettingDAO {

    List<UserSettingDO> getUserSettingsByUsername(String username) throws SQLException;

    boolean insertUserSetting(UserSettingDO userSetting) throws SQLException;

    boolean updateUserSetting(UserSettingDO userSetting) throws SQLException;
}