package com.lcl.galaxy.customer.service.provider.lucene.index;

import com.lcl.galaxy.customer.service.provider.lucene.entity.Book;
import com.lcl.galaxy.customer.service.provider.lucene.utils.IndexUtil;
import org.apache.lucene.index.IndexWriter;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class BookIndex {

    private String indexPath = "/Users/conglongli/Documents/workspace/customer-system/customer-system-distribution/distribution-search-service/logs";

    private IndexWriter writer;

    public BookIndex() {
        try {
            File file = new File(indexPath);
            if (!file.exists()) {
                file.mkdir();
            }
            this.writer = IndexUtil.getIndexWriter(indexPath, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void indexDoc(List<Book> books) throws Exception {
    }
}