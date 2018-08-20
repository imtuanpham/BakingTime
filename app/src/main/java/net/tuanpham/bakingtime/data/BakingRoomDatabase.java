package net.tuanpham.bakingtime.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import net.tuanpham.bakingtime.data.daos.IngredientDao;
import net.tuanpham.bakingtime.data.daos.RecipeDao;
import net.tuanpham.bakingtime.data.daos.StepDao;
import net.tuanpham.bakingtime.data.entities.Ingredient;
import net.tuanpham.bakingtime.data.entities.Recipe;
import net.tuanpham.bakingtime.data.entities.Step;

@Database(entities = {Recipe.class, Ingredient.class, Step.class}, version = 6, exportSchema = false)
public abstract class BakingRoomDatabase extends RoomDatabase {

    private final static String LOG_TAG = BakingRoomDatabase.class.getSimpleName();

    public abstract RecipeDao recipeDao();

    public abstract IngredientDao ingredientDao();

    public abstract StepDao stepDao();

    private static BakingRoomDatabase INSTANCE;

    private static Callback sRoomDatabaseCallback =
            new Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    Log.d(LOG_TAG, "Opening database...");
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    public static BakingRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (BakingRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            BakingRoomDatabase.class, "baking_database")
                            .addCallback(sRoomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .build();

                }
            }
        }
        return INSTANCE;
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final BakingRoomDatabase mDb;

        PopulateDbAsync(BakingRoomDatabase db) {
            mDb = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            BakingDataSync.sync(mDb);
            return null;
        }
    }


}