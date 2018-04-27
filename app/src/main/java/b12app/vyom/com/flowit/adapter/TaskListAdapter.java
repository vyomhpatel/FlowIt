package b12app.vyom.com.flowit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.model.GeneralTask;
import b12app.vyom.com.flowit.model.Project;

/**
 * Created by jinliyu on 4/27/18.
 */

public class TaskListAdapter extends BaseAdapter {
    private final LayoutInflater layoutInflater;
    private List<GeneralTask.ProjecttaskBean>  tasklist;
    private ListView listView;
    private Context context;

    public TaskListAdapter( List<GeneralTask.ProjecttaskBean> tasks, Context context) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.tasklist = tasks;
        this.context = context;
    }

    @Override
    public int getCount() {
        return tasklist.size();
    }

    @Override
    public Object getItem(int position) {
       return  tasklist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TaskListAdapter.TaskListViewHolder taskListViewHolder;

        if(convertView==null){
            taskListViewHolder = new TaskListViewHolder();
            convertView = layoutInflater.inflate(R.layout.task_list_item_format,parent,false);
            taskListViewHolder.tvTitle = convertView.findViewById(R.id.taskTitle);
            convertView.setTag(taskListViewHolder);
        } else {

            taskListViewHolder = (TaskListViewHolder) convertView.getTag();
        }

        taskListViewHolder.tvTitle.setText(tasklist.get(position).getTaskname());

        return convertView;
    }


    private class TaskListViewHolder{

        TextView tvTitle;

    }
}
