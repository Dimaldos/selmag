package ru.dimaldos.selmag.managerapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestClient;
import ru.dimaldos.selmag.managerapp.client.DefaultProductsRestClient;

@Configuration
public class ClientBeans {

    @Bean
    public DefaultProductsRestClient productsRestClient(
            @Value("${selmag.services.catalogue.uri:http://localhost:8082}") String catalogueBaseUri,
            @Value("${selmag.services.catalogue.username:}") String catalogueUsername,
            @Value("${selmag.services.catalogue.password:}") String cataloguePassword
    ) {
        return new DefaultProductsRestClient(RestClient.builder()
                .baseUrl(catalogueBaseUri)
                .requestInterceptor(new BasicAuthenticationInterceptor(catalogueUsername, cataloguePassword))
                .build());
    }
}
