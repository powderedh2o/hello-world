package com.pw.helloworld.kotlin


val aTopLevelConstant = 123

object MyConstants {
    val aConstantInASingletonObject = 456
}

fun main(args: Array<String>) {

    // ====
    // Null
    // ====

    var canBeNull: String? = null

    if (canBeNull != null) {
        // Smart cast to nul-null
        println(canBeNull.length)
    }

    // Will print null when null
    println(canBeNull?.length)

    // Will throw NPE when null
    println(canBeNull!!.length)

    // =======
    // Casting
    // =======

    var unknownType: Any = 10
    var castToSring = unknownType as? String

    // Will print null when not String
    println(castToSring?.length)

    // =================
    // Pairs and Triples
    // =================

    fun getAPair(): Pair<String, Int> {
        return Pair("Tom", 45)
    }

    fun getATriple(): Triple<String, Int, Float> {
        return Triple("Tom", 45, 1.23f)
    }

    getAPair().first
    getAPair().second

    getATriple().first
    getATriple().second
    getATriple().third

    val (name, age, height) = getATriple()
    println(age)

    // For loops with pairs
    val pairs = listOf(Pair("Tom", 45), Pair("Bev", 21))
    for ((name, age) in pairs) {
        println(name + age)
    }

    // ==========
    // Exceptions (there are no checked exceptions)
    // ==========

    class MyException(msg: String): Throwable(msg) {
    }

    fun checkWhole(obj: Any) {
        when (obj) {
            !is Int, Long -> throw MyException("Not a whole")
        }
    }

    // try/catch is an expression like if/else and when
    val result = try {
        checkWhole(1.23)
        1
    } catch (e: MyException) {
        println("Message is ${e.message}")
        2
    } catch (e: Throwable) {
        3
    } finally {
        // No return value in finally block
    }

    // =========
    // Constants
    // =========

    println(aTopLevelConstant)
    println(MyConstants.aConstantInASingletonObject)

    // ======================
    // Functional programming
    // ======================

    fun sum(x: Int, y: Int): Int {
        return x + y
    }

    fun square(x: Int): Int {
        return x * x
    }

    fun mySum(x: Int, y: Int, funcArg: (Int, Int) -> Int) {
        funcArg(x, y)
    }

    fun mySquare(x: Int, funcArg: (Int) -> Int) {
        funcArg(x)
    }

    // Pass in pre-defined function
    mySum(1, 2, ::sum)

    // Lambda expression: function defined inline using {args -> return}
    mySum(1, 2, {x, y -> x + y})

    // Single argument Lambda expression can refer to 'it'
    mySquare(2, ::square)
    mySquare(2, {it * it})
    mySquare(2) {it * it} // When last param, function param can be outside of ()

    // Anonymous function (has no name)
    mySquare(2, fun(i: Int): Int {return i * i})

    // Closures
    fun outsideFunction() {

        for (number in 1..20) {

            // number refers to value defined outside of itself
            mySquare(number, { it * number })
        }
    }

    // Extension functions
    fun String.hello(): String {
        return "Hello " + this
    }

    fun String.toString(): String {
        return "This will be ignored as class def takes precedence"
    }

    println("Tom".hello())

    // When a function is added to base class and subclass, function used depends on how variable is declared
    // => is declared as base uses base version
}