package b12app.vyom.com.flowit.dialog;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.adapter.EmployeeListAdapter;
import b12app.vyom.com.flowit.model.Employee;
import b12app.vyom.com.flowit.model.GeneralTask;
import b12app.vyom.com.flowit.networkutils.ApiService;
import b12app.vyom.com.flowit.networkutils.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeListFragmentDialog extends android.support.v4.app.DialogFragment {

    private ListView empList;
    private ApiService apiService;
    private OnCompleteListener mListener;
    public static String TAG = "employee list dialog tag";

    public interface OnCompleteListener {
        void onComplete(String project_id, String project_name);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.project_list_fragment, container, false);
        apiService = RetrofitInstance.getRetrofitInstance().create(ApiService.class);
        empList = view.findViewById(R.id.projectList);
        Call<Employee> projectCall = apiService.getEmployee();
        projectCall.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, final Response<Employee> response) {


                Log.i(TAG, "employee list: " + response);
                EmployeeListAdapter employeeListAdapter = new EmployeeListAdapter(response.body().getEmployees(), getActivity());
                empList.setAdapter(employeeListAdapter);
                empList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        String empfirstname = response.body().getEmployees().get(position).getEmpfirstname();
                        String empid = response.body().getEmployees().get(position).getEmpid();
                        Bundle bundle = getArguments();
                        String task_id = bundle.getString("task_id");
                        String project_id = bundle.getString("project_id");
                        apiService.assignTask(task_id, project_id, empid).enqueue(new Callback<JSONObject>() {

                            @Override
                            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {

                                try {
                                    Log.i(TAG, "Task Assign Status" + response.body().toString());
                                    JSONArray jsonArray = response.body().getJSONArray("msg");

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                            @Override
                            public void onFailure(Call<JSONObject> call, Throwable t) {
                                Log.i(TAG, "Task Assign Failure" + t.getMessage());
                            }
                        });
                        getDialog().dismiss();

                    }
                });
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {

                Log.i(TAG, "employee list error response: " + t.getMessage());

            }
        });


        return view;
    }
}