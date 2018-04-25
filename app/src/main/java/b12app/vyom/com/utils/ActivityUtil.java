package b12app.vyom.com.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * @Package b12app.vyom.com.utils
 * @FileName ActivityUtil
 * @Date 4/24/18, 11:55 PM
 * @Author Created by fengchengding
 * @Description FlowIt
 */

public class ActivityUtil {

    public static void addFragmentToActivity(int layout, FragmentManager fragmentManager, Fragment fragment, String tag){
        fragmentManager.
                beginTransaction().
                replace(layout, fragment, tag).
                commit();
    }
}
