package hsj.com.testdemo

import android.text.TextUtils
import android.util.Log
import com.google.gson.Gson
import java.io.*
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

/**
 * 作者：create by 18729 on 2018/11/14
 * 说明：
 */
class HttpUtils {
    /**
     * get请求
     */
    fun httpGetRequest(addrss: String, callbackListener: HttpCallbackListener) {
        Thread(Runnable {
            var httpURLConnection: HttpURLConnection? = null
            try {
                //请求地址
                val url = URL(addrss)
                //建立连接
                httpURLConnection = url.openConnection() as HttpURLConnection
                //请求方式
                httpURLConnection.requestMethod = "GET"
                //连接超时
                httpURLConnection.connectTimeout = 8000
                // 读取超时
                httpURLConnection.readTimeout = 8000
                // 获取网络数据
                var inputStream: InputStream = httpURLConnection.inputStream
                //解析数据
                var reader = BufferedReader(InputStreamReader(inputStream))
                var content = StringBuffer()
                var line: String? = reader.readLine()
                while (line != null) {
                    content.append(line)
                    line = reader.readLine()
                }
                callbackListener.onFinished(content.toString())
            } catch (e: Exception) {
                callbackListener.onError(e)
            } finally {
                //断开连接
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect()
                }
            }

        }).start()
    }
}

/**
 * post 请求
 */
fun httpPostRequest(addrss: String, paramas: String, callbackListener: HttpCallbackListener) {
    Thread(Runnable {
        var httpURLConnection: HttpURLConnection? = null
        var out: DataOutputStream? = null
        var reader: BufferedReader? = null

        try {
            //请求地址
            val url = URL(addrss)
            //建立连接
            httpURLConnection = url.openConnection() as HttpURLConnection
            //请求方式
            httpURLConnection.requestMethod = "POST"
            //连接超时
            httpURLConnection.connectTimeout = 8000
            // 读取超时
            httpURLConnection.readTimeout = 8000
            //提交数据
            out = DataOutputStream(httpURLConnection.outputStream)
            out.writeBytes(paramas)
            // 获取网络数据
            var inputStream: InputStream = httpURLConnection.inputStream
            //解析数据
            reader = BufferedReader(InputStreamReader(inputStream))
            var content = StringBuffer()
            var line: String? = reader.readLine()
            while (line != null) {
                content.append(line)
                line = reader.readLine()
            }
            callbackListener.onFinished(content.toString())
        } catch (e: Exception) {
            callbackListener.onError(e)
        } finally {
            //断开连接
            try {
                if (null != out) {
                    out.close()
                }
                if (null != reader) {
                    reader.close()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect()
            }
        }

    }).start()
}


interface HttpCallbackListener {

    fun onFinished(response: String)

    fun onError(e: Exception)

}