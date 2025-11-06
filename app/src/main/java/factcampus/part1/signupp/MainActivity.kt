package factcampus.part1.signupp

import android.content.Intent
import android.os.Bundle
import android.os.Looper
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import factcampus.part1.signupp.databinding.ActivityMainBinding
import java.util.logging.Handler

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        android.os.Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, Onboaring1::class.java)
            startActivity(intent)
            finish()
        }, 3000)


        onBackPressedDispatcher.addCallback(this){
            finishAffinity()
        }
    }



}