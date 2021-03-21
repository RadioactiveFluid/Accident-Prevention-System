int motor1pin1 = 2;
int motor1pin2 = 3;

int motor2pin1 = 4;
int motor2pin2 = 5;

int trigPin1=7;
int echoPin1=6;

int echoPin2=9;

int echoPin3=10;

int echoPin4=11;

//instance fields
long distance1, duration1, FrontSensor_1, BackSensor_2, LeftSensor_3, RightSensor_4;
int received = 0;
//Attempts when APS allows for
int tries=3;
//Switch attributes to start/stop the car
boolean forward=true;
boolean left=true;
boolean right=true;

boolean rightBlocked, backBlocked, leftBlocked;
void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  pinMode(motor1pin1, OUTPUT);
  pinMode(motor1pin2, OUTPUT);
  pinMode(motor2pin1, OUTPUT);
  pinMode(motor2pin2, OUTPUT);

  //ultrasound
  pinMode(trigPin1, OUTPUT);
  pinMode(echoPin1, INPUT);

  //Sensor 2
  pinMode(echoPin2, INPUT);
  //Sensor 3
  pinMode(echoPin3, INPUT);
  //Sensor 4
  pinMode(echoPin4, INPUT);

}

void loop() {
  // put your main code here, to run repeatedly:
  
  //Update the distances of each sensor
  FrontSensor_1=SonarSensor1(trigPin1,echoPin1);
  delay(400);//delaymilliseconds() instead. remember that delay stops everything.
  //handle APS
  APS();
  
  //receive inputs and move
  handleMovement();
 
  
}

void handleMovement()
{
    if(Serial.available()>0)
    {
      //it will update when it receives a new input
      received=Serial.read();
      if(received=='c')
      {
          if(forward==true)
          {
              //move forward
              digitalWrite(motor1pin1, HIGH);
              digitalWrite(motor1pin2, LOW);
              digitalWrite(motor2pin1, LOW);
              digitalWrite(motor2pin2, HIGH);
              forward=false;
          }
          else
          {
             digitalWrite(motor1pin1, LOW);
              digitalWrite(motor1pin2, LOW);
              digitalWrite(motor2pin1, LOW);
              digitalWrite(motor2pin2, LOW);
              forward=true;
        
          }
      }
      else if(received=='d')
      {
        digitalWrite(motor1pin1, LOW);
        digitalWrite(motor1pin2, LOW);
        digitalWrite(motor2pin1, LOW);
        digitalWrite(motor2pin2, LOW);
      }
      else if(received=='a')
      {
          if(left==true)
          {
              //turn left
              digitalWrite(motor1pin1, HIGH);
              digitalWrite(motor1pin2, LOW);
              digitalWrite(motor2pin1, HIGH);
              digitalWrite(motor2pin2, LOW);
              delay(2000);
              digitalWrite(motor1pin1, LOW);
              digitalWrite(motor1pin2, LOW);
              digitalWrite(motor2pin1, LOW);
              digitalWrite(motor2pin2, LOW);
          }
      }
      else if(received=='b')
      {
          if(right==true)
          {
              //turn right
              digitalWrite(motor1pin1, LOW);
              digitalWrite(motor1pin2, HIGH);
              digitalWrite(motor2pin1, LOW);
              digitalWrite(motor2pin2, HIGH);
              delay(2000);
              digitalWrite(motor1pin1, LOW);
              digitalWrite(motor1pin2, LOW);
              digitalWrite(motor2pin1, LOW);
              digitalWrite(motor2pin2, LOW);
          }
      } 
      
    }
}
//determining an open direction
void dirFinder(){
   //logic can be cleaner. It should do the action indicated.
   for(int i=1;i<=3;i++) 
   {
       if(i==1)
       {
          BackSensor_2=SonarSensor1(trigPin1,echoPin2);
          if(BackSensor_2<=20)
              rightBlocked=true;
          else
              rightBlocked=false;
       }
       else if(i==2)
       {
          LeftSensor_3=SonarSensor1(trigPin1,echoPin3);//this can be front sensor
          if(LeftSensor_3<=20)
              backBlocked=true;
          else
              backBlocked=false;
       }
       else if(i==3)
       {
          RightSensor_4=SonarSensor1(trigPin1,echoPin4);
          if(RightSensor_4<=20)
              leftBlocked=true;
          else
              leftBlocked=false;
       }
   }
   
}
//handle the front obstacle automatically.
void APS()
{
    if(FrontSensor_1<=20)//frontSensor reads a value less than 20 cm.
    {
        //turn off all of the motors regardless of how they were moving
        digitalWrite(motor1pin1, LOW);
        digitalWrite(motor1pin2, LOW);
        digitalWrite(motor2pin1, LOW);
        digitalWrite(motor2pin2, LOW);
        delay(500);
        //Serial.println("OBSTACLE HAS BEEN DECTECTED IN CLOSE PROXIMITY OF THE CAR. WAIT TO SEE SAFE DIRECTION(S) OF MOVEMENT");
        dirFinder();//find the directions that are block
        if(backBlocked==true)
        {
            if(rightBlocked==true)
            {
                //turn left
                Serial.println("Left");
                //turn left
              //digitalWrite(motor1pin1, HIGH);
              //digitalWrite(motor1pin2, LOW);
              digitalWrite(motor2pin1, HIGH);
              digitalWrite(motor2pin2, LOW);
                delay(1000);
                //stop motors
                digitalWrite(motor1pin1, LOW);
                digitalWrite(motor1pin2, LOW);
                digitalWrite(motor2pin1, LOW);
                digitalWrite(motor2pin2, LOW);
            }
            else if(leftBlocked==true)
            {
                //turn right
                Serial.println("Right");
                //turn right
                digitalWrite(motor1pin1, LOW);
                digitalWrite(motor1pin2, HIGH);
              //digitalWrite(motor2pin1, LOW);
              //digitalWrite(motor2pin2, HIGH);
                delay(1000);
                //stop motors
                digitalWrite(motor1pin1, LOW);
                digitalWrite(motor1pin2, LOW);
                digitalWrite(motor2pin1, LOW);
                digitalWrite(motor2pin2, LOW);
            }
            else
            {
                //choice. Should include a wait
            }
        }
        else
        {
            //move back
            Serial.println("Back");
            digitalWrite(motor1pin1, HIGH);
            digitalWrite(motor1pin2, LOW);
            digitalWrite(motor2pin1, LOW);
            digitalWrite(motor2pin2, HIGH);
            delay(1000);
            //stop motors
            digitalWrite(motor1pin1, LOW);
            digitalWrite(motor1pin2, LOW);
            digitalWrite(motor2pin1, LOW);
            digitalWrite(motor2pin2, LOW);
        }
    }
}
// SonarSensor function used to generate and read the ultrasonic wave & it takes the trigPIN and the echoPIN
//may need to return the distance instead. It can still do the other sensor initialization that it's doing, but returning the distance can be very useful.
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
                        //Trace 
                        
                        Serial.print("distance1: ");
                        Serial.print(distance1);
                        Serial.println(" cm");
                        
                        return distance1;
                        }// END SonarSensor FUNCTION

                    
