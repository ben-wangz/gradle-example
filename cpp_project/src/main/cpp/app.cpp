#include <iostream>
#include <stdlib.h>
#include "app.h"

std::string app::Greeter::greeting() {
    return std::string("Hello, World!");
}

int main () {
    app::Greeter greeter;
    std::cout << greeter.greeting() << std::endl;
    return 0;
}