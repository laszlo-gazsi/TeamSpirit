package net.gazsi.laszlo.apps.team_spirit.core;

import net.gazsi.laszlo.apps.team_spirit.config.Configuration;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.CommitService;
import org.eclipse.egit.github.core.service.OrganizationService;
import org.eclipse.egit.github.core.service.RepositoryService;

import java.io.IOException;
import java.util.List;

public class Controller {

    List<Repository> repositories;
    Configuration configuration;

    public Controller(Configuration configuration) {
        this.configuration = configuration;
    }

    public void loadRepositories() throws IOException {
        GitHubClient client = createClient();

        // public repositories
        RepositoryService repositoryService = new RepositoryService(client);
        List<Repository> repositories = repositoryService.getRepositories();
        CommitService commitService = new CommitService();

        // organization repositories
        OrganizationService organizationService = new OrganizationService(client);
        List<User> organizations = organizationService.getOrganizations();
        for (User organization : organizations)
        {
            repositories.addAll(repositoryService.getOrgRepositories(organization.getLogin()));
        }
    }

    private GitHubClient createClient()
    {
        GitHubClient client = new GitHubClient();
        client.setCredentials(configuration.getGitUserName(), configuration.getGitPassword());

        return client;
    }


}
