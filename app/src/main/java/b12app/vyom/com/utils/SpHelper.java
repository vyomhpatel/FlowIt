package b12app.vyom.com.utils;

import android.content.Context;
import android.content.SharedPreferences;

import b12app.vyom.com.flowit.model.User;

/**
 * @Package b12app.vyom.com.utils
 * @FileName SpHelper
 * @Date 5/2/18, 11:39 PM
 * @Author Created by fengchengding
 * @Description FlowIt
 */

public class SpHelper {

    public static void saveUserInfo(SharedPreferences sharedPreferences, User user) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", user.getUseremail());
        editor.putString("fname", user.getUserfirstname());
        editor.putString("lname", user.getUserlastname());
        editor.putString("id", user.getUserid());
        editor.putString("apikey", user.getAppapikey());
        editor.commit();
    }

    public static void clearUserInfo(SharedPreferences sharedPreferences) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", "");
        editor.putString("fname", "");
        editor.putString("lname", "");
        editor.putString("id", "");
        editor.putString("apikey", "");
        editor.commit();
    }

    public static void saveUserType(SharedPreferences sharedPreferences, String type) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("type", type);
        editor.commit();
    }

    public static User getUserInfo(SharedPreferences sharedPreferences) {

        return new User("",
                sharedPreferences.getString("id", ""),
                sharedPreferences.getString("fname", ""),
                sharedPreferences.getString("lname", ""),
                sharedPreferences.getString("email", ""),
                sharedPreferences.getString("apikey", ""));
    }

    public static String getUserId(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString("id", "");
    }

    public static String getLastName(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString("lname", "");
    }

    public static String getFirstName(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString("fname", "");
    }

    public static String getEmail(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString("email", "");
    }

    public static String getApiKey(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString("apikey", "");
    }

    public static String getUserType(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString("type", "user");
    }
}
