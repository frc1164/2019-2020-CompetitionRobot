/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

//PhoenixTuner libraries
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

//WPILIB libraries
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Solenoid;

//Local imports
import frc.robot.Constants.conPanConstants;

public class ControlPanel extends SubsystemBase {
  private final TalonSRX talon;
  public final Solenoid raiseConPan;
  public final Solenoid lowerConPan;

  /**
   * Creates a new ControlPanel.
   */
  public ControlPanel() {
    talon = new TalonSRX(conPanConstants.talon);
    raiseConPan = new Solenoid(conPanConstants.PCM, conPanConstants.raiseConPan);
    lowerConPan = new Solenoid(conPanConstants.PCM, conPanConstants.lowerConPan);
  }

  //Control Panel motor
  public void conPanSpeed(double conPanSpeed) {
    talon.set(ControlMode.PercentOutput, conPanSpeed);
    SmartDashboard.putNumber("conPanTalon", conPanSpeed);
  }

  //Control Panel solenoid
  public void lowerConPanSol() {
    raiseConPan.set(false);
    lowerConPan.set(true);
  }

  //Control Panel solenoid
  public void raiseConPanSol() {
    lowerConPan.set(false);
    raiseConPan.set(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}