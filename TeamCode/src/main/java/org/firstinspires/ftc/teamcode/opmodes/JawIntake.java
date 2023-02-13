package org.firstinspires.ftc.teamcode.opmodes;

import org.firstinspires.ftc.teamcode.internals.hardware.Devices;
import org.firstinspires.ftc.teamcode.internals.registration.OperationMode;
import org.firstinspires.ftc.teamcode.internals.registration.TeleOperation;

public class JawIntake extends OperationMode implements TeleOperation {
    @Override
    public void construct() {
        //Devices.Companion.getServo0().setPosition(0.5);
        Devices.servo0.setPosition(50.0);
    }

    @Override
    public void run() {
        if (Devices.getController1().getA()) {
            Devices.servo0.setPosition(70.0);
        }
    }
}
