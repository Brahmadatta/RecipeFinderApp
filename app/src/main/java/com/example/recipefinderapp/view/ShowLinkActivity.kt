package com.example.recipefinderapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recipefinderapp.R
import kotlinx.android.synthetic.main.activity_show_link.*

class ShowLinkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_link)

        var extras = intent.extras

        if (extras != null)
        {
            var link = extras.get("link")

            webviewId.loadUrl(link.toString())

        }
    }
}