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
import frc.robot.Constants.conPanConstants;

//Subsystems
import frc.robot.subsystems.FuelCellEE;
import frc.robot.subsystems.Chassis;

//Commands
import frc.robot.commands.ChangeGear;
import frc.robot.commands.Drive;
import frc.robot.commands.FuelCellEESol;
import frc.robot.commands.FuelCellEEMot;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Chassis m_Chassis;
  private final Drive m_Drive;
  private final FuelCellEEMot m_FuelCellEEMot;
  private final FuelCellEE m_FuelCellEE;
  public static Joystick m_DriverStick;
  public static XboxController m_OperatorController;


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

   //Instantiate Subsystems 
    m_Chassis = new Chassis();
    m_FuelCellEE = new FuelCellEE();


    //Set Autonomous Commands

    //Set Default Commands
    m_Drive = new Drive(m_Chassis);
    m_FuelCellEEMot = new FuelCellEEMot(m_FuelCellEE);

    m_Chassis.setDefaultCommand(m_Drive);
    m_FuelCellEE.setDefaultCommand(m_FuelCellEEMot);
    
    //Define Controller
    m_DriverStick = new Joystick(joyStickConstants.stickPort);
    m_OperatorController = new XboxController(xBoxConstants.operatorPort);

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
    new JoystickButton(m_DriverStick, joyStickConstants.changeGear)
                       .whenPressed(new ChangeGear(m_Chassis));

    new JoystickButton(m_DriverStick, joyStickConstants.fuelCellEESol)
                       .whenPressed(new FuelCellEESol(m_FuelCellEE));
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
