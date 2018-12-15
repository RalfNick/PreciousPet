package com.ralf.www.module_login_register.dg.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.ralf.www.module_login_register.dg.module.RegisterUserModule;
import com.ralf.www.module_login_register.mvp.contact.RegisterUserContact;
import com.ralf.www.module_login_register.mvp.ui.RegisterUserActivity;

import dagger.BindsInstance;
import dagger.Component;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name RegisterUserComponent
 * @email -
 * @date 2018/12/12 上午9:46
 **/
@ActivityScope
@Component(modules = RegisterUserModule.class, dependencies = AppComponent.class)
public interface RegisterUserComponent {

    void inject(RegisterUserActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        RegisterUserComponent.Builder view(RegisterUserContact.View view);
        RegisterUserComponent.Builder appComponent(AppComponent appComponent);
        RegisterUserComponent build();
    }
}
