package ml.regression

import org.apache.spark.ml.feature.{VectorAssembler, VectorIndexer}
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.slf4j.LoggerFactory

/**
  * https://github.com/PacktPublishing/Machine-Learning-with-Spark-Second-Edition/tree/master/Chapter07/scala/2.0.0/scala-spark-app/src/main/scala/org/sparksamples/regression/bikesharing
  */
object Regression {
  /**
    * 日志
    */
  private final val logger = LoggerFactory.getLogger(Regression.getClass)
  private val path: String = "./src/main/scala/org/sparksamples/regression/dataset/BikeSharing/hour.csv"

  /**
    * 主函数
    */
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession.builder().master("local").appName("regression").getOrCreate()

    /**
      * 各种回归的方法
      */
    val df: DataFrame = spark.read.format("csv").option("header", "true").load(path)
    df.cache()
    df.createOrReplaceTempView("BikeSharing")
    print(df.count())
    spark.sql("SELECT * FROM BikeSharing").show()
    val df1: DataFrame = df.drop("instant").drop("dteday").drop("casual").drop("registered")
    val df2: DataFrame = df1
      .withColumn("season", df1("season").cast("double"))
      .withColumn("yr", df1("yr").cast("double"))
      .withColumn("mnth", df1("mnth").cast("double"))
      .withColumn("hr", df1("hr").cast("double"))
      .withColumn("holiday", df1("holiday").cast("double"))
      .withColumn("weekday", df1("weekday").cast("double"))
      .withColumn("workingday", df1("workingday").cast("double"))
      .withColumn("weathersit", df1("weathersit").cast("double"))
      .withColumn("temp", df1("temp").cast("double"))
      .withColumn("atemp", df1("atemp").cast("double"))
      .withColumn("hum", df1("hum").cast("double"))
      .withColumn("windspeed", df1("windspeed").cast("double"))
      .withColumn("label", df1("label").cast("double"))
    df2.printSchema()
    val df3: DataFrame = df2.drop("label")
    val featureCols: Array[String] = df3.columns
    val vectorAssembler: VectorAssembler = new VectorAssembler().setInputCols(featureCols).setOutputCol("rawFeatures")
    val vectorIndexer: VectorIndexer = new VectorIndexer().setInputCol("rawFeatures").setOutputCol("features").setMaxCategories(2)
    val command = "GLR_SVM"
  }
}
