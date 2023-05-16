/*****************
 * Program: ToDo List
 * Author: Tim Orgill
 ******************/

fun main(args: Array<String>) {
    println("Hello world!")

    val myList = mutableListOf("One", "Two", "Three")

    myList.add("Wacky")

//    for (item in myList) println("<> $item")
    displayList(myList)

    myList.remove("Wacky")

    displayList(myList)

    addToList(myList, "Silly")

    displayList(myList)

    // removeFromList(myList, "Silly")

    // for (i in 0..myList.lastIndex) println("<${i + 1}> ${myList[i]}")

    updateList(myList, "Three", "Forty")

    displayList(myList)

}

fun displayList(list: MutableList<String>) {
    for (i in 0..list.lastIndex) println("<${i + 1}> ${list[i]}")
    print("\n")
}

fun addToList(list: MutableList<String>, item: String): MutableList<String> {
    list.add(item)
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


