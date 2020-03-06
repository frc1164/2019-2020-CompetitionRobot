/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.controller.PIDController;
import frc.robot.subsystems.Vision;
import frc.robot.subsystems.Chassis;

public class TestDistancePID extends CommandBase {
  private final Vision m_Vision;
  private final Chassis m_Chassis;
  private ShuffleboardTab tab = Shuffleboard.getTab("PID US Settings");
  private NetworkTableEntry kP = tab.add("Line P", 0.017).getEntry();
  private NetworkTableEntry kI = tab.add("Line I", 0.006).getEntry();
  private NetworkTableEntry kD = tab.add("Line D", 0.003).getEntry();
  private NetworkTableEntry Dist = tab.add("Goal Distance", 40).getEntry();
  double P, I, D; 
  public static double PIDout;
  private boolean buttonReleased;
  PIDController testPID = new PIDController(P, I, D);

  /**
   * Creates a new SeekBall.
   */
  public TestDistancePID(Chassis m_Chassis, Vision m_Vision) {
    this.m_Vision = m_Vision;
    this.m_Chassis = m_Chassis;
    addRequirements(m_Chassis);
  }

  // Called when the command is initially scheduled.-
  @Override
  public void initialize() {
  PIDout = 0.0;
  P = kP.getDouble(0.0);
  I = kI.getDouble(0.0);
  D = kD.getDouble(0.0);
  testPID.setPID(P, I, D);
  testPID.setSetpoint(Dist.getDouble(30)); // 30 inches
  testPID.enableContinuousInput(0, 96);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override

  public void execute() {
    buttonReleased = false;
    PIDout = testPID.calculate(Vision.get_Distance());
    m_Chassis.rightSpeed (PIDout);
    m_Chassis.leftSpeed (PIDout);
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
