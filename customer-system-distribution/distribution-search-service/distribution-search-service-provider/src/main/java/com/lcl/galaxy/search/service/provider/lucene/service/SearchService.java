package com.lcl.galaxy.search.service.provider.lucene.service;

import com.lcl.galaxy.search.service.provider.lucene.entity.Book;
import com.lcl.galaxy.search.service.provider.lucene.index.BookIndex;
import com.lcl.galaxy.search.service.provider.lucene.utils.SearchUtil;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class SearchService {

    private String indexPath = "/Users/conglongli/Documents/workspace/customer-system/customer-system-distribution/distribution-search-service/logs";

    @Autowired
    private BookIndex bookIndex;

    public void write(List<Book> books) throws Exception {

        bookIndex.indexDoc(books);
    }

    //搜索
    public List<Map> search(String value) throws Exception {
        List<Map> list = new ArrayList<>();
        Map map = null;
        Analyzer analyzer = new WhitespaceAnalyzer();
        ExecutorService executorService = null;
        try{
            executorService = Executors.newCachedThreadPool();
            IndexSearcher searcher = SearchUtil.getIndexSearcher(indexPath, executorService);
            // 构建 Query 对象
            String[] fields = {"title", "summary"};
            MultiFieldQueryParser parser = new MultiFieldQueryParser(fields, analyzer);
            Query query = parser.parse(value);
            // 搜索并获取结果
            TopDocs result = searcher.search(query, 1000);
            ScoreDoc[] hits = result.scoreDocs;
            for(int i=0; i< hits.length; i++){
                Document hitDoc = searcher.doc(hits[i].doc);
                map = new HashMap();
                map.put("id", hitDoc.get("id"));
                map.put("title", hitDoc.get("title"));
                map.put("summary", hitDoc.get("summary"));
                list.add(map);
            }
            return list;
        }finally {
            executorService.shutdown();
        }

    }
}
