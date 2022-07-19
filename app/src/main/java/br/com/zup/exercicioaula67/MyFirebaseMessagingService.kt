package br.com.zup.exercicioaula67

import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import br.com.zup.exercicioaula67.domain.model.Message
import br.com.zup.exercicioaula67.ui.main.viewmodel.MainViewModel
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        val newToken = token
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        remoteMessage.notification?.let {
            val broadcaster = LocalBroadcastManager.getInstance(baseContext)
            val intent = Intent(NEW_MESSAGE)
            intent.putExtra(MESSAGE_TITLE_KEY, it.title)
            intent.putExtra(MESSAGE_BODY_KEY, it.body)
            broadcaster.sendBroadcast(intent)
        }

    }

    companion object{
        const val NEW_MESSAGE = "NEW_MESSAGE"
        const val MESSAGE_TITLE_KEY = "MESSAGE_TITLE_KEY"
        const val MESSAGE_BODY_KEY = "MESSAGE_BODY_KEY"
        const val TAG = "Token"

    }
}