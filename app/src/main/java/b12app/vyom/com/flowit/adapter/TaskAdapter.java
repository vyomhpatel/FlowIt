package b12app.vyom.com.flowit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import b12app.vyom.com.flowit.R;
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
    private List<String> dataList;

    public TaskAdapter(Context context, List<String> dataList) {
        this.context = context;
        this.dataList = dataList;
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

            holder.nameTv.setText(dataList.get(position));
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

    class mViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_task_name)
        TextView nameTv;


        @BindView(R.id.tv_task_timeline)
        TextView timeTv;

        public mViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
