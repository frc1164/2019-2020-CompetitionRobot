/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

//PhoenixTuner libraries
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

//WPILIB libraries
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Solenoid;

//Local imports
import frc.robot.Constants.driveConstants;

public class Chassis extends SubsystemBase {
  private final VictorSPX leftMotorRear = new VictorSPX(driveConstants.leftMotorRear);
  private final VictorSPX rightMotorRear = new VictorSPX(driveConstants.rightMotorRear);
  private final VictorSPX leftMotorFront = new VictorSPX(driveConstants.leftMotorFront);
  private final VictorSPX rightMotorFront = new VictorSPX(driveConstants.rightMotorFront);
  private final Solenoid LowSol, HighSol;

  /**
   * Creates a new Chassis.
   */
  public Chassis() {
    LowSol = new Solenoid(driveConstants.PCM, driveConstants.LowSol);                      
    HighSol = new Solenoid(driveConstants.PCM, driveConstants.HighSol);
  }

//not sure if this needs to be in Periodic
  public void leftSpeed(double speed) {
    leftMotorRear.setInverted(driveConstants.invertLeftMotorRear);
    leftMotorFront.setInverted(driveConstants.invertLeftMotorFront);
    leftMotorFront.set(ControlMode.PercentOutput, speed);
    leftMotorRear.set(ControlMode.PercentOutput, speed);
  }

  public void rightSpeed(double speed) {
    rightMotorRear.setInverted(driveConstants.invertRightMotorRear);
    rightMotorFront.setInverted(driveConstants.invertRightMotorFront);
    rightMotorFront.set(ControlMode.PercentOutput, speed);
    rightMotorRear.set(ControlMode.PercentOutput, speed);
  }

  public void changeGear(boolean isHigh) {
    LowSol.set(!isHigh);
    HighSol.set(isHigh);
  }

  public void setLowGear() {
    changeGear(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
