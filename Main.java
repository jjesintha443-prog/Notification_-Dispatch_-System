import java.util.*;

class Notification {
    String type;
    String message;

    Notification(String type, String message) {
        this.type = type;
        this.message = message;
    }
}

interface Observer {
    void update(Notification n);
}

class EmailObserver implements Observer {
    public void update(Notification n) {
        System.out.println("Email Sent : " + n.message);
    }
}

class SMSObserver implements Observer {
    public void update(Notification n) {
        System.out.println("SMS Sent : " + n.message);
    }
}

class PushObserver implements Observer {
    public void update(Notification n) {
        System.out.println("Push Notification : " + n.message);
    }
}

class NotificationService {
    Queue<Notification> queue = new LinkedList<>();
    ArrayList<Observer> observers = new ArrayList<>();

    void subscribe(Observer o) {
        observers.add(o);
    }

    void addNotification(Notification n) {
        queue.offer(n);
    }

    void dispatch() {
        while (!queue.isEmpty()) {
            Notification n = queue.poll();

            for (Observer o : observers) {
                o.update(n);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {

        NotificationService service = new NotificationService();

        service.subscribe(new EmailObserver());
        service.subscribe(new SMSObserver());
        service.subscribe(new PushObserver());

        service.addNotification(new Notification("Email", "Welcome to Java"));
        service.addNotification(new Notification("SMS", "OTP is 1234"));
        service.addNotification(new Notification("Push", "Order Delivered"));

        service.dispatch();
    }
}