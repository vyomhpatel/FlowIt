package b12app.vyom.com.flowit.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.model.GeneralTask;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Package b12app.vyom.com.flowit.adapter
 * @FileName ProjectAdapter
 * @Date 4/26/18, 12:39 AM
 * @Author Created by fengchengding
 * @Description FlowIt
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.mViewHolder> {

    private Context context;
    private List<GeneralTask.ProjecttaskBean> dataList;
    private TaskAdapter.OnItemClickListener onItemClickListener;

    public TaskAdapter(Context context, List<GeneralTask.ProjecttaskBean> generalTask) {
        this.context = context;
        this.dataList = generalTask;
    }


    @Override
    public TaskAdapter.mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mViewHolder mVH;

        if (viewType == -1) {//if empty

            View v = LayoutInflater.from(context).inflate(R.layout.item_empty, parent, false);

            mVH = new mViewHolder(v);

        } else {

            View v = LayoutInflater.from(context).inflate(R.layout.item_task, parent, false);

            mVH = new mViewHolder(v);
        }

        return mVH;
    }

    @Override
    public void onBindViewHolder(TaskAdapter.mViewHolder holder, int position) {
        if (dataList.size() > 0) {

            holder.nameTv.setText(dataList.get(position).getTaskname());
            holder.timeTv.setText(dataList.get(position).getEndstart());
            holder.itemView.setTag(position);
            switch (dataList.get(position).getTaskstatus()){
                case "1":
                    holder.statusTv.setText("Initial");
//                    holder.statusTv.setCompoundDrawablesRelative(context.getResources().getDrawable(R.drawable.initial),null,null,null);
                    break;
                case "2":
                    holder.statusTv.setText("In Progress");
//                    holder.statusTv.setCompoundDrawablesRelative(context.getResources().getDrawable(R.drawable.in_progress),null,null,null);
                    break;
                case "3":
//                    holder.statusTv.setCompoundDrawablesRelative(context.getResources().getDrawable(R.drawable.task_complete),null,null,null);
                    holder.statusTv.setText("Complete");
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return (dataList != null && dataList.size() > 0) ? dataList.size() : 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (dataList.size() <= 0) {

            return -1;

        }

        return super.getItemViewType(position);
    }

    class mViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_task_name)
        TextView nameTv;


        @BindView(R.id.tv_task_timeline)
        TextView timeTv;

        @BindView(R.id.tv_task_status_task_list)
        TextView statusTv;

        public mViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if(onItemClickListener!=null){

                onItemClickListener.onItemClick(v, (Integer) v.getTag());
            }
        }
    }

    public void setOnTaskItemClickListener(TaskAdapter.OnItemClickListener mOnItemClickListener){
        onItemClickListener = mOnItemClickListener;
    }

    public interface OnItemClickListener{

        void onItemClick(View v,int position);
    }
}
