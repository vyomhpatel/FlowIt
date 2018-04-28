package b12app.vyom.com.flowit.tabfragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.squareup.picasso.Picasso;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.dialog.TeamDialog;
import b12app.vyom.com.flowit.model.Employee;
import b12app.vyom.com.flowit.model.MsgReponseBody;
import b12app.vyom.com.flowit.model.Project;
import b12app.vyom.com.flowit.networkutils.RetrofitInstance;
import b12app.vyom.com.utils.CircleImageView;
import b12app.vyom.com.utils.MyFlowlayout;
import b12app.vyom.com.utils.StatusHelper;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Package b12app.vyom.com.flowit.tabfragment
 * @FileName FragmentProjectEdit
 * @Date 4/26/18, 3:58 PM
 * @Author Created by fengchengding
 * @Description FlowIt
 */

public class FragmentProjectEdit extends Fragment {
    @BindView(R.id.tv_detail_id)
    TextView projectIdTv;

    @BindView(R.id.edt_detail_name)
    TextView nameEdt;

    @BindView(R.id.edt_detail_status)
    Spinner statusSpr;

    @BindView(R.id.tv_detail_desc)
    TextView descEdt;

    @BindView(R.id.tv_detail_date)
    TextView dateTv;

    @BindView(R.id.layout_detail_flow)
    MyFlowlayout myFlowlayout;

    @BindView(R.id.fab_detail_project)
    FloatingActionButton editFloatBtn;

    @BindView(R.id.imgbtn_add_member)
    ImageButton addMemberBtn;

    private DatePickerDialog toDatePickerDialog;
    private Project.ProjectsBean projectNode;
    private String projectId, projectName, projectDesc, currentEndDate;
    private int projectStatus;

    private Unbinder unbinder;
    private static boolean FLAG_EDIT_MODE = false;

    int[] urls = {R.drawable.ic_avatar, R.drawable.ic_avatar};

    private static final String TAG = "FragmentProjectEdit";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_project_detail, container, false);
        unbinder = ButterKnife.bind(this, v);

        initFlow();

        initView();

        clickListener();

        return v;
    }


    private void clickListener() {
        editFloatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Boolean.valueOf(v.getTag().toString())) {
                    //enable edit mode
                    FLAG_EDIT_MODE = true;
                    v.setTag(FLAG_EDIT_MODE);
                    enableEdit(FLAG_EDIT_MODE);
                    addMemberBtn.setVisibility(View.VISIBLE);
                    editFloatBtn.setImageResource(R.drawable.ic_correct);

                } else {
                    //disable edit mode
                    FLAG_EDIT_MODE = false;
                    v.setTag(FLAG_EDIT_MODE);
                    enableEdit(FLAG_EDIT_MODE);
                    addMemberBtn.setVisibility(View.GONE);
                    editFloatBtn.setImageResource(R.drawable.ic_edit);

                    //save new name, status, desc
                    projectName = nameEdt.getText().toString();
                    projectStatus = statusSpr.getSelectedItemPosition() + 1;
                    projectDesc = descEdt.getText().toString();

                    //do network call to update
                    RetrofitInstance.apiService().updateProject(projectId, projectName, String.valueOf(projectStatus), projectDesc, projectNode.getStartdate(), currentEndDate).enqueue(new Callback<MsgReponseBody>() {
                        @Override
                        public void onResponse(Call<MsgReponseBody> call, Response<MsgReponseBody> response) {

                            Toast.makeText(getContext(), response.body().getMsg().get(0), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<MsgReponseBody> call, Throwable t) {
                            t.getMessage();
                        }
                    });
                }
            }
        });

        dateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDatePickerDialog.show();
            }
        });

        addMemberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TeamDialog dialog = TeamDialog.newInstance();
                dialog.showDialog(getActivity().getSupportFragmentManager(), "employeeDlg");

                //listener to show the result of team creation
                //TODO need a way to save team member data, since api only allow to post 1 employee id
                dialog.setListener(new TeamDialog.OnCompleteListener() {
                    @Override
                    public void onComplete(List<String> employeeIdList) {

                        Toast.makeText(getContext(), "fake msg: team created successfully", Toast.LENGTH_SHORT).show();

//                        RetrofitInstance.apiService().postEmployee(projectNode.getId(), employeeIdList.get(0)).enqueue(new Callback<MsgReponseBody>() {
//                            @Override
//                            public void onResponse(Call<MsgReponseBody> call, Response<MsgReponseBody> response) {
//
//                                Toast.makeText(getContext(), response.body().getMsg().get(0), Toast.LENGTH_SHORT).show();
//                            }
//
//                            @Override
//                            public void onFailure(Call<MsgReponseBody> call, Throwable t) {
//
//                            }
//                        });
                    }
                });
            }
        });
    }

    private void initView() {
        projectNode = getArguments().getParcelable("projectnode");

        projectId = projectNode.getId();
        projectName = projectNode.getProjectname();
        projectDesc = projectNode.getProjectdesc();
        projectStatus = Integer.valueOf(projectNode.getProjectstatus());
        currentEndDate = projectNode.getEndstart();

        projectIdTv.setText(getString(R.string.feng_project_id) + "  " + projectId);
        nameEdt.setText(projectName);
        descEdt.setText(projectDesc);
        statusSpr.setSelection(projectStatus - 1);
        dateTv.setText(getString(R.string.feng_due_date) + "  " + currentEndDate);
        editFloatBtn.setTag(FLAG_EDIT_MODE);

        initDatePicker();

        //disable edit mode
        enableEdit(false);

    }

    public void initFlow() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        for (int url : urls) {
            CircleImageView imageView = (CircleImageView) inflater.inflate(R.layout.item_flowlayout, myFlowlayout, false);
            Picasso.with(getContext()).load(url).fit().into(imageView);
            myFlowlayout.addView(imageView);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void enableEdit(Boolean enable) {
        nameEdt.setEnabled(enable);
        statusSpr.setEnabled(enable);
        descEdt.setEnabled(enable);
        myFlowlayout.setEnabled(enable);
        dateTv.setEnabled(enable);
    }

    public void initDatePicker() {
        Calendar newCalendar = Calendar.getInstance();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        toDatePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                StringBuilder sb = new StringBuilder();
                try {
                    Date startDate = sdf.parse(projectNode.getStartdate());
                    Date endDate = sdf.parse(sb.append(year).append("-").append(monthOfYear + 1).append("-").append(dayOfMonth).toString());

                    //check if end date is early than start date
                    if (startDate.getTime() > endDate.getTime()) {

                        Toast.makeText(getContext(), R.string.feng_date_alert, Toast.LENGTH_SHORT).show();

                    } else {
                        //save due date
                        currentEndDate = sdf.format(endDate);
                        dateTv.setText(getString(R.string.feng_due_date) + " " + currentEndDate);

                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }


}
