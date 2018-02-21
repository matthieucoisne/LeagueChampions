package com.leaguechampions.injection;

import com.leaguechampions.LeagueChampions;
import com.leaguechampions.injection.viewmodel.ViewModelModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityBuilder.class,

        AppModule.class,
        NetworkModule.class,
        DataModule.class,
        ViewModelModule.class,
})
public interface AppComponent extends AndroidInjector<LeagueChampions> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<LeagueChampions> {}
}
