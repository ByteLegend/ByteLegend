
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
    "check_run",
    "repository",
    "organization",
    "sender",
    "installation"
})
@Generated("jsonschema2pojo")
public class CheckRunGitHubEvent implements com.bytelegend.app.jsonmodel.event.GitHubEvent {

    @JsonProperty("action")
    private String action;
    @JsonProperty("check_run")
    private CheckRunGitHubEvent.CheckRun checkRun;
    @JsonProperty("repository")
    private CheckRunGitHubEvent.Repository repository;
    @JsonProperty("organization")
    private CheckRunGitHubEvent.Organization organization;
    @JsonProperty("sender")
    private CheckRunGitHubEvent.Sender sender;
    @JsonProperty("installation")
    private CheckRunGitHubEvent.Installation installation;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public CheckRunGitHubEvent() {
    }

    /**
     * 
     * @param sender
     * @param organization
     * @param installation
     * @param action
     * @param checkRun
     * @param repository
     */
    public CheckRunGitHubEvent(String action, CheckRunGitHubEvent.CheckRun checkRun, CheckRunGitHubEvent.Repository repository, CheckRunGitHubEvent.Organization organization, CheckRunGitHubEvent.Sender sender, CheckRunGitHubEvent.Installation installation) {
        super();
        this.action = action;
        this.checkRun = checkRun;
        this.repository = repository;
        this.organization = organization;
        this.sender = sender;
        this.installation = installation;
    }

    @JsonProperty("action")
    public String getAction() {
        return action;
    }

    @JsonProperty("action")
    public void setAction(String action) {
        this.action = action;
    }

    @JsonProperty("check_run")
    public CheckRunGitHubEvent.CheckRun getCheckRun() {
        return checkRun;
    }

    @JsonProperty("check_run")
    public void setCheckRun(CheckRunGitHubEvent.CheckRun checkRun) {
        this.checkRun = checkRun;
    }

    @JsonProperty("repository")
    public CheckRunGitHubEvent.Repository getRepository() {
        return repository;
    }

    @JsonProperty("repository")
    public void setRepository(CheckRunGitHubEvent.Repository repository) {
        this.repository = repository;
    }

    @JsonProperty("organization")
    public CheckRunGitHubEvent.Organization getOrganization() {
        return organization;
    }

    @JsonProperty("organization")
    public void setOrganization(CheckRunGitHubEvent.Organization organization) {
        this.organization = organization;
    }

    @JsonProperty("sender")
    public CheckRunGitHubEvent.Sender getSender() {
        return sender;
    }

    @JsonProperty("sender")
    public void setSender(CheckRunGitHubEvent.Sender sender) {
        this.sender = sender;
    }

    @JsonProperty("installation")
    public CheckRunGitHubEvent.Installation getInstallation() {
        return installation;
    }

    @JsonProperty("installation")
    public void setInstallation(CheckRunGitHubEvent.Installation installation) {
        this.installation = installation;
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
        sb.append(CheckRunGitHubEvent.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("action");
        sb.append('=');
        sb.append(((this.action == null)?"<null>":this.action));
        sb.append(',');
        sb.append("checkRun");
        sb.append('=');
        sb.append(((this.checkRun == null)?"<null>":this.checkRun));
        sb.append(',');
        sb.append("repository");
        sb.append('=');
        sb.append(((this.repository == null)?"<null>":this.repository));
        sb.append(',');
        sb.append("organization");
        sb.append('=');
        sb.append(((this.organization == null)?"<null>":this.organization));
        sb.append(',');
        sb.append("sender");
        sb.append('=');
        sb.append(((this.sender == null)?"<null>":this.sender));
        sb.append(',');
        sb.append("installation");
        sb.append('=');
        sb.append(((this.installation == null)?"<null>":this.installation));
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
        result = ((result* 31)+((this.sender == null)? 0 :this.sender.hashCode()));
        result = ((result* 31)+((this.organization == null)? 0 :this.organization.hashCode()));
        result = ((result* 31)+((this.installation == null)? 0 :this.installation.hashCode()));
        result = ((result* 31)+((this.action == null)? 0 :this.action.hashCode()));
        result = ((result* 31)+((this.checkRun == null)? 0 :this.checkRun.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.repository == null)? 0 :this.repository.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CheckRunGitHubEvent) == false) {
            return false;
        }
        CheckRunGitHubEvent rhs = ((CheckRunGitHubEvent) other);
        return ((((((((this.sender == rhs.sender)||((this.sender!= null)&&this.sender.equals(rhs.sender)))&&((this.organization == rhs.organization)||((this.organization!= null)&&this.organization.equals(rhs.organization))))&&((this.installation == rhs.installation)||((this.installation!= null)&&this.installation.equals(rhs.installation))))&&((this.action == rhs.action)||((this.action!= null)&&this.action.equals(rhs.action))))&&((this.checkRun == rhs.checkRun)||((this.checkRun!= null)&&this.checkRun.equals(rhs.checkRun))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.repository == rhs.repository)||((this.repository!= null)&&this.repository.equals(rhs.repository))));
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "id",
        "slug",
        "node_id",
        "owner",
        "name",
        "description",
        "external_url",
        "html_url",
        "created_at",
        "updated_at",
        "permissions",
        "events"
    })
    @Generated("jsonschema2pojo")
    public static class App {

        @JsonProperty("id")
        private Long id;
        @JsonProperty("slug")
        private String slug;
        @JsonProperty("node_id")
        private String nodeId;
        @JsonProperty("owner")
        private CheckRunGitHubEvent.Owner owner;
        @JsonProperty("name")
        private String name;
        @JsonProperty("description")
        private String description;
        @JsonProperty("external_url")
        private String externalUrl;
        @JsonProperty("html_url")
        private String htmlUrl;
        @JsonProperty("created_at")
        private String createdAt;
        @JsonProperty("updated_at")
        private String updatedAt;
        @JsonProperty("permissions")
        private CheckRunGitHubEvent.Permissions permissions;
        @JsonProperty("events")
        private List<String> events = new ArrayList<String>();
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public App() {
        }

        /**
         * 
         * @param owner
         * @param externalUrl
         * @param createdAt
         * @param permissions
         * @param name
         * @param htmlUrl
         * @param description
         * @param id
         * @param nodeId
         * @param slug
         * @param events
         * @param updatedAt
         */
        public App(Long id, String slug, String nodeId, CheckRunGitHubEvent.Owner owner, String name, String description, String externalUrl, String htmlUrl, String createdAt, String updatedAt, CheckRunGitHubEvent.Permissions permissions, List<String> events) {
            super();
            this.id = id;
            this.slug = slug;
            this.nodeId = nodeId;
            this.owner = owner;
            this.name = name;
            this.description = description;
            this.externalUrl = externalUrl;
            this.htmlUrl = htmlUrl;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
            this.permissions = permissions;
            this.events = events;
        }

        @JsonProperty("id")
        public Long getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(Long id) {
            this.id = id;
        }

        @JsonProperty("slug")
        public String getSlug() {
            return slug;
        }

        @JsonProperty("slug")
        public void setSlug(String slug) {
            this.slug = slug;
        }

        @JsonProperty("node_id")
        public String getNodeId() {
            return nodeId;
        }

        @JsonProperty("node_id")
        public void setNodeId(String nodeId) {
            this.nodeId = nodeId;
        }

        @JsonProperty("owner")
        public CheckRunGitHubEvent.Owner getOwner() {
            return owner;
        }

        @JsonProperty("owner")
        public void setOwner(CheckRunGitHubEvent.Owner owner) {
            this.owner = owner;
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

        @JsonProperty("external_url")
        public String getExternalUrl() {
            return externalUrl;
        }

        @JsonProperty("external_url")
        public void setExternalUrl(String externalUrl) {
            this.externalUrl = externalUrl;
        }

        @JsonProperty("html_url")
        public String getHtmlUrl() {
            return htmlUrl;
        }

        @JsonProperty("html_url")
        public void setHtmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
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
        public CheckRunGitHubEvent.Permissions getPermissions() {
            return permissions;
        }

        @JsonProperty("permissions")
        public void setPermissions(CheckRunGitHubEvent.Permissions permissions) {
            this.permissions = permissions;
        }

        @JsonProperty("events")
        public List<String> getEvents() {
            return events;
        }

        @JsonProperty("events")
        public void setEvents(List<String> events) {
            this.events = events;
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
            sb.append(CheckRunGitHubEvent.App.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("id");
            sb.append('=');
            sb.append(((this.id == null)?"<null>":this.id));
            sb.append(',');
            sb.append("slug");
            sb.append('=');
            sb.append(((this.slug == null)?"<null>":this.slug));
            sb.append(',');
            sb.append("nodeId");
            sb.append('=');
            sb.append(((this.nodeId == null)?"<null>":this.nodeId));
            sb.append(',');
            sb.append("owner");
            sb.append('=');
            sb.append(((this.owner == null)?"<null>":this.owner));
            sb.append(',');
            sb.append("name");
            sb.append('=');
            sb.append(((this.name == null)?"<null>":this.name));
            sb.append(',');
            sb.append("description");
            sb.append('=');
            sb.append(((this.description == null)?"<null>":this.description));
            sb.append(',');
            sb.append("externalUrl");
            sb.append('=');
            sb.append(((this.externalUrl == null)?"<null>":this.externalUrl));
            sb.append(',');
            sb.append("htmlUrl");
            sb.append('=');
            sb.append(((this.htmlUrl == null)?"<null>":this.htmlUrl));
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
            sb.append("events");
            sb.append('=');
            sb.append(((this.events == null)?"<null>":this.events));
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
            result = ((result* 31)+((this.owner == null)? 0 :this.owner.hashCode()));
            result = ((result* 31)+((this.externalUrl == null)? 0 :this.externalUrl.hashCode()));
            result = ((result* 31)+((this.htmlUrl == null)? 0 :this.htmlUrl.hashCode()));
            result = ((result* 31)+((this.description == null)? 0 :this.description.hashCode()));
            result = ((result* 31)+((this.createdAt == null)? 0 :this.createdAt.hashCode()));
            result = ((result* 31)+((this.permissions == null)? 0 :this.permissions.hashCode()));
            result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
            result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.nodeId == null)? 0 :this.nodeId.hashCode()));
            result = ((result* 31)+((this.slug == null)? 0 :this.slug.hashCode()));
            result = ((result* 31)+((this.events == null)? 0 :this.events.hashCode()));
            result = ((result* 31)+((this.updatedAt == null)? 0 :this.updatedAt.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof CheckRunGitHubEvent.App) == false) {
                return false;
            }
            CheckRunGitHubEvent.App rhs = ((CheckRunGitHubEvent.App) other);
            return ((((((((((((((this.owner == rhs.owner)||((this.owner!= null)&&this.owner.equals(rhs.owner)))&&((this.externalUrl == rhs.externalUrl)||((this.externalUrl!= null)&&this.externalUrl.equals(rhs.externalUrl))))&&((this.htmlUrl == rhs.htmlUrl)||((this.htmlUrl!= null)&&this.htmlUrl.equals(rhs.htmlUrl))))&&((this.description == rhs.description)||((this.description!= null)&&this.description.equals(rhs.description))))&&((this.createdAt == rhs.createdAt)||((this.createdAt!= null)&&this.createdAt.equals(rhs.createdAt))))&&((this.permissions == rhs.permissions)||((this.permissions!= null)&&this.permissions.equals(rhs.permissions))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.nodeId == rhs.nodeId)||((this.nodeId!= null)&&this.nodeId.equals(rhs.nodeId))))&&((this.slug == rhs.slug)||((this.slug!= null)&&this.slug.equals(rhs.slug))))&&((this.events == rhs.events)||((this.events!= null)&&this.events.equals(rhs.events))))&&((this.updatedAt == rhs.updatedAt)||((this.updatedAt!= null)&&this.updatedAt.equals(rhs.updatedAt))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "id",
        "slug",
        "node_id",
        "owner",
        "name",
        "description",
        "external_url",
        "html_url",
        "created_at",
        "updated_at",
        "permissions",
        "events"
    })
    @Generated("jsonschema2pojo")
    public static class App2 {

        @JsonProperty("id")
        private Long id;
        @JsonProperty("slug")
        private String slug;
        @JsonProperty("node_id")
        private String nodeId;
        @JsonProperty("owner")
        private CheckRunGitHubEvent.Owner2 owner;
        @JsonProperty("name")
        private String name;
        @JsonProperty("description")
        private String description;
        @JsonProperty("external_url")
        private String externalUrl;
        @JsonProperty("html_url")
        private String htmlUrl;
        @JsonProperty("created_at")
        private String createdAt;
        @JsonProperty("updated_at")
        private String updatedAt;
        @JsonProperty("permissions")
        private CheckRunGitHubEvent.Permissions2 permissions;
        @JsonProperty("events")
        private List<String> events = new ArrayList<String>();
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public App2() {
        }

        /**
         * 
         * @param owner
         * @param externalUrl
         * @param createdAt
         * @param permissions
         * @param name
         * @param htmlUrl
         * @param description
         * @param id
         * @param nodeId
         * @param slug
         * @param events
         * @param updatedAt
         */
        public App2(Long id, String slug, String nodeId, CheckRunGitHubEvent.Owner2 owner, String name, String description, String externalUrl, String htmlUrl, String createdAt, String updatedAt, CheckRunGitHubEvent.Permissions2 permissions, List<String> events) {
            super();
            this.id = id;
            this.slug = slug;
            this.nodeId = nodeId;
            this.owner = owner;
            this.name = name;
            this.description = description;
            this.externalUrl = externalUrl;
            this.htmlUrl = htmlUrl;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
            this.permissions = permissions;
            this.events = events;
        }

        @JsonProperty("id")
        public Long getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(Long id) {
            this.id = id;
        }

        @JsonProperty("slug")
        public String getSlug() {
            return slug;
        }

        @JsonProperty("slug")
        public void setSlug(String slug) {
            this.slug = slug;
        }

        @JsonProperty("node_id")
        public String getNodeId() {
            return nodeId;
        }

        @JsonProperty("node_id")
        public void setNodeId(String nodeId) {
            this.nodeId = nodeId;
        }

        @JsonProperty("owner")
        public CheckRunGitHubEvent.Owner2 getOwner() {
            return owner;
        }

        @JsonProperty("owner")
        public void setOwner(CheckRunGitHubEvent.Owner2 owner) {
            this.owner = owner;
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

        @JsonProperty("external_url")
        public String getExternalUrl() {
            return externalUrl;
        }

        @JsonProperty("external_url")
        public void setExternalUrl(String externalUrl) {
            this.externalUrl = externalUrl;
        }

        @JsonProperty("html_url")
        public String getHtmlUrl() {
            return htmlUrl;
        }

        @JsonProperty("html_url")
        public void setHtmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
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
        public CheckRunGitHubEvent.Permissions2 getPermissions() {
            return permissions;
        }

        @JsonProperty("permissions")
        public void setPermissions(CheckRunGitHubEvent.Permissions2 permissions) {
            this.permissions = permissions;
        }

        @JsonProperty("events")
        public List<String> getEvents() {
            return events;
        }

        @JsonProperty("events")
        public void setEvents(List<String> events) {
            this.events = events;
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
            sb.append(CheckRunGitHubEvent.App2 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("id");
            sb.append('=');
            sb.append(((this.id == null)?"<null>":this.id));
            sb.append(',');
            sb.append("slug");
            sb.append('=');
            sb.append(((this.slug == null)?"<null>":this.slug));
            sb.append(',');
            sb.append("nodeId");
            sb.append('=');
            sb.append(((this.nodeId == null)?"<null>":this.nodeId));
            sb.append(',');
            sb.append("owner");
            sb.append('=');
            sb.append(((this.owner == null)?"<null>":this.owner));
            sb.append(',');
            sb.append("name");
            sb.append('=');
            sb.append(((this.name == null)?"<null>":this.name));
            sb.append(',');
            sb.append("description");
            sb.append('=');
            sb.append(((this.description == null)?"<null>":this.description));
            sb.append(',');
            sb.append("externalUrl");
            sb.append('=');
            sb.append(((this.externalUrl == null)?"<null>":this.externalUrl));
            sb.append(',');
            sb.append("htmlUrl");
            sb.append('=');
            sb.append(((this.htmlUrl == null)?"<null>":this.htmlUrl));
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
            sb.append("events");
            sb.append('=');
            sb.append(((this.events == null)?"<null>":this.events));
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
            result = ((result* 31)+((this.owner == null)? 0 :this.owner.hashCode()));
            result = ((result* 31)+((this.externalUrl == null)? 0 :this.externalUrl.hashCode()));
            result = ((result* 31)+((this.htmlUrl == null)? 0 :this.htmlUrl.hashCode()));
            result = ((result* 31)+((this.description == null)? 0 :this.description.hashCode()));
            result = ((result* 31)+((this.createdAt == null)? 0 :this.createdAt.hashCode()));
            result = ((result* 31)+((this.permissions == null)? 0 :this.permissions.hashCode()));
            result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
            result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.nodeId == null)? 0 :this.nodeId.hashCode()));
            result = ((result* 31)+((this.slug == null)? 0 :this.slug.hashCode()));
            result = ((result* 31)+((this.events == null)? 0 :this.events.hashCode()));
            result = ((result* 31)+((this.updatedAt == null)? 0 :this.updatedAt.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof CheckRunGitHubEvent.App2) == false) {
                return false;
            }
            CheckRunGitHubEvent.App2 rhs = ((CheckRunGitHubEvent.App2) other);
            return ((((((((((((((this.owner == rhs.owner)||((this.owner!= null)&&this.owner.equals(rhs.owner)))&&((this.externalUrl == rhs.externalUrl)||((this.externalUrl!= null)&&this.externalUrl.equals(rhs.externalUrl))))&&((this.htmlUrl == rhs.htmlUrl)||((this.htmlUrl!= null)&&this.htmlUrl.equals(rhs.htmlUrl))))&&((this.description == rhs.description)||((this.description!= null)&&this.description.equals(rhs.description))))&&((this.createdAt == rhs.createdAt)||((this.createdAt!= null)&&this.createdAt.equals(rhs.createdAt))))&&((this.permissions == rhs.permissions)||((this.permissions!= null)&&this.permissions.equals(rhs.permissions))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.nodeId == rhs.nodeId)||((this.nodeId!= null)&&this.nodeId.equals(rhs.nodeId))))&&((this.slug == rhs.slug)||((this.slug!= null)&&this.slug.equals(rhs.slug))))&&((this.events == rhs.events)||((this.events!= null)&&this.events.equals(rhs.events))))&&((this.updatedAt == rhs.updatedAt)||((this.updatedAt!= null)&&this.updatedAt.equals(rhs.updatedAt))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "id",
        "node_id",
        "head_sha",
        "external_id",
        "url",
        "html_url",
        "details_url",
        "status",
        "conclusion",
        "started_at",
        "completed_at",
        "output",
        "name",
        "check_suite",
        "app",
        "pull_requests"
    })
    @Generated("jsonschema2pojo")
    public static class CheckRun {

        @JsonProperty("id")
        private Long id;
        @JsonProperty("node_id")
        private String nodeId;
        @JsonProperty("head_sha")
        private String headSha;
        @JsonProperty("external_id")
        private String externalId;
        @JsonProperty("url")
        private String url;
        @JsonProperty("html_url")
        private String htmlUrl;
        @JsonProperty("details_url")
        private String detailsUrl;
        @JsonProperty("status")
        private String status;
        @JsonProperty("conclusion")
        private String conclusion;
        @JsonProperty("started_at")
        private String startedAt;
        @JsonProperty("completed_at")
        private String completedAt;
        @JsonProperty("output")
        private CheckRunGitHubEvent.Output output;
        @JsonProperty("name")
        private String name;
        @JsonProperty("check_suite")
        private CheckRunGitHubEvent.CheckSuite checkSuite;
        @JsonProperty("app")
        private CheckRunGitHubEvent.App2 app;
        @JsonProperty("pull_requests")
        private List<Object> pullRequests = new ArrayList<Object>();
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public CheckRun() {
        }

        /**
         * 
         * @param app
         * @param completedAt
         * @param checkSuite
         * @param detailsUrl
         * @param htmlUrl
         * @param externalId
         * @param startedAt
         * @param headSha
         * @param pullRequests
         * @param url
         * @param conclusion
         * @param output
         * @param name
         * @param id
         * @param nodeId
         * @param status
         */
        public CheckRun(Long id, String nodeId, String headSha, String externalId, String url, String htmlUrl, String detailsUrl, String status, String conclusion, String startedAt, String completedAt, CheckRunGitHubEvent.Output output, String name, CheckRunGitHubEvent.CheckSuite checkSuite, CheckRunGitHubEvent.App2 app, List<Object> pullRequests) {
            super();
            this.id = id;
            this.nodeId = nodeId;
            this.headSha = headSha;
            this.externalId = externalId;
            this.url = url;
            this.htmlUrl = htmlUrl;
            this.detailsUrl = detailsUrl;
            this.status = status;
            this.conclusion = conclusion;
            this.startedAt = startedAt;
            this.completedAt = completedAt;
            this.output = output;
            this.name = name;
            this.checkSuite = checkSuite;
            this.app = app;
            this.pullRequests = pullRequests;
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

        @JsonProperty("head_sha")
        public String getHeadSha() {
            return headSha;
        }

        @JsonProperty("head_sha")
        public void setHeadSha(String headSha) {
            this.headSha = headSha;
        }

        @JsonProperty("external_id")
        public String getExternalId() {
            return externalId;
        }

        @JsonProperty("external_id")
        public void setExternalId(String externalId) {
            this.externalId = externalId;
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

        @JsonProperty("details_url")
        public String getDetailsUrl() {
            return detailsUrl;
        }

        @JsonProperty("details_url")
        public void setDetailsUrl(String detailsUrl) {
            this.detailsUrl = detailsUrl;
        }

        @JsonProperty("status")
        public String getStatus() {
            return status;
        }

        @JsonProperty("status")
        public void setStatus(String status) {
            this.status = status;
        }

        @JsonProperty("conclusion")
        public String getConclusion() {
            return conclusion;
        }

        @JsonProperty("conclusion")
        public void setConclusion(String conclusion) {
            this.conclusion = conclusion;
        }

        @JsonProperty("started_at")
        public String getStartedAt() {
            return startedAt;
        }

        @JsonProperty("started_at")
        public void setStartedAt(String startedAt) {
            this.startedAt = startedAt;
        }

        @JsonProperty("completed_at")
        public String getCompletedAt() {
            return completedAt;
        }

        @JsonProperty("completed_at")
        public void setCompletedAt(String completedAt) {
            this.completedAt = completedAt;
        }

        @JsonProperty("output")
        public CheckRunGitHubEvent.Output getOutput() {
            return output;
        }

        @JsonProperty("output")
        public void setOutput(CheckRunGitHubEvent.Output output) {
            this.output = output;
        }

        @JsonProperty("name")
        public String getName() {
            return name;
        }

        @JsonProperty("name")
        public void setName(String name) {
            this.name = name;
        }

        @JsonProperty("check_suite")
        public CheckRunGitHubEvent.CheckSuite getCheckSuite() {
            return checkSuite;
        }

        @JsonProperty("check_suite")
        public void setCheckSuite(CheckRunGitHubEvent.CheckSuite checkSuite) {
            this.checkSuite = checkSuite;
        }

        @JsonProperty("app")
        public CheckRunGitHubEvent.App2 getApp() {
            return app;
        }

        @JsonProperty("app")
        public void setApp(CheckRunGitHubEvent.App2 app) {
            this.app = app;
        }

        @JsonProperty("pull_requests")
        public List<Object> getPullRequests() {
            return pullRequests;
        }

        @JsonProperty("pull_requests")
        public void setPullRequests(List<Object> pullRequests) {
            this.pullRequests = pullRequests;
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
            sb.append(CheckRunGitHubEvent.CheckRun.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("id");
            sb.append('=');
            sb.append(((this.id == null)?"<null>":this.id));
            sb.append(',');
            sb.append("nodeId");
            sb.append('=');
            sb.append(((this.nodeId == null)?"<null>":this.nodeId));
            sb.append(',');
            sb.append("headSha");
            sb.append('=');
            sb.append(((this.headSha == null)?"<null>":this.headSha));
            sb.append(',');
            sb.append("externalId");
            sb.append('=');
            sb.append(((this.externalId == null)?"<null>":this.externalId));
            sb.append(',');
            sb.append("url");
            sb.append('=');
            sb.append(((this.url == null)?"<null>":this.url));
            sb.append(',');
            sb.append("htmlUrl");
            sb.append('=');
            sb.append(((this.htmlUrl == null)?"<null>":this.htmlUrl));
            sb.append(',');
            sb.append("detailsUrl");
            sb.append('=');
            sb.append(((this.detailsUrl == null)?"<null>":this.detailsUrl));
            sb.append(',');
            sb.append("status");
            sb.append('=');
            sb.append(((this.status == null)?"<null>":this.status));
            sb.append(',');
            sb.append("conclusion");
            sb.append('=');
            sb.append(((this.conclusion == null)?"<null>":this.conclusion));
            sb.append(',');
            sb.append("startedAt");
            sb.append('=');
            sb.append(((this.startedAt == null)?"<null>":this.startedAt));
            sb.append(',');
            sb.append("completedAt");
            sb.append('=');
            sb.append(((this.completedAt == null)?"<null>":this.completedAt));
            sb.append(',');
            sb.append("output");
            sb.append('=');
            sb.append(((this.output == null)?"<null>":this.output));
            sb.append(',');
            sb.append("name");
            sb.append('=');
            sb.append(((this.name == null)?"<null>":this.name));
            sb.append(',');
            sb.append("checkSuite");
            sb.append('=');
            sb.append(((this.checkSuite == null)?"<null>":this.checkSuite));
            sb.append(',');
            sb.append("app");
            sb.append('=');
            sb.append(((this.app == null)?"<null>":this.app));
            sb.append(',');
            sb.append("pullRequests");
            sb.append('=');
            sb.append(((this.pullRequests == null)?"<null>":this.pullRequests));
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
            result = ((result* 31)+((this.app == null)? 0 :this.app.hashCode()));
            result = ((result* 31)+((this.completedAt == null)? 0 :this.completedAt.hashCode()));
            result = ((result* 31)+((this.checkSuite == null)? 0 :this.checkSuite.hashCode()));
            result = ((result* 31)+((this.detailsUrl == null)? 0 :this.detailsUrl.hashCode()));
            result = ((result* 31)+((this.htmlUrl == null)? 0 :this.htmlUrl.hashCode()));
            result = ((result* 31)+((this.externalId == null)? 0 :this.externalId.hashCode()));
            result = ((result* 31)+((this.startedAt == null)? 0 :this.startedAt.hashCode()));
            result = ((result* 31)+((this.headSha == null)? 0 :this.headSha.hashCode()));
            result = ((result* 31)+((this.pullRequests == null)? 0 :this.pullRequests.hashCode()));
            result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
            result = ((result* 31)+((this.conclusion == null)? 0 :this.conclusion.hashCode()));
            result = ((result* 31)+((this.output == null)? 0 :this.output.hashCode()));
            result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
            result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.nodeId == null)? 0 :this.nodeId.hashCode()));
            result = ((result* 31)+((this.status == null)? 0 :this.status.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof CheckRunGitHubEvent.CheckRun) == false) {
                return false;
            }
            CheckRunGitHubEvent.CheckRun rhs = ((CheckRunGitHubEvent.CheckRun) other);
            return ((((((((((((((((((this.app == rhs.app)||((this.app!= null)&&this.app.equals(rhs.app)))&&((this.completedAt == rhs.completedAt)||((this.completedAt!= null)&&this.completedAt.equals(rhs.completedAt))))&&((this.checkSuite == rhs.checkSuite)||((this.checkSuite!= null)&&this.checkSuite.equals(rhs.checkSuite))))&&((this.detailsUrl == rhs.detailsUrl)||((this.detailsUrl!= null)&&this.detailsUrl.equals(rhs.detailsUrl))))&&((this.htmlUrl == rhs.htmlUrl)||((this.htmlUrl!= null)&&this.htmlUrl.equals(rhs.htmlUrl))))&&((this.externalId == rhs.externalId)||((this.externalId!= null)&&this.externalId.equals(rhs.externalId))))&&((this.startedAt == rhs.startedAt)||((this.startedAt!= null)&&this.startedAt.equals(rhs.startedAt))))&&((this.headSha == rhs.headSha)||((this.headSha!= null)&&this.headSha.equals(rhs.headSha))))&&((this.pullRequests == rhs.pullRequests)||((this.pullRequests!= null)&&this.pullRequests.equals(rhs.pullRequests))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))))&&((this.conclusion == rhs.conclusion)||((this.conclusion!= null)&&this.conclusion.equals(rhs.conclusion))))&&((this.output == rhs.output)||((this.output!= null)&&this.output.equals(rhs.output))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.nodeId == rhs.nodeId)||((this.nodeId!= null)&&this.nodeId.equals(rhs.nodeId))))&&((this.status == rhs.status)||((this.status!= null)&&this.status.equals(rhs.status))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "id",
        "node_id",
        "head_branch",
        "head_sha",
        "status",
        "conclusion",
        "url",
        "before",
        "after",
        "pull_requests",
        "app",
        "created_at",
        "updated_at"
    })
    @Generated("jsonschema2pojo")
    public static class CheckSuite {

        @JsonProperty("id")
        private Long id;
        @JsonProperty("node_id")
        private String nodeId;
        @JsonProperty("head_branch")
        private String headBranch;
        @JsonProperty("head_sha")
        private String headSha;
        @JsonProperty("status")
        private String status;
        @JsonProperty("conclusion")
        private Object conclusion;
        @JsonProperty("url")
        private String url;
        @JsonProperty("before")
        private String before;
        @JsonProperty("after")
        private String after;
        @JsonProperty("pull_requests")
        private List<Object> pullRequests = new ArrayList<Object>();
        @JsonProperty("app")
        private CheckRunGitHubEvent.App app;
        @JsonProperty("created_at")
        private String createdAt;
        @JsonProperty("updated_at")
        private String updatedAt;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public CheckSuite() {
        }

        /**
         * 
         * @param app
         * @param before
         * @param headBranch
         * @param headSha
         * @param pullRequests
         * @param url
         * @param conclusion
         * @param createdAt
         * @param id
         * @param after
         * @param nodeId
         * @param status
         * @param updatedAt
         */
        public CheckSuite(Long id, String nodeId, String headBranch, String headSha, String status, Object conclusion, String url, String before, String after, List<Object> pullRequests, CheckRunGitHubEvent.App app, String createdAt, String updatedAt) {
            super();
            this.id = id;
            this.nodeId = nodeId;
            this.headBranch = headBranch;
            this.headSha = headSha;
            this.status = status;
            this.conclusion = conclusion;
            this.url = url;
            this.before = before;
            this.after = after;
            this.pullRequests = pullRequests;
            this.app = app;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
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

        @JsonProperty("head_branch")
        public String getHeadBranch() {
            return headBranch;
        }

        @JsonProperty("head_branch")
        public void setHeadBranch(String headBranch) {
            this.headBranch = headBranch;
        }

        @JsonProperty("head_sha")
        public String getHeadSha() {
            return headSha;
        }

        @JsonProperty("head_sha")
        public void setHeadSha(String headSha) {
            this.headSha = headSha;
        }

        @JsonProperty("status")
        public String getStatus() {
            return status;
        }

        @JsonProperty("status")
        public void setStatus(String status) {
            this.status = status;
        }

        @JsonProperty("conclusion")
        public Object getConclusion() {
            return conclusion;
        }

        @JsonProperty("conclusion")
        public void setConclusion(Object conclusion) {
            this.conclusion = conclusion;
        }

        @JsonProperty("url")
        public String getUrl() {
            return url;
        }

        @JsonProperty("url")
        public void setUrl(String url) {
            this.url = url;
        }

        @JsonProperty("before")
        public String getBefore() {
            return before;
        }

        @JsonProperty("before")
        public void setBefore(String before) {
            this.before = before;
        }

        @JsonProperty("after")
        public String getAfter() {
            return after;
        }

        @JsonProperty("after")
        public void setAfter(String after) {
            this.after = after;
        }

        @JsonProperty("pull_requests")
        public List<Object> getPullRequests() {
            return pullRequests;
        }

        @JsonProperty("pull_requests")
        public void setPullRequests(List<Object> pullRequests) {
            this.pullRequests = pullRequests;
        }

        @JsonProperty("app")
        public CheckRunGitHubEvent.App getApp() {
            return app;
        }

        @JsonProperty("app")
        public void setApp(CheckRunGitHubEvent.App app) {
            this.app = app;
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
            sb.append(CheckRunGitHubEvent.CheckSuite.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("id");
            sb.append('=');
            sb.append(((this.id == null)?"<null>":this.id));
            sb.append(',');
            sb.append("nodeId");
            sb.append('=');
            sb.append(((this.nodeId == null)?"<null>":this.nodeId));
            sb.append(',');
            sb.append("headBranch");
            sb.append('=');
            sb.append(((this.headBranch == null)?"<null>":this.headBranch));
            sb.append(',');
            sb.append("headSha");
            sb.append('=');
            sb.append(((this.headSha == null)?"<null>":this.headSha));
            sb.append(',');
            sb.append("status");
            sb.append('=');
            sb.append(((this.status == null)?"<null>":this.status));
            sb.append(',');
            sb.append("conclusion");
            sb.append('=');
            sb.append(((this.conclusion == null)?"<null>":this.conclusion));
            sb.append(',');
            sb.append("url");
            sb.append('=');
            sb.append(((this.url == null)?"<null>":this.url));
            sb.append(',');
            sb.append("before");
            sb.append('=');
            sb.append(((this.before == null)?"<null>":this.before));
            sb.append(',');
            sb.append("after");
            sb.append('=');
            sb.append(((this.after == null)?"<null>":this.after));
            sb.append(',');
            sb.append("pullRequests");
            sb.append('=');
            sb.append(((this.pullRequests == null)?"<null>":this.pullRequests));
            sb.append(',');
            sb.append("app");
            sb.append('=');
            sb.append(((this.app == null)?"<null>":this.app));
            sb.append(',');
            sb.append("createdAt");
            sb.append('=');
            sb.append(((this.createdAt == null)?"<null>":this.createdAt));
            sb.append(',');
            sb.append("updatedAt");
            sb.append('=');
            sb.append(((this.updatedAt == null)?"<null>":this.updatedAt));
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
            result = ((result* 31)+((this.app == null)? 0 :this.app.hashCode()));
            result = ((result* 31)+((this.before == null)? 0 :this.before.hashCode()));
            result = ((result* 31)+((this.headBranch == null)? 0 :this.headBranch.hashCode()));
            result = ((result* 31)+((this.headSha == null)? 0 :this.headSha.hashCode()));
            result = ((result* 31)+((this.pullRequests == null)? 0 :this.pullRequests.hashCode()));
            result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
            result = ((result* 31)+((this.conclusion == null)? 0 :this.conclusion.hashCode()));
            result = ((result* 31)+((this.createdAt == null)? 0 :this.createdAt.hashCode()));
            result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
            result = ((result* 31)+((this.after == null)? 0 :this.after.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.nodeId == null)? 0 :this.nodeId.hashCode()));
            result = ((result* 31)+((this.status == null)? 0 :this.status.hashCode()));
            result = ((result* 31)+((this.updatedAt == null)? 0 :this.updatedAt.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof CheckRunGitHubEvent.CheckSuite) == false) {
                return false;
            }
            CheckRunGitHubEvent.CheckSuite rhs = ((CheckRunGitHubEvent.CheckSuite) other);
            return (((((((((((((((this.app == rhs.app)||((this.app!= null)&&this.app.equals(rhs.app)))&&((this.before == rhs.before)||((this.before!= null)&&this.before.equals(rhs.before))))&&((this.headBranch == rhs.headBranch)||((this.headBranch!= null)&&this.headBranch.equals(rhs.headBranch))))&&((this.headSha == rhs.headSha)||((this.headSha!= null)&&this.headSha.equals(rhs.headSha))))&&((this.pullRequests == rhs.pullRequests)||((this.pullRequests!= null)&&this.pullRequests.equals(rhs.pullRequests))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))))&&((this.conclusion == rhs.conclusion)||((this.conclusion!= null)&&this.conclusion.equals(rhs.conclusion))))&&((this.createdAt == rhs.createdAt)||((this.createdAt!= null)&&this.createdAt.equals(rhs.createdAt))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.after == rhs.after)||((this.after!= null)&&this.after.equals(rhs.after))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.nodeId == rhs.nodeId)||((this.nodeId!= null)&&this.nodeId.equals(rhs.nodeId))))&&((this.status == rhs.status)||((this.status!= null)&&this.status.equals(rhs.status))))&&((this.updatedAt == rhs.updatedAt)||((this.updatedAt!= null)&&this.updatedAt.equals(rhs.updatedAt))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "id",
        "node_id"
    })
    @Generated("jsonschema2pojo")
    public static class Installation {

        @JsonProperty("id")
        private Long id;
        @JsonProperty("node_id")
        private String nodeId;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Installation() {
        }

        /**
         * 
         * @param id
         * @param nodeId
         */
        public Installation(Long id, String nodeId) {
            super();
            this.id = id;
            this.nodeId = nodeId;
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
            sb.append(CheckRunGitHubEvent.Installation.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("id");
            sb.append('=');
            sb.append(((this.id == null)?"<null>":this.id));
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
            if ((other instanceof CheckRunGitHubEvent.Installation) == false) {
                return false;
            }
            CheckRunGitHubEvent.Installation rhs = ((CheckRunGitHubEvent.Installation) other);
            return ((((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.nodeId == rhs.nodeId)||((this.nodeId!= null)&&this.nodeId.equals(rhs.nodeId))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "key",
        "name",
        "spdx_id",
        "url",
        "node_id"
    })
    @Generated("jsonschema2pojo")
    public static class License {

        @JsonProperty("key")
        private String key;
        @JsonProperty("name")
        private String name;
        @JsonProperty("spdx_id")
        private String spdxId;
        @JsonProperty("url")
        private String url;
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
        public License(String key, String name, String spdxId, String url, String nodeId) {
            super();
            this.key = key;
            this.name = name;
            this.spdxId = spdxId;
            this.url = url;
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

        @JsonProperty("spdx_id")
        public String getSpdxId() {
            return spdxId;
        }

        @JsonProperty("spdx_id")
        public void setSpdxId(String spdxId) {
            this.spdxId = spdxId;
        }

        @JsonProperty("url")
        public String getUrl() {
            return url;
        }

        @JsonProperty("url")
        public void setUrl(String url) {
            this.url = url;
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
            sb.append(CheckRunGitHubEvent.License.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("key");
            sb.append('=');
            sb.append(((this.key == null)?"<null>":this.key));
            sb.append(',');
            sb.append("name");
            sb.append('=');
            sb.append(((this.name == null)?"<null>":this.name));
            sb.append(',');
            sb.append("spdxId");
            sb.append('=');
            sb.append(((this.spdxId == null)?"<null>":this.spdxId));
            sb.append(',');
            sb.append("url");
            sb.append('=');
            sb.append(((this.url == null)?"<null>":this.url));
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
            if ((other instanceof CheckRunGitHubEvent.License) == false) {
                return false;
            }
            CheckRunGitHubEvent.License rhs = ((CheckRunGitHubEvent.License) other);
            return (((((((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name)))&&((this.spdxId == rhs.spdxId)||((this.spdxId!= null)&&this.spdxId.equals(rhs.spdxId))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.nodeId == rhs.nodeId)||((this.nodeId!= null)&&this.nodeId.equals(rhs.nodeId))))&&((this.key == rhs.key)||((this.key!= null)&&this.key.equals(rhs.key))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "login",
        "id",
        "node_id",
        "url",
        "repos_url",
        "events_url",
        "hooks_url",
        "issues_url",
        "members_url",
        "public_members_url",
        "avatar_url",
        "description"
    })
    @Generated("jsonschema2pojo")
    public static class Organization {

        @JsonProperty("login")
        private String login;
        @JsonProperty("id")
        private Long id;
        @JsonProperty("node_id")
        private String nodeId;
        @JsonProperty("url")
        private String url;
        @JsonProperty("repos_url")
        private String reposUrl;
        @JsonProperty("events_url")
        private String eventsUrl;
        @JsonProperty("hooks_url")
        private String hooksUrl;
        @JsonProperty("issues_url")
        private String issuesUrl;
        @JsonProperty("members_url")
        private String membersUrl;
        @JsonProperty("public_members_url")
        private String publicMembersUrl;
        @JsonProperty("avatar_url")
        private String avatarUrl;
        @JsonProperty("description")
        private Object description;
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
         * @param reposUrl
         * @param avatarUrl
         * @param description
         * @param id
         * @param eventsUrl
         * @param membersUrl
         * @param login
         * @param publicMembersUrl
         * @param nodeId
         * @param url
         * @param hooksUrl
         */
        public Organization(String login, Long id, String nodeId, String url, String reposUrl, String eventsUrl, String hooksUrl, String issuesUrl, String membersUrl, String publicMembersUrl, String avatarUrl, Object description) {
            super();
            this.login = login;
            this.id = id;
            this.nodeId = nodeId;
            this.url = url;
            this.reposUrl = reposUrl;
            this.eventsUrl = eventsUrl;
            this.hooksUrl = hooksUrl;
            this.issuesUrl = issuesUrl;
            this.membersUrl = membersUrl;
            this.publicMembersUrl = publicMembersUrl;
            this.avatarUrl = avatarUrl;
            this.description = description;
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

        @JsonProperty("url")
        public String getUrl() {
            return url;
        }

        @JsonProperty("url")
        public void setUrl(String url) {
            this.url = url;
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

        @JsonProperty("hooks_url")
        public String getHooksUrl() {
            return hooksUrl;
        }

        @JsonProperty("hooks_url")
        public void setHooksUrl(String hooksUrl) {
            this.hooksUrl = hooksUrl;
        }

        @JsonProperty("issues_url")
        public String getIssuesUrl() {
            return issuesUrl;
        }

        @JsonProperty("issues_url")
        public void setIssuesUrl(String issuesUrl) {
            this.issuesUrl = issuesUrl;
        }

        @JsonProperty("members_url")
        public String getMembersUrl() {
            return membersUrl;
        }

        @JsonProperty("members_url")
        public void setMembersUrl(String membersUrl) {
            this.membersUrl = membersUrl;
        }

        @JsonProperty("public_members_url")
        public String getPublicMembersUrl() {
            return publicMembersUrl;
        }

        @JsonProperty("public_members_url")
        public void setPublicMembersUrl(String publicMembersUrl) {
            this.publicMembersUrl = publicMembersUrl;
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
            sb.append(CheckRunGitHubEvent.Organization.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
            sb.append("url");
            sb.append('=');
            sb.append(((this.url == null)?"<null>":this.url));
            sb.append(',');
            sb.append("reposUrl");
            sb.append('=');
            sb.append(((this.reposUrl == null)?"<null>":this.reposUrl));
            sb.append(',');
            sb.append("eventsUrl");
            sb.append('=');
            sb.append(((this.eventsUrl == null)?"<null>":this.eventsUrl));
            sb.append(',');
            sb.append("hooksUrl");
            sb.append('=');
            sb.append(((this.hooksUrl == null)?"<null>":this.hooksUrl));
            sb.append(',');
            sb.append("issuesUrl");
            sb.append('=');
            sb.append(((this.issuesUrl == null)?"<null>":this.issuesUrl));
            sb.append(',');
            sb.append("membersUrl");
            sb.append('=');
            sb.append(((this.membersUrl == null)?"<null>":this.membersUrl));
            sb.append(',');
            sb.append("publicMembersUrl");
            sb.append('=');
            sb.append(((this.publicMembersUrl == null)?"<null>":this.publicMembersUrl));
            sb.append(',');
            sb.append("avatarUrl");
            sb.append('=');
            sb.append(((this.avatarUrl == null)?"<null>":this.avatarUrl));
            sb.append(',');
            sb.append("description");
            sb.append('=');
            sb.append(((this.description == null)?"<null>":this.description));
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
            result = ((result* 31)+((this.reposUrl == null)? 0 :this.reposUrl.hashCode()));
            result = ((result* 31)+((this.avatarUrl == null)? 0 :this.avatarUrl.hashCode()));
            result = ((result* 31)+((this.description == null)? 0 :this.description.hashCode()));
            result = ((result* 31)+((this.membersUrl == null)? 0 :this.membersUrl.hashCode()));
            result = ((result* 31)+((this.login == null)? 0 :this.login.hashCode()));
            result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
            result = ((result* 31)+((this.hooksUrl == null)? 0 :this.hooksUrl.hashCode()));
            result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
            result = ((result* 31)+((this.eventsUrl == null)? 0 :this.eventsUrl.hashCode()));
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
            if ((other instanceof CheckRunGitHubEvent.Organization) == false) {
                return false;
            }
            CheckRunGitHubEvent.Organization rhs = ((CheckRunGitHubEvent.Organization) other);
            return ((((((((((((((this.issuesUrl == rhs.issuesUrl)||((this.issuesUrl!= null)&&this.issuesUrl.equals(rhs.issuesUrl)))&&((this.reposUrl == rhs.reposUrl)||((this.reposUrl!= null)&&this.reposUrl.equals(rhs.reposUrl))))&&((this.avatarUrl == rhs.avatarUrl)||((this.avatarUrl!= null)&&this.avatarUrl.equals(rhs.avatarUrl))))&&((this.description == rhs.description)||((this.description!= null)&&this.description.equals(rhs.description))))&&((this.membersUrl == rhs.membersUrl)||((this.membersUrl!= null)&&this.membersUrl.equals(rhs.membersUrl))))&&((this.login == rhs.login)||((this.login!= null)&&this.login.equals(rhs.login))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))))&&((this.hooksUrl == rhs.hooksUrl)||((this.hooksUrl!= null)&&this.hooksUrl.equals(rhs.hooksUrl))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.eventsUrl == rhs.eventsUrl)||((this.eventsUrl!= null)&&this.eventsUrl.equals(rhs.eventsUrl))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.publicMembersUrl == rhs.publicMembersUrl)||((this.publicMembersUrl!= null)&&this.publicMembersUrl.equals(rhs.publicMembersUrl))))&&((this.nodeId == rhs.nodeId)||((this.nodeId!= null)&&this.nodeId.equals(rhs.nodeId))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "title",
        "summary",
        "text",
        "annotations_count",
        "annotations_url"
    })
    @Generated("jsonschema2pojo")
    public static class Output {

        @JsonProperty("title")
        private Object title;
        @JsonProperty("summary")
        private Object summary;
        @JsonProperty("text")
        private Object text;
        @JsonProperty("annotations_count")
        private Long annotationsCount;
        @JsonProperty("annotations_url")
        private String annotationsUrl;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Output() {
        }

        /**
         * 
         * @param summary
         * @param annotationsCount
         * @param annotationsUrl
         * @param text
         * @param title
         */
        public Output(Object title, Object summary, Object text, Long annotationsCount, String annotationsUrl) {
            super();
            this.title = title;
            this.summary = summary;
            this.text = text;
            this.annotationsCount = annotationsCount;
            this.annotationsUrl = annotationsUrl;
        }

        @JsonProperty("title")
        public Object getTitle() {
            return title;
        }

        @JsonProperty("title")
        public void setTitle(Object title) {
            this.title = title;
        }

        @JsonProperty("summary")
        public Object getSummary() {
            return summary;
        }

        @JsonProperty("summary")
        public void setSummary(Object summary) {
            this.summary = summary;
        }

        @JsonProperty("text")
        public Object getText() {
            return text;
        }

        @JsonProperty("text")
        public void setText(Object text) {
            this.text = text;
        }

        @JsonProperty("annotations_count")
        public Long getAnnotationsCount() {
            return annotationsCount;
        }

        @JsonProperty("annotations_count")
        public void setAnnotationsCount(Long annotationsCount) {
            this.annotationsCount = annotationsCount;
        }

        @JsonProperty("annotations_url")
        public String getAnnotationsUrl() {
            return annotationsUrl;
        }

        @JsonProperty("annotations_url")
        public void setAnnotationsUrl(String annotationsUrl) {
            this.annotationsUrl = annotationsUrl;
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
            sb.append(CheckRunGitHubEvent.Output.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("title");
            sb.append('=');
            sb.append(((this.title == null)?"<null>":this.title));
            sb.append(',');
            sb.append("summary");
            sb.append('=');
            sb.append(((this.summary == null)?"<null>":this.summary));
            sb.append(',');
            sb.append("text");
            sb.append('=');
            sb.append(((this.text == null)?"<null>":this.text));
            sb.append(',');
            sb.append("annotationsCount");
            sb.append('=');
            sb.append(((this.annotationsCount == null)?"<null>":this.annotationsCount));
            sb.append(',');
            sb.append("annotationsUrl");
            sb.append('=');
            sb.append(((this.annotationsUrl == null)?"<null>":this.annotationsUrl));
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
            result = ((result* 31)+((this.summary == null)? 0 :this.summary.hashCode()));
            result = ((result* 31)+((this.annotationsCount == null)? 0 :this.annotationsCount.hashCode()));
            result = ((result* 31)+((this.annotationsUrl == null)? 0 :this.annotationsUrl.hashCode()));
            result = ((result* 31)+((this.text == null)? 0 :this.text.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.title == null)? 0 :this.title.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof CheckRunGitHubEvent.Output) == false) {
                return false;
            }
            CheckRunGitHubEvent.Output rhs = ((CheckRunGitHubEvent.Output) other);
            return (((((((this.summary == rhs.summary)||((this.summary!= null)&&this.summary.equals(rhs.summary)))&&((this.annotationsCount == rhs.annotationsCount)||((this.annotationsCount!= null)&&this.annotationsCount.equals(rhs.annotationsCount))))&&((this.annotationsUrl == rhs.annotationsUrl)||((this.annotationsUrl!= null)&&this.annotationsUrl.equals(rhs.annotationsUrl))))&&((this.text == rhs.text)||((this.text!= null)&&this.text.equals(rhs.text))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.title == rhs.title)||((this.title!= null)&&this.title.equals(rhs.title))));
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
            sb.append(CheckRunGitHubEvent.Owner.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
            if ((other instanceof CheckRunGitHubEvent.Owner) == false) {
                return false;
            }
            CheckRunGitHubEvent.Owner rhs = ((CheckRunGitHubEvent.Owner) other);
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
            sb.append(CheckRunGitHubEvent.Owner2 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
            if ((other instanceof CheckRunGitHubEvent.Owner2) == false) {
                return false;
            }
            CheckRunGitHubEvent.Owner2 rhs = ((CheckRunGitHubEvent.Owner2) other);
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
    public static class Owner3 {

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
         * @param url
         * @param starredUrl
         * @param gravatarId
         * @param followersUrl
         * @param id
         * @param eventsUrl
         * @param nodeId
         */
        public Owner3(String login, Long id, String nodeId, String avatarUrl, String gravatarId, String url, String htmlUrl, String followersUrl, String followingUrl, String gistsUrl, String starredUrl, String subscriptionsUrl, String organizationsUrl, String reposUrl, String eventsUrl, String receivedEventsUrl, String type, Boolean siteAdmin) {
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
            sb.append(CheckRunGitHubEvent.Owner3 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
            if ((other instanceof CheckRunGitHubEvent.Owner3) == false) {
                return false;
            }
            CheckRunGitHubEvent.Owner3 rhs = ((CheckRunGitHubEvent.Owner3) other);
            return ((((((((((((((((((((this.receivedEventsUrl == rhs.receivedEventsUrl)||((this.receivedEventsUrl!= null)&&this.receivedEventsUrl.equals(rhs.receivedEventsUrl)))&&((this.siteAdmin == rhs.siteAdmin)||((this.siteAdmin!= null)&&this.siteAdmin.equals(rhs.siteAdmin))))&&((this.followingUrl == rhs.followingUrl)||((this.followingUrl!= null)&&this.followingUrl.equals(rhs.followingUrl))))&&((this.gistsUrl == rhs.gistsUrl)||((this.gistsUrl!= null)&&this.gistsUrl.equals(rhs.gistsUrl))))&&((this.avatarUrl == rhs.avatarUrl)||((this.avatarUrl!= null)&&this.avatarUrl.equals(rhs.avatarUrl))))&&((this.organizationsUrl == rhs.organizationsUrl)||((this.organizationsUrl!= null)&&this.organizationsUrl.equals(rhs.organizationsUrl))))&&((this.reposUrl == rhs.reposUrl)||((this.reposUrl!= null)&&this.reposUrl.equals(rhs.reposUrl))))&&((this.htmlUrl == rhs.htmlUrl)||((this.htmlUrl!= null)&&this.htmlUrl.equals(rhs.htmlUrl))))&&((this.subscriptionsUrl == rhs.subscriptionsUrl)||((this.subscriptionsUrl!= null)&&this.subscriptionsUrl.equals(rhs.subscriptionsUrl))))&&((this.login == rhs.login)||((this.login!= null)&&this.login.equals(rhs.login))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))))&&((this.starredUrl == rhs.starredUrl)||((this.starredUrl!= null)&&this.starredUrl.equals(rhs.starredUrl))))&&((this.gravatarId == rhs.gravatarId)||((this.gravatarId!= null)&&this.gravatarId.equals(rhs.gravatarId))))&&((this.followersUrl == rhs.followersUrl)||((this.followersUrl!= null)&&this.followersUrl.equals(rhs.followersUrl))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.eventsUrl == rhs.eventsUrl)||((this.eventsUrl!= null)&&this.eventsUrl.equals(rhs.eventsUrl))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.nodeId == rhs.nodeId)||((this.nodeId!= null)&&this.nodeId.equals(rhs.nodeId))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "actions",
        "checks",
        "contents",
        "deployments",
        "discussions",
        "issues",
        "metadata",
        "organization_packages",
        "packages",
        "pages",
        "pull_requests",
        "repository_hooks",
        "repository_projects",
        "security_events",
        "statuses",
        "vulnerability_alerts"
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
        @JsonProperty("discussions")
        private String discussions;
        @JsonProperty("issues")
        private String issues;
        @JsonProperty("metadata")
        private String metadata;
        @JsonProperty("organization_packages")
        private String organizationPackages;
        @JsonProperty("packages")
        private String packages;
        @JsonProperty("pages")
        private String pages;
        @JsonProperty("pull_requests")
        private String pullRequests;
        @JsonProperty("repository_hooks")
        private String repositoryHooks;
        @JsonProperty("repository_projects")
        private String repositoryProjects;
        @JsonProperty("security_events")
        private String securityEvents;
        @JsonProperty("statuses")
        private String statuses;
        @JsonProperty("vulnerability_alerts")
        private String vulnerabilityAlerts;
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
         * @param securityEvents
         * @param metadata
         * @param organizationPackages
         * @param discussions
         * @param pullRequests
         * @param packages
         * @param issues
         * @param deployments
         * @param checks
         * @param pages
         * @param repositoryProjects
         * @param contents
         * @param statuses
         * @param vulnerabilityAlerts
         * @param actions
         * @param repositoryHooks
         */
        public Permissions(String actions, String checks, String contents, String deployments, String discussions, String issues, String metadata, String organizationPackages, String packages, String pages, String pullRequests, String repositoryHooks, String repositoryProjects, String securityEvents, String statuses, String vulnerabilityAlerts) {
            super();
            this.actions = actions;
            this.checks = checks;
            this.contents = contents;
            this.deployments = deployments;
            this.discussions = discussions;
            this.issues = issues;
            this.metadata = metadata;
            this.organizationPackages = organizationPackages;
            this.packages = packages;
            this.pages = pages;
            this.pullRequests = pullRequests;
            this.repositoryHooks = repositoryHooks;
            this.repositoryProjects = repositoryProjects;
            this.securityEvents = securityEvents;
            this.statuses = statuses;
            this.vulnerabilityAlerts = vulnerabilityAlerts;
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

        @JsonProperty("discussions")
        public String getDiscussions() {
            return discussions;
        }

        @JsonProperty("discussions")
        public void setDiscussions(String discussions) {
            this.discussions = discussions;
        }

        @JsonProperty("issues")
        public String getIssues() {
            return issues;
        }

        @JsonProperty("issues")
        public void setIssues(String issues) {
            this.issues = issues;
        }

        @JsonProperty("metadata")
        public String getMetadata() {
            return metadata;
        }

        @JsonProperty("metadata")
        public void setMetadata(String metadata) {
            this.metadata = metadata;
        }

        @JsonProperty("organization_packages")
        public String getOrganizationPackages() {
            return organizationPackages;
        }

        @JsonProperty("organization_packages")
        public void setOrganizationPackages(String organizationPackages) {
            this.organizationPackages = organizationPackages;
        }

        @JsonProperty("packages")
        public String getPackages() {
            return packages;
        }

        @JsonProperty("packages")
        public void setPackages(String packages) {
            this.packages = packages;
        }

        @JsonProperty("pages")
        public String getPages() {
            return pages;
        }

        @JsonProperty("pages")
        public void setPages(String pages) {
            this.pages = pages;
        }

        @JsonProperty("pull_requests")
        public String getPullRequests() {
            return pullRequests;
        }

        @JsonProperty("pull_requests")
        public void setPullRequests(String pullRequests) {
            this.pullRequests = pullRequests;
        }

        @JsonProperty("repository_hooks")
        public String getRepositoryHooks() {
            return repositoryHooks;
        }

        @JsonProperty("repository_hooks")
        public void setRepositoryHooks(String repositoryHooks) {
            this.repositoryHooks = repositoryHooks;
        }

        @JsonProperty("repository_projects")
        public String getRepositoryProjects() {
            return repositoryProjects;
        }

        @JsonProperty("repository_projects")
        public void setRepositoryProjects(String repositoryProjects) {
            this.repositoryProjects = repositoryProjects;
        }

        @JsonProperty("security_events")
        public String getSecurityEvents() {
            return securityEvents;
        }

        @JsonProperty("security_events")
        public void setSecurityEvents(String securityEvents) {
            this.securityEvents = securityEvents;
        }

        @JsonProperty("statuses")
        public String getStatuses() {
            return statuses;
        }

        @JsonProperty("statuses")
        public void setStatuses(String statuses) {
            this.statuses = statuses;
        }

        @JsonProperty("vulnerability_alerts")
        public String getVulnerabilityAlerts() {
            return vulnerabilityAlerts;
        }

        @JsonProperty("vulnerability_alerts")
        public void setVulnerabilityAlerts(String vulnerabilityAlerts) {
            this.vulnerabilityAlerts = vulnerabilityAlerts;
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
            sb.append(CheckRunGitHubEvent.Permissions.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
            sb.append("discussions");
            sb.append('=');
            sb.append(((this.discussions == null)?"<null>":this.discussions));
            sb.append(',');
            sb.append("issues");
            sb.append('=');
            sb.append(((this.issues == null)?"<null>":this.issues));
            sb.append(',');
            sb.append("metadata");
            sb.append('=');
            sb.append(((this.metadata == null)?"<null>":this.metadata));
            sb.append(',');
            sb.append("organizationPackages");
            sb.append('=');
            sb.append(((this.organizationPackages == null)?"<null>":this.organizationPackages));
            sb.append(',');
            sb.append("packages");
            sb.append('=');
            sb.append(((this.packages == null)?"<null>":this.packages));
            sb.append(',');
            sb.append("pages");
            sb.append('=');
            sb.append(((this.pages == null)?"<null>":this.pages));
            sb.append(',');
            sb.append("pullRequests");
            sb.append('=');
            sb.append(((this.pullRequests == null)?"<null>":this.pullRequests));
            sb.append(',');
            sb.append("repositoryHooks");
            sb.append('=');
            sb.append(((this.repositoryHooks == null)?"<null>":this.repositoryHooks));
            sb.append(',');
            sb.append("repositoryProjects");
            sb.append('=');
            sb.append(((this.repositoryProjects == null)?"<null>":this.repositoryProjects));
            sb.append(',');
            sb.append("securityEvents");
            sb.append('=');
            sb.append(((this.securityEvents == null)?"<null>":this.securityEvents));
            sb.append(',');
            sb.append("statuses");
            sb.append('=');
            sb.append(((this.statuses == null)?"<null>":this.statuses));
            sb.append(',');
            sb.append("vulnerabilityAlerts");
            sb.append('=');
            sb.append(((this.vulnerabilityAlerts == null)?"<null>":this.vulnerabilityAlerts));
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
            result = ((result* 31)+((this.securityEvents == null)? 0 :this.securityEvents.hashCode()));
            result = ((result* 31)+((this.metadata == null)? 0 :this.metadata.hashCode()));
            result = ((result* 31)+((this.organizationPackages == null)? 0 :this.organizationPackages.hashCode()));
            result = ((result* 31)+((this.discussions == null)? 0 :this.discussions.hashCode()));
            result = ((result* 31)+((this.pullRequests == null)? 0 :this.pullRequests.hashCode()));
            result = ((result* 31)+((this.packages == null)? 0 :this.packages.hashCode()));
            result = ((result* 31)+((this.issues == null)? 0 :this.issues.hashCode()));
            result = ((result* 31)+((this.deployments == null)? 0 :this.deployments.hashCode()));
            result = ((result* 31)+((this.checks == null)? 0 :this.checks.hashCode()));
            result = ((result* 31)+((this.pages == null)? 0 :this.pages.hashCode()));
            result = ((result* 31)+((this.repositoryProjects == null)? 0 :this.repositoryProjects.hashCode()));
            result = ((result* 31)+((this.contents == null)? 0 :this.contents.hashCode()));
            result = ((result* 31)+((this.statuses == null)? 0 :this.statuses.hashCode()));
            result = ((result* 31)+((this.vulnerabilityAlerts == null)? 0 :this.vulnerabilityAlerts.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.actions == null)? 0 :this.actions.hashCode()));
            result = ((result* 31)+((this.repositoryHooks == null)? 0 :this.repositoryHooks.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof CheckRunGitHubEvent.Permissions) == false) {
                return false;
            }
            CheckRunGitHubEvent.Permissions rhs = ((CheckRunGitHubEvent.Permissions) other);
            return ((((((((((((((((((this.securityEvents == rhs.securityEvents)||((this.securityEvents!= null)&&this.securityEvents.equals(rhs.securityEvents)))&&((this.metadata == rhs.metadata)||((this.metadata!= null)&&this.metadata.equals(rhs.metadata))))&&((this.organizationPackages == rhs.organizationPackages)||((this.organizationPackages!= null)&&this.organizationPackages.equals(rhs.organizationPackages))))&&((this.discussions == rhs.discussions)||((this.discussions!= null)&&this.discussions.equals(rhs.discussions))))&&((this.pullRequests == rhs.pullRequests)||((this.pullRequests!= null)&&this.pullRequests.equals(rhs.pullRequests))))&&((this.packages == rhs.packages)||((this.packages!= null)&&this.packages.equals(rhs.packages))))&&((this.issues == rhs.issues)||((this.issues!= null)&&this.issues.equals(rhs.issues))))&&((this.deployments == rhs.deployments)||((this.deployments!= null)&&this.deployments.equals(rhs.deployments))))&&((this.checks == rhs.checks)||((this.checks!= null)&&this.checks.equals(rhs.checks))))&&((this.pages == rhs.pages)||((this.pages!= null)&&this.pages.equals(rhs.pages))))&&((this.repositoryProjects == rhs.repositoryProjects)||((this.repositoryProjects!= null)&&this.repositoryProjects.equals(rhs.repositoryProjects))))&&((this.contents == rhs.contents)||((this.contents!= null)&&this.contents.equals(rhs.contents))))&&((this.statuses == rhs.statuses)||((this.statuses!= null)&&this.statuses.equals(rhs.statuses))))&&((this.vulnerabilityAlerts == rhs.vulnerabilityAlerts)||((this.vulnerabilityAlerts!= null)&&this.vulnerabilityAlerts.equals(rhs.vulnerabilityAlerts))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.actions == rhs.actions)||((this.actions!= null)&&this.actions.equals(rhs.actions))))&&((this.repositoryHooks == rhs.repositoryHooks)||((this.repositoryHooks!= null)&&this.repositoryHooks.equals(rhs.repositoryHooks))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "actions",
        "checks",
        "contents",
        "deployments",
        "discussions",
        "issues",
        "metadata",
        "organization_packages",
        "packages",
        "pages",
        "pull_requests",
        "repository_hooks",
        "repository_projects",
        "security_events",
        "statuses",
        "vulnerability_alerts"
    })
    @Generated("jsonschema2pojo")
    public static class Permissions2 {

        @JsonProperty("actions")
        private String actions;
        @JsonProperty("checks")
        private String checks;
        @JsonProperty("contents")
        private String contents;
        @JsonProperty("deployments")
        private String deployments;
        @JsonProperty("discussions")
        private String discussions;
        @JsonProperty("issues")
        private String issues;
        @JsonProperty("metadata")
        private String metadata;
        @JsonProperty("organization_packages")
        private String organizationPackages;
        @JsonProperty("packages")
        private String packages;
        @JsonProperty("pages")
        private String pages;
        @JsonProperty("pull_requests")
        private String pullRequests;
        @JsonProperty("repository_hooks")
        private String repositoryHooks;
        @JsonProperty("repository_projects")
        private String repositoryProjects;
        @JsonProperty("security_events")
        private String securityEvents;
        @JsonProperty("statuses")
        private String statuses;
        @JsonProperty("vulnerability_alerts")
        private String vulnerabilityAlerts;
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
         * @param securityEvents
         * @param metadata
         * @param organizationPackages
         * @param discussions
         * @param pullRequests
         * @param packages
         * @param issues
         * @param deployments
         * @param checks
         * @param pages
         * @param repositoryProjects
         * @param contents
         * @param statuses
         * @param vulnerabilityAlerts
         * @param actions
         * @param repositoryHooks
         */
        public Permissions2(String actions, String checks, String contents, String deployments, String discussions, String issues, String metadata, String organizationPackages, String packages, String pages, String pullRequests, String repositoryHooks, String repositoryProjects, String securityEvents, String statuses, String vulnerabilityAlerts) {
            super();
            this.actions = actions;
            this.checks = checks;
            this.contents = contents;
            this.deployments = deployments;
            this.discussions = discussions;
            this.issues = issues;
            this.metadata = metadata;
            this.organizationPackages = organizationPackages;
            this.packages = packages;
            this.pages = pages;
            this.pullRequests = pullRequests;
            this.repositoryHooks = repositoryHooks;
            this.repositoryProjects = repositoryProjects;
            this.securityEvents = securityEvents;
            this.statuses = statuses;
            this.vulnerabilityAlerts = vulnerabilityAlerts;
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

        @JsonProperty("discussions")
        public String getDiscussions() {
            return discussions;
        }

        @JsonProperty("discussions")
        public void setDiscussions(String discussions) {
            this.discussions = discussions;
        }

        @JsonProperty("issues")
        public String getIssues() {
            return issues;
        }

        @JsonProperty("issues")
        public void setIssues(String issues) {
            this.issues = issues;
        }

        @JsonProperty("metadata")
        public String getMetadata() {
            return metadata;
        }

        @JsonProperty("metadata")
        public void setMetadata(String metadata) {
            this.metadata = metadata;
        }

        @JsonProperty("organization_packages")
        public String getOrganizationPackages() {
            return organizationPackages;
        }

        @JsonProperty("organization_packages")
        public void setOrganizationPackages(String organizationPackages) {
            this.organizationPackages = organizationPackages;
        }

        @JsonProperty("packages")
        public String getPackages() {
            return packages;
        }

        @JsonProperty("packages")
        public void setPackages(String packages) {
            this.packages = packages;
        }

        @JsonProperty("pages")
        public String getPages() {
            return pages;
        }

        @JsonProperty("pages")
        public void setPages(String pages) {
            this.pages = pages;
        }

        @JsonProperty("pull_requests")
        public String getPullRequests() {
            return pullRequests;
        }

        @JsonProperty("pull_requests")
        public void setPullRequests(String pullRequests) {
            this.pullRequests = pullRequests;
        }

        @JsonProperty("repository_hooks")
        public String getRepositoryHooks() {
            return repositoryHooks;
        }

        @JsonProperty("repository_hooks")
        public void setRepositoryHooks(String repositoryHooks) {
            this.repositoryHooks = repositoryHooks;
        }

        @JsonProperty("repository_projects")
        public String getRepositoryProjects() {
            return repositoryProjects;
        }

        @JsonProperty("repository_projects")
        public void setRepositoryProjects(String repositoryProjects) {
            this.repositoryProjects = repositoryProjects;
        }

        @JsonProperty("security_events")
        public String getSecurityEvents() {
            return securityEvents;
        }

        @JsonProperty("security_events")
        public void setSecurityEvents(String securityEvents) {
            this.securityEvents = securityEvents;
        }

        @JsonProperty("statuses")
        public String getStatuses() {
            return statuses;
        }

        @JsonProperty("statuses")
        public void setStatuses(String statuses) {
            this.statuses = statuses;
        }

        @JsonProperty("vulnerability_alerts")
        public String getVulnerabilityAlerts() {
            return vulnerabilityAlerts;
        }

        @JsonProperty("vulnerability_alerts")
        public void setVulnerabilityAlerts(String vulnerabilityAlerts) {
            this.vulnerabilityAlerts = vulnerabilityAlerts;
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
            sb.append(CheckRunGitHubEvent.Permissions2 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
            sb.append("discussions");
            sb.append('=');
            sb.append(((this.discussions == null)?"<null>":this.discussions));
            sb.append(',');
            sb.append("issues");
            sb.append('=');
            sb.append(((this.issues == null)?"<null>":this.issues));
            sb.append(',');
            sb.append("metadata");
            sb.append('=');
            sb.append(((this.metadata == null)?"<null>":this.metadata));
            sb.append(',');
            sb.append("organizationPackages");
            sb.append('=');
            sb.append(((this.organizationPackages == null)?"<null>":this.organizationPackages));
            sb.append(',');
            sb.append("packages");
            sb.append('=');
            sb.append(((this.packages == null)?"<null>":this.packages));
            sb.append(',');
            sb.append("pages");
            sb.append('=');
            sb.append(((this.pages == null)?"<null>":this.pages));
            sb.append(',');
            sb.append("pullRequests");
            sb.append('=');
            sb.append(((this.pullRequests == null)?"<null>":this.pullRequests));
            sb.append(',');
            sb.append("repositoryHooks");
            sb.append('=');
            sb.append(((this.repositoryHooks == null)?"<null>":this.repositoryHooks));
            sb.append(',');
            sb.append("repositoryProjects");
            sb.append('=');
            sb.append(((this.repositoryProjects == null)?"<null>":this.repositoryProjects));
            sb.append(',');
            sb.append("securityEvents");
            sb.append('=');
            sb.append(((this.securityEvents == null)?"<null>":this.securityEvents));
            sb.append(',');
            sb.append("statuses");
            sb.append('=');
            sb.append(((this.statuses == null)?"<null>":this.statuses));
            sb.append(',');
            sb.append("vulnerabilityAlerts");
            sb.append('=');
            sb.append(((this.vulnerabilityAlerts == null)?"<null>":this.vulnerabilityAlerts));
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
            result = ((result* 31)+((this.securityEvents == null)? 0 :this.securityEvents.hashCode()));
            result = ((result* 31)+((this.metadata == null)? 0 :this.metadata.hashCode()));
            result = ((result* 31)+((this.organizationPackages == null)? 0 :this.organizationPackages.hashCode()));
            result = ((result* 31)+((this.discussions == null)? 0 :this.discussions.hashCode()));
            result = ((result* 31)+((this.pullRequests == null)? 0 :this.pullRequests.hashCode()));
            result = ((result* 31)+((this.packages == null)? 0 :this.packages.hashCode()));
            result = ((result* 31)+((this.issues == null)? 0 :this.issues.hashCode()));
            result = ((result* 31)+((this.deployments == null)? 0 :this.deployments.hashCode()));
            result = ((result* 31)+((this.checks == null)? 0 :this.checks.hashCode()));
            result = ((result* 31)+((this.pages == null)? 0 :this.pages.hashCode()));
            result = ((result* 31)+((this.repositoryProjects == null)? 0 :this.repositoryProjects.hashCode()));
            result = ((result* 31)+((this.contents == null)? 0 :this.contents.hashCode()));
            result = ((result* 31)+((this.statuses == null)? 0 :this.statuses.hashCode()));
            result = ((result* 31)+((this.vulnerabilityAlerts == null)? 0 :this.vulnerabilityAlerts.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.actions == null)? 0 :this.actions.hashCode()));
            result = ((result* 31)+((this.repositoryHooks == null)? 0 :this.repositoryHooks.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof CheckRunGitHubEvent.Permissions2) == false) {
                return false;
            }
            CheckRunGitHubEvent.Permissions2 rhs = ((CheckRunGitHubEvent.Permissions2) other);
            return ((((((((((((((((((this.securityEvents == rhs.securityEvents)||((this.securityEvents!= null)&&this.securityEvents.equals(rhs.securityEvents)))&&((this.metadata == rhs.metadata)||((this.metadata!= null)&&this.metadata.equals(rhs.metadata))))&&((this.organizationPackages == rhs.organizationPackages)||((this.organizationPackages!= null)&&this.organizationPackages.equals(rhs.organizationPackages))))&&((this.discussions == rhs.discussions)||((this.discussions!= null)&&this.discussions.equals(rhs.discussions))))&&((this.pullRequests == rhs.pullRequests)||((this.pullRequests!= null)&&this.pullRequests.equals(rhs.pullRequests))))&&((this.packages == rhs.packages)||((this.packages!= null)&&this.packages.equals(rhs.packages))))&&((this.issues == rhs.issues)||((this.issues!= null)&&this.issues.equals(rhs.issues))))&&((this.deployments == rhs.deployments)||((this.deployments!= null)&&this.deployments.equals(rhs.deployments))))&&((this.checks == rhs.checks)||((this.checks!= null)&&this.checks.equals(rhs.checks))))&&((this.pages == rhs.pages)||((this.pages!= null)&&this.pages.equals(rhs.pages))))&&((this.repositoryProjects == rhs.repositoryProjects)||((this.repositoryProjects!= null)&&this.repositoryProjects.equals(rhs.repositoryProjects))))&&((this.contents == rhs.contents)||((this.contents!= null)&&this.contents.equals(rhs.contents))))&&((this.statuses == rhs.statuses)||((this.statuses!= null)&&this.statuses.equals(rhs.statuses))))&&((this.vulnerabilityAlerts == rhs.vulnerabilityAlerts)||((this.vulnerabilityAlerts!= null)&&this.vulnerabilityAlerts.equals(rhs.vulnerabilityAlerts))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.actions == rhs.actions)||((this.actions!= null)&&this.actions.equals(rhs.actions))))&&((this.repositoryHooks == rhs.repositoryHooks)||((this.repositoryHooks!= null)&&this.repositoryHooks.equals(rhs.repositoryHooks))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "id",
        "node_id",
        "name",
        "full_name",
        "private",
        "owner",
        "html_url",
        "description",
        "fork",
        "url",
        "forks_url",
        "keys_url",
        "collaborators_url",
        "teams_url",
        "hooks_url",
        "issue_events_url",
        "events_url",
        "assignees_url",
        "branches_url",
        "tags_url",
        "blobs_url",
        "git_tags_url",
        "git_refs_url",
        "trees_url",
        "statuses_url",
        "languages_url",
        "stargazers_url",
        "contributors_url",
        "subscribers_url",
        "subscription_url",
        "commits_url",
        "git_commits_url",
        "comments_url",
        "issue_comment_url",
        "contents_url",
        "compare_url",
        "merges_url",
        "archive_url",
        "downloads_url",
        "issues_url",
        "pulls_url",
        "milestones_url",
        "notifications_url",
        "labels_url",
        "releases_url",
        "deployments_url",
        "created_at",
        "updated_at",
        "pushed_at",
        "git_url",
        "ssh_url",
        "clone_url",
        "svn_url",
        "homepage",
        "size",
        "stargazers_count",
        "watchers_count",
        "language",
        "has_issues",
        "has_projects",
        "has_downloads",
        "has_wiki",
        "has_pages",
        "forks_count",
        "mirror_url",
        "archived",
        "disabled",
        "open_issues_count",
        "license",
        "forks",
        "open_issues",
        "watchers",
        "default_branch"
    })
    @Generated("jsonschema2pojo")
    public static class Repository {

        @JsonProperty("id")
        private Long id;
        @JsonProperty("node_id")
        private String nodeId;
        @JsonProperty("name")
        private String name;
        @JsonProperty("full_name")
        private String fullName;
        @JsonProperty("private")
        private Boolean _private;
        @JsonProperty("owner")
        private CheckRunGitHubEvent.Owner3 owner;
        @JsonProperty("html_url")
        private String htmlUrl;
        @JsonProperty("description")
        private Object description;
        @JsonProperty("fork")
        private Boolean fork;
        @JsonProperty("url")
        private String url;
        @JsonProperty("forks_url")
        private String forksUrl;
        @JsonProperty("keys_url")
        private String keysUrl;
        @JsonProperty("collaborators_url")
        private String collaboratorsUrl;
        @JsonProperty("teams_url")
        private String teamsUrl;
        @JsonProperty("hooks_url")
        private String hooksUrl;
        @JsonProperty("issue_events_url")
        private String issueEventsUrl;
        @JsonProperty("events_url")
        private String eventsUrl;
        @JsonProperty("assignees_url")
        private String assigneesUrl;
        @JsonProperty("branches_url")
        private String branchesUrl;
        @JsonProperty("tags_url")
        private String tagsUrl;
        @JsonProperty("blobs_url")
        private String blobsUrl;
        @JsonProperty("git_tags_url")
        private String gitTagsUrl;
        @JsonProperty("git_refs_url")
        private String gitRefsUrl;
        @JsonProperty("trees_url")
        private String treesUrl;
        @JsonProperty("statuses_url")
        private String statusesUrl;
        @JsonProperty("languages_url")
        private String languagesUrl;
        @JsonProperty("stargazers_url")
        private String stargazersUrl;
        @JsonProperty("contributors_url")
        private String contributorsUrl;
        @JsonProperty("subscribers_url")
        private String subscribersUrl;
        @JsonProperty("subscription_url")
        private String subscriptionUrl;
        @JsonProperty("commits_url")
        private String commitsUrl;
        @JsonProperty("git_commits_url")
        private String gitCommitsUrl;
        @JsonProperty("comments_url")
        private String commentsUrl;
        @JsonProperty("issue_comment_url")
        private String issueCommentUrl;
        @JsonProperty("contents_url")
        private String contentsUrl;
        @JsonProperty("compare_url")
        private String compareUrl;
        @JsonProperty("merges_url")
        private String mergesUrl;
        @JsonProperty("archive_url")
        private String archiveUrl;
        @JsonProperty("downloads_url")
        private String downloadsUrl;
        @JsonProperty("issues_url")
        private String issuesUrl;
        @JsonProperty("pulls_url")
        private String pullsUrl;
        @JsonProperty("milestones_url")
        private String milestonesUrl;
        @JsonProperty("notifications_url")
        private String notificationsUrl;
        @JsonProperty("labels_url")
        private String labelsUrl;
        @JsonProperty("releases_url")
        private String releasesUrl;
        @JsonProperty("deployments_url")
        private String deploymentsUrl;
        @JsonProperty("created_at")
        private String createdAt;
        @JsonProperty("updated_at")
        private String updatedAt;
        @JsonProperty("pushed_at")
        private String pushedAt;
        @JsonProperty("git_url")
        private String gitUrl;
        @JsonProperty("ssh_url")
        private String sshUrl;
        @JsonProperty("clone_url")
        private String cloneUrl;
        @JsonProperty("svn_url")
        private String svnUrl;
        @JsonProperty("homepage")
        private Object homepage;
        @JsonProperty("size")
        private Long size;
        @JsonProperty("stargazers_count")
        private Long stargazersCount;
        @JsonProperty("watchers_count")
        private Long watchersCount;
        @JsonProperty("language")
        private String language;
        @JsonProperty("has_issues")
        private Boolean hasIssues;
        @JsonProperty("has_projects")
        private Boolean hasProjects;
        @JsonProperty("has_downloads")
        private Boolean hasDownloads;
        @JsonProperty("has_wiki")
        private Boolean hasWiki;
        @JsonProperty("has_pages")
        private Boolean hasPages;
        @JsonProperty("forks_count")
        private Long forksCount;
        @JsonProperty("mirror_url")
        private Object mirrorUrl;
        @JsonProperty("archived")
        private Boolean archived;
        @JsonProperty("disabled")
        private Boolean disabled;
        @JsonProperty("open_issues_count")
        private Long openIssuesCount;
        @JsonProperty("license")
        private CheckRunGitHubEvent.License license;
        @JsonProperty("forks")
        private Long forks;
        @JsonProperty("open_issues")
        private Long openIssues;
        @JsonProperty("watchers")
        private Long watchers;
        @JsonProperty("default_branch")
        private String defaultBranch;
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
         * @param issueCommentUrl
         * @param statusesUrl
         * @param id
         * @param collaboratorsUrl
         * @param updatedAt
         * @param forks
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
         * @param htmlUrl
         * @param stargazersUrl
         * @param fullName
         * @param svnUrl
         * @param url
         * @param pullsUrl
         * @param mirrorUrl
         * @param hasDownloads
         * @param fork
         * @param hasProjects
         * @param deploymentsUrl
         * @param eventsUrl
         * @param gitTagsUrl
         * @param notificationsUrl
         * @param gitUrl
         * @param subscriptionUrl
         * @param homepage
         */
        public Repository(Long id, String nodeId, String name, String fullName, Boolean _private, CheckRunGitHubEvent.Owner3 owner, String htmlUrl, Object description, Boolean fork, String url, String forksUrl, String keysUrl, String collaboratorsUrl, String teamsUrl, String hooksUrl, String issueEventsUrl, String eventsUrl, String assigneesUrl, String branchesUrl, String tagsUrl, String blobsUrl, String gitTagsUrl, String gitRefsUrl, String treesUrl, String statusesUrl, String languagesUrl, String stargazersUrl, String contributorsUrl, String subscribersUrl, String subscriptionUrl, String commitsUrl, String gitCommitsUrl, String commentsUrl, String issueCommentUrl, String contentsUrl, String compareUrl, String mergesUrl, String archiveUrl, String downloadsUrl, String issuesUrl, String pullsUrl, String milestonesUrl, String notificationsUrl, String labelsUrl, String releasesUrl, String deploymentsUrl, String createdAt, String updatedAt, String pushedAt, String gitUrl, String sshUrl, String cloneUrl, String svnUrl, Object homepage, Long size, Long stargazersCount, Long watchersCount, String language, Boolean hasIssues, Boolean hasProjects, Boolean hasDownloads, Boolean hasWiki, Boolean hasPages, Long forksCount, Object mirrorUrl, Boolean archived, Boolean disabled, Long openIssuesCount, CheckRunGitHubEvent.License license, Long forks, Long openIssues, Long watchers, String defaultBranch) {
            super();
            this.id = id;
            this.nodeId = nodeId;
            this.name = name;
            this.fullName = fullName;
            this._private = _private;
            this.owner = owner;
            this.htmlUrl = htmlUrl;
            this.description = description;
            this.fork = fork;
            this.url = url;
            this.forksUrl = forksUrl;
            this.keysUrl = keysUrl;
            this.collaboratorsUrl = collaboratorsUrl;
            this.teamsUrl = teamsUrl;
            this.hooksUrl = hooksUrl;
            this.issueEventsUrl = issueEventsUrl;
            this.eventsUrl = eventsUrl;
            this.assigneesUrl = assigneesUrl;
            this.branchesUrl = branchesUrl;
            this.tagsUrl = tagsUrl;
            this.blobsUrl = blobsUrl;
            this.gitTagsUrl = gitTagsUrl;
            this.gitRefsUrl = gitRefsUrl;
            this.treesUrl = treesUrl;
            this.statusesUrl = statusesUrl;
            this.languagesUrl = languagesUrl;
            this.stargazersUrl = stargazersUrl;
            this.contributorsUrl = contributorsUrl;
            this.subscribersUrl = subscribersUrl;
            this.subscriptionUrl = subscriptionUrl;
            this.commitsUrl = commitsUrl;
            this.gitCommitsUrl = gitCommitsUrl;
            this.commentsUrl = commentsUrl;
            this.issueCommentUrl = issueCommentUrl;
            this.contentsUrl = contentsUrl;
            this.compareUrl = compareUrl;
            this.mergesUrl = mergesUrl;
            this.archiveUrl = archiveUrl;
            this.downloadsUrl = downloadsUrl;
            this.issuesUrl = issuesUrl;
            this.pullsUrl = pullsUrl;
            this.milestonesUrl = milestonesUrl;
            this.notificationsUrl = notificationsUrl;
            this.labelsUrl = labelsUrl;
            this.releasesUrl = releasesUrl;
            this.deploymentsUrl = deploymentsUrl;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
            this.pushedAt = pushedAt;
            this.gitUrl = gitUrl;
            this.sshUrl = sshUrl;
            this.cloneUrl = cloneUrl;
            this.svnUrl = svnUrl;
            this.homepage = homepage;
            this.size = size;
            this.stargazersCount = stargazersCount;
            this.watchersCount = watchersCount;
            this.language = language;
            this.hasIssues = hasIssues;
            this.hasProjects = hasProjects;
            this.hasDownloads = hasDownloads;
            this.hasWiki = hasWiki;
            this.hasPages = hasPages;
            this.forksCount = forksCount;
            this.mirrorUrl = mirrorUrl;
            this.archived = archived;
            this.disabled = disabled;
            this.openIssuesCount = openIssuesCount;
            this.license = license;
            this.forks = forks;
            this.openIssues = openIssues;
            this.watchers = watchers;
            this.defaultBranch = defaultBranch;
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

        @JsonProperty("private")
        public Boolean getPrivate() {
            return _private;
        }

        @JsonProperty("private")
        public void setPrivate(Boolean _private) {
            this._private = _private;
        }

        @JsonProperty("owner")
        public CheckRunGitHubEvent.Owner3 getOwner() {
            return owner;
        }

        @JsonProperty("owner")
        public void setOwner(CheckRunGitHubEvent.Owner3 owner) {
            this.owner = owner;
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
        public Object getDescription() {
            return description;
        }

        @JsonProperty("description")
        public void setDescription(Object description) {
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

        @JsonProperty("forks_url")
        public String getForksUrl() {
            return forksUrl;
        }

        @JsonProperty("forks_url")
        public void setForksUrl(String forksUrl) {
            this.forksUrl = forksUrl;
        }

        @JsonProperty("keys_url")
        public String getKeysUrl() {
            return keysUrl;
        }

        @JsonProperty("keys_url")
        public void setKeysUrl(String keysUrl) {
            this.keysUrl = keysUrl;
        }

        @JsonProperty("collaborators_url")
        public String getCollaboratorsUrl() {
            return collaboratorsUrl;
        }

        @JsonProperty("collaborators_url")
        public void setCollaboratorsUrl(String collaboratorsUrl) {
            this.collaboratorsUrl = collaboratorsUrl;
        }

        @JsonProperty("teams_url")
        public String getTeamsUrl() {
            return teamsUrl;
        }

        @JsonProperty("teams_url")
        public void setTeamsUrl(String teamsUrl) {
            this.teamsUrl = teamsUrl;
        }

        @JsonProperty("hooks_url")
        public String getHooksUrl() {
            return hooksUrl;
        }

        @JsonProperty("hooks_url")
        public void setHooksUrl(String hooksUrl) {
            this.hooksUrl = hooksUrl;
        }

        @JsonProperty("issue_events_url")
        public String getIssueEventsUrl() {
            return issueEventsUrl;
        }

        @JsonProperty("issue_events_url")
        public void setIssueEventsUrl(String issueEventsUrl) {
            this.issueEventsUrl = issueEventsUrl;
        }

        @JsonProperty("events_url")
        public String getEventsUrl() {
            return eventsUrl;
        }

        @JsonProperty("events_url")
        public void setEventsUrl(String eventsUrl) {
            this.eventsUrl = eventsUrl;
        }

        @JsonProperty("assignees_url")
        public String getAssigneesUrl() {
            return assigneesUrl;
        }

        @JsonProperty("assignees_url")
        public void setAssigneesUrl(String assigneesUrl) {
            this.assigneesUrl = assigneesUrl;
        }

        @JsonProperty("branches_url")
        public String getBranchesUrl() {
            return branchesUrl;
        }

        @JsonProperty("branches_url")
        public void setBranchesUrl(String branchesUrl) {
            this.branchesUrl = branchesUrl;
        }

        @JsonProperty("tags_url")
        public String getTagsUrl() {
            return tagsUrl;
        }

        @JsonProperty("tags_url")
        public void setTagsUrl(String tagsUrl) {
            this.tagsUrl = tagsUrl;
        }

        @JsonProperty("blobs_url")
        public String getBlobsUrl() {
            return blobsUrl;
        }

        @JsonProperty("blobs_url")
        public void setBlobsUrl(String blobsUrl) {
            this.blobsUrl = blobsUrl;
        }

        @JsonProperty("git_tags_url")
        public String getGitTagsUrl() {
            return gitTagsUrl;
        }

        @JsonProperty("git_tags_url")
        public void setGitTagsUrl(String gitTagsUrl) {
            this.gitTagsUrl = gitTagsUrl;
        }

        @JsonProperty("git_refs_url")
        public String getGitRefsUrl() {
            return gitRefsUrl;
        }

        @JsonProperty("git_refs_url")
        public void setGitRefsUrl(String gitRefsUrl) {
            this.gitRefsUrl = gitRefsUrl;
        }

        @JsonProperty("trees_url")
        public String getTreesUrl() {
            return treesUrl;
        }

        @JsonProperty("trees_url")
        public void setTreesUrl(String treesUrl) {
            this.treesUrl = treesUrl;
        }

        @JsonProperty("statuses_url")
        public String getStatusesUrl() {
            return statusesUrl;
        }

        @JsonProperty("statuses_url")
        public void setStatusesUrl(String statusesUrl) {
            this.statusesUrl = statusesUrl;
        }

        @JsonProperty("languages_url")
        public String getLanguagesUrl() {
            return languagesUrl;
        }

        @JsonProperty("languages_url")
        public void setLanguagesUrl(String languagesUrl) {
            this.languagesUrl = languagesUrl;
        }

        @JsonProperty("stargazers_url")
        public String getStargazersUrl() {
            return stargazersUrl;
        }

        @JsonProperty("stargazers_url")
        public void setStargazersUrl(String stargazersUrl) {
            this.stargazersUrl = stargazersUrl;
        }

        @JsonProperty("contributors_url")
        public String getContributorsUrl() {
            return contributorsUrl;
        }

        @JsonProperty("contributors_url")
        public void setContributorsUrl(String contributorsUrl) {
            this.contributorsUrl = contributorsUrl;
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

        @JsonProperty("commits_url")
        public String getCommitsUrl() {
            return commitsUrl;
        }

        @JsonProperty("commits_url")
        public void setCommitsUrl(String commitsUrl) {
            this.commitsUrl = commitsUrl;
        }

        @JsonProperty("git_commits_url")
        public String getGitCommitsUrl() {
            return gitCommitsUrl;
        }

        @JsonProperty("git_commits_url")
        public void setGitCommitsUrl(String gitCommitsUrl) {
            this.gitCommitsUrl = gitCommitsUrl;
        }

        @JsonProperty("comments_url")
        public String getCommentsUrl() {
            return commentsUrl;
        }

        @JsonProperty("comments_url")
        public void setCommentsUrl(String commentsUrl) {
            this.commentsUrl = commentsUrl;
        }

        @JsonProperty("issue_comment_url")
        public String getIssueCommentUrl() {
            return issueCommentUrl;
        }

        @JsonProperty("issue_comment_url")
        public void setIssueCommentUrl(String issueCommentUrl) {
            this.issueCommentUrl = issueCommentUrl;
        }

        @JsonProperty("contents_url")
        public String getContentsUrl() {
            return contentsUrl;
        }

        @JsonProperty("contents_url")
        public void setContentsUrl(String contentsUrl) {
            this.contentsUrl = contentsUrl;
        }

        @JsonProperty("compare_url")
        public String getCompareUrl() {
            return compareUrl;
        }

        @JsonProperty("compare_url")
        public void setCompareUrl(String compareUrl) {
            this.compareUrl = compareUrl;
        }

        @JsonProperty("merges_url")
        public String getMergesUrl() {
            return mergesUrl;
        }

        @JsonProperty("merges_url")
        public void setMergesUrl(String mergesUrl) {
            this.mergesUrl = mergesUrl;
        }

        @JsonProperty("archive_url")
        public String getArchiveUrl() {
            return archiveUrl;
        }

        @JsonProperty("archive_url")
        public void setArchiveUrl(String archiveUrl) {
            this.archiveUrl = archiveUrl;
        }

        @JsonProperty("downloads_url")
        public String getDownloadsUrl() {
            return downloadsUrl;
        }

        @JsonProperty("downloads_url")
        public void setDownloadsUrl(String downloadsUrl) {
            this.downloadsUrl = downloadsUrl;
        }

        @JsonProperty("issues_url")
        public String getIssuesUrl() {
            return issuesUrl;
        }

        @JsonProperty("issues_url")
        public void setIssuesUrl(String issuesUrl) {
            this.issuesUrl = issuesUrl;
        }

        @JsonProperty("pulls_url")
        public String getPullsUrl() {
            return pullsUrl;
        }

        @JsonProperty("pulls_url")
        public void setPullsUrl(String pullsUrl) {
            this.pullsUrl = pullsUrl;
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

        @JsonProperty("labels_url")
        public String getLabelsUrl() {
            return labelsUrl;
        }

        @JsonProperty("labels_url")
        public void setLabelsUrl(String labelsUrl) {
            this.labelsUrl = labelsUrl;
        }

        @JsonProperty("releases_url")
        public String getReleasesUrl() {
            return releasesUrl;
        }

        @JsonProperty("releases_url")
        public void setReleasesUrl(String releasesUrl) {
            this.releasesUrl = releasesUrl;
        }

        @JsonProperty("deployments_url")
        public String getDeploymentsUrl() {
            return deploymentsUrl;
        }

        @JsonProperty("deployments_url")
        public void setDeploymentsUrl(String deploymentsUrl) {
            this.deploymentsUrl = deploymentsUrl;
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

        @JsonProperty("pushed_at")
        public String getPushedAt() {
            return pushedAt;
        }

        @JsonProperty("pushed_at")
        public void setPushedAt(String pushedAt) {
            this.pushedAt = pushedAt;
        }

        @JsonProperty("git_url")
        public String getGitUrl() {
            return gitUrl;
        }

        @JsonProperty("git_url")
        public void setGitUrl(String gitUrl) {
            this.gitUrl = gitUrl;
        }

        @JsonProperty("ssh_url")
        public String getSshUrl() {
            return sshUrl;
        }

        @JsonProperty("ssh_url")
        public void setSshUrl(String sshUrl) {
            this.sshUrl = sshUrl;
        }

        @JsonProperty("clone_url")
        public String getCloneUrl() {
            return cloneUrl;
        }

        @JsonProperty("clone_url")
        public void setCloneUrl(String cloneUrl) {
            this.cloneUrl = cloneUrl;
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
        public Object getHomepage() {
            return homepage;
        }

        @JsonProperty("homepage")
        public void setHomepage(Object homepage) {
            this.homepage = homepage;
        }

        @JsonProperty("size")
        public Long getSize() {
            return size;
        }

        @JsonProperty("size")
        public void setSize(Long size) {
            this.size = size;
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

        @JsonProperty("language")
        public String getLanguage() {
            return language;
        }

        @JsonProperty("language")
        public void setLanguage(String language) {
            this.language = language;
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

        @JsonProperty("has_downloads")
        public Boolean getHasDownloads() {
            return hasDownloads;
        }

        @JsonProperty("has_downloads")
        public void setHasDownloads(Boolean hasDownloads) {
            this.hasDownloads = hasDownloads;
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

        @JsonProperty("forks_count")
        public Long getForksCount() {
            return forksCount;
        }

        @JsonProperty("forks_count")
        public void setForksCount(Long forksCount) {
            this.forksCount = forksCount;
        }

        @JsonProperty("mirror_url")
        public Object getMirrorUrl() {
            return mirrorUrl;
        }

        @JsonProperty("mirror_url")
        public void setMirrorUrl(Object mirrorUrl) {
            this.mirrorUrl = mirrorUrl;
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

        @JsonProperty("open_issues_count")
        public Long getOpenIssuesCount() {
            return openIssuesCount;
        }

        @JsonProperty("open_issues_count")
        public void setOpenIssuesCount(Long openIssuesCount) {
            this.openIssuesCount = openIssuesCount;
        }

        @JsonProperty("license")
        public CheckRunGitHubEvent.License getLicense() {
            return license;
        }

        @JsonProperty("license")
        public void setLicense(CheckRunGitHubEvent.License license) {
            this.license = license;
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

        @JsonProperty("watchers")
        public Long getWatchers() {
            return watchers;
        }

        @JsonProperty("watchers")
        public void setWatchers(Long watchers) {
            this.watchers = watchers;
        }

        @JsonProperty("default_branch")
        public String getDefaultBranch() {
            return defaultBranch;
        }

        @JsonProperty("default_branch")
        public void setDefaultBranch(String defaultBranch) {
            this.defaultBranch = defaultBranch;
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
            sb.append(CheckRunGitHubEvent.Repository.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
            sb.append("_private");
            sb.append('=');
            sb.append(((this._private == null)?"<null>":this._private));
            sb.append(',');
            sb.append("owner");
            sb.append('=');
            sb.append(((this.owner == null)?"<null>":this.owner));
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
            sb.append("forksUrl");
            sb.append('=');
            sb.append(((this.forksUrl == null)?"<null>":this.forksUrl));
            sb.append(',');
            sb.append("keysUrl");
            sb.append('=');
            sb.append(((this.keysUrl == null)?"<null>":this.keysUrl));
            sb.append(',');
            sb.append("collaboratorsUrl");
            sb.append('=');
            sb.append(((this.collaboratorsUrl == null)?"<null>":this.collaboratorsUrl));
            sb.append(',');
            sb.append("teamsUrl");
            sb.append('=');
            sb.append(((this.teamsUrl == null)?"<null>":this.teamsUrl));
            sb.append(',');
            sb.append("hooksUrl");
            sb.append('=');
            sb.append(((this.hooksUrl == null)?"<null>":this.hooksUrl));
            sb.append(',');
            sb.append("issueEventsUrl");
            sb.append('=');
            sb.append(((this.issueEventsUrl == null)?"<null>":this.issueEventsUrl));
            sb.append(',');
            sb.append("eventsUrl");
            sb.append('=');
            sb.append(((this.eventsUrl == null)?"<null>":this.eventsUrl));
            sb.append(',');
            sb.append("assigneesUrl");
            sb.append('=');
            sb.append(((this.assigneesUrl == null)?"<null>":this.assigneesUrl));
            sb.append(',');
            sb.append("branchesUrl");
            sb.append('=');
            sb.append(((this.branchesUrl == null)?"<null>":this.branchesUrl));
            sb.append(',');
            sb.append("tagsUrl");
            sb.append('=');
            sb.append(((this.tagsUrl == null)?"<null>":this.tagsUrl));
            sb.append(',');
            sb.append("blobsUrl");
            sb.append('=');
            sb.append(((this.blobsUrl == null)?"<null>":this.blobsUrl));
            sb.append(',');
            sb.append("gitTagsUrl");
            sb.append('=');
            sb.append(((this.gitTagsUrl == null)?"<null>":this.gitTagsUrl));
            sb.append(',');
            sb.append("gitRefsUrl");
            sb.append('=');
            sb.append(((this.gitRefsUrl == null)?"<null>":this.gitRefsUrl));
            sb.append(',');
            sb.append("treesUrl");
            sb.append('=');
            sb.append(((this.treesUrl == null)?"<null>":this.treesUrl));
            sb.append(',');
            sb.append("statusesUrl");
            sb.append('=');
            sb.append(((this.statusesUrl == null)?"<null>":this.statusesUrl));
            sb.append(',');
            sb.append("languagesUrl");
            sb.append('=');
            sb.append(((this.languagesUrl == null)?"<null>":this.languagesUrl));
            sb.append(',');
            sb.append("stargazersUrl");
            sb.append('=');
            sb.append(((this.stargazersUrl == null)?"<null>":this.stargazersUrl));
            sb.append(',');
            sb.append("contributorsUrl");
            sb.append('=');
            sb.append(((this.contributorsUrl == null)?"<null>":this.contributorsUrl));
            sb.append(',');
            sb.append("subscribersUrl");
            sb.append('=');
            sb.append(((this.subscribersUrl == null)?"<null>":this.subscribersUrl));
            sb.append(',');
            sb.append("subscriptionUrl");
            sb.append('=');
            sb.append(((this.subscriptionUrl == null)?"<null>":this.subscriptionUrl));
            sb.append(',');
            sb.append("commitsUrl");
            sb.append('=');
            sb.append(((this.commitsUrl == null)?"<null>":this.commitsUrl));
            sb.append(',');
            sb.append("gitCommitsUrl");
            sb.append('=');
            sb.append(((this.gitCommitsUrl == null)?"<null>":this.gitCommitsUrl));
            sb.append(',');
            sb.append("commentsUrl");
            sb.append('=');
            sb.append(((this.commentsUrl == null)?"<null>":this.commentsUrl));
            sb.append(',');
            sb.append("issueCommentUrl");
            sb.append('=');
            sb.append(((this.issueCommentUrl == null)?"<null>":this.issueCommentUrl));
            sb.append(',');
            sb.append("contentsUrl");
            sb.append('=');
            sb.append(((this.contentsUrl == null)?"<null>":this.contentsUrl));
            sb.append(',');
            sb.append("compareUrl");
            sb.append('=');
            sb.append(((this.compareUrl == null)?"<null>":this.compareUrl));
            sb.append(',');
            sb.append("mergesUrl");
            sb.append('=');
            sb.append(((this.mergesUrl == null)?"<null>":this.mergesUrl));
            sb.append(',');
            sb.append("archiveUrl");
            sb.append('=');
            sb.append(((this.archiveUrl == null)?"<null>":this.archiveUrl));
            sb.append(',');
            sb.append("downloadsUrl");
            sb.append('=');
            sb.append(((this.downloadsUrl == null)?"<null>":this.downloadsUrl));
            sb.append(',');
            sb.append("issuesUrl");
            sb.append('=');
            sb.append(((this.issuesUrl == null)?"<null>":this.issuesUrl));
            sb.append(',');
            sb.append("pullsUrl");
            sb.append('=');
            sb.append(((this.pullsUrl == null)?"<null>":this.pullsUrl));
            sb.append(',');
            sb.append("milestonesUrl");
            sb.append('=');
            sb.append(((this.milestonesUrl == null)?"<null>":this.milestonesUrl));
            sb.append(',');
            sb.append("notificationsUrl");
            sb.append('=');
            sb.append(((this.notificationsUrl == null)?"<null>":this.notificationsUrl));
            sb.append(',');
            sb.append("labelsUrl");
            sb.append('=');
            sb.append(((this.labelsUrl == null)?"<null>":this.labelsUrl));
            sb.append(',');
            sb.append("releasesUrl");
            sb.append('=');
            sb.append(((this.releasesUrl == null)?"<null>":this.releasesUrl));
            sb.append(',');
            sb.append("deploymentsUrl");
            sb.append('=');
            sb.append(((this.deploymentsUrl == null)?"<null>":this.deploymentsUrl));
            sb.append(',');
            sb.append("createdAt");
            sb.append('=');
            sb.append(((this.createdAt == null)?"<null>":this.createdAt));
            sb.append(',');
            sb.append("updatedAt");
            sb.append('=');
            sb.append(((this.updatedAt == null)?"<null>":this.updatedAt));
            sb.append(',');
            sb.append("pushedAt");
            sb.append('=');
            sb.append(((this.pushedAt == null)?"<null>":this.pushedAt));
            sb.append(',');
            sb.append("gitUrl");
            sb.append('=');
            sb.append(((this.gitUrl == null)?"<null>":this.gitUrl));
            sb.append(',');
            sb.append("sshUrl");
            sb.append('=');
            sb.append(((this.sshUrl == null)?"<null>":this.sshUrl));
            sb.append(',');
            sb.append("cloneUrl");
            sb.append('=');
            sb.append(((this.cloneUrl == null)?"<null>":this.cloneUrl));
            sb.append(',');
            sb.append("svnUrl");
            sb.append('=');
            sb.append(((this.svnUrl == null)?"<null>":this.svnUrl));
            sb.append(',');
            sb.append("homepage");
            sb.append('=');
            sb.append(((this.homepage == null)?"<null>":this.homepage));
            sb.append(',');
            sb.append("size");
            sb.append('=');
            sb.append(((this.size == null)?"<null>":this.size));
            sb.append(',');
            sb.append("stargazersCount");
            sb.append('=');
            sb.append(((this.stargazersCount == null)?"<null>":this.stargazersCount));
            sb.append(',');
            sb.append("watchersCount");
            sb.append('=');
            sb.append(((this.watchersCount == null)?"<null>":this.watchersCount));
            sb.append(',');
            sb.append("language");
            sb.append('=');
            sb.append(((this.language == null)?"<null>":this.language));
            sb.append(',');
            sb.append("hasIssues");
            sb.append('=');
            sb.append(((this.hasIssues == null)?"<null>":this.hasIssues));
            sb.append(',');
            sb.append("hasProjects");
            sb.append('=');
            sb.append(((this.hasProjects == null)?"<null>":this.hasProjects));
            sb.append(',');
            sb.append("hasDownloads");
            sb.append('=');
            sb.append(((this.hasDownloads == null)?"<null>":this.hasDownloads));
            sb.append(',');
            sb.append("hasWiki");
            sb.append('=');
            sb.append(((this.hasWiki == null)?"<null>":this.hasWiki));
            sb.append(',');
            sb.append("hasPages");
            sb.append('=');
            sb.append(((this.hasPages == null)?"<null>":this.hasPages));
            sb.append(',');
            sb.append("forksCount");
            sb.append('=');
            sb.append(((this.forksCount == null)?"<null>":this.forksCount));
            sb.append(',');
            sb.append("mirrorUrl");
            sb.append('=');
            sb.append(((this.mirrorUrl == null)?"<null>":this.mirrorUrl));
            sb.append(',');
            sb.append("archived");
            sb.append('=');
            sb.append(((this.archived == null)?"<null>":this.archived));
            sb.append(',');
            sb.append("disabled");
            sb.append('=');
            sb.append(((this.disabled == null)?"<null>":this.disabled));
            sb.append(',');
            sb.append("openIssuesCount");
            sb.append('=');
            sb.append(((this.openIssuesCount == null)?"<null>":this.openIssuesCount));
            sb.append(',');
            sb.append("license");
            sb.append('=');
            sb.append(((this.license == null)?"<null>":this.license));
            sb.append(',');
            sb.append("forks");
            sb.append('=');
            sb.append(((this.forks == null)?"<null>":this.forks));
            sb.append(',');
            sb.append("openIssues");
            sb.append('=');
            sb.append(((this.openIssues == null)?"<null>":this.openIssues));
            sb.append(',');
            sb.append("watchers");
            sb.append('=');
            sb.append(((this.watchers == null)?"<null>":this.watchers));
            sb.append(',');
            sb.append("defaultBranch");
            sb.append('=');
            sb.append(((this.defaultBranch == null)?"<null>":this.defaultBranch));
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
            result = ((result* 31)+((this.issueCommentUrl == null)? 0 :this.issueCommentUrl.hashCode()));
            result = ((result* 31)+((this.statusesUrl == null)? 0 :this.statusesUrl.hashCode()));
            result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
            result = ((result* 31)+((this.collaboratorsUrl == null)? 0 :this.collaboratorsUrl.hashCode()));
            result = ((result* 31)+((this.updatedAt == null)? 0 :this.updatedAt.hashCode()));
            result = ((result* 31)+((this.forks == null)? 0 :this.forks.hashCode()));
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
            result = ((result* 31)+((this.htmlUrl == null)? 0 :this.htmlUrl.hashCode()));
            result = ((result* 31)+((this.stargazersUrl == null)? 0 :this.stargazersUrl.hashCode()));
            result = ((result* 31)+((this.fullName == null)? 0 :this.fullName.hashCode()));
            result = ((result* 31)+((this.svnUrl == null)? 0 :this.svnUrl.hashCode()));
            result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
            result = ((result* 31)+((this.pullsUrl == null)? 0 :this.pullsUrl.hashCode()));
            result = ((result* 31)+((this.mirrorUrl == null)? 0 :this.mirrorUrl.hashCode()));
            result = ((result* 31)+((this.hasDownloads == null)? 0 :this.hasDownloads.hashCode()));
            result = ((result* 31)+((this.fork == null)? 0 :this.fork.hashCode()));
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
            if ((other instanceof CheckRunGitHubEvent.Repository) == false) {
                return false;
            }
            CheckRunGitHubEvent.Repository rhs = ((CheckRunGitHubEvent.Repository) other);
            return (((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((this.sshUrl == rhs.sshUrl)||((this.sshUrl!= null)&&this.sshUrl.equals(rhs.sshUrl)))&&((this.archiveUrl == rhs.archiveUrl)||((this.archiveUrl!= null)&&this.archiveUrl.equals(rhs.archiveUrl))))&&((this.languagesUrl == rhs.languagesUrl)||((this.languagesUrl!= null)&&this.languagesUrl.equals(rhs.languagesUrl))))&&((this.language == rhs.language)||((this.language!= null)&&this.language.equals(rhs.language))))&&((this.assigneesUrl == rhs.assigneesUrl)||((this.assigneesUrl!= null)&&this.assigneesUrl.equals(rhs.assigneesUrl))))&&((this.commitsUrl == rhs.commitsUrl)||((this.commitsUrl!= null)&&this.commitsUrl.equals(rhs.commitsUrl))))&&((this.openIssues == rhs.openIssues)||((this.openIssues!= null)&&this.openIssues.equals(rhs.openIssues))))&&((this.cloneUrl == rhs.cloneUrl)||((this.cloneUrl!= null)&&this.cloneUrl.equals(rhs.cloneUrl))))&&((this.forksCount == rhs.forksCount)||((this.forksCount!= null)&&this.forksCount.equals(rhs.forksCount))))&&((this.subscribersUrl == rhs.subscribersUrl)||((this.subscribersUrl!= null)&&this.subscribersUrl.equals(rhs.subscribersUrl))))&&((this.createdAt == rhs.createdAt)||((this.createdAt!= null)&&this.createdAt.equals(rhs.createdAt))))&&((this.forksUrl == rhs.forksUrl)||((this.forksUrl!= null)&&this.forksUrl.equals(rhs.forksUrl))))&&((this.watchersCount == rhs.watchersCount)||((this.watchersCount!= null)&&this.watchersCount.equals(rhs.watchersCount))))&&((this._private == rhs._private)||((this._private!= null)&&this._private.equals(rhs._private))))&&((this.issueCommentUrl == rhs.issueCommentUrl)||((this.issueCommentUrl!= null)&&this.issueCommentUrl.equals(rhs.issueCommentUrl))))&&((this.statusesUrl == rhs.statusesUrl)||((this.statusesUrl!= null)&&this.statusesUrl.equals(rhs.statusesUrl))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.collaboratorsUrl == rhs.collaboratorsUrl)||((this.collaboratorsUrl!= null)&&this.collaboratorsUrl.equals(rhs.collaboratorsUrl))))&&((this.updatedAt == rhs.updatedAt)||((this.updatedAt!= null)&&this.updatedAt.equals(rhs.updatedAt))))&&((this.forks == rhs.forks)||((this.forks!= null)&&this.forks.equals(rhs.forks))))&&((this.labelsUrl == rhs.labelsUrl)||((this.labelsUrl!= null)&&this.labelsUrl.equals(rhs.labelsUrl))))&&((this.defaultBranch == rhs.defaultBranch)||((this.defaultBranch!= null)&&this.defaultBranch.equals(rhs.defaultBranch))))&&((this.keysUrl == rhs.keysUrl)||((this.keysUrl!= null)&&this.keysUrl.equals(rhs.keysUrl))))&&((this.downloadsUrl == rhs.downloadsUrl)||((this.downloadsUrl!= null)&&this.downloadsUrl.equals(rhs.downloadsUrl))))&&((this.contentsUrl == rhs.contentsUrl)||((this.contentsUrl!= null)&&this.contentsUrl.equals(rhs.contentsUrl))))&&((this.pushedAt == rhs.pushedAt)||((this.pushedAt!= null)&&this.pushedAt.equals(rhs.pushedAt))))&&((this.tagsUrl == rhs.tagsUrl)||((this.tagsUrl!= null)&&this.tagsUrl.equals(rhs.tagsUrl))))&&((this.license == rhs.license)||((this.license!= null)&&this.license.equals(rhs.license))))&&((this.commentsUrl == rhs.commentsUrl)||((this.commentsUrl!= null)&&this.commentsUrl.equals(rhs.commentsUrl))))&&((this.size == rhs.size)||((this.size!= null)&&this.size.equals(rhs.size))))&&((this.treesUrl == rhs.treesUrl)||((this.treesUrl!= null)&&this.treesUrl.equals(rhs.treesUrl))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.mergesUrl == rhs.mergesUrl)||((this.mergesUrl!= null)&&this.mergesUrl.equals(rhs.mergesUrl))))&&((this.nodeId == rhs.nodeId)||((this.nodeId!= null)&&this.nodeId.equals(rhs.nodeId))))&&((this.teamsUrl == rhs.teamsUrl)||((this.teamsUrl!= null)&&this.teamsUrl.equals(rhs.teamsUrl))))&&((this.blobsUrl == rhs.blobsUrl)||((this.blobsUrl!= null)&&this.blobsUrl.equals(rhs.blobsUrl))))&&((this.issueEventsUrl == rhs.issueEventsUrl)||((this.issueEventsUrl!= null)&&this.issueEventsUrl.equals(rhs.issueEventsUrl))))&&((this.hasPages == rhs.hasPages)||((this.hasPages!= null)&&this.hasPages.equals(rhs.hasPages))))&&((this.milestonesUrl == rhs.milestonesUrl)||((this.milestonesUrl!= null)&&this.milestonesUrl.equals(rhs.milestonesUrl))))&&((this.issuesUrl == rhs.issuesUrl)||((this.issuesUrl!= null)&&this.issuesUrl.equals(rhs.issuesUrl))))&&((this.releasesUrl == rhs.releasesUrl)||((this.releasesUrl!= null)&&this.releasesUrl.equals(rhs.releasesUrl))))&&((this.description == rhs.description)||((this.description!= null)&&this.description.equals(rhs.description))))&&((this.watchers == rhs.watchers)||((this.watchers!= null)&&this.watchers.equals(rhs.watchers))))&&((this.branchesUrl == rhs.branchesUrl)||((this.branchesUrl!= null)&&this.branchesUrl.equals(rhs.branchesUrl))))&&((this.contributorsUrl == rhs.contributorsUrl)||((this.contributorsUrl!= null)&&this.contributorsUrl.equals(rhs.contributorsUrl))))&&((this.gitRefsUrl == rhs.gitRefsUrl)||((this.gitRefsUrl!= null)&&this.gitRefsUrl.equals(rhs.gitRefsUrl))))&&((this.hooksUrl == rhs.hooksUrl)||((this.hooksUrl!= null)&&this.hooksUrl.equals(rhs.hooksUrl))))&&((this.openIssuesCount == rhs.openIssuesCount)||((this.openIssuesCount!= null)&&this.openIssuesCount.equals(rhs.openIssuesCount))))&&((this.archived == rhs.archived)||((this.archived!= null)&&this.archived.equals(rhs.archived))))&&((this.stargazersCount == rhs.stargazersCount)||((this.stargazersCount!= null)&&this.stargazersCount.equals(rhs.stargazersCount))))&&((this.disabled == rhs.disabled)||((this.disabled!= null)&&this.disabled.equals(rhs.disabled))))&&((this.hasIssues == rhs.hasIssues)||((this.hasIssues!= null)&&this.hasIssues.equals(rhs.hasIssues))))&&((this.owner == rhs.owner)||((this.owner!= null)&&this.owner.equals(rhs.owner))))&&((this.hasWiki == rhs.hasWiki)||((this.hasWiki!= null)&&this.hasWiki.equals(rhs.hasWiki))))&&((this.compareUrl == rhs.compareUrl)||((this.compareUrl!= null)&&this.compareUrl.equals(rhs.compareUrl))))&&((this.gitCommitsUrl == rhs.gitCommitsUrl)||((this.gitCommitsUrl!= null)&&this.gitCommitsUrl.equals(rhs.gitCommitsUrl))))&&((this.htmlUrl == rhs.htmlUrl)||((this.htmlUrl!= null)&&this.htmlUrl.equals(rhs.htmlUrl))))&&((this.stargazersUrl == rhs.stargazersUrl)||((this.stargazersUrl!= null)&&this.stargazersUrl.equals(rhs.stargazersUrl))))&&((this.fullName == rhs.fullName)||((this.fullName!= null)&&this.fullName.equals(rhs.fullName))))&&((this.svnUrl == rhs.svnUrl)||((this.svnUrl!= null)&&this.svnUrl.equals(rhs.svnUrl))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))))&&((this.pullsUrl == rhs.pullsUrl)||((this.pullsUrl!= null)&&this.pullsUrl.equals(rhs.pullsUrl))))&&((this.mirrorUrl == rhs.mirrorUrl)||((this.mirrorUrl!= null)&&this.mirrorUrl.equals(rhs.mirrorUrl))))&&((this.hasDownloads == rhs.hasDownloads)||((this.hasDownloads!= null)&&this.hasDownloads.equals(rhs.hasDownloads))))&&((this.fork == rhs.fork)||((this.fork!= null)&&this.fork.equals(rhs.fork))))&&((this.hasProjects == rhs.hasProjects)||((this.hasProjects!= null)&&this.hasProjects.equals(rhs.hasProjects))))&&((this.deploymentsUrl == rhs.deploymentsUrl)||((this.deploymentsUrl!= null)&&this.deploymentsUrl.equals(rhs.deploymentsUrl))))&&((this.eventsUrl == rhs.eventsUrl)||((this.eventsUrl!= null)&&this.eventsUrl.equals(rhs.eventsUrl))))&&((this.gitTagsUrl == rhs.gitTagsUrl)||((this.gitTagsUrl!= null)&&this.gitTagsUrl.equals(rhs.gitTagsUrl))))&&((this.notificationsUrl == rhs.notificationsUrl)||((this.notificationsUrl!= null)&&this.notificationsUrl.equals(rhs.notificationsUrl))))&&((this.gitUrl == rhs.gitUrl)||((this.gitUrl!= null)&&this.gitUrl.equals(rhs.gitUrl))))&&((this.subscriptionUrl == rhs.subscriptionUrl)||((this.subscriptionUrl!= null)&&this.subscriptionUrl.equals(rhs.subscriptionUrl))))&&((this.homepage == rhs.homepage)||((this.homepage!= null)&&this.homepage.equals(rhs.homepage))));
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
    public static class Sender {

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
         * @param url
         * @param starredUrl
         * @param gravatarId
         * @param followersUrl
         * @param id
         * @param eventsUrl
         * @param nodeId
         */
        public Sender(String login, Long id, String nodeId, String avatarUrl, String gravatarId, String url, String htmlUrl, String followersUrl, String followingUrl, String gistsUrl, String starredUrl, String subscriptionsUrl, String organizationsUrl, String reposUrl, String eventsUrl, String receivedEventsUrl, String type, Boolean siteAdmin) {
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
            sb.append(CheckRunGitHubEvent.Sender.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
            if ((other instanceof CheckRunGitHubEvent.Sender) == false) {
                return false;
            }
            CheckRunGitHubEvent.Sender rhs = ((CheckRunGitHubEvent.Sender) other);
            return ((((((((((((((((((((this.receivedEventsUrl == rhs.receivedEventsUrl)||((this.receivedEventsUrl!= null)&&this.receivedEventsUrl.equals(rhs.receivedEventsUrl)))&&((this.siteAdmin == rhs.siteAdmin)||((this.siteAdmin!= null)&&this.siteAdmin.equals(rhs.siteAdmin))))&&((this.followingUrl == rhs.followingUrl)||((this.followingUrl!= null)&&this.followingUrl.equals(rhs.followingUrl))))&&((this.gistsUrl == rhs.gistsUrl)||((this.gistsUrl!= null)&&this.gistsUrl.equals(rhs.gistsUrl))))&&((this.avatarUrl == rhs.avatarUrl)||((this.avatarUrl!= null)&&this.avatarUrl.equals(rhs.avatarUrl))))&&((this.organizationsUrl == rhs.organizationsUrl)||((this.organizationsUrl!= null)&&this.organizationsUrl.equals(rhs.organizationsUrl))))&&((this.reposUrl == rhs.reposUrl)||((this.reposUrl!= null)&&this.reposUrl.equals(rhs.reposUrl))))&&((this.htmlUrl == rhs.htmlUrl)||((this.htmlUrl!= null)&&this.htmlUrl.equals(rhs.htmlUrl))))&&((this.subscriptionsUrl == rhs.subscriptionsUrl)||((this.subscriptionsUrl!= null)&&this.subscriptionsUrl.equals(rhs.subscriptionsUrl))))&&((this.login == rhs.login)||((this.login!= null)&&this.login.equals(rhs.login))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))))&&((this.starredUrl == rhs.starredUrl)||((this.starredUrl!= null)&&this.starredUrl.equals(rhs.starredUrl))))&&((this.gravatarId == rhs.gravatarId)||((this.gravatarId!= null)&&this.gravatarId.equals(rhs.gravatarId))))&&((this.followersUrl == rhs.followersUrl)||((this.followersUrl!= null)&&this.followersUrl.equals(rhs.followersUrl))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.eventsUrl == rhs.eventsUrl)||((this.eventsUrl!= null)&&this.eventsUrl.equals(rhs.eventsUrl))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.nodeId == rhs.nodeId)||((this.nodeId!= null)&&this.nodeId.equals(rhs.nodeId))));
        }

    }

}
