import android.content.Context
import android.content.SharedPreferences

class SharedPrefManager(private val context: Context) {

    private val PREFS_NAME = "user_prefs"
    private val TOKEN_KEY = "user_token"

    private val sharedPref: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        val editor = sharedPref.edit()
        editor.putString(TOKEN_KEY, token)
        editor.apply()
    }

    fun getToken(): String? {
        return sharedPref.getString(TOKEN_KEY, null)
    }

    fun removeToken() {
        val editor = sharedPref.edit()
        editor.remove(TOKEN_KEY)
        editor.apply()
    }


}
