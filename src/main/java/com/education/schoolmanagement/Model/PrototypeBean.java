package com.education.schoolmanagement.Model;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
public class PrototypeBean {
    public PrototypeBean() {
    }

    @PostConstruct
    public void init(){
        System.out.println("PrototypeBean hashCode:" + this.hashCode());
    }
}
