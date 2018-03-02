package com.yyt.example.generator;

import java.util.HashMap;
import java.util.Map;

public class GeneratorTemplate {

    private String fileName;

    private String content;

    private Map<String, Tag> tags;

    private String result;

    public GeneratorTemplate() {
    }

    public GeneratorTemplate(String content,String fileName) {
        this.content = content;
        this.fileName = fileName;
        this.tags = getDefaultTag();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, Tag> getTags() {
        return tags;
    }

    public void setTags(Map<String, Tag> tags) {
        this.tags = tags;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Map<String, Tag> getDefaultTag() {
        Map<String, Tag> map = new HashMap<>();
        map.put("className", new Tag("#className", null, null));
        map.put("simpleClassName", new Tag("#simpleClassName", null, null));
        map.put("id", new Tag("#id", null, null));
        map.put("wholeClassName", new Tag("#wholeClassName", null, null));
        map.put("var", new Tag("#var", null, null));
        map.put("mean", new Tag("#mean", null, null));
        map.put("repeat", new Tag("#for", "#for", "#/for"));
        map.put("itemName", new Tag("#itemName", null, null));
        map.put("itemMean", new Tag("#itemMean", null, null));
        return map;
    }
}
