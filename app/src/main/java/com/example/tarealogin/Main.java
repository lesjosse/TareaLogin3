package com.example.tarealogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main extends AppCompatActivity implements View.OnClickListener {

    EditText user, pass;
    Button btnEntrar, btnRegistrar;
    daoUsuario dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        user = (EditText) findViewById(R.id.User);
        pass = (EditText) findViewById(R.id.Pass);
        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);

        btnEntrar.setOnClickListener(this);
        btnRegistrar.setOnClickListener(this);
        dao = new daoUsuario(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEntrar:
                String u = user.getText().toString();
                String p = pass.getText().toString();
                if (u.equals("") && p.equals("")) {
                    Toast.makeText(this, "ERROR! Campos vacíos", Toast.LENGTH_LONG).show();
                } else if (dao.login(u, p) == 1) {
                    Usuario us = dao.getUsuario(u, p);
                    Toast.makeText(this, "Successful!", Toast.LENGTH_LONG).show();
                    Intent intt = new Intent(Main.this, Inicio.class);
                    intt.putExtra("Id", us.getId());
                    startActivity(intt);
                    finish();
                }else{
                    Toast.makeText(this, "ERROR! Usuario o contraseña invalidos", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnRegistrar:
                Intent i = new Intent(Main.this, Registrar.class);
                startActivity(i);

                break;
        }
    }
}