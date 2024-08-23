package com.caio.hr_payroll.services;

import com.caio.hr_payroll.entities.Worker;
import com.caio.hr_payroll.feignclients.WorkerFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.caio.hr_payroll.entities.Payment;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

//    @Value("${hr-worker.host}")
//    private String workerHost;
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    public Payment getPayment(long workerId, int days) {
//        Map<String, String> uriVariables = new HashMap<>();
//        uriVariables.put("id", "" + workerId);
//
//        System.out.println(workerHost + "/workers/{id}");
//        Worker worker = restTemplate.getForObject(workerHost + "/workers/{id}", Worker.class, uriVariables);
//
//        return new Payment(worker.getName(), worker.getDailyIncome(), days);
//    }
    @Autowired
    private WorkerFeignClient workerFeignClient;

    public Payment getPayment(long workerId, int days) {
        Worker worker = workerFeignClient.findById(workerId).getBody();
        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }
}


