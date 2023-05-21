/********************
 * Program: ToDo List
 * Author: Tim Orgill
 ********************/
import java.util.Scanner
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileReader
import java.io.FileWriter

fun main(args: Array<String>) {
    // Display friendly welcome to user.
    println("What is your name? ")
    val user = readLine()
    println("Welcome, $user, to your To-Do List!")

    // Instantiate various class objects
    val todo = ToDoList()
    val fileManager = FileManager()

    // Initialize list
    var myList = mutableListOf("item")

    // Load list elements from file.
    try {
        myList = fileManager.loadFile()
    } catch (e: Exception) {
        println("An error occurred: ${e.message}")
    }

    // Initialize reader variable using Scanner object
    val reader = Scanner(System.`in`)

    // Create program control loop
    var done = false
    while (!done) {

        // Display menu of options for user to pick from
        todo.printMenu()

        print("Enter your number: ")

        when (reader.nextInt()) {
            1 -> todo.addToList(myList)
            2 -> todo.displayList(myList)
            3 -> todo.updateList(myList)
            4 -> todo.removeFromList(myList)
            5 -> {
                fileManager.saveToFile(myList)
                done = true
                println("Exiting program...")
            }
        }
    }
}

// TODO - record video
// TODO - complete README
// TODO - complete submission doc
// TODO - submit

class ToDoList {
    // Default constructor
    class ToDoList()

    fun printMenu() {
        println("\n<1> Add to List")
        println("<2> Display List")
        println("<3> Update List")
        println("<4> Delete from List")
        println("<5> Save & Exit\n")
    }

    fun displayList(list: MutableList<String>) {
        println("\nYour list:")
        for (i in 0..list.lastIndex) println("\t[${i + 1}]" +
                " ${list[i]}")
        print("\n")
    }

    fun addToList(list: MutableList<String>): MutableList<String> {
        print("Add your item: ")
        val input = readlnOrNull()

        if (list[0] == "item"){
            list.remove("item")
            list.add(input.toString())
        }
        else
            list.add(input.toString())

        return list
    }

    fun removeFromList(list: MutableList<String>): MutableList<String> {
        if (list.count() == 1) {
            println("Sorry, there is only one element left.")
            println("The list cannot be empty")
            println("Try updating the value instead.")
            return list
        }

        // Initialize variable for scope.
        var itemToRemove = ""

        // Loop control to ensure valid input before updating list.
        var valid = false
        while (!valid){
            displayList(list)
            println("Which item would you like to remove? ")
            itemToRemove = readlnOrNull().toString()

            if (list.contains(itemToRemove))
                valid = true
            else {
                println("Item not found in list, try again.")
                println("If you know the item is in the list, check your spelling.")
                println("This checker is case sensitive.")
            }
        }

        // Verify user choice to delete
        println("Are you sure you want to delete this item? (y/n)")
        val verify = readlnOrNull()
        if (verify == "y" || verify == "yes")
        {
            // Remove item
            list.remove(itemToRemove)
            if (!list.contains(itemToRemove))
                println("Item removed successfully.")
            else
                println("Sorry, there was an error processing your request.")
        }

        return list
    }

    fun updateList(list: MutableList<String>): MutableList<String> {
        // Initialize variable for scope.
        var itemToRemove: String = ""

        // Loop control to ensure valid input before updating list.
        var valid = false
        while (!valid){
            displayList(list)
            println("Which item would you like to update? ")
            itemToRemove = readlnOrNull().toString()

            if (list.contains(itemToRemove))
                valid = true
            else {
                println("Item not found in list, try again.")
                println("If you know the item is in the list, check your spelling.")
                println("This checker is case sensitive.")
            }
        }

        println("What would you like to replace it with? ")
        val itemToAdd = readlnOrNull()

        // Get item's index for removal
        val i = list.indexOf(itemToRemove)
        // Remove the item and add a new item in its place
        list.removeAt(i)
        list.add(i, itemToAdd.toString())

        return list
    }
}

class FileManager {

    class FileManager()

    /***********************************************************
    Save items of list to file for loading after program closes.
     ***********************************************************/
    fun saveToFile(list: MutableList<String>) {
        val file = File("todo.txt")
        val writer = BufferedWriter(FileWriter(file, false)) // The second parameter 'true' appends to the file

        try {
            for (item in list) {
                writer.write(item)
                writer.newLine()
            }
            writer.flush()
            println("List saved to file.")
        } catch (e: Exception) {
            println("An error occurred: ${e.message}")
        } finally {
            writer.close()
        }
    }

    /***********************************************************
    Load file contents into list
     ***********************************************************/
    fun loadFile(): MutableList<String> {
        val file = File("todo.txt")
        val list = mutableListOf<String>()

        try {
            val reader = BufferedReader(FileReader(file))
            var line: String?

            while (reader.readLine().also { line = it } != null) {
                list.add(line!!)
            }

            reader.close()
            println("File loaded successfully.")
        } catch (e: Exception) {
            println("An error occurred: ${e.message}")
        }

        return list

    }
}
