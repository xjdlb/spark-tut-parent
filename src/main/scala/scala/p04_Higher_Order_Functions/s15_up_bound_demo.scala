package scala.p04_Higher_Order_Functions

/**
  * 上界：up_bound
  */
class s15_up_bound_demo[T <: Comparable[T]] {
    def select(first: T, second: T): T = {
        if (first.compareTo(second) > 0) first else second
    }
}

object s15_up_bound_demo {
    def main(args: Array[String]): Unit = {
        val u = new s15_up_bound_demo[Goddess]
        val m1 = new Goddess("ningning", 90)
        val m2 = new Goddess("jingjing", 100)
        val res = u.select(m1, m2)
        println(res.name + "   " + res.faceVaule)
    }
}

class Goddess(val name: String, val faceVaule: Int) extends Comparable[Goddess] {
    override def compareTo(o: Goddess): Int = {
        this.faceVaule - o.faceVaule
    }
}