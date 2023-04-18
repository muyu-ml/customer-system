package com.lcl.galaxy.search.service.provider.lucene.index;

import com.lcl.galaxy.search.service.provider.lucene.entity.Book;
import com.lcl.galaxy.search.service.provider.lucene.utils.IndexUtil;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
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
        for (Book book : books) {
            Document document = new Document();
            Field id = new Field("id", book.getId()+"", TextField.TYPE_STORED);
            Field title = new Field("title", book.getTitle(), TextField.TYPE_STORED);
            Field summary = new Field("summary", book.getSummary(), TextField.TYPE_STORED);
            document.add(id);
            document.add(title);
            document.add(summary);
            if(writer.getConfig().getOpenMode() == IndexWriterConfig.OpenMode.CREATE) {
                writer.addDocument(document);
            }else {
                writer.updateDocument(new Term("id", book.getId()+""), document);
            }
        }
        // 关闭索引
        writer.commit();
        writer.close();
    }
}