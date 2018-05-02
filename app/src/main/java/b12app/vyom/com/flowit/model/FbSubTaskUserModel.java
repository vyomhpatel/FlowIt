package b12app.vyom.com.flowit.model;

/**
 * Created by jinliyu on 5/2/18.
 */

public class FbSubTaskUserModel {
    String empid;
    String empfirstname;
    String emplastname;
    String empemail;
    String empmobile;
    String empdesignation;
    String dateofjoining;
    String taskid;
    String subtaskid;
    String subtaskname;
    String subtaskstatus;
    String subtaskdesc;
    String subtaskstartdate;
    String subtaskenddate;
    String projectid;

    public FbSubTaskUserModel() {
    }

    public FbSubTaskUserModel(String empid, String empfirstname, String emplastname, String empemail, String empmobile, String empdesignation, String dateofjoining, String taskid, String subtaskid, String subtaskname, String subtaskstatus, String subtaskdesc, String subtaskstartdate, String subtaskenddate, String projectid) {
        this.empid = empid;
        this.empfirstname = empfirstname;
        this.emplastname = emplastname;
        this.empemail = empemail;
        this.empmobile = empmobile;
        this.empdesignation = empdesignation;
        this.dateofjoining = dateofjoining;
        this.taskid = taskid;
        this.subtaskid = subtaskid;
        this.subtaskname = subtaskname;
        this.subtaskstatus = subtaskstatus;
        this.subtaskdesc = subtaskdesc;
        this.subtaskstartdate = subtaskstartdate;
        this.subtaskenddate = subtaskenddate;
        this.projectid = projectid;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getEmpfirstname() {
        return empfirstname;
    }

    public void setEmpfirstname(String empfirstname) {
        this.empfirstname = empfirstname;
    }

    public String getEmplastname() {
        return emplastname;
    }

    public void setEmplastname(String emplastname) {
        this.emplastname = emplastname;
    }

    public String getEmpemail() {
        return empemail;
    }

    public void setEmpemail(String empemail) {
        this.empemail = empemail;
    }

    public String getEmpmobile() {
        return empmobile;
    }

    public void setEmpmobile(String empmobile) {
        this.empmobile = empmobile;
    }

    public String getEmpdesignation() {
        return empdesignation;
    }

    public void setEmpdesignation(String empdesignation) {
        this.empdesignation = empdesignation;
    }

    public String getDateofjoining() {
        return dateofjoining;
    }

    public void setDateofjoining(String dateofjoining) {
        this.dateofjoining = dateofjoining;
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    public String getSubtaskid() {
        return subtaskid;
    }

    public void setSubtaskid(String subtaskid) {
        this.subtaskid = subtaskid;
    }

    public String getSubtaskname() {
        return subtaskname;
    }

    public void setSubtaskname(String subtaskname) {
        this.subtaskname = subtaskname;
    }

    public String getSubtaskstatus() {
        return subtaskstatus;
    }

    public void setSubtaskstatus(String subtaskstatus) {
        this.subtaskstatus = subtaskstatus;
    }

    public String getSubtaskdesc() {
        return subtaskdesc;
    }

    public void setSubtaskdesc(String subtaskdesc) {
        this.subtaskdesc = subtaskdesc;
    }

    public String getSubtaskenddate() {
        return subtaskenddate;
    }

    public void setSubtaskenddate(String subtaskenddate) {
        this.subtaskenddate = subtaskenddate;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getSubtaskstartdate() {
        return subtaskstartdate;
    }

    public void setSubtaskstartdate(String subtaskstartdate) {
        this.subtaskstartdate = subtaskstartdate;
    }
}