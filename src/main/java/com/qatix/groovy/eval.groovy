package com.qatix.groovy


import groovy.util.Eval

assert Eval.me('33*3') == 99
assert Eval.me('"foo".toUpperCase()') == 'FOO'

assert Eval.x(4, '2*x') == 8
assert Eval.me('k', 4, '2*k') == 8
assert Eval.xy(4, 5, 'x*y') == 20
assert Eval.xyz(4, 5, 6, 'x*y+z') == 26
println "ok"