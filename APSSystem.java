/*
 * Software to implement the Accident Prevention System 
 * Software Architects: Hashir Saifullah Syed & Matthew Santiago 
 */

/**
 *
 * @author hp
 */
import java.util.Scanner;
public class APSSystem {
    public double FrontSensor_1;
    public double BackSensor_2;
    public double LeftSensor_3;
    public double RightSensor_4;
    
    public double FrontEcho_1;
    public double BackEcho_2;
    public double LeftEcho_3;
    public double RightEcho_4;
    
    public double duration;
    public double distance1;
    public double distance2;
    public int UserInputSensor;
   
    public int Count;
    public String Front;
    public String Back;
    public String Left;
    public String Right;
    
    public APSSystem (int a)
    {
        if (a==0)
            {
                System.out.println("THE ACCIDENT PREVENTION SYSTEM HAS BEEN ACTIVATED.");
            }
        else 
            {
                throw new NumberFormatException();
             }
    }
   /** public double SensorPulseDuration(double x){
        duration= x;
        return duration;
    } */
    public double SensorDistance (double a2)
        {
            distance1 = (a2/2)/29.1;
            return distance1;
        }
    
     public void getUserInput(int SX)
        {
    
                    if (SX ==1)
                                {
                                    distance2= distance1;
                                    FrontSensor_1 = distance2;
                                }
                                    
                           else if (SX==2)
                                    {
                                        distance2=distance1;
                                        BackSensor_2=distance2;
                                    }
                           else if (SX==3)
                                    {
                                        distance2=distance1;
                                        LeftSensor_3=distance2;
                                    }
                            else if (SX==4)
                                    {
                                        distance2=distance1;
                                        RightSensor_4=distance2;
                                    }
                                else 
                                    {
                                        System.out.print ("Not a Valid Input");
                                        throw new NumberFormatException();
                                    } 

                                           /** double rx_byte = SX; // gets the character input from user
                                                          boolean timeout = false;
                                                                while (timeout == false)
                                                                    {
                                                                            if (Count >= 2)
                                                                                {
                                                                                   timeout = true;
                                                                                }
                                                                            else 
                                                                            {
                                                                                Count++;
                                                                            }
                                                                    } **/
            }
                
     public boolean isUnsafeDir()
        {
                return (FrontSensor_1<=20) || (BackSensor_2<=20) || ((LeftSensor_3 <=20)) || (RightSensor_4 <=20);
        }
     
     public void userMovementDirectionFinder()
        {   
             if(FrontSensor_1<=20)// if distance is less than 20 Cm 
                {
                        if ((BackSensor_2<=20) || ((LeftSensor_3 <=20)) || (RightSensor_4 <=20))
                            { 
                                        if ((BackSensor_2 <=20) && ((LeftSensor_3 >=20)) && (RightSensor_4 >=20))
                                           {
                                         System.out.println("Please Instruct To Move Vehicle (ENTER 3=Left, 4=Right):"); //Prompt User for Input
                                           }
                                        if ((LeftSensor_3 <=20) && (BackSensor_2 >=20) && (RightSensor_4 >=20))
                                           {
                                         System.out.println("Please Instruct To Move Vehicle (ENTER 2=Back, 4=Right):"); //Prompt User for Input
                                           }
                                        if ((RightSensor_4 <=20) && (LeftSensor_3 >=20) && (BackSensor_2 >=20))
                                           {
                                         System.out.println("Please Instruct To Move Vehicle (ENTER 2=Back, 3=Left):"); //Prompt User for Input
                                           }
                                        if ((BackSensor_2 <=20) && (LeftSensor_3 <=20) && (RightSensor_4 >=20))
                                           {
                                         System.out.println("Please Instruct To Move Vehicle (ENTER 4=Right):"); //Prompt User for Input
                                           }
                                        if ((BackSensor_2 <=20) && (RightSensor_4 <=20)&& (LeftSensor_3 >=20))
                                           {
                                         System.out.println("Please Instruct To Move Vehicle (ENTER 3=Left):"); //Prompt User for Input
                                           }
                                         else if ((LeftSensor_3 <=20) && (RightSensor_4 <=20) && (BackSensor_2 >=20))
                                           {
                                         System.out.println ("Please Instruct To Move Vehicle (ENTER 2=Back):"); //Prompt User for Input
                                           }
                                                     else
                                                            {
                                                               System.out.println("Please Instruct To Move Vehicle (ENTER 1= Back, 2=Left, 3=Right):"); //Prompt User for Input
                                                            }
                                        
                            }
                }
        }
                                  public String TriggerAlarm()
                                        {
                                             return "BEEP....BEEP...BEEP" ;
                                        }
   
                                public void AOM_Protocol(int b)
                                        {
                                               /**  CALL THE AOM Protocol */
                                        }
                                
                                public int moveCar()
                                        {
                                                return 11111;
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



         
            