package com.lcl.galaxy.distribution.im.server;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.text.NumberFormat;
import java.util.*;


@Slf4j
public class BloomFilterTest {
    public static final Integer orderNums = 1000000;

    @Test
    public void bloom(){
        // 创建一个布隆过滤器对象
        BloomFilter<String> bf = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), BloomFilterTest.orderNums, 0.03D);

        List<String> uuidQueryList = new ArrayList<>();
        Set<String> uuidQuerySet = new HashSet<>();
        long start = System.currentTimeMillis();

        // 生成 100w 个uuid，分别放到三个容器中
        for (int i=0; i<orderNums; i++) {
            String uuid = UUID.randomUUID().toString();
            bf.put(uuid);
            uuidQueryList.add(uuid);
            uuidQuerySet.add(uuid);
        }
        log.info("随机初始化100w个订单号耗时：{} ms", System.currentTimeMillis() - start);

        // 布隆过滤器正确判断的订单号个数
        int correct = 0;
        // 布隆过滤器错误判断的订单号个数
        int wrong = 0;
        // 假设，测试的订单号数量为 1w 个
        int testNum = 10000;
        String uuidTest;
        for (int i=0; i<testNum; i++) {
            // 从已有的 100w 个订单中取出100个
            uuidTest = i%100==0 ? uuidQueryList.get(i) : UUID.randomUUID().toString();
            // 开始判断，布隆过滤器来一波
            if(bf.mightContain(uuidTest)) {
                // 如果集合中存在，说明真的存在
                if(uuidQuerySet.contains(uuidTest)){
                    correct++;
                }else {
                    wrong++;
                }
            }
        }
        log.info("==========================");
        log.info("在已知已存在的100w个订单号中，从其中选100个已存在的订单号，其中布隆过滤器判断确实存在的数量有 {} 个", correct);
        log.info("在已知已存在的100w个订单号中，外界随机生成9900个新订单号，其中布隆过滤器误判存在的数量有 {} 个，挡住的请求有 {} 个", wrong, testNum-wrong);
        NumberFormat percent = NumberFormat.getPercentInstance();
        // 保留两位小数
        percent.setMaximumFractionDigits(2);
        float wp = (float) wrong/(testNum-100);
        float rp = (float) (testNum-100-wrong)/(testNum-100);
        log.info("==========================");
        log.info("布隆过滤器的正确率（抵挡住的）：{}", percent.format(rp));
        log.info("布隆过滤器的误报率（没有抵挡住的）：{}", percent.format(wp));
    }

}
