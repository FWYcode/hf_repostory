package com.hf.game.service.impl;

import com.hf.game.modul.response.CommonCode;
import com.hf.game.modul.response.QueryResponseResult;
import com.hf.game.modul.response.QueryResult;
import com.hf.game.module.items.ItemPub;
import com.hf.game.module.search.SearchQueryParam;
import com.hf.game.service.ItemSearchServer;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 123 on 2019-7-6.
 */
@Service
public class ItemSearchServerImpl implements ItemSearchServer {
    @Autowired
    RestClient restClient;
    @Autowired
    RestHighLevelClient restHighLevelClient;
    @Value("${hf.item.index}")
    String index;
    @Value("${hf.item.type}")
    String type;
    @Value("${hf.item.source_filed}")
    String source_filed;

    @Override
    public QueryResponseResult<ItemPub> seachList(int page, int size, SearchQueryParam param) {
        //创建搜索对象
        SearchRequest searchRequest = createSearchRequest(index);
        //设置搜索type
        searchRequest.types(type);
        //过滤源字段
        String[] source_filed_array = source_filed.trim().split(",");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.fetchSource(source_filed_array, new String[]{});
        //组装搜索条件
        BoolQueryBuilder assemble = this.assemble(param);
        sourceBuilder.query(assemble);
        //设置分页
        if (page <= 0) {
            page=1;
        }
        if (size <= 0) {
            size=60;
        }
        int from=(page-1)*size;
        sourceBuilder.from(from);
        sourceBuilder.size(size);
        //shz高亮字段
        HighlightBuilder highlightBuilder = setHighlight();
        sourceBuilder.highlighter(highlightBuilder);
        searchRequest.source(sourceBuilder);
        //执行搜索
        QueryResponseResult<ItemPub> result=this.search(searchRequest);
        return result;
    }
    //搜索方法
    private QueryResponseResult<ItemPub> search(SearchRequest searchRequest) {
        QueryResult<ItemPub> queryResult =new QueryResult<>();
        QueryResponseResult<ItemPub> responseResult=null;
        List<ItemPub> items = new ArrayList<>();
        try {
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest);
            SearchHits searchHits = searchResponse.getHits();
            queryResult.setTotal(searchHits.totalHits);
            SearchHit[] hits = searchHits.getHits();
            for (SearchHit hit : hits) {
                ItemPub itemPub=new ItemPub();
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                Integer id = (Integer)sourceAsMap.get("id");
                itemPub.setId(id);
                String itemName = (String) sourceAsMap.get("item_name");
                Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                if (highlightFields != null) {
                    HighlightField itemName1 = highlightFields.get("item_name");
                    if (itemName1!=null) {
                        Text[] fragments = itemName1.fragments();
                        StringBuffer stringBuffer = new StringBuffer();
                        for (Text fragment : fragments) {
                            stringBuffer.append(fragment);
                        }
                        itemName=stringBuffer.toString();
                    }
                }
                itemPub.setItemName(itemName);
                String image= (String) sourceAsMap.get("image");
                itemPub.setImage(image);
                String sellPoint = (String) sourceAsMap.get("sell_point");
                itemPub.setSellPoint(sellPoint);
                Double price = (Double) sourceAsMap.get("price");
                itemPub.setPrice(price);
                String barcode = (String) sourceAsMap.get("barcode");
                itemPub.setBarcode(barcode);
                Integer cate1 = (Integer) sourceAsMap.get("category_id1");
                itemPub.setCategoryId1(cate1);
                Integer cate2 = (Integer) sourceAsMap.get("category_id2");
                itemPub.setCategoryId2(cate2);
                Integer status= (Integer) sourceAsMap.get("status");
                itemPub.setStatus(status);
                items.add(itemPub);
            }
            queryResult.setList(items);
            responseResult = new QueryResponseResult<>(CommonCode.SUCCESS, queryResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseResult;
    }

    //shz高亮字段
    private HighlightBuilder setHighlight() {
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<font class='eslight'>");
        highlightBuilder.postTags("</font>");
        highlightBuilder.fields().add(new HighlightBuilder.Field("item_name"));
        return highlightBuilder;
    }

    //组装搜索条件
    private BoolQueryBuilder assemble(SearchQueryParam param) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (param == null) {
            return boolQueryBuilder;
        }
        if (StringUtils.isNotEmpty(param.getKeyWord())) {
            MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(param.getKeyWord(),
                    "item_name", "sell_point").minimumShouldMatch("70%").field("itemName",10);
            boolQueryBuilder.must(multiMatchQueryBuilder);
        }
        if (StringUtils.isNotEmpty((String)(Object)param.getCategoryId1())) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("category_id1", param.getCategoryId1()));
        }
        if (StringUtils.isNotEmpty((String)(Object)param.getCategoryId2())) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("category_id2", param.getCategoryId2()));
        }
        if (StringUtils.isNotEmpty(param.getItemBarnd())) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("item_barnd", param.getItemBarnd()));
        }
        if (StringUtils.isNotEmpty((String)(Object)param.getPriceMin())) {
            QueryBuilders.rangeQuery("price").gte(param.getPriceMin());
        }
        if (StringUtils.isNotEmpty((String)(Object)param.getPriceMax())) {
            QueryBuilders.rangeQuery("price").lte(param.getPriceMax());
        }

        return boolQueryBuilder;
    }

    private SearchRequest createSearchRequest(String index) {
        return new SearchRequest(index);
    }

}
