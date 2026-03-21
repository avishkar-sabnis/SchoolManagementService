package com.education.schoolmanagement.Model;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.jackson.JacksonComponent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("singleton")
@Component
public class SingletonBean {
    public SingletonBean() {
    }

    @PostConstruct
    public void init(){
        System.out.println("Singleton Bean hashCode:" + this.hashCode());
    }
}
