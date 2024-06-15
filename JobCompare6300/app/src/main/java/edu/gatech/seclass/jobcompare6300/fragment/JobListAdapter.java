package edu.gatech.seclass.jobcompare6300.fragment;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.jobcompare6300.R;
import edu.gatech.seclass.jobcompare6300.job.Job;

public class JobListAdapter extends RecyclerView.Adapter<JobListAdapter.JobViewHolder> {


    private final LayoutInflater mInflater;
    private ArrayList<Job> mJobs;

    JobListAdapter(Context context, ArrayList<Job> jobs) {
        mInflater = LayoutInflater.from(context);
        mJobs = jobs;
    }

    @Override
    public JobViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item, parent, false);
        return new JobViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(JobViewHolder holder, int position) {
        Job current = mJobs.get(position);


// this block controls what output wants to be shown.
        if(current.isCurrentJob()){
            holder.score.setText(current.getScore().toString());
            holder.jobItemView.setText(current.getTitle() + ",  " + current.getCompany());
            holder.currentIndicator.setText("current");
            holder.currentIndicator.setTextColor(Color.BLUE);
        }else{
            holder.score.setText(current.getScore().toString());
            holder.jobItemView.setText(current.getTitle() + ",  " + current.getCompany());
        }

        holder.checkBox.setOnCheckedChangeListener(null);
        holder.checkBox.setSelected(false);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                current.setSelected(isChecked);

            }
        });
    }

    List<Job> getJobs() {
        return mJobs;
    }

    @Override
    public int getItemCount() {
        if (mJobs != null)
            return mJobs.size();
        else return 0;
    }

    class JobViewHolder extends RecyclerView.ViewHolder {
        private final TextView jobItemView, currentIndicator, score;
        private CheckBox checkBox;

        private JobViewHolder(View itemView) {
            super(itemView);
            score = itemView.findViewById(R.id.score);
            jobItemView = itemView.findViewById(R.id.item);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
            currentIndicator = itemView.findViewById(R.id.current_indicator);
        }

    }



}
