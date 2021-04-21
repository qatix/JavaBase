package com.qatix.groovy

@Grab('com.google.guava:guava:r09')

import com.google.common.base.*
def split(string) {
    [on: { sep ->
        [trimming: { trimChar ->
            Splitter.on(sep).trimResults(CharMatcher.is(trimChar as char)).split(string).iterator().toList()
        }]
    }]
}

def result1 = Splitter.on(',').trimResults(CharMatcher.is('_' as char)).split("_a ,_b_ ,c__").iterator().toList()

def result2 = split "_a ,_b_ ,c__" on ',' trimming '_'
println(result1)
println(result2)
