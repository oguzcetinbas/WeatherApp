
import android.Manifest
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.hw13_weatherapp.R
import com.example.hw13_weatherapp.view.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        showNotification(message)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    private fun showNotification(remoteMessage: RemoteMessage) {

        val notification = NotificationCompat.Builder(this, "NOTI_CHANNEL_ID")
            .setSmallIcon(R.drawable.baseline_message_24)
            .setContentTitle(remoteMessage.data["tittle"])
            .setContentText(remoteMessage.data["message"])
            .setPriority(NotificationCompat.COLOR_DEFAULT)
            .setContentIntent(getPendingIntent())
            .build()

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED)
            NotificationManagerCompat.from(this).notify(1, notification)

    }
    private fun getPendingIntent(): PendingIntent {
        val intent = Intent(
            this,
            MainActivity::class.java
        )
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        return PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
    }
}