package b12app.vyom.com.flowit.MyFragment;

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
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Package b12app.vyom.com.flowit.fragmentbrowse
 * @FileName FragmentProject
 * @Date 4/26/18, 12:18 AM
 * @Author Created by fengchengding
 * @Description FlowIt
 */

public class FragmentProject extends Fragment {
    @BindView(R.id.rv_project)
    RecyclerView recyclerView;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_project, container, false);
        unbinder = ButterKnife.bind(this, v);

        initRecyclerView();

        return v;
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        List<String> list = new ArrayList<>();
        list.add("project 1");
        list.add("project 2");
        list.add("project 3");
        list.add("project 4");
        list.add("project 5");
        list.add("project 6");

        recyclerView.setAdapter(new ProjectAdapter(getContext(), list));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
