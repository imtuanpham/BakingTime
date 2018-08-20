/*
    @author: Tuan Pham
    @since: 2018-08-05 15:45:27

    Generated from http://www.jsonschema2pojo.org/
 */
package net.tuanpham.bakingtime.models;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "shortDescription",
    "description",
    "videoURL",
    "thumbnailURL"
})
public class StepModel {

    @JsonProperty("id")
    private int id;
    @JsonProperty("shortDescription")
    private String shortDescription;
    @JsonProperty("description")
    private String description;
    @JsonProperty("videoURL")
    private String videoURL;
    @JsonProperty("thumbnailURL")
    private String thumbnailURL;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public StepModel() {
    }

    /**
     * 
     * @param id
     * @param shortDescription
     * @param description
     * @param videoURL
     * @param thumbnailURL
     */
    public StepModel(int id, String shortDescription, String description, String videoURL, String thumbnailURL) {
        super();
        this.id = id;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("shortDescription")
    public String getShortDescription() {
        return shortDescription;
    }

    @JsonProperty("shortDescription")
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("videoURL")
    public String getVideoURL() {
        return videoURL;
    }

    @JsonProperty("videoURL")
    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    @JsonProperty("thumbnailURL")
    public String getThumbnailURL() {
        return thumbnailURL;
    }

    @JsonProperty("thumbnailURL")
    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
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
        return new ToStringBuilder(this).append("id", id).append("shortDescription", shortDescription).append("description", description).append("videoURL", videoURL).append("thumbnailURL", thumbnailURL).append("additionalProperties", additionalProperties).toString();
    }

}
