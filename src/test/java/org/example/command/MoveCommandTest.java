package org.example.command;

import org.example.model.Coordinates;
import org.example.model.Movable;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

class MoveCommandTest {
    private final Movable movable = mock(Movable.class);
    private final MoveCommand move = new MoveCommand(movable);

    /**
     * Для объекта, находящегося в точке (12, 5) и движущегося со скоростью (-7, 3) движение меняет положение объекта на (5, 8)
     */
    @Test
    public void given_movable_when_move_then_move_to_correct_position() {
        when(movable.getPosition()).thenReturn(new Coordinates(12, 5));
        when(movable.getVelocity()).thenReturn(new Coordinates(-7, 3));
        move.execute();

        verify(movable, times(1)).setPosition(argThat(coordinates -> coordinates.getX() == 5 && coordinates.getY() == 8));
    }

    /**
     * Попытка сдвинуть объект, у которого невозможно прочитать положение в пространстве, приводит к ошибке
     */
    @Test
    public void given_movable_without_start_position_when_move_then_exception_thrown() {
        when(movable.getPosition()).thenReturn(null);

        assertThatThrownBy(move::execute).isInstanceOf(IllegalStateException.class).hasMessageContaining("There is no start position for movable object");
    }

    /**
     * Попытка сдвинуть объект, у которого невозможно прочитать значение мгновенной скорости, приводит к ошибке
     */
    @Test
    public void given_movable_without_velocity_when_move_then_exception_thrown() {
        when(movable.getPosition()).thenReturn(new Coordinates(12, 5));
        when(movable.getVelocity()).thenReturn(null);

        assertThatThrownBy(move::execute).isInstanceOf(IllegalStateException.class).hasMessageContaining("There is no velocity for movable object");
    }

    /**
     * Попытка сдвинуть объект, у которого невозможно изменить положение в пространстве, приводит к ошибке
     */
    @Test
    public void shouldThrowExceptionWhenCanNotChangePosition() {
        when(movable.getPosition()).thenReturn(new Coordinates(12, 5));
        when(movable.getVelocity()).thenReturn(new Coordinates(-7, 3));
        doThrow(new RuntimeException()).when(movable).setPosition(any());

        assertThatThrownBy(move::execute).isInstanceOf(RuntimeException.class);
    }

}