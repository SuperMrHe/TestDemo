package hsj.com.testdemo

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.util.Log
import com.yanzhenjie.andserver.AndServer
import com.yanzhenjie.andserver.Server
import com.yanzhenjie.andserver.Server.ServerListener
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.lang.Exception
import java.net.InetAddress
import java.util.concurrent.TimeUnit
import java.util.function.Consumer

/**
 * 作者：create by 18729 on 2018/11/15
 * 说明：
 */
class UserServers : Service() {
    lateinit var mService: Server
    val tag: String = "UserServers"
    var host:InetAddress? =null

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Thread(Runnable {
            host = InetAddress.getLocalHost()
        }).start()
        Observable.timer(5, TimeUnit.SECONDS)
            .subscribe(){
                init()
            }
    }

    private fun init() {
        mService = AndServer.serverBuilder()
            .inetAddress(host)
            .port(8080)
            .timeout(3, TimeUnit.SECONDS)
            .listener(object : ServerListener {
                override fun onStarted() {
                    Log.d(tag, "onStarted()")

                }

                override fun onStopped() {
                    Log.d(tag, "onStopped()")
                }

                override fun onException(e: Exception?) {
                    Log.d(tag, "onException()" + e.toString())

                }
            })
            .build()
        startServer()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        startServer()
        return Service.START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        stopServer()

    }

    private fun stopServer() {
        mService.shutdown()
    }

    private fun startServer() {
        if (!mService.isRunning) {
            mService.startup()
        } else {
        }
    }
}