package vip.retail.heartchart.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import vip.retail.heartchart.di.module.TestModule;

import com.jess.arms.di.scope.ActivityScope;

import vip.retail.heartchart.mvp.ui.activity.TestActivity;

@ActivityScope
@Component(modules = TestModule.class, dependencies = AppComponent.class)
public interface TestComponent {
    void inject(TestActivity activity);
}