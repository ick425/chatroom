//package com.github.face.config;
//
//import co.elastic.clients.elasticsearch.ElasticsearchClient;
//import co.elastic.clients.json.jackson.JacksonJsonpMapper;
//import co.elastic.clients.transport.ElasticsearchTransport;
//import co.elastic.clients.transport.rest_client.RestClientTransport;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.http.HttpHost;
//import org.apache.http.auth.AuthScope;
//import org.apache.http.auth.UsernamePasswordCredentials;
//import org.apache.http.client.CredentialsProvider;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.impl.client.BasicCredentialsProvider;
//import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestClientBuilder;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.util.StringUtils;
//
///**
// * ES配置
// *
// * @author wo
// */
//@Slf4j
//@Configuration
//public class ElasticSearchConfig {
//
//    @Value("${spring.elasticsearch.rest.uris}")
//    private String hosts;
//
//    @Value("${spring.elasticsearch.rest.enable}")
//    private Boolean enable;
//
//    @Value("${spring.elasticsearch.rest.username}")
//    private String userName;
//
//    @Value("${spring.elasticsearch.rest.password}")
//    private String passWord;
//
//
//    @Bean
//    public void elasticsearchClient() {
//        if (!enable) {
//            return;
//        }
//        HttpHost[] httpHosts = toHttpHost();
//        // 无验证信息
//        //RestClient restClient = RestClient.builder(httpHosts).build();
//
//        // 有验证信息
//        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//        credentialsProvider.setCredentials(
//                AuthScope.ANY, new UsernamePasswordCredentials(userName, passWord));
//
//        RestClientBuilder builder = RestClient.builder(httpHosts);
//        builder.setRequestConfigCallback(
//                requestConfigBuilder -> requestConfigBuilder.setSocketTimeout(60000).setConnectTimeout(5000));
//        builder.setHttpClientConfigCallback(
//                httpAsyncClientBuilder -> httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
//        RestClient restClient = builder.build();
//        // Create the transport with a Jackson mapper
//        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
//        // And create the API client
//        return;
//    }
//
//    private HttpHost[] toHttpHost() {
//        if (!StringUtils.hasLength(hosts)) {
//            throw new RuntimeException("invalid elasticsearch configuration. elasticsearch.hosts不能为空！");
//        }
//
//        // 多个IP逗号隔开
//        String[] hostArray = hosts.split(",");
//        HttpHost[] httpHosts = new HttpHost[hostArray.length];
//        HttpHost httpHost;
//        for (int i = 0; i < hostArray.length; i++) {
//            String[] strings = hostArray[i].split(":");
//            httpHost = new HttpHost(strings[0], Integer.parseInt(strings[1]), "http");
//            httpHosts[i] = httpHost;
//        }
//        return httpHosts;
//    }
//}