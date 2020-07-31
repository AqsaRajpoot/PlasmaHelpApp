package com.example.plasmahelpapp


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.example.plasmahelpapp.R
import com.example.plasmahelpapp.utils.Endpoints
import com.example.plasmahelpapp.utils.VolleySingleton

class SearchActivity : AppCompatActivity() {
    lateinit var s_city:EditText
    lateinit var s_bg:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        s_city=findViewById(R.id.search_city)
        s_bg=findViewById(R.id.search_bloodgroup)
    }


    fun search(view: View){
        var city=s_city.text.toString().trim()
        var bg=s_bg.text.toString().trim()
        if(isvalid(city, bg)){
            val intent = Intent(this,SearchResults::class.java);
            intent.putExtra("City", city)
            intent.putExtra("Blood Group", bg)
            startActivity(intent);
        }



    }

    fun isvalid(city:String,bg:String):Boolean {
        val valid_blood_groups:List<String> = listOf("A+","A-","B+","B-","AB+","AB-","O+","O-","a+","a-","b+","b-","ab+","ab-","o+","o-")

        if(city.isEmpty()){

            s_city.setError("FILL THIS FIELD")
            return false
        }
        else if((!valid_blood_groups.contains(bg))){
            Toast.makeText(this,"YOU EITHER ENTERED THE INVALID FIELD OR NOTHING ENTERED IN BLOOD GROUP",Toast.LENGTH_SHORT).show()
            return false

        }
        else   if(bg.isEmpty()){

            s_bg.setError("FILL THIS FIELD")
            return false
        }
        return true
    }

}
