package scala.p01_grammar_fp

//import scala.collection.mutable._
import scala.collection.mutable.{ArrayBuffer, ListBuffer}
import scala.collection.{immutable, mutable}

class s01_scala_grammar {

}

object s01_scala_grammar {
    def main(args: Array[String]): Unit = {
        println("1=============================================")
        println("变量声明")
        // var可更改
        var myVar: Int = 0
        myVar = 1
        // val不可更改
        val myVal: String = "Hello Scala with data type declaration."
        //换一种声明类型：自动推断
        val s = "helloworld"
        println(myVar)
        println(myVal)
        println(s)
        println("2=============================================")
        println("条件表达式")
        // 条件表达式
        val x = 3
        val y = if (x > 2) 1 else -1
        println(y)
        // 条件表达式返回字符串
        val x1 = 3
        val y1 = if (x1 > 2) 1 else "error"
        println(y1)
        //返回空类型，不指定返回类型
        val x2 = 3
        val y2 = if (x1 > 100) 1
        println(y2)
        println("3=============================================")
        println("循环控制")
        //循环
        print(1 to 10)
        for (i <- 1 to 5) {
            println("i = " + i)
        }
        //数组
        val arr = Array(1, 2, 3, 4, 5)
        for (i <- arr) {
            println("arr = " + i)
        }
        //高级的for循环
        for (i <- 1 to 3; j <- 1.to(3); if i != j)
            println(i * 10 + j + "")
        //for循环的结果复制给变量，yield把每次生成的结果封装到集合Vector中
        val res = for (i <- 1 until 10) yield i
        println(res)
        println("4=============================================")
        println("定义方法")
        //方法
        //+是方法 双击shift 查找Int
        println(1.+(2))

        //声明一个方法
        def m1(x: Int, y: Int):
        Int = x * 10 + y

        val res1 = m1(10, 10)
        println(res1)
        println("5=============================================")
        //这里注意方法和函数是不一样的
        val f2 = (x: Int, y: Int) => x + y
        println(f2(1, 1))
        println("6=============================================")
        println("方法转化成函数")

        //把函数当成方法的参数传递给方法
        def m2(f: (Int, Int) => Int) = f(3, 4) //把函数当成参数的方法

        val f1 = (x: Int, y: Int) => x + y
        println(m2(f1))
        println("7=============================================")
        println("方法转化成函数")

        //把方法转换成函数
        def m3(x: Int, y: Int): Int = x + y

        val f3 = m3 _ //把方法转换成函数，显示转换
        println(f3(1, 2))
        println(m2(f3)) //显示转换后调用
        println(m2(m3)) //隐式转换后调用，自动转换
        println("8=============================================")
        println("定长数组")
        //容器：数组，分为定长和变长数组，默认为定长
        val arr1 = new Array[Int](8)
        val arr2 = new Array[String](8)
        println(arr1)
        println(arr2)
        println(arr1.toBuffer) //打印元素
        println(arr2.toBuffer) //打印元素
        // 定长数组
        val arr3 = Array("java", "scala", "python")
        println(arr3(0)) //小括号引用
        println("9=============================================")
        println("变长数组")
        //容器：变长数组 import scala.collection.mutable.ArrayBuffer
        val arr4 = ArrayBuffer[Int]()
        arr4 += 1 //追加元素
        arr4 += (2, 3, 4)
        arr4 ++= Array(5, 6)
        arr4 ++= ArrayBuffer(7, 8)
        println(arr4)
        arr4.insert(0, -1, 0) //插入元素
        println(arr4)
        //变长数组还有其他很多的API
        //遍历
        for (x <- arr4)
            print(x + " ")
        println()
        //下标遍历
        for (i <- arr4.indices)
            print(arr4(i) + " ")
        println()
        //反着遍历
        for (i <- arr4.indices.reverse)
            print(arr4(i) + " ")
        println()
        //使用遍历接受遍历结果，封装到新的数组中
        val res2 = for (i <- arr4.indices) yield i * 10
        println(res2)
        println("最小值为：" + arr4.min + " " + "和为：" + arr4.sum)
        println("10=============================================")
        println("映射")
        val map1 = mutable.Map("scala" -> 1, "java" -> 2)
        println(map1)
        val map2 = mutable.Map(("scala", 1), ("java", 2))
        println(map2)
        //获取和修改
        println(map1("scala"))
        val map3 = mutable.Map("scala" -> 1, "java" -> 2)
        map3("scala") = 6 //注意val修饰的引用不可变，里面的值是可以变化的
        println(map3)
        //异常
        println(map3.getOrElse("C#", -1))
        println("11=============================================")
        println("元组")
        val t1 = (1, 2, "java", (1, "spark"))
        println(t1)
        println(t1._4) //取值，下标从1开始
        println(t1._4._1) // 取子元组的值
        //指定下标
        val t2, (a1, b1, c1, d1) = (1, 2, "java", (1, "spark"))
        println(d1)
        println(d1._1)
        println("12=============================================")
        println("介绍几个方法")
        println("把数组转化成Map")
        val arr5 = Array(("tingting", 24), ("ningning", 25), ("yueyue", 10))
        println(arr5)
        for (i <- arr5) {
            println("arr = " + i)
        }
        println(arr5.toMap)
        println("拉链操作：把两个元素转化成包含元组的数组")
        val arr6 = Array("tingting", "ningning", "yueyue")
        val arr7 = Array(24, 25, 10)
        val arr8 = arr6 zip arr7 //拉链操作
        println(arr8)
        for (i <- arr8) {
            println(i)
        }
        //不等长拉链
        val arr9 = Array(24, 25, 10, 20)
        val arr10 = arr6.zip(arr9)
        println(arr10)
        for (i <- arr10) {
            println("arr = " + i)
        }
        println("13=============================================")
        println("容器：三大类集合Seq，Set，Map")
        println("List")
        val list1 = List(1, 2, 3, 4)
        println("前插")
        val list2 = 90 :: list1
        val list3 = list1.::(91)
        val list4 = 92 +: list1
        val list5 = list1.+:(93)
        println(list1 + "\n" + list2 + "\n" + list3 + "\n" + list4 + "\n" + list5)
        println("追加")
        val list6 = list1.:+(81)
        val list7 = list1 :+ 82
        println(list6 + "\n" + list7)
        println("合并，拼接，不可变的生成新的序列list")
        val list8 = List(1, 2, 3, 4)
        val list9 = List(11, 12, 13, 14)
        println(list8 ++ list9)
        println(list9 ++ list8)
        println(list9 ::: list8)
        println(list8 ++: list9)
        println("可变的序列") //import scala.collection.mutable.{ArrayBuffer, ListBuffer, Map}
        val lb1 = ListBuffer(1, 2, 3)
        lb1.+=(4)
        println(lb1)
        lb1.append(5)
        println(lb1)
        println("可变的序列追加")
        val listBuffer1 = ListBuffer(1, 2, 3)
        val listBuffer2 = ListBuffer(10, 20, 30, 40)
        listBuffer1 ++= listBuffer2 //追加
        println(listBuffer1)
        println("可变的序列合并生成")
        val listBuffer3 = ListBuffer(1, 2, 3)
        val listBuffer4 = ListBuffer(10, 20, 30, 40)
        println(listBuffer3 ++ listBuffer4) //合并生成新的List
        println(listBuffer3 :+ 4) //生成新的
        println(listBuffer3)
        println("14=============================================")
        println("容器：三大类集合Seq，Set，Map")
        println("不可变set")
        println("方法声明1")
        val set1 = immutable.HashSet(1, 2, 3)
        println(set1)
        println("声明方式2")
        val set_2 = new immutable.HashSet[Int]()
        val set2 = set_2 + 11 //immutable.HashSet只能生成新的set
        println(set2)
        println("set拼接set，追加操作，生成新的set")
        val set3 = set2 ++ set1
        print(set3)
        val set4 = set1 ++ Set(55, 66)
        println(set4)
        println("15=============================================")
        println("可变set")
        val set5 = new mutable.HashSet[Int]()
        val set6 = mutable.HashSet(11, 21, 31)
        //追加操作
        set5 += 1
        set5.add(2)
        set5 ++= set6
        println(set5)
        //删除操作
        set5 -= 2
        println(set5)
        set5 --= set6
        println(set5)
        println("16=============================================")
        println("可变map")
        //map也是有可变的和不可变的
        val hashMap1 = new mutable.HashMap[String, Int]()
        hashMap1("java") = 1
        hashMap1 += (("python", 1))
        hashMap1 += (("scala", 1), ("c++", 2)) //-=
        hashMap1.put("ruby", 2) //remove
        println(hashMap1)
    }
}
