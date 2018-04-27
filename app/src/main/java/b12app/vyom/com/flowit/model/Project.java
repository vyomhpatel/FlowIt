package b12app.vyom.com.flowit.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Project {


    private List<ProjectsBean> projects;


    public List<ProjectsBean> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectsBean> projects) {
        this.projects = projects;
    }


    public static class ProjectsBean implements  Parcelable {
        /**
         * id : 27
         * projectname : e-commerce
         * projectstatus : 1
         * projectdesc : xyzss
         * startdate : 2018-04-05
         * endstart : 0000-00-00
         */

        private String id;
        private String projectname;
        private String projectstatus;
        private String projectdesc;
        private String startdate;
        private String endstart;

        public ProjectsBean(String id, String projectname, String projectstatus, String projectdesc, String startdate, String endstart) {
            this.id = id;
            this.projectname = projectname;
            this.projectstatus = projectstatus;
            this.projectdesc = projectdesc;
            this.startdate = startdate;
            this.endstart = endstart;
        }

        public ProjectsBean(String projectname, String projectstatus, String projectdesc, String startdate, String endstart) {
            this.projectname = projectname;
            this.projectstatus = projectstatus;
            this.projectdesc = projectdesc;
            this.startdate = startdate;
            this.endstart = endstart;
        }

        ProjectsBean(Parcel in) {
            id = in.readString();
            projectname = in.readString();
            projectstatus = in.readString();
            projectdesc = in.readString();
            startdate = in.readString();
            endstart = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(projectname);
            dest.writeString(projectstatus);
            dest.writeString(projectdesc);
            dest.writeString(startdate);
            dest.writeString(endstart);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<ProjectsBean> CREATOR = new Creator<ProjectsBean>() {
            @Override
            public ProjectsBean createFromParcel(Parcel in) {
                return new ProjectsBean(in);
            }

            @Override
            public ProjectsBean[] newArray(int size) {
                return new ProjectsBean[size];
            }
        };

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProjectname() {
            return projectname;
        }

        public void setProjectname(String projectname) {
            this.projectname = projectname;
        }

        public String getProjectstatus() {
            return projectstatus;
        }

        public void setProjectstatus(String projectstatus) {
            this.projectstatus = projectstatus;
        }

        public String getProjectdesc() {
            return projectdesc;
        }

        public void setProjectdesc(String projectdesc) {
            this.projectdesc = projectdesc;
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
    }
}
