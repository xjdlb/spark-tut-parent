package scala.p02_class_object

//设定了包的访问权限private[p02_class_object]
//构造器参数列表前面加private，只有伴生对象才能访问
private[p02_class_object] class s09_privateDemo private(val gender: Int, var faceValue: Int) {
    //变量前加上private，类私有变量，伴生对象可以访问
    private val name = "jingjing"
    //对象私有字段，表示只能在本类访问，伴生对象不能访问
    private[this] var age = 24

    //私有方法
    private def sayHello(): Unit = {
        println(s"jingjing's age is：$age")
    }
}

//伴生对象
object s09_privateDemo {
    def main(args: Array[String]): Unit = {
        val p = new s09_privateDemo(0, 90)
        println(p.name)
        println(p.sayHello())
    }
}

//下面的静态类中是不能访问里面的内容，运行报错，无访问权限
//object Test3 {
//    def main(args: Array[String]): Unit = {
//        val p = new s09_privateDemo(0, 90)
//        println(p.gender)
//    }
//}
