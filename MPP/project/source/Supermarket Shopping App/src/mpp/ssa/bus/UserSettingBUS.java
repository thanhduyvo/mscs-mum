package mpp.ssa.bus;

import mpp.ssa.dao.UserSettingDAO;
import mpp.ssa.dao.UserSettingDO;
import mpp.ssa.domain.UserSetting;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserSettingBUS implements IUserSettingBUS {

    private UserSettingDAO userSettingDAO;

    public UserSettingBUS() {
        this.userSettingDAO = new UserSettingDAO();
    }

    private static UserSettingBUS userSettingBUS;

    public static UserSettingBUS getUserSettingBUS() {
        if(userSettingBUS == null) {
            userSettingBUS = new UserSettingBUS();
        }

        return userSettingBUS;
    }

    @Override
    public List<UserSetting> getUserSettingsByUsername(String username) {
        try {
            List<UserSettingDO> userSettingDOList = userSettingDAO.getUserSettingsByUsername(username);
            if(userSettingDOList != null) {
                List<UserSetting> userSettings = new ArrayList<UserSetting>();
                for(UserSettingDO userSettingDO : userSettingDOList) {
                    userSettings.add(new UserSetting(userSettingDO.getId(), userSettingDO.getUsername(),
                            userSettingDO.getSettingName(), userSettingDO.getSettingValue()));
                }
                return userSettings;
            }
        } catch (SQLException ex) {
        }

        return null;
    }

    @Override
    public UserSetting createUserSetting(UserSetting userSetting) {
        try {
            UserSettingDO userSettingDO = new UserSettingDO();
            UUID userSettingId = UUID.randomUUID();
            userSettingDO.setId(userSettingId.toString());
            userSettingDO.setUsername(userSetting.getUser().getUsername());
            userSettingDO.setSettingName(userSetting.getSettingName());
            userSettingDO.setSettingValue(userSetting.getSettingValue());
            boolean retValue = userSettingDAO.insertUserSetting(userSettingDO);
            if(retValue) {
                userSetting.setId(userSettingId.toString());
                return userSetting;
            }
        } catch (SQLException ex) {
        }

        return null;
    }

    @Override
    public boolean updateUserSetting(UserSetting userSetting) {
        try {
            UserSettingDO userSettingDO = new UserSettingDO();
            userSettingDO.setId(userSetting.getId());
            userSettingDO.setUsername(userSetting.getUser().getUsername());
            userSettingDO.setSettingName(userSetting.getSettingName());
            userSettingDO.setSettingValue(userSetting.getSettingValue());
            return userSettingDAO.updateUserSetting(userSettingDO);

        } catch (SQLException ex) {
        }

        return false;
    }
}