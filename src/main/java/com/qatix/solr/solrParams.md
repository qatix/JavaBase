1.1. 查询索引
通过/select搜索索引，Solr制定一些参数完成不同需求的搜索：

 1. q - 查询字符串，必须的，如果查询所有使用*:*。
/select
q : 下面是输入的查询条件，并且支持and or
product：中国 AND
product：北京 OR
product：美丽

2. fq - （filter query）过虑查询，作用：在q查询符合结果中同时是fq查询符合的，例如：：
fq：product：[1 TO 20]
过滤查询价格从1到20的记录。
也可以在“q”查询条件中使用product_price:[1 TO 20]，如下： 
也可以使用“*”表示无限，例如：
20以上：product_price:[20 TO *]
20以下：product_price:[* TO 20]

3. sort - 排序，格式：sort=<field name>+<desc|asc>[,<field name>+<desc|asc>]… 。示例：
sort:product_price asc
按价格降序

4. start - 分页显示使用，开始记录下标，从0开始
5. rows - 指定返回结果最多有多少条记录，配合start来实现分页。

6. fl - 指定返回那些字段内容，用逗号或空格分隔多个。
product_name,product_price
显示商品图片、商品名称、商品价格

7. df-指定一个搜索Field
也可以在SolrCore目录 中conf/solrconfig.xml文件中指定默认搜索Field，指定后就可以直接在“q”查询条件中输入关键字。
df:name

8. wt - (writer type)指定输出格式，可以有 xml, json, php, phps, 后面 solr 1.3增加的，要用通知我们，因为默认没有打开。
9. hl 是否高亮 ,设置高亮Field，设置格式前缀和后缀。

http://localhost:8983/solr/techprod/select?df=name&hl.fl=name&hl.simple.post=%3C%2Fb%3E&hl.simple.pre=%3Cb%3E&hl=on&q=*&sort=inStock%20desc%2Cprice%20DESC

http://localhost:8983/solr/techprod/select?df=name&fl=name%2Cmanu%2Cid%2Cprice&fq=price%3A%5B100%20TO%20*%5D&hl.fl=name&hl.simple.post=%3C%2Fb%3E&hl.simple.pre=%3Cb%3E&hl=on&q=*&rows=5&sort=inStock%20desc%2Cprice%20DESC&start=0&wt=json
```javascript
{
  "responseHeader": {
    "zkConnected": true,
    "status": 0,
    "QTime": 1,
    "params": {
      "df": "name",
      "hl": "on",
      "fl": "name,manu,id,price",
      "start": "0",
      "fq": "price:[100 TO *]",
      "sort": "inStock desc,price DESC",
      "rows": "5",
      "_forwardedCount": "1",
      "hl.simple.pre": "<b>",
      "q": "*",
      "hl.simple.post": "</b>",
      "hl.fl": "name",
      "wt": "json"
    }
  },
  "response": {
    "numFound": 9,
    "start": 0,
    "numFoundExact": true,
    "docs": [
      {
        "id": "3007WFP",
        "name": "Dell Widescreen UltraSharp 3007WFP",
        "manu": "Dell, Inc.",
        "price": 2199.0
      },
      {
        "id": "MA147LL/A",
        "name": "Apple 60 GB iPod with Video Playback Black",
        "manu": "Apple Computer Inc.",
        "price": 399.0
      },
      {
        "id": "6H500F0",
        "name": "Maxtor DiamondMax 11 - hard drive - 500 GB - SATA-300",
        "manu": "Maxtor Corp.",
        "price": 350.0
      },
      {
        "id": "9885A004",
        "name": "Canon PowerShot SD500",
        "manu": "Canon Inc.",
        "price": 329.95
      },
      {
        "id": "VA902B",
        "name": "ViewSonic VA902B - flat panel display - TFT - 19\"",
        "manu": "ViewSonic Corp.",
        "price": 279.95
      }
    ]
  },
  "highlighting": {
    "3007WFP": {
      
    },
    "MA147LL/A": {
      
    },
    "6H500F0": {
      
    },
    "9885A004": {
      
    },
    "VA902B": {
      
    }
  }
}
```