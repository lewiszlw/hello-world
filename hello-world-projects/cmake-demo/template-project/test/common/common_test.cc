#include <gtest/gtest.h>

extern "C" {
#include "common/common.h"
}

TEST(CommonTest, common_calc) {
    EXPECT_EQ(3, common_calc(1, 2));
    EXPECT_NE(4, common_calc(1, 2));
}