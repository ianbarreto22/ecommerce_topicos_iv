package com.vcib.store.services;

import org.springframework.stereotype.Service;

@Service
public class FailureService {
    private static double OMISSION_FAILURE_PROBABILITY = 0.2;
    private static double TIME_FAILURE_PROBABILITY = 0.1;


    public void simulateOmissionFailure() {
        if(Math.random() < OMISSION_FAILURE_PROBABILITY) {
            while(true) {
            }
        }
    }

    public void simulateTimeFailure() {
        if(Math.random() < TIME_FAILURE_PROBABILITY) {
            System.out.println("Simulating failure by time");
            try {
                Thread.sleep(2000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Simulating failure by time");
        }
    }

}
