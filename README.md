# Soap sample
Sample project showing how use 
[country info ](http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso)
soap service

### gradle
add plugin

    plugins {
        ... 
        id("io.mateo.cxf-codegen") version "1.0.3"
    }

add dependencies

    dependencies {
        ...
        implementation("com.sun.xml.ws:rt:2.3.1")
        implementation("javax.xml.ws:jaxws-api:2.2")
        cxfCodegen("javax.xml.ws:jaxws-api:2.2")
    }

configure task, input and output

    cxfCodegen {
        wsdl2java {
            create("Sample") {
                wsdl.set(file("$projectDir/src/main/resources/wsdl/CountryInfoService.wsdl"))
                outputDir.set(file("${project.buildDir}/generated/wsdl"))
            }
        }
    }
    
generate code from wsdl before compile

    tasks.compileJava {
        dependsOn(tasks.wsdl2java)
    }
    
    tasks.compileKotlin {
        dependsOn(tasks.wsdl2java)
    }

### Author
Marcin Lesiński
