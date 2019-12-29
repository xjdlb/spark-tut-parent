package core

import org.apache.log4j.{LogManager, Logger}
import org.apache.spark.SparkContext

object LongTimeTask {
  private val log: Logger = LogManager.getLogger(TextReadJob.getClass.getName)

  def main(args: Array[String]): Unit = {
    doTask()
  }

  /**
    * spark长时间任务：主要用于测试端口占用的情况
    */
  def doTask(): Unit = {
    val sc: SparkContext = SparkUtil.getSparkContext("test-spark-context", "local[*]")
    log.info("开启长时间任务")
    Thread.sleep(1000 * 1000)
  }
}
