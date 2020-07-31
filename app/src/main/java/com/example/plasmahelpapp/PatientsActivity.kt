package com.example.plasmahelpapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.example.plasmahelpapp.R

import com.example.plasmahelpapp.utils.Endpoints
import com.example.plasmahelpapp.utils.VolleySingleton
import kotlinx.android.synthetic.main.activity_donors.*
import kotlinx.android.synthetic.main.activity_patient.*
import kotlinx.android.synthetic.main.activity_patient.*
import kotlin.system.exitProcess

class PatientsActivity : AppCompatActivity() {
    lateinit var patientval: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient)
        setSupportActionBar(toolbar_patients)
        patientval=findViewById(R.id.patients_values)
        showpatients()
    }
    fun showpatients(){
        var e: Endpoints = Endpoints()
        val stringRequest: StringRequest = object : StringRequest(
            Request.Method.POST,e.patient_url ,
            object : Response.Listener<String?> {
                override fun onResponse(ServerResponse: String?) =
                    if(ServerResponse.equals("success")){
                        // Toast.makeText(this@DonorsActivity,ServerResponse, Toast.LENGTH_LONG).show()
                        var str=ServerResponse.toString()
                        patientval.setText(str)

                    }
                    else{
                        // Toast.makeText(this@DonorsActivity,ServerResponse, Toast.LENGTH_SHORT).show()
                        var str=ServerResponse.toString()
                        patientval.setText(str)
                    }
            },

            object : Response.ErrorListener {
                override fun onErrorResponse(volleyError: VolleyError) {
                    Toast.makeText(
                        applicationContext,
                        "Something went wrong",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.d("VOLLEY", volleyError.toString())
                }
            }) {
            override fun getParams(): Map<String, String> {
                val params: MutableMap<String, String> =
                    HashMap()

                return params
            }
        }

        VolleySingleton.getInstance(this)!!.addToRequestQueue(stringRequest)

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
