#include <fstream>
#include <iostream>
#include <string>

#include "Rule.h"

using namespace std;

void Grammar::readFile(string filename){
    string line;
    ifstream computergram("computergram");
    while(getline(computergram, line)){
        theRules.push_back(line);
    }
    computergram.close();
}
void Grammar::printVector(){
    for(int i = 0; i < theRules.size(); i++){
      cout << theRules[i]+"\n";
    }
}
void Grammar::printRule(int index){
    cout << "--------------------\n";
    cout << theRules[index]+"\n";
}
string Grammar::extractMother(int index){
    int node, momPos;

    Rule tmpRule;

    momPos = 0;
    node = theRules[index].find(" --> ");

    while(momPos <= node){
        tmpRule.mother += theRules[index].at(momPos);
        momPos++;
    }

    cout << "Mother : "+ tmpRule.mother+"\n";

    return tmpRule.mother ;
}
vector <string> Grammar::extractDaughters(int index){
    int node, dauPos, comPos;
    bool found = false;

    Rule tmpRule;

    node = theRules[index].find(" --> ");
    dauPos = node + 5;

    while(dauPos != theRules[index].size()){
        tmpRule.daughter += theRules[index].at(dauPos);
        dauPos++;
    }

    if(tmpRule.daughter.find(",")){
        found = true;
        comPos= tmpRule.daughter.find(",");
    }

    if(found){
        dauPos = 0;
        string tmp;
        while(dauPos != comPos){
            tmp += tmpRule.daughter.at(dauPos);
            dauPos++;
        }
        theDaughters.push_back(tmp);
        tmp = "";
        while(comPos != tmpRule.daughter.size()){
            tmp += tmpRule.daughter.at(comPos);
            comPos++;
        }
        theDaughters.push_back(tmp);
    }
    else{
        theDaughters.push_back(tmpRule.daughter);
    }


    cout << "Daughter(s) 'string' : "+ tmpRule.daughter+"\n";
    return theDaughters;
}