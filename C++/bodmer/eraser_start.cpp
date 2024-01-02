#include <string>
#include <iostream>
#include <fstream>
using namespace std;

bool is_vowel(char c);
bool is_cons(char c);

int main() {
    ifstream thefile;
    thefile.open("afile_from_bodmer"); // remove ../ before submition!!!!!!!!!

    cout << "elim which?: enter 1 for cons or 2 for vowels\n";
    int choice;
    cin >> choice;

    char star = '*';

    string s;
    while(getline(thefile,s)) { // puts whole next line into s
        /* walk thru s doing the necessary thing to consonants or vowels */
        //cout << s;

           if(choice == 1){
               for(int i = 0; i <= s.size(); i++){
                   if(is_cons(s[i])){
                       cout << '*';
                   }
                   else{
                       cout << s[i];
                   }
               }
           }
           else if(choice == 2){
               for(int i = 0; i <= s.size(); i++){
                   if(is_vowel(s[i])){
                       cout << '*';
                   }
                   else{
                       cout << s[i];
                   }
               }
           }
           else{cout << "error!";}
	   cout << endl;


    }



}

#include "eraser_start.h"
