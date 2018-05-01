package b12app.vyom.com.flowit.networkutils;


import org.json.JSONObject;

import b12app.vyom.com.flowit.model.Employee;
import b12app.vyom.com.flowit.model.GeneralSubTask;
import b12app.vyom.com.flowit.model.GeneralTask;
import b12app.vyom.com.flowit.model.MsgReponseBody;
import b12app.vyom.com.flowit.model.Project;
import b12app.vyom.com.flowit.model.SubTaskMember;
import b12app.vyom.com.flowit.model.TaskMembers;
import b12app.vyom.com.flowit.model.User;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {


    //-------------------------------------USER -----------------------------------------

    @POST("pms_reg.php")
    Call<User> postUser(@Query("first_name") String first_name,
                        @Query("last_name") String last_name,
                        @Query("email") String email,
                        @Query("mobile") String mobile,
                        @Query("password") String password,
                        @Query("company_size") String company_size,
                        @Query("your_role") String your_role);

    @GET("pms_login.php")
    Call<User> getUser(@Query("useremail") String mobileNumber,
                       @Query("password") String password);


    //-------------------------------------PROJECT -----------------------------------------

    @POST("pms_create_project.php")
    Call<Project.ProjectsBean> postProject(@Query("project_name") String project_name,
                                           @Query("project_status") String project_status,
                                           @Query("project_desc") String project_desc,
                                           @Query("start_date") String start_date,
                                           @Query("end_date") String end_date);

    @POST("pms_edit_project.php")
    Call<MsgReponseBody> updateProject(@Query("project_id") String projectId,
                                       @Query("project_name") String project_name,
                                       @Query("project_status") String project_status,
                                       @Query("project_desc") String project_desc,
                                       @Query("start_date") String start_date,
                                       @Query("end_date") String end_date);

    @GET("pms_projects.php")
    Call<Project> getProjects();


    //-------------------------------------EMPLOYEE  -----------------------------------------

    @GET("pms_employee_list.php")
    Call<Employee> getEmployee();

    @POST("pms_create_project_team.php")
    Call<MsgReponseBody> postEmployee(@Query("project_id") String project_id,
                                      @Query("team_member_userid") String team_member_userid);

    //-------------------------------------TASK -----------------------------------------

    @POST("pms_create_task.php.php")
    Call<GeneralTask> postTask(@Query("project_id") String project_id,
                               @Query("task_name") String task_name,
                               @Query("task_status") String task_status,
                               @Query("task_desc") String task_desc,
                               @Query("start_date") String start_date,
                               @Query("end_date") String end_date);

    @GET("pms_project_task_list.php")
    Call<GeneralTask> getTaskList();

    @GET("pms_project_task_list.php")
    Observable<GeneralTask> getTaskObservableList();

    @POST("pms_assign_task_project.php")
    Call<JSONObject>assignTask(@Query("taskid") String taskid,
                               @Query("project_id") String project_id,
                               @Query("team_member_userid") String team_member_userid);

    @POST("pms_edit_task_status.php")
    Call<MsgReponseBody>updateTaskStatus(@Query("taskid") String taskid,
                                                      @Query("project_id") String project_id,
                                                      @Query("userid") String userid,
                                                      @Query("task_status") String task_status);


    //-------------------------------------SUB-TASK -----------------------------------------

    @POST("pms_create_sub_task.php")
    Call<GeneralSubTask> postSubTask(@Query("project_id") String project_id,
                                     @Query("task_id") String task_id,
                                     @Query("sub_task_name") String sub_task_name,
                                     @Query("sub_task_status") String sub_task_status,
                                     @Query("sub_task_desc") String sub_task_desc,
                                     @Query("start_date") String start_date,
                                     @Query("end_date") String end_date);

    @GET("pms_project_sub_task_list.php")
    Call<GeneralSubTask> listSubTask();


    /******************************************************* Observable Api ********************************************************/
    @GET("pms_projects.php")
    Observable<Project> getProjectList();


    //-------------------------------------USER TASK -----------------------------------------

    @GET("pms_view_task_deatil.php")
    Call<GeneralTask.ProjecttaskBean>getTaskDetails(@Query("taskid") String taskid,
                                                    @Query("project_id") String project_id);

    @GET("pms_team_task.php")
    Call<TaskMembers>getTaskMembers(@Query("taskid") String taskid,
                                    @Query("projectid") String projectid);

    //-------------------------------------USER SUB-TASK -----------------------------------------

    @GET("/pms_view_sub_task_deatil.php")
    Call<GeneralSubTask.ProjectsubtaskBean>getSubTaskDetails(@Query("taskid") String taskid,
                                                             @Query("subtask_id") String subtask_id,
                                                             @Query("project_id") String project_id);
    @GET("pms_team_sub_task.php")
    Call<SubTaskMember>getSubTaskMembers(@Query("taskid") String taskid,
                                         @Query("subtaskid") String subtaskid,
                                         @Query("projectid") String projectid);

}
