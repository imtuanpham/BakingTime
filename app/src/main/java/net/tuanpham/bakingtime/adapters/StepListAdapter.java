package net.tuanpham.bakingtime.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.tuanpham.bakingtime.R;
import net.tuanpham.bakingtime.data.entities.Step;

import java.util.List;

public class StepListAdapter extends RecyclerView.Adapter<StepListAdapter.StepViewHolder> {

    private final String LOG_TAG = StepListAdapter.class.getSimpleName();

    private final OnStepClickHandler mClickHandler;

    public interface OnStepClickHandler {
        public void onStepClick(int stepId);
    }

    class StepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        private final TextView StepItemView;

        private StepViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            StepItemView = itemView.findViewById(R.id.tv_step_short_description);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            Step step = mSteps.get(pos);
            mClickHandler.onStepClick(step.getPid());
        }
    }

    private List<Step> mSteps; // Cached copy of Steps

    public StepListAdapter(OnStepClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }

    @Override
    public StepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.rv_step_list_item, parent, false);
        return new StepViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StepViewHolder holder, int position) {
        if (mSteps != null) {
            Step current = mSteps.get(position);
            holder.StepItemView.setText(current.getShortDescription());
        } else {
            // Covers the case of data not being ready yet.
            holder.StepItemView.setText("No Step");
        }
    }

    public void setSteps(List<Step> Steps){
        mSteps = Steps;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mSteps has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mSteps != null) {
            return mSteps.size();
        }
        else {
            return 0;
        }
    }
}
