package product.workflow.worker;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductWorker {

    @Autowired
    WorkerHandler handler;

    @JobWorker(type = "worker")
    public void zeebeWorker(final JobClient client, final ActivatedJob job) throws Exception {
        handler.handle(client, job);
    }

}
