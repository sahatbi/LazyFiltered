package com.oleg;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static java.lang.System.currentTimeMillis;

public class Main {

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        CategoryData main = new CategoryData("0", "Main");
        CategoryData men = new CategoryData("3", "Men");
        main.addSubcategory(men);

        CategoryData menShirts = new CategoryData("3.1", "Shirts");
        men.addSubcategory(menShirts);

        CategoryData menPolo = new CategoryData("3.1.1", "Polo");
        CategoryData menCasual = new CategoryData("3.1.2", "Casual");
        menShirts.addSubcategory(menPolo);
        menShirts.addSubcategory(menCasual);

        CategoryData menPants = new CategoryData("3.2", "Pants");
        men.addSubcategory(menPants);

        CategoryData menRegular = new CategoryData("3.2.1", "Regular");
        CategoryData menShorts = new CategoryData("3.2.2", "Shorts");
        menPants.addSubcategory(menRegular);
        menPants.addSubcategory(menShorts);

        CategoryData women = new CategoryData("2", "Women");
        main.addSubcategory(women);

        CategoryData womenShirts = new CategoryData("2.1", "Shirts");
        women.addSubcategory(womenShirts);

        CategoryData womenPolo = new CategoryData("2.1.1", "Polo");
        CategoryData womenCasual = new CategoryData("2.1.2", "Casual");
        womenShirts.addSubcategory(womenPolo);
        womenShirts.addSubcategory(womenCasual);

        CategoryData womenPants = new CategoryData("2.2", "Pants");
        women.addSubcategory(womenPants);

        CategoryData womenRegular = new CategoryData("2.2.1", "Regular");
        CategoryData womenShorts = new CategoryData("2.2.2", "Shorts");
        womenPants.addSubcategory(womenRegular);
        womenPants.addSubcategory(womenShorts);

        CategoryData promo = new CategoryData("4", "Promo");
        main.addSubcategory(promo);

        CategoryData caps = new CategoryData("4.1", "Caps");
        CategoryData bags = new CategoryData("4.2", "Bags");
        promo.addSubcategory(caps);
        promo.addSubcategory(bags);


        long start = currentTimeMillis();
        StringWriter stringWriter = new StringWriter();
        objectMapper.writeValue(stringWriter, main);
        System.out.println(stringWriter.toString());
        long finish = currentTimeMillis();
        System.out.println(finish - start);

        start = currentTimeMillis();
        Set<String> existing = new HashSet<>(Arrays.asList("0", "2", "2.1", "2.1.2", "2.2", "3", "3.1", "3.2", "3.2.1", "4", "4.1"));
        LazyFilteredCategoryData lazyFiltered = new LazyFilteredCategoryData(main, existing);
        stringWriter = new StringWriter();
        objectMapper.writeValue(stringWriter, lazyFiltered);
        System.out.println(stringWriter.toString());
        finish = currentTimeMillis();
        System.out.println(finish - start);


        start = currentTimeMillis();
        existing = new HashSet<>(Arrays.asList("0", "2", "2.1", "2.1.1", "2.1.2", "2.2", "2.2.1", "3", "3.1", "3.2", "3.2.1", "4", "4.1", "4.2"));
        lazyFiltered = new LazyFilteredCategoryData(main, existing);
        stringWriter = new StringWriter();
        objectMapper.writeValue(stringWriter, lazyFiltered);
        System.out.println(stringWriter.toString());
        finish = currentTimeMillis();
        System.out.println(finish - start);

        existing = new HashSet<>(Arrays.asList("0", "2", "2.1", "2.1.1", "2.1.2", "2.2"));
        lazyFiltered = new LazyFilteredCategoryData(main, existing);
        stringWriter = new StringWriter();
        objectMapper.writeValue(stringWriter, lazyFiltered);
        System.out.println(stringWriter.toString());


        existing = new HashSet<>(Arrays.asList("0", "2", "3"));
        lazyFiltered = new LazyFilteredCategoryData(main, existing);
        stringWriter = new StringWriter();
        objectMapper.writeValue(stringWriter, lazyFiltered);
        System.out.println(stringWriter.toString());


    }
}
