package ypractice;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InitTest {

    @Test
    void sanityCheck() {
        assertThat(2 + 2).isEqualTo(4);
    }
}