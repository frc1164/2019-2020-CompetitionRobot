/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.controller.PIDController;
import frc.robot.subsystems.Pixy;
import frc.robot.subsystems.Chassis;
import frc.robot.RobotContainer;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;


public class centerBall extends CommandBase {
  private final Chassis m_Chassis;
  private final Pixy m_Pixy;
  public static double PIDout;
  private boolean buttonReleased;

  private ShuffleboardTab tab = Shuffleboard.getTab("PID Settings");
  private NetworkTableEntry kP = tab.add("Line P", 0.006).getEntry();
  private NetworkTableEntry kI = tab.add("Line I", 0.0015).getEntry();
  private NetworkTableEntry kD = tab.add("Line D", 0.0005).getEntry();
  double P, I, D, PIDGoal; {
  
  }
  PIDController testPID = new PIDController(P, I, D);



  /**
   * Creates a new centerBall.
   */
  public centerBall(Chassis m_Chassis, Pixy m_Pixy) {
    this.m_Chassis = m_Chassis;
    this.m_Pixy = m_Pixy;
  }

  // Called when the command is initially scheduled.-
  @Override
  public void initialize() {
  P = kP.getDouble(0.0);
  I = kI.getDouble(0.0);
  D = kD.getDouble(0.0);
  testPID.setPID(P, I, D);
  testPID.setSetpoint(158);
  testPID.enableContinuousInput(0, 315);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override

  public void execute() {
    buttonReleased = false;
    if (m_Pixy.ballSeen()) {
    PIDout = testPID.calculate(m_Pixy.getXAxis());
    SmartDashboard.putNumber("Output",PIDout);

    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    PIDout = 0.0;
    buttonReleased = true;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return buttonReleased;
  }
}