package hsj.com.testdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import com.bumptech.glide.Glide
import com.google.gson.Gson

import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    //    val baseUrl = ""
//    lateinit var okHttpClient: OkHttpClient
//    lateinit var retrofit: Retrofit
    val httpUtils = HttpUtils()
    var num: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        initClient()
        getData()

    }

    private fun getData() {
        if (num > 100) {
            return
        }
        num++
        val address = "http://192.168.31.100/get_data.json"
        httpUtils.httpGetRequest(address, object : HttpCallbackListener {
            override fun onError(e: Exception) {
                Log.e("tag", e.toString())
            }

            override fun onFinished(response: String) {
                Log.e("tag", response)
                if (!TextUtils.isEmpty(response)) {
                    var gson = Gson()
                    var person = gson.fromJson(response, Person::class.java)
                    if (null != person) {
                        showPerson(person)
                        getData()
                    }
                }
            }
        })
    }

    private fun showPerson(person: Person) {
        runOnUiThread(Runnable {
            tvName.setText(person.empname + num)
            tvDp.setText(person.company)
            tvBM.setText(person.department)
            tvJB.setText(person.department)
            tvGH.setText(person.ickh)
            Glide.with(this).load(person.photo).into(imageView)

        })
    }


//    fun initClient() {
    //         retrofit = Retrofit.Builder()
//            .baseUrl(baseUrl)
//            .client(okHttpClient)
//            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//            .addConverterFactory(GsonConverterFactory.create())
//             .build()
//        if (okHttpClient == null) {
//            val log = HttpLoggingInterceptor()
//            log.level = HttpLoggingInterceptor.Level.BODY
//            okHttpClient = OkHttpClient.Builder()
//                .addInterceptor(log)
//                .connectTimeout(2000, TimeUnit.MILLISECONDS)
//                .readTimeout(2000, TimeUnit.MILLISECONDS)
//                .build()
//        }
//    }
}
