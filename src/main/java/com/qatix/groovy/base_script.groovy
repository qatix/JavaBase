package com.qatix.groovy

import groovy.transform.BaseScript

abstract class MyBaseClass extends Script {
    String name
    public void greet() { println "Hello, $name!" }
}

// --- use baseScript
@BaseScript MyBaseClass baseScript
setName 'Judith2'
greet()