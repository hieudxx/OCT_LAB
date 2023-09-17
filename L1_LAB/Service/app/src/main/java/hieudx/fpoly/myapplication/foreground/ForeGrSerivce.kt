package hieudx.fpoly.myapplication.foreground

import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import hieudx.fpoly.myapplication.R

class ForeGrSerivce : Service() {
    //    private var mediaPlayer: MediaPlayer? = null
    private var mediaPlayer: MediaPlayer? = null
    private val ACTION_PAUSE = 1
    private val ACTION_RESUME = 2
    private val ACTION_STOP = 3
    private var isPlay: Boolean = false
    private var mSong = Song()
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.e("onCreate", "onCreate: ")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val bundle = intent?.extras
        if (bundle != null) {
            val song = bundle.get("song") as? Song
            if (song != null) {
                mSong = song
                startMusic(song)
                sendNoti(song)
            }
        }
//        nhận action bên broadcast bắn sang
        val action = intent?.getIntExtra("action_service", 0)
        action?.let { actionMusic(it) }
        return START_NOT_STICKY
    }

    private fun actionMusic(action: Int) {
        when (action) {
            ACTION_PAUSE -> pauseMusic()
            ACTION_RESUME -> resumeMusic()
            ACTION_STOP -> stopSelf()
        }
    }

    private fun resumeMusic() {
        if (mediaPlayer != null && !isPlay) {
            mediaPlayer?.start()
            isPlay = true
            sendNoti(mSong)
        }
    }

    private fun pauseMusic() {
        if (mediaPlayer != null && isPlay) {
            mediaPlayer?.pause()
            isPlay = false
            sendNoti(mSong)
        }
    }

    private fun startMusic(song: Song) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer().apply {
                setDataSource(
                    applicationContext,
                    Uri.parse("android.resource://" + packageName + "/" + R.raw.music)
                )
//                setOnPreparedListener{
//                    start()
//                }
//                prepareAsync()
                prepare()
                start()
            }
            isPlay = true
            Log.e("playyyyyyyyy", "startMusic: $isPlay")
        }
//        mediaPlayer?.release()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "onDestroy: ")
        if (mediaPlayer != null) {
            mediaPlayer!!.release()
            mediaPlayer = null
        }
    }

    private fun sendNoti(song: Song) {
//        val i = Intent(this, ForeGroundActivity::class.java)
//        val pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT)
        val bitmap = BitmapFactory.decodeResource(resources, song.Img)
        val view = RemoteViews(packageName, R.layout.layout_custom_noti)
        view.setTextViewText(R.id.tvAuthor, song.Author)
        view.setTextViewText(R.id.tvName, song.Name)
        view.setImageViewBitmap(R.id.imgSong, bitmap)
        view.setImageViewResource(R.id.imgPlayPause, R.drawable.baseline_pause_24)
        if (isPlay) {
//            nếu mediaPlayer đang chạy
            view.setOnClickPendingIntent(
                R.id.imgPlayPause,
                getPendingIntent(this, ACTION_PAUSE)
            ) //bắt sự kiện khi click vào icon
            view.setImageViewResource(R.id.imgPlayPause, R.drawable.baseline_pause_24)
        } else {
            view.setOnClickPendingIntent(
                R.id.imgPlayPause,
                getPendingIntent(this, ACTION_RESUME)
            )
            view.setImageViewResource(R.id.imgPlayPause, R.drawable.baseline_play_arrow_24)
        }
        view.setOnClickPendingIntent(
            R.id.imgClear,
            getPendingIntent(this, ACTION_STOP)
        ) //bắ
        val noti = NotificationCompat.Builder(this, ConfigNoti().CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_music_note_24)
//            .setContentIntent(pendingIntent)
            .setCustomContentView(view)
            .build()
        startForeground(1, noti)
    }

    private fun getPendingIntent(context: Context, action: Int): PendingIntent {
//        chuyển action sang broadcastReceiver
        val i = Intent(this, MyReceiver::class.java)
        i.putExtra("action", action)
        return PendingIntent.getBroadcast(
            context.applicationContext,
            action,
            i,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
    }

}