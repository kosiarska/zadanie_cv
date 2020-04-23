package pl.michal.tretowicz.injection.component

import javax.inject.Singleton

import dagger.Component
import pl.michal.tretowicz.injection.module.ApplicationTestModule

@Singleton
@Component(modules = arrayOf(ApplicationTestModule::class))
interface TestComponent : ApplicationComponent
