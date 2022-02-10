package com.mxss.actividad_17;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DAOCliente extends SQLiteOpenHelper {
    private static final String base="VENTAS";
    private static final int version=1;
    private static final String tabla="CLIENTE";

    private static final String cod_cli="COD_CLI";
    private static final String nom_cli="NOM_CLI";
    private static final String ape_cli="APE_CLI";
    private static final String fon_cli="FONO_CLI";

    public DAOCliente (Context context){
        super(context, base, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL= "CREATE TABLE " + tabla + "(" +cod_cli + " text PRIMARY KEY, " + nom_cli+ " text, " +ape_cli+ " text, " +fon_cli+ " text)";
        db.execSQL(SQL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+ tabla);
                onCreate(db);

    }
    public void nuevoCliente(cliente objC){

        ContentValues values=new ContentValues();
        values.put(cod_cli,objC.getCodigo());
        values.put(nom_cli,objC.getNombres());
        values.put(ape_cli,objC.getApellidos());
        values.put(fon_cli,objC.getTelefono());

        SQLiteDatabase db=this.getWritableDatabase();
        db.insert(tabla, null, values);
        db.close();

    }
    public ArrayList listaClientes(){
        ArrayList listaClientes=new ArrayList();
        SQLiteDatabase db=getWritableDatabase();

        String SQL=" SELECT *FROM " + tabla;
        Cursor c=db.rawQuery(SQL,null);

        if (c.moveToFirst()) {
            do {
                listaClientes.add(c.getInt(0)+ " " + c.getString(1) + " " + c.getString(2) + " " + c.getString(3)+" ");
            } while (c.moveToNext());
        }
        return listaClientes;
            }
            public void actualizaCliente(cliente objC){
        int cod=objC.getCodigo();
        String nom=objC.getNombres();
        String ape=objC.getApellidos();
        String tel=objC.getTelefono();

        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("update CLIENTE set NOM_CLI='"+nom+"',APE_CLI='"+ape+"',FON_CLI='"+tel+"'where COD_CLI='"+cod+"'");
        db.close();

        }
        public void eliminarCliente(cliente objC) {

            int cod = objC.getCodigo();
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("delete from cliente where cod_cli=" +cod);
            db.close();
        }

    }

