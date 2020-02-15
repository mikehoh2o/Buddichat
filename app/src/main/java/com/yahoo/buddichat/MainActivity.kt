package com.yahoo.buddichat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log


class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        register_button_register.setOnClickListener {
            performRegister()
        }
            already_have_account_textview.setOnClickListener {
                Log.d("LoginActivity", "Try to show login activity")

                //launch the login activity
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    private fun performRegister() {
        val email = email_edittext_register.text.toString()
        val password = password_edittext_register.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this,
                "Please enter text in email and password",
                Toast.LENGTH_SHORT)
                .show()
            return
        }

        Log.d("MainActivity", "Email is " + email)
        Log.d("MainActivity", "Password: $password")
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("Main", "createUserWithEmail:success")
                    val user = auth.currentUser
                } else {
                    Log.w("Main", "createUserWithEmail:failure",
                        task.exception)
                    Toast.makeText(
                        baseContext, "Failed to create user.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

}