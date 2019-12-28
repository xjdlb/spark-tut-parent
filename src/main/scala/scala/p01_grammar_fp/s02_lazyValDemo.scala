package scala.p01_grammar_fp

class lazyValDemo {

}

//scala中的class是不能有main方法的
object lazyDemo {
    def init(): Unit = {
        println("call init()")
    }

    def main(args: Array[String]): Unit = {
        val property = init() //没有有Lazy修饰的
        println("after init()")
        println(property) //相当于返回值，上面我们定义的是Unit
    }
}

//scala中的class是不能有main方法的
object lazyDemo2 {
    def init(): Unit = {
        println("call init()")
    }

    //Java语言中调用单例模式采用的逻辑
    def main(args: Array[String]): Unit = {
        lazy val property = init() //有Lazy修饰的，后执行，延时加载，惰性变量只能是不可变的变量
        println("after init()")
        println(property) //相当于返回值，上面我们定义的是Unit
    }
}