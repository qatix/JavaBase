package com.qatix.groovy.design_pattern

class UnixLister {
    private nextInLine

    UnixLister(next) { nextInLine = next }

    def listFiles(dir) {
        if (System.getProperty('os.name') == 'Linux') {
            println "ls $dir".execute().text
        } else {
            nextInLine.listFiles(dir)
        }
    }
}

class WindowsLister {
    private nextInLine

    WindowsLister(next) { nextInLine = next }

    def listFiles(dir) {
        if (System.getProperty('os.name') == 'Windows XP') {
            println "cmd.exe /c dir $dir".execute().text
        } else {
            nextInLine.listFiles(dir)
        }
    }
}

class DefaultLister {
    def listFiles(dir) {
        println("default")
        new File(dir).eachFile { f -> println f }
    }
}

def lister = new UnixLister(new WindowsLister(new DefaultLister()))

lister.listFiles('/tmp')
