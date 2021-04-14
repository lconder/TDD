package usantatecla;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntervalTest {

    private Point left = new Point(-2.2);
    private Point right = new Point(4.4);
    private IntervalBuilder intervalBuilder;

    @BeforeEach
    public void before() {
        this.left = new Point(-2.2);
        this.right = new Point(4.4);
        this.intervalBuilder = new IntervalBuilder();
    }

    @Test
    public void givenIntervaOpenOpenlwhenIncludeWithIncludedValueThenTrue() {
        Interval interval = this.intervalBuilder.open(left.getEquals()).open(right.getEquals()).build();
        assertFalse(interval.include(left.getLess()));
        assertFalse(interval.include(left.getEquals()));
        assertTrue(interval.include(left.getGreater()));
        assertTrue(interval.include(right.getLess()));
        assertFalse(interval.include(right.getEquals()));
        assertFalse(interval.include(right.getGreater()));
    }

    @Test
    public void givenIntervaOpenOpenlwhenInc3ludeWithIncludedValueThenTrue() {
        Interval interval = this.intervalBuilder.closed(left.getEquals()).open(right.getEquals()).build();
        assertFalse(interval.include(left.getLess()));
        assertTrue(interval.include(left.getEquals()));
        assertTrue(interval.include(left.getGreater()));

        assertTrue(interval.include(right.getLess()));
        assertFalse(interval.include(right.getEquals()));
        assertFalse(interval.include(right.getGreater()));
    }

    @Test
    public void givenIntervaOpenOpenlwhenIncludeWit3hIncludedValueThenTrue() {
        Interval interval = this.intervalBuilder.open(left.getEquals()).closed(right.getEquals()).build();
        assertFalse(interval.include(left.getLess()));
        assertFalse(interval.include(left.getEquals()));
        assertTrue(interval.include(left.getGreater()));

        assertTrue(interval.include(right.getLess()));
        assertTrue(interval.include(right.getEquals()));
        assertFalse(interval.include(right.getGreater()));
    }

    @Test
    public void givenIntervaOpenOpenlwhenIncludeWithInclude5dValueThenTrue() {
        Interval interval = this.intervalBuilder.closed(left.getEquals()).closed(right.getEquals()).build();
        assertFalse(interval.include(left.getLess()));
        assertTrue(interval.include(left.getEquals()));
        assertTrue(interval.include(left.getGreater()));

        assertTrue(interval.include(right.getLess()));
        assertTrue(interval.include(right.getEquals()));
        assertFalse(interval.include(right.getGreater()));
    }

    @Test
    public void intervalsOpenIntersectedThenFalse() {
        Interval one = new IntervalBuilder().open(left.getEquals()).open(left.getGreater()).build();
        Interval two = new IntervalBuilder().open(right.getEquals()).open(right.getGreater()).build();
        assertFalse(one.intersects(two));
        two = new IntervalBuilder().open(left.getLess()).open(left.getEquals()).build();
        assertFalse(one.intersects(two));
    }

    @Test
    public void intervalsOpenIntersectedThenTrue() {
        Interval one = new IntervalBuilder().open(left.getEquals()).open(left.getGreater()).build();
        Interval two = new IntervalBuilder().open(left.getEquals()).open(right.getGreater()).build();
        assertTrue(one.intersects(two));
        one = new IntervalBuilder().open(left.getLess()).open(left.getGreater()).build();
        two = new IntervalBuilder().open(left.getEquals()).open(right.getEquals()).build();
        assertTrue(one.intersects(two));
        one = new IntervalBuilder().open(left.getLess()).open(left.getGreater()).build();
        two = new IntervalBuilder().open(left.getEquals()).open(right.getGreater()).build();
        assertTrue(one.intersects(two));
    }

    @Test
    public void intervalsCloseIntersectedThenFalse() {
        Interval one = new IntervalBuilder().closed(left.getEquals()).closed(left.getGreater()).build();
        Interval two = new IntervalBuilder().closed(right.getEquals()).closed(right.getGreater()).build();
        assertFalse(one.intersects(two));
    }
}
