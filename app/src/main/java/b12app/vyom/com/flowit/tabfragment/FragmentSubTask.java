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
import java.util.List;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.adapter.SubTaskAdapter;
import b12app.vyom.com.flowit.home.HomeActivity;
import b12app.vyom.com.flowit.model.GeneralSubTask;
import b12app.vyom.com.flowit.tabfragment.subtask.SubTaskFragmentContract;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Package b12app.vyom.com.flowit.fragmentbrowse
 * @FileName FragmentSubTask
 * @Date 4/26/18, 12:26 AM
 * @Author Created by fengchengding
 * @Description FlowIt
 */

public class FragmentSubTask extends Fragment implements SubTaskFragmentContract.IView, SubTaskAdapter.OnItemClickListener {
    @BindView(R.id.rv_subtask)
    RecyclerView recyclerview;
    private Unbinder unbinder;
    private SubTaskFragmentContract.IPresenter subtaskFgtPresenter;
    private List<GeneralSubTask.ProjectsubtaskBean> subtaskBeanList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);

        unbinder = ButterKnife.bind(this, v);

        subtaskFgtPresenter.getSubtaskList(getActivity(), getArguments());

        return v;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }

    @Override
    public void setPresenter(SubTaskFragmentContract.IPresenter presenter) {
        subtaskFgtPresenter = presenter;
    }

    @Override
    public void initRecyclerView(List<GeneralSubTask.ProjectsubtaskBean> subtasklist) {
        this.subtaskBeanList = subtasklist;

        recyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        SubTaskAdapter subtaskAdapter = new SubTaskAdapter(getActivity(), subtasklist);

        recyclerview.setAdapter(subtaskAdapter);

        subtaskAdapter.setMItemClickListener(this);

    }

    @Override
    public void hideMainFloatBtn() {
        HomeActivity activity = (HomeActivity) getActivity();
        activity.dismissMainFloatBtn();
    }

    @Override
    public void onItemClick(View v, int position) {

        subtaskFgtPresenter.rvItemClick(v, position, subtaskBeanList, getActivity());

    }
}
