package by.lostFinder;

import by.lostFinder.entities.*;
import by.lostFinder.repositories.superRepositories.NamedRepository;
import by.lostFinder.repositories.superRepositories.SimpleRepository;
import by.lostFinder.services.NamedService;
import by.lostFinder.services.SimpleService;
import by.lostFinder.services.impl.AccountServiceImpl;
import by.lostFinder.services.impl.NamedServiceImpl;
import by.lostFinder.services.impl.PostServiceImpl;
import by.lostFinder.services.impl.SimpleServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@ComponentScan
@EnableJpaRepositories(value = "by.lostFinder.repositories")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public SimpleService<Account> accountService(){return new AccountServiceImpl() {}; }

    @Bean
    public NamedService<Contact> contactService(){return new NamedServiceImpl<Contact, NamedRepository<Contact>>() {}; }

    @Bean
    public NamedService<HashTag> hashTagService(){return new NamedServiceImpl<HashTag, NamedRepository<HashTag>>() {}; }

    @Bean
    public SimpleService<AccountDetail> accountDetailService(){return new SimpleServiceImpl<AccountDetail, SimpleRepository<AccountDetail>>() {}; }

    @Bean
    public SimpleService<Post> postService(){return new PostServiceImpl() {}; }

    @Bean
    public NamedService<PostType> postTypeService(){return new NamedServiceImpl<PostType, NamedRepository<PostType>>() {};}

}

