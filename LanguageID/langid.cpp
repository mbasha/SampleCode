#include <cmath>
#include <math.h>
#include <string.h>
#include <iostream>
#include <iomanip>
#include <fstream>
#include <cstdlib>
#include <stdio.h>

using namespace std;

// Name:		Abrar Basha, Spring 2013
//                      Asgn #2, langid.cpp

// Header Comment: Program that takes in 5 known languages and compares with an unknown amount of unknown languages. 
//Will show the correlation coefficient with the known languages and ouputs which program it most likely is.
//
//Known Issues: This program does not take care of punctuation, which results in correlation coefficients that are slightly off.
//              This issue is most prevalant with the language Tagalog.
//
//Testing: Tests were done with own input, along with sample inputs from Glyder and King.
// ==========================================================

struct language
{  
   int freq[25];
   string name;
};

//creates frequency table
language readLang(){
   language lang;
   
   for(int i = 0; i < 25; i++){
      lang.freq[i] = 0;}
   
   cin >> lang.name;
   string token;
   while(true){
      cin>>token;
      if(token.compare("//") == 0){
         cin >> token;
         return lang;
      }//if
      else{
         lang.freq[token.size()-1]++;
      }//else
   }//while
}//readLang

//calculates correlation coefficient between two language frequency tables
double corCoef(language lang, language knownLang){
   double eX = 0, eY = 0, eXY = 0, eX2 = 0, eY2 = 0;//n = 25
   double r = 0;
   //calculate remaining variables
   for(int i = 0; i < 25; i++){
      eX += lang.freq[i];
      eY += knownLang.freq[i];
      eXY += (lang.freq[i]*knownLang.freq[i]);
      eX2 += pow(lang.freq[i], 2);
      eY2 += pow(knownLang.freq[i], 2);
   }
   
   r = ((25*eXY)-(eX*eY))/(sqrt((25*eX2-pow(eX,2))*(25*eY2-pow(eY,2))));
   return r;
}//corCoef

int main (int argc, char *argv[] ) {
    //when running, cin first (to get language name), then call readLang to get known frequency tables
    language known1, known2, known3, known4, known5;
    string temp;
    cin >> temp;
    known1 = readLang();
    cin >> temp;
    known2 = readLang();
    cin >> temp;
    known3 = readLang();
    cin >> temp;
    known4 = readLang();
    cin >> temp;
    known5 = readLang();
    while(true){
       string temp2;
       cin >> temp2;
       if(temp2.compare("//") == 0){
          language testLanguage;
          testLanguage = readLang();
          double coefs[5] = {0,0,0,0,0};
          string nameArray[5];
          coefs[0] = corCoef(testLanguage, known1);
          coefs[1] = corCoef(testLanguage, known2);
          coefs[2] = corCoef(testLanguage, known3);
          coefs[3] = corCoef(testLanguage, known4);
          coefs[4] = corCoef(testLanguage, known5);
          nameArray[0] = known1.name;
          nameArray[1] = known2.name;
          nameArray[2] = known3.name;
          nameArray[3] = known4.name;
          nameArray[4] = known5.name;
          cout<<"Text " <<testLanguage.name<<"\n";          
          cout<<"   Correlation with "<<nameArray[0]<< " : "<<coefs[0]<< "\n";
          cout<<"   Correlation with "<<nameArray[1]<< " : "<<coefs[1]<< "\n";
          cout<<"   Correlation with "<<nameArray[2]<< " : "<<coefs[2]<< "\n";
          cout<<"   Correlation with "<<nameArray[3]<< " : "<<coefs[3]<< "\n";
          cout<<"   Correlation with "<<nameArray[4]<< " : "<<coefs[4]<< "\n";
          double bestCoef = coefs[0];
          for(int i = 1; i < 5; i++){
             if(coefs[i] > bestCoef){
                bestCoef = coefs[i];
             }
          }
          for(int i = 0; i < 5; i++){
             if(bestCoef == coefs[i]){
                cout<<"Text " <<testLanguage.name<< " is most likely written in " <<nameArray[i]<<".\n";
             }
          }
       }//if
       else {return 0;}
    }//while    
   return 0;
}//main
