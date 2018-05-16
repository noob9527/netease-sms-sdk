package cn.staynoob.neteasesms.util

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class RandomUtilKtTest {

    @Test
    @DisplayName("generateRandomString")
    fun generateRandomStringTest() {
        for (i in 1..20) {
            val res = generateRandomString(i)
            assertThat(res).hasSize(i)
        }
    }
}