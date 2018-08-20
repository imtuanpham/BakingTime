/*
    @author: Tuan Pham
    @since: 2018-08-16 20:58:41
 */

package net.tuanpham.bakingtime.data.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "ingredient",
        foreignKeys = @ForeignKey(  entity = Recipe.class,
                                    parentColumns = "recipe_id",
                                    childColumns = "recipe_id",
                                    onDelete = CASCADE),
        indices = {
                @Index(name = "idx_ingredient_recipe_id", value = {"recipe_id"})
        }
)
public class Ingredient {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "pid")
    private int pid;

    @NonNull
    @ColumnInfo(name = "recipe_id")
    private int recipeId;

    @ColumnInfo(name = "quantity")
    private double quantity;

    @ColumnInfo(name = "measure")
    private String measure;

    @ColumnInfo(name = "ingredient")
    private String ingredient;

    public Ingredient(int recipeId, double quantity, String measure, String ingredient) {
        this.recipeId = recipeId;
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    public void setPid(@NonNull int pid) {
        this.pid = pid;
    }

    @NonNull
    public int getPid() {
        return pid;
    }

    public int getRecipeId(){
        return this.recipeId;
    }

    public double getQuantity(){
        return this.quantity;
    }

    public String getMeasure(){
        return this.measure;
    }

    public String getIngredient(){
        return this.ingredient;
    }

    public String getIngredientDisplay() {
        String ingredientDisp = this.ingredient + " - " + this.getQuantity() + " " + this.getMeasure();
        return ingredientDisp;
    }
}
