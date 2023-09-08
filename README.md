# Registration API

## JUnit with Mockito , JaCoCo Code Coverage, OpenAPI documentation

---

---
### OpenAPI

```html
    <dependency>
	    <groupId>org.springdoc</groupId>
		<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
		<version>2.0.4</version>
	</dependency>
```

```yaml
springdoc:
  api-docs:
    path: /api-docs
  swagger-ui:
    path: /swagger-ui.html
    enabled: true
```

```java
@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI registrationOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Your API Tilte")
                        .description("Your API Description")
                        .version("1.0"));
    }
}

//http://localhost:9090/swagger-ui.html
```
---

### JaCoCo

```html
<plugin>
				<groupId>org.jacoco</groupId>
				<artifactId>jacoco-maven-plugin</artifactId>
				<version>0.8.2</version>
				<configuration>
					<excludes>
						<exclude>com/app/amrit/entity/**</exclude>
					</excludes>
				</configuration>
				<executions>
					<execution>
						<goals>
							<goal>prepare-agent</goal>
						</goals>
					</execution>
					<execution>
						<id>report</id>
						<phase>test</phase>
						<goals>
							<goal>report</goal>
						</goals>
					</execution>
				</executions>
			</plugin>
```
Run `mvn clean test`, and once the test has been completed, JaCoCo report can be accessed at `target/site/jacoco/index.html` 

# registration-api
