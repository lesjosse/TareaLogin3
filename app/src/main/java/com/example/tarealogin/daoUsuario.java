package com.example.tarealogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class daoUsuario {
    Context c;
    Usuario u;
    ArrayList<Usuario> lista;
    SQLiteDatabase sql;
    String bd = "BDUsuarios";
    String tabla = "CREATE TABLE IF NOT EXISTS usuario(id integer PRIMARY KEY AUTOINCREMENT, usuario text, pass text, nombre text, ap text)";

    public daoUsuario(Context c) {
        this.c = c;
        //Creamos la DB
        sql = c.openOrCreateDatabase(bd, c.MODE_PRIVATE, null);
        //Creamos la tabla
        sql.execSQL(tabla);
        u = new Usuario();
    }

    //Método para insert
    public boolean insertUsuario(Usuario u) {
        if (buscar(u.getUsuario()) == 0) {
            ContentValues cv = new ContentValues();
            cv.put("Usuario", u.getUsuario());
            cv.put("pass", u.getPassword());
            cv.put("nombre", u.getNombre());
            cv.put("ap", u.getApellido());
            return (sql.insert("usuario", null, cv) > 0);
        } else {
            return false;
        }
    }

    //Método para buscar
    public int buscar(String u) {
        int x = 0;
        lista = selectUsuarios();
        for (Usuario us : lista) {
            if (us.getUsuario().equals(u)) {
                x++;
            }
        }
        return x;
    }

    //Método para listar
    public ArrayList<Usuario> selectUsuarios() {
        ArrayList<Usuario> lista = new ArrayList<>();
        lista.clear();
        Cursor cr = sql.rawQuery("SELECT * FROM usuario", null);
        if (cr != null && cr.moveToFirst()) {
            do {
                Usuario u = new Usuario();
                u.setId(cr.getInt(0));
                u.setUsuario(cr.getString(1));
                u.setPassword(cr.getString(2));
                u.setNombre(cr.getString(3));
                u.setApellido(cr.getString(4));
                lista.add(u);

            } while (cr.moveToNext());
        }
        return lista;
    }

    public int login(String u, String p) {
        int a = 0;
        Cursor cr = sql.rawQuery("SELECT * FROM usuario", null);
        if (cr != null && cr.moveToFirst()) {
            do {
                if (cr.getString(1).equals(u) && cr.getString(2).equals(p)) {
                    a++;
                }

            } while (cr.moveToNext());
        }

        return a;
    }

    public Usuario getUsuario(String u, String p) {
        lista = selectUsuarios();
        for (Usuario us : lista) {
            if (us.getUsuario().equals(u) && us.getPassword().equals(p)) {
                return us;
            }
        }
        return null;
    }

    public Usuario getUsuarioById(int id) {
        lista = selectUsuarios();
        for (Usuario us : lista) {
            if (us.getId() == id) {
                return us;
            }
        }
        return null;
    }

    public boolean updateUsuario(Usuario u) {
        ContentValues cv = new ContentValues();
        cv.put("Usuario", u.getUsuario());
        cv.put("pass", u.getPassword());
        cv.put("nombre", u.getNombre());
        cv.put("ap", u.getApellido());
        return (sql.update("usuario", cv, "id = " + u.getId(), null) > 0);

    }

    public boolean deleteUsuario(int id) {
        return (sql.delete("usuario", "id = " + id, null) > 0);
    }
}

