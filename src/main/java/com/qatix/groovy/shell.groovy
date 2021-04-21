package com.qatix.groovy

def shell = new GroovyShell()
def result = shell.evaluate '3*5'
def result2 = shell.evaluate(new StringReader('3*5'))
assert result == result2
def script = shell.parse '3*5'
assert script instanceof Script
assert script.run() == 15

println(shell.evaluate("44-12"))


def sharedData = new Binding()
def shell2 = new GroovyShell(sharedData)
def now = new Date()
sharedData.setProperty("text","groovy shell")
sharedData.setProperty('date',now)
String result3 = shell2.evaluate('"At $date, $text"')
println(result3)

shell2.evaluate("foo=123")
println(sharedData.getProperty("foo"))


def shell3 = new GroovyShell()
def b1 = new Binding(x:3)
def b2 = new Binding(x:4)
def script3 = shell3.parse('x = 2*x')
script3.binding = b1
script3.run()
script3.binding = b2
script3.run()
assert b1.getProperty('x') == 6
assert b2.getProperty('x') == 8
assert b1 != b2
println('bindx ok')