package com.qatix.groovy.scriptengine

def binding = new Binding()
def engine = new GroovyScriptEngine([tmpDir.toURI().toURL()] as URL[])

while (true) {
    def greeter = engine.run('ReloadingTest.groovy', binding)
    println greeter.sayHello()
    Thread.sleep(1000)
}
