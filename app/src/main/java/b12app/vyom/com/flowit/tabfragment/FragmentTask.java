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
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.adapter.TaskAdapter;
import b12app.vyom.com.flowit.home.HomeActivity;
import b12app.vyom.com.flowit.model.GeneralTask;
import b12app.vyom.com.flowit.model.Project;
import b12app.vyom.com.flowit.networkutils.ApiService;
import b12app.vyom.com.flowit.networkutils.RetrofitInstance;
import b12app.vyom.com.utils.ActivityUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
    List<GeneralTask.ProjecttaskBean> projecttaskBeanList;
    public static String TAG = FragmentTask.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_task, container, false);
        unbinder = ButterKnife.bind(this, v);

        getData();
        projecttaskBeanList = new ArrayList<>();

        return v;
    }

    private void getData() {


        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        String url = "http://rjtmobile.com/aamir/pms/android-app/pms_project_task_list.php?";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray projecttask = response.getJSONArray("project task");
                    for(int i = 0; i<projecttask.length();i++){

                        JSONObject task = projecttask.getJSONObject(i);
                        String taskid = task.getString("taskid");
                        String projectid = task.getString("projectid");
                        String taskname = task.getString("taskname");
                        String taskstatus = task.getString("taskstatus");
                        String taskdesc = task.getString("taskdesc");
                        String startdate = task.getString("startdate");
                        String endstart = task.getString("endstart");
                        GeneralTask.ProjecttaskBean projecttaskBean = new GeneralTask.ProjecttaskBean(
                                taskid,projectid,taskname,taskstatus,taskdesc,startdate,endstart);
                        projecttaskBeanList.add(projecttaskBean);

                    }
                    Log.i(TAG, "project task bean list: "+projecttaskBeanList);
                    initRecyclerView(projecttaskBeanList);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i(TAG, "error: "+e);
                }


            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonObjectRequest);
//        apiService.getTaskObservableList()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<GeneralTask>() {
//
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                        Log.i(TAG, "error task list: "+e.getMessage());
//
//                    }
//
//                    @Override
//                    public void onNext(GeneralTask generalTasks) {
//
//                        Log.i(TAG, "onNext: "+generalTasks.toString());
////                        initRecyclerView(generalTasks);
//                    }
//                });


    }



    private void initRecyclerView(List<GeneralTask.ProjecttaskBean> generalTask) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
       TaskAdapter taskAdapter= new TaskAdapter(getActivity(), generalTask);
        recyclerView.setAdapter(taskAdapter);
            taskAdapter.setOnTaskItemClickListener(new TaskAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    FragmentTaskEdit fragmentTaskEdit = new FragmentTaskEdit();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("taskNode",projecttaskBeanList.get(position));
                    fragmentTaskEdit.setArguments(bundle);
                    ActivityUtil.addFragmentToActivity(R.id.fl_float_container,getFragmentManager(),fragmentTaskEdit,"task");
                    HomeActivity activity = (HomeActivity)getActivity();
                    activity.dismissMainFloatBtn();
                }
            });

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
