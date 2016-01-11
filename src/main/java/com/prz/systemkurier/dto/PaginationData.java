package com.prz.systemkurier.dto;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaginationData<T> {

    private Integer totalItems;

    private List<T> data;

    public PaginationData(){

    }

    public PaginationData(Integer totalItems, List<T> data){
        this.data = data;
        this.totalItems = totalItems;
    }
}
