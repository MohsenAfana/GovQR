package ps.gov.mtit.govqr.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by pdx on 4/5/2015.
 */
public class PrefsUtils {

    private static final String SHARED_NAME = "sso_conf";
    public static final String TOKEN = "token";
    public static final String REFRESH_TOKEN = "refresh_token";
    public static final String EMAIL = "email";
    public static final String PW = "password";
    public static final String USER = "user";
    public static final String LANG = "lang";

    public static final String ISFIRSTTIME = "isFirstTime";

    public static SharedPreferences getPrefs(Context context) {
        SharedPreferences settings = context.getSharedPreferences(SHARED_NAME,
                Context.MODE_PRIVATE);
        return settings;
    }

    public static void saveToken(Context context, String token) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString(TOKEN, token);
        editor.apply();
    }

    public static String getToken(Context context) {
        return getPrefs(context).getString(TOKEN, "");
    }

    public static void saveRefreshToken(Context context, String token) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString(REFRESH_TOKEN, token);
        editor.apply();
    }

    public static String getRefreshToken(Context context) {
        return getPrefs(context).getString(REFRESH_TOKEN, "");
    }


    public static void saveIsFirstTime(Context context, Boolean firstTime) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putBoolean(ISFIRSTTIME, firstTime);
        editor.apply();
    }

    public static boolean getIsFirstTime(Context context) {
        return getPrefs(context).getBoolean(ISFIRSTTIME, true);
    }


    public static void saveUser(Context context, String user) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString(USER, user);
        editor.apply();
    }

    public static String getUser(Context context) {
        return getPrefs(context).getString(USER, "");
    }


    public static void saveLang(Context context, String lang) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString(LANG, lang);
        editor.apply();
    }

    public static String getLang(Context context) {
        return getPrefs(context).getString(LANG, "en");
    }


    public static void saveEmail(Context context, String email) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString(EMAIL, email);
        editor.apply();
    }

    public static String getEmail(Context context) {
        return getPrefs(context).getString(EMAIL, "");
    }

    public static void savePw(Context context, String pw) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString(PW, pw);
        editor.apply();
    }

    public static String getPw(Context context) {
        return getPrefs(context).getString(PW, "");
    }


}
