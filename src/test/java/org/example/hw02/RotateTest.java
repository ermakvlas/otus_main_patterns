package org.example.hw02;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

class RotateTest {

    private final Rotatable rotatable = mock(Rotatable.class);
    private final Rotate rotate = new Rotate(rotatable);

    @Test
    public void given_rotatable_when_rotate_then_rotate_to_correct_direction() {
        when(rotatable.getDirectionsNum()).thenReturn(360);
        when(rotatable.getDirection()).thenReturn(1);
        when(rotatable.getAngularVelocity()).thenReturn(45);
        rotate.execute();

        verify(rotatable, times(1)).setDirection(eq(46));
    }

    @Test
    public void given_rotatable_when_directionNum_is_zero_then_exception_thrown() {
        when(rotatable.getDirectionsNum()).thenReturn(0);
        when(rotatable.getDirection()).thenReturn(100);
        when(rotatable.getAngularVelocity()).thenReturn(20);
        assertThatThrownBy(rotate::execute).isInstanceOf(IllegalStateException.class);
    }
}