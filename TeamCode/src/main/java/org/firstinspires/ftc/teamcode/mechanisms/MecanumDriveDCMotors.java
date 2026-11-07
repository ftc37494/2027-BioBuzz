package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class MecanumDriveDCMotors {
    private DcMotor FLMotor; //Front Left Motor
    private DcMotor FRMotor; //Front Right Motor
    private DcMotor BLMotor; //Front Left Motor
    private DcMotor BRMotor; //Front Right Motor
    public void init(HardwareMap hwMap){
        FLMotor = hwMap.get(DcMotor.class,"FLMotor");
        FLMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FRMotor = hwMap.get(DcMotor.class,"FRMotor");
        FRMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BLMotor = hwMap.get(DcMotor.class,"BLMotor");
        BLMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BRMotor = hwMap.get(DcMotor.class,"BRMotor");
        BRMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void setMotorSpeed(double speed){
        //input -1<speed<1
        FLMotor.setPower(speed);
        FRMotor.setPower(-speed);
        BLMotor.setPower(speed);
        BRMotor.setPower(-speed);
    }
    public void turn(double turnSpeed){
        FLMotor.setPower(-turnSpeed);
        FRMotor.setPower(-turnSpeed);
        BLMotor.setPower(-turnSpeed);
        BRMotor.setPower(-turnSpeed);
    }
    public void mecanumDrive(double speed, double turnSpeed, double strafe){
        double FLSpeed=speed-turnSpeed-strafe;
        double FRSpeed=-speed-turnSpeed-strafe;
        double BLSpeed=speed-turnSpeed+strafe;
        double BRSpeed=-speed-turnSpeed+strafe;
        double maxSpeed = Math.abs(Math.max(Math.max(FLSpeed, FRSpeed), Math.max(BLSpeed, BRSpeed)));
        if(maxSpeed<1){
            maxSpeed=1;
        }
        FLMotor.setPower(FLSpeed/maxSpeed);
        FRMotor.setPower(FRSpeed/maxSpeed);
        BLMotor.setPower(BLSpeed/maxSpeed);
        BRMotor.setPower(BRSpeed/maxSpeed);
    }

}
