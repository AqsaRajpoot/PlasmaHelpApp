package com.example.plasmahelpapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.RatingBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.plasmahelpapp.R
import kotlinx.android.synthetic.main.activity_about_us.*
import kotlin.system.exitProcess

class AboutUs : AppCompatActivity() {
    lateinit var rBar:RatingBar
    lateinit var rbtn:Button

    lateinit var fbtn:Button
    lateinit var tbtn:Button
    lateinit var ibtn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)
        setSupportActionBar(toolbar_aboutus)
        rBar = findViewById(R.id.RatingBar)
        rbtn = findViewById<Button>(R.id.button_rating)
        fbtn=findViewById(R.id.fb)
        tbtn=findViewById(R.id.twitter)
        ibtn=findViewById(R.id.insta)

        var rat=rBar.numStars
        if(rat==null){
            rbtn.isEnabled=false
        }
        else{
            rbtn.isEnabled=true
        }
    }

    fun rbtn_click(view:View){

        val builder = AlertDialog.Builder(this)
        builder.setTitle("SUBMISSION")
        builder.setMessage("Thank You For Your Feedback")
        builder.setPositiveButton("submit") { dialog, which ->
            startActivity(Intent(this,AboutUs::class.java))

        }
        builder.setNegativeButton("cancel") { dialog, which ->
            dialog.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun fb_click(view: View){
        val url = "http://www.facebook.com."
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }
    fun twitter_click(view: View){
        val url = "https://apps.twitter.com."
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }
    fun insta_click(view: View){
        val url = "http://www.instagram.com."
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.search_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout -> {

                val builder = AlertDialog.Builder(this)
                builder.setTitle("Message")
                builder.setMessage("ARE YOU SURE,YOU WANT TO LOGOUT?")
                builder.setPositiveButton("Yes") { dialog, which ->
                    finishAffinity()

                }
                builder.setNegativeButton("No") { dialog, which ->
                    dialog.dismiss()
                }
                val dialog: AlertDialog = builder.create()
                dialog.show()

                //  Toast.makeText(applicationContext, "click on logout", Toast.LENGTH_LONG).show()
                true
            }
            R.id.aboutus ->{
                startActivity(Intent(this,AboutUs::class.java))
                return true
            }

            R.id.search ->{
                startActivity(Intent(this,SearchActivity::class.java))
                return true
            }
            R.id.home ->{
                startActivity(Intent(this,MainActivity::class.java))
                return true
            }
            R.id.refresh ->{
                val intent = intent
                finish()
                startActivity(intent)
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }


    }



}
