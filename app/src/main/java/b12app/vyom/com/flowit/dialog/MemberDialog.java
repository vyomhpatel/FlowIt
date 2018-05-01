package b12app.vyom.com.flowit.dialog;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
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

import java.util.ArrayList;
import java.util.List;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.adapter.EmployeeRvAdapter;
import b12app.vyom.com.flowit.adapter.MemRvAdapter;
import b12app.vyom.com.flowit.model.Employee;
import b12app.vyom.com.flowit.networkutils.ApiService;
import b12app.vyom.com.flowit.networkutils.RetrofitInstance;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemberDialog extends DialogFragment {
    @BindView(R.id.rv_member)
    RecyclerView memberRv;

    private Unbinder unbinder;

    private MemRvAdapter employeeRvAdapter;

    private ArrayList<Employee.EmployeesBean> memberList;


    public static MemberDialog newInstance(List<Employee.EmployeesBean> currentEmployeeList) {
        Bundle args = new Bundle();
        args.putParcelableArrayList("list", (ArrayList<? extends Parcelable>) currentEmployeeList);
        MemberDialog dialogTeam = new MemberDialog();
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(true);
        //dialog rootView
        View v = inflater.inflate(R.layout.dialog_current_member, container, false);
        unbinder = ButterKnife.bind(this, v);

        fetchData();

        initRecyclerView();

        return v;
    }


    private void fetchData() {
        memberList = getArguments().getParcelableArrayList("list");

//        Log.i("测试", memberList.get(0).getEmpfirstname());
    }

    private void initRecyclerView() {
        employeeRvAdapter = new MemRvAdapter(getActivity(), memberList);
        memberRv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        memberRv.setAdapter(employeeRvAdapter);
        memberRv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

    }

    public void showDialog(FragmentManager fragmentManager, String tag) {
        if (getDialog() == null || !getDialog().isShowing()) {
            show(fragmentManager, tag);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
