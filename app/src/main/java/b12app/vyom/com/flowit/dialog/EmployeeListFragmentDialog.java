package b12app.vyom.com.flowit.dialog;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.adapter.EmployeeListAdapter;
import b12app.vyom.com.flowit.model.Employee;
import b12app.vyom.com.flowit.model.GeneralTask;
import b12app.vyom.com.flowit.model.MsgReponseBody;
import b12app.vyom.com.flowit.networkutils.ApiService;
import b12app.vyom.com.flowit.networkutils.RetrofitInstance;
import b12app.vyom.com.utils.FbHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeListFragmentDialog extends android.support.v4.app.DialogFragment {

    private ListView empList;
    private ApiService apiService;
    private OnCompleteListener mListener;
    private static final String TAG = "EmployeeListFragmentDia";

    public interface OnCompleteListener {
        void onComplete(int position, List<Employee.EmployeesBean> list);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        View view = inflater.inflate(R.layout.project_list_fragment, container, false);

        empList = view.findViewById(R.id.projectList);

        RetrofitInstance.apiService().getEmployee().enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, final Response<Employee> response) {
                
                EmployeeListAdapter employeeListAdapter = new EmployeeListAdapter(response.body().getEmployees(), getActivity());
                empList.setAdapter(employeeListAdapter);
                empList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        mListener.onComplete(position, response.body().getEmployees());

                        //web request
                        String empfirstname = response.body().getEmployees().get(position).getEmpfirstname();
                        String empid = response.body().getEmployees().get(position).getEmpid();
                        Bundle bundle = getArguments();
                        String task_id = bundle.getString("task_id");
                        String project_id = bundle.getString("project_id");
                        RetrofitInstance.apiService().assignTask(task_id, project_id, empid).enqueue(new Callback<MsgReponseBody>() {

                            @Override
                            public void onResponse(Call<MsgReponseBody> call, Response<MsgReponseBody> response) {

                                Log.i(TAG, "Task Assign Status" + response.body().getMsg());

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

    public void setListener(OnCompleteListener listener){
        Log.i(TAG, "setListener: ");
        this.mListener = listener;
    }
}