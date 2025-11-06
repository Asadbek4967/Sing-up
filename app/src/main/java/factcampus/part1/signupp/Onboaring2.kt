package factcampus.part1.signupp

import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import factcampus.part1.signupp.databinding.ActivityOnboaring2Binding

class Onboaring2 : AppCompatActivity() {
    private lateinit var binding2: ActivityOnboaring2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding2 = ActivityOnboaring2Binding.inflate(layoutInflater)
        setContentView(binding2.root)

        binding2.btnNext2.setOnClickListener {
            val intent = Intent(this, Onboaring3::class.java)
            startActivity(intent)
        }
        onBackPressedDispatcher.addCallback(this){
            finishAffinity()
        }
    }
}