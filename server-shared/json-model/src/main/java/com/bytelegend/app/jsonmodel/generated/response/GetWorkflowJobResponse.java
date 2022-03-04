
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
    "id",
    "run_id",
    "run_url",
    "run_attempt",
    "node_id",
    "head_sha",
    "url",
    "html_url",
    "status",
    "conclusion",
    "started_at",
    "completed_at",
    "name",
    "steps",
    "check_run_url",
    "labels",
    "runner_id",
    "runner_name",
    "runner_group_id",
    "runner_group_name"
})
@Generated("jsonschema2pojo")
public class GetWorkflowJobResponse {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("run_id")
    private Long runId;
    @JsonProperty("run_url")
    private String runUrl;
    @JsonProperty("run_attempt")
    private Long runAttempt;
    @JsonProperty("node_id")
    private String nodeId;
    @JsonProperty("head_sha")
    private String headSha;
    @JsonProperty("url")
    private String url;
    @JsonProperty("html_url")
    private String htmlUrl;
    @JsonProperty("status")
    private String status;
    @JsonProperty("conclusion")
    private String conclusion;
    @JsonProperty("started_at")
    private String startedAt;
    @JsonProperty("completed_at")
    private String completedAt;
    @JsonProperty("name")
    private String name;
    @JsonProperty("steps")
    private List<GetWorkflowJobResponse.Step> steps = new ArrayList<GetWorkflowJobResponse.Step>();
    @JsonProperty("check_run_url")
    private String checkRunUrl;
    @JsonProperty("labels")
    private List<String> labels = new ArrayList<String>();
    @JsonProperty("runner_id")
    private Long runnerId;
    @JsonProperty("runner_name")
    private String runnerName;
    @JsonProperty("runner_group_id")
    private Long runnerGroupId;
    @JsonProperty("runner_group_name")
    private String runnerGroupName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public GetWorkflowJobResponse() {
    }

    /**
     * 
     * @param completedAt
     * @param runUrl
     * @param htmlUrl
     * @param startedAt
     * @param headSha
     * @param checkRunUrl
     * @param steps
     * @param url
     * @param labels
     * @param conclusion
     * @param runnerGroupId
     * @param runnerName
     * @param runAttempt
     * @param runnerGroupName
     * @param name
     * @param runnerId
     * @param id
     * @param runId
     * @param nodeId
     * @param status
     */
    public GetWorkflowJobResponse(Long id, Long runId, String runUrl, Long runAttempt, String nodeId, String headSha, String url, String htmlUrl, String status, String conclusion, String startedAt, String completedAt, String name, List<GetWorkflowJobResponse.Step> steps, String checkRunUrl, List<String> labels, Long runnerId, String runnerName, Long runnerGroupId, String runnerGroupName) {
        super();
        this.id = id;
        this.runId = runId;
        this.runUrl = runUrl;
        this.runAttempt = runAttempt;
        this.nodeId = nodeId;
        this.headSha = headSha;
        this.url = url;
        this.htmlUrl = htmlUrl;
        this.status = status;
        this.conclusion = conclusion;
        this.startedAt = startedAt;
        this.completedAt = completedAt;
        this.name = name;
        this.steps = steps;
        this.checkRunUrl = checkRunUrl;
        this.labels = labels;
        this.runnerId = runnerId;
        this.runnerName = runnerName;
        this.runnerGroupId = runnerGroupId;
        this.runnerGroupName = runnerGroupName;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("run_id")
    public Long getRunId() {
        return runId;
    }

    @JsonProperty("run_id")
    public void setRunId(Long runId) {
        this.runId = runId;
    }

    @JsonProperty("run_url")
    public String getRunUrl() {
        return runUrl;
    }

    @JsonProperty("run_url")
    public void setRunUrl(String runUrl) {
        this.runUrl = runUrl;
    }

    @JsonProperty("run_attempt")
    public Long getRunAttempt() {
        return runAttempt;
    }

    @JsonProperty("run_attempt")
    public void setRunAttempt(Long runAttempt) {
        this.runAttempt = runAttempt;
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

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("steps")
    public List<GetWorkflowJobResponse.Step> getSteps() {
        return steps;
    }

    @JsonProperty("steps")
    public void setSteps(List<GetWorkflowJobResponse.Step> steps) {
        this.steps = steps;
    }

    @JsonProperty("check_run_url")
    public String getCheckRunUrl() {
        return checkRunUrl;
    }

    @JsonProperty("check_run_url")
    public void setCheckRunUrl(String checkRunUrl) {
        this.checkRunUrl = checkRunUrl;
    }

    @JsonProperty("labels")
    public List<String> getLabels() {
        return labels;
    }

    @JsonProperty("labels")
    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    @JsonProperty("runner_id")
    public Long getRunnerId() {
        return runnerId;
    }

    @JsonProperty("runner_id")
    public void setRunnerId(Long runnerId) {
        this.runnerId = runnerId;
    }

    @JsonProperty("runner_name")
    public String getRunnerName() {
        return runnerName;
    }

    @JsonProperty("runner_name")
    public void setRunnerName(String runnerName) {
        this.runnerName = runnerName;
    }

    @JsonProperty("runner_group_id")
    public Long getRunnerGroupId() {
        return runnerGroupId;
    }

    @JsonProperty("runner_group_id")
    public void setRunnerGroupId(Long runnerGroupId) {
        this.runnerGroupId = runnerGroupId;
    }

    @JsonProperty("runner_group_name")
    public String getRunnerGroupName() {
        return runnerGroupName;
    }

    @JsonProperty("runner_group_name")
    public void setRunnerGroupName(String runnerGroupName) {
        this.runnerGroupName = runnerGroupName;
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
        sb.append(GetWorkflowJobResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("runId");
        sb.append('=');
        sb.append(((this.runId == null)?"<null>":this.runId));
        sb.append(',');
        sb.append("runUrl");
        sb.append('=');
        sb.append(((this.runUrl == null)?"<null>":this.runUrl));
        sb.append(',');
        sb.append("runAttempt");
        sb.append('=');
        sb.append(((this.runAttempt == null)?"<null>":this.runAttempt));
        sb.append(',');
        sb.append("nodeId");
        sb.append('=');
        sb.append(((this.nodeId == null)?"<null>":this.nodeId));
        sb.append(',');
        sb.append("headSha");
        sb.append('=');
        sb.append(((this.headSha == null)?"<null>":this.headSha));
        sb.append(',');
        sb.append("url");
        sb.append('=');
        sb.append(((this.url == null)?"<null>":this.url));
        sb.append(',');
        sb.append("htmlUrl");
        sb.append('=');
        sb.append(((this.htmlUrl == null)?"<null>":this.htmlUrl));
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
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("steps");
        sb.append('=');
        sb.append(((this.steps == null)?"<null>":this.steps));
        sb.append(',');
        sb.append("checkRunUrl");
        sb.append('=');
        sb.append(((this.checkRunUrl == null)?"<null>":this.checkRunUrl));
        sb.append(',');
        sb.append("labels");
        sb.append('=');
        sb.append(((this.labels == null)?"<null>":this.labels));
        sb.append(',');
        sb.append("runnerId");
        sb.append('=');
        sb.append(((this.runnerId == null)?"<null>":this.runnerId));
        sb.append(',');
        sb.append("runnerName");
        sb.append('=');
        sb.append(((this.runnerName == null)?"<null>":this.runnerName));
        sb.append(',');
        sb.append("runnerGroupId");
        sb.append('=');
        sb.append(((this.runnerGroupId == null)?"<null>":this.runnerGroupId));
        sb.append(',');
        sb.append("runnerGroupName");
        sb.append('=');
        sb.append(((this.runnerGroupName == null)?"<null>":this.runnerGroupName));
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
        result = ((result* 31)+((this.completedAt == null)? 0 :this.completedAt.hashCode()));
        result = ((result* 31)+((this.runUrl == null)? 0 :this.runUrl.hashCode()));
        result = ((result* 31)+((this.htmlUrl == null)? 0 :this.htmlUrl.hashCode()));
        result = ((result* 31)+((this.startedAt == null)? 0 :this.startedAt.hashCode()));
        result = ((result* 31)+((this.headSha == null)? 0 :this.headSha.hashCode()));
        result = ((result* 31)+((this.checkRunUrl == null)? 0 :this.checkRunUrl.hashCode()));
        result = ((result* 31)+((this.steps == null)? 0 :this.steps.hashCode()));
        result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
        result = ((result* 31)+((this.labels == null)? 0 :this.labels.hashCode()));
        result = ((result* 31)+((this.conclusion == null)? 0 :this.conclusion.hashCode()));
        result = ((result* 31)+((this.runnerGroupId == null)? 0 :this.runnerGroupId.hashCode()));
        result = ((result* 31)+((this.runnerName == null)? 0 :this.runnerName.hashCode()));
        result = ((result* 31)+((this.runAttempt == null)? 0 :this.runAttempt.hashCode()));
        result = ((result* 31)+((this.runnerGroupName == null)? 0 :this.runnerGroupName.hashCode()));
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        result = ((result* 31)+((this.runnerId == null)? 0 :this.runnerId.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.runId == null)? 0 :this.runId.hashCode()));
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
        if ((other instanceof GetWorkflowJobResponse) == false) {
            return false;
        }
        GetWorkflowJobResponse rhs = ((GetWorkflowJobResponse) other);
        return ((((((((((((((((((((((this.completedAt == rhs.completedAt)||((this.completedAt!= null)&&this.completedAt.equals(rhs.completedAt)))&&((this.runUrl == rhs.runUrl)||((this.runUrl!= null)&&this.runUrl.equals(rhs.runUrl))))&&((this.htmlUrl == rhs.htmlUrl)||((this.htmlUrl!= null)&&this.htmlUrl.equals(rhs.htmlUrl))))&&((this.startedAt == rhs.startedAt)||((this.startedAt!= null)&&this.startedAt.equals(rhs.startedAt))))&&((this.headSha == rhs.headSha)||((this.headSha!= null)&&this.headSha.equals(rhs.headSha))))&&((this.checkRunUrl == rhs.checkRunUrl)||((this.checkRunUrl!= null)&&this.checkRunUrl.equals(rhs.checkRunUrl))))&&((this.steps == rhs.steps)||((this.steps!= null)&&this.steps.equals(rhs.steps))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))))&&((this.labels == rhs.labels)||((this.labels!= null)&&this.labels.equals(rhs.labels))))&&((this.conclusion == rhs.conclusion)||((this.conclusion!= null)&&this.conclusion.equals(rhs.conclusion))))&&((this.runnerGroupId == rhs.runnerGroupId)||((this.runnerGroupId!= null)&&this.runnerGroupId.equals(rhs.runnerGroupId))))&&((this.runnerName == rhs.runnerName)||((this.runnerName!= null)&&this.runnerName.equals(rhs.runnerName))))&&((this.runAttempt == rhs.runAttempt)||((this.runAttempt!= null)&&this.runAttempt.equals(rhs.runAttempt))))&&((this.runnerGroupName == rhs.runnerGroupName)||((this.runnerGroupName!= null)&&this.runnerGroupName.equals(rhs.runnerGroupName))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.runnerId == rhs.runnerId)||((this.runnerId!= null)&&this.runnerId.equals(rhs.runnerId))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.runId == rhs.runId)||((this.runId!= null)&&this.runId.equals(rhs.runId))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.nodeId == rhs.nodeId)||((this.nodeId!= null)&&this.nodeId.equals(rhs.nodeId))))&&((this.status == rhs.status)||((this.status!= null)&&this.status.equals(rhs.status))));
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "name",
        "status",
        "conclusion",
        "number",
        "started_at",
        "completed_at"
    })
    @Generated("jsonschema2pojo")
    public static class Step {

        @JsonProperty("name")
        private String name;
        @JsonProperty("status")
        private String status;
        @JsonProperty("conclusion")
        private String conclusion;
        @JsonProperty("number")
        private Long number;
        @JsonProperty("started_at")
        private String startedAt;
        @JsonProperty("completed_at")
        private String completedAt;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Step() {
        }

        /**
         * 
         * @param conclusion
         * @param number
         * @param completedAt
         * @param name
         * @param startedAt
         * @param status
         */
        public Step(String name, String status, String conclusion, Long number, String startedAt, String completedAt) {
            super();
            this.name = name;
            this.status = status;
            this.conclusion = conclusion;
            this.number = number;
            this.startedAt = startedAt;
            this.completedAt = completedAt;
        }

        @JsonProperty("name")
        public String getName() {
            return name;
        }

        @JsonProperty("name")
        public void setName(String name) {
            this.name = name;
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

        @JsonProperty("number")
        public Long getNumber() {
            return number;
        }

        @JsonProperty("number")
        public void setNumber(Long number) {
            this.number = number;
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
            sb.append(GetWorkflowJobResponse.Step.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("name");
            sb.append('=');
            sb.append(((this.name == null)?"<null>":this.name));
            sb.append(',');
            sb.append("status");
            sb.append('=');
            sb.append(((this.status == null)?"<null>":this.status));
            sb.append(',');
            sb.append("conclusion");
            sb.append('=');
            sb.append(((this.conclusion == null)?"<null>":this.conclusion));
            sb.append(',');
            sb.append("number");
            sb.append('=');
            sb.append(((this.number == null)?"<null>":this.number));
            sb.append(',');
            sb.append("startedAt");
            sb.append('=');
            sb.append(((this.startedAt == null)?"<null>":this.startedAt));
            sb.append(',');
            sb.append("completedAt");
            sb.append('=');
            sb.append(((this.completedAt == null)?"<null>":this.completedAt));
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
            result = ((result* 31)+((this.conclusion == null)? 0 :this.conclusion.hashCode()));
            result = ((result* 31)+((this.number == null)? 0 :this.number.hashCode()));
            result = ((result* 31)+((this.completedAt == null)? 0 :this.completedAt.hashCode()));
            result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
            result = ((result* 31)+((this.startedAt == null)? 0 :this.startedAt.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.status == null)? 0 :this.status.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof GetWorkflowJobResponse.Step) == false) {
                return false;
            }
            GetWorkflowJobResponse.Step rhs = ((GetWorkflowJobResponse.Step) other);
            return ((((((((this.conclusion == rhs.conclusion)||((this.conclusion!= null)&&this.conclusion.equals(rhs.conclusion)))&&((this.number == rhs.number)||((this.number!= null)&&this.number.equals(rhs.number))))&&((this.completedAt == rhs.completedAt)||((this.completedAt!= null)&&this.completedAt.equals(rhs.completedAt))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.startedAt == rhs.startedAt)||((this.startedAt!= null)&&this.startedAt.equals(rhs.startedAt))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.status == rhs.status)||((this.status!= null)&&this.status.equals(rhs.status))));
        }

    }

}
