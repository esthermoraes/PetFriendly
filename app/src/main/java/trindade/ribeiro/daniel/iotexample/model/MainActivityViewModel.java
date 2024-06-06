package trindade.ribeiro.daniel.iotexample.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivityViewModel extends AndroidViewModel {

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * Método que cria e executa uma requisição ao servidor web para ligar o LED
     * @return um LiveData que vai conter a resposta do servidor quando esta estiver disponível
     */
    public LiveData<Boolean> turnLedOn() {

        // Cria um container do tipo MutableLiveData (um LiveData que pode ter seu conteúdo alterado).
        MutableLiveData<Boolean> result = new MutableLiveData<>();

        // Cria uma nova linha de execução (thread). O android obriga que chamadas de rede sejam feitas
        // em uma linha de execução separada da principal.
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Executa a nova linha de execução. Dentro dessa linha, iremos realizar as requisições ao
        // servidor web.
        executorService.execute(new Runnable() {

            /**
             * Tudo o que colocármos dentro da função run abaixo será executada dentro da nova linha
             * de execução.
             */
            @Override
            public void run() {

                // Criamos uma instância de Repository. É dentro dessa classe que estão os
                // métodos que se comunicam com o ESP32.
                Repository repository = new Repository(getApplication());

                // O método turnLedOn envia uma requisição ao ESP32 pedindo que ele ligue o LED. Ele
                // retorna um booleano indicando true caso o ESP32 tenha realizado a ação e
                // false em caso contrário
                boolean b = repository.turnLedOn();

                // Aqui postamos o resultado da operação dentro do LiveData. Quando fazemos isso,
                // quem estiver observando o LiveData será avisado de que o resultado está disponível.
                result.postValue(b);
            }
        });

        return result;
    }

    /**
     * Método que cria e executa uma requisição ao servidor web para desligar o LED
     * @return um LiveData que vai conter a resposta do servidor quando esta estiver disponível
     */
    public LiveData<Boolean> turnLedOff() {

        // Cria um container do tipo MutableLiveData (um LiveData que pode ter seu conteúdo alterado).
        MutableLiveData<Boolean> result = new MutableLiveData<>();

        // Cria uma nova linha de execução (thread). O android obriga que chamadas de rede sejam feitas
        // em uma linha de execução separada da principal.
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Executa a nova linha de execução. Dentro dessa linha, iremos realizar as requisições ao
        // servidor web.
        executorService.execute(new Runnable() {

            /**
             * Tudo o que colocármos dentro da função run abaixo será executada dentro da nova linha
             * de execução.
             */
            @Override
            public void run() {

                // Criamos uma instância de Repository. É dentro dessa classe que estão os
                // métodos que se comunicam com o ESP32.
                Repository repository = new Repository(getApplication());

                // O método turnLedOff envia uma requisição ao ESP32 pedindo que ele desligue o LED. Ele
                // retorna um booleano indicando true caso o ESP32 tenha realizado a ação e
                // false em caso contrário
                boolean b = repository.turnLedOff();

                // Aqui postamos o resultado da operação dentro do LiveData. Quando fazemos isso,
                // quem estiver observando o LiveData será avisado de que o resultado está disponível.
                result.postValue(b);
            }
        });

        return result;
    }

    /**
     * Método que cria e executa uma requisição ao servidor web para pegar o status do LED
     * @return um LiveData que vai conter a resposta do servidor quando esta estiver disponível
     */
    public LiveData<Boolean> getLedStatus() {

        // Cria um container do tipo MutableLiveData (um LiveData que pode ter seu conteúdo alterado).
        MutableLiveData<Boolean> result = new MutableLiveData<>();

        // Cria uma nova linha de execução (thread). O android obriga que chamadas de rede sejam feitas
        // em uma linha de execução separada da principal.
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Executa a nova linha de execução. Dentro dessa linha, iremos realizar as requisições ao
        // servidor web.
        executorService.execute(new Runnable() {

            /**
             * Tudo o que colocármos dentro da função run abaixo será executada dentro da nova linha
             * de execução.
             */
            @Override
            public void run() {

                // Criamos uma instância de Repository. É dentro dessa classe que estão os
                // métodos que se comunicam com o ESP32.
                Repository repository = new Repository(getApplication());

                // O método getLedStatus envia uma requisição ao ESP32 pedindo que ele informe o status
                // do LED. Ele retorna um booleano indicando true caso o ESP32 tenha realizado a ação
                // e false em caso contrário
                boolean b = repository.getLedStatus();

                // Aqui postamos o resultado da operação dentro do LiveData. Quando fazemos isso,
                // quem estiver observando o LiveData será avisado de que o resultado está disponível.
                result.postValue(b);
            }
        });

        return result;
    }

    /**
     * Método que cria e executa uma requisição ao servidor web para ligar o Motor
     * @return um LiveData que vai conter a resposta do servidor quando esta estiver disponível
     */
    public LiveData<Boolean> turnMotorOn() {

        // Cria um container do tipo MutableLiveData (um LiveData que pode ter seu conteúdo alterado).
        MutableLiveData<Boolean> result = new MutableLiveData<>();

        // Cria uma nova linha de execução (thread). O android obriga que chamadas de rede sejam feitas
        // em uma linha de execução separada da principal.
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Executa a nova linha de execução. Dentro dessa linha, iremos realizar as requisições ao
        // servidor web.
        executorService.execute(new Runnable() {

            /**
             * Tudo o que colocármos dentro da função run abaixo será executada dentro da nova linha
             * de execução.
             */
            @Override
            public void run() {

                // Criamos uma instância de Repository. É dentro dessa classe que estão os
                // métodos que se comunicam com o ESP32.
                Repository repository = new Repository(getApplication());

                // O método turnMotorOn envia uma requisição ao ESP32 pedindo que ele ligue o motor. Ele
                // retorna um booleano indicando true caso o ESP32 tenha realizado a ação e
                // false em caso contrário
                boolean b = repository.turnMotorOn();

                // Aqui postamos o resultado da operação dentro do LiveData. Quando fazemos isso,
                // quem estiver observando o LiveData será avisado de que o resultado está disponível.
                result.postValue(b);
            }
        });

        return result;
    }

    /**
     * Método que cria e executa uma requisição ao servidor web para desligar o motor
     * @return um LiveData que vai conter a resposta do servidor quando esta estiver disponível
     */
    public LiveData<Boolean> turnMotorOff() {

        // Cria um container do tipo MutableLiveData (um LiveData que pode ter seu conteúdo alterado).
        MutableLiveData<Boolean> result = new MutableLiveData<>();

        // Cria uma nova linha de execução (thread). O android obriga que chamadas de rede sejam feitas
        // em uma linha de execução separada da principal.
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Executa a nova linha de execução. Dentro dessa linha, iremos realizar as requisições ao
        // servidor web.
        executorService.execute(new Runnable() {

            /**
             * Tudo o que colocármos dentro da função run abaixo será executada dentro da nova linha
             * de execução.
             */
            @Override
            public void run() {

                // Criamos uma instância de Repository. É dentro dessa classe que estão os
                // métodos que se comunicam com o ESP32.
                Repository repository = new Repository(getApplication());

                // O método turnMotorOff envia uma requisição ao ESP32 pedindo que ele desligue o motor. Ele
                // retorna um booleano indicando true caso o ESP32 tenha realizado a ação e
                // false em caso contrário
                boolean b = repository.turnMotorOff();

                // Aqui postamos o resultado da operação dentro do LiveData. Quando fazemos isso,
                // quem estiver observando o LiveData será avisado de que o resultado está disponível.
                result.postValue(b);
            }
        });

        return result;
    }

    /**
     * Método que cria e executa uma requisição ao servidor web para pegar o status do motor
     * @return um LiveData que vai conter a resposta do servidor quando esta estiver disponível
     */
    public LiveData<Boolean> getMotorStatus() {

        // Cria um container do tipo MutableLiveData (um LiveData que pode ter seu conteúdo alterado).
        MutableLiveData<Boolean> result = new MutableLiveData<>();

        // Cria uma nova linha de execução (thread). O android obriga que chamadas de rede sejam feitas
        // em uma linha de execução separada da principal.
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Executa a nova linha de execução. Dentro dessa linha, iremos realizar as requisições ao
        // servidor web.
        executorService.execute(new Runnable() {

            /**
             * Tudo o que colocármos dentro da função run abaixo será executada dentro da nova linha
             * de execução.
             */
            @Override
            public void run() {

                // Criamos uma instância de Repository. É dentro dessa classe que estão os
                // métodos que se comunicam com o ESP32.
                Repository repository = new Repository(getApplication());

                // O método getMotorStatus envia uma requisição ao ESP32 pedindo que ele informe o status
                // do motor. Ele retorna um booleano indicando true caso o ESP32 tenha realizado a ação
                // e false em caso contrário
                boolean b = repository.getMotorStatus();

                // Aqui postamos o resultado da operação dentro do LiveData. Quando fazemos isso,
                // quem estiver observando o LiveData será avisado de que o resultado está disponível.
                result.postValue(b);
            }
        });

        return result;
    }

    /**
     * Método que cria e executa uma requisição ao servidor web para ajustar a velocidade do motor
     * @return um LiveData que vai conter a resposta do servidor quando esta estiver disponível
     */
    public LiveData<Boolean> setMotorVel(int vel) {

        // Cria um container do tipo MutableLiveData (um LiveData que pode ter seu conteúdo alterado).
        MutableLiveData<Boolean> result = new MutableLiveData<>();

        // Cria uma nova linha de execução (thread). O android obriga que chamadas de rede sejam feitas
        // em uma linha de execução separada da principal.
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Executa a nova linha de execução. Dentro dessa linha, iremos realizar as requisições ao
        // servidor web.
        executorService.execute(new Runnable() {

            /**
             * Tudo o que colocármos dentro da função run abaixo será executada dentro da nova linha
             * de execução.
             */
            @Override
            public void run() {

                // Criamos uma instância de Repository. É dentro dessa classe que estão os
                // métodos que se comunicam com o ESP32.
                Repository repository = new Repository(getApplication());

                // O método setMotorVel envia uma requisição ao ESP32 pedindo que ele ajuste
                // a velocidade do motor. Ele retorna um booleano indicando true caso o ESP32 tenha
                // realizado a ação e false em caso contrário
                boolean b = repository.setMotorVel(vel);

                // Aqui postamos o resultado da operação dentro do LiveData. Quando fazemos isso,
                // quem estiver observando o LiveData será avisado de que o resultado está disponível.
                result.postValue(b);
            }
        });

        return result;
    }
}
