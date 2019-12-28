package scala.p02_class_object

/**
  * 主构造器的参数列表放在类名的后面，且放在一起
  * val修饰的构造参数不可变
  * var修饰的构造参数可变
  * 没有申明类型的faceValue只能在本类调用，伴生对象都不能调用
  *
  * @param name
  * @param age
  * @param faceValue
  */
//主构造器，构造器的声明，faceValue不能访问，默认的是val修饰的，这里设定了默认值
class s05_constructor(val name: String, var age: Int, faceValue: Int = 90) {
    //成员变量
    var gender: String = _

    //get方法，写方法返回值不需要加return，默认最后一句为返回值
    def getFaceValue(): Int = {
        faceValue
    }

    //辅助构造器:主构造器中的字段以外的字段
    def this(name: String, age: Int, faceValue: Int, gender: String) {
        this(name, age, faceValue)
        this.gender = gender
    }
}

object s05_constructor {
    def main(args: Array[String]): Unit = {
        println("-----------------------------------------------------")
        println("测试主构造器")
        val s1 = new s05_constructor("ningning", 26, 98)
        val s2 = new s05_constructor("jingjing", 25, 97)
        println(s1.age)
        println(s2)
        s1.age = 27
        println(s1.age)
        println(s1.getFaceValue())
        println("-----------------------------------------------------")
        println("测试辅助构造器")
        val s3 = new s05_constructor("yangyang", 26, 97, "female")
        println(s3.gender)
    }
}
