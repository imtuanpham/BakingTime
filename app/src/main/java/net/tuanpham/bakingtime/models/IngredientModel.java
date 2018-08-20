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
    "quantity",
    "measure",
    "ingredient"
})
public class IngredientModel {

    @JsonProperty("quantity")
    private double quantity;
    @JsonProperty("measure")
    private String measure;
    @JsonProperty("ingredient")
    private String ingredient;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public IngredientModel() {
    }

    /**
     * 
     * @param measure
     * @param ingredient
     * @param quantity
     */
    public IngredientModel(double quantity, String measure, String ingredient) {
        super();
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    @JsonProperty("quantity")
    public double getQuantity() {
        return quantity;
    }

    @JsonProperty("quantity")
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @JsonProperty("measure")
    public String getMeasure() {
        return measure;
    }

    @JsonProperty("measure")
    public void setMeasure(String measure) {
        this.measure = measure;
    }

    @JsonProperty("ingredient")
    public String getIngredient() {
        return ingredient;
    }

    @JsonProperty("ingredient")
    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
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
        return new ToStringBuilder(this).append("quantity", quantity).append("measure", measure).append("ingredient", ingredient).append("additionalProperties", additionalProperties).toString();
    }

}
