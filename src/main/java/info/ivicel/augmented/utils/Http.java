package info.ivicel.augmented.utils;

import info.ivicel.augmented.core.result.Response;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public final class Http {
    private static RestTemplate restTemplate;

    static {
        // 使用 Apache Http Client, 原 RestTemplate 不支持 https
        HttpClient httpClient;
        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient());
        restTemplate = new RestTemplate(requestFactory);
    }

    private static HttpClient httpClient() {
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .build();
        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager(registry);
        manager.setMaxTotal(200);
        manager.setDefaultMaxPerRoute(20);
        manager.setValidateAfterInactivity(2000);
        String cookieSpec;
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setSocketTimeout(65000)
                .setConnectionRequestTimeout(1000)
                .build();
        return HttpClientBuilder.create()
                .setDefaultRequestConfig(config)
                .setConnectionManager(manager)
                .build();
    }

    public static <T> ResponseEntity<Response> getForEntity(String url, Class<T> type) throws RestClientException {
        return getForEntity(url, type, null);
    }

    public static <T> ResponseEntity<Response> getForEntity(String url, Class<T> type, RequestParam params)
            throws RestClientException {
        return Response.success(getForObject(url, type, params));
    }

    public static <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity httpEntity, Class<T> type)
            throws RestClientException {
        return restTemplate.exchange(url, method, httpEntity, type);
    }

    public static <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity httpEntity,
            Class<T> type, Map<String, ?> uriVariable)
            throws RestClientException {
        return restTemplate.exchange(url, method, httpEntity, type, uriVariable);
    }

    public static <T> T getForObject(String url, Class<T> type) throws RestClientException {
        return getForObject(url, type, null);
    }

    public static <T> T getForObject(String url, Class<T> type, RequestParam params) throws RestClientException {
        StringBuilder builder = new StringBuilder();
        if (params != null) {
            for (Entry<String, Object> entry : params.getParams().entrySet()) {
                builder.append(entry.getKey());
                builder.append("=");
                builder.append(entry.getValue());
                builder.append("&");
            }
        }

        return restTemplate.getForObject(url + "?" + builder.toString(), type);
    }


    public static RequestParam requestParam() {
        return new RequestParam();
    }

    public static class RequestParam {
        private Map<String, Object> params;

        public RequestParam() {
            params = new HashMap<>();
        }

        public RequestParam add(String key, Object val) {
            if (key != null && val != null) {
                params.put(key, val);
            }
            return this;
        }

        public Map<String, Object> getParams() {
            return params;
        }
    }

    public static String format(String ids, Function<String, String> mapper) {
        return Arrays.stream(ids.split("[,\\s]")).filter(StringUtils::hasText).map(mapper)
                .collect(Collectors.joining(","));
    }

}