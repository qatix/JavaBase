package com.qatix.base.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UpdateExample {

    public static void main(String[] args) throws IOException {
        IndexWriter writer = createWriter();
        List<Document> documents = new ArrayList<>();
        documents.add(createDocument(1, "china daily", "china ddd in malysia achivement"));
        writer.updateDocuments(new Term("id","1"),documents);

        writer.updateDocument(new Term("id","2"),
                createDocument(2, "china daily", "china ddd in malysia achivement"));


        writer.commit();
        writer.close();
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
