package b12app.vyom.com.flowit.db;

import android.database.Cursor;

import b12app.vyom.com.flowit.model.Project;
import b12app.vyom.com.flowit.model.User;
import b12app.vyom.com.flowit.model.UserTask;

public interface IDbHelper {

    void openDb();

    void closeDb();

    void createUser(User user);

    void createTask(UserTask userTask);

    void createProject(Project project);


    Cursor readUser(Integer mobile, String password);

    void readUsers();



    void updateRow(User user);

    void deleteRow();

    public interface OnDataReceive{
        public void onSuccess(Cursor cursor);
        void onFailure();
    }


}
