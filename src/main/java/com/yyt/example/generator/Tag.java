package com.yyt.example.generator;

public class Tag {

    private String tag;

    private String prefix;

    private String suffix;

    private String replaceContent;

    public Tag() {
    }

    public Tag(String tag, String prefix, String suffix) {
        this.tag = tag;
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getReplaceContent() {
        return replaceContent;
    }

    public void setReplaceContent(String replaceContent) {
        this.replaceContent = replaceContent;
    }
}
