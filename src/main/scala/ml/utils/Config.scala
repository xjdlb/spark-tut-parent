package ml.utils

import java.io.{File, FileInputStream}
import java.util.Properties

object Config {
    /**
      * 系统的名称，程序运行根目录
      */
    private val OS_WIN = "windows"
    private val OS_NAME: String = System.getProperty("os.name").toLowerCase
    private val ROOT_DIR: String = System.getProperty("user.dir")

    /**
      * windows运行jar文件，linux运行jar文件，idea调试文件
      */
    private val WINDOWS_JAR_CONFIG_FILE_NAME: String = ROOT_DIR + File.separator + "properties\\config.properties"
    private val LINUX_CONFIG_FILE_NAME: String = ROOT_DIR + File.separator + "properties/config.properties"
    private val WINDOWS_IDEA_DEBUG_CONFIG_FILE_NAME: String = ROOT_DIR + File.separator + "src\\main\\resources\\properties\\config.properties"

    /**
      * 根据key读取value
      */
    def getString(key: String): String = {
        //配置文件地址
        var configPath: String = null
        //根据具体情况分配配置文件地址
        if (OS_NAME.contains(OS_WIN)) {
            if (new File(WINDOWS_JAR_CONFIG_FILE_NAME).exists()) {
                configPath = WINDOWS_JAR_CONFIG_FILE_NAME
            } else {
                configPath = WINDOWS_IDEA_DEBUG_CONFIG_FILE_NAME
            }
        } else {
            configPath = LINUX_CONFIG_FILE_NAME
        }
        //获取配置文件流
        val properties = new Properties()
        properties.load(new FileInputStream(new File(configPath)))
        properties.getProperty(key)
    }
}