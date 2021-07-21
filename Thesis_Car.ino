//motor pins
int motor1pin1 = 2;
int motor1pin2 = 3;
int motor2pin1 = 4;
int motor2pin2 = 5;

//Ultrasound pins. Trig pin from the front sensor triggers all of the other sensors
int trigPin1=7;
int echoPin1=6;
int echoPin2=9;
int echoPin3=10;
int echoPin4=11;

//buzzer pin
int piezzoPin=8;

//instance fields for sensor
long distance1, duration1, FrontSensor_1, BackSensor_2, LeftSensor_3, RightSensor_4;
int received = 0;//used for the Serial communication

long mil=millis();//current time in milliseconds
int sec=mil/1000;//current time in seconds

//booleans to check if the sensors are blocked (See dirFinder())
boolean rightBlocked, backBlocked, leftBlocked;

void setup() {
  // put your code here, to run once:
  Serial.begin(9600);
  //init motor pins
  pinMode(motor1pin1, OUTPUT);
  pinMode(motor1pin2, OUTPUT);
  pinMode(motor2pin1, OUTPUT);
  pinMode(motor2pin2, OUTPUT);

  //init trig pin
  pinMode(trigPin1, OUTPUT);

  //init echo pins
  pinMode(echoPin1, INPUT);
  pinMode(echoPin2, INPUT);
  pinMode(echoPin3, INPUT);
  pinMode(echoPin4, INPUT);
  
  //init buzzer pin
  pinMode(piezzoPin,OUTPUT);
}

void loop()//repeat forever
{
  // put your main code here, to run repeatedly:
  
  //check the front sensor reading
  FrontSensor_1=SonarSensor1(trigPin1,echoPin1);  
  //handle APS
  APS();
  //hard delay the system. This must happen, or else the car will not receive inputs from the headset.
  delay(20);//minimum delay possible
   
  //receive inputs and move
  handleMovement();
}
void handleMovement()
{
    if(Serial.available()>0)
    {
      //it will update when it receives a new input
      received=Serial.read();
      if(received=='f')//receive forward input
      {
              //move forward
              digitalWrite(motor1pin1, HIGH);
              digitalWrite(motor1pin2, LOW);
              digitalWrite(motor2pin1, HIGH);
              digitalWrite(motor2pin2, LOW);
      }
      else if(received=='l')//receive left input 
      {
              //turn left
              digitalWrite(motor1pin1, LOW);
              digitalWrite(motor1pin2, LOW);
              digitalWrite(motor2pin1, HIGH);
              digitalWrite(motor2pin2, LOW);
              delay(700);//turn left for 0.7 seconds
              //immediately move forward; completes 90 degree left turn
              digitalWrite(motor1pin1, HIGH);
              digitalWrite(motor1pin2, LOW);
              digitalWrite(motor2pin1, HIGH);
              digitalWrite(motor2pin2, LOW); 
      }
      else if(received=='r')//receive right input
      {
              //turn right
              digitalWrite(motor1pin1, HIGH);
              digitalWrite(motor1pin2, LOW);
              digitalWrite(motor2pin1, LOW);
              digitalWrite(motor2pin2, LOW);
              delay(700);//turn right for 0.7 seconds
              //immediately move forward; completes 90 degree right turn
              digitalWrite(motor1pin1, HIGH);
              digitalWrite(motor1pin2, LOW);
              digitalWrite(motor2pin1, HIGH);
              digitalWrite(motor2pin2, LOW);
      }
      else if(received=='s')//receive stop input
      {
              //stop the car
              digitalWrite(motor1pin1, LOW);
              digitalWrite(motor1pin2, LOW);
              digitalWrite(motor2pin1, LOW);
              digitalWrite(motor2pin2, LOW); 
      }
    }
}
//determining an open direction
void dirFinder(){
   for(int i=1;i<=3;i++)//go through the other 3 sensors one at a time
   {
       if(i==1)//check back sensor
       {
          BackSensor_2=SonarSensor1(trigPin1,echoPin2);//read distance of back sensor
          if(BackSensor_2<=20)//back sensor is blocked
              backBlocked=true;
          else
              backBlocked=false;
       }
       else if(i==2)//check left sensor
       {
          LeftSensor_3=SonarSensor1(trigPin1,echoPin3);//read distance of left sensor
          if(LeftSensor_3<=20)//back sensor is blocked
              leftBlocked=true;
          else
              leftBlocked=false;
       }
       else if(i==3)//check right sensor
       {
          RightSensor_4=SonarSensor1(trigPin1,echoPin4);//read distance of right sensor
          if(RightSensor_4<=20)//right sensor is blocked
              rightBlocked=true;
          else
              rightBlocked=false;
       }
   }
}
//handle the front obstacle automatically.
void APS()
{
    if(FrontSensor_1>30&&FrontSensor_1<=60&&sec%2==0)//within buzzer range, turn on tone
    {
      tone(piezzoPin, 220);//play a tone
    }
    else if(FrontSensor_1>30&&FrontSensor_1<=60&&sec%2==1)//within buzzer range, turn off tone
    {
      noTone(piezzoPin);//turn off the buzzer
    }
    else if(FrontSensor_1<=30)//frontSensor reads a value less than 30 cm.
    {
        noTone(piezzoPin);//turn off the buzzer
        //turn off all of the motors regardless of how they were moving
        digitalWrite(motor1pin1, LOW);
        digitalWrite(motor1pin2, LOW);
        digitalWrite(motor2pin1, LOW);
        digitalWrite(motor2pin2, LOW);
        delay(500);
        dirFinder();//find the directions that are blocked
        if(backBlocked==true)//back is blocked
        {
            if(rightBlocked==true)//right is blocked
            {
                //turn left
                digitalWrite(motor1pin1, LOW);
                digitalWrite(motor1pin2, LOW);
                digitalWrite(motor2pin1, HIGH);
                digitalWrite(motor2pin2, LOW);
                delay(660);//turn 90 degrees
                //stop motors
                digitalWrite(motor1pin1, LOW);
                digitalWrite(motor1pin2, LOW);
                digitalWrite(motor2pin1, LOW);
                digitalWrite(motor2pin2, LOW);
            }
            else if(leftBlocked==true)//right is not blocked, check left
            {
                //turn right
                digitalWrite(motor1pin1, HIGH);
                digitalWrite(motor1pin2, LOW);
                digitalWrite(motor2pin1, LOW);
                digitalWrite(motor2pin2, LOW);
                delay(660);//completes 90 degree turn
                //stop motors
                digitalWrite(motor1pin1, LOW);
                digitalWrite(motor1pin2, LOW);
                digitalWrite(motor2pin1, LOW);
                digitalWrite(motor2pin2, LOW);
            }
            else//both left and right are available
            {
                handleMovement();//the user decides to move
            }
        }
        else//back is open
        {
            //move back
            digitalWrite(motor1pin1, LOW);
            digitalWrite(motor1pin2, HIGH);
            digitalWrite(motor2pin1, LOW);
            digitalWrite(motor2pin2, HIGH);
            delay(350);
            //stop motors
            digitalWrite(motor1pin1, LOW);
            digitalWrite(motor1pin2, LOW);
            digitalWrite(motor2pin1, LOW);
            digitalWrite(motor2pin2, LOW);
        }
    }
    else//done to make sure that the buzzer turns off
    {
      noTone(piezzoPin);//turn off the buzzer  
    }
}
// SonarSensor function used to generate and read the ultrasonic wave & it takes the trigPIN and the echoPIN
int SonarSensor1(int trigPinSensor,int echoPinSensor) 
{
     //START SonarSensor FUNCTION
     //generate the ultrasonic wave 
     digitalWrite(trigPinSensor, LOW);// put trigpin LOW 
     delayMicroseconds(5);// wait 2 microseconds
     digitalWrite(trigPinSensor, HIGH);// switch trigpin HIGH
     delayMicroseconds(10); // wait 10 microseconds
     digitalWrite(trigPinSensor, LOW);// turn it LOW again

     //read the distance
     duration1 = pulseIn(echoPinSensor, HIGH);//pulseIn funtion will return the time on how much the configured pin remain the level HIGH or LOW; in this case it will return how much time echoPinSensor stay HIGH
     distance1=(duration1/2) / 29.1; // first we have to divide the duration by two to get the distance
     
     //Trace statement: displays the distance in the Serial Monitor                 
     Serial.print("distance1: ");
     Serial.print(distance1);
     Serial.println(" cm");
     return distance1;
}// END SonarSensor FUNCTION

                    
