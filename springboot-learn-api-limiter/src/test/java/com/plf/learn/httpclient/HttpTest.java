package com.plf.learn.httpclient;

import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author panlf
 * @date 2021/9/1
 */
public class HttpTest {

    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder(URI.create("http://localhost:8080/api/limiter/hello"))
            .build();
    HttpResponse.BodyHandler<String> responseBodyHandler = HttpResponse.BodyHandlers.ofString();


    @Test
    public void test(){
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for(int i=0;i<5;i++) {
            executorService.submit(()->{
                sendHttp();
            });
        }


        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sendHttp(){
        try {
            HttpResponse<String> response = client.send(request,responseBodyHandler);

            String body = response.body();
            System.out.println(body);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
