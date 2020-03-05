/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;

public class Arduino extends SubsystemBase {
  public static String gameData = null;
  Timer timer = new Timer();
  /**
   * Creates a new Arduino.
   */

   public SerialPort arduino;

  public Arduino() {
    try{
      arduino = new SerialPort(9600, SerialPort.Port.kUSB);
      System.out.println("USB1 connected");
    }
    catch(Exception e){
      System.out.println("USB1 did not connect");

      try{
        arduino = new SerialPort(9600, SerialPort.Port.kUSB1);
        System.out.println("USB2 connected");
      }
      catch(Exception e1){
        System.out.println("USB2 did not connect");
        
        try{
          arduino = new SerialPort(9600, SerialPort.Port.kUSB2);
          System.out.println("USB3 connected");
        }
        catch(Exception e2){
          System.out.println("USB3 did not connect");
        }
      }
    }
  }

  public static String gameData() {
    gameData = DriverStation.getInstance().getGameSpecificMessage();
    return gameData;
  }

  public void sendRed() {
    arduino.write(new byte[] {0x12}, 1);
  }

  public void sendGreen() {
    arduino.write(new byte[] {0x13}, 1);
  }

  public void sendBlue() {
    arduino.write(new byte[] {0x14}, 1);
  }

  public void sendYellow() {
    arduino.write(new byte[] {0x15}, 1);
  }

  public void sendBall() {
    arduino.write(new byte[] {0x16}, 1);
  }
  public void sendTarget() {
    arduino.write(new byte[] {0x17}, 1);
  }

  public void readArduino() {
    timer.start();
    if(arduino.getBytesReceived() > 0) {
      System.out.print(arduino.readString());
    }
    timer.delay(1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
