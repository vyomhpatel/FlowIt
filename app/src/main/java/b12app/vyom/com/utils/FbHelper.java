package b12app.vyom.com.utils;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import b12app.vyom.com.flowit.model.Employee;

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

        for (DataSnapshot ds : dataSnapshot.child(projectId).child(taskId).getChildren()) {

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


    public void addTaskTeamMember(DatabaseReference myRef, Employee.EmployeesBean employeesBean, String projectId, String taskId) {
        //add team member
        //if already have member, it will wipe out content and reset
        //if not, add new project team
        myRef.child(projectId).child(taskId).setValue(null);
        myRef.child(projectId).child(taskId).child(employeesBean.getEmpid()).child("empid").setValue(employeesBean.getEmpid());
        myRef.child(projectId).child(taskId).child(employeesBean.getEmpid()).child("empfirstname").setValue(employeesBean.getEmpfirstname());
        myRef.child(projectId).child(taskId).child(employeesBean.getEmpid()).child("emplastname").setValue(employeesBean.getEmplastname());
        myRef.child(projectId).child(taskId).child(employeesBean.getEmpid()).child("empemail").setValue(employeesBean.getEmpemail());
        myRef.child(projectId).child(taskId).child(employeesBean.getEmpid()).child("empmobile").setValue(employeesBean.getEmpmobile());
        myRef.child(projectId).child(taskId).child(employeesBean.getEmpid()).child("empdesignation").setValue(employeesBean.getEmpdesignation());
        myRef.child(projectId).child(taskId).child(employeesBean.getEmpid()).child("dateofjoining").setValue(employeesBean.getDateofjoining());

    }


}
