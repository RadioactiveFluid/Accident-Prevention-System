/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hp
 */
/*
*Albi: From what I see, the for loops seem to cause a lot of the problems. Also, the logic that was discussed on our first official meeting should be done. 
*Please bear those ideas in mind.
*I should also suggest that you shouid try to implement a large while loop.
*/
import java.util.Scanner;
public class APSSystemmain {
    public static void main(String args[]) {
        //Albi:why is there an array of inputs? Make it easier and just ask for one input.
        Double[] durationValues = new Double[4];// Array of duration values*******
        Integer[] movementDirection = new Integer[4]; //Array grade********
        
        Scanner in= new Scanner(System.in);
        System.out.println("INPUT 0 TO ACTIVATE THE ACCIDENT PREVENTION SYSTEM:");
        System.out.println ("                                                  ");
        int a1= in.nextInt();
        APSSystem b1= new APSSystem(a1);
        System.out.println ("                                                  ");
        b1.TriggerAlarm();
        //Albi: Please trace the output.
        for (int i=0; i<=3;i++)
            {
                
            System.out.printf("INPUT PULSE DURATION (IN MICROSECONDS) FOR THE SENSOR %d: \n", i+1);
            System.out.println ("                                                  ");
            durationValues [i] = in.nextDouble();
            double a2= durationValues[i];  //Albi: what is a2 used for? I don't see this used again in the code.         
            }
        
        for (int j=0; j<=3;j++)
                {
        
                    double distance_x=b1.SensorDistance(durationValues[j]);
                    System.out.printf("DISTANCE FROM OBSTACLE FOR SENSOR %d is %f cm \n", j+1, distance_x);
                    System.out.println ("                                                  ");
  
                     System.out.println("CHECKING TO SEE WHICH DIRECTION IS SAFE TO MOVE IN. PLEASE WAIT");
                     System.out.println ("                                                  ");
                     if ((b1.isUnsafeDir()== true))
                                {     
                          System.out.println("OBSTACLE HAS BEEN DECTECTED IN CLOSE PROXIMITY OF THE CAR. WAIT TO SEE SAFE DIRECTION(S) OF MOVEMENT");
                          System.out.println ("                                                  ");
                         //Albi: Is there a reason for the size of this loop? It feels like the elements in the arrays are constantly changing. 
                         //PLease perform a trace of a theoretical output.
                          for (int g=0; g<=2;g++)
                          {
                                 boolean commandDir=false;
                                   while (commandDir==false)
                                        {
                                             b1.userMovementDirectionFinder();
                                             movementDirection[j] =in.nextInt();
                                             int s1= movementDirection[j];
                                                    b1.getUserInput(s1);
                                                        if (g==2)
                                                                {
                                                                    commandDir=true;
                                                                        b1.TriggerAlarm();
                                                                        System.out.println("USER INPUT TIMEOUT ACTIVATED. AOM PROTOCOL TRIGGERED!!!");
                                                                }
                                                        else
                                                                {
                                                                    commandDir=false;
                                                                }
                                        }
                          }
                            b1.moveCar(); // Command to move car in specified direction
                            System.out.println("CAR IS MOVING IN ASSIGNED DIRECTION NOW");
                          }
                else 
                    {
                     System.out.println("NO OBSTACLE DETECTED IN CLOSE PROXIMITY TO THE CAR.");
                     System.out.println ("                                                  ");
                    }    
        }
    }
}
