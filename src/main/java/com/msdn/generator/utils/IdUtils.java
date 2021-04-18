package com.msdn.generator.utils;

import tk.mybatis.mapper.genid.GenId;

import java.util.UUID;

public class IdUtils implements GenId<String> {

    @Override
    public String genId(String table, String column) {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String genId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
