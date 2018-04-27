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
import b12app.vyom.com.flowit.model.Project;

public class ProjectListAdapter extends BaseAdapter {


    private final LayoutInflater layoutInflater;
    private List<Project.ProjectsBean> project;
    private ListView listView;
    private Context context;

    public ProjectListAdapter(List<Project.ProjectsBean> project, Context context) {
        this.project = project;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return project.size();
    }

    @Override
    public Object getItem(int position) {
        return project.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ProjectListViewHolder projectListViewHolder;

        if (convertView == null) {
            projectListViewHolder = new ProjectListViewHolder();
            convertView = layoutInflater.inflate(R.layout.project_list_item_format, parent, false);
            projectListViewHolder.tvTitle = convertView.findViewById(R.id.tvTitle);
            convertView.setTag(projectListViewHolder);
        } else {

            projectListViewHolder = (ProjectListViewHolder) convertView.getTag();
        }

        projectListViewHolder.tvTitle.setText(project.get(position).getProjectname());

        return convertView;
    }

    private class ProjectListViewHolder {

        TextView tvTitle;

    }
}