package b12app.vyom.com.flowit.tabfragment.subtaskedit;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import b12app.vyom.com.flowit.datasource.DataManager;
import b12app.vyom.com.flowit.datasource.IDataSource;
import b12app.vyom.com.flowit.model.GeneralSubTask;
import b12app.vyom.com.flowit.tabfragment.FragmentSubTaskEdit;
import b12app.vyom.com.utils.FbHelper;

/**
 * Created by jinliyu on 5/1/18.
 */

public class SubTaskEditPresenter implements SubTaskEditContract.IPresenter {
    public static String TAG = "task edit presenter";
    private SubTaskEditContract.IView fragmentView;
    private FragmentSubTaskEdit fragmentTaskEdit;
    private DataManager mDataManager;
    private Context context;
    private SharedPreferences sharedPreferences;
    private String userId;
    private DatabaseReference myRef;


    public SubTaskEditPresenter(DataManager dataManager, FragmentSubTaskEdit subtaskEditFrag) {
        this.mDataManager = dataManager;
        fragmentView = subtaskEditFrag;
        context = subtaskEditFrag.getActivity();
        sharedPreferences = context.getSharedPreferences("local_user", Context.MODE_PRIVATE);
        userId = sharedPreferences.getString("userid", "");
    }

    @Override
    public void start() {

    }


    @Override
    public void getData(Bundle arguments) {
        fragmentView.initView(arguments.getParcelable("subtaskNode"));
    }

    @Override
    public void datePickerClick(int year, int month, int dayOfMonth, GeneralSubTask.ProjectsubtaskBean subtaskNode) {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        Calendar newDate = Calendar.getInstance();
        newDate.set(year, month, dayOfMonth);
        String dateEndString = String.valueOf(sdf.format(newDate.getTime()));

        fragmentView.updateEndDate(dateEndString);
    }

    @Override
    public void editFloatBtnClick(View v, boolean flagEditMode, TextView nameEdt, Spinner statusSpr, TextView descEdt, GeneralSubTask.ProjectsubtaskBean subtaskNode) {
        if (!Boolean.valueOf(v.getTag().toString())) {
            //start edit mode
            flagEditMode = true;
            v.setTag(flagEditMode);
            fragmentView.changeEditMode(flagEditMode, subtaskNode);

        } else {
            flagEditMode = false;
            v.setTag(flagEditMode);

            subtaskNode.setSubtaskstatus(String.valueOf(statusSpr.getSelectedItemPosition() + 1));

            fragmentView.changeEditMode(flagEditMode, subtaskNode);


        }
    }

    @Override
    public void updateSubTask(GeneralSubTask.ProjectsubtaskBean subtaskNode) {
        mDataManager.updateSubTask(userId, subtaskNode, new IDataSource.NetworkCallback() {
            @Override
            public void onSuccess(Object response) {

//                fragmentView.showToast(response.toString());

            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.i(TAG, throwable.getLocalizedMessage());
            }
        });
    }

    @Override
    public void initFireDb(final GeneralSubTask.ProjectsubtaskBean subtaskNode) {
        myRef = FbHelper.getInstance().getReference("SubTaskTeam");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fragmentView.updateMembList(FbHelper.getInstance().getSubTaskTeam(dataSnapshot, subtaskNode));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}