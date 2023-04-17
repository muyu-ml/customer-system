package com.lcl.galaxy.customer.service.provider.lucene.service;

import com.lcl.galaxy.customer.service.provider.lucene.entity.Book;
import com.lcl.galaxy.customer.service.provider.lucene.index.BookIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

        return null;
    }
}
