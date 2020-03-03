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
        public static final int X_AXIS = 0;
        public static final int Y_AXIS = 1;
        public static final int ROTATE = 2;
        public static final int SLIDER_AXIS = 3;
        public static final int TRIGGER = 1;
        public static final int BUTTON_3 = 3; //changeGear
        public static final int BUTTON_4 = 4;
    }

    public static final class xBoxConstants {
        public static final int OPERATOR_PORT = 1;
        public static final int RY_AXIS = 5;
        public static final int LY_AXIS = 1; //Winch
        public static final int A_BUTTON = 1; //FuelCellSol
        public static final int B_BUTTON = 2; //ConPanSol
        public static final int X_BUTTON = 3; 
        public static final int Y_BUTTON = 4; 
        public static final int L_BUMPER = 5; //fuelCellMotIn
        public static final int R_BUMPER = 6; //fuelCellMotOut
        public static final int BACK = 7; //RotateConPan
        public static final int START = 8; //SetColor

        //POV buttons
        public static final int POV_UP = 0; //RaiseClimb
        public static final int POV_DOWN = 180; //LowerClimb
    }

    public static final class fuelCellConstants {
        public static final int fuelCellMot = 0;
        public static final double fuelCellMotSpeed = 0.3;         
        public static final int raiseHopper = 4;       
        public static final int lowerHopper = 5;
    }

    public static final class conPanConstants {
        public static final int talon = 15;
        public static final double conPanMotSpeed = 0.25;   
        public static final int raiseConPan = 2;   //not firing  
        public static final int lowerConPan = 3; 

        //Color Sensor constants
        public static final I2C.Port i2cPort = I2C.Port.kOnboard;
        public static final double[] blue = {0.143, 0.427, 0.429};
        public static final double[] green = {0.197, 0.561, 0.240};
        public static final double[] red = {0.561, 0.232, 0.114};
        public static final double[] yellow = {0.361, 0.524, 0.113};
        public static final String FMScolor = "Green";
    }

    public static final class climbConstants {
        public static final int winchMot1 = 1;
        public static final int winchMot2 = 14;
        public static final boolean invertWinchMot1 = false;
        public static final boolean invertWinchMot2 = false;
        public static final int climbExtend = 6; //Solenoids may need to be reversed
        public static final int climbRetract = 7; //Solenoids may need to be reversed
        public static final int limitSwitchPort = 0;
    }
}
