/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

//REV libraries
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;

//PhoenixTuner libraries
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.NeutralMode;

//WPILIB libraries
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.Solenoid;

//Local imports
import frc.robot.Constants.conPanConstants;
import frc.robot.Constants.driveConstants;

public class ControlPanel extends SubsystemBase {
  private final TalonSRX talon;
  public final Solenoid raiseConPan;
  public final Solenoid lowerConPan;
  private static boolean conPanFlipSol = false;

  //Color target declarations
  private final Color BLUE_TARGET;
  private final Color GREEN_TARGET;
  private final Color RED_TARGET;
  private final Color YELLOW_TARGET;

  //REV object declarations
  public final ColorSensorV3 m_colorSensor;
  public final ColorMatch m_colorMatcher;
  public static ColorMatchResult match;

  //Local variables
  public static Color detectedColor;
  public static String colorString = "Unknown";

  /**
   * Creates a new ControlPanel.
   */
  public ControlPanel() {
      talon = new TalonSRX(conPanConstants.talon);
      talon.setNeutralMode(NeutralMode.Brake);
      lowerConPan = new Solenoid(driveConstants.PCM, conPanConstants.lowerConPan);
      raiseConPan = new Solenoid(driveConstants.PCM, conPanConstants.raiseConPan);

    //SetColor objects
    m_colorSensor = new ColorSensorV3(conPanConstants.i2cPort);
    m_colorMatcher = new ColorMatch();
    
    //Calibrates RGB values colors
    BLUE_TARGET = ColorMatch.makeColor(conPanConstants.blue[0], conPanConstants.blue[1], conPanConstants.blue[2]);
    GREEN_TARGET = ColorMatch.makeColor(conPanConstants.green[0], conPanConstants.green[1], conPanConstants.green[2]);
    RED_TARGET = ColorMatch.makeColor(conPanConstants.red[0], conPanConstants.red[1], conPanConstants.red[2]);
    YELLOW_TARGET = ColorMatch.makeColor(conPanConstants.yellow[0], conPanConstants.yellow[1], conPanConstants.yellow[2]);

    //Assigns colors to matcher
    m_colorMatcher.addColorMatch(BLUE_TARGET);
    m_colorMatcher.addColorMatch(GREEN_TARGET);
    m_colorMatcher.addColorMatch(RED_TARGET);
    m_colorMatcher.addColorMatch(YELLOW_TARGET);
  } 
    
  //Gets color (helpful for LED programming, etc.)
  public void getColor() {
    detectedColor = m_colorSensor.getColor();
  }

  //Sets string to matched color
  public void matchColor() {  
  match = m_colorMatcher.matchClosestColor(detectedColor);

    if (match.color == BLUE_TARGET) {
      colorString = "Blue";
    } 
    else if (match.color == RED_TARGET) {
      colorString = "Red";
    } 
    else if (match.color == GREEN_TARGET) {
      colorString = "Green";
    } 
    else if (match.color == YELLOW_TARGET) {
      colorString = "Yellow";
    } 
    else {
      colorString = "Unknown";
    }
  }

  //Prints color to SmartDashboard
  public void printColor() {
    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putString("Detected Color", colorString);
    }

  //Control Panel motor
  public void conPanSpeed(double conPanSpeed) {
    talon.set(ControlMode.PercentOutput, conPanSpeed);
    SmartDashboard.putNumber("conPanTalon", conPanSpeed);
  }

  //Toggle ConPanSol
  public void conPanflipSol() {
    ControlPanel.conPanFlipSol = !ControlPanel.conPanFlipSol;
    lowerConPan.set(!ControlPanel.conPanFlipSol);
    raiseConPan.set(ControlPanel.conPanFlipSol);
    SmartDashboard.putBoolean("conPanSol", ControlPanel.conPanFlipSol);
  }

  //Control Panel solenoid
  public void lowerConPanSol() {
    ControlPanel.conPanFlipSol = false;
    raiseConPan.set(false);
    lowerConPan.set(true);
  }

  //Control Panel solenoid
  public void raiseConPanSol() {
    ControlPanel.conPanFlipSol = true;
    lowerConPan.set(false);
    raiseConPan.set(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}