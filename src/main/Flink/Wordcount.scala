
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time

object Wordcount {
  def main(args: Array[String]): Unit = {
   /* //获得批处理环境

    val env = ExecutionEnvironment.getExecutionEnvironment

    val value: DataSet[String] = env.readTextFile("./data/word.txt")
    value.flatMap { _.toLowerCase.split(" ")}

      .filter (_.nonEmpty)//去掉非空
      .map { (_, 1) }
      .groupBy(0) //基于第一个字段分组
      .sum(1)//对第二个字段求和
      .print()*/
   val senv = StreamExecutionEnvironment.getExecutionEnvironment
//从这个主机的端口号读取的（用nc测试）
    val dataStream: DataStream[String] = senv.socketTextStream("192.168.8.104", 9999, '\n')
    dataStream.flatMap { line => line.split(" ") }
      .filter(_.nonEmpty)
      .map { word => (word, 1) }
      .keyBy(0)
      .timeWindow(Time.seconds(3))
      .sum(1)
      .print()

senv.execute("s")
  }
}
