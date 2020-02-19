/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.I2C;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class driveConstants {
        public static final int leftMotorRear = 13;
        public static final int rightMotorRear = 2;
        public static final int leftMotorFront = 12;
        public static final int rightMotorFront = 3;

        public static final boolean invertLeftMotorRear = true;
        public static final boolean invertRightMotorRear = false;
        public static final boolean invertLeftMotorFront = true;
        public static final boolean invertRightMotorFront = false;
        
        public static final int PCM = 5;
        public static final int LowSol = 1;
        public static final int HighSol = 0;

        public static final int leftEncoderChanA = 0;
        public static final int leftEncoderChanB = 1;
        public static final int rightEncoderChanA = 2;
        public static final int rightEncoderChanB = 3;
    }

    public static final class fuelCellEEConstants {
        public static final int talon = 15; // make this into a regular motor
        public static final int fuelCellEESolenoidExtend = 5;
        public static final int fuelCellEESolenoidRetract = 4;
    }

    public static final class joyStickConstants {
        public static final int stickPort = 0;
        public static final int y_Axis = 1;
        public static final int x_Axis = 0;
        public static final int rotate = 2;
        public static final int slider = 3;
        public static final int trigger = 1;
        public static final int changeGear = 3;
        public static final int fuelCellEESol = 4;
    }

    public static final class xBoxConstants {
        public static final int operatorPort = 1;
        public static final int ry_Axis = 5;
        public static final int A_BUTTON = 1;
    }

    public static final class conPanConstants {
        public static final int talon = 15;
        public static final double conPanSpeed = .1;
        public static final int conPanSolExtend = 2; //these may need to be swapped
        public static final int conPanSolRetract = 3; //these may need to be swapped
    }
}
