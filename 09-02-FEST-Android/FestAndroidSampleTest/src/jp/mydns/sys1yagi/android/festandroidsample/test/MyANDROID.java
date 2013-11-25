package jp.mydns.sys1yagi.android.festandroidsample.test;

import jp.mydns.sys1yagi.android.festandroidsample.CustomClass;

import org.fest.assertions.api.ANDROID;

public class MyANDROID extends ANDROID {

    public static CustomAssertion assertThat(CustomClass custom) {
        return new CustomAssertion(custom);
    }
}
