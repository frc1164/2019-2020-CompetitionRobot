/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.Vision;
import edu.wpi.first.wpilibj.controller.PIDController;

public class A_DriveToDistance extends CommandBase {
  private final Chassis m_Chassis;
  private final double m_distanceToStop;
  private final double m_DriveSpeed;
  PIDController UltrsDist = new PIDController(0.017, 0.006, 0.003);
  /**
   * Creates a new A_DriveDistance.
   */
  public A_DriveToDistance(double DriveSpeed, double InchesToStop, Chassis m_Chassis, Vision m_Vision) {
    this.m_Chassis = m_Chassis;
    m_DriveSpeed = -DriveSpeed;
    m_distanceToStop = InchesToStop;
    addRequirements(m_Chassis);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
        UltrsDist.reset();
        UltrsDist.setSetpoint(m_distanceToStop);
        UltrsDist.enableContinuousInput(-29.8, 29.8);
    while (Vision.get_Distance() >= m_distanceToStop) {
    m_Chassis.leftSpeed(m_DriveSpeed);
    m_Chassis.rightSpeed(m_DriveSpeed);
    }
    m_Chassis.brake();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
