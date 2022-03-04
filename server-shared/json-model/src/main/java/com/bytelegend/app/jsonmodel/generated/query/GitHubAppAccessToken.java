
package com.bytelegend.app.jsonmodel.generated.query;

import java.util.HashMap;
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
    "token",
    "expires_at",
    "permissions",
    "repository_selection"
})
@Generated("jsonschema2pojo")
public class GitHubAppAccessToken {

    @JsonProperty("token")
    private String token;
    @JsonProperty("expires_at")
    private String expiresAt;
    @JsonProperty("permissions")
    private GitHubAppAccessToken.Permissions permissions;
    @JsonProperty("repository_selection")
    private String repositorySelection;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public GitHubAppAccessToken() {
    }

    /**
     * 
     * @param permissions
     * @param repositorySelection
     * @param expiresAt
     * @param token
     */
    public GitHubAppAccessToken(String token, String expiresAt, GitHubAppAccessToken.Permissions permissions, String repositorySelection) {
        super();
        this.token = token;
        this.expiresAt = expiresAt;
        this.permissions = permissions;
        this.repositorySelection = repositorySelection;
    }

    @JsonProperty("token")
    public String getToken() {
        return token;
    }

    @JsonProperty("token")
    public void setToken(String token) {
        this.token = token;
    }

    @JsonProperty("expires_at")
    public String getExpiresAt() {
        return expiresAt;
    }

    @JsonProperty("expires_at")
    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }

    @JsonProperty("permissions")
    public GitHubAppAccessToken.Permissions getPermissions() {
        return permissions;
    }

    @JsonProperty("permissions")
    public void setPermissions(GitHubAppAccessToken.Permissions permissions) {
        this.permissions = permissions;
    }

    @JsonProperty("repository_selection")
    public String getRepositorySelection() {
        return repositorySelection;
    }

    @JsonProperty("repository_selection")
    public void setRepositorySelection(String repositorySelection) {
        this.repositorySelection = repositorySelection;
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
        sb.append(GitHubAppAccessToken.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("token");
        sb.append('=');
        sb.append(((this.token == null)?"<null>":this.token));
        sb.append(',');
        sb.append("expiresAt");
        sb.append('=');
        sb.append(((this.expiresAt == null)?"<null>":this.expiresAt));
        sb.append(',');
        sb.append("permissions");
        sb.append('=');
        sb.append(((this.permissions == null)?"<null>":this.permissions));
        sb.append(',');
        sb.append("repositorySelection");
        sb.append('=');
        sb.append(((this.repositorySelection == null)?"<null>":this.repositorySelection));
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
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.expiresAt == null)? 0 :this.expiresAt.hashCode()));
        result = ((result* 31)+((this.permissions == null)? 0 :this.permissions.hashCode()));
        result = ((result* 31)+((this.repositorySelection == null)? 0 :this.repositorySelection.hashCode()));
        result = ((result* 31)+((this.token == null)? 0 :this.token.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof GitHubAppAccessToken) == false) {
            return false;
        }
        GitHubAppAccessToken rhs = ((GitHubAppAccessToken) other);
        return ((((((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties)))&&((this.expiresAt == rhs.expiresAt)||((this.expiresAt!= null)&&this.expiresAt.equals(rhs.expiresAt))))&&((this.permissions == rhs.permissions)||((this.permissions!= null)&&this.permissions.equals(rhs.permissions))))&&((this.repositorySelection == rhs.repositorySelection)||((this.repositorySelection!= null)&&this.repositorySelection.equals(rhs.repositorySelection))))&&((this.token == rhs.token)||((this.token!= null)&&this.token.equals(rhs.token))));
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "actions",
        "checks",
        "contents",
        "deployments",
        "metadata",
        "pull_requests",
        "statuses"
    })
    @Generated("jsonschema2pojo")
    public static class Permissions {

        @JsonProperty("actions")
        private String actions;
        @JsonProperty("checks")
        private String checks;
        @JsonProperty("contents")
        private String contents;
        @JsonProperty("deployments")
        private String deployments;
        @JsonProperty("metadata")
        private String metadata;
        @JsonProperty("pull_requests")
        private String pullRequests;
        @JsonProperty("statuses")
        private String statuses;
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
         * @param deployments
         * @param metadata
         * @param checks
         * @param contents
         * @param statuses
         * @param pullRequests
         * @param actions
         */
        public Permissions(String actions, String checks, String contents, String deployments, String metadata, String pullRequests, String statuses) {
            super();
            this.actions = actions;
            this.checks = checks;
            this.contents = contents;
            this.deployments = deployments;
            this.metadata = metadata;
            this.pullRequests = pullRequests;
            this.statuses = statuses;
        }

        @JsonProperty("actions")
        public String getActions() {
            return actions;
        }

        @JsonProperty("actions")
        public void setActions(String actions) {
            this.actions = actions;
        }

        @JsonProperty("checks")
        public String getChecks() {
            return checks;
        }

        @JsonProperty("checks")
        public void setChecks(String checks) {
            this.checks = checks;
        }

        @JsonProperty("contents")
        public String getContents() {
            return contents;
        }

        @JsonProperty("contents")
        public void setContents(String contents) {
            this.contents = contents;
        }

        @JsonProperty("deployments")
        public String getDeployments() {
            return deployments;
        }

        @JsonProperty("deployments")
        public void setDeployments(String deployments) {
            this.deployments = deployments;
        }

        @JsonProperty("metadata")
        public String getMetadata() {
            return metadata;
        }

        @JsonProperty("metadata")
        public void setMetadata(String metadata) {
            this.metadata = metadata;
        }

        @JsonProperty("pull_requests")
        public String getPullRequests() {
            return pullRequests;
        }

        @JsonProperty("pull_requests")
        public void setPullRequests(String pullRequests) {
            this.pullRequests = pullRequests;
        }

        @JsonProperty("statuses")
        public String getStatuses() {
            return statuses;
        }

        @JsonProperty("statuses")
        public void setStatuses(String statuses) {
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
            sb.append(GitHubAppAccessToken.Permissions.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("actions");
            sb.append('=');
            sb.append(((this.actions == null)?"<null>":this.actions));
            sb.append(',');
            sb.append("checks");
            sb.append('=');
            sb.append(((this.checks == null)?"<null>":this.checks));
            sb.append(',');
            sb.append("contents");
            sb.append('=');
            sb.append(((this.contents == null)?"<null>":this.contents));
            sb.append(',');
            sb.append("deployments");
            sb.append('=');
            sb.append(((this.deployments == null)?"<null>":this.deployments));
            sb.append(',');
            sb.append("metadata");
            sb.append('=');
            sb.append(((this.metadata == null)?"<null>":this.metadata));
            sb.append(',');
            sb.append("pullRequests");
            sb.append('=');
            sb.append(((this.pullRequests == null)?"<null>":this.pullRequests));
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
            result = ((result* 31)+((this.deployments == null)? 0 :this.deployments.hashCode()));
            result = ((result* 31)+((this.metadata == null)? 0 :this.metadata.hashCode()));
            result = ((result* 31)+((this.checks == null)? 0 :this.checks.hashCode()));
            result = ((result* 31)+((this.contents == null)? 0 :this.contents.hashCode()));
            result = ((result* 31)+((this.statuses == null)? 0 :this.statuses.hashCode()));
            result = ((result* 31)+((this.pullRequests == null)? 0 :this.pullRequests.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.actions == null)? 0 :this.actions.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof GitHubAppAccessToken.Permissions) == false) {
                return false;
            }
            GitHubAppAccessToken.Permissions rhs = ((GitHubAppAccessToken.Permissions) other);
            return (((((((((this.deployments == rhs.deployments)||((this.deployments!= null)&&this.deployments.equals(rhs.deployments)))&&((this.metadata == rhs.metadata)||((this.metadata!= null)&&this.metadata.equals(rhs.metadata))))&&((this.checks == rhs.checks)||((this.checks!= null)&&this.checks.equals(rhs.checks))))&&((this.contents == rhs.contents)||((this.contents!= null)&&this.contents.equals(rhs.contents))))&&((this.statuses == rhs.statuses)||((this.statuses!= null)&&this.statuses.equals(rhs.statuses))))&&((this.pullRequests == rhs.pullRequests)||((this.pullRequests!= null)&&this.pullRequests.equals(rhs.pullRequests))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.actions == rhs.actions)||((this.actions!= null)&&this.actions.equals(rhs.actions))));
        }

    }

}
