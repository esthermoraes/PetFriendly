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

    private String h1 = "";
    private String h2 = "";
    private String m1 = "";
    private String m2 = "";
    private String q = "";

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
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                textViewHora1.setText(String.format("%02d:%02d", hourOfDay, minute));
                                h1 = String.valueOf(hourOfDay);
                                m1 = String.valueOf(minute);
                            }
                        }, hour, minute, false);
                timePickerDialog.show();
            }
        });

        ImageButton imageButtonHora2 = findViewById(R.id.imageButtonHora2);
        imageButtonHora2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                textViewHora2.setText(String.format("%02d:%02d", hourOfDay, minute));
                                h2 = String.valueOf(hourOfDay);
                                m2 = String.valueOf(minute);
                            }
                        }, hour, minute, false);
                timePickerDialog.show();
            }
        });

        tvQuantGramaRes = findViewById(R.id.tvQuantGramaRes);
        SeekBar skQuantAli = findViewById(R.id.skQuantAli);
        skQuantAli.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvQuantGramaRes.setText(String.valueOf(progress));
                q = String.valueOf(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (h1.isEmpty() || m1.isEmpty() || h2.isEmpty() || m2.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Preencha todas as horas de alimentação!", Toast.LENGTH_SHORT).show();
                    return;
                }

                MainActivityViewModel vm = new ViewModelProvider(MainActivity.this).get(MainActivityViewModel.class);
                LiveData<Boolean> resultLd = vm.setSchedule(h1, h2, m1, m2, q);
                resultLd.observe(MainActivity.this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        if (aBoolean) {
                            Toast.makeText(MainActivity.this, "Comedouro atualizado", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Falha ao atualizar o comedouro", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_tb, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.opConfig) {
            LayoutInflater inflater = getLayoutInflater();
            View configDlgView = inflater.inflate(R.layout.config_dlg, null);
            EditText etESP32Address = configDlgView.findViewById(R.id.etESP32Address);
            etESP32Address.setText(Config.getESP32Address(this));

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(configDlgView);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    String esp32Address = etESP32Address.getText().toString();
                    Config.setESP32Address(MainActivity.this, esp32Address);
                }
            });
            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // Do nothing
                }
            });
            builder.create().show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
