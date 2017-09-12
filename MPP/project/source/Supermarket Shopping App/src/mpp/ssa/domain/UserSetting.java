package mpp.ssa.domain;

/**
 * 
 */
public class UserSetting {

    /**
     * Default constructor
     */
    public UserSetting() {
        setUser(new User());
    }

    private User user;

    private int settingId;

    private String settingName;

    private String settingValue;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getSettingId() {
        return settingId;
    }

    public void setSettingId(int settingId) {
        this.settingId = settingId;
    }

    public String getSettingName() {
        return settingName;
    }

    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }

    public String getSettingValue() {
        return settingValue;
    }

    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
    }
}