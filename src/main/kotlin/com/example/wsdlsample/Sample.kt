package com.example.wsdlsample

import javax.annotation.PostConstruct
import org.oorsprong.websamples_countryinfo.CountryInfoServiceSoapType
import org.springframework.stereotype.Component

@Component
internal class Sample(
    private val service: CountryInfoServiceSoapType
) {

    @PostConstruct
    fun onStartUp() {
        service.listOfCountryNamesByCode().tCountryCodeAndName.forEach {
            println("${it.sName} ${it.sisoCode}")
        }
    }

}
