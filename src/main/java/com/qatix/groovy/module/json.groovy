import groovy.json.JsonGenerator
import groovy.json.JsonOutput
import groovy.json.JsonSlurper

def jsonSlurper = new JsonSlurper()
def object = jsonSlurper.parseText('{ "name": "John Doe" } /* some comment */')

assert object instanceof Map
assert object.name == 'John Doe'
println(object.name)


def object2 = jsonSlurper.parseText('{ "myList": [4, 8, 15, 16, 23, 42] }')

assert object2 instanceof Map
assert object2.myList instanceof List
assert object2.myList == [4, 8, 15, 16, 23, 42]
println(object2.myList)


def object3 = jsonSlurper.parseText '''
    { "simple": 123,
      "fraction": 123.66,
      "exponential": 123e12
    }
'''
assert object3 instanceof Map
assert object3.simple.class == Integer
assert object3.fraction.class == BigDecimal
assert object3.exponential.class == BigDecimal
println(object3.fraction)


def object4 = [
        "name": "groovy",
        "age" : 10]
def jsonStr = JsonOutput.toJson(object4)
println(object4.name)
println(jsonStr)


class Person {
    String name
}

def jsonStr2 = JsonOutput.toJson(
        [new Person(name: "hellogroovy"),
         new Person(name: "hellojava")
        ]
)
println(jsonStr2)


class Person2 {
    String name
    String title
    int age
    String password
    Date dob
    URL webUrl
}

Person2 p2 = new Person2(
        name: "qatix",
        title: "groovy test",
        age: 10,
        password: 'secret',
        dob: Date.parse('yyyy-MM-dd', '2020-01-31'),
        webUrl: new URL('http://baidu.com')
)
def jsonGenerater = new JsonGenerator.Options()
        .excludeNulls()
        .dateFormat('yyyy@MM')
        .excludeFieldsByName('age', 'password')
        .excludeFieldsByType(URL)
        .build()
def jsonStr3 = jsonGenerater.toJson(p2)
println(jsonStr3)

//pretty
println(JsonOutput.prettyPrint(JsonOutput.toJson(p2)))