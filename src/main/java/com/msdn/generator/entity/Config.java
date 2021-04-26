package com.msdn.generator.entity;

import java.io.File;
import java.util.Set;

public class Config {

    public static final String OutputPath = "." + File.separator + "output";

    public static final String Author = "hresh";

    public static final String[] mybatisPlusCommonColumns = new String[]{
            "is_deleted", "create_by", "create_name", "create_time", "update_by", "update_name", "update_time"
    };

    public static final String[] mybatisCommonColumns = new String[]{
            "del_flag", "create_user_code", "create_user_name", "create_date", "update_user_code", "update_user_name"
            , "update_date"
    };
}
