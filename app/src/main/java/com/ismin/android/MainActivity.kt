package com.ismin.android

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val createBookActivityRequestCode = 1;
    private val TAG = MainActivity::class.simpleName

    private val bookshelf = Bookshelf()

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
            val book = data?.extras?.get(CREATED_BOOK_EXTRA_KEY) as Book
            bookshelf.addBook(book)
        }
    }
}