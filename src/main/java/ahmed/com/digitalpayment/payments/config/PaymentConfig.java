package ahmed.com.digitalpayment.payments.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentConfig {


    @Bean
    public ModelMapper modelMapper()   {
        return new ModelMapper();
    }

}
