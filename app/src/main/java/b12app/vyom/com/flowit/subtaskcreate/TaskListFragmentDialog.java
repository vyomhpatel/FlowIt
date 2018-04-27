package b12app.vyom.com.flowit.subtaskcreate;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import java.util.List;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.adapter.ProjectListAdapter;
import b12app.vyom.com.flowit.adapter.TaskListAdapter;
import b12app.vyom.com.flowit.model.GeneralTask;
import b12app.vyom.com.flowit.model.MyProjectTask;
import b12app.vyom.com.flowit.model.Project;
import b12app.vyom.com.flowit.networkutils.ApiService;
import b12app.vyom.com.flowit.networkutils.RetrofitInstance;
import b12app.vyom.com.flowit.task.ProjectListFragmentDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jinliyu on 4/27/18.
 */

public class TaskListFragmentDialog extends DialogFragment {

    private ListView taskList;
    private ApiService apiService;
    private TaskListFragmentDialog.OnCompleteListener mListener;

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
//        apiService = RetrofitInstance.getRetrofitInstance().create(ApiService.class);
        taskList = view.findViewById(R.id.taskList);




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






        return view;
    }


}
