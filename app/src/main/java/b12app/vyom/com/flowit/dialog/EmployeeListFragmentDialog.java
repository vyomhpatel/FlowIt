package b12app.vyom.com.flowit.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.List;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.adapter.EmployeeListAdapter;
import b12app.vyom.com.flowit.home.Global;
import b12app.vyom.com.flowit.model.Employee;
import b12app.vyom.com.flowit.model.FcmResponse;
import b12app.vyom.com.flowit.model.GeneralTask;
import b12app.vyom.com.flowit.model.MsgReponseBody;
import b12app.vyom.com.flowit.model.Sender;
import b12app.vyom.com.flowit.networkutils.ApiService;
import b12app.vyom.com.flowit.networkutils.FCMApiService;
import b12app.vyom.com.flowit.networkutils.FCMRetrofitInstance;
import b12app.vyom.com.flowit.networkutils.RetrofitInstance;
import b12app.vyom.com.utils.FbHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeListFragmentDialog extends android.support.v4.app.DialogFragment {

    private ListView empList;
    private OnCompleteListener mListener;
    private GeneralTask.ProjecttaskBean projecttaskBean;
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

        getData();

        empList = view.findViewById(R.id.projectList);

        RetrofitInstance.apiService().getEmployee().enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, final Response<Employee> response) {

                EmployeeListAdapter employeeListAdapter = new EmployeeListAdapter(response.body().getEmployees(), getActivity());
                empList.setAdapter(employeeListAdapter);
                empList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        final String employeId = response.body().getEmployees().get(position).getEmpid();

                        mListener.onComplete(position, response.body().getEmployees());

                        //FCM notification
//                        String current_token = FirebaseInstanceId.getInstance().getToken();
                        String current_token = "f3fMgS7ELNc:APA91bGNUNBrlAdJhnqVnw_7ZVeBkWN3n59rOraEHKiJuRpbV3I6yj5p678Tj5ZnENgiqCVCvQQpEIbF7kPz1N6Pao3q5tH-y-Neu-1smraf4R2VvvBGfuD3IZrW4aHI5dfrW2yZ4WM0";
                        Sender.NotificationBean notificationBean = new Sender.NotificationBean(getString(R.string.new_task_assign) +
                                projecttaskBean.getTaskid() + ". " + projecttaskBean.getTaskname(),
                                getString(R.string.fcm_title));
                        //send to self
                        Sender sender = new Sender(current_token, notificationBean);
                        //send notification
                        FCMRetrofitInstance.apiService()
                                .sendNotification(sender)
                                .enqueue(new Callback<FcmResponse>() {
                                    @Override
                                    public void onResponse(Call<FcmResponse> call, Response<FcmResponse> response) {
                                        Log.i(TAG, "fcm response: " + response.body().getResults().get(0).getMessage_id());
                                        //Fb database
                                        FbHelper.getInstance().addUserInbox(projecttaskBean.getTaskid(),
                                                projecttaskBean.getTaskname(),
                                                projecttaskBean.getTaskdesc(),
                                                employeId);
                                    }

                                    @Override
                                    public void onFailure(Call<FcmResponse> call, Throwable t) {
                                        Log.i(TAG, "onFailure: " + t.getMessage());
                                    }
                                });

                        //web request
                        String empfirstname = response.body().getEmployees().get(position).getEmpfirstname();
                        String empid = response.body().getEmployees().get(position).getEmpid();

                        RetrofitInstance.apiService().
                                assignTask(projecttaskBean.getTaskid(), projecttaskBean.getProjectid(), empid).
                                enqueue(new Callback<MsgReponseBody>() {

                                    @Override
                                    public void onResponse
                                            (Call<MsgReponseBody> call, Response<MsgReponseBody> response) {

                                        Log.i(TAG, "Task Assign Status" + response.body().getMsg());

                                    }

                                    @Override
                                    public void onFailure(Call<MsgReponseBody> call, Throwable t) {
                                        Log.i(TAG, "Task Assign Failure" + t.getMessage());
                                    }
                                });

                        dismiss();
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

    private void getData() {
        projecttaskBean = getArguments().getParcelable(Global.TASK_NODE);
    }

    public void setListener(OnCompleteListener listener) {
        Log.i(TAG, "setListener: ");
        this.mListener = listener;
    }
}