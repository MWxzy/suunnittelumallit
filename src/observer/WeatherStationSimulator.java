package observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

interface Observer {
    void update(float temperature);
}

interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}

class WeatherStation implements Subject, Runnable {
    private List<Observer> observers;
    private float temperature;
    private final float MAX_TEMP = 40.0f;
    private final float MIN_TEMP = -10.0f;
    private boolean running;
    private Random random;

    public WeatherStation() {
        observers = new ArrayList<>();
        random = new Random();
        this.temperature = MIN_TEMP + random.nextFloat() * (MAX_TEMP - MIN_TEMP);
        this.running = true;
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
        System.out.println("Observer registered: " + o.getClass().getSimpleName());
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
        System.out.println("Observer removed: " + o.getClass().getSimpleName());
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature);
        }
    }

    private void updateTemperature() {
        float delta = (random.nextBoolean() ? 1 : -1);
        float newTemp = temperature + delta;

        if (newTemp >= MIN_TEMP && newTemp <= MAX_TEMP) {
            temperature = newTemp;
        } else {
            temperature = temperature - delta;
        }
    }

    @Override
    public void run() {
        System.out.println("Weather Station started. Initial temperature: " + temperature);
        while (running) {
            try {
                int sleepTime = 1000 + random.nextInt(4000);
                Thread.sleep(sleepTime);

                updateTemperature();
                System.out.println("\n--- Weather Update ---");
                System.out.println("New Temperature: " + temperature + "°C");
                notifyObservers();

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println("Weather Station stopped.");
    }

    public void stop() {
        this.running = false;
    }
}

class DisplayObserver implements Observer {
    private String name;

    public DisplayObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(float temperature) {
        System.out.println(name + " Display: Current temperature is " + temperature + "°C");
    }
}

class AlertObserver implements Observer {
    private String name;
    private float lastAlertTemp;

    public AlertObserver(String name) {
        this.name = name;
        this.lastAlertTemp = 0;
    }

    @Override
    public void update(float temperature) {
        if (Math.abs(temperature - lastAlertTemp) > 5.0f) {
            System.out.println(name + " Alert: Significant temperature change detected! New temp: " + temperature + "°C");
            lastAlertTemp = temperature;
        } else {
            System.out.println(name + " Alert: Temperature is " + temperature + "°C (no alert needed)");
        }
    }
}

public class WeatherStationSimulator {
    public static void main(String[] args) {
        WeatherStation station = new WeatherStation();

        Observer display1 = new DisplayObserver("Display 1");
        Observer display2 = new DisplayObserver("Display 2");
        Observer alert = new AlertObserver("Alert System");

        station.registerObserver(display1);
        station.registerObserver(display2);
        station.registerObserver(alert);

        Thread stationThread = new Thread(station);
        stationThread.start();

        try {
            Thread.sleep(15000);

            System.out.println("\n--- Removing Display 2 Observer ---");
            station.removeObserver(display2);

            Thread.sleep(10000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            station.stop();
            try {
                stationThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\nSimulation ended.");
    }
}
