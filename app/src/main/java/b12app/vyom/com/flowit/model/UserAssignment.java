package b12app.vyom.com.flowit.model;

// FIXME generate failure  field _$ViewTasks189

import java.util.List;

/**
 * @Package b12app.vyom.com.flowit.model
 * @FileName UserAssignment
 * @Date 5/1/18, 10:34 PM
 * @Author Created by fengchengding
 * @Description FlowIt
 */

public class UserAssignment {

    List<UserAssignmentBean> list;



    public List<UserAssignmentBean> getList() {
        return list;
    }

    public void setList(List<UserAssignmentBean> list) {
        this.list = list;
    }

    public static class UserAssignmentBean {
        /**
         * projectid : 27
         * taskid : 1
         * subtaskid : 1
         */

        private String projectid;
        private String taskid;
        private String subtaskid;
        private String taskName;
        private String taskDesc;
        private String taskStatus;
        private String startdate;
        private String enddate;

        public UserAssignmentBean(String projectid, String taskid, String subtaskid, String taskName, String taskDesc, String taskStatus, String startdate, String enddate) {
            this.projectid = projectid;
            this.taskid = taskid;
            this.subtaskid = subtaskid;
            this.taskName = taskName;
            this.taskDesc = taskDesc;
            this.taskStatus = taskStatus;
            this.startdate = startdate;
            this.enddate = enddate;
        }

        public String getProjectid() {
            return projectid;
        }

        public void setProjectid(String projectid) {
            this.projectid = projectid;
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

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        public String getTaskDesc() {
            return taskDesc;
        }

        public void setTaskDesc(String taskDesc) {
            this.taskDesc = taskDesc;
        }

        public String getTaskStatus() {
            return taskStatus;
        }

        public void setTaskStatus(String taskStatus) {
            this.taskStatus = taskStatus;
        }

        public String getStartdate() {
            return startdate;
        }

        public void setStartdate(String startdate) {
            this.startdate = startdate;
        }

        public String getEnddate() {
            return enddate;
        }

        public void setEnddate(String enddate) {
            this.enddate = enddate;
        }
    }
}
