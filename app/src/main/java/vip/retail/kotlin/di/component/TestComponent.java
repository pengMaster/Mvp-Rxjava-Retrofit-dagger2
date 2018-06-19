package vip.retail.kotlin.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;
import vip.retail.kotlin.di.module.TestModule;
import vip.retail.kotlin.mvp.ui.activity.TestActivity;

@ActivityScope
@Component(modules = TestModule.class, dependencies = AppComponent.class)
public interface TestComponent {
    void inject(TestActivity activity);
}