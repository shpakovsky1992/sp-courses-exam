package by.cources.spring.exam;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class BookSecurityConfig extends WebSecurityConfigurerAdapter {

  private final BookJpaConfig jpaConfig;

  public BookSecurityConfig(BookJpaConfig jpaConfig) {
    this.jpaConfig = jpaConfig;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    jdbcAuthentication(auth);
//    inMemoryAuthentication(auth);
  }

  private void jdbcAuthentication(AuthenticationManagerBuilder auth) throws Exception {
    auth.jdbcAuthentication().dataSource(jpaConfig.dataSource())
        .withDefaultSchema()
        .passwordEncoder(passwordEncoder())
        .withUser("user").password(passwordEncoder().encode("0123456")).roles("USER")
        .and()
        .withUser("petr").password(passwordEncoder().encode("123456")).roles("USER")
        .and()
        .withUser("admin").password(passwordEncoder().encode("123456")).roles("USER", "ADMIN");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/login").permitAll()
        .antMatchers("/book/edit/**").hasAnyRole("USER", "DIRECTOR")
        .antMatchers("/book/delete").hasAnyRole("ADMIN", "ROOT")
        .antMatchers("/book/**").hasRole("USER")
        .antMatchers("/**").hasAnyRole("ADMIN", "USER")
        .and().formLogin().defaultSuccessUrl("/book/list")
        .and().logout().logoutUrl("/exit").logoutSuccessUrl("/login").permitAll()
        .and().csrf().disable();
  }
}
