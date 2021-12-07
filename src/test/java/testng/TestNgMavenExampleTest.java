package testng;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import utilities.DateUtils;
import utilities.ReadConfigFiles;

public class TestNgMavenExampleTest {
    private static final Logger LOGGER = LogManager.getLogger(TestNgMavenExampleTest.class);

    //@Test
    public void run(){
        //System.out.println("This is maven example ");
        LOGGER.debug("This is debug message");
        LOGGER.info("This is info message");
        LOGGER.warn("This is warn message");
        LOGGER.error("This is error message");
        LOGGER.fatal("This is fatal message");
        LOGGER.trace("This is trace message");
    }
    //@Test
    public void testPropertyFile(){
        String value = ReadConfigFiles.getPropertyValues("DbUser");
        LOGGER.info(value);
    }
    @Test
    public void runDate(){
        System.out.println(DateUtils.returnNextMonth());
    }
}
