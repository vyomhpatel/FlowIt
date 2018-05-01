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

import java.util.ArrayList;
import java.util.List;


import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.adapter.ProjectAdapter;

import b12app.vyom.com.flowit.home.HomeActivity;
import b12app.vyom.com.flowit.model.Project;
import b12app.vyom.com.flowit.networkutils.ApiService;
import b12app.vyom.com.flowit.networkutils.RetrofitInstance;
import b12app.vyom.com.utils.ActivityUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @Package b12app.vyom.com.flowit.fragmentbrowse
 * @FileName FragmentProject
 * @Date 4/26/18, 12:18 AM
 * @Author Created by fengchengding
 * @Description FlowIt
 */

public class FragmentProject extends Fragment implements ProjectFragmentContract.IView {
    @BindView(R.id.rv_project)
    RecyclerView recyclerView;
    private Unbinder unbinder;
    private ProjectFragmentContract.IPresenter projectFragmentPresenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_project, container, false);
        unbinder = ButterKnife.bind(this, v);
        
        getData();
        return v;
    }

    private void getData() {

        ApiService apiService = RetrofitInstance.getRetrofitInstance().create(ApiService.class);

        apiService.getProjectList()
                
                .subscribe(new Observer<Project>() {

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Project projects) {

                        initRecyclerView(projects);
                    }
                });
    }

    private void initRecyclerView(final Project project) {
        ProjectAdapter adapter = new ProjectAdapter(getContext(), project);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        
        recyclerView.setAdapter(adapter);

        adapter.setMItemClickListener(new ProjectAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                FragmentProjectEdit fragmentProjectEdit = new FragmentProjectEdit();
                Bundle bundle = new Bundle();
                //project info
                bundle.putParcelable("projectnode", project.getProjects().get(position));
                fragmentProjectEdit.setArguments(bundle);
                ActivityUtil.addFragmentToActivity(R.id.fl_float_container, getActivity().getSupportFragmentManager(), fragmentProjectEdit, " editFgt");
                //dismiss main float btn
                HomeActivity activity = (HomeActivity) getActivity();
                activity.dismissMainFloatBtn();
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    //we link view and presenter here
    @Override
    public void setPresenter(ProjectFragmentContract.IPresenter presenter) {
        projectFragmentPresenter = presenter;
    }



}
