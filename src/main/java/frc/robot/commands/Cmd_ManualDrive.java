/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Cmd_ManualDrive extends Command {
  public Cmd_ManualDrive() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.s_drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // double speed = Robot.m_oi.getGamepad0().getY();
    // double rotate = Robot.m_oi.getGamepad1().getRawAxis(3);
    // Robot.s_drivetrain.driveArcade(speed, rotate);

    double y = Robot.m_oi.getGamepad0().getY();
    double z = Robot.m_oi.getGamepad0().getThrottle();

    Robot.s_drivetrain.drive(y, z);;


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
