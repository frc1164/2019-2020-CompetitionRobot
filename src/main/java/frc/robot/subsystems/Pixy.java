/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.ArrayList;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.links.SPILink;
import io.github.pseudoresonance.pixy2api.Pixy2CCC;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;

public class Pixy extends SubsystemBase {

  private static Pixy2 pixy;
  Block largestBlock;
  ArrayList<Block> blocks;
  /**
   * Creates a new Pixy.
   */
  public Pixy() {
    pixy = Pixy2.createInstance(new SPILink());
    pixy.init();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void pixyLED() {
    pixy.init(); // Initializes the camera and prepares to send/receive data
		pixy.setLamp((byte) 0, (byte) 0); // Turns the LEDs on
		pixy.setLED(200, 30, 255); // Sets the RGB LED to purple
  }

  public Block findBlock() {
    // Gets the number of "blocks", identified targets, that match signature 1 on the Pixy2,
		// does not wait for new data if none is available,
		// and limits the number of returned blocks to 25, for a slight increase in efficiency
    Pixy2CCC temp = pixy.getCCC();
    final int blockSignature = 1;
    int errorCode = temp.getBlocks(false, Pixy2CCC.CCC_SIG1, 25);
    if (errorCode <= 0){
      System.out.println("Recieved error code: " + errorCode); // Reports error code
      return null;
    }
    else {
      System.out.println("Recieved: " + errorCode + " Blocks"); //returns number of blocks
    }
    blocks = pixy.getCCC().getBlockCache(); // Gets a list of all blocks found by the Pixy2
    largestBlock = null;
    if (blocks == null) {
			System.err.println("No Blocks");
			return null;
		}
		for (Block block : blocks) {
			if (block.getSignature() == blockSignature) {
				if (largestBlock == null) {
					largestBlock = block;
				} else if (block.getWidth() > largestBlock.getWidth()) {
					largestBlock = block;
				}
			}
		}
    return largestBlock;
  }

  public boolean ballSeen(){
    if (findBlock() != null){
      return true;
    }
    else{
      return false;
    }
  }

  public int getXAxis(){
    Block b = findBlock();
    if(ballSeen()){
      if(b != null){
      return b.getX();
      }
      else{
        return Integer.MIN_VALUE;
      }
    }
     else{ return Integer.MIN_VALUE;
    }
  }
}
