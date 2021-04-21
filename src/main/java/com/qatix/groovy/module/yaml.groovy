import groovy.yaml.YamlBuilder
import groovy.yaml.YamlSlurper

def ys = new YamlSlurper()
def yaml = ys.parseText '''
language: groovy
sudo: required
dist: trusty

matrix:
  include:
#    - jdk: oraclejdk11
    - jdk: openjdk10
    - jdk: oraclejdk9
    - jdk: oraclejdk8

before_script:
  - |
    unset _JAVA_OPTIONS
'''

println(yaml)
println(yaml.language)
println(yaml.dist)


//builder
// ----

def builder = new YamlBuilder()
builder.records {
    car {
        name 'HSV Maloo'
        make 'Holden'
        year 2006
        country 'Australia'
        homepage new URL('http://example.org')
        record {
            type 'speed'
            description 'production pickup truck with speed of 271kph'
        }
    }
}
println(builder.toString())