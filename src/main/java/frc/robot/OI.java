/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.commands.Cmd_AutoTurn;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  Joystick Gamepad0 = new Joystick(0);
  Joystick Gamepad1 = new Joystick(1);
  Joystick Gamepad2 = new Joystick(2);

  JoystickButton a = new JoystickButton(Gamepad0, 2);
  JoystickButton b = new JoystickButton(Gamepad0, 3);
  JoystickButton y = new JoystickButton(Gamepad0, 4);
  JoystickButton x = new JoystickButton(Gamepad0, 1);
  JoystickButton rt = new JoystickButton(Gamepad0, 8);
  JoystickButton lt = new JoystickButton(Gamepad0, 7);

  public Joystick getGamepad0(){
      return Gamepad0;
  }

  public Joystick getGamepad1(){
    return Gamepad1;
  }

  public Joystick getGamepad2(){
    return Gamepad2;
  }


  Trigger joystick90 = new Trigger() {
    @Override 
    public boolean get() { 
      double degX = getGamepad2().getX();
      return (degX > 0.9); 
    }
  };

  


public OI(){

  joystick90.whenActive(new Cmd_AutoTurn(90, 0.6, 0.6));
  
}

}
