/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

//Controllers
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;

//Constants
import frc.robot.Constants.joyStickConstants;
import frc.robot.Constants.xBoxConstants;

//Subsystems
import frc.robot.subsystems.FuelCell;
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.ControlPanel;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.Vision;
import frc.robot.subsystems.Pixy;
import frc.robot.subsystems.Arduino;

//Chassis commands
import frc.robot.commands.ChangeGear;
import frc.robot.commands.Drive;

//FuelCell commands
import frc.robot.commands.FuelCellSol;
import frc.robot.commands.FuelCellMotIn;
import frc.robot.commands.FuelCellMotOut;

//ControlPanel commands
import frc.robot.commands.ConPanSol;
import frc.robot.commands.SetColor;
import frc.robot.commands.RotateConPan;

//Climb commands
import frc.robot.commands.LowerClimb;
import frc.robot.commands.RaiseClimb;
import frc.robot.commands.Winch;

//Arduino commands
import frc.robot.commands.ByteCodes;

//Pixy2 commands
import frc.robot.commands.SeekBall;

//Auto Commands
import frc.robot.commands.Auto.A_Score;
import frc.robot.commands.Auto.A_CenterGoalDriveToDistance;
import frc.robot.commands.Auto.A_DriveOffLine;
import frc.robot.commands.Auto.A_DriveToDistance;



/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  //Subsystems
  private final Chassis m_Chassis;
  private final Vision m_Vision;
  private final FuelCell m_FuelCell;
  private final ControlPanel m_ControlPanel;
  private final Climb m_Climb;
  private final Pixy m_Pixy;
  private final Arduino m_Arduino;


  //Default Commands
  private final Drive m_Drive;
  private final Winch m_Winch;
  private final ByteCodes m_ByteCodes;


  //Define Controllers
  public static Joystick m_DriverStick;
  public static XboxController m_OperatorController;

  //Define/Instantiate Chooser
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

   //Instantiate Subsystems 
    m_Chassis = new Chassis();
    m_FuelCell = new FuelCell();
    m_ControlPanel = new ControlPanel();
    m_Climb = new Climb();
    m_Vision = new Vision();
    m_Arduino = new Arduino();
    m_Pixy = new Pixy();


    //Set Autonomous Commands

    //Set Default Commands
    m_Drive = new Drive(m_Chassis);
    m_Chassis.setDefaultCommand(m_Drive);
    

    m_Winch = new Winch(m_Climb);
    m_Climb.setDefaultCommand(m_Winch);

    m_ByteCodes = new ByteCodes(m_Arduino, m_Pixy, m_Vision);
    m_Arduino.setDefaultCommand(m_ByteCodes);

    

    //Define Controller
    m_DriverStick = new Joystick(joyStickConstants.STICK_PORT);
    m_OperatorController = new XboxController(xBoxConstants.OPERATOR_PORT);

    configureButtonBindings();

    //Initialization methods
    m_Chassis.chassisInit();

    //define auto commands
    final Command m_simpleAuto = new ChangeGear(m_Chassis);
    final Command m_Score = new A_Score(m_Chassis, m_FuelCell, m_Vision);
    final Command m_CenterGoalDrive = new A_CenterGoalDriveToDistance(.3, 30, m_Chassis, m_Vision);
    final Command m_DriveOffLine = new A_DriveOffLine(m_Chassis);
    final Command m_DriveToDistance = new A_DriveToDistance(.3, 40, m_Chassis, m_Vision);

    //Autonomous chooser options
   
    m_chooser.setDefaultOption("Score From Start", m_Score);
    m_chooser.addOption("Drive Off Line", m_DriveOffLine);
    m_chooser.addOption("Drive/Center to Goal", m_CenterGoalDrive);
    m_chooser.addOption("Dribev  sdkfj", m_DriveToDistance);
    m_chooser.addOption("Simple Auto", m_simpleAuto);

    // Put the chooser on the dashboard
    Shuffleboard.getTab("Autonomous").add(m_chooser);
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //Chassis buttons
    new JoystickButton(m_DriverStick, joyStickConstants.BUTTON_3)
                       .whenPressed(new ChangeGear(m_Chassis));

    new JoystickButton(m_DriverStick, joyStickConstants.TRIGGER)
                       .whileHeld(new SeekBall(m_Chassis, m_Pixy));

    //FuelCell buttons
    new JoystickButton(m_OperatorController, xBoxConstants.L_BUMPER)
                      .whileHeld(new FuelCellMotIn(m_FuelCell));

    new JoystickButton(m_OperatorController, xBoxConstants.R_BUMPER)
                      .whileHeld(new FuelCellMotOut(m_FuelCell));

    new JoystickButton(m_OperatorController, xBoxConstants.A_BUTTON)
                      .whenPressed(new FuelCellSol(m_FuelCell));

    //ControlPanel buttons
    new JoystickButton(m_OperatorController, xBoxConstants.B_BUTTON)
                      .whenPressed(new ConPanSol(m_ControlPanel));
    
    new JoystickButton(m_OperatorController, xBoxConstants.BACK)
                      .whenPressed(new RotateConPan(m_ControlPanel));

    new JoystickButton(m_OperatorController, xBoxConstants.START)
                      .whenPressed(new SetColor(m_ControlPanel));

    //Climb buttons    
    new POVButton(m_OperatorController, xBoxConstants.POV_UP)
                       .whenPressed(new RaiseClimb(m_Climb, m_ControlPanel));
                       
    new POVButton(m_OperatorController, xBoxConstants.POV_DOWN)
                        .whenPressed(new LowerClimb(m_Climb));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_chooser.getSelected();
  }
}
