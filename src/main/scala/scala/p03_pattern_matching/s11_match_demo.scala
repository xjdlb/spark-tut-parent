package scala.p03_pattern_matching

import scala.util.Random

class s11_match_demo {

}

//匹配字符串
object MatchStr {
    def main(args: Array[String]): Unit = {
        val arr = Array("zhoudongyu", "zhengshuang", "guanxiaotong", "yangzi")
        //随机产生名字
        val name = arr(Random.nextInt(arr.length))
        println(name)
        //匹配名字
        name match {
            case "zhoudongyu" => println("zdy")
            case "zhengshuang" => println("zs")
            case "guanxiaotong" => println("gxt")
            case "yangzi" => println("yz")
            case _ => println("Nothing")
        }
    }
}

//匹配类型
object matchType {
    def main(args: Array[String]): Unit = {
        val arr = Array("abcde", 100, 3.14, true, matchType)
        val element = arr(Random.nextInt(arr.length))
        println(element)
        element match {
            case str: String => println(s"match string: $str")
            case int: Int => println(s"match string: $int")
            case bool: Boolean => println(s"match string: $bool")
            case mt: MatchTest => println(s"match string: $mt")
            case _ => println("Nothing")
        }
    }
}

class MatchTest {

}

//匹配list
object matchList {
    def main(args: Array[String]): Unit = {
        //match array
        println("---array-------------------------------------------")
        val arr = Array(3, 2, 5, 7)
        arr match {
            case Array(3, a, b, c) => println(s"case: $a,$b,$c")
            case Array(_, x, y, z) => println(s"case: $x,$y,$z")
            case _ => println("Not Matched") //default
        }
        // match tuple
        println("---tuple-------------------------------------------")
        val tup = (2, 3, 4)
        tup match {
            case (2, a, b) => println(s"case: $a,$b")
            case (_, x, y) => println(s"case:$x,$y")
            case _ => println("Not matched")
        }
        //match list
        println("---list-------------------------------------------")
        val list1 = List(0, 1, 2, 3)
        list1 match {
            // 普通匹配
            //            case List(0, a, b, c) => println(s"1:$a,$b,$c")
            // Nil list合并的运算符
            case 0 :: Nil => println("case1: 0")
            //            case a :: b :: c :: d => println(s"2: $a,$b,$c,$d")
            case a :: b :: c :: d :: Nil => println(s"3: $a,$b,$c,$d")
            case 0 :: a => println(s"4:$a")
            case _ => println("Nothing")
        }
    }
}

//样例类
object caseClassMatch {
    def main(args: Array[String]): Unit = {
        println("---caseClass-------------------------------------------")
        val arr = Array(CheackTimeOutTask, SubmiTask("1000", "task-0001"), HeartBeat(3000))
        arr(Random.nextInt(arr.length)) match {
            case CheackTimeOutTask => println("CheackTimeOutTask")
            case SubmiTask(port, task) => println("SubmiTask")
            case HeartBeat(time) => println("HeartBeat")
        }
    }
}

case class HeartBeat(time: Long)

case class SubmiTask(id: String, taskName: String)

case object CheackTimeOutTask
