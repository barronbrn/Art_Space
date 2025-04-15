package com.example.art_space

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    data class Art(
        val imageResId: Int,
        val title: String,
        val artist: String,
        val year: String
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val artworks = listOf(
            Art(R.drawable.art1, "Shahzia Sikander", "Vincent van Gogh", "2025"),
            Art(R.drawable.art2, "A Fluke", "Rebecca Manson", "2023"),
            Art(R.drawable.art3, "Dream a little dream", "Michaela Yearwood-Dan", "2025")
        )

        var currentIndex = 0

        // Ambil referensi view dari XML
        val imageView = findViewById<ImageView>(R.id.imageViewArt)
        val titleView = findViewById<TextView>(R.id.textViewTitle)
        val artistView = findViewById<TextView>(R.id.textViewArtist)
        val buttonPrev = findViewById<Button>(R.id.buttonPrev)
        val buttonNext = findViewById<Button>(R.id.buttonNext)

        // Fungsi untuk update tampilan
        fun updateArt() {
            val art = artworks[currentIndex]
            imageView.setImageResource(art.imageResId)
            titleView.text = art.title
            artistView.text = "${art.artist}, ${art.year}"
        }

        updateArt()

        // Event listener tombol
        buttonPrev.setOnClickListener {
            currentIndex = if (currentIndex == 0) artworks.lastIndex else currentIndex - 1
            updateArt()
        }

        buttonNext.setOnClickListener {
            currentIndex = (currentIndex + 1) % artworks.size
            updateArt()
        }
    }


}