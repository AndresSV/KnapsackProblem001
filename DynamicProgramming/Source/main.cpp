#include <iostream>
#include <fstream>
#include <string>
#include <sstream>

int max(int a, int b) {
    return (a > b)? a : b;
}

void knapSack(int weight[], int values[], int bagCapacity, int items) {

    int matrix [items+1][bagCapacity+1];
    int bagCapacityCopy = bagCapacity;

    for (int i = 0; i <= items; i++) {
        for (int j = 0; j <= bagCapacity; j++) {
            if (i==0 || j==0) {
                matrix [i][j] = 0;
            }
            else if (weight[i-1] <= j) {
                matrix [i][j] = max(values[i-1] + matrix [i-1][j-weight[i-1]], matrix[i-1][j]);
            }
            else{
                matrix [i][j] = matrix [i-1][j];
            }
            printf("%d ", matrix[i][j]);
        }
        printf("\n");
    }

    int result = matrix[items][bagCapacity];
    printf("\n");
    printf("Result %d\t",result);
    printf("\n");

}

int main(int argc, char const *argv[])
{
  int totalNumberOfElements = 0, bagCapacity = 0;
  int* elements = NULL;
  int* weights = NULL;
  std::ifstream myfile (argv[1]);
  std::string line;

  if (myfile.is_open())
  {

    getline (myfile,line);

    totalNumberOfElements = std::stoi(line);
    elements = new int[totalNumberOfElements];
    weights = new int[totalNumberOfElements];

    getline (myfile,line);
    std::stringstream ss1(line);
    int n1;
    for (size_t i = 0; i < totalNumberOfElements; i++) {
      ss1 >> n1;
      elements[i] = n1;
    }

    getline (myfile,line);
    std::stringstream ss2(line);
    int n2;
    for (size_t i = 0; i < totalNumberOfElements; i++) {
      ss2 >> n2;
      weights[i] = n2;
    }

    getline (myfile,line);
    bagCapacity = std::stoi(line);

    myfile.close();
  } else {
    printf("WARNING! Unable to open file \n");
    return 0;
  }

  knapSack(weights, elements, bagCapacity, totalNumberOfElements);
  printf("\n");
  return 0;
}
