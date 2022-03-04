
package com.bytelegend.app.jsonmodel.generated.response;

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
    "url",
    "id",
    "node_id",
    "html_url",
    "diff_url",
    "patch_url",
    "issue_url",
    "commits_url",
    "review_comments_url",
    "review_comment_url",
    "comments_url",
    "statuses_url",
    "number",
    "state",
    "locked",
    "title",
    "user",
    "body",
    "labels",
    "milestone",
    "active_lock_reason",
    "created_at",
    "updated_at",
    "closed_at",
    "merged_at",
    "merge_commit_sha",
    "assignee",
    "assignees",
    "requested_reviewers",
    "requested_teams",
    "head",
    "base",
    "_links",
    "author_association",
    "auto_merge",
    "draft",
    "merged",
    "mergeable",
    "rebaseable",
    "mergeable_state",
    "merged_by",
    "comments",
    "review_comments",
    "maintainer_can_modify",
    "commits",
    "additions",
    "deletions",
    "changed_files"
})
@Generated("jsonschema2pojo")
public class GetPullRequestResponse {

    @JsonProperty("url")
    private String url;
    @JsonProperty("id")
    private Long id;
    @JsonProperty("node_id")
    private String nodeId;
    @JsonProperty("html_url")
    private String htmlUrl;
    @JsonProperty("diff_url")
    private String diffUrl;
    @JsonProperty("patch_url")
    private String patchUrl;
    @JsonProperty("issue_url")
    private String issueUrl;
    @JsonProperty("commits_url")
    private String commitsUrl;
    @JsonProperty("review_comments_url")
    private String reviewCommentsUrl;
    @JsonProperty("review_comment_url")
    private String reviewCommentUrl;
    @JsonProperty("comments_url")
    private String commentsUrl;
    @JsonProperty("statuses_url")
    private String statusesUrl;
    @JsonProperty("number")
    private Long number;
    @JsonProperty("state")
    private String state;
    @JsonProperty("locked")
    private Boolean locked;
    @JsonProperty("title")
    private String title;
    @JsonProperty("user")
    private GetPullRequestResponse.User user;
    @JsonProperty("body")
    private String body;
    @JsonProperty("labels")
    private List<GetPullRequestResponse.Label> labels = new ArrayList<GetPullRequestResponse.Label>();
    @JsonProperty("milestone")
    private GetPullRequestResponse.Milestone milestone;
    @JsonProperty("active_lock_reason")
    private String activeLockReason;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("closed_at")
    private String closedAt;
    @JsonProperty("merged_at")
    private String mergedAt;
    @JsonProperty("merge_commit_sha")
    private String mergeCommitSha;
    @JsonProperty("assignee")
    private GetPullRequestResponse.Assignee assignee;
    @JsonProperty("assignees")
    private List<GetPullRequestResponse.Assignee2> assignees = new ArrayList<GetPullRequestResponse.Assignee2>();
    @JsonProperty("requested_reviewers")
    private List<GetPullRequestResponse.RequestedReviewer> requestedReviewers = new ArrayList<GetPullRequestResponse.RequestedReviewer>();
    @JsonProperty("requested_teams")
    private List<GetPullRequestResponse.RequestedTeam> requestedTeams = new ArrayList<GetPullRequestResponse.RequestedTeam>();
    @JsonProperty("head")
    private GetPullRequestResponse.Head head;
    @JsonProperty("base")
    private GetPullRequestResponse.Base base;
    @JsonProperty("_links")
    private GetPullRequestResponse.Links links;
    @JsonProperty("author_association")
    private String authorAssociation;
    @JsonProperty("auto_merge")
    private Object autoMerge;
    @JsonProperty("draft")
    private Boolean draft;
    @JsonProperty("merged")
    private Boolean merged;
    @JsonProperty("mergeable")
    private Boolean mergeable;
    @JsonProperty("rebaseable")
    private Boolean rebaseable;
    @JsonProperty("mergeable_state")
    private String mergeableState;
    @JsonProperty("merged_by")
    private GetPullRequestResponse.MergedBy mergedBy;
    @JsonProperty("comments")
    private Long comments;
    @JsonProperty("review_comments")
    private Long reviewComments;
    @JsonProperty("maintainer_can_modify")
    private Boolean maintainerCanModify;
    @JsonProperty("commits")
    private Long commits;
    @JsonProperty("additions")
    private Long additions;
    @JsonProperty("deletions")
    private Long deletions;
    @JsonProperty("changed_files")
    private Long changedFiles;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public GetPullRequestResponse() {
    }

    /**
     * 
     * @param requestedReviewers
     * @param mergeableState
     * @param diffUrl
     * @param rebaseable
     * @param reviewCommentUrl
     * @param requestedTeams
     * @param deletions
     * @param autoMerge
     * @param reviewCommentsUrl
     * @param assignees
     * @param commitsUrl
     * @param title
     * @param body
     * @param reviewComments
     * @param head
     * @param number
     * @param createdAt
     * @param patchUrl
     * @param mergeable
     * @param changedFiles
     * @param mergeCommitSha
     * @param draft
     * @param activeLockReason
     * @param statusesUrl
     * @param links
     * @param id
     * @param state
     * @param locked
     * @param closedAt
     * @param authorAssociation
     * @param updatedAt
     * @param comments
     * @param additions
     * @param mergedAt
     * @param htmlUrl
     * @param merged
     * @param maintainerCanModify
     * @param url
     * @param labels
     * @param issueUrl
     * @param commentsUrl
     * @param milestone
     * @param commits
     * @param assignee
     * @param mergedBy
     * @param nodeId
     * @param user
     * @param base
     */
    public GetPullRequestResponse(String url, Long id, String nodeId, String htmlUrl, String diffUrl, String patchUrl, String issueUrl, String commitsUrl, String reviewCommentsUrl, String reviewCommentUrl, String commentsUrl, String statusesUrl, Long number, String state, Boolean locked, String title, GetPullRequestResponse.User user, String body, List<GetPullRequestResponse.Label> labels, GetPullRequestResponse.Milestone milestone, String activeLockReason, String createdAt, String updatedAt, String closedAt, String mergedAt, String mergeCommitSha, GetPullRequestResponse.Assignee assignee, List<GetPullRequestResponse.Assignee2> assignees, List<GetPullRequestResponse.RequestedReviewer> requestedReviewers, List<GetPullRequestResponse.RequestedTeam> requestedTeams, GetPullRequestResponse.Head head, GetPullRequestResponse.Base base, GetPullRequestResponse.Links links, String authorAssociation, Object autoMerge, Boolean draft, Boolean merged, Boolean mergeable, Boolean rebaseable, String mergeableState, GetPullRequestResponse.MergedBy mergedBy, Long comments, Long reviewComments, Boolean maintainerCanModify, Long commits, Long additions, Long deletions, Long changedFiles) {
        super();
        this.url = url;
        this.id = id;
        this.nodeId = nodeId;
        this.htmlUrl = htmlUrl;
        this.diffUrl = diffUrl;
        this.patchUrl = patchUrl;
        this.issueUrl = issueUrl;
        this.commitsUrl = commitsUrl;
        this.reviewCommentsUrl = reviewCommentsUrl;
        this.reviewCommentUrl = reviewCommentUrl;
        this.commentsUrl = commentsUrl;
        this.statusesUrl = statusesUrl;
        this.number = number;
        this.state = state;
        this.locked = locked;
        this.title = title;
        this.user = user;
        this.body = body;
        this.labels = labels;
        this.milestone = milestone;
        this.activeLockReason = activeLockReason;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.closedAt = closedAt;
        this.mergedAt = mergedAt;
        this.mergeCommitSha = mergeCommitSha;
        this.assignee = assignee;
        this.assignees = assignees;
        this.requestedReviewers = requestedReviewers;
        this.requestedTeams = requestedTeams;
        this.head = head;
        this.base = base;
        this.links = links;
        this.authorAssociation = authorAssociation;
        this.autoMerge = autoMerge;
        this.draft = draft;
        this.merged = merged;
        this.mergeable = mergeable;
        this.rebaseable = rebaseable;
        this.mergeableState = mergeableState;
        this.mergedBy = mergedBy;
        this.comments = comments;
        this.reviewComments = reviewComments;
        this.maintainerCanModify = maintainerCanModify;
        this.commits = commits;
        this.additions = additions;
        this.deletions = deletions;
        this.changedFiles = changedFiles;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("node_id")
    public String getNodeId() {
        return nodeId;
    }

    @JsonProperty("node_id")
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    @JsonProperty("html_url")
    public String getHtmlUrl() {
        return htmlUrl;
    }

    @JsonProperty("html_url")
    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    @JsonProperty("diff_url")
    public String getDiffUrl() {
        return diffUrl;
    }

    @JsonProperty("diff_url")
    public void setDiffUrl(String diffUrl) {
        this.diffUrl = diffUrl;
    }

    @JsonProperty("patch_url")
    public String getPatchUrl() {
        return patchUrl;
    }

    @JsonProperty("patch_url")
    public void setPatchUrl(String patchUrl) {
        this.patchUrl = patchUrl;
    }

    @JsonProperty("issue_url")
    public String getIssueUrl() {
        return issueUrl;
    }

    @JsonProperty("issue_url")
    public void setIssueUrl(String issueUrl) {
        this.issueUrl = issueUrl;
    }

    @JsonProperty("commits_url")
    public String getCommitsUrl() {
        return commitsUrl;
    }

    @JsonProperty("commits_url")
    public void setCommitsUrl(String commitsUrl) {
        this.commitsUrl = commitsUrl;
    }

    @JsonProperty("review_comments_url")
    public String getReviewCommentsUrl() {
        return reviewCommentsUrl;
    }

    @JsonProperty("review_comments_url")
    public void setReviewCommentsUrl(String reviewCommentsUrl) {
        this.reviewCommentsUrl = reviewCommentsUrl;
    }

    @JsonProperty("review_comment_url")
    public String getReviewCommentUrl() {
        return reviewCommentUrl;
    }

    @JsonProperty("review_comment_url")
    public void setReviewCommentUrl(String reviewCommentUrl) {
        this.reviewCommentUrl = reviewCommentUrl;
    }

    @JsonProperty("comments_url")
    public String getCommentsUrl() {
        return commentsUrl;
    }

    @JsonProperty("comments_url")
    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }

    @JsonProperty("statuses_url")
    public String getStatusesUrl() {
        return statusesUrl;
    }

    @JsonProperty("statuses_url")
    public void setStatusesUrl(String statusesUrl) {
        this.statusesUrl = statusesUrl;
    }

    @JsonProperty("number")
    public Long getNumber() {
        return number;
    }

    @JsonProperty("number")
    public void setNumber(Long number) {
        this.number = number;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("locked")
    public Boolean getLocked() {
        return locked;
    }

    @JsonProperty("locked")
    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("user")
    public GetPullRequestResponse.User getUser() {
        return user;
    }

    @JsonProperty("user")
    public void setUser(GetPullRequestResponse.User user) {
        this.user = user;
    }

    @JsonProperty("body")
    public String getBody() {
        return body;
    }

    @JsonProperty("body")
    public void setBody(String body) {
        this.body = body;
    }

    @JsonProperty("labels")
    public List<GetPullRequestResponse.Label> getLabels() {
        return labels;
    }

    @JsonProperty("labels")
    public void setLabels(List<GetPullRequestResponse.Label> labels) {
        this.labels = labels;
    }

    @JsonProperty("milestone")
    public GetPullRequestResponse.Milestone getMilestone() {
        return milestone;
    }

    @JsonProperty("milestone")
    public void setMilestone(GetPullRequestResponse.Milestone milestone) {
        this.milestone = milestone;
    }

    @JsonProperty("active_lock_reason")
    public String getActiveLockReason() {
        return activeLockReason;
    }

    @JsonProperty("active_lock_reason")
    public void setActiveLockReason(String activeLockReason) {
        this.activeLockReason = activeLockReason;
    }

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("updated_at")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("closed_at")
    public String getClosedAt() {
        return closedAt;
    }

    @JsonProperty("closed_at")
    public void setClosedAt(String closedAt) {
        this.closedAt = closedAt;
    }

    @JsonProperty("merged_at")
    public String getMergedAt() {
        return mergedAt;
    }

    @JsonProperty("merged_at")
    public void setMergedAt(String mergedAt) {
        this.mergedAt = mergedAt;
    }

    @JsonProperty("merge_commit_sha")
    public String getMergeCommitSha() {
        return mergeCommitSha;
    }

    @JsonProperty("merge_commit_sha")
    public void setMergeCommitSha(String mergeCommitSha) {
        this.mergeCommitSha = mergeCommitSha;
    }

    @JsonProperty("assignee")
    public GetPullRequestResponse.Assignee getAssignee() {
        return assignee;
    }

    @JsonProperty("assignee")
    public void setAssignee(GetPullRequestResponse.Assignee assignee) {
        this.assignee = assignee;
    }

    @JsonProperty("assignees")
    public List<GetPullRequestResponse.Assignee2> getAssignees() {
        return assignees;
    }

    @JsonProperty("assignees")
    public void setAssignees(List<GetPullRequestResponse.Assignee2> assignees) {
        this.assignees = assignees;
    }

    @JsonProperty("requested_reviewers")
    public List<GetPullRequestResponse.RequestedReviewer> getRequestedReviewers() {
        return requestedReviewers;
    }

    @JsonProperty("requested_reviewers")
    public void setRequestedReviewers(List<GetPullRequestResponse.RequestedReviewer> requestedReviewers) {
        this.requestedReviewers = requestedReviewers;
    }

    @JsonProperty("requested_teams")
    public List<GetPullRequestResponse.RequestedTeam> getRequestedTeams() {
        return requestedTeams;
    }

    @JsonProperty("requested_teams")
    public void setRequestedTeams(List<GetPullRequestResponse.RequestedTeam> requestedTeams) {
        this.requestedTeams = requestedTeams;
    }

    @JsonProperty("head")
    public GetPullRequestResponse.Head getHead() {
        return head;
    }

    @JsonProperty("head")
    public void setHead(GetPullRequestResponse.Head head) {
        this.head = head;
    }

    @JsonProperty("base")
    public GetPullRequestResponse.Base getBase() {
        return base;
    }

    @JsonProperty("base")
    public void setBase(GetPullRequestResponse.Base base) {
        this.base = base;
    }

    @JsonProperty("_links")
    public GetPullRequestResponse.Links getLinks() {
        return links;
    }

    @JsonProperty("_links")
    public void setLinks(GetPullRequestResponse.Links links) {
        this.links = links;
    }

    @JsonProperty("author_association")
    public String getAuthorAssociation() {
        return authorAssociation;
    }

    @JsonProperty("author_association")
    public void setAuthorAssociation(String authorAssociation) {
        this.authorAssociation = authorAssociation;
    }

    @JsonProperty("auto_merge")
    public Object getAutoMerge() {
        return autoMerge;
    }

    @JsonProperty("auto_merge")
    public void setAutoMerge(Object autoMerge) {
        this.autoMerge = autoMerge;
    }

    @JsonProperty("draft")
    public Boolean getDraft() {
        return draft;
    }

    @JsonProperty("draft")
    public void setDraft(Boolean draft) {
        this.draft = draft;
    }

    @JsonProperty("merged")
    public Boolean getMerged() {
        return merged;
    }

    @JsonProperty("merged")
    public void setMerged(Boolean merged) {
        this.merged = merged;
    }

    @JsonProperty("mergeable")
    public Boolean getMergeable() {
        return mergeable;
    }

    @JsonProperty("mergeable")
    public void setMergeable(Boolean mergeable) {
        this.mergeable = mergeable;
    }

    @JsonProperty("rebaseable")
    public Boolean getRebaseable() {
        return rebaseable;
    }

    @JsonProperty("rebaseable")
    public void setRebaseable(Boolean rebaseable) {
        this.rebaseable = rebaseable;
    }

    @JsonProperty("mergeable_state")
    public String getMergeableState() {
        return mergeableState;
    }

    @JsonProperty("mergeable_state")
    public void setMergeableState(String mergeableState) {
        this.mergeableState = mergeableState;
    }

    @JsonProperty("merged_by")
    public GetPullRequestResponse.MergedBy getMergedBy() {
        return mergedBy;
    }

    @JsonProperty("merged_by")
    public void setMergedBy(GetPullRequestResponse.MergedBy mergedBy) {
        this.mergedBy = mergedBy;
    }

    @JsonProperty("comments")
    public Long getComments() {
        return comments;
    }

    @JsonProperty("comments")
    public void setComments(Long comments) {
        this.comments = comments;
    }

    @JsonProperty("review_comments")
    public Long getReviewComments() {
        return reviewComments;
    }

    @JsonProperty("review_comments")
    public void setReviewComments(Long reviewComments) {
        this.reviewComments = reviewComments;
    }

    @JsonProperty("maintainer_can_modify")
    public Boolean getMaintainerCanModify() {
        return maintainerCanModify;
    }

    @JsonProperty("maintainer_can_modify")
    public void setMaintainerCanModify(Boolean maintainerCanModify) {
        this.maintainerCanModify = maintainerCanModify;
    }

    @JsonProperty("commits")
    public Long getCommits() {
        return commits;
    }

    @JsonProperty("commits")
    public void setCommits(Long commits) {
        this.commits = commits;
    }

    @JsonProperty("additions")
    public Long getAdditions() {
        return additions;
    }

    @JsonProperty("additions")
    public void setAdditions(Long additions) {
        this.additions = additions;
    }

    @JsonProperty("deletions")
    public Long getDeletions() {
        return deletions;
    }

    @JsonProperty("deletions")
    public void setDeletions(Long deletions) {
        this.deletions = deletions;
    }

    @JsonProperty("changed_files")
    public Long getChangedFiles() {
        return changedFiles;
    }

    @JsonProperty("changed_files")
    public void setChangedFiles(Long changedFiles) {
        this.changedFiles = changedFiles;
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
        sb.append(GetPullRequestResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("url");
        sb.append('=');
        sb.append(((this.url == null)?"<null>":this.url));
        sb.append(',');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("nodeId");
        sb.append('=');
        sb.append(((this.nodeId == null)?"<null>":this.nodeId));
        sb.append(',');
        sb.append("htmlUrl");
        sb.append('=');
        sb.append(((this.htmlUrl == null)?"<null>":this.htmlUrl));
        sb.append(',');
        sb.append("diffUrl");
        sb.append('=');
        sb.append(((this.diffUrl == null)?"<null>":this.diffUrl));
        sb.append(',');
        sb.append("patchUrl");
        sb.append('=');
        sb.append(((this.patchUrl == null)?"<null>":this.patchUrl));
        sb.append(',');
        sb.append("issueUrl");
        sb.append('=');
        sb.append(((this.issueUrl == null)?"<null>":this.issueUrl));
        sb.append(',');
        sb.append("commitsUrl");
        sb.append('=');
        sb.append(((this.commitsUrl == null)?"<null>":this.commitsUrl));
        sb.append(',');
        sb.append("reviewCommentsUrl");
        sb.append('=');
        sb.append(((this.reviewCommentsUrl == null)?"<null>":this.reviewCommentsUrl));
        sb.append(',');
        sb.append("reviewCommentUrl");
        sb.append('=');
        sb.append(((this.reviewCommentUrl == null)?"<null>":this.reviewCommentUrl));
        sb.append(',');
        sb.append("commentsUrl");
        sb.append('=');
        sb.append(((this.commentsUrl == null)?"<null>":this.commentsUrl));
        sb.append(',');
        sb.append("statusesUrl");
        sb.append('=');
        sb.append(((this.statusesUrl == null)?"<null>":this.statusesUrl));
        sb.append(',');
        sb.append("number");
        sb.append('=');
        sb.append(((this.number == null)?"<null>":this.number));
        sb.append(',');
        sb.append("state");
        sb.append('=');
        sb.append(((this.state == null)?"<null>":this.state));
        sb.append(',');
        sb.append("locked");
        sb.append('=');
        sb.append(((this.locked == null)?"<null>":this.locked));
        sb.append(',');
        sb.append("title");
        sb.append('=');
        sb.append(((this.title == null)?"<null>":this.title));
        sb.append(',');
        sb.append("user");
        sb.append('=');
        sb.append(((this.user == null)?"<null>":this.user));
        sb.append(',');
        sb.append("body");
        sb.append('=');
        sb.append(((this.body == null)?"<null>":this.body));
        sb.append(',');
        sb.append("labels");
        sb.append('=');
        sb.append(((this.labels == null)?"<null>":this.labels));
        sb.append(',');
        sb.append("milestone");
        sb.append('=');
        sb.append(((this.milestone == null)?"<null>":this.milestone));
        sb.append(',');
        sb.append("activeLockReason");
        sb.append('=');
        sb.append(((this.activeLockReason == null)?"<null>":this.activeLockReason));
        sb.append(',');
        sb.append("createdAt");
        sb.append('=');
        sb.append(((this.createdAt == null)?"<null>":this.createdAt));
        sb.append(',');
        sb.append("updatedAt");
        sb.append('=');
        sb.append(((this.updatedAt == null)?"<null>":this.updatedAt));
        sb.append(',');
        sb.append("closedAt");
        sb.append('=');
        sb.append(((this.closedAt == null)?"<null>":this.closedAt));
        sb.append(',');
        sb.append("mergedAt");
        sb.append('=');
        sb.append(((this.mergedAt == null)?"<null>":this.mergedAt));
        sb.append(',');
        sb.append("mergeCommitSha");
        sb.append('=');
        sb.append(((this.mergeCommitSha == null)?"<null>":this.mergeCommitSha));
        sb.append(',');
        sb.append("assignee");
        sb.append('=');
        sb.append(((this.assignee == null)?"<null>":this.assignee));
        sb.append(',');
        sb.append("assignees");
        sb.append('=');
        sb.append(((this.assignees == null)?"<null>":this.assignees));
        sb.append(',');
        sb.append("requestedReviewers");
        sb.append('=');
        sb.append(((this.requestedReviewers == null)?"<null>":this.requestedReviewers));
        sb.append(',');
        sb.append("requestedTeams");
        sb.append('=');
        sb.append(((this.requestedTeams == null)?"<null>":this.requestedTeams));
        sb.append(',');
        sb.append("head");
        sb.append('=');
        sb.append(((this.head == null)?"<null>":this.head));
        sb.append(',');
        sb.append("base");
        sb.append('=');
        sb.append(((this.base == null)?"<null>":this.base));
        sb.append(',');
        sb.append("links");
        sb.append('=');
        sb.append(((this.links == null)?"<null>":this.links));
        sb.append(',');
        sb.append("authorAssociation");
        sb.append('=');
        sb.append(((this.authorAssociation == null)?"<null>":this.authorAssociation));
        sb.append(',');
        sb.append("autoMerge");
        sb.append('=');
        sb.append(((this.autoMerge == null)?"<null>":this.autoMerge));
        sb.append(',');
        sb.append("draft");
        sb.append('=');
        sb.append(((this.draft == null)?"<null>":this.draft));
        sb.append(',');
        sb.append("merged");
        sb.append('=');
        sb.append(((this.merged == null)?"<null>":this.merged));
        sb.append(',');
        sb.append("mergeable");
        sb.append('=');
        sb.append(((this.mergeable == null)?"<null>":this.mergeable));
        sb.append(',');
        sb.append("rebaseable");
        sb.append('=');
        sb.append(((this.rebaseable == null)?"<null>":this.rebaseable));
        sb.append(',');
        sb.append("mergeableState");
        sb.append('=');
        sb.append(((this.mergeableState == null)?"<null>":this.mergeableState));
        sb.append(',');
        sb.append("mergedBy");
        sb.append('=');
        sb.append(((this.mergedBy == null)?"<null>":this.mergedBy));
        sb.append(',');
        sb.append("comments");
        sb.append('=');
        sb.append(((this.comments == null)?"<null>":this.comments));
        sb.append(',');
        sb.append("reviewComments");
        sb.append('=');
        sb.append(((this.reviewComments == null)?"<null>":this.reviewComments));
        sb.append(',');
        sb.append("maintainerCanModify");
        sb.append('=');
        sb.append(((this.maintainerCanModify == null)?"<null>":this.maintainerCanModify));
        sb.append(',');
        sb.append("commits");
        sb.append('=');
        sb.append(((this.commits == null)?"<null>":this.commits));
        sb.append(',');
        sb.append("additions");
        sb.append('=');
        sb.append(((this.additions == null)?"<null>":this.additions));
        sb.append(',');
        sb.append("deletions");
        sb.append('=');
        sb.append(((this.deletions == null)?"<null>":this.deletions));
        sb.append(',');
        sb.append("changedFiles");
        sb.append('=');
        sb.append(((this.changedFiles == null)?"<null>":this.changedFiles));
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
        result = ((result* 31)+((this.requestedReviewers == null)? 0 :this.requestedReviewers.hashCode()));
        result = ((result* 31)+((this.mergeableState == null)? 0 :this.mergeableState.hashCode()));
        result = ((result* 31)+((this.requestedTeams == null)? 0 :this.requestedTeams.hashCode()));
        result = ((result* 31)+((this.reviewCommentsUrl == null)? 0 :this.reviewCommentsUrl.hashCode()));
        result = ((result* 31)+((this.assignees == null)? 0 :this.assignees.hashCode()));
        result = ((result* 31)+((this.commitsUrl == null)? 0 :this.commitsUrl.hashCode()));
        result = ((result* 31)+((this.body == null)? 0 :this.body.hashCode()));
        result = ((result* 31)+((this.reviewComments == null)? 0 :this.reviewComments.hashCode()));
        result = ((result* 31)+((this.number == null)? 0 :this.number.hashCode()));
        result = ((result* 31)+((this.createdAt == null)? 0 :this.createdAt.hashCode()));
        result = ((result* 31)+((this.mergeable == null)? 0 :this.mergeable.hashCode()));
        result = ((result* 31)+((this.draft == null)? 0 :this.draft.hashCode()));
        result = ((result* 31)+((this.activeLockReason == null)? 0 :this.activeLockReason.hashCode()));
        result = ((result* 31)+((this.statusesUrl == null)? 0 :this.statusesUrl.hashCode()));
        result = ((result* 31)+((this.links == null)? 0 :this.links.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.state == null)? 0 :this.state.hashCode()));
        result = ((result* 31)+((this.locked == null)? 0 :this.locked.hashCode()));
        result = ((result* 31)+((this.closedAt == null)? 0 :this.closedAt.hashCode()));
        result = ((result* 31)+((this.updatedAt == null)? 0 :this.updatedAt.hashCode()));
        result = ((result* 31)+((this.labels == null)? 0 :this.labels.hashCode()));
        result = ((result* 31)+((this.commentsUrl == null)? 0 :this.commentsUrl.hashCode()));
        result = ((result* 31)+((this.commits == null)? 0 :this.commits.hashCode()));
        result = ((result* 31)+((this.assignee == null)? 0 :this.assignee.hashCode()));
        result = ((result* 31)+((this.mergedBy == null)? 0 :this.mergedBy.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.nodeId == null)? 0 :this.nodeId.hashCode()));
        result = ((result* 31)+((this.diffUrl == null)? 0 :this.diffUrl.hashCode()));
        result = ((result* 31)+((this.rebaseable == null)? 0 :this.rebaseable.hashCode()));
        result = ((result* 31)+((this.reviewCommentUrl == null)? 0 :this.reviewCommentUrl.hashCode()));
        result = ((result* 31)+((this.deletions == null)? 0 :this.deletions.hashCode()));
        result = ((result* 31)+((this.autoMerge == null)? 0 :this.autoMerge.hashCode()));
        result = ((result* 31)+((this.title == null)? 0 :this.title.hashCode()));
        result = ((result* 31)+((this.head == null)? 0 :this.head.hashCode()));
        result = ((result* 31)+((this.patchUrl == null)? 0 :this.patchUrl.hashCode()));
        result = ((result* 31)+((this.changedFiles == null)? 0 :this.changedFiles.hashCode()));
        result = ((result* 31)+((this.mergeCommitSha == null)? 0 :this.mergeCommitSha.hashCode()));
        result = ((result* 31)+((this.authorAssociation == null)? 0 :this.authorAssociation.hashCode()));
        result = ((result* 31)+((this.comments == null)? 0 :this.comments.hashCode()));
        result = ((result* 31)+((this.additions == null)? 0 :this.additions.hashCode()));
        result = ((result* 31)+((this.mergedAt == null)? 0 :this.mergedAt.hashCode()));
        result = ((result* 31)+((this.htmlUrl == null)? 0 :this.htmlUrl.hashCode()));
        result = ((result* 31)+((this.merged == null)? 0 :this.merged.hashCode()));
        result = ((result* 31)+((this.maintainerCanModify == null)? 0 :this.maintainerCanModify.hashCode()));
        result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
        result = ((result* 31)+((this.issueUrl == null)? 0 :this.issueUrl.hashCode()));
        result = ((result* 31)+((this.milestone == null)? 0 :this.milestone.hashCode()));
        result = ((result* 31)+((this.user == null)? 0 :this.user.hashCode()));
        result = ((result* 31)+((this.base == null)? 0 :this.base.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof GetPullRequestResponse) == false) {
            return false;
        }
        GetPullRequestResponse rhs = ((GetPullRequestResponse) other);
        return ((((((((((((((((((((((((((((((((((((((((((((((((((this.requestedReviewers == rhs.requestedReviewers)||((this.requestedReviewers!= null)&&this.requestedReviewers.equals(rhs.requestedReviewers)))&&((this.mergeableState == rhs.mergeableState)||((this.mergeableState!= null)&&this.mergeableState.equals(rhs.mergeableState))))&&((this.requestedTeams == rhs.requestedTeams)||((this.requestedTeams!= null)&&this.requestedTeams.equals(rhs.requestedTeams))))&&((this.reviewCommentsUrl == rhs.reviewCommentsUrl)||((this.reviewCommentsUrl!= null)&&this.reviewCommentsUrl.equals(rhs.reviewCommentsUrl))))&&((this.assignees == rhs.assignees)||((this.assignees!= null)&&this.assignees.equals(rhs.assignees))))&&((this.commitsUrl == rhs.commitsUrl)||((this.commitsUrl!= null)&&this.commitsUrl.equals(rhs.commitsUrl))))&&((this.body == rhs.body)||((this.body!= null)&&this.body.equals(rhs.body))))&&((this.reviewComments == rhs.reviewComments)||((this.reviewComments!= null)&&this.reviewComments.equals(rhs.reviewComments))))&&((this.number == rhs.number)||((this.number!= null)&&this.number.equals(rhs.number))))&&((this.createdAt == rhs.createdAt)||((this.createdAt!= null)&&this.createdAt.equals(rhs.createdAt))))&&((this.mergeable == rhs.mergeable)||((this.mergeable!= null)&&this.mergeable.equals(rhs.mergeable))))&&((this.draft == rhs.draft)||((this.draft!= null)&&this.draft.equals(rhs.draft))))&&((this.activeLockReason == rhs.activeLockReason)||((this.activeLockReason!= null)&&this.activeLockReason.equals(rhs.activeLockReason))))&&((this.statusesUrl == rhs.statusesUrl)||((this.statusesUrl!= null)&&this.statusesUrl.equals(rhs.statusesUrl))))&&((this.links == rhs.links)||((this.links!= null)&&this.links.equals(rhs.links))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.state == rhs.state)||((this.state!= null)&&this.state.equals(rhs.state))))&&((this.locked == rhs.locked)||((this.locked!= null)&&this.locked.equals(rhs.locked))))&&((this.closedAt == rhs.closedAt)||((this.closedAt!= null)&&this.closedAt.equals(rhs.closedAt))))&&((this.updatedAt == rhs.updatedAt)||((this.updatedAt!= null)&&this.updatedAt.equals(rhs.updatedAt))))&&((this.labels == rhs.labels)||((this.labels!= null)&&this.labels.equals(rhs.labels))))&&((this.commentsUrl == rhs.commentsUrl)||((this.commentsUrl!= null)&&this.commentsUrl.equals(rhs.commentsUrl))))&&((this.commits == rhs.commits)||((this.commits!= null)&&this.commits.equals(rhs.commits))))&&((this.assignee == rhs.assignee)||((this.assignee!= null)&&this.assignee.equals(rhs.assignee))))&&((this.mergedBy == rhs.mergedBy)||((this.mergedBy!= null)&&this.mergedBy.equals(rhs.mergedBy))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.nodeId == rhs.nodeId)||((this.nodeId!= null)&&this.nodeId.equals(rhs.nodeId))))&&((this.diffUrl == rhs.diffUrl)||((this.diffUrl!= null)&&this.diffUrl.equals(rhs.diffUrl))))&&((this.rebaseable == rhs.rebaseable)||((this.rebaseable!= null)&&this.rebaseable.equals(rhs.rebaseable))))&&((this.reviewCommentUrl == rhs.reviewCommentUrl)||((this.reviewCommentUrl!= null)&&this.reviewCommentUrl.equals(rhs.reviewCommentUrl))))&&((this.deletions == rhs.deletions)||((this.deletions!= null)&&this.deletions.equals(rhs.deletions))))&&((this.autoMerge == rhs.autoMerge)||((this.autoMerge!= null)&&this.autoMerge.equals(rhs.autoMerge))))&&((this.title == rhs.title)||((this.title!= null)&&this.title.equals(rhs.title))))&&((this.head == rhs.head)||((this.head!= null)&&this.head.equals(rhs.head))))&&((this.patchUrl == rhs.patchUrl)||((this.patchUrl!= null)&&this.patchUrl.equals(rhs.patchUrl))))&&((this.changedFiles == rhs.changedFiles)||((this.changedFiles!= null)&&this.changedFiles.equals(rhs.changedFiles))))&&((this.mergeCommitSha == rhs.mergeCommitSha)||((this.mergeCommitSha!= null)&&this.mergeCommitSha.equals(rhs.mergeCommitSha))))&&((this.authorAssociation == rhs.authorAssociation)||((this.authorAssociation!= null)&&this.authorAssociation.equals(rhs.authorAssociation))))&&((this.comments == rhs.comments)||((this.comments!= null)&&this.comments.equals(rhs.comments))))&&((this.additions == rhs.additions)||((this.additions!= null)&&this.additions.equals(rhs.additions))))&&((this.mergedAt == rhs.mergedAt)||((this.mergedAt!= null)&&this.mergedAt.equals(rhs.mergedAt))))&&((this.htmlUrl == rhs.htmlUrl)||((this.htmlUrl!= null)&&this.htmlUrl.equals(rhs.htmlUrl))))&&((this.merged == rhs.merged)||((this.merged!= null)&&this.merged.equals(rhs.merged))))&&((this.maintainerCanModify == rhs.maintainerCanModify)||((this.maintainerCanModify!= null)&&this.maintainerCanModify.equals(rhs.maintainerCanModify))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))))&&((this.issueUrl == rhs.issueUrl)||((this.issueUrl!= null)&&this.issueUrl.equals(rhs.issueUrl))))&&((this.milestone == rhs.milestone)||((this.milestone!= null)&&this.milestone.equals(rhs.milestone))))&&((this.user == rhs.user)||((this.user!= null)&&this.user.equals(rhs.user))))&&((this.base == rhs.base)||((this.base!= null)&&this.base.equals(rhs.base))));
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "login",
        "id",
        "node_id",
        "avatar_url",
        "gravatar_id",
        "url",
        "html_url",
        "followers_url",
        "following_url",
        "gists_url",
        "starred_url",
        "subscriptions_url",
        "organizations_url",
        "repos_url",
        "events_url",
        "received_events_url",
        "type",
        "site_admin"
    })
    @Generated("jsonschema2pojo")
    public static class Assignee {

        @JsonProperty("login")
        private String login;
        @JsonProperty("id")
        private Long id;
        @JsonProperty("node_id")
        private String nodeId;
        @JsonProperty("avatar_url")
        private String avatarUrl;
        @JsonProperty("gravatar_id")
        private String gravatarId;
        @JsonProperty("url")
        private String url;
        @JsonProperty("html_url")
        private String htmlUrl;
        @JsonProperty("followers_url")
        private String followersUrl;
        @JsonProperty("following_url")
        private String followingUrl;
        @JsonProperty("gists_url")
        private String gistsUrl;
        @JsonProperty("starred_url")
        private String starredUrl;
        @JsonProperty("subscriptions_url")
        private String subscriptionsUrl;
        @JsonProperty("organizations_url")
        private String organizationsUrl;
        @JsonProperty("repos_url")
        private String reposUrl;
        @JsonProperty("events_url")
        private String eventsUrl;
        @JsonProperty("received_events_url")
        private String receivedEventsUrl;
        @JsonProperty("type")
        private String type;
        @JsonProperty("site_admin")
        private Boolean siteAdmin;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Assignee() {
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
         * @param url
         * @param starredUrl
         * @param gravatarId
         * @param followersUrl
         * @param id
         * @param eventsUrl
         * @param nodeId
         */
        public Assignee(String login, Long id, String nodeId, String avatarUrl, String gravatarId, String url, String htmlUrl, String followersUrl, String followingUrl, String gistsUrl, String starredUrl, String subscriptionsUrl, String organizationsUrl, String reposUrl, String eventsUrl, String receivedEventsUrl, String type, Boolean siteAdmin) {
            super();
            this.login = login;
            this.id = id;
            this.nodeId = nodeId;
            this.avatarUrl = avatarUrl;
            this.gravatarId = gravatarId;
            this.url = url;
            this.htmlUrl = htmlUrl;
            this.followersUrl = followersUrl;
            this.followingUrl = followingUrl;
            this.gistsUrl = gistsUrl;
            this.starredUrl = starredUrl;
            this.subscriptionsUrl = subscriptionsUrl;
            this.organizationsUrl = organizationsUrl;
            this.reposUrl = reposUrl;
            this.eventsUrl = eventsUrl;
            this.receivedEventsUrl = receivedEventsUrl;
            this.type = type;
            this.siteAdmin = siteAdmin;
        }

        @JsonProperty("login")
        public String getLogin() {
            return login;
        }

        @JsonProperty("login")
        public void setLogin(String login) {
            this.login = login;
        }

        @JsonProperty("id")
        public Long getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(Long id) {
            this.id = id;
        }

        @JsonProperty("node_id")
        public String getNodeId() {
            return nodeId;
        }

        @JsonProperty("node_id")
        public void setNodeId(String nodeId) {
            this.nodeId = nodeId;
        }

        @JsonProperty("avatar_url")
        public String getAvatarUrl() {
            return avatarUrl;
        }

        @JsonProperty("avatar_url")
        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        @JsonProperty("gravatar_id")
        public String getGravatarId() {
            return gravatarId;
        }

        @JsonProperty("gravatar_id")
        public void setGravatarId(String gravatarId) {
            this.gravatarId = gravatarId;
        }

        @JsonProperty("url")
        public String getUrl() {
            return url;
        }

        @JsonProperty("url")
        public void setUrl(String url) {
            this.url = url;
        }

        @JsonProperty("html_url")
        public String getHtmlUrl() {
            return htmlUrl;
        }

        @JsonProperty("html_url")
        public void setHtmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
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

        @JsonProperty("organizations_url")
        public String getOrganizationsUrl() {
            return organizationsUrl;
        }

        @JsonProperty("organizations_url")
        public void setOrganizationsUrl(String organizationsUrl) {
            this.organizationsUrl = organizationsUrl;
        }

        @JsonProperty("repos_url")
        public String getReposUrl() {
            return reposUrl;
        }

        @JsonProperty("repos_url")
        public void setReposUrl(String reposUrl) {
            this.reposUrl = reposUrl;
        }

        @JsonProperty("events_url")
        public String getEventsUrl() {
            return eventsUrl;
        }

        @JsonProperty("events_url")
        public void setEventsUrl(String eventsUrl) {
            this.eventsUrl = eventsUrl;
        }

        @JsonProperty("received_events_url")
        public String getReceivedEventsUrl() {
            return receivedEventsUrl;
        }

        @JsonProperty("received_events_url")
        public void setReceivedEventsUrl(String receivedEventsUrl) {
            this.receivedEventsUrl = receivedEventsUrl;
        }

        @JsonProperty("type")
        public String getType() {
            return type;
        }

        @JsonProperty("type")
        public void setType(String type) {
            this.type = type;
        }

        @JsonProperty("site_admin")
        public Boolean getSiteAdmin() {
            return siteAdmin;
        }

        @JsonProperty("site_admin")
        public void setSiteAdmin(Boolean siteAdmin) {
            this.siteAdmin = siteAdmin;
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
            sb.append(GetPullRequestResponse.Assignee.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("login");
            sb.append('=');
            sb.append(((this.login == null)?"<null>":this.login));
            sb.append(',');
            sb.append("id");
            sb.append('=');
            sb.append(((this.id == null)?"<null>":this.id));
            sb.append(',');
            sb.append("nodeId");
            sb.append('=');
            sb.append(((this.nodeId == null)?"<null>":this.nodeId));
            sb.append(',');
            sb.append("avatarUrl");
            sb.append('=');
            sb.append(((this.avatarUrl == null)?"<null>":this.avatarUrl));
            sb.append(',');
            sb.append("gravatarId");
            sb.append('=');
            sb.append(((this.gravatarId == null)?"<null>":this.gravatarId));
            sb.append(',');
            sb.append("url");
            sb.append('=');
            sb.append(((this.url == null)?"<null>":this.url));
            sb.append(',');
            sb.append("htmlUrl");
            sb.append('=');
            sb.append(((this.htmlUrl == null)?"<null>":this.htmlUrl));
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
            sb.append("starredUrl");
            sb.append('=');
            sb.append(((this.starredUrl == null)?"<null>":this.starredUrl));
            sb.append(',');
            sb.append("subscriptionsUrl");
            sb.append('=');
            sb.append(((this.subscriptionsUrl == null)?"<null>":this.subscriptionsUrl));
            sb.append(',');
            sb.append("organizationsUrl");
            sb.append('=');
            sb.append(((this.organizationsUrl == null)?"<null>":this.organizationsUrl));
            sb.append(',');
            sb.append("reposUrl");
            sb.append('=');
            sb.append(((this.reposUrl == null)?"<null>":this.reposUrl));
            sb.append(',');
            sb.append("eventsUrl");
            sb.append('=');
            sb.append(((this.eventsUrl == null)?"<null>":this.eventsUrl));
            sb.append(',');
            sb.append("receivedEventsUrl");
            sb.append('=');
            sb.append(((this.receivedEventsUrl == null)?"<null>":this.receivedEventsUrl));
            sb.append(',');
            sb.append("type");
            sb.append('=');
            sb.append(((this.type == null)?"<null>":this.type));
            sb.append(',');
            sb.append("siteAdmin");
            sb.append('=');
            sb.append(((this.siteAdmin == null)?"<null>":this.siteAdmin));
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
            result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
            result = ((result* 31)+((this.starredUrl == null)? 0 :this.starredUrl.hashCode()));
            result = ((result* 31)+((this.gravatarId == null)? 0 :this.gravatarId.hashCode()));
            result = ((result* 31)+((this.followersUrl == null)? 0 :this.followersUrl.hashCode()));
            result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
            result = ((result* 31)+((this.eventsUrl == null)? 0 :this.eventsUrl.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.nodeId == null)? 0 :this.nodeId.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof GetPullRequestResponse.Assignee) == false) {
                return false;
            }
            GetPullRequestResponse.Assignee rhs = ((GetPullRequestResponse.Assignee) other);
            return ((((((((((((((((((((this.receivedEventsUrl == rhs.receivedEventsUrl)||((this.receivedEventsUrl!= null)&&this.receivedEventsUrl.equals(rhs.receivedEventsUrl)))&&((this.siteAdmin == rhs.siteAdmin)||((this.siteAdmin!= null)&&this.siteAdmin.equals(rhs.siteAdmin))))&&((this.followingUrl == rhs.followingUrl)||((this.followingUrl!= null)&&this.followingUrl.equals(rhs.followingUrl))))&&((this.gistsUrl == rhs.gistsUrl)||((this.gistsUrl!= null)&&this.gistsUrl.equals(rhs.gistsUrl))))&&((this.avatarUrl == rhs.avatarUrl)||((this.avatarUrl!= null)&&this.avatarUrl.equals(rhs.avatarUrl))))&&((this.organizationsUrl == rhs.organizationsUrl)||((this.organizationsUrl!= null)&&this.organizationsUrl.equals(rhs.organizationsUrl))))&&((this.reposUrl == rhs.reposUrl)||((this.reposUrl!= null)&&this.reposUrl.equals(rhs.reposUrl))))&&((this.htmlUrl == rhs.htmlUrl)||((this.htmlUrl!= null)&&this.htmlUrl.equals(rhs.htmlUrl))))&&((this.subscriptionsUrl == rhs.subscriptionsUrl)||((this.subscriptionsUrl!= null)&&this.subscriptionsUrl.equals(rhs.subscriptionsUrl))))&&((this.login == rhs.login)||((this.login!= null)&&this.login.equals(rhs.login))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))))&&((this.starredUrl == rhs.starredUrl)||((this.starredUrl!= null)&&this.starredUrl.equals(rhs.starredUrl))))&&((this.gravatarId == rhs.gravatarId)||((this.gravatarId!= null)&&this.gravatarId.equals(rhs.gravatarId))))&&((this.followersUrl == rhs.followersUrl)||((this.followersUrl!= null)&&this.followersUrl.equals(rhs.followersUrl))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.eventsUrl == rhs.eventsUrl)||((this.eventsUrl!= null)&&this.eventsUrl.equals(rhs.eventsUrl))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.nodeId == rhs.nodeId)||((this.nodeId!= null)&&this.nodeId.equals(rhs.nodeId))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "login",
        "id",
        "node_id",
        "avatar_url",
        "gravatar_id",
        "url",
        "html_url",
        "followers_url",
        "following_url",
        "gists_url",
        "starred_url",
        "subscriptions_url",
        "organizations_url",
        "repos_url",
        "events_url",
        "received_events_url",
        "type",
        "site_admin"
    })
    @Generated("jsonschema2pojo")
    public static class Assignee2 {

        @JsonProperty("login")
        private String login;
        @JsonProperty("id")
        private Long id;
        @JsonProperty("node_id")
        private String nodeId;
        @JsonProperty("avatar_url")
        private String avatarUrl;
        @JsonProperty("gravatar_id")
        private String gravatarId;
        @JsonProperty("url")
        private String url;
        @JsonProperty("html_url")
        private String htmlUrl;
        @JsonProperty("followers_url")
        private String followersUrl;
        @JsonProperty("following_url")
        private String followingUrl;
        @JsonProperty("gists_url")
        private String gistsUrl;
        @JsonProperty("starred_url")
        private String starredUrl;
        @JsonProperty("subscriptions_url")
        private String subscriptionsUrl;
        @JsonProperty("organizations_url")
        private String organizationsUrl;
        @JsonProperty("repos_url")
        private String reposUrl;
        @JsonProperty("events_url")
        private String eventsUrl;
        @JsonProperty("received_events_url")
        private String receivedEventsUrl;
        @JsonProperty("type")
        private String type;
        @JsonProperty("site_admin")
        private Boolean siteAdmin;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Assignee2() {
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
         * @param url
         * @param starredUrl
         * @param gravatarId
         * @param followersUrl
         * @param id
         * @param eventsUrl
         * @param nodeId
         */
        public Assignee2(String login, Long id, String nodeId, String avatarUrl, String gravatarId, String url, String htmlUrl, String followersUrl, String followingUrl, String gistsUrl, String starredUrl, String subscriptionsUrl, String organizationsUrl, String reposUrl, String eventsUrl, String receivedEventsUrl, String type, Boolean siteAdmin) {
            super();
            this.login = login;
            this.id = id;
            this.nodeId = nodeId;
            this.avatarUrl = avatarUrl;
            this.gravatarId = gravatarId;
            this.url = url;
            this.htmlUrl = htmlUrl;
            this.followersUrl = followersUrl;
            this.followingUrl = followingUrl;
            this.gistsUrl = gistsUrl;
            this.starredUrl = starredUrl;
            this.subscriptionsUrl = subscriptionsUrl;
            this.organizationsUrl = organizationsUrl;
            this.reposUrl = reposUrl;
            this.eventsUrl = eventsUrl;
            this.receivedEventsUrl = receivedEventsUrl;
            this.type = type;
            this.siteAdmin = siteAdmin;
        }

        @JsonProperty("login")
        public String getLogin() {
            return login;
        }

        @JsonProperty("login")
        public void setLogin(String login) {
            this.login = login;
        }

        @JsonProperty("id")
        public Long getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(Long id) {
            this.id = id;
        }

        @JsonProperty("node_id")
        public String getNodeId() {
            return nodeId;
        }

        @JsonProperty("node_id")
        public void setNodeId(String nodeId) {
            this.nodeId = nodeId;
        }

        @JsonProperty("avatar_url")
        public String getAvatarUrl() {
            return avatarUrl;
        }

        @JsonProperty("avatar_url")
        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        @JsonProperty("gravatar_id")
        public String getGravatarId() {
            return gravatarId;
        }

        @JsonProperty("gravatar_id")
        public void setGravatarId(String gravatarId) {
            this.gravatarId = gravatarId;
        }

        @JsonProperty("url")
        public String getUrl() {
            return url;
        }

        @JsonProperty("url")
        public void setUrl(String url) {
            this.url = url;
        }

        @JsonProperty("html_url")
        public String getHtmlUrl() {
            return htmlUrl;
        }

        @JsonProperty("html_url")
        public void setHtmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
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

        @JsonProperty("organizations_url")
        public String getOrganizationsUrl() {
            return organizationsUrl;
        }

        @JsonProperty("organizations_url")
        public void setOrganizationsUrl(String organizationsUrl) {
            this.organizationsUrl = organizationsUrl;
        }

        @JsonProperty("repos_url")
        public String getReposUrl() {
            return reposUrl;
        }

        @JsonProperty("repos_url")
        public void setReposUrl(String reposUrl) {
            this.reposUrl = reposUrl;
        }

        @JsonProperty("events_url")
        public String getEventsUrl() {
            return eventsUrl;
        }

        @JsonProperty("events_url")
        public void setEventsUrl(String eventsUrl) {
            this.eventsUrl = eventsUrl;
        }

        @JsonProperty("received_events_url")
        public String getReceivedEventsUrl() {
            return receivedEventsUrl;
        }

        @JsonProperty("received_events_url")
        public void setReceivedEventsUrl(String receivedEventsUrl) {
            this.receivedEventsUrl = receivedEventsUrl;
        }

        @JsonProperty("type")
        public String getType() {
            return type;
        }

        @JsonProperty("type")
        public void setType(String type) {
            this.type = type;
        }

        @JsonProperty("site_admin")
        public Boolean getSiteAdmin() {
            return siteAdmin;
        }

        @JsonProperty("site_admin")
        public void setSiteAdmin(Boolean siteAdmin) {
            this.siteAdmin = siteAdmin;
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
            sb.append(GetPullRequestResponse.Assignee2 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("login");
            sb.append('=');
            sb.append(((this.login == null)?"<null>":this.login));
            sb.append(',');
            sb.append("id");
            sb.append('=');
            sb.append(((this.id == null)?"<null>":this.id));
            sb.append(',');
            sb.append("nodeId");
            sb.append('=');
            sb.append(((this.nodeId == null)?"<null>":this.nodeId));
            sb.append(',');
            sb.append("avatarUrl");
            sb.append('=');
            sb.append(((this.avatarUrl == null)?"<null>":this.avatarUrl));
            sb.append(',');
            sb.append("gravatarId");
            sb.append('=');
            sb.append(((this.gravatarId == null)?"<null>":this.gravatarId));
            sb.append(',');
            sb.append("url");
            sb.append('=');
            sb.append(((this.url == null)?"<null>":this.url));
            sb.append(',');
            sb.append("htmlUrl");
            sb.append('=');
            sb.append(((this.htmlUrl == null)?"<null>":this.htmlUrl));
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
            sb.append("starredUrl");
            sb.append('=');
            sb.append(((this.starredUrl == null)?"<null>":this.starredUrl));
            sb.append(',');
            sb.append("subscriptionsUrl");
            sb.append('=');
            sb.append(((this.subscriptionsUrl == null)?"<null>":this.subscriptionsUrl));
            sb.append(',');
            sb.append("organizationsUrl");
            sb.append('=');
            sb.append(((this.organizationsUrl == null)?"<null>":this.organizationsUrl));
            sb.append(',');
            sb.append("reposUrl");
            sb.append('=');
            sb.append(((this.reposUrl == null)?"<null>":this.reposUrl));
            sb.append(',');
            sb.append("eventsUrl");
            sb.append('=');
            sb.append(((this.eventsUrl == null)?"<null>":this.eventsUrl));
            sb.append(',');
            sb.append("receivedEventsUrl");
            sb.append('=');
            sb.append(((this.receivedEventsUrl == null)?"<null>":this.receivedEventsUrl));
            sb.append(',');
            sb.append("type");
            sb.append('=');
            sb.append(((this.type == null)?"<null>":this.type));
            sb.append(',');
            sb.append("siteAdmin");
            sb.append('=');
            sb.append(((this.siteAdmin == null)?"<null>":this.siteAdmin));
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
            result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
            result = ((result* 31)+((this.starredUrl == null)? 0 :this.starredUrl.hashCode()));
            result = ((result* 31)+((this.gravatarId == null)? 0 :this.gravatarId.hashCode()));
            result = ((result* 31)+((this.followersUrl == null)? 0 :this.followersUrl.hashCode()));
            result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
            result = ((result* 31)+((this.eventsUrl == null)? 0 :this.eventsUrl.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.nodeId == null)? 0 :this.nodeId.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof GetPullRequestResponse.Assignee2) == false) {
                return false;
            }
            GetPullRequestResponse.Assignee2 rhs = ((GetPullRequestResponse.Assignee2) other);
            return ((((((((((((((((((((this.receivedEventsUrl == rhs.receivedEventsUrl)||((this.receivedEventsUrl!= null)&&this.receivedEventsUrl.equals(rhs.receivedEventsUrl)))&&((this.siteAdmin == rhs.siteAdmin)||((this.siteAdmin!= null)&&this.siteAdmin.equals(rhs.siteAdmin))))&&((this.followingUrl == rhs.followingUrl)||((this.followingUrl!= null)&&this.followingUrl.equals(rhs.followingUrl))))&&((this.gistsUrl == rhs.gistsUrl)||((this.gistsUrl!= null)&&this.gistsUrl.equals(rhs.gistsUrl))))&&((this.avatarUrl == rhs.avatarUrl)||((this.avatarUrl!= null)&&this.avatarUrl.equals(rhs.avatarUrl))))&&((this.organizationsUrl == rhs.organizationsUrl)||((this.organizationsUrl!= null)&&this.organizationsUrl.equals(rhs.organizationsUrl))))&&((this.reposUrl == rhs.reposUrl)||((this.reposUrl!= null)&&this.reposUrl.equals(rhs.reposUrl))))&&((this.htmlUrl == rhs.htmlUrl)||((this.htmlUrl!= null)&&this.htmlUrl.equals(rhs.htmlUrl))))&&((this.subscriptionsUrl == rhs.subscriptionsUrl)||((this.subscriptionsUrl!= null)&&this.subscriptionsUrl.equals(rhs.subscriptionsUrl))))&&((this.login == rhs.login)||((this.login!= null)&&this.login.equals(rhs.login))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))))&&((this.starredUrl == rhs.starredUrl)||((this.starredUrl!= null)&&this.starredUrl.equals(rhs.starredUrl))))&&((this.gravatarId == rhs.gravatarId)||((this.gravatarId!= null)&&this.gravatarId.equals(rhs.gravatarId))))&&((this.followersUrl == rhs.followersUrl)||((this.followersUrl!= null)&&this.followersUrl.equals(rhs.followersUrl))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.eventsUrl == rhs.eventsUrl)||((this.eventsUrl!= null)&&this.eventsUrl.equals(rhs.eventsUrl))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.nodeId == rhs.nodeId)||((this.nodeId!= null)&&this.nodeId.equals(rhs.nodeId))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "label",
        "ref",
        "sha",
        "user",
        "repo"
    })
    @Generated("jsonschema2pojo")
    public static class Base {

        @JsonProperty("label")
        private String label;
        @JsonProperty("ref")
        private String ref;
        @JsonProperty("sha")
        private String sha;
        @JsonProperty("user")
        private GetPullRequestResponse.User3 user;
        @JsonProperty("repo")
        private GetPullRequestResponse.Repo2 repo;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Base() {
        }

        /**
         * 
         * @param ref
         * @param repo
         * @param label
         * @param sha
         * @param user
         */
        public Base(String label, String ref, String sha, GetPullRequestResponse.User3 user, GetPullRequestResponse.Repo2 repo) {
            super();
            this.label = label;
            this.ref = ref;
            this.sha = sha;
            this.user = user;
            this.repo = repo;
        }

        @JsonProperty("label")
        public String getLabel() {
            return label;
        }

        @JsonProperty("label")
        public void setLabel(String label) {
            this.label = label;
        }

        @JsonProperty("ref")
        public String getRef() {
            return ref;
        }

        @JsonProperty("ref")
        public void setRef(String ref) {
            this.ref = ref;
        }

        @JsonProperty("sha")
        public String getSha() {
            return sha;
        }

        @JsonProperty("sha")
        public void setSha(String sha) {
            this.sha = sha;
        }

        @JsonProperty("user")
        public GetPullRequestResponse.User3 getUser() {
            return user;
        }

        @JsonProperty("user")
        public void setUser(GetPullRequestResponse.User3 user) {
            this.user = user;
        }

        @JsonProperty("repo")
        public GetPullRequestResponse.Repo2 getRepo() {
            return repo;
        }

        @JsonProperty("repo")
        public void setRepo(GetPullRequestResponse.Repo2 repo) {
            this.repo = repo;
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
            sb.append(GetPullRequestResponse.Base.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("label");
            sb.append('=');
            sb.append(((this.label == null)?"<null>":this.label));
            sb.append(',');
            sb.append("ref");
            sb.append('=');
            sb.append(((this.ref == null)?"<null>":this.ref));
            sb.append(',');
            sb.append("sha");
            sb.append('=');
            sb.append(((this.sha == null)?"<null>":this.sha));
            sb.append(',');
            sb.append("user");
            sb.append('=');
            sb.append(((this.user == null)?"<null>":this.user));
            sb.append(',');
            sb.append("repo");
            sb.append('=');
            sb.append(((this.repo == null)?"<null>":this.repo));
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
            result = ((result* 31)+((this.ref == null)? 0 :this.ref.hashCode()));
            result = ((result* 31)+((this.repo == null)? 0 :this.repo.hashCode()));
            result = ((result* 31)+((this.label == null)? 0 :this.label.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.sha == null)? 0 :this.sha.hashCode()));
            result = ((result* 31)+((this.user == null)? 0 :this.user.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof GetPullRequestResponse.Base) == false) {
                return false;
            }
            GetPullRequestResponse.Base rhs = ((GetPullRequestResponse.Base) other);
            return (((((((this.ref == rhs.ref)||((this.ref!= null)&&this.ref.equals(rhs.ref)))&&((this.repo == rhs.repo)||((this.repo!= null)&&this.repo.equals(rhs.repo))))&&((this.label == rhs.label)||((this.label!= null)&&this.label.equals(rhs.label))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.sha == rhs.sha)||((this.sha!= null)&&this.sha.equals(rhs.sha))))&&((this.user == rhs.user)||((this.user!= null)&&this.user.equals(rhs.user))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "href"
    })
    @Generated("jsonschema2pojo")
    public static class Comments {

        @JsonProperty("href")
        private String href;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Comments() {
        }

        /**
         * 
         * @param href
         */
        public Comments(String href) {
            super();
            this.href = href;
        }

        @JsonProperty("href")
        public String getHref() {
            return href;
        }

        @JsonProperty("href")
        public void setHref(String href) {
            this.href = href;
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
            sb.append(GetPullRequestResponse.Comments.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("href");
            sb.append('=');
            sb.append(((this.href == null)?"<null>":this.href));
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
            result = ((result* 31)+((this.href == null)? 0 :this.href.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof GetPullRequestResponse.Comments) == false) {
                return false;
            }
            GetPullRequestResponse.Comments rhs = ((GetPullRequestResponse.Comments) other);
            return (((this.href == rhs.href)||((this.href!= null)&&this.href.equals(rhs.href)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "href"
    })
    @Generated("jsonschema2pojo")
    public static class Commits {

        @JsonProperty("href")
        private String href;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Commits() {
        }

        /**
         * 
         * @param href
         */
        public Commits(String href) {
            super();
            this.href = href;
        }

        @JsonProperty("href")
        public String getHref() {
            return href;
        }

        @JsonProperty("href")
        public void setHref(String href) {
            this.href = href;
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
            sb.append(GetPullRequestResponse.Commits.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("href");
            sb.append('=');
            sb.append(((this.href == null)?"<null>":this.href));
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
            result = ((result* 31)+((this.href == null)? 0 :this.href.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof GetPullRequestResponse.Commits) == false) {
                return false;
            }
            GetPullRequestResponse.Commits rhs = ((GetPullRequestResponse.Commits) other);
            return (((this.href == rhs.href)||((this.href!= null)&&this.href.equals(rhs.href)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "login",
        "id",
        "node_id",
        "avatar_url",
        "gravatar_id",
        "url",
        "html_url",
        "followers_url",
        "following_url",
        "gists_url",
        "starred_url",
        "subscriptions_url",
        "organizations_url",
        "repos_url",
        "events_url",
        "received_events_url",
        "type",
        "site_admin"
    })
    @Generated("jsonschema2pojo")
    public static class Creator {

        @JsonProperty("login")
        private String login;
        @JsonProperty("id")
        private Long id;
        @JsonProperty("node_id")
        private String nodeId;
        @JsonProperty("avatar_url")
        private String avatarUrl;
        @JsonProperty("gravatar_id")
        private String gravatarId;
        @JsonProperty("url")
        private String url;
        @JsonProperty("html_url")
        private String htmlUrl;
        @JsonProperty("followers_url")
        private String followersUrl;
        @JsonProperty("following_url")
        private String followingUrl;
        @JsonProperty("gists_url")
        private String gistsUrl;
        @JsonProperty("starred_url")
        private String starredUrl;
        @JsonProperty("subscriptions_url")
        private String subscriptionsUrl;
        @JsonProperty("organizations_url")
        private String organizationsUrl;
        @JsonProperty("repos_url")
        private String reposUrl;
        @JsonProperty("events_url")
        private String eventsUrl;
        @JsonProperty("received_events_url")
        private String receivedEventsUrl;
        @JsonProperty("type")
        private String type;
        @JsonProperty("site_admin")
        private Boolean siteAdmin;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Creator() {
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
         * @param url
         * @param starredUrl
         * @param gravatarId
         * @param followersUrl
         * @param id
         * @param eventsUrl
         * @param nodeId
         */
        public Creator(String login, Long id, String nodeId, String avatarUrl, String gravatarId, String url, String htmlUrl, String followersUrl, String followingUrl, String gistsUrl, String starredUrl, String subscriptionsUrl, String organizationsUrl, String reposUrl, String eventsUrl, String receivedEventsUrl, String type, Boolean siteAdmin) {
            super();
            this.login = login;
            this.id = id;
            this.nodeId = nodeId;
            this.avatarUrl = avatarUrl;
            this.gravatarId = gravatarId;
            this.url = url;
            this.htmlUrl = htmlUrl;
            this.followersUrl = followersUrl;
            this.followingUrl = followingUrl;
            this.gistsUrl = gistsUrl;
            this.starredUrl = starredUrl;
            this.subscriptionsUrl = subscriptionsUrl;
            this.organizationsUrl = organizationsUrl;
            this.reposUrl = reposUrl;
            this.eventsUrl = eventsUrl;
            this.receivedEventsUrl = receivedEventsUrl;
            this.type = type;
            this.siteAdmin = siteAdmin;
        }

        @JsonProperty("login")
        public String getLogin() {
            return login;
        }

        @JsonProperty("login")
        public void setLogin(String login) {
            this.login = login;
        }

        @JsonProperty("id")
        public Long getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(Long id) {
            this.id = id;
        }

        @JsonProperty("node_id")
        public String getNodeId() {
            return nodeId;
        }

        @JsonProperty("node_id")
        public void setNodeId(String nodeId) {
            this.nodeId = nodeId;
        }

        @JsonProperty("avatar_url")
        public String getAvatarUrl() {
            return avatarUrl;
        }

        @JsonProperty("avatar_url")
        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        @JsonProperty("gravatar_id")
        public String getGravatarId() {
            return gravatarId;
        }

        @JsonProperty("gravatar_id")
        public void setGravatarId(String gravatarId) {
            this.gravatarId = gravatarId;
        }

        @JsonProperty("url")
        public String getUrl() {
            return url;
        }

        @JsonProperty("url")
        public void setUrl(String url) {
            this.url = url;
        }

        @JsonProperty("html_url")
        public String getHtmlUrl() {
            return htmlUrl;
        }

        @JsonProperty("html_url")
        public void setHtmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
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

        @JsonProperty("organizations_url")
        public String getOrganizationsUrl() {
            return organizationsUrl;
        }

        @JsonProperty("organizations_url")
        public void setOrganizationsUrl(String organizationsUrl) {
            this.organizationsUrl = organizationsUrl;
        }

        @JsonProperty("repos_url")
        public String getReposUrl() {
            return reposUrl;
        }

        @JsonProperty("repos_url")
        public void setReposUrl(String reposUrl) {
            this.reposUrl = reposUrl;
        }

        @JsonProperty("events_url")
        public String getEventsUrl() {
            return eventsUrl;
        }

        @JsonProperty("events_url")
        public void setEventsUrl(String eventsUrl) {
            this.eventsUrl = eventsUrl;
        }

        @JsonProperty("received_events_url")
        public String getReceivedEventsUrl() {
            return receivedEventsUrl;
        }

        @JsonProperty("received_events_url")
        public void setReceivedEventsUrl(String receivedEventsUrl) {
            this.receivedEventsUrl = receivedEventsUrl;
        }

        @JsonProperty("type")
        public String getType() {
            return type;
        }

        @JsonProperty("type")
        public void setType(String type) {
            this.type = type;
        }

        @JsonProperty("site_admin")
        public Boolean getSiteAdmin() {
            return siteAdmin;
        }

        @JsonProperty("site_admin")
        public void setSiteAdmin(Boolean siteAdmin) {
            this.siteAdmin = siteAdmin;
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
            sb.append(GetPullRequestResponse.Creator.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("login");
            sb.append('=');
            sb.append(((this.login == null)?"<null>":this.login));
            sb.append(',');
            sb.append("id");
            sb.append('=');
            sb.append(((this.id == null)?"<null>":this.id));
            sb.append(',');
            sb.append("nodeId");
            sb.append('=');
            sb.append(((this.nodeId == null)?"<null>":this.nodeId));
            sb.append(',');
            sb.append("avatarUrl");
            sb.append('=');
            sb.append(((this.avatarUrl == null)?"<null>":this.avatarUrl));
            sb.append(',');
            sb.append("gravatarId");
            sb.append('=');
            sb.append(((this.gravatarId == null)?"<null>":this.gravatarId));
            sb.append(',');
            sb.append("url");
            sb.append('=');
            sb.append(((this.url == null)?"<null>":this.url));
            sb.append(',');
            sb.append("htmlUrl");
            sb.append('=');
            sb.append(((this.htmlUrl == null)?"<null>":this.htmlUrl));
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
            sb.append("starredUrl");
            sb.append('=');
            sb.append(((this.starredUrl == null)?"<null>":this.starredUrl));
            sb.append(',');
            sb.append("subscriptionsUrl");
            sb.append('=');
            sb.append(((this.subscriptionsUrl == null)?"<null>":this.subscriptionsUrl));
            sb.append(',');
            sb.append("organizationsUrl");
            sb.append('=');
            sb.append(((this.organizationsUrl == null)?"<null>":this.organizationsUrl));
            sb.append(',');
            sb.append("reposUrl");
            sb.append('=');
            sb.append(((this.reposUrl == null)?"<null>":this.reposUrl));
            sb.append(',');
            sb.append("eventsUrl");
            sb.append('=');
            sb.append(((this.eventsUrl == null)?"<null>":this.eventsUrl));
            sb.append(',');
            sb.append("receivedEventsUrl");
            sb.append('=');
            sb.append(((this.receivedEventsUrl == null)?"<null>":this.receivedEventsUrl));
            sb.append(',');
            sb.append("type");
            sb.append('=');
            sb.append(((this.type == null)?"<null>":this.type));
            sb.append(',');
            sb.append("siteAdmin");
            sb.append('=');
            sb.append(((this.siteAdmin == null)?"<null>":this.siteAdmin));
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
            result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
            result = ((result* 31)+((this.starredUrl == null)? 0 :this.starredUrl.hashCode()));
            result = ((result* 31)+((this.gravatarId == null)? 0 :this.gravatarId.hashCode()));
            result = ((result* 31)+((this.followersUrl == null)? 0 :this.followersUrl.hashCode()));
            result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
            result = ((result* 31)+((this.eventsUrl == null)? 0 :this.eventsUrl.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.nodeId == null)? 0 :this.nodeId.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof GetPullRequestResponse.Creator) == false) {
                return false;
            }
            GetPullRequestResponse.Creator rhs = ((GetPullRequestResponse.Creator) other);
            return ((((((((((((((((((((this.receivedEventsUrl == rhs.receivedEventsUrl)||((this.receivedEventsUrl!= null)&&this.receivedEventsUrl.equals(rhs.receivedEventsUrl)))&&((this.siteAdmin == rhs.siteAdmin)||((this.siteAdmin!= null)&&this.siteAdmin.equals(rhs.siteAdmin))))&&((this.followingUrl == rhs.followingUrl)||((this.followingUrl!= null)&&this.followingUrl.equals(rhs.followingUrl))))&&((this.gistsUrl == rhs.gistsUrl)||((this.gistsUrl!= null)&&this.gistsUrl.equals(rhs.gistsUrl))))&&((this.avatarUrl == rhs.avatarUrl)||((this.avatarUrl!= null)&&this.avatarUrl.equals(rhs.avatarUrl))))&&((this.organizationsUrl == rhs.organizationsUrl)||((this.organizationsUrl!= null)&&this.organizationsUrl.equals(rhs.organizationsUrl))))&&((this.reposUrl == rhs.reposUrl)||((this.reposUrl!= null)&&this.reposUrl.equals(rhs.reposUrl))))&&((this.htmlUrl == rhs.htmlUrl)||((this.htmlUrl!= null)&&this.htmlUrl.equals(rhs.htmlUrl))))&&((this.subscriptionsUrl == rhs.subscriptionsUrl)||((this.subscriptionsUrl!= null)&&this.subscriptionsUrl.equals(rhs.subscriptionsUrl))))&&((this.login == rhs.login)||((this.login!= null)&&this.login.equals(rhs.login))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))))&&((this.starredUrl == rhs.starredUrl)||((this.starredUrl!= null)&&this.starredUrl.equals(rhs.starredUrl))))&&((this.gravatarId == rhs.gravatarId)||((this.gravatarId!= null)&&this.gravatarId.equals(rhs.gravatarId))))&&((this.followersUrl == rhs.followersUrl)||((this.followersUrl!= null)&&this.followersUrl.equals(rhs.followersUrl))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.eventsUrl == rhs.eventsUrl)||((this.eventsUrl!= null)&&this.eventsUrl.equals(rhs.eventsUrl))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.nodeId == rhs.nodeId)||((this.nodeId!= null)&&this.nodeId.equals(rhs.nodeId))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "label",
        "ref",
        "sha",
        "user",
        "repo"
    })
    @Generated("jsonschema2pojo")
    public static class Head {

        @JsonProperty("label")
        private String label;
        @JsonProperty("ref")
        private String ref;
        @JsonProperty("sha")
        private String sha;
        @JsonProperty("user")
        private GetPullRequestResponse.User2 user;
        @JsonProperty("repo")
        private GetPullRequestResponse.Repo repo;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Head() {
        }

        /**
         * 
         * @param ref
         * @param repo
         * @param label
         * @param sha
         * @param user
         */
        public Head(String label, String ref, String sha, GetPullRequestResponse.User2 user, GetPullRequestResponse.Repo repo) {
            super();
            this.label = label;
            this.ref = ref;
            this.sha = sha;
            this.user = user;
            this.repo = repo;
        }

        @JsonProperty("label")
        public String getLabel() {
            return label;
        }

        @JsonProperty("label")
        public void setLabel(String label) {
            this.label = label;
        }

        @JsonProperty("ref")
        public String getRef() {
            return ref;
        }

        @JsonProperty("ref")
        public void setRef(String ref) {
            this.ref = ref;
        }

        @JsonProperty("sha")
        public String getSha() {
            return sha;
        }

        @JsonProperty("sha")
        public void setSha(String sha) {
            this.sha = sha;
        }

        @JsonProperty("user")
        public GetPullRequestResponse.User2 getUser() {
            return user;
        }

        @JsonProperty("user")
        public void setUser(GetPullRequestResponse.User2 user) {
            this.user = user;
        }

        @JsonProperty("repo")
        public GetPullRequestResponse.Repo getRepo() {
            return repo;
        }

        @JsonProperty("repo")
        public void setRepo(GetPullRequestResponse.Repo repo) {
            this.repo = repo;
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
            sb.append(GetPullRequestResponse.Head.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("label");
            sb.append('=');
            sb.append(((this.label == null)?"<null>":this.label));
            sb.append(',');
            sb.append("ref");
            sb.append('=');
            sb.append(((this.ref == null)?"<null>":this.ref));
            sb.append(',');
            sb.append("sha");
            sb.append('=');
            sb.append(((this.sha == null)?"<null>":this.sha));
            sb.append(',');
            sb.append("user");
            sb.append('=');
            sb.append(((this.user == null)?"<null>":this.user));
            sb.append(',');
            sb.append("repo");
            sb.append('=');
            sb.append(((this.repo == null)?"<null>":this.repo));
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
            result = ((result* 31)+((this.ref == null)? 0 :this.ref.hashCode()));
            result = ((result* 31)+((this.repo == null)? 0 :this.repo.hashCode()));
            result = ((result* 31)+((this.label == null)? 0 :this.label.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.sha == null)? 0 :this.sha.hashCode()));
            result = ((result* 31)+((this.user == null)? 0 :this.user.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof GetPullRequestResponse.Head) == false) {
                return false;
            }
            GetPullRequestResponse.Head rhs = ((GetPullRequestResponse.Head) other);
            return (((((((this.ref == rhs.ref)||((this.ref!= null)&&this.ref.equals(rhs.ref)))&&((this.repo == rhs.repo)||((this.repo!= null)&&this.repo.equals(rhs.repo))))&&((this.label == rhs.label)||((this.label!= null)&&this.label.equals(rhs.label))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.sha == rhs.sha)||((this.sha!= null)&&this.sha.equals(rhs.sha))))&&((this.user == rhs.user)||((this.user!= null)&&this.user.equals(rhs.user))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "href"
    })
    @Generated("jsonschema2pojo")
    public static class Html {

        @JsonProperty("href")
        private String href;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Html() {
        }

        /**
         * 
         * @param href
         */
        public Html(String href) {
            super();
            this.href = href;
        }

        @JsonProperty("href")
        public String getHref() {
            return href;
        }

        @JsonProperty("href")
        public void setHref(String href) {
            this.href = href;
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
            sb.append(GetPullRequestResponse.Html.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("href");
            sb.append('=');
            sb.append(((this.href == null)?"<null>":this.href));
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
            result = ((result* 31)+((this.href == null)? 0 :this.href.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof GetPullRequestResponse.Html) == false) {
                return false;
            }
            GetPullRequestResponse.Html rhs = ((GetPullRequestResponse.Html) other);
            return (((this.href == rhs.href)||((this.href!= null)&&this.href.equals(rhs.href)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "href"
    })
    @Generated("jsonschema2pojo")
    public static class Issue {

        @JsonProperty("href")
        private String href;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Issue() {
        }

        /**
         * 
         * @param href
         */
        public Issue(String href) {
            super();
            this.href = href;
        }

        @JsonProperty("href")
        public String getHref() {
            return href;
        }

        @JsonProperty("href")
        public void setHref(String href) {
            this.href = href;
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
            sb.append(GetPullRequestResponse.Issue.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("href");
            sb.append('=');
            sb.append(((this.href == null)?"<null>":this.href));
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
            result = ((result* 31)+((this.href == null)? 0 :this.href.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof GetPullRequestResponse.Issue) == false) {
                return false;
            }
            GetPullRequestResponse.Issue rhs = ((GetPullRequestResponse.Issue) other);
            return (((this.href == rhs.href)||((this.href!= null)&&this.href.equals(rhs.href)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "id",
        "node_id",
        "url",
        "name",
        "description",
        "color",
        "default"
    })
    @Generated("jsonschema2pojo")
    public static class Label {

        @JsonProperty("id")
        private Long id;
        @JsonProperty("node_id")
        private String nodeId;
        @JsonProperty("url")
        private String url;
        @JsonProperty("name")
        private String name;
        @JsonProperty("description")
        private String description;
        @JsonProperty("color")
        private String color;
        @JsonProperty("default")
        private Boolean _default;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Label() {
        }

        /**
         * 
         * @param _default
         * @param color
         * @param name
         * @param description
         * @param id
         * @param nodeId
         * @param url
         */
        public Label(Long id, String nodeId, String url, String name, String description, String color, Boolean _default) {
            super();
            this.id = id;
            this.nodeId = nodeId;
            this.url = url;
            this.name = name;
            this.description = description;
            this.color = color;
            this._default = _default;
        }

        @JsonProperty("id")
        public Long getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(Long id) {
            this.id = id;
        }

        @JsonProperty("node_id")
        public String getNodeId() {
            return nodeId;
        }

        @JsonProperty("node_id")
        public void setNodeId(String nodeId) {
            this.nodeId = nodeId;
        }

        @JsonProperty("url")
        public String getUrl() {
            return url;
        }

        @JsonProperty("url")
        public void setUrl(String url) {
            this.url = url;
        }

        @JsonProperty("name")
        public String getName() {
            return name;
        }

        @JsonProperty("name")
        public void setName(String name) {
            this.name = name;
        }

        @JsonProperty("description")
        public String getDescription() {
            return description;
        }

        @JsonProperty("description")
        public void setDescription(String description) {
            this.description = description;
        }

        @JsonProperty("color")
        public String getColor() {
            return color;
        }

        @JsonProperty("color")
        public void setColor(String color) {
            this.color = color;
        }

        @JsonProperty("default")
        public Boolean getDefault() {
            return _default;
        }

        @JsonProperty("default")
        public void setDefault(Boolean _default) {
            this._default = _default;
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
            sb.append(GetPullRequestResponse.Label.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("id");
            sb.append('=');
            sb.append(((this.id == null)?"<null>":this.id));
            sb.append(',');
            sb.append("nodeId");
            sb.append('=');
            sb.append(((this.nodeId == null)?"<null>":this.nodeId));
            sb.append(',');
            sb.append("url");
            sb.append('=');
            sb.append(((this.url == null)?"<null>":this.url));
            sb.append(',');
            sb.append("name");
            sb.append('=');
            sb.append(((this.name == null)?"<null>":this.name));
            sb.append(',');
            sb.append("description");
            sb.append('=');
            sb.append(((this.description == null)?"<null>":this.description));
            sb.append(',');
            sb.append("color");
            sb.append('=');
            sb.append(((this.color == null)?"<null>":this.color));
            sb.append(',');
            sb.append("_default");
            sb.append('=');
            sb.append(((this._default == null)?"<null>":this._default));
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
            result = ((result* 31)+((this._default == null)? 0 :this._default.hashCode()));
            result = ((result* 31)+((this.color == null)? 0 :this.color.hashCode()));
            result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
            result = ((result* 31)+((this.description == null)? 0 :this.description.hashCode()));
            result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.nodeId == null)? 0 :this.nodeId.hashCode()));
            result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof GetPullRequestResponse.Label) == false) {
                return false;
            }
            GetPullRequestResponse.Label rhs = ((GetPullRequestResponse.Label) other);
            return (((((((((this._default == rhs._default)||((this._default!= null)&&this._default.equals(rhs._default)))&&((this.color == rhs.color)||((this.color!= null)&&this.color.equals(rhs.color))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.description == rhs.description)||((this.description!= null)&&this.description.equals(rhs.description))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.nodeId == rhs.nodeId)||((this.nodeId!= null)&&this.nodeId.equals(rhs.nodeId))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "key",
        "name",
        "url",
        "spdx_id",
        "node_id"
    })
    @Generated("jsonschema2pojo")
    public static class License {

        @JsonProperty("key")
        private String key;
        @JsonProperty("name")
        private String name;
        @JsonProperty("url")
        private String url;
        @JsonProperty("spdx_id")
        private String spdxId;
        @JsonProperty("node_id")
        private String nodeId;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public License() {
        }

        /**
         * 
         * @param name
         * @param spdxId
         * @param nodeId
         * @param key
         * @param url
         */
        public License(String key, String name, String url, String spdxId, String nodeId) {
            super();
            this.key = key;
            this.name = name;
            this.url = url;
            this.spdxId = spdxId;
            this.nodeId = nodeId;
        }

        @JsonProperty("key")
        public String getKey() {
            return key;
        }

        @JsonProperty("key")
        public void setKey(String key) {
            this.key = key;
        }

        @JsonProperty("name")
        public String getName() {
            return name;
        }

        @JsonProperty("name")
        public void setName(String name) {
            this.name = name;
        }

        @JsonProperty("url")
        public String getUrl() {
            return url;
        }

        @JsonProperty("url")
        public void setUrl(String url) {
            this.url = url;
        }

        @JsonProperty("spdx_id")
        public String getSpdxId() {
            return spdxId;
        }

        @JsonProperty("spdx_id")
        public void setSpdxId(String spdxId) {
            this.spdxId = spdxId;
        }

        @JsonProperty("node_id")
        public String getNodeId() {
            return nodeId;
        }

        @JsonProperty("node_id")
        public void setNodeId(String nodeId) {
            this.nodeId = nodeId;
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
            sb.append(GetPullRequestResponse.License.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("key");
            sb.append('=');
            sb.append(((this.key == null)?"<null>":this.key));
            sb.append(',');
            sb.append("name");
            sb.append('=');
            sb.append(((this.name == null)?"<null>":this.name));
            sb.append(',');
            sb.append("url");
            sb.append('=');
            sb.append(((this.url == null)?"<null>":this.url));
            sb.append(',');
            sb.append("spdxId");
            sb.append('=');
            sb.append(((this.spdxId == null)?"<null>":this.spdxId));
            sb.append(',');
            sb.append("nodeId");
            sb.append('=');
            sb.append(((this.nodeId == null)?"<null>":this.nodeId));
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
            result = ((result* 31)+((this.spdxId == null)? 0 :this.spdxId.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.nodeId == null)? 0 :this.nodeId.hashCode()));
            result = ((result* 31)+((this.key == null)? 0 :this.key.hashCode()));
            result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof GetPullRequestResponse.License) == false) {
                return false;
            }
            GetPullRequestResponse.License rhs = ((GetPullRequestResponse.License) other);
            return (((((((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name)))&&((this.spdxId == rhs.spdxId)||((this.spdxId!= null)&&this.spdxId.equals(rhs.spdxId))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.nodeId == rhs.nodeId)||((this.nodeId!= null)&&this.nodeId.equals(rhs.nodeId))))&&((this.key == rhs.key)||((this.key!= null)&&this.key.equals(rhs.key))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "key",
        "name",
        "url",
        "spdx_id",
        "node_id"
    })
    @Generated("jsonschema2pojo")
    public static class License2 {

        @JsonProperty("key")
        private String key;
        @JsonProperty("name")
        private String name;
        @JsonProperty("url")
        private String url;
        @JsonProperty("spdx_id")
        private String spdxId;
        @JsonProperty("node_id")
        private String nodeId;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public License2() {
        }

        /**
         * 
         * @param name
         * @param spdxId
         * @param nodeId
         * @param key
         * @param url
         */
        public License2(String key, String name, String url, String spdxId, String nodeId) {
            super();
            this.key = key;
            this.name = name;
            this.url = url;
            this.spdxId = spdxId;
            this.nodeId = nodeId;
        }

        @JsonProperty("key")
        public String getKey() {
            return key;
        }

        @JsonProperty("key")
        public void setKey(String key) {
            this.key = key;
        }

        @JsonProperty("name")
        public String getName() {
            return name;
        }

        @JsonProperty("name")
        public void setName(String name) {
            this.name = name;
        }

        @JsonProperty("url")
        public String getUrl() {
            return url;
        }

        @JsonProperty("url")
        public void setUrl(String url) {
            this.url = url;
        }

        @JsonProperty("spdx_id")
        public String getSpdxId() {
            return spdxId;
        }

        @JsonProperty("spdx_id")
        public void setSpdxId(String spdxId) {
            this.spdxId = spdxId;
        }

        @JsonProperty("node_id")
        public String getNodeId() {
            return nodeId;
        }

        @JsonProperty("node_id")
        public void setNodeId(String nodeId) {
            this.nodeId = nodeId;
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
            sb.append(GetPullRequestResponse.License2 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("key");
            sb.append('=');
            sb.append(((this.key == null)?"<null>":this.key));
            sb.append(',');
            sb.append("name");
            sb.append('=');
            sb.append(((this.name == null)?"<null>":this.name));
            sb.append(',');
            sb.append("url");
            sb.append('=');
            sb.append(((this.url == null)?"<null>":this.url));
            sb.append(',');
            sb.append("spdxId");
            sb.append('=');
            sb.append(((this.spdxId == null)?"<null>":this.spdxId));
            sb.append(',');
            sb.append("nodeId");
            sb.append('=');
            sb.append(((this.nodeId == null)?"<null>":this.nodeId));
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
            result = ((result* 31)+((this.spdxId == null)? 0 :this.spdxId.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.nodeId == null)? 0 :this.nodeId.hashCode()));
            result = ((result* 31)+((this.key == null)? 0 :this.key.hashCode()));
            result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof GetPullRequestResponse.License2) == false) {
                return false;
            }
            GetPullRequestResponse.License2 rhs = ((GetPullRequestResponse.License2) other);
            return (((((((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name)))&&((this.spdxId == rhs.spdxId)||((this.spdxId!= null)&&this.spdxId.equals(rhs.spdxId))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.nodeId == rhs.nodeId)||((this.nodeId!= null)&&this.nodeId.equals(rhs.nodeId))))&&((this.key == rhs.key)||((this.key!= null)&&this.key.equals(rhs.key))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "self",
        "html",
        "issue",
        "comments",
        "review_comments",
        "review_comment",
        "commits",
        "statuses"
    })
    @Generated("jsonschema2pojo")
    public static class Links {

        @JsonProperty("self")
        private GetPullRequestResponse.Self self;
        @JsonProperty("html")
        private GetPullRequestResponse.Html html;
        @JsonProperty("issue")
        private GetPullRequestResponse.Issue issue;
        @JsonProperty("comments")
        private GetPullRequestResponse.Comments comments;
        @JsonProperty("review_comments")
        private GetPullRequestResponse.ReviewComments reviewComments;
        @JsonProperty("review_comment")
        private GetPullRequestResponse.ReviewComment reviewComment;
        @JsonProperty("commits")
        private GetPullRequestResponse.Commits commits;
        @JsonProperty("statuses")
        private GetPullRequestResponse.Statuses statuses;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Links() {
        }

        /**
         * 
         * @param comments
         * @param issue
         * @param reviewComment
         * @param self
         * @param commits
         * @param statuses
         * @param html
         * @param reviewComments
         */
        public Links(GetPullRequestResponse.Self self, GetPullRequestResponse.Html html, GetPullRequestResponse.Issue issue, GetPullRequestResponse.Comments comments, GetPullRequestResponse.ReviewComments reviewComments, GetPullRequestResponse.ReviewComment reviewComment, GetPullRequestResponse.Commits commits, GetPullRequestResponse.Statuses statuses) {
            super();
            this.self = self;
            this.html = html;
            this.issue = issue;
            this.comments = comments;
            this.reviewComments = reviewComments;
            this.reviewComment = reviewComment;
            this.commits = commits;
            this.statuses = statuses;
        }

        @JsonProperty("self")
        public GetPullRequestResponse.Self getSelf() {
            return self;
        }

        @JsonProperty("self")
        public void setSelf(GetPullRequestResponse.Self self) {
            this.self = self;
        }

        @JsonProperty("html")
        public GetPullRequestResponse.Html getHtml() {
            return html;
        }

        @JsonProperty("html")
        public void setHtml(GetPullRequestResponse.Html html) {
            this.html = html;
        }

        @JsonProperty("issue")
        public GetPullRequestResponse.Issue getIssue() {
            return issue;
        }

        @JsonProperty("issue")
        public void setIssue(GetPullRequestResponse.Issue issue) {
            this.issue = issue;
        }

        @JsonProperty("comments")
        public GetPullRequestResponse.Comments getComments() {
            return comments;
        }

        @JsonProperty("comments")
        public void setComments(GetPullRequestResponse.Comments comments) {
            this.comments = comments;
        }

        @JsonProperty("review_comments")
        public GetPullRequestResponse.ReviewComments getReviewComments() {
            return reviewComments;
        }

        @JsonProperty("review_comments")
        public void setReviewComments(GetPullRequestResponse.ReviewComments reviewComments) {
            this.reviewComments = reviewComments;
        }

        @JsonProperty("review_comment")
        public GetPullRequestResponse.ReviewComment getReviewComment() {
            return reviewComment;
        }

        @JsonProperty("review_comment")
        public void setReviewComment(GetPullRequestResponse.ReviewComment reviewComment) {
            this.reviewComment = reviewComment;
        }

        @JsonProperty("commits")
        public GetPullRequestResponse.Commits getCommits() {
            return commits;
        }

        @JsonProperty("commits")
        public void setCommits(GetPullRequestResponse.Commits commits) {
            this.commits = commits;
        }

        @JsonProperty("statuses")
        public GetPullRequestResponse.Statuses getStatuses() {
            return statuses;
        }

        @JsonProperty("statuses")
        public void setStatuses(GetPullRequestResponse.Statuses statuses) {
            this.statuses = statuses;
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
            sb.append(GetPullRequestResponse.Links.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("self");
            sb.append('=');
            sb.append(((this.self == null)?"<null>":this.self));
            sb.append(',');
            sb.append("html");
            sb.append('=');
            sb.append(((this.html == null)?"<null>":this.html));
            sb.append(',');
            sb.append("issue");
            sb.append('=');
            sb.append(((this.issue == null)?"<null>":this.issue));
            sb.append(',');
            sb.append("comments");
            sb.append('=');
            sb.append(((this.comments == null)?"<null>":this.comments));
            sb.append(',');
            sb.append("reviewComments");
            sb.append('=');
            sb.append(((this.reviewComments == null)?"<null>":this.reviewComments));
            sb.append(',');
            sb.append("reviewComment");
            sb.append('=');
            sb.append(((this.reviewComment == null)?"<null>":this.reviewComment));
            sb.append(',');
            sb.append("commits");
            sb.append('=');
            sb.append(((this.commits == null)?"<null>":this.commits));
            sb.append(',');
            sb.append("statuses");
            sb.append('=');
            sb.append(((this.statuses == null)?"<null>":this.statuses));
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
            result = ((result* 31)+((this.comments == null)? 0 :this.comments.hashCode()));
            result = ((result* 31)+((this.issue == null)? 0 :this.issue.hashCode()));
            result = ((result* 31)+((this.reviewComment == null)? 0 :this.reviewComment.hashCode()));
            result = ((result* 31)+((this.self == null)? 0 :this.self.hashCode()));
            result = ((result* 31)+((this.commits == null)? 0 :this.commits.hashCode()));
            result = ((result* 31)+((this.statuses == null)? 0 :this.statuses.hashCode()));
            result = ((result* 31)+((this.html == null)? 0 :this.html.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.reviewComments == null)? 0 :this.reviewComments.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof GetPullRequestResponse.Links) == false) {
                return false;
            }
            GetPullRequestResponse.Links rhs = ((GetPullRequestResponse.Links) other);
            return ((((((((((this.comments == rhs.comments)||((this.comments!= null)&&this.comments.equals(rhs.comments)))&&((this.issue == rhs.issue)||((this.issue!= null)&&this.issue.equals(rhs.issue))))&&((this.reviewComment == rhs.reviewComment)||((this.reviewComment!= null)&&this.reviewComment.equals(rhs.reviewComment))))&&((this.self == rhs.self)||((this.self!= null)&&this.self.equals(rhs.self))))&&((this.commits == rhs.commits)||((this.commits!= null)&&this.commits.equals(rhs.commits))))&&((this.statuses == rhs.statuses)||((this.statuses!= null)&&this.statuses.equals(rhs.statuses))))&&((this.html == rhs.html)||((this.html!= null)&&this.html.equals(rhs.html))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.reviewComments == rhs.reviewComments)||((this.reviewComments!= null)&&this.reviewComments.equals(rhs.reviewComments))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "login",
        "id",
        "node_id",
        "avatar_url",
        "gravatar_id",
        "url",
        "html_url",
        "followers_url",
        "following_url",
        "gists_url",
        "starred_url",
        "subscriptions_url",
        "organizations_url",
        "repos_url",
        "events_url",
        "received_events_url",
        "type",
        "site_admin"
    })
    @Generated("jsonschema2pojo")
    public static class MergedBy {

        @JsonProperty("login")
        private String login;
        @JsonProperty("id")
        private Long id;
        @JsonProperty("node_id")
        private String nodeId;
        @JsonProperty("avatar_url")
        private String avatarUrl;
        @JsonProperty("gravatar_id")
        private String gravatarId;
        @JsonProperty("url")
        private String url;
        @JsonProperty("html_url")
        private String htmlUrl;
        @JsonProperty("followers_url")
        private String followersUrl;
        @JsonProperty("following_url")
        private String followingUrl;
        @JsonProperty("gists_url")
        private String gistsUrl;
        @JsonProperty("starred_url")
        private String starredUrl;
        @JsonProperty("subscriptions_url")
        private String subscriptionsUrl;
        @JsonProperty("organizations_url")
        private String organizationsUrl;
        @JsonProperty("repos_url")
        private String reposUrl;
        @JsonProperty("events_url")
        private String eventsUrl;
        @JsonProperty("received_events_url")
        private String receivedEventsUrl;
        @JsonProperty("type")
        private String type;
        @JsonProperty("site_admin")
        private Boolean siteAdmin;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public MergedBy() {
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
         * @param url
         * @param starredUrl
         * @param gravatarId
         * @param followersUrl
         * @param id
         * @param eventsUrl
         * @param nodeId
         */
        public MergedBy(String login, Long id, String nodeId, String avatarUrl, String gravatarId, String url, String htmlUrl, String followersUrl, String followingUrl, String gistsUrl, String starredUrl, String subscriptionsUrl, String organizationsUrl, String reposUrl, String eventsUrl, String receivedEventsUrl, String type, Boolean siteAdmin) {
            super();
            this.login = login;
            this.id = id;
            this.nodeId = nodeId;
            this.avatarUrl = avatarUrl;
            this.gravatarId = gravatarId;
            this.url = url;
            this.htmlUrl = htmlUrl;
            this.followersUrl = followersUrl;
            this.followingUrl = followingUrl;
            this.gistsUrl = gistsUrl;
            this.starredUrl = starredUrl;
            this.subscriptionsUrl = subscriptionsUrl;
            this.organizationsUrl = organizationsUrl;
            this.reposUrl = reposUrl;
            this.eventsUrl = eventsUrl;
            this.receivedEventsUrl = receivedEventsUrl;
            this.type = type;
            this.siteAdmin = siteAdmin;
        }

        @JsonProperty("login")
        public String getLogin() {
            return login;
        }

        @JsonProperty("login")
        public void setLogin(String login) {
            this.login = login;
        }

        @JsonProperty("id")
        public Long getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(Long id) {
            this.id = id;
        }

        @JsonProperty("node_id")
        public String getNodeId() {
            return nodeId;
        }

        @JsonProperty("node_id")
        public void setNodeId(String nodeId) {
            this.nodeId = nodeId;
        }

        @JsonProperty("avatar_url")
        public String getAvatarUrl() {
            return avatarUrl;
        }

        @JsonProperty("avatar_url")
        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        @JsonProperty("gravatar_id")
        public String getGravatarId() {
            return gravatarId;
        }

        @JsonProperty("gravatar_id")
        public void setGravatarId(String gravatarId) {
            this.gravatarId = gravatarId;
        }

        @JsonProperty("url")
        public String getUrl() {
            return url;
        }

        @JsonProperty("url")
        public void setUrl(String url) {
            this.url = url;
        }

        @JsonProperty("html_url")
        public String getHtmlUrl() {
            return htmlUrl;
        }

        @JsonProperty("html_url")
        public void setHtmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
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

        @JsonProperty("organizations_url")
        public String getOrganizationsUrl() {
            return organizationsUrl;
        }

        @JsonProperty("organizations_url")
        public void setOrganizationsUrl(String organizationsUrl) {
            this.organizationsUrl = organizationsUrl;
        }

        @JsonProperty("repos_url")
        public String getReposUrl() {
            return reposUrl;
        }

        @JsonProperty("repos_url")
        public void setReposUrl(String reposUrl) {
            this.reposUrl = reposUrl;
        }

        @JsonProperty("events_url")
        public String getEventsUrl() {
            return eventsUrl;
        }

        @JsonProperty("events_url")
        public void setEventsUrl(String eventsUrl) {
            this.eventsUrl = eventsUrl;
        }

        @JsonProperty("received_events_url")
        public String getReceivedEventsUrl() {
            return receivedEventsUrl;
        }

        @JsonProperty("received_events_url")
        public void setReceivedEventsUrl(String receivedEventsUrl) {
            this.receivedEventsUrl = receivedEventsUrl;
        }

        @JsonProperty("type")
        public String getType() {
            return type;
        }

        @JsonProperty("type")
        public void setType(String type) {
            this.type = type;
        }

        @JsonProperty("site_admin")
        public Boolean getSiteAdmin() {
            return siteAdmin;
        }

        @JsonProperty("site_admin")
        public void setSiteAdmin(Boolean siteAdmin) {
            this.siteAdmin = siteAdmin;
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
            sb.append(GetPullRequestResponse.MergedBy.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("login");
            sb.append('=');
            sb.append(((this.login == null)?"<null>":this.login));
            sb.append(',');
            sb.append("id");
            sb.append('=');
            sb.append(((this.id == null)?"<null>":this.id));
            sb.append(',');
            sb.append("nodeId");
            sb.append('=');
            sb.append(((this.nodeId == null)?"<null>":this.nodeId));
            sb.append(',');
            sb.append("avatarUrl");
            sb.append('=');
            sb.append(((this.avatarUrl == null)?"<null>":this.avatarUrl));
            sb.append(',');
            sb.append("gravatarId");
            sb.append('=');
            sb.append(((this.gravatarId == null)?"<null>":this.gravatarId));
            sb.append(',');
            sb.append("url");
            sb.append('=');
            sb.append(((this.url == null)?"<null>":this.url));
            sb.append(',');
            sb.append("htmlUrl");
            sb.append('=');
            sb.append(((this.htmlUrl == null)?"<null>":this.htmlUrl));
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
            sb.append("starredUrl");
            sb.append('=');
            sb.append(((this.starredUrl == null)?"<null>":this.starredUrl));
            sb.append(',');
            sb.append("subscriptionsUrl");
            sb.append('=');
            sb.append(((this.subscriptionsUrl == null)?"<null>":this.subscriptionsUrl));
            sb.append(',');
            sb.append("organizationsUrl");
            sb.append('=');
            sb.append(((this.organizationsUrl == null)?"<null>":this.organizationsUrl));
            sb.append(',');
            sb.append("reposUrl");
            sb.append('=');
            sb.append(((this.reposUrl == null)?"<null>":this.reposUrl));
            sb.append(',');
            sb.append("eventsUrl");
            sb.append('=');
            sb.append(((this.eventsUrl == null)?"<null>":this.eventsUrl));
            sb.append(',');
            sb.append("receivedEventsUrl");
            sb.append('=');
            sb.append(((this.receivedEventsUrl == null)?"<null>":this.receivedEventsUrl));
            sb.append(',');
            sb.append("type");
            sb.append('=');
            sb.append(((this.type == null)?"<null>":this.type));
            sb.append(',');
            sb.append("siteAdmin");
            sb.append('=');
            sb.append(((this.siteAdmin == null)?"<null>":this.siteAdmin));
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
            result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
            result = ((result* 31)+((this.starredUrl == null)? 0 :this.starredUrl.hashCode()));
            result = ((result* 31)+((this.gravatarId == null)? 0 :this.gravatarId.hashCode()));
            result = ((result* 31)+((this.followersUrl == null)? 0 :this.followersUrl.hashCode()));
            result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
            result = ((result* 31)+((this.eventsUrl == null)? 0 :this.eventsUrl.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.nodeId == null)? 0 :this.nodeId.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof GetPullRequestResponse.MergedBy) == false) {
                return false;
            }
            GetPullRequestResponse.MergedBy rhs = ((GetPullRequestResponse.MergedBy) other);
            return ((((((((((((((((((((this.receivedEventsUrl == rhs.receivedEventsUrl)||((this.receivedEventsUrl!= null)&&this.receivedEventsUrl.equals(rhs.receivedEventsUrl)))&&((this.siteAdmin == rhs.siteAdmin)||((this.siteAdmin!= null)&&this.siteAdmin.equals(rhs.siteAdmin))))&&((this.followingUrl == rhs.followingUrl)||((this.followingUrl!= null)&&this.followingUrl.equals(rhs.followingUrl))))&&((this.gistsUrl == rhs.gistsUrl)||((this.gistsUrl!= null)&&this.gistsUrl.equals(rhs.gistsUrl))))&&((this.avatarUrl == rhs.avatarUrl)||((this.avatarUrl!= null)&&this.avatarUrl.equals(rhs.avatarUrl))))&&((this.organizationsUrl == rhs.organizationsUrl)||((this.organizationsUrl!= null)&&this.organizationsUrl.equals(rhs.organizationsUrl))))&&((this.reposUrl == rhs.reposUrl)||((this.reposUrl!= null)&&this.reposUrl.equals(rhs.reposUrl))))&&((this.htmlUrl == rhs.htmlUrl)||((this.htmlUrl!= null)&&this.htmlUrl.equals(rhs.htmlUrl))))&&((this.subscriptionsUrl == rhs.subscriptionsUrl)||((this.subscriptionsUrl!= null)&&this.subscriptionsUrl.equals(rhs.subscriptionsUrl))))&&((this.login == rhs.login)||((this.login!= null)&&this.login.equals(rhs.login))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))))&&((this.starredUrl == rhs.starredUrl)||((this.starredUrl!= null)&&this.starredUrl.equals(rhs.starredUrl))))&&((this.gravatarId == rhs.gravatarId)||((this.gravatarId!= null)&&this.gravatarId.equals(rhs.gravatarId))))&&((this.followersUrl == rhs.followersUrl)||((this.followersUrl!= null)&&this.followersUrl.equals(rhs.followersUrl))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.eventsUrl == rhs.eventsUrl)||((this.eventsUrl!= null)&&this.eventsUrl.equals(rhs.eventsUrl))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.nodeId == rhs.nodeId)||((this.nodeId!= null)&&this.nodeId.equals(rhs.nodeId))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "url",
        "html_url",
        "labels_url",
        "id",
        "node_id",
        "number",
        "state",
        "title",
        "description",
        "creator",
        "open_issues",
        "closed_issues",
        "created_at",
        "updated_at",
        "closed_at",
        "due_on"
    })
    @Generated("jsonschema2pojo")
    public static class Milestone {

        @JsonProperty("url")
        private String url;
        @JsonProperty("html_url")
        private String htmlUrl;
        @JsonProperty("labels_url")
        private String labelsUrl;
        @JsonProperty("id")
        private Long id;
        @JsonProperty("node_id")
        private String nodeId;
        @JsonProperty("number")
        private Long number;
        @JsonProperty("state")
        private String state;
        @JsonProperty("title")
        private String title;
        @JsonProperty("description")
        private String description;
        @JsonProperty("creator")
        private GetPullRequestResponse.Creator creator;
        @JsonProperty("open_issues")
        private Long openIssues;
        @JsonProperty("closed_issues")
        private Long closedIssues;
        @JsonProperty("created_at")
        private String createdAt;
        @JsonProperty("updated_at")
        private String updatedAt;
        @JsonProperty("closed_at")
        private String closedAt;
        @JsonProperty("due_on")
        private String dueOn;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Milestone() {
        }

        /**
         * 
         * @param closedIssues
         * @param creator
         * @param labelsUrl
         * @param dueOn
         * @param htmlUrl
         * @param description
         * @param title
         * @param openIssues
         * @param url
         * @param number
         * @param createdAt
         * @param id
         * @param state
         * @param closedAt
         * @param nodeId
         * @param updatedAt
         */
        public Milestone(String url, String htmlUrl, String labelsUrl, Long id, String nodeId, Long number, String state, String title, String description, GetPullRequestResponse.Creator creator, Long openIssues, Long closedIssues, String createdAt, String updatedAt, String closedAt, String dueOn) {
            super();
            this.url = url;
            this.htmlUrl = htmlUrl;
            this.labelsUrl = labelsUrl;
            this.id = id;
            this.nodeId = nodeId;
            this.number = number;
            this.state = state;
            this.title = title;
            this.description = description;
            this.creator = creator;
            this.openIssues = openIssues;
            this.closedIssues = closedIssues;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
            this.closedAt = closedAt;
            this.dueOn = dueOn;
        }

        @JsonProperty("url")
        public String getUrl() {
            return url;
        }

        @JsonProperty("url")
        public void setUrl(String url) {
            this.url = url;
        }

        @JsonProperty("html_url")
        public String getHtmlUrl() {
            return htmlUrl;
        }

        @JsonProperty("html_url")
        public void setHtmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
        }

        @JsonProperty("labels_url")
        public String getLabelsUrl() {
            return labelsUrl;
        }

        @JsonProperty("labels_url")
        public void setLabelsUrl(String labelsUrl) {
            this.labelsUrl = labelsUrl;
        }

        @JsonProperty("id")
        public Long getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(Long id) {
            this.id = id;
        }

        @JsonProperty("node_id")
        public String getNodeId() {
            return nodeId;
        }

        @JsonProperty("node_id")
        public void setNodeId(String nodeId) {
            this.nodeId = nodeId;
        }

        @JsonProperty("number")
        public Long getNumber() {
            return number;
        }

        @JsonProperty("number")
        public void setNumber(Long number) {
            this.number = number;
        }

        @JsonProperty("state")
        public String getState() {
            return state;
        }

        @JsonProperty("state")
        public void setState(String state) {
            this.state = state;
        }

        @JsonProperty("title")
        public String getTitle() {
            return title;
        }

        @JsonProperty("title")
        public void setTitle(String title) {
            this.title = title;
        }

        @JsonProperty("description")
        public String getDescription() {
            return description;
        }

        @JsonProperty("description")
        public void setDescription(String description) {
            this.description = description;
        }

        @JsonProperty("creator")
        public GetPullRequestResponse.Creator getCreator() {
            return creator;
        }

        @JsonProperty("creator")
        public void setCreator(GetPullRequestResponse.Creator creator) {
            this.creator = creator;
        }

        @JsonProperty("open_issues")
        public Long getOpenIssues() {
            return openIssues;
        }

        @JsonProperty("open_issues")
        public void setOpenIssues(Long openIssues) {
            this.openIssues = openIssues;
        }

        @JsonProperty("closed_issues")
        public Long getClosedIssues() {
            return closedIssues;
        }

        @JsonProperty("closed_issues")
        public void setClosedIssues(Long closedIssues) {
            this.closedIssues = closedIssues;
        }

        @JsonProperty("created_at")
        public String getCreatedAt() {
            return createdAt;
        }

        @JsonProperty("created_at")
        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        @JsonProperty("updated_at")
        public String getUpdatedAt() {
            return updatedAt;
        }

        @JsonProperty("updated_at")
        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        @JsonProperty("closed_at")
        public String getClosedAt() {
            return closedAt;
        }

        @JsonProperty("closed_at")
        public void setClosedAt(String closedAt) {
            this.closedAt = closedAt;
        }

        @JsonProperty("due_on")
        public String getDueOn() {
            return dueOn;
        }

        @JsonProperty("due_on")
        public void setDueOn(String dueOn) {
            this.dueOn = dueOn;
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
            sb.append(GetPullRequestResponse.Milestone.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("url");
            sb.append('=');
            sb.append(((this.url == null)?"<null>":this.url));
            sb.append(',');
            sb.append("htmlUrl");
            sb.append('=');
            sb.append(((this.htmlUrl == null)?"<null>":this.htmlUrl));
            sb.append(',');
            sb.append("labelsUrl");
            sb.append('=');
            sb.append(((this.labelsUrl == null)?"<null>":this.labelsUrl));
            sb.append(',');
            sb.append("id");
            sb.append('=');
            sb.append(((this.id == null)?"<null>":this.id));
            sb.append(',');
            sb.append("nodeId");
            sb.append('=');
            sb.append(((this.nodeId == null)?"<null>":this.nodeId));
            sb.append(',');
            sb.append("number");
            sb.append('=');
            sb.append(((this.number == null)?"<null>":this.number));
            sb.append(',');
            sb.append("state");
            sb.append('=');
            sb.append(((this.state == null)?"<null>":this.state));
            sb.append(',');
            sb.append("title");
            sb.append('=');
            sb.append(((this.title == null)?"<null>":this.title));
            sb.append(',');
            sb.append("description");
            sb.append('=');
            sb.append(((this.description == null)?"<null>":this.description));
            sb.append(',');
            sb.append("creator");
            sb.append('=');
            sb.append(((this.creator == null)?"<null>":this.creator));
            sb.append(',');
            sb.append("openIssues");
            sb.append('=');
            sb.append(((this.openIssues == null)?"<null>":this.openIssues));
            sb.append(',');
            sb.append("closedIssues");
            sb.append('=');
            sb.append(((this.closedIssues == null)?"<null>":this.closedIssues));
            sb.append(',');
            sb.append("createdAt");
            sb.append('=');
            sb.append(((this.createdAt == null)?"<null>":this.createdAt));
            sb.append(',');
            sb.append("updatedAt");
            sb.append('=');
            sb.append(((this.updatedAt == null)?"<null>":this.updatedAt));
            sb.append(',');
            sb.append("closedAt");
            sb.append('=');
            sb.append(((this.closedAt == null)?"<null>":this.closedAt));
            sb.append(',');
            sb.append("dueOn");
            sb.append('=');
            sb.append(((this.dueOn == null)?"<null>":this.dueOn));
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
            result = ((result* 31)+((this.closedIssues == null)? 0 :this.closedIssues.hashCode()));
            result = ((result* 31)+((this.creator == null)? 0 :this.creator.hashCode()));
            result = ((result* 31)+((this.labelsUrl == null)? 0 :this.labelsUrl.hashCode()));
            result = ((result* 31)+((this.dueOn == null)? 0 :this.dueOn.hashCode()));
            result = ((result* 31)+((this.htmlUrl == null)? 0 :this.htmlUrl.hashCode()));
            result = ((result* 31)+((this.description == null)? 0 :this.description.hashCode()));
            result = ((result* 31)+((this.title == null)? 0 :this.title.hashCode()));
            result = ((result* 31)+((this.openIssues == null)? 0 :this.openIssues.hashCode()));
            result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
            result = ((result* 31)+((this.number == null)? 0 :this.number.hashCode()));
            result = ((result* 31)+((this.createdAt == null)? 0 :this.createdAt.hashCode()));
            result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
            result = ((result* 31)+((this.state == null)? 0 :this.state.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.closedAt == null)? 0 :this.closedAt.hashCode()));
            result = ((result* 31)+((this.nodeId == null)? 0 :this.nodeId.hashCode()));
            result = ((result* 31)+((this.updatedAt == null)? 0 :this.updatedAt.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof GetPullRequestResponse.Milestone) == false) {
                return false;
            }
            GetPullRequestResponse.Milestone rhs = ((GetPullRequestResponse.Milestone) other);
            return ((((((((((((((((((this.closedIssues == rhs.closedIssues)||((this.closedIssues!= null)&&this.closedIssues.equals(rhs.closedIssues)))&&((this.creator == rhs.creator)||((this.creator!= null)&&this.creator.equals(rhs.creator))))&&((this.labelsUrl == rhs.labelsUrl)||((this.labelsUrl!= null)&&this.labelsUrl.equals(rhs.labelsUrl))))&&((this.dueOn == rhs.dueOn)||((this.dueOn!= null)&&this.dueOn.equals(rhs.dueOn))))&&((this.htmlUrl == rhs.htmlUrl)||((this.htmlUrl!= null)&&this.htmlUrl.equals(rhs.htmlUrl))))&&((this.description == rhs.description)||((this.description!= null)&&this.description.equals(rhs.description))))&&((this.title == rhs.title)||((this.title!= null)&&this.title.equals(rhs.title))))&&((this.openIssues == rhs.openIssues)||((this.openIssues!= null)&&this.openIssues.equals(rhs.openIssues))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))))&&((this.number == rhs.number)||((this.number!= null)&&this.number.equals(rhs.number))))&&((this.createdAt == rhs.createdAt)||((this.createdAt!= null)&&this.createdAt.equals(rhs.createdAt))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.state == rhs.state)||((this.state!= null)&&this.state.equals(rhs.state))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.closedAt == rhs.closedAt)||((this.closedAt!= null)&&this.closedAt.equals(rhs.closedAt))))&&((this.nodeId == rhs.nodeId)||((this.nodeId!= null)&&this.nodeId.equals(rhs.nodeId))))&&((this.updatedAt == rhs.updatedAt)||((this.updatedAt!= null)&&this.updatedAt.equals(rhs.updatedAt))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "login",
        "id",
        "node_id",
        "avatar_url",
        "gravatar_id",
        "url",
        "html_url",
        "followers_url",
        "following_url",
        "gists_url",
        "starred_url",
        "subscriptions_url",
        "organizations_url",
        "repos_url",
        "events_url",
        "received_events_url",
        "type",
        "site_admin"
    })
    @Generated("jsonschema2pojo")
    public static class Owner {

        @JsonProperty("login")
        private String login;
        @JsonProperty("id")
        private Long id;
        @JsonProperty("node_id")
        private String nodeId;
        @JsonProperty("avatar_url")
        private String avatarUrl;
        @JsonProperty("gravatar_id")
        private String gravatarId;
        @JsonProperty("url")
        private String url;
        @JsonProperty("html_url")
        private String htmlUrl;
        @JsonProperty("followers_url")
        private String followersUrl;
        @JsonProperty("following_url")
        private String followingUrl;
        @JsonProperty("gists_url")
        private String gistsUrl;
        @JsonProperty("starred_url")
        private String starredUrl;
        @JsonProperty("subscriptions_url")
        private String subscriptionsUrl;
        @JsonProperty("organizations_url")
        private String organizationsUrl;
        @JsonProperty("repos_url")
        private String reposUrl;
        @JsonProperty("events_url")
        private String eventsUrl;
        @JsonProperty("received_events_url")
        private String receivedEventsUrl;
        @JsonProperty("type")
        private String type;
        @JsonProperty("site_admin")
        private Boolean siteAdmin;
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
         * @param url
         * @param starredUrl
         * @param gravatarId
         * @param followersUrl
         * @param id
         * @param eventsUrl
         * @param nodeId
         */
        public Owner(String login, Long id, String nodeId, String avatarUrl, String gravatarId, String url, String htmlUrl, String followersUrl, String followingUrl, String gistsUrl, String starredUrl, String subscriptionsUrl, String organizationsUrl, String reposUrl, String eventsUrl, String receivedEventsUrl, String type, Boolean siteAdmin) {
            super();
            this.login = login;
            this.id = id;
            this.nodeId = nodeId;
            this.avatarUrl = avatarUrl;
            this.gravatarId = gravatarId;
            this.url = url;
            this.htmlUrl = htmlUrl;
            this.followersUrl = followersUrl;
            this.followingUrl = followingUrl;
            this.gistsUrl = gistsUrl;
            this.starredUrl = starredUrl;
            this.subscriptionsUrl = subscriptionsUrl;
            this.organizationsUrl = organizationsUrl;
            this.reposUrl = reposUrl;
            this.eventsUrl = eventsUrl;
            this.receivedEventsUrl = receivedEventsUrl;
            this.type = type;
            this.siteAdmin = siteAdmin;
        }

        @JsonProperty("login")
        public String getLogin() {
            return login;
        }

        @JsonProperty("login")
        public void setLogin(String login) {
            this.login = login;
        }

        @JsonProperty("id")
        public Long getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(Long id) {
            this.id = id;
        }

        @JsonProperty("node_id")
        public String getNodeId() {
            return nodeId;
        }

        @JsonProperty("node_id")
        public void setNodeId(String nodeId) {
            this.nodeId = nodeId;
        }

        @JsonProperty("avatar_url")
        public String getAvatarUrl() {
            return avatarUrl;
        }

        @JsonProperty("avatar_url")
        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        @JsonProperty("gravatar_id")
        public String getGravatarId() {
            return gravatarId;
        }

        @JsonProperty("gravatar_id")
        public void setGravatarId(String gravatarId) {
            this.gravatarId = gravatarId;
        }

        @JsonProperty("url")
        public String getUrl() {
            return url;
        }

        @JsonProperty("url")
        public void setUrl(String url) {
            this.url = url;
        }

        @JsonProperty("html_url")
        public String getHtmlUrl() {
            return htmlUrl;
        }

        @JsonProperty("html_url")
        public void setHtmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
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

        @JsonProperty("organizations_url")
        public String getOrganizationsUrl() {
            return organizationsUrl;
        }

        @JsonProperty("organizations_url")
        public void setOrganizationsUrl(String organizationsUrl) {
            this.organizationsUrl = organizationsUrl;
        }

        @JsonProperty("repos_url")
        public String getReposUrl() {
            return reposUrl;
        }

        @JsonProperty("repos_url")
        public void setReposUrl(String reposUrl) {
            this.reposUrl = reposUrl;
        }

        @JsonProperty("events_url")
        public String getEventsUrl() {
            return eventsUrl;
        }

        @JsonProperty("events_url")
        public void setEventsUrl(String eventsUrl) {
            this.eventsUrl = eventsUrl;
        }

        @JsonProperty("received_events_url")
        public String getReceivedEventsUrl() {
            return receivedEventsUrl;
        }

        @JsonProperty("received_events_url")
        public void setReceivedEventsUrl(String receivedEventsUrl) {
            this.receivedEventsUrl = receivedEventsUrl;
        }

        @JsonProperty("type")
        public String getType() {
            return type;
        }

        @JsonProperty("type")
        public void setType(String type) {
            this.type = type;
        }

        @JsonProperty("site_admin")
        public Boolean getSiteAdmin() {
            return siteAdmin;
        }

        @JsonProperty("site_admin")
        public void setSiteAdmin(Boolean siteAdmin) {
            this.siteAdmin = siteAdmin;
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
            sb.append(GetPullRequestResponse.Owner.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("login");
            sb.append('=');
            sb.append(((this.login == null)?"<null>":this.login));
            sb.append(',');
            sb.append("id");
            sb.append('=');
            sb.append(((this.id == null)?"<null>":this.id));
            sb.append(',');
            sb.append("nodeId");
            sb.append('=');
            sb.append(((this.nodeId == null)?"<null>":this.nodeId));
            sb.append(',');
            sb.append("avatarUrl");
            sb.append('=');
            sb.append(((this.avatarUrl == null)?"<null>":this.avatarUrl));
            sb.append(',');
            sb.append("gravatarId");
            sb.append('=');
            sb.append(((this.gravatarId == null)?"<null>":this.gravatarId));
            sb.append(',');
            sb.append("url");
            sb.append('=');
            sb.append(((this.url == null)?"<null>":this.url));
            sb.append(',');
            sb.append("htmlUrl");
            sb.append('=');
            sb.append(((this.htmlUrl == null)?"<null>":this.htmlUrl));
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
            sb.append("starredUrl");
            sb.append('=');
            sb.append(((this.starredUrl == null)?"<null>":this.starredUrl));
            sb.append(',');
            sb.append("subscriptionsUrl");
            sb.append('=');
            sb.append(((this.subscriptionsUrl == null)?"<null>":this.subscriptionsUrl));
            sb.append(',');
            sb.append("organizationsUrl");
            sb.append('=');
            sb.append(((this.organizationsUrl == null)?"<null>":this.organizationsUrl));
            sb.append(',');
            sb.append("reposUrl");
            sb.append('=');
            sb.append(((this.reposUrl == null)?"<null>":this.reposUrl));
            sb.append(',');
            sb.append("eventsUrl");
            sb.append('=');
            sb.append(((this.eventsUrl == null)?"<null>":this.eventsUrl));
            sb.append(',');
            sb.append("receivedEventsUrl");
            sb.append('=');
            sb.append(((this.receivedEventsUrl == null)?"<null>":this.receivedEventsUrl));
            sb.append(',');
            sb.append("type");
            sb.append('=');
            sb.append(((this.type == null)?"<null>":this.type));
            sb.append(',');
            sb.append("siteAdmin");
            sb.append('=');
            sb.append(((this.siteAdmin == null)?"<null>":this.siteAdmin));
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
            result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
            result = ((result* 31)+((this.starredUrl == null)? 0 :this.starredUrl.hashCode()));
            result = ((result* 31)+((this.gravatarId == null)? 0 :this.gravatarId.hashCode()));
            result = ((result* 31)+((this.followersUrl == null)? 0 :this.followersUrl.hashCode()));
            result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
            result = ((result* 31)+((this.eventsUrl == null)? 0 :this.eventsUrl.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.nodeId == null)? 0 :this.nodeId.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof GetPullRequestResponse.Owner) == false) {
                return false;
            }
            GetPullRequestResponse.Owner rhs = ((GetPullRequestResponse.Owner) other);
            return ((((((((((((((((((((this.receivedEventsUrl == rhs.receivedEventsUrl)||((this.receivedEventsUrl!= null)&&this.receivedEventsUrl.equals(rhs.receivedEventsUrl)))&&((this.siteAdmin == rhs.siteAdmin)||((this.siteAdmin!= null)&&this.siteAdmin.equals(rhs.siteAdmin))))&&((this.followingUrl == rhs.followingUrl)||((this.followingUrl!= null)&&this.followingUrl.equals(rhs.followingUrl))))&&((this.gistsUrl == rhs.gistsUrl)||((this.gistsUrl!= null)&&this.gistsUrl.equals(rhs.gistsUrl))))&&((this.avatarUrl == rhs.avatarUrl)||((this.avatarUrl!= null)&&this.avatarUrl.equals(rhs.avatarUrl))))&&((this.organizationsUrl == rhs.organizationsUrl)||((this.organizationsUrl!= null)&&this.organizationsUrl.equals(rhs.organizationsUrl))))&&((this.reposUrl == rhs.reposUrl)||((this.reposUrl!= null)&&this.reposUrl.equals(rhs.reposUrl))))&&((this.htmlUrl == rhs.htmlUrl)||((this.htmlUrl!= null)&&this.htmlUrl.equals(rhs.htmlUrl))))&&((this.subscriptionsUrl == rhs.subscriptionsUrl)||((this.subscriptionsUrl!= null)&&this.subscriptionsUrl.equals(rhs.subscriptionsUrl))))&&((this.login == rhs.login)||((this.login!= null)&&this.login.equals(rhs.login))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))))&&((this.starredUrl == rhs.starredUrl)||((this.starredUrl!= null)&&this.starredUrl.equals(rhs.starredUrl))))&&((this.gravatarId == rhs.gravatarId)||((this.gravatarId!= null)&&this.gravatarId.equals(rhs.gravatarId))))&&((this.followersUrl == rhs.followersUrl)||((this.followersUrl!= null)&&this.followersUrl.equals(rhs.followersUrl))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.eventsUrl == rhs.eventsUrl)||((this.eventsUrl!= null)&&this.eventsUrl.equals(rhs.eventsUrl))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.nodeId == rhs.nodeId)||((this.nodeId!= null)&&this.nodeId.equals(rhs.nodeId))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "login",
        "id",
        "node_id",
        "avatar_url",
        "gravatar_id",
        "url",
        "html_url",
        "followers_url",
        "following_url",
        "gists_url",
        "starred_url",
        "subscriptions_url",
        "organizations_url",
        "repos_url",
        "events_url",
        "received_events_url",
        "type",
        "site_admin"
    })
    @Generated("jsonschema2pojo")
    public static class Owner2 {

        @JsonProperty("login")
        private String login;
        @JsonProperty("id")
        private Long id;
        @JsonProperty("node_id")
        private String nodeId;
        @JsonProperty("avatar_url")
        private String avatarUrl;
        @JsonProperty("gravatar_id")
        private String gravatarId;
        @JsonProperty("url")
        private String url;
        @JsonProperty("html_url")
        private String htmlUrl;
        @JsonProperty("followers_url")
        private String followersUrl;
        @JsonProperty("following_url")
        private String followingUrl;
        @JsonProperty("gists_url")
        private String gistsUrl;
        @JsonProperty("starred_url")
        private String starredUrl;
        @JsonProperty("subscriptions_url")
        private String subscriptionsUrl;
        @JsonProperty("organizations_url")
        private String organizationsUrl;
        @JsonProperty("repos_url")
        private String reposUrl;
        @JsonProperty("events_url")
        private String eventsUrl;
        @JsonProperty("received_events_url")
        private String receivedEventsUrl;
        @JsonProperty("type")
        private String type;
        @JsonProperty("site_admin")
        private Boolean siteAdmin;
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
         * @param url
         * @param starredUrl
         * @param gravatarId
         * @param followersUrl
         * @param id
         * @param eventsUrl
         * @param nodeId
         */
        public Owner2(String login, Long id, String nodeId, String avatarUrl, String gravatarId, String url, String htmlUrl, String followersUrl, String followingUrl, String gistsUrl, String starredUrl, String subscriptionsUrl, String organizationsUrl, String reposUrl, String eventsUrl, String receivedEventsUrl, String type, Boolean siteAdmin) {
            super();
            this.login = login;
            this.id = id;
            this.nodeId = nodeId;
            this.avatarUrl = avatarUrl;
            this.gravatarId = gravatarId;
            this.url = url;
            this.htmlUrl = htmlUrl;
            this.followersUrl = followersUrl;
            this.followingUrl = followingUrl;
            this.gistsUrl = gistsUrl;
            this.starredUrl = starredUrl;
            this.subscriptionsUrl = subscriptionsUrl;
            this.organizationsUrl = organizationsUrl;
            this.reposUrl = reposUrl;
            this.eventsUrl = eventsUrl;
            this.receivedEventsUrl = receivedEventsUrl;
            this.type = type;
            this.siteAdmin = siteAdmin;
        }

        @JsonProperty("login")
        public String getLogin() {
            return login;
        }

        @JsonProperty("login")
        public void setLogin(String login) {
            this.login = login;
        }

        @JsonProperty("id")
        public Long getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(Long id) {
            this.id = id;
        }

        @JsonProperty("node_id")
        public String getNodeId() {
            return nodeId;
        }

        @JsonProperty("node_id")
        public void setNodeId(String nodeId) {
            this.nodeId = nodeId;
        }

        @JsonProperty("avatar_url")
        public String getAvatarUrl() {
            return avatarUrl;
        }

        @JsonProperty("avatar_url")
        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        @JsonProperty("gravatar_id")
        public String getGravatarId() {
            return gravatarId;
        }

        @JsonProperty("gravatar_id")
        public void setGravatarId(String gravatarId) {
            this.gravatarId = gravatarId;
        }

        @JsonProperty("url")
        public String getUrl() {
            return url;
        }

        @JsonProperty("url")
        public void setUrl(String url) {
            this.url = url;
        }

        @JsonProperty("html_url")
        public String getHtmlUrl() {
            return htmlUrl;
        }

        @JsonProperty("html_url")
        public void setHtmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
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

        @JsonProperty("organizations_url")
        public String getOrganizationsUrl() {
            return organizationsUrl;
        }

        @JsonProperty("organizations_url")
        public void setOrganizationsUrl(String organizationsUrl) {
            this.organizationsUrl = organizationsUrl;
        }

        @JsonProperty("repos_url")
        public String getReposUrl() {
            return reposUrl;
        }

        @JsonProperty("repos_url")
        public void setReposUrl(String reposUrl) {
            this.reposUrl = reposUrl;
        }

        @JsonProperty("events_url")
        public String getEventsUrl() {
            return eventsUrl;
        }

        @JsonProperty("events_url")
        public void setEventsUrl(String eventsUrl) {
            this.eventsUrl = eventsUrl;
        }

        @JsonProperty("received_events_url")
        public String getReceivedEventsUrl() {
            return receivedEventsUrl;
        }

        @JsonProperty("received_events_url")
        public void setReceivedEventsUrl(String receivedEventsUrl) {
            this.receivedEventsUrl = receivedEventsUrl;
        }

        @JsonProperty("type")
        public String getType() {
            return type;
        }

        @JsonProperty("type")
        public void setType(String type) {
            this.type = type;
        }

        @JsonProperty("site_admin")
        public Boolean getSiteAdmin() {
            return siteAdmin;
        }

        @JsonProperty("site_admin")
        public void setSiteAdmin(Boolean siteAdmin) {
            this.siteAdmin = siteAdmin;
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
            sb.append(GetPullRequestResponse.Owner2 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("login");
            sb.append('=');
            sb.append(((this.login == null)?"<null>":this.login));
            sb.append(',');
            sb.append("id");
            sb.append('=');
            sb.append(((this.id == null)?"<null>":this.id));
            sb.append(',');
            sb.append("nodeId");
            sb.append('=');
            sb.append(((this.nodeId == null)?"<null>":this.nodeId));
            sb.append(',');
            sb.append("avatarUrl");
            sb.append('=');
            sb.append(((this.avatarUrl == null)?"<null>":this.avatarUrl));
            sb.append(',');
            sb.append("gravatarId");
            sb.append('=');
            sb.append(((this.gravatarId == null)?"<null>":this.gravatarId));
            sb.append(',');
            sb.append("url");
            sb.append('=');
            sb.append(((this.url == null)?"<null>":this.url));
            sb.append(',');
            sb.append("htmlUrl");
            sb.append('=');
            sb.append(((this.htmlUrl == null)?"<null>":this.htmlUrl));
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
            sb.append("starredUrl");
            sb.append('=');
            sb.append(((this.starredUrl == null)?"<null>":this.starredUrl));
            sb.append(',');
            sb.append("subscriptionsUrl");
            sb.append('=');
            sb.append(((this.subscriptionsUrl == null)?"<null>":this.subscriptionsUrl));
            sb.append(',');
            sb.append("organizationsUrl");
            sb.append('=');
            sb.append(((this.organizationsUrl == null)?"<null>":this.organizationsUrl));
            sb.append(',');
            sb.append("reposUrl");
            sb.append('=');
            sb.append(((this.reposUrl == null)?"<null>":this.reposUrl));
            sb.append(',');
            sb.append("eventsUrl");
            sb.append('=');
            sb.append(((this.eventsUrl == null)?"<null>":this.eventsUrl));
            sb.append(',');
            sb.append("receivedEventsUrl");
            sb.append('=');
            sb.append(((this.receivedEventsUrl == null)?"<null>":this.receivedEventsUrl));
            sb.append(',');
            sb.append("type");
            sb.append('=');
            sb.append(((this.type == null)?"<null>":this.type));
            sb.append(',');
            sb.append("siteAdmin");
            sb.append('=');
            sb.append(((this.siteAdmin == null)?"<null>":this.siteAdmin));
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
            result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
            result = ((result* 31)+((this.starredUrl == null)? 0 :this.starredUrl.hashCode()));
            result = ((result* 31)+((this.gravatarId == null)? 0 :this.gravatarId.hashCode()));
            result = ((result* 31)+((this.followersUrl == null)? 0 :this.followersUrl.hashCode()));
            result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
            result = ((result* 31)+((this.eventsUrl == null)? 0 :this.eventsUrl.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.nodeId == null)? 0 :this.nodeId.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof GetPullRequestResponse.Owner2) == false) {
                return false;
            }
            GetPullRequestResponse.Owner2 rhs = ((GetPullRequestResponse.Owner2) other);
            return ((((((((((((((((((((this.receivedEventsUrl == rhs.receivedEventsUrl)||((this.receivedEventsUrl!= null)&&this.receivedEventsUrl.equals(rhs.receivedEventsUrl)))&&((this.siteAdmin == rhs.siteAdmin)||((this.siteAdmin!= null)&&this.siteAdmin.equals(rhs.siteAdmin))))&&((this.followingUrl == rhs.followingUrl)||((this.followingUrl!= null)&&this.followingUrl.equals(rhs.followingUrl))))&&((this.gistsUrl == rhs.gistsUrl)||((this.gistsUrl!= null)&&this.gistsUrl.equals(rhs.gistsUrl))))&&((this.avatarUrl == rhs.avatarUrl)||((this.avatarUrl!= null)&&this.avatarUrl.equals(rhs.avatarUrl))))&&((this.organizationsUrl == rhs.organizationsUrl)||((this.organizationsUrl!= null)&&this.organizationsUrl.equals(rhs.organizationsUrl))))&&((this.reposUrl == rhs.reposUrl)||((this.reposUrl!= null)&&this.reposUrl.equals(rhs.reposUrl))))&&((this.htmlUrl == rhs.htmlUrl)||((this.htmlUrl!= null)&&this.htmlUrl.equals(rhs.htmlUrl))))&&((this.subscriptionsUrl == rhs.subscriptionsUrl)||((this.subscriptionsUrl!= null)&&this.subscriptionsUrl.equals(rhs.subscriptionsUrl))))&&((this.login == rhs.login)||((this.login!= null)&&this.login.equals(rhs.login))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))))&&((this.starredUrl == rhs.starredUrl)||((this.starredUrl!= null)&&this.starredUrl.equals(rhs.starredUrl))))&&((this.gravatarId == rhs.gravatarId)||((this.gravatarId!= null)&&this.gravatarId.equals(rhs.gravatarId))))&&((this.followersUrl == rhs.followersUrl)||((this.followersUrl!= null)&&this.followersUrl.equals(rhs.followersUrl))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.eventsUrl == rhs.eventsUrl)||((this.eventsUrl!= null)&&this.eventsUrl.equals(rhs.eventsUrl))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.nodeId == rhs.nodeId)||((this.nodeId!= null)&&this.nodeId.equals(rhs.nodeId))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "admin",
        "push",
        "pull"
    })
    @Generated("jsonschema2pojo")
    public static class Permissions {

        @JsonProperty("admin")
        private Boolean admin;
        @JsonProperty("push")
        private Boolean push;
        @JsonProperty("pull")
        private Boolean pull;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Permissions() {
        }

        /**
         * 
         * @param pull
         * @param admin
         * @param push
         */
        public Permissions(Boolean admin, Boolean push, Boolean pull) {
            super();
            this.admin = admin;
            this.push = push;
            this.pull = pull;
        }

        @JsonProperty("admin")
        public Boolean getAdmin() {
            return admin;
        }

        @JsonProperty("admin")
        public void setAdmin(Boolean admin) {
            this.admin = admin;
        }

        @JsonProperty("push")
        public Boolean getPush() {
            return push;
        }

        @JsonProperty("push")
        public void setPush(Boolean push) {
            this.push = push;
        }

        @JsonProperty("pull")
        public Boolean getPull() {
            return pull;
        }

        @JsonProperty("pull")
        public void setPull(Boolean pull) {
            this.pull = pull;
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
            sb.append(GetPullRequestResponse.Permissions.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("admin");
            sb.append('=');
            sb.append(((this.admin == null)?"<null>":this.admin));
            sb.append(',');
            sb.append("push");
            sb.append('=');
            sb.append(((this.push == null)?"<null>":this.push));
            sb.append(',');
            sb.append("pull");
            sb.append('=');
            sb.append(((this.pull == null)?"<null>":this.pull));
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
            result = ((result* 31)+((this.admin == null)? 0 :this.admin.hashCode()));
            result = ((result* 31)+((this.pull == null)? 0 :this.pull.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.push == null)? 0 :this.push.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof GetPullRequestResponse.Permissions) == false) {
                return false;
            }
            GetPullRequestResponse.Permissions rhs = ((GetPullRequestResponse.Permissions) other);
            return (((((this.admin == rhs.admin)||((this.admin!= null)&&this.admin.equals(rhs.admin)))&&((this.pull == rhs.pull)||((this.pull!= null)&&this.pull.equals(rhs.pull))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.push == rhs.push)||((this.push!= null)&&this.push.equals(rhs.push))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "admin",
        "push",
        "pull"
    })
    @Generated("jsonschema2pojo")
    public static class Permissions2 {

        @JsonProperty("admin")
        private Boolean admin;
        @JsonProperty("push")
        private Boolean push;
        @JsonProperty("pull")
        private Boolean pull;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Permissions2() {
        }

        /**
         * 
         * @param pull
         * @param admin
         * @param push
         */
        public Permissions2(Boolean admin, Boolean push, Boolean pull) {
            super();
            this.admin = admin;
            this.push = push;
            this.pull = pull;
        }

        @JsonProperty("admin")
        public Boolean getAdmin() {
            return admin;
        }

        @JsonProperty("admin")
        public void setAdmin(Boolean admin) {
            this.admin = admin;
        }

        @JsonProperty("push")
        public Boolean getPush() {
            return push;
        }

        @JsonProperty("push")
        public void setPush(Boolean push) {
            this.push = push;
        }

        @JsonProperty("pull")
        public Boolean getPull() {
            return pull;
        }

        @JsonProperty("pull")
        public void setPull(Boolean pull) {
            this.pull = pull;
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
            sb.append(GetPullRequestResponse.Permissions2 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("admin");
            sb.append('=');
            sb.append(((this.admin == null)?"<null>":this.admin));
            sb.append(',');
            sb.append("push");
            sb.append('=');
            sb.append(((this.push == null)?"<null>":this.push));
            sb.append(',');
            sb.append("pull");
            sb.append('=');
            sb.append(((this.pull == null)?"<null>":this.pull));
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
            result = ((result* 31)+((this.admin == null)? 0 :this.admin.hashCode()));
            result = ((result* 31)+((this.pull == null)? 0 :this.pull.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.push == null)? 0 :this.push.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof GetPullRequestResponse.Permissions2) == false) {
                return false;
            }
            GetPullRequestResponse.Permissions2 rhs = ((GetPullRequestResponse.Permissions2) other);
            return (((((this.admin == rhs.admin)||((this.admin!= null)&&this.admin.equals(rhs.admin)))&&((this.pull == rhs.pull)||((this.pull!= null)&&this.pull.equals(rhs.pull))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.push == rhs.push)||((this.push!= null)&&this.push.equals(rhs.push))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "id",
        "node_id",
        "name",
        "full_name",
        "owner",
        "private",
        "html_url",
        "description",
        "fork",
        "url",
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
        "downloads_url",
        "events_url",
        "forks_url",
        "git_commits_url",
        "git_refs_url",
        "git_tags_url",
        "git_url",
        "issue_comment_url",
        "issue_events_url",
        "issues_url",
        "keys_url",
        "labels_url",
        "languages_url",
        "merges_url",
        "milestones_url",
        "notifications_url",
        "pulls_url",
        "releases_url",
        "ssh_url",
        "stargazers_url",
        "statuses_url",
        "subscribers_url",
        "subscription_url",
        "tags_url",
        "teams_url",
        "trees_url",
        "clone_url",
        "mirror_url",
        "hooks_url",
        "svn_url",
        "homepage",
        "language",
        "forks_count",
        "stargazers_count",
        "watchers_count",
        "size",
        "default_branch",
        "open_issues_count",
        "topics",
        "has_issues",
        "has_projects",
        "has_wiki",
        "has_pages",
        "has_downloads",
        "archived",
        "disabled",
        "pushed_at",
        "created_at",
        "updated_at",
        "permissions",
        "allow_rebase_merge",
        "temp_clone_token",
        "allow_squash_merge",
        "allow_merge_commit",
        "allow_forking",
        "forks",
        "open_issues",
        "license",
        "watchers"
    })
    @Generated("jsonschema2pojo")
    public static class Repo {

        @JsonProperty("id")
        private Long id;
        @JsonProperty("node_id")
        private String nodeId;
        @JsonProperty("name")
        private String name;
        @JsonProperty("full_name")
        private String fullName;
        @JsonProperty("owner")
        private GetPullRequestResponse.Owner owner;
        @JsonProperty("private")
        private Boolean _private;
        @JsonProperty("html_url")
        private String htmlUrl;
        @JsonProperty("description")
        private String description;
        @JsonProperty("fork")
        private Boolean fork;
        @JsonProperty("url")
        private String url;
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
        @JsonProperty("downloads_url")
        private String downloadsUrl;
        @JsonProperty("events_url")
        private String eventsUrl;
        @JsonProperty("forks_url")
        private String forksUrl;
        @JsonProperty("git_commits_url")
        private String gitCommitsUrl;
        @JsonProperty("git_refs_url")
        private String gitRefsUrl;
        @JsonProperty("git_tags_url")
        private String gitTagsUrl;
        @JsonProperty("git_url")
        private String gitUrl;
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
        @JsonProperty("notifications_url")
        private String notificationsUrl;
        @JsonProperty("pulls_url")
        private String pullsUrl;
        @JsonProperty("releases_url")
        private String releasesUrl;
        @JsonProperty("ssh_url")
        private String sshUrl;
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
        @JsonProperty("clone_url")
        private String cloneUrl;
        @JsonProperty("mirror_url")
        private String mirrorUrl;
        @JsonProperty("hooks_url")
        private String hooksUrl;
        @JsonProperty("svn_url")
        private String svnUrl;
        @JsonProperty("homepage")
        private String homepage;
        @JsonProperty("language")
        private Object language;
        @JsonProperty("forks_count")
        private Long forksCount;
        @JsonProperty("stargazers_count")
        private Long stargazersCount;
        @JsonProperty("watchers_count")
        private Long watchersCount;
        @JsonProperty("size")
        private Long size;
        @JsonProperty("default_branch")
        private String defaultBranch;
        @JsonProperty("open_issues_count")
        private Long openIssuesCount;
        @JsonProperty("topics")
        private List<String> topics = new ArrayList<String>();
        @JsonProperty("has_issues")
        private Boolean hasIssues;
        @JsonProperty("has_projects")
        private Boolean hasProjects;
        @JsonProperty("has_wiki")
        private Boolean hasWiki;
        @JsonProperty("has_pages")
        private Boolean hasPages;
        @JsonProperty("has_downloads")
        private Boolean hasDownloads;
        @JsonProperty("archived")
        private Boolean archived;
        @JsonProperty("disabled")
        private Boolean disabled;
        @JsonProperty("pushed_at")
        private String pushedAt;
        @JsonProperty("created_at")
        private String createdAt;
        @JsonProperty("updated_at")
        private String updatedAt;
        @JsonProperty("permissions")
        private GetPullRequestResponse.Permissions permissions;
        @JsonProperty("allow_rebase_merge")
        private Boolean allowRebaseMerge;
        @JsonProperty("temp_clone_token")
        private String tempCloneToken;
        @JsonProperty("allow_squash_merge")
        private Boolean allowSquashMerge;
        @JsonProperty("allow_merge_commit")
        private Boolean allowMergeCommit;
        @JsonProperty("allow_forking")
        private Boolean allowForking;
        @JsonProperty("forks")
        private Long forks;
        @JsonProperty("open_issues")
        private Long openIssues;
        @JsonProperty("license")
        private GetPullRequestResponse.License license;
        @JsonProperty("watchers")
        private Long watchers;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Repo() {
        }

        /**
         * 
         * @param sshUrl
         * @param archiveUrl
         * @param languagesUrl
         * @param language
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
         * @param permissions
         * @param issueCommentUrl
         * @param statusesUrl
         * @param id
         * @param collaboratorsUrl
         * @param updatedAt
         * @param allowMergeCommit
         * @param forks
         * @param allowForking
         * @param labelsUrl
         * @param defaultBranch
         * @param tempCloneToken
         * @param keysUrl
         * @param downloadsUrl
         * @param contentsUrl
         * @param pushedAt
         * @param tagsUrl
         * @param license
         * @param commentsUrl
         * @param size
         * @param treesUrl
         * @param allowRebaseMerge
         * @param name
         * @param mergesUrl
         * @param nodeId
         * @param teamsUrl
         * @param blobsUrl
         * @param issueEventsUrl
         * @param hasPages
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
         * @param topics
         * @param htmlUrl
         * @param stargazersUrl
         * @param fullName
         * @param svnUrl
         * @param url
         * @param pullsUrl
         * @param mirrorUrl
         * @param hasDownloads
         * @param fork
         * @param allowSquashMerge
         * @param hasProjects
         * @param deploymentsUrl
         * @param eventsUrl
         * @param gitTagsUrl
         * @param notificationsUrl
         * @param gitUrl
         * @param subscriptionUrl
         * @param homepage
         */
        public Repo(Long id, String nodeId, String name, String fullName, GetPullRequestResponse.Owner owner, Boolean _private, String htmlUrl, String description, Boolean fork, String url, String archiveUrl, String assigneesUrl, String blobsUrl, String branchesUrl, String collaboratorsUrl, String commentsUrl, String commitsUrl, String compareUrl, String contentsUrl, String contributorsUrl, String deploymentsUrl, String downloadsUrl, String eventsUrl, String forksUrl, String gitCommitsUrl, String gitRefsUrl, String gitTagsUrl, String gitUrl, String issueCommentUrl, String issueEventsUrl, String issuesUrl, String keysUrl, String labelsUrl, String languagesUrl, String mergesUrl, String milestonesUrl, String notificationsUrl, String pullsUrl, String releasesUrl, String sshUrl, String stargazersUrl, String statusesUrl, String subscribersUrl, String subscriptionUrl, String tagsUrl, String teamsUrl, String treesUrl, String cloneUrl, String mirrorUrl, String hooksUrl, String svnUrl, String homepage, Object language, Long forksCount, Long stargazersCount, Long watchersCount, Long size, String defaultBranch, Long openIssuesCount, List<String> topics, Boolean hasIssues, Boolean hasProjects, Boolean hasWiki, Boolean hasPages, Boolean hasDownloads, Boolean archived, Boolean disabled, String pushedAt, String createdAt, String updatedAt, GetPullRequestResponse.Permissions permissions, Boolean allowRebaseMerge, String tempCloneToken, Boolean allowSquashMerge, Boolean allowMergeCommit, Boolean allowForking, Long forks, Long openIssues, GetPullRequestResponse.License license, Long watchers) {
            super();
            this.id = id;
            this.nodeId = nodeId;
            this.name = name;
            this.fullName = fullName;
            this.owner = owner;
            this._private = _private;
            this.htmlUrl = htmlUrl;
            this.description = description;
            this.fork = fork;
            this.url = url;
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
            this.downloadsUrl = downloadsUrl;
            this.eventsUrl = eventsUrl;
            this.forksUrl = forksUrl;
            this.gitCommitsUrl = gitCommitsUrl;
            this.gitRefsUrl = gitRefsUrl;
            this.gitTagsUrl = gitTagsUrl;
            this.gitUrl = gitUrl;
            this.issueCommentUrl = issueCommentUrl;
            this.issueEventsUrl = issueEventsUrl;
            this.issuesUrl = issuesUrl;
            this.keysUrl = keysUrl;
            this.labelsUrl = labelsUrl;
            this.languagesUrl = languagesUrl;
            this.mergesUrl = mergesUrl;
            this.milestonesUrl = milestonesUrl;
            this.notificationsUrl = notificationsUrl;
            this.pullsUrl = pullsUrl;
            this.releasesUrl = releasesUrl;
            this.sshUrl = sshUrl;
            this.stargazersUrl = stargazersUrl;
            this.statusesUrl = statusesUrl;
            this.subscribersUrl = subscribersUrl;
            this.subscriptionUrl = subscriptionUrl;
            this.tagsUrl = tagsUrl;
            this.teamsUrl = teamsUrl;
            this.treesUrl = treesUrl;
            this.cloneUrl = cloneUrl;
            this.mirrorUrl = mirrorUrl;
            this.hooksUrl = hooksUrl;
            this.svnUrl = svnUrl;
            this.homepage = homepage;
            this.language = language;
            this.forksCount = forksCount;
            this.stargazersCount = stargazersCount;
            this.watchersCount = watchersCount;
            this.size = size;
            this.defaultBranch = defaultBranch;
            this.openIssuesCount = openIssuesCount;
            this.topics = topics;
            this.hasIssues = hasIssues;
            this.hasProjects = hasProjects;
            this.hasWiki = hasWiki;
            this.hasPages = hasPages;
            this.hasDownloads = hasDownloads;
            this.archived = archived;
            this.disabled = disabled;
            this.pushedAt = pushedAt;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
            this.permissions = permissions;
            this.allowRebaseMerge = allowRebaseMerge;
            this.tempCloneToken = tempCloneToken;
            this.allowSquashMerge = allowSquashMerge;
            this.allowMergeCommit = allowMergeCommit;
            this.allowForking = allowForking;
            this.forks = forks;
            this.openIssues = openIssues;
            this.license = license;
            this.watchers = watchers;
        }

        @JsonProperty("id")
        public Long getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(Long id) {
            this.id = id;
        }

        @JsonProperty("node_id")
        public String getNodeId() {
            return nodeId;
        }

        @JsonProperty("node_id")
        public void setNodeId(String nodeId) {
            this.nodeId = nodeId;
        }

        @JsonProperty("name")
        public String getName() {
            return name;
        }

        @JsonProperty("name")
        public void setName(String name) {
            this.name = name;
        }

        @JsonProperty("full_name")
        public String getFullName() {
            return fullName;
        }

        @JsonProperty("full_name")
        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        @JsonProperty("owner")
        public GetPullRequestResponse.Owner getOwner() {
            return owner;
        }

        @JsonProperty("owner")
        public void setOwner(GetPullRequestResponse.Owner owner) {
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

        @JsonProperty("html_url")
        public String getHtmlUrl() {
            return htmlUrl;
        }

        @JsonProperty("html_url")
        public void setHtmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
        }

        @JsonProperty("description")
        public String getDescription() {
            return description;
        }

        @JsonProperty("description")
        public void setDescription(String description) {
            this.description = description;
        }

        @JsonProperty("fork")
        public Boolean getFork() {
            return fork;
        }

        @JsonProperty("fork")
        public void setFork(Boolean fork) {
            this.fork = fork;
        }

        @JsonProperty("url")
        public String getUrl() {
            return url;
        }

        @JsonProperty("url")
        public void setUrl(String url) {
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

        @JsonProperty("forks_url")
        public String getForksUrl() {
            return forksUrl;
        }

        @JsonProperty("forks_url")
        public void setForksUrl(String forksUrl) {
            this.forksUrl = forksUrl;
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

        @JsonProperty("notifications_url")
        public String getNotificationsUrl() {
            return notificationsUrl;
        }

        @JsonProperty("notifications_url")
        public void setNotificationsUrl(String notificationsUrl) {
            this.notificationsUrl = notificationsUrl;
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

        @JsonProperty("ssh_url")
        public String getSshUrl() {
            return sshUrl;
        }

        @JsonProperty("ssh_url")
        public void setSshUrl(String sshUrl) {
            this.sshUrl = sshUrl;
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

        @JsonProperty("clone_url")
        public String getCloneUrl() {
            return cloneUrl;
        }

        @JsonProperty("clone_url")
        public void setCloneUrl(String cloneUrl) {
            this.cloneUrl = cloneUrl;
        }

        @JsonProperty("mirror_url")
        public String getMirrorUrl() {
            return mirrorUrl;
        }

        @JsonProperty("mirror_url")
        public void setMirrorUrl(String mirrorUrl) {
            this.mirrorUrl = mirrorUrl;
        }

        @JsonProperty("hooks_url")
        public String getHooksUrl() {
            return hooksUrl;
        }

        @JsonProperty("hooks_url")
        public void setHooksUrl(String hooksUrl) {
            this.hooksUrl = hooksUrl;
        }

        @JsonProperty("svn_url")
        public String getSvnUrl() {
            return svnUrl;
        }

        @JsonProperty("svn_url")
        public void setSvnUrl(String svnUrl) {
            this.svnUrl = svnUrl;
        }

        @JsonProperty("homepage")
        public String getHomepage() {
            return homepage;
        }

        @JsonProperty("homepage")
        public void setHomepage(String homepage) {
            this.homepage = homepage;
        }

        @JsonProperty("language")
        public Object getLanguage() {
            return language;
        }

        @JsonProperty("language")
        public void setLanguage(Object language) {
            this.language = language;
        }

        @JsonProperty("forks_count")
        public Long getForksCount() {
            return forksCount;
        }

        @JsonProperty("forks_count")
        public void setForksCount(Long forksCount) {
            this.forksCount = forksCount;
        }

        @JsonProperty("stargazers_count")
        public Long getStargazersCount() {
            return stargazersCount;
        }

        @JsonProperty("stargazers_count")
        public void setStargazersCount(Long stargazersCount) {
            this.stargazersCount = stargazersCount;
        }

        @JsonProperty("watchers_count")
        public Long getWatchersCount() {
            return watchersCount;
        }

        @JsonProperty("watchers_count")
        public void setWatchersCount(Long watchersCount) {
            this.watchersCount = watchersCount;
        }

        @JsonProperty("size")
        public Long getSize() {
            return size;
        }

        @JsonProperty("size")
        public void setSize(Long size) {
            this.size = size;
        }

        @JsonProperty("default_branch")
        public String getDefaultBranch() {
            return defaultBranch;
        }

        @JsonProperty("default_branch")
        public void setDefaultBranch(String defaultBranch) {
            this.defaultBranch = defaultBranch;
        }

        @JsonProperty("open_issues_count")
        public Long getOpenIssuesCount() {
            return openIssuesCount;
        }

        @JsonProperty("open_issues_count")
        public void setOpenIssuesCount(Long openIssuesCount) {
            this.openIssuesCount = openIssuesCount;
        }

        @JsonProperty("topics")
        public List<String> getTopics() {
            return topics;
        }

        @JsonProperty("topics")
        public void setTopics(List<String> topics) {
            this.topics = topics;
        }

        @JsonProperty("has_issues")
        public Boolean getHasIssues() {
            return hasIssues;
        }

        @JsonProperty("has_issues")
        public void setHasIssues(Boolean hasIssues) {
            this.hasIssues = hasIssues;
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

        @JsonProperty("has_pages")
        public Boolean getHasPages() {
            return hasPages;
        }

        @JsonProperty("has_pages")
        public void setHasPages(Boolean hasPages) {
            this.hasPages = hasPages;
        }

        @JsonProperty("has_downloads")
        public Boolean getHasDownloads() {
            return hasDownloads;
        }

        @JsonProperty("has_downloads")
        public void setHasDownloads(Boolean hasDownloads) {
            this.hasDownloads = hasDownloads;
        }

        @JsonProperty("archived")
        public Boolean getArchived() {
            return archived;
        }

        @JsonProperty("archived")
        public void setArchived(Boolean archived) {
            this.archived = archived;
        }

        @JsonProperty("disabled")
        public Boolean getDisabled() {
            return disabled;
        }

        @JsonProperty("disabled")
        public void setDisabled(Boolean disabled) {
            this.disabled = disabled;
        }

        @JsonProperty("pushed_at")
        public String getPushedAt() {
            return pushedAt;
        }

        @JsonProperty("pushed_at")
        public void setPushedAt(String pushedAt) {
            this.pushedAt = pushedAt;
        }

        @JsonProperty("created_at")
        public String getCreatedAt() {
            return createdAt;
        }

        @JsonProperty("created_at")
        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        @JsonProperty("updated_at")
        public String getUpdatedAt() {
            return updatedAt;
        }

        @JsonProperty("updated_at")
        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        @JsonProperty("permissions")
        public GetPullRequestResponse.Permissions getPermissions() {
            return permissions;
        }

        @JsonProperty("permissions")
        public void setPermissions(GetPullRequestResponse.Permissions permissions) {
            this.permissions = permissions;
        }

        @JsonProperty("allow_rebase_merge")
        public Boolean getAllowRebaseMerge() {
            return allowRebaseMerge;
        }

        @JsonProperty("allow_rebase_merge")
        public void setAllowRebaseMerge(Boolean allowRebaseMerge) {
            this.allowRebaseMerge = allowRebaseMerge;
        }

        @JsonProperty("temp_clone_token")
        public String getTempCloneToken() {
            return tempCloneToken;
        }

        @JsonProperty("temp_clone_token")
        public void setTempCloneToken(String tempCloneToken) {
            this.tempCloneToken = tempCloneToken;
        }

        @JsonProperty("allow_squash_merge")
        public Boolean getAllowSquashMerge() {
            return allowSquashMerge;
        }

        @JsonProperty("allow_squash_merge")
        public void setAllowSquashMerge(Boolean allowSquashMerge) {
            this.allowSquashMerge = allowSquashMerge;
        }

        @JsonProperty("allow_merge_commit")
        public Boolean getAllowMergeCommit() {
            return allowMergeCommit;
        }

        @JsonProperty("allow_merge_commit")
        public void setAllowMergeCommit(Boolean allowMergeCommit) {
            this.allowMergeCommit = allowMergeCommit;
        }

        @JsonProperty("allow_forking")
        public Boolean getAllowForking() {
            return allowForking;
        }

        @JsonProperty("allow_forking")
        public void setAllowForking(Boolean allowForking) {
            this.allowForking = allowForking;
        }

        @JsonProperty("forks")
        public Long getForks() {
            return forks;
        }

        @JsonProperty("forks")
        public void setForks(Long forks) {
            this.forks = forks;
        }

        @JsonProperty("open_issues")
        public Long getOpenIssues() {
            return openIssues;
        }

        @JsonProperty("open_issues")
        public void setOpenIssues(Long openIssues) {
            this.openIssues = openIssues;
        }

        @JsonProperty("license")
        public GetPullRequestResponse.License getLicense() {
            return license;
        }

        @JsonProperty("license")
        public void setLicense(GetPullRequestResponse.License license) {
            this.license = license;
        }

        @JsonProperty("watchers")
        public Long getWatchers() {
            return watchers;
        }

        @JsonProperty("watchers")
        public void setWatchers(Long watchers) {
            this.watchers = watchers;
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
            sb.append(GetPullRequestResponse.Repo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("id");
            sb.append('=');
            sb.append(((this.id == null)?"<null>":this.id));
            sb.append(',');
            sb.append("nodeId");
            sb.append('=');
            sb.append(((this.nodeId == null)?"<null>":this.nodeId));
            sb.append(',');
            sb.append("name");
            sb.append('=');
            sb.append(((this.name == null)?"<null>":this.name));
            sb.append(',');
            sb.append("fullName");
            sb.append('=');
            sb.append(((this.fullName == null)?"<null>":this.fullName));
            sb.append(',');
            sb.append("owner");
            sb.append('=');
            sb.append(((this.owner == null)?"<null>":this.owner));
            sb.append(',');
            sb.append("_private");
            sb.append('=');
            sb.append(((this._private == null)?"<null>":this._private));
            sb.append(',');
            sb.append("htmlUrl");
            sb.append('=');
            sb.append(((this.htmlUrl == null)?"<null>":this.htmlUrl));
            sb.append(',');
            sb.append("description");
            sb.append('=');
            sb.append(((this.description == null)?"<null>":this.description));
            sb.append(',');
            sb.append("fork");
            sb.append('=');
            sb.append(((this.fork == null)?"<null>":this.fork));
            sb.append(',');
            sb.append("url");
            sb.append('=');
            sb.append(((this.url == null)?"<null>":this.url));
            sb.append(',');
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
            sb.append("downloadsUrl");
            sb.append('=');
            sb.append(((this.downloadsUrl == null)?"<null>":this.downloadsUrl));
            sb.append(',');
            sb.append("eventsUrl");
            sb.append('=');
            sb.append(((this.eventsUrl == null)?"<null>":this.eventsUrl));
            sb.append(',');
            sb.append("forksUrl");
            sb.append('=');
            sb.append(((this.forksUrl == null)?"<null>":this.forksUrl));
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
            sb.append("notificationsUrl");
            sb.append('=');
            sb.append(((this.notificationsUrl == null)?"<null>":this.notificationsUrl));
            sb.append(',');
            sb.append("pullsUrl");
            sb.append('=');
            sb.append(((this.pullsUrl == null)?"<null>":this.pullsUrl));
            sb.append(',');
            sb.append("releasesUrl");
            sb.append('=');
            sb.append(((this.releasesUrl == null)?"<null>":this.releasesUrl));
            sb.append(',');
            sb.append("sshUrl");
            sb.append('=');
            sb.append(((this.sshUrl == null)?"<null>":this.sshUrl));
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
            sb.append("cloneUrl");
            sb.append('=');
            sb.append(((this.cloneUrl == null)?"<null>":this.cloneUrl));
            sb.append(',');
            sb.append("mirrorUrl");
            sb.append('=');
            sb.append(((this.mirrorUrl == null)?"<null>":this.mirrorUrl));
            sb.append(',');
            sb.append("hooksUrl");
            sb.append('=');
            sb.append(((this.hooksUrl == null)?"<null>":this.hooksUrl));
            sb.append(',');
            sb.append("svnUrl");
            sb.append('=');
            sb.append(((this.svnUrl == null)?"<null>":this.svnUrl));
            sb.append(',');
            sb.append("homepage");
            sb.append('=');
            sb.append(((this.homepage == null)?"<null>":this.homepage));
            sb.append(',');
            sb.append("language");
            sb.append('=');
            sb.append(((this.language == null)?"<null>":this.language));
            sb.append(',');
            sb.append("forksCount");
            sb.append('=');
            sb.append(((this.forksCount == null)?"<null>":this.forksCount));
            sb.append(',');
            sb.append("stargazersCount");
            sb.append('=');
            sb.append(((this.stargazersCount == null)?"<null>":this.stargazersCount));
            sb.append(',');
            sb.append("watchersCount");
            sb.append('=');
            sb.append(((this.watchersCount == null)?"<null>":this.watchersCount));
            sb.append(',');
            sb.append("size");
            sb.append('=');
            sb.append(((this.size == null)?"<null>":this.size));
            sb.append(',');
            sb.append("defaultBranch");
            sb.append('=');
            sb.append(((this.defaultBranch == null)?"<null>":this.defaultBranch));
            sb.append(',');
            sb.append("openIssuesCount");
            sb.append('=');
            sb.append(((this.openIssuesCount == null)?"<null>":this.openIssuesCount));
            sb.append(',');
            sb.append("topics");
            sb.append('=');
            sb.append(((this.topics == null)?"<null>":this.topics));
            sb.append(',');
            sb.append("hasIssues");
            sb.append('=');
            sb.append(((this.hasIssues == null)?"<null>":this.hasIssues));
            sb.append(',');
            sb.append("hasProjects");
            sb.append('=');
            sb.append(((this.hasProjects == null)?"<null>":this.hasProjects));
            sb.append(',');
            sb.append("hasWiki");
            sb.append('=');
            sb.append(((this.hasWiki == null)?"<null>":this.hasWiki));
            sb.append(',');
            sb.append("hasPages");
            sb.append('=');
            sb.append(((this.hasPages == null)?"<null>":this.hasPages));
            sb.append(',');
            sb.append("hasDownloads");
            sb.append('=');
            sb.append(((this.hasDownloads == null)?"<null>":this.hasDownloads));
            sb.append(',');
            sb.append("archived");
            sb.append('=');
            sb.append(((this.archived == null)?"<null>":this.archived));
            sb.append(',');
            sb.append("disabled");
            sb.append('=');
            sb.append(((this.disabled == null)?"<null>":this.disabled));
            sb.append(',');
            sb.append("pushedAt");
            sb.append('=');
            sb.append(((this.pushedAt == null)?"<null>":this.pushedAt));
            sb.append(',');
            sb.append("createdAt");
            sb.append('=');
            sb.append(((this.createdAt == null)?"<null>":this.createdAt));
            sb.append(',');
            sb.append("updatedAt");
            sb.append('=');
            sb.append(((this.updatedAt == null)?"<null>":this.updatedAt));
            sb.append(',');
            sb.append("permissions");
            sb.append('=');
            sb.append(((this.permissions == null)?"<null>":this.permissions));
            sb.append(',');
            sb.append("allowRebaseMerge");
            sb.append('=');
            sb.append(((this.allowRebaseMerge == null)?"<null>":this.allowRebaseMerge));
            sb.append(',');
            sb.append("tempCloneToken");
            sb.append('=');
            sb.append(((this.tempCloneToken == null)?"<null>":this.tempCloneToken));
            sb.append(',');
            sb.append("allowSquashMerge");
            sb.append('=');
            sb.append(((this.allowSquashMerge == null)?"<null>":this.allowSquashMerge));
            sb.append(',');
            sb.append("allowMergeCommit");
            sb.append('=');
            sb.append(((this.allowMergeCommit == null)?"<null>":this.allowMergeCommit));
            sb.append(',');
            sb.append("allowForking");
            sb.append('=');
            sb.append(((this.allowForking == null)?"<null>":this.allowForking));
            sb.append(',');
            sb.append("forks");
            sb.append('=');
            sb.append(((this.forks == null)?"<null>":this.forks));
            sb.append(',');
            sb.append("openIssues");
            sb.append('=');
            sb.append(((this.openIssues == null)?"<null>":this.openIssues));
            sb.append(',');
            sb.append("license");
            sb.append('=');
            sb.append(((this.license == null)?"<null>":this.license));
            sb.append(',');
            sb.append("watchers");
            sb.append('=');
            sb.append(((this.watchers == null)?"<null>":this.watchers));
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
            result = ((result* 31)+((this.languagesUrl == null)? 0 :this.languagesUrl.hashCode()));
            result = ((result* 31)+((this.language == null)? 0 :this.language.hashCode()));
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
            result = ((result* 31)+((this.permissions == null)? 0 :this.permissions.hashCode()));
            result = ((result* 31)+((this.issueCommentUrl == null)? 0 :this.issueCommentUrl.hashCode()));
            result = ((result* 31)+((this.statusesUrl == null)? 0 :this.statusesUrl.hashCode()));
            result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
            result = ((result* 31)+((this.collaboratorsUrl == null)? 0 :this.collaboratorsUrl.hashCode()));
            result = ((result* 31)+((this.updatedAt == null)? 0 :this.updatedAt.hashCode()));
            result = ((result* 31)+((this.allowMergeCommit == null)? 0 :this.allowMergeCommit.hashCode()));
            result = ((result* 31)+((this.forks == null)? 0 :this.forks.hashCode()));
            result = ((result* 31)+((this.allowForking == null)? 0 :this.allowForking.hashCode()));
            result = ((result* 31)+((this.labelsUrl == null)? 0 :this.labelsUrl.hashCode()));
            result = ((result* 31)+((this.defaultBranch == null)? 0 :this.defaultBranch.hashCode()));
            result = ((result* 31)+((this.tempCloneToken == null)? 0 :this.tempCloneToken.hashCode()));
            result = ((result* 31)+((this.keysUrl == null)? 0 :this.keysUrl.hashCode()));
            result = ((result* 31)+((this.downloadsUrl == null)? 0 :this.downloadsUrl.hashCode()));
            result = ((result* 31)+((this.contentsUrl == null)? 0 :this.contentsUrl.hashCode()));
            result = ((result* 31)+((this.pushedAt == null)? 0 :this.pushedAt.hashCode()));
            result = ((result* 31)+((this.tagsUrl == null)? 0 :this.tagsUrl.hashCode()));
            result = ((result* 31)+((this.license == null)? 0 :this.license.hashCode()));
            result = ((result* 31)+((this.commentsUrl == null)? 0 :this.commentsUrl.hashCode()));
            result = ((result* 31)+((this.size == null)? 0 :this.size.hashCode()));
            result = ((result* 31)+((this.treesUrl == null)? 0 :this.treesUrl.hashCode()));
            result = ((result* 31)+((this.allowRebaseMerge == null)? 0 :this.allowRebaseMerge.hashCode()));
            result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.mergesUrl == null)? 0 :this.mergesUrl.hashCode()));
            result = ((result* 31)+((this.nodeId == null)? 0 :this.nodeId.hashCode()));
            result = ((result* 31)+((this.teamsUrl == null)? 0 :this.teamsUrl.hashCode()));
            result = ((result* 31)+((this.blobsUrl == null)? 0 :this.blobsUrl.hashCode()));
            result = ((result* 31)+((this.issueEventsUrl == null)? 0 :this.issueEventsUrl.hashCode()));
            result = ((result* 31)+((this.hasPages == null)? 0 :this.hasPages.hashCode()));
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
            result = ((result* 31)+((this.topics == null)? 0 :this.topics.hashCode()));
            result = ((result* 31)+((this.htmlUrl == null)? 0 :this.htmlUrl.hashCode()));
            result = ((result* 31)+((this.stargazersUrl == null)? 0 :this.stargazersUrl.hashCode()));
            result = ((result* 31)+((this.fullName == null)? 0 :this.fullName.hashCode()));
            result = ((result* 31)+((this.svnUrl == null)? 0 :this.svnUrl.hashCode()));
            result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
            result = ((result* 31)+((this.pullsUrl == null)? 0 :this.pullsUrl.hashCode()));
            result = ((result* 31)+((this.mirrorUrl == null)? 0 :this.mirrorUrl.hashCode()));
            result = ((result* 31)+((this.hasDownloads == null)? 0 :this.hasDownloads.hashCode()));
            result = ((result* 31)+((this.fork == null)? 0 :this.fork.hashCode()));
            result = ((result* 31)+((this.allowSquashMerge == null)? 0 :this.allowSquashMerge.hashCode()));
            result = ((result* 31)+((this.hasProjects == null)? 0 :this.hasProjects.hashCode()));
            result = ((result* 31)+((this.deploymentsUrl == null)? 0 :this.deploymentsUrl.hashCode()));
            result = ((result* 31)+((this.eventsUrl == null)? 0 :this.eventsUrl.hashCode()));
            result = ((result* 31)+((this.gitTagsUrl == null)? 0 :this.gitTagsUrl.hashCode()));
            result = ((result* 31)+((this.notificationsUrl == null)? 0 :this.notificationsUrl.hashCode()));
            result = ((result* 31)+((this.gitUrl == null)? 0 :this.gitUrl.hashCode()));
            result = ((result* 31)+((this.subscriptionUrl == null)? 0 :this.subscriptionUrl.hashCode()));
            result = ((result* 31)+((this.homepage == null)? 0 :this.homepage.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof GetPullRequestResponse.Repo) == false) {
                return false;
            }
            GetPullRequestResponse.Repo rhs = ((GetPullRequestResponse.Repo) other);
            return ((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((this.sshUrl == rhs.sshUrl)||((this.sshUrl!= null)&&this.sshUrl.equals(rhs.sshUrl)))&&((this.archiveUrl == rhs.archiveUrl)||((this.archiveUrl!= null)&&this.archiveUrl.equals(rhs.archiveUrl))))&&((this.languagesUrl == rhs.languagesUrl)||((this.languagesUrl!= null)&&this.languagesUrl.equals(rhs.languagesUrl))))&&((this.language == rhs.language)||((this.language!= null)&&this.language.equals(rhs.language))))&&((this.assigneesUrl == rhs.assigneesUrl)||((this.assigneesUrl!= null)&&this.assigneesUrl.equals(rhs.assigneesUrl))))&&((this.commitsUrl == rhs.commitsUrl)||((this.commitsUrl!= null)&&this.commitsUrl.equals(rhs.commitsUrl))))&&((this.openIssues == rhs.openIssues)||((this.openIssues!= null)&&this.openIssues.equals(rhs.openIssues))))&&((this.cloneUrl == rhs.cloneUrl)||((this.cloneUrl!= null)&&this.cloneUrl.equals(rhs.cloneUrl))))&&((this.forksCount == rhs.forksCount)||((this.forksCount!= null)&&this.forksCount.equals(rhs.forksCount))))&&((this.subscribersUrl == rhs.subscribersUrl)||((this.subscribersUrl!= null)&&this.subscribersUrl.equals(rhs.subscribersUrl))))&&((this.createdAt == rhs.createdAt)||((this.createdAt!= null)&&this.createdAt.equals(rhs.createdAt))))&&((this.forksUrl == rhs.forksUrl)||((this.forksUrl!= null)&&this.forksUrl.equals(rhs.forksUrl))))&&((this.watchersCount == rhs.watchersCount)||((this.watchersCount!= null)&&this.watchersCount.equals(rhs.watchersCount))))&&((this._private == rhs._private)||((this._private!= null)&&this._private.equals(rhs._private))))&&((this.permissions == rhs.permissions)||((this.permissions!= null)&&this.permissions.equals(rhs.permissions))))&&((this.issueCommentUrl == rhs.issueCommentUrl)||((this.issueCommentUrl!= null)&&this.issueCommentUrl.equals(rhs.issueCommentUrl))))&&((this.statusesUrl == rhs.statusesUrl)||((this.statusesUrl!= null)&&this.statusesUrl.equals(rhs.statusesUrl))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.collaboratorsUrl == rhs.collaboratorsUrl)||((this.collaboratorsUrl!= null)&&this.collaboratorsUrl.equals(rhs.collaboratorsUrl))))&&((this.updatedAt == rhs.updatedAt)||((this.updatedAt!= null)&&this.updatedAt.equals(rhs.updatedAt))))&&((this.allowMergeCommit == rhs.allowMergeCommit)||((this.allowMergeCommit!= null)&&this.allowMergeCommit.equals(rhs.allowMergeCommit))))&&((this.forks == rhs.forks)||((this.forks!= null)&&this.forks.equals(rhs.forks))))&&((this.allowForking == rhs.allowForking)||((this.allowForking!= null)&&this.allowForking.equals(rhs.allowForking))))&&((this.labelsUrl == rhs.labelsUrl)||((this.labelsUrl!= null)&&this.labelsUrl.equals(rhs.labelsUrl))))&&((this.defaultBranch == rhs.defaultBranch)||((this.defaultBranch!= null)&&this.defaultBranch.equals(rhs.defaultBranch))))&&((this.tempCloneToken == rhs.tempCloneToken)||((this.tempCloneToken!= null)&&this.tempCloneToken.equals(rhs.tempCloneToken))))&&((this.keysUrl == rhs.keysUrl)||((this.keysUrl!= null)&&this.keysUrl.equals(rhs.keysUrl))))&&((this.downloadsUrl == rhs.downloadsUrl)||((this.downloadsUrl!= null)&&this.downloadsUrl.equals(rhs.downloadsUrl))))&&((this.contentsUrl == rhs.contentsUrl)||((this.contentsUrl!= null)&&this.contentsUrl.equals(rhs.contentsUrl))))&&((this.pushedAt == rhs.pushedAt)||((this.pushedAt!= null)&&this.pushedAt.equals(rhs.pushedAt))))&&((this.tagsUrl == rhs.tagsUrl)||((this.tagsUrl!= null)&&this.tagsUrl.equals(rhs.tagsUrl))))&&((this.license == rhs.license)||((this.license!= null)&&this.license.equals(rhs.license))))&&((this.commentsUrl == rhs.commentsUrl)||((this.commentsUrl!= null)&&this.commentsUrl.equals(rhs.commentsUrl))))&&((this.size == rhs.size)||((this.size!= null)&&this.size.equals(rhs.size))))&&((this.treesUrl == rhs.treesUrl)||((this.treesUrl!= null)&&this.treesUrl.equals(rhs.treesUrl))))&&((this.allowRebaseMerge == rhs.allowRebaseMerge)||((this.allowRebaseMerge!= null)&&this.allowRebaseMerge.equals(rhs.allowRebaseMerge))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.mergesUrl == rhs.mergesUrl)||((this.mergesUrl!= null)&&this.mergesUrl.equals(rhs.mergesUrl))))&&((this.nodeId == rhs.nodeId)||((this.nodeId!= null)&&this.nodeId.equals(rhs.nodeId))))&&((this.teamsUrl == rhs.teamsUrl)||((this.teamsUrl!= null)&&this.teamsUrl.equals(rhs.teamsUrl))))&&((this.blobsUrl == rhs.blobsUrl)||((this.blobsUrl!= null)&&this.blobsUrl.equals(rhs.blobsUrl))))&&((this.issueEventsUrl == rhs.issueEventsUrl)||((this.issueEventsUrl!= null)&&this.issueEventsUrl.equals(rhs.issueEventsUrl))))&&((this.hasPages == rhs.hasPages)||((this.hasPages!= null)&&this.hasPages.equals(rhs.hasPages))))&&((this.milestonesUrl == rhs.milestonesUrl)||((this.milestonesUrl!= null)&&this.milestonesUrl.equals(rhs.milestonesUrl))))&&((this.issuesUrl == rhs.issuesUrl)||((this.issuesUrl!= null)&&this.issuesUrl.equals(rhs.issuesUrl))))&&((this.releasesUrl == rhs.releasesUrl)||((this.releasesUrl!= null)&&this.releasesUrl.equals(rhs.releasesUrl))))&&((this.description == rhs.description)||((this.description!= null)&&this.description.equals(rhs.description))))&&((this.watchers == rhs.watchers)||((this.watchers!= null)&&this.watchers.equals(rhs.watchers))))&&((this.branchesUrl == rhs.branchesUrl)||((this.branchesUrl!= null)&&this.branchesUrl.equals(rhs.branchesUrl))))&&((this.contributorsUrl == rhs.contributorsUrl)||((this.contributorsUrl!= null)&&this.contributorsUrl.equals(rhs.contributorsUrl))))&&((this.gitRefsUrl == rhs.gitRefsUrl)||((this.gitRefsUrl!= null)&&this.gitRefsUrl.equals(rhs.gitRefsUrl))))&&((this.hooksUrl == rhs.hooksUrl)||((this.hooksUrl!= null)&&this.hooksUrl.equals(rhs.hooksUrl))))&&((this.openIssuesCount == rhs.openIssuesCount)||((this.openIssuesCount!= null)&&this.openIssuesCount.equals(rhs.openIssuesCount))))&&((this.archived == rhs.archived)||((this.archived!= null)&&this.archived.equals(rhs.archived))))&&((this.stargazersCount == rhs.stargazersCount)||((this.stargazersCount!= null)&&this.stargazersCount.equals(rhs.stargazersCount))))&&((this.disabled == rhs.disabled)||((this.disabled!= null)&&this.disabled.equals(rhs.disabled))))&&((this.hasIssues == rhs.hasIssues)||((this.hasIssues!= null)&&this.hasIssues.equals(rhs.hasIssues))))&&((this.owner == rhs.owner)||((this.owner!= null)&&this.owner.equals(rhs.owner))))&&((this.hasWiki == rhs.hasWiki)||((this.hasWiki!= null)&&this.hasWiki.equals(rhs.hasWiki))))&&((this.compareUrl == rhs.compareUrl)||((this.compareUrl!= null)&&this.compareUrl.equals(rhs.compareUrl))))&&((this.gitCommitsUrl == rhs.gitCommitsUrl)||((this.gitCommitsUrl!= null)&&this.gitCommitsUrl.equals(rhs.gitCommitsUrl))))&&((this.topics == rhs.topics)||((this.topics!= null)&&this.topics.equals(rhs.topics))))&&((this.htmlUrl == rhs.htmlUrl)||((this.htmlUrl!= null)&&this.htmlUrl.equals(rhs.htmlUrl))))&&((this.stargazersUrl == rhs.stargazersUrl)||((this.stargazersUrl!= null)&&this.stargazersUrl.equals(rhs.stargazersUrl))))&&((this.fullName == rhs.fullName)||((this.fullName!= null)&&this.fullName.equals(rhs.fullName))))&&((this.svnUrl == rhs.svnUrl)||((this.svnUrl!= null)&&this.svnUrl.equals(rhs.svnUrl))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))))&&((this.pullsUrl == rhs.pullsUrl)||((this.pullsUrl!= null)&&this.pullsUrl.equals(rhs.pullsUrl))))&&((this.mirrorUrl == rhs.mirrorUrl)||((this.mirrorUrl!= null)&&this.mirrorUrl.equals(rhs.mirrorUrl))))&&((this.hasDownloads == rhs.hasDownloads)||((this.hasDownloads!= null)&&this.hasDownloads.equals(rhs.hasDownloads))))&&((this.fork == rhs.fork)||((this.fork!= null)&&this.fork.equals(rhs.fork))))&&((this.allowSquashMerge == rhs.allowSquashMerge)||((this.allowSquashMerge!= null)&&this.allowSquashMerge.equals(rhs.allowSquashMerge))))&&((this.hasProjects == rhs.hasProjects)||((this.hasProjects!= null)&&this.hasProjects.equals(rhs.hasProjects))))&&((this.deploymentsUrl == rhs.deploymentsUrl)||((this.deploymentsUrl!= null)&&this.deploymentsUrl.equals(rhs.deploymentsUrl))))&&((this.eventsUrl == rhs.eventsUrl)||((this.eventsUrl!= null)&&this.eventsUrl.equals(rhs.eventsUrl))))&&((this.gitTagsUrl == rhs.gitTagsUrl)||((this.gitTagsUrl!= null)&&this.gitTagsUrl.equals(rhs.gitTagsUrl))))&&((this.notificationsUrl == rhs.notificationsUrl)||((this.notificationsUrl!= null)&&this.notificationsUrl.equals(rhs.notificationsUrl))))&&((this.gitUrl == rhs.gitUrl)||((this.gitUrl!= null)&&this.gitUrl.equals(rhs.gitUrl))))&&((this.subscriptionUrl == rhs.subscriptionUrl)||((this.subscriptionUrl!= null)&&this.subscriptionUrl.equals(rhs.subscriptionUrl))))&&((this.homepage == rhs.homepage)||((this.homepage!= null)&&this.homepage.equals(rhs.homepage))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "id",
        "node_id",
        "name",
        "full_name",
        "owner",
        "private",
        "html_url",
        "description",
        "fork",
        "url",
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
        "downloads_url",
        "events_url",
        "forks_url",
        "git_commits_url",
        "git_refs_url",
        "git_tags_url",
        "git_url",
        "issue_comment_url",
        "issue_events_url",
        "issues_url",
        "keys_url",
        "labels_url",
        "languages_url",
        "merges_url",
        "milestones_url",
        "notifications_url",
        "pulls_url",
        "releases_url",
        "ssh_url",
        "stargazers_url",
        "statuses_url",
        "subscribers_url",
        "subscription_url",
        "tags_url",
        "teams_url",
        "trees_url",
        "clone_url",
        "mirror_url",
        "hooks_url",
        "svn_url",
        "homepage",
        "language",
        "forks_count",
        "stargazers_count",
        "watchers_count",
        "size",
        "default_branch",
        "open_issues_count",
        "topics",
        "has_issues",
        "has_projects",
        "has_wiki",
        "has_pages",
        "has_downloads",
        "archived",
        "disabled",
        "pushed_at",
        "created_at",
        "updated_at",
        "permissions",
        "allow_rebase_merge",
        "temp_clone_token",
        "allow_squash_merge",
        "allow_merge_commit",
        "forks",
        "open_issues",
        "license",
        "watchers"
    })
    @Generated("jsonschema2pojo")
    public static class Repo2 {

        @JsonProperty("id")
        private Long id;
        @JsonProperty("node_id")
        private String nodeId;
        @JsonProperty("name")
        private String name;
        @JsonProperty("full_name")
        private String fullName;
        @JsonProperty("owner")
        private GetPullRequestResponse.Owner2 owner;
        @JsonProperty("private")
        private Boolean _private;
        @JsonProperty("html_url")
        private String htmlUrl;
        @JsonProperty("description")
        private String description;
        @JsonProperty("fork")
        private Boolean fork;
        @JsonProperty("url")
        private String url;
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
        @JsonProperty("downloads_url")
        private String downloadsUrl;
        @JsonProperty("events_url")
        private String eventsUrl;
        @JsonProperty("forks_url")
        private String forksUrl;
        @JsonProperty("git_commits_url")
        private String gitCommitsUrl;
        @JsonProperty("git_refs_url")
        private String gitRefsUrl;
        @JsonProperty("git_tags_url")
        private String gitTagsUrl;
        @JsonProperty("git_url")
        private String gitUrl;
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
        @JsonProperty("notifications_url")
        private String notificationsUrl;
        @JsonProperty("pulls_url")
        private String pullsUrl;
        @JsonProperty("releases_url")
        private String releasesUrl;
        @JsonProperty("ssh_url")
        private String sshUrl;
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
        @JsonProperty("clone_url")
        private String cloneUrl;
        @JsonProperty("mirror_url")
        private String mirrorUrl;
        @JsonProperty("hooks_url")
        private String hooksUrl;
        @JsonProperty("svn_url")
        private String svnUrl;
        @JsonProperty("homepage")
        private String homepage;
        @JsonProperty("language")
        private Object language;
        @JsonProperty("forks_count")
        private Long forksCount;
        @JsonProperty("stargazers_count")
        private Long stargazersCount;
        @JsonProperty("watchers_count")
        private Long watchersCount;
        @JsonProperty("size")
        private Long size;
        @JsonProperty("default_branch")
        private String defaultBranch;
        @JsonProperty("open_issues_count")
        private Long openIssuesCount;
        @JsonProperty("topics")
        private List<String> topics = new ArrayList<String>();
        @JsonProperty("has_issues")
        private Boolean hasIssues;
        @JsonProperty("has_projects")
        private Boolean hasProjects;
        @JsonProperty("has_wiki")
        private Boolean hasWiki;
        @JsonProperty("has_pages")
        private Boolean hasPages;
        @JsonProperty("has_downloads")
        private Boolean hasDownloads;
        @JsonProperty("archived")
        private Boolean archived;
        @JsonProperty("disabled")
        private Boolean disabled;
        @JsonProperty("pushed_at")
        private String pushedAt;
        @JsonProperty("created_at")
        private String createdAt;
        @JsonProperty("updated_at")
        private String updatedAt;
        @JsonProperty("permissions")
        private GetPullRequestResponse.Permissions2 permissions;
        @JsonProperty("allow_rebase_merge")
        private Boolean allowRebaseMerge;
        @JsonProperty("temp_clone_token")
        private String tempCloneToken;
        @JsonProperty("allow_squash_merge")
        private Boolean allowSquashMerge;
        @JsonProperty("allow_merge_commit")
        private Boolean allowMergeCommit;
        @JsonProperty("forks")
        private Long forks;
        @JsonProperty("open_issues")
        private Long openIssues;
        @JsonProperty("license")
        private GetPullRequestResponse.License2 license;
        @JsonProperty("watchers")
        private Long watchers;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Repo2() {
        }

        /**
         * 
         * @param sshUrl
         * @param archiveUrl
         * @param languagesUrl
         * @param language
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
         * @param permissions
         * @param issueCommentUrl
         * @param statusesUrl
         * @param id
         * @param collaboratorsUrl
         * @param updatedAt
         * @param allowMergeCommit
         * @param forks
         * @param labelsUrl
         * @param defaultBranch
         * @param tempCloneToken
         * @param keysUrl
         * @param downloadsUrl
         * @param contentsUrl
         * @param pushedAt
         * @param tagsUrl
         * @param license
         * @param commentsUrl
         * @param size
         * @param treesUrl
         * @param allowRebaseMerge
         * @param name
         * @param mergesUrl
         * @param nodeId
         * @param teamsUrl
         * @param blobsUrl
         * @param issueEventsUrl
         * @param hasPages
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
         * @param topics
         * @param htmlUrl
         * @param stargazersUrl
         * @param fullName
         * @param svnUrl
         * @param url
         * @param pullsUrl
         * @param mirrorUrl
         * @param hasDownloads
         * @param fork
         * @param allowSquashMerge
         * @param hasProjects
         * @param deploymentsUrl
         * @param eventsUrl
         * @param gitTagsUrl
         * @param notificationsUrl
         * @param gitUrl
         * @param subscriptionUrl
         * @param homepage
         */
        public Repo2(Long id, String nodeId, String name, String fullName, GetPullRequestResponse.Owner2 owner, Boolean _private, String htmlUrl, String description, Boolean fork, String url, String archiveUrl, String assigneesUrl, String blobsUrl, String branchesUrl, String collaboratorsUrl, String commentsUrl, String commitsUrl, String compareUrl, String contentsUrl, String contributorsUrl, String deploymentsUrl, String downloadsUrl, String eventsUrl, String forksUrl, String gitCommitsUrl, String gitRefsUrl, String gitTagsUrl, String gitUrl, String issueCommentUrl, String issueEventsUrl, String issuesUrl, String keysUrl, String labelsUrl, String languagesUrl, String mergesUrl, String milestonesUrl, String notificationsUrl, String pullsUrl, String releasesUrl, String sshUrl, String stargazersUrl, String statusesUrl, String subscribersUrl, String subscriptionUrl, String tagsUrl, String teamsUrl, String treesUrl, String cloneUrl, String mirrorUrl, String hooksUrl, String svnUrl, String homepage, Object language, Long forksCount, Long stargazersCount, Long watchersCount, Long size, String defaultBranch, Long openIssuesCount, List<String> topics, Boolean hasIssues, Boolean hasProjects, Boolean hasWiki, Boolean hasPages, Boolean hasDownloads, Boolean archived, Boolean disabled, String pushedAt, String createdAt, String updatedAt, GetPullRequestResponse.Permissions2 permissions, Boolean allowRebaseMerge, String tempCloneToken, Boolean allowSquashMerge, Boolean allowMergeCommit, Long forks, Long openIssues, GetPullRequestResponse.License2 license, Long watchers) {
            super();
            this.id = id;
            this.nodeId = nodeId;
            this.name = name;
            this.fullName = fullName;
            this.owner = owner;
            this._private = _private;
            this.htmlUrl = htmlUrl;
            this.description = description;
            this.fork = fork;
            this.url = url;
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
            this.downloadsUrl = downloadsUrl;
            this.eventsUrl = eventsUrl;
            this.forksUrl = forksUrl;
            this.gitCommitsUrl = gitCommitsUrl;
            this.gitRefsUrl = gitRefsUrl;
            this.gitTagsUrl = gitTagsUrl;
            this.gitUrl = gitUrl;
            this.issueCommentUrl = issueCommentUrl;
            this.issueEventsUrl = issueEventsUrl;
            this.issuesUrl = issuesUrl;
            this.keysUrl = keysUrl;
            this.labelsUrl = labelsUrl;
            this.languagesUrl = languagesUrl;
            this.mergesUrl = mergesUrl;
            this.milestonesUrl = milestonesUrl;
            this.notificationsUrl = notificationsUrl;
            this.pullsUrl = pullsUrl;
            this.releasesUrl = releasesUrl;
            this.sshUrl = sshUrl;
            this.stargazersUrl = stargazersUrl;
            this.statusesUrl = statusesUrl;
            this.subscribersUrl = subscribersUrl;
            this.subscriptionUrl = subscriptionUrl;
            this.tagsUrl = tagsUrl;
            this.teamsUrl = teamsUrl;
            this.treesUrl = treesUrl;
            this.cloneUrl = cloneUrl;
            this.mirrorUrl = mirrorUrl;
            this.hooksUrl = hooksUrl;
            this.svnUrl = svnUrl;
            this.homepage = homepage;
            this.language = language;
            this.forksCount = forksCount;
            this.stargazersCount = stargazersCount;
            this.watchersCount = watchersCount;
            this.size = size;
            this.defaultBranch = defaultBranch;
            this.openIssuesCount = openIssuesCount;
            this.topics = topics;
            this.hasIssues = hasIssues;
            this.hasProjects = hasProjects;
            this.hasWiki = hasWiki;
            this.hasPages = hasPages;
            this.hasDownloads = hasDownloads;
            this.archived = archived;
            this.disabled = disabled;
            this.pushedAt = pushedAt;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
            this.permissions = permissions;
            this.allowRebaseMerge = allowRebaseMerge;
            this.tempCloneToken = tempCloneToken;
            this.allowSquashMerge = allowSquashMerge;
            this.allowMergeCommit = allowMergeCommit;
            this.forks = forks;
            this.openIssues = openIssues;
            this.license = license;
            this.watchers = watchers;
        }

        @JsonProperty("id")
        public Long getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(Long id) {
            this.id = id;
        }

        @JsonProperty("node_id")
        public String getNodeId() {
            return nodeId;
        }

        @JsonProperty("node_id")
        public void setNodeId(String nodeId) {
            this.nodeId = nodeId;
        }

        @JsonProperty("name")
        public String getName() {
            return name;
        }

        @JsonProperty("name")
        public void setName(String name) {
            this.name = name;
        }

        @JsonProperty("full_name")
        public String getFullName() {
            return fullName;
        }

        @JsonProperty("full_name")
        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        @JsonProperty("owner")
        public GetPullRequestResponse.Owner2 getOwner() {
            return owner;
        }

        @JsonProperty("owner")
        public void setOwner(GetPullRequestResponse.Owner2 owner) {
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

        @JsonProperty("html_url")
        public String getHtmlUrl() {
            return htmlUrl;
        }

        @JsonProperty("html_url")
        public void setHtmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
        }

        @JsonProperty("description")
        public String getDescription() {
            return description;
        }

        @JsonProperty("description")
        public void setDescription(String description) {
            this.description = description;
        }

        @JsonProperty("fork")
        public Boolean getFork() {
            return fork;
        }

        @JsonProperty("fork")
        public void setFork(Boolean fork) {
            this.fork = fork;
        }

        @JsonProperty("url")
        public String getUrl() {
            return url;
        }

        @JsonProperty("url")
        public void setUrl(String url) {
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

        @JsonProperty("forks_url")
        public String getForksUrl() {
            return forksUrl;
        }

        @JsonProperty("forks_url")
        public void setForksUrl(String forksUrl) {
            this.forksUrl = forksUrl;
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

        @JsonProperty("notifications_url")
        public String getNotificationsUrl() {
            return notificationsUrl;
        }

        @JsonProperty("notifications_url")
        public void setNotificationsUrl(String notificationsUrl) {
            this.notificationsUrl = notificationsUrl;
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

        @JsonProperty("ssh_url")
        public String getSshUrl() {
            return sshUrl;
        }

        @JsonProperty("ssh_url")
        public void setSshUrl(String sshUrl) {
            this.sshUrl = sshUrl;
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

        @JsonProperty("clone_url")
        public String getCloneUrl() {
            return cloneUrl;
        }

        @JsonProperty("clone_url")
        public void setCloneUrl(String cloneUrl) {
            this.cloneUrl = cloneUrl;
        }

        @JsonProperty("mirror_url")
        public String getMirrorUrl() {
            return mirrorUrl;
        }

        @JsonProperty("mirror_url")
        public void setMirrorUrl(String mirrorUrl) {
            this.mirrorUrl = mirrorUrl;
        }

        @JsonProperty("hooks_url")
        public String getHooksUrl() {
            return hooksUrl;
        }

        @JsonProperty("hooks_url")
        public void setHooksUrl(String hooksUrl) {
            this.hooksUrl = hooksUrl;
        }

        @JsonProperty("svn_url")
        public String getSvnUrl() {
            return svnUrl;
        }

        @JsonProperty("svn_url")
        public void setSvnUrl(String svnUrl) {
            this.svnUrl = svnUrl;
        }

        @JsonProperty("homepage")
        public String getHomepage() {
            return homepage;
        }

        @JsonProperty("homepage")
        public void setHomepage(String homepage) {
            this.homepage = homepage;
        }

        @JsonProperty("language")
        public Object getLanguage() {
            return language;
        }

        @JsonProperty("language")
        public void setLanguage(Object language) {
            this.language = language;
        }

        @JsonProperty("forks_count")
        public Long getForksCount() {
            return forksCount;
        }

        @JsonProperty("forks_count")
        public void setForksCount(Long forksCount) {
            this.forksCount = forksCount;
        }

        @JsonProperty("stargazers_count")
        public Long getStargazersCount() {
            return stargazersCount;
        }

        @JsonProperty("stargazers_count")
        public void setStargazersCount(Long stargazersCount) {
            this.stargazersCount = stargazersCount;
        }

        @JsonProperty("watchers_count")
        public Long getWatchersCount() {
            return watchersCount;
        }

        @JsonProperty("watchers_count")
        public void setWatchersCount(Long watchersCount) {
            this.watchersCount = watchersCount;
        }

        @JsonProperty("size")
        public Long getSize() {
            return size;
        }

        @JsonProperty("size")
        public void setSize(Long size) {
            this.size = size;
        }

        @JsonProperty("default_branch")
        public String getDefaultBranch() {
            return defaultBranch;
        }

        @JsonProperty("default_branch")
        public void setDefaultBranch(String defaultBranch) {
            this.defaultBranch = defaultBranch;
        }

        @JsonProperty("open_issues_count")
        public Long getOpenIssuesCount() {
            return openIssuesCount;
        }

        @JsonProperty("open_issues_count")
        public void setOpenIssuesCount(Long openIssuesCount) {
            this.openIssuesCount = openIssuesCount;
        }

        @JsonProperty("topics")
        public List<String> getTopics() {
            return topics;
        }

        @JsonProperty("topics")
        public void setTopics(List<String> topics) {
            this.topics = topics;
        }

        @JsonProperty("has_issues")
        public Boolean getHasIssues() {
            return hasIssues;
        }

        @JsonProperty("has_issues")
        public void setHasIssues(Boolean hasIssues) {
            this.hasIssues = hasIssues;
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

        @JsonProperty("has_pages")
        public Boolean getHasPages() {
            return hasPages;
        }

        @JsonProperty("has_pages")
        public void setHasPages(Boolean hasPages) {
            this.hasPages = hasPages;
        }

        @JsonProperty("has_downloads")
        public Boolean getHasDownloads() {
            return hasDownloads;
        }

        @JsonProperty("has_downloads")
        public void setHasDownloads(Boolean hasDownloads) {
            this.hasDownloads = hasDownloads;
        }

        @JsonProperty("archived")
        public Boolean getArchived() {
            return archived;
        }

        @JsonProperty("archived")
        public void setArchived(Boolean archived) {
            this.archived = archived;
        }

        @JsonProperty("disabled")
        public Boolean getDisabled() {
            return disabled;
        }

        @JsonProperty("disabled")
        public void setDisabled(Boolean disabled) {
            this.disabled = disabled;
        }

        @JsonProperty("pushed_at")
        public String getPushedAt() {
            return pushedAt;
        }

        @JsonProperty("pushed_at")
        public void setPushedAt(String pushedAt) {
            this.pushedAt = pushedAt;
        }

        @JsonProperty("created_at")
        public String getCreatedAt() {
            return createdAt;
        }

        @JsonProperty("created_at")
        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        @JsonProperty("updated_at")
        public String getUpdatedAt() {
            return updatedAt;
        }

        @JsonProperty("updated_at")
        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        @JsonProperty("permissions")
        public GetPullRequestResponse.Permissions2 getPermissions() {
            return permissions;
        }

        @JsonProperty("permissions")
        public void setPermissions(GetPullRequestResponse.Permissions2 permissions) {
            this.permissions = permissions;
        }

        @JsonProperty("allow_rebase_merge")
        public Boolean getAllowRebaseMerge() {
            return allowRebaseMerge;
        }

        @JsonProperty("allow_rebase_merge")
        public void setAllowRebaseMerge(Boolean allowRebaseMerge) {
            this.allowRebaseMerge = allowRebaseMerge;
        }

        @JsonProperty("temp_clone_token")
        public String getTempCloneToken() {
            return tempCloneToken;
        }

        @JsonProperty("temp_clone_token")
        public void setTempCloneToken(String tempCloneToken) {
            this.tempCloneToken = tempCloneToken;
        }

        @JsonProperty("allow_squash_merge")
        public Boolean getAllowSquashMerge() {
            return allowSquashMerge;
        }

        @JsonProperty("allow_squash_merge")
        public void setAllowSquashMerge(Boolean allowSquashMerge) {
            this.allowSquashMerge = allowSquashMerge;
        }

        @JsonProperty("allow_merge_commit")
        public Boolean getAllowMergeCommit() {
            return allowMergeCommit;
        }

        @JsonProperty("allow_merge_commit")
        public void setAllowMergeCommit(Boolean allowMergeCommit) {
            this.allowMergeCommit = allowMergeCommit;
        }

        @JsonProperty("forks")
        public Long getForks() {
            return forks;
        }

        @JsonProperty("forks")
        public void setForks(Long forks) {
            this.forks = forks;
        }

        @JsonProperty("open_issues")
        public Long getOpenIssues() {
            return openIssues;
        }

        @JsonProperty("open_issues")
        public void setOpenIssues(Long openIssues) {
            this.openIssues = openIssues;
        }

        @JsonProperty("license")
        public GetPullRequestResponse.License2 getLicense() {
            return license;
        }

        @JsonProperty("license")
        public void setLicense(GetPullRequestResponse.License2 license) {
            this.license = license;
        }

        @JsonProperty("watchers")
        public Long getWatchers() {
            return watchers;
        }

        @JsonProperty("watchers")
        public void setWatchers(Long watchers) {
            this.watchers = watchers;
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
            sb.append(GetPullRequestResponse.Repo2 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("id");
            sb.append('=');
            sb.append(((this.id == null)?"<null>":this.id));
            sb.append(',');
            sb.append("nodeId");
            sb.append('=');
            sb.append(((this.nodeId == null)?"<null>":this.nodeId));
            sb.append(',');
            sb.append("name");
            sb.append('=');
            sb.append(((this.name == null)?"<null>":this.name));
            sb.append(',');
            sb.append("fullName");
            sb.append('=');
            sb.append(((this.fullName == null)?"<null>":this.fullName));
            sb.append(',');
            sb.append("owner");
            sb.append('=');
            sb.append(((this.owner == null)?"<null>":this.owner));
            sb.append(',');
            sb.append("_private");
            sb.append('=');
            sb.append(((this._private == null)?"<null>":this._private));
            sb.append(',');
            sb.append("htmlUrl");
            sb.append('=');
            sb.append(((this.htmlUrl == null)?"<null>":this.htmlUrl));
            sb.append(',');
            sb.append("description");
            sb.append('=');
            sb.append(((this.description == null)?"<null>":this.description));
            sb.append(',');
            sb.append("fork");
            sb.append('=');
            sb.append(((this.fork == null)?"<null>":this.fork));
            sb.append(',');
            sb.append("url");
            sb.append('=');
            sb.append(((this.url == null)?"<null>":this.url));
            sb.append(',');
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
            sb.append("downloadsUrl");
            sb.append('=');
            sb.append(((this.downloadsUrl == null)?"<null>":this.downloadsUrl));
            sb.append(',');
            sb.append("eventsUrl");
            sb.append('=');
            sb.append(((this.eventsUrl == null)?"<null>":this.eventsUrl));
            sb.append(',');
            sb.append("forksUrl");
            sb.append('=');
            sb.append(((this.forksUrl == null)?"<null>":this.forksUrl));
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
            sb.append("notificationsUrl");
            sb.append('=');
            sb.append(((this.notificationsUrl == null)?"<null>":this.notificationsUrl));
            sb.append(',');
            sb.append("pullsUrl");
            sb.append('=');
            sb.append(((this.pullsUrl == null)?"<null>":this.pullsUrl));
            sb.append(',');
            sb.append("releasesUrl");
            sb.append('=');
            sb.append(((this.releasesUrl == null)?"<null>":this.releasesUrl));
            sb.append(',');
            sb.append("sshUrl");
            sb.append('=');
            sb.append(((this.sshUrl == null)?"<null>":this.sshUrl));
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
            sb.append("cloneUrl");
            sb.append('=');
            sb.append(((this.cloneUrl == null)?"<null>":this.cloneUrl));
            sb.append(',');
            sb.append("mirrorUrl");
            sb.append('=');
            sb.append(((this.mirrorUrl == null)?"<null>":this.mirrorUrl));
            sb.append(',');
            sb.append("hooksUrl");
            sb.append('=');
            sb.append(((this.hooksUrl == null)?"<null>":this.hooksUrl));
            sb.append(',');
            sb.append("svnUrl");
            sb.append('=');
            sb.append(((this.svnUrl == null)?"<null>":this.svnUrl));
            sb.append(',');
            sb.append("homepage");
            sb.append('=');
            sb.append(((this.homepage == null)?"<null>":this.homepage));
            sb.append(',');
            sb.append("language");
            sb.append('=');
            sb.append(((this.language == null)?"<null>":this.language));
            sb.append(',');
            sb.append("forksCount");
            sb.append('=');
            sb.append(((this.forksCount == null)?"<null>":this.forksCount));
            sb.append(',');
            sb.append("stargazersCount");
            sb.append('=');
            sb.append(((this.stargazersCount == null)?"<null>":this.stargazersCount));
            sb.append(',');
            sb.append("watchersCount");
            sb.append('=');
            sb.append(((this.watchersCount == null)?"<null>":this.watchersCount));
            sb.append(',');
            sb.append("size");
            sb.append('=');
            sb.append(((this.size == null)?"<null>":this.size));
            sb.append(',');
            sb.append("defaultBranch");
            sb.append('=');
            sb.append(((this.defaultBranch == null)?"<null>":this.defaultBranch));
            sb.append(',');
            sb.append("openIssuesCount");
            sb.append('=');
            sb.append(((this.openIssuesCount == null)?"<null>":this.openIssuesCount));
            sb.append(',');
            sb.append("topics");
            sb.append('=');
            sb.append(((this.topics == null)?"<null>":this.topics));
            sb.append(',');
            sb.append("hasIssues");
            sb.append('=');
            sb.append(((this.hasIssues == null)?"<null>":this.hasIssues));
            sb.append(',');
            sb.append("hasProjects");
            sb.append('=');
            sb.append(((this.hasProjects == null)?"<null>":this.hasProjects));
            sb.append(',');
            sb.append("hasWiki");
            sb.append('=');
            sb.append(((this.hasWiki == null)?"<null>":this.hasWiki));
            sb.append(',');
            sb.append("hasPages");
            sb.append('=');
            sb.append(((this.hasPages == null)?"<null>":this.hasPages));
            sb.append(',');
            sb.append("hasDownloads");
            sb.append('=');
            sb.append(((this.hasDownloads == null)?"<null>":this.hasDownloads));
            sb.append(',');
            sb.append("archived");
            sb.append('=');
            sb.append(((this.archived == null)?"<null>":this.archived));
            sb.append(',');
            sb.append("disabled");
            sb.append('=');
            sb.append(((this.disabled == null)?"<null>":this.disabled));
            sb.append(',');
            sb.append("pushedAt");
            sb.append('=');
            sb.append(((this.pushedAt == null)?"<null>":this.pushedAt));
            sb.append(',');
            sb.append("createdAt");
            sb.append('=');
            sb.append(((this.createdAt == null)?"<null>":this.createdAt));
            sb.append(',');
            sb.append("updatedAt");
            sb.append('=');
            sb.append(((this.updatedAt == null)?"<null>":this.updatedAt));
            sb.append(',');
            sb.append("permissions");
            sb.append('=');
            sb.append(((this.permissions == null)?"<null>":this.permissions));
            sb.append(',');
            sb.append("allowRebaseMerge");
            sb.append('=');
            sb.append(((this.allowRebaseMerge == null)?"<null>":this.allowRebaseMerge));
            sb.append(',');
            sb.append("tempCloneToken");
            sb.append('=');
            sb.append(((this.tempCloneToken == null)?"<null>":this.tempCloneToken));
            sb.append(',');
            sb.append("allowSquashMerge");
            sb.append('=');
            sb.append(((this.allowSquashMerge == null)?"<null>":this.allowSquashMerge));
            sb.append(',');
            sb.append("allowMergeCommit");
            sb.append('=');
            sb.append(((this.allowMergeCommit == null)?"<null>":this.allowMergeCommit));
            sb.append(',');
            sb.append("forks");
            sb.append('=');
            sb.append(((this.forks == null)?"<null>":this.forks));
            sb.append(',');
            sb.append("openIssues");
            sb.append('=');
            sb.append(((this.openIssues == null)?"<null>":this.openIssues));
            sb.append(',');
            sb.append("license");
            sb.append('=');
            sb.append(((this.license == null)?"<null>":this.license));
            sb.append(',');
            sb.append("watchers");
            sb.append('=');
            sb.append(((this.watchers == null)?"<null>":this.watchers));
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
            result = ((result* 31)+((this.languagesUrl == null)? 0 :this.languagesUrl.hashCode()));
            result = ((result* 31)+((this.language == null)? 0 :this.language.hashCode()));
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
            result = ((result* 31)+((this.permissions == null)? 0 :this.permissions.hashCode()));
            result = ((result* 31)+((this.issueCommentUrl == null)? 0 :this.issueCommentUrl.hashCode()));
            result = ((result* 31)+((this.statusesUrl == null)? 0 :this.statusesUrl.hashCode()));
            result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
            result = ((result* 31)+((this.collaboratorsUrl == null)? 0 :this.collaboratorsUrl.hashCode()));
            result = ((result* 31)+((this.updatedAt == null)? 0 :this.updatedAt.hashCode()));
            result = ((result* 31)+((this.allowMergeCommit == null)? 0 :this.allowMergeCommit.hashCode()));
            result = ((result* 31)+((this.forks == null)? 0 :this.forks.hashCode()));
            result = ((result* 31)+((this.labelsUrl == null)? 0 :this.labelsUrl.hashCode()));
            result = ((result* 31)+((this.defaultBranch == null)? 0 :this.defaultBranch.hashCode()));
            result = ((result* 31)+((this.tempCloneToken == null)? 0 :this.tempCloneToken.hashCode()));
            result = ((result* 31)+((this.keysUrl == null)? 0 :this.keysUrl.hashCode()));
            result = ((result* 31)+((this.downloadsUrl == null)? 0 :this.downloadsUrl.hashCode()));
            result = ((result* 31)+((this.contentsUrl == null)? 0 :this.contentsUrl.hashCode()));
            result = ((result* 31)+((this.pushedAt == null)? 0 :this.pushedAt.hashCode()));
            result = ((result* 31)+((this.tagsUrl == null)? 0 :this.tagsUrl.hashCode()));
            result = ((result* 31)+((this.license == null)? 0 :this.license.hashCode()));
            result = ((result* 31)+((this.commentsUrl == null)? 0 :this.commentsUrl.hashCode()));
            result = ((result* 31)+((this.size == null)? 0 :this.size.hashCode()));
            result = ((result* 31)+((this.treesUrl == null)? 0 :this.treesUrl.hashCode()));
            result = ((result* 31)+((this.allowRebaseMerge == null)? 0 :this.allowRebaseMerge.hashCode()));
            result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.mergesUrl == null)? 0 :this.mergesUrl.hashCode()));
            result = ((result* 31)+((this.nodeId == null)? 0 :this.nodeId.hashCode()));
            result = ((result* 31)+((this.teamsUrl == null)? 0 :this.teamsUrl.hashCode()));
            result = ((result* 31)+((this.blobsUrl == null)? 0 :this.blobsUrl.hashCode()));
            result = ((result* 31)+((this.issueEventsUrl == null)? 0 :this.issueEventsUrl.hashCode()));
            result = ((result* 31)+((this.hasPages == null)? 0 :this.hasPages.hashCode()));
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
            result = ((result* 31)+((this.topics == null)? 0 :this.topics.hashCode()));
            result = ((result* 31)+((this.htmlUrl == null)? 0 :this.htmlUrl.hashCode()));
            result = ((result* 31)+((this.stargazersUrl == null)? 0 :this.stargazersUrl.hashCode()));
            result = ((result* 31)+((this.fullName == null)? 0 :this.fullName.hashCode()));
            result = ((result* 31)+((this.svnUrl == null)? 0 :this.svnUrl.hashCode()));
            result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
            result = ((result* 31)+((this.pullsUrl == null)? 0 :this.pullsUrl.hashCode()));
            result = ((result* 31)+((this.mirrorUrl == null)? 0 :this.mirrorUrl.hashCode()));
            result = ((result* 31)+((this.hasDownloads == null)? 0 :this.hasDownloads.hashCode()));
            result = ((result* 31)+((this.fork == null)? 0 :this.fork.hashCode()));
            result = ((result* 31)+((this.allowSquashMerge == null)? 0 :this.allowSquashMerge.hashCode()));
            result = ((result* 31)+((this.hasProjects == null)? 0 :this.hasProjects.hashCode()));
            result = ((result* 31)+((this.deploymentsUrl == null)? 0 :this.deploymentsUrl.hashCode()));
            result = ((result* 31)+((this.eventsUrl == null)? 0 :this.eventsUrl.hashCode()));
            result = ((result* 31)+((this.gitTagsUrl == null)? 0 :this.gitTagsUrl.hashCode()));
            result = ((result* 31)+((this.notificationsUrl == null)? 0 :this.notificationsUrl.hashCode()));
            result = ((result* 31)+((this.gitUrl == null)? 0 :this.gitUrl.hashCode()));
            result = ((result* 31)+((this.subscriptionUrl == null)? 0 :this.subscriptionUrl.hashCode()));
            result = ((result* 31)+((this.homepage == null)? 0 :this.homepage.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof GetPullRequestResponse.Repo2) == false) {
                return false;
            }
            GetPullRequestResponse.Repo2 rhs = ((GetPullRequestResponse.Repo2) other);
            return (((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((this.sshUrl == rhs.sshUrl)||((this.sshUrl!= null)&&this.sshUrl.equals(rhs.sshUrl)))&&((this.archiveUrl == rhs.archiveUrl)||((this.archiveUrl!= null)&&this.archiveUrl.equals(rhs.archiveUrl))))&&((this.languagesUrl == rhs.languagesUrl)||((this.languagesUrl!= null)&&this.languagesUrl.equals(rhs.languagesUrl))))&&((this.language == rhs.language)||((this.language!= null)&&this.language.equals(rhs.language))))&&((this.assigneesUrl == rhs.assigneesUrl)||((this.assigneesUrl!= null)&&this.assigneesUrl.equals(rhs.assigneesUrl))))&&((this.commitsUrl == rhs.commitsUrl)||((this.commitsUrl!= null)&&this.commitsUrl.equals(rhs.commitsUrl))))&&((this.openIssues == rhs.openIssues)||((this.openIssues!= null)&&this.openIssues.equals(rhs.openIssues))))&&((this.cloneUrl == rhs.cloneUrl)||((this.cloneUrl!= null)&&this.cloneUrl.equals(rhs.cloneUrl))))&&((this.forksCount == rhs.forksCount)||((this.forksCount!= null)&&this.forksCount.equals(rhs.forksCount))))&&((this.subscribersUrl == rhs.subscribersUrl)||((this.subscribersUrl!= null)&&this.subscribersUrl.equals(rhs.subscribersUrl))))&&((this.createdAt == rhs.createdAt)||((this.createdAt!= null)&&this.createdAt.equals(rhs.createdAt))))&&((this.forksUrl == rhs.forksUrl)||((this.forksUrl!= null)&&this.forksUrl.equals(rhs.forksUrl))))&&((this.watchersCount == rhs.watchersCount)||((this.watchersCount!= null)&&this.watchersCount.equals(rhs.watchersCount))))&&((this._private == rhs._private)||((this._private!= null)&&this._private.equals(rhs._private))))&&((this.permissions == rhs.permissions)||((this.permissions!= null)&&this.permissions.equals(rhs.permissions))))&&((this.issueCommentUrl == rhs.issueCommentUrl)||((this.issueCommentUrl!= null)&&this.issueCommentUrl.equals(rhs.issueCommentUrl))))&&((this.statusesUrl == rhs.statusesUrl)||((this.statusesUrl!= null)&&this.statusesUrl.equals(rhs.statusesUrl))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.collaboratorsUrl == rhs.collaboratorsUrl)||((this.collaboratorsUrl!= null)&&this.collaboratorsUrl.equals(rhs.collaboratorsUrl))))&&((this.updatedAt == rhs.updatedAt)||((this.updatedAt!= null)&&this.updatedAt.equals(rhs.updatedAt))))&&((this.allowMergeCommit == rhs.allowMergeCommit)||((this.allowMergeCommit!= null)&&this.allowMergeCommit.equals(rhs.allowMergeCommit))))&&((this.forks == rhs.forks)||((this.forks!= null)&&this.forks.equals(rhs.forks))))&&((this.labelsUrl == rhs.labelsUrl)||((this.labelsUrl!= null)&&this.labelsUrl.equals(rhs.labelsUrl))))&&((this.defaultBranch == rhs.defaultBranch)||((this.defaultBranch!= null)&&this.defaultBranch.equals(rhs.defaultBranch))))&&((this.tempCloneToken == rhs.tempCloneToken)||((this.tempCloneToken!= null)&&this.tempCloneToken.equals(rhs.tempCloneToken))))&&((this.keysUrl == rhs.keysUrl)||((this.keysUrl!= null)&&this.keysUrl.equals(rhs.keysUrl))))&&((this.downloadsUrl == rhs.downloadsUrl)||((this.downloadsUrl!= null)&&this.downloadsUrl.equals(rhs.downloadsUrl))))&&((this.contentsUrl == rhs.contentsUrl)||((this.contentsUrl!= null)&&this.contentsUrl.equals(rhs.contentsUrl))))&&((this.pushedAt == rhs.pushedAt)||((this.pushedAt!= null)&&this.pushedAt.equals(rhs.pushedAt))))&&((this.tagsUrl == rhs.tagsUrl)||((this.tagsUrl!= null)&&this.tagsUrl.equals(rhs.tagsUrl))))&&((this.license == rhs.license)||((this.license!= null)&&this.license.equals(rhs.license))))&&((this.commentsUrl == rhs.commentsUrl)||((this.commentsUrl!= null)&&this.commentsUrl.equals(rhs.commentsUrl))))&&((this.size == rhs.size)||((this.size!= null)&&this.size.equals(rhs.size))))&&((this.treesUrl == rhs.treesUrl)||((this.treesUrl!= null)&&this.treesUrl.equals(rhs.treesUrl))))&&((this.allowRebaseMerge == rhs.allowRebaseMerge)||((this.allowRebaseMerge!= null)&&this.allowRebaseMerge.equals(rhs.allowRebaseMerge))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.mergesUrl == rhs.mergesUrl)||((this.mergesUrl!= null)&&this.mergesUrl.equals(rhs.mergesUrl))))&&((this.nodeId == rhs.nodeId)||((this.nodeId!= null)&&this.nodeId.equals(rhs.nodeId))))&&((this.teamsUrl == rhs.teamsUrl)||((this.teamsUrl!= null)&&this.teamsUrl.equals(rhs.teamsUrl))))&&((this.blobsUrl == rhs.blobsUrl)||((this.blobsUrl!= null)&&this.blobsUrl.equals(rhs.blobsUrl))))&&((this.issueEventsUrl == rhs.issueEventsUrl)||((this.issueEventsUrl!= null)&&this.issueEventsUrl.equals(rhs.issueEventsUrl))))&&((this.hasPages == rhs.hasPages)||((this.hasPages!= null)&&this.hasPages.equals(rhs.hasPages))))&&((this.milestonesUrl == rhs.milestonesUrl)||((this.milestonesUrl!= null)&&this.milestonesUrl.equals(rhs.milestonesUrl))))&&((this.issuesUrl == rhs.issuesUrl)||((this.issuesUrl!= null)&&this.issuesUrl.equals(rhs.issuesUrl))))&&((this.releasesUrl == rhs.releasesUrl)||((this.releasesUrl!= null)&&this.releasesUrl.equals(rhs.releasesUrl))))&&((this.description == rhs.description)||((this.description!= null)&&this.description.equals(rhs.description))))&&((this.watchers == rhs.watchers)||((this.watchers!= null)&&this.watchers.equals(rhs.watchers))))&&((this.branchesUrl == rhs.branchesUrl)||((this.branchesUrl!= null)&&this.branchesUrl.equals(rhs.branchesUrl))))&&((this.contributorsUrl == rhs.contributorsUrl)||((this.contributorsUrl!= null)&&this.contributorsUrl.equals(rhs.contributorsUrl))))&&((this.gitRefsUrl == rhs.gitRefsUrl)||((this.gitRefsUrl!= null)&&this.gitRefsUrl.equals(rhs.gitRefsUrl))))&&((this.hooksUrl == rhs.hooksUrl)||((this.hooksUrl!= null)&&this.hooksUrl.equals(rhs.hooksUrl))))&&((this.openIssuesCount == rhs.openIssuesCount)||((this.openIssuesCount!= null)&&this.openIssuesCount.equals(rhs.openIssuesCount))))&&((this.archived == rhs.archived)||((this.archived!= null)&&this.archived.equals(rhs.archived))))&&((this.stargazersCount == rhs.stargazersCount)||((this.stargazersCount!= null)&&this.stargazersCount.equals(rhs.stargazersCount))))&&((this.disabled == rhs.disabled)||((this.disabled!= null)&&this.disabled.equals(rhs.disabled))))&&((this.hasIssues == rhs.hasIssues)||((this.hasIssues!= null)&&this.hasIssues.equals(rhs.hasIssues))))&&((this.owner == rhs.owner)||((this.owner!= null)&&this.owner.equals(rhs.owner))))&&((this.hasWiki == rhs.hasWiki)||((this.hasWiki!= null)&&this.hasWiki.equals(rhs.hasWiki))))&&((this.compareUrl == rhs.compareUrl)||((this.compareUrl!= null)&&this.compareUrl.equals(rhs.compareUrl))))&&((this.gitCommitsUrl == rhs.gitCommitsUrl)||((this.gitCommitsUrl!= null)&&this.gitCommitsUrl.equals(rhs.gitCommitsUrl))))&&((this.topics == rhs.topics)||((this.topics!= null)&&this.topics.equals(rhs.topics))))&&((this.htmlUrl == rhs.htmlUrl)||((this.htmlUrl!= null)&&this.htmlUrl.equals(rhs.htmlUrl))))&&((this.stargazersUrl == rhs.stargazersUrl)||((this.stargazersUrl!= null)&&this.stargazersUrl.equals(rhs.stargazersUrl))))&&((this.fullName == rhs.fullName)||((this.fullName!= null)&&this.fullName.equals(rhs.fullName))))&&((this.svnUrl == rhs.svnUrl)||((this.svnUrl!= null)&&this.svnUrl.equals(rhs.svnUrl))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))))&&((this.pullsUrl == rhs.pullsUrl)||((this.pullsUrl!= null)&&this.pullsUrl.equals(rhs.pullsUrl))))&&((this.mirrorUrl == rhs.mirrorUrl)||((this.mirrorUrl!= null)&&this.mirrorUrl.equals(rhs.mirrorUrl))))&&((this.hasDownloads == rhs.hasDownloads)||((this.hasDownloads!= null)&&this.hasDownloads.equals(rhs.hasDownloads))))&&((this.fork == rhs.fork)||((this.fork!= null)&&this.fork.equals(rhs.fork))))&&((this.allowSquashMerge == rhs.allowSquashMerge)||((this.allowSquashMerge!= null)&&this.allowSquashMerge.equals(rhs.allowSquashMerge))))&&((this.hasProjects == rhs.hasProjects)||((this.hasProjects!= null)&&this.hasProjects.equals(rhs.hasProjects))))&&((this.deploymentsUrl == rhs.deploymentsUrl)||((this.deploymentsUrl!= null)&&this.deploymentsUrl.equals(rhs.deploymentsUrl))))&&((this.eventsUrl == rhs.eventsUrl)||((this.eventsUrl!= null)&&this.eventsUrl.equals(rhs.eventsUrl))))&&((this.gitTagsUrl == rhs.gitTagsUrl)||((this.gitTagsUrl!= null)&&this.gitTagsUrl.equals(rhs.gitTagsUrl))))&&((this.notificationsUrl == rhs.notificationsUrl)||((this.notificationsUrl!= null)&&this.notificationsUrl.equals(rhs.notificationsUrl))))&&((this.gitUrl == rhs.gitUrl)||((this.gitUrl!= null)&&this.gitUrl.equals(rhs.gitUrl))))&&((this.subscriptionUrl == rhs.subscriptionUrl)||((this.subscriptionUrl!= null)&&this.subscriptionUrl.equals(rhs.subscriptionUrl))))&&((this.homepage == rhs.homepage)||((this.homepage!= null)&&this.homepage.equals(rhs.homepage))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "login",
        "id",
        "node_id",
        "avatar_url",
        "gravatar_id",
        "url",
        "html_url",
        "followers_url",
        "following_url",
        "gists_url",
        "starred_url",
        "subscriptions_url",
        "organizations_url",
        "repos_url",
        "events_url",
        "received_events_url",
        "type",
        "site_admin"
    })
    @Generated("jsonschema2pojo")
    public static class RequestedReviewer {

        @JsonProperty("login")
        private String login;
        @JsonProperty("id")
        private Long id;
        @JsonProperty("node_id")
        private String nodeId;
        @JsonProperty("avatar_url")
        private String avatarUrl;
        @JsonProperty("gravatar_id")
        private String gravatarId;
        @JsonProperty("url")
        private String url;
        @JsonProperty("html_url")
        private String htmlUrl;
        @JsonProperty("followers_url")
        private String followersUrl;
        @JsonProperty("following_url")
        private String followingUrl;
        @JsonProperty("gists_url")
        private String gistsUrl;
        @JsonProperty("starred_url")
        private String starredUrl;
        @JsonProperty("subscriptions_url")
        private String subscriptionsUrl;
        @JsonProperty("organizations_url")
        private String organizationsUrl;
        @JsonProperty("repos_url")
        private String reposUrl;
        @JsonProperty("events_url")
        private String eventsUrl;
        @JsonProperty("received_events_url")
        private String receivedEventsUrl;
        @JsonProperty("type")
        private String type;
        @JsonProperty("site_admin")
        private Boolean siteAdmin;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public RequestedReviewer() {
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
         * @param url
         * @param starredUrl
         * @param gravatarId
         * @param followersUrl
         * @param id
         * @param eventsUrl
         * @param nodeId
         */
        public RequestedReviewer(String login, Long id, String nodeId, String avatarUrl, String gravatarId, String url, String htmlUrl, String followersUrl, String followingUrl, String gistsUrl, String starredUrl, String subscriptionsUrl, String organizationsUrl, String reposUrl, String eventsUrl, String receivedEventsUrl, String type, Boolean siteAdmin) {
            super();
            this.login = login;
            this.id = id;
            this.nodeId = nodeId;
            this.avatarUrl = avatarUrl;
            this.gravatarId = gravatarId;
            this.url = url;
            this.htmlUrl = htmlUrl;
            this.followersUrl = followersUrl;
            this.followingUrl = followingUrl;
            this.gistsUrl = gistsUrl;
            this.starredUrl = starredUrl;
            this.subscriptionsUrl = subscriptionsUrl;
            this.organizationsUrl = organizationsUrl;
            this.reposUrl = reposUrl;
            this.eventsUrl = eventsUrl;
            this.receivedEventsUrl = receivedEventsUrl;
            this.type = type;
            this.siteAdmin = siteAdmin;
        }

        @JsonProperty("login")
        public String getLogin() {
            return login;
        }

        @JsonProperty("login")
        public void setLogin(String login) {
            this.login = login;
        }

        @JsonProperty("id")
        public Long getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(Long id) {
            this.id = id;
        }

        @JsonProperty("node_id")
        public String getNodeId() {
            return nodeId;
        }

        @JsonProperty("node_id")
        public void setNodeId(String nodeId) {
            this.nodeId = nodeId;
        }

        @JsonProperty("avatar_url")
        public String getAvatarUrl() {
            return avatarUrl;
        }

        @JsonProperty("avatar_url")
        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        @JsonProperty("gravatar_id")
        public String getGravatarId() {
            return gravatarId;
        }

        @JsonProperty("gravatar_id")
        public void setGravatarId(String gravatarId) {
            this.gravatarId = gravatarId;
        }

        @JsonProperty("url")
        public String getUrl() {
            return url;
        }

        @JsonProperty("url")
        public void setUrl(String url) {
            this.url = url;
        }

        @JsonProperty("html_url")
        public String getHtmlUrl() {
            return htmlUrl;
        }

        @JsonProperty("html_url")
        public void setHtmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
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

        @JsonProperty("organizations_url")
        public String getOrganizationsUrl() {
            return organizationsUrl;
        }

        @JsonProperty("organizations_url")
        public void setOrganizationsUrl(String organizationsUrl) {
            this.organizationsUrl = organizationsUrl;
        }

        @JsonProperty("repos_url")
        public String getReposUrl() {
            return reposUrl;
        }

        @JsonProperty("repos_url")
        public void setReposUrl(String reposUrl) {
            this.reposUrl = reposUrl;
        }

        @JsonProperty("events_url")
        public String getEventsUrl() {
            return eventsUrl;
        }

        @JsonProperty("events_url")
        public void setEventsUrl(String eventsUrl) {
            this.eventsUrl = eventsUrl;
        }

        @JsonProperty("received_events_url")
        public String getReceivedEventsUrl() {
            return receivedEventsUrl;
        }

        @JsonProperty("received_events_url")
        public void setReceivedEventsUrl(String receivedEventsUrl) {
            this.receivedEventsUrl = receivedEventsUrl;
        }

        @JsonProperty("type")
        public String getType() {
            return type;
        }

        @JsonProperty("type")
        public void setType(String type) {
            this.type = type;
        }

        @JsonProperty("site_admin")
        public Boolean getSiteAdmin() {
            return siteAdmin;
        }

        @JsonProperty("site_admin")
        public void setSiteAdmin(Boolean siteAdmin) {
            this.siteAdmin = siteAdmin;
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
            sb.append(GetPullRequestResponse.RequestedReviewer.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("login");
            sb.append('=');
            sb.append(((this.login == null)?"<null>":this.login));
            sb.append(',');
            sb.append("id");
            sb.append('=');
            sb.append(((this.id == null)?"<null>":this.id));
            sb.append(',');
            sb.append("nodeId");
            sb.append('=');
            sb.append(((this.nodeId == null)?"<null>":this.nodeId));
            sb.append(',');
            sb.append("avatarUrl");
            sb.append('=');
            sb.append(((this.avatarUrl == null)?"<null>":this.avatarUrl));
            sb.append(',');
            sb.append("gravatarId");
            sb.append('=');
            sb.append(((this.gravatarId == null)?"<null>":this.gravatarId));
            sb.append(',');
            sb.append("url");
            sb.append('=');
            sb.append(((this.url == null)?"<null>":this.url));
            sb.append(',');
            sb.append("htmlUrl");
            sb.append('=');
            sb.append(((this.htmlUrl == null)?"<null>":this.htmlUrl));
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
            sb.append("starredUrl");
            sb.append('=');
            sb.append(((this.starredUrl == null)?"<null>":this.starredUrl));
            sb.append(',');
            sb.append("subscriptionsUrl");
            sb.append('=');
            sb.append(((this.subscriptionsUrl == null)?"<null>":this.subscriptionsUrl));
            sb.append(',');
            sb.append("organizationsUrl");
            sb.append('=');
            sb.append(((this.organizationsUrl == null)?"<null>":this.organizationsUrl));
            sb.append(',');
            sb.append("reposUrl");
            sb.append('=');
            sb.append(((this.reposUrl == null)?"<null>":this.reposUrl));
            sb.append(',');
            sb.append("eventsUrl");
            sb.append('=');
            sb.append(((this.eventsUrl == null)?"<null>":this.eventsUrl));
            sb.append(',');
            sb.append("receivedEventsUrl");
            sb.append('=');
            sb.append(((this.receivedEventsUrl == null)?"<null>":this.receivedEventsUrl));
            sb.append(',');
            sb.append("type");
            sb.append('=');
            sb.append(((this.type == null)?"<null>":this.type));
            sb.append(',');
            sb.append("siteAdmin");
            sb.append('=');
            sb.append(((this.siteAdmin == null)?"<null>":this.siteAdmin));
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
            result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
            result = ((result* 31)+((this.starredUrl == null)? 0 :this.starredUrl.hashCode()));
            result = ((result* 31)+((this.gravatarId == null)? 0 :this.gravatarId.hashCode()));
            result = ((result* 31)+((this.followersUrl == null)? 0 :this.followersUrl.hashCode()));
            result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
            result = ((result* 31)+((this.eventsUrl == null)? 0 :this.eventsUrl.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.nodeId == null)? 0 :this.nodeId.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof GetPullRequestResponse.RequestedReviewer) == false) {
                return false;
            }
            GetPullRequestResponse.RequestedReviewer rhs = ((GetPullRequestResponse.RequestedReviewer) other);
            return ((((((((((((((((((((this.receivedEventsUrl == rhs.receivedEventsUrl)||((this.receivedEventsUrl!= null)&&this.receivedEventsUrl.equals(rhs.receivedEventsUrl)))&&((this.siteAdmin == rhs.siteAdmin)||((this.siteAdmin!= null)&&this.siteAdmin.equals(rhs.siteAdmin))))&&((this.followingUrl == rhs.followingUrl)||((this.followingUrl!= null)&&this.followingUrl.equals(rhs.followingUrl))))&&((this.gistsUrl == rhs.gistsUrl)||((this.gistsUrl!= null)&&this.gistsUrl.equals(rhs.gistsUrl))))&&((this.avatarUrl == rhs.avatarUrl)||((this.avatarUrl!= null)&&this.avatarUrl.equals(rhs.avatarUrl))))&&((this.organizationsUrl == rhs.organizationsUrl)||((this.organizationsUrl!= null)&&this.organizationsUrl.equals(rhs.organizationsUrl))))&&((this.reposUrl == rhs.reposUrl)||((this.reposUrl!= null)&&this.reposUrl.equals(rhs.reposUrl))))&&((this.htmlUrl == rhs.htmlUrl)||((this.htmlUrl!= null)&&this.htmlUrl.equals(rhs.htmlUrl))))&&((this.subscriptionsUrl == rhs.subscriptionsUrl)||((this.subscriptionsUrl!= null)&&this.subscriptionsUrl.equals(rhs.subscriptionsUrl))))&&((this.login == rhs.login)||((this.login!= null)&&this.login.equals(rhs.login))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))))&&((this.starredUrl == rhs.starredUrl)||((this.starredUrl!= null)&&this.starredUrl.equals(rhs.starredUrl))))&&((this.gravatarId == rhs.gravatarId)||((this.gravatarId!= null)&&this.gravatarId.equals(rhs.gravatarId))))&&((this.followersUrl == rhs.followersUrl)||((this.followersUrl!= null)&&this.followersUrl.equals(rhs.followersUrl))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.eventsUrl == rhs.eventsUrl)||((this.eventsUrl!= null)&&this.eventsUrl.equals(rhs.eventsUrl))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.nodeId == rhs.nodeId)||((this.nodeId!= null)&&this.nodeId.equals(rhs.nodeId))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "id",
        "node_id",
        "url",
        "html_url",
        "name",
        "slug",
        "description",
        "privacy",
        "permission",
        "members_url",
        "repositories_url"
    })
    @Generated("jsonschema2pojo")
    public static class RequestedTeam {

        @JsonProperty("id")
        private Long id;
        @JsonProperty("node_id")
        private String nodeId;
        @JsonProperty("url")
        private String url;
        @JsonProperty("html_url")
        private String htmlUrl;
        @JsonProperty("name")
        private String name;
        @JsonProperty("slug")
        private String slug;
        @JsonProperty("description")
        private String description;
        @JsonProperty("privacy")
        private String privacy;
        @JsonProperty("permission")
        private String permission;
        @JsonProperty("members_url")
        private String membersUrl;
        @JsonProperty("repositories_url")
        private String repositoriesUrl;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public RequestedTeam() {
        }

        /**
         * 
         * @param repositoriesUrl
         * @param htmlUrl
         * @param name
         * @param description
         * @param privacy
         * @param permission
         * @param id
         * @param membersUrl
         * @param nodeId
         * @param url
         * @param slug
         */
        public RequestedTeam(Long id, String nodeId, String url, String htmlUrl, String name, String slug, String description, String privacy, String permission, String membersUrl, String repositoriesUrl) {
            super();
            this.id = id;
            this.nodeId = nodeId;
            this.url = url;
            this.htmlUrl = htmlUrl;
            this.name = name;
            this.slug = slug;
            this.description = description;
            this.privacy = privacy;
            this.permission = permission;
            this.membersUrl = membersUrl;
            this.repositoriesUrl = repositoriesUrl;
        }

        @JsonProperty("id")
        public Long getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(Long id) {
            this.id = id;
        }

        @JsonProperty("node_id")
        public String getNodeId() {
            return nodeId;
        }

        @JsonProperty("node_id")
        public void setNodeId(String nodeId) {
            this.nodeId = nodeId;
        }

        @JsonProperty("url")
        public String getUrl() {
            return url;
        }

        @JsonProperty("url")
        public void setUrl(String url) {
            this.url = url;
        }

        @JsonProperty("html_url")
        public String getHtmlUrl() {
            return htmlUrl;
        }

        @JsonProperty("html_url")
        public void setHtmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
        }

        @JsonProperty("name")
        public String getName() {
            return name;
        }

        @JsonProperty("name")
        public void setName(String name) {
            this.name = name;
        }

        @JsonProperty("slug")
        public String getSlug() {
            return slug;
        }

        @JsonProperty("slug")
        public void setSlug(String slug) {
            this.slug = slug;
        }

        @JsonProperty("description")
        public String getDescription() {
            return description;
        }

        @JsonProperty("description")
        public void setDescription(String description) {
            this.description = description;
        }

        @JsonProperty("privacy")
        public String getPrivacy() {
            return privacy;
        }

        @JsonProperty("privacy")
        public void setPrivacy(String privacy) {
            this.privacy = privacy;
        }

        @JsonProperty("permission")
        public String getPermission() {
            return permission;
        }

        @JsonProperty("permission")
        public void setPermission(String permission) {
            this.permission = permission;
        }

        @JsonProperty("members_url")
        public String getMembersUrl() {
            return membersUrl;
        }

        @JsonProperty("members_url")
        public void setMembersUrl(String membersUrl) {
            this.membersUrl = membersUrl;
        }

        @JsonProperty("repositories_url")
        public String getRepositoriesUrl() {
            return repositoriesUrl;
        }

        @JsonProperty("repositories_url")
        public void setRepositoriesUrl(String repositoriesUrl) {
            this.repositoriesUrl = repositoriesUrl;
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
            sb.append(GetPullRequestResponse.RequestedTeam.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("id");
            sb.append('=');
            sb.append(((this.id == null)?"<null>":this.id));
            sb.append(',');
            sb.append("nodeId");
            sb.append('=');
            sb.append(((this.nodeId == null)?"<null>":this.nodeId));
            sb.append(',');
            sb.append("url");
            sb.append('=');
            sb.append(((this.url == null)?"<null>":this.url));
            sb.append(',');
            sb.append("htmlUrl");
            sb.append('=');
            sb.append(((this.htmlUrl == null)?"<null>":this.htmlUrl));
            sb.append(',');
            sb.append("name");
            sb.append('=');
            sb.append(((this.name == null)?"<null>":this.name));
            sb.append(',');
            sb.append("slug");
            sb.append('=');
            sb.append(((this.slug == null)?"<null>":this.slug));
            sb.append(',');
            sb.append("description");
            sb.append('=');
            sb.append(((this.description == null)?"<null>":this.description));
            sb.append(',');
            sb.append("privacy");
            sb.append('=');
            sb.append(((this.privacy == null)?"<null>":this.privacy));
            sb.append(',');
            sb.append("permission");
            sb.append('=');
            sb.append(((this.permission == null)?"<null>":this.permission));
            sb.append(',');
            sb.append("membersUrl");
            sb.append('=');
            sb.append(((this.membersUrl == null)?"<null>":this.membersUrl));
            sb.append(',');
            sb.append("repositoriesUrl");
            sb.append('=');
            sb.append(((this.repositoriesUrl == null)?"<null>":this.repositoriesUrl));
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
            result = ((result* 31)+((this.repositoriesUrl == null)? 0 :this.repositoriesUrl.hashCode()));
            result = ((result* 31)+((this.htmlUrl == null)? 0 :this.htmlUrl.hashCode()));
            result = ((result* 31)+((this.description == null)? 0 :this.description.hashCode()));
            result = ((result* 31)+((this.privacy == null)? 0 :this.privacy.hashCode()));
            result = ((result* 31)+((this.permission == null)? 0 :this.permission.hashCode()));
            result = ((result* 31)+((this.membersUrl == null)? 0 :this.membersUrl.hashCode()));
            result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
            result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
            result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.nodeId == null)? 0 :this.nodeId.hashCode()));
            result = ((result* 31)+((this.slug == null)? 0 :this.slug.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof GetPullRequestResponse.RequestedTeam) == false) {
                return false;
            }
            GetPullRequestResponse.RequestedTeam rhs = ((GetPullRequestResponse.RequestedTeam) other);
            return (((((((((((((this.repositoriesUrl == rhs.repositoriesUrl)||((this.repositoriesUrl!= null)&&this.repositoriesUrl.equals(rhs.repositoriesUrl)))&&((this.htmlUrl == rhs.htmlUrl)||((this.htmlUrl!= null)&&this.htmlUrl.equals(rhs.htmlUrl))))&&((this.description == rhs.description)||((this.description!= null)&&this.description.equals(rhs.description))))&&((this.privacy == rhs.privacy)||((this.privacy!= null)&&this.privacy.equals(rhs.privacy))))&&((this.permission == rhs.permission)||((this.permission!= null)&&this.permission.equals(rhs.permission))))&&((this.membersUrl == rhs.membersUrl)||((this.membersUrl!= null)&&this.membersUrl.equals(rhs.membersUrl))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.nodeId == rhs.nodeId)||((this.nodeId!= null)&&this.nodeId.equals(rhs.nodeId))))&&((this.slug == rhs.slug)||((this.slug!= null)&&this.slug.equals(rhs.slug))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "href"
    })
    @Generated("jsonschema2pojo")
    public static class ReviewComment {

        @JsonProperty("href")
        private String href;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public ReviewComment() {
        }

        /**
         * 
         * @param href
         */
        public ReviewComment(String href) {
            super();
            this.href = href;
        }

        @JsonProperty("href")
        public String getHref() {
            return href;
        }

        @JsonProperty("href")
        public void setHref(String href) {
            this.href = href;
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
            sb.append(GetPullRequestResponse.ReviewComment.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("href");
            sb.append('=');
            sb.append(((this.href == null)?"<null>":this.href));
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
            result = ((result* 31)+((this.href == null)? 0 :this.href.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof GetPullRequestResponse.ReviewComment) == false) {
                return false;
            }
            GetPullRequestResponse.ReviewComment rhs = ((GetPullRequestResponse.ReviewComment) other);
            return (((this.href == rhs.href)||((this.href!= null)&&this.href.equals(rhs.href)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "href"
    })
    @Generated("jsonschema2pojo")
    public static class ReviewComments {

        @JsonProperty("href")
        private String href;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public ReviewComments() {
        }

        /**
         * 
         * @param href
         */
        public ReviewComments(String href) {
            super();
            this.href = href;
        }

        @JsonProperty("href")
        public String getHref() {
            return href;
        }

        @JsonProperty("href")
        public void setHref(String href) {
            this.href = href;
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
            sb.append(GetPullRequestResponse.ReviewComments.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("href");
            sb.append('=');
            sb.append(((this.href == null)?"<null>":this.href));
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
            result = ((result* 31)+((this.href == null)? 0 :this.href.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof GetPullRequestResponse.ReviewComments) == false) {
                return false;
            }
            GetPullRequestResponse.ReviewComments rhs = ((GetPullRequestResponse.ReviewComments) other);
            return (((this.href == rhs.href)||((this.href!= null)&&this.href.equals(rhs.href)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "href"
    })
    @Generated("jsonschema2pojo")
    public static class Self {

        @JsonProperty("href")
        private String href;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Self() {
        }

        /**
         * 
         * @param href
         */
        public Self(String href) {
            super();
            this.href = href;
        }

        @JsonProperty("href")
        public String getHref() {
            return href;
        }

        @JsonProperty("href")
        public void setHref(String href) {
            this.href = href;
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
            sb.append(GetPullRequestResponse.Self.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("href");
            sb.append('=');
            sb.append(((this.href == null)?"<null>":this.href));
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
            result = ((result* 31)+((this.href == null)? 0 :this.href.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof GetPullRequestResponse.Self) == false) {
                return false;
            }
            GetPullRequestResponse.Self rhs = ((GetPullRequestResponse.Self) other);
            return (((this.href == rhs.href)||((this.href!= null)&&this.href.equals(rhs.href)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "href"
    })
    @Generated("jsonschema2pojo")
    public static class Statuses {

        @JsonProperty("href")
        private String href;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Statuses() {
        }

        /**
         * 
         * @param href
         */
        public Statuses(String href) {
            super();
            this.href = href;
        }

        @JsonProperty("href")
        public String getHref() {
            return href;
        }

        @JsonProperty("href")
        public void setHref(String href) {
            this.href = href;
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
            sb.append(GetPullRequestResponse.Statuses.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("href");
            sb.append('=');
            sb.append(((this.href == null)?"<null>":this.href));
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
            result = ((result* 31)+((this.href == null)? 0 :this.href.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof GetPullRequestResponse.Statuses) == false) {
                return false;
            }
            GetPullRequestResponse.Statuses rhs = ((GetPullRequestResponse.Statuses) other);
            return (((this.href == rhs.href)||((this.href!= null)&&this.href.equals(rhs.href)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "login",
        "id",
        "node_id",
        "avatar_url",
        "gravatar_id",
        "url",
        "html_url",
        "followers_url",
        "following_url",
        "gists_url",
        "starred_url",
        "subscriptions_url",
        "organizations_url",
        "repos_url",
        "events_url",
        "received_events_url",
        "type",
        "site_admin"
    })
    @Generated("jsonschema2pojo")
    public static class User {

        @JsonProperty("login")
        private String login;
        @JsonProperty("id")
        private Long id;
        @JsonProperty("node_id")
        private String nodeId;
        @JsonProperty("avatar_url")
        private String avatarUrl;
        @JsonProperty("gravatar_id")
        private String gravatarId;
        @JsonProperty("url")
        private String url;
        @JsonProperty("html_url")
        private String htmlUrl;
        @JsonProperty("followers_url")
        private String followersUrl;
        @JsonProperty("following_url")
        private String followingUrl;
        @JsonProperty("gists_url")
        private String gistsUrl;
        @JsonProperty("starred_url")
        private String starredUrl;
        @JsonProperty("subscriptions_url")
        private String subscriptionsUrl;
        @JsonProperty("organizations_url")
        private String organizationsUrl;
        @JsonProperty("repos_url")
        private String reposUrl;
        @JsonProperty("events_url")
        private String eventsUrl;
        @JsonProperty("received_events_url")
        private String receivedEventsUrl;
        @JsonProperty("type")
        private String type;
        @JsonProperty("site_admin")
        private Boolean siteAdmin;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public User() {
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
         * @param url
         * @param starredUrl
         * @param gravatarId
         * @param followersUrl
         * @param id
         * @param eventsUrl
         * @param nodeId
         */
        public User(String login, Long id, String nodeId, String avatarUrl, String gravatarId, String url, String htmlUrl, String followersUrl, String followingUrl, String gistsUrl, String starredUrl, String subscriptionsUrl, String organizationsUrl, String reposUrl, String eventsUrl, String receivedEventsUrl, String type, Boolean siteAdmin) {
            super();
            this.login = login;
            this.id = id;
            this.nodeId = nodeId;
            this.avatarUrl = avatarUrl;
            this.gravatarId = gravatarId;
            this.url = url;
            this.htmlUrl = htmlUrl;
            this.followersUrl = followersUrl;
            this.followingUrl = followingUrl;
            this.gistsUrl = gistsUrl;
            this.starredUrl = starredUrl;
            this.subscriptionsUrl = subscriptionsUrl;
            this.organizationsUrl = organizationsUrl;
            this.reposUrl = reposUrl;
            this.eventsUrl = eventsUrl;
            this.receivedEventsUrl = receivedEventsUrl;
            this.type = type;
            this.siteAdmin = siteAdmin;
        }

        @JsonProperty("login")
        public String getLogin() {
            return login;
        }

        @JsonProperty("login")
        public void setLogin(String login) {
            this.login = login;
        }

        @JsonProperty("id")
        public Long getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(Long id) {
            this.id = id;
        }

        @JsonProperty("node_id")
        public String getNodeId() {
            return nodeId;
        }

        @JsonProperty("node_id")
        public void setNodeId(String nodeId) {
            this.nodeId = nodeId;
        }

        @JsonProperty("avatar_url")
        public String getAvatarUrl() {
            return avatarUrl;
        }

        @JsonProperty("avatar_url")
        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        @JsonProperty("gravatar_id")
        public String getGravatarId() {
            return gravatarId;
        }

        @JsonProperty("gravatar_id")
        public void setGravatarId(String gravatarId) {
            this.gravatarId = gravatarId;
        }

        @JsonProperty("url")
        public String getUrl() {
            return url;
        }

        @JsonProperty("url")
        public void setUrl(String url) {
            this.url = url;
        }

        @JsonProperty("html_url")
        public String getHtmlUrl() {
            return htmlUrl;
        }

        @JsonProperty("html_url")
        public void setHtmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
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

        @JsonProperty("organizations_url")
        public String getOrganizationsUrl() {
            return organizationsUrl;
        }

        @JsonProperty("organizations_url")
        public void setOrganizationsUrl(String organizationsUrl) {
            this.organizationsUrl = organizationsUrl;
        }

        @JsonProperty("repos_url")
        public String getReposUrl() {
            return reposUrl;
        }

        @JsonProperty("repos_url")
        public void setReposUrl(String reposUrl) {
            this.reposUrl = reposUrl;
        }

        @JsonProperty("events_url")
        public String getEventsUrl() {
            return eventsUrl;
        }

        @JsonProperty("events_url")
        public void setEventsUrl(String eventsUrl) {
            this.eventsUrl = eventsUrl;
        }

        @JsonProperty("received_events_url")
        public String getReceivedEventsUrl() {
            return receivedEventsUrl;
        }

        @JsonProperty("received_events_url")
        public void setReceivedEventsUrl(String receivedEventsUrl) {
            this.receivedEventsUrl = receivedEventsUrl;
        }

        @JsonProperty("type")
        public String getType() {
            return type;
        }

        @JsonProperty("type")
        public void setType(String type) {
            this.type = type;
        }

        @JsonProperty("site_admin")
        public Boolean getSiteAdmin() {
            return siteAdmin;
        }

        @JsonProperty("site_admin")
        public void setSiteAdmin(Boolean siteAdmin) {
            this.siteAdmin = siteAdmin;
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
            sb.append(GetPullRequestResponse.User.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("login");
            sb.append('=');
            sb.append(((this.login == null)?"<null>":this.login));
            sb.append(',');
            sb.append("id");
            sb.append('=');
            sb.append(((this.id == null)?"<null>":this.id));
            sb.append(',');
            sb.append("nodeId");
            sb.append('=');
            sb.append(((this.nodeId == null)?"<null>":this.nodeId));
            sb.append(',');
            sb.append("avatarUrl");
            sb.append('=');
            sb.append(((this.avatarUrl == null)?"<null>":this.avatarUrl));
            sb.append(',');
            sb.append("gravatarId");
            sb.append('=');
            sb.append(((this.gravatarId == null)?"<null>":this.gravatarId));
            sb.append(',');
            sb.append("url");
            sb.append('=');
            sb.append(((this.url == null)?"<null>":this.url));
            sb.append(',');
            sb.append("htmlUrl");
            sb.append('=');
            sb.append(((this.htmlUrl == null)?"<null>":this.htmlUrl));
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
            sb.append("starredUrl");
            sb.append('=');
            sb.append(((this.starredUrl == null)?"<null>":this.starredUrl));
            sb.append(',');
            sb.append("subscriptionsUrl");
            sb.append('=');
            sb.append(((this.subscriptionsUrl == null)?"<null>":this.subscriptionsUrl));
            sb.append(',');
            sb.append("organizationsUrl");
            sb.append('=');
            sb.append(((this.organizationsUrl == null)?"<null>":this.organizationsUrl));
            sb.append(',');
            sb.append("reposUrl");
            sb.append('=');
            sb.append(((this.reposUrl == null)?"<null>":this.reposUrl));
            sb.append(',');
            sb.append("eventsUrl");
            sb.append('=');
            sb.append(((this.eventsUrl == null)?"<null>":this.eventsUrl));
            sb.append(',');
            sb.append("receivedEventsUrl");
            sb.append('=');
            sb.append(((this.receivedEventsUrl == null)?"<null>":this.receivedEventsUrl));
            sb.append(',');
            sb.append("type");
            sb.append('=');
            sb.append(((this.type == null)?"<null>":this.type));
            sb.append(',');
            sb.append("siteAdmin");
            sb.append('=');
            sb.append(((this.siteAdmin == null)?"<null>":this.siteAdmin));
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
            result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
            result = ((result* 31)+((this.starredUrl == null)? 0 :this.starredUrl.hashCode()));
            result = ((result* 31)+((this.gravatarId == null)? 0 :this.gravatarId.hashCode()));
            result = ((result* 31)+((this.followersUrl == null)? 0 :this.followersUrl.hashCode()));
            result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
            result = ((result* 31)+((this.eventsUrl == null)? 0 :this.eventsUrl.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.nodeId == null)? 0 :this.nodeId.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof GetPullRequestResponse.User) == false) {
                return false;
            }
            GetPullRequestResponse.User rhs = ((GetPullRequestResponse.User) other);
            return ((((((((((((((((((((this.receivedEventsUrl == rhs.receivedEventsUrl)||((this.receivedEventsUrl!= null)&&this.receivedEventsUrl.equals(rhs.receivedEventsUrl)))&&((this.siteAdmin == rhs.siteAdmin)||((this.siteAdmin!= null)&&this.siteAdmin.equals(rhs.siteAdmin))))&&((this.followingUrl == rhs.followingUrl)||((this.followingUrl!= null)&&this.followingUrl.equals(rhs.followingUrl))))&&((this.gistsUrl == rhs.gistsUrl)||((this.gistsUrl!= null)&&this.gistsUrl.equals(rhs.gistsUrl))))&&((this.avatarUrl == rhs.avatarUrl)||((this.avatarUrl!= null)&&this.avatarUrl.equals(rhs.avatarUrl))))&&((this.organizationsUrl == rhs.organizationsUrl)||((this.organizationsUrl!= null)&&this.organizationsUrl.equals(rhs.organizationsUrl))))&&((this.reposUrl == rhs.reposUrl)||((this.reposUrl!= null)&&this.reposUrl.equals(rhs.reposUrl))))&&((this.htmlUrl == rhs.htmlUrl)||((this.htmlUrl!= null)&&this.htmlUrl.equals(rhs.htmlUrl))))&&((this.subscriptionsUrl == rhs.subscriptionsUrl)||((this.subscriptionsUrl!= null)&&this.subscriptionsUrl.equals(rhs.subscriptionsUrl))))&&((this.login == rhs.login)||((this.login!= null)&&this.login.equals(rhs.login))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))))&&((this.starredUrl == rhs.starredUrl)||((this.starredUrl!= null)&&this.starredUrl.equals(rhs.starredUrl))))&&((this.gravatarId == rhs.gravatarId)||((this.gravatarId!= null)&&this.gravatarId.equals(rhs.gravatarId))))&&((this.followersUrl == rhs.followersUrl)||((this.followersUrl!= null)&&this.followersUrl.equals(rhs.followersUrl))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.eventsUrl == rhs.eventsUrl)||((this.eventsUrl!= null)&&this.eventsUrl.equals(rhs.eventsUrl))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.nodeId == rhs.nodeId)||((this.nodeId!= null)&&this.nodeId.equals(rhs.nodeId))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "login",
        "id",
        "node_id",
        "avatar_url",
        "gravatar_id",
        "url",
        "html_url",
        "followers_url",
        "following_url",
        "gists_url",
        "starred_url",
        "subscriptions_url",
        "organizations_url",
        "repos_url",
        "events_url",
        "received_events_url",
        "type",
        "site_admin"
    })
    @Generated("jsonschema2pojo")
    public static class User2 {

        @JsonProperty("login")
        private String login;
        @JsonProperty("id")
        private Long id;
        @JsonProperty("node_id")
        private String nodeId;
        @JsonProperty("avatar_url")
        private String avatarUrl;
        @JsonProperty("gravatar_id")
        private String gravatarId;
        @JsonProperty("url")
        private String url;
        @JsonProperty("html_url")
        private String htmlUrl;
        @JsonProperty("followers_url")
        private String followersUrl;
        @JsonProperty("following_url")
        private String followingUrl;
        @JsonProperty("gists_url")
        private String gistsUrl;
        @JsonProperty("starred_url")
        private String starredUrl;
        @JsonProperty("subscriptions_url")
        private String subscriptionsUrl;
        @JsonProperty("organizations_url")
        private String organizationsUrl;
        @JsonProperty("repos_url")
        private String reposUrl;
        @JsonProperty("events_url")
        private String eventsUrl;
        @JsonProperty("received_events_url")
        private String receivedEventsUrl;
        @JsonProperty("type")
        private String type;
        @JsonProperty("site_admin")
        private Boolean siteAdmin;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public User2() {
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
         * @param url
         * @param starredUrl
         * @param gravatarId
         * @param followersUrl
         * @param id
         * @param eventsUrl
         * @param nodeId
         */
        public User2(String login, Long id, String nodeId, String avatarUrl, String gravatarId, String url, String htmlUrl, String followersUrl, String followingUrl, String gistsUrl, String starredUrl, String subscriptionsUrl, String organizationsUrl, String reposUrl, String eventsUrl, String receivedEventsUrl, String type, Boolean siteAdmin) {
            super();
            this.login = login;
            this.id = id;
            this.nodeId = nodeId;
            this.avatarUrl = avatarUrl;
            this.gravatarId = gravatarId;
            this.url = url;
            this.htmlUrl = htmlUrl;
            this.followersUrl = followersUrl;
            this.followingUrl = followingUrl;
            this.gistsUrl = gistsUrl;
            this.starredUrl = starredUrl;
            this.subscriptionsUrl = subscriptionsUrl;
            this.organizationsUrl = organizationsUrl;
            this.reposUrl = reposUrl;
            this.eventsUrl = eventsUrl;
            this.receivedEventsUrl = receivedEventsUrl;
            this.type = type;
            this.siteAdmin = siteAdmin;
        }

        @JsonProperty("login")
        public String getLogin() {
            return login;
        }

        @JsonProperty("login")
        public void setLogin(String login) {
            this.login = login;
        }

        @JsonProperty("id")
        public Long getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(Long id) {
            this.id = id;
        }

        @JsonProperty("node_id")
        public String getNodeId() {
            return nodeId;
        }

        @JsonProperty("node_id")
        public void setNodeId(String nodeId) {
            this.nodeId = nodeId;
        }

        @JsonProperty("avatar_url")
        public String getAvatarUrl() {
            return avatarUrl;
        }

        @JsonProperty("avatar_url")
        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        @JsonProperty("gravatar_id")
        public String getGravatarId() {
            return gravatarId;
        }

        @JsonProperty("gravatar_id")
        public void setGravatarId(String gravatarId) {
            this.gravatarId = gravatarId;
        }

        @JsonProperty("url")
        public String getUrl() {
            return url;
        }

        @JsonProperty("url")
        public void setUrl(String url) {
            this.url = url;
        }

        @JsonProperty("html_url")
        public String getHtmlUrl() {
            return htmlUrl;
        }

        @JsonProperty("html_url")
        public void setHtmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
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

        @JsonProperty("organizations_url")
        public String getOrganizationsUrl() {
            return organizationsUrl;
        }

        @JsonProperty("organizations_url")
        public void setOrganizationsUrl(String organizationsUrl) {
            this.organizationsUrl = organizationsUrl;
        }

        @JsonProperty("repos_url")
        public String getReposUrl() {
            return reposUrl;
        }

        @JsonProperty("repos_url")
        public void setReposUrl(String reposUrl) {
            this.reposUrl = reposUrl;
        }

        @JsonProperty("events_url")
        public String getEventsUrl() {
            return eventsUrl;
        }

        @JsonProperty("events_url")
        public void setEventsUrl(String eventsUrl) {
            this.eventsUrl = eventsUrl;
        }

        @JsonProperty("received_events_url")
        public String getReceivedEventsUrl() {
            return receivedEventsUrl;
        }

        @JsonProperty("received_events_url")
        public void setReceivedEventsUrl(String receivedEventsUrl) {
            this.receivedEventsUrl = receivedEventsUrl;
        }

        @JsonProperty("type")
        public String getType() {
            return type;
        }

        @JsonProperty("type")
        public void setType(String type) {
            this.type = type;
        }

        @JsonProperty("site_admin")
        public Boolean getSiteAdmin() {
            return siteAdmin;
        }

        @JsonProperty("site_admin")
        public void setSiteAdmin(Boolean siteAdmin) {
            this.siteAdmin = siteAdmin;
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
            sb.append(GetPullRequestResponse.User2 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("login");
            sb.append('=');
            sb.append(((this.login == null)?"<null>":this.login));
            sb.append(',');
            sb.append("id");
            sb.append('=');
            sb.append(((this.id == null)?"<null>":this.id));
            sb.append(',');
            sb.append("nodeId");
            sb.append('=');
            sb.append(((this.nodeId == null)?"<null>":this.nodeId));
            sb.append(',');
            sb.append("avatarUrl");
            sb.append('=');
            sb.append(((this.avatarUrl == null)?"<null>":this.avatarUrl));
            sb.append(',');
            sb.append("gravatarId");
            sb.append('=');
            sb.append(((this.gravatarId == null)?"<null>":this.gravatarId));
            sb.append(',');
            sb.append("url");
            sb.append('=');
            sb.append(((this.url == null)?"<null>":this.url));
            sb.append(',');
            sb.append("htmlUrl");
            sb.append('=');
            sb.append(((this.htmlUrl == null)?"<null>":this.htmlUrl));
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
            sb.append("starredUrl");
            sb.append('=');
            sb.append(((this.starredUrl == null)?"<null>":this.starredUrl));
            sb.append(',');
            sb.append("subscriptionsUrl");
            sb.append('=');
            sb.append(((this.subscriptionsUrl == null)?"<null>":this.subscriptionsUrl));
            sb.append(',');
            sb.append("organizationsUrl");
            sb.append('=');
            sb.append(((this.organizationsUrl == null)?"<null>":this.organizationsUrl));
            sb.append(',');
            sb.append("reposUrl");
            sb.append('=');
            sb.append(((this.reposUrl == null)?"<null>":this.reposUrl));
            sb.append(',');
            sb.append("eventsUrl");
            sb.append('=');
            sb.append(((this.eventsUrl == null)?"<null>":this.eventsUrl));
            sb.append(',');
            sb.append("receivedEventsUrl");
            sb.append('=');
            sb.append(((this.receivedEventsUrl == null)?"<null>":this.receivedEventsUrl));
            sb.append(',');
            sb.append("type");
            sb.append('=');
            sb.append(((this.type == null)?"<null>":this.type));
            sb.append(',');
            sb.append("siteAdmin");
            sb.append('=');
            sb.append(((this.siteAdmin == null)?"<null>":this.siteAdmin));
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
            result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
            result = ((result* 31)+((this.starredUrl == null)? 0 :this.starredUrl.hashCode()));
            result = ((result* 31)+((this.gravatarId == null)? 0 :this.gravatarId.hashCode()));
            result = ((result* 31)+((this.followersUrl == null)? 0 :this.followersUrl.hashCode()));
            result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
            result = ((result* 31)+((this.eventsUrl == null)? 0 :this.eventsUrl.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.nodeId == null)? 0 :this.nodeId.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof GetPullRequestResponse.User2) == false) {
                return false;
            }
            GetPullRequestResponse.User2 rhs = ((GetPullRequestResponse.User2) other);
            return ((((((((((((((((((((this.receivedEventsUrl == rhs.receivedEventsUrl)||((this.receivedEventsUrl!= null)&&this.receivedEventsUrl.equals(rhs.receivedEventsUrl)))&&((this.siteAdmin == rhs.siteAdmin)||((this.siteAdmin!= null)&&this.siteAdmin.equals(rhs.siteAdmin))))&&((this.followingUrl == rhs.followingUrl)||((this.followingUrl!= null)&&this.followingUrl.equals(rhs.followingUrl))))&&((this.gistsUrl == rhs.gistsUrl)||((this.gistsUrl!= null)&&this.gistsUrl.equals(rhs.gistsUrl))))&&((this.avatarUrl == rhs.avatarUrl)||((this.avatarUrl!= null)&&this.avatarUrl.equals(rhs.avatarUrl))))&&((this.organizationsUrl == rhs.organizationsUrl)||((this.organizationsUrl!= null)&&this.organizationsUrl.equals(rhs.organizationsUrl))))&&((this.reposUrl == rhs.reposUrl)||((this.reposUrl!= null)&&this.reposUrl.equals(rhs.reposUrl))))&&((this.htmlUrl == rhs.htmlUrl)||((this.htmlUrl!= null)&&this.htmlUrl.equals(rhs.htmlUrl))))&&((this.subscriptionsUrl == rhs.subscriptionsUrl)||((this.subscriptionsUrl!= null)&&this.subscriptionsUrl.equals(rhs.subscriptionsUrl))))&&((this.login == rhs.login)||((this.login!= null)&&this.login.equals(rhs.login))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))))&&((this.starredUrl == rhs.starredUrl)||((this.starredUrl!= null)&&this.starredUrl.equals(rhs.starredUrl))))&&((this.gravatarId == rhs.gravatarId)||((this.gravatarId!= null)&&this.gravatarId.equals(rhs.gravatarId))))&&((this.followersUrl == rhs.followersUrl)||((this.followersUrl!= null)&&this.followersUrl.equals(rhs.followersUrl))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.eventsUrl == rhs.eventsUrl)||((this.eventsUrl!= null)&&this.eventsUrl.equals(rhs.eventsUrl))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.nodeId == rhs.nodeId)||((this.nodeId!= null)&&this.nodeId.equals(rhs.nodeId))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "login",
        "id",
        "node_id",
        "avatar_url",
        "gravatar_id",
        "url",
        "html_url",
        "followers_url",
        "following_url",
        "gists_url",
        "starred_url",
        "subscriptions_url",
        "organizations_url",
        "repos_url",
        "events_url",
        "received_events_url",
        "type",
        "site_admin"
    })
    @Generated("jsonschema2pojo")
    public static class User3 {

        @JsonProperty("login")
        private String login;
        @JsonProperty("id")
        private Long id;
        @JsonProperty("node_id")
        private String nodeId;
        @JsonProperty("avatar_url")
        private String avatarUrl;
        @JsonProperty("gravatar_id")
        private String gravatarId;
        @JsonProperty("url")
        private String url;
        @JsonProperty("html_url")
        private String htmlUrl;
        @JsonProperty("followers_url")
        private String followersUrl;
        @JsonProperty("following_url")
        private String followingUrl;
        @JsonProperty("gists_url")
        private String gistsUrl;
        @JsonProperty("starred_url")
        private String starredUrl;
        @JsonProperty("subscriptions_url")
        private String subscriptionsUrl;
        @JsonProperty("organizations_url")
        private String organizationsUrl;
        @JsonProperty("repos_url")
        private String reposUrl;
        @JsonProperty("events_url")
        private String eventsUrl;
        @JsonProperty("received_events_url")
        private String receivedEventsUrl;
        @JsonProperty("type")
        private String type;
        @JsonProperty("site_admin")
        private Boolean siteAdmin;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public User3() {
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
         * @param url
         * @param starredUrl
         * @param gravatarId
         * @param followersUrl
         * @param id
         * @param eventsUrl
         * @param nodeId
         */
        public User3(String login, Long id, String nodeId, String avatarUrl, String gravatarId, String url, String htmlUrl, String followersUrl, String followingUrl, String gistsUrl, String starredUrl, String subscriptionsUrl, String organizationsUrl, String reposUrl, String eventsUrl, String receivedEventsUrl, String type, Boolean siteAdmin) {
            super();
            this.login = login;
            this.id = id;
            this.nodeId = nodeId;
            this.avatarUrl = avatarUrl;
            this.gravatarId = gravatarId;
            this.url = url;
            this.htmlUrl = htmlUrl;
            this.followersUrl = followersUrl;
            this.followingUrl = followingUrl;
            this.gistsUrl = gistsUrl;
            this.starredUrl = starredUrl;
            this.subscriptionsUrl = subscriptionsUrl;
            this.organizationsUrl = organizationsUrl;
            this.reposUrl = reposUrl;
            this.eventsUrl = eventsUrl;
            this.receivedEventsUrl = receivedEventsUrl;
            this.type = type;
            this.siteAdmin = siteAdmin;
        }

        @JsonProperty("login")
        public String getLogin() {
            return login;
        }

        @JsonProperty("login")
        public void setLogin(String login) {
            this.login = login;
        }

        @JsonProperty("id")
        public Long getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(Long id) {
            this.id = id;
        }

        @JsonProperty("node_id")
        public String getNodeId() {
            return nodeId;
        }

        @JsonProperty("node_id")
        public void setNodeId(String nodeId) {
            this.nodeId = nodeId;
        }

        @JsonProperty("avatar_url")
        public String getAvatarUrl() {
            return avatarUrl;
        }

        @JsonProperty("avatar_url")
        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        @JsonProperty("gravatar_id")
        public String getGravatarId() {
            return gravatarId;
        }

        @JsonProperty("gravatar_id")
        public void setGravatarId(String gravatarId) {
            this.gravatarId = gravatarId;
        }

        @JsonProperty("url")
        public String getUrl() {
            return url;
        }

        @JsonProperty("url")
        public void setUrl(String url) {
            this.url = url;
        }

        @JsonProperty("html_url")
        public String getHtmlUrl() {
            return htmlUrl;
        }

        @JsonProperty("html_url")
        public void setHtmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
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

        @JsonProperty("organizations_url")
        public String getOrganizationsUrl() {
            return organizationsUrl;
        }

        @JsonProperty("organizations_url")
        public void setOrganizationsUrl(String organizationsUrl) {
            this.organizationsUrl = organizationsUrl;
        }

        @JsonProperty("repos_url")
        public String getReposUrl() {
            return reposUrl;
        }

        @JsonProperty("repos_url")
        public void setReposUrl(String reposUrl) {
            this.reposUrl = reposUrl;
        }

        @JsonProperty("events_url")
        public String getEventsUrl() {
            return eventsUrl;
        }

        @JsonProperty("events_url")
        public void setEventsUrl(String eventsUrl) {
            this.eventsUrl = eventsUrl;
        }

        @JsonProperty("received_events_url")
        public String getReceivedEventsUrl() {
            return receivedEventsUrl;
        }

        @JsonProperty("received_events_url")
        public void setReceivedEventsUrl(String receivedEventsUrl) {
            this.receivedEventsUrl = receivedEventsUrl;
        }

        @JsonProperty("type")
        public String getType() {
            return type;
        }

        @JsonProperty("type")
        public void setType(String type) {
            this.type = type;
        }

        @JsonProperty("site_admin")
        public Boolean getSiteAdmin() {
            return siteAdmin;
        }

        @JsonProperty("site_admin")
        public void setSiteAdmin(Boolean siteAdmin) {
            this.siteAdmin = siteAdmin;
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
            sb.append(GetPullRequestResponse.User3 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("login");
            sb.append('=');
            sb.append(((this.login == null)?"<null>":this.login));
            sb.append(',');
            sb.append("id");
            sb.append('=');
            sb.append(((this.id == null)?"<null>":this.id));
            sb.append(',');
            sb.append("nodeId");
            sb.append('=');
            sb.append(((this.nodeId == null)?"<null>":this.nodeId));
            sb.append(',');
            sb.append("avatarUrl");
            sb.append('=');
            sb.append(((this.avatarUrl == null)?"<null>":this.avatarUrl));
            sb.append(',');
            sb.append("gravatarId");
            sb.append('=');
            sb.append(((this.gravatarId == null)?"<null>":this.gravatarId));
            sb.append(',');
            sb.append("url");
            sb.append('=');
            sb.append(((this.url == null)?"<null>":this.url));
            sb.append(',');
            sb.append("htmlUrl");
            sb.append('=');
            sb.append(((this.htmlUrl == null)?"<null>":this.htmlUrl));
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
            sb.append("starredUrl");
            sb.append('=');
            sb.append(((this.starredUrl == null)?"<null>":this.starredUrl));
            sb.append(',');
            sb.append("subscriptionsUrl");
            sb.append('=');
            sb.append(((this.subscriptionsUrl == null)?"<null>":this.subscriptionsUrl));
            sb.append(',');
            sb.append("organizationsUrl");
            sb.append('=');
            sb.append(((this.organizationsUrl == null)?"<null>":this.organizationsUrl));
            sb.append(',');
            sb.append("reposUrl");
            sb.append('=');
            sb.append(((this.reposUrl == null)?"<null>":this.reposUrl));
            sb.append(',');
            sb.append("eventsUrl");
            sb.append('=');
            sb.append(((this.eventsUrl == null)?"<null>":this.eventsUrl));
            sb.append(',');
            sb.append("receivedEventsUrl");
            sb.append('=');
            sb.append(((this.receivedEventsUrl == null)?"<null>":this.receivedEventsUrl));
            sb.append(',');
            sb.append("type");
            sb.append('=');
            sb.append(((this.type == null)?"<null>":this.type));
            sb.append(',');
            sb.append("siteAdmin");
            sb.append('=');
            sb.append(((this.siteAdmin == null)?"<null>":this.siteAdmin));
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
            result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
            result = ((result* 31)+((this.starredUrl == null)? 0 :this.starredUrl.hashCode()));
            result = ((result* 31)+((this.gravatarId == null)? 0 :this.gravatarId.hashCode()));
            result = ((result* 31)+((this.followersUrl == null)? 0 :this.followersUrl.hashCode()));
            result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
            result = ((result* 31)+((this.eventsUrl == null)? 0 :this.eventsUrl.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.nodeId == null)? 0 :this.nodeId.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof GetPullRequestResponse.User3) == false) {
                return false;
            }
            GetPullRequestResponse.User3 rhs = ((GetPullRequestResponse.User3) other);
            return ((((((((((((((((((((this.receivedEventsUrl == rhs.receivedEventsUrl)||((this.receivedEventsUrl!= null)&&this.receivedEventsUrl.equals(rhs.receivedEventsUrl)))&&((this.siteAdmin == rhs.siteAdmin)||((this.siteAdmin!= null)&&this.siteAdmin.equals(rhs.siteAdmin))))&&((this.followingUrl == rhs.followingUrl)||((this.followingUrl!= null)&&this.followingUrl.equals(rhs.followingUrl))))&&((this.gistsUrl == rhs.gistsUrl)||((this.gistsUrl!= null)&&this.gistsUrl.equals(rhs.gistsUrl))))&&((this.avatarUrl == rhs.avatarUrl)||((this.avatarUrl!= null)&&this.avatarUrl.equals(rhs.avatarUrl))))&&((this.organizationsUrl == rhs.organizationsUrl)||((this.organizationsUrl!= null)&&this.organizationsUrl.equals(rhs.organizationsUrl))))&&((this.reposUrl == rhs.reposUrl)||((this.reposUrl!= null)&&this.reposUrl.equals(rhs.reposUrl))))&&((this.htmlUrl == rhs.htmlUrl)||((this.htmlUrl!= null)&&this.htmlUrl.equals(rhs.htmlUrl))))&&((this.subscriptionsUrl == rhs.subscriptionsUrl)||((this.subscriptionsUrl!= null)&&this.subscriptionsUrl.equals(rhs.subscriptionsUrl))))&&((this.login == rhs.login)||((this.login!= null)&&this.login.equals(rhs.login))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))))&&((this.starredUrl == rhs.starredUrl)||((this.starredUrl!= null)&&this.starredUrl.equals(rhs.starredUrl))))&&((this.gravatarId == rhs.gravatarId)||((this.gravatarId!= null)&&this.gravatarId.equals(rhs.gravatarId))))&&((this.followersUrl == rhs.followersUrl)||((this.followersUrl!= null)&&this.followersUrl.equals(rhs.followersUrl))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.eventsUrl == rhs.eventsUrl)||((this.eventsUrl!= null)&&this.eventsUrl.equals(rhs.eventsUrl))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.nodeId == rhs.nodeId)||((this.nodeId!= null)&&this.nodeId.equals(rhs.nodeId))));
        }

    }

}
