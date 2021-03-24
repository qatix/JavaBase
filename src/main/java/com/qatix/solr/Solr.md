

# Handler
多个handlers (包括是相同的类的实例，却拥有不同的配置) 能够在solrconfig.xml 中配置。
```xml
 <requestHandler name="/foo" default="true" class="my.package.CustomRequestHandler" />
  <requestHandler name="/bar" class="my.package.AnotherCustomRequestHandler" />
    <!-- initialization args may optionally be defined here -->
     <lst name="defaults">
       <int name="rows">10</int>
       <str name="fl">*</str>
       <str name="version">2.1</str>
     <lst>
  </requestHandler>
 
  <requestHandler name="/baz" class="my.package.AnotherCustomRequestHandler">
  <requestHandler name="/update/my-pdf-reader" class="my.package.MyPdfHandler">
```

## 扩展自己的Handler
实现一个SolrRequestHandler 最简单的方法是去扩展 RequestHandlerBase 类。也参考下面的几个Request Handlers。
solr自带的Handler
目前solr提供如下handler给大家使用，下面给出的solr官方文档中hander的文档，如果大家觉得有必要让笔者详细解析每个handler的用法，可以回复一下，笔者将会在接下来的章节中为大家讲解每个handler的具体用法。
Search handlers:
```
DisMaxRequestHandler
LukeRequestHandler
MoreLikeThisHandler
SearchHandler
SpellCheckerRequestHandler
Update handlers:
DataImportHandler

BinaryUpdateRequestHandler
CSVUpdateRequestHandler
ExtractingRequestHandler
JsonUpdateRequestHandler
XmlUpdateRequestHandler
XsltUpdateRequestHandler
```