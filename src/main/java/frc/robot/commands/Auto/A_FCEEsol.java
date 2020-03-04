/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FuelCell;

public class A_FCEEsol extends CommandBase {
  private final FuelCell m_FuelCell;
  private boolean m_extend;
  /**
   * Creates a new A_FCEEsol.
   */
  public A_FCEEsol(boolean extend, FuelCell m_FuelCell) {
    this.m_FuelCell = m_FuelCell;
    m_extend = extend;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_FuelCell.fuelCellAutoSol(m_extend);
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
