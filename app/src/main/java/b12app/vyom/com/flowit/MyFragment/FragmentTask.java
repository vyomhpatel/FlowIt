package b12app.vyom.com.flowit.MyFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.adapter.ProjectAdapter;
import b12app.vyom.com.flowit.adapter.TaskAdapter;
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

public class FragmentTask extends Fragment {
    @BindView(R.id.rv_task)
    RecyclerView recyclerView;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_task, container, false);
        unbinder = ButterKnife.bind(this, v);

        initRecyclerView();

        return v;
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        List<String> list = new ArrayList<>();
        list.add("Task 1");
        list.add("Task 2");
        list.add("Task 3");
        list.add("Task 4");
        list.add("Task 5");
        list.add("Task 6");

        recyclerView.setAdapter(new TaskAdapter(getContext(), list));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
