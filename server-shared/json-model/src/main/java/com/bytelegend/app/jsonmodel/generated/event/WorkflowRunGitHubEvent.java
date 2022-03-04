
package com.bytelegend.app.jsonmodel.generated.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "action",
    "organization",
    "repository",
    "sender",
    "workflow",
    "workflow_run"
})
@Generated("jsonschema2pojo")
public class WorkflowRunGitHubEvent implements com.bytelegend.app.jsonmodel.event.GitHubEvent {

    @JsonProperty("action")
    private String action;
    @JsonProperty("organization")
    private WorkflowRunGitHubEvent.Organization organization;
    @JsonProperty("repository")
    private WorkflowRunGitHubEvent.Repository repository;
    @JsonProperty("sender")
    private WorkflowRunGitHubEvent.Sender sender;
    @JsonProperty("workflow")
    private WorkflowRunGitHubEvent.Workflow workflow;
    @JsonProperty("workflow_run")
    private WorkflowRunGitHubEvent.WorkflowRun workflowRun;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public WorkflowRunGitHubEvent() {
    }

    /**
     * 
     * @param workflow
     * @param workflowRun
     * @param sender
     * @param organization
     * @param action
     * @param repository
     */
    public WorkflowRunGitHubEvent(String action, WorkflowRunGitHubEvent.Organization organization, WorkflowRunGitHubEvent.Repository repository, WorkflowRunGitHubEvent.Sender sender, WorkflowRunGitHubEvent.Workflow workflow, WorkflowRunGitHubEvent.WorkflowRun workflowRun) {
        super();
        this.action = action;
        this.organization = organization;
        this.repository = repository;
        this.sender = sender;
        this.workflow = workflow;
        this.workflowRun = workflowRun;
    }

    @JsonProperty("action")
    public String getAction() {
        return action;
    }

    @JsonProperty("action")
    public void setAction(String action) {
        this.action = action;
    }

    @JsonProperty("organization")
    public WorkflowRunGitHubEvent.Organization getOrganization() {
        return organization;
    }

    @JsonProperty("organization")
    public void setOrganization(WorkflowRunGitHubEvent.Organization organization) {
        this.organization = organization;
    }

    @JsonProperty("repository")
    public WorkflowRunGitHubEvent.Repository getRepository() {
        return repository;
    }

    @JsonProperty("repository")
    public void setRepository(WorkflowRunGitHubEvent.Repository repository) {
        this.repository = repository;
    }

    @JsonProperty("sender")
    public WorkflowRunGitHubEvent.Sender getSender() {
        return sender;
    }

    @JsonProperty("sender")
    public void setSender(WorkflowRunGitHubEvent.Sender sender) {
        this.sender = sender;
    }

    @JsonProperty("workflow")
    public WorkflowRunGitHubEvent.Workflow getWorkflow() {
        return workflow;
    }

    @JsonProperty("workflow")
    public void setWorkflow(WorkflowRunGitHubEvent.Workflow workflow) {
        this.workflow = workflow;
    }

    @JsonProperty("workflow_run")
    public WorkflowRunGitHubEvent.WorkflowRun getWorkflowRun() {
        return workflowRun;
    }

    @JsonProperty("workflow_run")
    public void setWorkflowRun(WorkflowRunGitHubEvent.WorkflowRun workflowRun) {
        this.workflowRun = workflowRun;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(WorkflowRunGitHubEvent.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("action");
        sb.append('=');
        sb.append(((this.action == null)?"<null>":this.action));
        sb.append(',');
        sb.append("organization");
        sb.append('=');
        sb.append(((this.organization == null)?"<null>":this.organization));
        sb.append(',');
        sb.append("repository");
        sb.append('=');
        sb.append(((this.repository == null)?"<null>":this.repository));
        sb.append(',');
        sb.append("sender");
        sb.append('=');
        sb.append(((this.sender == null)?"<null>":this.sender));
        sb.append(',');
        sb.append("workflow");
        sb.append('=');
        sb.append(((this.workflow == null)?"<null>":this.workflow));
        sb.append(',');
        sb.append("workflowRun");
        sb.append('=');
        sb.append(((this.workflowRun == null)?"<null>":this.workflowRun));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.workflow == null)? 0 :this.workflow.hashCode()));
        result = ((result* 31)+((this.workflowRun == null)? 0 :this.workflowRun.hashCode()));
        result = ((result* 31)+((this.sender == null)? 0 :this.sender.hashCode()));
        result = ((result* 31)+((this.organization == null)? 0 :this.organization.hashCode()));
        result = ((result* 31)+((this.action == null)? 0 :this.action.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.repository == null)? 0 :this.repository.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof WorkflowRunGitHubEvent) == false) {
            return false;
        }
        WorkflowRunGitHubEvent rhs = ((WorkflowRunGitHubEvent) other);
        return ((((((((this.workflow == rhs.workflow)||((this.workflow!= null)&&this.workflow.equals(rhs.workflow)))&&((this.workflowRun == rhs.workflowRun)||((this.workflowRun!= null)&&this.workflowRun.equals(rhs.workflowRun))))&&((this.sender == rhs.sender)||((this.sender!= null)&&this.sender.equals(rhs.sender))))&&((this.organization == rhs.organization)||((this.organization!= null)&&this.organization.equals(rhs.organization))))&&((this.action == rhs.action)||((this.action!= null)&&this.action.equals(rhs.action))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.repository == rhs.repository)||((this.repository!= null)&&this.repository.equals(rhs.repository))));
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "email",
        "name"
    })
    @Generated("jsonschema2pojo")
    public static class Author {

        @JsonProperty("email")
        private String email;
        @JsonProperty("name")
        private String name;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Author() {
        }

        /**
         * 
         * @param name
         * @param email
         */
        public Author(String email, String name) {
            super();
            this.email = email;
            this.name = name;
        }

        @JsonProperty("email")
        public String getEmail() {
            return email;
        }

        @JsonProperty("email")
        public void setEmail(String email) {
            this.email = email;
        }

        @JsonProperty("name")
        public String getName() {
            return name;
        }

        @JsonProperty("name")
        public void setName(String name) {
            this.name = name;
        }

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(WorkflowRunGitHubEvent.Author.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("email");
            sb.append('=');
            sb.append(((this.email == null)?"<null>":this.email));
            sb.append(',');
            sb.append("name");
            sb.append('=');
            sb.append(((this.name == null)?"<null>":this.name));
            sb.append(',');
            sb.append("additionalProperties");
            sb.append('=');
            sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
            sb.append(',');
            if (sb.charAt((sb.length()- 1)) == ',') {
                sb.setCharAt((sb.length()- 1), ']');
            } else {
                sb.append(']');
            }
            return sb.toString();
        }

        @Override
        public int hashCode() {
            int result = 1;
            result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.email == null)? 0 :this.email.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof WorkflowRunGitHubEvent.Author) == false) {
                return false;
            }
            WorkflowRunGitHubEvent.Author rhs = ((WorkflowRunGitHubEvent.Author) other);
            return ((((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.email == rhs.email)||((this.email!= null)&&this.email.equals(rhs.email))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "email",
        "name"
    })
    @Generated("jsonschema2pojo")
    public static class Committer {

        @JsonProperty("email")
        private String email;
        @JsonProperty("name")
        private String name;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Committer() {
        }

        /**
         * 
         * @param name
         * @param email
         */
        public Committer(String email, String name) {
            super();
            this.email = email;
            this.name = name;
        }

        @JsonProperty("email")
        public String getEmail() {
            return email;
        }

        @JsonProperty("email")
        public void setEmail(String email) {
            this.email = email;
        }

        @JsonProperty("name")
        public String getName() {
            return name;
        }

        @JsonProperty("name")
        public void setName(String name) {
            this.name = name;
        }

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(WorkflowRunGitHubEvent.Committer.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("email");
            sb.append('=');
            sb.append(((this.email == null)?"<null>":this.email));
            sb.append(',');
            sb.append("name");
            sb.append('=');
            sb.append(((this.name == null)?"<null>":this.name));
            sb.append(',');
            sb.append("additionalProperties");
            sb.append('=');
            sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
            sb.append(',');
            if (sb.charAt((sb.length()- 1)) == ',') {
                sb.setCharAt((sb.length()- 1), ']');
            } else {
                sb.append(']');
            }
            return sb.toString();
        }

        @Override
        public int hashCode() {
            int result = 1;
            result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.email == null)? 0 :this.email.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof WorkflowRunGitHubEvent.Committer) == false) {
                return false;
            }
            WorkflowRunGitHubEvent.Committer rhs = ((WorkflowRunGitHubEvent.Committer) other);
            return ((((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.email == rhs.email)||((this.email!= null)&&this.email.equals(rhs.email))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "author",
        "committer",
        "id",
        "message",
        "timestamp",
        "tree_id"
    })
    @Generated("jsonschema2pojo")
    public static class HeadCommit {

        @JsonProperty("author")
        private WorkflowRunGitHubEvent.Author author;
        @JsonProperty("committer")
        private WorkflowRunGitHubEvent.Committer committer;
        @JsonProperty("id")
        private String id;
        @JsonProperty("message")
        private String message;
        @JsonProperty("timestamp")
        private String timestamp;
        @JsonProperty("tree_id")
        private String treeId;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public HeadCommit() {
        }

        /**
         * 
         * @param treeId
         * @param committer
         * @param author
         * @param id
         * @param message
         * @param timestamp
         */
        public HeadCommit(WorkflowRunGitHubEvent.Author author, WorkflowRunGitHubEvent.Committer committer, String id, String message, String timestamp, String treeId) {
            super();
            this.author = author;
            this.committer = committer;
            this.id = id;
            this.message = message;
            this.timestamp = timestamp;
            this.treeId = treeId;
        }

        @JsonProperty("author")
        public WorkflowRunGitHubEvent.Author getAuthor() {
            return author;
        }

        @JsonProperty("author")
        public void setAuthor(WorkflowRunGitHubEvent.Author author) {
            this.author = author;
        }

        @JsonProperty("committer")
        public WorkflowRunGitHubEvent.Committer getCommitter() {
            return committer;
        }

        @JsonProperty("committer")
        public void setCommitter(WorkflowRunGitHubEvent.Committer committer) {
            this.committer = committer;
        }

        @JsonProperty("id")
        public String getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(String id) {
            this.id = id;
        }

        @JsonProperty("message")
        public String getMessage() {
            return message;
        }

        @JsonProperty("message")
        public void setMessage(String message) {
            this.message = message;
        }

        @JsonProperty("timestamp")
        public String getTimestamp() {
            return timestamp;
        }

        @JsonProperty("timestamp")
        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        @JsonProperty("tree_id")
        public String getTreeId() {
            return treeId;
        }

        @JsonProperty("tree_id")
        public void setTreeId(String treeId) {
            this.treeId = treeId;
        }

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(WorkflowRunGitHubEvent.HeadCommit.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("author");
            sb.append('=');
            sb.append(((this.author == null)?"<null>":this.author));
            sb.append(',');
            sb.append("committer");
            sb.append('=');
            sb.append(((this.committer == null)?"<null>":this.committer));
            sb.append(',');
            sb.append("id");
            sb.append('=');
            sb.append(((this.id == null)?"<null>":this.id));
            sb.append(',');
            sb.append("message");
            sb.append('=');
            sb.append(((this.message == null)?"<null>":this.message));
            sb.append(',');
            sb.append("timestamp");
            sb.append('=');
            sb.append(((this.timestamp == null)?"<null>":this.timestamp));
            sb.append(',');
            sb.append("treeId");
            sb.append('=');
            sb.append(((this.treeId == null)?"<null>":this.treeId));
            sb.append(',');
            sb.append("additionalProperties");
            sb.append('=');
            sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
            sb.append(',');
            if (sb.charAt((sb.length()- 1)) == ',') {
                sb.setCharAt((sb.length()- 1), ']');
            } else {
                sb.append(']');
            }
            return sb.toString();
        }

        @Override
        public int hashCode() {
            int result = 1;
            result = ((result* 31)+((this.treeId == null)? 0 :this.treeId.hashCode()));
            result = ((result* 31)+((this.committer == null)? 0 :this.committer.hashCode()));
            result = ((result* 31)+((this.author == null)? 0 :this.author.hashCode()));
            result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.message == null)? 0 :this.message.hashCode()));
            result = ((result* 31)+((this.timestamp == null)? 0 :this.timestamp.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof WorkflowRunGitHubEvent.HeadCommit) == false) {
                return false;
            }
            WorkflowRunGitHubEvent.HeadCommit rhs = ((WorkflowRunGitHubEvent.HeadCommit) other);
            return ((((((((this.treeId == rhs.treeId)||((this.treeId!= null)&&this.treeId.equals(rhs.treeId)))&&((this.committer == rhs.committer)||((this.committer!= null)&&this.committer.equals(rhs.committer))))&&((this.author == rhs.author)||((this.author!= null)&&this.author.equals(rhs.author))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.message == rhs.message)||((this.message!= null)&&this.message.equals(rhs.message))))&&((this.timestamp == rhs.timestamp)||((this.timestamp!= null)&&this.timestamp.equals(rhs.timestamp))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "archive_url",
        "assignees_url",
        "blobs_url",
        "branches_url",
        "collaborators_url",
        "comments_url",
        "commits_url",
        "compare_url",
        "contents_url",
        "contributors_url",
        "deployments_url",
        "description",
        "downloads_url",
        "events_url",
        "fork",
        "forks_url",
        "full_name",
        "git_commits_url",
        "git_refs_url",
        "git_tags_url",
        "hooks_url",
        "html_url",
        "id",
        "issue_comment_url",
        "issue_events_url",
        "issues_url",
        "keys_url",
        "labels_url",
        "languages_url",
        "merges_url",
        "milestones_url",
        "name",
        "node_id",
        "notifications_url",
        "owner",
        "private",
        "pulls_url",
        "releases_url",
        "stargazers_url",
        "statuses_url",
        "subscribers_url",
        "subscription_url",
        "tags_url",
        "teams_url",
        "trees_url",
        "url"
    })
    @Generated("jsonschema2pojo")
    public static class HeadRepository {

        @JsonProperty("archive_url")
        private String archiveUrl;
        @JsonProperty("assignees_url")
        private String assigneesUrl;
        @JsonProperty("blobs_url")
        private String blobsUrl;
        @JsonProperty("branches_url")
        private String branchesUrl;
        @JsonProperty("collaborators_url")
        private String collaboratorsUrl;
        @JsonProperty("comments_url")
        private String commentsUrl;
        @JsonProperty("commits_url")
        private String commitsUrl;
        @JsonProperty("compare_url")
        private String compareUrl;
        @JsonProperty("contents_url")
        private String contentsUrl;
        @JsonProperty("contributors_url")
        private String contributorsUrl;
        @JsonProperty("deployments_url")
        private String deploymentsUrl;
        @JsonProperty("description")
        private Object description;
        @JsonProperty("downloads_url")
        private String downloadsUrl;
        @JsonProperty("events_url")
        private String eventsUrl;
        @JsonProperty("fork")
        private Boolean fork;
        @JsonProperty("forks_url")
        private String forksUrl;
        @JsonProperty("full_name")
        private String fullName;
        @JsonProperty("git_commits_url")
        private String gitCommitsUrl;
        @JsonProperty("git_refs_url")
        private String gitRefsUrl;
        @JsonProperty("git_tags_url")
        private String gitTagsUrl;
        @JsonProperty("hooks_url")
        private String hooksUrl;
        @JsonProperty("html_url")
        private String htmlUrl;
        @JsonProperty("id")
        private Long id;
        @JsonProperty("issue_comment_url")
        private String issueCommentUrl;
        @JsonProperty("issue_events_url")
        private String issueEventsUrl;
        @JsonProperty("issues_url")
        private String issuesUrl;
        @JsonProperty("keys_url")
        private String keysUrl;
        @JsonProperty("labels_url")
        private String labelsUrl;
        @JsonProperty("languages_url")
        private String languagesUrl;
        @JsonProperty("merges_url")
        private String mergesUrl;
        @JsonProperty("milestones_url")
        private String milestonesUrl;
        @JsonProperty("name")
        private String name;
        @JsonProperty("node_id")
        private String nodeId;
        @JsonProperty("notifications_url")
        private String notificationsUrl;
        @JsonProperty("owner")
        private WorkflowRunGitHubEvent.Owner2 owner;
        @JsonProperty("private")
        private Boolean _private;
        @JsonProperty("pulls_url")
        private String pullsUrl;
        @JsonProperty("releases_url")
        private String releasesUrl;
        @JsonProperty("stargazers_url")
        private String stargazersUrl;
        @JsonProperty("statuses_url")
        private String statusesUrl;
        @JsonProperty("subscribers_url")
        private String subscribersUrl;
        @JsonProperty("subscription_url")
        private String subscriptionUrl;
        @JsonProperty("tags_url")
        private String tagsUrl;
        @JsonProperty("teams_url")
        private String teamsUrl;
        @JsonProperty("trees_url")
        private String treesUrl;
        @JsonProperty("url")
        private String url;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public HeadRepository() {
        }

        /**
         * 
         * @param teamsUrl
         * @param blobsUrl
         * @param issueEventsUrl
         * @param milestonesUrl
         * @param issuesUrl
         * @param releasesUrl
         * @param archiveUrl
         * @param description
         * @param branchesUrl
         * @param languagesUrl
         * @param assigneesUrl
         * @param contributorsUrl
         * @param commitsUrl
         * @param gitRefsUrl
         * @param hooksUrl
         * @param subscribersUrl
         * @param forksUrl
         * @param _private
         * @param issueCommentUrl
         * @param statusesUrl
         * @param id
         * @param collaboratorsUrl
         * @param owner
         * @param compareUrl
         * @param gitCommitsUrl
         * @param labelsUrl
         * @param htmlUrl
         * @param stargazersUrl
         * @param fullName
         * @param keysUrl
         * @param downloadsUrl
         * @param contentsUrl
         * @param pullsUrl
         * @param url
         * @param tagsUrl
         * @param fork
         * @param commentsUrl
         * @param treesUrl
         * @param name
         * @param deploymentsUrl
         * @param eventsUrl
         * @param gitTagsUrl
         * @param mergesUrl
         * @param notificationsUrl
         * @param nodeId
         * @param subscriptionUrl
         */
        public HeadRepository(String archiveUrl, String assigneesUrl, String blobsUrl, String branchesUrl, String collaboratorsUrl, String commentsUrl, String commitsUrl, String compareUrl, String contentsUrl, String contributorsUrl, String deploymentsUrl, Object description, String downloadsUrl, String eventsUrl, Boolean fork, String forksUrl, String fullName, String gitCommitsUrl, String gitRefsUrl, String gitTagsUrl, String hooksUrl, String htmlUrl, Long id, String issueCommentUrl, String issueEventsUrl, String issuesUrl, String keysUrl, String labelsUrl, String languagesUrl, String mergesUrl, String milestonesUrl, String name, String nodeId, String notificationsUrl, WorkflowRunGitHubEvent.Owner2 owner, Boolean _private, String pullsUrl, String releasesUrl, String stargazersUrl, String statusesUrl, String subscribersUrl, String subscriptionUrl, String tagsUrl, String teamsUrl, String treesUrl, String url) {
            super();
            this.archiveUrl = archiveUrl;
            this.assigneesUrl = assigneesUrl;
            this.blobsUrl = blobsUrl;
            this.branchesUrl = branchesUrl;
            this.collaboratorsUrl = collaboratorsUrl;
            this.commentsUrl = commentsUrl;
            this.commitsUrl = commitsUrl;
            this.compareUrl = compareUrl;
            this.contentsUrl = contentsUrl;
            this.contributorsUrl = contributorsUrl;
            this.deploymentsUrl = deploymentsUrl;
            this.description = description;
            this.downloadsUrl = downloadsUrl;
            this.eventsUrl = eventsUrl;
            this.fork = fork;
            this.forksUrl = forksUrl;
            this.fullName = fullName;
            this.gitCommitsUrl = gitCommitsUrl;
            this.gitRefsUrl = gitRefsUrl;
            this.gitTagsUrl = gitTagsUrl;
            this.hooksUrl = hooksUrl;
            this.htmlUrl = htmlUrl;
            this.id = id;
            this.issueCommentUrl = issueCommentUrl;
            this.issueEventsUrl = issueEventsUrl;
            this.issuesUrl = issuesUrl;
            this.keysUrl = keysUrl;
            this.labelsUrl = labelsUrl;
            this.languagesUrl = languagesUrl;
            this.mergesUrl = mergesUrl;
            this.milestonesUrl = milestonesUrl;
            this.name = name;
            this.nodeId = nodeId;
            this.notificationsUrl = notificationsUrl;
            this.owner = owner;
            this._private = _private;
            this.pullsUrl = pullsUrl;
            this.releasesUrl = releasesUrl;
            this.stargazersUrl = stargazersUrl;
            this.statusesUrl = statusesUrl;
            this.subscribersUrl = subscribersUrl;
            this.subscriptionUrl = subscriptionUrl;
            this.tagsUrl = tagsUrl;
            this.teamsUrl = teamsUrl;
            this.treesUrl = treesUrl;
            this.url = url;
        }

        @JsonProperty("archive_url")
        public String getArchiveUrl() {
            return archiveUrl;
        }

        @JsonProperty("archive_url")
        public void setArchiveUrl(String archiveUrl) {
            this.archiveUrl = archiveUrl;
        }

        @JsonProperty("assignees_url")
        public String getAssigneesUrl() {
            return assigneesUrl;
        }

        @JsonProperty("assignees_url")
        public void setAssigneesUrl(String assigneesUrl) {
            this.assigneesUrl = assigneesUrl;
        }

        @JsonProperty("blobs_url")
        public String getBlobsUrl() {
            return blobsUrl;
        }

        @JsonProperty("blobs_url")
        public void setBlobsUrl(String blobsUrl) {
            this.blobsUrl = blobsUrl;
        }

        @JsonProperty("branches_url")
        public String getBranchesUrl() {
            return branchesUrl;
        }

        @JsonProperty("branches_url")
        public void setBranchesUrl(String branchesUrl) {
            this.branchesUrl = branchesUrl;
        }

        @JsonProperty("collaborators_url")
        public String getCollaboratorsUrl() {
            return collaboratorsUrl;
        }

        @JsonProperty("collaborators_url")
        public void setCollaboratorsUrl(String collaboratorsUrl) {
            this.collaboratorsUrl = collaboratorsUrl;
        }

        @JsonProperty("comments_url")
        public String getCommentsUrl() {
            return commentsUrl;
        }

        @JsonProperty("comments_url")
        public void setCommentsUrl(String commentsUrl) {
            this.commentsUrl = commentsUrl;
        }

        @JsonProperty("commits_url")
        public String getCommitsUrl() {
            return commitsUrl;
        }

        @JsonProperty("commits_url")
        public void setCommitsUrl(String commitsUrl) {
            this.commitsUrl = commitsUrl;
        }

        @JsonProperty("compare_url")
        public String getCompareUrl() {
            return compareUrl;
        }

        @JsonProperty("compare_url")
        public void setCompareUrl(String compareUrl) {
            this.compareUrl = compareUrl;
        }

        @JsonProperty("contents_url")
        public String getContentsUrl() {
            return contentsUrl;
        }

        @JsonProperty("contents_url")
        public void setContentsUrl(String contentsUrl) {
            this.contentsUrl = contentsUrl;
        }

        @JsonProperty("contributors_url")
        public String getContributorsUrl() {
            return contributorsUrl;
        }

        @JsonProperty("contributors_url")
        public void setContributorsUrl(String contributorsUrl) {
            this.contributorsUrl = contributorsUrl;
        }

        @JsonProperty("deployments_url")
        public String getDeploymentsUrl() {
            return deploymentsUrl;
        }

        @JsonProperty("deployments_url")
        public void setDeploymentsUrl(String deploymentsUrl) {
            this.deploymentsUrl = deploymentsUrl;
        }

        @JsonProperty("description")
        public Object getDescription() {
            return description;
        }

        @JsonProperty("description")
        public void setDescription(Object description) {
            this.description = description;
        }

        @JsonProperty("downloads_url")
        public String getDownloadsUrl() {
            return downloadsUrl;
        }

        @JsonProperty("downloads_url")
        public void setDownloadsUrl(String downloadsUrl) {
            this.downloadsUrl = downloadsUrl;
        }

        @JsonProperty("events_url")
        public String getEventsUrl() {
            return eventsUrl;
        }

        @JsonProperty("events_url")
        public void setEventsUrl(String eventsUrl) {
            this.eventsUrl = eventsUrl;
        }

        @JsonProperty("fork")
        public Boolean getFork() {
            return fork;
        }

        @JsonProperty("fork")
        public void setFork(Boolean fork) {
            this.fork = fork;
        }

        @JsonProperty("forks_url")
        public String getForksUrl() {
            return forksUrl;
        }

        @JsonProperty("forks_url")
        public void setForksUrl(String forksUrl) {
            this.forksUrl = forksUrl;
        }

        @JsonProperty("full_name")
        public String getFullName() {
            return fullName;
        }

        @JsonProperty("full_name")
        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        @JsonProperty("git_commits_url")
        public String getGitCommitsUrl() {
            return gitCommitsUrl;
        }

        @JsonProperty("git_commits_url")
        public void setGitCommitsUrl(String gitCommitsUrl) {
            this.gitCommitsUrl = gitCommitsUrl;
        }

        @JsonProperty("git_refs_url")
        public String getGitRefsUrl() {
            return gitRefsUrl;
        }

        @JsonProperty("git_refs_url")
        public void setGitRefsUrl(String gitRefsUrl) {
            this.gitRefsUrl = gitRefsUrl;
        }

        @JsonProperty("git_tags_url")
        public String getGitTagsUrl() {
            return gitTagsUrl;
        }

        @JsonProperty("git_tags_url")
        public void setGitTagsUrl(String gitTagsUrl) {
            this.gitTagsUrl = gitTagsUrl;
        }

        @JsonProperty("hooks_url")
        public String getHooksUrl() {
            return hooksUrl;
        }

        @JsonProperty("hooks_url")
        public void setHooksUrl(String hooksUrl) {
            this.hooksUrl = hooksUrl;
        }

        @JsonProperty("html_url")
        public String getHtmlUrl() {
            return htmlUrl;
        }

        @JsonProperty("html_url")
        public void setHtmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
        }

        @JsonProperty("id")
        public Long getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(Long id) {
            this.id = id;
        }

        @JsonProperty("issue_comment_url")
        public String getIssueCommentUrl() {
            return issueCommentUrl;
        }

        @JsonProperty("issue_comment_url")
        public void setIssueCommentUrl(String issueCommentUrl) {
            this.issueCommentUrl = issueCommentUrl;
        }

        @JsonProperty("issue_events_url")
        public String getIssueEventsUrl() {
            return issueEventsUrl;
        }

        @JsonProperty("issue_events_url")
        public void setIssueEventsUrl(String issueEventsUrl) {
            this.issueEventsUrl = issueEventsUrl;
        }

        @JsonProperty("issues_url")
        public String getIssuesUrl() {
            return issuesUrl;
        }

        @JsonProperty("issues_url")
        public void setIssuesUrl(String issuesUrl) {
            this.issuesUrl = issuesUrl;
        }

        @JsonProperty("keys_url")
        public String getKeysUrl() {
            return keysUrl;
        }

        @JsonProperty("keys_url")
        public void setKeysUrl(String keysUrl) {
            this.keysUrl = keysUrl;
        }

        @JsonProperty("labels_url")
        public String getLabelsUrl() {
            return labelsUrl;
        }

        @JsonProperty("labels_url")
        public void setLabelsUrl(String labelsUrl) {
            this.labelsUrl = labelsUrl;
        }

        @JsonProperty("languages_url")
        public String getLanguagesUrl() {
            return languagesUrl;
        }

        @JsonProperty("languages_url")
        public void setLanguagesUrl(String languagesUrl) {
            this.languagesUrl = languagesUrl;
        }

        @JsonProperty("merges_url")
        public String getMergesUrl() {
            return mergesUrl;
        }

        @JsonProperty("merges_url")
        public void setMergesUrl(String mergesUrl) {
            this.mergesUrl = mergesUrl;
        }

        @JsonProperty("milestones_url")
        public String getMilestonesUrl() {
            return milestonesUrl;
        }

        @JsonProperty("milestones_url")
        public void setMilestonesUrl(String milestonesUrl) {
            this.milestonesUrl = milestonesUrl;
        }

        @JsonProperty("name")
        public String getName() {
            return name;
        }

        @JsonProperty("name")
        public void setName(String name) {
            this.name = name;
        }

        @JsonProperty("node_id")
        public String getNodeId() {
            return nodeId;
        }

        @JsonProperty("node_id")
        public void setNodeId(String nodeId) {
            this.nodeId = nodeId;
        }

        @JsonProperty("notifications_url")
        public String getNotificationsUrl() {
            return notificationsUrl;
        }

        @JsonProperty("notifications_url")
        public void setNotificationsUrl(String notificationsUrl) {
            this.notificationsUrl = notificationsUrl;
        }

        @JsonProperty("owner")
        public WorkflowRunGitHubEvent.Owner2 getOwner() {
            return owner;
        }

        @JsonProperty("owner")
        public void setOwner(WorkflowRunGitHubEvent.Owner2 owner) {
            this.owner = owner;
        }

        @JsonProperty("private")
        public Boolean getPrivate() {
            return _private;
        }

        @JsonProperty("private")
        public void setPrivate(Boolean _private) {
            this._private = _private;
        }

        @JsonProperty("pulls_url")
        public String getPullsUrl() {
            return pullsUrl;
        }

        @JsonProperty("pulls_url")
        public void setPullsUrl(String pullsUrl) {
            this.pullsUrl = pullsUrl;
        }

        @JsonProperty("releases_url")
        public String getReleasesUrl() {
            return releasesUrl;
        }

        @JsonProperty("releases_url")
        public void setReleasesUrl(String releasesUrl) {
            this.releasesUrl = releasesUrl;
        }

        @JsonProperty("stargazers_url")
        public String getStargazersUrl() {
            return stargazersUrl;
        }

        @JsonProperty("stargazers_url")
        public void setStargazersUrl(String stargazersUrl) {
            this.stargazersUrl = stargazersUrl;
        }

        @JsonProperty("statuses_url")
        public String getStatusesUrl() {
            return statusesUrl;
        }

        @JsonProperty("statuses_url")
        public void setStatusesUrl(String statusesUrl) {
            this.statusesUrl = statusesUrl;
        }

        @JsonProperty("subscribers_url")
        public String getSubscribersUrl() {
            return subscribersUrl;
        }

        @JsonProperty("subscribers_url")
        public void setSubscribersUrl(String subscribersUrl) {
            this.subscribersUrl = subscribersUrl;
        }

        @JsonProperty("subscription_url")
        public String getSubscriptionUrl() {
            return subscriptionUrl;
        }

        @JsonProperty("subscription_url")
        public void setSubscriptionUrl(String subscriptionUrl) {
            this.subscriptionUrl = subscriptionUrl;
        }

        @JsonProperty("tags_url")
        public String getTagsUrl() {
            return tagsUrl;
        }

        @JsonProperty("tags_url")
        public void setTagsUrl(String tagsUrl) {
            this.tagsUrl = tagsUrl;
        }

        @JsonProperty("teams_url")
        public String getTeamsUrl() {
            return teamsUrl;
        }

        @JsonProperty("teams_url")
        public void setTeamsUrl(String teamsUrl) {
            this.teamsUrl = teamsUrl;
        }

        @JsonProperty("trees_url")
        public String getTreesUrl() {
            return treesUrl;
        }

        @JsonProperty("trees_url")
        public void setTreesUrl(String treesUrl) {
            this.treesUrl = treesUrl;
        }

        @JsonProperty("url")
        public String getUrl() {
            return url;
        }

        @JsonProperty("url")
        public void setUrl(String url) {
            this.url = url;
        }

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(WorkflowRunGitHubEvent.HeadRepository.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("archiveUrl");
            sb.append('=');
            sb.append(((this.archiveUrl == null)?"<null>":this.archiveUrl));
            sb.append(',');
            sb.append("assigneesUrl");
            sb.append('=');
            sb.append(((this.assigneesUrl == null)?"<null>":this.assigneesUrl));
            sb.append(',');
            sb.append("blobsUrl");
            sb.append('=');
            sb.append(((this.blobsUrl == null)?"<null>":this.blobsUrl));
            sb.append(',');
            sb.append("branchesUrl");
            sb.append('=');
            sb.append(((this.branchesUrl == null)?"<null>":this.branchesUrl));
            sb.append(',');
            sb.append("collaboratorsUrl");
            sb.append('=');
            sb.append(((this.collaboratorsUrl == null)?"<null>":this.collaboratorsUrl));
            sb.append(',');
            sb.append("commentsUrl");
            sb.append('=');
            sb.append(((this.commentsUrl == null)?"<null>":this.commentsUrl));
            sb.append(',');
            sb.append("commitsUrl");
            sb.append('=');
            sb.append(((this.commitsUrl == null)?"<null>":this.commitsUrl));
            sb.append(',');
            sb.append("compareUrl");
            sb.append('=');
            sb.append(((this.compareUrl == null)?"<null>":this.compareUrl));
            sb.append(',');
            sb.append("contentsUrl");
            sb.append('=');
            sb.append(((this.contentsUrl == null)?"<null>":this.contentsUrl));
            sb.append(',');
            sb.append("contributorsUrl");
            sb.append('=');
            sb.append(((this.contributorsUrl == null)?"<null>":this.contributorsUrl));
            sb.append(',');
            sb.append("deploymentsUrl");
            sb.append('=');
            sb.append(((this.deploymentsUrl == null)?"<null>":this.deploymentsUrl));
            sb.append(',');
            sb.append("description");
            sb.append('=');
            sb.append(((this.description == null)?"<null>":this.description));
            sb.append(',');
            sb.append("downloadsUrl");
            sb.append('=');
            sb.append(((this.downloadsUrl == null)?"<null>":this.downloadsUrl));
            sb.append(',');
            sb.append("eventsUrl");
            sb.append('=');
            sb.append(((this.eventsUrl == null)?"<null>":this.eventsUrl));
            sb.append(',');
            sb.append("fork");
            sb.append('=');
            sb.append(((this.fork == null)?"<null>":this.fork));
            sb.append(',');
            sb.append("forksUrl");
            sb.append('=');
            sb.append(((this.forksUrl == null)?"<null>":this.forksUrl));
            sb.append(',');
            sb.append("fullName");
            sb.append('=');
            sb.append(((this.fullName == null)?"<null>":this.fullName));
            sb.append(',');
            sb.append("gitCommitsUrl");
            sb.append('=');
            sb.append(((this.gitCommitsUrl == null)?"<null>":this.gitCommitsUrl));
            sb.append(',');
            sb.append("gitRefsUrl");
            sb.append('=');
            sb.append(((this.gitRefsUrl == null)?"<null>":this.gitRefsUrl));
            sb.append(',');
            sb.append("gitTagsUrl");
            sb.append('=');
            sb.append(((this.gitTagsUrl == null)?"<null>":this.gitTagsUrl));
            sb.append(',');
            sb.append("hooksUrl");
            sb.append('=');
            sb.append(((this.hooksUrl == null)?"<null>":this.hooksUrl));
            sb.append(',');
            sb.append("htmlUrl");
            sb.append('=');
            sb.append(((this.htmlUrl == null)?"<null>":this.htmlUrl));
            sb.append(',');
            sb.append("id");
            sb.append('=');
            sb.append(((this.id == null)?"<null>":this.id));
            sb.append(',');
            sb.append("issueCommentUrl");
            sb.append('=');
            sb.append(((this.issueCommentUrl == null)?"<null>":this.issueCommentUrl));
            sb.append(',');
            sb.append("issueEventsUrl");
            sb.append('=');
            sb.append(((this.issueEventsUrl == null)?"<null>":this.issueEventsUrl));
            sb.append(',');
            sb.append("issuesUrl");
            sb.append('=');
            sb.append(((this.issuesUrl == null)?"<null>":this.issuesUrl));
            sb.append(',');
            sb.append("keysUrl");
            sb.append('=');
            sb.append(((this.keysUrl == null)?"<null>":this.keysUrl));
            sb.append(',');
            sb.append("labelsUrl");
            sb.append('=');
            sb.append(((this.labelsUrl == null)?"<null>":this.labelsUrl));
            sb.append(',');
            sb.append("languagesUrl");
            sb.append('=');
            sb.append(((this.languagesUrl == null)?"<null>":this.languagesUrl));
            sb.append(',');
            sb.append("mergesUrl");
            sb.append('=');
            sb.append(((this.mergesUrl == null)?"<null>":this.mergesUrl));
            sb.append(',');
            sb.append("milestonesUrl");
            sb.append('=');
            sb.append(((this.milestonesUrl == null)?"<null>":this.milestonesUrl));
            sb.append(',');
            sb.append("name");
            sb.append('=');
            sb.append(((this.name == null)?"<null>":this.name));
            sb.append(',');
            sb.append("nodeId");
            sb.append('=');
            sb.append(((this.nodeId == null)?"<null>":this.nodeId));
            sb.append(',');
            sb.append("notificationsUrl");
            sb.append('=');
            sb.append(((this.notificationsUrl == null)?"<null>":this.notificationsUrl));
            sb.append(',');
            sb.append("owner");
            sb.append('=');
            sb.append(((this.owner == null)?"<null>":this.owner));
            sb.append(',');
            sb.append("_private");
            sb.append('=');
            sb.append(((this._private == null)?"<null>":this._private));
            sb.append(',');
            sb.append("pullsUrl");
            sb.append('=');
            sb.append(((this.pullsUrl == null)?"<null>":this.pullsUrl));
            sb.append(',');
            sb.append("releasesUrl");
            sb.append('=');
            sb.append(((this.releasesUrl == null)?"<null>":this.releasesUrl));
            sb.append(',');
            sb.append("stargazersUrl");
            sb.append('=');
            sb.append(((this.stargazersUrl == null)?"<null>":this.stargazersUrl));
            sb.append(',');
            sb.append("statusesUrl");
            sb.append('=');
            sb.append(((this.statusesUrl == null)?"<null>":this.statusesUrl));
            sb.append(',');
            sb.append("subscribersUrl");
            sb.append('=');
            sb.append(((this.subscribersUrl == null)?"<null>":this.subscribersUrl));
            sb.append(',');
            sb.append("subscriptionUrl");
            sb.append('=');
            sb.append(((this.subscriptionUrl == null)?"<null>":this.subscriptionUrl));
            sb.append(',');
            sb.append("tagsUrl");
            sb.append('=');
            sb.append(((this.tagsUrl == null)?"<null>":this.tagsUrl));
            sb.append(',');
            sb.append("teamsUrl");
            sb.append('=');
            sb.append(((this.teamsUrl == null)?"<null>":this.teamsUrl));
            sb.append(',');
            sb.append("treesUrl");
            sb.append('=');
            sb.append(((this.treesUrl == null)?"<null>":this.treesUrl));
            sb.append(',');
            sb.append("url");
            sb.append('=');
            sb.append(((this.url == null)?"<null>":this.url));
            sb.append(',');
            sb.append("additionalProperties");
            sb.append('=');
            sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
            sb.append(',');
            if (sb.charAt((sb.length()- 1)) == ',') {
                sb.setCharAt((sb.length()- 1), ']');
            } else {
                sb.append(']');
            }
            return sb.toString();
        }

        @Override
        public int hashCode() {
            int result = 1;
            result = ((result* 31)+((this.teamsUrl == null)? 0 :this.teamsUrl.hashCode()));
            result = ((result* 31)+((this.blobsUrl == null)? 0 :this.blobsUrl.hashCode()));
            result = ((result* 31)+((this.issueEventsUrl == null)? 0 :this.issueEventsUrl.hashCode()));
            result = ((result* 31)+((this.milestonesUrl == null)? 0 :this.milestonesUrl.hashCode()));
            result = ((result* 31)+((this.issuesUrl == null)? 0 :this.issuesUrl.hashCode()));
            result = ((result* 31)+((this.releasesUrl == null)? 0 :this.releasesUrl.hashCode()));
            result = ((result* 31)+((this.archiveUrl == null)? 0 :this.archiveUrl.hashCode()));
            result = ((result* 31)+((this.description == null)? 0 :this.description.hashCode()));
            result = ((result* 31)+((this.branchesUrl == null)? 0 :this.branchesUrl.hashCode()));
            result = ((result* 31)+((this.languagesUrl == null)? 0 :this.languagesUrl.hashCode()));
            result = ((result* 31)+((this.assigneesUrl == null)? 0 :this.assigneesUrl.hashCode()));
            result = ((result* 31)+((this.contributorsUrl == null)? 0 :this.contributorsUrl.hashCode()));
            result = ((result* 31)+((this.commitsUrl == null)? 0 :this.commitsUrl.hashCode()));
            result = ((result* 31)+((this.gitRefsUrl == null)? 0 :this.gitRefsUrl.hashCode()));
            result = ((result* 31)+((this.hooksUrl == null)? 0 :this.hooksUrl.hashCode()));
            result = ((result* 31)+((this.subscribersUrl == null)? 0 :this.subscribersUrl.hashCode()));
            result = ((result* 31)+((this.forksUrl == null)? 0 :this.forksUrl.hashCode()));
            result = ((result* 31)+((this._private == null)? 0 :this._private.hashCode()));
            result = ((result* 31)+((this.issueCommentUrl == null)? 0 :this.issueCommentUrl.hashCode()));
            result = ((result* 31)+((this.statusesUrl == null)? 0 :this.statusesUrl.hashCode()));
            result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
            result = ((result* 31)+((this.collaboratorsUrl == null)? 0 :this.collaboratorsUrl.hashCode()));
            result = ((result* 31)+((this.owner == null)? 0 :this.owner.hashCode()));
            result = ((result* 31)+((this.compareUrl == null)? 0 :this.compareUrl.hashCode()));
            result = ((result* 31)+((this.gitCommitsUrl == null)? 0 :this.gitCommitsUrl.hashCode()));
            result = ((result* 31)+((this.labelsUrl == null)? 0 :this.labelsUrl.hashCode()));
            result = ((result* 31)+((this.htmlUrl == null)? 0 :this.htmlUrl.hashCode()));
            result = ((result* 31)+((this.stargazersUrl == null)? 0 :this.stargazersUrl.hashCode()));
            result = ((result* 31)+((this.fullName == null)? 0 :this.fullName.hashCode()));
            result = ((result* 31)+((this.keysUrl == null)? 0 :this.keysUrl.hashCode()));
            result = ((result* 31)+((this.downloadsUrl == null)? 0 :this.downloadsUrl.hashCode()));
            result = ((result* 31)+((this.contentsUrl == null)? 0 :this.contentsUrl.hashCode()));
            result = ((result* 31)+((this.pullsUrl == null)? 0 :this.pullsUrl.hashCode()));
            result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
            result = ((result* 31)+((this.tagsUrl == null)? 0 :this.tagsUrl.hashCode()));
            result = ((result* 31)+((this.fork == null)? 0 :this.fork.hashCode()));
            result = ((result* 31)+((this.commentsUrl == null)? 0 :this.commentsUrl.hashCode()));
            result = ((result* 31)+((this.treesUrl == null)? 0 :this.treesUrl.hashCode()));
            result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
            result = ((result* 31)+((this.deploymentsUrl == null)? 0 :this.deploymentsUrl.hashCode()));
            result = ((result* 31)+((this.eventsUrl == null)? 0 :this.eventsUrl.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.gitTagsUrl == null)? 0 :this.gitTagsUrl.hashCode()));
            result = ((result* 31)+((this.mergesUrl == null)? 0 :this.mergesUrl.hashCode()));
            result = ((result* 31)+((this.notificationsUrl == null)? 0 :this.notificationsUrl.hashCode()));
            result = ((result* 31)+((this.nodeId == null)? 0 :this.nodeId.hashCode()));
            result = ((result* 31)+((this.subscriptionUrl == null)? 0 :this.subscriptionUrl.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof WorkflowRunGitHubEvent.HeadRepository) == false) {
                return false;
            }
            WorkflowRunGitHubEvent.HeadRepository rhs = ((WorkflowRunGitHubEvent.HeadRepository) other);
            return ((((((((((((((((((((((((((((((((((((((((((((((((this.teamsUrl == rhs.teamsUrl)||((this.teamsUrl!= null)&&this.teamsUrl.equals(rhs.teamsUrl)))&&((this.blobsUrl == rhs.blobsUrl)||((this.blobsUrl!= null)&&this.blobsUrl.equals(rhs.blobsUrl))))&&((this.issueEventsUrl == rhs.issueEventsUrl)||((this.issueEventsUrl!= null)&&this.issueEventsUrl.equals(rhs.issueEventsUrl))))&&((this.milestonesUrl == rhs.milestonesUrl)||((this.milestonesUrl!= null)&&this.milestonesUrl.equals(rhs.milestonesUrl))))&&((this.issuesUrl == rhs.issuesUrl)||((this.issuesUrl!= null)&&this.issuesUrl.equals(rhs.issuesUrl))))&&((this.releasesUrl == rhs.releasesUrl)||((this.releasesUrl!= null)&&this.releasesUrl.equals(rhs.releasesUrl))))&&((this.archiveUrl == rhs.archiveUrl)||((this.archiveUrl!= null)&&this.archiveUrl.equals(rhs.archiveUrl))))&&((this.description == rhs.description)||((this.description!= null)&&this.description.equals(rhs.description))))&&((this.branchesUrl == rhs.branchesUrl)||((this.branchesUrl!= null)&&this.branchesUrl.equals(rhs.branchesUrl))))&&((this.languagesUrl == rhs.languagesUrl)||((this.languagesUrl!= null)&&this.languagesUrl.equals(rhs.languagesUrl))))&&((this.assigneesUrl == rhs.assigneesUrl)||((this.assigneesUrl!= null)&&this.assigneesUrl.equals(rhs.assigneesUrl))))&&((this.contributorsUrl == rhs.contributorsUrl)||((this.contributorsUrl!= null)&&this.contributorsUrl.equals(rhs.contributorsUrl))))&&((this.commitsUrl == rhs.commitsUrl)||((this.commitsUrl!= null)&&this.commitsUrl.equals(rhs.commitsUrl))))&&((this.gitRefsUrl == rhs.gitRefsUrl)||((this.gitRefsUrl!= null)&&this.gitRefsUrl.equals(rhs.gitRefsUrl))))&&((this.hooksUrl == rhs.hooksUrl)||((this.hooksUrl!= null)&&this.hooksUrl.equals(rhs.hooksUrl))))&&((this.subscribersUrl == rhs.subscribersUrl)||((this.subscribersUrl!= null)&&this.subscribersUrl.equals(rhs.subscribersUrl))))&&((this.forksUrl == rhs.forksUrl)||((this.forksUrl!= null)&&this.forksUrl.equals(rhs.forksUrl))))&&((this._private == rhs._private)||((this._private!= null)&&this._private.equals(rhs._private))))&&((this.issueCommentUrl == rhs.issueCommentUrl)||((this.issueCommentUrl!= null)&&this.issueCommentUrl.equals(rhs.issueCommentUrl))))&&((this.statusesUrl == rhs.statusesUrl)||((this.statusesUrl!= null)&&this.statusesUrl.equals(rhs.statusesUrl))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.collaboratorsUrl == rhs.collaboratorsUrl)||((this.collaboratorsUrl!= null)&&this.collaboratorsUrl.equals(rhs.collaboratorsUrl))))&&((this.owner == rhs.owner)||((this.owner!= null)&&this.owner.equals(rhs.owner))))&&((this.compareUrl == rhs.compareUrl)||((this.compareUrl!= null)&&this.compareUrl.equals(rhs.compareUrl))))&&((this.gitCommitsUrl == rhs.gitCommitsUrl)||((this.gitCommitsUrl!= null)&&this.gitCommitsUrl.equals(rhs.gitCommitsUrl))))&&((this.labelsUrl == rhs.labelsUrl)||((this.labelsUrl!= null)&&this.labelsUrl.equals(rhs.labelsUrl))))&&((this.htmlUrl == rhs.htmlUrl)||((this.htmlUrl!= null)&&this.htmlUrl.equals(rhs.htmlUrl))))&&((this.stargazersUrl == rhs.stargazersUrl)||((this.stargazersUrl!= null)&&this.stargazersUrl.equals(rhs.stargazersUrl))))&&((this.fullName == rhs.fullName)||((this.fullName!= null)&&this.fullName.equals(rhs.fullName))))&&((this.keysUrl == rhs.keysUrl)||((this.keysUrl!= null)&&this.keysUrl.equals(rhs.keysUrl))))&&((this.downloadsUrl == rhs.downloadsUrl)||((this.downloadsUrl!= null)&&this.downloadsUrl.equals(rhs.downloadsUrl))))&&((this.contentsUrl == rhs.contentsUrl)||((this.contentsUrl!= null)&&this.contentsUrl.equals(rhs.contentsUrl))))&&((this.pullsUrl == rhs.pullsUrl)||((this.pullsUrl!= null)&&this.pullsUrl.equals(rhs.pullsUrl))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))))&&((this.tagsUrl == rhs.tagsUrl)||((this.tagsUrl!= null)&&this.tagsUrl.equals(rhs.tagsUrl))))&&((this.fork == rhs.fork)||((this.fork!= null)&&this.fork.equals(rhs.fork))))&&((this.commentsUrl == rhs.commentsUrl)||((this.commentsUrl!= null)&&this.commentsUrl.equals(rhs.commentsUrl))))&&((this.treesUrl == rhs.treesUrl)||((this.treesUrl!= null)&&this.treesUrl.equals(rhs.treesUrl))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.deploymentsUrl == rhs.deploymentsUrl)||((this.deploymentsUrl!= null)&&this.deploymentsUrl.equals(rhs.deploymentsUrl))))&&((this.eventsUrl == rhs.eventsUrl)||((this.eventsUrl!= null)&&this.eventsUrl.equals(rhs.eventsUrl))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.gitTagsUrl == rhs.gitTagsUrl)||((this.gitTagsUrl!= null)&&this.gitTagsUrl.equals(rhs.gitTagsUrl))))&&((this.mergesUrl == rhs.mergesUrl)||((this.mergesUrl!= null)&&this.mergesUrl.equals(rhs.mergesUrl))))&&((this.notificationsUrl == rhs.notificationsUrl)||((this.notificationsUrl!= null)&&this.notificationsUrl.equals(rhs.notificationsUrl))))&&((this.nodeId == rhs.nodeId)||((this.nodeId!= null)&&this.nodeId.equals(rhs.nodeId))))&&((this.subscriptionUrl == rhs.subscriptionUrl)||((this.subscriptionUrl!= null)&&this.subscriptionUrl.equals(rhs.subscriptionUrl))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "avatar_url",
        "description",
        "events_url",
        "hooks_url",
        "id",
        "issues_url",
        "login",
        "members_url",
        "node_id",
        "public_members_url",
        "repos_url",
        "url"
    })
    @Generated("jsonschema2pojo")
    public static class Organization {

        @JsonProperty("avatar_url")
        private String avatarUrl;
        @JsonProperty("description")
        private Object description;
        @JsonProperty("events_url")
        private String eventsUrl;
        @JsonProperty("hooks_url")
        private String hooksUrl;
        @JsonProperty("id")
        private Long id;
        @JsonProperty("issues_url")
        private String issuesUrl;
        @JsonProperty("login")
        private String login;
        @JsonProperty("members_url")
        private String membersUrl;
        @JsonProperty("node_id")
        private String nodeId;
        @JsonProperty("public_members_url")
        private String publicMembersUrl;
        @JsonProperty("repos_url")
        private String reposUrl;
        @JsonProperty("url")
        private String url;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Organization() {
        }

        /**
         * 
         * @param issuesUrl
         * @param avatarUrl
         * @param reposUrl
         * @param description
         * @param eventsUrl
         * @param id
         * @param membersUrl
         * @param login
         * @param publicMembersUrl
         * @param nodeId
         * @param hooksUrl
         * @param url
         */
        public Organization(String avatarUrl, Object description, String eventsUrl, String hooksUrl, Long id, String issuesUrl, String login, String membersUrl, String nodeId, String publicMembersUrl, String reposUrl, String url) {
            super();
            this.avatarUrl = avatarUrl;
            this.description = description;
            this.eventsUrl = eventsUrl;
            this.hooksUrl = hooksUrl;
            this.id = id;
            this.issuesUrl = issuesUrl;
            this.login = login;
            this.membersUrl = membersUrl;
            this.nodeId = nodeId;
            this.publicMembersUrl = publicMembersUrl;
            this.reposUrl = reposUrl;
            this.url = url;
        }

        @JsonProperty("avatar_url")
        public String getAvatarUrl() {
            return avatarUrl;
        }

        @JsonProperty("avatar_url")
        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        @JsonProperty("description")
        public Object getDescription() {
            return description;
        }

        @JsonProperty("description")
        public void setDescription(Object description) {
            this.description = description;
        }

        @JsonProperty("events_url")
        public String getEventsUrl() {
            return eventsUrl;
        }

        @JsonProperty("events_url")
        public void setEventsUrl(String eventsUrl) {
            this.eventsUrl = eventsUrl;
        }

        @JsonProperty("hooks_url")
        public String getHooksUrl() {
            return hooksUrl;
        }

        @JsonProperty("hooks_url")
        public void setHooksUrl(String hooksUrl) {
            this.hooksUrl = hooksUrl;
        }

        @JsonProperty("id")
        public Long getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(Long id) {
            this.id = id;
        }

        @JsonProperty("issues_url")
        public String getIssuesUrl() {
            return issuesUrl;
        }

        @JsonProperty("issues_url")
        public void setIssuesUrl(String issuesUrl) {
            this.issuesUrl = issuesUrl;
        }

        @JsonProperty("login")
        public String getLogin() {
            return login;
        }

        @JsonProperty("login")
        public void setLogin(String login) {
            this.login = login;
        }

        @JsonProperty("members_url")
        public String getMembersUrl() {
            return membersUrl;
        }

        @JsonProperty("members_url")
        public void setMembersUrl(String membersUrl) {
            this.membersUrl = membersUrl;
        }

        @JsonProperty("node_id")
        public String getNodeId() {
            return nodeId;
        }

        @JsonProperty("node_id")
        public void setNodeId(String nodeId) {
            this.nodeId = nodeId;
        }

        @JsonProperty("public_members_url")
        public String getPublicMembersUrl() {
            return publicMembersUrl;
        }

        @JsonProperty("public_members_url")
        public void setPublicMembersUrl(String publicMembersUrl) {
            this.publicMembersUrl = publicMembersUrl;
        }

        @JsonProperty("repos_url")
        public String getReposUrl() {
            return reposUrl;
        }

        @JsonProperty("repos_url")
        public void setReposUrl(String reposUrl) {
            this.reposUrl = reposUrl;
        }

        @JsonProperty("url")
        public String getUrl() {
            return url;
        }

        @JsonProperty("url")
        public void setUrl(String url) {
            this.url = url;
        }

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(WorkflowRunGitHubEvent.Organization.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("avatarUrl");
            sb.append('=');
            sb.append(((this.avatarUrl == null)?"<null>":this.avatarUrl));
            sb.append(',');
            sb.append("description");
            sb.append('=');
            sb.append(((this.description == null)?"<null>":this.description));
            sb.append(',');
            sb.append("eventsUrl");
            sb.append('=');
            sb.append(((this.eventsUrl == null)?"<null>":this.eventsUrl));
            sb.append(',');
            sb.append("hooksUrl");
            sb.append('=');
            sb.append(((this.hooksUrl == null)?"<null>":this.hooksUrl));
            sb.append(',');
            sb.append("id");
            sb.append('=');
            sb.append(((this.id == null)?"<null>":this.id));
            sb.append(',');
            sb.append("issuesUrl");
            sb.append('=');
            sb.append(((this.issuesUrl == null)?"<null>":this.issuesUrl));
            sb.append(',');
            sb.append("login");
            sb.append('=');
            sb.append(((this.login == null)?"<null>":this.login));
            sb.append(',');
            sb.append("membersUrl");
            sb.append('=');
            sb.append(((this.membersUrl == null)?"<null>":this.membersUrl));
            sb.append(',');
            sb.append("nodeId");
            sb.append('=');
            sb.append(((this.nodeId == null)?"<null>":this.nodeId));
            sb.append(',');
            sb.append("publicMembersUrl");
            sb.append('=');
            sb.append(((this.publicMembersUrl == null)?"<null>":this.publicMembersUrl));
            sb.append(',');
            sb.append("reposUrl");
            sb.append('=');
            sb.append(((this.reposUrl == null)?"<null>":this.reposUrl));
            sb.append(',');
            sb.append("url");
            sb.append('=');
            sb.append(((this.url == null)?"<null>":this.url));
            sb.append(',');
            sb.append("additionalProperties");
            sb.append('=');
            sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
            sb.append(',');
            if (sb.charAt((sb.length()- 1)) == ',') {
                sb.setCharAt((sb.length()- 1), ']');
            } else {
                sb.append(']');
            }
            return sb.toString();
        }

        @Override
        public int hashCode() {
            int result = 1;
            result = ((result* 31)+((this.issuesUrl == null)? 0 :this.issuesUrl.hashCode()));
            result = ((result* 31)+((this.avatarUrl == null)? 0 :this.avatarUrl.hashCode()));
            result = ((result* 31)+((this.reposUrl == null)? 0 :this.reposUrl.hashCode()));
            result = ((result* 31)+((this.description == null)? 0 :this.description.hashCode()));
            result = ((result* 31)+((this.membersUrl == null)? 0 :this.membersUrl.hashCode()));
            result = ((result* 31)+((this.login == null)? 0 :this.login.hashCode()));
            result = ((result* 31)+((this.hooksUrl == null)? 0 :this.hooksUrl.hashCode()));
            result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
            result = ((result* 31)+((this.eventsUrl == null)? 0 :this.eventsUrl.hashCode()));
            result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.publicMembersUrl == null)? 0 :this.publicMembersUrl.hashCode()));
            result = ((result* 31)+((this.nodeId == null)? 0 :this.nodeId.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof WorkflowRunGitHubEvent.Organization) == false) {
                return false;
            }
            WorkflowRunGitHubEvent.Organization rhs = ((WorkflowRunGitHubEvent.Organization) other);
            return ((((((((((((((this.issuesUrl == rhs.issuesUrl)||((this.issuesUrl!= null)&&this.issuesUrl.equals(rhs.issuesUrl)))&&((this.avatarUrl == rhs.avatarUrl)||((this.avatarUrl!= null)&&this.avatarUrl.equals(rhs.avatarUrl))))&&((this.reposUrl == rhs.reposUrl)||((this.reposUrl!= null)&&this.reposUrl.equals(rhs.reposUrl))))&&((this.description == rhs.description)||((this.description!= null)&&this.description.equals(rhs.description))))&&((this.membersUrl == rhs.membersUrl)||((this.membersUrl!= null)&&this.membersUrl.equals(rhs.membersUrl))))&&((this.login == rhs.login)||((this.login!= null)&&this.login.equals(rhs.login))))&&((this.hooksUrl == rhs.hooksUrl)||((this.hooksUrl!= null)&&this.hooksUrl.equals(rhs.hooksUrl))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))))&&((this.eventsUrl == rhs.eventsUrl)||((this.eventsUrl!= null)&&this.eventsUrl.equals(rhs.eventsUrl))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.publicMembersUrl == rhs.publicMembersUrl)||((this.publicMembersUrl!= null)&&this.publicMembersUrl.equals(rhs.publicMembersUrl))))&&((this.nodeId == rhs.nodeId)||((this.nodeId!= null)&&this.nodeId.equals(rhs.nodeId))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "avatar_url",
        "events_url",
        "followers_url",
        "following_url",
        "gists_url",
        "gravatar_id",
        "html_url",
        "id",
        "login",
        "node_id",
        "organizations_url",
        "received_events_url",
        "repos_url",
        "site_admin",
        "starred_url",
        "subscriptions_url",
        "type",
        "url"
    })
    @Generated("jsonschema2pojo")
    public static class Owner {

        @JsonProperty("avatar_url")
        private String avatarUrl;
        @JsonProperty("events_url")
        private String eventsUrl;
        @JsonProperty("followers_url")
        private String followersUrl;
        @JsonProperty("following_url")
        private String followingUrl;
        @JsonProperty("gists_url")
        private String gistsUrl;
        @JsonProperty("gravatar_id")
        private String gravatarId;
        @JsonProperty("html_url")
        private String htmlUrl;
        @JsonProperty("id")
        private Long id;
        @JsonProperty("login")
        private String login;
        @JsonProperty("node_id")
        private String nodeId;
        @JsonProperty("organizations_url")
        private String organizationsUrl;
        @JsonProperty("received_events_url")
        private String receivedEventsUrl;
        @JsonProperty("repos_url")
        private String reposUrl;
        @JsonProperty("site_admin")
        private Boolean siteAdmin;
        @JsonProperty("starred_url")
        private String starredUrl;
        @JsonProperty("subscriptions_url")
        private String subscriptionsUrl;
        @JsonProperty("type")
        private String type;
        @JsonProperty("url")
        private String url;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Owner() {
        }

        /**
         * 
         * @param receivedEventsUrl
         * @param siteAdmin
         * @param followingUrl
         * @param gistsUrl
         * @param avatarUrl
         * @param organizationsUrl
         * @param reposUrl
         * @param htmlUrl
         * @param subscriptionsUrl
         * @param login
         * @param type
         * @param starredUrl
         * @param url
         * @param gravatarId
         * @param followersUrl
         * @param eventsUrl
         * @param id
         * @param nodeId
         */
        public Owner(String avatarUrl, String eventsUrl, String followersUrl, String followingUrl, String gistsUrl, String gravatarId, String htmlUrl, Long id, String login, String nodeId, String organizationsUrl, String receivedEventsUrl, String reposUrl, Boolean siteAdmin, String starredUrl, String subscriptionsUrl, String type, String url) {
            super();
            this.avatarUrl = avatarUrl;
            this.eventsUrl = eventsUrl;
            this.followersUrl = followersUrl;
            this.followingUrl = followingUrl;
            this.gistsUrl = gistsUrl;
            this.gravatarId = gravatarId;
            this.htmlUrl = htmlUrl;
            this.id = id;
            this.login = login;
            this.nodeId = nodeId;
            this.organizationsUrl = organizationsUrl;
            this.receivedEventsUrl = receivedEventsUrl;
            this.reposUrl = reposUrl;
            this.siteAdmin = siteAdmin;
            this.starredUrl = starredUrl;
            this.subscriptionsUrl = subscriptionsUrl;
            this.type = type;
            this.url = url;
        }

        @JsonProperty("avatar_url")
        public String getAvatarUrl() {
            return avatarUrl;
        }

        @JsonProperty("avatar_url")
        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        @JsonProperty("events_url")
        public String getEventsUrl() {
            return eventsUrl;
        }

        @JsonProperty("events_url")
        public void setEventsUrl(String eventsUrl) {
            this.eventsUrl = eventsUrl;
        }

        @JsonProperty("followers_url")
        public String getFollowersUrl() {
            return followersUrl;
        }

        @JsonProperty("followers_url")
        public void setFollowersUrl(String followersUrl) {
            this.followersUrl = followersUrl;
        }

        @JsonProperty("following_url")
        public String getFollowingUrl() {
            return followingUrl;
        }

        @JsonProperty("following_url")
        public void setFollowingUrl(String followingUrl) {
            this.followingUrl = followingUrl;
        }

        @JsonProperty("gists_url")
        public String getGistsUrl() {
            return gistsUrl;
        }

        @JsonProperty("gists_url")
        public void setGistsUrl(String gistsUrl) {
            this.gistsUrl = gistsUrl;
        }

        @JsonProperty("gravatar_id")
        public String getGravatarId() {
            return gravatarId;
        }

        @JsonProperty("gravatar_id")
        public void setGravatarId(String gravatarId) {
            this.gravatarId = gravatarId;
        }

        @JsonProperty("html_url")
        public String getHtmlUrl() {
            return htmlUrl;
        }

        @JsonProperty("html_url")
        public void setHtmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
        }

        @JsonProperty("id")
        public Long getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(Long id) {
            this.id = id;
        }

        @JsonProperty("login")
        public String getLogin() {
            return login;
        }

        @JsonProperty("login")
        public void setLogin(String login) {
            this.login = login;
        }

        @JsonProperty("node_id")
        public String getNodeId() {
            return nodeId;
        }

        @JsonProperty("node_id")
        public void setNodeId(String nodeId) {
            this.nodeId = nodeId;
        }

        @JsonProperty("organizations_url")
        public String getOrganizationsUrl() {
            return organizationsUrl;
        }

        @JsonProperty("organizations_url")
        public void setOrganizationsUrl(String organizationsUrl) {
            this.organizationsUrl = organizationsUrl;
        }

        @JsonProperty("received_events_url")
        public String getReceivedEventsUrl() {
            return receivedEventsUrl;
        }

        @JsonProperty("received_events_url")
        public void setReceivedEventsUrl(String receivedEventsUrl) {
            this.receivedEventsUrl = receivedEventsUrl;
        }

        @JsonProperty("repos_url")
        public String getReposUrl() {
            return reposUrl;
        }

        @JsonProperty("repos_url")
        public void setReposUrl(String reposUrl) {
            this.reposUrl = reposUrl;
        }

        @JsonProperty("site_admin")
        public Boolean getSiteAdmin() {
            return siteAdmin;
        }

        @JsonProperty("site_admin")
        public void setSiteAdmin(Boolean siteAdmin) {
            this.siteAdmin = siteAdmin;
        }

        @JsonProperty("starred_url")
        public String getStarredUrl() {
            return starredUrl;
        }

        @JsonProperty("starred_url")
        public void setStarredUrl(String starredUrl) {
            this.starredUrl = starredUrl;
        }

        @JsonProperty("subscriptions_url")
        public String getSubscriptionsUrl() {
            return subscriptionsUrl;
        }

        @JsonProperty("subscriptions_url")
        public void setSubscriptionsUrl(String subscriptionsUrl) {
            this.subscriptionsUrl = subscriptionsUrl;
        }

        @JsonProperty("type")
        public String getType() {
            return type;
        }

        @JsonProperty("type")
        public void setType(String type) {
            this.type = type;
        }

        @JsonProperty("url")
        public String getUrl() {
            return url;
        }

        @JsonProperty("url")
        public void setUrl(String url) {
            this.url = url;
        }

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(WorkflowRunGitHubEvent.Owner.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("avatarUrl");
            sb.append('=');
            sb.append(((this.avatarUrl == null)?"<null>":this.avatarUrl));
            sb.append(',');
            sb.append("eventsUrl");
            sb.append('=');
            sb.append(((this.eventsUrl == null)?"<null>":this.eventsUrl));
            sb.append(',');
            sb.append("followersUrl");
            sb.append('=');
            sb.append(((this.followersUrl == null)?"<null>":this.followersUrl));
            sb.append(',');
            sb.append("followingUrl");
            sb.append('=');
            sb.append(((this.followingUrl == null)?"<null>":this.followingUrl));
            sb.append(',');
            sb.append("gistsUrl");
            sb.append('=');
            sb.append(((this.gistsUrl == null)?"<null>":this.gistsUrl));
            sb.append(',');
            sb.append("gravatarId");
            sb.append('=');
            sb.append(((this.gravatarId == null)?"<null>":this.gravatarId));
            sb.append(',');
            sb.append("htmlUrl");
            sb.append('=');
            sb.append(((this.htmlUrl == null)?"<null>":this.htmlUrl));
            sb.append(',');
            sb.append("id");
            sb.append('=');
            sb.append(((this.id == null)?"<null>":this.id));
            sb.append(',');
            sb.append("login");
            sb.append('=');
            sb.append(((this.login == null)?"<null>":this.login));
            sb.append(',');
            sb.append("nodeId");
            sb.append('=');
            sb.append(((this.nodeId == null)?"<null>":this.nodeId));
            sb.append(',');
            sb.append("organizationsUrl");
            sb.append('=');
            sb.append(((this.organizationsUrl == null)?"<null>":this.organizationsUrl));
            sb.append(',');
            sb.append("receivedEventsUrl");
            sb.append('=');
            sb.append(((this.receivedEventsUrl == null)?"<null>":this.receivedEventsUrl));
            sb.append(',');
            sb.append("reposUrl");
            sb.append('=');
            sb.append(((this.reposUrl == null)?"<null>":this.reposUrl));
            sb.append(',');
            sb.append("siteAdmin");
            sb.append('=');
            sb.append(((this.siteAdmin == null)?"<null>":this.siteAdmin));
            sb.append(',');
            sb.append("starredUrl");
            sb.append('=');
            sb.append(((this.starredUrl == null)?"<null>":this.starredUrl));
            sb.append(',');
            sb.append("subscriptionsUrl");
            sb.append('=');
            sb.append(((this.subscriptionsUrl == null)?"<null>":this.subscriptionsUrl));
            sb.append(',');
            sb.append("type");
            sb.append('=');
            sb.append(((this.type == null)?"<null>":this.type));
            sb.append(',');
            sb.append("url");
            sb.append('=');
            sb.append(((this.url == null)?"<null>":this.url));
            sb.append(',');
            sb.append("additionalProperties");
            sb.append('=');
            sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
            sb.append(',');
            if (sb.charAt((sb.length()- 1)) == ',') {
                sb.setCharAt((sb.length()- 1), ']');
            } else {
                sb.append(']');
            }
            return sb.toString();
        }

        @Override
        public int hashCode() {
            int result = 1;
            result = ((result* 31)+((this.receivedEventsUrl == null)? 0 :this.receivedEventsUrl.hashCode()));
            result = ((result* 31)+((this.siteAdmin == null)? 0 :this.siteAdmin.hashCode()));
            result = ((result* 31)+((this.followingUrl == null)? 0 :this.followingUrl.hashCode()));
            result = ((result* 31)+((this.gistsUrl == null)? 0 :this.gistsUrl.hashCode()));
            result = ((result* 31)+((this.avatarUrl == null)? 0 :this.avatarUrl.hashCode()));
            result = ((result* 31)+((this.organizationsUrl == null)? 0 :this.organizationsUrl.hashCode()));
            result = ((result* 31)+((this.reposUrl == null)? 0 :this.reposUrl.hashCode()));
            result = ((result* 31)+((this.htmlUrl == null)? 0 :this.htmlUrl.hashCode()));
            result = ((result* 31)+((this.subscriptionsUrl == null)? 0 :this.subscriptionsUrl.hashCode()));
            result = ((result* 31)+((this.login == null)? 0 :this.login.hashCode()));
            result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
            result = ((result* 31)+((this.starredUrl == null)? 0 :this.starredUrl.hashCode()));
            result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
            result = ((result* 31)+((this.gravatarId == null)? 0 :this.gravatarId.hashCode()));
            result = ((result* 31)+((this.followersUrl == null)? 0 :this.followersUrl.hashCode()));
            result = ((result* 31)+((this.eventsUrl == null)? 0 :this.eventsUrl.hashCode()));
            result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.nodeId == null)? 0 :this.nodeId.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof WorkflowRunGitHubEvent.Owner) == false) {
                return false;
            }
            WorkflowRunGitHubEvent.Owner rhs = ((WorkflowRunGitHubEvent.Owner) other);
            return ((((((((((((((((((((this.receivedEventsUrl == rhs.receivedEventsUrl)||((this.receivedEventsUrl!= null)&&this.receivedEventsUrl.equals(rhs.receivedEventsUrl)))&&((this.siteAdmin == rhs.siteAdmin)||((this.siteAdmin!= null)&&this.siteAdmin.equals(rhs.siteAdmin))))&&((this.followingUrl == rhs.followingUrl)||((this.followingUrl!= null)&&this.followingUrl.equals(rhs.followingUrl))))&&((this.gistsUrl == rhs.gistsUrl)||((this.gistsUrl!= null)&&this.gistsUrl.equals(rhs.gistsUrl))))&&((this.avatarUrl == rhs.avatarUrl)||((this.avatarUrl!= null)&&this.avatarUrl.equals(rhs.avatarUrl))))&&((this.organizationsUrl == rhs.organizationsUrl)||((this.organizationsUrl!= null)&&this.organizationsUrl.equals(rhs.organizationsUrl))))&&((this.reposUrl == rhs.reposUrl)||((this.reposUrl!= null)&&this.reposUrl.equals(rhs.reposUrl))))&&((this.htmlUrl == rhs.htmlUrl)||((this.htmlUrl!= null)&&this.htmlUrl.equals(rhs.htmlUrl))))&&((this.subscriptionsUrl == rhs.subscriptionsUrl)||((this.subscriptionsUrl!= null)&&this.subscriptionsUrl.equals(rhs.subscriptionsUrl))))&&((this.login == rhs.login)||((this.login!= null)&&this.login.equals(rhs.login))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.starredUrl == rhs.starredUrl)||((this.starredUrl!= null)&&this.starredUrl.equals(rhs.starredUrl))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))))&&((this.gravatarId == rhs.gravatarId)||((this.gravatarId!= null)&&this.gravatarId.equals(rhs.gravatarId))))&&((this.followersUrl == rhs.followersUrl)||((this.followersUrl!= null)&&this.followersUrl.equals(rhs.followersUrl))))&&((this.eventsUrl == rhs.eventsUrl)||((this.eventsUrl!= null)&&this.eventsUrl.equals(rhs.eventsUrl))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.nodeId == rhs.nodeId)||((this.nodeId!= null)&&this.nodeId.equals(rhs.nodeId))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "avatar_url",
        "events_url",
        "followers_url",
        "following_url",
        "gists_url",
        "gravatar_id",
        "html_url",
        "id",
        "login",
        "node_id",
        "organizations_url",
        "received_events_url",
        "repos_url",
        "site_admin",
        "starred_url",
        "subscriptions_url",
        "type",
        "url"
    })
    @Generated("jsonschema2pojo")
    public static class Owner2 {

        @JsonProperty("avatar_url")
        private String avatarUrl;
        @JsonProperty("events_url")
        private String eventsUrl;
        @JsonProperty("followers_url")
        private String followersUrl;
        @JsonProperty("following_url")
        private String followingUrl;
        @JsonProperty("gists_url")
        private String gistsUrl;
        @JsonProperty("gravatar_id")
        private String gravatarId;
        @JsonProperty("html_url")
        private String htmlUrl;
        @JsonProperty("id")
        private Long id;
        @JsonProperty("login")
        private String login;
        @JsonProperty("node_id")
        private String nodeId;
        @JsonProperty("organizations_url")
        private String organizationsUrl;
        @JsonProperty("received_events_url")
        private String receivedEventsUrl;
        @JsonProperty("repos_url")
        private String reposUrl;
        @JsonProperty("site_admin")
        private Boolean siteAdmin;
        @JsonProperty("starred_url")
        private String starredUrl;
        @JsonProperty("subscriptions_url")
        private String subscriptionsUrl;
        @JsonProperty("type")
        private String type;
        @JsonProperty("url")
        private String url;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Owner2() {
        }

        /**
         * 
         * @param receivedEventsUrl
         * @param siteAdmin
         * @param followingUrl
         * @param gistsUrl
         * @param avatarUrl
         * @param organizationsUrl
         * @param reposUrl
         * @param htmlUrl
         * @param subscriptionsUrl
         * @param login
         * @param type
         * @param starredUrl
         * @param url
         * @param gravatarId
         * @param followersUrl
         * @param eventsUrl
         * @param id
         * @param nodeId
         */
        public Owner2(String avatarUrl, String eventsUrl, String followersUrl, String followingUrl, String gistsUrl, String gravatarId, String htmlUrl, Long id, String login, String nodeId, String organizationsUrl, String receivedEventsUrl, String reposUrl, Boolean siteAdmin, String starredUrl, String subscriptionsUrl, String type, String url) {
            super();
            this.avatarUrl = avatarUrl;
            this.eventsUrl = eventsUrl;
            this.followersUrl = followersUrl;
            this.followingUrl = followingUrl;
            this.gistsUrl = gistsUrl;
            this.gravatarId = gravatarId;
            this.htmlUrl = htmlUrl;
            this.id = id;
            this.login = login;
            this.nodeId = nodeId;
            this.organizationsUrl = organizationsUrl;
            this.receivedEventsUrl = receivedEventsUrl;
            this.reposUrl = reposUrl;
            this.siteAdmin = siteAdmin;
            this.starredUrl = starredUrl;
            this.subscriptionsUrl = subscriptionsUrl;
            this.type = type;
            this.url = url;
        }

        @JsonProperty("avatar_url")
        public String getAvatarUrl() {
            return avatarUrl;
        }

        @JsonProperty("avatar_url")
        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        @JsonProperty("events_url")
        public String getEventsUrl() {
            return eventsUrl;
        }

        @JsonProperty("events_url")
        public void setEventsUrl(String eventsUrl) {
            this.eventsUrl = eventsUrl;
        }

        @JsonProperty("followers_url")
        public String getFollowersUrl() {
            return followersUrl;
        }

        @JsonProperty("followers_url")
        public void setFollowersUrl(String followersUrl) {
            this.followersUrl = followersUrl;
        }

        @JsonProperty("following_url")
        public String getFollowingUrl() {
            return followingUrl;
        }

        @JsonProperty("following_url")
        public void setFollowingUrl(String followingUrl) {
            this.followingUrl = followingUrl;
        }

        @JsonProperty("gists_url")
        public String getGistsUrl() {
            return gistsUrl;
        }

        @JsonProperty("gists_url")
        public void setGistsUrl(String gistsUrl) {
            this.gistsUrl = gistsUrl;
        }

        @JsonProperty("gravatar_id")
        public String getGravatarId() {
            return gravatarId;
        }

        @JsonProperty("gravatar_id")
        public void setGravatarId(String gravatarId) {
            this.gravatarId = gravatarId;
        }

        @JsonProperty("html_url")
        public String getHtmlUrl() {
            return htmlUrl;
        }

        @JsonProperty("html_url")
        public void setHtmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
        }

        @JsonProperty("id")
        public Long getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(Long id) {
            this.id = id;
        }

        @JsonProperty("login")
        public String getLogin() {
            return login;
        }

        @JsonProperty("login")
        public void setLogin(String login) {
            this.login = login;
        }

        @JsonProperty("node_id")
        public String getNodeId() {
            return nodeId;
        }

        @JsonProperty("node_id")
        public void setNodeId(String nodeId) {
            this.nodeId = nodeId;
        }

        @JsonProperty("organizations_url")
        public String getOrganizationsUrl() {
            return organizationsUrl;
        }

        @JsonProperty("organizations_url")
        public void setOrganizationsUrl(String organizationsUrl) {
            this.organizationsUrl = organizationsUrl;
        }

        @JsonProperty("received_events_url")
        public String getReceivedEventsUrl() {
            return receivedEventsUrl;
        }

        @JsonProperty("received_events_url")
        public void setReceivedEventsUrl(String receivedEventsUrl) {
            this.receivedEventsUrl = receivedEventsUrl;
        }

        @JsonProperty("repos_url")
        public String getReposUrl() {
            return reposUrl;
        }

        @JsonProperty("repos_url")
        public void setReposUrl(String reposUrl) {
            this.reposUrl = reposUrl;
        }

        @JsonProperty("site_admin")
        public Boolean getSiteAdmin() {
            return siteAdmin;
        }

        @JsonProperty("site_admin")
        public void setSiteAdmin(Boolean siteAdmin) {
            this.siteAdmin = siteAdmin;
        }

        @JsonProperty("starred_url")
        public String getStarredUrl() {
            return starredUrl;
        }

        @JsonProperty("starred_url")
        public void setStarredUrl(String starredUrl) {
            this.starredUrl = starredUrl;
        }

        @JsonProperty("subscriptions_url")
        public String getSubscriptionsUrl() {
            return subscriptionsUrl;
        }

        @JsonProperty("subscriptions_url")
        public void setSubscriptionsUrl(String subscriptionsUrl) {
            this.subscriptionsUrl = subscriptionsUrl;
        }

        @JsonProperty("type")
        public String getType() {
            return type;
        }

        @JsonProperty("type")
        public void setType(String type) {
            this.type = type;
        }

        @JsonProperty("url")
        public String getUrl() {
            return url;
        }

        @JsonProperty("url")
        public void setUrl(String url) {
            this.url = url;
        }

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(WorkflowRunGitHubEvent.Owner2 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("avatarUrl");
            sb.append('=');
            sb.append(((this.avatarUrl == null)?"<null>":this.avatarUrl));
            sb.append(',');
            sb.append("eventsUrl");
            sb.append('=');
            sb.append(((this.eventsUrl == null)?"<null>":this.eventsUrl));
            sb.append(',');
            sb.append("followersUrl");
            sb.append('=');
            sb.append(((this.followersUrl == null)?"<null>":this.followersUrl));
            sb.append(',');
            sb.append("followingUrl");
            sb.append('=');
            sb.append(((this.followingUrl == null)?"<null>":this.followingUrl));
            sb.append(',');
            sb.append("gistsUrl");
            sb.append('=');
            sb.append(((this.gistsUrl == null)?"<null>":this.gistsUrl));
            sb.append(',');
            sb.append("gravatarId");
            sb.append('=');
            sb.append(((this.gravatarId == null)?"<null>":this.gravatarId));
            sb.append(',');
            sb.append("htmlUrl");
            sb.append('=');
            sb.append(((this.htmlUrl == null)?"<null>":this.htmlUrl));
            sb.append(',');
            sb.append("id");
            sb.append('=');
            sb.append(((this.id == null)?"<null>":this.id));
            sb.append(',');
            sb.append("login");
            sb.append('=');
            sb.append(((this.login == null)?"<null>":this.login));
            sb.append(',');
            sb.append("nodeId");
            sb.append('=');
            sb.append(((this.nodeId == null)?"<null>":this.nodeId));
            sb.append(',');
            sb.append("organizationsUrl");
            sb.append('=');
            sb.append(((this.organizationsUrl == null)?"<null>":this.organizationsUrl));
            sb.append(',');
            sb.append("receivedEventsUrl");
            sb.append('=');
            sb.append(((this.receivedEventsUrl == null)?"<null>":this.receivedEventsUrl));
            sb.append(',');
            sb.append("reposUrl");
            sb.append('=');
            sb.append(((this.reposUrl == null)?"<null>":this.reposUrl));
            sb.append(',');
            sb.append("siteAdmin");
            sb.append('=');
            sb.append(((this.siteAdmin == null)?"<null>":this.siteAdmin));
            sb.append(',');
            sb.append("starredUrl");
            sb.append('=');
            sb.append(((this.starredUrl == null)?"<null>":this.starredUrl));
            sb.append(',');
            sb.append("subscriptionsUrl");
            sb.append('=');
            sb.append(((this.subscriptionsUrl == null)?"<null>":this.subscriptionsUrl));
            sb.append(',');
            sb.append("type");
            sb.append('=');
            sb.append(((this.type == null)?"<null>":this.type));
            sb.append(',');
            sb.append("url");
            sb.append('=');
            sb.append(((this.url == null)?"<null>":this.url));
            sb.append(',');
            sb.append("additionalProperties");
            sb.append('=');
            sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
            sb.append(',');
            if (sb.charAt((sb.length()- 1)) == ',') {
                sb.setCharAt((sb.length()- 1), ']');
            } else {
                sb.append(']');
            }
            return sb.toString();
        }

        @Override
        public int hashCode() {
            int result = 1;
            result = ((result* 31)+((this.receivedEventsUrl == null)? 0 :this.receivedEventsUrl.hashCode()));
            result = ((result* 31)+((this.siteAdmin == null)? 0 :this.siteAdmin.hashCode()));
            result = ((result* 31)+((this.followingUrl == null)? 0 :this.followingUrl.hashCode()));
            result = ((result* 31)+((this.gistsUrl == null)? 0 :this.gistsUrl.hashCode()));
            result = ((result* 31)+((this.avatarUrl == null)? 0 :this.avatarUrl.hashCode()));
            result = ((result* 31)+((this.organizationsUrl == null)? 0 :this.organizationsUrl.hashCode()));
            result = ((result* 31)+((this.reposUrl == null)? 0 :this.reposUrl.hashCode()));
            result = ((result* 31)+((this.htmlUrl == null)? 0 :this.htmlUrl.hashCode()));
            result = ((result* 31)+((this.subscriptionsUrl == null)? 0 :this.subscriptionsUrl.hashCode()));
            result = ((result* 31)+((this.login == null)? 0 :this.login.hashCode()));
            result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
            result = ((result* 31)+((this.starredUrl == null)? 0 :this.starredUrl.hashCode()));
            result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
            result = ((result* 31)+((this.gravatarId == null)? 0 :this.gravatarId.hashCode()));
            result = ((result* 31)+((this.followersUrl == null)? 0 :this.followersUrl.hashCode()));
            result = ((result* 31)+((this.eventsUrl == null)? 0 :this.eventsUrl.hashCode()));
            result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.nodeId == null)? 0 :this.nodeId.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof WorkflowRunGitHubEvent.Owner2) == false) {
                return false;
            }
            WorkflowRunGitHubEvent.Owner2 rhs = ((WorkflowRunGitHubEvent.Owner2) other);
            return ((((((((((((((((((((this.receivedEventsUrl == rhs.receivedEventsUrl)||((this.receivedEventsUrl!= null)&&this.receivedEventsUrl.equals(rhs.receivedEventsUrl)))&&((this.siteAdmin == rhs.siteAdmin)||((this.siteAdmin!= null)&&this.siteAdmin.equals(rhs.siteAdmin))))&&((this.followingUrl == rhs.followingUrl)||((this.followingUrl!= null)&&this.followingUrl.equals(rhs.followingUrl))))&&((this.gistsUrl == rhs.gistsUrl)||((this.gistsUrl!= null)&&this.gistsUrl.equals(rhs.gistsUrl))))&&((this.avatarUrl == rhs.avatarUrl)||((this.avatarUrl!= null)&&this.avatarUrl.equals(rhs.avatarUrl))))&&((this.organizationsUrl == rhs.organizationsUrl)||((this.organizationsUrl!= null)&&this.organizationsUrl.equals(rhs.organizationsUrl))))&&((this.reposUrl == rhs.reposUrl)||((this.reposUrl!= null)&&this.reposUrl.equals(rhs.reposUrl))))&&((this.htmlUrl == rhs.htmlUrl)||((this.htmlUrl!= null)&&this.htmlUrl.equals(rhs.htmlUrl))))&&((this.subscriptionsUrl == rhs.subscriptionsUrl)||((this.subscriptionsUrl!= null)&&this.subscriptionsUrl.equals(rhs.subscriptionsUrl))))&&((this.login == rhs.login)||((this.login!= null)&&this.login.equals(rhs.login))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.starredUrl == rhs.starredUrl)||((this.starredUrl!= null)&&this.starredUrl.equals(rhs.starredUrl))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))))&&((this.gravatarId == rhs.gravatarId)||((this.gravatarId!= null)&&this.gravatarId.equals(rhs.gravatarId))))&&((this.followersUrl == rhs.followersUrl)||((this.followersUrl!= null)&&this.followersUrl.equals(rhs.followersUrl))))&&((this.eventsUrl == rhs.eventsUrl)||((this.eventsUrl!= null)&&this.eventsUrl.equals(rhs.eventsUrl))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.nodeId == rhs.nodeId)||((this.nodeId!= null)&&this.nodeId.equals(rhs.nodeId))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "avatar_url",
        "events_url",
        "followers_url",
        "following_url",
        "gists_url",
        "gravatar_id",
        "html_url",
        "id",
        "login",
        "node_id",
        "organizations_url",
        "received_events_url",
        "repos_url",
        "site_admin",
        "starred_url",
        "subscriptions_url",
        "type",
        "url"
    })
    @Generated("jsonschema2pojo")
    public static class Owner3 {

        @JsonProperty("avatar_url")
        private String avatarUrl;
        @JsonProperty("events_url")
        private String eventsUrl;
        @JsonProperty("followers_url")
        private String followersUrl;
        @JsonProperty("following_url")
        private String followingUrl;
        @JsonProperty("gists_url")
        private String gistsUrl;
        @JsonProperty("gravatar_id")
        private String gravatarId;
        @JsonProperty("html_url")
        private String htmlUrl;
        @JsonProperty("id")
        private Long id;
        @JsonProperty("login")
        private String login;
        @JsonProperty("node_id")
        private String nodeId;
        @JsonProperty("organizations_url")
        private String organizationsUrl;
        @JsonProperty("received_events_url")
        private String receivedEventsUrl;
        @JsonProperty("repos_url")
        private String reposUrl;
        @JsonProperty("site_admin")
        private Boolean siteAdmin;
        @JsonProperty("starred_url")
        private String starredUrl;
        @JsonProperty("subscriptions_url")
        private String subscriptionsUrl;
        @JsonProperty("type")
        private String type;
        @JsonProperty("url")
        private String url;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Owner3() {
        }

        /**
         * 
         * @param receivedEventsUrl
         * @param siteAdmin
         * @param followingUrl
         * @param gistsUrl
         * @param avatarUrl
         * @param organizationsUrl
         * @param reposUrl
         * @param htmlUrl
         * @param subscriptionsUrl
         * @param login
         * @param type
         * @param starredUrl
         * @param url
         * @param gravatarId
         * @param followersUrl
         * @param eventsUrl
         * @param id
         * @param nodeId
         */
        public Owner3(String avatarUrl, String eventsUrl, String followersUrl, String followingUrl, String gistsUrl, String gravatarId, String htmlUrl, Long id, String login, String nodeId, String organizationsUrl, String receivedEventsUrl, String reposUrl, Boolean siteAdmin, String starredUrl, String subscriptionsUrl, String type, String url) {
            super();
            this.avatarUrl = avatarUrl;
            this.eventsUrl = eventsUrl;
            this.followersUrl = followersUrl;
            this.followingUrl = followingUrl;
            this.gistsUrl = gistsUrl;
            this.gravatarId = gravatarId;
            this.htmlUrl = htmlUrl;
            this.id = id;
            this.login = login;
            this.nodeId = nodeId;
            this.organizationsUrl = organizationsUrl;
            this.receivedEventsUrl = receivedEventsUrl;
            this.reposUrl = reposUrl;
            this.siteAdmin = siteAdmin;
            this.starredUrl = starredUrl;
            this.subscriptionsUrl = subscriptionsUrl;
            this.type = type;
            this.url = url;
        }

        @JsonProperty("avatar_url")
        public String getAvatarUrl() {
            return avatarUrl;
        }

        @JsonProperty("avatar_url")
        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        @JsonProperty("events_url")
        public String getEventsUrl() {
            return eventsUrl;
        }

        @JsonProperty("events_url")
        public void setEventsUrl(String eventsUrl) {
            this.eventsUrl = eventsUrl;
        }

        @JsonProperty("followers_url")
        public String getFollowersUrl() {
            return followersUrl;
        }

        @JsonProperty("followers_url")
        public void setFollowersUrl(String followersUrl) {
            this.followersUrl = followersUrl;
        }

        @JsonProperty("following_url")
        public String getFollowingUrl() {
            return followingUrl;
        }

        @JsonProperty("following_url")
        public void setFollowingUrl(String followingUrl) {
            this.followingUrl = followingUrl;
        }

        @JsonProperty("gists_url")
        public String getGistsUrl() {
            return gistsUrl;
        }

        @JsonProperty("gists_url")
        public void setGistsUrl(String gistsUrl) {
            this.gistsUrl = gistsUrl;
        }

        @JsonProperty("gravatar_id")
        public String getGravatarId() {
            return gravatarId;
        }

        @JsonProperty("gravatar_id")
        public void setGravatarId(String gravatarId) {
            this.gravatarId = gravatarId;
        }

        @JsonProperty("html_url")
        public String getHtmlUrl() {
            return htmlUrl;
        }

        @JsonProperty("html_url")
        public void setHtmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
        }

        @JsonProperty("id")
        public Long getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(Long id) {
            this.id = id;
        }

        @JsonProperty("login")
        public String getLogin() {
            return login;
        }

        @JsonProperty("login")
        public void setLogin(String login) {
            this.login = login;
        }

        @JsonProperty("node_id")
        public String getNodeId() {
            return nodeId;
        }

        @JsonProperty("node_id")
        public void setNodeId(String nodeId) {
            this.nodeId = nodeId;
        }

        @JsonProperty("organizations_url")
        public String getOrganizationsUrl() {
            return organizationsUrl;
        }

        @JsonProperty("organizations_url")
        public void setOrganizationsUrl(String organizationsUrl) {
            this.organizationsUrl = organizationsUrl;
        }

        @JsonProperty("received_events_url")
        public String getReceivedEventsUrl() {
            return receivedEventsUrl;
        }

        @JsonProperty("received_events_url")
        public void setReceivedEventsUrl(String receivedEventsUrl) {
            this.receivedEventsUrl = receivedEventsUrl;
        }

        @JsonProperty("repos_url")
        public String getReposUrl() {
            return reposUrl;
        }

        @JsonProperty("repos_url")
        public void setReposUrl(String reposUrl) {
            this.reposUrl = reposUrl;
        }

        @JsonProperty("site_admin")
        public Boolean getSiteAdmin() {
            return siteAdmin;
        }

        @JsonProperty("site_admin")
        public void setSiteAdmin(Boolean siteAdmin) {
            this.siteAdmin = siteAdmin;
        }

        @JsonProperty("starred_url")
        public String getStarredUrl() {
            return starredUrl;
        }

        @JsonProperty("starred_url")
        public void setStarredUrl(String starredUrl) {
            this.starredUrl = starredUrl;
        }

        @JsonProperty("subscriptions_url")
        public String getSubscriptionsUrl() {
            return subscriptionsUrl;
        }

        @JsonProperty("subscriptions_url")
        public void setSubscriptionsUrl(String subscriptionsUrl) {
            this.subscriptionsUrl = subscriptionsUrl;
        }

        @JsonProperty("type")
        public String getType() {
            return type;
        }

        @JsonProperty("type")
        public void setType(String type) {
            this.type = type;
        }

        @JsonProperty("url")
        public String getUrl() {
            return url;
        }

        @JsonProperty("url")
        public void setUrl(String url) {
            this.url = url;
        }

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(WorkflowRunGitHubEvent.Owner3 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("avatarUrl");
            sb.append('=');
            sb.append(((this.avatarUrl == null)?"<null>":this.avatarUrl));
            sb.append(',');
            sb.append("eventsUrl");
            sb.append('=');
            sb.append(((this.eventsUrl == null)?"<null>":this.eventsUrl));
            sb.append(',');
            sb.append("followersUrl");
            sb.append('=');
            sb.append(((this.followersUrl == null)?"<null>":this.followersUrl));
            sb.append(',');
            sb.append("followingUrl");
            sb.append('=');
            sb.append(((this.followingUrl == null)?"<null>":this.followingUrl));
            sb.append(',');
            sb.append("gistsUrl");
            sb.append('=');
            sb.append(((this.gistsUrl == null)?"<null>":this.gistsUrl));
            sb.append(',');
            sb.append("gravatarId");
            sb.append('=');
            sb.append(((this.gravatarId == null)?"<null>":this.gravatarId));
            sb.append(',');
            sb.append("htmlUrl");
            sb.append('=');
            sb.append(((this.htmlUrl == null)?"<null>":this.htmlUrl));
            sb.append(',');
            sb.append("id");
            sb.append('=');
            sb.append(((this.id == null)?"<null>":this.id));
            sb.append(',');
            sb.append("login");
            sb.append('=');
            sb.append(((this.login == null)?"<null>":this.login));
            sb.append(',');
            sb.append("nodeId");
            sb.append('=');
            sb.append(((this.nodeId == null)?"<null>":this.nodeId));
            sb.append(',');
            sb.append("organizationsUrl");
            sb.append('=');
            sb.append(((this.organizationsUrl == null)?"<null>":this.organizationsUrl));
            sb.append(',');
            sb.append("receivedEventsUrl");
            sb.append('=');
            sb.append(((this.receivedEventsUrl == null)?"<null>":this.receivedEventsUrl));
            sb.append(',');
            sb.append("reposUrl");
            sb.append('=');
            sb.append(((this.reposUrl == null)?"<null>":this.reposUrl));
            sb.append(',');
            sb.append("siteAdmin");
            sb.append('=');
            sb.append(((this.siteAdmin == null)?"<null>":this.siteAdmin));
            sb.append(',');
            sb.append("starredUrl");
            sb.append('=');
            sb.append(((this.starredUrl == null)?"<null>":this.starredUrl));
            sb.append(',');
            sb.append("subscriptionsUrl");
            sb.append('=');
            sb.append(((this.subscriptionsUrl == null)?"<null>":this.subscriptionsUrl));
            sb.append(',');
            sb.append("type");
            sb.append('=');
            sb.append(((this.type == null)?"<null>":this.type));
            sb.append(',');
            sb.append("url");
            sb.append('=');
            sb.append(((this.url == null)?"<null>":this.url));
            sb.append(',');
            sb.append("additionalProperties");
            sb.append('=');
            sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
            sb.append(',');
            if (sb.charAt((sb.length()- 1)) == ',') {
                sb.setCharAt((sb.length()- 1), ']');
            } else {
                sb.append(']');
            }
            return sb.toString();
        }

        @Override
        public int hashCode() {
            int result = 1;
            result = ((result* 31)+((this.receivedEventsUrl == null)? 0 :this.receivedEventsUrl.hashCode()));
            result = ((result* 31)+((this.siteAdmin == null)? 0 :this.siteAdmin.hashCode()));
            result = ((result* 31)+((this.followingUrl == null)? 0 :this.followingUrl.hashCode()));
            result = ((result* 31)+((this.gistsUrl == null)? 0 :this.gistsUrl.hashCode()));
            result = ((result* 31)+((this.avatarUrl == null)? 0 :this.avatarUrl.hashCode()));
            result = ((result* 31)+((this.organizationsUrl == null)? 0 :this.organizationsUrl.hashCode()));
            result = ((result* 31)+((this.reposUrl == null)? 0 :this.reposUrl.hashCode()));
            result = ((result* 31)+((this.htmlUrl == null)? 0 :this.htmlUrl.hashCode()));
            result = ((result* 31)+((this.subscriptionsUrl == null)? 0 :this.subscriptionsUrl.hashCode()));
            result = ((result* 31)+((this.login == null)? 0 :this.login.hashCode()));
            result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
            result = ((result* 31)+((this.starredUrl == null)? 0 :this.starredUrl.hashCode()));
            result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
            result = ((result* 31)+((this.gravatarId == null)? 0 :this.gravatarId.hashCode()));
            result = ((result* 31)+((this.followersUrl == null)? 0 :this.followersUrl.hashCode()));
            result = ((result* 31)+((this.eventsUrl == null)? 0 :this.eventsUrl.hashCode()));
            result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.nodeId == null)? 0 :this.nodeId.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof WorkflowRunGitHubEvent.Owner3) == false) {
                return false;
            }
            WorkflowRunGitHubEvent.Owner3 rhs = ((WorkflowRunGitHubEvent.Owner3) other);
            return ((((((((((((((((((((this.receivedEventsUrl == rhs.receivedEventsUrl)||((this.receivedEventsUrl!= null)&&this.receivedEventsUrl.equals(rhs.receivedEventsUrl)))&&((this.siteAdmin == rhs.siteAdmin)||((this.siteAdmin!= null)&&this.siteAdmin.equals(rhs.siteAdmin))))&&((this.followingUrl == rhs.followingUrl)||((this.followingUrl!= null)&&this.followingUrl.equals(rhs.followingUrl))))&&((this.gistsUrl == rhs.gistsUrl)||((this.gistsUrl!= null)&&this.gistsUrl.equals(rhs.gistsUrl))))&&((this.avatarUrl == rhs.avatarUrl)||((this.avatarUrl!= null)&&this.avatarUrl.equals(rhs.avatarUrl))))&&((this.organizationsUrl == rhs.organizationsUrl)||((this.organizationsUrl!= null)&&this.organizationsUrl.equals(rhs.organizationsUrl))))&&((this.reposUrl == rhs.reposUrl)||((this.reposUrl!= null)&&this.reposUrl.equals(rhs.reposUrl))))&&((this.htmlUrl == rhs.htmlUrl)||((this.htmlUrl!= null)&&this.htmlUrl.equals(rhs.htmlUrl))))&&((this.subscriptionsUrl == rhs.subscriptionsUrl)||((this.subscriptionsUrl!= null)&&this.subscriptionsUrl.equals(rhs.subscriptionsUrl))))&&((this.login == rhs.login)||((this.login!= null)&&this.login.equals(rhs.login))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.starredUrl == rhs.starredUrl)||((this.starredUrl!= null)&&this.starredUrl.equals(rhs.starredUrl))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))))&&((this.gravatarId == rhs.gravatarId)||((this.gravatarId!= null)&&this.gravatarId.equals(rhs.gravatarId))))&&((this.followersUrl == rhs.followersUrl)||((this.followersUrl!= null)&&this.followersUrl.equals(rhs.followersUrl))))&&((this.eventsUrl == rhs.eventsUrl)||((this.eventsUrl!= null)&&this.eventsUrl.equals(rhs.eventsUrl))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.nodeId == rhs.nodeId)||((this.nodeId!= null)&&this.nodeId.equals(rhs.nodeId))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "allow_forking",
        "archive_url",
        "archived",
        "assignees_url",
        "blobs_url",
        "branches_url",
        "clone_url",
        "collaborators_url",
        "comments_url",
        "commits_url",
        "compare_url",
        "contents_url",
        "contributors_url",
        "created_at",
        "default_branch",
        "deployments_url",
        "description",
        "disabled",
        "downloads_url",
        "events_url",
        "fork",
        "forks",
        "forks_count",
        "forks_url",
        "full_name",
        "git_commits_url",
        "git_refs_url",
        "git_tags_url",
        "git_url",
        "has_downloads",
        "has_issues",
        "has_pages",
        "has_projects",
        "has_wiki",
        "homepage",
        "hooks_url",
        "html_url",
        "id",
        "issue_comment_url",
        "issue_events_url",
        "issues_url",
        "keys_url",
        "labels_url",
        "language",
        "languages_url",
        "license",
        "merges_url",
        "milestones_url",
        "mirror_url",
        "name",
        "node_id",
        "notifications_url",
        "open_issues",
        "open_issues_count",
        "owner",
        "private",
        "pulls_url",
        "pushed_at",
        "releases_url",
        "size",
        "ssh_url",
        "stargazers_count",
        "stargazers_url",
        "statuses_url",
        "subscribers_url",
        "subscription_url",
        "svn_url",
        "tags_url",
        "teams_url",
        "trees_url",
        "updated_at",
        "url",
        "watchers",
        "watchers_count"
    })
    @Generated("jsonschema2pojo")
    public static class Repository {

        @JsonProperty("allow_forking")
        private Boolean allowForking;
        @JsonProperty("archive_url")
        private String archiveUrl;
        @JsonProperty("archived")
        private Boolean archived;
        @JsonProperty("assignees_url")
        private String assigneesUrl;
        @JsonProperty("blobs_url")
        private String blobsUrl;
        @JsonProperty("branches_url")
        private String branchesUrl;
        @JsonProperty("clone_url")
        private String cloneUrl;
        @JsonProperty("collaborators_url")
        private String collaboratorsUrl;
        @JsonProperty("comments_url")
        private String commentsUrl;
        @JsonProperty("commits_url")
        private String commitsUrl;
        @JsonProperty("compare_url")
        private String compareUrl;
        @JsonProperty("contents_url")
        private String contentsUrl;
        @JsonProperty("contributors_url")
        private String contributorsUrl;
        @JsonProperty("created_at")
        private String createdAt;
        @JsonProperty("default_branch")
        private String defaultBranch;
        @JsonProperty("deployments_url")
        private String deploymentsUrl;
        @JsonProperty("description")
        private Object description;
        @JsonProperty("disabled")
        private Boolean disabled;
        @JsonProperty("downloads_url")
        private String downloadsUrl;
        @JsonProperty("events_url")
        private String eventsUrl;
        @JsonProperty("fork")
        private Boolean fork;
        @JsonProperty("forks")
        private Long forks;
        @JsonProperty("forks_count")
        private Long forksCount;
        @JsonProperty("forks_url")
        private String forksUrl;
        @JsonProperty("full_name")
        private String fullName;
        @JsonProperty("git_commits_url")
        private String gitCommitsUrl;
        @JsonProperty("git_refs_url")
        private String gitRefsUrl;
        @JsonProperty("git_tags_url")
        private String gitTagsUrl;
        @JsonProperty("git_url")
        private String gitUrl;
        @JsonProperty("has_downloads")
        private Boolean hasDownloads;
        @JsonProperty("has_issues")
        private Boolean hasIssues;
        @JsonProperty("has_pages")
        private Boolean hasPages;
        @JsonProperty("has_projects")
        private Boolean hasProjects;
        @JsonProperty("has_wiki")
        private Boolean hasWiki;
        @JsonProperty("homepage")
        private Object homepage;
        @JsonProperty("hooks_url")
        private String hooksUrl;
        @JsonProperty("html_url")
        private String htmlUrl;
        @JsonProperty("id")
        private Long id;
        @JsonProperty("issue_comment_url")
        private String issueCommentUrl;
        @JsonProperty("issue_events_url")
        private String issueEventsUrl;
        @JsonProperty("issues_url")
        private String issuesUrl;
        @JsonProperty("keys_url")
        private String keysUrl;
        @JsonProperty("labels_url")
        private String labelsUrl;
        @JsonProperty("language")
        private String language;
        @JsonProperty("languages_url")
        private String languagesUrl;
        @JsonProperty("license")
        private Object license;
        @JsonProperty("merges_url")
        private String mergesUrl;
        @JsonProperty("milestones_url")
        private String milestonesUrl;
        @JsonProperty("mirror_url")
        private Object mirrorUrl;
        @JsonProperty("name")
        private String name;
        @JsonProperty("node_id")
        private String nodeId;
        @JsonProperty("notifications_url")
        private String notificationsUrl;
        @JsonProperty("open_issues")
        private Long openIssues;
        @JsonProperty("open_issues_count")
        private Long openIssuesCount;
        @JsonProperty("owner")
        private WorkflowRunGitHubEvent.Owner owner;
        @JsonProperty("private")
        private Boolean _private;
        @JsonProperty("pulls_url")
        private String pullsUrl;
        @JsonProperty("pushed_at")
        private String pushedAt;
        @JsonProperty("releases_url")
        private String releasesUrl;
        @JsonProperty("size")
        private Long size;
        @JsonProperty("ssh_url")
        private String sshUrl;
        @JsonProperty("stargazers_count")
        private Long stargazersCount;
        @JsonProperty("stargazers_url")
        private String stargazersUrl;
        @JsonProperty("statuses_url")
        private String statusesUrl;
        @JsonProperty("subscribers_url")
        private String subscribersUrl;
        @JsonProperty("subscription_url")
        private String subscriptionUrl;
        @JsonProperty("svn_url")
        private String svnUrl;
        @JsonProperty("tags_url")
        private String tagsUrl;
        @JsonProperty("teams_url")
        private String teamsUrl;
        @JsonProperty("trees_url")
        private String treesUrl;
        @JsonProperty("updated_at")
        private String updatedAt;
        @JsonProperty("url")
        private String url;
        @JsonProperty("watchers")
        private Long watchers;
        @JsonProperty("watchers_count")
        private Long watchersCount;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Repository() {
        }

        /**
         * 
         * @param sshUrl
         * @param archiveUrl
         * @param language
         * @param languagesUrl
         * @param assigneesUrl
         * @param commitsUrl
         * @param openIssues
         * @param cloneUrl
         * @param forksCount
         * @param subscribersUrl
         * @param createdAt
         * @param forksUrl
         * @param watchersCount
         * @param _private
         * @param issueCommentUrl
         * @param statusesUrl
         * @param id
         * @param collaboratorsUrl
         * @param updatedAt
         * @param forks
         * @param allowForking
         * @param labelsUrl
         * @param defaultBranch
         * @param keysUrl
         * @param downloadsUrl
         * @param contentsUrl
         * @param pushedAt
         * @param tagsUrl
         * @param license
         * @param commentsUrl
         * @param size
         * @param treesUrl
         * @param name
         * @param mergesUrl
         * @param nodeId
         * @param teamsUrl
         * @param blobsUrl
         * @param hasPages
         * @param issueEventsUrl
         * @param milestonesUrl
         * @param issuesUrl
         * @param releasesUrl
         * @param description
         * @param watchers
         * @param branchesUrl
         * @param contributorsUrl
         * @param gitRefsUrl
         * @param hooksUrl
         * @param openIssuesCount
         * @param archived
         * @param stargazersCount
         * @param disabled
         * @param hasIssues
         * @param owner
         * @param hasWiki
         * @param compareUrl
         * @param gitCommitsUrl
         * @param htmlUrl
         * @param stargazersUrl
         * @param fullName
         * @param svnUrl
         * @param mirrorUrl
         * @param pullsUrl
         * @param url
         * @param hasDownloads
         * @param fork
         * @param hasProjects
         * @param deploymentsUrl
         * @param eventsUrl
         * @param gitTagsUrl
         * @param notificationsUrl
         * @param gitUrl
         * @param homepage
         * @param subscriptionUrl
         */
        public Repository(Boolean allowForking, String archiveUrl, Boolean archived, String assigneesUrl, String blobsUrl, String branchesUrl, String cloneUrl, String collaboratorsUrl, String commentsUrl, String commitsUrl, String compareUrl, String contentsUrl, String contributorsUrl, String createdAt, String defaultBranch, String deploymentsUrl, Object description, Boolean disabled, String downloadsUrl, String eventsUrl, Boolean fork, Long forks, Long forksCount, String forksUrl, String fullName, String gitCommitsUrl, String gitRefsUrl, String gitTagsUrl, String gitUrl, Boolean hasDownloads, Boolean hasIssues, Boolean hasPages, Boolean hasProjects, Boolean hasWiki, Object homepage, String hooksUrl, String htmlUrl, Long id, String issueCommentUrl, String issueEventsUrl, String issuesUrl, String keysUrl, String labelsUrl, String language, String languagesUrl, Object license, String mergesUrl, String milestonesUrl, Object mirrorUrl, String name, String nodeId, String notificationsUrl, Long openIssues, Long openIssuesCount, WorkflowRunGitHubEvent.Owner owner, Boolean _private, String pullsUrl, String pushedAt, String releasesUrl, Long size, String sshUrl, Long stargazersCount, String stargazersUrl, String statusesUrl, String subscribersUrl, String subscriptionUrl, String svnUrl, String tagsUrl, String teamsUrl, String treesUrl, String updatedAt, String url, Long watchers, Long watchersCount) {
            super();
            this.allowForking = allowForking;
            this.archiveUrl = archiveUrl;
            this.archived = archived;
            this.assigneesUrl = assigneesUrl;
            this.blobsUrl = blobsUrl;
            this.branchesUrl = branchesUrl;
            this.cloneUrl = cloneUrl;
            this.collaboratorsUrl = collaboratorsUrl;
            this.commentsUrl = commentsUrl;
            this.commitsUrl = commitsUrl;
            this.compareUrl = compareUrl;
            this.contentsUrl = contentsUrl;
            this.contributorsUrl = contributorsUrl;
            this.createdAt = createdAt;
            this.defaultBranch = defaultBranch;
            this.deploymentsUrl = deploymentsUrl;
            this.description = description;
            this.disabled = disabled;
            this.downloadsUrl = downloadsUrl;
            this.eventsUrl = eventsUrl;
            this.fork = fork;
            this.forks = forks;
            this.forksCount = forksCount;
            this.forksUrl = forksUrl;
            this.fullName = fullName;
            this.gitCommitsUrl = gitCommitsUrl;
            this.gitRefsUrl = gitRefsUrl;
            this.gitTagsUrl = gitTagsUrl;
            this.gitUrl = gitUrl;
            this.hasDownloads = hasDownloads;
            this.hasIssues = hasIssues;
            this.hasPages = hasPages;
            this.hasProjects = hasProjects;
            this.hasWiki = hasWiki;
            this.homepage = homepage;
            this.hooksUrl = hooksUrl;
            this.htmlUrl = htmlUrl;
            this.id = id;
            this.issueCommentUrl = issueCommentUrl;
            this.issueEventsUrl = issueEventsUrl;
            this.issuesUrl = issuesUrl;
            this.keysUrl = keysUrl;
            this.labelsUrl = labelsUrl;
            this.language = language;
            this.languagesUrl = languagesUrl;
            this.license = license;
            this.mergesUrl = mergesUrl;
            this.milestonesUrl = milestonesUrl;
            this.mirrorUrl = mirrorUrl;
            this.name = name;
            this.nodeId = nodeId;
            this.notificationsUrl = notificationsUrl;
            this.openIssues = openIssues;
            this.openIssuesCount = openIssuesCount;
            this.owner = owner;
            this._private = _private;
            this.pullsUrl = pullsUrl;
            this.pushedAt = pushedAt;
            this.releasesUrl = releasesUrl;
            this.size = size;
            this.sshUrl = sshUrl;
            this.stargazersCount = stargazersCount;
            this.stargazersUrl = stargazersUrl;
            this.statusesUrl = statusesUrl;
            this.subscribersUrl = subscribersUrl;
            this.subscriptionUrl = subscriptionUrl;
            this.svnUrl = svnUrl;
            this.tagsUrl = tagsUrl;
            this.teamsUrl = teamsUrl;
            this.treesUrl = treesUrl;
            this.updatedAt = updatedAt;
            this.url = url;
            this.watchers = watchers;
            this.watchersCount = watchersCount;
        }

        @JsonProperty("allow_forking")
        public Boolean getAllowForking() {
            return allowForking;
        }

        @JsonProperty("allow_forking")
        public void setAllowForking(Boolean allowForking) {
            this.allowForking = allowForking;
        }

        @JsonProperty("archive_url")
        public String getArchiveUrl() {
            return archiveUrl;
        }

        @JsonProperty("archive_url")
        public void setArchiveUrl(String archiveUrl) {
            this.archiveUrl = archiveUrl;
        }

        @JsonProperty("archived")
        public Boolean getArchived() {
            return archived;
        }

        @JsonProperty("archived")
        public void setArchived(Boolean archived) {
            this.archived = archived;
        }

        @JsonProperty("assignees_url")
        public String getAssigneesUrl() {
            return assigneesUrl;
        }

        @JsonProperty("assignees_url")
        public void setAssigneesUrl(String assigneesUrl) {
            this.assigneesUrl = assigneesUrl;
        }

        @JsonProperty("blobs_url")
        public String getBlobsUrl() {
            return blobsUrl;
        }

        @JsonProperty("blobs_url")
        public void setBlobsUrl(String blobsUrl) {
            this.blobsUrl = blobsUrl;
        }

        @JsonProperty("branches_url")
        public String getBranchesUrl() {
            return branchesUrl;
        }

        @JsonProperty("branches_url")
        public void setBranchesUrl(String branchesUrl) {
            this.branchesUrl = branchesUrl;
        }

        @JsonProperty("clone_url")
        public String getCloneUrl() {
            return cloneUrl;
        }

        @JsonProperty("clone_url")
        public void setCloneUrl(String cloneUrl) {
            this.cloneUrl = cloneUrl;
        }

        @JsonProperty("collaborators_url")
        public String getCollaboratorsUrl() {
            return collaboratorsUrl;
        }

        @JsonProperty("collaborators_url")
        public void setCollaboratorsUrl(String collaboratorsUrl) {
            this.collaboratorsUrl = collaboratorsUrl;
        }

        @JsonProperty("comments_url")
        public String getCommentsUrl() {
            return commentsUrl;
        }

        @JsonProperty("comments_url")
        public void setCommentsUrl(String commentsUrl) {
            this.commentsUrl = commentsUrl;
        }

        @JsonProperty("commits_url")
        public String getCommitsUrl() {
            return commitsUrl;
        }

        @JsonProperty("commits_url")
        public void setCommitsUrl(String commitsUrl) {
            this.commitsUrl = commitsUrl;
        }

        @JsonProperty("compare_url")
        public String getCompareUrl() {
            return compareUrl;
        }

        @JsonProperty("compare_url")
        public void setCompareUrl(String compareUrl) {
            this.compareUrl = compareUrl;
        }

        @JsonProperty("contents_url")
        public String getContentsUrl() {
            return contentsUrl;
        }

        @JsonProperty("contents_url")
        public void setContentsUrl(String contentsUrl) {
            this.contentsUrl = contentsUrl;
        }

        @JsonProperty("contributors_url")
        public String getContributorsUrl() {
            return contributorsUrl;
        }

        @JsonProperty("contributors_url")
        public void setContributorsUrl(String contributorsUrl) {
            this.contributorsUrl = contributorsUrl;
        }

        @JsonProperty("created_at")
        public String getCreatedAt() {
            return createdAt;
        }

        @JsonProperty("created_at")
        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        @JsonProperty("default_branch")
        public String getDefaultBranch() {
            return defaultBranch;
        }

        @JsonProperty("default_branch")
        public void setDefaultBranch(String defaultBranch) {
            this.defaultBranch = defaultBranch;
        }

        @JsonProperty("deployments_url")
        public String getDeploymentsUrl() {
            return deploymentsUrl;
        }

        @JsonProperty("deployments_url")
        public void setDeploymentsUrl(String deploymentsUrl) {
            this.deploymentsUrl = deploymentsUrl;
        }

        @JsonProperty("description")
        public Object getDescription() {
            return description;
        }

        @JsonProperty("description")
        public void setDescription(Object description) {
            this.description = description;
        }

        @JsonProperty("disabled")
        public Boolean getDisabled() {
            return disabled;
        }

        @JsonProperty("disabled")
        public void setDisabled(Boolean disabled) {
            this.disabled = disabled;
        }

        @JsonProperty("downloads_url")
        public String getDownloadsUrl() {
            return downloadsUrl;
        }

        @JsonProperty("downloads_url")
        public void setDownloadsUrl(String downloadsUrl) {
            this.downloadsUrl = downloadsUrl;
        }

        @JsonProperty("events_url")
        public String getEventsUrl() {
            return eventsUrl;
        }

        @JsonProperty("events_url")
        public void setEventsUrl(String eventsUrl) {
            this.eventsUrl = eventsUrl;
        }

        @JsonProperty("fork")
        public Boolean getFork() {
            return fork;
        }

        @JsonProperty("fork")
        public void setFork(Boolean fork) {
            this.fork = fork;
        }

        @JsonProperty("forks")
        public Long getForks() {
            return forks;
        }

        @JsonProperty("forks")
        public void setForks(Long forks) {
            this.forks = forks;
        }

        @JsonProperty("forks_count")
        public Long getForksCount() {
            return forksCount;
        }

        @JsonProperty("forks_count")
        public void setForksCount(Long forksCount) {
            this.forksCount = forksCount;
        }

        @JsonProperty("forks_url")
        public String getForksUrl() {
            return forksUrl;
        }

        @JsonProperty("forks_url")
        public void setForksUrl(String forksUrl) {
            this.forksUrl = forksUrl;
        }

        @JsonProperty("full_name")
        public String getFullName() {
            return fullName;
        }

        @JsonProperty("full_name")
        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        @JsonProperty("git_commits_url")
        public String getGitCommitsUrl() {
            return gitCommitsUrl;
        }

        @JsonProperty("git_commits_url")
        public void setGitCommitsUrl(String gitCommitsUrl) {
            this.gitCommitsUrl = gitCommitsUrl;
        }

        @JsonProperty("git_refs_url")
        public String getGitRefsUrl() {
            return gitRefsUrl;
        }

        @JsonProperty("git_refs_url")
        public void setGitRefsUrl(String gitRefsUrl) {
            this.gitRefsUrl = gitRefsUrl;
        }

        @JsonProperty("git_tags_url")
        public String getGitTagsUrl() {
            return gitTagsUrl;
        }

        @JsonProperty("git_tags_url")
        public void setGitTagsUrl(String gitTagsUrl) {
            this.gitTagsUrl = gitTagsUrl;
        }

        @JsonProperty("git_url")
        public String getGitUrl() {
            return gitUrl;
        }

        @JsonProperty("git_url")
        public void setGitUrl(String gitUrl) {
            this.gitUrl = gitUrl;
        }

        @JsonProperty("has_downloads")
        public Boolean getHasDownloads() {
            return hasDownloads;
        }

        @JsonProperty("has_downloads")
        public void setHasDownloads(Boolean hasDownloads) {
            this.hasDownloads = hasDownloads;
        }

        @JsonProperty("has_issues")
        public Boolean getHasIssues() {
            return hasIssues;
        }

        @JsonProperty("has_issues")
        public void setHasIssues(Boolean hasIssues) {
            this.hasIssues = hasIssues;
        }

        @JsonProperty("has_pages")
        public Boolean getHasPages() {
            return hasPages;
        }

        @JsonProperty("has_pages")
        public void setHasPages(Boolean hasPages) {
            this.hasPages = hasPages;
        }

        @JsonProperty("has_projects")
        public Boolean getHasProjects() {
            return hasProjects;
        }

        @JsonProperty("has_projects")
        public void setHasProjects(Boolean hasProjects) {
            this.hasProjects = hasProjects;
        }

        @JsonProperty("has_wiki")
        public Boolean getHasWiki() {
            return hasWiki;
        }

        @JsonProperty("has_wiki")
        public void setHasWiki(Boolean hasWiki) {
            this.hasWiki = hasWiki;
        }

        @JsonProperty("homepage")
        public Object getHomepage() {
            return homepage;
        }

        @JsonProperty("homepage")
        public void setHomepage(Object homepage) {
            this.homepage = homepage;
        }

        @JsonProperty("hooks_url")
        public String getHooksUrl() {
            return hooksUrl;
        }

        @JsonProperty("hooks_url")
        public void setHooksUrl(String hooksUrl) {
            this.hooksUrl = hooksUrl;
        }

        @JsonProperty("html_url")
        public String getHtmlUrl() {
            return htmlUrl;
        }

        @JsonProperty("html_url")
        public void setHtmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
        }

        @JsonProperty("id")
        public Long getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(Long id) {
            this.id = id;
        }

        @JsonProperty("issue_comment_url")
        public String getIssueCommentUrl() {
            return issueCommentUrl;
        }

        @JsonProperty("issue_comment_url")
        public void setIssueCommentUrl(String issueCommentUrl) {
            this.issueCommentUrl = issueCommentUrl;
        }

        @JsonProperty("issue_events_url")
        public String getIssueEventsUrl() {
            return issueEventsUrl;
        }

        @JsonProperty("issue_events_url")
        public void setIssueEventsUrl(String issueEventsUrl) {
            this.issueEventsUrl = issueEventsUrl;
        }

        @JsonProperty("issues_url")
        public String getIssuesUrl() {
            return issuesUrl;
        }

        @JsonProperty("issues_url")
        public void setIssuesUrl(String issuesUrl) {
            this.issuesUrl = issuesUrl;
        }

        @JsonProperty("keys_url")
        public String getKeysUrl() {
            return keysUrl;
        }

        @JsonProperty("keys_url")
        public void setKeysUrl(String keysUrl) {
            this.keysUrl = keysUrl;
        }

        @JsonProperty("labels_url")
        public String getLabelsUrl() {
            return labelsUrl;
        }

        @JsonProperty("labels_url")
        public void setLabelsUrl(String labelsUrl) {
            this.labelsUrl = labelsUrl;
        }

        @JsonProperty("language")
        public String getLanguage() {
            return language;
        }

        @JsonProperty("language")
        public void setLanguage(String language) {
            this.language = language;
        }

        @JsonProperty("languages_url")
        public String getLanguagesUrl() {
            return languagesUrl;
        }

        @JsonProperty("languages_url")
        public void setLanguagesUrl(String languagesUrl) {
            this.languagesUrl = languagesUrl;
        }

        @JsonProperty("license")
        public Object getLicense() {
            return license;
        }

        @JsonProperty("license")
        public void setLicense(Object license) {
            this.license = license;
        }

        @JsonProperty("merges_url")
        public String getMergesUrl() {
            return mergesUrl;
        }

        @JsonProperty("merges_url")
        public void setMergesUrl(String mergesUrl) {
            this.mergesUrl = mergesUrl;
        }

        @JsonProperty("milestones_url")
        public String getMilestonesUrl() {
            return milestonesUrl;
        }

        @JsonProperty("milestones_url")
        public void setMilestonesUrl(String milestonesUrl) {
            this.milestonesUrl = milestonesUrl;
        }

        @JsonProperty("mirror_url")
        public Object getMirrorUrl() {
            return mirrorUrl;
        }

        @JsonProperty("mirror_url")
        public void setMirrorUrl(Object mirrorUrl) {
            this.mirrorUrl = mirrorUrl;
        }

        @JsonProperty("name")
        public String getName() {
            return name;
        }

        @JsonProperty("name")
        public void setName(String name) {
            this.name = name;
        }

        @JsonProperty("node_id")
        public String getNodeId() {
            return nodeId;
        }

        @JsonProperty("node_id")
        public void setNodeId(String nodeId) {
            this.nodeId = nodeId;
        }

        @JsonProperty("notifications_url")
        public String getNotificationsUrl() {
            return notificationsUrl;
        }

        @JsonProperty("notifications_url")
        public void setNotificationsUrl(String notificationsUrl) {
            this.notificationsUrl = notificationsUrl;
        }

        @JsonProperty("open_issues")
        public Long getOpenIssues() {
            return openIssues;
        }

        @JsonProperty("open_issues")
        public void setOpenIssues(Long openIssues) {
            this.openIssues = openIssues;
        }

        @JsonProperty("open_issues_count")
        public Long getOpenIssuesCount() {
            return openIssuesCount;
        }

        @JsonProperty("open_issues_count")
        public void setOpenIssuesCount(Long openIssuesCount) {
            this.openIssuesCount = openIssuesCount;
        }

        @JsonProperty("owner")
        public WorkflowRunGitHubEvent.Owner getOwner() {
            return owner;
        }

        @JsonProperty("owner")
        public void setOwner(WorkflowRunGitHubEvent.Owner owner) {
            this.owner = owner;
        }

        @JsonProperty("private")
        public Boolean getPrivate() {
            return _private;
        }

        @JsonProperty("private")
        public void setPrivate(Boolean _private) {
            this._private = _private;
        }

        @JsonProperty("pulls_url")
        public String getPullsUrl() {
            return pullsUrl;
        }

        @JsonProperty("pulls_url")
        public void setPullsUrl(String pullsUrl) {
            this.pullsUrl = pullsUrl;
        }

        @JsonProperty("pushed_at")
        public String getPushedAt() {
            return pushedAt;
        }

        @JsonProperty("pushed_at")
        public void setPushedAt(String pushedAt) {
            this.pushedAt = pushedAt;
        }

        @JsonProperty("releases_url")
        public String getReleasesUrl() {
            return releasesUrl;
        }

        @JsonProperty("releases_url")
        public void setReleasesUrl(String releasesUrl) {
            this.releasesUrl = releasesUrl;
        }

        @JsonProperty("size")
        public Long getSize() {
            return size;
        }

        @JsonProperty("size")
        public void setSize(Long size) {
            this.size = size;
        }

        @JsonProperty("ssh_url")
        public String getSshUrl() {
            return sshUrl;
        }

        @JsonProperty("ssh_url")
        public void setSshUrl(String sshUrl) {
            this.sshUrl = sshUrl;
        }

        @JsonProperty("stargazers_count")
        public Long getStargazersCount() {
            return stargazersCount;
        }

        @JsonProperty("stargazers_count")
        public void setStargazersCount(Long stargazersCount) {
            this.stargazersCount = stargazersCount;
        }

        @JsonProperty("stargazers_url")
        public String getStargazersUrl() {
            return stargazersUrl;
        }

        @JsonProperty("stargazers_url")
        public void setStargazersUrl(String stargazersUrl) {
            this.stargazersUrl = stargazersUrl;
        }

        @JsonProperty("statuses_url")
        public String getStatusesUrl() {
            return statusesUrl;
        }

        @JsonProperty("statuses_url")
        public void setStatusesUrl(String statusesUrl) {
            this.statusesUrl = statusesUrl;
        }

        @JsonProperty("subscribers_url")
        public String getSubscribersUrl() {
            return subscribersUrl;
        }

        @JsonProperty("subscribers_url")
        public void setSubscribersUrl(String subscribersUrl) {
            this.subscribersUrl = subscribersUrl;
        }

        @JsonProperty("subscription_url")
        public String getSubscriptionUrl() {
            return subscriptionUrl;
        }

        @JsonProperty("subscription_url")
        public void setSubscriptionUrl(String subscriptionUrl) {
            this.subscriptionUrl = subscriptionUrl;
        }

        @JsonProperty("svn_url")
        public String getSvnUrl() {
            return svnUrl;
        }

        @JsonProperty("svn_url")
        public void setSvnUrl(String svnUrl) {
            this.svnUrl = svnUrl;
        }

        @JsonProperty("tags_url")
        public String getTagsUrl() {
            return tagsUrl;
        }

        @JsonProperty("tags_url")
        public void setTagsUrl(String tagsUrl) {
            this.tagsUrl = tagsUrl;
        }

        @JsonProperty("teams_url")
        public String getTeamsUrl() {
            return teamsUrl;
        }

        @JsonProperty("teams_url")
        public void setTeamsUrl(String teamsUrl) {
            this.teamsUrl = teamsUrl;
        }

        @JsonProperty("trees_url")
        public String getTreesUrl() {
            return treesUrl;
        }

        @JsonProperty("trees_url")
        public void setTreesUrl(String treesUrl) {
            this.treesUrl = treesUrl;
        }

        @JsonProperty("updated_at")
        public String getUpdatedAt() {
            return updatedAt;
        }

        @JsonProperty("updated_at")
        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        @JsonProperty("url")
        public String getUrl() {
            return url;
        }

        @JsonProperty("url")
        public void setUrl(String url) {
            this.url = url;
        }

        @JsonProperty("watchers")
        public Long getWatchers() {
            return watchers;
        }

        @JsonProperty("watchers")
        public void setWatchers(Long watchers) {
            this.watchers = watchers;
        }

        @JsonProperty("watchers_count")
        public Long getWatchersCount() {
            return watchersCount;
        }

        @JsonProperty("watchers_count")
        public void setWatchersCount(Long watchersCount) {
            this.watchersCount = watchersCount;
        }

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(WorkflowRunGitHubEvent.Repository.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("allowForking");
            sb.append('=');
            sb.append(((this.allowForking == null)?"<null>":this.allowForking));
            sb.append(',');
            sb.append("archiveUrl");
            sb.append('=');
            sb.append(((this.archiveUrl == null)?"<null>":this.archiveUrl));
            sb.append(',');
            sb.append("archived");
            sb.append('=');
            sb.append(((this.archived == null)?"<null>":this.archived));
            sb.append(',');
            sb.append("assigneesUrl");
            sb.append('=');
            sb.append(((this.assigneesUrl == null)?"<null>":this.assigneesUrl));
            sb.append(',');
            sb.append("blobsUrl");
            sb.append('=');
            sb.append(((this.blobsUrl == null)?"<null>":this.blobsUrl));
            sb.append(',');
            sb.append("branchesUrl");
            sb.append('=');
            sb.append(((this.branchesUrl == null)?"<null>":this.branchesUrl));
            sb.append(',');
            sb.append("cloneUrl");
            sb.append('=');
            sb.append(((this.cloneUrl == null)?"<null>":this.cloneUrl));
            sb.append(',');
            sb.append("collaboratorsUrl");
            sb.append('=');
            sb.append(((this.collaboratorsUrl == null)?"<null>":this.collaboratorsUrl));
            sb.append(',');
            sb.append("commentsUrl");
            sb.append('=');
            sb.append(((this.commentsUrl == null)?"<null>":this.commentsUrl));
            sb.append(',');
            sb.append("commitsUrl");
            sb.append('=');
            sb.append(((this.commitsUrl == null)?"<null>":this.commitsUrl));
            sb.append(',');
            sb.append("compareUrl");
            sb.append('=');
            sb.append(((this.compareUrl == null)?"<null>":this.compareUrl));
            sb.append(',');
            sb.append("contentsUrl");
            sb.append('=');
            sb.append(((this.contentsUrl == null)?"<null>":this.contentsUrl));
            sb.append(',');
            sb.append("contributorsUrl");
            sb.append('=');
            sb.append(((this.contributorsUrl == null)?"<null>":this.contributorsUrl));
            sb.append(',');
            sb.append("createdAt");
            sb.append('=');
            sb.append(((this.createdAt == null)?"<null>":this.createdAt));
            sb.append(',');
            sb.append("defaultBranch");
            sb.append('=');
            sb.append(((this.defaultBranch == null)?"<null>":this.defaultBranch));
            sb.append(',');
            sb.append("deploymentsUrl");
            sb.append('=');
            sb.append(((this.deploymentsUrl == null)?"<null>":this.deploymentsUrl));
            sb.append(',');
            sb.append("description");
            sb.append('=');
            sb.append(((this.description == null)?"<null>":this.description));
            sb.append(',');
            sb.append("disabled");
            sb.append('=');
            sb.append(((this.disabled == null)?"<null>":this.disabled));
            sb.append(',');
            sb.append("downloadsUrl");
            sb.append('=');
            sb.append(((this.downloadsUrl == null)?"<null>":this.downloadsUrl));
            sb.append(',');
            sb.append("eventsUrl");
            sb.append('=');
            sb.append(((this.eventsUrl == null)?"<null>":this.eventsUrl));
            sb.append(',');
            sb.append("fork");
            sb.append('=');
            sb.append(((this.fork == null)?"<null>":this.fork));
            sb.append(',');
            sb.append("forks");
            sb.append('=');
            sb.append(((this.forks == null)?"<null>":this.forks));
            sb.append(',');
            sb.append("forksCount");
            sb.append('=');
            sb.append(((this.forksCount == null)?"<null>":this.forksCount));
            sb.append(',');
            sb.append("forksUrl");
            sb.append('=');
            sb.append(((this.forksUrl == null)?"<null>":this.forksUrl));
            sb.append(',');
            sb.append("fullName");
            sb.append('=');
            sb.append(((this.fullName == null)?"<null>":this.fullName));
            sb.append(',');
            sb.append("gitCommitsUrl");
            sb.append('=');
            sb.append(((this.gitCommitsUrl == null)?"<null>":this.gitCommitsUrl));
            sb.append(',');
            sb.append("gitRefsUrl");
            sb.append('=');
            sb.append(((this.gitRefsUrl == null)?"<null>":this.gitRefsUrl));
            sb.append(',');
            sb.append("gitTagsUrl");
            sb.append('=');
            sb.append(((this.gitTagsUrl == null)?"<null>":this.gitTagsUrl));
            sb.append(',');
            sb.append("gitUrl");
            sb.append('=');
            sb.append(((this.gitUrl == null)?"<null>":this.gitUrl));
            sb.append(',');
            sb.append("hasDownloads");
            sb.append('=');
            sb.append(((this.hasDownloads == null)?"<null>":this.hasDownloads));
            sb.append(',');
            sb.append("hasIssues");
            sb.append('=');
            sb.append(((this.hasIssues == null)?"<null>":this.hasIssues));
            sb.append(',');
            sb.append("hasPages");
            sb.append('=');
            sb.append(((this.hasPages == null)?"<null>":this.hasPages));
            sb.append(',');
            sb.append("hasProjects");
            sb.append('=');
            sb.append(((this.hasProjects == null)?"<null>":this.hasProjects));
            sb.append(',');
            sb.append("hasWiki");
            sb.append('=');
            sb.append(((this.hasWiki == null)?"<null>":this.hasWiki));
            sb.append(',');
            sb.append("homepage");
            sb.append('=');
            sb.append(((this.homepage == null)?"<null>":this.homepage));
            sb.append(',');
            sb.append("hooksUrl");
            sb.append('=');
            sb.append(((this.hooksUrl == null)?"<null>":this.hooksUrl));
            sb.append(',');
            sb.append("htmlUrl");
            sb.append('=');
            sb.append(((this.htmlUrl == null)?"<null>":this.htmlUrl));
            sb.append(',');
            sb.append("id");
            sb.append('=');
            sb.append(((this.id == null)?"<null>":this.id));
            sb.append(',');
            sb.append("issueCommentUrl");
            sb.append('=');
            sb.append(((this.issueCommentUrl == null)?"<null>":this.issueCommentUrl));
            sb.append(',');
            sb.append("issueEventsUrl");
            sb.append('=');
            sb.append(((this.issueEventsUrl == null)?"<null>":this.issueEventsUrl));
            sb.append(',');
            sb.append("issuesUrl");
            sb.append('=');
            sb.append(((this.issuesUrl == null)?"<null>":this.issuesUrl));
            sb.append(',');
            sb.append("keysUrl");
            sb.append('=');
            sb.append(((this.keysUrl == null)?"<null>":this.keysUrl));
            sb.append(',');
            sb.append("labelsUrl");
            sb.append('=');
            sb.append(((this.labelsUrl == null)?"<null>":this.labelsUrl));
            sb.append(',');
            sb.append("language");
            sb.append('=');
            sb.append(((this.language == null)?"<null>":this.language));
            sb.append(',');
            sb.append("languagesUrl");
            sb.append('=');
            sb.append(((this.languagesUrl == null)?"<null>":this.languagesUrl));
            sb.append(',');
            sb.append("license");
            sb.append('=');
            sb.append(((this.license == null)?"<null>":this.license));
            sb.append(',');
            sb.append("mergesUrl");
            sb.append('=');
            sb.append(((this.mergesUrl == null)?"<null>":this.mergesUrl));
            sb.append(',');
            sb.append("milestonesUrl");
            sb.append('=');
            sb.append(((this.milestonesUrl == null)?"<null>":this.milestonesUrl));
            sb.append(',');
            sb.append("mirrorUrl");
            sb.append('=');
            sb.append(((this.mirrorUrl == null)?"<null>":this.mirrorUrl));
            sb.append(',');
            sb.append("name");
            sb.append('=');
            sb.append(((this.name == null)?"<null>":this.name));
            sb.append(',');
            sb.append("nodeId");
            sb.append('=');
            sb.append(((this.nodeId == null)?"<null>":this.nodeId));
            sb.append(',');
            sb.append("notificationsUrl");
            sb.append('=');
            sb.append(((this.notificationsUrl == null)?"<null>":this.notificationsUrl));
            sb.append(',');
            sb.append("openIssues");
            sb.append('=');
            sb.append(((this.openIssues == null)?"<null>":this.openIssues));
            sb.append(',');
            sb.append("openIssuesCount");
            sb.append('=');
            sb.append(((this.openIssuesCount == null)?"<null>":this.openIssuesCount));
            sb.append(',');
            sb.append("owner");
            sb.append('=');
            sb.append(((this.owner == null)?"<null>":this.owner));
            sb.append(',');
            sb.append("_private");
            sb.append('=');
            sb.append(((this._private == null)?"<null>":this._private));
            sb.append(',');
            sb.append("pullsUrl");
            sb.append('=');
            sb.append(((this.pullsUrl == null)?"<null>":this.pullsUrl));
            sb.append(',');
            sb.append("pushedAt");
            sb.append('=');
            sb.append(((this.pushedAt == null)?"<null>":this.pushedAt));
            sb.append(',');
            sb.append("releasesUrl");
            sb.append('=');
            sb.append(((this.releasesUrl == null)?"<null>":this.releasesUrl));
            sb.append(',');
            sb.append("size");
            sb.append('=');
            sb.append(((this.size == null)?"<null>":this.size));
            sb.append(',');
            sb.append("sshUrl");
            sb.append('=');
            sb.append(((this.sshUrl == null)?"<null>":this.sshUrl));
            sb.append(',');
            sb.append("stargazersCount");
            sb.append('=');
            sb.append(((this.stargazersCount == null)?"<null>":this.stargazersCount));
            sb.append(',');
            sb.append("stargazersUrl");
            sb.append('=');
            sb.append(((this.stargazersUrl == null)?"<null>":this.stargazersUrl));
            sb.append(',');
            sb.append("statusesUrl");
            sb.append('=');
            sb.append(((this.statusesUrl == null)?"<null>":this.statusesUrl));
            sb.append(',');
            sb.append("subscribersUrl");
            sb.append('=');
            sb.append(((this.subscribersUrl == null)?"<null>":this.subscribersUrl));
            sb.append(',');
            sb.append("subscriptionUrl");
            sb.append('=');
            sb.append(((this.subscriptionUrl == null)?"<null>":this.subscriptionUrl));
            sb.append(',');
            sb.append("svnUrl");
            sb.append('=');
            sb.append(((this.svnUrl == null)?"<null>":this.svnUrl));
            sb.append(',');
            sb.append("tagsUrl");
            sb.append('=');
            sb.append(((this.tagsUrl == null)?"<null>":this.tagsUrl));
            sb.append(',');
            sb.append("teamsUrl");
            sb.append('=');
            sb.append(((this.teamsUrl == null)?"<null>":this.teamsUrl));
            sb.append(',');
            sb.append("treesUrl");
            sb.append('=');
            sb.append(((this.treesUrl == null)?"<null>":this.treesUrl));
            sb.append(',');
            sb.append("updatedAt");
            sb.append('=');
            sb.append(((this.updatedAt == null)?"<null>":this.updatedAt));
            sb.append(',');
            sb.append("url");
            sb.append('=');
            sb.append(((this.url == null)?"<null>":this.url));
            sb.append(',');
            sb.append("watchers");
            sb.append('=');
            sb.append(((this.watchers == null)?"<null>":this.watchers));
            sb.append(',');
            sb.append("watchersCount");
            sb.append('=');
            sb.append(((this.watchersCount == null)?"<null>":this.watchersCount));
            sb.append(',');
            sb.append("additionalProperties");
            sb.append('=');
            sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
            sb.append(',');
            if (sb.charAt((sb.length()- 1)) == ',') {
                sb.setCharAt((sb.length()- 1), ']');
            } else {
                sb.append(']');
            }
            return sb.toString();
        }

        @Override
        public int hashCode() {
            int result = 1;
            result = ((result* 31)+((this.sshUrl == null)? 0 :this.sshUrl.hashCode()));
            result = ((result* 31)+((this.archiveUrl == null)? 0 :this.archiveUrl.hashCode()));
            result = ((result* 31)+((this.language == null)? 0 :this.language.hashCode()));
            result = ((result* 31)+((this.languagesUrl == null)? 0 :this.languagesUrl.hashCode()));
            result = ((result* 31)+((this.assigneesUrl == null)? 0 :this.assigneesUrl.hashCode()));
            result = ((result* 31)+((this.commitsUrl == null)? 0 :this.commitsUrl.hashCode()));
            result = ((result* 31)+((this.openIssues == null)? 0 :this.openIssues.hashCode()));
            result = ((result* 31)+((this.cloneUrl == null)? 0 :this.cloneUrl.hashCode()));
            result = ((result* 31)+((this.forksCount == null)? 0 :this.forksCount.hashCode()));
            result = ((result* 31)+((this.subscribersUrl == null)? 0 :this.subscribersUrl.hashCode()));
            result = ((result* 31)+((this.createdAt == null)? 0 :this.createdAt.hashCode()));
            result = ((result* 31)+((this.forksUrl == null)? 0 :this.forksUrl.hashCode()));
            result = ((result* 31)+((this.watchersCount == null)? 0 :this.watchersCount.hashCode()));
            result = ((result* 31)+((this._private == null)? 0 :this._private.hashCode()));
            result = ((result* 31)+((this.issueCommentUrl == null)? 0 :this.issueCommentUrl.hashCode()));
            result = ((result* 31)+((this.statusesUrl == null)? 0 :this.statusesUrl.hashCode()));
            result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
            result = ((result* 31)+((this.collaboratorsUrl == null)? 0 :this.collaboratorsUrl.hashCode()));
            result = ((result* 31)+((this.updatedAt == null)? 0 :this.updatedAt.hashCode()));
            result = ((result* 31)+((this.forks == null)? 0 :this.forks.hashCode()));
            result = ((result* 31)+((this.allowForking == null)? 0 :this.allowForking.hashCode()));
            result = ((result* 31)+((this.labelsUrl == null)? 0 :this.labelsUrl.hashCode()));
            result = ((result* 31)+((this.defaultBranch == null)? 0 :this.defaultBranch.hashCode()));
            result = ((result* 31)+((this.keysUrl == null)? 0 :this.keysUrl.hashCode()));
            result = ((result* 31)+((this.downloadsUrl == null)? 0 :this.downloadsUrl.hashCode()));
            result = ((result* 31)+((this.contentsUrl == null)? 0 :this.contentsUrl.hashCode()));
            result = ((result* 31)+((this.pushedAt == null)? 0 :this.pushedAt.hashCode()));
            result = ((result* 31)+((this.tagsUrl == null)? 0 :this.tagsUrl.hashCode()));
            result = ((result* 31)+((this.license == null)? 0 :this.license.hashCode()));
            result = ((result* 31)+((this.commentsUrl == null)? 0 :this.commentsUrl.hashCode()));
            result = ((result* 31)+((this.size == null)? 0 :this.size.hashCode()));
            result = ((result* 31)+((this.treesUrl == null)? 0 :this.treesUrl.hashCode()));
            result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.mergesUrl == null)? 0 :this.mergesUrl.hashCode()));
            result = ((result* 31)+((this.nodeId == null)? 0 :this.nodeId.hashCode()));
            result = ((result* 31)+((this.teamsUrl == null)? 0 :this.teamsUrl.hashCode()));
            result = ((result* 31)+((this.blobsUrl == null)? 0 :this.blobsUrl.hashCode()));
            result = ((result* 31)+((this.hasPages == null)? 0 :this.hasPages.hashCode()));
            result = ((result* 31)+((this.issueEventsUrl == null)? 0 :this.issueEventsUrl.hashCode()));
            result = ((result* 31)+((this.milestonesUrl == null)? 0 :this.milestonesUrl.hashCode()));
            result = ((result* 31)+((this.issuesUrl == null)? 0 :this.issuesUrl.hashCode()));
            result = ((result* 31)+((this.releasesUrl == null)? 0 :this.releasesUrl.hashCode()));
            result = ((result* 31)+((this.description == null)? 0 :this.description.hashCode()));
            result = ((result* 31)+((this.watchers == null)? 0 :this.watchers.hashCode()));
            result = ((result* 31)+((this.branchesUrl == null)? 0 :this.branchesUrl.hashCode()));
            result = ((result* 31)+((this.contributorsUrl == null)? 0 :this.contributorsUrl.hashCode()));
            result = ((result* 31)+((this.gitRefsUrl == null)? 0 :this.gitRefsUrl.hashCode()));
            result = ((result* 31)+((this.hooksUrl == null)? 0 :this.hooksUrl.hashCode()));
            result = ((result* 31)+((this.openIssuesCount == null)? 0 :this.openIssuesCount.hashCode()));
            result = ((result* 31)+((this.archived == null)? 0 :this.archived.hashCode()));
            result = ((result* 31)+((this.stargazersCount == null)? 0 :this.stargazersCount.hashCode()));
            result = ((result* 31)+((this.disabled == null)? 0 :this.disabled.hashCode()));
            result = ((result* 31)+((this.hasIssues == null)? 0 :this.hasIssues.hashCode()));
            result = ((result* 31)+((this.owner == null)? 0 :this.owner.hashCode()));
            result = ((result* 31)+((this.hasWiki == null)? 0 :this.hasWiki.hashCode()));
            result = ((result* 31)+((this.compareUrl == null)? 0 :this.compareUrl.hashCode()));
            result = ((result* 31)+((this.gitCommitsUrl == null)? 0 :this.gitCommitsUrl.hashCode()));
            result = ((result* 31)+((this.htmlUrl == null)? 0 :this.htmlUrl.hashCode()));
            result = ((result* 31)+((this.stargazersUrl == null)? 0 :this.stargazersUrl.hashCode()));
            result = ((result* 31)+((this.fullName == null)? 0 :this.fullName.hashCode()));
            result = ((result* 31)+((this.svnUrl == null)? 0 :this.svnUrl.hashCode()));
            result = ((result* 31)+((this.mirrorUrl == null)? 0 :this.mirrorUrl.hashCode()));
            result = ((result* 31)+((this.pullsUrl == null)? 0 :this.pullsUrl.hashCode()));
            result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
            result = ((result* 31)+((this.hasDownloads == null)? 0 :this.hasDownloads.hashCode()));
            result = ((result* 31)+((this.fork == null)? 0 :this.fork.hashCode()));
            result = ((result* 31)+((this.hasProjects == null)? 0 :this.hasProjects.hashCode()));
            result = ((result* 31)+((this.deploymentsUrl == null)? 0 :this.deploymentsUrl.hashCode()));
            result = ((result* 31)+((this.eventsUrl == null)? 0 :this.eventsUrl.hashCode()));
            result = ((result* 31)+((this.gitTagsUrl == null)? 0 :this.gitTagsUrl.hashCode()));
            result = ((result* 31)+((this.notificationsUrl == null)? 0 :this.notificationsUrl.hashCode()));
            result = ((result* 31)+((this.gitUrl == null)? 0 :this.gitUrl.hashCode()));
            result = ((result* 31)+((this.homepage == null)? 0 :this.homepage.hashCode()));
            result = ((result* 31)+((this.subscriptionUrl == null)? 0 :this.subscriptionUrl.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof WorkflowRunGitHubEvent.Repository) == false) {
                return false;
            }
            WorkflowRunGitHubEvent.Repository rhs = ((WorkflowRunGitHubEvent.Repository) other);
            return ((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((this.sshUrl == rhs.sshUrl)||((this.sshUrl!= null)&&this.sshUrl.equals(rhs.sshUrl)))&&((this.archiveUrl == rhs.archiveUrl)||((this.archiveUrl!= null)&&this.archiveUrl.equals(rhs.archiveUrl))))&&((this.language == rhs.language)||((this.language!= null)&&this.language.equals(rhs.language))))&&((this.languagesUrl == rhs.languagesUrl)||((this.languagesUrl!= null)&&this.languagesUrl.equals(rhs.languagesUrl))))&&((this.assigneesUrl == rhs.assigneesUrl)||((this.assigneesUrl!= null)&&this.assigneesUrl.equals(rhs.assigneesUrl))))&&((this.commitsUrl == rhs.commitsUrl)||((this.commitsUrl!= null)&&this.commitsUrl.equals(rhs.commitsUrl))))&&((this.openIssues == rhs.openIssues)||((this.openIssues!= null)&&this.openIssues.equals(rhs.openIssues))))&&((this.cloneUrl == rhs.cloneUrl)||((this.cloneUrl!= null)&&this.cloneUrl.equals(rhs.cloneUrl))))&&((this.forksCount == rhs.forksCount)||((this.forksCount!= null)&&this.forksCount.equals(rhs.forksCount))))&&((this.subscribersUrl == rhs.subscribersUrl)||((this.subscribersUrl!= null)&&this.subscribersUrl.equals(rhs.subscribersUrl))))&&((this.createdAt == rhs.createdAt)||((this.createdAt!= null)&&this.createdAt.equals(rhs.createdAt))))&&((this.forksUrl == rhs.forksUrl)||((this.forksUrl!= null)&&this.forksUrl.equals(rhs.forksUrl))))&&((this.watchersCount == rhs.watchersCount)||((this.watchersCount!= null)&&this.watchersCount.equals(rhs.watchersCount))))&&((this._private == rhs._private)||((this._private!= null)&&this._private.equals(rhs._private))))&&((this.issueCommentUrl == rhs.issueCommentUrl)||((this.issueCommentUrl!= null)&&this.issueCommentUrl.equals(rhs.issueCommentUrl))))&&((this.statusesUrl == rhs.statusesUrl)||((this.statusesUrl!= null)&&this.statusesUrl.equals(rhs.statusesUrl))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.collaboratorsUrl == rhs.collaboratorsUrl)||((this.collaboratorsUrl!= null)&&this.collaboratorsUrl.equals(rhs.collaboratorsUrl))))&&((this.updatedAt == rhs.updatedAt)||((this.updatedAt!= null)&&this.updatedAt.equals(rhs.updatedAt))))&&((this.forks == rhs.forks)||((this.forks!= null)&&this.forks.equals(rhs.forks))))&&((this.allowForking == rhs.allowForking)||((this.allowForking!= null)&&this.allowForking.equals(rhs.allowForking))))&&((this.labelsUrl == rhs.labelsUrl)||((this.labelsUrl!= null)&&this.labelsUrl.equals(rhs.labelsUrl))))&&((this.defaultBranch == rhs.defaultBranch)||((this.defaultBranch!= null)&&this.defaultBranch.equals(rhs.defaultBranch))))&&((this.keysUrl == rhs.keysUrl)||((this.keysUrl!= null)&&this.keysUrl.equals(rhs.keysUrl))))&&((this.downloadsUrl == rhs.downloadsUrl)||((this.downloadsUrl!= null)&&this.downloadsUrl.equals(rhs.downloadsUrl))))&&((this.contentsUrl == rhs.contentsUrl)||((this.contentsUrl!= null)&&this.contentsUrl.equals(rhs.contentsUrl))))&&((this.pushedAt == rhs.pushedAt)||((this.pushedAt!= null)&&this.pushedAt.equals(rhs.pushedAt))))&&((this.tagsUrl == rhs.tagsUrl)||((this.tagsUrl!= null)&&this.tagsUrl.equals(rhs.tagsUrl))))&&((this.license == rhs.license)||((this.license!= null)&&this.license.equals(rhs.license))))&&((this.commentsUrl == rhs.commentsUrl)||((this.commentsUrl!= null)&&this.commentsUrl.equals(rhs.commentsUrl))))&&((this.size == rhs.size)||((this.size!= null)&&this.size.equals(rhs.size))))&&((this.treesUrl == rhs.treesUrl)||((this.treesUrl!= null)&&this.treesUrl.equals(rhs.treesUrl))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.mergesUrl == rhs.mergesUrl)||((this.mergesUrl!= null)&&this.mergesUrl.equals(rhs.mergesUrl))))&&((this.nodeId == rhs.nodeId)||((this.nodeId!= null)&&this.nodeId.equals(rhs.nodeId))))&&((this.teamsUrl == rhs.teamsUrl)||((this.teamsUrl!= null)&&this.teamsUrl.equals(rhs.teamsUrl))))&&((this.blobsUrl == rhs.blobsUrl)||((this.blobsUrl!= null)&&this.blobsUrl.equals(rhs.blobsUrl))))&&((this.hasPages == rhs.hasPages)||((this.hasPages!= null)&&this.hasPages.equals(rhs.hasPages))))&&((this.issueEventsUrl == rhs.issueEventsUrl)||((this.issueEventsUrl!= null)&&this.issueEventsUrl.equals(rhs.issueEventsUrl))))&&((this.milestonesUrl == rhs.milestonesUrl)||((this.milestonesUrl!= null)&&this.milestonesUrl.equals(rhs.milestonesUrl))))&&((this.issuesUrl == rhs.issuesUrl)||((this.issuesUrl!= null)&&this.issuesUrl.equals(rhs.issuesUrl))))&&((this.releasesUrl == rhs.releasesUrl)||((this.releasesUrl!= null)&&this.releasesUrl.equals(rhs.releasesUrl))))&&((this.description == rhs.description)||((this.description!= null)&&this.description.equals(rhs.description))))&&((this.watchers == rhs.watchers)||((this.watchers!= null)&&this.watchers.equals(rhs.watchers))))&&((this.branchesUrl == rhs.branchesUrl)||((this.branchesUrl!= null)&&this.branchesUrl.equals(rhs.branchesUrl))))&&((this.contributorsUrl == rhs.contributorsUrl)||((this.contributorsUrl!= null)&&this.contributorsUrl.equals(rhs.contributorsUrl))))&&((this.gitRefsUrl == rhs.gitRefsUrl)||((this.gitRefsUrl!= null)&&this.gitRefsUrl.equals(rhs.gitRefsUrl))))&&((this.hooksUrl == rhs.hooksUrl)||((this.hooksUrl!= null)&&this.hooksUrl.equals(rhs.hooksUrl))))&&((this.openIssuesCount == rhs.openIssuesCount)||((this.openIssuesCount!= null)&&this.openIssuesCount.equals(rhs.openIssuesCount))))&&((this.archived == rhs.archived)||((this.archived!= null)&&this.archived.equals(rhs.archived))))&&((this.stargazersCount == rhs.stargazersCount)||((this.stargazersCount!= null)&&this.stargazersCount.equals(rhs.stargazersCount))))&&((this.disabled == rhs.disabled)||((this.disabled!= null)&&this.disabled.equals(rhs.disabled))))&&((this.hasIssues == rhs.hasIssues)||((this.hasIssues!= null)&&this.hasIssues.equals(rhs.hasIssues))))&&((this.owner == rhs.owner)||((this.owner!= null)&&this.owner.equals(rhs.owner))))&&((this.hasWiki == rhs.hasWiki)||((this.hasWiki!= null)&&this.hasWiki.equals(rhs.hasWiki))))&&((this.compareUrl == rhs.compareUrl)||((this.compareUrl!= null)&&this.compareUrl.equals(rhs.compareUrl))))&&((this.gitCommitsUrl == rhs.gitCommitsUrl)||((this.gitCommitsUrl!= null)&&this.gitCommitsUrl.equals(rhs.gitCommitsUrl))))&&((this.htmlUrl == rhs.htmlUrl)||((this.htmlUrl!= null)&&this.htmlUrl.equals(rhs.htmlUrl))))&&((this.stargazersUrl == rhs.stargazersUrl)||((this.stargazersUrl!= null)&&this.stargazersUrl.equals(rhs.stargazersUrl))))&&((this.fullName == rhs.fullName)||((this.fullName!= null)&&this.fullName.equals(rhs.fullName))))&&((this.svnUrl == rhs.svnUrl)||((this.svnUrl!= null)&&this.svnUrl.equals(rhs.svnUrl))))&&((this.mirrorUrl == rhs.mirrorUrl)||((this.mirrorUrl!= null)&&this.mirrorUrl.equals(rhs.mirrorUrl))))&&((this.pullsUrl == rhs.pullsUrl)||((this.pullsUrl!= null)&&this.pullsUrl.equals(rhs.pullsUrl))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))))&&((this.hasDownloads == rhs.hasDownloads)||((this.hasDownloads!= null)&&this.hasDownloads.equals(rhs.hasDownloads))))&&((this.fork == rhs.fork)||((this.fork!= null)&&this.fork.equals(rhs.fork))))&&((this.hasProjects == rhs.hasProjects)||((this.hasProjects!= null)&&this.hasProjects.equals(rhs.hasProjects))))&&((this.deploymentsUrl == rhs.deploymentsUrl)||((this.deploymentsUrl!= null)&&this.deploymentsUrl.equals(rhs.deploymentsUrl))))&&((this.eventsUrl == rhs.eventsUrl)||((this.eventsUrl!= null)&&this.eventsUrl.equals(rhs.eventsUrl))))&&((this.gitTagsUrl == rhs.gitTagsUrl)||((this.gitTagsUrl!= null)&&this.gitTagsUrl.equals(rhs.gitTagsUrl))))&&((this.notificationsUrl == rhs.notificationsUrl)||((this.notificationsUrl!= null)&&this.notificationsUrl.equals(rhs.notificationsUrl))))&&((this.gitUrl == rhs.gitUrl)||((this.gitUrl!= null)&&this.gitUrl.equals(rhs.gitUrl))))&&((this.homepage == rhs.homepage)||((this.homepage!= null)&&this.homepage.equals(rhs.homepage))))&&((this.subscriptionUrl == rhs.subscriptionUrl)||((this.subscriptionUrl!= null)&&this.subscriptionUrl.equals(rhs.subscriptionUrl))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "archive_url",
        "assignees_url",
        "blobs_url",
        "branches_url",
        "collaborators_url",
        "comments_url",
        "commits_url",
        "compare_url",
        "contents_url",
        "contributors_url",
        "deployments_url",
        "description",
        "downloads_url",
        "events_url",
        "fork",
        "forks_url",
        "full_name",
        "git_commits_url",
        "git_refs_url",
        "git_tags_url",
        "hooks_url",
        "html_url",
        "id",
        "issue_comment_url",
        "issue_events_url",
        "issues_url",
        "keys_url",
        "labels_url",
        "languages_url",
        "merges_url",
        "milestones_url",
        "name",
        "node_id",
        "notifications_url",
        "owner",
        "private",
        "pulls_url",
        "releases_url",
        "stargazers_url",
        "statuses_url",
        "subscribers_url",
        "subscription_url",
        "tags_url",
        "teams_url",
        "trees_url",
        "url"
    })
    @Generated("jsonschema2pojo")
    public static class Repository2 {

        @JsonProperty("archive_url")
        private String archiveUrl;
        @JsonProperty("assignees_url")
        private String assigneesUrl;
        @JsonProperty("blobs_url")
        private String blobsUrl;
        @JsonProperty("branches_url")
        private String branchesUrl;
        @JsonProperty("collaborators_url")
        private String collaboratorsUrl;
        @JsonProperty("comments_url")
        private String commentsUrl;
        @JsonProperty("commits_url")
        private String commitsUrl;
        @JsonProperty("compare_url")
        private String compareUrl;
        @JsonProperty("contents_url")
        private String contentsUrl;
        @JsonProperty("contributors_url")
        private String contributorsUrl;
        @JsonProperty("deployments_url")
        private String deploymentsUrl;
        @JsonProperty("description")
        private Object description;
        @JsonProperty("downloads_url")
        private String downloadsUrl;
        @JsonProperty("events_url")
        private String eventsUrl;
        @JsonProperty("fork")
        private Boolean fork;
        @JsonProperty("forks_url")
        private String forksUrl;
        @JsonProperty("full_name")
        private String fullName;
        @JsonProperty("git_commits_url")
        private String gitCommitsUrl;
        @JsonProperty("git_refs_url")
        private String gitRefsUrl;
        @JsonProperty("git_tags_url")
        private String gitTagsUrl;
        @JsonProperty("hooks_url")
        private String hooksUrl;
        @JsonProperty("html_url")
        private String htmlUrl;
        @JsonProperty("id")
        private Long id;
        @JsonProperty("issue_comment_url")
        private String issueCommentUrl;
        @JsonProperty("issue_events_url")
        private String issueEventsUrl;
        @JsonProperty("issues_url")
        private String issuesUrl;
        @JsonProperty("keys_url")
        private String keysUrl;
        @JsonProperty("labels_url")
        private String labelsUrl;
        @JsonProperty("languages_url")
        private String languagesUrl;
        @JsonProperty("merges_url")
        private String mergesUrl;
        @JsonProperty("milestones_url")
        private String milestonesUrl;
        @JsonProperty("name")
        private String name;
        @JsonProperty("node_id")
        private String nodeId;
        @JsonProperty("notifications_url")
        private String notificationsUrl;
        @JsonProperty("owner")
        private WorkflowRunGitHubEvent.Owner3 owner;
        @JsonProperty("private")
        private Boolean _private;
        @JsonProperty("pulls_url")
        private String pullsUrl;
        @JsonProperty("releases_url")
        private String releasesUrl;
        @JsonProperty("stargazers_url")
        private String stargazersUrl;
        @JsonProperty("statuses_url")
        private String statusesUrl;
        @JsonProperty("subscribers_url")
        private String subscribersUrl;
        @JsonProperty("subscription_url")
        private String subscriptionUrl;
        @JsonProperty("tags_url")
        private String tagsUrl;
        @JsonProperty("teams_url")
        private String teamsUrl;
        @JsonProperty("trees_url")
        private String treesUrl;
        @JsonProperty("url")
        private String url;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Repository2() {
        }

        /**
         * 
         * @param teamsUrl
         * @param blobsUrl
         * @param issueEventsUrl
         * @param milestonesUrl
         * @param issuesUrl
         * @param releasesUrl
         * @param archiveUrl
         * @param description
         * @param branchesUrl
         * @param languagesUrl
         * @param assigneesUrl
         * @param contributorsUrl
         * @param commitsUrl
         * @param gitRefsUrl
         * @param hooksUrl
         * @param subscribersUrl
         * @param forksUrl
         * @param _private
         * @param issueCommentUrl
         * @param statusesUrl
         * @param id
         * @param collaboratorsUrl
         * @param owner
         * @param compareUrl
         * @param gitCommitsUrl
         * @param labelsUrl
         * @param htmlUrl
         * @param stargazersUrl
         * @param fullName
         * @param keysUrl
         * @param downloadsUrl
         * @param contentsUrl
         * @param pullsUrl
         * @param url
         * @param tagsUrl
         * @param fork
         * @param commentsUrl
         * @param treesUrl
         * @param name
         * @param deploymentsUrl
         * @param eventsUrl
         * @param gitTagsUrl
         * @param mergesUrl
         * @param notificationsUrl
         * @param nodeId
         * @param subscriptionUrl
         */
        public Repository2(String archiveUrl, String assigneesUrl, String blobsUrl, String branchesUrl, String collaboratorsUrl, String commentsUrl, String commitsUrl, String compareUrl, String contentsUrl, String contributorsUrl, String deploymentsUrl, Object description, String downloadsUrl, String eventsUrl, Boolean fork, String forksUrl, String fullName, String gitCommitsUrl, String gitRefsUrl, String gitTagsUrl, String hooksUrl, String htmlUrl, Long id, String issueCommentUrl, String issueEventsUrl, String issuesUrl, String keysUrl, String labelsUrl, String languagesUrl, String mergesUrl, String milestonesUrl, String name, String nodeId, String notificationsUrl, WorkflowRunGitHubEvent.Owner3 owner, Boolean _private, String pullsUrl, String releasesUrl, String stargazersUrl, String statusesUrl, String subscribersUrl, String subscriptionUrl, String tagsUrl, String teamsUrl, String treesUrl, String url) {
            super();
            this.archiveUrl = archiveUrl;
            this.assigneesUrl = assigneesUrl;
            this.blobsUrl = blobsUrl;
            this.branchesUrl = branchesUrl;
            this.collaboratorsUrl = collaboratorsUrl;
            this.commentsUrl = commentsUrl;
            this.commitsUrl = commitsUrl;
            this.compareUrl = compareUrl;
            this.contentsUrl = contentsUrl;
            this.contributorsUrl = contributorsUrl;
            this.deploymentsUrl = deploymentsUrl;
            this.description = description;
            this.downloadsUrl = downloadsUrl;
            this.eventsUrl = eventsUrl;
            this.fork = fork;
            this.forksUrl = forksUrl;
            this.fullName = fullName;
            this.gitCommitsUrl = gitCommitsUrl;
            this.gitRefsUrl = gitRefsUrl;
            this.gitTagsUrl = gitTagsUrl;
            this.hooksUrl = hooksUrl;
            this.htmlUrl = htmlUrl;
            this.id = id;
            this.issueCommentUrl = issueCommentUrl;
            this.issueEventsUrl = issueEventsUrl;
            this.issuesUrl = issuesUrl;
            this.keysUrl = keysUrl;
            this.labelsUrl = labelsUrl;
            this.languagesUrl = languagesUrl;
            this.mergesUrl = mergesUrl;
            this.milestonesUrl = milestonesUrl;
            this.name = name;
            this.nodeId = nodeId;
            this.notificationsUrl = notificationsUrl;
            this.owner = owner;
            this._private = _private;
            this.pullsUrl = pullsUrl;
            this.releasesUrl = releasesUrl;
            this.stargazersUrl = stargazersUrl;
            this.statusesUrl = statusesUrl;
            this.subscribersUrl = subscribersUrl;
            this.subscriptionUrl = subscriptionUrl;
            this.tagsUrl = tagsUrl;
            this.teamsUrl = teamsUrl;
            this.treesUrl = treesUrl;
            this.url = url;
        }

        @JsonProperty("archive_url")
        public String getArchiveUrl() {
            return archiveUrl;
        }

        @JsonProperty("archive_url")
        public void setArchiveUrl(String archiveUrl) {
            this.archiveUrl = archiveUrl;
        }

        @JsonProperty("assignees_url")
        public String getAssigneesUrl() {
            return assigneesUrl;
        }

        @JsonProperty("assignees_url")
        public void setAssigneesUrl(String assigneesUrl) {
            this.assigneesUrl = assigneesUrl;
        }

        @JsonProperty("blobs_url")
        public String getBlobsUrl() {
            return blobsUrl;
        }

        @JsonProperty("blobs_url")
        public void setBlobsUrl(String blobsUrl) {
            this.blobsUrl = blobsUrl;
        }

        @JsonProperty("branches_url")
        public String getBranchesUrl() {
            return branchesUrl;
        }

        @JsonProperty("branches_url")
        public void setBranchesUrl(String branchesUrl) {
            this.branchesUrl = branchesUrl;
        }

        @JsonProperty("collaborators_url")
        public String getCollaboratorsUrl() {
            return collaboratorsUrl;
        }

        @JsonProperty("collaborators_url")
        public void setCollaboratorsUrl(String collaboratorsUrl) {
            this.collaboratorsUrl = collaboratorsUrl;
        }

        @JsonProperty("comments_url")
        public String getCommentsUrl() {
            return commentsUrl;
        }

        @JsonProperty("comments_url")
        public void setCommentsUrl(String commentsUrl) {
            this.commentsUrl = commentsUrl;
        }

        @JsonProperty("commits_url")
        public String getCommitsUrl() {
            return commitsUrl;
        }

        @JsonProperty("commits_url")
        public void setCommitsUrl(String commitsUrl) {
            this.commitsUrl = commitsUrl;
        }

        @JsonProperty("compare_url")
        public String getCompareUrl() {
            return compareUrl;
        }

        @JsonProperty("compare_url")
        public void setCompareUrl(String compareUrl) {
            this.compareUrl = compareUrl;
        }

        @JsonProperty("contents_url")
        public String getContentsUrl() {
            return contentsUrl;
        }

        @JsonProperty("contents_url")
        public void setContentsUrl(String contentsUrl) {
            this.contentsUrl = contentsUrl;
        }

        @JsonProperty("contributors_url")
        public String getContributorsUrl() {
            return contributorsUrl;
        }

        @JsonProperty("contributors_url")
        public void setContributorsUrl(String contributorsUrl) {
            this.contributorsUrl = contributorsUrl;
        }

        @JsonProperty("deployments_url")
        public String getDeploymentsUrl() {
            return deploymentsUrl;
        }

        @JsonProperty("deployments_url")
        public void setDeploymentsUrl(String deploymentsUrl) {
            this.deploymentsUrl = deploymentsUrl;
        }

        @JsonProperty("description")
        public Object getDescription() {
            return description;
        }

        @JsonProperty("description")
        public void setDescription(Object description) {
            this.description = description;
        }

        @JsonProperty("downloads_url")
        public String getDownloadsUrl() {
            return downloadsUrl;
        }

        @JsonProperty("downloads_url")
        public void setDownloadsUrl(String downloadsUrl) {
            this.downloadsUrl = downloadsUrl;
        }

        @JsonProperty("events_url")
        public String getEventsUrl() {
            return eventsUrl;
        }

        @JsonProperty("events_url")
        public void setEventsUrl(String eventsUrl) {
            this.eventsUrl = eventsUrl;
        }

        @JsonProperty("fork")
        public Boolean getFork() {
            return fork;
        }

        @JsonProperty("fork")
        public void setFork(Boolean fork) {
            this.fork = fork;
        }

        @JsonProperty("forks_url")
        public String getForksUrl() {
            return forksUrl;
        }

        @JsonProperty("forks_url")
        public void setForksUrl(String forksUrl) {
            this.forksUrl = forksUrl;
        }

        @JsonProperty("full_name")
        public String getFullName() {
            return fullName;
        }

        @JsonProperty("full_name")
        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        @JsonProperty("git_commits_url")
        public String getGitCommitsUrl() {
            return gitCommitsUrl;
        }

        @JsonProperty("git_commits_url")
        public void setGitCommitsUrl(String gitCommitsUrl) {
            this.gitCommitsUrl = gitCommitsUrl;
        }

        @JsonProperty("git_refs_url")
        public String getGitRefsUrl() {
            return gitRefsUrl;
        }

        @JsonProperty("git_refs_url")
        public void setGitRefsUrl(String gitRefsUrl) {
            this.gitRefsUrl = gitRefsUrl;
        }

        @JsonProperty("git_tags_url")
        public String getGitTagsUrl() {
            return gitTagsUrl;
        }

        @JsonProperty("git_tags_url")
        public void setGitTagsUrl(String gitTagsUrl) {
            this.gitTagsUrl = gitTagsUrl;
        }

        @JsonProperty("hooks_url")
        public String getHooksUrl() {
            return hooksUrl;
        }

        @JsonProperty("hooks_url")
        public void setHooksUrl(String hooksUrl) {
            this.hooksUrl = hooksUrl;
        }

        @JsonProperty("html_url")
        public String getHtmlUrl() {
            return htmlUrl;
        }

        @JsonProperty("html_url")
        public void setHtmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
        }

        @JsonProperty("id")
        public Long getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(Long id) {
            this.id = id;
        }

        @JsonProperty("issue_comment_url")
        public String getIssueCommentUrl() {
            return issueCommentUrl;
        }

        @JsonProperty("issue_comment_url")
        public void setIssueCommentUrl(String issueCommentUrl) {
            this.issueCommentUrl = issueCommentUrl;
        }

        @JsonProperty("issue_events_url")
        public String getIssueEventsUrl() {
            return issueEventsUrl;
        }

        @JsonProperty("issue_events_url")
        public void setIssueEventsUrl(String issueEventsUrl) {
            this.issueEventsUrl = issueEventsUrl;
        }

        @JsonProperty("issues_url")
        public String getIssuesUrl() {
            return issuesUrl;
        }

        @JsonProperty("issues_url")
        public void setIssuesUrl(String issuesUrl) {
            this.issuesUrl = issuesUrl;
        }

        @JsonProperty("keys_url")
        public String getKeysUrl() {
            return keysUrl;
        }

        @JsonProperty("keys_url")
        public void setKeysUrl(String keysUrl) {
            this.keysUrl = keysUrl;
        }

        @JsonProperty("labels_url")
        public String getLabelsUrl() {
            return labelsUrl;
        }

        @JsonProperty("labels_url")
        public void setLabelsUrl(String labelsUrl) {
            this.labelsUrl = labelsUrl;
        }

        @JsonProperty("languages_url")
        public String getLanguagesUrl() {
            return languagesUrl;
        }

        @JsonProperty("languages_url")
        public void setLanguagesUrl(String languagesUrl) {
            this.languagesUrl = languagesUrl;
        }

        @JsonProperty("merges_url")
        public String getMergesUrl() {
            return mergesUrl;
        }

        @JsonProperty("merges_url")
        public void setMergesUrl(String mergesUrl) {
            this.mergesUrl = mergesUrl;
        }

        @JsonProperty("milestones_url")
        public String getMilestonesUrl() {
            return milestonesUrl;
        }

        @JsonProperty("milestones_url")
        public void setMilestonesUrl(String milestonesUrl) {
            this.milestonesUrl = milestonesUrl;
        }

        @JsonProperty("name")
        public String getName() {
            return name;
        }

        @JsonProperty("name")
        public void setName(String name) {
            this.name = name;
        }

        @JsonProperty("node_id")
        public String getNodeId() {
            return nodeId;
        }

        @JsonProperty("node_id")
        public void setNodeId(String nodeId) {
            this.nodeId = nodeId;
        }

        @JsonProperty("notifications_url")
        public String getNotificationsUrl() {
            return notificationsUrl;
        }

        @JsonProperty("notifications_url")
        public void setNotificationsUrl(String notificationsUrl) {
            this.notificationsUrl = notificationsUrl;
        }

        @JsonProperty("owner")
        public WorkflowRunGitHubEvent.Owner3 getOwner() {
            return owner;
        }

        @JsonProperty("owner")
        public void setOwner(WorkflowRunGitHubEvent.Owner3 owner) {
            this.owner = owner;
        }

        @JsonProperty("private")
        public Boolean getPrivate() {
            return _private;
        }

        @JsonProperty("private")
        public void setPrivate(Boolean _private) {
            this._private = _private;
        }

        @JsonProperty("pulls_url")
        public String getPullsUrl() {
            return pullsUrl;
        }

        @JsonProperty("pulls_url")
        public void setPullsUrl(String pullsUrl) {
            this.pullsUrl = pullsUrl;
        }

        @JsonProperty("releases_url")
        public String getReleasesUrl() {
            return releasesUrl;
        }

        @JsonProperty("releases_url")
        public void setReleasesUrl(String releasesUrl) {
            this.releasesUrl = releasesUrl;
        }

        @JsonProperty("stargazers_url")
        public String getStargazersUrl() {
            return stargazersUrl;
        }

        @JsonProperty("stargazers_url")
        public void setStargazersUrl(String stargazersUrl) {
            this.stargazersUrl = stargazersUrl;
        }

        @JsonProperty("statuses_url")
        public String getStatusesUrl() {
            return statusesUrl;
        }

        @JsonProperty("statuses_url")
        public void setStatusesUrl(String statusesUrl) {
            this.statusesUrl = statusesUrl;
        }

        @JsonProperty("subscribers_url")
        public String getSubscribersUrl() {
            return subscribersUrl;
        }

        @JsonProperty("subscribers_url")
        public void setSubscribersUrl(String subscribersUrl) {
            this.subscribersUrl = subscribersUrl;
        }

        @JsonProperty("subscription_url")
        public String getSubscriptionUrl() {
            return subscriptionUrl;
        }

        @JsonProperty("subscription_url")
        public void setSubscriptionUrl(String subscriptionUrl) {
            this.subscriptionUrl = subscriptionUrl;
        }

        @JsonProperty("tags_url")
        public String getTagsUrl() {
            return tagsUrl;
        }

        @JsonProperty("tags_url")
        public void setTagsUrl(String tagsUrl) {
            this.tagsUrl = tagsUrl;
        }

        @JsonProperty("teams_url")
        public String getTeamsUrl() {
            return teamsUrl;
        }

        @JsonProperty("teams_url")
        public void setTeamsUrl(String teamsUrl) {
            this.teamsUrl = teamsUrl;
        }

        @JsonProperty("trees_url")
        public String getTreesUrl() {
            return treesUrl;
        }

        @JsonProperty("trees_url")
        public void setTreesUrl(String treesUrl) {
            this.treesUrl = treesUrl;
        }

        @JsonProperty("url")
        public String getUrl() {
            return url;
        }

        @JsonProperty("url")
        public void setUrl(String url) {
            this.url = url;
        }

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(WorkflowRunGitHubEvent.Repository2 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("archiveUrl");
            sb.append('=');
            sb.append(((this.archiveUrl == null)?"<null>":this.archiveUrl));
            sb.append(',');
            sb.append("assigneesUrl");
            sb.append('=');
            sb.append(((this.assigneesUrl == null)?"<null>":this.assigneesUrl));
            sb.append(',');
            sb.append("blobsUrl");
            sb.append('=');
            sb.append(((this.blobsUrl == null)?"<null>":this.blobsUrl));
            sb.append(',');
            sb.append("branchesUrl");
            sb.append('=');
            sb.append(((this.branchesUrl == null)?"<null>":this.branchesUrl));
            sb.append(',');
            sb.append("collaboratorsUrl");
            sb.append('=');
            sb.append(((this.collaboratorsUrl == null)?"<null>":this.collaboratorsUrl));
            sb.append(',');
            sb.append("commentsUrl");
            sb.append('=');
            sb.append(((this.commentsUrl == null)?"<null>":this.commentsUrl));
            sb.append(',');
            sb.append("commitsUrl");
            sb.append('=');
            sb.append(((this.commitsUrl == null)?"<null>":this.commitsUrl));
            sb.append(',');
            sb.append("compareUrl");
            sb.append('=');
            sb.append(((this.compareUrl == null)?"<null>":this.compareUrl));
            sb.append(',');
            sb.append("contentsUrl");
            sb.append('=');
            sb.append(((this.contentsUrl == null)?"<null>":this.contentsUrl));
            sb.append(',');
            sb.append("contributorsUrl");
            sb.append('=');
            sb.append(((this.contributorsUrl == null)?"<null>":this.contributorsUrl));
            sb.append(',');
            sb.append("deploymentsUrl");
            sb.append('=');
            sb.append(((this.deploymentsUrl == null)?"<null>":this.deploymentsUrl));
            sb.append(',');
            sb.append("description");
            sb.append('=');
            sb.append(((this.description == null)?"<null>":this.description));
            sb.append(',');
            sb.append("downloadsUrl");
            sb.append('=');
            sb.append(((this.downloadsUrl == null)?"<null>":this.downloadsUrl));
            sb.append(',');
            sb.append("eventsUrl");
            sb.append('=');
            sb.append(((this.eventsUrl == null)?"<null>":this.eventsUrl));
            sb.append(',');
            sb.append("fork");
            sb.append('=');
            sb.append(((this.fork == null)?"<null>":this.fork));
            sb.append(',');
            sb.append("forksUrl");
            sb.append('=');
            sb.append(((this.forksUrl == null)?"<null>":this.forksUrl));
            sb.append(',');
            sb.append("fullName");
            sb.append('=');
            sb.append(((this.fullName == null)?"<null>":this.fullName));
            sb.append(',');
            sb.append("gitCommitsUrl");
            sb.append('=');
            sb.append(((this.gitCommitsUrl == null)?"<null>":this.gitCommitsUrl));
            sb.append(',');
            sb.append("gitRefsUrl");
            sb.append('=');
            sb.append(((this.gitRefsUrl == null)?"<null>":this.gitRefsUrl));
            sb.append(',');
            sb.append("gitTagsUrl");
            sb.append('=');
            sb.append(((this.gitTagsUrl == null)?"<null>":this.gitTagsUrl));
            sb.append(',');
            sb.append("hooksUrl");
            sb.append('=');
            sb.append(((this.hooksUrl == null)?"<null>":this.hooksUrl));
            sb.append(',');
            sb.append("htmlUrl");
            sb.append('=');
            sb.append(((this.htmlUrl == null)?"<null>":this.htmlUrl));
            sb.append(',');
            sb.append("id");
            sb.append('=');
            sb.append(((this.id == null)?"<null>":this.id));
            sb.append(',');
            sb.append("issueCommentUrl");
            sb.append('=');
            sb.append(((this.issueCommentUrl == null)?"<null>":this.issueCommentUrl));
            sb.append(',');
            sb.append("issueEventsUrl");
            sb.append('=');
            sb.append(((this.issueEventsUrl == null)?"<null>":this.issueEventsUrl));
            sb.append(',');
            sb.append("issuesUrl");
            sb.append('=');
            sb.append(((this.issuesUrl == null)?"<null>":this.issuesUrl));
            sb.append(',');
            sb.append("keysUrl");
            sb.append('=');
            sb.append(((this.keysUrl == null)?"<null>":this.keysUrl));
            sb.append(',');
            sb.append("labelsUrl");
            sb.append('=');
            sb.append(((this.labelsUrl == null)?"<null>":this.labelsUrl));
            sb.append(',');
            sb.append("languagesUrl");
            sb.append('=');
            sb.append(((this.languagesUrl == null)?"<null>":this.languagesUrl));
            sb.append(',');
            sb.append("mergesUrl");
            sb.append('=');
            sb.append(((this.mergesUrl == null)?"<null>":this.mergesUrl));
            sb.append(',');
            sb.append("milestonesUrl");
            sb.append('=');
            sb.append(((this.milestonesUrl == null)?"<null>":this.milestonesUrl));
            sb.append(',');
            sb.append("name");
            sb.append('=');
            sb.append(((this.name == null)?"<null>":this.name));
            sb.append(',');
            sb.append("nodeId");
            sb.append('=');
            sb.append(((this.nodeId == null)?"<null>":this.nodeId));
            sb.append(',');
            sb.append("notificationsUrl");
            sb.append('=');
            sb.append(((this.notificationsUrl == null)?"<null>":this.notificationsUrl));
            sb.append(',');
            sb.append("owner");
            sb.append('=');
            sb.append(((this.owner == null)?"<null>":this.owner));
            sb.append(',');
            sb.append("_private");
            sb.append('=');
            sb.append(((this._private == null)?"<null>":this._private));
            sb.append(',');
            sb.append("pullsUrl");
            sb.append('=');
            sb.append(((this.pullsUrl == null)?"<null>":this.pullsUrl));
            sb.append(',');
            sb.append("releasesUrl");
            sb.append('=');
            sb.append(((this.releasesUrl == null)?"<null>":this.releasesUrl));
            sb.append(',');
            sb.append("stargazersUrl");
            sb.append('=');
            sb.append(((this.stargazersUrl == null)?"<null>":this.stargazersUrl));
            sb.append(',');
            sb.append("statusesUrl");
            sb.append('=');
            sb.append(((this.statusesUrl == null)?"<null>":this.statusesUrl));
            sb.append(',');
            sb.append("subscribersUrl");
            sb.append('=');
            sb.append(((this.subscribersUrl == null)?"<null>":this.subscribersUrl));
            sb.append(',');
            sb.append("subscriptionUrl");
            sb.append('=');
            sb.append(((this.subscriptionUrl == null)?"<null>":this.subscriptionUrl));
            sb.append(',');
            sb.append("tagsUrl");
            sb.append('=');
            sb.append(((this.tagsUrl == null)?"<null>":this.tagsUrl));
            sb.append(',');
            sb.append("teamsUrl");
            sb.append('=');
            sb.append(((this.teamsUrl == null)?"<null>":this.teamsUrl));
            sb.append(',');
            sb.append("treesUrl");
            sb.append('=');
            sb.append(((this.treesUrl == null)?"<null>":this.treesUrl));
            sb.append(',');
            sb.append("url");
            sb.append('=');
            sb.append(((this.url == null)?"<null>":this.url));
            sb.append(',');
            sb.append("additionalProperties");
            sb.append('=');
            sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
            sb.append(',');
            if (sb.charAt((sb.length()- 1)) == ',') {
                sb.setCharAt((sb.length()- 1), ']');
            } else {
                sb.append(']');
            }
            return sb.toString();
        }

        @Override
        public int hashCode() {
            int result = 1;
            result = ((result* 31)+((this.teamsUrl == null)? 0 :this.teamsUrl.hashCode()));
            result = ((result* 31)+((this.blobsUrl == null)? 0 :this.blobsUrl.hashCode()));
            result = ((result* 31)+((this.issueEventsUrl == null)? 0 :this.issueEventsUrl.hashCode()));
            result = ((result* 31)+((this.milestonesUrl == null)? 0 :this.milestonesUrl.hashCode()));
            result = ((result* 31)+((this.issuesUrl == null)? 0 :this.issuesUrl.hashCode()));
            result = ((result* 31)+((this.releasesUrl == null)? 0 :this.releasesUrl.hashCode()));
            result = ((result* 31)+((this.archiveUrl == null)? 0 :this.archiveUrl.hashCode()));
            result = ((result* 31)+((this.description == null)? 0 :this.description.hashCode()));
            result = ((result* 31)+((this.branchesUrl == null)? 0 :this.branchesUrl.hashCode()));
            result = ((result* 31)+((this.languagesUrl == null)? 0 :this.languagesUrl.hashCode()));
            result = ((result* 31)+((this.assigneesUrl == null)? 0 :this.assigneesUrl.hashCode()));
            result = ((result* 31)+((this.contributorsUrl == null)? 0 :this.contributorsUrl.hashCode()));
            result = ((result* 31)+((this.commitsUrl == null)? 0 :this.commitsUrl.hashCode()));
            result = ((result* 31)+((this.gitRefsUrl == null)? 0 :this.gitRefsUrl.hashCode()));
            result = ((result* 31)+((this.hooksUrl == null)? 0 :this.hooksUrl.hashCode()));
            result = ((result* 31)+((this.subscribersUrl == null)? 0 :this.subscribersUrl.hashCode()));
            result = ((result* 31)+((this.forksUrl == null)? 0 :this.forksUrl.hashCode()));
            result = ((result* 31)+((this._private == null)? 0 :this._private.hashCode()));
            result = ((result* 31)+((this.issueCommentUrl == null)? 0 :this.issueCommentUrl.hashCode()));
            result = ((result* 31)+((this.statusesUrl == null)? 0 :this.statusesUrl.hashCode()));
            result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
            result = ((result* 31)+((this.collaboratorsUrl == null)? 0 :this.collaboratorsUrl.hashCode()));
            result = ((result* 31)+((this.owner == null)? 0 :this.owner.hashCode()));
            result = ((result* 31)+((this.compareUrl == null)? 0 :this.compareUrl.hashCode()));
            result = ((result* 31)+((this.gitCommitsUrl == null)? 0 :this.gitCommitsUrl.hashCode()));
            result = ((result* 31)+((this.labelsUrl == null)? 0 :this.labelsUrl.hashCode()));
            result = ((result* 31)+((this.htmlUrl == null)? 0 :this.htmlUrl.hashCode()));
            result = ((result* 31)+((this.stargazersUrl == null)? 0 :this.stargazersUrl.hashCode()));
            result = ((result* 31)+((this.fullName == null)? 0 :this.fullName.hashCode()));
            result = ((result* 31)+((this.keysUrl == null)? 0 :this.keysUrl.hashCode()));
            result = ((result* 31)+((this.downloadsUrl == null)? 0 :this.downloadsUrl.hashCode()));
            result = ((result* 31)+((this.contentsUrl == null)? 0 :this.contentsUrl.hashCode()));
            result = ((result* 31)+((this.pullsUrl == null)? 0 :this.pullsUrl.hashCode()));
            result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
            result = ((result* 31)+((this.tagsUrl == null)? 0 :this.tagsUrl.hashCode()));
            result = ((result* 31)+((this.fork == null)? 0 :this.fork.hashCode()));
            result = ((result* 31)+((this.commentsUrl == null)? 0 :this.commentsUrl.hashCode()));
            result = ((result* 31)+((this.treesUrl == null)? 0 :this.treesUrl.hashCode()));
            result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
            result = ((result* 31)+((this.deploymentsUrl == null)? 0 :this.deploymentsUrl.hashCode()));
            result = ((result* 31)+((this.eventsUrl == null)? 0 :this.eventsUrl.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.gitTagsUrl == null)? 0 :this.gitTagsUrl.hashCode()));
            result = ((result* 31)+((this.mergesUrl == null)? 0 :this.mergesUrl.hashCode()));
            result = ((result* 31)+((this.notificationsUrl == null)? 0 :this.notificationsUrl.hashCode()));
            result = ((result* 31)+((this.nodeId == null)? 0 :this.nodeId.hashCode()));
            result = ((result* 31)+((this.subscriptionUrl == null)? 0 :this.subscriptionUrl.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof WorkflowRunGitHubEvent.Repository2) == false) {
                return false;
            }
            WorkflowRunGitHubEvent.Repository2 rhs = ((WorkflowRunGitHubEvent.Repository2) other);
            return ((((((((((((((((((((((((((((((((((((((((((((((((this.teamsUrl == rhs.teamsUrl)||((this.teamsUrl!= null)&&this.teamsUrl.equals(rhs.teamsUrl)))&&((this.blobsUrl == rhs.blobsUrl)||((this.blobsUrl!= null)&&this.blobsUrl.equals(rhs.blobsUrl))))&&((this.issueEventsUrl == rhs.issueEventsUrl)||((this.issueEventsUrl!= null)&&this.issueEventsUrl.equals(rhs.issueEventsUrl))))&&((this.milestonesUrl == rhs.milestonesUrl)||((this.milestonesUrl!= null)&&this.milestonesUrl.equals(rhs.milestonesUrl))))&&((this.issuesUrl == rhs.issuesUrl)||((this.issuesUrl!= null)&&this.issuesUrl.equals(rhs.issuesUrl))))&&((this.releasesUrl == rhs.releasesUrl)||((this.releasesUrl!= null)&&this.releasesUrl.equals(rhs.releasesUrl))))&&((this.archiveUrl == rhs.archiveUrl)||((this.archiveUrl!= null)&&this.archiveUrl.equals(rhs.archiveUrl))))&&((this.description == rhs.description)||((this.description!= null)&&this.description.equals(rhs.description))))&&((this.branchesUrl == rhs.branchesUrl)||((this.branchesUrl!= null)&&this.branchesUrl.equals(rhs.branchesUrl))))&&((this.languagesUrl == rhs.languagesUrl)||((this.languagesUrl!= null)&&this.languagesUrl.equals(rhs.languagesUrl))))&&((this.assigneesUrl == rhs.assigneesUrl)||((this.assigneesUrl!= null)&&this.assigneesUrl.equals(rhs.assigneesUrl))))&&((this.contributorsUrl == rhs.contributorsUrl)||((this.contributorsUrl!= null)&&this.contributorsUrl.equals(rhs.contributorsUrl))))&&((this.commitsUrl == rhs.commitsUrl)||((this.commitsUrl!= null)&&this.commitsUrl.equals(rhs.commitsUrl))))&&((this.gitRefsUrl == rhs.gitRefsUrl)||((this.gitRefsUrl!= null)&&this.gitRefsUrl.equals(rhs.gitRefsUrl))))&&((this.hooksUrl == rhs.hooksUrl)||((this.hooksUrl!= null)&&this.hooksUrl.equals(rhs.hooksUrl))))&&((this.subscribersUrl == rhs.subscribersUrl)||((this.subscribersUrl!= null)&&this.subscribersUrl.equals(rhs.subscribersUrl))))&&((this.forksUrl == rhs.forksUrl)||((this.forksUrl!= null)&&this.forksUrl.equals(rhs.forksUrl))))&&((this._private == rhs._private)||((this._private!= null)&&this._private.equals(rhs._private))))&&((this.issueCommentUrl == rhs.issueCommentUrl)||((this.issueCommentUrl!= null)&&this.issueCommentUrl.equals(rhs.issueCommentUrl))))&&((this.statusesUrl == rhs.statusesUrl)||((this.statusesUrl!= null)&&this.statusesUrl.equals(rhs.statusesUrl))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.collaboratorsUrl == rhs.collaboratorsUrl)||((this.collaboratorsUrl!= null)&&this.collaboratorsUrl.equals(rhs.collaboratorsUrl))))&&((this.owner == rhs.owner)||((this.owner!= null)&&this.owner.equals(rhs.owner))))&&((this.compareUrl == rhs.compareUrl)||((this.compareUrl!= null)&&this.compareUrl.equals(rhs.compareUrl))))&&((this.gitCommitsUrl == rhs.gitCommitsUrl)||((this.gitCommitsUrl!= null)&&this.gitCommitsUrl.equals(rhs.gitCommitsUrl))))&&((this.labelsUrl == rhs.labelsUrl)||((this.labelsUrl!= null)&&this.labelsUrl.equals(rhs.labelsUrl))))&&((this.htmlUrl == rhs.htmlUrl)||((this.htmlUrl!= null)&&this.htmlUrl.equals(rhs.htmlUrl))))&&((this.stargazersUrl == rhs.stargazersUrl)||((this.stargazersUrl!= null)&&this.stargazersUrl.equals(rhs.stargazersUrl))))&&((this.fullName == rhs.fullName)||((this.fullName!= null)&&this.fullName.equals(rhs.fullName))))&&((this.keysUrl == rhs.keysUrl)||((this.keysUrl!= null)&&this.keysUrl.equals(rhs.keysUrl))))&&((this.downloadsUrl == rhs.downloadsUrl)||((this.downloadsUrl!= null)&&this.downloadsUrl.equals(rhs.downloadsUrl))))&&((this.contentsUrl == rhs.contentsUrl)||((this.contentsUrl!= null)&&this.contentsUrl.equals(rhs.contentsUrl))))&&((this.pullsUrl == rhs.pullsUrl)||((this.pullsUrl!= null)&&this.pullsUrl.equals(rhs.pullsUrl))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))))&&((this.tagsUrl == rhs.tagsUrl)||((this.tagsUrl!= null)&&this.tagsUrl.equals(rhs.tagsUrl))))&&((this.fork == rhs.fork)||((this.fork!= null)&&this.fork.equals(rhs.fork))))&&((this.commentsUrl == rhs.commentsUrl)||((this.commentsUrl!= null)&&this.commentsUrl.equals(rhs.commentsUrl))))&&((this.treesUrl == rhs.treesUrl)||((this.treesUrl!= null)&&this.treesUrl.equals(rhs.treesUrl))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.deploymentsUrl == rhs.deploymentsUrl)||((this.deploymentsUrl!= null)&&this.deploymentsUrl.equals(rhs.deploymentsUrl))))&&((this.eventsUrl == rhs.eventsUrl)||((this.eventsUrl!= null)&&this.eventsUrl.equals(rhs.eventsUrl))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.gitTagsUrl == rhs.gitTagsUrl)||((this.gitTagsUrl!= null)&&this.gitTagsUrl.equals(rhs.gitTagsUrl))))&&((this.mergesUrl == rhs.mergesUrl)||((this.mergesUrl!= null)&&this.mergesUrl.equals(rhs.mergesUrl))))&&((this.notificationsUrl == rhs.notificationsUrl)||((this.notificationsUrl!= null)&&this.notificationsUrl.equals(rhs.notificationsUrl))))&&((this.nodeId == rhs.nodeId)||((this.nodeId!= null)&&this.nodeId.equals(rhs.nodeId))))&&((this.subscriptionUrl == rhs.subscriptionUrl)||((this.subscriptionUrl!= null)&&this.subscriptionUrl.equals(rhs.subscriptionUrl))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "avatar_url",
        "events_url",
        "followers_url",
        "following_url",
        "gists_url",
        "gravatar_id",
        "html_url",
        "id",
        "login",
        "node_id",
        "organizations_url",
        "received_events_url",
        "repos_url",
        "site_admin",
        "starred_url",
        "subscriptions_url",
        "type",
        "url"
    })
    @Generated("jsonschema2pojo")
    public static class Sender {

        @JsonProperty("avatar_url")
        private String avatarUrl;
        @JsonProperty("events_url")
        private String eventsUrl;
        @JsonProperty("followers_url")
        private String followersUrl;
        @JsonProperty("following_url")
        private String followingUrl;
        @JsonProperty("gists_url")
        private String gistsUrl;
        @JsonProperty("gravatar_id")
        private String gravatarId;
        @JsonProperty("html_url")
        private String htmlUrl;
        @JsonProperty("id")
        private Long id;
        @JsonProperty("login")
        private String login;
        @JsonProperty("node_id")
        private String nodeId;
        @JsonProperty("organizations_url")
        private String organizationsUrl;
        @JsonProperty("received_events_url")
        private String receivedEventsUrl;
        @JsonProperty("repos_url")
        private String reposUrl;
        @JsonProperty("site_admin")
        private Boolean siteAdmin;
        @JsonProperty("starred_url")
        private String starredUrl;
        @JsonProperty("subscriptions_url")
        private String subscriptionsUrl;
        @JsonProperty("type")
        private String type;
        @JsonProperty("url")
        private String url;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Sender() {
        }

        /**
         * 
         * @param receivedEventsUrl
         * @param siteAdmin
         * @param followingUrl
         * @param gistsUrl
         * @param avatarUrl
         * @param organizationsUrl
         * @param reposUrl
         * @param htmlUrl
         * @param subscriptionsUrl
         * @param login
         * @param type
         * @param starredUrl
         * @param url
         * @param gravatarId
         * @param followersUrl
         * @param eventsUrl
         * @param id
         * @param nodeId
         */
        public Sender(String avatarUrl, String eventsUrl, String followersUrl, String followingUrl, String gistsUrl, String gravatarId, String htmlUrl, Long id, String login, String nodeId, String organizationsUrl, String receivedEventsUrl, String reposUrl, Boolean siteAdmin, String starredUrl, String subscriptionsUrl, String type, String url) {
            super();
            this.avatarUrl = avatarUrl;
            this.eventsUrl = eventsUrl;
            this.followersUrl = followersUrl;
            this.followingUrl = followingUrl;
            this.gistsUrl = gistsUrl;
            this.gravatarId = gravatarId;
            this.htmlUrl = htmlUrl;
            this.id = id;
            this.login = login;
            this.nodeId = nodeId;
            this.organizationsUrl = organizationsUrl;
            this.receivedEventsUrl = receivedEventsUrl;
            this.reposUrl = reposUrl;
            this.siteAdmin = siteAdmin;
            this.starredUrl = starredUrl;
            this.subscriptionsUrl = subscriptionsUrl;
            this.type = type;
            this.url = url;
        }

        @JsonProperty("avatar_url")
        public String getAvatarUrl() {
            return avatarUrl;
        }

        @JsonProperty("avatar_url")
        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        @JsonProperty("events_url")
        public String getEventsUrl() {
            return eventsUrl;
        }

        @JsonProperty("events_url")
        public void setEventsUrl(String eventsUrl) {
            this.eventsUrl = eventsUrl;
        }

        @JsonProperty("followers_url")
        public String getFollowersUrl() {
            return followersUrl;
        }

        @JsonProperty("followers_url")
        public void setFollowersUrl(String followersUrl) {
            this.followersUrl = followersUrl;
        }

        @JsonProperty("following_url")
        public String getFollowingUrl() {
            return followingUrl;
        }

        @JsonProperty("following_url")
        public void setFollowingUrl(String followingUrl) {
            this.followingUrl = followingUrl;
        }

        @JsonProperty("gists_url")
        public String getGistsUrl() {
            return gistsUrl;
        }

        @JsonProperty("gists_url")
        public void setGistsUrl(String gistsUrl) {
            this.gistsUrl = gistsUrl;
        }

        @JsonProperty("gravatar_id")
        public String getGravatarId() {
            return gravatarId;
        }

        @JsonProperty("gravatar_id")
        public void setGravatarId(String gravatarId) {
            this.gravatarId = gravatarId;
        }

        @JsonProperty("html_url")
        public String getHtmlUrl() {
            return htmlUrl;
        }

        @JsonProperty("html_url")
        public void setHtmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
        }

        @JsonProperty("id")
        public Long getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(Long id) {
            this.id = id;
        }

        @JsonProperty("login")
        public String getLogin() {
            return login;
        }

        @JsonProperty("login")
        public void setLogin(String login) {
            this.login = login;
        }

        @JsonProperty("node_id")
        public String getNodeId() {
            return nodeId;
        }

        @JsonProperty("node_id")
        public void setNodeId(String nodeId) {
            this.nodeId = nodeId;
        }

        @JsonProperty("organizations_url")
        public String getOrganizationsUrl() {
            return organizationsUrl;
        }

        @JsonProperty("organizations_url")
        public void setOrganizationsUrl(String organizationsUrl) {
            this.organizationsUrl = organizationsUrl;
        }

        @JsonProperty("received_events_url")
        public String getReceivedEventsUrl() {
            return receivedEventsUrl;
        }

        @JsonProperty("received_events_url")
        public void setReceivedEventsUrl(String receivedEventsUrl) {
            this.receivedEventsUrl = receivedEventsUrl;
        }

        @JsonProperty("repos_url")
        public String getReposUrl() {
            return reposUrl;
        }

        @JsonProperty("repos_url")
        public void setReposUrl(String reposUrl) {
            this.reposUrl = reposUrl;
        }

        @JsonProperty("site_admin")
        public Boolean getSiteAdmin() {
            return siteAdmin;
        }

        @JsonProperty("site_admin")
        public void setSiteAdmin(Boolean siteAdmin) {
            this.siteAdmin = siteAdmin;
        }

        @JsonProperty("starred_url")
        public String getStarredUrl() {
            return starredUrl;
        }

        @JsonProperty("starred_url")
        public void setStarredUrl(String starredUrl) {
            this.starredUrl = starredUrl;
        }

        @JsonProperty("subscriptions_url")
        public String getSubscriptionsUrl() {
            return subscriptionsUrl;
        }

        @JsonProperty("subscriptions_url")
        public void setSubscriptionsUrl(String subscriptionsUrl) {
            this.subscriptionsUrl = subscriptionsUrl;
        }

        @JsonProperty("type")
        public String getType() {
            return type;
        }

        @JsonProperty("type")
        public void setType(String type) {
            this.type = type;
        }

        @JsonProperty("url")
        public String getUrl() {
            return url;
        }

        @JsonProperty("url")
        public void setUrl(String url) {
            this.url = url;
        }

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(WorkflowRunGitHubEvent.Sender.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("avatarUrl");
            sb.append('=');
            sb.append(((this.avatarUrl == null)?"<null>":this.avatarUrl));
            sb.append(',');
            sb.append("eventsUrl");
            sb.append('=');
            sb.append(((this.eventsUrl == null)?"<null>":this.eventsUrl));
            sb.append(',');
            sb.append("followersUrl");
            sb.append('=');
            sb.append(((this.followersUrl == null)?"<null>":this.followersUrl));
            sb.append(',');
            sb.append("followingUrl");
            sb.append('=');
            sb.append(((this.followingUrl == null)?"<null>":this.followingUrl));
            sb.append(',');
            sb.append("gistsUrl");
            sb.append('=');
            sb.append(((this.gistsUrl == null)?"<null>":this.gistsUrl));
            sb.append(',');
            sb.append("gravatarId");
            sb.append('=');
            sb.append(((this.gravatarId == null)?"<null>":this.gravatarId));
            sb.append(',');
            sb.append("htmlUrl");
            sb.append('=');
            sb.append(((this.htmlUrl == null)?"<null>":this.htmlUrl));
            sb.append(',');
            sb.append("id");
            sb.append('=');
            sb.append(((this.id == null)?"<null>":this.id));
            sb.append(',');
            sb.append("login");
            sb.append('=');
            sb.append(((this.login == null)?"<null>":this.login));
            sb.append(',');
            sb.append("nodeId");
            sb.append('=');
            sb.append(((this.nodeId == null)?"<null>":this.nodeId));
            sb.append(',');
            sb.append("organizationsUrl");
            sb.append('=');
            sb.append(((this.organizationsUrl == null)?"<null>":this.organizationsUrl));
            sb.append(',');
            sb.append("receivedEventsUrl");
            sb.append('=');
            sb.append(((this.receivedEventsUrl == null)?"<null>":this.receivedEventsUrl));
            sb.append(',');
            sb.append("reposUrl");
            sb.append('=');
            sb.append(((this.reposUrl == null)?"<null>":this.reposUrl));
            sb.append(',');
            sb.append("siteAdmin");
            sb.append('=');
            sb.append(((this.siteAdmin == null)?"<null>":this.siteAdmin));
            sb.append(',');
            sb.append("starredUrl");
            sb.append('=');
            sb.append(((this.starredUrl == null)?"<null>":this.starredUrl));
            sb.append(',');
            sb.append("subscriptionsUrl");
            sb.append('=');
            sb.append(((this.subscriptionsUrl == null)?"<null>":this.subscriptionsUrl));
            sb.append(',');
            sb.append("type");
            sb.append('=');
            sb.append(((this.type == null)?"<null>":this.type));
            sb.append(',');
            sb.append("url");
            sb.append('=');
            sb.append(((this.url == null)?"<null>":this.url));
            sb.append(',');
            sb.append("additionalProperties");
            sb.append('=');
            sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
            sb.append(',');
            if (sb.charAt((sb.length()- 1)) == ',') {
                sb.setCharAt((sb.length()- 1), ']');
            } else {
                sb.append(']');
            }
            return sb.toString();
        }

        @Override
        public int hashCode() {
            int result = 1;
            result = ((result* 31)+((this.receivedEventsUrl == null)? 0 :this.receivedEventsUrl.hashCode()));
            result = ((result* 31)+((this.siteAdmin == null)? 0 :this.siteAdmin.hashCode()));
            result = ((result* 31)+((this.followingUrl == null)? 0 :this.followingUrl.hashCode()));
            result = ((result* 31)+((this.gistsUrl == null)? 0 :this.gistsUrl.hashCode()));
            result = ((result* 31)+((this.avatarUrl == null)? 0 :this.avatarUrl.hashCode()));
            result = ((result* 31)+((this.organizationsUrl == null)? 0 :this.organizationsUrl.hashCode()));
            result = ((result* 31)+((this.reposUrl == null)? 0 :this.reposUrl.hashCode()));
            result = ((result* 31)+((this.htmlUrl == null)? 0 :this.htmlUrl.hashCode()));
            result = ((result* 31)+((this.subscriptionsUrl == null)? 0 :this.subscriptionsUrl.hashCode()));
            result = ((result* 31)+((this.login == null)? 0 :this.login.hashCode()));
            result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
            result = ((result* 31)+((this.starredUrl == null)? 0 :this.starredUrl.hashCode()));
            result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
            result = ((result* 31)+((this.gravatarId == null)? 0 :this.gravatarId.hashCode()));
            result = ((result* 31)+((this.followersUrl == null)? 0 :this.followersUrl.hashCode()));
            result = ((result* 31)+((this.eventsUrl == null)? 0 :this.eventsUrl.hashCode()));
            result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.nodeId == null)? 0 :this.nodeId.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof WorkflowRunGitHubEvent.Sender) == false) {
                return false;
            }
            WorkflowRunGitHubEvent.Sender rhs = ((WorkflowRunGitHubEvent.Sender) other);
            return ((((((((((((((((((((this.receivedEventsUrl == rhs.receivedEventsUrl)||((this.receivedEventsUrl!= null)&&this.receivedEventsUrl.equals(rhs.receivedEventsUrl)))&&((this.siteAdmin == rhs.siteAdmin)||((this.siteAdmin!= null)&&this.siteAdmin.equals(rhs.siteAdmin))))&&((this.followingUrl == rhs.followingUrl)||((this.followingUrl!= null)&&this.followingUrl.equals(rhs.followingUrl))))&&((this.gistsUrl == rhs.gistsUrl)||((this.gistsUrl!= null)&&this.gistsUrl.equals(rhs.gistsUrl))))&&((this.avatarUrl == rhs.avatarUrl)||((this.avatarUrl!= null)&&this.avatarUrl.equals(rhs.avatarUrl))))&&((this.organizationsUrl == rhs.organizationsUrl)||((this.organizationsUrl!= null)&&this.organizationsUrl.equals(rhs.organizationsUrl))))&&((this.reposUrl == rhs.reposUrl)||((this.reposUrl!= null)&&this.reposUrl.equals(rhs.reposUrl))))&&((this.htmlUrl == rhs.htmlUrl)||((this.htmlUrl!= null)&&this.htmlUrl.equals(rhs.htmlUrl))))&&((this.subscriptionsUrl == rhs.subscriptionsUrl)||((this.subscriptionsUrl!= null)&&this.subscriptionsUrl.equals(rhs.subscriptionsUrl))))&&((this.login == rhs.login)||((this.login!= null)&&this.login.equals(rhs.login))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.starredUrl == rhs.starredUrl)||((this.starredUrl!= null)&&this.starredUrl.equals(rhs.starredUrl))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))))&&((this.gravatarId == rhs.gravatarId)||((this.gravatarId!= null)&&this.gravatarId.equals(rhs.gravatarId))))&&((this.followersUrl == rhs.followersUrl)||((this.followersUrl!= null)&&this.followersUrl.equals(rhs.followersUrl))))&&((this.eventsUrl == rhs.eventsUrl)||((this.eventsUrl!= null)&&this.eventsUrl.equals(rhs.eventsUrl))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.nodeId == rhs.nodeId)||((this.nodeId!= null)&&this.nodeId.equals(rhs.nodeId))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "badge_url",
        "created_at",
        "html_url",
        "id",
        "name",
        "node_id",
        "path",
        "state",
        "updated_at",
        "url"
    })
    @Generated("jsonschema2pojo")
    public static class Workflow {

        @JsonProperty("badge_url")
        private String badgeUrl;
        @JsonProperty("created_at")
        private String createdAt;
        @JsonProperty("html_url")
        private String htmlUrl;
        @JsonProperty("id")
        private Long id;
        @JsonProperty("name")
        private String name;
        @JsonProperty("node_id")
        private String nodeId;
        @JsonProperty("path")
        private String path;
        @JsonProperty("state")
        private String state;
        @JsonProperty("updated_at")
        private String updatedAt;
        @JsonProperty("url")
        private String url;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Workflow() {
        }

        /**
         * 
         * @param createdAt
         * @param path
         * @param badgeUrl
         * @param htmlUrl
         * @param name
         * @param id
         * @param state
         * @param nodeId
         * @param url
         * @param updatedAt
         */
        public Workflow(String badgeUrl, String createdAt, String htmlUrl, Long id, String name, String nodeId, String path, String state, String updatedAt, String url) {
            super();
            this.badgeUrl = badgeUrl;
            this.createdAt = createdAt;
            this.htmlUrl = htmlUrl;
            this.id = id;
            this.name = name;
            this.nodeId = nodeId;
            this.path = path;
            this.state = state;
            this.updatedAt = updatedAt;
            this.url = url;
        }

        @JsonProperty("badge_url")
        public String getBadgeUrl() {
            return badgeUrl;
        }

        @JsonProperty("badge_url")
        public void setBadgeUrl(String badgeUrl) {
            this.badgeUrl = badgeUrl;
        }

        @JsonProperty("created_at")
        public String getCreatedAt() {
            return createdAt;
        }

        @JsonProperty("created_at")
        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        @JsonProperty("html_url")
        public String getHtmlUrl() {
            return htmlUrl;
        }

        @JsonProperty("html_url")
        public void setHtmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
        }

        @JsonProperty("id")
        public Long getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(Long id) {
            this.id = id;
        }

        @JsonProperty("name")
        public String getName() {
            return name;
        }

        @JsonProperty("name")
        public void setName(String name) {
            this.name = name;
        }

        @JsonProperty("node_id")
        public String getNodeId() {
            return nodeId;
        }

        @JsonProperty("node_id")
        public void setNodeId(String nodeId) {
            this.nodeId = nodeId;
        }

        @JsonProperty("path")
        public String getPath() {
            return path;
        }

        @JsonProperty("path")
        public void setPath(String path) {
            this.path = path;
        }

        @JsonProperty("state")
        public String getState() {
            return state;
        }

        @JsonProperty("state")
        public void setState(String state) {
            this.state = state;
        }

        @JsonProperty("updated_at")
        public String getUpdatedAt() {
            return updatedAt;
        }

        @JsonProperty("updated_at")
        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        @JsonProperty("url")
        public String getUrl() {
            return url;
        }

        @JsonProperty("url")
        public void setUrl(String url) {
            this.url = url;
        }

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(WorkflowRunGitHubEvent.Workflow.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("badgeUrl");
            sb.append('=');
            sb.append(((this.badgeUrl == null)?"<null>":this.badgeUrl));
            sb.append(',');
            sb.append("createdAt");
            sb.append('=');
            sb.append(((this.createdAt == null)?"<null>":this.createdAt));
            sb.append(',');
            sb.append("htmlUrl");
            sb.append('=');
            sb.append(((this.htmlUrl == null)?"<null>":this.htmlUrl));
            sb.append(',');
            sb.append("id");
            sb.append('=');
            sb.append(((this.id == null)?"<null>":this.id));
            sb.append(',');
            sb.append("name");
            sb.append('=');
            sb.append(((this.name == null)?"<null>":this.name));
            sb.append(',');
            sb.append("nodeId");
            sb.append('=');
            sb.append(((this.nodeId == null)?"<null>":this.nodeId));
            sb.append(',');
            sb.append("path");
            sb.append('=');
            sb.append(((this.path == null)?"<null>":this.path));
            sb.append(',');
            sb.append("state");
            sb.append('=');
            sb.append(((this.state == null)?"<null>":this.state));
            sb.append(',');
            sb.append("updatedAt");
            sb.append('=');
            sb.append(((this.updatedAt == null)?"<null>":this.updatedAt));
            sb.append(',');
            sb.append("url");
            sb.append('=');
            sb.append(((this.url == null)?"<null>":this.url));
            sb.append(',');
            sb.append("additionalProperties");
            sb.append('=');
            sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
            sb.append(',');
            if (sb.charAt((sb.length()- 1)) == ',') {
                sb.setCharAt((sb.length()- 1), ']');
            } else {
                sb.append(']');
            }
            return sb.toString();
        }

        @Override
        public int hashCode() {
            int result = 1;
            result = ((result* 31)+((this.createdAt == null)? 0 :this.createdAt.hashCode()));
            result = ((result* 31)+((this.path == null)? 0 :this.path.hashCode()));
            result = ((result* 31)+((this.badgeUrl == null)? 0 :this.badgeUrl.hashCode()));
            result = ((result* 31)+((this.htmlUrl == null)? 0 :this.htmlUrl.hashCode()));
            result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
            result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
            result = ((result* 31)+((this.state == null)? 0 :this.state.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.nodeId == null)? 0 :this.nodeId.hashCode()));
            result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
            result = ((result* 31)+((this.updatedAt == null)? 0 :this.updatedAt.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof WorkflowRunGitHubEvent.Workflow) == false) {
                return false;
            }
            WorkflowRunGitHubEvent.Workflow rhs = ((WorkflowRunGitHubEvent.Workflow) other);
            return ((((((((((((this.createdAt == rhs.createdAt)||((this.createdAt!= null)&&this.createdAt.equals(rhs.createdAt)))&&((this.path == rhs.path)||((this.path!= null)&&this.path.equals(rhs.path))))&&((this.badgeUrl == rhs.badgeUrl)||((this.badgeUrl!= null)&&this.badgeUrl.equals(rhs.badgeUrl))))&&((this.htmlUrl == rhs.htmlUrl)||((this.htmlUrl!= null)&&this.htmlUrl.equals(rhs.htmlUrl))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.state == rhs.state)||((this.state!= null)&&this.state.equals(rhs.state))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.nodeId == rhs.nodeId)||((this.nodeId!= null)&&this.nodeId.equals(rhs.nodeId))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))))&&((this.updatedAt == rhs.updatedAt)||((this.updatedAt!= null)&&this.updatedAt.equals(rhs.updatedAt))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "artifacts_url",
        "cancel_url",
        "check_suite_id",
        "check_suite_node_id",
        "check_suite_url",
        "conclusion",
        "created_at",
        "event",
        "head_branch",
        "head_commit",
        "head_repository",
        "head_sha",
        "html_url",
        "id",
        "jobs_url",
        "logs_url",
        "name",
        "node_id",
        "pull_requests",
        "repository",
        "rerun_url",
        "run_number",
        "status",
        "updated_at",
        "url",
        "workflow_id",
        "workflow_url"
    })
    @Generated("jsonschema2pojo")
    public static class WorkflowRun {

        @JsonProperty("artifacts_url")
        private String artifactsUrl;
        @JsonProperty("cancel_url")
        private String cancelUrl;
        @JsonProperty("check_suite_id")
        private Long checkSuiteId;
        @JsonProperty("check_suite_node_id")
        private String checkSuiteNodeId;
        @JsonProperty("check_suite_url")
        private String checkSuiteUrl;
        @JsonProperty("conclusion")
        private String conclusion;
        @JsonProperty("created_at")
        private String createdAt;
        @JsonProperty("event")
        private String event;
        @JsonProperty("head_branch")
        private String headBranch;
        @JsonProperty("head_commit")
        private WorkflowRunGitHubEvent.HeadCommit headCommit;
        @JsonProperty("head_repository")
        private WorkflowRunGitHubEvent.HeadRepository headRepository;
        @JsonProperty("head_sha")
        private String headSha;
        @JsonProperty("html_url")
        private String htmlUrl;
        @JsonProperty("id")
        private Long id;
        @JsonProperty("jobs_url")
        private String jobsUrl;
        @JsonProperty("logs_url")
        private String logsUrl;
        @JsonProperty("name")
        private String name;
        @JsonProperty("node_id")
        private String nodeId;
        @JsonProperty("pull_requests")
        private List<Object> pullRequests = new ArrayList<Object>();
        @JsonProperty("repository")
        private WorkflowRunGitHubEvent.Repository2 repository;
        @JsonProperty("rerun_url")
        private String rerunUrl;
        @JsonProperty("run_number")
        private Long runNumber;
        @JsonProperty("status")
        private String status;
        @JsonProperty("updated_at")
        private String updatedAt;
        @JsonProperty("url")
        private String url;
        @JsonProperty("workflow_id")
        private Long workflowId;
        @JsonProperty("workflow_url")
        private String workflowUrl;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public WorkflowRun() {
        }

        /**
         * 
         * @param headRepository
         * @param headBranch
         * @param headSha
         * @param repository
         * @param headCommit
         * @param conclusion
         * @param rerunUrl
         * @param createdAt
         * @param id
         * @param event
         * @param updatedAt
         * @param jobsUrl
         * @param htmlUrl
         * @param workflowUrl
         * @param logsUrl
         * @param pullRequests
         * @param checkSuiteNodeId
         * @param url
         * @param artifactsUrl
         * @param cancelUrl
         * @param checkSuiteUrl
         * @param checkSuiteId
         * @param name
         * @param runNumber
         * @param nodeId
         * @param workflowId
         * @param status
         */
        public WorkflowRun(String artifactsUrl, String cancelUrl, Long checkSuiteId, String checkSuiteNodeId, String checkSuiteUrl, String conclusion, String createdAt, String event, String headBranch, WorkflowRunGitHubEvent.HeadCommit headCommit, WorkflowRunGitHubEvent.HeadRepository headRepository, String headSha, String htmlUrl, Long id, String jobsUrl, String logsUrl, String name, String nodeId, List<Object> pullRequests, WorkflowRunGitHubEvent.Repository2 repository, String rerunUrl, Long runNumber, String status, String updatedAt, String url, Long workflowId, String workflowUrl) {
            super();
            this.artifactsUrl = artifactsUrl;
            this.cancelUrl = cancelUrl;
            this.checkSuiteId = checkSuiteId;
            this.checkSuiteNodeId = checkSuiteNodeId;
            this.checkSuiteUrl = checkSuiteUrl;
            this.conclusion = conclusion;
            this.createdAt = createdAt;
            this.event = event;
            this.headBranch = headBranch;
            this.headCommit = headCommit;
            this.headRepository = headRepository;
            this.headSha = headSha;
            this.htmlUrl = htmlUrl;
            this.id = id;
            this.jobsUrl = jobsUrl;
            this.logsUrl = logsUrl;
            this.name = name;
            this.nodeId = nodeId;
            this.pullRequests = pullRequests;
            this.repository = repository;
            this.rerunUrl = rerunUrl;
            this.runNumber = runNumber;
            this.status = status;
            this.updatedAt = updatedAt;
            this.url = url;
            this.workflowId = workflowId;
            this.workflowUrl = workflowUrl;
        }

        @JsonProperty("artifacts_url")
        public String getArtifactsUrl() {
            return artifactsUrl;
        }

        @JsonProperty("artifacts_url")
        public void setArtifactsUrl(String artifactsUrl) {
            this.artifactsUrl = artifactsUrl;
        }

        @JsonProperty("cancel_url")
        public String getCancelUrl() {
            return cancelUrl;
        }

        @JsonProperty("cancel_url")
        public void setCancelUrl(String cancelUrl) {
            this.cancelUrl = cancelUrl;
        }

        @JsonProperty("check_suite_id")
        public Long getCheckSuiteId() {
            return checkSuiteId;
        }

        @JsonProperty("check_suite_id")
        public void setCheckSuiteId(Long checkSuiteId) {
            this.checkSuiteId = checkSuiteId;
        }

        @JsonProperty("check_suite_node_id")
        public String getCheckSuiteNodeId() {
            return checkSuiteNodeId;
        }

        @JsonProperty("check_suite_node_id")
        public void setCheckSuiteNodeId(String checkSuiteNodeId) {
            this.checkSuiteNodeId = checkSuiteNodeId;
        }

        @JsonProperty("check_suite_url")
        public String getCheckSuiteUrl() {
            return checkSuiteUrl;
        }

        @JsonProperty("check_suite_url")
        public void setCheckSuiteUrl(String checkSuiteUrl) {
            this.checkSuiteUrl = checkSuiteUrl;
        }

        @JsonProperty("conclusion")
        public String getConclusion() {
            return conclusion;
        }

        @JsonProperty("conclusion")
        public void setConclusion(String conclusion) {
            this.conclusion = conclusion;
        }

        @JsonProperty("created_at")
        public String getCreatedAt() {
            return createdAt;
        }

        @JsonProperty("created_at")
        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        @JsonProperty("event")
        public String getEvent() {
            return event;
        }

        @JsonProperty("event")
        public void setEvent(String event) {
            this.event = event;
        }

        @JsonProperty("head_branch")
        public String getHeadBranch() {
            return headBranch;
        }

        @JsonProperty("head_branch")
        public void setHeadBranch(String headBranch) {
            this.headBranch = headBranch;
        }

        @JsonProperty("head_commit")
        public WorkflowRunGitHubEvent.HeadCommit getHeadCommit() {
            return headCommit;
        }

        @JsonProperty("head_commit")
        public void setHeadCommit(WorkflowRunGitHubEvent.HeadCommit headCommit) {
            this.headCommit = headCommit;
        }

        @JsonProperty("head_repository")
        public WorkflowRunGitHubEvent.HeadRepository getHeadRepository() {
            return headRepository;
        }

        @JsonProperty("head_repository")
        public void setHeadRepository(WorkflowRunGitHubEvent.HeadRepository headRepository) {
            this.headRepository = headRepository;
        }

        @JsonProperty("head_sha")
        public String getHeadSha() {
            return headSha;
        }

        @JsonProperty("head_sha")
        public void setHeadSha(String headSha) {
            this.headSha = headSha;
        }

        @JsonProperty("html_url")
        public String getHtmlUrl() {
            return htmlUrl;
        }

        @JsonProperty("html_url")
        public void setHtmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
        }

        @JsonProperty("id")
        public Long getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(Long id) {
            this.id = id;
        }

        @JsonProperty("jobs_url")
        public String getJobsUrl() {
            return jobsUrl;
        }

        @JsonProperty("jobs_url")
        public void setJobsUrl(String jobsUrl) {
            this.jobsUrl = jobsUrl;
        }

        @JsonProperty("logs_url")
        public String getLogsUrl() {
            return logsUrl;
        }

        @JsonProperty("logs_url")
        public void setLogsUrl(String logsUrl) {
            this.logsUrl = logsUrl;
        }

        @JsonProperty("name")
        public String getName() {
            return name;
        }

        @JsonProperty("name")
        public void setName(String name) {
            this.name = name;
        }

        @JsonProperty("node_id")
        public String getNodeId() {
            return nodeId;
        }

        @JsonProperty("node_id")
        public void setNodeId(String nodeId) {
            this.nodeId = nodeId;
        }

        @JsonProperty("pull_requests")
        public List<Object> getPullRequests() {
            return pullRequests;
        }

        @JsonProperty("pull_requests")
        public void setPullRequests(List<Object> pullRequests) {
            this.pullRequests = pullRequests;
        }

        @JsonProperty("repository")
        public WorkflowRunGitHubEvent.Repository2 getRepository() {
            return repository;
        }

        @JsonProperty("repository")
        public void setRepository(WorkflowRunGitHubEvent.Repository2 repository) {
            this.repository = repository;
        }

        @JsonProperty("rerun_url")
        public String getRerunUrl() {
            return rerunUrl;
        }

        @JsonProperty("rerun_url")
        public void setRerunUrl(String rerunUrl) {
            this.rerunUrl = rerunUrl;
        }

        @JsonProperty("run_number")
        public Long getRunNumber() {
            return runNumber;
        }

        @JsonProperty("run_number")
        public void setRunNumber(Long runNumber) {
            this.runNumber = runNumber;
        }

        @JsonProperty("status")
        public String getStatus() {
            return status;
        }

        @JsonProperty("status")
        public void setStatus(String status) {
            this.status = status;
        }

        @JsonProperty("updated_at")
        public String getUpdatedAt() {
            return updatedAt;
        }

        @JsonProperty("updated_at")
        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        @JsonProperty("url")
        public String getUrl() {
            return url;
        }

        @JsonProperty("url")
        public void setUrl(String url) {
            this.url = url;
        }

        @JsonProperty("workflow_id")
        public Long getWorkflowId() {
            return workflowId;
        }

        @JsonProperty("workflow_id")
        public void setWorkflowId(Long workflowId) {
            this.workflowId = workflowId;
        }

        @JsonProperty("workflow_url")
        public String getWorkflowUrl() {
            return workflowUrl;
        }

        @JsonProperty("workflow_url")
        public void setWorkflowUrl(String workflowUrl) {
            this.workflowUrl = workflowUrl;
        }

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(WorkflowRunGitHubEvent.WorkflowRun.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("artifactsUrl");
            sb.append('=');
            sb.append(((this.artifactsUrl == null)?"<null>":this.artifactsUrl));
            sb.append(',');
            sb.append("cancelUrl");
            sb.append('=');
            sb.append(((this.cancelUrl == null)?"<null>":this.cancelUrl));
            sb.append(',');
            sb.append("checkSuiteId");
            sb.append('=');
            sb.append(((this.checkSuiteId == null)?"<null>":this.checkSuiteId));
            sb.append(',');
            sb.append("checkSuiteNodeId");
            sb.append('=');
            sb.append(((this.checkSuiteNodeId == null)?"<null>":this.checkSuiteNodeId));
            sb.append(',');
            sb.append("checkSuiteUrl");
            sb.append('=');
            sb.append(((this.checkSuiteUrl == null)?"<null>":this.checkSuiteUrl));
            sb.append(',');
            sb.append("conclusion");
            sb.append('=');
            sb.append(((this.conclusion == null)?"<null>":this.conclusion));
            sb.append(',');
            sb.append("createdAt");
            sb.append('=');
            sb.append(((this.createdAt == null)?"<null>":this.createdAt));
            sb.append(',');
            sb.append("event");
            sb.append('=');
            sb.append(((this.event == null)?"<null>":this.event));
            sb.append(',');
            sb.append("headBranch");
            sb.append('=');
            sb.append(((this.headBranch == null)?"<null>":this.headBranch));
            sb.append(',');
            sb.append("headCommit");
            sb.append('=');
            sb.append(((this.headCommit == null)?"<null>":this.headCommit));
            sb.append(',');
            sb.append("headRepository");
            sb.append('=');
            sb.append(((this.headRepository == null)?"<null>":this.headRepository));
            sb.append(',');
            sb.append("headSha");
            sb.append('=');
            sb.append(((this.headSha == null)?"<null>":this.headSha));
            sb.append(',');
            sb.append("htmlUrl");
            sb.append('=');
            sb.append(((this.htmlUrl == null)?"<null>":this.htmlUrl));
            sb.append(',');
            sb.append("id");
            sb.append('=');
            sb.append(((this.id == null)?"<null>":this.id));
            sb.append(',');
            sb.append("jobsUrl");
            sb.append('=');
            sb.append(((this.jobsUrl == null)?"<null>":this.jobsUrl));
            sb.append(',');
            sb.append("logsUrl");
            sb.append('=');
            sb.append(((this.logsUrl == null)?"<null>":this.logsUrl));
            sb.append(',');
            sb.append("name");
            sb.append('=');
            sb.append(((this.name == null)?"<null>":this.name));
            sb.append(',');
            sb.append("nodeId");
            sb.append('=');
            sb.append(((this.nodeId == null)?"<null>":this.nodeId));
            sb.append(',');
            sb.append("pullRequests");
            sb.append('=');
            sb.append(((this.pullRequests == null)?"<null>":this.pullRequests));
            sb.append(',');
            sb.append("repository");
            sb.append('=');
            sb.append(((this.repository == null)?"<null>":this.repository));
            sb.append(',');
            sb.append("rerunUrl");
            sb.append('=');
            sb.append(((this.rerunUrl == null)?"<null>":this.rerunUrl));
            sb.append(',');
            sb.append("runNumber");
            sb.append('=');
            sb.append(((this.runNumber == null)?"<null>":this.runNumber));
            sb.append(',');
            sb.append("status");
            sb.append('=');
            sb.append(((this.status == null)?"<null>":this.status));
            sb.append(',');
            sb.append("updatedAt");
            sb.append('=');
            sb.append(((this.updatedAt == null)?"<null>":this.updatedAt));
            sb.append(',');
            sb.append("url");
            sb.append('=');
            sb.append(((this.url == null)?"<null>":this.url));
            sb.append(',');
            sb.append("workflowId");
            sb.append('=');
            sb.append(((this.workflowId == null)?"<null>":this.workflowId));
            sb.append(',');
            sb.append("workflowUrl");
            sb.append('=');
            sb.append(((this.workflowUrl == null)?"<null>":this.workflowUrl));
            sb.append(',');
            sb.append("additionalProperties");
            sb.append('=');
            sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
            sb.append(',');
            if (sb.charAt((sb.length()- 1)) == ',') {
                sb.setCharAt((sb.length()- 1), ']');
            } else {
                sb.append(']');
            }
            return sb.toString();
        }

        @Override
        public int hashCode() {
            int result = 1;
            result = ((result* 31)+((this.headRepository == null)? 0 :this.headRepository.hashCode()));
            result = ((result* 31)+((this.headBranch == null)? 0 :this.headBranch.hashCode()));
            result = ((result* 31)+((this.headSha == null)? 0 :this.headSha.hashCode()));
            result = ((result* 31)+((this.repository == null)? 0 :this.repository.hashCode()));
            result = ((result* 31)+((this.headCommit == null)? 0 :this.headCommit.hashCode()));
            result = ((result* 31)+((this.conclusion == null)? 0 :this.conclusion.hashCode()));
            result = ((result* 31)+((this.rerunUrl == null)? 0 :this.rerunUrl.hashCode()));
            result = ((result* 31)+((this.createdAt == null)? 0 :this.createdAt.hashCode()));
            result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
            result = ((result* 31)+((this.event == null)? 0 :this.event.hashCode()));
            result = ((result* 31)+((this.updatedAt == null)? 0 :this.updatedAt.hashCode()));
            result = ((result* 31)+((this.jobsUrl == null)? 0 :this.jobsUrl.hashCode()));
            result = ((result* 31)+((this.htmlUrl == null)? 0 :this.htmlUrl.hashCode()));
            result = ((result* 31)+((this.workflowUrl == null)? 0 :this.workflowUrl.hashCode()));
            result = ((result* 31)+((this.logsUrl == null)? 0 :this.logsUrl.hashCode()));
            result = ((result* 31)+((this.pullRequests == null)? 0 :this.pullRequests.hashCode()));
            result = ((result* 31)+((this.checkSuiteNodeId == null)? 0 :this.checkSuiteNodeId.hashCode()));
            result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
            result = ((result* 31)+((this.artifactsUrl == null)? 0 :this.artifactsUrl.hashCode()));
            result = ((result* 31)+((this.cancelUrl == null)? 0 :this.cancelUrl.hashCode()));
            result = ((result* 31)+((this.checkSuiteUrl == null)? 0 :this.checkSuiteUrl.hashCode()));
            result = ((result* 31)+((this.checkSuiteId == null)? 0 :this.checkSuiteId.hashCode()));
            result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
            result = ((result* 31)+((this.runNumber == null)? 0 :this.runNumber.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.nodeId == null)? 0 :this.nodeId.hashCode()));
            result = ((result* 31)+((this.workflowId == null)? 0 :this.workflowId.hashCode()));
            result = ((result* 31)+((this.status == null)? 0 :this.status.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof WorkflowRunGitHubEvent.WorkflowRun) == false) {
                return false;
            }
            WorkflowRunGitHubEvent.WorkflowRun rhs = ((WorkflowRunGitHubEvent.WorkflowRun) other);
            return (((((((((((((((((((((((((((((this.headRepository == rhs.headRepository)||((this.headRepository!= null)&&this.headRepository.equals(rhs.headRepository)))&&((this.headBranch == rhs.headBranch)||((this.headBranch!= null)&&this.headBranch.equals(rhs.headBranch))))&&((this.headSha == rhs.headSha)||((this.headSha!= null)&&this.headSha.equals(rhs.headSha))))&&((this.repository == rhs.repository)||((this.repository!= null)&&this.repository.equals(rhs.repository))))&&((this.headCommit == rhs.headCommit)||((this.headCommit!= null)&&this.headCommit.equals(rhs.headCommit))))&&((this.conclusion == rhs.conclusion)||((this.conclusion!= null)&&this.conclusion.equals(rhs.conclusion))))&&((this.rerunUrl == rhs.rerunUrl)||((this.rerunUrl!= null)&&this.rerunUrl.equals(rhs.rerunUrl))))&&((this.createdAt == rhs.createdAt)||((this.createdAt!= null)&&this.createdAt.equals(rhs.createdAt))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.event == rhs.event)||((this.event!= null)&&this.event.equals(rhs.event))))&&((this.updatedAt == rhs.updatedAt)||((this.updatedAt!= null)&&this.updatedAt.equals(rhs.updatedAt))))&&((this.jobsUrl == rhs.jobsUrl)||((this.jobsUrl!= null)&&this.jobsUrl.equals(rhs.jobsUrl))))&&((this.htmlUrl == rhs.htmlUrl)||((this.htmlUrl!= null)&&this.htmlUrl.equals(rhs.htmlUrl))))&&((this.workflowUrl == rhs.workflowUrl)||((this.workflowUrl!= null)&&this.workflowUrl.equals(rhs.workflowUrl))))&&((this.logsUrl == rhs.logsUrl)||((this.logsUrl!= null)&&this.logsUrl.equals(rhs.logsUrl))))&&((this.pullRequests == rhs.pullRequests)||((this.pullRequests!= null)&&this.pullRequests.equals(rhs.pullRequests))))&&((this.checkSuiteNodeId == rhs.checkSuiteNodeId)||((this.checkSuiteNodeId!= null)&&this.checkSuiteNodeId.equals(rhs.checkSuiteNodeId))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))))&&((this.artifactsUrl == rhs.artifactsUrl)||((this.artifactsUrl!= null)&&this.artifactsUrl.equals(rhs.artifactsUrl))))&&((this.cancelUrl == rhs.cancelUrl)||((this.cancelUrl!= null)&&this.cancelUrl.equals(rhs.cancelUrl))))&&((this.checkSuiteUrl == rhs.checkSuiteUrl)||((this.checkSuiteUrl!= null)&&this.checkSuiteUrl.equals(rhs.checkSuiteUrl))))&&((this.checkSuiteId == rhs.checkSuiteId)||((this.checkSuiteId!= null)&&this.checkSuiteId.equals(rhs.checkSuiteId))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.runNumber == rhs.runNumber)||((this.runNumber!= null)&&this.runNumber.equals(rhs.runNumber))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.nodeId == rhs.nodeId)||((this.nodeId!= null)&&this.nodeId.equals(rhs.nodeId))))&&((this.workflowId == rhs.workflowId)||((this.workflowId!= null)&&this.workflowId.equals(rhs.workflowId))))&&((this.status == rhs.status)||((this.status!= null)&&this.status.equals(rhs.status))));
        }

    }

}
