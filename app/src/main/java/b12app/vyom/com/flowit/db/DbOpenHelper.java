package b12app.vyom.com.flowit.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import b12app.vyom.com.flowit.db.utility.ProjectContract;
import b12app.vyom.com.flowit.db.utility.TaskContract;
import b12app.vyom.com.flowit.db.utility.UserContract;


public class DbOpenHelper extends SQLiteOpenHelper {


    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_USER =
            "CREATE TABLE " + UserContract.UserEntry.TABLE_NAME + " (" +
                    UserContract.UserEntry.MOBILE + " INTEGER PRIMARY KEY," +
                    UserContract.UserEntry.USER_ID + " INTEGER " + COMMA_SEP +
                    UserContract.UserEntry.USER_TYPE + TEXT_TYPE + COMMA_SEP +
                    UserContract.UserEntry.EMAIL+ TEXT_TYPE + COMMA_SEP +
                    UserContract.UserEntry.PASSWORD + TEXT_TYPE +
                    " )";

    private static final String SQL_CREATE_TASK =
            "CREATE TABLE " + TaskContract.TaskEntry.TABLE_NAME + " (" +
                    TaskContract.TaskEntry.ID + " INTEGER PRIMARY KEY," +
                    TaskContract.TaskEntry.TASK_ID + " INTEGER," +
                    TaskContract.TaskEntry.TASK_TITLE + TEXT_TYPE + COMMA_SEP +
                    TaskContract.TaskEntry.TASK_DESCRIPTION+ TEXT_TYPE + COMMA_SEP +
                    TaskContract.TaskEntry.ASSIGNED_TO + " INTEGER " + COMMA_SEP +
                    TaskContract.TaskEntry.TASK_STATUS + TEXT_TYPE + COMMA_SEP +
                    TaskContract.TaskEntry.TASK_DUE_DATE+ TEXT_TYPE + COMMA_SEP+
                    TaskContract.TaskEntry.TASK_FILE+ TEXT_TYPE + COMMA_SEP +
                    TaskContract.TaskEntry.PROJECT_ID+ "INTEGER"+
                    " )";

    private static final String SQL_CREATE_PROJECT =
            "CREATE TABLE " + ProjectContract.ProjectEntry.TABLE_NAME + " (" +
                    ProjectContract.ProjectEntry.ID + " INTEGER PRIMARY KEY," +
                    ProjectContract.ProjectEntry.PROJECT_ID + " INTEGER," +
                    ProjectContract.ProjectEntry.PROJECT_TITLE + TEXT_TYPE + COMMA_SEP +
                    ProjectContract.ProjectEntry.PROJECT_DESCRIPTION+ TEXT_TYPE + COMMA_SEP +
                    ProjectContract.ProjectEntry.MEMBER_ID + " INTEGER," +
                    ProjectContract.ProjectEntry.START_DATE + TEXT_TYPE + COMMA_SEP +
                    ProjectContract.ProjectEntry.END_DATE + TEXT_TYPE + COMMA_SEP +
                    ProjectContract.ProjectEntry.ATTACHMENT_FILE_NAME + TEXT_TYPE +
                    " )";

    private static final String SQL_DELETE_USER =
            "DROP TABLE IF EXISTS " + UserContract.UserEntry.TABLE_NAME;

    private static final String SQL_DELETE_TASK =
            "DROP TABLE IF EXISTS " + TaskContract.TaskEntry.TABLE_NAME;

    private static final String SQL_DELETE_PROJECT =
            "DROP TABLE IF EXISTS " + ProjectContract.ProjectEntry.TABLE_NAME;

    public DbOpenHelper(Context context) {
        super(context, "user", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_USER);
        sqLiteDatabase.execSQL(SQL_CREATE_TASK);
        sqLiteDatabase.execSQL(SQL_CREATE_PROJECT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
