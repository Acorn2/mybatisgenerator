package com.msdn.generator.entity;

import java.io.File;
import java.util.Set;

public class Config {

    public static final String OutputPath = "." + File.separator + "output";

    public static final String Author = "hresh";

    // 公共实体类字段
    public static final String[] COMMON_COLUMNS = new String[]{
            "is_deleted", "create_user_code", "create_user_name", "create_date", "update_user_code", "update_user_name"
            , "update_date","version"
    };
}
