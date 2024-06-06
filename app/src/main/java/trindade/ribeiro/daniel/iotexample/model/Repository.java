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

    /**
     * Método que cria uma requisição HTTP para ligar o LED.
     * @return true se o LED foi ligado com sucesso
     */
    public boolean turnLedOn() {

        // Cria uma requisição HTTP a ser enviada ao ESP32
        HttpRequest httpRequest = new HttpRequest("http://" + Config.getESP32Address(context) + "/ligar_led", "GET", "UTF-8");

        String result = "";
        try {
            // Executa a requisição HTTP. É neste momento que o ESP32 é contactado. Ao executar
            // a requisição é aberto um fluxo de dados entre o ESP32 e a app (InputStream is).
            InputStream is = httpRequest.execute();

            // Obtém a resposta fornecida pelo ESP32. O InputStream é convertido em uma String.
            //
            // Em caso de sucesso, 1. Falha, 0:
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

    /**
     * Método que cria uma requisição HTTP e obtem o estado do LED.
     * @return true se o LED esta ligado
     */
    public boolean getLedStatus() {

        // Cria uma requisição HTTP a ser enviada ao ESP32
        HttpRequest httpRequest = new HttpRequest("http://" + Config.getESP32Address(context) + "/pegar_status_led", "GET", "UTF-8");

        String result = "";
        try {
            // Executa a requisição HTTP. É neste momento que o ESP32 é contactado. Ao executar
            // a requisição é aberto um fluxo de dados entre o ESP32 e a app (InputStream is).
            InputStream is = httpRequest.execute();

            // Obtém a resposta fornecida pelo ESP32. O InputStream é convertido em uma String.
            //
            // Em caso de sucesso, 1. Falha, 0:
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

    /**
     * Método que cria uma requisição HTTP para desligar o LED.
     * @return true se o LED foi ligado com sucesso
     */
    public boolean turnLedOff() {

        // Cria uma requisição HTTP a ser enviada ao ESP32
        HttpRequest httpRequest = new HttpRequest("http://" + Config.getESP32Address(context) + "/desligar_led", "GET", "UTF-8");

        String result = "";
        try {
            // Executa a requisição HTTP. É neste momento que o ESP32 é contactado. Ao executar
            // a requisição é aberto um fluxo de dados entre o ESP32 e a app (InputStream is).
            InputStream is = httpRequest.execute();

            // Obtém a resposta fornecida pelo ESP32. O InputStream é convertido em uma String.
            //
            // Em caso de sucesso, 1. Falha, 0:
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

    /**
     * Método que cria uma requisição HTTP para ligar o motor.
     * @return true se o LED foi ligado com sucesso
     */
    public boolean turnMotorOn() {

        // Cria uma requisição HTTP a ser enviada ao ESP32
        HttpRequest httpRequest = new HttpRequest("http://" + Config.getESP32Address(context) + "/ligar_motor", "GET", "UTF-8");

        String result = "";
        try {
            // Executa a requisição HTTP. É neste momento que o ESP32 é contactado. Ao executar
            // a requisição é aberto um fluxo de dados entre o ESP32 e a app (InputStream is).
            InputStream is = httpRequest.execute();

            // Obtém a resposta fornecida pelo ESP32. O InputStream é convertido em uma String.
            //
            // Em caso de sucesso, 1. Falha, 0:
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

    /**
     * Método que cria uma requisição HTTP para desligar o motor.
     * @return true se o LED foi ligado com sucesso
     */
    public boolean turnMotorOff() {

        // Cria uma requisição HTTP a ser enviada ao ESP32
        HttpRequest httpRequest = new HttpRequest("http://" + Config.getESP32Address(context) + "/desligar_motor", "GET", "UTF-8");

        String result = "";
        try {
            // Executa a requisição HTTP. É neste momento que o ESP32 é contactado. Ao executar
            // a requisição é aberto um fluxo de dados entre o ESP32 e a app (InputStream is).
            InputStream is = httpRequest.execute();

            // Obtém a resposta fornecida pelo ESP32. O InputStream é convertido em uma String.
            //
            // Em caso de sucesso, 1. Falha, 0:
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

    /**
     * Método que cria uma requisição HTTP e obtem o estado do motor.
     * @return true se o motor esta ligado
     */
    public boolean getMotorStatus() {

        // Cria uma requisição HTTP a ser enviada ao ESP32
        HttpRequest httpRequest = new HttpRequest("http://" + Config.getESP32Address(context) + "/pegar_status_motor", "GET", "UTF-8");

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

    /**
     * Método que cria uma requisição HTTP para ajustar a velocidade do Motor.
     * @return true se o motor esta ligado
     */
    public boolean setMotorVel(int vel) {

        // Cria uma requisição HTTP a ser enviada ao ESP32
        HttpRequest httpRequest = new HttpRequest("http://" + Config.getESP32Address(context) + "/ajustar_vel_motor/" + String.valueOf(vel), "GET", "UTF-8");

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
