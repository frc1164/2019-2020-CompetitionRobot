/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

//PhoenixTuner libraries
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

//WPILIB libraries
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Solenoid;

//Local imports
import frc.robot.Constants.fuelCellConstants;
import frc.robot.Constants.driveConstants;

public class FuelCell extends SubsystemBase {
  private final VictorSPX fuelCellMot;
  private final Solenoid fuelCellSolenoidExtend;
  private final Solenoid fuelCellSolenoidRetract; //Change to "sol"
  private static boolean fuelCellFlipSol = false;

  /**
   * Creates a new FuelCell.
   */
  public FuelCell() {
    fuelCellMot = new VictorSPX(fuelCellConstants.fuelCellMot);
    fuelCellSolenoidExtend = new Solenoid(driveConstants.PCM, fuelCellConstants.fuelCellSolenoidExtend);
    fuelCellSolenoidRetract = new Solenoid(driveConstants.PCM, fuelCellConstants.fuelCellSolenoidRetract);
  }

  public void fuelCellFlip() {
    FuelCell.fuelCellFlipSol = !FuelCell.fuelCellFlipSol;
    fuelCellSolenoidRetract.set(!FuelCell.fuelCellFlipSol);
    fuelCellSolenoidExtend.set(FuelCell.fuelCellFlipSol);
    SmartDashboard.putBoolean("fuelCellSol", FuelCell.fuelCellFlipSol);
  }
  
  public void fuelCellSpeed(double fuelCellSpeed) {
    fuelCellMot.set(ControlMode.PercentOutput, fuelCellSpeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
