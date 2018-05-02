package b12app.vyom.com.flowit.model;

/**
 * @Package b12app.vyom.com.flowit.model
 * @FileName InboxModel
 * @Date 5/1/18, 2:02 PM
 * @Author Created by fengchengding
 * @Description FlowIt
 */

public class InboxModel {
    String userId;
    String taskName;
    String taskId;
    String taskDesc;

    public InboxModel() {
    }

    public InboxModel(String userId, String taskName, String taskId, String taskDesc) {
        this.userId = userId;
        this.taskName = taskName;
        this.taskId = taskId;
        this.taskDesc = taskDesc;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }
}
