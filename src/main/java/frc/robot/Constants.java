/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import org.w3c.dom.css.RGBColor;
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
        public static final int talon = 15;
        //Exted and retract may have to be be reversed
        public static final int fuelCellEESolenoidExtend = 4;
        public static final int fuelCellEESolenoidRetract = 5;
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
        public static final int readRGB = 1;
    }

    public static final class xBoxConstants {
        public static final int operatorPort = 1;
        public static final int ry_Axis = 5;
    }

    public static final class conPanConstants {
        public static final int conPanEESolenoidExtend = 4;
        public static final int conPanEESolenoidRetract = 5;
        public static final I2C.Port i2cPort = I2C.Port.kOnboard;
        public static final double[] blue = {0.143, 0.427, 0.429};
        public static final double[] green = {0.197, 0.561, 0.240};
        public static final double[] red = {0.561, 0.232, 0.114};
        public static final double[] yellow = {0.361, 0.524, 0.113};
    }
}
