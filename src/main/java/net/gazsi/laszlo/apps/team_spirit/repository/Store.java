package net.gazsi.laszlo.apps.team_spirit.repository;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.CommitService;
import org.eclipse.egit.github.core.service.OrganizationService;
import org.eclipse.egit.github.core.service.RepositoryService;

import java.io.IOException;
import java.util.List;

public class Store {
    List<Repository> repositories;

    public void loadRepositories(GitHubClient client) throws IOException {

        // public repositories
        RepositoryService repositoryService = new RepositoryService(client);
        repositories = repositoryService.getRepositories();
        CommitService commitService = new CommitService();

        // organization repositories
        OrganizationService organizationService = new OrganizationService(client);
        List<User> organizations = organizationService.getOrganizations();
        for (User organization : organizations)
        {
            repositories.addAll(repositoryService.getOrgRepositories(organization.getLogin()));
        }
    }

    public List<Repository> getRepositories() {
        return repositories;
    }
}
