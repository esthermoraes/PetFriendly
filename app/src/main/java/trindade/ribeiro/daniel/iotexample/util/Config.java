package trindade.ribeiro.daniel.iotexample.util;

import android.content.Context;
import android.content.SharedPreferences;

public class Config {

    /**
     * Salva o endereço do ESP32 no espaço reservado da app
     * @param context contexto da app
     * @param login o login
     */
    public static void setESP32Address(Context context, String login) {
        SharedPreferences mPrefs = context.getSharedPreferences("configs", 0);
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString("esp32address", login).commit();
    }

    /**
     * Obtem o o endereço do ESP32 salvo no espaço reservado da app
     * @param context
     * @return
     */
    public static String getESP32Address(Context context) {
        SharedPreferences mPrefs = context.getSharedPreferences("configs", 0);
        return mPrefs.getString("esp32address", "");
    }
}
