package scala.p02_class_object

class s07_伴生对象 {

}

/**
  * 伴生对象：与类名相同且用object修饰的对象
  * 类和伴生对象之间可以相互访问私有的方法和属性
  */
class Dog {
    private var name = "erha"

    def printName(): Unit = {
        //在Dog类中访问其伴生对象的私有属性
        println(Dog.CONSTANT + name)
    }
}

object Dog {
    private val CONSTANT = "wangwang："

    def main(args: Array[String]): Unit = {
        val d = new Dog
        println(d.name)
        d.printName()
        d.name = "dahuang"
        println(d.name)
    }
}