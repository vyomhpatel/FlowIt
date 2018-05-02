package b12app.vyom.com.flowit.subtaskcreate;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.adapter.ProjectListAdapter;
import b12app.vyom.com.flowit.adapter.TaskListAdapter;
import b12app.vyom.com.flowit.model.GeneralTask;
import b12app.vyom.com.flowit.model.MyProjectTask;
import b12app.vyom.com.flowit.model.Project;
import b12app.vyom.com.flowit.networkutils.ApiService;
import b12app.vyom.com.flowit.networkutils.RetrofitInstance;
import b12app.vyom.com.flowit.task.ProjectListFragmentDialog;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jinliyu on 4/27/18.
 */

public class TaskListFragmentDialog extends DialogFragment {

    List<GeneralTask.ProjecttaskBean> alltasks;

    private ApiService apiService;
    private TaskListFragmentDialog.OnCompleteListener mListener;
//    private Unbinder unbinder;
//    @BindView(R.id.taskList)

    ListView taskList;


    public static interface OnCompleteListener {
        public abstract void onComplete(String task_id, String task_name);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.mListener = (TaskListFragmentDialog.OnCompleteListener) context;
        } catch (final ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnCompleteListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.task_list_fragment, container, false);

//       unbinder =  ButterKnife.bind(this,view);
        taskList = view.findViewById(R.id.taskList);
//        apiService = RetrofitInstance.getRetrofitInstance().create(ApiService.class);

        alltasks = new ArrayList<GeneralTask.ProjecttaskBean>() ;

        JsonObjectRequest request  = new JsonObjectRequest(
                Request.Method.GET,
                "http://rjtmobile.com/aamir/pms/android-app/pms_project_task_list.php?",
                null,
                new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("project task");

                    for(int i = 0; i < jsonArray.length(); i ++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String taskid = jsonObject.getString("taskid");
                        String projectid = jsonObject.getString("projectid");
                        String taskname = jsonObject.getString("taskname");
                        String taskstatus = jsonObject.getString("taskstatus");
                        String taskdesc = jsonObject.getString("taskdesc");
                        String startdate = jsonObject.getString("startdate");
                        String endstart = jsonObject.getString("endstart");

                        GeneralTask.ProjecttaskBean task = new GeneralTask.ProjecttaskBean(taskid,projectid,taskname,taskstatus
                                ,taskdesc, startdate,endstart);

                        Log.i("test", task.getTaskname());
                        alltasks.add(task);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("test", e.getMessage());

                }


                TaskListAdapter taskListAdapter = new TaskListAdapter(alltasks, getActivity());
                taskList.setAdapter(taskListAdapter);
                taskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String task_name = alltasks.get(position).getTaskname();
                        String task_id = alltasks.get(position).getTaskid();
                        mListener.onComplete(task_id, task_name);
                        getDialog().dismiss();
                    }
                });


            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("test", error.getMessage());

            }
        });





//        Call<List<GeneralTask.ProjecttaskBean>> taskcall = apiService.getTaskList();
//
//
//        taskcall.enqueue(new Callback<List<GeneralTask.ProjecttaskBean>>() {
//
//            @Override
//            public void onResponse(Call<List<GeneralTask.ProjecttaskBean>> call, final Response<List<GeneralTask.ProjecttaskBean>> response) {
//                TaskListAdapter taskListAdapter = new TaskListAdapter(response.body(), getActivity());
//                taskList.setAdapter(taskListAdapter);
//                taskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        String task_name = response.body().get(position).getTaskname();
//                        String task_id = response.body().get(position).getTaskid();
//                        mListener.onComplete(task_id, task_name);
//                        getDialog().dismiss();
//                    }
//                });
//            }
//
//            @Override
//            public void onFailure(Call<List<GeneralTask.ProjecttaskBean>> call, Throwable t) {
//
//            }
//        });

        Volley.newRequestQueue(getActivity()).add(request);


        return view;
    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        unbinder.unbind();
//    }


}
