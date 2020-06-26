package hello.world.models

case class Counter(value: Integer = 1)

case class ApplicationContainer(var app: Application = Application())

case class Application
(
  counter1: Counter = Counter(),
  counter2: Counter = Counter()
)

