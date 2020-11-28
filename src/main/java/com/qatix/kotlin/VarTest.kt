package com.qatix.kotlin

fun getStringLength(obj:Any):Int?{
    if(obj is String){
        return obj.length
    }
    if(obj !is String){
        println("is not String")
    }

    return null
}

fun main(args: Array<String>) {
    var a = 1
    val b = 3 //unchangable

    a = 2
//    b = 4 不可改
    println("a = $a")
    println("b = $b")

    //string
    val s1 = "a is $a"
    a = 3
    val s2 = "${s1.replace("is","was")}, but now is $a"
    println(s2)

    //null check
    //类型后面加?表示可为空
    var age:String? = "23"
//    var age:String? = null
    //npe if age is null
    val ages = age!!.toInt()
    //不做处理返回 null
    var ages1 = age?.toInt()
    //return -1 if age is null
    var ages2 = age?.toInt() ?: -1

    println("age = $age")
    println("ages = $ages")
    println("ages1 = $ages1")
    println("ages2 = $ages2")


    //range
    for (i in 1..5) print(i)
//    for (i in 4..1) print(i) output nothing
    for (i in 4 downTo 1) print(i)

    println("for range step:")
    for( i in 1..10 step 2) print(i)

    for(i in 1 until 3){
        println(i)
    }


    println(getStringLength("4567"))
    println(getStringLength(678))
    println(getStringLength(Object()))

}