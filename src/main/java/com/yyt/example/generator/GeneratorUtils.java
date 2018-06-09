package com.yyt.example.generator;

import com.yyt.example.GeneratorMain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class GeneratorUtils {

    public static List<GeneratorTemplate> getTemplates() throws Exception {
        String path = GeneratorMain.class.getClassLoader().getResource("generator").getPath() + "/";
        String[] files = {"tmp.ftl.tmp", "tmp.js.tmp", "tmpController.java.tmp", "tmpRepository.java.tmp", "tmpService.java.tmp"};

        List<GeneratorTemplate> list = new ArrayList<>();

        for (String fileName : files) {
            FileReader fileReader = new FileReader(path + fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";
            StringBuilder sb = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line+"\n");
            }
            GeneratorTemplate generatorTemplate = new GeneratorTemplate(sb.toString(),fileName);
            list.add(generatorTemplate);
        }
        return list;
    }

    public static void  parse(List<ParseEntity> parseEntities){
        for (ParseEntity parseEntity : parseEntities) {
            List<String> fields = new ArrayList<>();
            Method[] methods1 = parseEntity.getaClass().getMethods();
            for (Method method : methods1) {
                String name = method.getName();
                if (name.contains("get")){
                    String get = name.replace("get", "");
                    String s = get.substring(0, 1).toLowerCase();
                    String filedName = s+get.substring(1,get.length());
                    fields.add(filedName);
                }
            }
            parseEntity.setFiledName(fields);

            String wholeClassName = parseEntity.getaClass().getName();
            parseEntity.setWholeClassName(wholeClassName);

            int index = wholeClassName.lastIndexOf(".");
            String className = wholeClassName.substring(index+1);
            parseEntity.setClassName(className);

            parseEntity.setSimpleClassName(className.replace("Entity",""));

            String var = lowerCaseFirst(className);
            parseEntity.setVar(var.replace("Entity",""));
        }
    }

    public static void replaceContent(List<ParseEntity> parseEntities,String path) throws Exception {
        parse(parseEntities);
        for (ParseEntity parseEntity : parseEntities) {
            List<GeneratorTemplate> templates = getTemplates();
            for (GeneratorTemplate template : templates) {
                String content = template.getContent();
                content = content.replaceAll("#className",parseEntity.getClassName())
                       .replaceAll("#id",parseEntity.getId())
                       .replaceAll("#simpleClassName",parseEntity.getSimpleClassName())
                       .replaceAll("#wholeClassName",parseEntity.getWholeClassName())
                       .replaceAll("#var",parseEntity.getVar())
                       .replaceAll("#modlue",parseEntity.getModule())
                       .replaceAll("#mean",parseEntity.getMean());
                List<String> filedNames = parseEntity.getFiledName();
                if (template.getFileName().equals("tmp.js.tmp")){
                    StringBuilder sb = new StringBuilder();
                    for (String filedName : filedNames) {
                        String s = filedName + ": [{ required: true, message: '请输入内容', trigger: 'blur' }],\n";
                        sb.append(s);
                    }
                    content = content.replaceAll("#for",sb.toString());
                }

                if (template.getFileName().equals("tmp.ftl.tmp")){
                    StringBuilder sb = new StringBuilder();

                    StringBuilder sb2 = new StringBuilder();
                    for (String filedName : filedNames) {
                        String s = "            <el-table-column prop=\""+filedName+"\" label=\""+filedName+"\"></el-table-column>\n";
                        sb.append(s);
                        String form = "                    <el-form-item label=\""+filedName+"\" prop=\""+filedName+"\">\n" +
                                "                        <el-input v-model=\"ruleForm."+filedName+"\" maxlength=\"20\"></el-input>\n" +
                                "                    </el-form-item>\n";
                        sb2.append(form);
                    }
                    content = content.replaceAll("#fortable",sb.toString());
                    content = content.replaceAll("#forform",sb2.toString());

                }
                String wholePath = "";
                String fileName = template.getFileName().replaceFirst("tmp",parseEntity.getSimpleClassName()).replaceFirst("tmp","");
                File file = null;
                if (template.getFileName().equals("tmpRepository.java.tmp")){
                    wholePath = path + "/java/com/yyt/example/repository/"+parseEntity.getModule();
                }
                if (template.getFileName().equals("tmpService.java.tmp")){
                    wholePath = path + "/java/com/yyt/example/service/"+parseEntity.getModule();
                }
                if (template.getFileName().equals("tmpController.java.tmp")){
                    wholePath = path + "/java/com/yyt/example/controller/"+parseEntity.getModule();
                }
                if (template.getFileName().equals("tmp.ftl.tmp")){
                    wholePath = path + "/resources/templates/"+parseEntity.getModule();
                    fileName = lowerCaseFirst(fileName);
                }
                if (template.getFileName().equals("tmp.js.tmp")){
                    wholePath = path + "/resources/static/js/"+parseEntity.getModule();
                    fileName = lowerCaseFirst(fileName);
                }
                file = new File(wholePath);
                if (!file .isDirectory()){
                    file .mkdir();
                }
                FileWriter fileWriter = new FileWriter(file.getPath()+"/"+fileName);
                fileWriter.write(content);
                fileWriter.close();
            }
        }
    }

    public static void  outPut(String path){

    }

    public static String lowerCaseFirst(String s){
        String first = s.substring(0, 1).toLowerCase();
        String str = first+s.substring(1,s.length());
        return str;
    }

}
