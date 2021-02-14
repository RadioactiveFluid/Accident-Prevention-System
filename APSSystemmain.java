/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hp
 */
import java.util.Arrays;
import java.util.Scanner;
public class APSSystemmain 
{

    public static boolean isDirectionInputValid(int inputDirection, boolean[] expectedDirections)
    {
        return inputDirection >= 1 && inputDirection <= 4 && expectedDirections[inputDirection-1];
    }
    
    public static void main(String args[]) 
    {
        double[] distanceValues = new double[4];// Array of distance values*******
        int selectedDirection = 0;
        
        Scanner in= new Scanner(System.in);
        int ACTIV_APS = 0;
        APSSystem APS_Object= new APSSystem(ACTIV_APS);
        System.out.println ("");
        APS_Object.TriggerAlarm();
        
        System.out.println("INPUT PULSE DURATION (IN MICROSECONDS) FOR THE SENSORS 1,2,3,4 (SEPERATED BY COMMAS):");
         String input = in.nextLine();
         String[] inputNum = input.split(",");
           double[] durations = new double[inputNum.length];
        
        for(int i=0; i < durations.length; i++)
        {
            durations[i] = Double.parseDouble(inputNum[i]);
            System.out.println ("");     
        }
        
//                 double[] durationValues = {1500.0, 1200.0, 1600.0, 800.0};
//                int[] movementDirection = {1, 2, 3, 4}; //Array grade********

        for (int j=0; j<=3;j++)
        {
            distanceValues[j]=APS_Object.SensorDistance(durations[j]);
            APS_Object.getUserInput(j+1);
            System.out.printf("DISTANCE FROM OBSTACLE FOR SENSOR %d is %f cm \n", j+1, distanceValues[j]);
            System.out.println ("");
        }
                
        System.out.println("CHECKING TO SEE WHICH DIRECTION IS SAFE TO MOVE IN. PLEASE WAIT");
        System.out.println ("");
                       
        if ((distanceValues[0]<=20.00)||(distanceValues[1]<=20.00)||(distanceValues[2]<=20.00)||(distanceValues[3]<=20.00))
        {   
            System.out.print("");
            System.out.println("OBSTACLE HAS BEEN DECTECTED IN CLOSE PROXIMITY OF THE CAR. WAIT TO SEE SAFE DIRECTION(S) OF MOVEMENT");
            System.out.print("");
            int tries = 0;
            
            while (tries < 3)
            {    
                System.out.println("");
                APS_Object.userMovementDirectionFinder(distanceValues);
                System.out.print("");
                System.out.println("PLEASE MAKE SURE TO SELECT FROM THE MOVEMENT COMMAND OPTIONS SPECIFIED ABOVE: ");
                int directionInput = in.nextInt();
                if(isDirectionInputValid(directionInput, APS_Object.expectedNextDirection))
                {
                    selectedDirection = directionInput;
                    Arrays.fill(APS_Object.expectedNextDirection, false); //To reset the array
                    System.out.println();
                    APS_Object.moveCar(); // Command to move car in specified direction
                    System.out.println("");
                    System.out.printf("CAR IS MOVING IN ASSIGNED DIRECTION OF SENSOR %d (KEY: FRONT=1, BACK=2, LEFT=3, RIGHT=4) " , selectedDirection);
                    break; //break from the if condition if the user if user input has been validated 
                }
                
                else
                {
                    System.out.print("");
                    System.out.printf("INVALID INPUT! YOU HAVE %d TRIES REMAINING", 2-tries);
                    System.out.print("");
                }
                
                tries++;
            }

            if(tries == 3)
            {
                System.out.println("");
                System.out.println("ACTIVATING AOM PROTOCOL");
                APS_Object.AOM_Protocol(distanceValues);
            }
        }
        
        else 
        {
            System.out.println("");
            System.out.println("NO OBSTACLE DETECTED IN CLOSE PROXIMITY TO THE CAR.");
            System.out.println ("");
        }
    }   
}
