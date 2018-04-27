package b12app.vyom.com.flowit.model;

// FIXME generate failure  field _$ProjectTask189

import java.util.List;

/**
 * Created by jinliyu on 4/27/18.
 */

public class MyProjectTask {

    //task list
    List<taskBean> projectTaskList;


    public List<taskBean> getProjectTaskList() {
        return projectTaskList;
    }

    public void setProjectTaskList(List<taskBean> projectTaskList) {
        this.projectTaskList = projectTaskList;
    }



    //task bean
    public static class taskBean {
        /**
         * taskid : 1
         * projectid : 27
         * taskname : category screen
         * taskstatus : 1
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
    }
}

