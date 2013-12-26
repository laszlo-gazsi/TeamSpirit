package net.gazsi.laszlo.apps.team_spirit.repository;

import net.gazsi.laszlo.apps.team_spirit.config.Configuration;
import net.gazsi.laszlo.apps.team_spirit.ui.Notifier;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.client.PageIterator;
import org.eclipse.egit.github.core.service.CommitService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class PushChecker {

    Store store = new Store();
    Configuration configuration;

    public PushChecker(Configuration configuration) {
        this.configuration = configuration;

        try {

            store.loadRepositories(createClient());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkForGitPushes(Date lastCheckedAt) throws IOException {

        final GitHubClient client = createClient();

        for (Repository repository : store.getRepositories()) {

            Collection<RepositoryCommit> commits = getCommits(client, repository);

            for (RepositoryCommit commit : commits) {
                Date date = commit.getCommit().getAuthor().getDate();
                if (date.after(lastCheckedAt)) {
                    Notifier.displayNotification(repository.getName(), commit.getCommit().getMessage());
                }
            }
        }
    }

    private Collection<RepositoryCommit> getCommits(GitHubClient client, Repository repository) {
        CommitService commitService = new CommitService(client);

        Collection<RepositoryCommit> commits = new ArrayList<>(0);

        try {
            PageIterator<RepositoryCommit> commitPageIterator = commitService.pageCommits(repository, 3);

            if (commitPageIterator.hasNext()) {
                commits = commitPageIterator.next();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return commits;
    }

    private GitHubClient createClient() {
        GitHubClient client = new GitHubClient();
        client.setCredentials(configuration.getGitUserName(), configuration.getGitPassword());

        return client;
    }
}
