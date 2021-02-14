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

    /////
    public String scenario;
    enum userInput{
      LEFT,
      RIGHT,
      BACK
    }
    ////

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
                    //Albi:Is distance2 a necessary variable? I don't see it called anywhere else,
                    //so perhaps remove it to simplify the code. Just set each sensor to distance1.
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

     public void userMovementDirectionFinder(double[] durations)
        {
            this.FrontSensor_1=durations[0];
            this.BackSensor_2=durations[1];
            this.LeftSensor_3=durations[2];
            this.RightSensor_4=durations[3];
            //Albi: Just to clarify, there should only be user input when the right and left sensors are open while the front and back sensors are blocked.
            //We discussed this in a meeting.
             if(FrontSensor_1<=20)// if distance is less than 20 Cm
                {
                        if ((BackSensor_2<=20) || ((LeftSensor_3 <=20)) || (RightSensor_4 <=20))
                            {
                                        if ((BackSensor_2 <=20) && ((LeftSensor_3 >=20)) && (RightSensor_4 >=20))
                                           {
                                         System.out.println("Please Instruct To Move Vehicle (ENTER 3=Left, 4=Right):"); //Prompt User for Input
                                         scenario = LR;
                                           }
                                        if ((LeftSensor_3 <=20) && (BackSensor_2 >=20) && (RightSensor_4 >=20))
                                           {
                                         System.out.println("Please Instruct To Move Vehicle (ENTER 2=Back, 4=Right):"); //Prompt User for Input
                                         scenario = RB;
                                           }
                                        if ((RightSensor_4 <=20) && (LeftSensor_3 >=20) && (BackSensor_2 >=20))
                                           {
                                         System.out.println("Please Instruct To Move Vehicle (ENTER 2=Back, 3=Left):"); //Prompt User for Input
                                         scenario = LB;
                                           }
                                        if ((BackSensor_2 <=20) && (LeftSensor_3 <=20) && (RightSensor_4 >=20))
                                           {
                                         System.out.println("Please Instruct To Move Vehicle (ENTER 4=Right):"); //Prompt User for Input
                                         scenario = RR;
                                           }
                                        if ((BackSensor_2 <=20) && (RightSensor_4 <=20)&& (LeftSensor_3 >=20))
                                           {
                                         System.out.println("Please Instruct To Move Vehicle (ENTER 3=Left):"); //Prompt User for Input
                                         scenario = LL;
                                           }
                                         else if ((LeftSensor_3 <=20) && (RightSensor_4 <=20) && (BackSensor_2 >=20))
                                           {
                                         System.out.println ("Please Instruct To Move Vehicle (ENTER 2=Back):"); //Prompt User for Input
                                         scenario = BB;
                                           }
                                                     else
                                                            {
                                                               System.out.println("Please Instruct To Move Vehicle (ENTER 1= Back, 2=Left, 3=Right):"); //Prompt User for Input
                                                               scenario = LRB;
                                                            }

                            }
                }
        }                         //Albi: Nothing wrong with this, but I believe that the user should hear the buzzer when it's close to 20 cm.
                                  //then if the car is 20 cm away from an obstacle, just have the car move automatically.
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
