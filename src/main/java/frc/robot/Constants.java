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

    public static final class joyStickConstants {
        public static final int STICK_PORT = 0;
        public static final int Y_AXIS = 1;
        public static final int X_AXIS = 0;
        public static final int ROTATE = 2;
        public static final int SLIDER_AXIS = 3;
        public static final int TRIGGER = 1;
        public static final int BUTTON_3 = 3; //changeGear
        public static final int BUTTON_4 = 4;
    }

    public static final class xBoxConstants {
        public static final int OPERATOR_PORT = 1;
        public static final int RY_AXIS = 5;
        public static final int A_BUTTON = 1; //fuelCellSol
        public static final int B_BUTTON = 2; //conPanMot
        public static final int X_BUTTON = 3; //fuelCellMot
        public static final int Y_BUTTON = 4; //conPanSol
        public static final int L_BUMPER = 5;
        public static final int R_BUMPER = 6;
    }

    public static final class fuelCellConstants {
        public static final int fuelCellMot = 0;
        public static final double fuelCellMotSpeed = 0.3;         
        public static final int fuelCellSolenoidExtend = 4;       
        public static final int fuelCellSolenoidRetract = 5;
    }

    public static final class conPanConstants {
        public static final int talon = 15;
        public static final double conPanMotSpeed = 0.1;        
        public static final int lowerConPan = 2;
        public static final int raiseConPan = 3;
    }
}
