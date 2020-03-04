/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.revrobotics.ColorMatch;
import frc.robot.subsystems.ControlPanel;
import frc.robot.Constants.conPanConstants;

public class RotateConPan extends CommandBase {
    private final ControlPanel m_ControlPanel;
    public ColorMatch m_colorMatcher;
    public String lastColor;
    public String currentColor;
    public int numChanges;
    public final int changesPerRotation = 8;
    public final int numRotations = 5;

  public RotateConPan(ControlPanel m_ControlPanel) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.m_ControlPanel = m_ControlPanel;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    numChanges = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_ControlPanel.getColor();
    m_ControlPanel.matchColor();
    m_ControlPanel.printColor();

    lastColor = ControlPanel.colorString;

    double setConPanMotSpeed = conPanConstants.conPanMotSpeed;
    m_ControlPanel.conPanSpeed(setConPanMotSpeed);

    while (numChanges < (changesPerRotation * numRotations)) {
        m_ControlPanel.getColor();
        m_ControlPanel.matchColor();
        currentColor = ControlPanel.colorString;
        
        //smartDashboard stuff
        m_ControlPanel.printColor();
        SmartDashboard.putNumber("numChanges", numChanges);
        
        if (currentColor.equals(lastColor) == false) {
          ++numChanges;
          lastColor = currentColor;
        }
    }
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