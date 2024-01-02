#include <string>
#include <vector>
using namespace std;

struct Rule{
    string mother;
    string daughter;
};

class Grammar{

private:
    vector<string> theRules;
    vector<string> theDaughters;

public:
    void readFile(string filename);
    void printVector();
    string extractMother(int index);
   vector <string> extractDaughters(int index);
    void printRule(int index);
};