package b12app.vyom.com.flowit.db.utility;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import b12app.vyom.com.flowit.db.DbOpenHelper;
import b12app.vyom.com.flowit.db.IDbHelper;
import b12app.vyom.com.flowit.model.Project;
import b12app.vyom.com.flowit.model.User;
import b12app.vyom.com.flowit.model.UserTask;

public class DbHelper implements IDbHelper {

    Context context;
    SQLiteDatabase database;
    DbOpenHelper dbOpenHelper;

    public DbHelper(Context context) {
        this.context = context;
        dbOpenHelper = new DbOpenHelper(context);
    }

    @Override
    public void openDb() {

        database = dbOpenHelper.getWritableDatabase();

    }

    @Override
    public void closeDb() {

        database.close();
    }

    @Override
    public void createUser(User user) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(UserContract.UserEntry.MOBILE, user.getMobile());
        contentValues.put(UserContract.UserEntry.NAME, user.getName());
        contentValues.put(UserContract.UserEntry.USER_TYPE, user.getType());
        contentValues.put(UserContract.UserEntry.EMAIL, user.getEmail());
        contentValues.put(UserContract.UserEntry.PASSWORD, user.getPassword());

        database.insert(UserContract.UserEntry.TABLE_NAME,null,contentValues);


    }

    @Override
    public void createTask(UserTask userTask) {



        ContentValues contentValues = new ContentValues();
        contentValues.put(TaskContract.TaskEntry.TASK_ID, userTask.getTask_id());
        contentValues.put(TaskContract.TaskEntry.TASK_TITLE, userTask.getTask_title());
        contentValues.put(TaskContract.TaskEntry.TASK_DESCRIPTION, userTask.getTask_description());
        contentValues.put(TaskContract.TaskEntry.TASK_DUE_DATE, userTask.getDue_date());
        contentValues.put(TaskContract.TaskEntry.TASK_FILE, userTask.getTask_file());
        contentValues.put(TaskContract.TaskEntry.TASK_STATUS, userTask.getStatus());
        contentValues.put(TaskContract.TaskEntry.ASSIGNED_TO, userTask.getAssigned_to());
        contentValues.put(TaskContract.TaskEntry.PROJECT_ID, userTask.getProject_id());
        contentValues.put(TaskContract.TaskEntry.PRIORITY, userTask.getPriority());

        database.insert(TaskContract.TaskEntry.TABLE_NAME,null,contentValues);

    }

    @Override
    public void createProject(Project project) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ProjectContract.ProjectEntry.PROJECT_ID, project.getProject_id());
        contentValues.put(ProjectContract.ProjectEntry.PROJECT_TITLE, project.getProject_title());
        contentValues.put(ProjectContract.ProjectEntry.PROJECT_DESCRIPTION, project.getProject_description());
        contentValues.put(ProjectContract.ProjectEntry.MEMBER_ID, project.getMember_id());
        contentValues.put(ProjectContract.ProjectEntry.ATTACHMENT_FILE_NAME, project.getAttach_file_name());
        contentValues.put(ProjectContract.ProjectEntry.START_DATE, project.getStart_date());
        contentValues.put(ProjectContract.ProjectEntry.END_DATE, project.getEnd_date());

        database.insert(ProjectContract.ProjectEntry.TABLE_NAME,null,contentValues);

    }



    @Override
    public Cursor readUser(Integer mobile, String password) {

        String selectUser = "SELECT * FROM "+ UserContract.UserEntry.TABLE_NAME+" WHERE "+ UserContract.UserEntry.MOBILE+" = "+mobile+" &&"+
                                                                                        UserContract.UserEntry.PASSWORD+" = "+password;
       Cursor cursor =  database.rawQuery(selectUser,null);
       return cursor;
    }

    @Override
    public void readUsers() {
        
    }


    @Override
    public void updateRow(User user) {

        String updateUser = "UPDATE "+ UserContract.UserEntry.TABLE_NAME+
                             " SET "
                   + UserContract.UserEntry.NAME+" = "+user.getName()
                   + UserContract.UserEntry.EMAIL+" = "+user.getEmail()
                    + UserContract.UserEntry.PASSWORD+" "+user.getPassword()
                         +" WHERE "
                    + UserContract.UserEntry.MOBILE +" = "+user.getMobile();

    }

    @Override
    public void deleteRow() {

    }
}
