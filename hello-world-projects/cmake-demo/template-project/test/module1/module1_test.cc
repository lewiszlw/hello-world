#include <gtest/gtest.h>

extern "C" {
#include "module1/module1.h"
}

TEST(Module1Test, module1_calc) {
    EXPECT_EQ(-1, module1_calc(1, 2));
    EXPECT_NE(0, module1_calc(1, 2));
}