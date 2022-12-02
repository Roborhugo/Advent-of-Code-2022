package Day01.Main

import scala.io.Source

@main
def Main(args: String*): Unit =
  val filename: String = if args.length == 0 then
    "src/main/scala/Day01/Main/input.txt"
  else
    args(0)

  val input: Vector[String] = (for line <- Source.fromFile(filename).getLines() yield line).toVector

  var elfList: Vector[Int] = Vector()
  var currentElfCalories: Int = 0
  for line <- input do
    val value: Option[Int] = line.toIntOption
    if(value.nonEmpty) then
      currentElfCalories += value.get
    else
      elfList = elfList :+ currentElfCalories
      currentElfCalories = 0

  println("Part 1:")
  println(s"Highest carried amount: ${elfList.sorted.reverse.head} kcal.")
  println("Part 2:")
  println(s"Amount carried by top three: ${elfList.sorted.reverse.take(3).sum} kcal")
