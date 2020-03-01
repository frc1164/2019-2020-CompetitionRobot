/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.DigitalInput;

import frc.robot.RobotContainer;
import frc.robot.Constants.xBoxConstants;
import frc.robot.Constants.climbConstants;
import frc.robot.subsystems.Climb;

public class Winch extends CommandBase {
  private final Climb m_Climb;
  private static boolean switchState;
  private static boolean switchTriggered = false;
  private static DigitalInput limitSwitch;

  /**
   * Creates a new Winch.
   */
  public Winch(Climb m_Climb) {
    this.m_Climb = m_Climb;
    addRequirements(m_Climb);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    limitSwitch = new DigitalInput(climbConstants.limitSwitchPort);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    switchState = limitSwitch.get();

    if (limitSwitch.get() != switchState) {
      switchTriggered = true;
    }

    double runWinch = RobotContainer.m_OperatorController.getRawAxis(xBoxConstants.RY_AXIS);

    runWinch = (Math.abs(runWinch) <= 0.05) ? 0 : runWinch; 
    
    double winchMot1speed = (runWinch);
    double winchMot2speed = (runWinch);

    m_Climb.winchSpeed(winchMot1speed);
    m_Climb.winchSpeed(winchMot2speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Climb.winchSpeed(0.0);
    m_Climb.winchSpeed(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return switchTriggered;
  }
}
