/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ControlPanel;

public class ConPanSol extends CommandBase {
  private static boolean conPanFlipSol = false;
  private ControlPanel m_ControlPanel;
  /**
   * Creates a new ExtendConPan.
   */
  public ConPanSol(ControlPanel m_ControlPanel) {
    this.m_ControlPanel = m_ControlPanel;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    ConPanSol.conPanFlipSol = !ConPanSol.conPanFlipSol;
    this.m_ControlPanel.conPanEEflipSol(ConPanSol.conPanFlipSol);
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
