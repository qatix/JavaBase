package com.qatix.kotlin

fun vars(vararg  vv:Int){
    for(vt in vv){
        print(vt)
    }
}

fun sum(a:Int,b:Int) = a+b

public fun psum(a:Int,b:Int):Int = a+b

fun main(args: Array<String>) {


    vars(1,2,4,5)

    println(sum(2,3))
    println(psum(3,4))

    val sumLambda:(Int, Int) -> Int = { x, y -> x+y}
    println(sumLambda(10,21))
}