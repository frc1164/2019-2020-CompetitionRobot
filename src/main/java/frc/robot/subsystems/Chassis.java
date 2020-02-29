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
  private final Solenoid LowSol, HighSol;
  private final VictorSPX leftMotorRear;
  private final VictorSPX rightMotorRear;
  private final VictorSPX leftMotorFront;
  private final VictorSPX rightMotorFront;
  private static boolean m_changeGear = false;

  /**
   * Creates a new Chassis.
   */
  public Chassis() {
    LowSol = new Solenoid(driveConstants.PCM, driveConstants.LowSol);                      
    HighSol = new Solenoid(driveConstants.PCM, driveConstants.HighSol);
    leftMotorRear = new VictorSPX(driveConstants.leftMotorRear);
    rightMotorRear = new VictorSPX(driveConstants.rightMotorRear);
    leftMotorFront = new VictorSPX(driveConstants.leftMotorFront);
    rightMotorFront = new VictorSPX(driveConstants.rightMotorFront);
  }

  //Used to set to low gear when initialized
  public void chassisInit() {
    HighSol.set(false);
    LowSol.set(true);
  }

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

  public void changeGear() {
    Chassis.m_changeGear = !Chassis.m_changeGear;
    LowSol.set(!Chassis.m_changeGear);
    HighSol.set(Chassis.m_changeGear);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
