package com.yyt.example;

import com.yyt.example.entity.AEntity;
import com.yyt.example.entity.sys.MenuEntity;
import com.yyt.example.generator.GeneratorUtils;
import com.yyt.example.generator.ParseEntity;

import java.util.ArrayList;
import java.util.List;

public class GeneratorMain {

    public static void main(String[] args) throws Exception {
        List<ParseEntity> parseEntities = new ArrayList<>();
        parseEntities.add(new ParseEntity(AEntity.class, "Integer", "A管理", "sys"));
        GeneratorUtils.replaceContent(parseEntities, "e:\\project\\example\\src\\main");
    }
}
