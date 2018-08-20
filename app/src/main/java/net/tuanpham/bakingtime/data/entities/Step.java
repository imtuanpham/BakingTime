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

@Entity(tableName = "step",
        foreignKeys = @ForeignKey(  entity = Recipe.class,
                parentColumns = "recipe_id",
                childColumns = "recipe_id",
                onDelete = CASCADE),
        indices = {
                @Index(name = "idx_step_recipe_id", value = {"recipe_id"})
        })
public class Step {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "pid")
    private int pid;

    @NonNull
    @ColumnInfo(name = "recipe_id")
    private int recipeId;

    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "short_description")
    private String shortDescription;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "video_url")
    private String videoURL;

    @ColumnInfo(name = "thumbnail_url")
    private String thumbnailURL;

    public Step(int recipeId, int id, String shortDescription, String description, String videoURL, String thumbnailURL) {
        this.recipeId = recipeId;
        this.id = id;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
    }

    public void setPid(@NonNull int pid) {
        this.pid = pid;
    }

    @NonNull
    public int getPid() {
        return pid;
    }

    public int getId() {
        return id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public int getRecipeId(){
        return this.recipeId;
    }
}

