/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



// 4134 564 234 123 
// 



/**
 *
 * @author hp
 */
import java.util.Scanner;
public class APSSystemmain {
    public static void main(String args[]) {
        Double[] distanceValues = new Double[4];// Array of distance values*******
        int user_Comd_movt_Dir []= new int [4];
        
        Scanner in= new Scanner(System.in);
//        System.out.println("INPUT 0 TO ACTIVATE THE ACCIDENT PREVENTION SYSTEM:");
//        System.out.println ("                                                  ");
//        int a1= in.nextInt(); 
        int a1 = 0;
        APSSystem b1= new APSSystem(a1);
        System.out.println ("");
        b1.TriggerAlarm();
        
//        // 4134 564 234 123 
       
//        // ["4134", "564", "234" "123"]

//       String[] inputNum = input.split(" ");
//        double[] durations = new double[inputNum.length];
//        for(int i=0; i < durations.length; i++)
//            {
//                durations[i] = Double.parseDouble(inputNum[i]);
//            }
//                  
        System.out.print("INPUT PULSE DURATION (IN MICROSECONDS) FOR THE SENSORS 1,2,3,4 (SEPERATED BY COMMAS)");
         String input = in.nextLine();
         String[] inputNum = input.split(",");
           double[] durations = new double[inputNum.length];
        //Albi: Make the constructor fill in the durations instead of the main method if you continue with the array approach.
        for(int i=0; i < durations.length; i++)
            {
                durations[i] = Double.parseDouble(inputNum[i]);
                System.out.println ("");
    //            durationValues [i] = in.nextDouble();
    //            double a2= durationValues[i];      
            }
        

//                 double[] durationValues = {1500.0, 1200.0, 1600.0, 800.0};
//                int[] movementDirection = {1, 2, 3, 4}; //Array grade********

        //Albi: You can adjust the variables in the for loop condition so that you don't need to have a j+1.
        //Albi: This for loop feels messy. What's happening is that it's going to show each distance, so tracing the code can be difficult.
        for (int j=0; j<=3;j++)
            {
                    distanceValues[j]=b1.SensorDistance(durations[j]);
                    System.out.printf("DISTANCE FROM OBSTACLE FOR SENSOR %d is %f cm \n", j+1, distanceValues[j]);
                    System.out.println ("");
                    b1.getUserInput(j+1);
            }
                     //Albi: So at this point, is the car already moving, or has it encountered an obstacle. Is this meant to handle movement?
                     //Unclear, so perhaps rethink this portion. Assume that the system already encountered an obstacle in the front sensor.
                     //I'm making that assumption because that's what we decided was going to be the sensor that enters the APS.
                     //Unless I'm wrong.
                     System.out.println("CHECKING TO SEE WHICH DIRECTION IS SAFE TO MOVE IN. PLEASE WAIT");
                     System.out.println ("");
                     
//        for (int k=0; k<=3;k++)
//            {
                       if ((b1.isUnsafeDir()== true)){     
                          System.out.println("OBSTACLE HAS BEEN DECTECTED IN CLOSE PROXIMITY OF THE CAR. WAIT TO SEE SAFE DIRECTION(S) OF MOVEMENT");
                          System.out.println ("");
                          
//                          for (int g=0; g<=2;g++)
//                          {
//                                boolean commandDir=false;
//                                   while (commandDir==false)
//                                        {
                                             b1.userMovementDirectionFinder(durations);
                                             movementDirection[j] =in.nextInt();
                                             //Active AOM Protocol 
//                                             
//                                            if (g==2)
//                                                    {
//                                                        commandDir=true;
//                                                            b1.TriggerAlarm();
//                                                            System.out.println("USER INPUT TIMEOUT ACTIVATED. AOM PROTOCOL TRIGGERED!!!");
//                                                    }
//                                            else
//                                                    {
//                                                        commandDir=false;
//                                                    }
                                        }
                          }
                            b1.moveCar(); // Command to move car in specified direction
                            System.out.println("CAR IS MOVING IN ASSIGNED DIRECTION NOW");
                          }
                else 
                    {
                     System.out.println("NO OBSTACLE DETECTED IN CLOSE PROXIMITY TO THE CAR.");
                     System.out.println ("");
                    }    
        }
    }
}
