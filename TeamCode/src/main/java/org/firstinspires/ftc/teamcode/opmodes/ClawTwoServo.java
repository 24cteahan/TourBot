package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.internals.hardware.Devices;
import org.firstinspires.ftc.teamcode.internals.registration.OperationMode;
import org.firstinspires.ftc.teamcode.internals.registration.TeleOperation;

public class ClawTwoServo extends OperationMode implements TeleOperation {

    private DistanceSensor distSensor;
    ElapsedTime OpenClawTimer;
    final int ClawOpenWaitMS = 1500;
    boolean closed;
    double distVal;

    //left servo is servo 0 and right servo is servo 1
    @Override
    public void construct() {;

        // This has servos set by Servo Programmer
        OpenClawTimer = new ElapsedTime();
        closed = false;
        Devices.servo0.setPosition(0);
        Devices.servo1.setPosition(1);
        telemetry.speak("Home Position", null, null);
        telemetry.update();
    }

    @Override
    public void run() {
        distVal = Devices.distanceSensor.getSensor().getDistance(DistanceUnit.CM);
        Devices.distanceSensor.setUnit(DistanceUnit.CM);
        Devices.distanceSensor.getDistance();
        if (distVal < 2.5) {
            Devices.servo0.setPosition(1);
            Devices.servo1.setPosition(0);
            closed = true;
        }
        if (closed && gamepad1.circle) {
            OpenClawTimer.reset();
            Devices.servo0.setPosition(0);
            Devices.servo1.setPosition(1);
            closed = false;
            while (OpenClawTimer.milliseconds() < ClawOpenWaitMS);
        }
        if (!closed && gamepad1.cross) {
            Devices.servo0.setPosition(1);
            Devices.servo1.setPosition(0);
            closed = true;
        }
        telemetry.addData("DistVal", distVal);
        telemetry.addData("Circle", gamepad1.circle);
        telemetry.update();
    }
}