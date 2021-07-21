using System;
using System.Threading;
using System.IO;
using System.IO.Ports;
using System.Collections;
using System.Text;
using CortexAccess;
using Newtonsoft.Json.Linq;
using System.Collections.Generic;

namespace EEGLogger
{
    class Program
    {
        const string OutFilePath = @"EEGLogger.csv";
        const string licenseID = "62b8c487-bbd8-4989-ae17-c61edd3ddfa9";
        private static FileStream OutFileStream;
        public static SerialPort port;
        private static Int64 current;
        private static int timer;
        static void Main(string[] args)
        {
            Console.WriteLine("EEG LOGGER");
            Console.WriteLine("Please wear Headset with good signal!!!");

            // Delete Output file if existed
            if (File.Exists(OutFilePath))
            {
                File.Delete(OutFilePath);
            }
            OutFileStream = new FileStream(OutFilePath, FileMode.Append, FileAccess.Write);


            DataStreamExample dse = new DataStreamExample();
            dse.AddStreams("eeg");                          // You can add more streams to subscribe multiple streams
            dse.OnSubscribed += SubscribedOK;
            dse.OnEEGDataReceived += OnEEGDataReceived;

            // Need a valid license key and activeSession when subscribe eeg data
            dse.Start(licenseID, true);

            Console.WriteLine("Press Esc to flush data to file and exit");
            while (Console.ReadKey().Key != ConsoleKey.Escape) { }

            // Unsubcribe stream
            dse.UnSubscribe();
            Thread.Sleep(5000);

            // Close Session
            dse.CloseSession();
            Thread.Sleep(5000);
            // Close Out Stream
            OutFileStream.Dispose();
        }

        private static void SubscribedOK(object sender, Dictionary<string, JArray> e)
        {
            foreach (string key in e.Keys)
            {
                if (key == "eeg")
                {
                    // print header
                    ArrayList header = e[key].ToObject<ArrayList>();
                    //add timeStamp to header
                    header.Insert(0, "Timestamp");
                    WriteDataToFile(header);
                    
                }
            }
        }

        // Write Header and Data to File
        private static void WriteDataToFile(ArrayList data)
        {
            //Configure XBEE Dongle to COM Port
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
            //interpret inputs. Needs to be surrounded in a try-catch.
            try
            {
                //The range needs to be around the peak value of each impulse. 
                //In that way, we won't receive multiple inputs
                if ((double)data[4] > 5450&&(double)data[4]<5500)//F7 Sensor
                {
                    //best done when blinking left
                    port.Write("l");
                    //Trace
                    Console.WriteLine("l" + data[4]);
                }
                else if ((double)data[15] > 5450 && (double)data[15] < 5500)//F8 Sensor
                {
                    //best done when blinking right
                    port.Write("r");
                    //Trace
                    Console.WriteLine("r"+data[15]);
                }
                else if((double)data[7] > 5450 && (double)data[7] < 5500)//T7 Sensor
                {
                    {
                        //best done when biting down right side of mouth
                        port.Write("f");
                        //Trace
                        Console.WriteLine("f"+data[7]);
                    }
                }
                else if ((double)data[12] > 5550 && (double)data[12] < 5600)//T8 Sensor
                {
                    //best done when biting down right side of mouth
                    port.Write("s");
                    //Trace
                    Console.WriteLine("s" + data[12]);
                }
            }
            catch
            {

            }
            //default of EEGLogger.Process
            int i = 0;
            for (; i < data.Count - 1; i++)
            {
                byte[] val = Encoding.UTF8.GetBytes(data[i].ToString() + ", ");

                if (OutFileStream != null)
                    OutFileStream.Write(val, 0, val.Length);
                else
                    break;
            }
            // Last element
            byte[] lastVal = Encoding.UTF8.GetBytes(data[i].ToString() + "\n");
            if (OutFileStream != null)
                OutFileStream.Write(lastVal, 0, lastVal.Length);
            
        }

        private static void OnEEGDataReceived(object sender, ArrayList eegData)
        {
            WriteDataToFile(eegData);
            
        }

    }
}

