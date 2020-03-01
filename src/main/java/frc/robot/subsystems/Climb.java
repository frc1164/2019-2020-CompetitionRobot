/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.robot.Constants.climbConstants;

public class Climb extends SubsystemBase {
  private final VictorSPX winchMot1;
  private final VictorSPX winchMot2;
  private final Solenoid climbExtend;
  private final Solenoid climbRetract;
  private static DigitalInput limitSwitch;

  /**
   * Creates a new Climb.
   */
  public Climb() {
    winchMot1 = new VictorSPX(climbConstants.winchMot1);
    winchMot2 = new VictorSPX(climbConstants.winchMot2);
    climbExtend = new Solenoid(climbConstants.climbExtend);
    climbRetract = new Solenoid(climbConstants.climbRetract);
    limitSwitch = new DigitalInput(driveConstants.limitSwitchPort);
  }

  public void climbInit() {
    climbExtend.set(false);
    climbRetract.set(true);
  }
  
  public void lowerClimb() {
    climbExtend.set(false);
    climbRetract.set(true);
  }

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

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
