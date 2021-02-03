using System;
using System.Collections;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Web;
using System.Threading;
using System.Diagnostics;
using System.Runtime.InteropServices;
using System.IO.Ports;

class DataProcess
{

	private int CheckThreshold(string input, double min, double max)
	{
		double converted = Double.Parse(input.Trim()); //parse string into double value
		if (converted >= min && converted <= max) //if double value is in between minimum and maximum (inclusive)
			return 1; //true
		return 0; //false
	}

	static public void Main(String[] args)
	{
		//if (!File.Exists("EEGLogger.csv"))
		//throw exception
		DataProcess process = new DataProcess();
		string[] input = File.ReadAllLines("EEGLogger1.csv"); //converts .csv file into array
		StreamWriter output = new StreamWriter("output.csv"); //create output.csv file
		for (int i = 1; i < input.Length; i++) //loop through EEGLogger1
		{
			var line = input[i];
			var parts = line.Split(','); //split line

			if (parts.Length >= 8)
			{
				for (int j = 3; j <= 7; j++) //cycle through sensor values in .csv file
				{
					if (j == 3)
						output.Write(process.CheckThreshold(parts[j], 4250, 4270).ToString()); //process data from AF3 column
					else if(j == 6)
						output.Write(process.CheckThreshold(parts[j], 4180, 4190).ToString()); //process data from T8 column
					else if(j == 7)
						output.Write(process.CheckThreshold(parts[j], 4200, 4300).ToString()); //process data from AF4 column
					else
						output.Write(0); //sets entire T7 and Pz column (unused sensors) to 0
				}
				output.WriteLine();
			}
		}
		
	}
}
