package net.tuanpham.bakingtime.data.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "recipe")
public class Recipe {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "recipe_id")
    private int recipeId;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "servings")
    private int servings;

    @ColumnInfo(name = "image")
    private String image;

    public Recipe(int recipeId, String name, int servings, String image) {
        this.recipeId = recipeId;
        this.name = name;
        this.servings = servings;
        this.image = image;
    }

    public int getRecipeId(){
        return this.recipeId;
    }

    public String getName(){
        return this.name;
    }

    public int getServings(){
        return this.servings;
    }

    public String getImage(){
        return this.image;
    }
}