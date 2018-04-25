package b12app.vyom.com.flowit.model;

public class Project {

    private int project_id, member_id;
    private String project_title, project_description, start_date, end_date, attach_file_name;

    public Project(int project_id, int member_id, String project_title, String project_description, String start_date, String end_date, String attach_file_name) {
        this.project_id = project_id;
        this.member_id = member_id;
        this.project_title = project_title;
        this.project_description = project_description;
        this.start_date = start_date;
        this.end_date = end_date;
        this.attach_file_name = attach_file_name;
    }


    //----------getters-----------
    public int getProject_id() {
        return project_id;
    }

    public int getMember_id() {
        return member_id;
    }

    public String getProject_title() {
        return project_title;
    }

    public String getProject_description() {
        return project_description;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public String getAttach_file_name() {
        return attach_file_name;
    }


    //------------setters---------------

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public void setProject_title(String project_title) {
        this.project_title = project_title;
    }

    public void setProject_description(String project_description) {
        this.project_description = project_description;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public void setAttach_file_name(String attach_file_name) {
        this.attach_file_name = attach_file_name;
    }
}
