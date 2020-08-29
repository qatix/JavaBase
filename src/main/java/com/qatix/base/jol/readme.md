

https://my.oschina.net/u/2518341/blog/1838006

 Stackoverflow上看到的Java对象头部mark word和kclass pointer的大小
    从Stackoverflow上看到，Java对象头部有一个mark word和一个klass pointer，

    mark word：32bits architectures上，mark word占32bits，64bits architectures上，mark word占64bits；
    kclass pointer：32bits architectures上，kclass pointer占32bits，64bits architectures上，kclass pointer占64bits，但也可能是32bits，原话是这样"the klass pointer has word size on 32 bit architectures. On 64 bit architectures the klass pointer either has word size, but can also have 4 byte if the heap addresses can be encoded in these 4 bytes"。
    