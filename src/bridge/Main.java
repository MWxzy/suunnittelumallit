package bridge;

import bridge.devices.Device;
import bridge.devices.Radio;
import bridge.devices.SmartTV;
import bridge.devices.Tv;
import bridge.remotes.AdvancedRemote;
import bridge.remotes.BasicRemote;
import bridge.remotes.SmartRemote;

public class Main {

    public static void main(String[] args) {
        testBasicRemote(new Tv());
        testAdvancedRemote(new Radio());
        testSmartRemote(new SmartTV());
        testSmartRemoteWithClassicDevice(new Tv());
    }

    private static void testBasicRemote(Device device) {
        System.out.println("== Basic remote with a regular TV ==");
        BasicRemote remote = new BasicRemote(device);
        remote.power();
        remote.channelUp();
        remote.volumeUp();
        device.printStatus();
    }

    private static void testAdvancedRemote(Device device) {
        System.out.println("== Advanced remote with a radio ==");
        AdvancedRemote remote = new AdvancedRemote(device);
        remote.power();
        remote.mute();
        device.printStatus();
    }

    private static void testSmartRemote(SmartTV smartTV) {
        System.out.println("== Smart remote with a smart TV ==");
        SmartRemote remote = new SmartRemote(smartTV);
        remote.voiceControl("power on");
        remote.voiceControl("open netflix.com");
        remote.voiceControl("volume 45");
        remote.voiceControl("next channel");
        remote.voiceControl("mute");
        smartTV.printStatus();

        remote.voiceControl("open youtube.com");
        remote.voiceControl("volume up");
        smartTV.printStatus();
    }

    private static void testSmartRemoteWithClassicDevice(Device device) {
        System.out.println("== Smart remote with a regular TV ==");
        SmartRemote remote = new SmartRemote(device);
        remote.voiceControl("power on");
        remote.voiceControl("volume 70");
        remote.voiceControl("open example.com");
        device.printStatus();
    }
}
