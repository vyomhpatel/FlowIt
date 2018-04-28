package b12app.vyom.com.flowit.tabfragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.model.GeneralTask;
import b12app.vyom.com.flowit.networkutils.ApiService;
import b12app.vyom.com.flowit.networkutils.RetrofitInstance;
import b12app.vyom.com.utils.CircleImageView;
import b12app.vyom.com.utils.MyFlowlayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import android.app.DialogFragment;
import android.app.Fragment;

/**
 * @Package b12app.vyom.com.flowit.tabfragment
 * @FileName FragmentProjectEdit
 * @Date 4/26/18, 3:58 PM
 * @Author Created by fengchengding
 * @Description FlowIt
 */

public class FragmentTaskEdit extends android.support.v4.app.Fragment  {
    @BindView(R.id.tv_task_detail_id)
    TextView taskDetailId;

    @BindView(R.id.edt_task__detail_name)
    TextView nameEdt;

    @BindView(R.id.spn_task_detail_status)
    Spinner statusSpr;

    @BindView(R.id.tv_task_detail_desc)
    TextView descEdt;

    @BindView(R.id.tv_task__detail_due_date)
    TextView dateTv;

    @BindView(R.id.layout__task_detail_flow_task)
    MyFlowlayout myFlowlayout;

    @BindView(R.id.fab_detail_task)
    FloatingActionButton editFloatBtn;

    @BindView(R.id.imgbtn_add_member_to_task)
    ImageButton addMemberBtn;

    private DatePickerDialog toDatePickerDialog;

    private Unbinder unbinder;
    private static boolean FLAG_EDIT_MODE = false;
    private ApiService apiService;
    private  GeneralTask.ProjecttaskBean taskNode;

    int[] urls = {R.drawable.ic_avatar};

    private static final String TAG = "FragmentProjectEdit";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_task_detail, container, false);
        unbinder = ButterKnife.bind(this, v);

        //api service initialization
         apiService = RetrofitInstance.getRetrofitInstance().create(ApiService.class);

        initFlow();

        initView();

        clickListener();

        return v;
    }

    private void clickListener() {
        editFloatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Boolean.valueOf(v.getTag().toString())){
                    //start edit mode
                    FLAG_EDIT_MODE = true;
                    v.setTag(FLAG_EDIT_MODE);
                    enableEdit(FLAG_EDIT_MODE);
                    addMemberBtn.setVisibility(View.VISIBLE);
                    editFloatBtn.setImageResource(R.drawable.ic_correct);

                }else {
                    FLAG_EDIT_MODE = false;
                    v.setTag(FLAG_EDIT_MODE);
                    enableEdit(FLAG_EDIT_MODE);
                    addMemberBtn.setVisibility(View.GONE);
                    editFloatBtn.setImageResource(R.drawable.ic_edit);


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


                EmployeeListFragmentDialog employeeListFragmentDialog = new EmployeeListFragmentDialog();
                Bundle bundle = new Bundle();
                bundle.putString("task_id",taskNode.getTaskid());
                bundle.putString("project_id",taskNode.getProjectid());
                employeeListFragmentDialog.setArguments(bundle);
                    employeeListFragmentDialog.show(getFragmentManager(),"emp dialog");

         //       apiService.assignTask(taskNode.getTaskid(),taskNode.getProjectid(),)
            }
        });
    }

    private void initView() {
        taskNode = getArguments().getParcelable("taskNode");

        taskDetailId.setText("Task ID: " + taskNode.getTaskid());
        nameEdt.setText(taskNode.getTaskname());
        descEdt.setText(taskNode.getTaskdesc());
        statusSpr.setSelection(Integer.valueOf(taskNode.getTaskstatus()) - 1);
        dateTv.setText(getString(R.string.feng_due_date) + "  " + taskNode.getEndstart());
        editFloatBtn.setTag(FLAG_EDIT_MODE);

        initDatePicker();

        //disable edit mode
        enableEdit(false);

    }

    public void initFlow() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        for (int url : urls) {
            CircleImageView imageView = (CircleImageView) inflater.inflate(R.layout.item_flowlayout, myFlowlayout, false);
            Picasso.with(getActivity()).load(url).fit().into(imageView);
            myFlowlayout.addView(imageView);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void enableEdit(Boolean enable){
       // nameEdt.setEnabled(enable);
        statusSpr.setEnabled(enable);
       // descEdt.setEnabled(enable);
        myFlowlayout.setEnabled(enable);
      //  dateTv.setEnabled(enable);
    }

    public void initDatePicker(){
        Calendar newCalendar = Calendar.getInstance();
     final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
//        Date date = new Date(System.currentTimeMillis());
        toDatePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
              String  dateEndString = String.valueOf(sdf.format(newDate.getTime()));
                Toast.makeText(getActivity(), dateEndString, Toast.LENGTH_SHORT).show();
                dateTv.setText(dateEndString);
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

}
