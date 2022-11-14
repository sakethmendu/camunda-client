package product.workflow;

import io.camunda.zeebe.spring.client.EnableZeebeClient;
import io.camunda.zeebe.spring.client.annotation.Deployment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@SpringBootApplication
@EnableZeebeClient
@Deployment(resources = "classpath:dummy.bpmn")
public class ProductWorkflowApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductWorkflowApplication.class, args);
    }
}


