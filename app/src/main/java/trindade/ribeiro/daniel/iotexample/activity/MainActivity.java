package trindade.ribeiro.daniel.iotexample.activity;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.Calendar;

import trindade.ribeiro.daniel.iotexample.R;
import trindade.ribeiro.daniel.iotexample.model.MainActivityViewModel;
import trindade.ribeiro.daniel.iotexample.util.Config;

public class MainActivity extends AppCompatActivity {

    private TextView tvQuantGramaRes;
    private TextView textViewHora1, textViewHora2;
    private Button btnConfirmar;

    private String h1, h2, m1, m2, q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvQuantGramaRes = findViewById(R.id.tvQuantGramaRes);
        textViewHora1 = findViewById(R.id.textViewHora1);
        textViewHora2 = findViewById(R.id.textViewHora2);
        btnConfirmar = findViewById(R.id.btnConfirmar);

        Toolbar toolbar = findViewById(R.id.tbMain);
        setSupportActionBar(toolbar);

        ImageButton imageButtonHora1 = findViewById(R.id.imageButtonHora1);
        imageButtonHora1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting the
                // instance of our calendar.
                final Calendar c = Calendar.getInstance();

                // on below line we are getting our hour, minute.
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                // on below line we are initializing our Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                // on below line we are setting selected time
                                // in our text view.
                                textViewHora1.setText(hourOfDay + ":" + minute);
                                h1 = String.valueOf(hourOfDay);
                                m1 = String.valueOf(minute);
                            }
                        }, hour, minute, false);
                // at last we are calling show to
                // display our time picker dialog.
                timePickerDialog.show();
            }
        });

        ImageButton imageButtonHora2 = findViewById(R.id.imageButtonHora2);
        imageButtonHora2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting the
                // instance of our calendar.
                final Calendar c = Calendar.getInstance();

                // on below line we are getting our hour, minute.
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                // on below line we are initializing our Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                // on below line we are setting selected time
                                // in our text view.
                                textViewHora2.setText(hourOfDay + ":" + minute);
                                h2 = String.valueOf(hourOfDay);
                                m2 = String.valueOf(minute);
                            }
                        }, hour, minute, false);
                // at last we are calling show to
                // display our time picker dialog.
                timePickerDialog.show();
            }
        });

        // obtem o textview que mostra a quant atual do motor
        TextView tvQuantGramaRes = findViewById(R.id.tvQuantGramaRes);

        // obtém o slider de velocidade
        SeekBar skQuantAli = findViewById(R.id.skQuantAli);
        skQuantAli.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            // esse método é chamado sempre que o usuário modifica o slider de quantidade. Sempre
            // que isso acontece, atualizamos o valor que é mostrado no textview que exibe a
            // quantidade
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvQuantGramaRes.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            // esse método é chamado assim que o usuário para de ajusta o slider
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                q = String.valueOf(seekBar.getProgress());
            }
        });


        // Ação do botão Confirmar
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(h1.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Preencha a hora da primeira alimentacao!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(h2.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Preencha a hora da primeira alimentacao!", Toast.LENGTH_SHORT).show();
                    return;
                }


                // obtem o viewmodel correspondente. É através do viewmodel que é possível obter os
                // estados do LED e motor, acioná-los e configurá-los
                MainActivityViewModel vm = new ViewModelProvider(MainActivity.this).get(MainActivityViewModel.class);
                LiveData<Boolean> resultLd = vm.setSchedule("alimentacao", h1, h2, m1, m2, q);
                resultLd.observe(MainActivity.this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        if(aBoolean) {
                            Toast.makeText(MainActivity.this, "Comedouro atualizado", Toast.LENGTH_SHORT).show();
                            String atualizar = "ok";
                            System.out.print(atualizar);
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Comedouro não atualizado", Toast.LENGTH_SHORT).show();
                        }
                        return;
                    }
                });
            }
        });
    }

    // preenche o menu da toolbar com os itens de ações definidos no xml main_activity_tb
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_tb, menu);
        return true;
    }

    // método que é chamado toda vez que um item da toolbar é chamado
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        // caso o usuário clique na ação de configurar, exibimos uma caixa de diálogo que
        // permite que o usuário configure o endereço do ESP32
        if (item.getItemId() == R.id.opConfig) {

            // Constrói o layout da caixa de diálogo
            LayoutInflater inflater = getLayoutInflater();
            View configDlgView = inflater.inflate(R.layout.config_dlg, null);

            // obtem a caixa de texto dentro da caixa de diálogo usada pelo usuário
            EditText etESP32Address = configDlgView.findViewById(R.id.etESP32Address);

            // setamos na caixa de texto o valor atual de endereço do ESP32
            etESP32Address.setText(Config.getESP32Address(this));

            // Construção da caixa de diálogo
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            // definimos o layout que a caixa de diálogo vai ter
            builder.setView(configDlgView);

            // definimos o que acontece quando clicamos no botão OK da caixa de diálogo
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    // obtemos o endereço do ESP32 definido pelo usuário
                    String esp32Address = etESP32Address.getText().toString();

                    // salvamos esse novo endereço no arquivo de configuração da app
                    Config.setESP32Address(MainActivity.this, esp32Address);
                }
            });

            // se clicar em cancelar, não fazemos nada
            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                }
            });

            // depois de configurada a caixa, a criamos e exibimos
            builder.create().show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


