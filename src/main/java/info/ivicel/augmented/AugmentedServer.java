package info.ivicel.augmented;

import info.ivicel.augmented.common.AugmentedProperties;
import info.ivicel.augmented.repository.support.JpaSpecificationExecutorWithProjectionImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties(AugmentedProperties.class)
@EnableJpaRepositories(basePackages = "info.ivicel.augmented.repository",
        repositoryBaseClass = JpaSpecificationExecutorWithProjectionImpl.class)
public class AugmentedServer extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AugmentedServer.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(AugmentedServer.class);
    }
}
