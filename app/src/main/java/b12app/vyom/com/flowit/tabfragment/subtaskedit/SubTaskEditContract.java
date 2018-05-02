package b12app.vyom.com.flowit.tabfragment.subtaskedit;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import b12app.vyom.com.flowit.home.BasePresenter;
import b12app.vyom.com.flowit.home.BaseView;
import b12app.vyom.com.flowit.model.Employee;
import b12app.vyom.com.flowit.model.GeneralSubTask;

/**
 * Created by jinliyu on 5/1/18.
 */

public interface SubTaskEditContract {
    interface IView extends BaseView<SubTaskEditContract.IPresenter> {

        void initView(Parcelable subtaskNode);

        void updateEndDate(String dateEndString);

        void changeEditMode(boolean flagEditMode, GeneralSubTask.ProjectsubtaskBean subtaskNode);

        void showToast(String s);

        void updateMembList(List<Employee.EmployeesBean> list);
    }

    interface IPresenter extends BasePresenter {

        void getData(Bundle arguments);

        void datePickerClick(int year, int month, int dayOfMonth, GeneralSubTask.ProjectsubtaskBean subtaskNode);

        void editFloatBtnClick(View v, boolean flagEditMode, TextView nameEdt, Spinner statusSpr, TextView descEdt, GeneralSubTask.ProjectsubtaskBean subtaskNode);

        void updateSubTask(GeneralSubTask.ProjectsubtaskBean subtaskNode);

        void initFireDb(GeneralSubTask.ProjectsubtaskBean subtaskNode);
    }
}
