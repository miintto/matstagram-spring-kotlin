package com.miintto.matstagram.config

import com.miintto.matstagram.config.interceptor.MatstagramInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfiguration : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(MatstagramInterceptor()).addPathPatterns("/**")
    }
}
