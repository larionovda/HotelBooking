package com.larionov.epam.thread;

import com.larionov.epam.entity.MyQueue;
import com.larionov.epam.repository.NameHotels;
import com.larionov.epam.entity.Reservation;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerThreadImpl implements Runnable {

    private final NameHotels nameHotels;
    private final MyQueue<Reservation> myQueue;
    private final ReentrantLock locker;

    public ProducerThreadImpl(NameHotels nameHotels, MyQueue<Reservation> myQueue, ReentrantLock locker) {
        this.nameHotels = nameHotels;
        this.myQueue = myQueue;
        this.locker = locker;
    }

    @Override
    public void run() {
        locker.lock();
        for (int i = 0; i < 5; i++) {
            Reservation reservation = new Reservation(nameHotels.getNamesOfHotels()[nameHotels.getCount()], LocalDateTime.now());
            myQueue.add(reservation);
            nameHotels.setCount(nameHotels.getCount() + 1);
            System.out.println("Name of Thread is " + Thread.currentThread().getName() + " Added " + reservation.toString() + ". Size of queue - " + myQueue.size());
        }
        locker.unlock();
    }
}
