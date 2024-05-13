package swa.semproject.reservationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ReservationServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(ReservationServiceApp.class, args);
    }

}

