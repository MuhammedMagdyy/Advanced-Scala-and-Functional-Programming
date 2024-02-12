package sectionFive.implicits

object PimpMyLibrary extends App {
  // implicit classes must take one and only argument 
  implicit class MathWithInt(value: Int) {
    def isEven: Boolean = value % 2 == 0
    def sqrt: Double = Math.sqrt(value)
  }
  
  new MathWithInt(43).isEven
  
  42.isEven // type enrichment = pimping (new MathWithInt(42).isEven)
  25.sqrt
}
