package trindade.ribeiro.daniel.iotexample.model;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

import trindade.ribeiro.daniel.iotexample.util.Util;
import trindade.ribeiro.daniel.iotexample.util.Config;
import trindade.ribeiro.daniel.iotexample.util.HttpRequest;

public class Repository {

    Context context;
    public Repository(Context context) {
        this.context = context;
    }

    public boolean setQuantidade(String alimentacao, String h1, String h2, String m1, String m2, String peso) {

        // Cria uma requisição HTTP a ser enviada ao ESP32
        HttpRequest httpRequest = new HttpRequest("http://" + Config.getESP32Address(context) + "/ajustar_quant_aliment/" + alimentacao + "/" + h1 + "/" + m1 + "/" + "/" + h2 + "/" + m2 + "/" + peso , "GET", "UTF-8");

        String result = "";
        try {
            // Executa a requisição HTTP. É neste momento que o ESP32 é contactado. Ao executar
            // a requisição é aberto um fluxo de dados entre o ESP32 e a app (InputStream is).
            InputStream is = httpRequest.execute();

            // Obtém a resposta fornecida pelo ESP32. O InputStream é convertido em uma String.
            //
            // Em caso de motor ligado, 1. Desligado, 0:
            result = Util.inputStream2String(is, "UTF-8");

            Log.i("HTTP REGISTER RESULT", result);

            // Fecha a conexão com o ESP32.
            httpRequest.finish();

            // Se result igual a 1, significa que o usuário foi registrado com sucesso.
            if(result.equals("1")) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}