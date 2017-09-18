package mpp.ssa.bus;

import mpp.ssa.domain.UserSetting;
import java.util.List;

public interface IUserSettingBUS {

    List<UserSetting> getUserSettingsByUsername(String username);

    UserSetting createUserSetting(UserSetting userSetting);

    boolean updateUserSetting(UserSetting userSetting);
}