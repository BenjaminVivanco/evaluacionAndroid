package com.example.evaluacion1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

 //Librerias para thread
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText usuarioEditText;
    private EditText contrasenaEditText;
    private Spinner spinner;

    //Variables para thread
    private TextView textView;
    private ImageView imageView;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuarioEditText = findViewById(R.id.usuario);
        contrasenaEditText = findViewById(R.id.contraseña);
        spinner = findViewById(R.id.spinnerRoles);

        String[] roles = {"Administrador", "Usuario"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, roles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        //Enlace de ID
        textView = findViewById(R.id.text1);
        imageView = findViewById(R.id.imagenLogo);
        progressBar = findViewById(R.id.barraProgreso);


        //Implementación de thread, declaración de tiempo de espera y carga de imagen
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                        textView.setText("Carga completa");
                        imageView.setVisibility(View.VISIBLE);
                        imageView.setImageResource(R.drawable.crustaceo);
                    }
                });
            }
        });
        thread.start();


    }

    public void onClickAcceder(View view){
        String user = usuarioEditText.getText().toString().trim();
        String pass = contrasenaEditText.getText().toString().trim();
        String rol = spinner.getSelectedItem().toString();

        if(user.isEmpty()){
            Toast.makeText(this,"El campo usuario esta vacio", Toast.LENGTH_SHORT).show();
            return;
        }

        if(pass.isEmpty()){
            Toast.makeText(this,"El campo contraseña esta vacio", Toast.LENGTH_SHORT).show();
            return;
        }

        if(user.equals("usuario1") && pass.equals("1234") && rol.equals("Usuario")){
            Intent intent = new Intent(this, AccesoActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this,"Credenciales incorrectas", Toast.LENGTH_SHORT).show();
        }


    }



}