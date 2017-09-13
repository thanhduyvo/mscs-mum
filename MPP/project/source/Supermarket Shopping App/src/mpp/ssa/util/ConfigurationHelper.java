package mpp.ssa.util;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class ConfigurationHelper {

    private static Properties props;

    static {
        File configFile = new File("config.properties");
        try{
            FileReader reader = new FileReader(configFile);
            props = new Properties();
            props.load(reader);
            reader.close();
        } catch(Exception ex) {
             //
        }
    }

    public static String getConfiguration(String key) {
        if (props != null) {
            return props.getProperty(key);
        }

        return "";
    }
}