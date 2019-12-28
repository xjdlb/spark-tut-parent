package scala.p02_class_object

/**
  * apply通常称为注入方法，在伴生对象里做一下初始化操作
  * apply方法参数列表，不一定需要和构造器参数统一
  * unapply常为提取方法，使用unapply方法来提取固定数目的对象
  * unapply方法会返回一个序列option，内部会生成一个some对象，some对象会存放一些值
  * apply和unapply方法会被隐式调用
  *
  * @param name
  * @param age
  * @param faceValue
  */
//构造器
class ApplyDemo(val name: String, var age: Int, var faceValue: Int) {

}

//伴生对象
object ApplyDemo {
    //参数不一致也可以匹配
    def apply(name: String, age: Int, gender: Int, faceValue: Int): ApplyDemo =
        new ApplyDemo(name, age, faceValue)

    //返回option方法
    def unapply(x: ApplyDemo): Option[(String, Int, Int)] = {
        if (x == null) {
            None
        } else {
            Some(x.name, x.age, x.faceValue)
        }
    }
}

//测试类
object s08_apply_unapply {
    def main(args: Array[String]): Unit = {
        val s08_apply_unapply1 = ApplyDemo("jingjing", 24, 0, 90)
        s08_apply_unapply1 match {
            case ApplyDemo("jingjing", age, faceValue) => println(s"age: $age")
            case _ => println("No match nothing")
        }
    }
}