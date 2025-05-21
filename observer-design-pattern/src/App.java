import observable.IphoneObservableImpl;
import observable.StockObservable;
import observer.EmailObserverImpl;
import observer.MobileObserverImpl;
import observer.NotificationObserver;

public class App {
    public static void main(String[] args) throws Exception {
        StockObservable iphoneObservable = new IphoneObservableImpl();

        NotificationObserver observer1 = new EmailObserverImpl("xyz@gmail.com", iphoneObservable);
        NotificationObserver observer2 = new EmailObserverImpl("xyz1@gmail.com", iphoneObservable);
        NotificationObserver observer3 = new MobileObserverImpl("1344345343", iphoneObservable);

        iphoneObservable.add(observer1);
        iphoneObservable.add(observer2);
        iphoneObservable.add(observer3);
        iphoneObservable.setStockCount(10);
    }
}
