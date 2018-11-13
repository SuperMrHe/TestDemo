package hsj.com.testdemo

/**
 * 作者：create by 18729 on 2018/11/13
 * 说明：
 * 提交人员信息：{position=, sex= , flag=1, icsn=3985BB14,
 * department=总部城区运行维护班, company=广州移动总公司,
 * empcode=100, empname=张学友, ickh=100, photo=http://192.168.1.222:9999/photo/100.jpg}
 *
 * flag=1通过，0不通过
 */
data class Person(
    var position: String,
    var sex: String,
    var flag: Int,
    var icsn: String,
    var department: String,
    var company: String,
    var empcode: String,
    var empname: String,
    var ickh: String,
    var photo: String
) {


}