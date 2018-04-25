package b12app.vyom.com.flowit.model;

public class UserTask {

    private int task_id, assigned_to, project_id;
    private String task_title, task_description, priority, status, due_date, task_file;

    public UserTask(int task_id, int assigned_to, int project_id, String task_title, String task_description, String priority, String status, String due_date, String task_file) {
        this.task_id = task_id;
        this.assigned_to = assigned_to;
        this.project_id = project_id;
        this.task_title = task_title;
        this.task_description = task_description;
        this.priority = priority;
        this.status = status;
        this.due_date = due_date;
        this.task_file = task_file;
    }

    // ---------getters----------
    public int getTask_id() {
        return task_id;
    }

    public int getAssigned_to() {
        return assigned_to;
    }

    public int getProject_id() {
        return project_id;
    }

    public String getTask_title() {
        return task_title;
    }

    public String getTask_description() {
        return task_description;
    }

    public String getPriority() {
        return priority;
    }

    public String getStatus() {
        return status;
    }

    public String getDue_date() {
        return due_date;
    }

    public String getTask_file() {
        return task_file;
    }


    // ---------setters----------
    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public void setAssigned_to(int assigned_to) {
        this.assigned_to = assigned_to;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public void setTask_title(String task_title) {
        this.task_title = task_title;
    }

    public void setTask_description(String task_description) {
        this.task_description = task_description;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public void setTask_file(String task_file) {
        this.task_file = task_file;
    }
}
