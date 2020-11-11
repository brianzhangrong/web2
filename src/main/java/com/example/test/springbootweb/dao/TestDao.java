package com.example.test.springbootweb.dao;

import com.example.test.springbootweb.constant.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class TestDao {

    Logger log = LoggerFactory.getLogger("TestDao");

    public String version(){
        log.info("web2 dao {}",Version.VERSION);
        return "web2-dao-"+ Version.VERSION;
    }
}
