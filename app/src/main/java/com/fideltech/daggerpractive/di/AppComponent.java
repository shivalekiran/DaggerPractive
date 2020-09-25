package com.fideltech.daggerpractive.di;

import android.app.Application;

import com.fideltech.daggerpractive.BaseApplication;
import com.fideltech.daggerpractive.SessionManager;
import com.fideltech.daggerpractive.ui.auth.AuthViewModel;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                ActivityBuilderModule.class,
                ApplicationModule.class,
                ViewModelFactoryModule.class,
        }
)
public interface AppComponent extends AndroidInjector<BaseApplication> {
    SessionManager sessionManager();

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    ;
}
