package net.tuanpham.bakingtime.data;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.tuanpham.bakingtime.data.daos.RecipeDao;
import net.tuanpham.bakingtime.data.daos.IngredientDao;
import net.tuanpham.bakingtime.data.daos.StepDao;
import net.tuanpham.bakingtime.data.entities.Recipe;
import net.tuanpham.bakingtime.data.entities.Ingredient;
import net.tuanpham.bakingtime.data.entities.Step;
import net.tuanpham.bakingtime.models.IngredientModel;
import net.tuanpham.bakingtime.models.RecipeModel;
import net.tuanpham.bakingtime.models.StepModel;
import net.tuanpham.bakingtime.utils.NetworkUtils;

import java.net.URL;

public class BakingDataSync {

    private final static String LOG_TAG = BakingDataSync.class.getSimpleName();

    // IN DEBUG MODE, BAKING DATA ARE LOADED EVERY TIME THE APP IS OPEN
    private final static boolean DEBUG = false;

    public static void sync(BakingRoomDatabase db) {

        RecipeDao recipeDao;
        recipeDao = db.recipeDao();
        int recipeCount = recipeDao.getRecipeCount();
        if(!DEBUG &&  recipeCount != 0)
            return;

        IngredientDao ingredientDao = db.ingredientDao();
        StepDao stepDao = db.stepDao();

        Log.d(LOG_TAG, "Deleting Recipes...");
        recipeDao.deleteAll();

        Log.d(LOG_TAG, "Syncing Baking Data...");
        URL requestUrl = NetworkUtils.buildListUrl();
        try {
            String jsonResponse = NetworkUtils.getResponseFromHttpUrl(requestUrl);

            ObjectMapper objectMapper = new ObjectMapper();
            RecipeModel[] recipeModels = objectMapper.readValue(jsonResponse, RecipeModel[].class);

//            List<RecipeModel> recipeModels = objectMapper.readValue(jsonResponse, new TypeReference<List<RecipeModel>>(){});
            Recipe recipe; int recipeId;
            Ingredient ingredient; Step step;
            for(RecipeModel recipeModel : recipeModels) {
                recipeId = recipeModel.getId();
                Log.d(LOG_TAG, "Inserting recipe..." + String.valueOf(recipeId));

                recipe = new Recipe(recipeId, recipeModel.getName(),
                        recipeModel.getServings(), recipeModel.getImage());
                recipeDao.insert(recipe);

                for(IngredientModel ingredientModel : recipeModel.getIngredientModels()) {
                    ingredient = new Ingredient(recipeId, ingredientModel.getQuantity(),
                            ingredientModel.getMeasure(), ingredientModel.getIngredient());
                    ingredientDao.insert(ingredient);
                }

                for(StepModel stepModel : recipeModel.getStepModels()) {
                    step = new Step(recipeId, stepModel.getId(), stepModel.getShortDescription(),
                            stepModel.getDescription(), stepModel.getVideoURL(), stepModel.getThumbnailURL());
                    stepDao.insert(step);
                }

//                Log.d(LOG_TAG, recipeModel.toString());
            }

            /* If the code reaches this point, we have successfully performed our sync */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
