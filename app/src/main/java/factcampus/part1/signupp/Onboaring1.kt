package factcampus.part1.signupp

import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import factcampus.part1.signupp.databinding.ActivityOnboaring1Binding

class Onboaring1 : AppCompatActivity() {
    private lateinit var binding: ActivityOnboaring1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboaring1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener {
            val intent = Intent(this, Onboaring2::class.java)
            startActivity(intent)
        }

        onBackPressedDispatcher.addCallback(this){
            finishAffinity()
        }
    }
}