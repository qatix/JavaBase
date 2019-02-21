package com.qatix.base.model;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.List;

public class DtoUtil {
    public static void test(){
        ModelMapper modelMapper = new ModelMapper();
        ProductA productA = new ProductA();
        productA.setName("Apple");

        //language=JSON
        String feature = "[\n" +
                "  {\n" +
                "    \"title\": \"feature1\",\n" +
                "    \"intro\": \"intro1\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"title\": \"feature2222\",\n" +
                "    \"intro\": \"intro222\"\n" +
                "  }\n" +
                "]\n";
        productA.setFeature(feature);
        System.out.println(productA.toString());

        ProductB productB = modelMapper.map(productA,ProductB.class);
        System.out.println(productB.toString());
        productB.setFeature(JSON.parseArray(productA.feature, ProductB.FeatureItem.class));
        System.out.println(productB.toString());
        System.out.println(JSON.toJSON(productB));
    }

    public static void main(String[] args) {
        test();
    }

    @Data
    public static class ProductA{
        private String name;
        private String feature;
    }

    @Data
    public static class ProductB{
        private String name;
        private List<FeatureItem> feature;

        @Data
        public static class FeatureItem{
            private String title;
            private String intro;
        }
    }

}
