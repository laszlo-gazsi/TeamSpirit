package net.gazsi.laszlo.apps.team_spirit.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class Configurator {

    public static final String CONFIGURATION_FILE_NAME = "config";
    public static final String CONFIGURATION_USERNAME_KEY = "username";
    public static final String CONFIGURATION_PASSWORD_KEY = "password";
    public static final String CONFIGURATION_BLACKLIST_KEY = "blacklist";

    Configuration configuration;

    public Configuration getConfiguration() throws IOException {
        if (configuration == null)
        {
            configuration = configure();
        }

        return configuration;
    }

    private Configuration configure() throws IOException {
        Map<String, String> config = getRawConfig();
        return new Configuration(
                config.get(CONFIGURATION_USERNAME_KEY),
                config.get(CONFIGURATION_PASSWORD_KEY),
                null
        );
    }

    private Map<String, String> getRawConfig() throws IOException {
        String config;
        BufferedReader reader = new BufferedReader(new FileReader(CONFIGURATION_FILE_NAME));
        Map<String, String> configOptions = new Hashtable<>(3);

        while((config = reader.readLine()) != null)
        {
            String[] configOption = config.split(":");

            switch (configOption[0]){
                case CONFIGURATION_USERNAME_KEY:
                    configOptions.put(CONFIGURATION_USERNAME_KEY, configOption[1].trim());
                    break;
                case CONFIGURATION_PASSWORD_KEY:
                    configOptions.put(CONFIGURATION_PASSWORD_KEY, configOption[1].trim());
                    break;
                case CONFIGURATION_BLACKLIST_KEY:
                    configOptions.put(CONFIGURATION_BLACKLIST_KEY, "");
                    break;

            }
        }

        return configOptions;
    }
}
