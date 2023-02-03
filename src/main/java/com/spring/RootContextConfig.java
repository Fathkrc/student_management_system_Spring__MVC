package com.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:db.properties")//db bilgilerimizi setlediğimiz file ı tanıttık
public class RootContextConfig {
    @Autowired
    private Environment environment;
    //resource de bulunan verilere ulaşabilmek için çevre değişkenini autowired ile oluşturduk beanledik
    @Bean//hibernate de ki session ürettiğimiz sessionfactory springte bu şekilde bean olarak üretiliyor
    public LocalSessionFactoryBean sessionFactoryBean(){//design pattern mantığı
        LocalSessionFactoryBean LSF=new LocalSessionFactoryBean();
        LSF.setDataSource(dataSource());
        LSF.setPackagesToScan(new String[]{"com.spring.domain"});
        LSF.setHibernateProperties(hiberNateProperties());
        return LSF;
      //  sessionfactory mizi bean yaptık ve sürekli newlemek zorunda değiliz hibernate deki
        //session factory nin esas olarak aynısı

    }
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource DMDS=new DriverManagerDataSource();
        DMDS.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
        DMDS.setUrl(environment.getProperty("jdbc.url"));
        DMDS.setUsername(environment.getProperty("jdbc.username"));
        DMDS.setPassword(environment.getProperty("jdbc.password"));
        return DMDS;
    }

    private Properties hiberNateProperties(){//hibernate de elle set ettiğimiz değerleri enviroment üzerinden çektik
        Properties properties=new Properties();//properties key - value yapısıyla çalışıyor
        properties.put("hibernate.dialect",environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql",environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql",environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto",environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }
}
