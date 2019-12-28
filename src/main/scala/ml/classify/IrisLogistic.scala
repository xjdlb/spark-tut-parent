package ml.classify

import org.apache.spark.SparkContext
import org.apache.spark.ml.classification._
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{StringIndexer, VectorAssembler}
import org.apache.spark.ml.{Pipeline, PipelineModel, PipelineStage}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}
import org.slf4j.LoggerFactory

import scala.collection.mutable.ArrayBuffer

/**
  * 利用spark的LogisticRegression模型和Iris（鸢尾花）数据集实现的多标签分类
  * spark API
  * http://spark.apache.org/docs/latest/api/scala/index.html#org.apache.spark.ml.classification.LogisticRegressionModel
  * 数据集下载地址：搜索Iris
  * https://blog.csdn.net/c1z2w3456789/article/details/50983713
  */
object IrisLogistic {

  private final val logger = LoggerFactory.getLogger(IrisLogistic.getClass)
  val path: String = "D:\\a02_allcode\\p181226_scala2sparkml\\scala2spark4ml\\src\\main\\resources\\data\\iris2.txt"

  def main(args: Array[String]): Unit = {
    Lr4Iris()
  }

  /**
    * lr模型多值预测
    */
  private def Lr4Iris(): Unit = {
    //获取的sqlContext和sparkContext
    logger.info("创建spark连接")
    val sqlContext: SparkSession = SparkSession.builder().appName("WordCount").master("local[*]").getOrCreate()
    //紧跟着sqlContext
    import sqlContext.implicits._

    val sc: SparkContext = sqlContext.sparkContext
    //spark日志级别
    sc.setLogLevel("ERROR")

    //拆分 列名 比例
    val irisRDD: RDD[Iris] = sc.textFile(path).map((_: String).split(",")).map((line: Array[String]) => Iris(line(0).toDouble, line(1).toDouble, line(2).toDouble, line(3).toDouble, line(4)))
    val irisDF: DataFrame = irisRDD.toDF("sepalLength", "sepalWidth", "petalLength", "petalWidth", "Species")
    val Array(training, test): Array[Dataset[Row]] = irisDF.randomSplit(Array(0.8, 0.2), seed = 12345L)

    //标签化 向量化 模型 三阶段
    val indexer: StringIndexer = new StringIndexer().setInputCol("Species").setOutputCol("label")
    val vectorAssembler: VectorAssembler = new VectorAssembler().setInputCols(Array("sepalLength", "sepalWidth", "petalLength", "petalWidth")).setOutputCol("features")
    //分类模型
    val lr: LogisticRegression = new LogisticRegression().setMaxIter(100).setRegParam(0.01).setThreshold(0.55).setElasticNetParam(0.8)
    val dt: DecisionTreeClassifier = new DecisionTreeClassifier().setMaxDepth(5).setMaxBins(32).setMinInstancesPerNode(1).setMinInfoGain(0.0).setCacheNodeIds(false).setCheckpointInterval(10)
    val rf: RandomForestClassifier = new RandomForestClassifier().setNumTrees(20).setMaxDepth(5).setMaxBins(5).setMinInstancesPerNode(1).setMinInfoGain(0.0).setCacheNodeIds(false).setCheckpointInterval(10)
    val nb = new NaiveBayes()
    //GBT支持2分类
    val gbt: GBTClassifier = new GBTClassifier().setMaxIter(30).setFeaturesCol(vectorAssembler.getOutputCol).setLabelCol(indexer.getOutputCol)
    val mp: MultilayerPerceptronClassifier = new MultilayerPerceptronClassifier().setLayers(Array[Int](4, 5, 4, 3)).setBlockSize(128).setSeed(1234L).setMaxIter(100)

    //三阶段
    val stages: ArrayBuffer[PipelineStage] = new ArrayBuffer[PipelineStage]()
    stages.append(indexer)
    stages += vectorAssembler
    //stages.append(lr)
    stages += nb
    val pipeline: Pipeline = new Pipeline().setStages(stages.toArray)

    //训练
    val model: PipelineModel = pipeline.fit(training)

    //预测结果
    val holdOut: DataFrame = model.transform(test).select("prediction", "label")
    holdOut.show(1000)
    //评价
    //case "f1" => metrics.weightedFMeasure
    //case "weightedPrecision" => metrics.weightedPrecision
    //case "weightedRecall" => metrics.weightedRecall
    //case "accuracy" => metrics.accuracy
    val evaluator: MulticlassClassificationEvaluator = new MulticlassClassificationEvaluator().setLabelCol("label").setPredictionCol("prediction").setMetricName("accuracy")
    val accuracy: Double = evaluator.evaluate(holdOut)
    println(accuracy)
  }

  //必须是方法外定义
  case class Iris(sepalLength: Double, sepalWidth: Double, petalLength: Double, petalWidth: Double, Species: String)

}
