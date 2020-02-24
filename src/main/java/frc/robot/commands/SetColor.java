/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.revrobotics.ColorMatch;
import frc.robot.subsystems.ControlPanel;
import frc.robot.Constants.conPanConstants;

public class SetColor extends CommandBase {
    private final ControlPanel m_ControlPanel;
    public ColorMatch m_colorMatcher;
    public boolean isMatched;

  public SetColor(ControlPanel m_ControlPanel) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.m_ControlPanel = m_ControlPanel;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    isMatched = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Add code that sets the LEDs
    m_ControlPanel.getColor();
    m_ControlPanel.matchColor();
    m_ControlPanel.printColor();

    //if the SmartDashboard output 'colorString' is not equal to the String 'FMScolor,' run the motor
    if (ControlPanel.colorString.compareTo(conPanConstants.FMScolor) != 0) {
        double setConPanMotSpeed = conPanConstants.conPanMotSpeed;
        m_ControlPanel.conPanSpeed(setConPanMotSpeed);
    }
    //else, end the command and turn off the motor
    else {
        isMatched = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_ControlPanel.conPanSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isMatched;
  }
}