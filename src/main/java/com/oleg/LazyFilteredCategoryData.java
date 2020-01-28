package com.oleg;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LazyFilteredCategoryData {

    private CategoryData categoryData;
    private Predicate<CategoryData> categoryDataPredicate;

    public LazyFilteredCategoryData(CategoryData categoryData, Set<String> existingCategories) {
        this.categoryData = categoryData;
        categoryDataPredicate = category -> existingCategories.contains(category.getCode());
    }

    public LazyFilteredCategoryData(CategoryData categoryData, Predicate<CategoryData> filterCondition) {
        this.categoryData = categoryData;
        categoryDataPredicate = filterCondition;
    }


    public String getCode() {
        return categoryData.getCode();
    }

    public String getName() {
        return categoryData.getName();
    }

    public List<LazyFilteredCategoryData> getSubcategories() {
        return categoryData.getSubcategories().stream()
                .filter(categoryDataPredicate)
                .map(category -> new LazyFilteredCategoryData(category, categoryDataPredicate))
                .collect(Collectors.toList());
    }

    public void setCode(String code) {
    }

    public void setName(String name) {
    }

    public void setSubcategories(List<LazyFilteredCategoryData> subcategories) {
    }
}
