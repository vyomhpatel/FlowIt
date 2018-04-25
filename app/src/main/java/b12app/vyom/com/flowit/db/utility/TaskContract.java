package b12app.vyom.com.flowit.db.utility;

import android.provider.BaseColumns;

public class TaskContract {
//h
    public TaskContract() {}

    /* Inner class that defines the table contents */
    public static abstract class TaskEntry implements BaseColumns {
        public static final String TABLE_NAME = "task";
        public static final String ID = "task_table_id";
        public static final String TASK_ID = "task_id";
        public static final String TASK_TITLE = "task_title";
        public static final String TASK_DESCRIPTION = "task_description";
        public static final String ASSIGNED_TO = "assigned_to";
        public static final String PRIORITY = "priority";
        public static final String TASK_STATUS = "task_status";
        public static final String TASK_DUE_DATE = "task_due_date";
        public static final String TASK_FILE = "task_file";
        public static final String PROJECT_ID = "project_id";
    }
}
