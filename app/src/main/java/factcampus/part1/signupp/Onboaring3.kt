package factcampus.part1.signupp

import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import factcampus.part1.signupp.databinding.ActivityOnboaring3Binding

class Onboaring3 : AppCompatActivity() {
    private lateinit var binding3: ActivityOnboaring3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding3 = ActivityOnboaring3Binding.inflate(layoutInflater)
        setContentView(binding3.root)

        binding3.btnNext3.setOnClickListener {
            val intent = Intent(this, Onboaring4::class.java)
            startActivity(intent)
        }

        onBackPressedDispatcher.addCallback(this){
            finishAffinity()
        }
    }
}