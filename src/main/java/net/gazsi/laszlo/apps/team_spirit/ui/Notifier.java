package net.gazsi.laszlo.apps.team_spirit.ui;

import java.io.IOException;

public class Notifier {

    public static final String NOTIFY_SYSTEM_COMMAND = "notify-send";

    public static void displayNotification(String repositoryName, String commitAuthor, String commitMessage) {
        String[] command = {
                NOTIFY_SYSTEM_COMMAND,
                "-t",
                "2000",
                repositoryName,
                commitMessage
        };

        try {
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();  //nothing else can be done here.
        }
    }
}
