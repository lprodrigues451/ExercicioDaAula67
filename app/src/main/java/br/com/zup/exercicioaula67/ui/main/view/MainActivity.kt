package br.com.zup.exercicioaula67.ui.main.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import br.com.zup.exercicioaula67.MyFirebaseMessagingService
import br.com.zup.exercicioaula67.databinding.ActivityMainBinding
import br.com.zup.exercicioaula67.ui.main.viewmodel.MainViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObserver()
        getCurrentMessagingToken()

        }

    @SuppressLint("SetTextI18n")
    private fun getCurrentMessagingToken(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener(
            OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    return@OnCompleteListener
                }
                val token = task.result
                Log.d(MyFirebaseMessagingService.TAG, "TokenExemplo : $token")
                binding.token.text = "Token : $token"
            }
        )
    }
    private fun initObserver(){

        viewModel.message.observe(this){
            binding.title.text = it.title
            binding.text.text = it.text
        }
    }

}