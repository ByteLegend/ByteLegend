
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
    "data",
    "errors"
})
@Generated("jsonschema2pojo")
public class MergePullRequestErrorResponse {

    @JsonProperty("data")
    private MergePullRequestErrorResponse.Data data;
    @JsonProperty("errors")
    private List<MergePullRequestErrorResponse.Error> errors = new ArrayList<MergePullRequestErrorResponse.Error>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public MergePullRequestErrorResponse() {
    }

    /**
     * 
     * @param data
     * @param errors
     */
    public MergePullRequestErrorResponse(MergePullRequestErrorResponse.Data data, List<MergePullRequestErrorResponse.Error> errors) {
        super();
        this.data = data;
        this.errors = errors;
    }

    @JsonProperty("data")
    public MergePullRequestErrorResponse.Data getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(MergePullRequestErrorResponse.Data data) {
        this.data = data;
    }

    @JsonProperty("errors")
    public List<MergePullRequestErrorResponse.Error> getErrors() {
        return errors;
    }

    @JsonProperty("errors")
    public void setErrors(List<MergePullRequestErrorResponse.Error> errors) {
        this.errors = errors;
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
        sb.append(MergePullRequestErrorResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("data");
        sb.append('=');
        sb.append(((this.data == null)?"<null>":this.data));
        sb.append(',');
        sb.append("errors");
        sb.append('=');
        sb.append(((this.errors == null)?"<null>":this.errors));
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
        result = ((result* 31)+((this.data == null)? 0 :this.data.hashCode()));
        result = ((result* 31)+((this.errors == null)? 0 :this.errors.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MergePullRequestErrorResponse) == false) {
            return false;
        }
        MergePullRequestErrorResponse rhs = ((MergePullRequestErrorResponse) other);
        return ((((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties)))&&((this.data == rhs.data)||((this.data!= null)&&this.data.equals(rhs.data))))&&((this.errors == rhs.errors)||((this.errors!= null)&&this.errors.equals(rhs.errors))));
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "mergePullRequest"
    })
    @Generated("jsonschema2pojo")
    public static class Data {

        @JsonProperty("mergePullRequest")
        private Object mergePullRequest;
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
        public Data(Object mergePullRequest) {
            super();
            this.mergePullRequest = mergePullRequest;
        }

        @JsonProperty("mergePullRequest")
        public Object getMergePullRequest() {
            return mergePullRequest;
        }

        @JsonProperty("mergePullRequest")
        public void setMergePullRequest(Object mergePullRequest) {
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
            sb.append(MergePullRequestErrorResponse.Data.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
            if ((other instanceof MergePullRequestErrorResponse.Data) == false) {
                return false;
            }
            MergePullRequestErrorResponse.Data rhs = ((MergePullRequestErrorResponse.Data) other);
            return (((this.mergePullRequest == rhs.mergePullRequest)||((this.mergePullRequest!= null)&&this.mergePullRequest.equals(rhs.mergePullRequest)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "type",
        "path",
        "locations",
        "message"
    })
    @Generated("jsonschema2pojo")
    public static class Error {

        @JsonProperty("type")
        private String type;
        @JsonProperty("path")
        private List<String> path = new ArrayList<String>();
        @JsonProperty("locations")
        private List<MergePullRequestErrorResponse.Location> locations = new ArrayList<MergePullRequestErrorResponse.Location>();
        @JsonProperty("message")
        private String message;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Error() {
        }

        /**
         * 
         * @param path
         * @param locations
         * @param type
         * @param message
         */
        public Error(String type, List<String> path, List<MergePullRequestErrorResponse.Location> locations, String message) {
            super();
            this.type = type;
            this.path = path;
            this.locations = locations;
            this.message = message;
        }

        @JsonProperty("type")
        public String getType() {
            return type;
        }

        @JsonProperty("type")
        public void setType(String type) {
            this.type = type;
        }

        @JsonProperty("path")
        public List<String> getPath() {
            return path;
        }

        @JsonProperty("path")
        public void setPath(List<String> path) {
            this.path = path;
        }

        @JsonProperty("locations")
        public List<MergePullRequestErrorResponse.Location> getLocations() {
            return locations;
        }

        @JsonProperty("locations")
        public void setLocations(List<MergePullRequestErrorResponse.Location> locations) {
            this.locations = locations;
        }

        @JsonProperty("message")
        public String getMessage() {
            return message;
        }

        @JsonProperty("message")
        public void setMessage(String message) {
            this.message = message;
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
            sb.append(MergePullRequestErrorResponse.Error.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("type");
            sb.append('=');
            sb.append(((this.type == null)?"<null>":this.type));
            sb.append(',');
            sb.append("path");
            sb.append('=');
            sb.append(((this.path == null)?"<null>":this.path));
            sb.append(',');
            sb.append("locations");
            sb.append('=');
            sb.append(((this.locations == null)?"<null>":this.locations));
            sb.append(',');
            sb.append("message");
            sb.append('=');
            sb.append(((this.message == null)?"<null>":this.message));
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
            result = ((result* 31)+((this.path == null)? 0 :this.path.hashCode()));
            result = ((result* 31)+((this.locations == null)? 0 :this.locations.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
            result = ((result* 31)+((this.message == null)? 0 :this.message.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof MergePullRequestErrorResponse.Error) == false) {
                return false;
            }
            MergePullRequestErrorResponse.Error rhs = ((MergePullRequestErrorResponse.Error) other);
            return ((((((this.path == rhs.path)||((this.path!= null)&&this.path.equals(rhs.path)))&&((this.locations == rhs.locations)||((this.locations!= null)&&this.locations.equals(rhs.locations))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.message == rhs.message)||((this.message!= null)&&this.message.equals(rhs.message))));
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "line",
        "column"
    })
    @Generated("jsonschema2pojo")
    public static class Location {

        @JsonProperty("line")
        private Long line;
        @JsonProperty("column")
        private Long column;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * No args constructor for use in serialization
         * 
         */
        public Location() {
        }

        /**
         * 
         * @param line
         * @param column
         */
        public Location(Long line, Long column) {
            super();
            this.line = line;
            this.column = column;
        }

        @JsonProperty("line")
        public Long getLine() {
            return line;
        }

        @JsonProperty("line")
        public void setLine(Long line) {
            this.line = line;
        }

        @JsonProperty("column")
        public Long getColumn() {
            return column;
        }

        @JsonProperty("column")
        public void setColumn(Long column) {
            this.column = column;
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
            sb.append(MergePullRequestErrorResponse.Location.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("line");
            sb.append('=');
            sb.append(((this.line == null)?"<null>":this.line));
            sb.append(',');
            sb.append("column");
            sb.append('=');
            sb.append(((this.column == null)?"<null>":this.column));
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
            result = ((result* 31)+((this.column == null)? 0 :this.column.hashCode()));
            result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
            result = ((result* 31)+((this.line == null)? 0 :this.line.hashCode()));
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof MergePullRequestErrorResponse.Location) == false) {
                return false;
            }
            MergePullRequestErrorResponse.Location rhs = ((MergePullRequestErrorResponse.Location) other);
            return ((((this.column == rhs.column)||((this.column!= null)&&this.column.equals(rhs.column)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.line == rhs.line)||((this.line!= null)&&this.line.equals(rhs.line))));
        }

    }

}
