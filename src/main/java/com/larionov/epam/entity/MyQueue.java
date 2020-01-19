package com.larionov.epam.entity;

import java.util.Deque;
import java.util.Queue;

public class MyQueue<T> {
    private final Queue<T> queue;
    private int count = 0;

    public MyQueue(Deque<T> queue) {
        this.queue = queue;
    }

    public synchronized void add(T value) {
        while (queue.size() >= 5) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.add(value);
        notify();
    }

    public synchronized T remove() {
        count++;
        if (count <= 15) {
            while (queue.size() < 1) {
                try {
                    wait(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T object = queue.poll();
            notify();
            return object;
        } else {
            return null;
        }
    }

    public synchronized int size() {
        return queue.size();
    }
}
