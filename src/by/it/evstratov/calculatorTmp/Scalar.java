package by.it.evstratov.calculatorTmp;

class Scalar extends Var {

    private final double value;

    public Scalar(double value) {
        this.value = value;
    }
    public Scalar(String strValue) {
        this.value = Double.parseDouble(strValue);
    }
    public Scalar(Scalar otherScalar) {
        this.value = otherScalar.value;
    }
    
    public Var addWith(Scalar leftScalarInExpression){
        double otherValue = leftScalarInExpression.value;
        double result = this.value + otherValue;
        return new Scalar(result);
    }

    public Var addWith(Vector leftVectorInExpression){
        return leftVectorInExpression.add(this);
    }

    public Var addWith(Matrix leftMatrixInExpression){
        return leftMatrixInExpression.add(this);
    }

     @Override
     public Var add(Var other) {
         return other.addWith(this);
     }


     @Override
     public Var sub(Var other) {
         if (other instanceof Scalar) {
             double otherValue = ((Scalar) other).value;
             double result = this.value - otherValue;
             return new Scalar(result);
         }
         else
             return other.sub(this).mul(new Scalar(-1));
     }

     @Override
     public Var mul(Var other) {
         if (other instanceof Scalar) {
             double otherValue = ((Scalar) other).value;
             double result = this.value * otherValue;
             return new Scalar(result);
         }
         else
             return other.mul(this);
     }

     @Override
     public Var div(Var other) {
         if (other instanceof Scalar) {
             double otherValue = ((Scalar) other).value;
             if (otherValue==0){
                 System.out.println("Division by zero");
                 return null; //stub
             }
             double result = this.value / otherValue;
             return new Scalar(result);
         }
         else
             return super.div(other);
     }

    @Override
    public String toString() {
        return Double.toString(value);
    }

     public double getValue() {
         return value;
     }
 }
