package com.example.crudapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.crudapp.ui.crud.CrudFormFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, CrudFormFragment())
            .commit()
    }
}