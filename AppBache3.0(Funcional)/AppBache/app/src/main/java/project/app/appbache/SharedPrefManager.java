package project.app.appbache;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPrefManager {
    private static SharedPrefManager instance;
    private static Context mCtx;

    private static String SHARED_PREF_NAME = "mysharedpref";
    private static String USER_EMAIL = "lferht@gmail.com";
    private static String USER_TYPE = "123";

    private SharedPrefManager(Context context) {
        mCtx = context;

    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPrefManager(context);
        }
        return instance;
    }

    public boolean userLogin ( String Correo, int user_type ){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences( SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(USER_EMAIL, Correo);
        editor.putInt(USER_TYPE, user_type);

        editor.apply();

        return true;
    }


    public boolean IsLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if ( sharedPreferences.getString(USER_EMAIL, null) != null ){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean Logout(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences( SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }

}
