package scala.p04_Higher_Order_Functions

//这个上下文类可以单独放在一个文件中
object Context {
    implicit val a = "java"
    implicit val b = "python"
}

object s14_curry_demo {
    //定义柯里化方法，必须要对一个函数参数进行初始化
    def m1(str: String)(implicit name: String = "scala") = {
        str + name
    }

    def main(args: Array[String]): Unit = {
        //使用柯里化
        val res = m1("hi: ") _
        println(res)
        //使用柯里化
        import Context.a
        //import Context.b //直接报错，上下文环境不能有两个implicit变量，这个范围可以是包
        println(m1("Hi: "))
    }
}
