package core

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{SQLContext, SparkSession}
import org.apache.spark.{Dependency, SparkConf, SparkContext}

/**
  * @Author: leebin
  * @Date: 2019-11-02 22:04
  * @Description:
  */
object ReadTextScalaEdition {
  def main(args: Array[String]): Unit = {
    //conf
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("test-spark-scala")
    //create session
    val sparkSession: SparkSession = SparkSession.builder().config(conf).getOrCreate()
    //get context and sqlContext
    val sc: SparkContext = sparkSession.sparkContext
    val sqlContext: SQLContext = sparkSession.sqlContext
    //set log level
    sc.setLogLevel("warn")
    //read text
    val textRdd: RDD[String] = sc.textFile("/Users/leebin/Downloads/train.csv")
    //skip first line, split and filter
    val lineArr: RDD[Array[String]] = textRdd.zipWithIndex().filter(_._2 >= 1).map(_._1).map(line => line.split(",")).filter(e => e.length == 13).cache()
    //lineArr.foreach(lineArr => println(lineArr.toList.toString()))
    //lineArr.foreach(e => println(e.length))
    //trans array to tuple
    val lineTuple: RDD[(String, String, String, String, String, String, String, String, String, String, String, String, String)] = lineArr.map(e => (e(0), e(1), e(2), e(3), e(4), e(5), e(6), e(7), e(8), e(9), e(10), e(11), e(12)))

    //测试1：排序==========
    //print P4
    //lineTuple.foreach(e => println(e))
    //sortBy & sortByKey
    val sortedBy: Array[(String, String, String, String, String, String, String, String, String, String, String, String, String)] = lineTuple.sortBy(e => e._1).collect()

    //order function
    implicit val ordering: Ordering[(String, String)] = new Ordering[(String, String)] {
      override def compare(a: (String, String), b: (String, String)): Int = {
        var result = 0
        if (a._1 == b._1) {
          result = a._2.compareTo(b._2)
        } else {
          result = -a._1.compareTo(b._1)
        }
        result
      }
    }
    //order apply
    val sortByKey: RDD[((String, String), (String, String, String, String, String, String, String, String, String, String, String, String, String))] = lineTuple.map(e => ((e._2, e._3), e)).sortByKey(ascending = false)
    //sortByKey.foreach(e => println(e))

    //测试2：分组==========
    val group: RDD[(String, Iterable[(String, String, String, String, String, String, String, String, String, String, String, String, String)])] = lineTuple.groupBy(e => e._2)
    group.foreach(e => {
      val key: String = e._1
      val group: Iterable[(String, String, String, String, String, String, String, String, String, String, String, String, String)] = e._2
      if (key equals "1") {
        //group.foreach(x => println(x))
      }
    })

    //get same info
    val dependencies: Seq[Dependency[_]] = lineTuple.dependencies
    dependencies.foreach(e => println(e))

    //

    //close session
    sparkSession.stop()
  }
}
