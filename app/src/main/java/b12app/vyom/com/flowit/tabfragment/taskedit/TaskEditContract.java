package b12app.vyom.com.flowit.tabfragment.taskedit;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import b12app.vyom.com.flowit.home.BasePresenter;
import b12app.vyom.com.flowit.home.BaseView;
import b12app.vyom.com.flowit.model.Employee;
import b12app.vyom.com.flowit.model.GeneralTask;

public interface TaskEditContract {


    interface IView extends BaseView<TaskEditContract.IPresenter> {

        void initView(Parcelable taskNode);

        void changeEditMode(boolean flagEditMode);

        void showToast(String s);

        void updateMembList(List<Employee.EmployeesBean> list);

    }

    interface IPresenter extends BasePresenter {

        void getData(Bundle arguments);

        void editFloatBtnClick(View v, boolean flagEditMode, TextView nameEdt, Spinner statusSpr, TextView descEdt, GeneralTask.ProjecttaskBean taskNode);

        void updateTask(GeneralTask.ProjecttaskBean taskNode);

        void initFireDb(GeneralTask.ProjecttaskBean taskNode);

        void addTaskMember(int position, List<Employee.EmployeesBean> list, GeneralTask.ProjecttaskBean taskNode);
    }
}