/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.Faults;
import com.ctre.phoenix.motorcontrol.StickyFaults;
import com.revrobotics.CANSparkMax.FaultID;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class Cmd_CheckCAN extends Command {

  I2C i2c = new I2C(I2C.Port.kOnboard,84);
  byte[] toSend = new byte[7];

  public Cmd_CheckCAN() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.s_can);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }


  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    boolean frontLeftCAN, frontRightCAN, midLeftCAN, midRightCAN, backLeftCAN, backRightCAN;
    String msg = "CAN_MSG_NOT_FOUND";
    StickyFaults fault = new StickyFaults();
    Robot.s_drivetrain.driveFrontLeft.getStickyFaults(fault).toString();
    

    String errorFrontLeft = Robot.s_drivetrain.getMotorTalon("driveFrontLeft").getStickyFaults(fault).toString();
    if (errorFrontLeft == msg) {
      frontLeftCAN = false;
      toSend[0] = 72;
    } else {
      frontLeftCAN = true;
      toSend[0] = 76;
    }

    String errorMidLeft = Robot.s_drivetrain.getMotorVictor("driveMidLeft").getStickyFaults(fault).toString();
    if (errorMidLeft == msg) {
      midLeftCAN = false;
      toSend[1] = 72;
    } else {
      midLeftCAN = true;
      toSend[1] = 76;
    }

    String errorBackLeft = Robot.s_drivetrain.getMotorVictor("driveBackLeft").getStickyFaults(fault).toString();
    if (errorBackLeft == msg) {
      backLeftCAN = false;
      toSend[2] = 72;
    } else {
      backLeftCAN = true;
      toSend[2] = 76;
    }   

    String errorBackRight = Robot.s_drivetrain.getMotorVictor("driveBackRight").getStickyFaults(fault).toString();
    if (errorBackRight == msg) {
      backRightCAN = false;
      toSend[3] = 72;
    } else {
      backRightCAN = true;
      toSend[3] = 76;
    }

    String errorMidRight = Robot.s_drivetrain.getMotorVictor("driveMidRight").getStickyFaults(fault).toString();
    if (errorMidRight == msg) {
      midRightCAN = false;
      toSend[4] = 72;
    } else {
      midRightCAN = true;
      toSend[4] = 76;
    }

    String errorFrontRight = Robot.s_drivetrain.getMotorTalon("driveFrontRight").getStickyFaults(fault).toString();
    if (errorFrontRight == msg) {
      frontRightCAN = false;
      toSend[5] = 72;
    } else {
      frontRightCAN = true;
      toSend[5] = 76;
    }

    // boolean errorNEO = Robot.s_drivetrain.getNEO("driveNEO").getStickyFault(NEOFault);
    //i2c.transaction(toSend, toSend.length, null, 0);
    i2c.writeBulk(toSend);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
