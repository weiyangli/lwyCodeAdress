package ssm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;
import ssm.bean.EsBean;
@Service
public class EsService {

    @Autowired(required=false)
    private ElasticsearchTemplate elasticsearchTemplate;

    public void esAddData(Object school,long schoolId) {
        IndexQuery indexQuery = new IndexQueryBuilder().withIndexName(EsBean.ES_INDEX_NAME)
                .withType(EsBean.ES_TYPE).withId(schoolId+"").withObject(school).build();
        elasticsearchTemplate.index(indexQuery);
    }
}
