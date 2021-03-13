
-- start mysql 5.7.8
CREATE TABLE tab_base_info (
   id BIGINT NOT NULL PRIMARY KEY auto_increment,
    content json
);


 INSERT INTO tab_base_info (content)
 VALUES
(
   '{"author": "zhang", "blog": "https://www.baidu.com/"}'
);

INSERT INTO tab_base_info (content)
VALUES
(
    '{"author": "li", "blog": "https://www.baidu2.com/"}'
);

insert into tab_base_info (content)values('{"blog": "https://blog.csdn.net/111", "account": "anzy1"}');

insert into tab_base_info (content)values('{"blog": "https://blog.csdn.net/223", "account": "jef","age":33}');
insert into tab_base_info (content)values('{"blog": "https://blog.csdn.net/55", "account": "kis","age":22,"books":["b1","b2","b3"]}');


select id,content from tab_base_info;

select JSON_EXTRACT(content,'$.blog'),JSON_EXTRACT(content,'$.account') from tab_base_info;

select JSON_EXTRACT(content,'$.blog'),JSON_EXTRACT(content,'$.account'),JSON_EXTRACT(content,'$.age') from tab_base_info;

select JSON_EXTRACT(content,'$.blog'),JSON_EXTRACT(content,'$.account'),JSON_EXTRACT(content,'$.age'),JSON_EXTRACT(content,'$.books') from tab_base_info;


UPDATE tab_base_info
    SET content = json_replace(content, '$.author', "tom")
    WHERE id = 1;