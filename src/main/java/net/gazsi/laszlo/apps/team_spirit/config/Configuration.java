package net.gazsi.laszlo.apps.team_spirit.config;

import java.util.Set;

public class Configuration {

    private String gitUserName;
    private String gitPassword;
    private Set<String> blackList;

    public Configuration(String gitUserName, String gitPassword, Set<String> blackList) {
        this.gitUserName = gitUserName;
        this.gitPassword = gitPassword;
        this.blackList = blackList;
    }

    public String getGitUserName() {
        return gitUserName;
    }

    public String getGitPassword() {
        return gitPassword;
    }

    public Set<String> getBlackList() {
        return blackList;
    }
}
