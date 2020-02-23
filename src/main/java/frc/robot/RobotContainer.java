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

//Commands
import frc.robot.commands.ChangeGear;
import frc.robot.commands.Drive;
import frc.robot.commands.FuelCellSol;
import frc.robot.commands.FuelCellMotIn;
import frc.robot.commands.FuelCellMotOut;
//import frc.robot.commands.RaiseConPan;
//import frc.robot.commands.LowerConPan;
import frc.robot.commands.ConPanMot;

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

    //Set Autonomous Commands

    //Set Default Commands
    m_Drive = new Drive(m_Chassis);
    m_Chassis.setDefaultCommand(m_Drive);
    
    //Define Controller
    m_DriverStick = new Joystick(joyStickConstants.STICK_PORT);
    m_OperatorController = new XboxController(xBoxConstants.OPERATOR_PORT);

    // Configure the button bindings
    configureButtonBindings();
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

    //FuelCell buttons                       
    new JoystickButton(m_OperatorController, xBoxConstants.X_BUTTON)
                       .whenPressed(new FuelCellSol(m_FuelCell));

    new JoystickButton(m_OperatorController, xBoxConstants.R_BUMPER)
                       .whileHeld(new FuelCellMotIn(m_FuelCell));

    new JoystickButton(m_OperatorController, xBoxConstants.L_BUMPER)
                       .whileHeld(new FuelCellMotOut(m_FuelCell));

    //ControlPanel buttons
    new JoystickButton(m_OperatorController, xBoxConstants.Y_BUTTON)
                       .whenPressed(new RaiseConPan(m_ControlPanel));

    new JoystickButton(m_OperatorController, xBoxConstants.B_BUTTON)
                       .whenPressed(new LowerConPan(m_ControlPanel));

    new JoystickButton(m_OperatorController, xBoxConstants.A_BUTTON)
                       .whileHeld(new ConPanMot(m_ControlPanel));
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
