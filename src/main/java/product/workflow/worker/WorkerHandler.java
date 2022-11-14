package product.workflow.worker;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.client.api.worker.JobHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import product.workflow.model.Product;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class WorkerHandler implements JobHandler {

    WebClient webClient = WebClient.create("http://localhost:8088/product-app");

    @Override
    public void handle(JobClient client, ActivatedJob job) throws Exception {
        Map<String, Object> map = job.getVariablesAsMap();
        Product product = new Product((String) map.get("productName"), (String) map.get("productDescription"), (String) map.get("productUrl"));
        System.out.println(product);
        webClient.post().uri("/rejected/product").body(Mono.just(product), Product.class)
                .retrieve().bodyToMono(Product.class).subscribe();
        client.newCompleteCommand(job.getKey()).send().join();//send? join?
    }
}
