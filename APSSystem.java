/*
 * Software to implement the Accident Prevention System 
 * Software Architects: Hashir Saifullah Syed & Matthew Santiago 
 */

/**
 *
 * @author hp
 */
import java.util.Arrays;
import java.util.Scanner;

public class APSSystem
{
    public double FrontSensor_1;
    public double BackSensor_2;
    public double LeftSensor_3;
    public double RightSensor_4;
    
    public double distance1;
    public double distance2;
  
    public boolean[] expectedNextDirection;
    
    public APSSystem (int usr_APS_Activ_Command)
    {
        expectedNextDirection = new boolean[4];
        if (usr_APS_Activ_Command==0)
        {
            System.out.println("THE ACCIDENT PREVENTION SYSTEM HAS BEEN ACTIVATED.");
        }
        else 
        {
            throw new NumberFormatException();
        }
    }

    public double SensorDistance (double duration_User_IP)
    {
        distance1 = (duration_User_IP/2)/29.1;
        return distance1;
    }

    public void getUserInput(int sensor_number)
    {
        if (sensor_number ==1)
        {
            distance2= distance1;
            FrontSensor_1 = distance2;
        }

        else if (sensor_number==2)
        {
            distance2=distance1;
            BackSensor_2=distance2;
        }

        else if (sensor_number==3)
        {
            distance2=distance1;
            LeftSensor_3=distance2;
        }
            
        else if (sensor_number==4)
        {
            distance2=distance1;
            RightSensor_4=distance2;
        }
        
        else 
        {
            System.out.print ("Not a Valid Input");
            throw new NumberFormatException();
        } 
    }
                
    public void userMovementDirectionFinder(double[] distVal)
    {   
       this.FrontSensor_1=distVal[0];
       this.BackSensor_2=distVal[1];
       this.LeftSensor_3=distVal[2];
       this.RightSensor_4=distVal[3];

       if(FrontSensor_1<=20)// if distance is less than 20 Cm 
       {
           if ((BackSensor_2<=20) || ((LeftSensor_3 <=20)) || (RightSensor_4 <=20))
           { 
               if ((BackSensor_2 <=20) && ((LeftSensor_3 >=20)) && (RightSensor_4 >=20))
               {
                   System.out.println("Please Instruct To Move Vehicle (ENTER 3=Left, 4=Right):"); //Prompt User for Input
                   System.out.println("");
                   expectedNextDirection[2] = true;
                   expectedNextDirection[3] = true;
   //                                         
               }

               if ((LeftSensor_3 <=20) && (BackSensor_2 >=20) && (RightSensor_4 >=20))
               {
                   System.out.println("Please Instruct To Move Vehicle (ENTER 2=Back, 4=Right):"); //Prompt User for Input
                   expectedNextDirection[1] = true;
                   expectedNextDirection[3] = true;
               }

               if ((RightSensor_4 <=20) && (LeftSensor_3 >=20) && (BackSensor_2 >=20))
               {
                    System.out.println("Please Instruct To Move Vehicle (ENTER 2=Back, 3=Left):"); //Prompt User for Input
                    expectedNextDirection[1] = true;
                    expectedNextDirection[2] = true;
               }

               if ((BackSensor_2 <=20) && (LeftSensor_3 <=20) && (RightSensor_4 >=20))
               {
                   System.out.println("Please Instruct To Move Vehicle (ENTER 4=Right):"); //Prompt User for Input
                   expectedNextDirection[3] = true;
               }

               if ((BackSensor_2 <=20) && (RightSensor_4 <=20)&& (LeftSensor_3 >=20))
               {
                    System.out.println("Please Instruct To Move Vehicle (ENTER 3=Left):"); //Prompt User for Input
                    expectedNextDirection[2] = true;
               }

               else if ((LeftSensor_3 <=20) && (RightSensor_4 <=20) && (BackSensor_2 >=20))
               {
                   System.out.println ("Please Instruct To Move Vehicle (ENTER 2=Back):"); //Prompt User for Input
                   expectedNextDirection[1] = true;
               }
           }
           
           else
           {
                System.out.println("Please Instruct To Move Vehicle (ENTER 2= Back, 3=Left, 4=Right):"); //Prompt User for Input
                expectedNextDirection[1] = true;
                expectedNextDirection[2] = true;
                expectedNextDirection[3] = true;
           }   
       }

       else 
       {
              System.out.println("NO OBSTACLE DETECTED, FALSE ALARM!!!");
       }  
    }

    public void AOM_Protocol(double[] distVal)
    {
        this.FrontSensor_1=distVal[0];
        this.BackSensor_2=distVal[1];
        this.LeftSensor_3=distVal[2];
        this.RightSensor_4=distVal[3];

        if(FrontSensor_1<=20)// if distance is less than 20 Cm 
        {
            if ((BackSensor_2<=20) || ((LeftSensor_3 <=20)) || (RightSensor_4 <=20))
            { 
                if ((BackSensor_2 <=20) && ((LeftSensor_3 >=20)) && (RightSensor_4 >=20))
                {
                   System.out.println("Moving left..."); //RC Car automatically moves in that direction
                }

                if ((LeftSensor_3 <=20) && (BackSensor_2 >=20) && (RightSensor_4 >=20))
                {
                    System.out.println("Moving right..."); //RC Car automatically moves in that direction
                }

                if ((RightSensor_4 <=20) && (LeftSensor_3 >=20) && (BackSensor_2 >=20))
                {
                    System.out.println("Moving left..."); //RC Car automatically moves in that direction
                }

                if ((BackSensor_2 <=20) && (LeftSensor_3 <=20) && (RightSensor_4 >=20))
                {
                    System.out.println("Moving right..."); //RC Car automatically moves in that direction
                }

                if ((BackSensor_2 <=20) && (RightSensor_4 <=20)&& (LeftSensor_3 >=20))
                {
                    System.out.println("Moving left..."); //RC Car automatically moves in that direction
                }

                else if ((LeftSensor_3 <=20) && (RightSensor_4 <=20) && (BackSensor_2 >=20))
                {
                    System.out.println ("Moving back..."); //RC Car automatically moves in that direction
                }
            }

            else
            {
                System.out.println("Moving left..."); //RC Car automatically moves in that direction
            }
        }

        else
        {
            System.out.println("NO OBSTACLE DETECTED, FALSE ALARM!!!");
        }
    }

    public int moveCar()
    {
        return 11111;
    }
    
            
    public String TriggerAlarm()
    {
        return "BEEP....BEEP...BEEP" ;
    }
   

                               /* public boolean CheckRange(int value) {
   
                                    if ((value < 1) || (value > 4))
                                        {
                                            throw new IllegalArgumentException("value is out of range for anumber");
                                        }
                                        else 
                                        {
                                            this.UserInputSensor = value;
                                            return true;
                                        } **/
    

}

         
            