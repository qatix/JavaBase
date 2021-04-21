package com.qatix.groovy

class ReadFile {
    public static void main(String[] args) {
        new File('tmp/file1.txt').eachLine("UTF-8") {
            println(it)
        }

        new File('tmp/file2.txt').withReader("UTF-8") {
            reader ->
                {
                    reader.eachLine {
                        println(it)
                    }
                }
        }

    }
}
