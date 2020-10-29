package com.ismin.android

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ismin.android.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val createBookActivityRequestCode = 1;
    private val TAG = MainActivity::class.simpleName

    private val bookshelf = Bookshelf()
    private val theLordOfTheRings = Book(
        title = "The Lord of the Rings",
        author = "J. R. R. Tolkien",
        date = "1954-02-15"
    )

    private val theHobbit = Book(
        title = "The Hobbit",
        author = "J. R. R. Tolkien",
        date = "1937-09-21"
    )
    private val aLaRechercheDuTempsPerdu = Book(
        title = "À la recherche du temps perdu",
        author = "Marcel Proust",
        date = "1927"
    );

    private lateinit var bookAdapter: BookAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.bookshelf.addBook(theLordOfTheRings)
        this.bookshelf.addBook(theHobbit)
        this.bookshelf.addBook(aLaRechercheDuTempsPerdu)

        bookAdapter = BookAdapter(bookshelf.getAllBooks())
        binding.aMainRcvBooks.adapter = bookAdapter
        val linearLayoutManager = LinearLayoutManager(this)
        binding.aMainRcvBooks.layoutManager = linearLayoutManager

        val dividerItemDecoration = DividerItemDecoration(this, linearLayoutManager.orientation)
        binding.aMainRcvBooks.addItemDecoration(dividerItemDecoration)
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
            bookAdapter.refreshData(bookshelf.getAllBooks())
            bookAdapter.notifyDataSetChanged()
        }
    }
}