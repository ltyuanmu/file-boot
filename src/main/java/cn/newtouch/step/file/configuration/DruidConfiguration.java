package cn.newtouch.step.file.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.sql.SQLException;
import java.util.Optional;

/**
 * datasource 配置
 * Created by haijiang.mo@newtouch.cn on 2016/8/30.
 */
@Configuration
public class DruidConfiguration {

    /**基础数据驱动配置*/
    @Autowired
    private DataSourceProperties properties;

    /**d*/
    @Autowired
    private DataSourceDruidProperties druidProperties;

    /**
     * 数据源配置
     * @return dataSource
     */
    @Bean
    @Primary
    public DruidDataSource dataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(properties.determineDriverClassName());
        dataSource.setUrl(properties.determineUrl());
        dataSource.setUsername(properties.determineUsername());
        dataSource.setPassword(properties.determinePassword());
        DatabaseDriver databaseDriver = DatabaseDriver
                .fromJdbcUrl(properties.determineUrl());
        String validationQuery = databaseDriver.getValidationQuery();
        if (validationQuery != null) {
            dataSource.setTestOnBorrow(true);
            dataSource.setValidationQuery(validationQuery);
        }

        if(StringUtils.isNotBlank(druidProperties.getFilters())){
            dataSource.setFilters(druidProperties.getFilters());
        }

        Optional.ofNullable(druidProperties.getConnectionProperties()).ifPresent(t -> dataSource.setConnectionProperties(t));
        Optional.ofNullable(druidProperties.getMaxActive()).ifPresent(t -> dataSource.setMaxActive(t));
        Optional.ofNullable(druidProperties.getInitialSize()).ifPresent(t -> dataSource.setInitialSize(t));
        Optional.ofNullable(druidProperties.getMaxWait()).ifPresent(t -> dataSource.setMaxWait(t));
        Optional.ofNullable(druidProperties.getMinIdle()).ifPresent(t -> dataSource.setMinIdle(t));
        Optional.ofNullable(druidProperties.getTimeBetweenEvictionRunsMillis()).ifPresent(t -> dataSource.setTimeBetweenEvictionRunsMillis(t));
        Optional.ofNullable(druidProperties.getMinEvictableIdleTimeMillis()).ifPresent(t -> dataSource.setMinEvictableIdleTimeMillis(t));
        Optional.ofNullable(druidProperties.getValidationQuery()).ifPresent(t -> dataSource.setValidationQuery(t));
        Optional.ofNullable(druidProperties.getTestWhileIdle()).ifPresent(t -> dataSource.setTestWhileIdle(t));
        Optional.ofNullable(druidProperties.getTestOnBorrow()).ifPresent(t -> dataSource.setTestOnBorrow(t));
        Optional.ofNullable(druidProperties.getTestOnReturn()).ifPresent(t -> dataSource.setTestOnReturn(t));
        Optional.ofNullable(druidProperties.getPoolPreparedStatements()).ifPresent(t -> dataSource.setPoolPreparedStatements(t));
        Optional.ofNullable(druidProperties.getMaxPoolPreparedStatementPerConnectionSize()).ifPresent(t -> dataSource.setMaxPoolPreparedStatementPerConnectionSize(t));
        return dataSource;
    }
}
