package com.qatix.base.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;


public class SearchExample {
    public static void main(String[] args) throws IOException, ParseException {
        IndexSearcher searcher = createSearcher();
        TopDocs foundDocs = searchById(2, searcher);
        System.out.println("id query found:" + foundDocs.totalHits);
        for (ScoreDoc scoreDoc : foundDocs.scoreDocs) {
            Document doc = searcher.doc(scoreDoc.doc);
            System.out.println("doc:" + doc.get("id") + "|" + doc.get("title") + "|" + doc.get("content"));
        }

        System.out.println("====================");
        foundDocs = searchByContent("city", searcher);
        System.out.println("content query city found:" + foundDocs.totalHits);
        for (ScoreDoc scoreDoc : foundDocs.scoreDocs) {
            Document doc = searcher.doc(scoreDoc.doc);
            System.out.println("doc:" + doc.get("id") + "|" + doc.get("title") + "|" + doc.get("content"));
        }

        System.out.println("====================");
        foundDocs = searchByTitlePrefix("bb", searcher);
        System.out.println("content query prefix found:" + foundDocs.totalHits);
        for (ScoreDoc scoreDoc : foundDocs.scoreDocs) {
            Document doc = searcher.doc(scoreDoc.doc);
            System.out.println("doc:" + doc.get("id") + "|" + doc.get("title") + "|" + doc.get("content"));
        }
    }

    private static TopDocs searchById(Integer id, IndexSearcher searcher) throws ParseException, IOException {
        QueryParser queryParser = new QueryParser("id", new StandardAnalyzer());
        Query idQuery = queryParser.parse(id.toString());
        return searcher.search(idQuery, 10);
    }

    private static TopDocs searchByContent(String keyword, IndexSearcher searcher) throws ParseException, IOException {
        QueryParser queryParser = new QueryParser("content", new StandardAnalyzer());
        Query contentQuery = queryParser.parse(keyword);
        return searcher.search(contentQuery, 10);
    }

    private static TopDocs searchByTitlePrefix(String prefix, IndexSearcher searcher) throws ParseException, IOException {
        Term term = new Term("title",prefix);
        Query prefixQuery = new PrefixQuery(term);
        return searcher.search(prefixQuery, 10);
    }

    private static IndexSearcher createSearcher() throws IOException {
        IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(Constant.INDEX_DIR)));
        return new IndexSearcher(reader);
    }
}