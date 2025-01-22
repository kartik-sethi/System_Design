package observer;

import observable.StockObservable;

public class MobileObserverImpl implements NotificationObserver {

    String mobileNumber;
    StockObservable observable;

    public MobileObserverImpl(String mobileNumber, StockObservable observable) {
        this.mobileNumber = mobileNumber;
        this.observable = observable;
    }

    @Override
    public void update() {
        sendMessageOnMobile();
    }

    private void sendMessageOnMobile() {
        System.out.println(mobileNumber + ":  Product is available");
    }
}
