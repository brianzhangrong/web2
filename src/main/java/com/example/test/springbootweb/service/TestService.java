package com.example.test.springbootweb.service;

import com.example.test.springbootweb.constant.Version;
import com.example.test.springbootweb.dao.TestDao;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    Logger log = LoggerFactory.getLogger("TestService");

    @Resource
    TestDao testDao;
    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build();

    public String version(){
        log.info("web2 service {}",Version.VERSION);
        Request getRequest =
                new Request.Builder().get().url("http://web3:8080/test").build();
        Call getCall = okHttpClient.newCall(getRequest);
        Response execute = null;
        try {
            execute = getCall.execute();
            if(execute.isSuccessful()){
                return "web2 svc-"+ Version.VERSION+","+testDao.version()+"--->"+execute.body().string();
            }
        } catch (IOException e) {

        }
        return "web3 error";

    }
}
