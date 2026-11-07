package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.MecanumDriveDCMotors;
@TeleOp
public class MecanumDrive extends OpMode {
    MecanumDriveDCMotors bench = new MecanumDriveDCMotors();
    @Override
    public void init(){
        bench.init(hardwareMap);
    }
    @Override
    public void loop(){
        bench.mecanumDrive(gamepad1.left_stick_y,gamepad1.right_stick_x,gamepad1.left_stick_x);
    }
}
