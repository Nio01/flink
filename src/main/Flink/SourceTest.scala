import java.util.Properties

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010
//导包要用下划线，太具体会报错
//pom依赖streaming里有一行是多余的
//pom里不加client依赖也会报错
object SourceTest {
  def main(args: Array[String]): Unit = {
    //创建环境
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    //集合导入
    val readings = List(
      SensorReading("sensor_1", 35.4),
      SensorReading("sensor_2", 35.1)
    )
    val value = env.fromCollection[SensorReading](readings)


    //文件导入
    val value1 = env.readTextFile("D:\\tool\\2019idea\\project\\flink\\data\\sensor")

    //kafka导入
    val properties = new Properties()
    properties.setProperty("bootstrap.servers","v4:9092")
    properties.setProperty("group.id","1")
    val value2 = env.addSource(new FlinkKafkaConsumer010[String]("topic", new SimpleStringSchema(), properties))

    value2.print()
    //执行
    env.execute()
  }
}
//sensor 传感器
case class SensorReading(id : String,temperature :Double)