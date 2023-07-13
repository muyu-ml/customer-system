package com.lcl.galaxy.microservice.middleground.search.service.provider.service.impl;

import com.lcl.galaxy.microservice.middleground.search.service.provider.config.EsIndexProperties;
import com.lcl.galaxy.microservice.middleground.search.service.provider.controller.vo.SearchParamReq;
import com.lcl.galaxy.microservice.middleground.search.service.provider.entity.CustomerAutoReply;
import com.lcl.galaxy.microservice.middleground.search.service.provider.entity.PinnedQueryConfig;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.page.PageObject;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.vo.Result;
import com.lcl.galaxy.microservice.middleground.search.service.provider.service.CustomerAutoReplySearchService;
import com.lcl.galaxy.microservice.middleground.search.service.provider.service.PinnedQueryConfigService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.*;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.ScriptSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CustomerAutoReplySearchServiceImpl implements CustomerAutoReplySearchService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @Autowired
    private EsIndexProperties esIndexProperties;
    @Autowired
    private PinnedQueryConfigService pinnedQueryConfigService;

    @Override
    public Result<PageObject<CustomerAutoReply>> searchCustomerAutoReplies(SearchParamReq searchParamReq) throws IOException {
        // 填充置顶配置
        fillPinnedQuery(searchParamReq);
        // 1、创建搜索对象
        SearchRequest searchRequest = createSearchRequest(searchParamReq);
        // 2、搜索执行
        SearchResponse response;
        try {
            response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            log.info("搜索结果：{}", response.toString());
        } catch (IOException e){
            log.error("查询异常：{}", e.getMessage());
            return Result.error("查询ES异常");
        }
        // 3、处理搜索结果
        SearchHits searchHits = response.getHits();
        List<CustomerAutoReply> customerAutoReplyList = new ArrayList<>();
        for(SearchHit searchHit : searchHits){
            Map<String, Object> result = searchHit.getSourceAsMap();
            CustomerAutoReply customerAutoReply = convertCustomerAutoReply(result);
            customerAutoReplyList.add(customerAutoReply);
        }
        PageObject<CustomerAutoReply> page = new PageObject<>();
        page.buildPage(customerAutoReplyList, searchHits.getTotalHits().value, Long.valueOf(searchParamReq.getPageNum().toString()),Long.valueOf(searchParamReq.getPageSize().toString()));
        return Result.success(page);
    }

    private void fillPinnedQuery(SearchParamReq searchParamReq) {
        // 从数据库获取置顶配置
        PinnedQueryConfig pinnedQueryConfig = pinnedQueryConfigService.findActivePinnedQueryConfigBySubjectWord(searchParamReq.getKeyWord(), 1);
        try {
            if(pinnedQueryConfig != null){
                searchParamReq.setPinnedContentIds(Arrays.asList(pinnedQueryConfig.getContentIds()));
            }
        } catch (Exception e){
            searchParamReq.setPinnedContentIds(null);
        }

    }


    private CustomerAutoReply convertCustomerAutoReply(Map<String, Object> result) {
        CustomerAutoReply customerAutoReply = new CustomerAutoReply();
        customerAutoReply.setId(Long.parseLong(result.get("id").toString()));
        customerAutoReply.setWord(result.get("word").toString());
        customerAutoReply.setContent(result.get("content").toString());
        customerAutoReply.setSort(Long.parseLong(result.get("sort").toString()));
        customerAutoReply.setIsDeleted(Integer.parseInt(result.get("id").toString()));
        return customerAutoReply;
    }

    private SearchRequest createSearchRequest(SearchParamReq searchParamReq) {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        // 创建搜索条件
        // 1、构建项搜索，指定未被删除的数据
        boolQueryBuilder.must(QueryBuilders.termQuery("is_deleted", searchParamReq.getIsDeleted()));
        // 2、执行多字段搜索
        String keyword = searchParamReq.getKeyWord();
        if(StringUtils.hasText(keyword)) {
            boolQueryBuilder.must(QueryBuilders.multiMatchQuery(keyword, "word", "content")
                    .field("word", 1f)
                    .field("content", 0.25f)
                    .type(MultiMatchQueryBuilder.Type.MOST_FIELDS)
                    .operator(Operator.AND));
        }
        sourceBuilder.query(boolQueryBuilder);

        // 添加置顶搜索支持
        createPinnedQuery(searchParamReq, sourceBuilder);


        // 3、添加分页
        sourceBuilder.from((searchParamReq.getPageNum()-1) * searchParamReq.getPageSize());
        sourceBuilder.size(searchParamReq.getPageSize());
        // 构建一级对象
        SearchRequest searchRequest = new SearchRequest(new String[] {esIndexProperties.getCustomerAutoReplyIndex()}, sourceBuilder);
        log.info("搜索条件sourceBuilder：{}", sourceBuilder.toString());
        log.info("搜索条件searchRequest：{}", searchRequest.toString());
        return searchRequest;
    }

    private void createPinnedQuery(SearchParamReq searchParamReq, SearchSourceBuilder sourceBuilder) {
        List<String> customerAutoReplyIds = searchParamReq.getPinnedContentIds();
        if(customerAutoReplyIds != null){
            String customerAutoReplyString = buildQueryString(customerAutoReplyIds);

            Script script = new Script(
                    "List customerAutoReplyList = " + customerAutoReplyString +";"
                            + "Long customerAutoReplyId = doc['id'].value;"
                            + "if(customerAutoReplyList.contains(customerAutoReplyId)) { return 0; }"
                            + "else {return 1;}"
            );
            ScriptSortBuilder scriptSortBuilder = new ScriptSortBuilder(script, ScriptSortBuilder.ScriptSortType.NUMBER).order(SortOrder.ASC);
            sourceBuilder.sort(scriptSortBuilder);
        }
    }

    private String buildQueryString(List<String> customerAutoReplyIds) {
        List<String> targetIds = new ArrayList<>();
        for (String id : customerAutoReplyIds) {
            String formatId = id + "L";//需要添加L后缀表明是Long类型
            targetIds.add(formatId);
        }
        String[] customerAutoReplyList = targetIds.toArray(new String[0]);
        return Arrays.toString(customerAutoReplyList);
    }
}
