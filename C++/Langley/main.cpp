#include <string>
#include <vector>
#include <iostream>
#include <fstream>
using namespace std;

//bool is_an_email(string s);
bool is_an_email(string possibleEmail){
    bool isEmail;
    size_t at_pos;
    at_pos = possibleEmail.find('@');

    if((at_pos > 0)&&(at_pos < possibleEmail.size()-1)&&(at_pos != string::npos)){
        isEmail = true;}
    else {
        isEmail = false;}

    return isEmail;
}

int main() {

    // admin
    string filename;
    cout << "which file\n";
    cin >> filename;
    string keyword = "TOP-SECRET";
    string keyLtr;

    // file open
    ifstream f;
    f.open(filename);
    if(!f) { cout << "could not open\n"; return 0; }

    // declarations
    string line;
    string tmp = "";
    string s = "";
    string possibleEmail;
    vector <string> words;
    bool found = false;

    // test emails
    string testEmail1 = "fifarrel@tcd.ie";
    string testEmail2 = "@saffron.com";
    string testEmail3 = "whaattttEamil.com@";


    // converts file to vector
     while(getline(f,line)){
         tmp = "";
         for(int v = 0; v < line.size(); v++){
             s += line[v];
         }
         for(int i = 0; i < line.size();i++){
            switch(line[i]){
                case ' ':
                case ',':
                case '\n':
                    if(tmp !=""){
                        words.push_back(tmp);
                        tmp = "";
                    }
                    break;
                default:
                    tmp += line[i];
                    if (i+1 == line.size()){
                        words.push_back(tmp);
                        break;
                    }
            break;
            }
         }
      }
     f.close();

     // finds keyword
     for(int k = 0; k < words.size(); k++){
        // cout << words[k];
         if(words[k] == "TOP-SECRET"){
             found = true;
         }
     }

     // output emails
     if(found){
         for(int h = 0; h < words.size(); h++){
             if(is_an_email(words[h])){
                 cout << words[h]+"\n";
             }
         }
     }

}


