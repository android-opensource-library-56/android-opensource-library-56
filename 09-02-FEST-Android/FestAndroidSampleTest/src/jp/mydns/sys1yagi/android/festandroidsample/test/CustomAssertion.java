package jp.mydns.sys1yagi.android.festandroidsample.test;

import jp.mydns.sys1yagi.android.festandroidsample.CustomClass;

import org.fest.assertions.api.AbstractAssert;

public class CustomAssertion extends
        AbstractAssert<CustomAssertion, CustomClass> {

    public CustomAssertion(CustomClass actual) {
        super(actual, CustomAssertion.class);
    }

    public CustomAssertion sumResult(int result) {

        if (this.actual.getSum() != result) {
            throw new AssertionError(String.format(
                    "Expected getSum() to be <%d> but was <%d>",
                    this.actual.getSum(), result));
        }

        return this;
    }
}