package creo.games.five.server.config

import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.web.servlet.invoke
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http {
            cors { disable() }
            csrf { disable() }
            authorizeRequests {
                authorize(HttpMethod.GET, "/", permitAll)
                authorize(HttpMethod.POST, "/user/login", permitAll)
                authorize(anyRequest, authenticated)
            }

            logout {
                logoutUrl = "/logout"
            }
        }
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication().withUser("user1").password(passwordEncoder().encode("ps1")).authorities("USER")
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager = super.authenticationManagerBean()

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()


}