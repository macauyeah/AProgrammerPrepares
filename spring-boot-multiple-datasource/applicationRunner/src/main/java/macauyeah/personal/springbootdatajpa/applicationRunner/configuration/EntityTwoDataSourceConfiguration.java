package macauyeah.personal.springbootdatajpa.applicationRunner.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
// @formatter:off
@EnableJpaRepositories(
    entityManagerFactoryRef = "entityTwoEntityManagerFactory",
    transactionManagerRef = "entityTwoTransactionManager",
    basePackages = {
        "macauyeah.personal.springbootdatajpa.entitytwo.database.entity",
        "macauyeah.personal.springbootdatajpa.entitytwo.database.repository"
    },
    repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class
)
// @formatter:on
public class EntityTwoDataSourceConfiguration {
    @Value("${spring.jpa.macauyeah.personal.entitytwo.hibernate.hbm2ddl.auto}")
    private String hibernateDdlAuto;
    @Value("${spring.jpa.macauyeah.personal.entitytwo.hibernate.format_sql}")
    private String hibernateFormatSql;
    @Value("${spring.jpa.macauyeah.personal.entitytwo.hibernate.dialect}")
    private String hibernateDialect;

    @Bean(name = "entityTwoDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.macauyeah.personal.entitytwo")
    public DataSource entityTwoDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "entityTwoTransactionManager")
    PlatformTransactionManager entityTwoTransactionManager(
            @Qualifier("entityTwoEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    // @formatter:off
    @Bean(name = "entityTwoEntityManagerFactory")
    LocalContainerEntityManagerFactoryBean entityTwoEntityManagerFactory(
        EntityManagerFactoryBuilder factoryBuilder,
        @Qualifier("entityTwoDataSource") DataSource dataSource,
        @Qualifier("entityTwoJpaVendorAdapter") JpaVendorAdapter vendorAdapter)
    {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = factoryBuilder
            .dataSource(dataSource)
            .properties(jpaProperties())
            .packages("macauyeah.personal.springbootdatajpa.entitytwo.database.entity")
            .build();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        return entityManagerFactoryBean;
    }
    // @formatter:on

    protected Map<String, Object> jpaProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.physical_naming_strategy", CamelCaseToUnderscoresNamingStrategy.class.getName());
        props.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName());
        if (StringUtils.hasLength(hibernateDdlAuto)) {
            props.put("hibernate.hbm2ddl.auto", hibernateDdlAuto);
        }
        if (StringUtils.hasLength(hibernateFormatSql)) {
            props.put("hibernate.format_sql", hibernateFormatSql);
        }
        if (StringUtils.hasLength(hibernateDialect)) {
            props.put("hibernate.dialect", hibernateDialect);
        }
        return props;
    }

    @Bean(name = "entityTwoJdbcTemplate")
    public JdbcTemplate entityTwoJdbcTemplate(@Qualifier("entityTwoDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = { "entityTwoJpaVendorAdapter" })
    @ConfigurationProperties(prefix = "spring.jpa.macauyeah.personal.entitytwo")
    public JpaVendorAdapter jpaVendorAdapter() {
        return (JpaVendorAdapter) new HibernateJpaVendorAdapter();
    }
}
