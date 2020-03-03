/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;

import frc.robot.subsystems.Climb;
import frc.robot.subsystems.ControlPanel;

public class RaiseClimb extends CommandBase {
  private final Climb m_Climb;
  private final ControlPanel m_ControlPanel;
  /**
   * Creates a new RaiseClimb.
   */
  public RaiseClimb(Climb m_Climb, ControlPanel m_ControlPanel) {
    this.m_Climb = m_Climb;
    this.m_ControlPanel = m_ControlPanel;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_ControlPanel.raiseConPanSol();
    Timer.delay(1);
    m_Climb.raiseClimb();
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
