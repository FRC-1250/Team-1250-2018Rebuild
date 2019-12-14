/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.commands.Cmd_CheckCAN;
import edu.wpi.first.hal.can.CANStatus;

import java.util.Vector;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.utilities.*;

/**
 * Add your docs here.
 */
public class Sub_CANState extends Subsystem implements CAN_input {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new Cmd_CheckCAN());
  }
@Override
public Vector<CAN_devicefaults> input() {
	Vector<CAN_devicefaults> masterCanDevices = new Vector<CAN_devicefaults>();
  masterCanDevices.addAll(Robot.s_drivetrain.input());
  return masterCanDevices;
}
}
