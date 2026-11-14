package bridge.devices;

public class SmartTV implements SmartDevice {

    private boolean on = false;
    private int volume = 30;
    private int channel = 1;
    private String currentPage = "home";

    @Override
    public boolean isEnabled() {
        return on;
    }

    @Override
    public void enable() {
        on = true;
    }

    @Override
    public void disable() {
        on = false;
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public void setVolume(int volume) {
        this.volume = Math.max(0, Math.min(100, volume));
    }

    @Override
    public int getChannel() {
        return channel;
    }

    @Override
    public void setChannel(int channel) {
        this.channel = channel;
    }

    @Override
    public void browseInternet(String url) {
        if (!on) {
            throw new IllegalStateException("SmartTV must be on to browse the internet");
        }
        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("URL must not be empty");
        }
        currentPage = url;
    }

    @Override
    public String getCurrentPage() {
        return currentPage;
    }

    @Override
    public void printStatus() {
        System.out.println("------------------------------------");
        System.out.println("| I'm smart TV.");
        System.out.println("| I'm " + (on ? "enabled" : "disabled"));
        System.out.println("| Current volume is " + volume + "%");
        System.out.println("| Current channel is " + channel);
        System.out.println("| Current page is " + currentPage);
        System.out.println("------------------------------------\n");
    }
}
