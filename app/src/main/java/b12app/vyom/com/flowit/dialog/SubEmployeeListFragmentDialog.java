package b12app.vyom.com.flowit.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.adapter.EmployeeListAdapter;
import b12app.vyom.com.flowit.model.Employee;
import b12app.vyom.com.flowit.model.GeneralSubTask;
import b12app.vyom.com.flowit.model.MsgReponseBody;
import b12app.vyom.com.flowit.networkutils.ApiService;
import b12app.vyom.com.flowit.networkutils.RetrofitInstance;
import b12app.vyom.com.utils.FbHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubEmployeeListFragmentDialog extends android.support.v4.app.DialogFragment {

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
                        Employee.EmployeesBean employeesBean = response.body().getEmployees().get(position);

                        String empfirstname = response.body().getEmployees().get(position).getEmpfirstname();
                        String empid = response.body().getEmployees().get(position).getEmpid();
                        Bundle bundle = getArguments();
                        GeneralSubTask.ProjectsubtaskBean subtasknode = bundle.getParcelable("subtasknode");
                        //fb db
                        FbHelper.getInstance().addSubTaskTeam(employeesBean, subtasknode);
                        apiService.assignSubTask(subtasknode.getTaskid(),
                                subtasknode.getSubtaskid(),
                                subtasknode.getProjectid(),
                                empid).enqueue(new Callback<MsgReponseBody>() {

                            @Override
                            public void onResponse(Call<MsgReponseBody> call, Response<MsgReponseBody> response) {

                                Toast.makeText(getActivity(), response.body().getMsg().get(0) + "", Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "Task Assign Status" + response.body().toString());

                            }

                            @Override
                            public void onFailure(Call<MsgReponseBody> call, Throwable t) {
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