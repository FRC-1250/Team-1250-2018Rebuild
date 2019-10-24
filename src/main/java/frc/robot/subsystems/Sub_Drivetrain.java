/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.Cmd_ManualDrive;

/**
 * Add your docs here.
 */
public class Sub_Drivetrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  WPI_TalonSRX driveFrontLeft = new WPI_TalonSRX(RobotMap.DRV_LEFT_FRONT);
  WPI_TalonSRX driveFrontRight = new WPI_TalonSRX(RobotMap.DRV_RIGHT_FRONT);

  WPI_VictorSPX driveMidLeft = new WPI_VictorSPX(RobotMap.DRV_LEFT_MID);
  WPI_VictorSPX driveMidRight = new WPI_VictorSPX(RobotMap.DRV_RIGHT_MID);
  WPI_VictorSPX driveBackLeft = new WPI_VictorSPX(RobotMap.DRV_LEFT_BACK);
  WPI_VictorSPX driveBackRight = new WPI_VictorSPX(RobotMap.DRV_RIGHT_BACK);

  // CANSparkMax driveNeo = new CANSparkMax(RobotMap.DRV_NEO, MotorType.kBrushless);

  private SpeedController gRightSide = new SpeedControllerGroup(driveFrontRight, driveMidRight, driveBackRight);
  private SpeedController gLeftSide = new SpeedControllerGroup(driveFrontLeft, driveMidLeft, driveBackLeft);

  private DifferentialDrive difDriveGroup = new DifferentialDrive(gLeftSide, gRightSide);

  PigeonIMU pigeon = new PigeonIMU(RobotMap.PIGEON);

  public Sub_Drivetrain(){
    driveFrontLeft.setInverted(true);
		driveFrontRight.setInverted(true);
  }

  public void drive(double left, double right) {
    difDriveGroup.tankDrive(left, right);
  }

  public void drive(Joystick joy) {
    drive(-joy.getY(), -joy.getThrottle());
  }

  public void driveArcade(double speed, double rotate) {
    difDriveGroup.arcadeDrive(-speed, rotate * 0.75);
  }

  public WPI_TalonSRX getMotorTalon(String motor) {
    if (motor == "driveFrontLeft"){
      return driveFrontLeft;
    } else if (motor == "driveFrontRight") {
      return driveFrontRight;
    } else {
      return null;
    }
  }

  public WPI_VictorSPX getMotorVictor(String motor) {
    if (motor == "driveMidLeft") {
      return driveMidLeft;
    } else if (motor == "driveMidRight") {
      return driveMidRight;
    } else if (motor == "driveBackLeft") {
      return driveBackLeft;
    } else if (motor == "driveBackRight") {
      return driveBackRight;
    } else {
      return null;
    }
  }

  // public CANSparkMax getNEO(String motor) {
  //   if (motor == "driveNEO") {
  //     return driveNeo;
  //   } else {
  //     return null;
  //   }
  // }

    // Pigeon stuff
    public double getPigeonX(){
      double[] xyz = new double[3];
      pigeon.getRawGyro(xyz);
      return xyz[0];
    }

    public double getPigeonY(){
      double[] xyz = new double[3];
      pigeon.getRawGyro(xyz);
      return xyz[1];
    }

    public double getPigeonYawPitchRoll(String axis){
      double[] ypr = new double[3];
      pigeon.getYawPitchRoll(ypr);
      if (axis.toLowerCase().equals("yaw")){
        return ypr[0];
      } else if (axis.toLowerCase().equals("pitch")) {
        return ypr[1];
      } else if (axis.toLowerCase().equals("roll")) {
        return ypr[2];
      }
      return 0;
    }
    
  public void turn (double angle, double upperSpeed, double lowerSpeed) {
    double corrected;
    double rotation = angle - getPigeonYawPitchRoll("yaw");
    double sign = Math.signum(rotation);
    corrected = 0.05 * rotation;
            
      if (sign > 0){
          corrected = Math.min(upperSpeed * sign, corrected);
          corrected = Math.max(lowerSpeed * sign, corrected);
        }
                
      else {
          corrected = Math.max(upperSpeed * sign, corrected);
          corrected = Math.min(lowerSpeed * sign, corrected);                    
        }
    
          difDriveGroup.arcadeDrive(0, corrected);
        }

  public void driveStop(){
    difDriveGroup.arcadeDrive(0, 0);
  }

  public boolean isDoneTurning(double angle) {
    return (Math.abs(angle - this.getPigeonYawPitchRoll("yaw")) < 2);
  }

  public void resetPigeonYaw(){
    pigeon.setYaw(0);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Cmd_ManualDrive());
  }
}
