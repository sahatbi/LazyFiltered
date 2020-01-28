package com.oleg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CategoryData {

    private String code;
    private String name;
    private List<CategoryData> subcategories;

    public CategoryData(String code, String name)
    {
        this.code = code;
        this.name = name;
        this.subcategories = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CategoryData> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<CategoryData> subcategories) {
        this.subcategories = new ArrayList<>(subcategories == null ? Collections.emptyList() : subcategories);
    }

    public void addSubcategory(CategoryData category)
    {
        subcategories.add(category);
    }
}
