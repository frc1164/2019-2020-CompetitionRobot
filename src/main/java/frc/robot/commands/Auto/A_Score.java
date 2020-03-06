/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.FuelCell;
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.Vision;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class A_Score extends SequentialCommandGroup {

  /**
   * Creates a new A_Score.
   */
  public A_Score(Chassis m_Chassis, FuelCell m_FuelCell, Vision m_Vision) {
    
    super(
      new A_FCEEsol(true, m_FuelCell),
      
      new A_CenterGoalDriveToDistance(0.3, 14.5, m_Chassis, m_Vision),

      new A_FCEEmot(-0.3, m_FuelCell),
      new A_Delay(6),
      new A_FCEEmot(0, m_FuelCell),

      new A_FCEEsol(false , m_FuelCell),
      
      new A_Drive(-0.3, m_Chassis),
      new A_Delay(2),
      new A_Brake(m_Chassis)
    );
  }
}
