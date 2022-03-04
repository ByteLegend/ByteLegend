
package com.bytelegend.app.jsonmodel.generated.query;

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
    "data"
})
@Generated("jsonschema2pojo")
public class ListOpenPullRequestsResponse {

    @JsonProperty("data")
    private ListOpenPullRequestsResponse.Data data;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public ListOpenPullRequestsResponse() {
    }

    /**
     * 
     * @param data
     */
    public ListOpenPullRequestsResponse(ListOpenPullRequestsResponse.Data data) {
        super();
        this.data = data;
    }

    @JsonProperty("data")
    public ListOpenPullRequestsResponse.Data getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(ListOpenPullRequestsResponse.Data data) {
        this.data = data;
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
        sb.append(ListOpenPullRequestsResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("data");
        sb.append('=');
        sb.append(((this.data == null)?"<null>":this.data));
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
        result = ((result* 31)+((this.data == null)? 0 :this.data.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ListOpenPullRequestsResponse) == false) {
            return false;
        }
        ListOpenPullRequestsResponse rhs = ((ListOpenPullRequestsResponse) other);
        return (((this.data == rhs.data)||((this.data!= null)&&this.data.equals(rhs.data)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "commitUrl",
        "committedDate",
        "oid",
        "status"
    })
    @Generated("jsonschema2pojo")
    public static class Commit {

        @JsonProperty("commitUrl")
        private String commitUrl;
        @JsonProperty("committedDate")
        private String committedDate;
        @JsonProperty("oid")
        private String oid;
        @JsonProperty("status")
        private ListOpenPullRequestsResponse.Status status;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Commit() {
        }

        /**
         * 
         * @param committedDate
         * @param commitUrl
         * @param oid
         * @param status
         */
        public Commit(String commitUrl, String committedDate, String oid, ListOpenPullRequestsResponse.Status status) {
            super();
            this.commitUrl = commitUrl;
            this.committedDate = committedDate;
            this.oid = oid;
            this.status = status;
        }

        @JsonProperty("commitUrl")
        public String getCommitUrl() {
            return commitUrl;
        }

        @JsonProperty("commitUrl")
        public void setCommitUrl(String commitUrl) {
            this.commitUrl = commitUrl;
        }

        @JsonProperty("committedDate")
        public String getCommittedDate() {
            return committedDate;
        }

        @JsonProperty("committedDate")
        public void setCommittedDate(String committedDate) {
            this.committedDate = committedDate;
        }

        @JsonProperty("oid")
        public String getOid() {
            return oid;
        }

        @JsonProperty("oid")
        public void setOid(String oid) {
            this.oid = oid;
        }

        @JsonProperty("status")
        public ListOpenPullRequestsResponse.Status getStatus() {
            return status;
        }

        @JsonProperty("status")
        public void setStatus(ListOpenPullRequestsResponse.Status status) {
            this.status = status;
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
            sb.append(ListOpenPullRequestsResponse.Commit.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("commitUrl");
            sb.append('=');
            sb.append(((this.commitUrl == null)?"<null>":this.commitUrl));
            sb.append(',');
            sb.append("committedDate");
            sb.append('=');
            sb.append(((this.committedDate == null)?"<null>":this.committedDate));
            sb.append(',');
            sb.append("oid");
            sb.append('=');
            sb.append(((this.oid == null)?"<null>":this.oid));
            sb.append(',');
            sb.append("status");
            sb.append('=');
            sb.append(((this.status == null)?"<null>":this.status));
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
            result = ((result* 31)+((this.oid == null)? 0 :this.oid.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.committedDate == null)? 0 :this.committedDate.hashCode()));
            result = ((result* 31)+((this.commitUrl == null)? 0 :this.commitUrl.hashCode()));
            result = ((result* 31)+((this.status == null)? 0 :this.status.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof ListOpenPullRequestsResponse.Commit) == false) {
                return false;
            }
            ListOpenPullRequestsResponse.Commit rhs = ((ListOpenPullRequestsResponse.Commit) other);
            return ((((((this.oid == rhs.oid)||((this.oid!= null)&&this.oid.equals(rhs.oid)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.committedDate == rhs.committedDate)||((this.committedDate!= null)&&this.committedDate.equals(rhs.committedDate))))&&((this.commitUrl == rhs.commitUrl)||((this.commitUrl!= null)&&this.commitUrl.equals(rhs.commitUrl))))&&((this.status == rhs.status)||((this.status!= null)&&this.status.equals(rhs.status))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "nodes"
    })
    @Generated("jsonschema2pojo")
    public static class Commits {

        @JsonProperty("nodes")
        private List<ListOpenPullRequestsResponse.Node2> nodes = new ArrayList<ListOpenPullRequestsResponse.Node2>();
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
         * @param nodes
         */
        public Commits(List<ListOpenPullRequestsResponse.Node2> nodes) {
            super();
            this.nodes = nodes;
        }

        @JsonProperty("nodes")
        public List<ListOpenPullRequestsResponse.Node2> getNodes() {
            return nodes;
        }

        @JsonProperty("nodes")
        public void setNodes(List<ListOpenPullRequestsResponse.Node2> nodes) {
            this.nodes = nodes;
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
            sb.append(ListOpenPullRequestsResponse.Commits.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("nodes");
            sb.append('=');
            sb.append(((this.nodes == null)?"<null>":this.nodes));
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
            result = ((result* 31)+((this.nodes == null)? 0 :this.nodes.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof ListOpenPullRequestsResponse.Commits) == false) {
                return false;
            }
            ListOpenPullRequestsResponse.Commits rhs = ((ListOpenPullRequestsResponse.Commits) other);
            return (((this.nodes == rhs.nodes)||((this.nodes!= null)&&this.nodes.equals(rhs.nodes)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "state",
        "targetUrl",
        "description",
        "context"
    })
    @Generated("jsonschema2pojo")
    public static class Context {

        @JsonProperty("state")
        private String state;
        @JsonProperty("targetUrl")
        private String targetUrl;
        @JsonProperty("description")
        private String description;
        @JsonProperty("context")
        private String context;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Context() {
        }

        /**
         * 
         * @param context
         * @param description
         * @param state
         * @param targetUrl
         */
        public Context(String state, String targetUrl, String description, String context) {
            super();
            this.state = state;
            this.targetUrl = targetUrl;
            this.description = description;
            this.context = context;
        }

        @JsonProperty("state")
        public String getState() {
            return state;
        }

        @JsonProperty("state")
        public void setState(String state) {
            this.state = state;
        }

        @JsonProperty("targetUrl")
        public String getTargetUrl() {
            return targetUrl;
        }

        @JsonProperty("targetUrl")
        public void setTargetUrl(String targetUrl) {
            this.targetUrl = targetUrl;
        }

        @JsonProperty("description")
        public String getDescription() {
            return description;
        }

        @JsonProperty("description")
        public void setDescription(String description) {
            this.description = description;
        }

        @JsonProperty("context")
        public String getContext() {
            return context;
        }

        @JsonProperty("context")
        public void setContext(String context) {
            this.context = context;
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
            sb.append(ListOpenPullRequestsResponse.Context.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("state");
            sb.append('=');
            sb.append(((this.state == null)?"<null>":this.state));
            sb.append(',');
            sb.append("targetUrl");
            sb.append('=');
            sb.append(((this.targetUrl == null)?"<null>":this.targetUrl));
            sb.append(',');
            sb.append("description");
            sb.append('=');
            sb.append(((this.description == null)?"<null>":this.description));
            sb.append(',');
            sb.append("context");
            sb.append('=');
            sb.append(((this.context == null)?"<null>":this.context));
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
            result = ((result* 31)+((this.context == null)? 0 :this.context.hashCode()));
            result = ((result* 31)+((this.description == null)? 0 :this.description.hashCode()));
            result = ((result* 31)+((this.state == null)? 0 :this.state.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.targetUrl == null)? 0 :this.targetUrl.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof ListOpenPullRequestsResponse.Context) == false) {
                return false;
            }
            ListOpenPullRequestsResponse.Context rhs = ((ListOpenPullRequestsResponse.Context) other);
            return ((((((this.context == rhs.context)||((this.context!= null)&&this.context.equals(rhs.context)))&&((this.description == rhs.description)||((this.description!= null)&&this.description.equals(rhs.description))))&&((this.state == rhs.state)||((this.state!= null)&&this.state.equals(rhs.state))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.targetUrl == rhs.targetUrl)||((this.targetUrl!= null)&&this.targetUrl.equals(rhs.targetUrl))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "repository"
    })
    @Generated("jsonschema2pojo")
    public static class Data {

        @JsonProperty("repository")
        private ListOpenPullRequestsResponse.Repository repository;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Data() {
        }

        /**
         * 
         * @param repository
         */
        public Data(ListOpenPullRequestsResponse.Repository repository) {
            super();
            this.repository = repository;
        }

        @JsonProperty("repository")
        public ListOpenPullRequestsResponse.Repository getRepository() {
            return repository;
        }

        @JsonProperty("repository")
        public void setRepository(ListOpenPullRequestsResponse.Repository repository) {
            this.repository = repository;
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
            sb.append(ListOpenPullRequestsResponse.Data.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("repository");
            sb.append('=');
            sb.append(((this.repository == null)?"<null>":this.repository));
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
            result = ((result* 31)+((this.repository == null)? 0 :this.repository.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof ListOpenPullRequestsResponse.Data) == false) {
                return false;
            }
            ListOpenPullRequestsResponse.Data rhs = ((ListOpenPullRequestsResponse.Data) other);
            return (((this.repository == rhs.repository)||((this.repository!= null)&&this.repository.equals(rhs.repository)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "target",
        "repository",
        "name"
    })
    @Generated("jsonschema2pojo")
    public static class HeadRef {

        @JsonProperty("target")
        private ListOpenPullRequestsResponse.Target target;
        @JsonProperty("repository")
        private ListOpenPullRequestsResponse.Repository2 repository;
        @JsonProperty("name")
        private String name;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public HeadRef() {
        }

        /**
         * 
         * @param name
         * @param repository
         * @param target
         */
        public HeadRef(ListOpenPullRequestsResponse.Target target, ListOpenPullRequestsResponse.Repository2 repository, String name) {
            super();
            this.target = target;
            this.repository = repository;
            this.name = name;
        }

        @JsonProperty("target")
        public ListOpenPullRequestsResponse.Target getTarget() {
            return target;
        }

        @JsonProperty("target")
        public void setTarget(ListOpenPullRequestsResponse.Target target) {
            this.target = target;
        }

        @JsonProperty("repository")
        public ListOpenPullRequestsResponse.Repository2 getRepository() {
            return repository;
        }

        @JsonProperty("repository")
        public void setRepository(ListOpenPullRequestsResponse.Repository2 repository) {
            this.repository = repository;
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
            sb.append(ListOpenPullRequestsResponse.HeadRef.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("target");
            sb.append('=');
            sb.append(((this.target == null)?"<null>":this.target));
            sb.append(',');
            sb.append("repository");
            sb.append('=');
            sb.append(((this.repository == null)?"<null>":this.repository));
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
            result = ((result* 31)+((this.repository == null)? 0 :this.repository.hashCode()));
            result = ((result* 31)+((this.target == null)? 0 :this.target.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof ListOpenPullRequestsResponse.HeadRef) == false) {
                return false;
            }
            ListOpenPullRequestsResponse.HeadRef rhs = ((ListOpenPullRequestsResponse.HeadRef) other);
            return (((((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.repository == rhs.repository)||((this.repository!= null)&&this.repository.equals(rhs.repository))))&&((this.target == rhs.target)||((this.target!= null)&&this.target.equals(rhs.target))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "id",
        "title",
        "number",
        "body",
        "url",
        "headRef",
        "baseRefName",
        "commits"
    })
    @Generated("jsonschema2pojo")
    public static class Node {

        @JsonProperty("id")
        private String id;
        @JsonProperty("title")
        private String title;
        @JsonProperty("number")
        private Long number;
        @JsonProperty("body")
        private String body;
        @JsonProperty("url")
        private String url;
        @JsonProperty("headRef")
        private ListOpenPullRequestsResponse.HeadRef headRef;
        @JsonProperty("baseRefName")
        private String baseRefName;
        @JsonProperty("commits")
        private ListOpenPullRequestsResponse.Commits commits;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Node() {
        }

        /**
         * 
         * @param number
         * @param baseRefName
         * @param headRef
         * @param commits
         * @param id
         * @param title
         * @param body
         * @param url
         */
        public Node(String id, String title, Long number, String body, String url, ListOpenPullRequestsResponse.HeadRef headRef, String baseRefName, ListOpenPullRequestsResponse.Commits commits) {
            super();
            this.id = id;
            this.title = title;
            this.number = number;
            this.body = body;
            this.url = url;
            this.headRef = headRef;
            this.baseRefName = baseRefName;
            this.commits = commits;
        }

        @JsonProperty("id")
        public String getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(String id) {
            this.id = id;
        }

        @JsonProperty("title")
        public String getTitle() {
            return title;
        }

        @JsonProperty("title")
        public void setTitle(String title) {
            this.title = title;
        }

        @JsonProperty("number")
        public Long getNumber() {
            return number;
        }

        @JsonProperty("number")
        public void setNumber(Long number) {
            this.number = number;
        }

        @JsonProperty("body")
        public String getBody() {
            return body;
        }

        @JsonProperty("body")
        public void setBody(String body) {
            this.body = body;
        }

        @JsonProperty("url")
        public String getUrl() {
            return url;
        }

        @JsonProperty("url")
        public void setUrl(String url) {
            this.url = url;
        }

        @JsonProperty("headRef")
        public ListOpenPullRequestsResponse.HeadRef getHeadRef() {
            return headRef;
        }

        @JsonProperty("headRef")
        public void setHeadRef(ListOpenPullRequestsResponse.HeadRef headRef) {
            this.headRef = headRef;
        }

        @JsonProperty("baseRefName")
        public String getBaseRefName() {
            return baseRefName;
        }

        @JsonProperty("baseRefName")
        public void setBaseRefName(String baseRefName) {
            this.baseRefName = baseRefName;
        }

        @JsonProperty("commits")
        public ListOpenPullRequestsResponse.Commits getCommits() {
            return commits;
        }

        @JsonProperty("commits")
        public void setCommits(ListOpenPullRequestsResponse.Commits commits) {
            this.commits = commits;
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
            sb.append(ListOpenPullRequestsResponse.Node.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("id");
            sb.append('=');
            sb.append(((this.id == null)?"<null>":this.id));
            sb.append(',');
            sb.append("title");
            sb.append('=');
            sb.append(((this.title == null)?"<null>":this.title));
            sb.append(',');
            sb.append("number");
            sb.append('=');
            sb.append(((this.number == null)?"<null>":this.number));
            sb.append(',');
            sb.append("body");
            sb.append('=');
            sb.append(((this.body == null)?"<null>":this.body));
            sb.append(',');
            sb.append("url");
            sb.append('=');
            sb.append(((this.url == null)?"<null>":this.url));
            sb.append(',');
            sb.append("headRef");
            sb.append('=');
            sb.append(((this.headRef == null)?"<null>":this.headRef));
            sb.append(',');
            sb.append("baseRefName");
            sb.append('=');
            sb.append(((this.baseRefName == null)?"<null>":this.baseRefName));
            sb.append(',');
            sb.append("commits");
            sb.append('=');
            sb.append(((this.commits == null)?"<null>":this.commits));
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
            result = ((result* 31)+((this.number == null)? 0 :this.number.hashCode()));
            result = ((result* 31)+((this.baseRefName == null)? 0 :this.baseRefName.hashCode()));
            result = ((result* 31)+((this.headRef == null)? 0 :this.headRef.hashCode()));
            result = ((result* 31)+((this.commits == null)? 0 :this.commits.hashCode()));
            result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.title == null)? 0 :this.title.hashCode()));
            result = ((result* 31)+((this.body == null)? 0 :this.body.hashCode()));
            result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof ListOpenPullRequestsResponse.Node) == false) {
                return false;
            }
            ListOpenPullRequestsResponse.Node rhs = ((ListOpenPullRequestsResponse.Node) other);
            return ((((((((((this.number == rhs.number)||((this.number!= null)&&this.number.equals(rhs.number)))&&((this.baseRefName == rhs.baseRefName)||((this.baseRefName!= null)&&this.baseRefName.equals(rhs.baseRefName))))&&((this.headRef == rhs.headRef)||((this.headRef!= null)&&this.headRef.equals(rhs.headRef))))&&((this.commits == rhs.commits)||((this.commits!= null)&&this.commits.equals(rhs.commits))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.title == rhs.title)||((this.title!= null)&&this.title.equals(rhs.title))))&&((this.body == rhs.body)||((this.body!= null)&&this.body.equals(rhs.body))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "commit"
    })
    @Generated("jsonschema2pojo")
    public static class Node2 {

        @JsonProperty("commit")
        private ListOpenPullRequestsResponse.Commit commit;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Node2() {
        }

        /**
         * 
         * @param commit
         */
        public Node2(ListOpenPullRequestsResponse.Commit commit) {
            super();
            this.commit = commit;
        }

        @JsonProperty("commit")
        public ListOpenPullRequestsResponse.Commit getCommit() {
            return commit;
        }

        @JsonProperty("commit")
        public void setCommit(ListOpenPullRequestsResponse.Commit commit) {
            this.commit = commit;
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
            sb.append(ListOpenPullRequestsResponse.Node2 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("commit");
            sb.append('=');
            sb.append(((this.commit == null)?"<null>":this.commit));
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
            result = ((result* 31)+((this.commit == null)? 0 :this.commit.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof ListOpenPullRequestsResponse.Node2) == false) {
                return false;
            }
            ListOpenPullRequestsResponse.Node2 rhs = ((ListOpenPullRequestsResponse.Node2) other);
            return (((this.commit == rhs.commit)||((this.commit!= null)&&this.commit.equals(rhs.commit)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "login"
    })
    @Generated("jsonschema2pojo")
    public static class Owner {

        @JsonProperty("login")
        private String login;
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
         * @param login
         */
        public Owner(String login) {
            super();
            this.login = login;
        }

        @JsonProperty("login")
        public String getLogin() {
            return login;
        }

        @JsonProperty("login")
        public void setLogin(String login) {
            this.login = login;
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
            sb.append(ListOpenPullRequestsResponse.Owner.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("login");
            sb.append('=');
            sb.append(((this.login == null)?"<null>":this.login));
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
            result = ((result* 31)+((this.login == null)? 0 :this.login.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof ListOpenPullRequestsResponse.Owner) == false) {
                return false;
            }
            ListOpenPullRequestsResponse.Owner rhs = ((ListOpenPullRequestsResponse.Owner) other);
            return (((this.login == rhs.login)||((this.login!= null)&&this.login.equals(rhs.login)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "login"
    })
    @Generated("jsonschema2pojo")
    public static class Owner2 {

        @JsonProperty("login")
        private String login;
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
         * @param login
         */
        public Owner2(String login) {
            super();
            this.login = login;
        }

        @JsonProperty("login")
        public String getLogin() {
            return login;
        }

        @JsonProperty("login")
        public void setLogin(String login) {
            this.login = login;
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
            sb.append(ListOpenPullRequestsResponse.Owner2 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("login");
            sb.append('=');
            sb.append(((this.login == null)?"<null>":this.login));
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
            result = ((result* 31)+((this.login == null)? 0 :this.login.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof ListOpenPullRequestsResponse.Owner2) == false) {
                return false;
            }
            ListOpenPullRequestsResponse.Owner2 rhs = ((ListOpenPullRequestsResponse.Owner2) other);
            return (((this.login == rhs.login)||((this.login!= null)&&this.login.equals(rhs.login)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "nodes"
    })
    @Generated("jsonschema2pojo")
    public static class PullRequests {

        @JsonProperty("nodes")
        private List<ListOpenPullRequestsResponse.Node> nodes = new ArrayList<ListOpenPullRequestsResponse.Node>();
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public PullRequests() {
        }

        /**
         * 
         * @param nodes
         */
        public PullRequests(List<ListOpenPullRequestsResponse.Node> nodes) {
            super();
            this.nodes = nodes;
        }

        @JsonProperty("nodes")
        public List<ListOpenPullRequestsResponse.Node> getNodes() {
            return nodes;
        }

        @JsonProperty("nodes")
        public void setNodes(List<ListOpenPullRequestsResponse.Node> nodes) {
            this.nodes = nodes;
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
            sb.append(ListOpenPullRequestsResponse.PullRequests.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("nodes");
            sb.append('=');
            sb.append(((this.nodes == null)?"<null>":this.nodes));
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
            result = ((result* 31)+((this.nodes == null)? 0 :this.nodes.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof ListOpenPullRequestsResponse.PullRequests) == false) {
                return false;
            }
            ListOpenPullRequestsResponse.PullRequests rhs = ((ListOpenPullRequestsResponse.PullRequests) other);
            return (((this.nodes == rhs.nodes)||((this.nodes!= null)&&this.nodes.equals(rhs.nodes)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "owner",
        "name",
        "pullRequests"
    })
    @Generated("jsonschema2pojo")
    public static class Repository {

        @JsonProperty("owner")
        private ListOpenPullRequestsResponse.Owner owner;
        @JsonProperty("name")
        private String name;
        @JsonProperty("pullRequests")
        private ListOpenPullRequestsResponse.PullRequests pullRequests;
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
         * @param owner
         * @param name
         * @param pullRequests
         */
        public Repository(ListOpenPullRequestsResponse.Owner owner, String name, ListOpenPullRequestsResponse.PullRequests pullRequests) {
            super();
            this.owner = owner;
            this.name = name;
            this.pullRequests = pullRequests;
        }

        @JsonProperty("owner")
        public ListOpenPullRequestsResponse.Owner getOwner() {
            return owner;
        }

        @JsonProperty("owner")
        public void setOwner(ListOpenPullRequestsResponse.Owner owner) {
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

        @JsonProperty("pullRequests")
        public ListOpenPullRequestsResponse.PullRequests getPullRequests() {
            return pullRequests;
        }

        @JsonProperty("pullRequests")
        public void setPullRequests(ListOpenPullRequestsResponse.PullRequests pullRequests) {
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
            sb.append(ListOpenPullRequestsResponse.Repository.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("owner");
            sb.append('=');
            sb.append(((this.owner == null)?"<null>":this.owner));
            sb.append(',');
            sb.append("name");
            sb.append('=');
            sb.append(((this.name == null)?"<null>":this.name));
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
            result = ((result* 31)+((this.owner == null)? 0 :this.owner.hashCode()));
            result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
            result = ((result* 31)+((this.pullRequests == null)? 0 :this.pullRequests.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof ListOpenPullRequestsResponse.Repository) == false) {
                return false;
            }
            ListOpenPullRequestsResponse.Repository rhs = ((ListOpenPullRequestsResponse.Repository) other);
            return (((((this.owner == rhs.owner)||((this.owner!= null)&&this.owner.equals(rhs.owner)))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.pullRequests == rhs.pullRequests)||((this.pullRequests!= null)&&this.pullRequests.equals(rhs.pullRequests))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "isFork",
        "owner",
        "name"
    })
    @Generated("jsonschema2pojo")
    public static class Repository2 {

        @JsonProperty("isFork")
        private Boolean isFork;
        @JsonProperty("owner")
        private ListOpenPullRequestsResponse.Owner2 owner;
        @JsonProperty("name")
        private String name;
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
         * @param owner
         * @param name
         * @param isFork
         */
        public Repository2(Boolean isFork, ListOpenPullRequestsResponse.Owner2 owner, String name) {
            super();
            this.isFork = isFork;
            this.owner = owner;
            this.name = name;
        }

        @JsonProperty("isFork")
        public Boolean getIsFork() {
            return isFork;
        }

        @JsonProperty("isFork")
        public void setIsFork(Boolean isFork) {
            this.isFork = isFork;
        }

        @JsonProperty("owner")
        public ListOpenPullRequestsResponse.Owner2 getOwner() {
            return owner;
        }

        @JsonProperty("owner")
        public void setOwner(ListOpenPullRequestsResponse.Owner2 owner) {
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
            sb.append(ListOpenPullRequestsResponse.Repository2 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("isFork");
            sb.append('=');
            sb.append(((this.isFork == null)?"<null>":this.isFork));
            sb.append(',');
            sb.append("owner");
            sb.append('=');
            sb.append(((this.owner == null)?"<null>":this.owner));
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
            result = ((result* 31)+((this.owner == null)? 0 :this.owner.hashCode()));
            result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.isFork == null)? 0 :this.isFork.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof ListOpenPullRequestsResponse.Repository2) == false) {
                return false;
            }
            ListOpenPullRequestsResponse.Repository2 rhs = ((ListOpenPullRequestsResponse.Repository2) other);
            return (((((this.owner == rhs.owner)||((this.owner!= null)&&this.owner.equals(rhs.owner)))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.isFork == rhs.isFork)||((this.isFork!= null)&&this.isFork.equals(rhs.isFork))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "state",
        "contexts"
    })
    @Generated("jsonschema2pojo")
    public static class Status {

        @JsonProperty("state")
        private String state;
        @JsonProperty("contexts")
        private List<ListOpenPullRequestsResponse.Context> contexts = new ArrayList<ListOpenPullRequestsResponse.Context>();
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Status() {
        }

        /**
         * 
         * @param state
         * @param contexts
         */
        public Status(String state, List<ListOpenPullRequestsResponse.Context> contexts) {
            super();
            this.state = state;
            this.contexts = contexts;
        }

        @JsonProperty("state")
        public String getState() {
            return state;
        }

        @JsonProperty("state")
        public void setState(String state) {
            this.state = state;
        }

        @JsonProperty("contexts")
        public List<ListOpenPullRequestsResponse.Context> getContexts() {
            return contexts;
        }

        @JsonProperty("contexts")
        public void setContexts(List<ListOpenPullRequestsResponse.Context> contexts) {
            this.contexts = contexts;
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
            sb.append(ListOpenPullRequestsResponse.Status.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("state");
            sb.append('=');
            sb.append(((this.state == null)?"<null>":this.state));
            sb.append(',');
            sb.append("contexts");
            sb.append('=');
            sb.append(((this.contexts == null)?"<null>":this.contexts));
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
            result = ((result* 31)+((this.state == null)? 0 :this.state.hashCode()));
            result = ((result* 31)+((this.contexts == null)? 0 :this.contexts.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof ListOpenPullRequestsResponse.Status) == false) {
                return false;
            }
            ListOpenPullRequestsResponse.Status rhs = ((ListOpenPullRequestsResponse.Status) other);
            return ((((this.state == rhs.state)||((this.state!= null)&&this.state.equals(rhs.state)))&&((this.contexts == rhs.contexts)||((this.contexts!= null)&&this.contexts.equals(rhs.contexts))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "oid"
    })
    @Generated("jsonschema2pojo")
    public static class Target {

        @JsonProperty("oid")
        private String oid;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Target() {
        }

        /**
         * 
         * @param oid
         */
        public Target(String oid) {
            super();
            this.oid = oid;
        }

        @JsonProperty("oid")
        public String getOid() {
            return oid;
        }

        @JsonProperty("oid")
        public void setOid(String oid) {
            this.oid = oid;
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
            sb.append(ListOpenPullRequestsResponse.Target.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("oid");
            sb.append('=');
            sb.append(((this.oid == null)?"<null>":this.oid));
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
            result = ((result* 31)+((this.oid == null)? 0 :this.oid.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof ListOpenPullRequestsResponse.Target) == false) {
                return false;
            }
            ListOpenPullRequestsResponse.Target rhs = ((ListOpenPullRequestsResponse.Target) other);
            return (((this.oid == rhs.oid)||((this.oid!= null)&&this.oid.equals(rhs.oid)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
        }

    }

}
