package b12app.vyom.com.flowit.tabfragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.adapter.SubTaskAdapter;
import b12app.vyom.com.flowit.adapter.TaskAdapter;
import b12app.vyom.com.flowit.home.HomeActivity;
import b12app.vyom.com.flowit.model.GeneralSubTask;
import b12app.vyom.com.flowit.subtaskcreate.SubTaskContract;
import b12app.vyom.com.flowit.tabfragment.subtask.SubTaskFragmentContract;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;

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

        subtaskFgtPresenter.getSubtaskList(getActivity());

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

//        subtask_recyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
//
//        subtaskBeanList = new ArrayList<GeneralSubTask.ProjectsubtaskBean>();
//
//
//        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "http://rjtmobile.com/aamir/pms/android-app/pms_project_sub_task_list.php?", null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//
//                Log.i("test","into onResponse");
//                try {
//                    JSONArray subtasks = response.getJSONArray("project sub task");
//                    for(int i = 0; i < subtasks.length(); i ++)
//                    {
//                        JSONObject subtaskget = subtasks.getJSONObject(i);
//                        String subtaskid = subtaskget.getString("subtaskid");
//                        String taskid = subtaskget.getString("taskid");
//                        String projectid = subtaskget.getString("projectid");
//                        String subtaskname = subtaskget.getString("subtaskname");
//                        String subtaskstatus = subtaskget.getString("subtaskstatus");
//                        String subtaskdesc = subtaskget.getString("subtaskdesc");
//                        String startdate = subtaskget.getString("startdate");
//                        String endstart = subtaskget.getString("endstart");
//
//                        GeneralSubTask.ProjectsubtaskBean subtask = new GeneralSubTask.ProjectsubtaskBean(subtaskid,taskid,projectid,subtaskname,subtaskstatus
//                                ,subtaskdesc, startdate, endstart);
//                        Log.i("output",subtask.getSubtaskname());
//                        list.add(subtask);
//
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
//                subtask_recyclerview.setAdapter(new SubTaskAdapter(getContext(), list));
//
//
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//
//        Volley.newRequestQueue(getContext()).add(request);

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
