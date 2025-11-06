package factcampus.part1.signupp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.InputFilter
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import factcampus.part1.signupp.databinding.ActivityOnboaring5Binding

class Onboaring5 : AppCompatActivity() {

    private lateinit var binding5: ActivityOnboaring5Binding
    private val cardNumberSpaces = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding5 = ActivityOnboaring5Binding.inflate(layoutInflater)
        setContentView(binding5.root)

        // --- CARD NUMBER TEXTWATCHER ---
        val textWatcher = CreditCardNumberFormattingTextWatcher()
        binding5.cardnumber.addTextChangedListener(textWatcher)

        textWatcher.setOnCreditCardNumberChangedListener(object :
            CreditCardNumberFormattingTextWatcher.OnCreditCardNumberChangedListener {
            override fun onCreditNumberChanged(cardNumber: String) {

                // Rang holati
                if (cardNumber.length == 16 + cardNumberSpaces) {
                    binding5.cardnumber.setTextColor(Color.parseColor("#3F704D"))
                } else if (cardNumber.length > 16 + cardNumberSpaces) {
                    binding5.cardnumber.setTextColor(Color.RED)
                } else {
                    binding5.cardnumber.setTextColor(Color.BLACK)
                }

                // Icon aniqlash
                when {
                    cardNumber.startsWith("4430") -> binding5.icCard.setImageResource(R.drawable.visa)
                    cardNumber.startsWith("4770") -> binding5.icCard.setImageResource(R.drawable.mastercard)
                    cardNumber.startsWith("3362") -> binding5.icCard.setImageResource(R.drawable.paypal)
                    else -> binding5.icCard.setImageResource(0)
                }

                // Counter
                binding5.txtCounter.text = "${cardNumber.replace(" ", "").length}/16"
            }
        })

        // --- PHONE NUMBER FORMATTER ---
        binding5.btnPhone.filters = arrayOf(InputFilter.LengthFilter(13)) // 010 1234 5678 (13 belgi)
        val phoneWatcher = object : TextWatcher {
            private var isFormatting = false
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: android.text.Editable?) {
                if (isFormatting) return
                isFormatting = true

                val digitsOnly = s?.toString()?.replace("\\D".toRegex(), "") ?: ""
                val limited = if (digitsOnly.length > 11) digitsOnly.substring(0, 11) else digitsOnly

                val part1 = if (limited.length >= 3) limited.substring(0, 3) else limited
                val part2 = if (limited.length > 3) {
                    val end = kotlin.math.min(7, limited.length)
                    limited.substring(3, end)
                } else ""
                val part3 = if (limited.length > 7) limited.substring(7) else ""

                val formatted = buildString {
                    append(part1)
                    if (part2.isNotEmpty()) append(" ").append(part2)
                    if (part3.isNotEmpty()) append(" ").append(part3)
                }

                if (formatted != s.toString()) {
                    binding5.btnPhone.setText(formatted)
                    binding5.btnPhone.setSelection(formatted.length)
                }
                isFormatting = false
            }
        }
        binding5.btnPhone.addTextChangedListener(phoneWatcher)

        // --- BACK BUTTON ---
        binding5.btnBack.setOnClickListener {
            val intent = Intent(this, Onboaring4::class.java)
            startActivity(intent)
            finish()
        }

        // --- SIGN UP BUTTON ---
        binding5.btnSignUp.setOnClickListener {
            val fullName = binding5.btnUserName.text.toString().trim()
            val cardNumber = binding5.cardnumber.text.toString().replace(" ", "")
            val phoneFormatted = binding5.btnPhone.text.toString().trim()
            val phoneDigits = phoneFormatted.replace("\\D".toRegex(), "") // faqat raqamlar

            // 1️⃣ Full Name
            if (fullName.isEmpty()) {
                Toast.makeText(this, "Iltimos, ismni kiriting", Toast.LENGTH_SHORT).show()
                binding5.btnUserName.requestFocus()
                return@setOnClickListener
            }

            // 2️⃣ Card Number
            if (cardNumber.isEmpty()) {
                Toast.makeText(this, "Karta raqamini kiriting", Toast.LENGTH_SHORT).show()
                binding5.cardnumber.requestFocus()
                return@setOnClickListener
            }
            if (cardNumber.length < 16) {
                Toast.makeText(this, "Karta raqami 16 ta raqamdan iborat bo‘lishi kerak", Toast.LENGTH_SHORT).show()
                binding5.cardnumber.requestFocus()
                return@setOnClickListener
            }

            // 3️⃣ Phone Number
            if (phoneDigits.isEmpty()) {
                Toast.makeText(this, "Telefon raqamini kiriting", Toast.LENGTH_SHORT).show()
                binding5.btnPhone.requestFocus()
                return@setOnClickListener
            }
            if (phoneDigits.length != 11) {
                Toast.makeText(this, "Telefon raqam 11 ta raqamdan iborat bo‘lishi kerak (misol: 010 1234 5678)", Toast.LENGTH_SHORT).show()
                binding5.btnPhone.requestFocus()
                return@setOnClickListener
            }

            // ✅ Hammasi to‘g‘ri — keyingi oynaga o‘tish
            Toast.makeText(this, "Muvaffaqiyatli ro‘yxatdan o‘tildi ✅", Toast.LENGTH_SHORT).show()

            // 🔹 Onboaring6 sahifasiga ism, karta raqami va icon turini yuboramiz
            val intent = Intent(this, Onboaring6::class.java)
            intent.putExtra("USER_NAME", fullName)
            intent.putExtra("CARD_NUMBER", cardNumber)

            val cardType = when {
                cardNumber.startsWith("4430") -> "visa"
                cardNumber.startsWith("4770") -> "mastercard"
                cardNumber.startsWith("3362") -> "paypal"
                else -> "none"
            }
            intent.putExtra("CARD_TYPE", cardType)

            startActivity(intent)
        }
    }
}
