package com.ismin.android

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.ismin.android.databinding.ActivityCreateBookBinding

const val CREATED_BOOK_EXTRA_KEY = "CREATED_BOOK_EXTRA_KEY"


class CreateBookActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCreateBookBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateBookBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun saveBook(view: View) {
        val title = binding.aCreateBookEdtTitle.text.toString()
        val author = binding.aCreateBookEdtAuthor.text.toString()
        val date = binding.aCreateBookEdtDate.text.toString()

        val book = Book(title, author, date)
        val intent = Intent()
        intent.putExtra(CREATED_BOOK_EXTRA_KEY, book)
        setResult(RESULT_OK, intent)
        finish()
    }
}