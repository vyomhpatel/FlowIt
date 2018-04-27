package b12app.vyom.com.flowit.model;

/*
this is task
*/
public class GeneralTask {

    public static class ProjecttaskBean {
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
        public String toString() {
            return "ProjecttaskBean{" +
                    "taskid='" + taskid + '\'' +
                    ", projectid='" + projectid + '\'' +
                    ", taskname='" + taskname + '\'' +
                    ", taskstatus='" + taskstatus + '\'' +
                    ", taskdesc='" + taskdesc + '\'' +
                    ", startdate='" + startdate + '\'' +
                    ", endstart='" + endstart + '\'' +
                    '}';
        }
    }
}
