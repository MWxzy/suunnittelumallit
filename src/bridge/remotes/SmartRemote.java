package bridge.remotes;

import bridge.devices.Device;
import bridge.devices.SmartDevice;

import java.util.Locale;

public class SmartRemote extends AdvancedRemote {

    public SmartRemote(Device device) {
        super(device);
    }

    public void voiceControl(String command) {
        if (command == null) {
            throw new IllegalArgumentException("Voice command must not be null");
        }

        String normalized = command.trim().toLowerCase(Locale.ROOT);
        System.out.println("SmartRemote: \"" + command.trim() + "\"");

        if (normalized.isEmpty()) {
            System.out.println("SmartRemote: empty command");
            return;
        }

        switch (normalized) {
            case "power", "power toggle" -> power();
            case "power on" -> device.enable();
            case "power off" -> device.disable();
            case "volume up" -> volumeUp();
            case "volume down" -> volumeDown();
            case "mute" -> mute();
            case "channel up", "next channel" -> channelUp();
            case "channel down", "previous channel" -> channelDown();
            default -> handleParameterizedCommand(normalized);
        }
    }

    private void handleParameterizedCommand(String command) {
        if (command.startsWith("volume ")) {
            Integer volume = parseNumber(command.substring("volume ".length()).trim());
            if (volume != null) {
                device.setVolume(volume);
            }
            return;
        }

        if (command.startsWith("channel ")) {
            Integer channel = parseNumber(command.substring("channel ".length()).trim());
            if (channel != null) {
                device.setChannel(channel);
            }
            return;
        }

        if (command.startsWith("open ")) {
            open(command.substring("open ".length()).trim());
            return;
        }

        System.out.println("SmartRemote: command not recognized");
    }

    private void open(String target) {
        if (!(device instanceof SmartDevice smartDevice)) {
            System.out.println("SmartRemote: " + device.getClass().getSimpleName()
                    + " does not support internet browsing");
            return;
        }

        if (!device.isEnabled()) {
            System.out.println("SmartRemote: turn the device on before browsing");
            return;
        }

        smartDevice.browseInternet(target);
        System.out.println("SmartRemote: opened " + target);
    }

    private Integer parseNumber(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            System.out.println("SmartRemote: '" + value + "' is not a number");
            return null;
        }
    }
}
