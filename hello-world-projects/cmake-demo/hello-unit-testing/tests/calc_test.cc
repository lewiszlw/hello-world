//
// Created by Linwei Zhang on 2022/4/11.
//

#include <gtest/gtest.h>

extern "C" {
#include "calc.h"
}

TEST(CalcTest, add) {
    EXPECT_EQ(3, add(1, 2));
    EXPECT_NE(4, add(1, 2));
}