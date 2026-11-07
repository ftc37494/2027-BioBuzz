# Pedro Pathing setup

Pedro Pathing 3 and its AutoTune library are added to the TeamCode Gradle dependencies.
The official Quickstart `pedro` package is included here with its configuration entry
point and tuning procedures. The FTC project uses Maven Central for Pedro artifacts
and Dairy's release repository for AutoTune's supporting libraries.

## Desktop setup from the linked video

The project is open in Android Studio, using JDK 17, and the official ADB Wi-Fi
plugin by Yury Polek is installed. PedroPathing and FTControl Panels dependencies
are integrated into the FTC workspace already in this folder, preserving your
existing team code.

PedroPathing 3 provides its own AutoTune page at `http://192.168.43.1:10158` for
robot tuning. FTControl Panels is also included; access its dashboard at
`http://192.168.43.1:801` when the computer is connected to the Control Hub Wi-Fi.
The Panels default template and field view are available there.

## Finish setup for your robot

The library is installed, but it cannot safely be configured from this project alone:
the drive motor HardwareMap names and directions, localizer type and hardware name,
and odometry pod offsets are specific to your robot. Do not run a path until these
values have been configured and tuned.

1. In `Constants.java`, configure the drive, localizer, and Foresight values for your
   robot. The Quickstart's `Constants.create()` is intentionally an unconfigured
   scaffold, so it returns `null` until you implement the follower construction.
2. In `Tuning.java`, register the tuning procedures appropriate for your robot using
   the `@Tuner` API and classes in `procedures` (see the official Quickstart for the
   current examples).
3. Set the drive motor names/directions and select the localizer
   that matches your robot (for example, goBILDA Pinpoint or dead-wheel encoders).
4. Connect the powered Control Hub to this computer with a USB-A to USB-C data cable
   once, open Android Studio's **ADB Wi-Fi** tool window, and connect the device.
   The video notes that USB-C to USB-C cables may not work for initial pairing.
   After pairing, disconnect USB and join the Control Hub's Wi-Fi from the computer.
5. Build and install TeamCode on the Robot Controller, then open
   `http://192.168.43.1:10158` in a browser on the Control Hub network.
6. Run the drivetrain and localization procedures with the robot safely on blocks
   where appropriate. Apply the generated constants, then tune Foresight and validate
   localization before writing autonomous paths.

Official references:

- [Installation](https://pedropathing.com/docs/pathing/installation)
- [Tuning overview](https://pedropathing.com/docs/pathing/tuning)
- [Mecanum drivetrain tuning](https://pedropathing.com/docs/pathing/tuning/drivetrain/mecanum)
- [Localization tuning](https://pedropathing.com/docs/pathing/tuning/localization)
