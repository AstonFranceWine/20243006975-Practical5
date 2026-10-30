package com.example.practicalfive;

public class TextProcessor {
    public static String processMunicipality(String municipality) {
        // 如果为空或全是空格，返回 null
        if (municipality == null || municipality.trim().isEmpty()) {
            return null;
        }

        String cleanName = municipality.trim();

        // 忽略大小写进行匹配
        if (cleanName.equalsIgnoreCase("beijing")) {
            return "Beijing";
        } else if (cleanName.equalsIgnoreCase("shanghai")) {
            return "Shanghai";
        } else if (cleanName.equalsIgnoreCase("tianjin")) {
            return "Tianjin";
        } else if (cleanName.equalsIgnoreCase("chongqing")) {
            return "Chongqing";
        }

        // 输入了不支持的词（如 Guangzhou），返回 null
        return null;
    }
}
