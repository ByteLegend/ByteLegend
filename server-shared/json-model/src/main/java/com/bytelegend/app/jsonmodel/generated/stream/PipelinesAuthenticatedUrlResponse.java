
package com.bytelegend.app.jsonmodel.generated.stream;

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
    "logStreamWebSocketUrl"
})
@Generated("jsonschema2pojo")
public class PipelinesAuthenticatedUrlResponse {

    @JsonProperty("logStreamWebSocketUrl")
    private String logStreamWebSocketUrl;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public PipelinesAuthenticatedUrlResponse() {
    }

    /**
     * 
     * @param logStreamWebSocketUrl
     */
    public PipelinesAuthenticatedUrlResponse(String logStreamWebSocketUrl) {
        super();
        this.logStreamWebSocketUrl = logStreamWebSocketUrl;
    }

    @JsonProperty("logStreamWebSocketUrl")
    public String getLogStreamWebSocketUrl() {
        return logStreamWebSocketUrl;
    }

    @JsonProperty("logStreamWebSocketUrl")
    public void setLogStreamWebSocketUrl(String logStreamWebSocketUrl) {
        this.logStreamWebSocketUrl = logStreamWebSocketUrl;
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
        sb.append(PipelinesAuthenticatedUrlResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("logStreamWebSocketUrl");
        sb.append('=');
        sb.append(((this.logStreamWebSocketUrl == null)?"<null>":this.logStreamWebSocketUrl));
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
        result = ((result* 31)+((this.logStreamWebSocketUrl == null)? 0 :this.logStreamWebSocketUrl.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PipelinesAuthenticatedUrlResponse) == false) {
            return false;
        }
        PipelinesAuthenticatedUrlResponse rhs = ((PipelinesAuthenticatedUrlResponse) other);
        return (((this.logStreamWebSocketUrl == rhs.logStreamWebSocketUrl)||((this.logStreamWebSocketUrl!= null)&&this.logStreamWebSocketUrl.equals(rhs.logStreamWebSocketUrl)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}
