package com.qatix.base.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class IndexExample {

    public static void main(String[] args) throws IOException {
        IndexWriter writer = createWriter();
        List<Document> documents = new ArrayList<>();
        documents.add(createDocument(1, "china daily", "china ccc in malysia achivement"));
        documents.add(createDocument(2, "china daily", "Isri Lanka boom catastrophe"));
        documents.add(createDocument(3, "china daily", "City ratio will reach 70% in 2030"));
        documents.add(createDocument(4, "china daily", "30 Cities best to live"));
        documents.add(createDocument(5, "china daily", "city ratio for good"));
        //这些测试多个文档，在搜索时也会搜索出多个，Lucene本身并不去重
        documents.add(createDocument(5, "china daily", "city ratio for good"));
        documents.add(createDocument(10, "BBC news", "Grand Tour TV program in amazon "));
        documents.add(createDocument(12, "BBC news", "British  exit from EU"));
        documents.add(createDocument(13, "BBC news", "Trump Donand exppresed angry with Isral Prisident"));
        documents.add(createDocument(14, "BBA news", "BBA news test1"));
        documents.add(createDocument(15, "BAC news", "BAC news test2"));


        System.out.println("delete all");
        writer.deleteAll();
        writer.addDocuments(documents);
        System.out.println("add documents");
        writer.commit();
        System.out.println("commit done");
        writer.close();
        System.out.println("write close");
    }

    private static IndexWriter createWriter() throws IOException {
        FSDirectory dir = FSDirectory.open(Paths.get(Constant.INDEX_DIR));
        IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
        return new IndexWriter(dir, config);
    }

    private static Document createDocument(Integer id, String title, String content) {
        Document doc = new Document();
        doc.add(new StringField("id", id.toString(), Field.Store.YES));
        doc.add(new TextField("title", title, Field.Store.YES));
        doc.add(new TextField("content", content, Field.Store.YES));
        return doc;
    }


}
