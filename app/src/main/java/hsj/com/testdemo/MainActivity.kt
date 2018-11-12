package hsj.com.testdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.jetbrains.anko.toast
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    val baseUrl = ""
    lateinit var okHttpClient: OkHttpClient
    lateinit var retrofit:Retrofit
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        initClient()
//         retrofit = Retrofit.Builder()
//            .baseUrl(baseUrl)
//            .client(okHttpClient)
//            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//            .addConverterFactory(GsonConverterFactory.create())
//             .build()
//        toast("")
    }

    fun initClient() {
        if (okHttpClient == null) {
            val log = HttpLoggingInterceptor()
            log.level = HttpLoggingInterceptor.Level.BODY
            okHttpClient = OkHttpClient.Builder()
                .addInterceptor(log)
                .connectTimeout(2000, TimeUnit.MILLISECONDS)
                .readTimeout(2000, TimeUnit.MILLISECONDS)
                .build()
        }
    }
}
