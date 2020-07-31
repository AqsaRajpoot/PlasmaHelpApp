package com.example.plasmahelpapp


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.example.plasmahelpapp.R
import com.example.plasmahelpapp.utils.Endpoints
import com.example.plasmahelpapp.utils.VolleySingleton


class LoginActivity : AppCompatActivity() {
    private lateinit var signup: TextView
    private lateinit var username: EditText
    private lateinit var pass: EditText
    private lateinit var login_btn: Button
    private lateinit var login_image:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        signup = findViewById(R.id.l_signup)
        username = findViewById(R.id.l_name)
        pass = findViewById(R.id.l_pwd)
        login_btn = findViewById(R.id.l_btn)
        login_image=findViewById(R.id.imageView_anim)

        var ani: Animation = AnimationUtils.loadAnimation(applicationContext,R.anim.fade)
        login_image.startAnimation(ani)
    }

    fun signupclick(view: View) {
        startActivity(Intent(this, RegisterActivity::class.java))
    }


    fun loginclick(view: View) {
        var username1: String
        var password1: String
        username.setError(null)
        pass.setError(null)

        username1 = username.text.toString().trim()
        password1 = pass.text.toString().trim()


        if (isvalid(username1, password1)) {

            login(username1, password1)
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        }

    }

    fun ref(view: View){
        val intent = intent
        finish()
        startActivity(intent)
    }

    fun login(name: String,password: String){

        var e: Endpoints = Endpoints()
        val stringRequest: StringRequest = object : StringRequest(
            Request.Method.POST, e.login_url,
            Response.Listener<String?> { ServerResponse ->
                if (ServerResponse.equals("success")) {
                    Toast.makeText(this@LoginActivity, ServerResponse, Toast.LENGTH_SHORT).show()
                   // startActivity(Intent(this@LoginActivity, MainActivity::class.java))


                } else {
                    Toast.makeText(this@LoginActivity, ServerResponse, Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener { volleyError ->
                Toast.makeText(
                    applicationContext,
                    "Something went wrong",
                    Toast.LENGTH_SHORT
                )
                    .show()
                Log.d("VOLLEY", volleyError.toString())
            }) {
            override fun getParams(): Map<String, String> {
                val params: MutableMap<String, String> =
                    HashMap()
                params["name"] = name
                params["password"] = password
                return params
            }
        }
        VolleySingleton.getInstance(this)!!.addToRequestQueue(stringRequest)
    }

    fun isvalid(name:String,password:String):Boolean {


        if(name.isEmpty()){
            //  show_msg("NAME FIELD IS EMPTY")
            username.setError("FILL THIS FIELD")
            return false
        }
        else   if(password.isEmpty()){
            // show_msg("PASSWORD FIELD IS EMPTY")
            pass.setError("FILL THIS FIELD")
            return false
        }
        return true
    }
    fun show_msg(msg:String){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }
}








