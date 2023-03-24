package com.example.tarealogin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Inicio extends AppCompatActivity implements View.OnClickListener {

    Button btnEditar, btnEliminar, btnMostrar, btnSalir;
    TextView nombre;
    int id = 0;
    Usuario u;
    daoUsuario dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        nombre = (TextView) findViewById(R.id.nombreUsuario);
        btnEditar = (Button) findViewById(R.id.btnEditar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);
        btnMostrar = (Button) findViewById(R.id.btnMostrar);
        btnSalir = (Button) findViewById(R.id.btnSalir);
        btnEditar.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);
        btnMostrar.setOnClickListener(this);
        btnSalir.setOnClickListener(this);

        Bundle b = getIntent().getExtras();
        id = b.getInt("Id");
        dao = new daoUsuario(this);
        u = dao.getUsuarioById(id);
        nombre.setText(u.getNombre() + " " + u.getApellido());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEditar:
                Intent e = new Intent(Inicio.this, Editar.class);
                e.putExtra("Id", id);
                startActivity(e);
                break;
            case R.id.btnEliminar:
                //Eliminar
                AlertDialog.Builder b = new AlertDialog.Builder(this);
                b.setMessage("Seguro que quieres eliminar tu cuenta?");
                b.setCancelable(false);
                b.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(dao.deleteUsuario(id)){
                            Toast.makeText(Inicio.this, "Se eliminó correctamente", Toast.LENGTH_LONG).show();
                            Intent e = new Intent(Inicio.this, Main.class);
                            startActivity(e);
                            finish();
                        }else{
                            Toast.makeText(Inicio.this, "ERROR: No se pudo eliminar la cuenta", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                b.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                b.show();

                break;
            case R.id.btnMostrar:
                Intent m = new Intent(Inicio.this, Mostrar.class);
                startActivity(m);
                break;
            case R.id.btnSalir:
                Intent s = new Intent(Inicio.this, Main.class);
                startActivity(s);
                break;
        }
    }
}