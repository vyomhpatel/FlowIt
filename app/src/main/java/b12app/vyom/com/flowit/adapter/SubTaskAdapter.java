package b12app.vyom.com.flowit.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.model.GeneralSubTask;
import b12app.vyom.com.utils.StatusHelper;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jinliyu on 4/29/18.
 */

public class SubTaskAdapter extends RecyclerView.Adapter<SubTaskAdapter.myViewHolder> {
    private Context context;
    private List<GeneralSubTask.ProjectsubtaskBean> dataList;
    private SubTaskAdapter.OnItemClickListener myItemClickListener;

    public SubTaskAdapter(Context context, List<GeneralSubTask.ProjectsubtaskBean> subtaskList) {
        this.context = context;
        this.dataList = subtaskList;
    }


    @Override
    public SubTaskAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SubTaskAdapter.myViewHolder mVH;

        if (viewType == -1) {//if empty

            View v = LayoutInflater.from(context).inflate(R.layout.item_empty, parent, false);

            mVH = new SubTaskAdapter.myViewHolder(v);

        } else {

            View v = LayoutInflater.from(context).inflate(R.layout.item_subtask, parent, false);

            mVH = new SubTaskAdapter.myViewHolder(v);
        }

        return mVH;

    }

    @Override
    public void onBindViewHolder( SubTaskAdapter.myViewHolder holder, int position) {
        if(dataList.size() > 0) {
            holder.subtaskname.setText(dataList.get(position).getSubtaskname());
            holder.subtaskstatus.setText("status: "+ StatusHelper.getTaskStatus(Integer.parseInt(dataList.get(position).getSubtaskstatus())));
            holder.subtasktimeline.setText("TimeLine: "+ dataList.get(position).getStartdate()+ " —— "+ dataList.get(position).getEndstart());
            holder.subtaskdesc.setText(dataList.get(position).getSubtaskdesc());

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

    class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.tv_subtask_name)
        TextView subtaskname;

        @BindView(R.id.tv_subtask_status)
        TextView subtaskstatus;

        @BindView(R.id.tv_subtask_timeline)
        TextView subtasktimeline;

        @BindView(R.id.tv_subtask_detail_desc)
        TextView subtaskdesc;

        public myViewHolder(View itemview) {
            super(itemview);
            ButterKnife.bind(this, itemview);

            itemview.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (myItemClickListener != null) {
                myItemClickListener.onItemClick(v, (Integer) v.getTag());
            }
        }
    }



    public void setMItemClickListener(SubTaskAdapter.OnItemClickListener onItemClickListener) {
        this.myItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

}