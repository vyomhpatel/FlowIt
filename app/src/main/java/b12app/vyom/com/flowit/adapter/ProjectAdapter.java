package b12app.vyom.com.flowit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import b12app.vyom.com.flowit.R;

import b12app.vyom.com.flowit.model.Project;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Package b12app.vyom.com.flowit.adapter
 * @FileName ProjectAdapter
 * @Date 4/26/18, 12:39 AM
 * @Author Created by fengchengding
 * @Description FlowIt
 */

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.mViewHolder> {

    private Context context;

    private List<Project.ProjectsBean> dataList;

    private ProjectAdapter.OnItemClickListener mItemClickListener;


    public ProjectAdapter(Context context, Project project) {
        this.context = context;
        this.dataList = project.getProjects();
    }


    @Override
    public ProjectAdapter.mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mViewHolder mVH;

        if (viewType == -1) {//if empty

            View v = LayoutInflater.from(context).inflate(R.layout.item_empty, parent, false);

            mVH = new mViewHolder(v);

        } else {

            View v = LayoutInflater.from(context).inflate(R.layout.item_project, parent, false);

            mVH = new mViewHolder(v);
        }

        return mVH;
    }

    @Override
    public void onBindViewHolder(ProjectAdapter.mViewHolder holder, int position) {
        if (dataList.size() > 0) {

            holder.nameTv.setText(dataList.get(position).getProjectname());
            holder.endTv.setText(dataList.get(position).getEndstart());

            Calendar start = Calendar.getInstance();
            Calendar end = Calendar.getInstance();
            Calendar today = Calendar.getInstance();

            try {
                start.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(dataList.get(position).getStartdate()));
                end.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(dataList.get(position).getEndstart()));

                if (end.after(start)) {
                    long total_difference = end.getTimeInMillis() - start.getTimeInMillis();
                    long total_days = total_difference / (24 * 60 * 60 * 1000);
                    long current_difference = end.getTimeInMillis() - today.getTimeInMillis();
                    long remaining_days = current_difference / (24 * 60 * 60 * 1000);

                    long past_days = total_days - remaining_days;

                    if (today.after(end)) {

                        holder.startTv.setText(dataList.get(position).getStartdate());
                        holder.projectPgs.setProgress(100);

                    } else if (today.before(start)) {
                        Log.i("current difference", "remaining days: " + remaining_days);
                        holder.startTv.setText(dataList.get(position).getStartdate());
                        holder.projectPgs.setProgress(0);

                    } else if (today.before(end) && today.after(start)) {
                        int pgs = (int) ((past_days * 100) / total_days);

                        holder.startTv.setText(dataList.get(position).getStartdate());
                        holder.projectPgs.setProgress(pgs);

                    }
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }

            holder.itemView.setTag(position);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size() > 0 ? dataList.size() : 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (dataList.size() <= 0) {

            return -1;
        }
        return super.getItemViewType(position);
    }


    class mViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_project_name)
        TextView nameTv;

        @BindView(R.id.pgs_project)
        ProgressBar projectPgs;

        @BindView(R.id.tv_endDate)
        TextView endTv;

        @BindView(R.id.tv_startDate)
        TextView startTv;

        public mViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, (Integer) v.getTag());
            }
        }
    }

    public void setMItemClickListener(ProjectAdapter.OnItemClickListener onItemClickListener) {
        this.mItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }
}



