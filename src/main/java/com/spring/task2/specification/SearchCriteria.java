package com.spring.task2.specification;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SearchCriteria {
    private String key;
    private String operation;
    private Object value;


}
