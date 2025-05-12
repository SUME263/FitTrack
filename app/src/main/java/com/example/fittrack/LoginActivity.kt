package com.example.fittrack

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.fittrack.ui.home.HomeFragment
import com.example.fittrack.ui.home.HomeViewModel
import org.json.JSONException
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize views
        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)
        registerButton = findViewById(R.id.registerButton)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show()
            } else {
                loginUser(username, password)
            }

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        registerButton.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
        }
    }

    private fun loginUser(username: String, password: String) {
        val url = "http://10.0.2.2/fittrack_api/login.php"

        val request = object : StringRequest(Method.POST, url,
            { response ->
                Log.d("LOGIN_RESPONSE", response)

                try {
                    val json = JSONObject(response)
                    val message = json.getString("message")
                    if (json.getBoolean("success")) {
                        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                    }
                } catch (e: JSONException) {
                    Log.e("LOGIN_ERROR", "JSON parsing failed: ${e.message}")
                    Toast.makeText(this, "Unexpected response from server", Toast.LENGTH_LONG).show()
                }
            },
            { error ->
                Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        ) {
            override fun getParams(): Map<String, String> = mapOf(
                "email" to username,
                "password" to password
            )
        }

        Volley.newRequestQueue(this).add(request)
    }
}
