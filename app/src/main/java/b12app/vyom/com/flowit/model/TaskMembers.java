package b12app.vyom.com.flowit.model;

import com.google.gson.Gson;

import java.util.List;

public class TaskMembers {

    private List<MembersBean> members;

    public static TaskMembers objectFromData(String str) {

        return new Gson().fromJson(str, TaskMembers.class);
    }

    public List<MembersBean> getMembers() {
        return members;
    }

    public void setMembers(List<MembersBean> members) {
        this.members = members;
    }

    public static class MembersBean {
        /**
         * assignid : 1
         * taskid : 1
         * projectid : 27
         * userid : 14
         */

        private String assignid;
        private String taskid;
        private String projectid;
        private String userid;

        public MembersBean(String assignid, String taskid, String projectid, String userid) {
            this.assignid = assignid;
            this.taskid = taskid;
            this.projectid = projectid;
            this.userid = userid;
        }


        public String getAssignid() {
            return assignid;
        }

        public void setAssignid(String assignid) {
            this.assignid = assignid;
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

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }
    }
}
