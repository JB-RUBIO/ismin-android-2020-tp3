package com.ismin.android

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val createBookActivityRequestCode = 1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun goToCreation(view: View) {
        val intent = Intent(this, CreateBookActivity::class.java)
        startActivityForResult(intent, this.createBookActivityRequestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == this.createBookActivityRequestCode) {
            val book = data?.extras?.get(CREATED_BOOK_EXTRA_KEY);
            Toast.makeText(this, book.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}