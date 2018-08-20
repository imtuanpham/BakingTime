/*
    @author: Tuan Pham
    @since: 2018-08-05 15:45:27

    Generated from http://www.jsonschema2pojo.org/
 */

package net.tuanpham.bakingtime.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    "name",
    "ingredients",
    "steps",
    "servings",
    "image"
})
public class RecipeModel {

    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("ingredients")
    private List<IngredientModel> ingredientModels = new ArrayList<IngredientModel>();
    @JsonProperty("steps")
    private List<StepModel> stepModels = new ArrayList<StepModel>();
    @JsonProperty("servings")
    private int servings;
    @JsonProperty("image")
    private String image;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public RecipeModel() {
    }

    /**
     * 
     * @param ingredientModels
     * @param id
     * @param servings
     * @param name
     * @param image
     * @param stepModels
     */
    public RecipeModel(int id, String name, List<IngredientModel> ingredientModels, List<StepModel> stepModels, int servings, String image) {
        super();
        this.id = id;
        this.name = name;
        this.ingredientModels = ingredientModels;
        this.stepModels = stepModels;
        this.servings = servings;
        this.image = image;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("ingredients")
    public List<IngredientModel> getIngredientModels() {
        return ingredientModels;
    }

    @JsonProperty("ingredients")
    public void setIngredientModels(List<IngredientModel> ingredientModels) {
        this.ingredientModels = ingredientModels;
    }

    @JsonProperty("steps")
    public List<StepModel> getStepModels() {
        return stepModels;
    }

    @JsonProperty("steps")
    public void setStepModels(List<StepModel> stepModels) {
        this.stepModels = stepModels;
    }

    @JsonProperty("servings")
    public int getServings() {
        return servings;
    }

    @JsonProperty("servings")
    public void setServings(int servings) {
        this.servings = servings;
    }

    @JsonProperty("image")
    public String getImage() {
        return image;
    }

    @JsonProperty("image")
    public void setImage(String image) {
        this.image = image;
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
        return new ToStringBuilder(this).append("id", id).append("name", name).append("ingredients", ingredientModels).append("steps", stepModels).append("servings", servings).append("image", image).append("additionalProperties", additionalProperties).toString();
    }

}
