package org.firstinspires.ftc.teamcode.mechanisms;

import android.util.Size;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.ArrayList;
import java.util.List;

public class AprilTagWebcam {
    private AprilTagProcessor aprilTagProcessor;
    private VisionPortal visionPortal;

    private List<AprilTagDetection> detectedTags= new ArrayList<>();

    private Telemetry telemetry;

    public void init(HardwareMap hwMap, Telemetry telemetry){
        this.telemetry=telemetry;

        aprilTagProcessor=new AprilTagProcessor.Builder()
                .setDrawTagOutline(true)
                .setDrawAxes(true)
                .setDrawCubeProjection(true)
                .setOutputUnits(DistanceUnit.CM, AngleUnit.DEGREES)
                .build();
        VisionPortal.Builder builder= new VisionPortal.Builder();
        builder.setCamera(hwMap.get(WebcamName.class,"Webcam 1"));
        builder.setCameraResolution(new Size(640,480));
        builder.addProcessor(aprilTagProcessor);

        visionPortal=builder.build();
    }

    public void update(){
        detectedTags=aprilTagProcessor.getDetections();
    }

    public List<AprilTagDetection> getDetectedTags() {
        return detectedTags;
    }

    public void displayDetectionTelemetry(AprilTagDetection detectId){
        if(detectId==null) {return;}
        if (detectId.metadata != null) {
            telemetry.addLine(String.format("\n==== (ID %d) %s", detectId.id, detectId.metadata.name));
            telemetry.addLine(String.format("XYZ %6.1f %6.1f %6.1f  (inch)", detectId.ftcPose.x, detectId.ftcPose.y, detectId.ftcPose.z));
            telemetry.addLine(String.format("PRY %6.1f %6.1f %6.1f  (deg)", detectId.ftcPose.pitch, detectId.ftcPose.roll, detectId.ftcPose.yaw));
            telemetry.addLine(String.format("RBE %6.1f %6.1f %6.1f  (inch, deg, deg)", detectId.ftcPose.range, detectId.ftcPose.bearing, detectId.ftcPose.elevation));
        } else {
            telemetry.addLine(String.format("\n==== (ID %d) Unknown", detectId.id));
            telemetry.addLine(String.format("Center %6.0f %6.0f   (pixels)", detectId.center.x, detectId.center.y));
        }
    }

    public AprilTagDetection getTagBySpecificId(int id){
        for(AprilTagDetection detection : detectedTags){
            if(detection.id==id){
                return detection;
            }
        }
        return null;
    }
    public void stop(){
        if(visionPortal != null){
            visionPortal.close();
        }
    }
}
