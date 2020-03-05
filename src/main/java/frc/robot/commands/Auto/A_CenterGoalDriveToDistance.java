/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Vision;
import frc.robot.subsystems.Chassis;
import edu.wpi.first.wpilibj.controller.PIDController;

public class A_CenterGoalDriveToDistance extends CommandBase {
  private final Chassis m_Chassis;
  private double m_SpeedWhileCentering;
  private double m_inchesToStop;
  private double PIDout;
  PIDController CenterLLPID = new PIDController(0.017, 0.006, 0.003);
  /**
   * Creates a new A_CenterGoalDrive.
   */
  public A_CenterGoalDriveToDistance(double forwardSpeed, double inchesToStop, Chassis m_Chassis, Vision m_Vision) {
    this.m_Chassis = m_Chassis;
    m_inchesToStop = inchesToStop;
    m_SpeedWhileCentering = -forwardSpeed;
    addRequirements(m_Chassis);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    while (!Vision.get_lltarget() || (Vision.get_Distance() >= m_inchesToStop)){
      if (Vision.get_lltarget()){   
        CenterLLPID.reset();
        CenterLLPID.setSetpoint(0.0);
        CenterLLPID.enableContinuousInput(-29.8, 29.8);
        PIDout = CenterLLPID.calculate(Vision.get_llx());
        m_Chassis.rightSpeed(m_SpeedWhileCentering - PIDout);
        m_Chassis.leftSpeed(m_SpeedWhileCentering + PIDout);
      }

      else {
        m_Chassis.leftSpeed(0.4);
        m_Chassis.rightSpeed(-0.4);
      }
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
