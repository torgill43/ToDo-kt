/*****************
 * Program: ToDo List
 * Author: Tim Orgill
 ******************/
import java.util.Scanner

fun main(args: Array<String>) {
    println("Hello world!")
    val myList = mutableListOf("item")

    val reader = Scanner(System.`in`)
    var done = false

    while (!done) {

        // Display menu of options for user to pick from
        printMenu()

        print("Enter your number: ")
        var input = reader.nextInt()

        when (input) {
            1 -> addToList(myList)
            2 -> println("Delete from List")
            3 -> println("Update List")
            4 -> displayList(myList)
            5 -> {
                done = true
                println("Exiting program...")
            }
        }
    }

//
////    for (item in myList) println("<> $item")
////    displayList(myList)
//
////    myList.remove("Wacky")
//
//    displayList(myList)
//
//    addToList(myList)
//
//    displayList(myList)
//
//    // removeFromList(myList, "Silly")
//
//    // for (i in 0..myList.lastIndex) println("<${i + 1}> ${myList[i]}")
//
//    updateList(myList, "Three", "Forty")
//
//    displayList(myList)

}

fun getMax(num1: Int, num2: Int) = if (num1 > num2) num1 else num2

fun printMenu() {
    println("\n<1> Add to List")
    println("<2> Delete from List")
    println("<3> Update List")
    println("<4> Display List")
    println("<5> Exit Program\n")

}

fun displayList(list: MutableList<String>) {
    println("Your list:")
    for (i in 0..list.lastIndex) println("\t[${i + 1}]1" +
            " ${list[i]}")
    print("\n")
}

fun addToList(list: MutableList<String>): MutableList<String> {
    print("Add your item: ")
    var input = readLine()

    list.add(input.toString())
    return list
}
fun removeFromList(list: MutableList<String>, item: String): MutableList<String> {
    list.remove(item)
    return list
}
fun updateList(list: MutableList<String>,
               itemToRemove: String,
               itemToAdd: String): MutableList<String> {
    val i = list.indexOf(itemToRemove)
    list.removeAt(i)
    list.add(i, itemToAdd)

    return list
}


