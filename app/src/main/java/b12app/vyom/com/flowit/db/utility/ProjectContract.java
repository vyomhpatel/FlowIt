package b12app.vyom.com.flowit.db.utility;

import android.provider.BaseColumns;

public class ProjectContract {
//h
    public ProjectContract() {}

    /* Inner class that defines the table contents */
    public static abstract class ProjectEntry implements BaseColumns {
        public static final String TABLE_NAME = "project";
        public static final String ID = "project_table_id";
        public static final String PROJECT_ID = "project_id";
        public static final String PROJECT_TITLE = "project_title";
        public static final String PROJECT_DESCRIPTION = "project_description";
        public static final String MEMBER_ID = "member_id";
        public static final String START_DATE = "start_date";
        public static final String END_DATE = "end_date";
        public static final String ATTACHMENT_FILE_NAME = "attach_file_name";
    }
}
