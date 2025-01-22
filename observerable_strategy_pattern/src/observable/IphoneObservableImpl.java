package observable;

import java.util.ArrayList;
import java.util.List;
import observer.NotificationObserver;

public class IphoneObservableImpl implements StockObservable {
    public List<NotificationObserver> observerList = new ArrayList<>();
    public int stockCount = 0;

    @Override
    public void add(NotificationObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void remove(NotificationObserver observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifySubscribers() {
        for (NotificationObserver observer : observerList) {
            observer.update();
        }
    }

    @Override
    public void setStockCount(int stockCount) {
        this.stockCount += stockCount;
        if (this.stockCount != 0) {
            notifySubscribers();
        }
    }

    @Override
    public int getStockCount() {
        return stockCount;
    }
}
