package b12app.vyom.com.flowit.model;

import android.os.Parcel;
import android.os.Parcelable;

// FIXME generate failure  field _$ProjectSubTask44
public class GeneralSubTask {

    public static class ProjectsubtaskBean implements Parcelable {
        /**
         * subtaskid : 1
         * taskid : 1
         * projectid : 27
         * subtaskname : category screen image loa
         * subtaskstatus : 3
         * subtaskdesc : xyz
         * startdate : 2018-04-03
         * endstart : 2018-04-15
         */

        private String subtaskid;
        private String taskid;
        private String projectid;
        private String subtaskname;
        private String subtaskstatus;
        private String subtaskdesc;
        private String startdate;
        private String endstart;

        public ProjectsubtaskBean(String subtaskid, String taskid, String projectid, String subtaskname, String subtaskstatus, String subtaskdesc, String startdate, String endstart) {
            this.subtaskid = subtaskid;
            this.taskid = taskid;
            this.projectid = projectid;
            this.subtaskname = subtaskname;
            this.subtaskstatus = subtaskstatus;
            this.subtaskdesc = subtaskdesc;
            this.startdate = startdate;
            this.endstart = endstart;
        }

        protected ProjectsubtaskBean(Parcel in) {
            subtaskid = in.readString();
            taskid = in.readString();
            projectid = in.readString();
            subtaskname = in.readString();
            subtaskstatus = in.readString();
            subtaskdesc = in.readString();
            startdate = in.readString();
            endstart = in.readString();
        }

        public static final Creator<ProjectsubtaskBean> CREATOR = new Creator<ProjectsubtaskBean>() {
            @Override
            public ProjectsubtaskBean createFromParcel(Parcel in) {
                return new ProjectsubtaskBean(in);
            }

            @Override
            public ProjectsubtaskBean[] newArray(int size) {
                return new ProjectsubtaskBean[size];
            }
        };

        public String getSubtaskid() {
            return subtaskid;
        }

        public void setSubtaskid(String subtaskid) {
            this.subtaskid = subtaskid;
        }

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
            dest.writeString(subtaskid);
            dest.writeString(taskid);
            dest.writeString(projectid);
            dest.writeString(subtaskname);
            dest.writeString(subtaskstatus);
            dest.writeString(subtaskdesc);
            dest.writeString(startdate);
            dest.writeString(endstart);
        }
    }
}
