package com.shopping.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@EnableWebMvc
@Configuration
@ComponentScan({ "com.shopping.*" })
@EnableTransactionManagement
@Import({ SpringSecurityConfig.class })
public class ShoppingServletConfig extends WebMvcConfigurerAdapter{
	@Bean
    public SessionFactory sessionFactory() {
            LocalSessionFactoryBuilder builder = 
		new LocalSessionFactoryBuilder(dataSource());
            builder.scanPackages("com.shopping.entity")
                  .addProperties(getHibernateProperties());

            return builder.buildSessionFactory();
    }
	private Properties getHibernateProperties() {
        Properties prop = new Properties();
        prop.put("hibernate.format_sql", "true");
        prop.put("hibernate.show_sql", "true");
        prop.put("hibernate.dialect", 
            "org.hibernate.dialect.MySQL5Dialect");
        prop.put("hibernate.hbm2ddl.auto", "update");
        prop.put("hibernate.id.new_generator_mappings","false");
        return prop;
      }
	@Bean(name = "dataSource")
	public BasicDataSource dataSource() {
		
		BasicDataSource ds = new BasicDataSource();
		BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/shop");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
	}
	//Create a transaction manager
		@Bean
	        public HibernateTransactionManager txManager() {
	                return new HibernateTransactionManager(sessionFactory());
	        }
			
		@Bean
		public InternalResourceViewResolver viewResolver() {
			InternalResourceViewResolver viewResolver 
	                             = new InternalResourceViewResolver();
			viewResolver.setViewClass(JstlView.class);
			viewResolver.setPrefix("/WEB-INF/ui/view/");
			viewResolver.setSuffix(".jsp");
			return viewResolver;
		}
		// equivalents for <mvc:resources/> tags
		public void addResourceHandlers(final ResourceHandlerRegistry registry) {
			registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		}
		// equivalent for <mvc:default-servlet-handler/> tag
		@Override
	    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	        configurer.enable();
	    }
		 @Override
		   public void addViewControllers(ViewControllerRegistry registry) {
		      registry.addViewController("/login").setViewName("login");
		   }
}
