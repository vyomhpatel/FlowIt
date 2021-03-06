package b12app.vyom.com.utils;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Package b12app.vyom.com.utils
 * @FileName StatusHelper
 * @Date 4/27/18, 12:21 AM
 * @Author Created by fengchengding
 * @Description FlowIt
 */

public class StatusHelper {

    @Retention(RetentionPolicy.SOURCE)
    public @interface StatusDef {
        int START_NEW_PROJECT = 1;
        int NOT_COMPLETE = 2;
        int COMPLETED = 3;
    }

    public static String getStatus(@StatusDef int status) {
        switch (status) {
            case StatusDef.START_NEW_PROJECT:
                return "Start new project";
            case StatusDef.NOT_COMPLETE:
                return "Not complete";
            case StatusDef.COMPLETED:
                return "Completed";
        }
        return null;
    }
}
