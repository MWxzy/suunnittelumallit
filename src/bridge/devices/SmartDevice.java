package bridge.devices;

public interface SmartDevice extends Device {

    void browseInternet(String url);

    String getCurrentPage();
}
