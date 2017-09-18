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

    public UserSetting(String id, String username, String settingName, String settingValue) {
        setId(id);
        setUser(new User(username));
        setSettingName(settingName);
        setSettingValue(settingValue);
    }

    private User user;

    private String id;

    private String settingName;

    private String settingValue;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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