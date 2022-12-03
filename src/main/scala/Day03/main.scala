//Rucksack Reorganization

package Day03

import scala.io.Source

@main
def main(args: String*): Unit =
  val filename: String = if args.isEmpty then
    "src/main/scala/Day03/input.txt"
  else
    args(0)

  val input: Vector[String] = (for line <- Source.fromFile(filename).getLines() yield line).toVector

  val rucksacks: Vector[(String, String)] = input.map(s => s.splitAt(s.length/2))

  var items: Map[Char, Int] = Map()

  for rucksack <- rucksacks do
    var foundDouble = false
    var i = 0
    while i < rucksack._1.length && !foundDouble do
      var j = 0
      while j < rucksack._2.length && !foundDouble do
        if rucksack._1(i) == rucksack._2(j) then
          if items.keySet.contains(rucksack._2(j)) then
            items = items + (rucksack._2(j) -> (items(rucksack._2(j)) + 1))
          else
            items = items + (rucksack._2(j) -> 1)
          foundDouble = true
        j += 1
      i += 1

  var totalSum = 0
  for item <- items do
    totalSum += getCharValue(item._1) * item._2

  println("Part 1:")
  println(s"The total sum of the priorities is: $totalSum.")

  val groupSacks: Vector[Vector[String]] = input.grouped(3).toVector

  items = Map()
  for groupSack <- groupSacks do
    var foundTriple = false
    var i = 0
    while i < groupSack(0).length && !foundTriple do
      var j = 0
      while j < groupSack(1).length && !foundTriple do
        var k = 0
        while k < groupSack(2).length && !foundTriple do
          if groupSack(0)(i).equals(groupSack(1)(j)) && groupSack(1)(j).equals(groupSack(2)(k)) then
            if items.keySet.contains(groupSack(0)(i)) then
              items = items + (groupSack(0)(i) -> (items(groupSack(0)(i)) + 1))
            else
              items = items + (groupSack(0)(i) -> 1)
            foundTriple = true
          k += 1
        j += 1
      i += 1

  var totalBadgeSum = 0
  for item <- items do
    totalBadgeSum += getCharValue(item._1) * item._2

  println("Part 2:")
  println(s"The total sum of the badge items is: $totalBadgeSum")

def getCharValue(char: Char): Int =
  val intValue: Int = char.toInt

  if intValue > 64 && intValue < 91 then
    intValue - 38
  else if intValue > 96 && intValue < 123 then
    intValue - 96
  else
    throw IllegalArgumentException(s"$char is not a valid character.")
