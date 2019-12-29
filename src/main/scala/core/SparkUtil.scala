package core

import org.apache.spark.{SparkConf, SparkContext}

/**
  * spark工具箱
  */
object SparkUtil {
  /**
    * 获取SparkContext
    *
    * @return
    */
  def getSparkContext(appName: String, master: String): SparkContext = {
    val conf: SparkConf = new SparkConf().setAppName(appName).setMaster(master)
    val sc = new SparkContext(conf)
    sc
  }

  /**
    * 关闭SparkContext
    *
    * @param sc
    */
  def stopSparkContext(sc: SparkContext): Unit = {
    if (sc != null) {
      sc.stop()
    }
  }
}
