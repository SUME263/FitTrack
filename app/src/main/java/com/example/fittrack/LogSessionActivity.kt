package com.example.fittrack

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fittrack.models.LogSession




class LogSessionActivity : AppCompatActivity() {

    private lateinit var titleInput: EditText
    private lateinit var durationInput: EditText
    private lateinit var submitButton: Button

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: LogSessionAdapter
    private val sessions = mutableListOf<LogSession>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_session)

        titleInput = findViewById(R.id.editTextTitle)
        durationInput = findViewById(R.id.editTextDuration)
        submitButton = findViewById(R.id.buttonSubmit)

        // Get passed title
        val exerciseTitle = intent.getStringExtra("EXERCISE_TITLE")
        titleInput.setText(exerciseTitle)

        submitButton.setOnClickListener {
            val title = titleInput.text.toString()
            val duration = durationInput.text.toString()

            // TODO: Validate input and send to server
            sendSessionToServer(title, duration)
        }

        recyclerView = findViewById(R.id.recyclerViewSessions)
        adapter = LogSessionAdapter(sessions)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        fetchSessionsFromServer()  // Fetch existing sessions


        val backButton: Button = findViewById(R.id.buttonBack)
        backButton.setOnClickListener {
            finish() // Closes current activity
        }

    }

    private fun sendSessionToServer(title: String, duration: String) {
        val url = "http://10.0.2.2/fittrack_api/log_session.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            { response ->
                try {
                    val jsonResponse = JSONObject(response)
                    val success = jsonResponse.getBoolean("success")
                    val message = jsonResponse.getString("message")

                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

                    if (success) {
                        finish()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this, "Response error", Toast.LENGTH_SHORT).show()
                }
            },
            { error ->
                Toast.makeText(this, "Network error: ${error.message}", Toast.LENGTH_SHORT).show()
            }) {

            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["title"] = title
                params["duration"] = duration
                return params
            }
        }

        Volley.newRequestQueue(this).add(stringRequest)
    }

    private fun fetchSessionsFromServer() {
        val url = "http://10.0.2.2/fittrack_api/get_session.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                try {
                    val jsonResponse = JSONObject(response)
                    if (jsonResponse.getBoolean("success")) {
                        val sessionArray = jsonResponse.getJSONArray("sessions")
                        sessions.clear()

                        for (i in 0 until sessionArray.length()) {
                            val sessionObj = sessionArray.getJSONObject(i)
                            val title = sessionObj.getString("title")
                            val duration = sessionObj.getInt("duration")

                            sessions.add(LogSession(title, duration))
                        }

                        adapter.notifyDataSetChanged()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this, "Parse error", Toast.LENGTH_SHORT).show()
                }
            },
            { error ->
                Toast.makeText(this, "Error fetching sessions: ${error.message}", Toast.LENGTH_SHORT).show()
            })

        Volley.newRequestQueue(this).add(stringRequest)
    }


}
