package Day02

import scala.io.Source

@main
def Main(args: String*): Unit =
  val filename: String = if args.isEmpty then
    "src/main/scala/Day02/input.txt"
  else
    args(0)

  val input: Vector[(Int, Int)] = (for line <- Source.fromFile(filename).getLines() yield line)
    .toVector.map(s => s.split(" ")).map(a => (a(0)(0), a(1)(0))).map(t => (t._1.toInt - 65, t._2.toInt - 88))

  var totalScore = 0
  for round <- input do
    totalScore += round._2 + 1

    if round._1 == round._2 then totalScore += 3
    else if (round._1 + 1) % 3 == round._2 then totalScore += 6

  var actualTotalScore = 0
  for round <- input do
    if round._2 == 0 then
      actualTotalScore += Math.floorMod(round._1 - 1, 3) + 1
    else if round._2 == 1 then
      actualTotalScore += round._1 + 1 + 3
    else
      actualTotalScore += Math.floorMod(round._1 + 1, 3) + 1 + 6


  println("Part 1:")
  println(s"If everything goes to plan, my total score would be $totalScore.")
  println("Part 2:")
  println(s"With the actual strategy guide, my total score would be $actualTotalScore.")
