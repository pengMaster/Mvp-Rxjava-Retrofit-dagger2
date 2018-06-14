package vip.retail.heartchart.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import vip.retail.heartchart.mvp.contract.TestContract;
import vip.retail.heartchart.mvp.model.TestModel;


@Module
public class TestModule {
    private TestContract.View view;

    /**
     * 构建TestModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public TestModule(TestContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    TestContract.View provideTestView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    TestContract.Model provideTestModel(TestModel model) {
        return model;
    }
}