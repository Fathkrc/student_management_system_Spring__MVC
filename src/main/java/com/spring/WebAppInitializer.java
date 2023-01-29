package com.spring;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//bu class xml config dosyasının java ile yapılan halidir
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /*
    EĞER SPRİNG MVC İLE PROJE YAPIYORSAK
    ****AbstractAnnotationConfigDispatcherServletInitializer****
    CLASSINI EXTEND ETMEK ZORUNDAYIZ ve 3 methodu override ediyoruz
    3 TANE Generic array classlar return eden methodu override ediyoruz
     */

    @Override
    //DATAbase hibernate ayarlarımızı burada yapıyoruz
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    //Servlet conigurasyonu burada yapılıyor
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
                WebMvcConfig.class//servlet config classımızı tanıttık 700. defa
        };
    }

    @Override
    //hangi url hangi servlete gideceği burada ayarlanıyor
    protected String[] getServletMappings() {
        return new String[]{
                "/"//gelen tüm requestler bu servlet tarafından karşılanacak başka servletimiz yok
        };
    }
}
