package com.yijian.workspace.workspace.observe;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义被观察者
 */
public class EditActionObservable implements Observable {
    private static List<Observer> observerList = new ArrayList<Observer>();

    public static void GlobalAddObserver(Observer observer) {
        if (observerList == null) {
            throw new NullPointerException();
        }

        if (observer == null) {
            throw new NullPointerException();
        }
        synchronized (observerList) {
            if (!observerList.contains(observer)) {
                observerList.add(observer);
            }
        }
    }

    public static void GlobalDeleteObserver(Observer observer) {
        if (observerList == null) {
            throw new NullPointerException();
        }

        if (observer == null) {
            throw new NullPointerException();
        }
        synchronized (observerList) {
            if (observerList.contains(observer)) {
                observerList.remove(observer);
            }
        }
    }

    @Override
    public synchronized void addObserver(Observer observer) {
        // TODO Auto-generated method stub
        if (observerList == null) {
            throw new NullPointerException();
        }

        if (observer == null) {
            throw new NullPointerException();
        }
        synchronized (observerList) {
            if (!observerList.contains(observer)) {
                observerList.add(observer);
            }
        }
    }

    @Override
    public synchronized void deleteObserver(Observer observer) {
        // TODO Auto-generated method stub
        if (observerList == null) {
            throw new NullPointerException();
        }

        if (observer == null) {
            throw new NullPointerException();
        }
        synchronized (observerList) {
            if (observerList.contains(observer)) {
                observerList.remove(observer);
            }
        }
    }

    @Override
    public synchronized void deleteObservers() {
        // TODO Auto-generated method stub
        if (observerList == null) {
            throw new NullPointerException();
        }

        synchronized (observerList) {
            observerList.removeAll(observerList);
        }
    }

    @Override
    public void notifyObservers(Object data) {
        // TODO Auto-generated method stub
        if (observerList == null) {
            throw new NullPointerException();
        }

        synchronized (observerList) {
            for (Observer observer : observerList) {
                observer.update(data);
            }
        }
    }

    @Override
    public void notifyObservers() {
        // TODO Auto-generated method stub
        notifyObservers(null);
    }

    @Override
    public int getCountObservers() {
        // TODO Auto-generated method stub
        if (observerList == null) {
            throw new NullPointerException();
        }
        synchronized (observerList) {
            return observerList.size();
        }
    }

}