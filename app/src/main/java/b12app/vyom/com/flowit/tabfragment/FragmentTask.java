package b12app.vyom.com.flowit.tabfragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.adapter.TaskAdapter;
import b12app.vyom.com.flowit.home.HomeActivity;
import b12app.vyom.com.flowit.model.GeneralTask;
import b12app.vyom.com.flowit.tabfragment.project.ProjectFgtContract;
import b12app.vyom.com.flowit.tabfragment.task.TaskFgtContract;
import b12app.vyom.com.utils.ActivityUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Package b12app.vyom.com.flowit.fragmentbrowse
 * @FileName FragmentTask
 * @Date 4/26/18, 12:26 AM
 * @Author Created by fengchengding
 * @Description FlowIt
 */

public class FragmentTask extends Fragment implements TaskFgtContract.IView, TaskAdapter.OnItemClickListener {
    @BindView(R.id.rv_task)
    RecyclerView recyclerView;

    private Unbinder unbinder;
    private TaskFgtContract.IPresenter taskFgtPresenter;
    private List<GeneralTask.ProjecttaskBean> taskBeanList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_task, container, false);
        unbinder = ButterKnife.bind(this, v);

        taskFgtPresenter.getTaskList(getActivity(), getArguments());

        return v;
    }


    @Override
    public void setPresenter(TaskFgtContract.IPresenter presenter) {
        taskFgtPresenter = presenter;
    }

    @Override
    public void initRecyclerView(final List<GeneralTask.ProjecttaskBean> taskBeanList) {
        this.taskBeanList = taskBeanList;

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        TaskAdapter taskAdapter = new TaskAdapter(getActivity(), taskBeanList);

        recyclerView.setAdapter(taskAdapter);

        taskAdapter.setOnTaskItemClickListener(this);
    }

    @Override
    public void hideMainFloatBtn() {
        //dismiss main float btn
        HomeActivity activity = (HomeActivity) getActivity();
        activity.dismissMainFloatBtn();
    }

    @Override
    public void onItemClick(View v, int position) {

        taskFgtPresenter.rvItemClick(v, position, taskBeanList, getActivity());

    }

}
