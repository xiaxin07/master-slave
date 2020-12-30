package com.xiaxin.config;

import com.xiaxin.utils.DbUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

/**
 * spring的jdbc提供了动态数据源的入口
 * 继承AbstractRoutingDataSource覆盖determineCurrentLookupKey()方法
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Nullable
    @Override
    protected Object determineCurrentLookupKey() {
        log.info("当前使用数据库：{}",DbUtil.getDb());
        return DbUtil.getDb();
    }
}
