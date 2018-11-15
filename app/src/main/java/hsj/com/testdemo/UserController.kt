package hsj.com.testdemo

import android.util.Log
import com.yanzhenjie.andserver.annotation.*

/**
 * 作者：create by 18729 on 2018/11/14
 * 说明： 服务器接口
 */
@RestController
@RequestMapping(path = arrayOf("/user"))
class UserController {
    @GetMapping("/info")
    fun requestUserInfo(@PathVariable("name") name: String): String {
        Log.e("服务器", "请求用户接口 $name")
        var result = if (name.equals("ddd")) {
            "true"
        } else {
            "false"
        }
        return result
    }
}