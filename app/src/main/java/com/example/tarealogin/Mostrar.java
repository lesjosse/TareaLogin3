package com.example.tarealogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Mostrar extends AppCompatActivity {

    ListView lista;
    daoUsuario dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrar);

        lista = (ListView) findViewById(R.id.lista);
        dao = new daoUsuario(this);
        ArrayList<Usuario> l = dao.selectUsuarios();
        ArrayList<String> list = new ArrayList<>();

        for(Usuario u: l){
            list.add(u.getNombre() + " " + u.getApellido());
        }
        ArrayAdapter<String> a = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, list);
        lista.setAdapter(a);

    }
}