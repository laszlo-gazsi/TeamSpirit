package net.gazsi.laszlo.apps.team_spirit.core;

import net.gazsi.laszlo.apps.team_spirit.config.Configurator;
import net.gazsi.laszlo.apps.team_spirit.repository.PushChecker;

import java.util.Calendar;
import java.util.Date;

import static java.lang.Thread.sleep;

public class Core {

    private static final int MINUTE = 1000 * 60;

    public static void main(String[] args) throws Exception {

        PushChecker pushChecker = new PushChecker(new Configurator().getConfiguration());

        Date lastChecked = Calendar.getInstance().getTime();

        while(true)
        {
            pushChecker.checkForGitPushes(lastChecked);
            lastChecked = Calendar.getInstance().getTime();

            sleep(MINUTE * 5);
        }
    }
}
