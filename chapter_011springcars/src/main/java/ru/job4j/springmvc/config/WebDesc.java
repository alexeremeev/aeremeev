package ru.job4j.springmvc.config;

import org.springframework.lang.Nullable;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class WebDesc extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Nullable
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter utf8filter = new CharacterEncodingFilter();
        utf8filter.setEncoding("UTF-8");
        utf8filter.setForceEncoding(true);
        return new Filter[]{utf8filter};
    }

    @Nullable
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {SpringRootConfig.class};
    }

    @Nullable
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringWebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
