package dev.seh.socketpushnoti.service;

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.provider.SyncStateContract
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import dev.seh.socketpushnoti.R
import dev.seh.socketpushnoti.network.SocketIOAPI
import dev.seh.socketpushnoti.ui.activity.MainActivity
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * @author : seungHo
 * @since : 2022-01-14
 * class : WorkMangerEx.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description :
 */

class WorkMangerEx(private val appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {
    private val notiId:String = "noti_id"
    override fun doWork(): Result {
        val number = 10
        val result = number * number
        Log.e("T", "Sd")
        CoroutineScope(Dispatchers.IO).launch {
            val mSocket: Socket = IO.socket("http://3.38.214.119:8080/chat")
            mSocket.connect()
            mSocket.on(SocketIOAPI.CHAT,Emitter.Listener{
                Timber.e("no : ${it[1]}")
                Handler(Looper.getMainLooper()).post{
                    Toast.makeText(appContext,"알림이 왔습니다. ${it[0]} ${it[1]} ${it[2]}",Toast.LENGTH_SHORT).show()
                    sendNotification("${it[1]}","${it[2]}")
                }

            })
        }
        Handler(Looper.getMainLooper()).post {
        }

        return Result.success()
    }

    private fun sendNotification(user:String,content:String) {

        // TODO Step 2: Add the notification id.
        // In our case the notification id is 0. If you are dealing with dynamic functionality then you can have it as unique for every notification.
        val notification_id = 0
        // END

        // TODO Step 4: Create an intent instance that we want to navigate the user when it is clicked.
        // START
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        // Pass the notification id as intent extra to handle the code when user is navigated in the app with notification.
        intent.putExtra(notiId, notification_id)
        // END

        // TODO Step 5: Create an instance of Notification Manager.
        // START
        /**
         * Class to notify the user of events that happen.  This is how you tell
         * the user that something has happened in the background.
         */
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        // END

        // TODO Step 7: Define the Notification Title and SubTitle.
        // START
        val titleNotification = user
        val subtitleNotification = content
        // END


        // TODO Step 11: Create the style of the Notification. You can create the style as you want here we will create a notification using BigPicture. For Example InboxStyle() which is used for simple Text message.
        // START
        // The style of the Notification. You can create the style as you want here we will create a notification using BigPicture.
        // For Example InboxStyle() which is used for simple Text message.

        // TODO Step 12: Define the pending intent for Notification.
        // START
        val pendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, 0)
        // END

        // TODO Step 13: Before building the Notification Builder add the notification icon. You can have look the note file where I have mentioned the step How to generate it.

        // TODO Step 14: Now as we most of the required params so lets build the Notification Builder.
        // START
        val notification =
            /**
             * @param context A {@link Context} that will be used to construct the
             *      RemoteViews. The Context will not be held past the lifetime of this
             *      Builder object.
             * @param channelId The constructed Notification will be posted on this
             *      NotificationChannel.
             */
            NotificationCompat.Builder(applicationContext, notiId)
                // Set the Notification Title
                .setContentTitle(titleNotification)
                // Set the Notification SubTitle
                .setContentText(subtitleNotification)
                // Set the small icon also you can say as notification icon that we have generated.
                // Set the default notification options that will be used.
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                // Supply a PendingIntent to send when the notification is clicked.
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_launcher_background)
                // Add a rich notification style to be applied at build time.
                // Setting this flag will make it so the notification is automatically canceled when the user clicks it in the panel.
                .setAutoCancel(true)
        // END

        // TODO Step 15: Set the Priority fo the notification.
        // START
        notification.priority = NotificationCompat.PRIORITY_MAX
        // END

        // TODO Step 16: Set channel ID for Notification if you are using the API level 26 or higher.
        // START
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notification.setChannelId(notiId)

            // Setup the Ringtone for Notification.
            val ringtoneManager =
                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val audioAttributes =
                AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_NOTIFICATION_RINGTONE)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build()

            val channel =
                NotificationChannel(
                    notiId,
                    "name",
                    NotificationManager.IMPORTANCE_HIGH
                )

            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(100, 2000, 100);

            channel.setSound(ringtoneManager, audioAttributes)
            notificationManager.createNotificationChannel(channel)
        }
        // END

        // TODO Step 17: Notify the user with Notification id and Notification builder using the NotificationManager instance that we have created.
        // START
        notificationManager.notify(notification_id, notification.build())
        // END
    }
}
