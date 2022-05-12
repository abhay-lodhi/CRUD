package com.example.crud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.crud.DBHandler.DB
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        save.setOnClickListener{
            save()
        }

        show.setOnClickListener{
            show()
        }




    }

    fun save(){
        val name = name.text
        val age = age.text.toString()
        val database = DB(baseContext)
        val status= database.dataentry(name.toString(),Integer.parseInt(age))

        if(status<0){
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
        }

    }

    fun show(){
        val name1 = name.text.toString()
        val database = DB(baseContext)
        val not= database.datashow(Integer.parseInt(name1))
        if(not.name2==null){
            Toast.makeText(this, "ID does not exist", Toast.LENGTH_SHORT).show()
        }else{
            name.setText(not.name2+"Updated")
            age.setText(not.age.toString()+"Updated")
        }
    }
}