TeamSpirit
==========

TeamSpirit is a simple Java-based application that will display a notification on your desktop if somebody has pushed new commits to one of the GitHub repositories you have access to.

Compatibility
-------------
The application uses the 'notify-send' system command to display the notification, so it will work on all modern Linux shells. For Windows users the 'Growl for Windows' could be a good option. (you still need to create a bridge app between 'notify-send' and 'Growl for Windows')

Approach
--------
I have used the <a href="https://github.com/eclipse/egit-github">EGit-GitHub</a> API to get the repositories and commits of a user, including the repositories of the organizations the user is part of. This is not be the optimal solution, but I wanted to give this API a try. Fetching the RSS of changes could be a lot easier and faster than my approach.
