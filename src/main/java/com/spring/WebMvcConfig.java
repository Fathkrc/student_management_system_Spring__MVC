package com.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration //configuration classımız bu olduğunu springe söylüyoruz
@EnableWebMvc //mvc ile çalışacağımızı springe söylüyoruz
@ComponentScan("com.spring")//Okunurluğu artırmak için yazdık
public class WebMvcConfig implements WebMvcConfigurer {
    // !!! ASIL CONFİG DOSYAMIZ BURASI
    // !!! viewResolver objemi bean olarak ekliyorum
    // ŞEMADA YER ALAN VIEWLERİ ÇÖZÜMLEMEMİZİ SAĞLAYACAK METHOD
    @Bean
    public InternalResourceViewResolver resolver() {//gelen viewleri çözümlüyoruz
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);//view tipini tanımladık
        resolver.setPrefix("/WEB-INF/views/");// view dosyalarımın nerede olduğunu yazıyorum
        resolver.setSuffix(".jsp"); // jsp uzantılı dosyaları dikkate almasını söylüyorum
        return resolver;
    }

    @Override
    // !!! kullanacağımız kaynakların yerini gösteriyorum (css, image vs.)
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //bu proje için hazır frontedi kopyaladık o kaynakları işaretliyoruz
        //buradaki resources frontedn kısmındaki resources javadaki java kaynakları
        registry.addResourceHandler("/resources/**").
                addResourceLocations("/resources/").setCachePeriod(0);
    }
}
