package factcampus.part1.signupp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import factcampus.part1.signupp.databinding.ActivityOnboaring6Binding

class Onboaring6 : AppCompatActivity() {

    private lateinit var binding6: ActivityOnboaring6Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding6 = ActivityOnboaring6Binding.inflate(layoutInflater)
        setContentView(binding6.root)

        // 🔹 Onboaring5 dan kelgan ma'lumotlarni olish
        val userName = intent.getStringExtra("USER_NAME") ?: "User Name"
        val cardNumber = intent.getStringExtra("CARD_NUMBER") ?: "0000000000000000"
        val cardType = intent.getStringExtra("CARD_TYPE") ?: "none"

        // 🔹 Karta raqamini 4 xonadan keyin bo‘lib chiqish
        val formattedCardNumber = cardNumber.chunked(4).joinToString(" ")

        // 🔹 Foydalanuvchi ismini va karta raqamini chiqarish
        binding6.tvUserName.text = userName
        binding6.tvCardHolder.text = userName
        binding6.tvCardNumber.text = formattedCardNumber

        // 🔹 Karta turiga qarab iconni o‘zgartirish
        when (cardType) {
            "visa" -> binding6.imgCardLogo.setImageResource(R.drawable.visa)
            "mastercard" -> binding6.imgCardLogo.setImageResource(R.drawable.mastercard)
            "paypal" -> binding6.imgCardLogo.setImageResource(R.drawable.paypal)
            else -> binding6.imgCardLogo.setImageResource(0)
        }
    }
}
