package b12app.vyom.com.flowit.tabfragment.taskedit;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import b12app.vyom.com.flowit.home.BasePresenter;
import b12app.vyom.com.flowit.home.BaseView;
import b12app.vyom.com.flowit.model.GeneralTask;
import b12app.vyom.com.flowit.model.Project;

public interface TaskEditContract {


    interface IView extends BaseView<TaskEditContract.IPresenter> {

        void initView(Parcelable taskNode);

        void updateEndDate(String dateEndString);

        void changeEditMode(boolean flagEditMode);

        void showToast(String s);
    }

    interface IPresenter extends BasePresenter {

        void getData(Bundle arguments);

        void datePickerClick(int year, int month, int dayOfMonth, GeneralTask.ProjecttaskBean taskNode);

        void editFloatBtnClick(View v, boolean flagEditMode, TextView nameEdt, Spinner statusSpr, TextView descEdt, GeneralTask.ProjecttaskBean taskNode);

        void updateTask(GeneralTask.ProjecttaskBean taskNode);
    }
}