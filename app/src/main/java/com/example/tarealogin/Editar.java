package com.example.tarealogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Editar extends AppCompatActivity implements View.OnClickListener {

    EditText ediUser, ediPass, ediNombre, ediApellido;
    Button btnActualizar, btnCancelar;
    int id = 0;
    Usuario u;
    daoUsuario dao;
    Intent x;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar);

        ediUser = (EditText) findViewById(R.id.EdiUser);
        ediPass = (EditText) findViewById(R.id.EdiPass);
        ediNombre = (EditText) findViewById(R.id.EdiNombre);
        ediApellido = (EditText) findViewById(R.id.EdiApellido);
        btnActualizar = (Button) findViewById(R.id.btnEdiActualizar);
        btnCancelar = (Button) findViewById(R.id.btnEdiCancelar);
        btnActualizar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);


        Bundle b = getIntent().getExtras();
        id = b.getInt("Id");
        dao = new daoUsuario(this);
        u = dao.getUsuarioById(id);
        ediUser.setText(u.getUsuario());
        ediPass.setText(u.getPassword());
        ediNombre.setText(u.getNombre());
        ediApellido.setText(u.getApellido());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEdiActualizar:

                u.setUsuario(ediUser.getText().toString());
                u.setPassword(ediPass.getText().toString());
                u.setNombre(ediNombre.getText().toString());
                u.setApellido(ediApellido.getText().toString());
                if (!u.isNull()) {
                    Toast.makeText(this, "ERROR: Campos vacios", Toast.LENGTH_LONG).show();
                } else if (dao.updateUsuario(u)) {
                    Toast.makeText(this, "Actualización exitosa!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Editar.this, Inicio.class);
                    intent.putExtra("Id", u.getId());
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "No se puede registrar", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnEdiCancelar:

                Intent s = new Intent(Editar.this, Inicio.class);
                s.putExtra("Id", u.getId());
                startActivity(s);
                break;
        }
    }
}