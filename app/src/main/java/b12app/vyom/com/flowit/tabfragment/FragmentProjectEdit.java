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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.model.Project;
import b12app.vyom.com.utils.CircleImageView;
import b12app.vyom.com.utils.MyFlowlayout;
import b12app.vyom.com.utils.StatusHelper;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

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

    private Unbinder unbinder;
    private static boolean FLAG_EDIT_MODE = false;

    int[] urls = {R.drawable.ic_avatar, R.drawable.ic_avatar, R.drawable.ic_avatar, R.drawable.ic_avatar};

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
    }

    private void initView() {
        Project.ProjectsBean projectNode = getArguments().getParcelable("projectnode");

        projectIdTv.setText(getString(R.string.feng_project_id) + "  " + projectNode.getId());
        nameEdt.setText(projectNode.getProjectname());
        descEdt.setText(projectNode.getProjectdesc());
        statusSpr.setSelection(Integer.valueOf(projectNode.getProjectstatus()) - 1);
        dateTv.setText(getString(R.string.feng_due_date) + "  " + projectNode.getEndstart());
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

    public void enableEdit(Boolean enable){
        nameEdt.setEnabled(enable);
        statusSpr.setEnabled(enable);
        descEdt.setEnabled(enable);
        myFlowlayout.setEnabled(enable);
        dateTv.setEnabled(enable);
    }

    public void initDatePicker(){
        Calendar newCalendar = Calendar.getInstance();
//        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
//        Date date = new Date(System.currentTimeMillis());
        toDatePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Toast.makeText(getContext(), "year" + year + "month" + monthOfYear + "day" + dayOfMonth + "", Toast.LENGTH_SHORT).show();
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }
}
