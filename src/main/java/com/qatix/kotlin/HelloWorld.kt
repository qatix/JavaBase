package com.qatix.kotlin

class Greeter(val name:String){
    fun greet(){
        println("hello ,$name")
    }
}

fun main() {
    println("hello kotlin")
    Greeter("ahzna").greet()
}

