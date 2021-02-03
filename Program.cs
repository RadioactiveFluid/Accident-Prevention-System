using System;
using System.IO.Ports;
using System.Threading;
namespace Test
{ 

    class Program
    {
        public static SerialPort port;
        //Author: Albi Vata
        static void Main(string[] args)
        {
            Console.WriteLine("Press ESC to stop");
            while (!(Console.ReadKey(true).Key == ConsoleKey.Escape))
            {
                if (port == null)
                {
                    port = new SerialPort("COM3", 9600);//for my computer. Check the COM port for your computer.
                    try
                    {
                        port.Open();
                    }
                    catch
                    {

                    }
                }
                if (Console.ReadKey(true).Key == ConsoleKey.A)
                {
                    port.Write("a");
                    Console.WriteLine("a");
                }
                else if (Console.ReadKey(true).Key == ConsoleKey.B)
                {
                    port.Write("b");
                    Console.WriteLine("b");
                }
            }
            /*
            bool moveForward=false;
            
            while (!( Console.ReadKey(true).Key == ConsoleKey.Escape))
            {
                //move forward
                if (Console.ReadKey(true).Key == ConsoleKey.NumPad1 && moveForward == true)
                {
                    //stop the car
                    Console.WriteLine("STOP");
                    moveForward = false;
                }
                else if (Console.ReadKey(true).Key == ConsoleKey.NumPad1 && moveForward != true)
                {
                    //start moving forward
                    Console.WriteLine("FORWARD");
                    moveForward = true;
                }

                //left turn
                if (Console.ReadKey(true).Key == ConsoleKey.NumPad2 && moveForward == true)
                {
                    //car is moving, but user wants to turn.
                    //stop the car and turn.
                    moveForward = false;
                    Console.WriteLine("STOP");
                    Console.WriteLine("TURN LEFT");
                }
                else if(Console.ReadKey(true).Key == ConsoleKey.NumPad2 && moveForward != true)
                {
                    //car is already stopped.
                    Console.WriteLine("TURN LEFT");
                }

                //right turn
                if (Console.ReadKey(true).Key == ConsoleKey.NumPad3 && moveForward == true)
                {
                    //car is moving, but user wants to turn.
                    //stop the car and turn.
                    moveForward = false;
                    Console.WriteLine("STOP");
                    Console.WriteLine("TURN RIGHT");
                }
                else if (Console.ReadKey(true).Key == ConsoleKey.NumPad3 && moveForward != true)
                {
                    //car is already stopped.
                    Console.WriteLine("TURN RIGHT");
                }


            }*/
            
        }
    }
}
