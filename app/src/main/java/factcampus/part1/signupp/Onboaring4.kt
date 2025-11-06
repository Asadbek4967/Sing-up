package factcampus.part1.signupp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import factcampus.part1.signupp.databinding.ActivityOnboaring4Binding

class Onboaring4 : AppCompatActivity() {

    private lateinit var binding4: ActivityOnboaring4Binding
    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding4 = ActivityOnboaring4Binding.inflate(layoutInflater)
        setContentView(binding4.root)

        // Tugma bosilishi doimiy bo'lsin — lekin vizual holatini matn bilan o'zgartiramiz
        binding4.btnSignIn.isEnabled = true
        updateSignInVisual() // dastlabki ko'rinish

        // TextWatcher maydonlarni kuzatib, tugma ko'rinishini (alpha) yangilaydi
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { updateSignInVisual() }
            override fun afterTextChanged(s: Editable?) {}
        }
        binding4.etEmail.addTextChangedListener(textWatcher)
        binding4.etPassword.addTextChangedListener(textWatcher)

        // Back tugmasi -> Onboaring3 ga o'tish
        binding4.btnBack.setOnClickListener {
            val intent = Intent(this, Onboaring3::class.java)
            startActivity(intent)
            finish()
        }

        // Parol ko'rsatish/yashirish
        binding4.ivTogglePassword.setOnClickListener { togglePasswordVisibility() }

        // Sign In bosilganda: agar maydonlar bo'sh bo'lsa toast va error, aks holda keyingi oynaga o'tish
        binding4.btnSignIn.setOnClickListener {
            val email = binding4.etEmail.text.toString().trim()
            val password = binding4.etPassword.text.toString()

            // Agar bo'sh maydonlar bo'lsa — toast bilan xabar beramiz va maydonga error qo'yamiz
            if (email.isEmpty() || password.isEmpty()) {
                if (email.isEmpty()) {
                    binding4.etEmail.error = "Emailni kiriting"
                }
                if (password.isEmpty()) {
                    binding4.etPassword.error = "Parolni kiriting"
                }
                Toast.makeText(this, "Iltimos, email va parolni kiriting", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Email formati to'g'riligi
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding4.etEmail.error = "To'g'ri email kiriting"
                Toast.makeText(this, "Iltimos, to'g'ri email kiriting", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Password minimal uzoqligi
            if (password.length < 6) {
                binding4.etPassword.error = "Parol kamida 6 belgidan iborat bo'lishi kerak"
                Toast.makeText(this, "Parol kamida 6 belgidan ibor bo'lsin", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // HAMMASI TO'G'RI -> Onboaring5 ga o'tamiz
            val intent = Intent(this, Onboaring5::class.java)
            intent.putExtra("email", email)
            startActivity(intent)
        }
    }

    // Tugma vizualini (alpha) yangilovchi yordamchi: agar ikkala maydon bo'sh bo'lsa, alpha kamayadi
    private fun updateSignInVisual() {
        val email = binding4.etEmail.text.toString().trim()
        val password = binding4.etPassword.text.toString()
        val anyFilled = email.isNotEmpty() || password.isNotEmpty()
        binding4.btnSignIn.alpha = if (anyFilled) 1.0f else 0.6f
        // tugma har doim bosilishi mumkin bo'lib qoladi (isEnabled = true)
    }

    // Parolni ko'rsatish / yashirish
    private fun togglePasswordVisibility() {
        if (isPasswordVisible) {
            // hozir ko'rinmoqda — yashiramiz
            binding4.etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            // iconni o'zgartirish: o'zingdagiga mos ravishda nomni almashtir
            binding4.ivTogglePassword.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.hidden))
            isPasswordVisible = false
        } else {
            // hozir yashiringan — ko'rsatamiz
            binding4.etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            binding4.ivTogglePassword.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.hidden2))
            isPasswordVisible = true
        }
        // kursor oxirida qolishi uchun
        binding4.etPassword.setSelection(binding4.etPassword.text.length)
    }
}
