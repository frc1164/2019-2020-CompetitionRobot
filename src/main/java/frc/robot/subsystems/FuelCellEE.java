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
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//Local imports
import frc.robot.Constants.fuelCellEEConstants;
import frc.robot.Constants.driveConstants;

public class FuelCellEE extends SubsystemBase {
  private final TalonSRX talon = new TalonSRX(fuelCellEEConstants.talon);
  private final Solenoid fuelCellEESolenoidExtend;
  private final Solenoid fuelCellEESolenoidRetract;
  /**
   * Creates a new FuelCellEE.
   */
  public FuelCellEE() {
    fuelCellEESolenoidExtend = new Solenoid(driveConstants.PCM, fuelCellEEConstants.fuelCellEESolenoidExtend);
    fuelCellEESolenoidRetract = new Solenoid(driveConstants.PCM, fuelCellEEConstants.fuelCellEESolenoidRetract);
  }

  public void fuelCellEESpeed(double speed) {
    speed = (Math.abs(speed) <= 0.1) ? 0 : speed;

    talon.set(ControlMode.PercentOutput, speed);
  
    SmartDashboard.putNumber("talon", speed);
  }

  public void fuelCellEEFlip(boolean isExtended) {
    fuelCellEESolenoidRetract.set(!isExtended);
    fuelCellEESolenoidExtend.set(isExtended);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
