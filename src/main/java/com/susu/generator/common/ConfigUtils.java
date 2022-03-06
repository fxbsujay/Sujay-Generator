package com.susu.generator.common;

import com.susu.generator.exception.GeneratorException;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import java.util.Arrays;
import java.util.List;

public class ConfigUtils {

    private static final String FILE_NAME = "generator.properties";

    private static final Configuration CONFIG;

    static {
        try {
            CONFIG =  new PropertiesConfiguration(FILE_NAME);
        } catch (ConfigurationException e) {
            throw new GeneratorException("获取配置文件失败，", e);
        }
    }

    public static String getString(String key) {
        return CONFIG.getString(key);
    }

    public static List<String> getStringArray(String key) {
        String[] stringArray = CONFIG.getStringArray(key);
        return Arrays.asList(stringArray);
    }

}
