/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DigitalInput;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.robot.Constants.climbConstants;
import frc.robot.Constants.driveConstants;

public class Climb extends SubsystemBase {
  private final VictorSPX winchMot1;
  private final VictorSPX winchMot2;
  private final Solenoid climbExtend;
  private final Solenoid climbRetract;
  private static DigitalInput limitSwitch;
  public static boolean switchState;

  /**
   * Creates a new Climb.
   */
  public Climb() {
    winchMot1 = new VictorSPX(climbConstants.winchMot1);
    winchMot2 = new VictorSPX(climbConstants.winchMot2);
    climbExtend = new Solenoid(driveConstants.PCM, climbConstants.climbExtend);
    climbRetract = new Solenoid(driveConstants.PCM, climbConstants.climbRetract);
    limitSwitch = new DigitalInput(climbConstants.limitSwitchPort);
  }
  
  public void lowerClimb() {
    climbExtend.set(false);
    climbRetract.set(true);
  }

  //Make sure you raise the ControlPanel before calling this method
  public void raiseClimb() {
    climbRetract.set(false);
    climbExtend.set(true);
  }
  
  public void winchSpeed(double speed) {
    winchMot1.setInverted(climbConstants.invertWinchMot1);
    winchMot2.setInverted(climbConstants.invertWinchMot2);
    winchMot1.set(ControlMode.PercentOutput, speed);
    winchMot2.set(ControlMode.PercentOutput, speed);
  }

  public boolean switchState() {
    switchState = limitSwitch.get();
    return switchState;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
