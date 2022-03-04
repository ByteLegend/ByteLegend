
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
public class MergePullRequestSuccessResponse {

    @JsonProperty("data")
    private MergePullRequestSuccessResponse.Data data;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public MergePullRequestSuccessResponse() {
    }

    /**
     * 
     * @param data
     */
    public MergePullRequestSuccessResponse(MergePullRequestSuccessResponse.Data data) {
        super();
        this.data = data;
    }

    @JsonProperty("data")
    public MergePullRequestSuccessResponse.Data getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(MergePullRequestSuccessResponse.Data data) {
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
        sb.append(MergePullRequestSuccessResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        if ((other instanceof MergePullRequestSuccessResponse) == false) {
            return false;
        }
        MergePullRequestSuccessResponse rhs = ((MergePullRequestSuccessResponse) other);
        return (((this.data == rhs.data)||((this.data!= null)&&this.data.equals(rhs.data)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "oid"
    })
    @Generated("jsonschema2pojo")
    public static class Commit {

        @JsonProperty("oid")
        private String oid;
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
         * @param oid
         */
        public Commit(String oid) {
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
            sb.append(MergePullRequestSuccessResponse.Commit.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
            if ((other instanceof MergePullRequestSuccessResponse.Commit) == false) {
                return false;
            }
            MergePullRequestSuccessResponse.Commit rhs = ((MergePullRequestSuccessResponse.Commit) other);
            return (((this.oid == rhs.oid)||((this.oid!= null)&&this.oid.equals(rhs.oid)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "nodes"
    })
    @Generated("jsonschema2pojo")
    public static class Commits {

        @JsonProperty("nodes")
        private List<MergePullRequestSuccessResponse.Node> nodes = new ArrayList<MergePullRequestSuccessResponse.Node>();
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
        public Commits(List<MergePullRequestSuccessResponse.Node> nodes) {
            super();
            this.nodes = nodes;
        }

        @JsonProperty("nodes")
        public List<MergePullRequestSuccessResponse.Node> getNodes() {
            return nodes;
        }

        @JsonProperty("nodes")
        public void setNodes(List<MergePullRequestSuccessResponse.Node> nodes) {
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
            sb.append(MergePullRequestSuccessResponse.Commits.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
            if ((other instanceof MergePullRequestSuccessResponse.Commits) == false) {
                return false;
            }
            MergePullRequestSuccessResponse.Commits rhs = ((MergePullRequestSuccessResponse.Commits) other);
            return (((this.nodes == rhs.nodes)||((this.nodes!= null)&&this.nodes.equals(rhs.nodes)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "mergePullRequest"
    })
    @Generated("jsonschema2pojo")
    public static class Data {

        @JsonProperty("mergePullRequest")
        private MergePullRequestSuccessResponse.MergePullRequest mergePullRequest;
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
         * @param mergePullRequest
         */
        public Data(MergePullRequestSuccessResponse.MergePullRequest mergePullRequest) {
            super();
            this.mergePullRequest = mergePullRequest;
        }

        @JsonProperty("mergePullRequest")
        public MergePullRequestSuccessResponse.MergePullRequest getMergePullRequest() {
            return mergePullRequest;
        }

        @JsonProperty("mergePullRequest")
        public void setMergePullRequest(MergePullRequestSuccessResponse.MergePullRequest mergePullRequest) {
            this.mergePullRequest = mergePullRequest;
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
            sb.append(MergePullRequestSuccessResponse.Data.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("mergePullRequest");
            sb.append('=');
            sb.append(((this.mergePullRequest == null)?"<null>":this.mergePullRequest));
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
            result = ((result* 31)+((this.mergePullRequest == null)? 0 :this.mergePullRequest.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof MergePullRequestSuccessResponse.Data) == false) {
                return false;
            }
            MergePullRequestSuccessResponse.Data rhs = ((MergePullRequestSuccessResponse.Data) other);
            return (((this.mergePullRequest == rhs.mergePullRequest)||((this.mergePullRequest!= null)&&this.mergePullRequest.equals(rhs.mergePullRequest)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "pullRequest"
    })
    @Generated("jsonschema2pojo")
    public static class MergePullRequest {

        @JsonProperty("pullRequest")
        private MergePullRequestSuccessResponse.PullRequest pullRequest;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public MergePullRequest() {
        }

        /**
         * 
         * @param pullRequest
         */
        public MergePullRequest(MergePullRequestSuccessResponse.PullRequest pullRequest) {
            super();
            this.pullRequest = pullRequest;
        }

        @JsonProperty("pullRequest")
        public MergePullRequestSuccessResponse.PullRequest getPullRequest() {
            return pullRequest;
        }

        @JsonProperty("pullRequest")
        public void setPullRequest(MergePullRequestSuccessResponse.PullRequest pullRequest) {
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
            sb.append(MergePullRequestSuccessResponse.MergePullRequest.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
            result = ((result* 31)+((this.pullRequest == null)? 0 :this.pullRequest.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof MergePullRequestSuccessResponse.MergePullRequest) == false) {
                return false;
            }
            MergePullRequestSuccessResponse.MergePullRequest rhs = ((MergePullRequestSuccessResponse.MergePullRequest) other);
            return (((this.pullRequest == rhs.pullRequest)||((this.pullRequest!= null)&&this.pullRequest.equals(rhs.pullRequest)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "commit"
    })
    @Generated("jsonschema2pojo")
    public static class Node {

        @JsonProperty("commit")
        private MergePullRequestSuccessResponse.Commit commit;
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
         * @param commit
         */
        public Node(MergePullRequestSuccessResponse.Commit commit) {
            super();
            this.commit = commit;
        }

        @JsonProperty("commit")
        public MergePullRequestSuccessResponse.Commit getCommit() {
            return commit;
        }

        @JsonProperty("commit")
        public void setCommit(MergePullRequestSuccessResponse.Commit commit) {
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
            sb.append(MergePullRequestSuccessResponse.Node.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
            if ((other instanceof MergePullRequestSuccessResponse.Node) == false) {
                return false;
            }
            MergePullRequestSuccessResponse.Node rhs = ((MergePullRequestSuccessResponse.Node) other);
            return (((this.commit == rhs.commit)||((this.commit!= null)&&this.commit.equals(rhs.commit)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "commits"
    })
    @Generated("jsonschema2pojo")
    public static class PullRequest {

        @JsonProperty("commits")
        private MergePullRequestSuccessResponse.Commits commits;
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
         * @param commits
         */
        public PullRequest(MergePullRequestSuccessResponse.Commits commits) {
            super();
            this.commits = commits;
        }

        @JsonProperty("commits")
        public MergePullRequestSuccessResponse.Commits getCommits() {
            return commits;
        }

        @JsonProperty("commits")
        public void setCommits(MergePullRequestSuccessResponse.Commits commits) {
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
            sb.append(MergePullRequestSuccessResponse.PullRequest.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
            result = ((result* 31)+((this.commits == null)? 0 :this.commits.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof MergePullRequestSuccessResponse.PullRequest) == false) {
                return false;
            }
            MergePullRequestSuccessResponse.PullRequest rhs = ((MergePullRequestSuccessResponse.PullRequest) other);
            return (((this.commits == rhs.commits)||((this.commits!= null)&&this.commits.equals(rhs.commits)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
        }

    }

}
