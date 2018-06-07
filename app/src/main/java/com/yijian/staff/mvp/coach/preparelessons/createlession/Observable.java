package com.yijian.staff.mvp.coach.preparelessons.createlession;

/**
 * 被观察者接口
 */
public interface Observable {
    /**
     * 添加观察者
     *
     * @param observer
     */
    public void addObserver(Observer observer);

    /**
     * 删除观察者
     *
     * @param observer
     */
    public void deleteObserver(Observer observer);

    /**
     * 删除所有观察者
     */
    public void deleteObservers();

    /**
     * 通知所有观察者
     *
     * @param data
     */
    public void notifyObservers(Object data);

    /**
     * 通知所有观察者
     */
    public void notifyObservers();

    /**
     * 获取观察者数量
     *
     * @return
     */
    public int getCountObservers();
}
