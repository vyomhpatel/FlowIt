package b12app.vyom.com.flowit.model;

import com.google.gson.Gson;

import java.util.List;

public class SubTaskMember {

    private List<MembersBean> members;

    public static SubTaskMember objectFromData(String str) {

        return new Gson().fromJson(str, SubTaskMember.class);
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
         * subtaskid : 1
         * projectid : 27
         * userid : 14
         */

        private String assignid;
        private String taskid;
        private String subtaskid;
        private String projectid;
        private String userid;

        public static MembersBean objectFromData(String str) {

            return new Gson().fromJson(str, MembersBean.class);
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

        public String getSubtaskid() {
            return subtaskid;
        }

        public void setSubtaskid(String subtaskid) {
            this.subtaskid = subtaskid;
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