package com.organic.shop.config;

//import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@Log4j2
public class WebMVCConfig implements WebMvcConfigurer {

    public WebMVCConfig() {
        log.info("WebMVCConfig RUN!!!!!!!");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**")
                .addResourceLocations("/WEB-INF/views/assets/", "classpath:/static/assets/");
//        registry.addResourceHandler("/user/assets/**")
//                .addResourceLocations("classpath:/static/user/assets/", "src/main/webapp/views/uploadFile/assets/");
    }
//    private String getCacheName() {
//        return "static-resources-cache";
//    }
//
//    private int getCachePeriod() {
//        return 60 * 60 * 24 * 7; // 7 days
//    }
//
//    @Bean
//    public CacheManager cacheManager() {
//        return new ConcurrentMapCacheManager(getCacheName());
//    }

//    @Override
//    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//        for(HttpMessageConverter converter : converters){
//            if (converter instanceof MappingJackson2HttpMessageConverter) {
//                MappingJackson2HttpMessageConverter jsonConverter = (MappingJackson2HttpMessageConverter) converter;
//                ObjectMapper objectMapper = jsonConverter.getObjectMapper();
//                objectMapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
//            }}
//    }
}
