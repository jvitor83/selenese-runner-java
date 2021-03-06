package jp.vmi.selenium.selenese.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.internal.BuildInfo;

/**
 * System Information.
 */
@SuppressWarnings("javadoc")
public class SystemInformation {

    private static final String UNKNOWN = "<unknown>";

    private static SystemInformation systemInformation = new SystemInformation();

    /**
     * Get system information.
     *
     * @return SystemInformation.
     */
    public static SystemInformation getInstance() {
        return systemInformation;
    }

    private SystemInformation() {
    }

    public static String getVersionFromPomProperties(String pomProps) {
        try (InputStream is = SystemInformation.class.getResourceAsStream(pomProps)) {
            if (is == null)
                return UNKNOWN;
            Properties prop = new Properties();
            prop.load(is);
            return prop.getProperty("version", UNKNOWN);
        } catch (IOException e) {
            return UNKNOWN;
        }
    }

    public String getSeleneseRunnerVersion() {
        return getVersionFromPomProperties("/META-INF/maven/jp.vmi/selenese-runner-java/pom.properties");
    }

    public String getSeleniumVersion() {
        BuildInfo buildInfo = new BuildInfo();
        String label = buildInfo.getReleaseLabel();
        return "unknown".equals(label) ? UNKNOWN : label;
    }

    public String getJavaVMName() {
        return System.getProperty("java.vm.name", UNKNOWN);
    }

    public String getJavaVersion() {
        return System.getProperty("java.version", UNKNOWN);
    }

    public String getOperatingSystem() {
        return String.format("%s %s (%s)",
            System.getProperty("os.name", UNKNOWN),
            System.getProperty("os.version", UNKNOWN),
            System.getProperty("os.arch", UNKNOWN));
    }

    public String getLanguage() {
        return System.getProperty("user.language", UNKNOWN);
    }

    public String getCountry() {
        return System.getProperty("user.country", UNKNOWN);
    }

    public String getTimeZone() {
        return System.getProperty("user.timezone", UNKNOWN);
    }
}
