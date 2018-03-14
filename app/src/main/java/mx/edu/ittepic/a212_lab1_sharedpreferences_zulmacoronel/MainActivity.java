package mx.edu.ittepic.a212_lab1_sharedpreferences_zulmacoronel;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    Button salvar,cargar;

    EditText nombre, direccion, celular, horaini, horafin, fecha, platillos, postres,meseros;
    TextView avance;

    SeekBar barra;
    String concat,total;
    int valormeseros=0;

    CheckBox basica, lujo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getPreferences(MODE_PRIVATE);

        nombre=findViewById(R.id.editnombre);
        celular=findViewById(R.id.editcelular);
        direccion=findViewById(R.id.editevento);
        fecha=findViewById(R.id.editfecha);
        horaini=findViewById(R.id.edithoraini);
        horafin=findViewById(R.id.edithorafin);
        platillos=findViewById(R.id.editplatillos);
        postres=findViewById(R.id.editpostres);

        barra=findViewById(R.id.seekBar);
        avance = findViewById(R.id.tvResultado);
        total="";
        concat="";

        basica=findViewById(R.id.cbxbasica);
        lujo=findViewById(R.id.cbxlujo);

        cargar=findViewById(R.id.btncargar);
        salvar=findViewById(R.id.btnsave);

        cargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nombre.setText(sharedPreferences.getString("nombre",null));
                celular.setText(sharedPreferences.getString("celular",null));
                direccion.setText(sharedPreferences.getString("direccion",null));
                fecha.setText(sharedPreferences.getString("fecha",null));
                horaini.setText(sharedPreferences.getString("horaini",null));
                horafin.setText(sharedPreferences.getString("horafin",null));

                platillos.setText(""+sharedPreferences.getInt("platillos",0));
                postres.setText(""+sharedPreferences.getInt("postres",0));
                barra.setProgress(sharedPreferences.getInt("meseros",0));

                basica.setChecked(sharedPreferences.getBoolean("basica",false));
                lujo.setChecked(sharedPreferences.getBoolean("lujo",false));

            }
        });

        salvar=findViewById(R.id.btnsave);

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor = getPreferences(MODE_PRIVATE).edit();

                editor.putString("nombre", nombre.getText().toString());
                editor.putString("celular", celular.getText().toString());
                editor.putString("direccion", direccion.getText().toString());
                editor.putString("fecha", fecha.getText().toString());
                editor.putString("horaini", horaini.getText().toString());
                editor.putString("horafin", horafin.getText().toString());
                editor.putInt("platillos",  Integer.parseInt(platillos.getText().toString()));
                editor.putInt("postres",  Integer.parseInt(postres.getText().toString()));
                editor.putInt("meseros", valormeseros);

                editor.putBoolean("basica",basica.isChecked() );
                editor.putBoolean("lujo",lujo.isChecked() );
                editor.commit();

                nombre.setText("");
                celular.setText("");
                direccion.setText("");
                fecha.setText("");
                horaini.setText("");
                horafin.setText("");
                platillos.setText("");
                postres.setText("");

                barra.setProgress(0);
                basica.setChecked(false);
                lujo.setChecked(false);

            }
        });

        barra.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                total="";
                concat="";
                avance.setText("# "+progress);
                valormeseros=progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
}
