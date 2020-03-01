/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

//Controllers
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

//Constants
import frc.robot.Constants.joyStickConstants;
import frc.robot.Constants.xBoxConstants;

//Subsystems
import frc.robot.subsystems.FuelCell;
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.ControlPanel;
import frc.robot.subsystems.Climb;

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
import frc.robot.commands.RaiseConPan;
import frc.robot.commands.LowerConPan;

//Climb commands
import frc.robot.commands.LowerClimb;
import frc.robot.commands.RaiseClimb;
import frc.robot.commands.Winch;


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and default commands are defined here...
  private final Chassis m_Chassis;
  private final Drive m_Drive;
  private final FuelCell m_FuelCell;
  private final ControlPanel m_ControlPanel;
  private final Climb m_Climb;
  private final Winch m_Winch;
  public static Joystick m_DriverStick;
  public static XboxController m_OperatorController;

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

   //Instantiate Subsystems 
    m_Chassis = new Chassis();
    m_FuelCell = new FuelCell();
    m_ControlPanel = new ControlPanel();
    m_Climb = new Climb();

    //Set Autonomous Commands

    //Set Default Commands
    m_Drive = new Drive(m_Chassis);
    m_Chassis.setDefaultCommand(m_Drive);

    m_Winch = new Winch(m_Climb);
    m_Climb.setDefaultCommand(m_Winch);
    
    //Define Controller
    m_DriverStick = new Joystick(joyStickConstants.STICK_PORT);
    m_OperatorController = new XboxController(xBoxConstants.OPERATOR_PORT);

    // Configure the button bindings
    configureButtonBindings();

    //Initialization methods
    //m_Chassis.chassisInit();
    //m_ControlPanel.conPanInit();
    //m_FuelCell.fuelCellInit();
    //m_Climb.climbInit();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //Drive buttons
    new JoystickButton(m_DriverStick, joyStickConstants.BUTTON_3)
                       .whenPressed(new ChangeGear(m_Chassis));

    //Climb buttons    
    new JoystickButton(m_OperatorController, xBoxConstants.Y_BUTTON)
                       .whenPressed(new RaiseClimb(m_Climb));
                       
    new JoystickButton(m_OperatorController, xBoxConstants.X_BUTTON)
                        .whenPressed(new LowerClimb(m_Climb));

    new JoystickButton(m_OperatorController, xBoxConstants.B_BUTTON)
                        .whenPressed(new ConPanSol(m_ControlPanel));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
