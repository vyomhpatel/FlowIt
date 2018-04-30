package b12app.vyom.com.flowit.tabfragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.adapter.ProjectAdapter;
import b12app.vyom.com.flowit.home.HomeActivity;
import b12app.vyom.com.flowit.model.Project;
import b12app.vyom.com.flowit.networkutils.RetrofitInstance;
import b12app.vyom.com.flowit.tabfragment.project.ProjectFragmentContract;
import b12app.vyom.com.utils.ActivityUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @Package b12app.vyom.com.flowit.fragmentbrowse
 * @FileName FragmentProject
 * @Date 4/26/18, 12:18 AM
 * @Author Created by fengchengding
 * @Description FlowIt
 */

public class FragmentProject extends Fragment implements ProjectFragmentContract.IView, ProjectAdapter.OnItemClickListener {
    @BindView(R.id.rv_project)
    RecyclerView recyclerView;
    private Unbinder unbinder;
    private ProjectFragmentContract.IPresenter projectFragmentPresenter;
    private Disposable disposable;
    private Project project;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_project, container, false);
        unbinder = ButterKnife.bind(this, v);

        disposable = projectFragmentPresenter.getProjectList();

        return v;
    }

    @Override
    public void initRecyclerView(Project project) {
        this.project = project;

        ProjectAdapter adapter = new ProjectAdapter(getContext(), project);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        recyclerView.setAdapter(adapter);

        adapter.setMItemClickListener(this);
    }

    @Override
    public void hideMainFloatBtn() {
        //dismiss main float btn
        HomeActivity activity = (HomeActivity) getActivity();
        activity.dismissMainFloatBtn();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (!disposable.isDisposed()){
            disposable.dispose();
        }
    }

    //we link view and presenter here
    @Override
    public void setPresenter(ProjectFragmentContract.IPresenter presenter) {
        projectFragmentPresenter = presenter;
    }

    @Override
    public void onItemClick(View v, int position) {
        projectFragmentPresenter.rvItemClick(v, position, project, getActivity());
    }
}
