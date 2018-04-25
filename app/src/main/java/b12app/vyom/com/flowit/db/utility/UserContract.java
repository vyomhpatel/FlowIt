package b12app.vyom.com.flowit.db.utility;

import android.provider.BaseColumns;

public class UserContract {

    public UserContract() {}

    /* Inner class that defines the table contents */
    public static abstract class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String USER_ID = "user_id";
        public static final String NAME = "name";
        public static final String USER_TYPE = "user_type";
        public static final String MOBILE = "mobile";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
    }
}
