package br.com.zup.exercicioaula67.ui.main.viewmodel

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import br.com.zup.exercicioaula67.MyFirebaseMessagingService.Companion.MESSAGE_BODY_KEY
import br.com.zup.exercicioaula67.MyFirebaseMessagingService.Companion.MESSAGE_TITLE_KEY
import br.com.zup.exercicioaula67.MyFirebaseMessagingService.Companion.NEW_MESSAGE
import br.com.zup.exercicioaula67.domain.model.Message

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var _message = MutableLiveData<Message>()
    val message : LiveData<Message> = _message

    private var context = application


    private var messageReceiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            try {
                val title = intent?.getStringExtra(MESSAGE_TITLE_KEY).toString()
                val body = intent?.getStringExtra(MESSAGE_BODY_KEY).toString()
                val Message = Message(
                    title = title,
                    text = body
                )
                _message.value = Message
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    init {

        LocalBroadcastManager.getInstance(context).registerReceiver(
            (messageReceiver),
            IntentFilter(NEW_MESSAGE)
        )
    }
}