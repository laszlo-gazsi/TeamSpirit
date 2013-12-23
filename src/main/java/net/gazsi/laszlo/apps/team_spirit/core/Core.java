package net.gazsi.laszlo.apps.team_spirit.core;

import net.gazsi.laszlo.apps.team_spirit.config.Configurator;

public class Core {

    public static void main(String[] args) throws Exception {

        Controller controller = new Controller(new Configurator().getConfiguration());
        controller.loadRepositories();
    }
}
