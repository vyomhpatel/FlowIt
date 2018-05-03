package b12app.vyom.com.utils;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import b12app.vyom.com.flowit.home.Global;
import b12app.vyom.com.flowit.model.Employee;
import b12app.vyom.com.flowit.model.FbSubTaskUserModel;
import b12app.vyom.com.flowit.model.FbTaskUserModel;
import b12app.vyom.com.flowit.model.GeneralSubTask;
import b12app.vyom.com.flowit.model.GeneralTask;
import b12app.vyom.com.flowit.model.InboxModel;
import b12app.vyom.com.flowit.model.UserAssignment;

/**
 * @Package b12app.vyom.com.utils
 * @FileName FbHelper
 * @Date 4/29/18, 5:22 PM
 * @Author Created by fengchengding
 * @Description FlowIt
 */

public class FbHelper {
    private static FbHelper instance;

    private FirebaseDatabase database;

    private FbHelper() {
        database = FirebaseDatabase.getInstance();
    }

    public static FbHelper getInstance() {
        if (instance == null) {
            synchronized (FbHelper.class) {
                if (instance == null) {
                    instance = new FbHelper();
                }
            }
        }
        return instance;
    }

    public DatabaseReference getReference(String nodeName) {

        return database.getReference(nodeName);
    }

    public void addTeamMember(DatabaseReference myRef, List<Employee.EmployeesBean> employeeIdList, String projectId) {
        //add team member
        //if already have member, it will wipe out content and reset
        //if not, add new project team
        myRef.child(projectId).setValue(null);
        for (int i = 0; i < employeeIdList.size(); i++) {
            myRef.child(projectId).child(employeeIdList.get(i).getEmpid()).child("empid").setValue(employeeIdList.get(i).getEmpid());
            myRef.child(projectId).child(employeeIdList.get(i).getEmpid()).child("empfirstname").setValue(employeeIdList.get(i).getEmpfirstname());
            myRef.child(projectId).child(employeeIdList.get(i).getEmpid()).child("emplastname").setValue(employeeIdList.get(i).getEmplastname());
            myRef.child(projectId).child(employeeIdList.get(i).getEmpid()).child("empemail").setValue(employeeIdList.get(i).getEmpemail());
            myRef.child(projectId).child(employeeIdList.get(i).getEmpid()).child("empmobile").setValue(employeeIdList.get(i).getEmpmobile());
            myRef.child(projectId).child(employeeIdList.get(i).getEmpid()).child("empdesignation").setValue(employeeIdList.get(i).getEmpdesignation());
            myRef.child(projectId).child(employeeIdList.get(i).getEmpid()).child("dateofjoining").setValue(employeeIdList.get(i).getDateofjoining());
        }
    }


    public List<Employee.EmployeesBean> getProjectTeam(DataSnapshot dataSnapshot, String projectId) {
        List<Employee.EmployeesBean> memberList = new ArrayList<>();

        for (DataSnapshot ds : dataSnapshot.child(projectId).getChildren()) {

            Employee.EmployeesBean currentEmp = new Employee.EmployeesBean(
                    ds.getValue(Employee.EmployeesBean.class).getEmpid(),
                    ds.getValue(Employee.EmployeesBean.class).getEmpfirstname(),
                    ds.getValue(Employee.EmployeesBean.class).getEmplastname(),
                    ds.getValue(Employee.EmployeesBean.class).getEmpemail(),
                    ds.getValue(Employee.EmployeesBean.class).getEmpmobile(),
                    ds.getValue(Employee.EmployeesBean.class).getEmpdesignation(),
                    ds.getValue(Employee.EmployeesBean.class).getDateofjoining());

            memberList.add(currentEmp);
        }

        return memberList;
    }

    public List<Employee.EmployeesBean> getTaskTeam(DataSnapshot dataSnapshot, String projectId, String taskId) {
        List<Employee.EmployeesBean> memberList = new ArrayList<>();

        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            if (taskId.equals(ds.getValue(FbTaskUserModel.class).getTaskid())) {
                Employee.EmployeesBean currentEmp = new Employee.EmployeesBean(
                        ds.getValue(FbTaskUserModel.class).getEmpid(),
                        ds.getValue(FbTaskUserModel.class).getEmpfirstname(),
                        ds.getValue(FbTaskUserModel.class).getEmplastname(),
                        ds.getValue(FbTaskUserModel.class).getEmpemail(),
                        ds.getValue(FbTaskUserModel.class).getEmpmobile(),
                        ds.getValue(FbTaskUserModel.class).getEmpdesignation(),
                        ds.getValue(FbTaskUserModel.class).getDateofjoining());

                memberList.add(currentEmp);
            }
        }

        return memberList;
    }

    public List<UserAssignment.UserAssignmentBean> getTaskTeamByName(DataSnapshot dataSnapshot, String userId) {
        List<UserAssignment.UserAssignmentBean> userTaskList = new ArrayList<>();

        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            if (ds.getKey().equals(userId)) {
                UserAssignment.UserAssignmentBean userAssignmentBean = new UserAssignment.UserAssignmentBean(
                        ds.getValue(FbTaskUserModel.class).getProjectid(),
                        ds.getValue(FbTaskUserModel.class).getTaskid(),
                        "",
                        ds.getValue(FbTaskUserModel.class).getTaskname(),
                        ds.getValue(FbTaskUserModel.class).getTaskdesc(),
                        ds.getValue(FbTaskUserModel.class).getTaskstatus(),
                        ds.getValue(FbTaskUserModel.class).getTaskstartdate(),
                        ds.getValue(FbTaskUserModel.class).getTaskenddate());

                userTaskList.add(userAssignmentBean);
            }
        }

        return userTaskList;
    }

    public List<UserAssignment.UserAssignmentBean> getSubTaskTeamByName(DataSnapshot dataSnapshot, String userId) {
        List<UserAssignment.UserAssignmentBean> userSubTaskList = new ArrayList<>();

        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            if (ds.getKey().equals(userId)) {
                UserAssignment.UserAssignmentBean userAssignmentBean = new UserAssignment.UserAssignmentBean(
                        ds.getValue(FbSubTaskUserModel.class).getProjectid(),
                        ds.getValue(FbSubTaskUserModel.class).getTaskid(),
                        ds.getValue(FbSubTaskUserModel.class).getSubtaskid(),
                        ds.getValue(FbSubTaskUserModel.class).getSubtaskname(),
                        ds.getValue(FbSubTaskUserModel.class).getSubtaskdesc(),
                        ds.getValue(FbSubTaskUserModel.class).getSubtaskstatus(),
                        ds.getValue(FbSubTaskUserModel.class).getSubtaskstartdate(),
                        ds.getValue(FbSubTaskUserModel.class).getSubtaskenddate());

                userSubTaskList.add(userAssignmentBean);
            }
        }

        return userSubTaskList;
    }


    public void addTaskTeamMember(Employee.EmployeesBean employeesBean, String projectId, GeneralTask.ProjecttaskBean projecttaskBean) {
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference(Global.TABLE_TASK_TEAM);
        //add team member
        //if already have member, it will wipe out content and reset
        //if not, add new project team
        myRef.child(employeesBean.getEmpid()).setValue(null);
        myRef.child(employeesBean.getEmpid()).child("empid").setValue(employeesBean.getEmpid());
        myRef.child(employeesBean.getEmpid()).child("empfirstname").setValue(employeesBean.getEmpfirstname());
        myRef.child(employeesBean.getEmpid()).child("emplastname").setValue(employeesBean.getEmplastname());
        myRef.child(employeesBean.getEmpid()).child("empemail").setValue(employeesBean.getEmpemail());
        myRef.child(employeesBean.getEmpid()).child("empmobile").setValue(employeesBean.getEmpmobile());
        myRef.child(employeesBean.getEmpid()).child("empdesignation").setValue(employeesBean.getEmpdesignation());
        myRef.child(employeesBean.getEmpid()).child("dateofjoining").setValue(employeesBean.getDateofjoining());
        myRef.child(employeesBean.getEmpid()).child("taskid").setValue(projecttaskBean.getTaskid());
        myRef.child(employeesBean.getEmpid()).child("taskname").setValue(projecttaskBean.getTaskname());
        myRef.child(employeesBean.getEmpid()).child("taskstatus").setValue(projecttaskBean.getTaskstatus());
        myRef.child(employeesBean.getEmpid()).child("taskdesc").setValue(projecttaskBean.getTaskdesc());
        myRef.child(employeesBean.getEmpid()).child("taskstartdate").setValue(projecttaskBean.getStartdate());
        myRef.child(employeesBean.getEmpid()).child("taskenddate").setValue(projecttaskBean.getEndstart());

        myRef.child(employeesBean.getEmpid()).child("projectid").setValue(projectId);
    }


    public List<InboxModel> getUserInbox(DataSnapshot dataSnapshot, String userId) {
        List<InboxModel> inboxModelList = new ArrayList<>();

        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            if (ds.getKey().equals(userId)) {
                InboxModel currentEmp = new InboxModel(
                        ds.getValue(InboxModel.class).getUserId(),
                        ds.getValue(InboxModel.class).getTaskName(),
                        ds.getValue(InboxModel.class).getTaskId(),
                        ds.getValue(InboxModel.class).getTaskDesc());

                inboxModelList.add(currentEmp);
            }
        }

        return inboxModelList;
    }

    public void addUserInbox(String taskId, String taskName, String taskDesc, String userId) {
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Inbox");
        myRef.child(userId).setValue(null);
        myRef.child(userId).child("userId").setValue(userId);
        myRef.child(userId).child("taskId").setValue(taskId);
        myRef.child(userId).child("taskName").setValue(taskName);
        myRef.child(userId).child("taskDesc").setValue(taskDesc);
    }


    public List<Employee.EmployeesBean> getSubTaskTeam(DataSnapshot dataSnapshot, GeneralSubTask.ProjectsubtaskBean subTaskNode) {
        List<Employee.EmployeesBean> memberList = new ArrayList<>();

        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            if (subTaskNode.getSubtaskid().equals(ds.getValue(FbSubTaskUserModel.class).getSubtaskid())) {
                Employee.EmployeesBean currentEmp = new Employee.EmployeesBean(
                        ds.getValue(FbSubTaskUserModel.class).getEmpid(),
                        ds.getValue(FbSubTaskUserModel.class).getEmpfirstname(),
                        ds.getValue(FbSubTaskUserModel.class).getEmplastname(),
                        ds.getValue(FbSubTaskUserModel.class).getEmpemail(),
                        ds.getValue(FbSubTaskUserModel.class).getEmpmobile(),
                        ds.getValue(FbSubTaskUserModel.class).getEmpdesignation(),
                        ds.getValue(FbSubTaskUserModel.class).getDateofjoining());

                memberList.add(currentEmp);
            }
        }

        return memberList;
    }

    public void addSubTaskTeam(Employee.EmployeesBean employeesBean, GeneralSubTask.ProjectsubtaskBean subTaskBean) {
        DatabaseReference myRef = FbHelper.getInstance().getReference(Global.TABLE_SUBTASK_TEAM);

        //add subtask team member
        //if already have member, it will wipe out content and reset
        //if not, add new project team
        myRef.child(employeesBean.getEmpid()).setValue(null);
        myRef.child(employeesBean.getEmpid()).child("empid").setValue(employeesBean.getEmpid());
        myRef.child(employeesBean.getEmpid()).child("empfirstname").setValue(employeesBean.getEmpfirstname());
        myRef.child(employeesBean.getEmpid()).child("emplastname").setValue(employeesBean.getEmplastname());
        myRef.child(employeesBean.getEmpid()).child("empemail").setValue(employeesBean.getEmpemail());
        myRef.child(employeesBean.getEmpid()).child("empmobile").setValue(employeesBean.getEmpmobile());
        myRef.child(employeesBean.getEmpid()).child("empdesignation").setValue(employeesBean.getEmpdesignation());
        myRef.child(employeesBean.getEmpid()).child("dateofjoining").setValue(employeesBean.getDateofjoining());
        myRef.child(employeesBean.getEmpid()).child("taskid").setValue(subTaskBean.getTaskid());
        myRef.child(employeesBean.getEmpid()).child("subtaskid").setValue(subTaskBean.getSubtaskid());
        myRef.child(employeesBean.getEmpid()).child("subtaskname").setValue(subTaskBean.getSubtaskname());
        myRef.child(employeesBean.getEmpid()).child("subtaskstatus").setValue(subTaskBean.getSubtaskstatus());
        myRef.child(employeesBean.getEmpid()).child("subtaskdesc").setValue(subTaskBean.getSubtaskdesc());
        myRef.child(employeesBean.getEmpid()).child("subtaskstartdate").setValue(subTaskBean.getStartdate());
        myRef.child(employeesBean.getEmpid()).child("subtaskenddate").setValue(subTaskBean.getEndstart());
        myRef.child(employeesBean.getEmpid()).child("projectid").setValue(subTaskBean.getProjectid());
    }
}
