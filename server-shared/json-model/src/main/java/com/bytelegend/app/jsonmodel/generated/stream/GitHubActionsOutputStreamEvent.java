
package com.bytelegend.app.jsonmodel.generated.stream;

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
    "type",
    "target",
    "arguments"
})
@Generated("jsonschema2pojo")
public class GitHubActionsOutputStreamEvent {

    @JsonProperty("type")
    private Long type;
    @JsonProperty("target")
    private String target;
    @JsonProperty("arguments")
    private List<GitHubActionsOutputStreamEvent.Argument> arguments = new ArrayList<GitHubActionsOutputStreamEvent.Argument>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public GitHubActionsOutputStreamEvent() {
    }

    /**
     * 
     * @param arguments
     * @param type
     * @param target
     */
    public GitHubActionsOutputStreamEvent(Long type, String target, List<GitHubActionsOutputStreamEvent.Argument> arguments) {
        super();
        this.type = type;
        this.target = target;
        this.arguments = arguments;
    }

    @JsonProperty("type")
    public Long getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(Long type) {
        this.type = type;
    }

    @JsonProperty("target")
    public String getTarget() {
        return target;
    }

    @JsonProperty("target")
    public void setTarget(String target) {
        this.target = target;
    }

    @JsonProperty("arguments")
    public List<GitHubActionsOutputStreamEvent.Argument> getArguments() {
        return arguments;
    }

    @JsonProperty("arguments")
    public void setArguments(List<GitHubActionsOutputStreamEvent.Argument> arguments) {
        this.arguments = arguments;
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
        sb.append(GitHubActionsOutputStreamEvent.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("target");
        sb.append('=');
        sb.append(((this.target == null)?"<null>":this.target));
        sb.append(',');
        sb.append("arguments");
        sb.append('=');
        sb.append(((this.arguments == null)?"<null>":this.arguments));
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
        result = ((result* 31)+((this.arguments == null)? 0 :this.arguments.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
        result = ((result* 31)+((this.target == null)? 0 :this.target.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof GitHubActionsOutputStreamEvent) == false) {
            return false;
        }
        GitHubActionsOutputStreamEvent rhs = ((GitHubActionsOutputStreamEvent) other);
        return (((((this.arguments == rhs.arguments)||((this.arguments!= null)&&this.arguments.equals(rhs.arguments)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.target == rhs.target)||((this.target!= null)&&this.target.equals(rhs.target))));
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "runId",
        "timelineId",
        "timelineRecordId",
        "stepRecordId",
        "startLine",
        "lines"
    })
    @Generated("jsonschema2pojo")
    public static class Argument {

        @JsonProperty("runId")
        private Long runId;
        @JsonProperty("timelineId")
        private String timelineId;
        @JsonProperty("timelineRecordId")
        private String timelineRecordId;
        @JsonProperty("stepRecordId")
        private String stepRecordId;
        @JsonProperty("startLine")
        private Long startLine;
        @JsonProperty("lines")
        private List<String> lines = new ArrayList<String>();
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Argument() {
        }

        /**
         * 
         * @param timelineRecordId
         * @param stepRecordId
         * @param startLine
         * @param timelineId
         * @param runId
         * @param lines
         */
        public Argument(Long runId, String timelineId, String timelineRecordId, String stepRecordId, Long startLine, List<String> lines) {
            super();
            this.runId = runId;
            this.timelineId = timelineId;
            this.timelineRecordId = timelineRecordId;
            this.stepRecordId = stepRecordId;
            this.startLine = startLine;
            this.lines = lines;
        }

        @JsonProperty("runId")
        public Long getRunId() {
            return runId;
        }

        @JsonProperty("runId")
        public void setRunId(Long runId) {
            this.runId = runId;
        }

        @JsonProperty("timelineId")
        public String getTimelineId() {
            return timelineId;
        }

        @JsonProperty("timelineId")
        public void setTimelineId(String timelineId) {
            this.timelineId = timelineId;
        }

        @JsonProperty("timelineRecordId")
        public String getTimelineRecordId() {
            return timelineRecordId;
        }

        @JsonProperty("timelineRecordId")
        public void setTimelineRecordId(String timelineRecordId) {
            this.timelineRecordId = timelineRecordId;
        }

        @JsonProperty("stepRecordId")
        public String getStepRecordId() {
            return stepRecordId;
        }

        @JsonProperty("stepRecordId")
        public void setStepRecordId(String stepRecordId) {
            this.stepRecordId = stepRecordId;
        }

        @JsonProperty("startLine")
        public Long getStartLine() {
            return startLine;
        }

        @JsonProperty("startLine")
        public void setStartLine(Long startLine) {
            this.startLine = startLine;
        }

        @JsonProperty("lines")
        public List<String> getLines() {
            return lines;
        }

        @JsonProperty("lines")
        public void setLines(List<String> lines) {
            this.lines = lines;
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
            sb.append(GitHubActionsOutputStreamEvent.Argument.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("runId");
            sb.append('=');
            sb.append(((this.runId == null)?"<null>":this.runId));
            sb.append(',');
            sb.append("timelineId");
            sb.append('=');
            sb.append(((this.timelineId == null)?"<null>":this.timelineId));
            sb.append(',');
            sb.append("timelineRecordId");
            sb.append('=');
            sb.append(((this.timelineRecordId == null)?"<null>":this.timelineRecordId));
            sb.append(',');
            sb.append("stepRecordId");
            sb.append('=');
            sb.append(((this.stepRecordId == null)?"<null>":this.stepRecordId));
            sb.append(',');
            sb.append("startLine");
            sb.append('=');
            sb.append(((this.startLine == null)?"<null>":this.startLine));
            sb.append(',');
            sb.append("lines");
            sb.append('=');
            sb.append(((this.lines == null)?"<null>":this.lines));
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
            result = ((result* 31)+((this.timelineRecordId == null)? 0 :this.timelineRecordId.hashCode()));
            result = ((result* 31)+((this.stepRecordId == null)? 0 :this.stepRecordId.hashCode()));
            result = ((result* 31)+((this.startLine == null)? 0 :this.startLine.hashCode()));
            result = ((result* 31)+((this.timelineId == null)? 0 :this.timelineId.hashCode()));
            result = ((result* 31)+((this.runId == null)? 0 :this.runId.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.lines == null)? 0 :this.lines.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof GitHubActionsOutputStreamEvent.Argument) == false) {
                return false;
            }
            GitHubActionsOutputStreamEvent.Argument rhs = ((GitHubActionsOutputStreamEvent.Argument) other);
            return ((((((((this.timelineRecordId == rhs.timelineRecordId)||((this.timelineRecordId!= null)&&this.timelineRecordId.equals(rhs.timelineRecordId)))&&((this.stepRecordId == rhs.stepRecordId)||((this.stepRecordId!= null)&&this.stepRecordId.equals(rhs.stepRecordId))))&&((this.startLine == rhs.startLine)||((this.startLine!= null)&&this.startLine.equals(rhs.startLine))))&&((this.timelineId == rhs.timelineId)||((this.timelineId!= null)&&this.timelineId.equals(rhs.timelineId))))&&((this.runId == rhs.runId)||((this.runId!= null)&&this.runId.equals(rhs.runId))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.lines == rhs.lines)||((this.lines!= null)&&this.lines.equals(rhs.lines))));
        }

    }

}
