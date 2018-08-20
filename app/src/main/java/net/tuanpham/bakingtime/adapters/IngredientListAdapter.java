package net.tuanpham.bakingtime.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.tuanpham.bakingtime.R;
import net.tuanpham.bakingtime.data.entities.Ingredient;

import java.util.List;

public class IngredientListAdapter extends RecyclerView.Adapter<IngredientListAdapter.IngredientViewHolder> {

    private final String LOG_TAG = IngredientListAdapter.class.getSimpleName();

    private final IngredientOnClickHandler mClickHandler;

    public interface IngredientOnClickHandler {
        public void onClickIngredient(int ingredientId);
    }

    class IngredientViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView IngredientItemView;

        private IngredientViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            IngredientItemView = itemView.findViewById(R.id.tv_ingredient);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            Ingredient ingredient = mIngredients.get(pos);
            mClickHandler.onClickIngredient(ingredient.getPid());
        }
    }

    private List<Ingredient> mIngredients; // Cached copy of Ingredients

    public IngredientListAdapter(IngredientOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }

    @Override
    public IngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.rv_ingredient_list_item, parent, false);
        return new IngredientViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(IngredientViewHolder holder, int position) {
        if (mIngredients != null) {
            Ingredient current = mIngredients.get(position);
            holder.IngredientItemView.setText(current.getIngredientDisplay());
        } else {
            // Covers the case of data not being ready yet.
            holder.IngredientItemView.setText("No Ingredient");
        }
    }

    public void setIngredients(List<Ingredient> Ingredients){
        mIngredients = Ingredients;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mIngredients has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mIngredients != null) {
            return mIngredients.size();
        } else {
            return 0;
        }
    }
}
