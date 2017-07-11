package com.pw.helloworld.kotlin

class ExampleClass(val x: Int, var y: Int, z: Int = 3) {

    // x and y are properties of class
    // Z is not as no var or val

    // Initialiser
    init {
        y = x * z
    }

    // Constructor
    constructor(x: Int) : this(x, 1) {
    }

    // Custom getter
    val myGetter: Int
        get() = x * 3

    // Custom setter (uses 'field' to refer to this field in setter logic)
    var mySettable: String = ""
        set(value) {
            field = "Hi " + value
        }

    // Method
    fun myMethod(): Int {
        return x + y
    }
}

data class ExampleClassWithToStringEqualsHashCodeAndCopy(var x: Int) {

    override fun toString(): String {
        return "Overridden to return " + x
    }
}

enum class ExampleEnum(val myValue: Int) {
    RED(10) {
        override fun toString(): String {
            return "My special toString() for red"
        }
        override fun myMethod(): String {
            return "Its red"
        }
    },
    GREEN(20) {
        override fun myMethod(): String {
            return "Its green"
        }
    },
    BLUE(30) {
        override fun myMethod(): String {
            return "Its blue"
        }
    };

    abstract fun myMethod(): String
}

object ExampleObject {
    val value = "This is effectively a singleton class"
}

abstract class BaseClass {
    open fun doSomethingOverridable() {
    }

    abstract fun doSomethingElse()
}

open class SubClass: BaseClass() {
    // No longer overridable
    final override fun doSomethingOverridable() {
    }

    override fun doSomethingElse() {
    }
}

class SubSubClass: SubClass() {
    override fun doSomethingElse() {
    }
}

// CAN implement multiple interfaces
// CANNOT have concrete properties
interface MyInterface {

    var myProperty : String

    fun get(id : Int): SubClass

    fun store(subClass: SubClass) {
        // Default implementation!
    }
}

interface MyTypedInterface<T> {
    fun put(t: T)
    fun get(id: Int): T
}

fun main(args: Array<String>) {

    // =========
    // Variables
    // =========

    // Mutable - variables
    var streetNo: Int                       // Declared with type
    var streetName: String = "My Street"    // Declared and initialised with type
    var postcode = "EH111SQ"                // Type inferred from initialisation

    // Immutable - values
    val country = "GB"                      // Cannot be changed

    // No implicit type conversion
    val myInt: Int = 123
    val myLong: Long = myInt.toLong()       // Cannot have myLong = myInt

    // Multi-line strings (triple quote)
    val multiLines = """
        Line 1
        Line 2
        Line 3
    """

    // Substitution
    val replacedValue = "String length is ${multiLines.length}"

    // =========
    // For Loops
    // =========

    // Use ranges defined using ..

    // For: up
    for (i in 1..100) {

    }

    // For: down
    for (i in 100..1) {

    }

    // Val of values + Step size
    val indexes = 1..100
    for (i in indexes step 5) {

    }

    val countries = listOf("UK, US", "JPN")
    for (country in countries) {

    }

    // ===========
    // While loops
    // ===========

    var i = 100
    while (i > 0) {
        i--
    }

    i = 100
    do {
        i--
    } while (i > 0)

    // Supports break and continue like Java

    // ============
    // Conditionals
    // ============

    val x = 4
    if (x == 4) {

    }

    if (x == 1) {

    } else if (x == 2) {

    }

    // Conditionals are expressions
    val result = if (x == 1) {
        1
    } else {
        "Not 1"
    }

    // =============
    // When (switch)
    // =============

    // Breaks after a match is found, no break
    // Also expressions
    val resultTypeName = when (result) {
        is String -> "String"
        is Int -> "Int"
        else -> "Other"
    }

    // =========
    // Functions
    // =========

    // Like Void
    fun hello(): Unit {
        println("Hi")
    }

    // Will never return
    fun aThrower(): Nothing {
        throw Exception("My exception")
    }

    // Can declare return type
    fun square(value: Int): Int {
        return value * value
    }

    // Don't need a body
    fun sum(value1: Int, value2: Int) = value1 + value2

    // Can have default params
    fun sum(value1: Int, value2: Int, value3: Int = 0) = value1 + value2 + value3

    val sumResult1 = sum(1, 2)
    val sumResult2 = sum(1, 2, 3)

    // Can name parameters and change order
    val sumResult3 = sum(value2 = 2, value1 = 1)

    // Varargs
    fun reallyPrintNames(vararg names: String) {
        for (name in names) {
            print(name)
        }
    }

    fun printNames(vararg names: String) {
        reallyPrintNames(*names)
    }

    // =======
    // Classes
    // =======

    // Properties and methods
    val classInstace = ExampleClass(1, 2, 3)
    println(classInstace.y)
    println(classInstace.myGetter)
    println(classInstace.myMethod())

    classInstace.mySettable = "Tom"

    // toString, equals, hashCode and copy
    val instance1 = ExampleClassWithToStringEqualsHashCodeAndCopy(1)
    val instance2 = instance1.copy()
    val instancesEquals = instance1 == instance2 // Uses == not equals as we have 'data'

    // Enums
    val enum1 = ExampleEnum.BLUE
    enum1.toString()
    enum1.myValue
    enum1.myMethod()
    enum1.ordinal
    enum1.name

    val enumValues : Array<ExampleEnum> = ExampleEnum.values()
    val redValue : ExampleEnum = ExampleEnum.valueOf("RED")

    // Objects
    val singleton = ExampleObject

    val inlineSingleton = object {
        val MORE_PI = 3.141592654
    }
}