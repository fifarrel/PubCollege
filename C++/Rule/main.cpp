#include <iostream>
#include <fstream>
#include "Rule.h"
#include <vector>

using namespace std;

int main() {
    Grammar grammar; //text file of rules
    Rule rule1; // first rule
    int index;

    grammar.readFile("computergram");

    cout << "Enter a number to access (or -1 to exit!): ";
    cin >> index;

    while(index != -1){
        grammar.printRule(index);
        rule1.mother = grammar.extractMother(index);
        //rule1.daughter = grammar.extractDaughters(index);

        cout << "Enter a number to access (or -1 to exit!): ";
        cin >> index;
    }
}
