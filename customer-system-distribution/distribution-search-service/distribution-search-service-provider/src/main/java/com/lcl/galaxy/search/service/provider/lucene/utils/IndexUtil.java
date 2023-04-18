package com.lcl.galaxy.search.service.provider.lucene.utils;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.LogByteSizeMergePolicy;
import org.apache.lucene.index.LogMergePolicy;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;


public class IndexUtil {

    public static IndexWriter getIndexWriter(String indexPath, boolean create) throws IOException {
        // 构建 FS 路径
        Directory dir = FSDirectory.open(Paths.get(indexPath, new String[0]));
        // 分析器，这里构建一个WhitespaceAnalyzer分析器，使用空格进行分析
        Analyzer analyzer = new WhitespaceAnalyzer();
        IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
        // 合并：把不同的索引合并成一个索引
        LogMergePolicy mergePolicy = new LogByteSizeMergePolicy();

        //设置segment添加文档(Document)时的合并频率
        // 值较小,建立索引的速度就较慢
        // 值较大,建立索引的速度就较快,>10适合批量建立索引
        mergePolicy.setMergeFactor(50);

        //设置segment最大合并文档(Document)数
        //值较小有利于追加索引的速度
        //值较大,适合批量建立索引和更快的搜索
        mergePolicy.setMaxMergeDocs(5000);
        if (create) {
            iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        } else {
            iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        }
        IndexWriter writer = new IndexWriter(dir, iwc);
        return writer;
    }
}