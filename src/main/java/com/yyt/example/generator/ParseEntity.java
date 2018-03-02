package com.yyt.example.generator;

import java.util.List;

public class ParseEntity {

    private Class aClass;

    private List<String> filedName;

    private String className;

    private String simpleClassName;

    private String id;

    private String wholeClassName;

    private String var;

    private String mean;

    private String module;

    public ParseEntity() {
    }

    public ParseEntity(Class aClass, String id, String mean,String module) {
        this.aClass = aClass;
        this.id = id;
        this.mean = mean;
        this.module = module;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    public List<String> getFiledName() {
        return filedName;
    }

    public void setFiledName(List<String> filedName) {
        this.filedName = filedName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSimpleClassName() {
        return simpleClassName;
    }

    public void setSimpleClassName(String simpleClassName) {
        this.simpleClassName = simpleClassName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWholeClassName() {
        return wholeClassName;
    }

    public void setWholeClassName(String wholeClassName) {
        this.wholeClassName = wholeClassName;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
}
