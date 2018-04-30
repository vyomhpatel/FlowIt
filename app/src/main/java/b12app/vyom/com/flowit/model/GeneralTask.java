package b12app.vyom.com.flowit.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

// FIXME generate failure  field _$ProjectTask249

public class GeneralTask {

    private List<GeneralTask.ProjecttaskBean> projecttaskBeanList;

    @Override
    public String toString() {


        return "GeneralTask{" +
                "projecttaskBeanList=" + projecttaskBeanList +
                '}';
    }

    public List<GeneralTask.ProjecttaskBean> getProjecttaskBeanList() {
        return projecttaskBeanList;
    }

    public void setProjects(List<GeneralTask.ProjecttaskBean> projecttaskBeanList) {
        this.projecttaskBeanList = projecttaskBeanList;
    }

    public static class ProjecttaskBean implements Parcelable {
        /**
         * taskid : 1
         * projectid : 27
         * taskname : category screen
         * taskstatus : 3
         * taskdesc : xyz
         * startdate : 2018-04-03
         * endstart : 2018-04-15
         */

        private String taskid;
        private String projectid;
        private String taskname;
        private String taskstatus;
        private String taskdesc;
        private String startdate;
        private String endstart;

        public ProjecttaskBean(String taskid, String projectid, String taskstatus) {
            this.taskid = taskid;
            this.projectid = projectid;
            this.taskstatus = taskstatus;
        }

        public ProjecttaskBean(String projectid, String taskname, String taskstatus, String taskdesc, String startdate, String endstart) {
            this.projectid = projectid;
            this.taskname = taskname;
            this.taskstatus = taskstatus;
            this.taskdesc = taskdesc;
            this.startdate = startdate;
            this.endstart = endstart;
        }

        public ProjecttaskBean(String taskid, String projectid, String taskname, String taskstatus, String taskdesc, String startdate, String endstart) {
            this.taskid = taskid;
            this.projectid = projectid;
            this.taskname = taskname;
            this.taskstatus = taskstatus;
            this.taskdesc = taskdesc;
            this.startdate = startdate;
            this.endstart = endstart;
        }

        protected ProjecttaskBean(Parcel in) {
            taskid = in.readString();
            projectid = in.readString();
            taskname = in.readString();
            taskstatus = in.readString();
            taskdesc = in.readString();
            startdate = in.readString();
            endstart = in.readString();
        }

        public static final Creator<ProjecttaskBean> CREATOR = new Creator<ProjecttaskBean>() {
            @Override
            public ProjecttaskBean createFromParcel(Parcel in) {
                return new ProjecttaskBean(in);
            }

            @Override
            public ProjecttaskBean[] newArray(int size) {
                return new ProjecttaskBean[size];
            }
        };

        public String getTaskid() {
            return taskid;
        }

        public void setTaskid(String taskid) {
            this.taskid = taskid;
        }

        public String getProjectid() {
            return projectid;
        }

        public void setProjectid(String projectid) {
            this.projectid = projectid;
        }

        public String getTaskname() {
            return taskname;
        }

        public void setTaskname(String taskname) {
            this.taskname = taskname;
        }

        public String getTaskstatus() {
            return taskstatus;
        }

        public void setTaskstatus(String taskstatus) {
            this.taskstatus = taskstatus;
        }

        public String getTaskdesc() {
            return taskdesc;
        }

        public void setTaskdesc(String taskdesc) {
            this.taskdesc = taskdesc;
        }

        public String getStartdate() {
            return startdate;
        }

        public void setStartdate(String startdate) {
            this.startdate = startdate;
        }

        public String getEndstart() {
            return endstart;
        }

        public void setEndstart(String endstart) {
            this.endstart = endstart;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(taskid);
            dest.writeString(projectid);
            dest.writeString(taskname);
            dest.writeString(taskstatus);
            dest.writeString(taskdesc);
            dest.writeString(startdate);
            dest.writeString(endstart);
        }
    }
}
