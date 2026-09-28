package com.example.madlab3.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.madlab3.R
import com.example.madlab3.utils.PreferenceManager
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ProfileActivity : AppCompatActivity() {

    private lateinit var prefManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        prefManager = PreferenceManager(this)
        
        // Get current user information
        val user = prefManager.getCurrentUser()
        
        // Set up UI elements
        val tvFullName = findViewById<TextView>(R.id.tvFullName)
        val tvUsername = findViewById<TextView>(R.id.tvUsername)
        val tvEmail = findViewById<TextView>(R.id.tvEmail)
        val tvJoinDate = findViewById<TextView>(R.id.tvJoinDate)
        val btnBack = findViewById<Button>(R.id.btnBack)
        val btnLogout = findViewById<Button>(R.id.btnLogout)
        
        // Display user information
        user?.let {
            tvFullName.text = it.fullName
            tvUsername.text = it.username
            tvEmail.text = it.email
            
            // Format join date if available
            it.createdAt?.let { timestamp ->
                val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
                val joinDate = dateFormat.format(Date(timestamp))
                tvJoinDate.text = getString(R.string.member_since, joinDate)
            }
        }
        
        // Set up button listeners
        btnBack.setOnClickListener { finish() }
        
        btnLogout.setOnClickListener {
            // Log out the user
            prefManager.logoutUser()
            
            // Redirect to login screen and clear activity stack
            val intent = Intent(this, LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(intent)
            finish()
        }
    }
}