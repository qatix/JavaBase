package com.qatix.groovy.scriptengine

import org.apache.bsf.BSFManager

String myScript = "println('Hello World')\n  return [1, 2, 3]";
BSFManager manager = new BSFManager();
List answer = (List) manager.eval("groovy", "myScript.groovy", 0, 0, myScript);
//assertEquals(3, answer.size());
println(answer)
println (answer.size())