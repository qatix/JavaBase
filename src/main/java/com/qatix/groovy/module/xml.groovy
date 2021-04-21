package com.qatix.groovy.module

import groovy.xml.slurpersupport.GPathResult

def text = '''
    <list>
        <technology>
            <name>Groovy</name>
        </technology>
    </list>
'''

def list = new XmlSlurper().parseText(text)

//assert list instanceof GPathResult
assert list.technology.name == 'Groovy'
println list.size()
println list.technology.name