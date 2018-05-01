package b12app.vyom.com.flowit.tabfragment.taskedit;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.datasource.DataManager;
import b12app.vyom.com.flowit.datasource.IDataSource;
import b12app.vyom.com.flowit.model.GeneralTask;
import b12app.vyom.com.flowit.networkutils.ApiService;
import b12app.vyom.com.flowit.networkutils.RetrofitInstance;
import b12app.vyom.com.flowit.tabfragment.FragmentTaskEdit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskEditPresenter implements TaskEditContract.IPresenter {
    public static String TAG = "task edit presenter";
    private TaskEditContract.IView fragmentView;
    private FragmentTaskEdit fragmentTaskEdit;
    private DataManager mDataManager;

    public TaskEditPresenter(DataManager dataManager, TaskEditContract.IView taskEditFrag) {

        this.mDataManager = dataManager;

        fragmentView = taskEditFrag;
    }

    @Override
    public void start() {

    }


    @Override
    public void getData(Bundle arguments) {
        fragmentView.initView(arguments.getParcelable("taskNode"));
    }

    @Override
    public void datePickerClick(int year, int month, int dayOfMonth, GeneralTask.ProjecttaskBean taskNode) {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        Calendar newDate = Calendar.getInstance();
        newDate.set(year, month, dayOfMonth);
        String dateEndString = String.valueOf(sdf.format(newDate.getTime()));

        fragmentView.updateEndDate(dateEndString);
    }

    @Override
    public void editFloatBtnClick(View v, boolean flagEditMode, TextView nameEdt, Spinner statusSpr, TextView descEdt, GeneralTask.ProjecttaskBean taskNode) {

        if (!Boolean.valueOf(v.getTag().toString())) {
            //start edit mode
            flagEditMode = true;
            v.setTag(flagEditMode);
            fragmentView.changeEditMode(flagEditMode);

        } else {
            flagEditMode = false;
            v.setTag(flagEditMode);
            fragmentView.changeEditMode(flagEditMode);

        }
    }

    @Override
    public void updateTask(GeneralTask.ProjecttaskBean taskNode) {
        mDataManager.updateTask(taskNode, new IDataSource.NetworkCallback() {
            @Override
            public void onSuccess(Object response) {

                fragmentView.showToast(response.toString());
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.i(TAG, throwable.getLocalizedMessage());
            }
        });
    }
}