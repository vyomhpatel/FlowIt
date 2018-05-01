package b12app.vyom.com.flowit.tabfragment.projectedit;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import b12app.vyom.com.flowit.home.BasePresenter;
import b12app.vyom.com.flowit.home.BaseView;
import b12app.vyom.com.flowit.model.Employee;
import b12app.vyom.com.flowit.model.Project;

public interface ProjectEditContract {


    interface IView extends BaseView<ProjectEditContract.IPresenter> {
        
        void initView(Project.ProjectsBean projectNode);

        void showToast();

        void showToast(String msg);

        void updateDate(String format);

        void changeEditMode(Boolean flag_edit_mode);

        void showDatePicker();

        void updateMembList(List<Employee.EmployeesBean> list);
    }

    interface IPresenter extends BasePresenter {

        void getData(Bundle bundle);

        void initFireDb(Project.ProjectsBean projectsBean);

        void datePickerClick();

        void datePickerClick(int year, int month, int dayOfMonth, Project.ProjectsBean projectsBean);

        void editFloatBtnClick(View v, boolean flagEditMode, TextView nameEdt, Spinner statusSpr, TextView descEdt, Project.ProjectsBean projectsBean);

        void updateMember(List<Employee.EmployeesBean> memberList, Project.ProjectsBean projectNode);
    }
}