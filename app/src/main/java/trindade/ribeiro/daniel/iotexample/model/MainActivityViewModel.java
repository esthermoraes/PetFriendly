package trindade.ribeiro.daniel.iotexample.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivityViewModel extends AndroidViewModel {

    private final Repository repository;

    public MainActivityViewModel(@NonNull Application application)  {
        super(application);
        repository = new Repository(application);
    }

    /**
     * Método que cria e executa uma requisição ao servidor web para ligar o LED
     * @return um LiveData que vai conter a resposta do servidor quando esta estiver disponível
     */
    public LiveData<Boolean> setSchedule(String h1, String h2, String m1, String m2, String q) {

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
                boolean b = repository.setQuantidade(h1, h2, m1, m2, q);

                // Aqui postamos o resultado da operação dentro do LiveData. Quando fazemos isso,
                // quem estiver observando o LiveData será avisado de que o resultado está disponível.
                result.postValue(b);
            }
        });

        return result;
    }
}