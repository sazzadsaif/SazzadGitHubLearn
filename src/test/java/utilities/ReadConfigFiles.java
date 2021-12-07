package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

public class ReadConfigFiles {
    public static final Logger LOGGER = LogManager.getLogger(ReadConfigFiles.class);
    public static String getPropertyValues(String propName){
        Properties prop;
        String propValue = null;
        try {
            //LoadConfigFiles loadConfigFiles = new LoadConfigFiles();(SAME METHOD)
            //loadConfigFiles.readPropertyValues();
            prop = new LoadConfigFiles().readPropertyValues();//(SAME METHOD AS ABOVE)
            propValue = prop.getProperty(propName);

        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }
        return propValue;

    }
}
