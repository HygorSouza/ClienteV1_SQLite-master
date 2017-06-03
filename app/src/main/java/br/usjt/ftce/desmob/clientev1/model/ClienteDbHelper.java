package br.usjt.ftce.desmob.clientev1.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Hygor on 9/19/15.
 */
public class ClienteDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Clientes.db";
    public static final String TEXT_TYPE = " TEXT";
    public static final String COMMA_SEP = ",";
    public static final String OPEN_PAR = "(";
    public static final String CLOSE_PAR = ")";
    public static final String SQL_CREATE_CLIENTE =
            "CREATE TABLE " + ClienteContract.ClienteEntry.TABLE_NAME + OPEN_PAR +
                    ClienteContract.ClienteEntry._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                    ClienteContract.ClienteEntry.COLUMN_NAME_CLIENTE_NOME + TEXT_TYPE +  COMMA_SEP +
                    ClienteContract.ClienteEntry.COLUMN_NAME_CLIENTE_FONE + TEXT_TYPE +  COMMA_SEP +
                    ClienteContract.ClienteEntry.COLUMN_NAME_CLIENTE_EMAIL + TEXT_TYPE + CLOSE_PAR;
    
    
    public static final String SQL_DROP_CLIENTE =
            "DROP TABLE IF EXISTS " + ClienteContract.ClienteEntry.TABLE_NAME;
   
    
    public ClienteDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_CLIENTE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //tabelas de parâmetros que podem ser recriadas
        db.execSQL(SQL_DROP_CLIENTE);
        db.execSQL(SQL_CREATE_CLIENTE);
    }

}
