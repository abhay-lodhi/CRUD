package com.example.crud.DBHandler

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.database.getIntOrNull
import androidx.core.database.getStringOrNull
import com.example.crud.NotesModelClass

class DB(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "CRUD"

        private val TABLE_NOTES = "NOTES"


        private val KEY_ID = "_id"
       private val KEY_NAME="NAME"
        private val KEY_AGE= "AGE"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        //p0?.execSQL("DROP TABLE "+ TABLE_TRANSACTIONS)

        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_NOTES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME +" TEXT,"+ KEY_AGE+" INTEGER )")

        p0?.execSQL(CREATE_CONTACTS_TABLE)


    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("DROP TABLE IF EXISTS $TABLE_NOTES")
        onCreate(p0)
    }

    fun dataentry(name:String, age:Int): Long{
        val status:Long
        val db= this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(KEY_NAME, name)
        contentValues.put(KEY_AGE, age)
        status= db.insert(TABLE_NOTES,null,contentValues)

     db.close()
     return status
    }

    fun datashow(id:Int): NotesModelClass{
        var notes:NotesModelClass
        val db= this.readableDatabase
        var cs:Cursor? = null

        cs = db.rawQuery("SELECT * FROM "+ TABLE_NOTES + " WHERE "+ KEY_ID+ " = "+id, null)

        cs.moveToFirst()

        notes= NotesModelClass( cs.getIntOrNull(cs.getColumnIndex(KEY_AGE)),cs.getStringOrNull(cs.getColumnIndex(KEY_NAME)))
        return notes
    }
//SELECT * FROM NOTES WHERE ID = ID
    }

