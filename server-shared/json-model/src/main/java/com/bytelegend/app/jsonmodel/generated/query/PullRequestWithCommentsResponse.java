
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
public class PullRequestWithCommentsResponse {

    @JsonProperty("data")
    private PullRequestWithCommentsResponse.Data data;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public PullRequestWithCommentsResponse() {
    }

    /**
     * 
     * @param data
     */
    public PullRequestWithCommentsResponse(PullRequestWithCommentsResponse.Data data) {
        super();
        this.data = data;
    }

    @JsonProperty("data")
    public PullRequestWithCommentsResponse.Data getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(PullRequestWithCommentsResponse.Data data) {
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
        sb.append(PullRequestWithCommentsResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        if ((other instanceof PullRequestWithCommentsResponse) == false) {
            return false;
        }
        PullRequestWithCommentsResponse rhs = ((PullRequestWithCommentsResponse) other);
        return (((this.data == rhs.data)||((this.data!= null)&&this.data.equals(rhs.data)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "login"
    })
    @Generated("jsonschema2pojo")
    public static class Author {

        @JsonProperty("login")
        private String login;
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
         * @param login
         */
        public Author(String login) {
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
            sb.append(PullRequestWithCommentsResponse.Author.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
            if ((other instanceof PullRequestWithCommentsResponse.Author) == false) {
                return false;
            }
            PullRequestWithCommentsResponse.Author rhs = ((PullRequestWithCommentsResponse.Author) other);
            return (((this.login == rhs.login)||((this.login!= null)&&this.login.equals(rhs.login)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "login"
    })
    @Generated("jsonschema2pojo")
    public static class Author2 {

        @JsonProperty("login")
        private String login;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Author2() {
        }

        /**
         * 
         * @param login
         */
        public Author2(String login) {
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
            sb.append(PullRequestWithCommentsResponse.Author2 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
            if ((other instanceof PullRequestWithCommentsResponse.Author2) == false) {
                return false;
            }
            PullRequestWithCommentsResponse.Author2 rhs = ((PullRequestWithCommentsResponse.Author2) other);
            return (((this.login == rhs.login)||((this.login!= null)&&this.login.equals(rhs.login)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "login"
    })
    @Generated("jsonschema2pojo")
    public static class Author3 {

        @JsonProperty("login")
        private String login;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Author3() {
        }

        /**
         * 
         * @param login
         */
        public Author3(String login) {
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
            sb.append(PullRequestWithCommentsResponse.Author3 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
            if ((other instanceof PullRequestWithCommentsResponse.Author3) == false) {
                return false;
            }
            PullRequestWithCommentsResponse.Author3 rhs = ((PullRequestWithCommentsResponse.Author3) other);
            return (((this.login == rhs.login)||((this.login!= null)&&this.login.equals(rhs.login)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "target",
        "name"
    })
    @Generated("jsonschema2pojo")
    public static class BaseRef {

        @JsonProperty("target")
        private PullRequestWithCommentsResponse.Target2 target;
        @JsonProperty("name")
        private String name;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public BaseRef() {
        }

        /**
         * 
         * @param name
         * @param target
         */
        public BaseRef(PullRequestWithCommentsResponse.Target2 target, String name) {
            super();
            this.target = target;
            this.name = name;
        }

        @JsonProperty("target")
        public PullRequestWithCommentsResponse.Target2 getTarget() {
            return target;
        }

        @JsonProperty("target")
        public void setTarget(PullRequestWithCommentsResponse.Target2 target) {
            this.target = target;
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
            sb.append(PullRequestWithCommentsResponse.BaseRef.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("target");
            sb.append('=');
            sb.append(((this.target == null)?"<null>":this.target));
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
            result = ((result* 31)+((this.target == null)? 0 :this.target.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof PullRequestWithCommentsResponse.BaseRef) == false) {
                return false;
            }
            PullRequestWithCommentsResponse.BaseRef rhs = ((PullRequestWithCommentsResponse.BaseRef) other);
            return ((((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.target == rhs.target)||((this.target!= null)&&this.target.equals(rhs.target))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "nodes"
    })
    @Generated("jsonschema2pojo")
    public static class Comments {

        @JsonProperty("nodes")
        private List<PullRequestWithCommentsResponse.Node> nodes = new ArrayList<PullRequestWithCommentsResponse.Node>();
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
         * @param nodes
         */
        public Comments(List<PullRequestWithCommentsResponse.Node> nodes) {
            super();
            this.nodes = nodes;
        }

        @JsonProperty("nodes")
        public List<PullRequestWithCommentsResponse.Node> getNodes() {
            return nodes;
        }

        @JsonProperty("nodes")
        public void setNodes(List<PullRequestWithCommentsResponse.Node> nodes) {
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
            sb.append(PullRequestWithCommentsResponse.Comments.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
            if ((other instanceof PullRequestWithCommentsResponse.Comments) == false) {
                return false;
            }
            PullRequestWithCommentsResponse.Comments rhs = ((PullRequestWithCommentsResponse.Comments) other);
            return (((this.nodes == rhs.nodes)||((this.nodes!= null)&&this.nodes.equals(rhs.nodes)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
        }

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
        private PullRequestWithCommentsResponse.Status status;
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
        public Commit(String commitUrl, String committedDate, String oid, PullRequestWithCommentsResponse.Status status) {
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
        public PullRequestWithCommentsResponse.Status getStatus() {
            return status;
        }

        @JsonProperty("status")
        public void setStatus(PullRequestWithCommentsResponse.Status status) {
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
            sb.append(PullRequestWithCommentsResponse.Commit.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
            if ((other instanceof PullRequestWithCommentsResponse.Commit) == false) {
                return false;
            }
            PullRequestWithCommentsResponse.Commit rhs = ((PullRequestWithCommentsResponse.Commit) other);
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
        private List<PullRequestWithCommentsResponse.Node3> nodes = new ArrayList<PullRequestWithCommentsResponse.Node3>();
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
        public Commits(List<PullRequestWithCommentsResponse.Node3> nodes) {
            super();
            this.nodes = nodes;
        }

        @JsonProperty("nodes")
        public List<PullRequestWithCommentsResponse.Node3> getNodes() {
            return nodes;
        }

        @JsonProperty("nodes")
        public void setNodes(List<PullRequestWithCommentsResponse.Node3> nodes) {
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
            sb.append(PullRequestWithCommentsResponse.Commits.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
            if ((other instanceof PullRequestWithCommentsResponse.Commits) == false) {
                return false;
            }
            PullRequestWithCommentsResponse.Commits rhs = ((PullRequestWithCommentsResponse.Commits) other);
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
            sb.append(PullRequestWithCommentsResponse.Context.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
            if ((other instanceof PullRequestWithCommentsResponse.Context) == false) {
                return false;
            }
            PullRequestWithCommentsResponse.Context rhs = ((PullRequestWithCommentsResponse.Context) other);
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
        private PullRequestWithCommentsResponse.Repository repository;
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
        public Data(PullRequestWithCommentsResponse.Repository repository) {
            super();
            this.repository = repository;
        }

        @JsonProperty("repository")
        public PullRequestWithCommentsResponse.Repository getRepository() {
            return repository;
        }

        @JsonProperty("repository")
        public void setRepository(PullRequestWithCommentsResponse.Repository repository) {
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
            sb.append(PullRequestWithCommentsResponse.Data.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
            if ((other instanceof PullRequestWithCommentsResponse.Data) == false) {
                return false;
            }
            PullRequestWithCommentsResponse.Data rhs = ((PullRequestWithCommentsResponse.Data) other);
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
        private PullRequestWithCommentsResponse.Target target;
        @JsonProperty("repository")
        private PullRequestWithCommentsResponse.Repository2 repository;
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
        public HeadRef(PullRequestWithCommentsResponse.Target target, PullRequestWithCommentsResponse.Repository2 repository, String name) {
            super();
            this.target = target;
            this.repository = repository;
            this.name = name;
        }

        @JsonProperty("target")
        public PullRequestWithCommentsResponse.Target getTarget() {
            return target;
        }

        @JsonProperty("target")
        public void setTarget(PullRequestWithCommentsResponse.Target target) {
            this.target = target;
        }

        @JsonProperty("repository")
        public PullRequestWithCommentsResponse.Repository2 getRepository() {
            return repository;
        }

        @JsonProperty("repository")
        public void setRepository(PullRequestWithCommentsResponse.Repository2 repository) {
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
            sb.append(PullRequestWithCommentsResponse.HeadRef.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
            if ((other instanceof PullRequestWithCommentsResponse.HeadRef) == false) {
                return false;
            }
            PullRequestWithCommentsResponse.HeadRef rhs = ((PullRequestWithCommentsResponse.HeadRef) other);
            return (((((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.repository == rhs.repository)||((this.repository!= null)&&this.repository.equals(rhs.repository))))&&((this.target == rhs.target)||((this.target!= null)&&this.target.equals(rhs.target))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "id",
        "databaseId",
        "author",
        "authorAssociation",
        "body"
    })
    @Generated("jsonschema2pojo")
    public static class Node {

        @JsonProperty("id")
        private String id;
        @JsonProperty("databaseId")
        private Long databaseId;
        @JsonProperty("author")
        private PullRequestWithCommentsResponse.Author2 author;
        @JsonProperty("authorAssociation")
        private String authorAssociation;
        @JsonProperty("body")
        private String body;
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
         * @param author
         * @param id
         * @param databaseId
         * @param body
         * @param authorAssociation
         */
        public Node(String id, Long databaseId, PullRequestWithCommentsResponse.Author2 author, String authorAssociation, String body) {
            super();
            this.id = id;
            this.databaseId = databaseId;
            this.author = author;
            this.authorAssociation = authorAssociation;
            this.body = body;
        }

        @JsonProperty("id")
        public String getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(String id) {
            this.id = id;
        }

        @JsonProperty("databaseId")
        public Long getDatabaseId() {
            return databaseId;
        }

        @JsonProperty("databaseId")
        public void setDatabaseId(Long databaseId) {
            this.databaseId = databaseId;
        }

        @JsonProperty("author")
        public PullRequestWithCommentsResponse.Author2 getAuthor() {
            return author;
        }

        @JsonProperty("author")
        public void setAuthor(PullRequestWithCommentsResponse.Author2 author) {
            this.author = author;
        }

        @JsonProperty("authorAssociation")
        public String getAuthorAssociation() {
            return authorAssociation;
        }

        @JsonProperty("authorAssociation")
        public void setAuthorAssociation(String authorAssociation) {
            this.authorAssociation = authorAssociation;
        }

        @JsonProperty("body")
        public String getBody() {
            return body;
        }

        @JsonProperty("body")
        public void setBody(String body) {
            this.body = body;
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
            sb.append(PullRequestWithCommentsResponse.Node.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("id");
            sb.append('=');
            sb.append(((this.id == null)?"<null>":this.id));
            sb.append(',');
            sb.append("databaseId");
            sb.append('=');
            sb.append(((this.databaseId == null)?"<null>":this.databaseId));
            sb.append(',');
            sb.append("author");
            sb.append('=');
            sb.append(((this.author == null)?"<null>":this.author));
            sb.append(',');
            sb.append("authorAssociation");
            sb.append('=');
            sb.append(((this.authorAssociation == null)?"<null>":this.authorAssociation));
            sb.append(',');
            sb.append("body");
            sb.append('=');
            sb.append(((this.body == null)?"<null>":this.body));
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
            result = ((result* 31)+((this.author == null)? 0 :this.author.hashCode()));
            result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.databaseId == null)? 0 :this.databaseId.hashCode()));
            result = ((result* 31)+((this.body == null)? 0 :this.body.hashCode()));
            result = ((result* 31)+((this.authorAssociation == null)? 0 :this.authorAssociation.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof PullRequestWithCommentsResponse.Node) == false) {
                return false;
            }
            PullRequestWithCommentsResponse.Node rhs = ((PullRequestWithCommentsResponse.Node) other);
            return (((((((this.author == rhs.author)||((this.author!= null)&&this.author.equals(rhs.author)))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.databaseId == rhs.databaseId)||((this.databaseId!= null)&&this.databaseId.equals(rhs.databaseId))))&&((this.body == rhs.body)||((this.body!= null)&&this.body.equals(rhs.body))))&&((this.authorAssociation == rhs.authorAssociation)||((this.authorAssociation!= null)&&this.authorAssociation.equals(rhs.authorAssociation))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "databaseId",
        "author",
        "body",
        "state"
    })
    @Generated("jsonschema2pojo")
    public static class Node2 {

        @JsonProperty("databaseId")
        private Long databaseId;
        @JsonProperty("author")
        private PullRequestWithCommentsResponse.Author3 author;
        @JsonProperty("body")
        private String body;
        @JsonProperty("state")
        private String state;
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
         * @param author
         * @param state
         * @param databaseId
         * @param body
         */
        public Node2(Long databaseId, PullRequestWithCommentsResponse.Author3 author, String body, String state) {
            super();
            this.databaseId = databaseId;
            this.author = author;
            this.body = body;
            this.state = state;
        }

        @JsonProperty("databaseId")
        public Long getDatabaseId() {
            return databaseId;
        }

        @JsonProperty("databaseId")
        public void setDatabaseId(Long databaseId) {
            this.databaseId = databaseId;
        }

        @JsonProperty("author")
        public PullRequestWithCommentsResponse.Author3 getAuthor() {
            return author;
        }

        @JsonProperty("author")
        public void setAuthor(PullRequestWithCommentsResponse.Author3 author) {
            this.author = author;
        }

        @JsonProperty("body")
        public String getBody() {
            return body;
        }

        @JsonProperty("body")
        public void setBody(String body) {
            this.body = body;
        }

        @JsonProperty("state")
        public String getState() {
            return state;
        }

        @JsonProperty("state")
        public void setState(String state) {
            this.state = state;
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
            sb.append(PullRequestWithCommentsResponse.Node2 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("databaseId");
            sb.append('=');
            sb.append(((this.databaseId == null)?"<null>":this.databaseId));
            sb.append(',');
            sb.append("author");
            sb.append('=');
            sb.append(((this.author == null)?"<null>":this.author));
            sb.append(',');
            sb.append("body");
            sb.append('=');
            sb.append(((this.body == null)?"<null>":this.body));
            sb.append(',');
            sb.append("state");
            sb.append('=');
            sb.append(((this.state == null)?"<null>":this.state));
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
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.databaseId == null)? 0 :this.databaseId.hashCode()));
            result = ((result* 31)+((this.body == null)? 0 :this.body.hashCode()));
            result = ((result* 31)+((this.author == null)? 0 :this.author.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof PullRequestWithCommentsResponse.Node2) == false) {
                return false;
            }
            PullRequestWithCommentsResponse.Node2 rhs = ((PullRequestWithCommentsResponse.Node2) other);
            return ((((((this.state == rhs.state)||((this.state!= null)&&this.state.equals(rhs.state)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.databaseId == rhs.databaseId)||((this.databaseId!= null)&&this.databaseId.equals(rhs.databaseId))))&&((this.body == rhs.body)||((this.body!= null)&&this.body.equals(rhs.body))))&&((this.author == rhs.author)||((this.author!= null)&&this.author.equals(rhs.author))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "commit"
    })
    @Generated("jsonschema2pojo")
    public static class Node3 {

        @JsonProperty("commit")
        private PullRequestWithCommentsResponse.Commit commit;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Node3() {
        }

        /**
         * 
         * @param commit
         */
        public Node3(PullRequestWithCommentsResponse.Commit commit) {
            super();
            this.commit = commit;
        }

        @JsonProperty("commit")
        public PullRequestWithCommentsResponse.Commit getCommit() {
            return commit;
        }

        @JsonProperty("commit")
        public void setCommit(PullRequestWithCommentsResponse.Commit commit) {
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
            sb.append(PullRequestWithCommentsResponse.Node3 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
            if ((other instanceof PullRequestWithCommentsResponse.Node3) == false) {
                return false;
            }
            PullRequestWithCommentsResponse.Node3 rhs = ((PullRequestWithCommentsResponse.Node3) other);
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
            sb.append(PullRequestWithCommentsResponse.Owner.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
            if ((other instanceof PullRequestWithCommentsResponse.Owner) == false) {
                return false;
            }
            PullRequestWithCommentsResponse.Owner rhs = ((PullRequestWithCommentsResponse.Owner) other);
            return (((this.login == rhs.login)||((this.login!= null)&&this.login.equals(rhs.login)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "id",
        "number",
        "title",
        "body",
        "url",
        "author",
        "headRef",
        "baseRef",
        "baseRefOid",
        "comments",
        "reviews",
        "commits"
    })
    @Generated("jsonschema2pojo")
    public static class PullRequest {

        @JsonProperty("id")
        private String id;
        @JsonProperty("number")
        private Long number;
        @JsonProperty("title")
        private String title;
        @JsonProperty("body")
        private String body;
        @JsonProperty("url")
        private String url;
        @JsonProperty("author")
        private PullRequestWithCommentsResponse.Author author;
        @JsonProperty("headRef")
        private PullRequestWithCommentsResponse.HeadRef headRef;
        @JsonProperty("baseRef")
        private PullRequestWithCommentsResponse.BaseRef baseRef;
        @JsonProperty("baseRefOid")
        private String baseRefOid;
        @JsonProperty("comments")
        private PullRequestWithCommentsResponse.Comments comments;
        @JsonProperty("reviews")
        private PullRequestWithCommentsResponse.Reviews reviews;
        @JsonProperty("commits")
        private PullRequestWithCommentsResponse.Commits commits;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public PullRequest() {
        }

        /**
         * 
         * @param number
         * @param comments
         * @param headRef
         * @param reviews
         * @param author
         * @param commits
         * @param id
         * @param title
         * @param body
         * @param baseRefOid
         * @param url
         * @param baseRef
         */
        public PullRequest(String id, Long number, String title, String body, String url, PullRequestWithCommentsResponse.Author author, PullRequestWithCommentsResponse.HeadRef headRef, PullRequestWithCommentsResponse.BaseRef baseRef, String baseRefOid, PullRequestWithCommentsResponse.Comments comments, PullRequestWithCommentsResponse.Reviews reviews, PullRequestWithCommentsResponse.Commits commits) {
            super();
            this.id = id;
            this.number = number;
            this.title = title;
            this.body = body;
            this.url = url;
            this.author = author;
            this.headRef = headRef;
            this.baseRef = baseRef;
            this.baseRefOid = baseRefOid;
            this.comments = comments;
            this.reviews = reviews;
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

        @JsonProperty("number")
        public Long getNumber() {
            return number;
        }

        @JsonProperty("number")
        public void setNumber(Long number) {
            this.number = number;
        }

        @JsonProperty("title")
        public String getTitle() {
            return title;
        }

        @JsonProperty("title")
        public void setTitle(String title) {
            this.title = title;
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

        @JsonProperty("author")
        public PullRequestWithCommentsResponse.Author getAuthor() {
            return author;
        }

        @JsonProperty("author")
        public void setAuthor(PullRequestWithCommentsResponse.Author author) {
            this.author = author;
        }

        @JsonProperty("headRef")
        public PullRequestWithCommentsResponse.HeadRef getHeadRef() {
            return headRef;
        }

        @JsonProperty("headRef")
        public void setHeadRef(PullRequestWithCommentsResponse.HeadRef headRef) {
            this.headRef = headRef;
        }

        @JsonProperty("baseRef")
        public PullRequestWithCommentsResponse.BaseRef getBaseRef() {
            return baseRef;
        }

        @JsonProperty("baseRef")
        public void setBaseRef(PullRequestWithCommentsResponse.BaseRef baseRef) {
            this.baseRef = baseRef;
        }

        @JsonProperty("baseRefOid")
        public String getBaseRefOid() {
            return baseRefOid;
        }

        @JsonProperty("baseRefOid")
        public void setBaseRefOid(String baseRefOid) {
            this.baseRefOid = baseRefOid;
        }

        @JsonProperty("comments")
        public PullRequestWithCommentsResponse.Comments getComments() {
            return comments;
        }

        @JsonProperty("comments")
        public void setComments(PullRequestWithCommentsResponse.Comments comments) {
            this.comments = comments;
        }

        @JsonProperty("reviews")
        public PullRequestWithCommentsResponse.Reviews getReviews() {
            return reviews;
        }

        @JsonProperty("reviews")
        public void setReviews(PullRequestWithCommentsResponse.Reviews reviews) {
            this.reviews = reviews;
        }

        @JsonProperty("commits")
        public PullRequestWithCommentsResponse.Commits getCommits() {
            return commits;
        }

        @JsonProperty("commits")
        public void setCommits(PullRequestWithCommentsResponse.Commits commits) {
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
            sb.append(PullRequestWithCommentsResponse.PullRequest.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("id");
            sb.append('=');
            sb.append(((this.id == null)?"<null>":this.id));
            sb.append(',');
            sb.append("number");
            sb.append('=');
            sb.append(((this.number == null)?"<null>":this.number));
            sb.append(',');
            sb.append("title");
            sb.append('=');
            sb.append(((this.title == null)?"<null>":this.title));
            sb.append(',');
            sb.append("body");
            sb.append('=');
            sb.append(((this.body == null)?"<null>":this.body));
            sb.append(',');
            sb.append("url");
            sb.append('=');
            sb.append(((this.url == null)?"<null>":this.url));
            sb.append(',');
            sb.append("author");
            sb.append('=');
            sb.append(((this.author == null)?"<null>":this.author));
            sb.append(',');
            sb.append("headRef");
            sb.append('=');
            sb.append(((this.headRef == null)?"<null>":this.headRef));
            sb.append(',');
            sb.append("baseRef");
            sb.append('=');
            sb.append(((this.baseRef == null)?"<null>":this.baseRef));
            sb.append(',');
            sb.append("baseRefOid");
            sb.append('=');
            sb.append(((this.baseRefOid == null)?"<null>":this.baseRefOid));
            sb.append(',');
            sb.append("comments");
            sb.append('=');
            sb.append(((this.comments == null)?"<null>":this.comments));
            sb.append(',');
            sb.append("reviews");
            sb.append('=');
            sb.append(((this.reviews == null)?"<null>":this.reviews));
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
            result = ((result* 31)+((this.comments == null)? 0 :this.comments.hashCode()));
            result = ((result* 31)+((this.author == null)? 0 :this.author.hashCode()));
            result = ((result* 31)+((this.title == null)? 0 :this.title.hashCode()));
            result = ((result* 31)+((this.body == null)? 0 :this.body.hashCode()));
            result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
            result = ((result* 31)+((this.baseRef == null)? 0 :this.baseRef.hashCode()));
            result = ((result* 31)+((this.number == null)? 0 :this.number.hashCode()));
            result = ((result* 31)+((this.headRef == null)? 0 :this.headRef.hashCode()));
            result = ((result* 31)+((this.reviews == null)? 0 :this.reviews.hashCode()));
            result = ((result* 31)+((this.commits == null)? 0 :this.commits.hashCode()));
            result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.baseRefOid == null)? 0 :this.baseRefOid.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof PullRequestWithCommentsResponse.PullRequest) == false) {
                return false;
            }
            PullRequestWithCommentsResponse.PullRequest rhs = ((PullRequestWithCommentsResponse.PullRequest) other);
            return ((((((((((((((this.comments == rhs.comments)||((this.comments!= null)&&this.comments.equals(rhs.comments)))&&((this.author == rhs.author)||((this.author!= null)&&this.author.equals(rhs.author))))&&((this.title == rhs.title)||((this.title!= null)&&this.title.equals(rhs.title))))&&((this.body == rhs.body)||((this.body!= null)&&this.body.equals(rhs.body))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))))&&((this.baseRef == rhs.baseRef)||((this.baseRef!= null)&&this.baseRef.equals(rhs.baseRef))))&&((this.number == rhs.number)||((this.number!= null)&&this.number.equals(rhs.number))))&&((this.headRef == rhs.headRef)||((this.headRef!= null)&&this.headRef.equals(rhs.headRef))))&&((this.reviews == rhs.reviews)||((this.reviews!= null)&&this.reviews.equals(rhs.reviews))))&&((this.commits == rhs.commits)||((this.commits!= null)&&this.commits.equals(rhs.commits))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.baseRefOid == rhs.baseRefOid)||((this.baseRefOid!= null)&&this.baseRefOid.equals(rhs.baseRefOid))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "nameWithOwner",
        "pullRequest"
    })
    @Generated("jsonschema2pojo")
    public static class Repository {

        @JsonProperty("nameWithOwner")
        private String nameWithOwner;
        @JsonProperty("pullRequest")
        private PullRequestWithCommentsResponse.PullRequest pullRequest;
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
         * @param nameWithOwner
         * @param pullRequest
         */
        public Repository(String nameWithOwner, PullRequestWithCommentsResponse.PullRequest pullRequest) {
            super();
            this.nameWithOwner = nameWithOwner;
            this.pullRequest = pullRequest;
        }

        @JsonProperty("nameWithOwner")
        public String getNameWithOwner() {
            return nameWithOwner;
        }

        @JsonProperty("nameWithOwner")
        public void setNameWithOwner(String nameWithOwner) {
            this.nameWithOwner = nameWithOwner;
        }

        @JsonProperty("pullRequest")
        public PullRequestWithCommentsResponse.PullRequest getPullRequest() {
            return pullRequest;
        }

        @JsonProperty("pullRequest")
        public void setPullRequest(PullRequestWithCommentsResponse.PullRequest pullRequest) {
            this.pullRequest = pullRequest;
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
            sb.append(PullRequestWithCommentsResponse.Repository.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("nameWithOwner");
            sb.append('=');
            sb.append(((this.nameWithOwner == null)?"<null>":this.nameWithOwner));
            sb.append(',');
            sb.append("pullRequest");
            sb.append('=');
            sb.append(((this.pullRequest == null)?"<null>":this.pullRequest));
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
            result = ((result* 31)+((this.nameWithOwner == null)? 0 :this.nameWithOwner.hashCode()));
            result = ((result* 31)+((this.pullRequest == null)? 0 :this.pullRequest.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof PullRequestWithCommentsResponse.Repository) == false) {
                return false;
            }
            PullRequestWithCommentsResponse.Repository rhs = ((PullRequestWithCommentsResponse.Repository) other);
            return ((((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties)))&&((this.nameWithOwner == rhs.nameWithOwner)||((this.nameWithOwner!= null)&&this.nameWithOwner.equals(rhs.nameWithOwner))))&&((this.pullRequest == rhs.pullRequest)||((this.pullRequest!= null)&&this.pullRequest.equals(rhs.pullRequest))));
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
        private PullRequestWithCommentsResponse.Owner owner;
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
        public Repository2(Boolean isFork, PullRequestWithCommentsResponse.Owner owner, String name) {
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
        public PullRequestWithCommentsResponse.Owner getOwner() {
            return owner;
        }

        @JsonProperty("owner")
        public void setOwner(PullRequestWithCommentsResponse.Owner owner) {
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
            sb.append(PullRequestWithCommentsResponse.Repository2 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
            if ((other instanceof PullRequestWithCommentsResponse.Repository2) == false) {
                return false;
            }
            PullRequestWithCommentsResponse.Repository2 rhs = ((PullRequestWithCommentsResponse.Repository2) other);
            return (((((this.owner == rhs.owner)||((this.owner!= null)&&this.owner.equals(rhs.owner)))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.isFork == rhs.isFork)||((this.isFork!= null)&&this.isFork.equals(rhs.isFork))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "nodes"
    })
    @Generated("jsonschema2pojo")
    public static class Reviews {

        @JsonProperty("nodes")
        private List<PullRequestWithCommentsResponse.Node2> nodes = new ArrayList<PullRequestWithCommentsResponse.Node2>();
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Reviews() {
        }

        /**
         * 
         * @param nodes
         */
        public Reviews(List<PullRequestWithCommentsResponse.Node2> nodes) {
            super();
            this.nodes = nodes;
        }

        @JsonProperty("nodes")
        public List<PullRequestWithCommentsResponse.Node2> getNodes() {
            return nodes;
        }

        @JsonProperty("nodes")
        public void setNodes(List<PullRequestWithCommentsResponse.Node2> nodes) {
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
            sb.append(PullRequestWithCommentsResponse.Reviews.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
            if ((other instanceof PullRequestWithCommentsResponse.Reviews) == false) {
                return false;
            }
            PullRequestWithCommentsResponse.Reviews rhs = ((PullRequestWithCommentsResponse.Reviews) other);
            return (((this.nodes == rhs.nodes)||((this.nodes!= null)&&this.nodes.equals(rhs.nodes)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
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
        private List<PullRequestWithCommentsResponse.Context> contexts = new ArrayList<PullRequestWithCommentsResponse.Context>();
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
        public Status(String state, List<PullRequestWithCommentsResponse.Context> contexts) {
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
        public List<PullRequestWithCommentsResponse.Context> getContexts() {
            return contexts;
        }

        @JsonProperty("contexts")
        public void setContexts(List<PullRequestWithCommentsResponse.Context> contexts) {
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
            sb.append(PullRequestWithCommentsResponse.Status.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
            if ((other instanceof PullRequestWithCommentsResponse.Status) == false) {
                return false;
            }
            PullRequestWithCommentsResponse.Status rhs = ((PullRequestWithCommentsResponse.Status) other);
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
            sb.append(PullRequestWithCommentsResponse.Target.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
            if ((other instanceof PullRequestWithCommentsResponse.Target) == false) {
                return false;
            }
            PullRequestWithCommentsResponse.Target rhs = ((PullRequestWithCommentsResponse.Target) other);
            return (((this.oid == rhs.oid)||((this.oid!= null)&&this.oid.equals(rhs.oid)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "oid"
    })
    @Generated("jsonschema2pojo")
    public static class Target2 {

        @JsonProperty("oid")
        private String oid;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Target2() {
        }

        /**
         * 
         * @param oid
         */
        public Target2(String oid) {
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
            sb.append(PullRequestWithCommentsResponse.Target2 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
            if ((other instanceof PullRequestWithCommentsResponse.Target2) == false) {
                return false;
            }
            PullRequestWithCommentsResponse.Target2 rhs = ((PullRequestWithCommentsResponse.Target2) other);
            return (((this.oid == rhs.oid)||((this.oid!= null)&&this.oid.equals(rhs.oid)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
        }

    }

}
