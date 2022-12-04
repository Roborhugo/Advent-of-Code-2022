//Camp Cleanup

package Day04

import scala.io.Source

@main
def main(args: String*): Unit =
  val filename: String = if args.isEmpty then
    "src/main/scala/Day04/input.txt"
  else
    args(0)

  val input: Vector[Vector[Vector[Int]]] = (for line <- Source.fromFile(filename).getLines() yield line).toVector
    .map(s => s.split(",").toVector)
    .map(v => v.map(s => s.split("-").toVector))
    .map(v => v.map(v => v.map(s => s.toInt)))


  val assignmentList: Vector[(Range, Range)] =
    input.map(v => (v(0)(0) to v(0)(1), v(1)(0) to v(1)(1)))

  var nbrContainedRanges = 0
  for pair <- assignmentList do
    if pair._1.containsSlice(pair._2) || pair._2.containsSlice(pair._1) then
      nbrContainedRanges += 1

  println("Part 1:")
  println(s"One range fully contains the other in $nbrContainedRanges pairs.")

  var nbrOverlappingRanges = 0
    for pair <- assignmentList do
      var containsOverlap = false
      for section <- pair._1 do
        if pair._2.contains(section) then
          containsOverlap = true

      if containsOverlap then
        nbrOverlappingRanges += 1


  println("Part 2:")
  println(s"The ranges overlap in $nbrOverlappingRanges pairs.")

