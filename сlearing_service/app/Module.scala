import com.google.inject.{AbstractModule, Scopes}
import services.{ClearingService, ClearingServiceImpl}

import java.time.Clock

/**
 * This class is a Guice module that tells Guice how to bind several
 * different types. This Guice module is created when the Play
 * application starts.

 * Play will automatically use any class called `Module` that is in
 * the root package. You can create modules in other locations by
 * adding `play.modules.enabled` settings to the `application.conf`
 * configuration file.
 */
class Module extends AbstractModule {

  protected def bindSingleton[T, C <: T](implicit tM: Manifest[T], tC: Manifest[C]) {
    bind(tM.runtimeClass.asInstanceOf[Class[T]]).to(tC.runtimeClass.asInstanceOf[Class[C]]).in(Scopes.SINGLETON)
  }

  override def configure() = {

    // Use the system clock as the default implementation of Clock
    bind(classOf[Clock]).toInstance(Clock.systemDefaultZone)
    // Ask Guice to create an instance of ApplicationTimer when the
    // application starts.
//    bind(classOf[ApplicationTimer]).asEagerSingleton()
    // Set AtomicCounter as the implementation for Counter.
    bindSingleton[ClearingService, ClearingServiceImpl]
  }

}
