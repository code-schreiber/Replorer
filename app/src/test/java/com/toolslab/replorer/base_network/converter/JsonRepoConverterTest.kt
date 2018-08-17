package com.toolslab.replorer.base_network.converter

import com.toolslab.replorer.base_network.model.License
import com.toolslab.replorer.base_network.model.Repo
import com.toolslab.replorer.converter.JsonRepoConverter
import org.amshove.kluent.shouldEqual
import org.intellij.lang.annotations.Language
import org.junit.Test

class JsonRepoConverterTest {

    private val repo0 = Repo("android-assessment", "", "2018-01-23T14:50:58Z", "Java", License(""))
    private val repo1 = Repo("AndroidStarterKit", "🏎️ Starter kit for kicking off a basic Android project 🚀", "2018-08-01T08:06:16Z", "Kotlin", License("MIT License"))

    private val underTest = JsonRepoConverter()

    @Test
    fun convertEmptyString() {
        underTest.convert("") shouldEqual emptyList()
        underTest.convert(" ") shouldEqual emptyList()
        underTest.convert("  ") shouldEqual emptyList()
        underTest.convert("\n") shouldEqual emptyList()
        underTest.convert("\t") shouldEqual emptyList()
        underTest.convert("  \n\t") shouldEqual emptyList()
    }

    @Test
    fun convertSimpleJson() {
        val repos = underTest.convert(simpleJson.asIfFromServer())

        repos.size shouldEqual 2
        repos[0] shouldEqual repo0
        repos[1] shouldEqual repo1
    }

    @Test
    fun convert() {
        val repos = underTest.convert(json.asIfFromServer())

        repos.size shouldEqual 2
        repos[0] shouldEqual repo0
        repos[1] shouldEqual repo1
    }

    private fun String.asIfFromServer() =
            this.trimIndent()
                    .replace("\n ", "")
                    .replace(" {", "{")
                    .replace(": ", ":")

    @Language("JSON")
    private val simpleJson = """
        [
          {
            "name": "android-assessment",
            "description": null,
            "updated_at": "2018-01-23T14:50:58Z",
            "language": "Java",
            "license": null
          },
          {
            "name": "AndroidStarterKit",
            "description": "🏎️ Starter kit for kicking off a basic Android project 🚀",
            "updated_at": "2018-08-01T08:06:16Z",
            "language": "Kotlin",
            "license": {
              "key": "mit",
              "name": "MIT License",
              "spdx_id": "MIT",
              "url": "https://api.github.com/licenses/mit",
              "node_id": "MDc6TGljZW5zZTEz"
            }
          }
        ]
     """
    @Language("JSON")
    private val json = """
        [
          {
            "id": 14054754,
            "node_id": "MDEwOlJlcG9zaXRvcnkxNDA1NDc1NA==",
            "name": "android-assessment",
            "full_name": "code-schreiber/android-assessment",
            "owner": {
              "login": "code-schreiber",
              "id": 759086,
              "node_id": "MDQ6VXNlcjc1OTA4Ng==",
              "avatar_url": "https://avatars3.githubusercontent.com/u/759086?v=4",
              "gravatar_id": "",
              "url": "https://api.github.com/users/code-schreiber",
              "html_url": "https://github.com/code-schreiber",
              "followers_url": "https://api.github.com/users/code-schreiber/followers",
              "following_url": "https://api.github.com/users/code-schreiber/following{/other_user}",
              "gists_url": "https://api.github.com/users/code-schreiber/gists{/gist_id}",
              "starred_url": "https://api.github.com/users/code-schreiber/starred{/owner}{/repo}",
              "subscriptions_url": "https://api.github.com/users/code-schreiber/subscriptions",
              "organizations_url": "https://api.github.com/users/code-schreiber/orgs",
              "repos_url": "https://api.github.com/users/code-schreiber/repos",
              "events_url": "https://api.github.com/users/code-schreiber/events{/privacy}",
              "received_events_url": "https://api.github.com/users/code-schreiber/received_events",
              "type": "User",
              "site_admin": false
            },
            "private": false,
            "html_url": "https://github.com/code-schreiber/android-assessment",
            "description": null,
            "fork": false,
            "url": "https://api.github.com/repos/code-schreiber/android-assessment",
            "forks_url": "https://api.github.com/repos/code-schreiber/android-assessment/forks",
            "keys_url": "https://api.github.com/repos/code-schreiber/android-assessment/keys{/key_id}",
            "collaborators_url": "https://api.github.com/repos/code-schreiber/android-assessment/collaborators{/collaborator}",
            "teams_url": "https://api.github.com/repos/code-schreiber/android-assessment/teams",
            "hooks_url": "https://api.github.com/repos/code-schreiber/android-assessment/hooks",
            "issue_events_url": "https://api.github.com/repos/code-schreiber/android-assessment/issues/events{/number}",
            "events_url": "https://api.github.com/repos/code-schreiber/android-assessment/events",
            "assignees_url": "https://api.github.com/repos/code-schreiber/android-assessment/assignees{/user}",
            "branches_url": "https://api.github.com/repos/code-schreiber/android-assessment/branches{/branch}",
            "tags_url": "https://api.github.com/repos/code-schreiber/android-assessment/tags",
            "blobs_url": "https://api.github.com/repos/code-schreiber/android-assessment/git/blobs{/sha}",
            "git_tags_url": "https://api.github.com/repos/code-schreiber/android-assessment/git/tags{/sha}",
            "git_refs_url": "https://api.github.com/repos/code-schreiber/android-assessment/git/refs{/sha}",
            "trees_url": "https://api.github.com/repos/code-schreiber/android-assessment/git/trees{/sha}",
            "statuses_url": "https://api.github.com/repos/code-schreiber/android-assessment/statuses/{sha}",
            "languages_url": "https://api.github.com/repos/code-schreiber/android-assessment/languages",
            "stargazers_url": "https://api.github.com/repos/code-schreiber/android-assessment/stargazers",
            "contributors_url": "https://api.github.com/repos/code-schreiber/android-assessment/contributors",
            "subscribers_url": "https://api.github.com/repos/code-schreiber/android-assessment/subscribers",
            "subscription_url": "https://api.github.com/repos/code-schreiber/android-assessment/subscription",
            "commits_url": "https://api.github.com/repos/code-schreiber/android-assessment/commits{/sha}",
            "git_commits_url": "https://api.github.com/repos/code-schreiber/android-assessment/git/commits{/sha}",
            "comments_url": "https://api.github.com/repos/code-schreiber/android-assessment/comments{/number}",
            "issue_comment_url": "https://api.github.com/repos/code-schreiber/android-assessment/issues/comments{/number}",
            "contents_url": "https://api.github.com/repos/code-schreiber/android-assessment/contents/{+path}",
            "compare_url": "https://api.github.com/repos/code-schreiber/android-assessment/compare/{base}...{head}",
            "merges_url": "https://api.github.com/repos/code-schreiber/android-assessment/merges",
            "archive_url": "https://api.github.com/repos/code-schreiber/android-assessment/{archive_format}{/ref}",
            "downloads_url": "https://api.github.com/repos/code-schreiber/android-assessment/downloads",
            "issues_url": "https://api.github.com/repos/code-schreiber/android-assessment/issues{/number}",
            "pulls_url": "https://api.github.com/repos/code-schreiber/android-assessment/pulls{/number}",
            "milestones_url": "https://api.github.com/repos/code-schreiber/android-assessment/milestones{/number}",
            "notifications_url": "https://api.github.com/repos/code-schreiber/android-assessment/notifications{?since,all,participating}",
            "labels_url": "https://api.github.com/repos/code-schreiber/android-assessment/labels{/name}",
            "releases_url": "https://api.github.com/repos/code-schreiber/android-assessment/releases{/id}",
            "deployments_url": "https://api.github.com/repos/code-schreiber/android-assessment/deployments",
            "created_at": "2013-11-01T21:34:12Z",
            "updated_at": "2018-01-23T14:50:58Z",
            "pushed_at": "2018-01-23T14:50:56Z",
            "git_url": "git://github.com/code-schreiber/android-assessment.git",
            "ssh_url": "git@github.com:code-schreiber/android-assessment.git",
            "clone_url": "https://github.com/code-schreiber/android-assessment.git",
            "svn_url": "https://github.com/code-schreiber/android-assessment",
            "homepage": null,
            "size": 1447,
            "stargazers_count": 0,
            "watchers_count": 0,
            "language": "Java",
            "has_issues": true,
            "has_projects": true,
            "has_downloads": true,
            "has_wiki": true,
            "has_pages": false,
            "forks_count": 0,
            "mirror_url": null,
            "archived": false,
            "open_issues_count": 0,
            "license": null,
            "forks": 0,
            "open_issues": 0,
            "watchers": 0,
            "default_branch": "master"
          },
          {
            "id": 139860750,
            "node_id": "MDEwOlJlcG9zaXRvcnkxMzk4NjA3NTA=",
            "name": "AndroidStarterKit",
            "full_name": "code-schreiber/AndroidStarterKit",
            "owner": {
              "login": "code-schreiber",
              "id": 759086,
              "node_id": "MDQ6VXNlcjc1OTA4Ng==",
              "avatar_url": "https://avatars3.githubusercontent.com/u/759086?v=4",
              "gravatar_id": "",
              "url": "https://api.github.com/users/code-schreiber",
              "html_url": "https://github.com/code-schreiber",
              "followers_url": "https://api.github.com/users/code-schreiber/followers",
              "following_url": "https://api.github.com/users/code-schreiber/following{/other_user}",
              "gists_url": "https://api.github.com/users/code-schreiber/gists{/gist_id}",
              "starred_url": "https://api.github.com/users/code-schreiber/starred{/owner}{/repo}",
              "subscriptions_url": "https://api.github.com/users/code-schreiber/subscriptions",
              "organizations_url": "https://api.github.com/users/code-schreiber/orgs",
              "repos_url": "https://api.github.com/users/code-schreiber/repos",
              "events_url": "https://api.github.com/users/code-schreiber/events{/privacy}",
              "received_events_url": "https://api.github.com/users/code-schreiber/received_events",
              "type": "User",
              "site_admin": false
            },
            "private": false,
            "html_url": "https://github.com/code-schreiber/AndroidStarterKit",
            "description": "🏎️ Starter kit for kicking off a basic Android project 🚀",
            "fork": false,
            "url": "https://api.github.com/repos/code-schreiber/AndroidStarterKit",
            "forks_url": "https://api.github.com/repos/code-schreiber/AndroidStarterKit/forks",
            "keys_url": "https://api.github.com/repos/code-schreiber/AndroidStarterKit/keys{/key_id}",
            "collaborators_url": "https://api.github.com/repos/code-schreiber/AndroidStarterKit/collaborators{/collaborator}",
            "teams_url": "https://api.github.com/repos/code-schreiber/AndroidStarterKit/teams",
            "hooks_url": "https://api.github.com/repos/code-schreiber/AndroidStarterKit/hooks",
            "issue_events_url": "https://api.github.com/repos/code-schreiber/AndroidStarterKit/issues/events{/number}",
            "events_url": "https://api.github.com/repos/code-schreiber/AndroidStarterKit/events",
            "assignees_url": "https://api.github.com/repos/code-schreiber/AndroidStarterKit/assignees{/user}",
            "branches_url": "https://api.github.com/repos/code-schreiber/AndroidStarterKit/branches{/branch}",
            "tags_url": "https://api.github.com/repos/code-schreiber/AndroidStarterKit/tags",
            "blobs_url": "https://api.github.com/repos/code-schreiber/AndroidStarterKit/git/blobs{/sha}",
            "git_tags_url": "https://api.github.com/repos/code-schreiber/AndroidStarterKit/git/tags{/sha}",
            "git_refs_url": "https://api.github.com/repos/code-schreiber/AndroidStarterKit/git/refs{/sha}",
            "trees_url": "https://api.github.com/repos/code-schreiber/AndroidStarterKit/git/trees{/sha}",
            "statuses_url": "https://api.github.com/repos/code-schreiber/AndroidStarterKit/statuses/{sha}",
            "languages_url": "https://api.github.com/repos/code-schreiber/AndroidStarterKit/languages",
            "stargazers_url": "https://api.github.com/repos/code-schreiber/AndroidStarterKit/stargazers",
            "contributors_url": "https://api.github.com/repos/code-schreiber/AndroidStarterKit/contributors",
            "subscribers_url": "https://api.github.com/repos/code-schreiber/AndroidStarterKit/subscribers",
            "subscription_url": "https://api.github.com/repos/code-schreiber/AndroidStarterKit/subscription",
            "commits_url": "https://api.github.com/repos/code-schreiber/AndroidStarterKit/commits{/sha}",
            "git_commits_url": "https://api.github.com/repos/code-schreiber/AndroidStarterKit/git/commits{/sha}",
            "comments_url": "https://api.github.com/repos/code-schreiber/AndroidStarterKit/comments{/number}",
            "issue_comment_url": "https://api.github.com/repos/code-schreiber/AndroidStarterKit/issues/comments{/number}",
            "contents_url": "https://api.github.com/repos/code-schreiber/AndroidStarterKit/contents/{+path}",
            "compare_url": "https://api.github.com/repos/code-schreiber/AndroidStarterKit/compare/{base}...{head}",
            "merges_url": "https://api.github.com/repos/code-schreiber/AndroidStarterKit/merges",
            "archive_url": "https://api.github.com/repos/code-schreiber/AndroidStarterKit/{archive_format}{/ref}",
            "downloads_url": "https://api.github.com/repos/code-schreiber/AndroidStarterKit/downloads",
            "issues_url": "https://api.github.com/repos/code-schreiber/AndroidStarterKit/issues{/number}",
            "pulls_url": "https://api.github.com/repos/code-schreiber/AndroidStarterKit/pulls{/number}",
            "milestones_url": "https://api.github.com/repos/code-schreiber/AndroidStarterKit/milestones{/number}",
            "notifications_url": "https://api.github.com/repos/code-schreiber/AndroidStarterKit/notifications{?since,all,participating}",
            "labels_url": "https://api.github.com/repos/code-schreiber/AndroidStarterKit/labels{/name}",
            "releases_url": "https://api.github.com/repos/code-schreiber/AndroidStarterKit/releases{/id}",
            "deployments_url": "https://api.github.com/repos/code-schreiber/AndroidStarterKit/deployments",
            "created_at": "2018-07-05T14:25:27Z",
            "updated_at": "2018-08-01T08:06:16Z",
            "pushed_at": "2018-08-01T08:06:14Z",
            "git_url": "git://github.com/code-schreiber/AndroidStarterKit.git",
            "ssh_url": "git@github.com:code-schreiber/AndroidStarterKit.git",
            "clone_url": "https://github.com/code-schreiber/AndroidStarterKit.git",
            "svn_url": "https://github.com/code-schreiber/AndroidStarterKit",
            "homepage": null,
            "size": 90,
            "stargazers_count": 0,
            "watchers_count": 0,
            "language": "Kotlin",
            "has_issues": true,
            "has_projects": true,
            "has_downloads": true,
            "has_wiki": true,
            "has_pages": false,
            "forks_count": 0,
            "mirror_url": null,
            "archived": false,
            "open_issues_count": 0,
            "license": {
              "key": "mit",
              "name": "MIT License",
              "spdx_id": "MIT",
              "url": "https://api.github.com/licenses/mit",
              "node_id": "MDc6TGljZW5zZTEz"
            },
            "forks": 0,
            "open_issues": 0,
            "watchers": 0,
            "default_branch": "master"
          }
        ]
     """

}
