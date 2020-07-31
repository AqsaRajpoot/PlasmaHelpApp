package com.example.plasmahelpapp



import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.example.plasmahelpapp.R
import com.example.plasmahelpapp.utils.Endpoints
import com.example.plasmahelpapp.utils.VolleySingleton


class RegisterActivity : AppCompatActivity() {

    private lateinit var name: EditText
    private lateinit var city: EditText
    private lateinit var number: EditText
    private lateinit var bg: EditText
    private lateinit var pwd: EditText
    private lateinit var status:EditText
    private lateinit var btn:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)




        name=findViewById(R.id.r_name)
        city=findViewById(R.id.r_city)
        number=findViewById(R.id.r_number)
        bg=findViewById(R.id.r_bloodgroup)
        pwd=findViewById(R.id.r_pwd)
        status=findViewById(R.id.r_status)
        btn=findViewById(R.id.btn_reg)





    }


    fun onclick(view: View) {
        var name1: String
        var city1: String
        var number1: String
        var bg1: String
        var status1: String
        var pwd1: String
        name1 = name.text.toString().trim()
        city1 = city.text.toString().trim()

        number1 = number.text.toString().trim()

        bg1 = bg.text.toString().trim()

        status1 = status.text.toString().trim()

        pwd1 = pwd.text.toString().trim()


        if (isvalid(name1, city1, number1, bg1, status1, pwd1)) {

            register(name1,city1,bg1,status1,number1,pwd1)
            startActivity(Intent(this,MainActivity::class.java))
        }

    }

    fun rref(view: View){
        val intent = intent
        finish()
        startActivity(intent)
    }

    fun register(name: String,city: String,blood_group: String,status: String,number: String,password: String){
        var e:Endpoints=Endpoints()
        val stringRequest: StringRequest = object : StringRequest(
            Request.Method.POST,e.register_url ,
            object : Response.Listener<String?> {
                override fun onResponse(ServerResponse: String?) {
                    if(ServerResponse.equals("success")){
                        Toast.makeText(this@RegisterActivity,ServerResponse,Toast.LENGTH_LONG).show()

                    }
                    else{
                        Toast.makeText(this@RegisterActivity,ServerResponse,Toast.LENGTH_LONG).show()

                    }
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
                params["name"] = name
                params["city"] = city
                params["blood_group"] = blood_group
                params["status"] = status
                params["number"] = number
                params["password"] = password
                return params
            }
        }
        VolleySingleton.getInstance(this)!!.addToRequestQueue(stringRequest)

    }

    //CODE FOR FIELDS VALIDATION
    fun isvalid(name:String,city:String,number:String,blood_group:String,status:String,password:String):Boolean {
        val valid_blood_groups:List<String> = listOf("A+","A-","B+","B-","AB+","AB-","O+","O-")


        if(name.isEmpty()){

            show_msg("NAME IS EMPTY")

            return false
        }
        else   if(city.isEmpty()){
            show_msg("CITY IS EMPTY")
            return false
        }

        else   if(status.isEmpty()){
            show_msg("Status IS EMPTY")
            return false
        }
        else   if(password.isEmpty()){
            show_msg("PASSWORS IS EMPTY")
            return false
        }
        else   if((number.isEmpty()) ){
            show_msg("Number  IS EMPTY")
            return false
        }
        else if((!valid_blood_groups.contains(blood_group)) or blood_group.isEmpty()){
            show_msg("YOU EITHER ENTERED THE INVALID FIELD ")
            return false
        }
        else if( blood_group.isEmpty()){
            show_msg(" NOTHING ENTERED IN BLOOD GROUP")
            return false
        }
        else if( number.length!==11){
            show_msg(" Number should be of 11 digits")
            return false
        }


        return true

    }

    //SHOWING ERROR MESSAGE
    fun show_msg(msg:String){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }

}

