package b12app.vyom.com.flowit.networkutils;

import java.util.List;

import b12app.vyom.com.flowit.model.Employee;
import b12app.vyom.com.flowit.model.GeneralTask;
import b12app.vyom.com.flowit.model.Project;
import b12app.vyom.com.flowit.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    //-------------------------------------USER -----------------------------------------

    @POST("pms_reg.php")
    Call<User> postUser(@Query("first_name") String first_name, @Query("last_name") String last_name, @Query("email") String email
            , @Query("mobile") String mobile, @Query("password") String password, @Query("company_size") String company_size, @Query("your_role") String your_role);

    @GET("pms_login.php")
    Call<User> getUser(@Query("useremail") String mobileNumber, @Query("password") String password);


    //-------------------------------------PROJECT -----------------------------------------

    @POST("pms_create_project.php")
    Call<Project.ProjectsBean> postProject(@Query("project_name") String project_name, @Query("project_status") String project_status, @Query("project_desc") String project_desc
            , @Query("start_date") String start_date, @Query("end_date") String end_date);

    @POST("pms_edit_project.php")
    Call<Project.ProjectsBean> updateProject(@Query("project_name") String project_name, @Query("project_status") String project_status, @Query("project_desc") String project_desc
            , @Query("start_date") String start_date, @Query("end_date") String end_date);

    @GET("pms_projects.php")
    Call<Project.ProjectsBean> getProjects();

    //-------------------------------------EMPLOYEE LIST -----------------------------------------

    @GET("pms_employee_list.php")
    Call<Employee.EmployeesBean> getEmployee();

    //-------------------------------------TASK -----------------------------------------

    @POST("pms_create_task.php.php")
    Call<GeneralTask> postTask(@Query("project_id") String project_id, @Query("task_name") String task_name, @Query("task_status") String task_status
            , @Query("task_desc") String task_desc, @Query("start_date") String start_date, @Query("end_date") String end_date);

    @GET("pms_project_task_list.php")
    Call<GeneralTask.ProjecttaskBean> getTaskList();

    //-------------------------------------TASK -----------------------------------------


}
