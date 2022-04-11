#include <gtest/gtest.h>

extern "C" {
#include "module2/module2.h"
}

TEST(Module2Test, module2_calc) {
    EXPECT_EQ(2, module2_calc(1, 2));
    EXPECT_NE(3, module2_calc(1, 2));
}