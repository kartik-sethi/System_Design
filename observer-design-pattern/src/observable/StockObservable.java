package observable;

import observer.*;

public interface StockObservable {

    public void add(NotificationObserver observer);

    public void remove(NotificationObserver observer);

    public void notifySubscribers();

    public void setStockCount(int newStockAdded);

    public int getStockCount();
}
