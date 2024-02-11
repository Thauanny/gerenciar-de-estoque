package com.example.primeiroapp.data.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.primeiroapp.data.model.ConstantesBancoDeDados;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "IMDMarket.db";
    private static final Integer DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "produto";
    private final Context context;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + ConstantesBancoDeDados.COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBancoDeDados.COLUNA_NOME + " TEXT, " +
                ConstantesBancoDeDados.COLUNA_DESCRICAO + " TEXT, " +
                ConstantesBancoDeDados.COLUNA_QUANTIDADE + " INTEGER, " +
                ConstantesBancoDeDados.COLUNA_CODIGO + " INTEGER, " +
                ConstantesBancoDeDados.NOTIFICAR + " INTEGER);";


        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insert(String name, String descricao, int quantidade, int cod_produto, int notificar) {
        if (dadoJaExiste(cod_produto)) {
            Toast.makeText(context, "Dado já inserido anteriormente", Toast.LENGTH_SHORT).show();


        } else {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put( ConstantesBancoDeDados.COLUNA_NOME, name);
            cv.put( ConstantesBancoDeDados.COLUNA_CODIGO, cod_produto);
            cv.put( ConstantesBancoDeDados.COLUNA_DESCRICAO, descricao);
            cv.put( ConstantesBancoDeDados.COLUNA_QUANTIDADE, quantidade);
            cv.put( ConstantesBancoDeDados.NOTIFICAR, notificar);


            long result =  db.insert(TABLE_NAME, null, cv);

            if (result == -1) {
                Toast.makeText(context, "Falha ao salvar dados. :(", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Sucesso ao salvar dados! :)", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @SuppressLint("Range")
    public boolean dadoJaExiste(int codigo) {
        Cursor cursor = read();
        Integer cod = null;

        if (cursor.getCount() == 0) {
            return false;

        } else {
            while (cursor.moveToNext()) {
                cod = cursor.getInt(cursor.getColumnIndex(ConstantesBancoDeDados.COLUNA_CODIGO));

            }
        }

        return cod == codigo;
    }


    public Cursor read() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public void remove(String codigo) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "codigo_produto=?", new String[]{codigo});
        if (result == -1 || result == 0) {
            Toast.makeText(context, "Produto não localizado, tente outro!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Deletado com sucesso", Toast.LENGTH_SHORT).show();
        }
    }


    public void update(String name, String descricao, int quantidade, int cod_produto, int notificar) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String codigo = String.valueOf(cod_produto);

        cv.put( ConstantesBancoDeDados.COLUNA_NOME, name);
        cv.put( ConstantesBancoDeDados.COLUNA_CODIGO, cod_produto);
        cv.put( ConstantesBancoDeDados.COLUNA_DESCRICAO, descricao);
        cv.put( ConstantesBancoDeDados.COLUNA_QUANTIDADE, quantidade);
        cv.put( ConstantesBancoDeDados.NOTIFICAR, notificar);

        long result = db.update(TABLE_NAME, cv, "codigo_produto=?",  new String[]{codigo});
        if(result == -1){
            Toast.makeText(context, "Falha ao atualizar dados. :(", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Sucesso ao atualizar dados! :)", Toast.LENGTH_SHORT).show();
        }

    }

}
