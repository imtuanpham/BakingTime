package net.tuanpham.bakingtime.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.tuanpham.bakingtime.R;
import net.tuanpham.bakingtime.data.entities.Recipe;

import java.util.List;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder> {

    private final String LOG_TAG = RecipeListAdapter.class.getSimpleName();

    private final OnRecipeClickHandler mClickHandler;

    public interface OnRecipeClickHandler {
        public void onRecipeClick(Recipe recipe);
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView RecipeItemView;

        private RecipeViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            RecipeItemView = itemView.findViewById(R.id.tv_recipe_name);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            Recipe recipe = mRecipes.get(pos);
            mClickHandler.onRecipeClick(recipe);
        }
    }

    private List<Recipe> mRecipes; // Cached copy of Recipes

    public RecipeListAdapter(OnRecipeClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.rv_recipe_list_item, parent, false);
        return new RecipeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        if (mRecipes != null) {
            Recipe current = mRecipes.get(position);
            holder.RecipeItemView.setText(current.getName());
        } else {
            // Covers the case of data not being ready yet.
            holder.RecipeItemView.setText("No Recipe");
        }
    }

    public void setRecipes(List<Recipe> Recipes){
        mRecipes = Recipes;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mRecipes has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mRecipes != null) {
            return mRecipes.size();
        } else {
            return 0;
        }
    }
}
