package ml.utils

import java.io.File

import org.apache.commons.io.FileUtils

import scala.io.Source

object FileUtilsHelper {
    /**
      * 文件写入
      */
    def write(file: String, data: String, encoding: String, append: Boolean): Unit = {
        FileUtils.write(new File(file), data, encoding, append)
    }

    def write(file: String, data: String, encoding: String): Unit = {
        FileUtils.write(new File(file), data, encoding, true)
    }

    def write(file: String, data: String): Unit = {
        FileUtils.write(new File(file), data, "utf-8", true)
        FileUtils.write(new File(file), "\r\n", "utf-8", true)
    }

    /**
      * 文件读取
      */
    def read(file: String, encoding: String): Iterator[String] = {
        Source.fromFile(file, encoding).getLines()
    }

    def read(file: String): Iterator[String] = {
        Source.fromFile(file, "utf-8").getLines()
    }
}
