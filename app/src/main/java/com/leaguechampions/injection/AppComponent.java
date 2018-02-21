package com.leaguechampions.injection;

import com.leaguechampions.LeagueChampions;

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
        DataModule.class
})
public interface AppComponent extends AndroidInjector<LeagueChampions> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<LeagueChampions> {}
}
