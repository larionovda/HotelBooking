package com.larionov.epam.service.impl;

import com.larionov.epam.entity.MyQueue;
import com.larionov.epam.entity.Reservation;

import java.util.concurrent.locks.ReentrantLock;

public class ConsumerThreadImpl implements Runnable {

    private final ReentrantLock locker;
    private final MyQueue<Reservation> myQueue;

    public ConsumerThreadImpl(ReentrantLock locker, MyQueue<Reservation> myQueue) {
        this.locker = locker;
        this.myQueue = myQueue;
    }

    @Override
    public void run() {
        locker.lock();
        try {
            for (int i = 0; i < 3; i++) {
                Thread.sleep(5000);
                Reservation reservation = myQueue.remove();
                if (reservation != null) {
                    System.out.println("Name of Thread is " + Thread.currentThread().getName() + " deleted element " + reservation + ". Size of queue: " + myQueue.size());
                } else {
                    return;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
    }
}
