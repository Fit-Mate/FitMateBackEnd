package FitMate.FitMateBackend.chanhaleWorking.config;

import FitMate.FitMateBackend.chanhaleWorking.interceptor.LoginAdminCheckInterceptor;
import FitMate.FitMateBackend.chanhaleWorking.interceptor.LoginUserCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SessionConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 사용자 로그인 인터셉터
        registry.addInterceptor(new LoginUserCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/admin/**", "/", "/login", "/css/**", "/*.ico", "/error");
        // 관리자 로그인 인터셉터
        registry.addInterceptor(new LoginAdminCheckInterceptor())
                .order(2)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login", "/admin/logout", "/css/**", "/*.ico", "/error");
    }
}
