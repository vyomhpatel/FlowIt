package b12app.vyom.com.flowit.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.adapter.EmployeeRvAdapter;
import b12app.vyom.com.flowit.model.Employee;
import b12app.vyom.com.flowit.networkutils.ApiService;
import b12app.vyom.com.flowit.networkutils.RetrofitInstance;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamDialog extends DialogFragment {
    @BindView(R.id.rv_empployee)
    RecyclerView employeeRv;

    @BindView(R.id.btn_yes)
    Button confirmBtn;

    @BindView(R.id.btn_no)
    Button cancelBtn;

    private Unbinder unbinder;

    private ApiService apiService;
    private OnCompleteListener mListener;

    private EmployeeRvAdapter employeeRvAdapter;

    private List<String> employeeIdList;

    public interface OnCompleteListener {
        void onComplete(List<String> employeeIdList);
    }

    public static TeamDialog newInstance() {
        Bundle args = new Bundle();
        TeamDialog dialogTeam = new TeamDialog();
        dialogTeam.setArguments(args);
        return dialogTeam;
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(params);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        try {
//            this.mListener = (OnCompleteListener) context;
//        } catch (final ClassCastException e) {
//            throw new ClassCastException(context.toString() + " must implement OnCompleteListener");
//        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("Create Team");
        getDialog().setCanceledOnTouchOutside(true);
        //dialog rootView
        View v = inflater.inflate(R.layout.dialog_team_create, container, false);
        unbinder = ButterKnife.bind(this, v);

        fetchData();

//        Call<Project> projectCall = apiService.getEmployee();
//        projectCall.enqueue(new Callback<Project>() {
//            @Override
//            public void onResponse(Call<Project> call, final Response<Project> response) {
//
//
//                ProjectListAdapter projectListAdapter = new ProjectListAdapter(response.body().getProjects(), getActivity());
//                employeeList.setAdapter(projectListAdapter);
//                employeeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                        String project_name = response.body().getProjects().get(position).getProjectname();
//                        String project_id = response.body().getProjects().get(position).getId();
//                        mListener.onComplete(project_id, project_name);
//                        getDialog().dismiss();
//
//                    }
//                });
//            }
//
//            @Override
//            public void onFailure(Call<Project> call, Throwable t) {
//
//            }
//        });

        return v;
    }

    private void clickListener(final List<Employee.EmployeesBean> employees) {
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                employeeIdList = new ArrayList<>();
                //loop the booleanArray to find all the key which value = true
                for (int i = 0; i < employees.size(); i++){
                    if (employeeRvAdapter.getPickedEmployee().get(i)){
                        employeeIdList.add(employees.get(i).getEmpid());
                        Log.i("multiple", i + "");
                    }

                }

                mListener.onComplete(employeeIdList);
                dismiss();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void fetchData() {
        apiService = RetrofitInstance.getRetrofitInstance().create(ApiService.class);

        apiService.getEmployee().enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {

                initRecyclerView(response.body().getEmployees());

            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {


            }
        });
    }

    private void initRecyclerView(List<Employee.EmployeesBean> employees) {
        employeeRvAdapter = new EmployeeRvAdapter(getActivity(), employees);
        employeeRv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        employeeRv.setAdapter(employeeRvAdapter);
        employeeRv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        clickListener(employees);
    }

    public void showDialog(FragmentManager fragmentManager, String tag) {
        if (getDialog() == null || !getDialog().isShowing()) {
            show(fragmentManager, tag);
        }
    }

    public void setListener(OnCompleteListener onCompleteListener){
        this.mListener = onCompleteListener;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
