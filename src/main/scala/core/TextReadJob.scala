package core

import org.apache.log4j.{LogManager, Logger}
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

/**
  * spark读取
  */
object TextReadJob {
  private val log: Logger = LogManager.getLogger(TextReadJob.getClass.getName)

  private final val path = "/Users/leebin/proj/day_20190323/demo-spring-parent/spark/src/main/scala/com/hest/compute/core/text.txt"

  /**
    * 正确main函数的写法
    *
    * @param args
    */
  def main(args: Array[String]): Unit = {
    if (args.length >= 0) {
      doTask()
    } else {
      log.error("输入参数长度不对：")
      System.exit(1)
    }
  }

  /**
    * spark 任务
    */
  def doTask(): Unit = {
    val sc: SparkContext = SparkUtil.getSparkContext("test-spark-context", "local[*]")
    printJob(sc)
    SparkUtil.stopSparkContext(sc)
  }

  /**
    * 打印
    *
    * @param sc
    */
  def printJob(sc: SparkContext): Unit = {
    val text: RDD[String] = sc.textFile(path)


    log.info("打印text")
    log.info(text.toString())
    text.foreach(println)
    val str: String = text.map(e => e.toString).toString()
    println(str)
  }
}
