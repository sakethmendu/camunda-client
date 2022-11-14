package product.workflow.controller;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.DeploymentEvent;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import product.workflow.model.Product;

@RestController
@RequestMapping("zeebe")
@CrossOrigin("*")
public class WorkFlowController {

    @Autowired
    private ZeebeClient client;

    @GetMapping("deploy/{name}")
    public String deploy(@PathVariable String name) {
        final DeploymentEvent deploymentEvent =
                client.newDeployCommand()
                        .addResourceFromClasspath(name)
                        .send()
                        .join();

        final int version = deploymentEvent.getProcesses().get(0).getVersion();
        return "Workflow deployed. Version: " + version;
    }

    @PostMapping("startprocess/{name}")//startZeebeProcess?
    public String startProcess(@PathVariable String name, @RequestBody Product product) {
        final ProcessInstanceEvent wfInstance = client.newCreateInstanceCommand()
                .bpmnProcessId(name)
                .latestVersion().variables(product)
                .send()
                .join();
        //.join sync call, whenComplete
        final long workflowInstanceKey = wfInstance.getProcessInstanceKey();
        return "Workflow instance created. Key: " + workflowInstanceKey;
    }

    @GetMapping
    public String hello(){
        return "Hello";
    }

}
