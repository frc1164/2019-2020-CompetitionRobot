/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.util.LinkedList;
import edu.wpi.first.wpilibj.Ultrasonic;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Vision extends SubsystemBase {
  public static NetworkTable table;
  public static NetworkTableEntry tx, ty, ta, tv;
  public static Ultrasonic m_Ultrasonic;
  LinkedList<Double> LLvalues = new LinkedList<Double>();

  /**
   * Creates a new Vision.
   */
  public Vision() {
    m_Ultrasonic = new Ultrasonic(6, 7);
    m_Ultrasonic.setAutomaticMode(true);
    //Sets up Lime Light Network Tables
    table = NetworkTableInstance.getDefault().getTable("limelight");
    tx = table.getEntry("tx");
    ty = table.getEntry("ty");
    ta = table.getEntry("ta");
    tv = table.getEntry("tv");
  }
  
  //Method for LimeLight valid target
  public static boolean get_lltarget() {
    double LLt = tv.getDouble(0.0);
    
    if (LLt == 1) {
      return true;
    }

    else {
      return false;
    }
  }

  //Method for LimeLight x_axis
  public static double get_llx() {
    double LLx = tx.getDouble(0.0);
    return LLx;
  }

  //Method for LimeLight y_axis
  public static double get_lly() {
    double LLy = ty.getDouble(0.0);
    return LLy;
  }

  //Method for LimeLight area
  public static double get_llarea() {
    double LLarea = ta.getDouble(0.0);
    return LLarea;
  }

  public static double get_Distance(){
    return m_Ultrasonic.getRangeInches();
  }

  //Displays LimeLight Valu
  public void printLLvalues() {
    SmartDashboard.putBoolean("Object Detected", get_lltarget());
    SmartDashboard.putNumber("LimelightX", get_llx());
    SmartDashboard.putNumber("LimelightY", get_lly());
    SmartDashboard.putNumber("LimelightArea", get_llarea());
    SmartDashboard.putNumber("Lime light Dist", get_Distance());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
