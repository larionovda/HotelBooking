package com.larionov.epam.service.impl;

import com.larionov.epam.entity.MyQueue;
import com.larionov.epam.repository.NameHotels;
import com.larionov.epam.entity.Reservation;
import com.larionov.epam.service.DemoService;
import com.larionov.epam.thread.ConsumerThreadImpl;
import com.larionov.epam.thread.ProducerThreadImpl;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class DemoServiceImpl implements DemoService {

    @Override
    public void service() {
        System.out.println("start");
        ReentrantLock locker1 = new ReentrantLock();
        ReentrantLock locker2 = new ReentrantLock();
        NameHotels nameHotels = new NameHotels();
        MyQueue<Reservation> myQueue = new MyQueue(new LinkedList<Reservation>());

        startProducer(locker1, myQueue, nameHotels);
        startConsumer(locker2, myQueue);

        System.out.println("finish");
    }

    private void startProducer(ReentrantLock locker, MyQueue<Reservation> myQueue, NameHotels nameHotels) {
        for (int i = 0; i < 3; i++) {
            Thread pThread = new Thread(new ProducerThreadImpl(nameHotels, myQueue, locker));
            pThread.start();
        }
    }

    private void startConsumer(ReentrantLock locker, MyQueue myQueue) {
        for (int i = 0; i < 6; i++) {
            Thread cThread = new Thread(new ConsumerThreadImpl(locker, myQueue));
            cThread.start();
            if (i == 5) {
                try {
                    cThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
