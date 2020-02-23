/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.conPanConstants;
import frc.robot.subsystems.ControlPanel;

public class ConPanMot extends CommandBase {
  private final ControlPanel m_ControlPanel;
  /**
   * Creates a new ConPanMot.
   */
  public ConPanMot(ControlPanel m_ControlPanel) {
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
    double setConPanMotSpeed = conPanConstants.conPanMotSpeed;
    m_ControlPanel.conPanSpeed(setConPanMotSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_ControlPanel.conPanSpeed(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
