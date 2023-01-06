package com.example.wsdlsample

import org.oorsprong.websamples_countryinfo.CountryInfoService
import org.oorsprong.websamples_countryinfo.CountryInfoServiceSoapType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
internal class SampleConfig {

    @Bean
    fun service(): CountryInfoServiceSoapType {
        val impl = CountryInfoService()
        return impl.countryInfoServiceSoap
    }
}
