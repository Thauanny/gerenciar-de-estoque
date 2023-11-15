package com.example.primeiroapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "IMDMarket.db";
    private static final Integer DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "produto";
    private static final String COlUMN_NAME = "nome_produto";
    private static final String COlUMN_ID = "_id";
    private static final String COlUMN_COD_PRODUTO = "codigo_produto";
    private static final String COlUMN_DESC_PRODUTO = "descricao_produto";
    private static final String COlUMN_QUANTIDADE = "quantidade_produto";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COlUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COlUMN_NAME + " TEXT, " +
                        COlUMN_DESC_PRODUTO + " TEXT, " +
                        COlUMN_QUANTIDADE + " INTEGER, " +
                        COlUMN_COD_PRODUTO + " INTEGER);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insert(String name, String descricao, int quantidade, int cod_produto){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COlUMN_NAME, name);
        cv.put(COlUMN_COD_PRODUTO, cod_produto);
        cv.put(COlUMN_DESC_PRODUTO, descricao);
        cv.put(COlUMN_QUANTIDADE, quantidade);
        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Falha ao salvar dados. :(", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Sucesso ao salvar dados! :)", Toast.LENGTH_SHORT).show();
        }

    }
}
