package scala.p03_pattern_matching

class s12_PartialFunction {

}

/**
  * 偏函数 PartialFunction[A, B]，A是参数类型，B是返回值类
  * 偏函数常用作模式匹配
  */
object PartialFunctionDemo {
    /*
        def m1: PartialFunction[String, Int] = {
            case"one"
            =>
            {
                println("case 1")
                1
            }
            case"two"
            =>
            {
                println("case 2")
                2
            }
        }
    */

    def m2(num: String): Int = num match {
        case "one" => 1
        case "two" => 2
        case _ => 0
    }

    def main(args: Array[String]): Unit = {
        //        println(m1("one"))
        println(m2("two"))
    }
}