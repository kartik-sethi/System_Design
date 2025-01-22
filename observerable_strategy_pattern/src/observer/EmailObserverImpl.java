package observer;

import observable.StockObservable;

public class EmailObserverImpl implements NotificationObserver {

    String emailId;
    StockObservable observable;

    public EmailObserverImpl(String emailId, StockObservable observable) {
        this.emailId = emailId;
        this.observable = observable;
    }

    @Override
    public void update() {
        sendMessageOnEmail();
    }

    private void sendMessageOnEmail() {
        System.out.println(emailId + ":  Product is available");
    }
}
